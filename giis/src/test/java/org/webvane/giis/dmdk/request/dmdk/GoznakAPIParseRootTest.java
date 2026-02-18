package org.webvane.giis.dmdk.request.dmdk;

import org.junit.Test;
import org.webvane.giis.dmdk.request.dmdk.elements.response.CheckGetContractorResponseData;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GoznakAPIParseRootTest {

    @Test
    public void parseRoot_shouldParseSendGetBatchResponseWithBenemedNamespace() throws Exception {
        String xml = "<?xml version='1.0' encoding='utf-8'?>\n"
                + "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"urn://xsd.benemed/exchange/1.0\">\n"
                + "  <soapenv:Header/>\n"
                + "  <soapenv:Body>\n"
                + "    <ns:SendGetBatchResponse>\n"
                + "      <ns:ResponseData>\n"
                + "        <ns:messageId>187710df-b9df-4476-aadd-e41e6d095e00</ns:messageId>\n"
                + "        <ns:status>Prepared</ns:status>\n"
                + "        <ns:error></ns:error>\n"
                + "      </ns:ResponseData>\n"
                + "    </ns:SendGetBatchResponse>\n"
                + "  </soapenv:Body>\n"
                + "</soapenv:Envelope>";

        Envelope envelope = GoznakAPI.parseRoot(xml);

        assertNotNull(envelope);
        assertNotNull(envelope.getBody());
        assertNotNull(envelope.getBody().getResponse());
        assertNotNull(envelope.getBody().getResponse().getResponseData());
        assertEquals("187710df-b9df-4476-aadd-e41e6d095e00", envelope.getBody().getResponse().getResponseData().getMessageId());
        assertEquals("Prepared", envelope.getBody().getResponse().getResponseData().getStatus());
        assertEquals("", envelope.getBody().getResponse().getResponseData().getError());
    }

    @Test
    public void parseRoot_shouldParseCheckGetContractorResponseWithNs2Ns3() throws Exception {
        String xml = "<?xml version='1.0' encoding='utf-8'?>\n"
                + "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns2=\"urn://xsd.benemed/exchange/1.0\" xmlns:ns3=\"urn://xsd.benemed/contractor/1.0\">\n"
                + "  <soapenv:Header/>\n"
                + "  <soapenv:Body>\n"
                + "    <ns2:CheckGetContractorResponse>\n"
                + "      <ns2:ResponseData id=\"resp1\">\n"
                + "        <ns2:messageId>80b92f51-d792-4916-b851-890c7a0aeb4b</ns2:messageId>\n"
                + "        <ns2:status>PREPARED</ns2:status>\n"
                + "        <ns2:result>\n"
                + "          <ns3:IDTOP>105</ns3:IDTOP>\n"
                + "          <ns3:UNP>100129348</ns3:UNP>\n"
                + "          <ns3:name>ОАО \"Белювелирторг\"</ns3:name>\n"
                + "          <ns3:address>Беларусь, г. Минск, УЛ. СМОЛЕНСКАЯ, дом 33</ns3:address>\n"
                + "          <ns3:grantSale>true</ns3:grantSale>\n"
                + "        </ns2:result>\n"
                + "        <ns2:error/>\n"
                + "      </ns2:ResponseData>\n"
                + "    </ns2:CheckGetContractorResponse>\n"
                + "  </soapenv:Body>\n"
                + "</soapenv:Envelope>";

        Envelope envelope = GoznakAPI.parseRoot(xml);
        CheckGetContractorResponseData responseData = (CheckGetContractorResponseData) envelope.getBody().getResponse().getResponseData();

        assertNotNull(responseData);
        assertEquals("80b92f51-d792-4916-b851-890c7a0aeb4b", responseData.getMessageId());
        assertEquals("PREPARED", responseData.getStatus());
        assertNotNull(responseData.getResult());
        assertEquals(1, responseData.getResult().size());
        assertEquals("100129348", responseData.getResult().get(0).getInn());
        assertEquals("105", responseData.getResult().get(0).getIdtop());
    }
}
