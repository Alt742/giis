package org.webvane.giis.dmdk.request.dmdk.elements.response.batch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class StoneList {
    @XmlElement(name = "type", namespace = "urn://xsd.benemed/batch/3.0")
    private String type;
    @XmlElement(name = "classCode", namespace = "urn://xsd.benemed/batch/3.0")
    private String classCode;
    @XmlElement(name = "shape", namespace = "urn://xsd.benemed/batch/3.0")
    private String shape;
    @XmlElement(name = "quality", namespace = "urn://xsd.benemed/batch/3.0")
    private String quality;
    @XmlElement(name = "color", namespace = "urn://xsd.benemed/batch/3.0")
    private String color;
    @XmlElement(name = "quantity", namespace = "urn://xsd.benemed/batch/3.0")
    private Long quantity;
    @XmlElement(name = "weight", namespace = "urn://xsd.benemed/batch/3.0")
    private Long weight;
    @XmlElement(name = "uom", namespace = "urn://xsd.benemed/batch/3.0")
    private String uom;

    public String getType() {
        return type;
    }
    public String getClassCode() {
        return classCode;
    }
    public String getShape() {
        return shape;
    }
    public String getQuality() {
        return quality;
    }
    public String getColor() {
        return color;
    }
    public Long getQuantity() {
        return quantity;
    }
    public Long getWeight() {
        return weight;
    }
    public String getUom() {
        return uom;
    }
}
