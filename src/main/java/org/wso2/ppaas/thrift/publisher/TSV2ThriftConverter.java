package org.wso2.ppaas.thrift.publisher;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.databridge.commons.Event;
import org.wso2.carbon.databridge.commons.utils.DataBridgeCommonsUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.wso2.ppaas.thrift.publisher.Constants.*;

public class TSV2ThriftConverter {
    private static Logger logger = LoggerFactory.getLogger(TSV2ThriftConverter.class);
    private List<TSVRecord> tsvRecordList = new ArrayList<>();
    private Map<String, TSVRecord> memberRecords = new HashMap<>();

    public void readData() throws IOException {
        logger.info(String.format("Reading user data file [source] %s", TSV_FILE_PATH));
        BufferedReader TSVFile = new BufferedReader(new FileReader(TSV_FILE_PATH));
        String dataRow = TSVFile.readLine();
        while (dataRow != null) {
            String[] dataArray = dataRow.split(TSV_DELIMITER);
            if (dataArray.length != TSV_COLUMN_COUNT) {
                throw new RuntimeException("TSV file format is invalid");
            }
            TSVRecord tsvRecord = new TSVRecord();
            tsvRecord.setMemberId(dataArray[0]);
            tsvRecord.setCreatedTime(getTimeStamp(dataArray[1]));
            tsvRecord.setInitializedTime(getTimeStamp(dataArray[2]));
            tsvRecord.setStartedTime(getTimeStamp(dataArray[3]));
            tsvRecord.setActivatedTime(getTimeStamp(dataArray[4]));
            tsvRecord.setClusterId(dataArray[5]);
            tsvRecord.setClusterAlias(dataArray[6]);
            tsvRecord.setServiceName(dataArray[7]);
            tsvRecord.setClusterInstanceId(dataArray[8]);
            tsvRecord.setAppId(dataArray[9]);
            tsvRecord.setNetworkPartition(dataArray[10]);
            tsvRecord.setPartitionId(dataArray[11]);
            tsvRecord.setInstanceType(dataArray[12]);
            tsvRecord.setScalingDecisionId(dataArray[13] + "-" + UUID.randomUUID().toString());
            tsvRecord.setIsMultiTenant(dataArray[14]);
            tsvRecord.setPrivateIpAddr(dataArray[15]);
            tsvRecord.setPublicIpAddr(dataArray[16]);
            tsvRecord.setAllocatedIpAddr(dataArray[17]);
            tsvRecord.setHostname(dataArray[18]);
            tsvRecord.setHypervisor(dataArray[19]);
            tsvRecord.setCpu(dataArray[20]);
            tsvRecord.setRam(dataArray[21]);
            tsvRecord.setImageId(dataArray[22]);
            tsvRecord.setLoginPort(Integer.parseInt(dataArray[23]));
            tsvRecord.setOsName(dataArray[24]);
            tsvRecord.setOsVersion(dataArray[25]);
            tsvRecord.setOsArchitecture(dataArray[26]);
            tsvRecord.setIs64BitOS(Boolean.parseBoolean(dataArray[27]));
            tsvRecord.setMinInstanceCount(Integer.parseInt(dataArray[28]));
            tsvRecord.setMaxInstanceCount(Integer.parseInt(dataArray[29]));
            tsvRecord.setRIFPredicted(Integer.parseInt(dataArray[30]));
            tsvRecord.setRIFThreshold(Integer.parseInt(dataArray[31]));
            tsvRecord.setRIFRequiredInstances(Integer.parseInt(dataArray[32]));
            tsvRecord.setMCPredicted(Integer.parseInt(dataArray[33]));
            tsvRecord.setMCThreshold(Integer.parseInt(dataArray[34]));
            tsvRecord.setMCRequiredInstances(Integer.parseInt(dataArray[35]));
            tsvRecord.setLAPredicted(Integer.parseInt(dataArray[36]));
            tsvRecord.setLAThreshold(Integer.parseInt(dataArray[37]));
            tsvRecord.setLARequiredInstances(Integer.parseInt(dataArray[38]));
            tsvRecord.setRequiredInstanceCount(Integer.parseInt(dataArray[39]));
            tsvRecord.setActiveInstanceCount(Integer.parseInt(dataArray[40]));
            tsvRecord.setAdditionalInstanceCount(Integer.parseInt(dataArray[41]));
            tsvRecord.setScalingReason(dataArray[42]);
            tsvRecordList.add(tsvRecord);
            memberRecords.put(tsvRecord.getMemberId(), tsvRecord);
            dataRow = TSVFile.readLine();
        }
        TSVFile.close();
    }

    public List<Event> generateMemberLifeCycleEvents() {
        if (tsvRecordList == null) {
            throw new RuntimeException("TSV record list is null");
        }

        String streamId = DataBridgeCommonsUtils.generateStreamId(Constants.MEMBER_LIFE_CYCLE_STREAM_NAME, Constants
                .MEMBER_LIFE_CYCLE_STREAM_VERSION);
        List<Event> eventList = new ArrayList<>();
        for (TSVRecord tsvRecord : tsvRecordList) {
            for (int i = 0; i < Constants.MEMBER_STATUS_COUNT; i++) {
                MemberLifeCycleEvent lifeCycleEvent = new MemberLifeCycleEvent();

                if (i == 0) {
                    lifeCycleEvent.setTimestamp(tsvRecord.getCreatedTime());
                    lifeCycleEvent.setMemberStatus(Constants.MEMBER_STATUS_CREATED);
                } else if (i == 1) {
                    lifeCycleEvent.setTimestamp(tsvRecord.getInitializedTime());
                    lifeCycleEvent.setMemberStatus(Constants.MEMBER_STATUS_INITIALIZED);
                } else if (i == 2) {
                    lifeCycleEvent.setTimestamp(tsvRecord.getStartedTime());
                    lifeCycleEvent.setMemberStatus(Constants.MEMBER_STATUS_STARTING);
                } else if (i == 3) {
                    lifeCycleEvent.setTimestamp(tsvRecord.getActivatedTime());
                    lifeCycleEvent.setMemberStatus(Constants.MEMBER_STATUS_ACTIVATED);
                }

                lifeCycleEvent.setAppId(tsvRecord.getAppId());
                lifeCycleEvent.setClusterAlias(tsvRecord.getClusterAlias());
                lifeCycleEvent.setClusterId(tsvRecord.getClusterId());
                lifeCycleEvent.setClusterInstanceId(tsvRecord.getClusterInstanceId());
                lifeCycleEvent.setMemberId(tsvRecord.getMemberId());
                lifeCycleEvent.setNetworkPartitionId(tsvRecord.getNetworkPartition());
                lifeCycleEvent.setPartitionId(tsvRecord.getPartitionId());
                lifeCycleEvent.setServiceName(tsvRecord.getServiceName());

                Event event = new Event(streamId, lifeCycleEvent.getTimestamp(), null, null, lifeCycleEvent
                        .getEventPayload());
                eventList.add(event);
            }
        }
        return eventList;
    }

    public List<Event> generateMemberInfoEvents() {
        if (tsvRecordList == null) {
            throw new RuntimeException("TSV record list is null");
        }

        String streamId = DataBridgeCommonsUtils.generateStreamId(Constants.MEMBER_INFO_STREAM_NAME, Constants
                .MEMBER_INFO_STREAM_VERSION);
        List<Event> eventList = new ArrayList<>();
        for (TSVRecord tsvRecord : tsvRecordList) {
            MemberInfoEvent memberInfoEvent = new MemberInfoEvent();
            memberInfoEvent.setMemberId(tsvRecord.getMemberId());
            memberInfoEvent.setInstanceType(tsvRecord.getInstanceType());
            memberInfoEvent.setScalingDecisionId(tsvRecord.getScalingDecisionId());
            memberInfoEvent.setMultiTenant(tsvRecord.getIsMultiTenant());
            memberInfoEvent.setPrivateIpAddr(tsvRecord.getPrivateIpAddr());
            memberInfoEvent.setPublicIpAddr(tsvRecord.getPublicIpAddr());
            memberInfoEvent.setAllocatedIpAddr(tsvRecord.getAllocatedIpAddr());
            memberInfoEvent.setHostname(tsvRecord.getHostname());
            memberInfoEvent.setHypervisor(tsvRecord.getHypervisor());
            memberInfoEvent.setCpu(tsvRecord.getCpu());
            memberInfoEvent.setRam(tsvRecord.getRam());
            memberInfoEvent.setImageId(tsvRecord.getImageId());
            memberInfoEvent.setLoginPort(tsvRecord.getLoginPort());
            memberInfoEvent.setOsName(tsvRecord.getOsName());
            memberInfoEvent.setOsVersion(tsvRecord.getOsVersion());
            memberInfoEvent.setOsArchitecture(tsvRecord.getOsArchitecture());
            memberInfoEvent.setIs64BitOS(tsvRecord.is64BitOS());

            Event event = new Event(streamId, memberRecords.get(memberInfoEvent.getMemberId()).getInitializedTime(),
                    null, null, memberInfoEvent.getEventPayload());
            eventList.add(event);
        }
        return eventList;
    }

    public List<Event> generateScalingDecisionEvents() {
        if (tsvRecordList == null) {
            throw new RuntimeException("TSV record list is null");
        }

        String streamId = DataBridgeCommonsUtils.generateStreamId(Constants.SCALING_DECISION_STREAM_NAME, Constants
                .SCALING_DECISION_STREAM_VERSION);
        List<Event> eventList = new ArrayList<>();
        for (TSVRecord tsvRecord : tsvRecordList) {
            ScalingDecisionEvent scalingDecisionEvent = new ScalingDecisionEvent();
            scalingDecisionEvent.setTimestamp(tsvRecord.getCreatedTime());
            scalingDecisionEvent.setScalingDecisionId(tsvRecord.getScalingDecisionId());
            scalingDecisionEvent.setClusterId(tsvRecord.getClusterId());
            scalingDecisionEvent.setMinInstanceCount(tsvRecord.getMinInstanceCount());
            scalingDecisionEvent.setMaxInstanceCount(tsvRecord.getMaxInstanceCount());
            scalingDecisionEvent.setRIFPredicted(tsvRecord.getRIFPredicted());
            scalingDecisionEvent.setRIFThreshold(tsvRecord.getRIFThreshold());
            scalingDecisionEvent.setRIFRequiredInstances(tsvRecord.getRIFRequiredInstances());
            scalingDecisionEvent.setMCPredicted(tsvRecord.getMCPredicted());
            scalingDecisionEvent.setMCThreshold(tsvRecord.getMCThreshold());
            scalingDecisionEvent.setMCRequiredInstances(tsvRecord.getMCRequiredInstances());
            scalingDecisionEvent.setLAPredicted(tsvRecord.getLAPredicted());
            scalingDecisionEvent.setLAThreshold(tsvRecord.getLAThreshold());
            scalingDecisionEvent.setLARequiredInstances(tsvRecord.getLARequiredInstances());
            scalingDecisionEvent.setRequiredInstanceCount(tsvRecord.getRequiredInstanceCount());
            scalingDecisionEvent.setActiveInstanceCount(tsvRecord.getActiveInstanceCount());
            scalingDecisionEvent.setAdditionalInstanceCount(tsvRecord.getAdditionalInstanceCount());
            scalingDecisionEvent.setScalingReason(tsvRecord.getScalingReason());

            Event event = new Event(streamId, scalingDecisionEvent.getTimestamp(),
                    null, null, scalingDecisionEvent.getEventPayload());
            eventList.add(event);
        }
        return eventList;
    }

    private long getTimeStamp(String dateUTC) {
        DateTime date;
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss,SSS").withZoneUTC();
        date = dateTimeFormatter.parseDateTime(dateUTC);
        return date.getMillis();
    }
}
