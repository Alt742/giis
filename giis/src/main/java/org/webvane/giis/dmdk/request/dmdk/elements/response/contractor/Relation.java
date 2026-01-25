package org.webvane.giis.dmdk.request.dmdk.elements.response.contractor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Relation {
    @XmlElement(name = "itemList", namespace = "urn://xsd.dmdk.goznak.ru/contractor/3.0")
    private List<ItemList> itemList;

    public List<ItemList> getItemList() {
        return itemList;
    }
}
