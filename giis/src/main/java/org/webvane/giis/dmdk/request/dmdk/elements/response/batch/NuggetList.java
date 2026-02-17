package org.webvane.giis.dmdk.request.dmdk.elements.response.batch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class NuggetList {
    @XmlElement(name = "nuggetNumber", namespace = "urn://xsd.benemed/batch/1.0")
    private String nuggetNumber;
    @XmlElement(name = "quantity", namespace = "urn://xsd.benemed/batch/1.0")
    private Long quantity;
    @XmlElement(name = "weight", namespace = "urn://xsd.benemed/batch/1.0")
    private Long weight;
    @XmlElement(name = "uom", namespace = "urn://xsd.benemed/batch/1.0")
    private String uom;
    @XmlElement(name = "metal", namespace = "urn://xsd.benemed/batch/1.0")
    private String metal;
    @XmlElement(name = "metalList", namespace = "urn://xsd.benemed/batch/1.0")
    private List<MetalList> metalList;

    public String getNuggetNumber() {
        return nuggetNumber;
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
    public String getMetal() {
        return metal;
    }
    public List<MetalList> getMetalList() {
        return metalList;
    }
}
