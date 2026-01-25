package org.webvane.giis.dmdk.request.dmdk.elements.response.batch;

import org.webvane.giis.dmdk.request.dmdk.elements.response.result.Keeper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class BatchProduct {
    @XmlElement(name = "metal", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String metal;
    @XmlElement(name = "hallmark", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String hallmark;
    @XmlElement(name = "confirmHallmark", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String confirmHallmark;
    @XmlElement(name = "mixMarkType", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String mixMarkType;
    @XmlElement(name = "metalList", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private List<MetalList> metalList;
    @XmlElement(name = "stoneList", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private List<StoneList> stoneList;
    @XmlElement(name = "nuggetList", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private List<NuggetList> nuggetList;
    @XmlElement(name = "otherCompositionListx", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private List<OtherCompositionList> otherCompositionList;
    @XmlElement(name = "dateManufacture", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String dateManufacture;
    @XmlElement(name = "brand", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String brand;
    @XmlElement(name = "article", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String article;
    @XmlElement(name = "imageList", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private List<String> imageList;
    @XmlElement(name = "jewelryType", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String jewelryType;
    @XmlElement(name = "size", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String size;
    @XmlElement(name = "serialNumber", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String serialNumber;
    @XmlElement(name = "INP", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String inp;

    public String getMetal() {
        return metal;
    }
    public String getHallmark() {
        return hallmark;
    }
    public String getConfirmHallmark() {
        return confirmHallmark;
    }
    public String getMixMarkType() {
        return mixMarkType;
    }
    public List<MetalList> getMetalList() {
        return metalList;
    }
    public List<StoneList> getStoneList() {
        return stoneList;
    }
    public List<NuggetList> getNuggetList() {
        return nuggetList;
    }
    public List<OtherCompositionList> getOtherCompositionList() {
        return otherCompositionList;
    }
    public String getDateManufacture() {
        return dateManufacture;
    }
    public String getBrand() {
        return brand;
    }
    public String getArticle() {
        return article;
    }
    public List<String> getImageList() {
        return imageList;
    }
    public String getJewelryType() {
        return jewelryType;
    }
    public String getSize() {
        return size;
    }
    public String getSerialNumber() {
        return serialNumber;
    }
    public String getInp() {
        return inp;
    }
}
