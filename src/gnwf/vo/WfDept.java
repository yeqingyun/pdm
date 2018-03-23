package gnwf.vo;

public class WfDept extends BasicWfDept {
	public static final String ALL_FIELDS = "WfDept.FlowId,WfDept.DeptId,WfCfg.FlowName as FlowName";
	public static final String LIST_FIELDS = "WfDept.FlowId,WfDept.DeptId,WfCfg.FlowName as FlowName";


	private String flowName;


	public String getFlowName(){
		return flowName;
	}
	public void setFlowName(String flowName){
		this.flowName = flowName;
	}

}