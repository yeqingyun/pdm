package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfStepNext {
	public static final String SELF_FIELDS = "WfStepNext.StepId,WfStepNext.NextId";

	private java.lang.Integer stepId;
	private java.lang.Integer nextId;

	public java.lang.Integer getStepId(){
		return stepId;
	}
	public void setStepId(java.lang.Integer stepId){
		this.stepId = stepId;
	}
	public java.lang.Integer getNextId(){
		return nextId;
	}
	public void setNextId(java.lang.Integer nextId){
		this.nextId = nextId;
	}

}