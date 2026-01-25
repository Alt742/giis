package org.webvane.giis.dmdk.request.dmdk.elements.signature;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Reference {
    @XmlAttribute(name = "URI")
    private String uri;

    @XmlElementWrapper(name="Transforms")
    @XmlElement(name = "Transform")
    private List<Transform> transform;

    @XmlElement(name = "DigestMethod")
    private DigestMethod digestMethod;
    @XmlElement(name = "DigestValue")
    private String digestValue;
}
