package zrsy.vo;

public class BasicGpBtn {
	public static final String SELF_FIELDS = "GpBtn.GpId,GpBtn.NodeId,GpBtn.BtnId";

	private java.lang.Integer gpId;
	private java.lang.Integer nodeId;
	private java.lang.Integer btnId;

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
	public java.lang.Integer getBtnId(){
		return btnId;
	}
	public void setBtnId(java.lang.Integer btnId){
		this.btnId = btnId;
	}

}