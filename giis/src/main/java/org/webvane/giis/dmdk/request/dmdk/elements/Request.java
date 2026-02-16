package org.webvane.giis.dmdk.request.dmdk.elements;

import javax.xml.bind.annotation.*;

@XmlType(name = "Request")
public abstract class Request {
    @XmlElement(name = "RequestData")
    protected RequestData requestData;

    public abstract String getXpath();
}
