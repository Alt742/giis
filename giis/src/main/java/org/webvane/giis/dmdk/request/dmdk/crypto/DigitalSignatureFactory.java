package org.webvane.giis.dmdk.request.dmdk.crypto;

import org.apache.xml.security.transforms.Transform;
import org.apache.xml.security.utils.XMLUtils;
import org.webvane.giis.dmdk.request.dmdk.crypto.exceptions.SigLibInitializationException;
import org.webvane.giis.dmdk.request.dmdk.crypto.impl.CacheOptions;
import org.webvane.giis.dmdk.request.dmdk.crypto.impl.CachingKeyStoreWrapper;
import org.webvane.giis.dmdk.request.dmdk.crypto.impl.DigitalSignatureProcessorImpl;
import org.webvane.giis.dmdk.request.dmdk.crypto.impl.SmevTransformSpi;
import org.webvane.giis.dmdk.request.dmdk.crypto.impl.csp_tj.TrustedKeyStoreWrapperCSP;
import org.webvane.giis.dmdk.request.dmdk.crypto.impl.jcp.KeyStoreWrapperJCP;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import ru.CryptoPro.JCPxml.xmldsig.JCPXMLDSigInit;

public class DigitalSignatureFactory {

    private static String providerName = null;
    private static volatile DigitalSignatureProcessor processor = null;
    private static volatile KeyStoreWrapper keyStoreWrapper = null;

    public static final String CSP_TJ_PROVIDER_NAME = "DIGT";
    public static final String JCP_PROVIDER_NAME = "JCP";

    public static synchronized void init(KeyStoreWrapper keyStoreWrapper, DigitalSignatureProcessor processor) throws SigLibInitializationException {
        DigitalSignatureFactory.keyStoreWrapper = keyStoreWrapper;
        DigitalSignatureFactory.processor = processor;
    }

    public static synchronized void init(String providerName) throws SigLibInitializationException {
        if (processor == null) {
            if (providerName == null) {
                throw new IllegalArgumentException("Метод вызван впервые. Должно быть задано имя провайдера");
            }
            try {
                if (CSP_TJ_PROVIDER_NAME.equals(providerName)) {
                    initXmlSec();
                    keyStoreWrapper = new TrustedKeyStoreWrapperCSP();
                } else if (JCP_PROVIDER_NAME.equals(providerName)) {
                    initXmlSec();
                    keyStoreWrapper = new KeyStoreWrapperJCP();
                } else {
                    throw new SigLibInitializationException("Процессор для запрошенного провайдера не найден!");
                }
            } catch (SigLibInitializationException e) {
                throw e;
            } catch (Exception e) {
                throw new SigLibInitializationException("Не удалось инищиализировать фабрику!", e);
            }
            DigitalSignatureFactory.providerName = providerName;
            processor = new DigitalSignatureProcessorImpl();
        } else {
            if (!DigitalSignatureFactory.providerName.equals(providerName)) {
                throw new SigLibInitializationException("Процессор уже инициализирован для криптопровайдера: " + DigitalSignatureFactory.providerName + "!");
            }
        }
    }

    private static void initXmlSec() throws SigLibInitializationException {
        try {

            // При формировании элемента Signature будут убраны все разрывы между элементами.
            System.setProperty("org.apache.xml.security.ignoreLineBreaks", "true");

            if (!JCPXMLDSigInit.isInitialized()) {
                JCPXMLDSigInit.init();
            }

            // Реализация дополнительной трансформации.
            Transform.register(SmevTransformSpi.ALGORITHM_URN, SmevTransformSpi.class.getName());
            hackXmlsecIgnoreLineBreaks(true);

        } catch (SigLibInitializationException e) {
            throw e;
        } catch (Exception e) {
            throw new SigLibInitializationException("Возникли проблемы при инициализации XmlSec!", e);
        }
    }

    private static void hackXmlsecIgnoreLineBreaks(Boolean mode) throws PrivilegedActionException {
        Boolean currMode = mode;
        AccessController.doPrivileged((PrivilegedExceptionAction<Boolean>) () -> {
            Field f = XMLUtils.class.getDeclaredField("ignoreLineBreaks");
            f.setAccessible(true);
            f.set(null, currMode);
            return false;
        });
    }

    public static DigitalSignatureProcessor getDigitalSignatureProcessor() throws SigLibInitializationException {
        DigitalSignatureProcessor p = processor;
        if (p == null) {
            throw new SigLibInitializationException("Перед использованием фабрику необходимо инициализировать!");
        }
        return p;
    }

    public static KeyStoreWrapper getKeyStoreWrapper() throws SigLibInitializationException {
        return getKeyStoreWrapper(null);
    }

    public static KeyStoreWrapper getKeyStoreWrapper(CacheOptions options) throws SigLibInitializationException {
        KeyStoreWrapper ks = keyStoreWrapper;
        if (ks == null) {
            throw new SigLibInitializationException("Перед использованием фабрику необходимо инициализировать!");
        }
        if (options == null) {
            return ks;
        } else {
            return new CachingKeyStoreWrapper(ks, options);
        }
    }
}
