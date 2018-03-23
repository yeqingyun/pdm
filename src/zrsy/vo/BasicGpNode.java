package zrsy.vo;

public class BasicGpNode {
	public static final String SELF_FIELDS = "GpNode.GpId,GpNode.NodeId";

	private java.lang.Integer gpId;
	private java.lang.Integer nodeId;

	public java.lang.Integer getGpId(){
		return gpId;
	}
	public void setGpId(java.lang.Integer gpId){
		this.gpId = gpId;
	}
	public java.lang.Integer getNodeId(){
		return nodeId;
	}
	public void setNodeId(java.lang.Integer nodeId){
		this.nodeId = nodeId;
	}

}