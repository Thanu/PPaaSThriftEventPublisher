package org.wso2.ppaas.test;

import net.lingala.zip4j.core.ZipFile;
import org.wso2.carbon.databridge.commons.Event;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class TestUtils {
    private static String currentDir = new File(".").getAbsolutePath();

    public static void unzip(String source, String destination) throws Exception {
        unzip(source, destination, null);
    }

    public static void unzip(String source, String destination, String password) throws Exception {
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

    public static String getTestResourcesPath() {
        return currentDir + File.separator + ".." + File.separator +
                "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    }

    public static boolean hasGeneratedEventReceived(List<Event> receivedEventList, Event generatedEvent) throws Exception {
        if (generatedEvent == null || receivedEventList == null || receivedEventList.size() == 0) {
            throw new Exception("Invalid arguments passed");
        }
        boolean eventReceived;
        for (Event event : receivedEventList) {
            eventReceived = Arrays.equals(event.getPayloadData(), generatedEvent.getPayloadData());
            if (eventReceived) {
                return true;
            }
        }
        return false;
    }
}
