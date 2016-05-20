package org.wso2.ppaas.integration.thrift.publisher;

import net.lingala.zip4j.core.ZipFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;

public class ThriftEventPublisherIntegrationTest {
    private static final Logger logger = LoggerFactory.getLogger(ThriftEventPublisherIntegrationTest.class);
    private Process publisherProcess = null;
    private String source = System.getProperty("distribution.zip");
    private String destination = System.getProperty("basedir") + File.separator + "target" + File.separator +
            "PPaaSThriftPublisher";
    private ServerLogReader inputStreamHandler;
    private ServerLogReader errorStreamHandler;

    private static void unzip(String source, String destination) throws Exception {
        unzip(source, destination, null);
    }

    private static void unzip(String source, String destination, String password) throws Exception {
        try {
            ZipFile zipFile = new ZipFile(source);
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(password);
            }
            zipFile.extractAll(destination);
        } catch (Exception e) {
            throw new Exception(String.format("Error while extracting the distribution zip [source] %s, [dest] %s, " +
                    "[password] %s", source, destination, password), e);
        }
    }

    protected void startPublisher() throws IOException {
        File publisherHome = new File(destination);
        String cmdArray[] = new String[]{"sh", "bin" + File.separator + "run.sh"};
        publisherProcess = Runtime.getRuntime().exec(cmdArray, (String[]) null, publisherHome);
        this.errorStreamHandler = new ServerLogReader("errorStream", publisherProcess.getErrorStream());
        this.inputStreamHandler = new ServerLogReader("inputStream", publisherProcess.getInputStream());
        this.inputStreamHandler.start();
        this.errorStreamHandler.start();
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
        this.inputStreamHandler.stop();
        this.errorStreamHandler.stop();
        this.publisherProcess.destroy();
        this.publisherProcess = null;
    }

    @BeforeSuite()
    public void setup() throws Exception {
        logger.info("Extracting Thrift Event Publisher distribution...");
        unzip(source, destination);
    }

    public String getInputStreamLogs() {
        return inputStreamHandler.getOutput();
    }

    public String getErrorStreamLogs() {
        return errorStreamHandler.getOutput();
    }
}
