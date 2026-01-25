package org.webvane.giis.dmdk.request.dmdk.elements.signature;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Signature")
public class Signature {
    @XmlElement(name = "SignedInfo")
    protected  SignedInfo signedInfo;
    @XmlElement(name = "SignatureValue")
    protected  String signatureValue;
    @XmlElement(name = "KeyInfo")
    protected  KeyInfo keyInfo;
}
