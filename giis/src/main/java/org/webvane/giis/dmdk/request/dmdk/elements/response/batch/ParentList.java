package org.webvane.giis.dmdk.request.dmdk.elements.response.batch;

import org.webvane.giis.dmdk.request.dmdk.elements.response.result.Keeper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ParentList {
    @XmlElement(name = "UIN_INP", namespace = "urn://xsd.benemed/batch/1.0")
    private String uin_inp;
    @XmlElement(name = "isAllQuantityAndWeight", namespace = "urn://xsd.benemed/batch/1.0")
    private boolean isAllQuantityAndWeight;
    @XmlElement(name = "quantity", namespace = "urn://xsd.benemed/batch/1.0")
    private Long quantity;
    @XmlElement(name = "weight", namespace = "urn://xsd.benemed/batch/1.0")
    private Long weight;
    @XmlElement(name = "uom", namespace = "urn://xsd.benemed/batch/1.0")
    private String uom;
    @XmlElement(name = "metalList", namespace = "urn://xsd.benemed/batch/1.0")
    private List<MetalList> metalList;
    @XmlElement(name = "stoneList", namespace = "urn://xsd.benemed/batch/1.0")
    private List<StoneList> stoneList;
    @XmlElement(name = "nuggetList", namespace = "urn://xsd.benemed/batch/1.0")
    private List<NuggetList> nuggetList;
    @XmlElement(name = "gemstone", namespace = "urn://xsd.benemed/batch/1.0")
    private Gemstone gemstone;
    @XmlElement(name = "weightRaw", namespace = "urn://xsd.benemed/batch/1.0")
    private Long weightRaw;
    @XmlElement(name = "ownerList", namespace = "urn://xsd.benemed/batch/1.0")
    private OwnerList ownerList;
    @XmlElement(name = "parentList", namespace = "urn://xsd.benemed/batch/1.0")
    private ShortParentList parentList;
}
