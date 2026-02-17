package org.webvane.giis.dmdk.request.dmdk.elements.response.batch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class OtherCompositionList {
    @XmlElement(name = "type", namespace = "urn://xsd.benemed/batch/3.0")
    private String type;
    @XmlElement(name = "quantity", namespace = "urn://xsd.benemed/batch/3.0")
    private Long quantity;
    @XmlElement(name = "weight", namespace = "urn://xsd.benemed/batch/3.0")
    private Long weight;

    public String getType() {
        return type;
    }
    public Long getQuantity() {
        return quantity;
    }
    public Long getWeight() {
        return weight;
    }
}
