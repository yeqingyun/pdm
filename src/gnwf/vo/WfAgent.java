package gnwf.vo;

public class WfAgent extends BasicWfAgent {
	public static final String ALL_FIELDS = "WfAgent.UserId,WfAgent.AgentId,WfAgent.FlowId,WfCfg.FlowName as FlowName";
	public static final String LIST_FIELDS = "WfAgent.UserId,WfAgent.AgentId,WfAgent.FlowId,WfCfg.FlowName as FlowName";


	private String flowName;


	public String getFlowName(){
		return flowName;
	}
	public void setFlowName(String flowName){
		this.flowName = flowName;
	}

}