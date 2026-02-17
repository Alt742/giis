package org.webvane.giis.dmdk.request.dmdk.elements.response;

import org.webvane.giis.dmdk.request.dmdk.elements.response.batch.ResultBatch;

import javax.xml.bind.annotation.XmlElement;
import java.util.Collections;
import java.util.List;

public class CheckGetBatchResponseData extends ResponseData{
    @XmlElement(name = "result", namespace = "urn://xsd.benemed/exchange/1.0")
    private List<ResultBatch> result;
    public List<ResultBatch> getResult() {
        return this.result;
    }
}
