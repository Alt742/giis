package org.webvane.giis.dmdk.request.dmdk;

import org.webvane.giis.dmdk.request.dmdk.elements.*;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.xpath.XPathExpressionException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;


import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.XMLConstants;
import org.xml.sax.InputSource;
import java.io.StringReader;

public class GoznakAPI {
    private final String apiAddress;
    private final String token;


    public GoznakAPI(String apiAddress, String token){
        this.apiAddress = apiAddress;
        this.token = token;
    }

    public Envelope sendReqiest(String type, Map<String,Object> args) throws Exception {
        GoznakRequest goznakRequest = new GoznakRequest();
        try {
            goznakRequest.createRequest(type);
        }catch (XPathExpressionException e){
            throw new Exception("ERROR TYPE REQIEST: " + e.getMessage());
        }

        //set arg in request
        switch (type) {
            case "SendGetContractorRequest":
                ((SendGetContractorRequest) goznakRequest.getEnvelope().getBody().getRequest())
                        .setInn(args.get("inn").toString());
                break;
            case "SendGetBatchBriefRequest":
                List<String> uins = (List<String>)args.get("uin");
                ((SendGetBatchBriefRequest) goznakRequest.getEnvelope().getBody().getRequest()).setUin(uins);
                break;
            case "SendGetBatchRequest":
                String uin = (String)args.get("uin");
                ((SendGetBatchRequest) goznakRequest.getEnvelope().getBody().getRequest()).setUin(uin);
                break;
            case "CheckGetContractorRequest":
                ((CheckGetContractorRequest) goznakRequest.getEnvelope().getBody().getRequest())
                        .setMessageId(args.get("messageId").toString());
                break;
            case "CheckGetBatchBriefRequest":
                ((CheckGetBatchBrief) goznakRequest.getEnvelope().getBody().getRequest()).
                        setMessageId(args.get("messageId").toString());
                break;
            case "CheckGetBatchRequest":
                ((CheckGetBatchRequest) goznakRequest.getEnvelope().getBody().getRequest()).
                        setMessageId(args.get("messageId").toString());
                break;
            default:
                break;
        }

        String requestBody = marshalEnvelope(goznakRequest.getEnvelope());
        String endpoint = buildEndpoint(type);
        String responseBody = sendPost(endpoint, requestBody);
        return parseRoot(responseBody);
    }

    private String buildEndpoint(String requestType) {
        String endpoint = requestType.endsWith("Request")
                ? requestType.substring(0, requestType.length() - "Request".length())
                : requestType;
        return apiAddress.endsWith("/") ? apiAddress + endpoint : apiAddress + "/" + endpoint;
    }

    private String marshalEnvelope(Envelope envelope) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Envelope.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.UTF_8.name());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        marshaller.marshal(envelope, outputStream);
        return outputStream.toString(StandardCharsets.UTF_8.name());
    }

    private String sendPost(String endpoint, String body) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(endpoint).openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Token", token);
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

        try (OutputStream os = connection.getOutputStream()) {
            os.write(body.getBytes(StandardCharsets.UTF_8));
        }

        int statusCode = connection.getResponseCode();
        InputStream responseStream = statusCode >= 400 ? connection.getErrorStream() : connection.getInputStream();
        if (responseStream == null) {
            throw new Exception("Empty response from DMDK API. HTTP status: " + statusCode);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int read;
        while ((read = responseStream.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }

        String response = baos.toString(StandardCharsets.UTF_8.name());
        if (statusCode >= 400) {
            throw new Exception("DMDK API request failed. HTTP status: " + statusCode + ". Body: " + response);
        }
        return response;
    }

    public static Envelope parseRoot(String xml) throws Exception {
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
    }
}
