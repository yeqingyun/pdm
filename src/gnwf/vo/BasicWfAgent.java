package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfAgent {
	public static final String SELF_FIELDS = "WfAgent.UserId,WfAgent.AgentId,WfAgent.FlowId";

	private java.lang.Integer userId;
	private java.lang.Integer agentId;
	private java.lang.Integer flowId;

	public java.lang.Integer getUserId(){
		return userId;
	}
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}
	public java.lang.Integer getAgentId(){
		return agentId;
	}
	public void setAgentId(java.lang.Integer agentId){
		this.agentId = agentId;
	}
	public java.lang.Integer getFlowId(){
		return flowId;
	}
	public void setFlowId(java.lang.Integer flowId){
		this.flowId = flowId;
	}

}