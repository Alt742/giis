package org.webvane.giis.dmdk.request.dmdk.elements.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Response {
    public abstract ResponseData getResponseData();
}
