package gnwf.vo;

import java.util.List;

import org.frm.jdbc.DbConnUtil;

import com.alibaba.fastjson.annotation.JSONField;


public class WfRd extends BasicWfRd {
	public static final String ALL_FIELDS = "WfRd.QuesId,WfRd.NeedQues,WfRd.DocCateId,WfRd.RepeatSort,WfRd.ScheId,WfRd.FlowId,WfRd.Status,WfRd.CreateBy,WfRd.LastUpd,WfRd.WfNo,WfRd.ProjectNo,WfRd.PreWfNo,WfRd.PlanSDate,WfRd.PlanEDate,WfRd.FactSDate,WfRd.FactEDate,WfRd.CreateDate,WfRd.LastUpdDate,WfRd.WfName,WfRd.WfDesc,Usr.UsrName";
	public static final String LIST_FIELDS = "WfRd.QuesId,WfRd.NeedQues,WfRd.DocCateId,WfRd.RepeatSort,WfRd.ScheId,WfRd.FlowId,WfRd.Status,WfRd.CreateBy,WfRd.LastUpd,WfRd.WfNo,WfRd.ProjectNo,WfRd.PreWfNo,WfRd.PlanSDate,WfRd.PlanEDate,WfRd.FactSDate,WfRd.FactEDate,WfRd.CreateDate,WfRd.LastUpdDate,WfRd.WfName,WfRd.WfDesc,Usr.UsrName";
	public static final String TASK_FIELDS = "WfRd.QuesId,WfRd.NeedQues,WfRd.DocCateId,WfRd.RepeatSort,WfRd.ScheId,WfRd.FlowId,WfRd.Status,WfRd.CreateBy,WfRd.LastUpd,WfRd.WfNo,WfRd.ProjectNo,WfRd.PreWfNo,WfRd.PlanSDate,WfRd.PlanEDate,WfRd.FactSDate,WfRd.FactEDate,WfRd.CreateDate,WfRd.LastUpdDate,WfRd.WfName,WfRd.WfDesc,Usr.UsrName," +
			                                 "WfStep.StepName as StepName,WfStep.StepId as TaskStepId,WfRdTask.Status as TaskStatus,WfRdTask.TaskId as TaskId,WfRdTask.EndDate as EndDate,WfRdTask.CreateDate as AcceptDate,PrjtDef.PrjtNm as PrjtNm";
	
	/**if projectNo is null or projectNo = "" then projectNo = "Not project number"*/
	public transient final static String NOT_PROJECT = "";

	private java.util.List<gnwf.vo.WfDoc> wfDocs;
	private java.util.List<gnwf.vo.WfQuesRelate> wfQuesRelates;
	private java.util.List<gnwf.vo.WfMatl> wfMatls;
	private java.util.List<gnwf.vo.WfRdTask> wfRdTasks;
	private java.util.List<gnwf.vo.WfRdField> wfRdFields;
	
	private String flowName;
	
	private String createName;
	
	private String deptName;
	private int createHour;
	private int selectType;
	private int currentUserId;
	private Integer onlySelectCurUser;
	private String quesIds;

	
	private String taskNm;
	private String stepName;
	
	private Integer parent;
	private List<WfRd> children;
	private String gylFlag;
	
	
	private Integer taskStepId;  //任务的步奏ID wxh
	private Integer taskId; 
	private Integer  taskStatus;
	/** 判断当前用户的角色是否是《不随项目变更角色》**/
	private Boolean isNotChangeRole = true;
	
	/** 判断当前用户的角色是否是《海外研发角色》**/
	private Boolean isOverseasRole = true;
	
	/**项目名字**/
	private String prjtNm;
	
	//对接EOA处理待办任务的url
	private String url;
	
	public Boolean getIsOverseasRole() {
		return isOverseasRole;
	}
	public void setIsOverseasRole(Boolean isOverseasRole) {
		this.isOverseasRole = isOverseasRole;
	}

	@JSONField(format="yyyy-MM-dd")
	private java.util.Date endDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date acceptDate;

	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getPrjtNm() {
		return prjtNm;
	}
	public void setPrjtNm(String prjtNm) {
		this.prjtNm = prjtNm;
	}
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
	public java.util.List<gnwf.vo.WfQuesRelate> getWfQuesRelates() {
		return wfQuesRelates;
	}
	public void setWfQuesRelates(java.util.List<gnwf.vo.WfQuesRelate> wfQuesRelates){
		this.wfQuesRelates = wfQuesRelates;
	}
	public void addtoWfQuesRelates(gnwf.vo.WfQuesRelate wfQuesRelate){
		if(getWfQuesRelates() == null) setWfQuesRelates(new java.util.ArrayList<gnwf.vo.WfQuesRelate>());
			getWfQuesRelates().add(wfQuesRelate);
	}
	public java.util.List<gnwf.vo.WfMatl> getWfMatls() {
		return wfMatls;
	}
	public void setWfMatls(java.util.List<gnwf.vo.WfMatl> wfMatls){
		this.wfMatls = wfMatls;
	}
	public void addtoWfMatls(gnwf.vo.WfMatl wfMatl){
		if(getWfMatls() == null) setWfMatls(new java.util.ArrayList<gnwf.vo.WfMatl>());
			getWfMatls().add(wfMatl);
	}
	public java.util.List<gnwf.vo.WfRdTask> getWfRdTasks() {
		return wfRdTasks;
	}
	public void setWfRdTasks(java.util.List<gnwf.vo.WfRdTask> wfRdTasks){
		this.wfRdTasks = wfRdTasks;
	}
	public void addtoWfRdTasks(gnwf.vo.WfRdTask wfRdTask){
		if(getWfRdTasks() == null) setWfRdTasks(new java.util.ArrayList<gnwf.vo.WfRdTask>());
			getWfRdTasks().add(wfRdTask);
	}
	public java.util.List<gnwf.vo.WfRdField> getWfRdFields() {
		return wfRdFields;
	}
	public void setWfRdFields(java.util.List<gnwf.vo.WfRdField> wfRdFields){
		this.wfRdFields = wfRdFields;
	}
	public void addtoWfRdFields(gnwf.vo.WfRdField wfRdField){
		if(getWfRdFields() == null) setWfRdFields(new java.util.ArrayList<gnwf.vo.WfRdField>());
			getWfRdFields().add(wfRdField);
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public int getCreateHour() {
		return createHour;
	}
	public void setCreateHour(int createHour) {
		this.createHour = createHour;
	}
	public int getSelectType() {
		return selectType;
	}
	public void setSelectType(int selectType) {
		this.selectType = selectType;
	}
	public int getCurrentUserId() {
		return currentUserId;
	}
	public void setCurrentUserId(int currentUserId) {
		this.currentUserId = currentUserId;
	}
	public Integer getOnlySelectCurUser() {
		return onlySelectCurUser;
	}
	public void setOnlySelectCurUser(Integer onlySelectCurUser) {
		this.onlySelectCurUser = onlySelectCurUser;
	}
	public String getQuesIds() {
		return quesIds;
	}
	public void setQuesIds(String quesIds) {
		this.quesIds = quesIds;
	}
	public String getTaskNm() {
		return taskNm;
	}
	public void setTaskNm(String taskNm) {
		this.taskNm = taskNm;
	}
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public Integer getParent() {
		return parent;
	}
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	public List<WfRd> getChildren() {
		return children;
	}
	public void setChildren(List<WfRd> children) {
		this.children = children;
	}
	public Integer getTaskStepId() {
		return taskStepId;
	}
	public void setTaskStepId(Integer taskStepId) {
		this.taskStepId = taskStepId;
	}
	public Integer getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public Boolean getIsNotChangeRole() {
		return isNotChangeRole;
	}
	public void setIsNotChangeRole(Boolean isNotChangeRole) {
		this.isNotChangeRole = isNotChangeRole;
	}
	public java.util.Date getEndDate() {
		return endDate;
	}
	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	public java.util.Date getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(java.util.Date acceptDate) {
		this.acceptDate = acceptDate;
	}
	public String getFlowName() {
		return flowName;
	}
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getGylFlag() {
		return gylFlag;
	}
	public void setGylFlag(String gylFlag) {
		this.gylFlag = gylFlag;
	}
	
}