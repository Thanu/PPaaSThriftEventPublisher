package org.wso2.ppaas.thrift.publisher;

import java.io.File;

public class Constants {
    public static final String PPAAS_PUBLISHER_HOME_KEY = "PPAAS_PUBLISHER_HOME";
    public static final String PPAAS_PUBLISHER_HOME_DIR = System.getProperty(PPAAS_PUBLISHER_HOME_KEY);
    public static final String THRIFT_RECEIVER_URL_KEY = "thrift.receiver.url";
    public static final String THRIFT_RECEIVER_AUTH_URL_KEY = "thrift.receiver.authURL";
    public static final String PROPERTIES_FILENAME_KEY = "thrift.event.publisher.properties";
    public static final String THRIFT_RECEIVER_USERNAME_KEY = "thrift.receiver.username";
    public static final String THRIFT_RECEIVER_PASSWORD_KEY = "thrift.receiver.password";
    public static final String THRIFT_PUBLISHER_PROPERTIES_FILENAME = "PPaaSThriftEventPublisher.properties";
    public static final String THRIFT_EVENT_TYPE = "Thrift";
    public static final String EVENT_TYPE_KEY = "type";

    public static final String MEMBER_LIFE_CYCLE_STREAM_VERSION = "1.0.0";
    public static final String MEMBER_LIFE_CYCLE_STREAM_NAME = "member_lifecycle";
    public static final String MEMBER_LIFE_CYCLE_JOURNAL_FILENAME = MEMBER_LIFE_CYCLE_STREAM_NAME + ".log";

    public static final String MEMBER_INFO_STREAM_VERSION = "1.0.0";
    public static final String MEMBER_INFO_STREAM_NAME = "member_info";
    public static final String MEMBER_INFO_JOURNAL_FILENAME = MEMBER_INFO_STREAM_NAME + ".log";

    public static final String SCALING_DECISION_STREAM_VERSION = "1.0.0";
    public static final String SCALING_DECISION_STREAM_NAME = "scaling_decision";
    public static final String SCALING_DECISION_JOURNAL_FILENAME = SCALING_DECISION_STREAM_NAME + ".log";

    public static final String EVENTS_DATA_TSV = "EventsData.tsv";
    public static final String TSV_DELIMITER = "\t";
    public static final String TSV_FILE_PATH = PPAAS_PUBLISHER_HOME_DIR + File.separator + "data" + File.separator +
            Constants.EVENTS_DATA_TSV;
    public static final int TSV_COLUMN_COUNT = 43;
    public static final int MEMBER_STATUS_COUNT = 4;
    public static final String MEMBER_STATUS_CREATED = "Created";
    public static final String MEMBER_STATUS_INITIALIZED = "Initialized";
    public static final String MEMBER_STATUS_STARTING = "Starting";
    public static final String MEMBER_STATUS_ACTIVATED = "Active";
}
