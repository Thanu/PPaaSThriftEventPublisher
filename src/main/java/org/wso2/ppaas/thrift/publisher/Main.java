package org.wso2.ppaas.thrift.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(PPaaSThriftEventPublisher.class);

    public static void main(String[] args) {
        try {
            logger.info("Initializing PPaaSThriftEventPublisher...");
            PPaaSThriftEventPublisher publisher = new PPaaSThriftEventPublisher();
            publisher.start();
        } catch (Exception e) {
            logger.error("Error while initializing PPaaSThriftEventPublisher", e);
        }
    }
}
