package org.webvane.giis.dmdk.request.dmdk.elements.response.contractor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Legal {
    @XmlElement(name = "IDTOP", namespace = "urn://xsd.benemed/contractor/1.0")
    private String idtop;
    @XmlElement(name = "OGRN", namespace = "urn://xsd.benemed/contractor/1.0")
    private String ogrn;
    @XmlElement(name = "INN", namespace = "urn://xsd.benemed/contractor/1.0")
    private String inn;
    @XmlElement(name = "KPP", namespace = "urn://xsd.benemed/contractor/1.0")
    private String kpp;
}
