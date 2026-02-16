package org.webvane.giis.dmdk.request.dmdk;

import org.webvane.giis.dmdk.request.dmdk.elements.*;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.xpath.XPathExpressionException;
import java.util.List;
import java.util.Map;


import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.XMLConstants;
import org.xml.sax.InputSource;
import java.io.StringReader;

public class GoznakAPI {
    private String soapAddress;
    private String alies;
    private String password;


    public GoznakAPI(String soapAdress, String alies, String password){
        this.soapAddress = soapAdress;
        this.alies = alies;
        this.password = password;
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

        //String signMsg = goznakRequest.signRequest(this.ksw, this.dsp, this.alies, this.password);

        //SOAPMessage response = SOAPClientDMDK.callSoapWebService(this.soapAddress, signMsg);

        //Node n = response.getSOAPBody().getParentNode();
        //n.normalize();

        //JAXBContext context = JAXBContext.newInstance(Envelope.class);
        //Unmarshaller unmarshaller = context.createUnmarshaller();

        //JAXBElement<Envelope> personElement = unmarshaller.unmarshal(n, Envelope.class);
        //return personElement.getValue();

        String xml="<?xml version='1.0' encoding='utf-8'?>\n" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns2=\"urn://xsd.benemed/exchange/1.0\" xmlns:ns3=\"urn://xsd.benemed/batch/1.0\" xmlns:ns4=\"urn://xsd.benemed/contractor/1.0\">\n" +
                "  <soapenv:Header/>\n" +
                "  <soapenv:Body>\n" +
                "    <ns2:CheckGetBatchResponse>\n" +
                "      <ns2:ResponseData id=\"resp1\">\n" +
                "        <ns2:messageId>0d8fed29-a2d4-44bf-b396-171845561423</ns2:messageId>\n" +
                "        <ns2:status>PREPARED</ns2:status>\n" +
                "        <ns2:result>\n" +
                "          <ns3:UIN_INP>1122610029883294</ns3:UIN_INP>\n" +
                "          <ns3:name>Серьга-кафф</ns3:name>\n" +
                "          <ns3:type>PRODUCT</ns3:type>\n" +
                "          <ns3:subType>JEWERLY</ns3:subType>\n" +
                "          <ns3:category>JT_EARRINGS</ns3:category>\n" +
                "          <ns3:process>STORED</ns3:process>\n" +
                "          <ns3:status>STORING</ns3:status>\n" +
                "          <ns3:weight>244000</ns3:weight>\n" +
                "          <ns3:batchProduct>\n" +
                "            <ns3:metalList>\n" +
                "              <ns3:sortNumber>1</ns3:sortNumber>\n" +
                "              <ns3:hallmark>92500</ns3:hallmark>\n" +
                "              <ns3:metal>DM_SILVER</ns3:metal>\n" +
                "              <ns3:weight>3000</ns3:weight>\n" +
                "            </ns3:metalList>\n" +
                "            <ns3:stoneList>\n" +
                "              <ns3:type>DIAMOND_REFINED</ns3:type>\n" +
                "              <ns3:shape>DIAMOND_REFINED_MOD_P</ns3:shape>\n" +
                "              <ns3:quality>DIAMOND_REFINED_3_GROUP</ns3:quality>\n" +
                "              <ns3:weight>100000</ns3:weight>\n" +
                "              <ns3:uom>CTM</ns3:uom>\n" +
                "            </ns3:stoneList>\n" +
                "            <ns3:nuggetList>\n" +
                "              <ns3:metalList>\n" +
                "                <ns3:hallmark>12200</ns3:hallmark>\n" +
                "                <ns3:metal>DM_SILVER</ns3:metal>\n" +
                "                <ns3:weight>2000</ns3:weight>\n" +
                "              </ns3:metalList>\n" +
                "            </ns3:nuggetList>\n" +
                "          </ns3:batchProduct>\n" +
                "          <ns3:brand/>\n" +
                "          <ns3:article>1991</ns3:article>\n" +
                "        </ns2:result>\n" +
                "        <ns2:error/>\n" +
                "        <ns2:page>1</ns2:page>\n" +
                "        <ns2:pages>1</ns2:pages>\n" +
                "        <ns2:size>1</ns2:size>\n" +
                "      </ns2:ResponseData>\n" +
                "    </ns2:CheckGetBatchResponse>\n" +
                "  </soapenv:Body>\n" +
                "</soapenv:Envelope>\n";
        return parseRoot(xml);
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
