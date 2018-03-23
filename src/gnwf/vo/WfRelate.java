package gnwf.vo;

public class WfRelate extends BasicWfRelate {
	public static final String ALL_FIELDS = "WfRelate.FlowId,WfRelate.RelateId,WfCfg.FlowName as FlowName";
	public static final String LIST_FIELDS = "WfRelate.FlowId,WfRelate.RelateId,WfCfg.FlowName as FlowName";


	private String flowName;


	public String getFlowName(){
		return flowName;
	}
	public void setFlowName(String flowName){
		this.flowName = flowName;
	}

}