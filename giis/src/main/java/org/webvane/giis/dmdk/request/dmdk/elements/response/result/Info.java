package org.webvane.giis.dmdk.request.dmdk.elements.response.result;

import org.webvane.giis.dmdk.request.dmdk.elements.response.contractor.Addresses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Info {
    @XmlElement(name = "name", namespace = "urn://xsd.benemed/contractor/1.0")
    private String name;
    @XmlElement(name = "email", namespace = "urn://xsd.benemed/contractor/1.0")
    private String email;
    @XmlElement(name = "phone", namespace = "urn://xsd.benemed/contractor/1.0")
    private String phone;
    @XmlElement(name = "address", namespace = "urn://xsd.benemed/contractor/1.0")
    private List<Addresses> address;

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public List<Addresses> getAddress() {
        return address;
    }
}
