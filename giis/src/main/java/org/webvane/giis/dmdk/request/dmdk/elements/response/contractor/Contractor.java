package org.webvane.giis.dmdk.request.dmdk.elements.response.contractor;

import org.webvane.giis.dmdk.request.dmdk.elements.response.result.Info;
import org.webvane.giis.dmdk.request.dmdk.elements.response.result.Physical;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Contractor {
    @XmlElement(name = "legal", namespace = "urn://xsd.benemed/contractor/3.0")
    private Legal legal;
    @XmlElement(name = "physical", namespace = "urn://xsd.benemed/contractor/3.0")
    private Physical physical;
    @XmlElement(name = "outer", namespace = "urn://xsd.benemed/contractor/3.0")
    private Outer outer;
    @XmlElement(name = "generic", namespace = "urn://xsd.benemed/contractor/3.0")
    private Generic generic;
    @XmlElement(name = "artist", namespace = "urn://xsd.benemed/contractor/3.0")
    private Artist artist;
    @XmlElement(name = "info", namespace = "urn://xsd.benemed/contractor/3.0")
    private Info info;
}
