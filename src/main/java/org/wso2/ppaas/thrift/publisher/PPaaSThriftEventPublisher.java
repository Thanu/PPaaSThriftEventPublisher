package org.wso2.ppaas.thrift.publisher;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.databridge.commons.Event;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.wso2.ppaas.thrift.publisher.Constants.EVENT_TYPE_KEY;
import static org.wso2.ppaas.thrift.publisher.Constants.THRIFT_EVENT_TYPE;

/**
 * Reads user data and converts to Thrift events, then publishes them to a Thrift receiver
 */
public class PPaaSThriftEventPublisher extends Thread {
    private static Logger logger = LoggerFactory.getLogger(PPaaSThriftEventPublisher.class);
    private static String currentDir = new File(".").getAbsolutePath();
    private static String memberLifeCycleJournalFile = currentDir + File.separator + "logs" + File.separator +
            Constants.MEMBER_LIFE_CYCLE_JOURNAL_FILENAME;
    private static String memberInfoJournalFile = currentDir + File.separator + "logs" + File.separator +
            Constants.MEMBER_INFO_JOURNAL_FILENAME;
    private Properties properties = new Properties();
    private TSV2ThriftConverter tsv2ThriftConverter = new TSV2ThriftConverter();
    private ThriftClient thriftClient;

    public PPaaSThriftEventPublisher() throws IOException {
        File propertiesFile = new File(System.getProperty(Constants.PROPERTIES_FILENAME_KEY));
        InputStream inputStream = new FileInputStream(propertiesFile);
        properties.load(inputStream);
        createJournalFile(memberLifeCycleJournalFile);
        createJournalFile(memberInfoJournalFile);
    }

    public void run() {
        try {
            String dataFile = properties.getProperty(Constants.THRIFT_PUBLISHER_DATA_FILE_KEY);
            logger.info(String.format("Reading user data file [source] %s", dataFile));
            tsv2ThriftConverter.readData();

            logger.info("Converting to Thrift event format...");
            List<Event> memberLifeCycleEvents = tsv2ThriftConverter.generateMemberLifeCycleEvents();
            List<Event> memberInfoEvents = tsv2ThriftConverter.generateMemberInfoEvents();
            writeEventsToJournal(memberLifeCycleJournalFile, memberLifeCycleEvents);
            writeEventsToJournal(memberInfoJournalFile, memberInfoEvents);

            String type = properties.getProperty(EVENT_TYPE_KEY, THRIFT_EVENT_TYPE);
            String url = properties.getProperty(Constants.THRIFT_RECEIVER_URL_KEY);
            String authURL = properties.getProperty(Constants.THRIFT_RECEIVER_AUTH_URL_KEY);
            String username = properties.getProperty(Constants.THRIFT_RECEIVER_USERNAME_KEY);
            String password = properties.getProperty(Constants.THRIFT_RECEIVER_PASSWORD_KEY);

            thriftClient = new ThriftClient(type, url, authURL, username, password);
            thriftClient.publishEvents(memberLifeCycleEvents);
            thriftClient.publishEvents(memberInfoEvents);
            logger.info("Successfully completed publishing PPaaS Thrift events");
        } catch (Exception e) {
            logger.error("Error while publishing PPaaS Thrift Events", e);
        } finally {
            thriftClient.shutdownClient();
            logger.info("Finished executing PPaaSThriftEventPublisher");
        }
    }

    private void createJournalFile(String filename) throws IOException {
        File file = new File(filename);
        FileUtils.writeStringToFile(file, "", "UTF-8");
    }

    private void writeEventsToJournal(String filename, List<Event> eventList) throws IOException {
        if (filename == null || filename.length() == 0 || eventList == null) {
            throw new IllegalArgumentException("Invalid arguments received for journal write operation");
        }
        PrintWriter out = null;
        try {
            File file = new File(filename);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            for (Event event : eventList) {
                out.println(Arrays.toString(event.getPayloadData()));
                out.flush();
            }
        } finally {
            if (out != null) {
                out.close();
            }
        }

    }
}
