package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfCfgRelate {
	public static final String SELF_FIELDS = "WfCfgRelate.RelateId,WfCfgRelate.FlowId";

	private java.lang.Integer relateId;
	private java.lang.Integer flowId;

	public java.lang.Integer getRelateId(){
		return relateId;
	}
	public void setRelateId(java.lang.Integer relateId){
		this.relateId = relateId;
	}
	public java.lang.Integer getFlowId(){
		return flowId;
	}
	public void setFlowId(java.lang.Integer flowId){
		this.flowId = flowId;
	}

}