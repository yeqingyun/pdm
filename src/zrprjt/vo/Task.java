package zrprjt.vo;

public class Task extends BasicTask {
	public static final String ALL_FIELDS = "SchCfg.ManualStart as ManualStart,Task.PredecessorLink,Task.Critical,Task.Summary,Task.Milestone,Task.SchId,Task.Tasker,Task.Sender,Task.SchNo,Task.Leve,Task.Grade,Task.Perce,Task.Status,Task.CreateBy,Task.LastUpd,Task.PlanStaDate,Task.PlanOveDate,Task.StaDate,Task.OveDate,Task.CreateDate,Task.LastDate,Task.TaskNo,Task.PrjtNo,Task.Parent,Task.TaskNm,Task.Remark,Task.PlanDuration";
	public static final String LIST_FIELDS = "SchCfg.ManualStart as ManualStart,Task.PredecessorLink,Task.Critical,Task.Summary,Task.Milestone,Task.SchId,Task.Tasker,Task.Sender,Task.SchNo,Task.Leve,Task.Grade,Task.Perce,Task.Status,Task.CreateBy,Task.LastUpd,Task.PlanStaDate,Task.PlanOveDate,Task.StaDate,Task.OveDate,Task.CreateDate,Task.LastDate,Task.TaskNo,Task.PrjtNo,Task.Parent,Task.TaskNm,Task.Remark,Task.PlanDuration";

	private java.util.List<zrprjt.vo.TaskMsg> taskMsgs;
	private java.util.List<zrprjt.vo.TaskWf> taskWfs;

	private Integer manualStart;

	public java.util.List<zrprjt.vo.TaskMsg> getTaskMsgs() {
		return taskMsgs;
	}
	public void setTaskMsgs(java.util.List<zrprjt.vo.TaskMsg> taskMsgs){
		this.taskMsgs = taskMsgs;
	}
	public void addtoTaskMsgs(zrprjt.vo.TaskMsg taskMsg){
		if(getTaskMsgs() == null) setTaskMsgs(new java.util.ArrayList<zrprjt.vo.TaskMsg>());
			getTaskMsgs().add(taskMsg);
	}
	public java.util.List<zrprjt.vo.TaskWf> getTaskWfs() {
		return taskWfs;
	}
	public void setTaskWfs(java.util.List<zrprjt.vo.TaskWf> taskWfs){
		this.taskWfs = taskWfs;
	}
	public void addtoTaskWfs(zrprjt.vo.TaskWf taskWf){
		if(getTaskWfs() == null) setTaskWfs(new java.util.ArrayList<zrprjt.vo.TaskWf>());
			getTaskWfs().add(taskWf);
	}
	public Integer getManualStart() {
		return manualStart;
	}
	public void setManualStart(Integer manualStart) {
		this.manualStart = manualStart;
	}


}