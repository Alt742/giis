package org.webvane.giis.dmdk.request.dmdk.elements.response.batch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Gemstone {
    @XmlElement(name = "type", namespace = "urn://xsd.benemed/batch/1.0")
    private String type;
    @XmlElement(name = "classCode", namespace = "urn://xsd.benemed/batch/1.0")
    private String classCode;
    @XmlElement(name = "shape", namespace = "urn://xsd.benemed/batch/1.0")
    private String shape;
    @XmlElement(name = "quality", namespace = "urn://xsd.benemed/batch/1.0")
    private String quality;
    @XmlElement(name = "color", namespace = "urn://xsd.benemed/batch/1.0")
    private String color;
}
