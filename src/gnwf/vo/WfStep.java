package gnwf.vo;

import java.util.Date;
import java.util.List;

import zrsy.vo.Usr;

/**
 * @author Administrator
 *
 */
public class WfStep extends BasicWfStep implements Comparable<WfStep>{
	public static final String ALL_FIELDS = "WfStep.StepId,WfStep.FlowId,WfStep.PreStep,WfStep.StepType,WfStep.Sort,WfStep.IsAuto,WfStep.IsUpdForm,WfStep.IsSysStartUp,WfStep.IsSysFinsh,WfStep.TimeQty,WfStep.SelectCom,WfStep.IsLastStep,WfStep.Status,WfStep.CreateBy,WfStep.LastUpd,WfStep.SelectDept,WfStep.WaitAuxiliary,WfStep.IsFillQues,WfStep.IsDQAJob,WfStep.CreateDate,WfStep.LastUpdDate,WfStep.StepName,WfStep.StepDesc,WfStep.StepUri,WfStep.DocName,WfStep.UploadSize,WfCfg.FlowName as FlowName";
	public static final String LIST_FIELDS = "WfStep.StepId,WfStep.FlowId,WfStep.PreStep,WfStep.StepType,WfStep.Sort,WfStep.IsAuto,WfStep.IsUpdForm,WfStep.IsSysStartUp,WfStep.IsSysFinsh,WfStep.TimeQty,WfStep.SelectCom,WfStep.IsLastStep,WfStep.Status,WfStep.CreateBy,WfStep.LastUpd,WfStep.SelectDept,WfStep.WaitAuxiliary,WfStep.IsFillQues,WfStep.IsDQAJob,WfStep.CreateDate,WfStep.LastUpdDate,WfStep.StepName,WfStep.StepDesc,WfStep.StepUri,WfStep.DocName,WfStep.UploadSize,WfCfg.FlowName as FlowName";
	public static final String LIST = "WfStep.StepId,WfStep.FlowId,WfStep.PreStep,WfStep.StepType,WfStep.Sort,WfStep.IsAuto,WfStep.IsUpdForm,WfStep.IsSysStartUp,WfStep.IsSysFinsh,WfStep.TimeQty,WfStep.SelectCom,WfStep.IsLastStep,WfStep.Status,WfStep.CreateBy,WfStep.LastUpd,WfStep.SelectDept,WfStep.WaitAuxiliary,WfStep.IsFillQues,WfStep.IsDQAJob,WfStep.CreateDate,WfStep.LastUpdDate,WfStep.StepName,WfStep.StepDesc,WfStep.StepUri,WfStep.DocName,WfStep.UploadSize";
	private java.util.List<gnwf.vo.WfRdTask> wfRdTasks;
	private java.util.List<gnwf.vo.WfStepUser> wfStepUsers;
	private java.util.List<gnwf.vo.WfStepNext> wfStepNexts;

	private String flowName;
	private String isChecked;
	
	private List<Usr> mainUser;			//�����û�
	private List<Usr> minorUser;		//Э���û�
	
	private Date 	taskTime;			//ʱ��
	private int		isFinshed;			//�Ƿ����
	private int		isCurrent;			//�Ƿ��ڰ첽��
	private int		nextStepId;			//��һ����
	
	private String idText;
	private String nameText;
	private String taskText;
	
	private String idText2;
	private String nameText2;
	private String taskText2;
	private String DocName;							//上传规定名字
	private int UploadSize;							//上传文件数量
	
	public WfStep(){}
	
	public WfStep(int stepId,String stepName,int sort,int isCurrentId){
		setStepId(stepId);
		setStepName(stepName);
		setSort(sort);
		setIsCurrent(isCurrentId);
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
	public java.util.List<gnwf.vo.WfStepUser> getWfStepUsers() {
		return wfStepUsers;
	}
	public void setWfStepUsers(java.util.List<gnwf.vo.WfStepUser> wfStepUsers){
		this.wfStepUsers = wfStepUsers;
	}
	public void addtoWfStepUsers(gnwf.vo.WfStepUser wfStepUser){
		if(getWfStepUsers() == null) setWfStepUsers(new java.util.ArrayList<gnwf.vo.WfStepUser>());
			getWfStepUsers().add(wfStepUser);
	}
	public java.util.List<gnwf.vo.WfStepNext> getWfStepNexts() {
		return wfStepNexts;
	}
	public void setWfStepNexts(java.util.List<gnwf.vo.WfStepNext> wfStepNexts){
		this.wfStepNexts = wfStepNexts;
	}
	public void addtoWfStepNexts(gnwf.vo.WfStepNext wfStepNext){
		if(getWfStepNexts() == null) setWfStepNexts(new java.util.ArrayList<gnwf.vo.WfStepNext>());
			getWfStepNexts().add(wfStepNext);
	}

	
	public String getFlowName(){
		return flowName;
	}
	public void setFlowName(String flowName){
		this.flowName = flowName;
	}
	public String getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}
	public List<Usr> getMainUser() {
		return mainUser;
	}
	public void setMainUser(List<Usr> mainUser) {
		this.mainUser = mainUser;
	}
	public List<Usr> getMinorUser() {
		return minorUser;
	}
	public void setMinorUser(List<Usr> minorUser) {
		this.minorUser = minorUser;
	}
	public Date getTaskTime() {
		return taskTime;
	}
	public void setTaskTime(Date taskTime) {
		this.taskTime = taskTime;
	}
	public String getIdText() {
		return idText;
	}
	public void setIdText(String idText) {
		this.idText = idText;
	}
	public String getNameText() {
		return nameText;
	}
	public void setNameText(String nameText) {
		this.nameText = nameText;
	}
	public String getTaskText() {
		return taskText;
	}
	public void setTaskText(String taskText) {
		this.taskText = taskText;
	}
	public String getIdText2() {
		return idText2;
	}
	public void setIdText2(String idText2) {
		this.idText2 = idText2;
	}
	public String getNameText2() {
		return nameText2;
	}
	public void setNameText2(String nameText2) {
		this.nameText2 = nameText2;
	}
	public String getTaskText2() {
		return taskText2;
	}
	public void setTaskText2(String taskText2) {
		this.taskText2 = taskText2;
	}
	public int getIsCurrent() {
		return isCurrent;
	}
	public void setIsCurrent(int isCurrent) {
		this.isCurrent = isCurrent;
	}
	public int getNextStepId() {
		return nextStepId;
	}
	public void setNextStepId(int nextStepId) {
		this.nextStepId = nextStepId;
	}
	public int getIsFinshed() {
		return isFinshed;
	}
	public void setIsFinshed(int isFinshed) {
		this.isFinshed = isFinshed;
	}

	@Override
	public int compareTo(WfStep o) {
		if(this.getSort()>o.getSort()){
			return 1;
		}else if(this.getSort()<o.getSort()){
			return -1;
		}
		return 0;
	}

	public String getDocName() {
		return DocName;
	}

	public void setDocName(String docName) {
		DocName = docName;
	}

	public int getUploadSize() {
		return UploadSize;
	}

	public void setUploadSize(int uploadSize) {
		UploadSize = uploadSize;
	}
	
}