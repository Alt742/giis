package org.webvane.giis.dmdk.request.dmdk.elements;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckGetContractorRequest")
public class CheckGetContractorRequest extends Request {
    @XmlElement(name = "TestMessage")
    public String testMessage;
    @XmlElement(name = "OGRN")
    public String OGRN;

    public static final String XPATH = "//soapenv:Envelope/soapenv:Body/ns:CheckGetContractorRequest/ns:RequestData";

    public void setMessageId(String messageId){
        if( this.requestData == null ){
            this.requestData = new RequestData();
        }
        this.requestData.setMessageId(messageId);
    }

    @Override
    public String getXpath() {
        return XPATH;
    }
}
