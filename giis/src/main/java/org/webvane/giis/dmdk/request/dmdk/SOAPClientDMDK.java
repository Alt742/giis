package org.webvane.giis.dmdk.request.dmdk;

import javax.xml.soap.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;


public class SOAPClientDMDK {
    public static SOAPMessage callSoapWebService(String soapEndpointUrl, String soapAction) {
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction), soapEndpointUrl);

            // Print the SOAP Response
            System.out.println("Response SOAP Message:");
            soapResponse.writeTo(System.out);
            System.out.println();
            soapConnection.close();
            return soapResponse;
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
        return null;
    }

    private static SOAPMessage createSOAPRequest(String send) throws Exception {
        InputStream is = new ByteArrayInputStream(send.getBytes());
        SOAPMessage request = MessageFactory.newInstance().createMessage(null, is);

        return request;
    }
}
