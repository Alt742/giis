package org.webvane.giis.dmdk.request.dmdk;

import org.webvane.giis.dmdk.request.dmdk.crypto.DigitalSignatureProcessor;
import org.webvane.giis.dmdk.request.dmdk.crypto.KeyStoreWrapper;
import org.webvane.giis.dmdk.request.dmdk.crypto.XMLTransformHelper;
import org.webvane.giis.dmdk.request.dmdk.crypto.exceptions.SignatureProcessingException;
import org.w3c.dom.Element;

import javax.xml.bind.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.*;

public class DMDKRequest {
    private Envelope envelope;
    private XPath xpath;
    private XPathExpression sigXPath;
    private String xmlSignMsg;

    public DMDKRequest(){
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
        this.sigXPath = this.xpath.compile(this.envelope.getBody().getRequest().getXpath());
    }

    public Envelope getEnvelope(){
        return this.envelope;
    }

    public String signRequest(KeyStoreWrapper ksw, DigitalSignatureProcessor dsp, String alies, String password) throws JAXBException, XPathExpressionException, CertificateException, KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, SignatureProcessingException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(Envelope.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(this.envelope, writer);
        Element e = XMLTransformHelper.buildDocumentFromString(writer.toString()).getDocumentElement();
        Element elementToBeSigned = (Element) this.sigXPath.evaluate(e, XPathConstants.NODE);
        Element sigElement = dsp.signXMLDSigDetached(elementToBeSigned, null, ksw.getPrivateKey(alies, password.toCharArray()),
                ksw.getX509Certificate(alies));
        Element callerSignature = e.getOwnerDocument().createElement("ns:CallerSignature");
        callerSignature.insertBefore(sigElement,null);
        elementToBeSigned.getParentNode().insertBefore(callerSignature,elementToBeSigned);

        this.xmlSignMsg = XMLTransformHelper.elementToString(e);
        StringReader reader = new StringReader(this.xmlSignMsg);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        this.envelope = (Envelope) unmarshaller.unmarshal(reader);
        return this.xmlSignMsg;
    }

    public String getXmlSignMsg(){
        return this.xmlSignMsg;
    }
}
