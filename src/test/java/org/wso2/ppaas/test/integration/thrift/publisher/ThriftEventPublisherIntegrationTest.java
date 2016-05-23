package org.wso2.ppaas.test.integration.thrift.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.lingala.zip4j.core.ZipFile;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.wso2.carbon.databridge.commons.Event;
import org.wso2.ppaas.test.TestUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ThriftEventPublisherIntegrationTest {
    private static final Logger logger = LoggerFactory.getLogger(ThriftEventPublisherIntegrationTest.class);

    private static String source = System.getProperty("distribution.zip");
    private static String destination = System.getProperty("basedir") +
            File.separator + "target" + File.separator + "PPaaSThriftPublisher";
    protected Properties properties = new Properties();
    protected ObjectMapper objectMapper = new ObjectMapper();
    private Process publisherProcess = null;
    private ServerLogReader inputStreamHandler;
    private ServerLogReader errorStreamHandler;

    public static String getPPaaSThriftEventPublisherHome() {
        return destination;
    }

    public static void copyFile(String source, String dest) throws IOException {
        File sourceFile = new File(source);
        File destDir = new File(dest);
        FileUtils.copyFileToDirectory(sourceFile, destDir);
    }

    protected void startPublisher() throws IOException {
        File publisherHome = new File(destination);
        String cmdArray[] = new String[]{"sh", "bin" + File.separator + "run.sh"};
        publisherProcess = Runtime.getRuntime().exec(cmdArray, (String[]) null, publisherHome);
        errorStreamHandler = new ServerLogReader("errorStream", publisherProcess.getErrorStream());
        inputStreamHandler = new ServerLogReader("inputStream", publisherProcess.getInputStream());
        inputStreamHandler.start();
        errorStreamHandler.start();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    serverShutdown();
                } catch (Exception e) {
                    logger.error("Error while server shutdown ..", e);
                }

            }
        });
    }

    protected void serverShutdown() {
        logger.info("Shutting down PPaaSThriftEventPublisher...");
        if (inputStreamHandler != null) {
            inputStreamHandler.stop();
        }
        if (errorStreamHandler != null) {
            errorStreamHandler.stop();
        }
        if (publisherProcess != null) {
            publisherProcess.destroy();
            publisherProcess = null;
        }
    }

    @BeforeSuite()
    public void setup() throws Exception {
        logger.info("Extracting Thrift Event Publisher distribution...");
        TestUtils.unzip(source, destination);
    }

    @AfterSuite()
    public void destroy() {

    }

    public String getInputStreamLogs() {
        return inputStreamHandler.getOutput();
    }

    public String getErrorStreamLogs() {
        return errorStreamHandler.getOutput();
    }


}
