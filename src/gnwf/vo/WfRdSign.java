package gnwf.vo;

import gnwf.ww.MSG;

import java.util.Date;


public class WfRdSign extends BasicWfRdSign {
	
	public WfRdSign() {}
	
	public WfRdSign(String text,String wfNo,int taskId,int userId) {
		this.setSignText(text);
		this.setTaskId(taskId);
		this.setWfNoId(wfNo);
		this.setUserId(userId);
		this.setStatus(MSG.CONST_STATUS_1);
		this.setCreateDate(new Date());
	}
	
	public static final String ALL_FIELDS = "WfRdSign.TaskId,WfRdSign.UserId,WfRdSign.Status,WfRdSign.WfNoId,WfRdSign.CreateDate,WfRdSign.SignText,WfRdTask.TaskId as TaskId,WfStep.StepName,Usr.UsrName,Dept.DeptNm";
	public static final String LIST_FIELDS = "WfRdSign.TaskId,WfRdSign.UserId,WfRdSign.Status,WfRdSign.WfNoId,WfRdSign.CreateDate,WfRdSign.SignText,WfRdTask.TaskId as TaskId,WfStep.StepName,Usr.UsrName,Dept.DeptNm";
	public static final String XLS_FIELDS = "WfRdSign.WfNo,WfRdSign.TaskId,WfRdSign.UserId,WfRdSign.SignText,WfRdSign.CreateDate,WfRdSign.Status";


	private Integer taskId;

	private String userName;
	private String stepName;
	private String deptName;

	public Integer getTaskId(){
		return taskId;
	}
	public void setTaskId(Integer taskId){
		this.taskId = taskId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}