package zrprjt.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicTask {
	public static final String SELF_FIELDS = "Task.PredecessorLink,Task.Critical,Task.Summary,Task.Milestone,Task.SchId,Task.Tasker,Task.Sender,Task.SchNo,Task.Leve,Task.Grade,Task.Perce,Task.Status,Task.CreateBy,Task.LastUpd,Task.PlanStaDate,Task.PlanOveDate,Task.StaDate,Task.OveDate,Task.CreateDate,Task.LastDate,Task.TaskNo,Task.PrjtNo,Task.Parent,Task.TaskNm,Task.Remark,Task.PlanDuration";

	private java.lang.Integer schId;
	private java.lang.Integer tasker;
	private java.lang.Integer sender;
	private java.lang.Integer schNo;
	private java.lang.Integer leve;
	private java.lang.Integer grade;
	private java.lang.Integer perce;
	private java.lang.Integer status;
	private java.lang.Integer createBy;
	private java.lang.Integer lastUpd;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date planStaDate;
	private java.util.Date startPlanStaDate;
	private java.util.Date endPlanStaDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date planOveDate;
	private java.util.Date startPlanOveDate;
	private java.util.Date endPlanOveDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date staDate;
	private java.util.Date startStaDate;
	private java.util.Date endStaDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date oveDate;
	private java.util.Date startOveDate;
	private java.util.Date endOveDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastDate;
	private java.util.Date startLastDate;
	private java.util.Date endLastDate;
	private java.lang.Integer taskNo;
	private java.lang.String prjtNo;
	private java.lang.Integer parent;
	private java.lang.String taskNm;
	private java.lang.String remark;
	
	private java.lang.Integer planDuration;
	
	private java.lang.String predecessorLink;
	private java.lang.Integer milestone;
	private java.lang.Integer summary;
	private java.lang.Integer critical;

	public java.lang.Integer getSchId(){
		return schId;
	}
	public void setSchId(java.lang.Integer schId){
		this.schId = schId;
	}
	public java.lang.Integer getTasker(){
		return tasker;
	}
	public void setTasker(java.lang.Integer tasker){
		this.tasker = tasker;
	}
	public java.lang.Integer getSender(){
		return sender;
	}
	public void setSender(java.lang.Integer sender){
		this.sender = sender;
	}
	public java.lang.Integer getSchNo(){
		return schNo;
	}
	public void setSchNo(java.lang.Integer schNo){
		this.schNo = schNo;
	}
	public java.lang.Integer getLeve(){
		return leve;
	}
	public void setLeve(java.lang.Integer leve){
		this.leve = leve;
	}
	public java.lang.Integer getGrade(){
		return grade;
	}
	public void setGrade(java.lang.Integer grade){
		this.grade = grade;
	}
	public java.lang.Integer getPerce(){
		return perce;
	}
	public void setPerce(java.lang.Integer perce){
		this.perce = perce;
	}
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
	public java.lang.Integer getCreateBy(){
		return createBy;
	}
	public void setCreateBy(java.lang.Integer createBy){
		this.createBy = createBy;
	}
	public java.lang.Integer getLastUpd(){
		return lastUpd;
	}
	public void setLastUpd(java.lang.Integer lastUpd){
		this.lastUpd = lastUpd;
	}
	public java.util.Date getStartPlanStaDate(){
		return startPlanStaDate;
	}
	public void setStartPlanStaDate(java.util.Date startPlanStaDate){
		this.startPlanStaDate = startPlanStaDate;
	}
	public java.util.Date getEndPlanStaDate(){
		return endPlanStaDate;
	}
	public void setEndPlanStaDate(java.util.Date endPlanStaDate){
		this.endPlanStaDate = endPlanStaDate;
	}
	public java.util.Date getPlanStaDate(){
		return planStaDate;
	}
	public void setPlanStaDate(java.util.Date planStaDate){
		this.planStaDate = planStaDate;
	}
	public java.util.Date getStartPlanOveDate(){
		return startPlanOveDate;
	}
	public void setStartPlanOveDate(java.util.Date startPlanOveDate){
		this.startPlanOveDate = startPlanOveDate;
	}
	public java.util.Date getEndPlanOveDate(){
		return endPlanOveDate;
	}
	public void setEndPlanOveDate(java.util.Date endPlanOveDate){
		this.endPlanOveDate = endPlanOveDate;
	}
	public java.util.Date getPlanOveDate(){
		return planOveDate;
	}
	public void setPlanOveDate(java.util.Date planOveDate){
		this.planOveDate = planOveDate;
	}
	public java.util.Date getStartStaDate(){
		return startStaDate;
	}
	public void setStartStaDate(java.util.Date startStaDate){
		this.startStaDate = startStaDate;
	}
	public java.util.Date getEndStaDate(){
		return endStaDate;
	}
	public void setEndStaDate(java.util.Date endStaDate){
		this.endStaDate = endStaDate;
	}
	public java.util.Date getStaDate(){
		return staDate;
	}
	public void setStaDate(java.util.Date staDate){
		this.staDate = staDate;
	}
	public java.util.Date getStartOveDate(){
		return startOveDate;
	}
	public void setStartOveDate(java.util.Date startOveDate){
		this.startOveDate = startOveDate;
	}
	public java.util.Date getEndOveDate(){
		return endOveDate;
	}
	public void setEndOveDate(java.util.Date endOveDate){
		this.endOveDate = endOveDate;
	}
	public java.util.Date getOveDate(){
		return oveDate;
	}
	public void setOveDate(java.util.Date oveDate){
		this.oveDate = oveDate;
	}
	public java.util.Date getStartCreateDate(){
		return startCreateDate;
	}
	public void setStartCreateDate(java.util.Date startCreateDate){
		this.startCreateDate = startCreateDate;
	}
	public java.util.Date getEndCreateDate(){
		return endCreateDate;
	}
	public void setEndCreateDate(java.util.Date endCreateDate){
		this.endCreateDate = endCreateDate;
	}
	public java.util.Date getCreateDate(){
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	public java.util.Date getStartLastDate(){
		return startLastDate;
	}
	public void setStartLastDate(java.util.Date startLastDate){
		this.startLastDate = startLastDate;
	}
	public java.util.Date getEndLastDate(){
		return endLastDate;
	}
	public void setEndLastDate(java.util.Date endLastDate){
		this.endLastDate = endLastDate;
	}
	public java.util.Date getLastDate(){
		return lastDate;
	}
	public void setLastDate(java.util.Date lastDate){
		this.lastDate = lastDate;
	}
	public java.lang.String getPrjtNo(){
		return prjtNo;
	}
	public void setPrjtNo(java.lang.String prjtNo){
		this.prjtNo = prjtNo;
	}
	public java.lang.String getTaskNm(){
		return taskNm;
	}
	public void setTaskNm(java.lang.String taskNm){
		this.taskNm = taskNm;
	}
	public java.lang.String getRemark(){
		return remark;
	}
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	public java.lang.Integer getPlanDuration() {
		return planDuration;
	}
	public void setPlanDuration(java.lang.Integer planDuration) {
		this.planDuration = planDuration;
	}
	public java.lang.String getPredecessorLink() {
		return predecessorLink;
	}
	public void setPredecessorLink(java.lang.String predecessorLink) {
		this.predecessorLink = predecessorLink;
	}
	public java.lang.Integer getMilestone() {
		return milestone;
	}
	public void setMilestone(java.lang.Integer milestone) {
		this.milestone = milestone;
	}
	public java.lang.Integer getSummary() {
		return summary;
	}
	public void setSummary(java.lang.Integer summary) {
		this.summary = summary;
	}
	public java.lang.Integer getCritical() {
		return critical;
	}
	public void setCritical(java.lang.Integer critical) {
		this.critical = critical;
	}
	public java.lang.Integer getParent() {
		return parent;
	}
	public void setParent(java.lang.Integer parent) {
		this.parent = parent;
	}
	public java.lang.Integer getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(java.lang.Integer taskNo) {
		this.taskNo = taskNo;
	}

}