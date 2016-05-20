package org.wso2.ppaas.thrift.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Hello world!
 */
public class PPaaSThriftEventPublisher extends Thread {
    private static Logger logger = LoggerFactory.getLogger(PPaaSThriftEventPublisher.class);
    private Properties properties = new Properties();
    private TSV2ThriftConverter tsv2ThriftConverter = new TSV2ThriftConverter();
    private ThriftClient thriftClient;

    public PPaaSThriftEventPublisher() throws Exception {
        File propertiesFile = new File(System.getProperty(Constants.PROPERTIES_FILENAME_KEY));
        InputStream inputStream = new FileInputStream(propertiesFile);
        properties.load(inputStream);
    }

    public void run() {
        try {
            String dataFile = properties.getProperty(Constants.THRIFT_PUBLISHER_DATA_FILE_KEY);
            logger.info(String.format("Reading user data file [source] %s", dataFile));
            tsv2ThriftConverter.readData();

            logger.info("Converting to Thrift event format...");
            tsv2ThriftConverter.convert2ThriftEvents();

            String type = properties.getProperty("type", "Thrift");
            String url = properties.getProperty(Constants.THRIFT_RECEIVER_URL_KEY);
            String authURL = properties.getProperty(Constants.THRIFT_RECEIVER_AUTH_URL_KEY);
            String username = properties.getProperty(Constants.THRIFT_RECEIVER_USERNAME_KEY);
            String passwrod = properties.getProperty(Constants.THRIFT_RECEIVER_PASSWORD_KEY);
            thriftClient = new ThriftClient(type, url, authURL, username, passwrod);

            logger.info("Successfully completed ");
        } catch (Exception e) {
            logger.error("Could not publish PPaaS Thrift Events", e);
        } finally {
            logger.info("Finished executing PPaaSThriftEventPublisher");
        }
    }
}
