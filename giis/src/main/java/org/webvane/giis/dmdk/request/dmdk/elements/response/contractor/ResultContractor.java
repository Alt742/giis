package org.webvane.giis.dmdk.request.dmdk.elements.response.contractor;

import org.webvane.giis.dmdk.request.dmdk.elements.response.result.Keeper;
import org.webvane.giis.dmdk.request.dmdk.elements.response.result.Result;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResultContractor implements Result {
    @XmlElement(name = "IDTOP", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private String idtop;
    @XmlElement(name = "OGRN", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private String ogrn;
    @XmlElement(name = "INN", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private String inn;
    @XmlElement(name = "address", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private String address;
    @XmlElement(name = "grantSale", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private boolean grantSale;
    @XmlElement(name = "name", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private String name;
    @XmlElement(name = "relation", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private Relation relation;

    public String getIdtop(){
        return idtop;
    }
    public String getOgrn(){
        return ogrn;
    }
    public String getAddress(){
        return address;
    }
    public boolean getGrantSale(){
        return grantSale;
    }
    public String getName(){
        return name;
    }
    public String getInn(){
        return inn;
    }
    public String getUin_inp(){
        return null;
    }
    public Relation getRelation(){
        return relation;
    }
}
