package org.webvane.giis.dmdk.request.dmdk.elements.response.contractor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ItemList {
    @XmlElement(name = "type", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private String type;
    @XmlElement(name = "status", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private String status;

    public String getType() {
        return type;
    }
    public String getStatus() {
        return status;
    }
}
