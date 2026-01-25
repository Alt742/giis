package org.webvane.giis.dmdk.request.dmdk.elements.signature;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class X509Data {
    @XmlElement(name = "X509Certificate")
    private String X509Certificate;
}
