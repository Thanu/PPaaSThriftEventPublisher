package org.wso2.ppaas.thrift.publisher;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.databridge.agent.AgentHolder;
import org.wso2.carbon.databridge.agent.DataPublisher;
import org.wso2.carbon.databridge.agent.exception.DataEndpointAgentConfigurationException;
import org.wso2.carbon.databridge.agent.exception.DataEndpointAuthenticationException;
import org.wso2.carbon.databridge.agent.exception.DataEndpointConfigurationException;
import org.wso2.carbon.databridge.agent.exception.DataEndpointException;
import org.wso2.carbon.databridge.commons.Event;
import org.wso2.carbon.databridge.commons.exception.TransportException;

import java.io.File;
import java.util.Arrays;
import java.util.List;

class ThriftClient {
    private static Logger logger = LoggerFactory.getLogger(ThriftClient.class);
    private static String currentDir = new File(".").getAbsolutePath();
    private DataPublisher dataPublisher;

    ThriftClient(String type, String url, String authURL, String username, String password) throws
            DataEndpointAuthenticationException, DataEndpointAgentConfigurationException, TransportException,
            DataEndpointException, DataEndpointConfigurationException {
        System.setProperty("javax.net.ssl.trustStore", currentDir + File.separator + "resources" + File.separator +
                "client-truststore.jks");
        AgentHolder.setConfigPath(getDataAgentConfigPath());
        this.dataPublisher = new DataPublisher(type, url, authURL, username, password);
        sleep(1000); // wait until connection is established
    }

    private static String getDataAgentConfigPath() {
        return currentDir + File.separator + "conf" + File.separator + "data-agent-conf.xml";
    }

    public void publishEvents(List<Event> eventList) {
        if (eventList == null || eventList.size() == 0) {
            logger.warn("No events to be published. Aborting publishing...");
            return;
        }
        for (Event event : eventList) {
            logger.info(String.format("Publishing event with [payload] %s", Arrays.toString(event.getPayloadData())));
            boolean eventSent = dataPublisher.tryPublish(event);
            if (eventSent) {
                logger.info(String.format("Successfully published event with [payload] %s", Arrays.toString(event
                        .getPayloadData())));
                sleep(200);
            } else {
                throw new RuntimeException(String.format("Could not publish event with [payload] %s", Arrays.toString
                        (event.getPayloadData())));
            }
        }
    }

    public void shutdownClient() {
        try {
            dataPublisher.shutdownWithAgent();
            sleep(1000); // wait until connection is closed
        } catch (DataEndpointException ignored) {
        }
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            logger.error("Thread interrupted", e);
        }
    }
}
