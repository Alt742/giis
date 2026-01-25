package org.webvane.giis.dmdk.request.dmdk.elements.response.result;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public interface Result {
    public String getInn();
    public String getUin_inp();
}
