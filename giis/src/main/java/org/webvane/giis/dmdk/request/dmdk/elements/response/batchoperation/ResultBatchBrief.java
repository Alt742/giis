package org.webvane.giis.dmdk.request.dmdk.elements.response.batchoperation;

import org.webvane.giis.dmdk.request.dmdk.elements.response.result.Keeper;
import org.webvane.giis.dmdk.request.dmdk.elements.response.result.Result;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class ResultBatchBrief implements Result {
    @XmlElement(name = "UIN_INP", namespace = "urn://xsd.dmdk.goznak.ru/batch-operation/3.0")
    private String uin_inp;
    @XmlElement(name = "name", namespace = "urn://xsd.dmdk.goznak.ru/batch-operation/3.0")
    private String name;
    @XmlElement(name = "article", namespace = "urn://xsd.dmdk.goznak.ru/batch-operation/3.0")
    private String article;
    @XmlElement(name = "keeper", namespace = "urn://xsd.dmdk.goznak.ru/batch-operation/3.0")
    private Keeper keeper;

    public String getUin_inp(){
        return uin_inp;
    }
    public String getInn(){
        return null;
    }
}
