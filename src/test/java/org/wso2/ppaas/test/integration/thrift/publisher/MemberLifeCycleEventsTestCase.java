package org.wso2.ppaas.test.integration.thrift.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.wso2.carbon.databridge.commons.Credentials;
import org.wso2.carbon.databridge.commons.Event;
import org.wso2.carbon.databridge.commons.StreamDefinition;
import org.wso2.carbon.databridge.core.AgentCallback;
import org.wso2.ppaas.test.TestUtils;
import org.wso2.ppaas.thrift.publisher.Constants;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.testng.Assert.*;

public class MemberLifeCycleEventsTestCase extends ThriftEventPublisherIntegrationTest {
    private final Logger logger = LoggerFactory.getLogger(MemberLifeCycleEventsTestCase.class);
    private final String TEST_CASE_RESOURCES = "member-life-cycle-events";
    private final String MEMBER_LIFE_CYCLE_STREAM_FILENAME = "member_lifecycle_1.0.0.json";
    private List<Event> receivedEventList = new ArrayList<>();
    private ThriftTestServer thriftTestServer = new ThriftTestServer(new AgentCallback() {
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
            receivedEventList.addAll(eventList);

           /* if (numberOfEventsReceived.get() == 1) {
                thriftTestServer.stop();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }*/
        }
    });

    @Test(description = "Test Member-Life-Cycle events",
            timeOut = 60000)
    public void testMemberLifeCycleEvents() throws Exception {
        logger.info("Testing Member-Life-Cycle events...");

        Path streamDefFilePath = Paths.get(TestUtils.getTestResourcesPath(), TEST_CASE_RESOURCES,
                MEMBER_LIFE_CYCLE_STREAM_FILENAME);
        String streamDefString = new String(Files.readAllBytes(streamDefFilePath), "UTF-8");
        assertNotEquals(streamDefString, "");
        thriftTestServer.addStreamDefinition(streamDefString, -1234);
        // start with non-ssl port; test server will automatically bind to ssl port
        logger.info("Starting Thrift server...");
        thriftTestServer.start(7611);
        Thread.sleep(5000);
        logger.info("Started Thrift server with stream definition: " + streamDefString);

        String testDataFilename = TestUtils.getTestResourcesPath() + TEST_CASE_RESOURCES + File.separator +
                Constants.EVENTS_DATA_TSV;
        String dest = getPPaaSThriftEventPublisherHome() + File.separator + "data";
        logger.info(String.format("Copying [file] %s to [dest] %s", testDataFilename, dest));
        copyFile(testDataFilename, dest);

        startPublisher();

        while (!getInputStreamLogs().contains("Finished executing PPaaSThriftEventPublisher")) {
            Thread.sleep(1000);
        }
        assertNotNull(receivedEventList);

        Path generatedEventsFilePath = Paths.get(getPPaaSThriftEventPublisherHome(), "logs",
                Constants.MEMBER_LIFE_CYCLE_JOURNAL_FILENAME);
        String generatedEventsStr = new String(Files.readAllBytes(generatedEventsFilePath), "UTF-8");
        Event[] eventArr = objectMapper.readValue(generatedEventsStr, Event[].class);
        List<Event> generatedEventList = Arrays.asList(eventArr);
        assertEquals(receivedEventList.size(), generatedEventList.size());
        for (Event generatedEvent : generatedEventList) {
            Assert.assertTrue(TestUtils.hasGeneratedEventReceived(receivedEventList, generatedEvent));
        }
        logger.info("Shutting down Thrift server...");
        thriftTestServer.stop();
        Thread.sleep(2000);
        logger.info("================ MemberLifeCycleEventsTestCase completed ==================");
    }
}
