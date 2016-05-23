package org.wso2.ppaas.test.integration.thrift.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemberInfoEventsTestCase extends ThriftEventPublisherIntegrationTest {
    private final Logger logger = LoggerFactory.getLogger(MemberInfoEventsTestCase.class);
    private final String TEST_CASE_RESOURCES = "member-info-events";
    private final String MEMBER_INFO_STREAM_FILENAME = "member_info_1.0.0.json";
    private ThriftTestServer thriftTestServer = new ThriftTestServer();

/*    @Test(description = "Test Member-Info events",
            timeOut = 60000)
    public void testMemberInfoEvents() throws Exception {
        logger.info("Testing Member-Info events...");

        Path streamDefFilePath = Paths.get(getTestResourcesPath(), TEST_CASE_RESOURCES,
                MEMBER_INFO_STREAM_FILENAME);
        String streamDefString = new String(Files.readAllBytes(streamDefFilePath), "UTF-8");
        assertNotEquals(streamDefString, "");
        thriftTestServer.addStreamDefinition(streamDefString, -1234);
        // start with non-ssl port; test server will automatically bind to ssl port
        logger.info("Starting Thrift server...");
        thriftTestServer.start(7611);
        Thread.sleep(2000);
        logger.info("Started Thrift server with stream definition: " + streamDefString);

        String testDataFilename = getTestResourcesPath() + TEST_CASE_RESOURCES + File.separator +
                Constants.EVENTS_DATA_TSV;
        String dest = getPPaaSThriftEventPublisherHome() + File.separator + "data";
        logger.info(String.format("Copying [file] %s to [dest] %s", testDataFilename, dest));
        ThriftEventPublisherIntegrationTest.copyFile(testDataFilename, dest);

        startPublisher();

        while (!getInputStreamLogs().contains("Finished executing PPaaSThriftEventPublisher")) {
            Thread.sleep(1000);
        }
        assertNotNull(thriftTestServer.getReceivedEventList());

        Path generatedEventsFilePath = Paths.get(getPPaaSThriftEventPublisherHome(), "logs",
                Constants.MEMBER_INFO_JOURNAL_FILENAME);
        String generatedEventsStr = new String(Files.readAllBytes(generatedEventsFilePath), "UTF-8");
        Event[] eventArr = objectMapper.readValue(generatedEventsStr, Event[].class);
        List<Event> generatedEventList = Arrays.asList(eventArr);
        assertEquals(thriftTestServer.getReceivedEventList().size(), generatedEventList.size());
        for (Event generatedEvent : generatedEventList) {
            Assert.assertTrue(hasGeneratedEventReceived(thriftTestServer.getReceivedEventList(), generatedEvent));
        }
        logger.info("Shutting down Thrift server...");
        thriftTestServer.stop();
        Thread.sleep(2000);
        logger.info("================ MemberInfoEventsTestCase completed ==================");
    }*/
}
