package org.webvane.giis.dmdk.request.dmdk.elements.response.batch;

import org.webvane.giis.dmdk.request.dmdk.elements.response.result.Info;
import org.webvane.giis.dmdk.request.dmdk.elements.response.result.Physical;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Producer {
    @XmlElement(name = "physical", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private Physical physical;
    @XmlElement(name = "info", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private Info info;

    public Physical getPhysical() {
        return physical;
    }
    public Info getInfo() {
        return info;
    }
}
