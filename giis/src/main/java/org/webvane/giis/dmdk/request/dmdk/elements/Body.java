package org.webvane.giis.dmdk.request.dmdk.elements;

import org.webvane.giis.dmdk.request.dmdk.elements.response.*;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Body {

    @XmlElements({
            @XmlElement(name = "SendGetContractorRequest", type = SendGetContractorRequest.class),
            @XmlElement(name = "SendGetBatchBriefRequest", type = SendGetBatchBriefRequest.class),
            @XmlElement(name = "CheckGetBatchBriefRequest", type = CheckGetBatchBrief.class),
            @XmlElement(name = "CheckGetContractorRequest", type = CheckGetContractorRequest.class),
            @XmlElement(name = "SendGetBatchRequest", type = SendGetBatchRequest.class),
            @XmlElement(name = "CheckGetBatchRequest", type = CheckGetBatchRequest.class)
    })
    private Request request;

    @XmlElements({
            @XmlElement(name = "CheckGetBatchBriefResponse", type = CheckGetBatchBriefResponse.class),
            @XmlElement(name = "SendGetBatchBriefResponse", type = SendGetBatchBriefResponse.class),
            @XmlElement(name = "SendGetContractorResponse", type = SendGetContractorResponse.class),
            @XmlElement(name = "CheckGetContractorResponse", type = CheckGetContractorResponse.class),
            @XmlElement(name = "SendGetBatchResponse", type = SendGetBatchResponse.class),
            @XmlElement(name = "CheckGetBatchResponse", type = CheckGetBatchResponse.class)
    })
    private Response response;

    public Body(){
    }
    public Body(Request request){
        this.request = request;
    }

    public Request getRequest() {
         return this.request;
    }
    public Response getResponse() {
        return this.response;
    }
}
