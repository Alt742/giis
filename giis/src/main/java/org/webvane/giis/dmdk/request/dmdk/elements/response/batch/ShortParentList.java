package org.webvane.giis.dmdk.request.dmdk.elements.response.batch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ShortParentList {
    @XmlElement(name = "UIN_INP", namespace = "urn://xsd.benemed/batch/1.0")
    private String uin_inp;
    @XmlElement(name = "weight", namespace = "urn://xsd.benemed/batch/1.0")
    private Long weight;
}
