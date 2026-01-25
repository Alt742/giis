package org.webvane.giis;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.google.common.primitives.Floats;
import org.junit.Test;
import org.webvane.common.Pair;
import org.webvane.framework.api.FMethodCallAPI;
import org.webvane.framework.fw;
import org.webvane.framework.fwpojo;
import org.webvane.giis.dmdk.request.dmdk.DmdkAPI;
import org.webvane.giis.dmdk.request.dmdk.Envelope;
import org.webvane.giis.dmdk.request.dmdk.elements.response.batch.ResultBatch;
import org.webvane.giis.model.*;
import org.webvane.giis.v2.GiisCheck;
import org.webvane.giis.v2.model.ShortResultBatch;

import java.util.*;

public class CheckContractorDemon {
    private final FMethodCallAPI methodCallAPI;
    private DmdkAPI dmdkAPI;

    private final fwpojo<IntContractor> intContractor;
    private final fwpojo<IntRequestStatus> fintRequestStatus;
    private final fwpojo<IntBatchBrief> intBatchBrief;
    private final fwpojo<IntProductProces> intProductProces;
    private final fwpojo<IntUinStatus> intUinStatus;

    public CheckContractorDemon(FMethodCallAPI methodCallAPI, String soapAddress, String alies, String password) {
        this.methodCallAPI = methodCallAPI;
        this.dmdkAPI = new DmdkAPI(soapAddress, alies, password);

        fintRequestStatus = new fwpojo<>( methodCallAPI, IntRequestStatus.class, "IntGIIS",
                "IntRequestStatus" );

        intContractor = new fwpojo<>( methodCallAPI, IntContractor.class, "Integration",
                "IntContractor" );
        intBatchBrief = new fwpojo<>( methodCallAPI, IntBatchBrief.class, "Integration",
                "IntBatchBrief" );
        intProductProces = new fwpojo<>( methodCallAPI, IntProductProces.class, "IntGIIS",
                "IntProductProces" );
        intUinStatus = new fwpojo<>( methodCallAPI, IntUinStatus.class, "IntGIIS",
                "IntUinStatus" );
    }

    public void processContractor() {
        List<IntContractor> requestStatus = intContractor.list("listUniqueIntContracor",
                fw.params());

        //System.out.println("List Contractor size: " + requestStatus.size());

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        AnnotationIntrospector introspector = new JaxbAnnotationIntrospector(mapper.getTypeFactory());
        mapper.setAnnotationIntrospector(introspector);
        List<String> inns = new LinkedList<>();
        for (IntContractor element : requestStatus) {
            if(inns.contains(element.inn))
                continue;
            inns.add(element.inn);

            Map<String, Object> args = new HashMap<>();
            args.put("inn", element.inn);

            IntRequestStatus request_status = new IntRequestStatus();
            try {
                request_status.requestType = "SendGetContractorRequest";
                Envelope env = dmdkAPI.sendReqiest(request_status.requestType, args);

                request_status.messageId = env.getBody().getResponse().getResponseData().getMessageId();
                request_status.status = env.getBody().getResponse().getResponseData().getStatus();
                request_status.response = mapper.writeValueAsString(env.getBody().getResponse());
            }catch (Exception e){
                request_status.status = "ERROR";
            }
            fintRequestStatus.crLong( "saveIntrequeststatus", request_status );
        }
    }

    public void processRequest() {
        List<IntRequestStatus> requestStatusList = fintRequestStatus.list("listIntrequeststatus",
                fw.params().p( "status", new Object[] { "ACCEPTED", "PROCESSING"} ));//.p("status", "ACCEPTED"));

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        AnnotationIntrospector introspector = new JaxbAnnotationIntrospector(mapper.getTypeFactory());
        mapper.setAnnotationIntrospector(introspector);
        for (IntRequestStatus requestStatus : requestStatusList) {
            Map<String, Object> args = new HashMap<String, Object>();
            args.put("messageId", requestStatus.messageId);

            try {
                Envelope env = dmdkAPI.sendReqiest(GiisCheck.giisChcemMapping(requestStatus.requestType), args);

                requestStatus.status = env.getBody().getResponse().getResponseData().getStatus();
                requestStatus.response = mapper.writeValueAsString(env.getBody().getResponse().getResponseData());
                fintRequestStatus.cr("updateIntrequeststatus", requestStatus);

                // TODO: закоменть
                //requestStatus.status = env.getBody().getResponse().getResponseData().getStatus();
                //requestStatus.response = mapper.writeValueAsString(env.getBody().getResponse().getResponseData());

                // TODO: раскоменть и проверь закоменть (тогда он будет обновлять не все поля а только 2 или 3 (время тоже - надо проверить) )
                String status = env.getBody().getResponse().getResponseData().getStatus();
                String response = mapper.writeValueAsString(env.getBody().getResponse().getResponseData());
                methodCallAPI.method( "IntGIIS", "IntRequestStatus", "updateIntrequeststatus",
                        requestStatus.id,  fw.params().p("status", status ).p("response", response ) );

                if(requestStatus.status.equals("PREPARED")) {
                    if ("CheckGetContractorRequest".equals(GiisCheck.giisChcemMapping(requestStatus.requestType))) {
                        List<IntContractor> pData = GiisCheck.setContractor(env.getBody().getResponse().getResponseData());
                        for (IntContractor elementData : pData) {
                            intContractor.cr("saveIntcontracor", elementData);
                        }
                        if(pData.isEmpty()){
                            IntContractor ic = new IntContractor();
                            ic.inn = (new ObjectMapper().readValue(requestStatus.args, HashMap.class)).get("inn").toString();
                            intContractor.cr("saveIntcontracor", ic);
                        }
                    }
                    if ("CheckGetBatchBriefRequest".equals(GiisCheck.giisChcemMapping(requestStatus.requestType))) {
                        List<IntBatchBrief> pData = GiisCheck.setBatchBrief(env.getBody().getResponse().getResponseData());
                        for (IntBatchBrief elementData : pData) {
                            intBatchBrief.cr("saveIntbatchbrief", elementData);
                        }
                    }
                    if ("CheckGetBatchRequest".equals(GiisCheck.giisChcemMapping(requestStatus.requestType))) {
                        List<ResultBatch> pData = GiisCheck.setBatch(env.getBody().getResponse().getResponseData());
                        for (ResultBatch elementData : pData) {
                            IntUinStatus ius = intUinStatus.firstFromList("listIntuinstatus", fw.params().p("message_id", requestStatus.messageId));
                            if(ius == null)
                                continue;
                            IntProductProces ipp = intProductProces.firstFromList("listIntproductproces", fw.params().p( "action_id", ius.action_id ));
                            if(ipp == null)
                                continue;
                            if(!ipp.status.equals(ProductProcesStatus.FINISHED))
                                continue;
                            if(elementData.getCheque()!=null && elementData.getCheque().size() > 0){
                                ius.valid = UinValidStatus.SOLD;
                                intUinStatus.cr("updateIntuinstatus", ius);
                            }
                        }
                    }
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void processProduct() {
        List<IntProductProces> ippl = intProductProces.list("listIntproductproces", fw.params().p( "status", new Object[] { ProductProcesStatus.INPROGRESS, ProductProcesStatus.PROCESSED } ));
        if(ippl.size()==0)
            return;
        for (IntProductProces ipp : ippl){
            List<IntRequestStatus> irsl = fintRequestStatus.list("listIntrequeststatus", fw.params().p( "action_id", ipp.action_id ));
            String old_status = ipp.status;
            ipp.status = getPropuctUinStatus(ipp.action_id, irsl);
            if(!ipp.status.equals(old_status))
                intProductProces.cr("updateIntproductproces", ipp);
        }
    }

    private String getPropuctUinStatus (Long action_id, List<IntRequestStatus> requestStatus) {
        String status = ProductProcesStatus.PROCESSED;
        Map res_first = null;
        for(IntRequestStatus rs : requestStatus) {
            Map args = readStringAsMap( rs.args );
            if (!rs.status.equals("PREPARED")) {
                checkUinAndSave(action_id, "", args.get("uin").toString(), UinValidStatus.UNKNOWN, rs.messageId,
                        null);
                status = ProductProcesStatus.INPROGRESS;
                continue;
            }
            Map result_response = readStringAsMap( rs.response );
            if(result_response.containsKey("result")){
                List<Map> resultl = (List<Map>)result_response.get("result");
                if(resultl.size()>0){
                    result_response = resultl.get(0);
                    String uinValidStatus;
                    Long variant = null;
                    if(res_first == null){
                        uinValidStatus = UinValidStatus.OK;
                        variant = new Long("1");
                        res_first = result_response;
                    }else {
                        uinValidStatus = validPropuct(res_first, result_response);
                    }
                    checkUinAndSave(action_id, result_response.get("name").toString(), args.get("uin").toString(),
                            uinValidStatus, rs.messageId, variant);
                    continue;
                }
            }
            checkUinAndSave(action_id, "", args.get("uin").toString(), UinValidStatus.FAIL_DATA_NOT_FOUND_IN_GIIS, rs.messageId, null);
        }

        List<IntUinStatus> iUin = intUinStatus.list("listIntuinstatus", fw.params().p("action_id", action_id));
        for (IntUinStatus euin : iUin) {
            if(checkDublicate(action_id, euin.uin))
                checkUinAndSave(action_id, euin.name, euin.uin, UinValidStatus.DUBLICATE, euin.messageId, null);
        }

        checkVariants(res_first, requestStatus);
        return status;
    }

    private boolean checkDublicate(Long intActId, String uin){
        List<IntUinStatus> iUin = intUinStatus.list("listIntuinstatus", fw.params().p( "uin",  uin));
        for (IntUinStatus euin : iUin) {
            if(!intActId.equals(euin.action_id)) {
                int count = intProductProces.count("listIntproductproces", fw.params()
                        .p("status", ProductProcesStatus.FINISHED).p("action_id", euin.action_id));
                if(count != 0){
                    return true;
                }
            }
        }
        return false;
    }

    private void checkVariants(Map res_first, List<IntRequestStatus> requestStatus) {
        List<String> required_fields = new LinkedList<>();
        required_fields.add("category");
        required_fields.add("weight");
        required_fields.add("batchProduct.article");
        required_fields.add("batchProduct.size");

        List<String> ignore_filds = new LinkedList<>();
        ignore_filds.add("UIN_INP");

        List<Pair<IntRequestStatus,IntUinStatus>>  list_pair = new LinkedList<>();
        for(IntRequestStatus rs : requestStatus) {
            if (!rs.status.equals("PREPARED")) {
                continue;
            }
            IntUinStatus is = intUinStatus.firstFromList("listIntuinstatus", fw.params().p("message_id",
                    rs.messageId));
            if(is == null){
                continue;
            }
            if(is.variant!=null){
                continue;
            }
            list_pair.add(new Pair<>(rs,is));
        }
        if(list_pair.size()==0)
            return;
        if(res_first==null)
            return;

        ListIterator<Pair<IntRequestStatus, IntUinStatus>> litr = list_pair.listIterator();
        List<List<String>> compere_keys = new LinkedList<>();

        while (litr.hasNext()){
            Pair<IntRequestStatus,IntUinStatus> next_object = litr.next();
            Map next = readStringAsMap( next_object.first.response );
            if(next != null){
                List<Map> resultArr = (List<Map>) next.get("result");
                if(resultArr != null) {
                    compere_keys.add(comperePropuctResult(res_first, resultArr.get(0)));
                }else{
                    compere_keys.add(null);
                }
            }else{
                compere_keys.add(null);
            }
        }

        Long variant = new Long("1");

        for(int i=0; i<compere_keys.size(); i++){
            boolean flag = false;
            List<String> keys = compere_keys.get(i);
            if (keys == null)
                continue;
            for(String field : keys){
                if(required_fields.contains(field)){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                list_pair.get(i).second.variant = variant;
            }
        }

        variant = new Long("2");
        ObjectMapper objectMapper = new ObjectMapper();

        litr = list_pair.listIterator();
        while (litr.hasNext()) {
            Pair<IntRequestStatus, IntUinStatus> next_out = litr.next();
            if(next_out.second.variant != null)
                continue;
            next_out.second.variant = variant;
            try {
                next_out.second.difference = objectMapper.writeValueAsString(compere_keys.get(litr.nextIndex()-1));
            }catch (Exception e){
                e.printStackTrace();
            }
            variant += 1;
            ListIterator<Pair<IntRequestStatus, IntUinStatus>> litr_in = list_pair.listIterator(litr.nextIndex());
            //Map first = readStringAsMap( next_out.first.response );

            Map<String, Object> rootObj = readStringAsMap(next_out.first.response);
            if (rootObj == null) continue;
            Object resultObj = rootObj.get("result");
            if (!(resultObj instanceof List)) continue;
            List<?> resultList = (List<?>) resultObj;
            if (resultList.isEmpty())
                continue;
            Object firstObj = resultList.get(0);
            if (!(firstObj instanceof Map)) continue;

            @SuppressWarnings("unchecked")
            Map<String, Object> first = (Map<String, Object>) firstObj;

            while (litr_in.hasNext()) {
                Pair<IntRequestStatus, IntUinStatus> next_in = litr_in.next();
                if(next_in.second.variant != null)
                    continue;
                Map second = readStringAsMap( next_in.first.response );
                if (second == null) continue;
                if (!(second.get("result") instanceof List)) continue;
                List<?> resultListIn = (List<?>) second.get("result");
                if (resultListIn.isEmpty())
                    continue;
                Object firstObjIn = resultListIn.get(0);
                if (!(firstObjIn instanceof Map)) continue;
                second = (Map<String, Object>) firstObjIn;

                boolean flag = true;
                for (String field : required_fields) {
                    if(getDifferenceValue(field, first) != null && getDifferenceValue(field, second) != null){
                        if(!getDifferenceValue(field, first).equals(getDifferenceValue(field, second))){
                            flag = false;
                            break;
                        }
                    }
                    if(getDifferenceValue(field, first) == null && getDifferenceValue(field, second) != null){
                        flag = false;
                        break;
                    }
                    if(getDifferenceValue(field, first) != null && getDifferenceValue(field, second) == null){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    next_in.second.variant = next_out.second.variant;
                    next_in.second.difference = next_out.second.difference;
                }
            }
        }

        for(Pair<IntRequestStatus,IntUinStatus> prs : list_pair)
            intUinStatus.cr("updateIntuinstatus", prs.second);
    }

    private Object getDifferenceValue(String difference, Map data){
        String[] demons = difference.split("\\.");
        Map subMap = data;

        for (String demon: demons){
            if(subMap.get(demon) instanceof Map){
                subMap  = (Map) subMap.get(demon);
            }else {
                return subMap.get(demon);
            }
        }
        return subMap;
    }

    private List<String> comperePropuctResult(Map first, Map next){
        List<String> out = new LinkedList<>();
        for ( Object key : first.keySet() ) {
            if(!next.containsKey(key)) {
                continue;
            }
            if(!first.get(key).equals(next.get(key))){
                if(first.get(key) instanceof Map){
                    List<String> comp = comperePropuctResult((Map) first.get(key), (Map) next.get(key));
                    for(String domen : comp){
                        out.add(key.toString() + '.' + domen);
                    }
                }else {
                    out.add(key.toString());
                }
            }
        }
        return out;
    }

    private void checkUinAndSave(Long action_id, String name, String uin, String valid, String messageId, Long variant){
        List<IntUinStatus> isl = intUinStatus.list("listIntuinstatus", fw.params().p("action_id", action_id));
        for(IntUinStatus is : isl) {
            if(is.uin.equals(uin)) {
                is.name = name;
                is.valid = valid;
                is.messageId = messageId;
                is.variant = variant;
                intUinStatus.cr("updateIntuinstatus", is);
            }
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
    static ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private static String validPropuct(Map reference, Map products){
        List<String> valid_fild = new LinkedList<>();
        //valid_fild.add("batchProduct.article");
        valid_fild.add("batchProduct.brand");
        valid_fild.add("batchProduct.metal");
        valid_fild.add("category");

        for (String fild : valid_fild){
            String[] routs = fild.split("\\.");
            Object ref_obj = reference;
            Object prod_obj = products;
            for (String rout : routs){
                ref_obj = ((Map)ref_obj).get(rout);
                prod_obj = ((Map)prod_obj).get(rout);
            }
            if(ref_obj == null && prod_obj == null){
                continue;
            }else{
                if(ref_obj == null || prod_obj == null) {
                    return UinValidStatus.FAIL_INCORRECT;
                }
            }
            if(!ref_obj.toString().equals(prod_obj.toString())){
                return UinValidStatus.FAIL_INCORRECT;
            }
        }
        return UinValidStatus.OK;
    }
}
