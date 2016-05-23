package org.wso2.ppaas.thrift.publisher;

public class TSVRecord {

    private String memberId;
    private long createdTime;
    private long initializedTime;
    private long startedTime;
    private long activatedTime;
    private String clusterId;
    private String clusterAlias;
    private String serviceName;
    private String clusterInstanceId;
    private String appId;
    private String networkPartition;
    private String partitionId;
    private String instanceType;
    private String scalingDecisionId;
    private boolean isMultiTenant;
    private String privateIpAddr;
    private String publicIpAddr;
    private String allocatedIpAddr;
    private String hostname;
    private String hypervisor;
    private String cpu;
    private String ram;
    private String imageId;
    private String loginPort;
    private String osName;
    private String osVersion;
    private String osArchitecture;
    private boolean is64BitOS;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getInitializedTime() {
        return initializedTime;
    }

    public void setInitializedTime(long initializedTime) {
        this.initializedTime = initializedTime;
    }

    public long getStartedTime() {
        return startedTime;
    }

    public void setStartedTime(long startedTime) {
        this.startedTime = startedTime;
    }

    public long getActivatedTime() {
        return activatedTime;
    }

    public void setActivatedTime(long activatedTime) {
        this.activatedTime = activatedTime;
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getClusterInstanceId() {
        return clusterInstanceId;
    }

    public void setClusterInstanceId(String clusterInstanceId) {
        this.clusterInstanceId = clusterInstanceId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNetworkPartition() {
        return networkPartition;
    }

    public void setNetworkPartition(String networkPartition) {
        this.networkPartition = networkPartition;
    }

    public String getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(String partitionId) {
        this.partitionId = partitionId;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public String getScalingDecisionId() {
        return scalingDecisionId;
    }

    public void setScalingDecisionId(String scalingDecisionId) {
        this.scalingDecisionId = scalingDecisionId;
    }

    public boolean getIsMultiTenant() {
        return isMultiTenant;
    }

    public void setIsMultiTenant(boolean isMultiTenant) {
        this.isMultiTenant = isMultiTenant;
    }

    public String getPrivateIpAddr() {
        return privateIpAddr;
    }

    public void setPrivateIpAddr(String privateIpAddr) {
        this.privateIpAddr = privateIpAddr;
    }

    public String getPublicIpAddr() {
        return publicIpAddr;
    }

    public void setPublicIpAddr(String publicIpAddr) {
        this.publicIpAddr = publicIpAddr;
    }

    public String getAllocatedIpAddr() {
        return allocatedIpAddr;
    }

    public void setAllocatedIpAddr(String allocatedIpAddr) {
        this.allocatedIpAddr = allocatedIpAddr;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getHypervisor() {
        return hypervisor;
    }

    public void setHypervisor(String hypervisor) {
        this.hypervisor = hypervisor;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getLoginPort() {
        return loginPort;
    }

    public void setLoginPort(String loginPort) {
        this.loginPort = loginPort;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getOsArchitecture() {
        return osArchitecture;
    }

    public void setOsArchitecture(String osArchitecture) {
        this.osArchitecture = osArchitecture;
    }

    public boolean is64BitOS() {
        return is64BitOS;
    }

    public void setIs64BitOS(boolean is64BitOS) {
        this.is64BitOS = is64BitOS;
    }
}
