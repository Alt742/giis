package org.webvane.giis.dmdk.request.dmdk.elements.response;

import org.webvane.giis.dmdk.request.dmdk.elements.response.contractor.ResultContractor;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class CheckGetContractorResponseData  extends ResponseData {
    @XmlElement(name = "result", namespace = "urn://xsd.benemed/exchange/1.0")
    private List<ResultContractor> result;
    public List<ResultContractor> getResult() {
        return this.result;
    }
}
