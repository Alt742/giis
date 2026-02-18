package org.webvane.giis.dmdk.request.dmdk.elements.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseData {
    @XmlElement(name = "messageId", namespace = "urn://xsd.benemed/exchange/1.0")
    private String messageId;
    @XmlElement(name = "status", namespace = "urn://xsd.benemed/exchange/1.0")
    private String status;
    @XmlElement(name = "error", namespace = "urn://xsd.benemed/exchange/1.0")
    private String error;

    @XmlElement(name = "page", namespace = "urn://xsd.benemed/exchange/1.0")
    private Long page;
    @XmlElement(name = "pages", namespace = "urn://xsd.benemed/exchange/1.0")
    private Long pages;
    @XmlElement(name = "size", namespace = "urn://xsd.benemed/exchange/1.0")
    private Long size;

    public String getMessageId() {
        return this.messageId;
    }
    public String getStatus() {
        return this.status;
    }
    public String getError() {
        return this.error;
    }

}
