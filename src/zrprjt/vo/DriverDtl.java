package zrprjt.vo;

public class DriverDtl extends BasicDriverDtl {
	public static final String ALL_FIELDS = "DriverDtl.DriveId,DriverDtl.FlowId,DriverDtl.StepId,WfCfg.FlowName,ws.StepName,ws.StepType";
	public static final String LIST_FIELDS = "DriverDtl.DriveId,DriverDtl.FlowId,DriverDtl.StepId,WfCfg.FlowName,ws.StepName,ws.StepType";

	private String flowName;
	private String stepName;
	private Integer stepType;
	
	public String getFlowName() {
		return flowName;
	}
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public Integer getStepType() {
		return stepType;
	}
	public void setStepType(Integer stepType) {
		this.stepType = stepType;
	}

}