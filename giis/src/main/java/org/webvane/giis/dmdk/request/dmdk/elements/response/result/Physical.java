package org.webvane.giis.dmdk.request.dmdk.elements.response.result;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Physical {
    @XmlElement(name = "IDTOP", namespace = "urn://xsd.benemed/contractor/1.0")
    private String IDTOP;
    @XmlElement(name = "OGRN", namespace = "urn://xsd.benemed/contractor/1.0")
    private String OGRN;
    @XmlElement(name = "INN", namespace = "urn://xsd.benemed/contractor/1.0")
    private String INN;

    public String getIdtop() {
        return IDTOP;
    }
    public String getOgrn() {
        return OGRN;
    }
    public String getInn() {
        return INN;
    }
}
