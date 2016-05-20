package org.wso2.ppaas.integration.thrift.publisher;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.wso2.ppaas.thrift.publisher.Constants.THRIFT_RECEIVER_URL_KEY;


/**
 * Integration test for PPaaSThriftEventPublisher
 */
public class PublishEventsTestCase extends ThriftEventPublisherIntegrationTest {

    private final Logger logger = LoggerFactory.getLogger(PublishEventsTestCase.class);
    private final String TEST_CASE_RESOURCES = "publish-events";

    @Test(description = "Publish User Events",
            timeOut = 60000)
    public void testPublisherClient() throws Exception {
        String thriftReceiverURL = System.getProperty(THRIFT_RECEIVER_URL_KEY);
        logger.info("Publishing events to Thrift receiver URL: " + thriftReceiverURL);

    }
}
