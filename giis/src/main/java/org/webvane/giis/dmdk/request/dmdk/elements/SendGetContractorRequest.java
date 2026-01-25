package org.webvane.giis.dmdk.request.dmdk.elements;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SendGetContractorRequest")
public class SendGetContractorRequest extends Request {

    @XmlElement(name = "TestMessage")
    public String testMessage;
    @XmlElement(name = "OGRN")
    public String OGRN;

    public static final String XPATH = "//soapenv:Envelope/soapenv:Body/ns:SendGetContractorRequest/ns:RequestData";

    public void setInn(String inn){
        if( this.requestData == null ){
            this.requestData = new RequestData();
        }
        this.requestData.setINN(inn);
    }

    @Override
    public String getXpath() {
        return XPATH;
    }
}
