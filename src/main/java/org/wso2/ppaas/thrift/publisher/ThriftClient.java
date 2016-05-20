package org.wso2.ppaas.thrift.publisher;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.databridge.agent.AgentHolder;
import org.wso2.carbon.databridge.agent.DataPublisher;
import org.wso2.carbon.databridge.commons.Event;

import java.io.File;
import java.util.List;

public class ThriftClient {
    private static Logger logger = LoggerFactory.getLogger(ThriftClient.class);
    private static String currentDir = new File(".").getAbsolutePath();
    private DataPublisher dataPublisher;

    public ThriftClient(String type, String url, String authURL, String username, String password) throws Exception {
        System.setProperty("javax.net.ssl.trustStore", currentDir + File.separator + "resources" + File.separator +
                "client-truststore.jks");
        AgentHolder.setConfigPath(getDataAgentConfigPath());
        this.dataPublisher = new DataPublisher(type, url, authURL, username, password);
    }

    private static String getDataAgentConfigPath() {
        return currentDir + File.separator + "conf" + File.separator + "data-agent-conf.xml";
    }

    public void publishEvents(List<Event> eventList) throws Exception {

    }
}
