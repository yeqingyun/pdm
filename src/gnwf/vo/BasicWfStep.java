package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfStep{
	public static final String SELF_FIELDS = "WfStep.StepId,WfStep.FlowId,WfStep.PreStep,WfStep.StepType,WfStep.Sort,WfStep.IsAuto,WfStep.IsUpdForm,WfStep.IsSysStartUp,WfStep.IsSysFinsh,WfStep.TimeQty,WfStep.SelectCom,WfStep.IsLastStep,WfStep.Status,WfStep.CreateBy,WfStep.LastUpd,WfStep.SelectDept,WfStep.WaitAuxiliary,WfStep.IsFillQues,WfStep.IsDQAJob,WfStep.CreateDate,WfStep.LastUpdDate,WfStep.StepName,WfStep.StepDesc,WfStep.StepUri";

	private java.lang.Integer stepId;
	private java.lang.Integer flowId;
	private java.lang.Integer preStep;
	private java.lang.Integer stepType;
	private java.lang.Integer sort;
	private java.lang.Integer isAuto;
	private java.lang.Integer isUpdForm;
	private java.lang.Integer isSysStartUp;
	private java.lang.Integer isSysFinsh;
	private java.lang.Integer timeQty;
	private java.lang.Integer selectCom;
	private java.lang.Integer isLastStep;
	private java.lang.Integer status;
	private java.lang.Integer createBy;
	private java.lang.Integer lastUpd;
	private java.lang.Integer selectDept;
	private java.lang.Integer waitAuxiliary;
	private java.lang.Integer isFillQues;
	private java.lang.Integer isDQAJob;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastUpdDate;
	private java.util.Date startLastUpdDate;
	private java.util.Date endLastUpdDate;
	private java.lang.String stepName;
	private java.lang.String stepDesc;
	private java.lang.String stepUri;

	public java.lang.Integer getStepId(){
		return stepId;
	}
	public void setStepId(java.lang.Integer stepId){
		this.stepId = stepId;
	}
	public java.lang.Integer getFlowId(){
		return flowId;
	}
	public void setFlowId(java.lang.Integer flowId){
		this.flowId = flowId;
	}
	public java.lang.Integer getPreStep(){
		return preStep;
	}
	public void setPreStep(java.lang.Integer preStep){
		this.preStep = preStep;
	}
	public java.lang.Integer getStepType(){
		return stepType;
	}
	public void setStepType(java.lang.Integer stepType){
		this.stepType = stepType;
	}
	public java.lang.Integer getSort(){
		return sort;
	}
	public void setSort(java.lang.Integer sort){
		this.sort = sort;
	}
	public java.lang.Integer getIsAuto(){
		return isAuto;
	}
	public void setIsAuto(java.lang.Integer isAuto){
		this.isAuto = isAuto;
	}
	public java.lang.Integer getIsUpdForm(){
		return isUpdForm;
	}
	public void setIsUpdForm(java.lang.Integer isUpdForm){
		this.isUpdForm = isUpdForm;
	}
	public java.lang.Integer getIsSysFinsh(){
		return isSysFinsh;
	}
	public void setIsSysFinsh(java.lang.Integer isSysFinsh){
		this.isSysFinsh = isSysFinsh;
	}
	public java.lang.Integer getTimeQty(){
		return timeQty;
	}
	public void setTimeQty(java.lang.Integer timeQty){
		this.timeQty = timeQty;
	}
	public java.lang.Integer getSelectCom(){
		return selectCom;
	}
	public void setSelectCom(java.lang.Integer selectCom){
		this.selectCom = selectCom;
	}
	public java.lang.Integer getIsLastStep(){
		return isLastStep;
	}
	public void setIsLastStep(java.lang.Integer isLastStep){
		this.isLastStep = isLastStep;
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
	public java.lang.Integer getSelectDept(){
		return selectDept;
	}
	public void setSelectDept(java.lang.Integer selectDept){
		this.selectDept = selectDept;
	}
	public java.lang.Integer getWaitAuxiliary(){
		return waitAuxiliary;
	}
	public void setWaitAuxiliary(java.lang.Integer waitAuxiliary){
		this.waitAuxiliary = waitAuxiliary;
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
	public java.util.Date getStartLastUpdDate(){
		return startLastUpdDate;
	}
	public void setStartLastUpdDate(java.util.Date startLastUpdDate){
		this.startLastUpdDate = startLastUpdDate;
	}
	public java.util.Date getEndLastUpdDate(){
		return endLastUpdDate;
	}
	public void setEndLastUpdDate(java.util.Date endLastUpdDate){
		this.endLastUpdDate = endLastUpdDate;
	}
	public java.util.Date getLastUpdDate(){
		return lastUpdDate;
	}
	public void setLastUpdDate(java.util.Date lastUpdDate){
		this.lastUpdDate = lastUpdDate;
	}
	public java.lang.String getStepName(){
		return stepName;
	}
	public void setStepName(java.lang.String stepName){
		this.stepName = stepName;
	}
	public java.lang.String getStepDesc(){
		return stepDesc;
	}
	public void setStepDesc(java.lang.String stepDesc){
		this.stepDesc = stepDesc;
	}
	public java.lang.String getStepUri(){
		return stepUri;
	}
	public void setStepUri(java.lang.String stepUri){
		this.stepUri = stepUri;
	}
	public java.lang.Integer getIsSysStartUp() {
		return isSysStartUp;
	}
	public void setIsSysStartUp(java.lang.Integer isSysStartUp) {
		this.isSysStartUp = isSysStartUp;
	}
	public java.lang.Integer getIsFillQues() {
		return isFillQues;
	}
	public void setIsFillQues(java.lang.Integer isFillQues) {
		this.isFillQues = isFillQues;
	}
	public java.lang.Integer getIsDQAJob() {
		return isDQAJob;
	}
	public void setIsDQAJob(java.lang.Integer isDQAJob) {
		this.isDQAJob = isDQAJob;
	}
	
}