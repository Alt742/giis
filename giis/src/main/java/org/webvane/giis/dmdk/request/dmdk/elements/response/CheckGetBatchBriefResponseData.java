package org.webvane.giis.dmdk.request.dmdk.elements.response;

import org.webvane.giis.dmdk.request.dmdk.elements.response.batchoperation.ResultBatchBrief;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class CheckGetBatchBriefResponseData extends ResponseData{
    @XmlElement(name = "result", namespace = "urn://xsd.dmdk.goznak.ru/exchange/3.0")
    private List<ResultBatchBrief> result;
    public List<ResultBatchBrief> getResult() {
        return this.result;
    }
}
