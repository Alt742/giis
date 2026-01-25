package org.webvane.giis.dmdk.request.dmdk.elements.response.result;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Keeper {
    @XmlElement(name = "physical", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private Physical physical;
    @XmlElement(name = "info", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private Info info;
    @XmlElement(name = "inn", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private String inn;
    @XmlElement(name = "short_name", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private String short_name;
    @XmlElement(name = "IDTOP", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private String idtop;

    public Physical getPhysical() {
        return physical;
    }
    public Info getInfo() {
        return info;
    }
    public String getInn() {
        return inn;
    }
    public String getShort_name() {
        return short_name;
    }
    public String getIdtop() {
        return idtop;
    }
}
