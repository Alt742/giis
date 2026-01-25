package org.webvane.giis.dmdk.request.dmdk.elements.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CheckGetBatchBriefResponse extends Response {
    @XmlElement(name = "TestMessage")
    public String testMessage;
    @XmlElement(name = "OGRN")
    public String OGRN;

    @XmlElement(name = "ResponseData", namespace = "urn://xsd.dmdk.goznak.ru/exchange/3.0")
    protected CheckGetBatchBriefResponseData responseData;
    public ResponseData getResponseData(){
        return this.responseData;
    }
}
