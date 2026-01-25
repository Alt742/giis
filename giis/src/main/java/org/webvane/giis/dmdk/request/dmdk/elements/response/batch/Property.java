package org.webvane.giis.dmdk.request.dmdk.elements.response.batch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Property {
    @XmlElement(name = "buyingupID", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String buyingupID;
    @XmlElement(name = "manufacturingTag", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String manufacturingTag;
    @XmlElement(name = "lossesTag", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String lossesTag;
    @XmlElement(name = "physicalMarking", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String physicalMarking;
    @XmlElement(name = "physicalMarkingStatus", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String physicalMarkingStatus;
    @XmlElement(name = "physicalMarkingStatusTag", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String physicalMarkingStatusTag;
    @XmlElement(name = "physicalMarkingDenyReason", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String physicalMarkingDenyReason;
    @XmlElement(name = "periodLosses", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String periodLosses;
    @XmlElement(name = "turnoverIsLimited", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private boolean turnoverIsLimited;
}
