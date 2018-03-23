package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfRdTask {
	public static final String SELF_FIELDS = " WfRdTask.DocCateId,WfRdTask.TaskId,WfRdTask.PreTaskId,WfRdTask.StepId,WfRdTask.CreateBy,WfRdTask.AcceptBy,WfRdTask.AgentBy,WfRdTask.TaskType,WfRdTask.Status,WfRdTask.IsSystemFinsh,WfRdTask.WfNo,WfRdTask.ReqDate,WfRdTask.CreateDate,WfRdTask.AcceptDate,WfRdTask.AgentDate,WfRdTask.EndDate,WfRdTask.Remark ";

	private java.lang.Integer taskId;
	private java.lang.Integer preTaskId;
	private java.lang.Integer stepId;
	private java.lang.Integer createBy;
	private java.lang.Integer acceptBy;
	private java.lang.Integer agentBy;
	private java.lang.Integer taskType;
	private java.lang.Integer status;
	private java.lang.Integer isSystemFinsh;
	private java.lang.String wfNo;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date reqDate;
	private java.util.Date startReqDate;
	private java.util.Date endReqDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date acceptDate;
	private java.util.Date startAcceptDate;
	private java.util.Date endAcceptDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date agentDate;
	private java.util.Date startAgentDate;
	private java.util.Date endAgentDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date endDate;
	private java.util.Date startEndDate;
	private java.util.Date endEndDate;
	private java.lang.String remark;
	
	private java.lang.String docCateId;

	public java.lang.Integer getTaskId(){
		return taskId;
	}
	public void setTaskId(java.lang.Integer taskId){
		this.taskId = taskId;
	}
	public java.lang.Integer getPreTaskId(){
		return preTaskId;
	}
	public void setPreTaskId(java.lang.Integer preTaskId){
		this.preTaskId = preTaskId;
	}
	public java.lang.Integer getStepId(){
		return stepId;
	}
	public void setStepId(java.lang.Integer stepId){
		this.stepId = stepId;
	}
	public java.lang.Integer getCreateBy(){
		return createBy;
	}
	public void setCreateBy(java.lang.Integer createBy){
		this.createBy = createBy;
	}
	public java.lang.Integer getAcceptBy(){
		return acceptBy;
	}
	public void setAcceptBy(java.lang.Integer acceptBy){
		this.acceptBy = acceptBy;
	}
	public java.lang.Integer getAgentBy(){
		return agentBy;
	}
	public void setAgentBy(java.lang.Integer agentBy){
		this.agentBy = agentBy;
	}
	public java.lang.Integer getTaskType(){
		return taskType;
	}
	public void setTaskType(java.lang.Integer taskType){
		this.taskType = taskType;
	}
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
	public java.lang.Integer getIsSystemFinsh(){
		return isSystemFinsh;
	}
	public void setIsSystemFinsh(java.lang.Integer isSystemFinsh){
		this.isSystemFinsh = isSystemFinsh;
	}
	public java.lang.String getWfNo(){
		return wfNo;
	}
	public void setWfNo(java.lang.String wfNo){
		this.wfNo = wfNo;
	}
	public java.util.Date getStartReqDate(){
		return startReqDate;
	}
	public void setStartReqDate(java.util.Date startReqDate){
		this.startReqDate = startReqDate;
	}
	public java.util.Date getEndReqDate(){
		return endReqDate;
	}
	public void setEndReqDate(java.util.Date endReqDate){
		this.endReqDate = endReqDate;
	}
	public java.util.Date getReqDate(){
		return reqDate;
	}
	public void setReqDate(java.util.Date reqDate){
		this.reqDate = reqDate;
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
	public java.util.Date getStartAcceptDate(){
		return startAcceptDate;
	}
	public void setStartAcceptDate(java.util.Date startAcceptDate){
		this.startAcceptDate = startAcceptDate;
	}
	public java.util.Date getEndAcceptDate(){
		return endAcceptDate;
	}
	public void setEndAcceptDate(java.util.Date endAcceptDate){
		this.endAcceptDate = endAcceptDate;
	}
	public java.util.Date getAcceptDate(){
		return acceptDate;
	}
	public void setAcceptDate(java.util.Date acceptDate){
		this.acceptDate = acceptDate;
	}
	public java.util.Date getStartAgentDate(){
		return startAgentDate;
	}
	public void setStartAgentDate(java.util.Date startAgentDate){
		this.startAgentDate = startAgentDate;
	}
	public java.util.Date getEndAgentDate(){
		return endAgentDate;
	}
	public void setEndAgentDate(java.util.Date endAgentDate){
		this.endAgentDate = endAgentDate;
	}
	public java.util.Date getAgentDate(){
		return agentDate;
	}
	public void setAgentDate(java.util.Date agentDate){
		this.agentDate = agentDate;
	}
	public java.util.Date getStartEndDate(){
		return startEndDate;
	}
	public void setStartEndDate(java.util.Date startEndDate){
		this.startEndDate = startEndDate;
	}
	public java.util.Date getEndEndDate(){
		return endEndDate;
	}
	public void setEndEndDate(java.util.Date endEndDate){
		this.endEndDate = endEndDate;
	}
	public java.util.Date getEndDate(){
		return endDate;
	}
	public void setEndDate(java.util.Date endDate){
		this.endDate = endDate;
	}
	public java.lang.String getRemark(){
		return remark;
	}
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	public java.lang.String getDocCateId() {
		return docCateId;
	}
	public void setDocCateId(java.lang.String docCateId) {
		this.docCateId = docCateId;
	}

}