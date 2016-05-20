package org.wso2.ppaas.integration.thrift.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class MemberLifeCycleEventsTestCase extends ThriftEventPublisherIntegrationTest {
    private final Logger logger = LoggerFactory.getLogger(MemberLifeCycleEventsTestCase.class);
    private final String TEST_CASE_RESOURCES = "member-life-cycle-events";

    @Test(description = "Generate Member-Life-Cycle events",
            timeOut = 60000)
    public void testMemberLifeCycleEvents() throws Exception {
        logger.info("Generating Member-Life-Cycle events...");

        startPublisher();

        while (!getInputStreamLogs().contains("Finished executing PPaaSThriftEventPublisher")) {
            Thread.sleep(1000);
        }
    }
}
