package gnwf.vo;

public class WfDoc extends BasicWfDoc {
	public static final String ALL_FIELDS = "WfDoc.DocType,WfDoc.DocId,WfDoc.TaskId,WfDoc.FileNo,WfDoc.ProjectNo,WfDoc.Status,WfDoc.CreateBy,WfDoc.LastUpd,WfDoc.CateId,WfDoc.FlowId,WfDoc.DeptDocId,WfDoc.WfNo,WfDoc.UriLink,WfDoc.CreateDate,WfDoc.LastUpdDate,WfDoc.DocName,WfDoc.DocVer,WfRdTask.TaskId as TaskId,WfStep.StepName,Usr.UsrName,WfScheCfgDoc.DocName as CateName,WfRd.ProjectNo as ProjectNo,WfRd.WfName as WfName,ProcFile.Status as IsBase";
	public static final String LIST_FIELDS = "WfDoc.DocType,WfDoc.DocId,WfDoc.TaskId,WfDoc.FileNo,WfDoc.ProjectNo,WfDoc.Status,WfDoc.CreateBy,WfDoc.LastUpd,WfDoc.CateId,WfDoc.FlowId,WfDoc.DeptDocId,WfDoc.WfNo,WfDoc.UriLink,WfDoc.CreateDate,WfDoc.LastUpdDate,WfDoc.DocName,WfDoc.DocVer,WfRdTask.TaskId as TaskId,WfStep.StepName,Usr.UsrName,WfScheCfgDoc.DocName as CateName,WfRd.ProjectNo as ProjectNo,WfRd.WfName as WfName,ProcFile.Status as IsBase";

	private java.util.List<gnwf.vo.WfDocRev> wfDocRevs;

	private Integer taskId;
	private String wfNo;
	private String icon;
	private String createName;
	private String cateName;
	private String stepName;
	private String projectNo;
	private String wfName;
	private int isBase; // �Ƿ�鵵
	private Integer downloadStatus;//判断项目经理是否能下载字段
	
	
	public Integer getDownloadStatus() {
		return downloadStatus;
	}
	public void setDownloadStatus(Integer downloadStatus) {
		this.downloadStatus = downloadStatus;
	}
	// WfScheCfgDoc.DocId, if scDocId is null then the row from DbBasic
	private Integer scDocId;
	
	
	private int isCurrntDoc;  //是不是当前步骤必须上传的文档

	public java.util.List<gnwf.vo.WfDocRev> getWfDocRevs() {
		return wfDocRevs;
	}
	public void setWfDocRevs(java.util.List<gnwf.vo.WfDocRev> wfDocRevs){
		this.wfDocRevs = wfDocRevs;
	}
	public void addtoWfDocRevs(gnwf.vo.WfDocRev wfDocRev){
		if(getWfDocRevs() == null) setWfDocRevs(new java.util.ArrayList<gnwf.vo.WfDocRev>());
			getWfDocRevs().add(wfDocRev);
	}

	public Integer getTaskId(){
		return taskId;
	}
	public void setTaskId(Integer taskId){
		this.taskId = taskId;
	}
	public String getWfNo(){
		return wfNo;
	}
	public void setWfNo(String wfNo){
		this.wfNo = wfNo;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getWfName() {
		return wfName;
	}
	public void setWfName(String wfName) {
		this.wfName = wfName;
	}
	public int getIsBase() {
		return isBase;
	}
	public void setIsBase(int isBase) {
		this.isBase = isBase;
	}
	public Integer getScDocId() {
		return scDocId;
	}
	public void setScDocId(Integer scDocId) {
		this.scDocId = scDocId;
	}
	public int getIsCurrntDoc() {
		return isCurrntDoc;
	}
	public void setIsCurrntDoc(int isCurrntDoc) {
		this.isCurrntDoc = isCurrntDoc;
	}

}