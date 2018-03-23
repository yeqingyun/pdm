package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfFieldStepRelate {
	public static final String SELF_FIELDS = "WfFieldStepRelate.FieldId,WfFieldStepRelate.StepId";

	private java.lang.Integer fieldId;
	private java.lang.Integer stepId;

	public java.lang.Integer getFieldId(){
		return fieldId;
	}
	public void setFieldId(java.lang.Integer fieldId){
		this.fieldId = fieldId;
	}
	public java.lang.Integer getStepId(){
		return stepId;
	}
	public void setStepId(java.lang.Integer stepId){
		this.stepId = stepId;
	}

}