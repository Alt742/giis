package org.webvane.giis.dmdk.request.dmdk.elements;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SendGetBatchBriefRequest")
public class SendGetBatchBriefRequest  extends Request {
    @XmlElement(name = "TestMessage")
    public String testMessage;
    @XmlElement(name = "OGRN")
    public String OGRN;

    public static final String XPATH = "//soapenv:Envelope/soapenv:Body/ns:SendGetBatchBriefRequest/ns:RequestData";

    public void setUin(List<String> uins){
        if( this.requestData == null ){
            this.requestData = new RequestData();
        }
        this.requestData.setUin(uins);
    }

    @Override
    public String getXpath() {
        return XPATH;
    }
}
