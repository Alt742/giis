package org.webvane.giis.dmdk.request.dmdk;

import org.w3c.dom.Document;

import java.io.StringWriter;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.namespace.NamespaceContext;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;

public class GoznakRequest {
    private Envelope envelope;
    private XPath xpath;
    private static final Logger LOGGER = Logger.getLogger(GoznakRequest.class.getName());

    public GoznakRequest(){
        XPathFactory factory = XPathFactory.newInstance();
        this.xpath = factory.newXPath();
        xpath.setNamespaceContext(new NamespaceContext(){
            @Override
            public Iterator getPrefixes(String namespaceURI) {
                return null;
            }

            @Override
            public String getPrefix(String namespaceURI) {
                return null;
            }

            @Override
            public String getNamespaceURI(String prefix) {
                if("soapenv".equals(prefix)) {
                    return "http://schemas.xmlsoap.org/soap/envelope/";
                }
                if("ns".equals(prefix)) {
                    return "urn://xsd.dmdk.goznak.ru/exchange/3.0";
                }
                if("ds".equals(prefix)) {
                    return "http://www.w3.org/2000/09/xmldsig#";
                }
                return null;
            }
        });
    }

    public void createRequest(String requestType) throws XPathExpressionException {
        EnvelopeFactory envFactory = EnvelopeFactory.getInstance();
        this.envelope = envFactory.createRequest(requestType);
    }

    public Envelope getEnvelope(){
        return this.envelope;
    }

    private static final javax.xml.transform.TransformerFactory TF =
            javax.xml.transform.TransformerFactory.newInstance();

}
