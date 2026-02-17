package org.webvane.giis.dmdk.request.dmdk.elements.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CheckGetBatchResponse  extends Response {
    @XmlElement(name = "ResponseData", namespace = "urn://xsd.benemed/exchange/1.0")
    protected CheckGetBatchResponseData responseData;

    public ResponseData getResponseData() {
        return this.responseData;
    }
}
