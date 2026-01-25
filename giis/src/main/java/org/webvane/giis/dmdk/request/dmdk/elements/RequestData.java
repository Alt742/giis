package org.webvane.giis.dmdk.request.dmdk.elements;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class RequestData {
    @XmlAttribute(name = "id")
    private String id = "body";
    @XmlElement(name = "INN")
    private String INN;
    @XmlElement(name = "IDTOP")
    private String IDTOP;
    @XmlElement(name = "UIN")
    private List<String> uin;
    @XmlElement(name = "messageId")
    private String messageId;
    @XmlElement(name = "page")
    private Integer page;
    @XmlElement(name = "size")
    private Integer size;

    @XmlElement(name = "UIN_INP")
    private String uin_inp;


    public void setUin(List<String> uin) {
        this.uin = uin;
    }
    public void setINN(String INN) {
        this.INN = INN;
    }
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    public void setUin_inp(String uin_inp) {
        this.uin_inp = uin_inp;
    }
}
