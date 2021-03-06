package org.wso2.ppaas.thrift.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.databridge.commons.Event;

import java.io.*;
import java.util.List;
import java.util.Properties;

import static org.wso2.ppaas.thrift.publisher.Constants.EVENT_TYPE_KEY;
import static org.wso2.ppaas.thrift.publisher.Constants.THRIFT_EVENT_TYPE;

/**
 * Reads user data and converts to Thrift events, then publishes them to a Thrift receiver
 */
public class PPaaSThriftEventPublisher extends Thread {
    private static Logger logger = LoggerFactory.getLogger(PPaaSThriftEventPublisher.class);
    private static String memberLifeCycleJournalFile = Constants.PPAAS_PUBLISHER_HOME_DIR + File.separator + "logs" +
            File.separator + Constants.MEMBER_LIFE_CYCLE_JOURNAL_FILENAME;
    private static String memberInfoJournalFile = Constants.PPAAS_PUBLISHER_HOME_DIR + File.separator + "logs" + File
            .separator + Constants.MEMBER_INFO_JOURNAL_FILENAME;
    private static String scalingDecisionJournalFile = Constants.PPAAS_PUBLISHER_HOME_DIR + File.separator + "logs" +
            File.separator + Constants.SCALING_DECISION_JOURNAL_FILENAME;
    private Properties properties = new Properties();
    private TSV2ThriftConverter tsv2ThriftConverter = new TSV2ThriftConverter();
    private ThriftClient thriftClient;
    private ObjectMapper objectMapper = new ObjectMapper();

    public PPaaSThriftEventPublisher() throws IOException {
        File propertiesFile = new File(System.getProperty(Constants.PROPERTIES_FILENAME_KEY));
        InputStream inputStream = new FileInputStream(propertiesFile);
        properties.load(inputStream);
        createJournalFile(memberLifeCycleJournalFile);
        createJournalFile(memberInfoJournalFile);
        createJournalFile(scalingDecisionJournalFile);
    }

    public void run() {
        try {
            tsv2ThriftConverter.readData();
            logger.info("Converting to Thrift event format...");
            List<Event> memberLifeCycleEvents = tsv2ThriftConverter.generateMemberLifeCycleEvents();
            List<Event> memberInfoEvents = tsv2ThriftConverter.generateMemberInfoEvents();
            List<Event> scalingDecisionEvents = tsv2ThriftConverter.generateScalingDecisionEvents();
            writeEventsToJournal(memberLifeCycleJournalFile, memberLifeCycleEvents);
            writeEventsToJournal(memberInfoJournalFile, memberInfoEvents);
            writeEventsToJournal(scalingDecisionJournalFile, scalingDecisionEvents);

            String type = properties.getProperty(EVENT_TYPE_KEY, THRIFT_EVENT_TYPE);
            String url = properties.getProperty(Constants.THRIFT_RECEIVER_URL_KEY);
            String authURL = properties.getProperty(Constants.THRIFT_RECEIVER_AUTH_URL_KEY);
            String username = properties.getProperty(Constants.THRIFT_RECEIVER_USERNAME_KEY);
            String password = properties.getProperty(Constants.THRIFT_RECEIVER_PASSWORD_KEY);

            thriftClient = new ThriftClient(type, url, authURL, username, password);
            thriftClient.publishEvents(memberLifeCycleEvents);
            thriftClient.publishEvents(memberInfoEvents);
            thriftClient.publishEvents(scalingDecisionEvents);
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
        StringWriter stringWriter = new StringWriter();
        objectMapper.writeValue(stringWriter, eventList);
        PrintWriter out = null;
        try {
            File file = new File(filename);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            out.println(stringWriter.toString());
            out.flush();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
