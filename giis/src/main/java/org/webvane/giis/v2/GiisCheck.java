package org.webvane.giis.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import org.webvane.giis.dmdk.request.dmdk.Envelope;
import org.webvane.giis.dmdk.request.dmdk.elements.CheckGetBatchBrief;
import org.webvane.giis.dmdk.request.dmdk.elements.CheckGetContractorRequest;
import org.webvane.giis.dmdk.request.dmdk.elements.SendGetBatchBriefRequest;
import org.webvane.giis.dmdk.request.dmdk.elements.SendGetContractorRequest;
import org.webvane.giis.dmdk.request.dmdk.elements.response.CheckGetBatchBriefResponseData;
import org.webvane.giis.dmdk.request.dmdk.elements.response.CheckGetBatchResponseData;
import org.webvane.giis.dmdk.request.dmdk.elements.response.CheckGetContractorResponseData;
import org.webvane.giis.dmdk.request.dmdk.elements.response.ResponseData;
import org.webvane.giis.dmdk.request.dmdk.elements.response.batch.ResultBatch;
import org.webvane.giis.dmdk.request.dmdk.elements.response.batchoperation.ResultBatchBrief;
import org.webvane.giis.dmdk.request.dmdk.elements.response.contractor.ResultContractor;
import org.webvane.giis.dmdk.request.dmdk.elements.response.result.Result;
import org.webvane.giis.model.IntBatchBrief;
import org.webvane.giis.model.IntContractor;

import java.util.ArrayList;
import java.util.List;

public class GiisCheck {
    public static String giisChcemMapping(String requestType){
        switch (requestType) {
            case "SendGetContractorRequest":
                return "CheckGetContractorRequest";
            case "SendGetBatchBriefRequest":
                return "CheckGetBatchBriefRequest";
            case "SendGetBatchRequest":
                return "CheckGetBatchRequest";
            default:
                break;
        }
        return null;
    }

    public static List<IntContractor> setContractor(ResponseData rData) throws Exception {
        List<ResultContractor> inns = ((CheckGetContractorResponseData)rData).getResult();
        List<IntContractor> out = new ArrayList<>();

        if(inns==null)
            return out;

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        AnnotationIntrospector introspector = new JaxbAnnotationIntrospector(mapper.getTypeFactory());
        mapper.setAnnotationIntrospector(introspector);

        for (Result element : inns) {
            IntContractor contractor = new IntContractor();
            contractor.data = mapper.writeValueAsString(element);
            contractor.inn = element.getInn();
            out.add(contractor);
        }
        return out;
    }

    public static List<IntBatchBrief> setBatchBrief(ResponseData rData) throws Exception {
        List<ResultBatchBrief> inns = ((CheckGetBatchBriefResponseData)rData).getResult();
        List<IntBatchBrief> out = new ArrayList<>();

        if(inns==null)
            return out;

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        AnnotationIntrospector introspector = new JaxbAnnotationIntrospector(mapper.getTypeFactory());
        mapper.setAnnotationIntrospector(introspector);

        for (Result element : inns) {
            IntBatchBrief batchBrief = new IntBatchBrief();
            batchBrief.data = mapper.writeValueAsString(element);
            batchBrief.uin = element.getUin_inp();
            out.add(batchBrief);
        }
        return out;
    }

    public static List<ResultBatch> setBatch(ResponseData rData) throws Exception {
        List<ResultBatch> inns = ((CheckGetBatchResponseData)rData).getResult();
        return inns;
    }
}
