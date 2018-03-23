package zrsy.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicPgBtn {
	public static final String SELF_FIELDS = "PgBtn.NodeId,PgBtn.BtnId,PgBtn.PgTyp";

	private java.lang.Integer nodeId;
	private java.lang.Integer btnId;
	private java.lang.Integer pgTyp;

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
	public java.lang.Integer getPgTyp(){
		return pgTyp;
	}
	public void setPgTyp(java.lang.Integer pgTyp){
		this.pgTyp = pgTyp;
	}

}