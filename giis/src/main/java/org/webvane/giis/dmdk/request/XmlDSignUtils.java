package org.webvane.giis.dmdk.request;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Element;
import org.webvane.giis.dmdk.request.dmdk.crypto.DigitalSignatureFactory;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class XmlDSignUtils {
    private static final String JCP_PROVIDER_NAME = "JCP";

    public static Element sign(final PrivateKey privateKey, final X509Certificate cert, final Element data) {
        if (privateKey == null) {
            throw new IllegalArgumentException("Argument [privateKey] can't be null");
        }

        if (cert == null) {
            throw new IllegalArgumentException("Argument [cert] can't be null");
        }

        if (data == null) {
            throw new IllegalArgumentException("Argument [data] can't be null");
        }

        try {
            DigitalSignatureFactory.init(JCP_PROVIDER_NAME);

            final String id = data.getAttribute("Id");
            if (StringUtils.isBlank(id)) {
                data.setAttribute("Id", "id-" + Math.round(data.getTagName().length() * Math.random()));
            }

            return signXMLDSigDetached(privateKey, cert, data, null);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    private static Element signXMLDSigDetached(final PrivateKey privateKey, final X509Certificate cert, final Element element, final String signatureId) {
        try {
            return DigitalSignatureFactory.getDigitalSignatureProcessor().signXMLDSigDetached(element, signatureId, privateKey, cert);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static boolean validate(final Element data, final Element signature) {
        if (data == null) {
            throw new IllegalArgumentException("Argument [signature] can't be null");
        }
        if (signature == null) {
            throw new IllegalArgumentException("Argument [signature] can't be null");
        }

        try {

            DigitalSignatureFactory.init(JCP_PROVIDER_NAME);
            final X509Certificate cert = DigitalSignatureFactory.getDigitalSignatureProcessor().validateXMLDSigDetachedSignature(data, signature);

            cert.checkValidity();

            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
