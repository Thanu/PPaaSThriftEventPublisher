package org.wso2.ppaas.integration.thrift.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class MemberInfoEventsTestCase extends ThriftEventPublisherIntegrationTest {
    private final Logger logger = LoggerFactory.getLogger(MemberInfoEventsTestCase.class);
    private final String TEST_CASE_RESOURCES = "member-info-events";

    @Test(description = "Generate Member-Info events",
            timeOut = 60000)
    public void testMemberInfoEvents() {
        logger.info("Generating Member-Info events...");


    }
}
