@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlJavaTypeAdapters({
        @XmlJavaTypeAdapter(value = CollapsedStringAdapter.class, type = String.class)
})
@XmlSchema(
        namespace = "urn://xsd.dmdk.goznak.ru/batch/3.0",
        xmlns = {

        },
        attributeFormDefault = XmlNsForm.UNSET,
        elementFormDefault = XmlNsForm.QUALIFIED
)
package org.webvane.giis.dmdk.request.dmdk.elements.response.batch;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.XmlNsForm;