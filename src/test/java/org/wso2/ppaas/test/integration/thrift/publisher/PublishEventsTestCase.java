package org.wso2.ppaas.test.integration.thrift.publisher;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


/**
 * Integration test for PPaaSThriftEventPublisher
 */
public class PublishEventsTestCase extends ThriftEventPublisherIntegrationTest {

    private final Logger logger = LoggerFactory.getLogger(PublishEventsTestCase.class);
    private final String TEST_CASE_RESOURCES = "publish-events";

    public PublishEventsTestCase() throws Exception {
    }

    @Test(description = "Publish User Events",
            timeOut = 60000)
    public void testPublisherClient() throws Exception {


    }
}
