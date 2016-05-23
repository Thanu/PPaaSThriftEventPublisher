package org.wso2.ppaas.thrift.publisher;

public class MemberInfoEvent {
    private String memberId;
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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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

    public String isMultiTenant() {
        return isMultiTenant;
    }

    public void setMultiTenant(String multiTenant) {
        isMultiTenant = multiTenant;
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

    public Object[] getEventPayload() {
        return new Object[]{
                memberId,
                instanceType,
                scalingDecisionId,
                isMultiTenant,
                privateIpAddr,
                publicIpAddr,
                allocatedIpAddr,
                hostname,
                hypervisor,
                cpu,
                ram,
                imageId,
                loginPort,
                osName,
                osVersion,
                osArchitecture,
                is64BitOS
        };
    }
}
