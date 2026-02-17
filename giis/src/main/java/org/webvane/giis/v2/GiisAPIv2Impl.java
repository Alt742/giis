package org.webvane.giis.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.webvane.common.Pair;
import org.webvane.framework.fw;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webvane.framework.api.FMethodCallAPI;
import org.webvane.framework.fwpojo;
import org.webvane.framework.mvc.ResultFW;
import org.webvane.giis.dmdk.request.dmdk.GoznakAPI;
import org.webvane.giis.dmdk.request.dmdk.Envelope;
import org.webvane.giis.dmdk.request.dmdk.elements.response.CheckGetBatchResponseData;
import org.webvane.giis.dmdk.request.dmdk.elements.response.contractor.ResultContractor;
import org.webvane.giis.model.*;
import org.webvane.giis.v2.model.*;

import java.util.*;

public class GiisAPIv2Impl implements GiisAPIv2 {
    private static final Logger log = LoggerFactory.getLogger(GiisAPIv2.class);

    private final fwpojo<IntAction> intAction;
    private final fwpojo<IntRequestStatus> intRequestStatus;
    private final fwpojo<IntProductProces> intProductProces;

    private final fwpojo<IntContractor> intContractor;
    private final fwpojo<IntBatchBrief> intBatchBrief;
    private final fwpojo<IntUinStatus> intUinStatus;
    private GoznakAPI goznakAPI;

    public GiisAPIv2Impl(FMethodCallAPI methodCallAPI, String soapAddress, String token) {
        goznakAPI = new GoznakAPI(soapAddress, token);

        intAction = new fwpojo<>(methodCallAPI, IntAction.class, "Integration", "IntAction");
        intRequestStatus = new fwpojo<>(methodCallAPI, IntRequestStatus.class, "IntGIIS",
                "IntRequestStatus");

        intContractor = new fwpojo<>(methodCallAPI, IntContractor.class, "Integration",
                "IntContractor");
        intBatchBrief = new fwpojo<>(methodCallAPI, IntBatchBrief.class, "Integration",
                "IntBatchBrief");

        intProductProces = new fwpojo<>(methodCallAPI, IntProductProces.class, "IntGIIS",
                "IntProductProces");
        intUinStatus = new fwpojo<>( methodCallAPI, IntUinStatus.class, "IntGIIS",
                "IntUinStatus" );
    }

    @Override
    public ResultFW registerRequest(String requestType, Map<String, Object> args) {
        Long requestId = 1L; // TODO get request API
        ResultFW res = new ResultFW();
        try {
            Envelope env = goznakAPI.sendReqiest(requestType, args);
            String status = env.getBody().getResponse().getResponseData().getStatus();

            IntAction action = new IntAction();
            IntRequestStatus request_status = new IntRequestStatus();
            action.type = requestType;
            action.status = status;

            if (!status.equals("ACCEPTED")) {
                action.error = env.getBody().getResponse().getResponseData().getError();
                Long intreqId = intAction.crLong("saveIntaction", action);
                throw new Exception("ERROR STATUS IN RESPONSE INCORRECT: "
                        + status + " ERROR MSG: "
                        + env.getBody().getResponse().getResponseData().getError());
            }

            request_status.requestType = requestType;
            request_status.args = JaxbToString(args);
            request_status.status = status;

            String messageId = env.getBody().getResponse().getResponseData().getMessageId();
            action.sysMessageId = messageId;
            request_status.messageId = messageId;

            String result = JaxbToString(env.getBody().getResponse());
            action.response = result;
            request_status.response = result;
            Long intreqId = intAction.crLong("saveIntaction", action);
            request_status.action_id = intreqId;
            Long reqestId = intRequestStatus.crLong("saveIntrequeststatus", request_status);

            res.success = true;
            res.params = new LinkedHashMap<>();
            res.params.put("requestId", intreqId);
        } catch (Exception e) {
            log.error("", e);
            System.out.println(e.getMessage());
            res.params = new LinkedHashMap<>();
            res.params.put("error", e.getMessage());
            res.success = false;
        }
        return res;
    }

    @Override
    public CheckResult checkRequest(Long id) {
        CheckResult result = new CheckResult();
        IntAction action = intAction.info("infoIntaction", id);

        IntRequestStatus requestStatus = intRequestStatus.firstFromList("listIntrequeststatus",
                fw.params().p("message_id", action.sysMessageId));
        if (requestStatus == null) {
            result.status = "NOT FOUND";
            return result;
        }

        if (requestStatus.status.equals("PREPARED")) {
            Map<String, Object> out = new HashMap<>();
            out.put("response", readStringAsMap( requestStatus.response ));
            result.data = out;
            result.status = requestStatus.status;
            return result;
        }

        Map<String, Object> arg = new HashMap<String, Object>();
        arg.put("messageId", requestStatus.messageId);
        try {
            Envelope env = goznakAPI.sendReqiest(GiisCheck.giisChcemMapping(requestStatus.requestType), arg);

            String status = env.getBody().getResponse().getResponseData().getStatus();
            requestStatus.status = status;
            String response = JaxbToString(env.getBody().getResponse().getResponseData());
            requestStatus.response = response;
            intRequestStatus.cr("updateIntrequeststatus", requestStatus);
            Map<String, Object> out = new HashMap<>();
            out.put("response", readStringAsMap( response ));
            result.data = out;
            result.status = status;

            if (status.equals("PREPARED")) {
                if ("CheckGetContractorRequest".equals(GiisCheck.giisChcemMapping(requestStatus.requestType))) {
                    List<IntContractor> pData = GiisCheck.setContractor(env.getBody().getResponse().getResponseData());
                    for (IntContractor element : pData) {
                        intContractor.cr("saveIntcontracor", element);
                    }
                    if (pData.isEmpty()) {
                        IntContractor ic = new IntContractor();
                        ic.inn = (new ObjectMapper().readValue(requestStatus.args, HashMap.class)).get("inn").toString();
                        intContractor.cr("saveIntcontracor", ic);
                    }
                }
                if ("CheckGetBatchBriefRequest".equals(GiisCheck.giisChcemMapping(requestStatus.requestType))) {
                    List<IntBatchBrief> pData = GiisCheck.setBatchBrief(env.getBody().getResponse().getResponseData());
                    for (IntBatchBrief element : pData) {
                        intBatchBrief.cr("saveIntbatchbrief", element);
                    }
                }
            }
        } catch (Exception e) {
            result.status = "REQUEST FAILED";
            return result;
        }

        return result;
    }

    @Override
    public OrgInfo checkOrg(Long inn) {
        IntContractor requestStatus = intContractor.firstFromList("listIntcontracor",
                fw.params().p("inn", inn).sortBy("update_time").desc());
        OrgInfo result = new OrgInfo();
        if (requestStatus == null) {
            result.status = "RESPONSE_NOT_FOUND";
            return result;
        }
        if (requestStatus.data == null) {
            result.status = "NOT_FOUND_IN_GIIS";
            return result;
        }

        result.status = "OK";
        result.data = readUsingXMLMapper(requestStatus.data, ResultContractor.class);//readStringAsMap( requestStatus.data );
        result.updateTime = requestStatus.updateTime;
        return result;
    }

    @Override
    public ResultFW setPropucts ( List<String> uins ){
        Long requestId = 1L; // TODO get request API
        ResultFW res = new ResultFW();

        IntAction action = new IntAction();
        action.type = "SendGetBatchRequest";
        action.status = "ACCEPTED";
        Long intreqId = intAction.crLong("saveIntaction", action);

        IntProductProces ipp = new IntProductProces();
        ipp.status = ProductProcesStatus.NEW;
        ipp.action_id = intreqId;
        ipp.id = intProductProces.crLong("saveIntproductproces",ipp);

        boolean success = false;
        for (String uin : uins) {
            try {
                if(!checkDublicateAndSave(intreqId, uin)) {
                    success = true;
                    continue;
                }

                Map<String, Object> args = new HashMap<>();
                args.put("uin", uin);
                Envelope env = goznakAPI.sendReqiest("SendGetBatchRequest", args);
                String status = env.getBody().getResponse().getResponseData().getStatus();

                if (!status.equals("ACCEPTED")) {
                    action.error = env.getBody().getResponse().getResponseData().getError();
                    throw new Exception("ERROR STATUS IN RESPONSE INCORRECT: "
                            + status + " ERROR MSG: "
                            + env.getBody().getResponse().getResponseData().getError());
                }

                IntRequestStatus request_status = new IntRequestStatus();
                request_status.requestType = "SendGetBatchRequest";
                request_status.args = JaxbToString(args);
                request_status.status = status;

                request_status.messageId = env.getBody().getResponse().getResponseData().getMessageId();;

                String result = JaxbToString(env.getBody().getResponse());
                action.response = result;
                request_status.response = result;

                request_status.action_id = intreqId;
                Long reqestId = intRequestStatus.crLong("saveIntrequeststatus", request_status);
                intUinStatus.crLong("saveIntuinstatus", new IntUinStatus(intreqId, uin, "", UinValidStatus.UNKNOWN, ""));
                success = true;
            } catch (Exception e) {
                log.error("", e);
            }
        }
        ipp.status = ProductProcesStatus.INPROGRESS;
        intProductProces.cr("updateIntproductproces", ipp);

        if(success){
            res.success = true;
            res.params = new LinkedHashMap<>();
            res.params.put("requestId", intreqId);
        }
        return res;
    }

    @Override
    public ResultFW delProduct ( Long id ){
        ResultFW res = new ResultFW();

        IntAction action = intAction.info("infoIntaction", id);
        if (action == null) {
            res.success = false;
            return res;
        }
        intAction.cr("removeIntaction",action);

        IntProductProces ipp = intProductProces.firstFromList("listIntproductproces",
                fw.params().p("action_id", id));
        if (ipp == null) {
            res.success = false;
            return res;
        }
        intProductProces.cr("removeIntproductproces",ipp);

        List<IntUinStatus> isl = intUinStatus.list("listIntuinstatus", fw.params().p("action_id", id));
        if (isl == null) {
            res.success = true;
            return res;
        }

        for(IntUinStatus uinStatus : isl){
            IntRequestStatus requestStatus = intRequestStatus.firstFromList("listIntrequeststatus",
                    fw.params().p("message_id", uinStatus.messageId));
            if(requestStatus != null){
                intRequestStatus.cr("removeIntrequeststatus", requestStatus);
            }
            intUinStatus.cr("removeIntuinstatus", uinStatus);
        }

        res.success = true;
        return res;
    }

    @Override
    public CheckPropuctResult getPropuctMergedInfo (Long id ){

        CheckPropuctResult result = new CheckPropuctResult();
        IntAction action = intAction.info("infoIntaction", id);
        if (action == null) {
            result.status = "ERROR";
            return result;
        }
        IntProductProces ipp = intProductProces.firstFromList("listIntproductproces",
                fw.params().p("action_id", action.id));
        if (ipp == null) {
            result.status = "ERROR";
            return result;
        }

        List<IntUinStatus> isl = intUinStatus.list("listIntuinstatus", fw.params().p("action_id", action.id));
        if (isl == null) {
            result.status = ProductProcesStatus.ERROR;
            return result;
        }
        result.uins = new LinkedList<>();
        IntUinStatus validUin = null;
        Long variant_max = new Long("0");
        for(IntUinStatus is: isl){
            if(is.valid.equals(UinValidStatus.OK))
                validUin = is;
            result.uins.add(new ShortResultBatch( is.name, is.uin, is.valid));
            if(is.variant==null)
                continue;
            if(is.variant > variant_max)
                variant_max = is.variant;
        }

        result.variants = new LinkedList<>(); //List<Pair<List<ShortResultBatch>, ResultBatch>>
        for(Long i = new Long("1"); i<(variant_max+1); ++i) {
            result.variants.add(new Pair<>());
            result.variants.get(result.variants.size()-1).first = new LinkedList<>();
            String msgId = null;
            for (IntUinStatus is : isl){
                if(is.variant==null)
                    continue;
                if (is.variant.equals(i)) {
                    result.variants.get(result.variants.size() - 1).first.add(new ShortResultBatch(is.name, is.uin, is.valid));
                    msgId = is.messageId;

                    if(is.difference!=null){
                        IntRequestStatus requestStatus = intRequestStatus.firstFromList("listIntrequeststatus",
                                fw.params().p("message_id", msgId));
                        Map value = readStringAsMap( requestStatus.response );
                        result.variants.get(result.variants.size() - 1).second = getDifference(is.difference, value);
                    }
                }

            }
            if(msgId==null){
                result.variants.remove(result.variants.size() - 1);
                continue;
            }


            //if(cgbr.getResult()!=null)
                //result.variants.get(0).second = cgbr.getResult().get(0);
        }

        if(validUin == null){
            result.status = ProductProcesStatus.ERROR;
            return result;
        }

        IntRequestStatus requestStatus = intRequestStatus.firstFromList("listIntrequeststatus",
                fw.params().p("message_id", validUin.messageId));
        if(requestStatus == null){
            result.status = ProductProcesStatus.ERROR;
            return result;
        }

        CheckGetBatchResponseData cgbr = readUsingXMLMapper(requestStatus.response, CheckGetBatchResponseData.class);
        result.data = cgbr.getResult().get(0);

        result.status = ProductProcesStatus.FINISHED;

        return result;
    }

    @Override
    public CheckUinsResult getPropuctUinStatus (Long id){
        CheckUinsResult result = new CheckUinsResult();


        IntAction action = intAction.info("infoIntaction", id);
        if (action == null) {
            result.status = ProductProcesStatus.ERROR;
            return result;
        }

        IntProductProces ipp = intProductProces.firstFromList("listIntproductproces",
                fw.params().p("action_id", id));
        if (ipp == null) {
            result.status = ProductProcesStatus.ERROR;
            return result;
        }
        result.status = ipp.status;

        List<IntUinStatus> isl = intUinStatus.list("listIntuinstatus", fw.params().p("action_id", action.id));
        if (isl == null) {
            result.status = ProductProcesStatus.ERROR;
            return result;
        }

        result.uins = new LinkedList<>();
        for(IntUinStatus is: isl){
            result.uins.add(new ShortResultBatch( is.name, is.uin, is.valid));
        }

        return result;
    }

    @Override
    public CheckPropuctStatus getPropuctStatus (Long id){
        CheckPropuctStatus result = new CheckPropuctStatus();

        IntAction action = intAction.info("infoIntaction", id);
        if (action == null) {
            result.status = "NOT FOUND";
            return result;
        }

        IntProductProces ipp = intProductProces.firstFromList("infoIntproductproces",
                fw.params().p("action_id", action.id));
        if (ipp == null) {
            result.status = "ERROR";
            return result;
        }

        result.status = ipp.status;
        return result;
    }

    @Override
    public ResultFW finishPropuct (Long id){
        ResultFW res = new ResultFW();
        res.success = true;

        IntAction action = intAction.info("infoIntaction", id);
        if (action == null) {
            res.success = false;
            res.params = new LinkedHashMap<>();
            res.params.put("error", "task not found");
            return res;
        }

        IntProductProces ipp = intProductProces.firstFromList("listIntproductproces",
                fw.params().p("action_id", action.id));
        if (ipp == null) {
            res.success = false;
            res.params = new LinkedHashMap<>();
            res.params.put("error", "task not found");
            return res;
        }
        ipp.status = ProductProcesStatus.FINISHED;
        intProductProces.cr("updateIntproductproces", ipp);
        return res;
    }

    @Override
    public ResultFW deletePropuctUin (Long id, String uin ){
        ResultFW res = new ResultFW();
        IntAction action = intAction.info("infoIntaction", id);
        if (action == null) {
            res.success = false;
            res.params = new LinkedHashMap<>();
            res.params.put("error", "task not found");
            return res;
        }

        List<IntUinStatus> isl = intUinStatus.list("listIntuinstatus", fw.params().p("action_id", action.id));

        if (isl == null) {
            res.success = false;
            res.params = new LinkedHashMap<>();
            res.params.put("error", "task not found");
            return res;
        }

        res.success = false;
        for (IntUinStatus irs: isl){
            if(uin.equals(irs.uin)){
                intUinStatus.cr("removeIntuinstatus", irs);
                res.success = true;
                break;
            }
        }
        if(!res.success){
            res.params = new LinkedHashMap<>();
            res.params.put("error", "uin not found");
        }

        return res;
    }

    @Override
    public ResultFW addPropuctUin (Long id, String uin ){
        ResultFW res = new ResultFW();
        Long intreqId = id;

        IntAction action = intAction.info("infoIntaction", id);
        if (action == null) {
            res.success = false;
            res.params = new LinkedHashMap<>();
            res.params.put("error", "task not found");
            return res;
        }

        res.success = true;
        try{
            if(!checkDublicateAndSave(intreqId, uin)) {
                res.success = false;
                return res;
            }

            Map<String, Object> args = new HashMap<>();
            args.put("uin", uin);
            Envelope env = goznakAPI.sendReqiest("SendGetBatchRequest", args);
            String status = env.getBody().getResponse().getResponseData().getStatus();

            if (!status.equals("ACCEPTED")) {
                action.error = env.getBody().getResponse().getResponseData().getError();
                throw new Exception("ERROR STATUS IN RESPONSE INCORRECT: "
                        + status + " ERROR MSG: "
                        + env.getBody().getResponse().getResponseData().getError());
            }

            IntRequestStatus request_status = new IntRequestStatus();
            request_status.requestType = "SendGetBatchRequest";
            request_status.args = mapper.writeValueAsString(args);
            request_status.status = status;

            request_status.messageId = env.getBody().getResponse().getResponseData().getMessageId();

            String result = mapper.writeValueAsString(env.getBody().getResponse());
            action.response = result;
            request_status.response = result;

            request_status.action_id = intreqId;
            intRequestStatus.crLong("saveIntrequeststatus", request_status);
            intUinStatus.crLong("saveIntuinstatus", new IntUinStatus(intreqId, uin, "",
                    UinValidStatus.UNKNOWN, request_status.messageId));
        } catch (Exception e) {
            log.error("", e);
            res.params = new LinkedHashMap<>();
            res.params.put("error", e.getMessage());
            res.success = false;
        }

        if(res.success){
            res.params = new LinkedHashMap<>();
            res.params.put("requestId", intreqId);
        }
        return res;
    }

    private Map<String, String> getDifference(String difference, Map data){
        Map<String, String> out = new HashMap<>();
        List<String> diff = readUsingXMLMapper(difference, List.class);
        for(String url: diff){
            String[] demons = url.split("\\.");
            Map subMap = ((List<Map>)data.get("result")).get(0);
            String value = new String("");
            for (String demon: demons){
                if(subMap.get(demon) instanceof Map){
                    subMap  = (Map) subMap.get(demon);
                }else {
                    value = subMap.get(demon).toString();
                }
            }
            out.put(url, value);
        }

        return out;
    }

    private boolean checkDublicateAndSave(Long intreqId, String uin){
        List<IntUinStatus> iUin = intUinStatus.list("listIntuinstatus", fw.params().p( "uin",  uin));
        for (IntUinStatus euin : iUin) {
            if(!intreqId.equals(euin.action_id)) {
                int count = intProductProces.count("listIntproductproces", fw.params()
                        .p("status", ProductProcesStatus.FINISHED).p("action_id", euin.action_id));
                if(count != 0){
                    intUinStatus.crLong("saveIntuinstatus", new IntUinStatus(intreqId, uin, "", UinValidStatus.DUBLICATE, ""));
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean validPropuct(Map reference, Map products, List<String> valid_fild){
        for (String fild : valid_fild){
            String[] routs = fild.split("\\.");
            Object ref_obj = reference;
            Object prod_obj = products;
            for (String rout : routs){
                ref_obj = ((Map)ref_obj).get(rout);
                prod_obj = ((Map)prod_obj).get(rout);
            }
            if(!ref_obj.toString().equals(prod_obj.toString())){
                return false;
            }
        }
        return true;
    }

    private IntRequestStatus updateRequestStatus(IntRequestStatus rs){
        Map<String, Object> arg = new HashMap<String, Object>();
        arg.put("messageId", rs.messageId);
        try {
            Envelope env = goznakAPI.sendReqiest(GiisCheck.giisChcemMapping(rs.requestType), arg);
            rs.status = env.getBody().getResponse().getResponseData().getStatus();
            rs.response = JaxbToString(env.getBody().getResponse().getResponseData());
            intRequestStatus.cr("updateIntrequeststatus", rs);
        } catch (Exception e) {
            return rs;
        }
        return rs;
    }

    public static String JaxbToString(Object obj) {
        if (obj == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        AnnotationIntrospector introspector = new JaxbAnnotationIntrospector(mapper.getTypeFactory());
        mapper.setAnnotationIntrospector(introspector);
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;

        }
    }

    public static Map readStringAsMap(Object str) {
        if (str == null) {
            return null;
        }
        try {
            Map<String, Object> obj = mapper.readValue(str.toString(), Map.class);
            return obj;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;

        }
    }

    public static <T> T readUsingXMLMapper( Object str, Class<T> clazz ) {
        if (str == null) {
            return null;
        }
        try {
            return xmlMapper.readValue( str.toString(), clazz );
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }


    static ObjectMapper xmlMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    static{
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        xmlMapper.registerModule(new JaxbAnnotationModule());
    }

    static ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

}
