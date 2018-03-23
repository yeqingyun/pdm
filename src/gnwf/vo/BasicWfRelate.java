package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfRelate {
	public static final String SELF_FIELDS = "WfRelate.FlowId,WfRelate.RelateId";

	private java.lang.Integer flowId;
	private java.lang.Integer relateId;

	public java.lang.Integer getFlowId(){
		return flowId;
	}
	public void setFlowId(java.lang.Integer flowId){
		this.flowId = flowId;
	}
	public java.lang.Integer getRelateId(){
		return relateId;
	}
	public void setRelateId(java.lang.Integer relateId){
		this.relateId = relateId;
	}

}