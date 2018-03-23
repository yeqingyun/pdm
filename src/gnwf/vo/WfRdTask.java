package gnwf.vo;

import java.util.List;

public class WfRdTask extends BasicWfRdTask {
	//public static final String ALL_FIELDS = "WfRdTask.TaskId,WfRdTask.PreTaskId,WfRdTask.StepId,WfRdTask.CreateBy,WfRdTask.AcceptBy,WfRdTask.AgentBy,WfRdTask.TaskType,WfRdTask.Status,WfRdTask.IsSystemFinsh,WfRdTask.WfNo,WfRdTask.ReqDate,WfRdTask.CreateDate,WfRdTask.AcceptDate,WfRdTask.AgentDate,WfRdTask.EndDate,WfRdTask.Remark,WfStep.StepId as StepName,WfRd.WfNo as WfNo";
	//public static final String LIST_FIELDS = "WfRdTask.TaskId,WfRdTask.PreTaskId,WfRdTask.StepId,WfRdTask.CreateBy,WfRdTask.AcceptBy,WfRdTask.AgentBy,WfRdTask.TaskType,WfRdTask.Status,WfRdTask.IsSystemFinsh,WfRdTask.WfNo,WfRdTask.ReqDate,WfRdTask.CreateDate,WfRdTask.AcceptDate,WfRdTask.AgentDate,WfRdTask.EndDate,WfRdTask.Remark,WfStep.StepId as StepName,WfRd.WfNo as WfNo";
	
	public static final String ALL_FIELDS = "WfRdTask.DocCateId,WfRdTask.TaskId,WfRdTask.PreTaskId,WfRdTask.StepId,WfRdTask.CreateBy,WfRdTask.AcceptBy,WfRdTask.AgentBy,WfRdTask.TaskType,WfRdTask.Status,WfRdTask.IsSystemFinsh,WfRdTask.WfNo,WfRdTask.ReqDate,WfRdTask.CreateDate,WfRdTask.AcceptDate,WfRdTask.AgentDate,WfRdTask.EndDate,WfRdTask.Remark,WfStep.StepId,WfStep.StepName,WfStep.StepUri,WfStep.Sort,WfStep.StepType,WfStep.IsLastStep,WfStep.IsAuto,WfStep.WaitAuxiliary,WfStep.IsFillQues,WfStep.IsDQAJob,WfStep.IsUpdForm,WfStep.StepDesc,Usr2.UsrName as CreaterName,Usr1.UsrName as AcceptName"; //,Usr3.UsrName as AgentName
	public static final String LIST_FIELDS = "WfRdTask.DocCateId,WfRdTask.TaskId,WfRdTask.PreTaskId,WfRdTask.StepId,WfRdTask.CreateBy,WfRdTask.AcceptBy,WfRdTask.AgentBy,WfRdTask.TaskType,WfRdTask.Status,WfRdTask.IsSystemFinsh,WfRdTask.WfNo,WfRdTask.ReqDate,WfRdTask.CreateDate,WfRdTask.AcceptDate,WfRdTask.AgentDate,WfRdTask.EndDate,WfRdTask.Remark,WfStep.StepId,WfStep.StepName,WfStep.StepUri,WfStep.Sort,WfStep.StepType,WfStep.IsLastStep,WfStep.IsAuto,WfStep.WaitAuxiliary,WfStep.IsFillQues,WfStep.IsDQAJob,WfStep.IsUpdForm,WfStep.StepDesc,Usr2.UsrName as CreaterName,Usr1.UsrName as AcceptName";//,Usr3.UsrName as AgentName
	
	private java.util.List<gnwf.vo.WfDoc> wfDocs;
	private java.util.List<gnwf.vo.WfRdSign> wfRdSigns;

    private String wfNo;
	
	private Integer stepId;
	private String  stepUri;
	private String  stepName;
	private Integer sort;				//步骤排序
	private Integer stepType;			//步骤类型
	private String  stepDesc;			//工作流描述
	private Integer isLastStep;			//是否最后步骤
	private Integer isUpdForm;			//协办是否可以更改表单
	private Integer stepIsAuto;			//为1则自动转交
	private Integer waitAuxiliary;		//主办是否必须等待协办
	private Integer IsFillQues;			//是否必须填写问题
	private Integer IsDQAJob;			//是否DQA步骤必须关闭所有问题
	private String  createrName;
	private String  acceptName;			
	private String  agentName;			//任务代办人
	private String  useTime;			//任务用时
	private Integer	overTime;			//超时天数
	private List<WfRdTask> backTasks;	//退回来的任务退回原因集
	
	private String acptRoleName;		//接收人角色名
	private String docNames;			//交付件名称集合
	
	

	public java.util.List<gnwf.vo.WfDoc> getWfDocs() {
		return wfDocs;
	}
	public void setWfDocs(java.util.List<gnwf.vo.WfDoc> wfDocs){
		this.wfDocs = wfDocs;
	}
	public void addtoWfDocs(gnwf.vo.WfDoc wfDoc){
		if(getWfDocs() == null) setWfDocs(new java.util.ArrayList<gnwf.vo.WfDoc>());
			getWfDocs().add(wfDoc);
	}
	public java.util.List<gnwf.vo.WfRdSign> getWfRdSigns() {
		return wfRdSigns;
	}
	public void setWfRdSigns(java.util.List<gnwf.vo.WfRdSign> wfRdSigns){
		this.wfRdSigns = wfRdSigns;
	}
	public void addtoWfRdSigns(gnwf.vo.WfRdSign wfRdSign){
		if(getWfRdSigns() == null) setWfRdSigns(new java.util.ArrayList<gnwf.vo.WfRdSign>());
			getWfRdSigns().add(wfRdSign);
	}

	public String getStepName(){
		return stepName;
	}
	public void setStepName(String stepName){
		this.stepName = stepName;
	}
	public String getWfNo(){
		return wfNo;
	}
	public void setWfNo(String wfNo){
		this.wfNo = wfNo;
	}
	public Integer getStepId() {
		return stepId;
	}
	public void setStepId(Integer stepId) {
		this.stepId = stepId;
	}
	public String getStepUri() {
		return stepUri;
	}
	public void setStepUri(String stepUri) {
		this.stepUri = stepUri;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getStepType() {
		return stepType;
	}
	public void setStepType(Integer stepType) {
		this.stepType = stepType;
	}
	public String getStepDesc() {
		return stepDesc;
	}
	public void setStepDesc(String stepDesc) {
		this.stepDesc = stepDesc;
	}
	public Integer getIsLastStep() {
		return isLastStep;
	}
	public void setIsLastStep(Integer isLastStep) {
		this.isLastStep = isLastStep;
	}
	public Integer getIsUpdForm() {
		return isUpdForm;
	}
	public void setIsUpdForm(Integer isUpdForm) {
		this.isUpdForm = isUpdForm;
	}
	public Integer getStepIsAuto() {
		return stepIsAuto;
	}
	public void setStepIsAuto(Integer stepIsAuto) {
		this.stepIsAuto = stepIsAuto;
	}
	public Integer getWaitAuxiliary() {
		return waitAuxiliary;
	}
	public void setWaitAuxiliary(Integer waitAuxiliary) {
		this.waitAuxiliary = waitAuxiliary;
	}
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	public String getAcceptName() {
		return acceptName;
	}
	public void setAcceptName(String acceptName) {
		this.acceptName = acceptName;
	}
	public String getUseTime() {
		return useTime;
	}
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	public Integer getOverTime() {
		return overTime;
	}
	public void setOverTime(Integer overTime) {
		this.overTime = overTime;
	}
	public List<WfRdTask> getBackTasks() {
		return backTasks;
	}
	public void setBackTasks(List<WfRdTask> backTasks) {
		this.backTasks = backTasks;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAcptRoleName() {
		return acptRoleName;
	}
	public void setAcptRoleName(String acptRoleName) {
		this.acptRoleName = acptRoleName;
	}
	public String getDocNames() {
		return docNames;
	}
	public void setDocNames(String docNames) {
		this.docNames = docNames;
	}
	public Integer getIsFillQues() {
		return IsFillQues;
	}
	public void setIsFillQues(Integer isFillQues) {
		IsFillQues = isFillQues;
	}
	public Integer getIsDQAJob() {
		return IsDQAJob;
	}
	public void setIsDQAJob(Integer isDQAJob) {
		IsDQAJob = isDQAJob;
	}

}