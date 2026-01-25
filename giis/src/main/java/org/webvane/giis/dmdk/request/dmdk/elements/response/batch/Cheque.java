package org.webvane.giis.dmdk.request.dmdk.elements.response.batch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Cheque {
    @XmlElement(name = "date", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String date;
    @XmlElement(name = "number", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String number;
    @XmlElement(name = "storeNumber", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String storeNumber;
    @XmlElement(name = "docType", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String docType;
    @XmlElement(name = "saleType", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String saleType;
}
