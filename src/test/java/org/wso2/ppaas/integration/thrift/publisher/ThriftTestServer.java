package org.wso2.ppaas.integration.thrift.publisher;

import org.apache.log4j.Logger;
import org.wso2.carbon.databridge.commons.Credentials;
import org.wso2.carbon.databridge.commons.Event;
import org.wso2.carbon.databridge.commons.StreamDefinition;
import org.wso2.carbon.databridge.commons.exception.MalformedStreamDefinitionException;
import org.wso2.carbon.databridge.commons.utils.EventDefinitionConverterUtils;
import org.wso2.carbon.databridge.core.AgentCallback;
import org.wso2.carbon.databridge.core.DataBridge;
import org.wso2.carbon.databridge.core.Utils.AgentSession;
import org.wso2.carbon.databridge.core.definitionstore.InMemoryStreamDefinitionStore;
import org.wso2.carbon.databridge.core.exception.DataBridgeException;
import org.wso2.carbon.databridge.core.exception.StreamDefinitionStoreException;
import org.wso2.carbon.databridge.core.internal.authentication.AuthenticationHandler;
import org.wso2.carbon.databridge.receiver.thrift.ThriftDataReceiver;
import org.wso2.carbon.user.api.UserStoreException;

import java.net.SocketException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ThriftTestServer {
    private Logger log = Logger.getLogger(ThriftTestServer.class);
    private ThriftDataReceiver thriftDataReceiver;
    private InMemoryStreamDefinitionStore streamDefinitionStore;
    private AtomicInteger numberOfEventsReceived = new AtomicInteger(0);
    private RestarterThread restarterThread;
    private AgentCallback agentCallback;

    public ThriftTestServer() {
        this.agentCallback = new AgentCallback() {
            int totalSize = 0;

            public void definedStream(StreamDefinition streamDefinition,
                                      int tenantId) {
                log.info("StreamDefinition " + streamDefinition);
            }

            @Override
            public void removeStream(StreamDefinition streamDefinition, int tenantId) {
                log.info("StreamDefinition remove " + streamDefinition);
            }

            @Override
            public void receive(List<Event> eventList, Credentials credentials) {
                numberOfEventsReceived.addAndGet(eventList.size());
                log.info("Received events : " + numberOfEventsReceived);
            }
        };
    }

    public ThriftTestServer(AgentCallback agentCallback) {
        this.agentCallback = agentCallback;
    }

    public void addStreamDefinition(StreamDefinition streamDefinition, int tenantId)
            throws StreamDefinitionStoreException {
        streamDefinitionStore.saveStreamDefinitionToStore(streamDefinition, tenantId);
    }

    public void addStreamDefinition(String streamDefinitionStr, int tenantId)
            throws StreamDefinitionStoreException, MalformedStreamDefinitionException {
        StreamDefinition streamDefinition = EventDefinitionConverterUtils.convertFromJson(streamDefinitionStr);
        getStreamDefinitionStore().saveStreamDefinitionToStore(streamDefinition, tenantId);
    }

    private InMemoryStreamDefinitionStore getStreamDefinitionStore() {
        if (streamDefinitionStore == null) {
            streamDefinitionStore = new InMemoryStreamDefinitionStore();
        }
        return streamDefinitionStore;
    }

    public void start(int receiverPort) throws DataBridgeException {
        DataPublisherTestUtil.setKeyStoreParams();
        streamDefinitionStore = getStreamDefinitionStore();
        DataBridge databridge = new DataBridge(new AuthenticationHandler() {
            @Override
            public boolean authenticate(String userName, String password) {
                log.info("Thrift authentication returning true");
                return true;// allays authenticate to true
            }

            @Override
            public String getTenantDomain(String userName) {
                return "admin";
            }

            @Override
            public int getTenantId(String tenantDomain) throws UserStoreException {
                return -1234;
            }

            @Override
            public void initContext(AgentSession agentSession) {
                //To change body of implemented methods use File | Settings | File Templates.
                log.info("Initializing Thrift agent context");
            }

            @Override
            public void destroyContext(AgentSession agentSession) {

            }
        }, streamDefinitionStore, DataPublisherTestUtil.getDataBridgeConfigPath());

        thriftDataReceiver = new ThriftDataReceiver(receiverPort, databridge);

        databridge.subscribe(this.agentCallback);

        String address = "localhost";
        log.info("Test Server starting on " + address);
        thriftDataReceiver.start(address);
        log.info("Test Server Started");
    }

    public int getNumberOfEventsReceived() {
        if (numberOfEventsReceived != null) {
            return numberOfEventsReceived.get();
        } else {
            return 0;
        }
    }

    public void resetReceivedEvents() {
        numberOfEventsReceived.set(0);
    }

    public void stop() {
        thriftDataReceiver.stop();
        log.info("Test Server Stopped");
    }

    public void stopAndStartDuration(int port, long stopAfterTimeMilliSeconds, long startAfterTimeMS)
            throws SocketException, DataBridgeException {
        restarterThread = new RestarterThread(port, stopAfterTimeMilliSeconds, startAfterTimeMS);
        Thread thread = new Thread(restarterThread);
        thread.start();
    }

    public int getEventsReceivedBeforeLastRestart() {
        return restarterThread.eventReceived;
    }


    private class RestarterThread implements Runnable {
        int eventReceived;
        int port;

        long stopAfterTimeMilliSeconds;
        long startAfterTimeMS;

        RestarterThread(int port, long stopAfterTime, long startAfterTime) {
            this.port = port;
            stopAfterTimeMilliSeconds = stopAfterTime;
            startAfterTimeMS = startAfterTime;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(stopAfterTimeMilliSeconds);
            } catch (InterruptedException e) {
                log.error(e);
            }
            if (thriftDataReceiver != null) {
                thriftDataReceiver.stop();
            }

            eventReceived = getNumberOfEventsReceived();

            log.info("Number of events received in server shutdown :" + eventReceived);
            try {
                Thread.sleep(startAfterTimeMS);
            } catch (InterruptedException e) {
                log.error(e);
            }

            try {
                if (thriftDataReceiver != null) {
                    thriftDataReceiver.start(DataPublisherTestUtil.LOCAL_HOST);
                } else {
                    start(port);
                }
            } catch (DataBridgeException e) {
                log.error(e);
            }

        }
    }
}