package org.wso2.ppaas.test.live.thrift.publisher;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.wso2.ppaas.test.TestUtils;
import org.wso2.ppaas.test.integration.thrift.publisher.MemberLifeCycleEventsTestCase;
import org.wso2.ppaas.test.integration.thrift.publisher.ThriftEventPublisherIntegrationTest;
import org.wso2.ppaas.thrift.publisher.Constants;

import java.io.File;

public class PPaaSPublisherLiveTestCase extends ThriftEventPublisherIntegrationTest {
    private final Logger logger = LoggerFactory.getLogger(MemberLifeCycleEventsTestCase.class);
    private final String TEST_CASE_RESOURCES = "live-test";
    private static String source = System.getProperty("distribution.zip");
    private static String destination = System.getProperty("basedir") +
            File.separator + "target" + File.separator + "PPaaSThriftPublisher";

    @Test(description = "Live test",
            groups = "org.wso2.ppaas.test.live.thrift.publisher",
            timeOut = 60000)
    public void testOnLiveDAS() throws Exception {
        logger.info("Extracting Thrift Event Publisher distribution...");
        TestUtils.unzip(source, destination);

        String testDataFilename = TestUtils.getTestResourcesPath() + TEST_CASE_RESOURCES + File.separator +
                Constants.EVENTS_DATA_TSV;
        String dest = getPPaaSThriftEventPublisherHome() + File.separator + "data";
        logger.info(String.format("Copying [file] %s to [dest] %s", testDataFilename, dest));
        copyFile(testDataFilename, dest);

        startPublisher();

        while (!getInputStreamLogs().contains("Finished executing PPaaSThriftEventPublisher")) {
            Thread.sleep(1000);
        }

        logger.info("================ PPaaSPublisherLiveTestCase completed ==================");
    }
}
