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
    private String isMultiTenant;
    private String privateIpAddr;
    private String publicIpAddr;
    private String allocatedIpAddr;
    private String hostname;
    private String hypervisor;
    private String cpu;
    private String ram;
    private String imageId;
    private int loginPort;
    private String osName;
    private String osVersion;
    private String osArchitecture;
    private boolean is64BitOS;
    private int minInstanceCount;
    private int maxInstanceCount;
    private int RIFPredicted;
    private int RIFThreshold;
    private int RIFRequiredInstances;
    private int MCPredicted;
    private int MCThreshold;
    private int MCRequiredInstances;
    private int LAPredicted;
    private int LAThreshold;
    private int LARequiredInstances;
    private int requiredInstanceCount;
    private int activeInstanceCount;
    private int additionalInstanceCount;
    private String scalingReason;

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

    public String getIsMultiTenant() {
        return isMultiTenant;
    }

    public void setIsMultiTenant(String isMultiTenant) {
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

    public int getLoginPort() {
        return loginPort;
    }

    public void setLoginPort(int loginPort) {
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

    public int getMinInstanceCount() {
        return minInstanceCount;
    }

    public void setMinInstanceCount(int minInstanceCount) {
        this.minInstanceCount = minInstanceCount;
    }

    public int getMaxInstanceCount() {
        return maxInstanceCount;
    }

    public void setMaxInstanceCount(int maxInstanceCount) {
        this.maxInstanceCount = maxInstanceCount;
    }

    public int getRIFPredicted() {
        return RIFPredicted;
    }

    public void setRIFPredicted(int RIFPredicted) {
        this.RIFPredicted = RIFPredicted;
    }

    public int getRIFThreshold() {
        return RIFThreshold;
    }

    public void setRIFThreshold(int RIFThreshold) {
        this.RIFThreshold = RIFThreshold;
    }

    public int getRIFRequiredInstances() {
        return RIFRequiredInstances;
    }

    public void setRIFRequiredInstances(int RIFRequiredInstances) {
        this.RIFRequiredInstances = RIFRequiredInstances;
    }

    public int getMCPredicted() {
        return MCPredicted;
    }

    public void setMCPredicted(int MCPredicted) {
        this.MCPredicted = MCPredicted;
    }

    public int getMCThreshold() {
        return MCThreshold;
    }

    public void setMCThreshold(int MCThreshold) {
        this.MCThreshold = MCThreshold;
    }

    public int getMCRequiredInstances() {
        return MCRequiredInstances;
    }

    public void setMCRequiredInstances(int MCRequiredInstances) {
        this.MCRequiredInstances = MCRequiredInstances;
    }

    public int getLAPredicted() {
        return LAPredicted;
    }

    public void setLAPredicted(int LAPredicted) {
        this.LAPredicted = LAPredicted;
    }

    public int getLAThreshold() {
        return LAThreshold;
    }

    public void setLAThreshold(int LAThreshold) {
        this.LAThreshold = LAThreshold;
    }

    public int getLARequiredInstances() {
        return LARequiredInstances;
    }

    public void setLARequiredInstances(int LARequiredInstances) {
        this.LARequiredInstances = LARequiredInstances;
    }

    public int getRequiredInstanceCount() {
        return requiredInstanceCount;
    }

    public void setRequiredInstanceCount(int requiredInstanceCount) {
        this.requiredInstanceCount = requiredInstanceCount;
    }

    public int getActiveInstanceCount() {
        return activeInstanceCount;
    }

    public void setActiveInstanceCount(int activeInstanceCount) {
        this.activeInstanceCount = activeInstanceCount;
    }

    public int getAdditionalInstanceCount() {
        return additionalInstanceCount;
    }

    public void setAdditionalInstanceCount(int additionalInstanceCount) {
        this.additionalInstanceCount = additionalInstanceCount;
    }

    public String getScalingReason() {
        return scalingReason;
    }

    public void setScalingReason(String scalingReason) {
        this.scalingReason = scalingReason;
    }
}
