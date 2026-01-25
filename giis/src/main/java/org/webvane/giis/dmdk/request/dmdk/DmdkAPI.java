package org.webvane.giis.dmdk.request.dmdk;

import org.webvane.giis.dmdk.request.dmdk.crypto.DigitalSignatureFactory;
import org.webvane.giis.dmdk.request.dmdk.crypto.DigitalSignatureProcessor;
import org.webvane.giis.dmdk.request.dmdk.crypto.KeyStoreWrapper;
import org.webvane.giis.dmdk.request.dmdk.elements.*;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.SOAPMessage;
import javax.xml.xpath.XPathExpressionException;
import java.util.List;
import java.util.Map;

/*
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.XMLConstants;
import org.xml.sax.InputSource;
import java.io.StringReader;
 */

public class DmdkAPI {
    private String soapAddress;
    private String alies;
    private String password;
    private DigitalSignatureProcessor dsp;
    private KeyStoreWrapper ksw;


    public DmdkAPI(String soapAdress, String alies, String password){
        this.soapAddress = soapAdress;
        this.alies = alies;
        this.password = password;
        if (java.security.Security.getProvider("JCP")==null)  java.security.Security.addProvider(new ru.CryptoPro.JCP.JCP());
        if (java.util.Arrays.stream(java.security.Security.getProviders())
                .noneMatch(p -> p.getClass().getName().equals("ru.CryptoPro.ssl.Provider"))) {
            java.security.Security.addProvider(new ru.CryptoPro.ssl.Provider());
        }
        if (java.util.Arrays.stream(java.security.Security.getProviders())
                .noneMatch(p -> p.getClass().getName().equals("ru.CryptoPro.JCPxml.dsig.internal.dom.XMLDSigRI"))) {
            java.security.Security.addProvider(new ru.CryptoPro.JCPxml.dsig.internal.dom.XMLDSigRI());
        }
        if (java.util.Arrays.stream(java.security.Security.getProviders())
                .noneMatch(p -> p.getClass().getName().equals("ru.CryptoPro.reprov.RevCheck"))) {
            java.security.Security.addProvider(new ru.CryptoPro.reprov.RevCheck());
        }

        DigitalSignatureFactory.init("JCP");
        this.dsp = DigitalSignatureFactory.getDigitalSignatureProcessor();
        this.ksw = DigitalSignatureFactory.getKeyStoreWrapper();
    }

    public Envelope sendReqiest(String type, Map<String,Object> args) throws Exception {
        DMDKRequest dmdkRequest = new DMDKRequest();
        try {
            dmdkRequest.createRequest(type);
        }catch (XPathExpressionException e){
            throw new Exception("ERROR TYPE REQIEST: " + e.getMessage());
        }

        //set arg in request
        switch (type) {
            case "SendGetContractorRequest":
                ((SendGetContractorRequest)dmdkRequest.getEnvelope().getBody().getRequest())
                        .setInn(args.get("inn").toString());
                break;
            case "SendGetBatchBriefRequest":
                List<String> uins = (List<String>)args.get("uin");
                ((SendGetBatchBriefRequest)dmdkRequest.getEnvelope().getBody().getRequest()).setUin(uins);
                break;
            case "SendGetBatchRequest":
                String uin = (String)args.get("uin");
                ((SendGetBatchRequest)dmdkRequest.getEnvelope().getBody().getRequest()).setUin(uin);
                break;
            case "CheckGetContractorRequest":
                ((CheckGetContractorRequest)dmdkRequest.getEnvelope().getBody().getRequest())
                        .setMessageId(args.get("messageId").toString());
                break;
            case "CheckGetBatchBriefRequest":
                ((CheckGetBatchBrief)dmdkRequest.getEnvelope().getBody().getRequest()).
                        setMessageId(args.get("messageId").toString());
                break;
            case "CheckGetBatchRequest":
                ((CheckGetBatchRequest)dmdkRequest.getEnvelope().getBody().getRequest()).
                        setMessageId(args.get("messageId").toString());
                break;
            default:
                break;
        }

        String signMsg = dmdkRequest.signRequest(this.ksw, this.dsp, this.alies, this.password);
        SOAPMessage response = SOAPClientDMDK.callSoapWebService(this.soapAddress, signMsg);

        Node n = response.getSOAPBody().getParentNode();
        n.normalize();

        JAXBContext context = JAXBContext.newInstance(Envelope.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        JAXBElement<Envelope> personElement = unmarshaller.unmarshal(n, Envelope.class);
        return personElement.getValue();
    }

    /*public static Envelope parseRoot(String xml) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setValidating(false);

        // Безопасность (XXE off) — работает в Java 8
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
        dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        dbf.setXIncludeAware(false);
        dbf.setExpandEntityReferences(false);
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        try {
            dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");     // может бросить в старых 8u — ок
            dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        } catch (Throwable ignore) {}

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new InputSource(new StringReader(xml)));

        Node n = doc.getDocumentElement();;
        n.normalize();

        JAXBContext context = JAXBContext.newInstance(Envelope.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        JAXBElement<Envelope> personElement = unmarshaller.unmarshal(n, Envelope.class);
        return personElement.getValue();
    }*/
}
