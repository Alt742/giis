package org.webvane.giis.dmdk.request.dmdk.elements.signature;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Transform {
    @XmlAttribute(name = "Algorithm")
    private String algorithm;
}
