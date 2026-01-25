package org.webvane.giis.dmdk.request.dmdk.elements.response.contractor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
    @XmlElement(name = "countryCode", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private String countryCode;
    @XmlElement(name = "index", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private String index;
    @XmlElement(name = "regionCode", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private String regionCode;
    @XmlElement(name = "area", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private String area;
    @XmlElement(name = "city", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private String city;
    @XmlElement(name = "place", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private String place;
    @XmlElement(name = "street", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private String street;
    @XmlElement(name = "house", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private String house;
    @XmlElement(name = "corps", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private String corps;
    @XmlElement(name = "litera", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private String litera;
    @XmlElement(name = "flat", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private String flat;
    @XmlElement(name = "FIAS", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private String fias;
    @XmlElement(name = "outerAddress", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private String outerAddress;

    public String getCountryCode() {
        return countryCode;
    }
    public String getIndex() {
        return index;
    }
    public String getRegionCode() {
        return regionCode;
    }
    public String getArea() {
        return area;
    }
    public String getCity() {
        return city;
    }
    public String getPlace() {
        return place;
    }
    public String getStreet() {
        return street;
    }
    public String getHouse() {
        return house;
    }
    public String getCorps() {
        return corps;
    }
    public String getLitera() {
        return litera;
    }
    public String getFlat() {
        return flat;
    }
    public String getFias() {
        return fias;
    }
    public String getOuterAddress() {
        return outerAddress;
    }
}
