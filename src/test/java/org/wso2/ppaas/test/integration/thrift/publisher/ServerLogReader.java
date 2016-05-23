package org.wso2.ppaas.test.integration.thrift.publisher;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ServerLogReader implements Runnable {
    private static final String STREAM_TYPE_IN = "inputStream";
    private static final String STREAM_TYPE_ERROR = "errorStream";
    private static final Logger log = LoggerFactory.getLogger(ServerLogReader.class);
    private final Object lock = new Object();
    private String streamType;
    private InputStream inputStream;
    private StringBuilder stringBuilder;
    private Thread thread;
    private volatile boolean running = true;

    public ServerLogReader(String name, InputStream is) {
        this.streamType = name;
        this.inputStream = is;
        this.stringBuilder = new StringBuilder();
    }

    public void start() {
        this.thread = new Thread(this);
        this.thread.start();
    }

    public void stop() {
        this.running = false;
    }

    public void run() {
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        try {
            inputStreamReader = new InputStreamReader(this.inputStream, Charset.defaultCharset());
            bufferedReader = new BufferedReader(inputStreamReader);

            while (this.running) {
                if (bufferedReader.ready()) {
                    String e = bufferedReader.readLine();
                    this.stringBuilder.setLength(0);
                    if (e == null) {
                        break;
                    }

                    if ("inputStream".equals(this.streamType)) {
                        this.stringBuilder.append(e).append("\n");
                        log.info(e);
                    } else if ("errorStream".equals(this.streamType)) {
                        this.stringBuilder.append(e).append("\n");
                        log.error(e);
                    }
                }
            }
        } catch (Exception var16) {
            log.error("Problem reading the [" + this.streamType + "] due to: " + var16.getMessage(), var16);
        } finally {
            if (inputStreamReader != null) {
                try {
                    this.inputStream.close();
                    inputStreamReader.close();
                } catch (IOException var15) {
                    log.error("Error occurred while closing the server log stream: " + var15.getMessage(), var15);
                }
            }

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException var14) {
                    log.error("Error occurred while closing the server log stream: " + var14.getMessage(), var14);
                }
            }

        }

    }

    public String getOutput() {
        Object var1 = this.lock;
        synchronized (this.lock) {
            return this.stringBuilder.toString();
        }
    }
}