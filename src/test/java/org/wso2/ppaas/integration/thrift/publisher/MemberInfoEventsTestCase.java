package org.wso2.ppaas.integration.thrift.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.wso2.ppaas.thrift.publisher.Constants.THRIFT_PUBLISHER_PROPERTIES_FILENAME;

public class MemberInfoEventsTestCase extends ThriftEventPublisherIntegrationTest {
    private final Logger logger = LoggerFactory.getLogger(MemberInfoEventsTestCase.class);
    private final String TEST_CASE_RESOURCES = "member-info-events";

    public MemberInfoEventsTestCase() throws Exception {
        File propertiesFile = new File(getTestResourcesPath() + TEST_CASE_RESOURCES +
                File.separator + THRIFT_PUBLISHER_PROPERTIES_FILENAME);
        InputStream inputStream = new FileInputStream(propertiesFile);
        properties.load(inputStream);
    }

    @Test(description = "Generate Member-Info events",
            timeOut = 60000)
    public void testMemberInfoEvents() {
        logger.info("Generating Member-Info events...");


    }
}
