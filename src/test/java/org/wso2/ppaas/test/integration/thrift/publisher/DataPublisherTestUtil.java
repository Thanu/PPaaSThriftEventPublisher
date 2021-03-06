package org.wso2.ppaas.test.integration.thrift.publisher;

import org.wso2.ppaas.test.TestUtils;

import java.io.File;

public class DataPublisherTestUtil {
    public static final String LOCAL_HOST = "localhost";

    public static void setTrustStoreParams() {
        String trustStorePath = ThriftEventPublisherIntegrationTest.getPPaaSThriftEventPublisherHome() +
                File.separator + "resources";
        System.setProperty("javax.net.ssl.trustStore", trustStorePath + File.separator + "client-truststore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "wso2carbon");
    }

    public static void setKeyStoreParams() {
        String keyStore = TestUtils.getTestResourcesPath();
        System.setProperty("Security.KeyStore.Location", keyStore + File.separator + "wso2carbon.jks");
        System.setProperty("Security.KeyStore.Password", "wso2carbon");
    }

    public static String getDataBridgeConfigPath() {
        String filePath = TestUtils.getTestResourcesPath();
        return filePath + File.separator + "data-bridge-config.xml";
    }
}
