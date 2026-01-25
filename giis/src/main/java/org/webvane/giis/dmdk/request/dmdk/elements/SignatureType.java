package org.webvane.giis.dmdk.request.dmdk.elements;

import org.webvane.giis.dmdk.request.dmdk.elements.signature.Signature;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

public class SignatureType {
    @XmlElement(name = "Signature")
    protected Signature signature;
}
