package org.webvane.giis.dmdk.request.dmdk.elements.response.batch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CostList {
    @XmlElement(name = "type", namespace = "urn://xsd.benemed/batch/1.0")
    private String type;
    @XmlElement(name = "currency", namespace = "urn://xsd.benemed/batch/1.0")
    private String currency;
    @XmlElement(name = "amount", namespace = "urn://xsd.benemed/batch/1.0")
    private Number amount;
    @XmlElement(name = "amountExVAT", namespace = "urn://xsd.benemed/batch/1.0")
    private Number amountExVAT;
    @XmlElement(name = "rateVAT", namespace = "urn://xsd.benemed/batch/1.0")
    private String rateVAT;
    @XmlElement(name = "amountVAT", namespace = "urn://xsd.benemed/batch/1.0")
    private Number amountVAT;
}
