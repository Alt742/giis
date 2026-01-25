package org.webvane.giis.dmdk.request.dmdk;

import org.webvane.giis.dmdk.request.dmdk.elements.Body;
import org.webvane.giis.dmdk.request.dmdk.elements.Request;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Envelope")
@XmlAccessorType(XmlAccessType.FIELD)
public class Envelope {
    @XmlElement(name = "Header")
    private String header;

    @XmlElement(name = "Body")
    protected Body body;

    public Envelope(){
    }
    public Envelope(Request request){
        this.header = "";
        this.body = new Body(request);
    }

    public Body getBody() {
        return body;
    }
}
