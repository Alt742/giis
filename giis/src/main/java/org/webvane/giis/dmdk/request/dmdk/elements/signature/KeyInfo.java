package org.webvane.giis.dmdk.request.dmdk.elements.signature;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class KeyInfo {
    @XmlElement(name = "X509Data")
    private X509Data x509Data;
}
