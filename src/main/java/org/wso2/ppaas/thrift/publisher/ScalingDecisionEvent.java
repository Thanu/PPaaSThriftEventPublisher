package org.wso2.ppaas.thrift.publisher;

public class ScalingDecisionEvent {
    private long timestamp;
    private String scalingDecisionId;
    private String clusterId;
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getScalingDecisionId() {
        return scalingDecisionId;
    }

    public void setScalingDecisionId(String scalingDecisionId) {
        this.scalingDecisionId = scalingDecisionId;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
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

    public Object[] getEventPayload() {
        return new Object[] { timestamp, scalingDecisionId, clusterId, minInstanceCount, maxInstanceCount, RIFPredicted,
                RIFThreshold, RIFRequiredInstances, MCPredicted, MCThreshold, MCRequiredInstances, LAPredicted,
                LAThreshold, LARequiredInstances, requiredInstanceCount, activeInstanceCount, additionalInstanceCount,
                scalingReason };
    }
}
