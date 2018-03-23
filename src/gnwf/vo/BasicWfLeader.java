package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfLeader {
	public static final String SELF_FIELDS = "WfLeader.FlowId,WfLeader.UserId";

	private java.lang.Integer flowId;
	private java.lang.Integer userId;

	public java.lang.Integer getFlowId(){
		return flowId;
	}
	public void setFlowId(java.lang.Integer flowId){
		this.flowId = flowId;
	}
	public java.lang.Integer getUserId(){
		return userId;
	}
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}

}