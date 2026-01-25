package org.webvane.giis.dmdk.request.dmdk.elements.response;

import org.webvane.giis.dmdk.request.dmdk.elements.SignatureType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Response {
    @XmlElement(name = "DmdkSignature")
    protected SignatureType callerSignature;

    public abstract ResponseData getResponseData();
}
