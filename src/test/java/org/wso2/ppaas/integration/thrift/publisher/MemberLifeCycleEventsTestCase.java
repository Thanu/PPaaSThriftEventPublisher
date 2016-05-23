package org.wso2.ppaas.integration.thrift.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.wso2.carbon.databridge.commons.Credentials;
import org.wso2.carbon.databridge.commons.Event;
import org.wso2.carbon.databridge.commons.StreamDefinition;
import org.wso2.carbon.databridge.core.AgentCallback;
import org.wso2.ppaas.thrift.publisher.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MemberLifeCycleEventsTestCase extends ThriftEventPublisherIntegrationTest {
    private final Logger logger = LoggerFactory.getLogger(MemberLifeCycleEventsTestCase.class);
    private final String TEST_CASE_RESOURCES = "member-life-cycle-events";
    private final String MEMBER_LIFE_CYCLE_STREAM_FILENAME = "member_lifecycle_1.0.0.json";
    private ThriftTestServer thriftTestServer;

    public MemberLifeCycleEventsTestCase() throws Exception {
        thriftTestServer = new ThriftTestServer(new AgentCallback() {
            private AtomicInteger numberOfEventsReceived = new AtomicInteger(0);

            @Override
            public void definedStream(StreamDefinition streamDefinition, int i) {
                logger.info("StreamDefinition " + streamDefinition);
            }

            @Override
            public void removeStream(StreamDefinition streamDefinition, int i) {
                logger.info("StreamDefinition remove " + streamDefinition);
            }

            @Override
            public void receive(List<Event> eventList, Credentials credentials) {
                numberOfEventsReceived.addAndGet(eventList.size());
                logger.info("Received events : " + numberOfEventsReceived);
            }
        });
    }

    @Test(description = "Generate Member-Life-Cycle events",
            timeOut = 60000)
    public void testMemberLifeCycleEvents() throws Exception {
        logger.info("Generating Member-Life-Cycle events...");

        File file = new File(getTestResourcesPath() + TEST_CASE_RESOURCES + File.separator +
                MEMBER_LIFE_CYCLE_STREAM_FILENAME);
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        String streamDefString = new String(data, "UTF-8");
        if (streamDefString.equals("")) {
            logger.warn("Stream definition of health stat stream is empty. Thrift server will not function " +
                    "properly");
        }
        thriftTestServer.addStreamDefinition(streamDefString, -1234);
        // start with non-ssl port; test server will automatically bind to ssl port
        thriftTestServer.start(7611);
        logger.info("Started Thrift server with stream definition: " + streamDefString);
        Thread.sleep(2000);

        String testDataFilename = getTestResourcesPath() + TEST_CASE_RESOURCES + File.separator +
                Constants.EVENTS_DATA_TSV;
        String dest = getPPaaSThriftEventPublisherHome() + File.separator + "data";
        logger.info(String.format("Copying [file] %s to [dest] %s", testDataFilename, dest));
        ThriftEventPublisherIntegrationTest.copyFile(testDataFilename, dest);

        startPublisher();

        while (!getInputStreamLogs().contains("Finished executing PPaaSThriftEventPublisher")) {
            Thread.sleep(1000);
        }
        logger.info("================ MemberLifeCycleEventsTestCase completed ==================");
    }
}
