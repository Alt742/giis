package org.webvane.giis.dmdk.request.dmdk.elements.response.batch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class MetalList {
    @XmlElement(name = "sortNumber", namespace = "urn://xsd.benemed/batch/1.0")
    private Long sortNumber;
    @XmlElement(name = "hallmark", namespace = "urn://xsd.benemed/batch/1.0")
    private Long hallmark;
    @XmlElement(name = "confirmHallmark", namespace = "urn://xsd.benemed/batch/1.0")
    private Long confirmHallmark;
    @XmlElement(name = "metal", namespace = "urn://xsd.benemed/batch/1.0")
    private String metal;
    @XmlElement(name = "mixMarkType", namespace = "urn://xsd.benemed/batch/1.0")
    private String mixMarkType;
    @XmlElement(name = "weight", namespace = "urn://xsd.benemed/batch/1.0")
    private Long weight;
    @XmlElement(name = "contentGrammOnTon", namespace = "urn://xsd.benemed/batch/1.0")
    private Long contentGrammOnTon;

    public Long getSortNumber() {
        return sortNumber;
    }
    public Long getHallmark() {
        return hallmark;
    }
    public Long getConfirmHallmark() {
        return confirmHallmark;
    }
    public String getMetal() {
        return metal;
    }
    public String getMixMarkType() {
        return mixMarkType;
    }
    public Long getWeight() {
        return weight;
    }
    public Long getContentGrammOnTon() {
        return contentGrammOnTon;
    }
}
