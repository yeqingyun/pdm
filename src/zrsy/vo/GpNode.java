package zrsy.vo;

public class GpNode extends BasicGpNode {
	public static final String ALL_FIELDS = "GpNode.GpId,GpNode.NodeId,Gp.GpName as GpName,Node.Text as NodeNm";
	public static final String LIST_FIELDS = "GpNode.GpId,GpNode.NodeId,Gp.GpName as GpName,Node.Text as NodeNm";


	private java.lang.String gpName;
	private java.lang.String nodeNm;


	public java.lang.String getGpName(){
		return gpName;
	}
	public void setGpName(java.lang.String gpName){
		this.gpName = gpName;
	}
	public java.lang.String getNodeNm(){
		return nodeNm;
	}
	public void setNodeNm(java.lang.String nodeNm){
		this.nodeNm = nodeNm;
	}

}