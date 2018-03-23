package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfStepUserHis {
	public static final String SELF_FIELDS = "WfStepUserHis.StepID,WfStepUserHis.Owner,WfStepUserHis.TaskType,WfStepUserHis.UserId";

	private java.lang.Integer stepID;
	private java.lang.Integer owner;
	private java.lang.Integer taskType;
	private java.lang.Integer userId;

	public java.lang.Integer getStepID(){
		return stepID;
	}
	public void setStepID(java.lang.Integer stepID){
		this.stepID = stepID;
	}
	public java.lang.Integer getOwner(){
		return owner;
	}
	public void setOwner(java.lang.Integer owner){
		this.owner = owner;
	}
	public java.lang.Integer getTaskType(){
		return taskType;
	}
	public void setTaskType(java.lang.Integer taskType){
		this.taskType = taskType;
	}
	public java.lang.Integer getUserId(){
		return userId;
	}
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}

}