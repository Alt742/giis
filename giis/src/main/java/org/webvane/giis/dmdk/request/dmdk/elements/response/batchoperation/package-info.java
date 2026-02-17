@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlJavaTypeAdapters({
        @XmlJavaTypeAdapter(value = CollapsedStringAdapter.class, type = String.class)
})
@XmlSchema(
        namespace = "urn:///xsd.benemed/batch-operation/3.0",
        xmlns = {

        },
        attributeFormDefault = XmlNsForm.UNSET,
        elementFormDefault = XmlNsForm.QUALIFIED
)
package org.webvane.giis.dmdk.request.dmdk.elements.response.batchoperation;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;