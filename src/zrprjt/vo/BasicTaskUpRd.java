package zrprjt.vo;

import com.alibaba.fastjson.annotation.JSONField;

public class BasicTaskUpRd {

	public static final String SELF_FIELDS = "TaskUpRd.Version,TaskUpRd.TaskId,TaskUpRd.PlanDuration,TaskUpRd.PlanStaDate,TaskUpRd.PlanOveDate,TaskUpRd.CreateBy,TaskUpRd.createDate";

	private java.lang.Float version;
	private java.lang.Integer taskId;
	private java.lang.Integer planDuration;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private java.util.Date planStaDate;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private java.util.Date planOveDate;
	private java.lang.Integer createBy;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createDate;
	public java.lang.Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(java.lang.Integer taskId) {
		this.taskId = taskId;
	}
	public java.lang.Integer getPlanDuration() {
		return planDuration;
	}
	public void setPlanDuration(java.lang.Integer planDuration) {
		this.planDuration = planDuration;
	}
	public java.util.Date getPlanStaDate() {
		return planStaDate;
	}
	public void setPlanStaDate(java.util.Date planStaDate) {
		this.planStaDate = planStaDate;
	}
	public java.util.Date getPlanOveDate() {
		return planOveDate;
	}
	public void setPlanOveDate(java.util.Date planOveDate) {
		this.planOveDate = planOveDate;
	}
	public java.lang.Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(java.lang.Integer createBy) {
		this.createBy = createBy;
	}
	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	public java.lang.Float getVersion() {
		return version;
	}
	public void setVersion(java.lang.Float version) {
		this.version = version;
	}
}
