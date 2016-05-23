package org.wso2.ppaas.thrift.publisher;

public class MemberLifeCycleEvent {
    private long timestamp;
    private String appId;
    private String clusterId;
    private String clusterAlias;
    private String clusterInstanceId;
    private String serviceName;
    private String networkPartitionId;
    private String partitionId;
    private String memberId;
    private String memberStatus;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getClusterAlias() {
        return clusterAlias;
    }

    public void setClusterAlias(String clusterAlias) {
        this.clusterAlias = clusterAlias;
    }

    public String getClusterInstanceId() {
        return clusterInstanceId;
    }

    public void setClusterInstanceId(String clusterInstanceId) {
        this.clusterInstanceId = clusterInstanceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getNetworkPartitionId() {
        return networkPartitionId;
    }

    public void setNetworkPartitionId(String networkPartitionId) {
        this.networkPartitionId = networkPartitionId;
    }

    public String getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(String partitionId) {
        this.partitionId = partitionId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    public Object[] getEventPayload() {
        return new Object[]{
                timestamp,
                appId,
                clusterId,
                clusterAlias,
                clusterInstanceId,
                serviceName,
                networkPartitionId,
                partitionId,
                memberId,
                memberStatus
        };
    }
}
