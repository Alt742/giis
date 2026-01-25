@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlJavaTypeAdapters({
        @XmlJavaTypeAdapter(value = CollapsedStringAdapter.class, type = String.class)
})
@XmlSchema(
        namespace = "http://www.w3.org/2000/09/xmldsig#",
        xmlns = {
                @XmlNs(prefix = "ds", namespaceURI = "http://www.w3.org/2000/09/xmldsig#")
        },
        attributeFormDefault = XmlNsForm.UNSET,
        elementFormDefault = XmlNsForm.QUALIFIED
)
package org.webvane.giis.dmdk.request.dmdk.elements.signature;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSchemaTypes;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;