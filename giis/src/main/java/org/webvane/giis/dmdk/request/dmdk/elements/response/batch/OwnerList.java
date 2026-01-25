package org.webvane.giis.dmdk.request.dmdk.elements.response.batch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class OwnerList {
    @XmlElement(name = "owner", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private Owner owner;
    @XmlElement(name = "metalList", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private List<MetalList> metalList;
}
