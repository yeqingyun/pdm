package gnwf.vo;

public class WfCfgRelate extends BasicWfCfgRelate {
	public static final String ALL_FIELDS = "WfCfgRelate.RelateId,WfCfgRelate.FlowId,WfCfg.FlowName";
	public static final String LIST_FIELDS = "WfCfgRelate.RelateId,WfCfgRelate.FlowId,WfCfg.FlowName";


	private String flowName;

	public String getFlowName() {
		return flowName;
	}
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

}