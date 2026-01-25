package org.webvane.giis.dmdk.request.dmdk.elements.response.batch;

import org.webvane.giis.dmdk.request.dmdk.elements.response.contractor.Contractor;
import org.webvane.giis.dmdk.request.dmdk.elements.response.result.Keeper;
import org.webvane.giis.dmdk.request.dmdk.elements.response.result.Result;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
public class ResultBatch implements Result {
    @XmlElement(name = "index", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String index;
    @XmlElement(name = "UIN_INP", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String uin_inp;
    @XmlElement(name = "name", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String name;
    @XmlElement(name = "keeper", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private Keeper keeper;
    @XmlElement(name = "remains", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private boolean remains;
    @XmlElement(name = "description", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String description;
    @XmlElement(name = "type", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String type;
    @XmlElement(name = "subType", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String subType;
    @XmlElement(name = "category", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String category;
    @XmlElement(name = "phase", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String phase;
    @XmlElement(name = "process", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String process;
    @XmlElement(name = "status", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String status;
    @XmlElement(name = "OKPD2", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String okpd2;
    @XmlElement(name = "TNVED", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String tnved;
    @XmlElement(name = "quantity", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private Long quantity;
    @XmlElement(name = "weight", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private Long weight;
    @XmlElement(name = "uom", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String uom;
    @XmlElement(name = "producer", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private Producer producer;
    @XmlElement(name = "owner", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private Owner owner;
    @XmlElement(name = "batchProduct", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private BatchProduct batchProduct;
    @XmlElement(name = "ownerList", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private OwnerList ownerList;
    @XmlElement(name = "costList", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private CostList costList;
    @XmlElement(name = "parentList", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private List<ParentList> parentList;
    @XmlElement(name = "uinList", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String uinList;
    @XmlElement(name = "storageList", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private List<Contractor> storageList;
    @XmlElement(name = "property", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private Property property;
    @XmlElement(name = "repair", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private Repair repair;
    @XmlElement(name = "fpp", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private Fpp fpp;
    @XmlElement(name = "set", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private String set;
    @XmlElement(name = "cheque", namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0")
    private List<Cheque> cheque;

    public ResultBatch() {
    }


    public String getUin_inp(){
        return uin_inp;
    }
    public String getInn(){
        return null;
    }
    public String getIndex() {
        return index;
    }
    public String getName() {
        return name;
    }
    public Keeper getKeeper() {
        return keeper;
    }
    public boolean isRemains() {
        return remains;
    }
    public String getDescription() {
        return description;
    }
    public String getType() {
        return type;
    }
    public String getSubType() {
        return subType;
    }
    public String getCategory() {
        return category;
    }
    public String getPhase() {
        return phase;
    }
    public String getProcess() {
        return process;
    }
    public String getStatus() {
        return status;
    }
    public String getOkpd2() {
        return okpd2;
    }
    public String getTnved() {
        return tnved;
    }
    public Long getQuantity() {
        return quantity;
    }
    public Long getWeight() {
        return weight;
    }
    public Producer getProducer() {
        return producer;
    }
    public Owner getOwner() {
        return owner;
    }
    public BatchProduct getBatchProduct() {
        return batchProduct;
    }
    public OwnerList getOwnerList() {
        return ownerList;
    }
    public CostList getCostList() {
        return costList;
    }
    public List<ParentList> getParentList() {
        return parentList;
    }
    public String getUinList() {
        return uinList;
    }
    public List<Contractor> getStorageList() {
        return storageList;
    }
    public Property getProperty() {
        return property;
    }
    public Repair getRepair() {
        return repair;
    }
    public Fpp getFpp() {
        return fpp;
    }
    public String getSet() {
        return set;
    }
    public List<Cheque> getCheque() {
        return cheque;
    }
}
