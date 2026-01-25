package org.webvane.giis.dmdk.request.dmdk.elements.response.batch;

import org.webvane.giis.dmdk.request.dmdk.elements.response.contractor.Contractor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class StorageList {
    @XmlElement(name = "contractor", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private Contractor contractor;
    @XmlElement(name = "dateBegin", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String dateBegin;
    @XmlElement(name = "dateEnd", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String dateEnd;
}
