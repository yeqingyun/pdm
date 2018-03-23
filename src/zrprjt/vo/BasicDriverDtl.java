package zrprjt.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicDriverDtl {
	public static final String SELF_FIELDS = "DriverDtl.DriveId,DriverDtl.FlowId,DriverDtl.StepId";

	private java.lang.Integer driveId;
	private java.lang.Integer flowId;
	private java.lang.Integer stepId;

	public java.lang.Integer getDriveId(){
		return driveId;
	}
	public void setDriveId(java.lang.Integer driveId){
		this.driveId = driveId;
	}
	public java.lang.Integer getFlowId(){
		return flowId;
	}
	public void setFlowId(java.lang.Integer flowId){
		this.flowId = flowId;
	}
	public java.lang.Integer getStepId(){
		return stepId;
	}
	public void setStepId(java.lang.Integer stepId){
		this.stepId = stepId;
	}

}