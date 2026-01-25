@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlJavaTypeAdapters({
        @XmlJavaTypeAdapter(value = CollapsedStringAdapter.class, type = String.class)
})
@XmlSchema(
        namespace = "http://schemas.xmlsoap.org/soap/envelope/",
        xmlns = {
                @XmlNs(prefix = "soapenv", namespaceURI = "http://schemas.xmlsoap.org/soap/envelope/")
        },
        attributeFormDefault = XmlNsForm.UNSET,
        elementFormDefault = XmlNsForm.QUALIFIED
)
package org.webvane.giis.dmdk.request.dmdk;

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