package org.webvane.giis.dmdk.request.dmdk.elements.signature;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SignedInfo {
    @XmlElement(name = "CanonicalizationMethod")
    private CanonicalizationMethod canonicalizationMethod;
    @XmlElement(name = "SignatureMethod")
    private SignatureMethod signatureMethod;
    @XmlElement(name = "Reference")
    private Reference reference;
}
