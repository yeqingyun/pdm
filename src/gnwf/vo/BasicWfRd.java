package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfRd {
	public static final String SELF_FIELDS = " WfRd.QuesId,WfRd.NeedQues,WfRd.DocCateId,WfRd.RepeatSort,WfRd.ScheId,WfRd.FlowId,WfRd.Status,WfRd.CreateBy,WfRd.LastUpd,WfRd.WfNo,WfRd.ProjectNo,WfRd.PreWfNo,WfRd.PlanSDate,WfRd.PlanEDate,WfRd.FactSDate,WfRd.FactEDate,WfRd.CreateDate,WfRd.LastUpdDate,WfRd.WfName,WfRd.WfDesc";

	private java.lang.Integer scheId;
	private java.lang.Integer flowId;
	private java.lang.Integer status;
	private java.lang.Integer createBy;
	private java.lang.Integer lastUpd;
	private java.lang.String wfNo;
	private java.lang.String projectNo;
	private java.lang.String preWfNo;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date planSDate;
	private java.util.Date startPlanSDate;
	private java.util.Date endPlanSDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date planEDate;
	private java.util.Date startPlanEDate;
	private java.util.Date endPlanEDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date factSDate;
	private java.util.Date startFactSDate;
	private java.util.Date endFactSDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date factEDate;
	private java.util.Date startFactEDate;
	private java.util.Date endFactEDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastUpdDate;
	private java.util.Date startLastUpdDate;
	private java.util.Date endLastUpdDate;
	private java.lang.String wfName;
	private java.lang.String wfDesc;
	
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date stopDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date restartDate;
	private java.lang.Integer stopDateNum;
	
	private java.lang.Integer repeatSort;
	private java.lang.Integer needQues;
	private java.lang.Integer quesId;
	
	
	private java.lang.String usrIDs;
	
	public java.lang.String getUsrIDs() {
		return usrIDs;
	}
	public void setUsrIDs(java.lang.String usrIDs) {
		this.usrIDs = usrIDs;
	}
	public java.lang.Integer getQuesId() {
		return quesId;
	}
	public void setQuesId(java.lang.Integer quesId) {
		this.quesId = quesId;
	}
	//为归档流程定义的一个字段
	private java.lang.String docCateId;

	public java.lang.Integer getScheId(){
		return scheId;
	}
	public void setScheId(java.lang.Integer scheId){
		this.scheId = scheId;
	}
	public java.lang.Integer getFlowId(){
		return flowId;
	}
	public void setFlowId(java.lang.Integer flowId){
		this.flowId = flowId;
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
	public java.lang.String getWfNo(){
		return wfNo;
	}
	public void setWfNo(java.lang.String wfNo){
		this.wfNo = wfNo;
	}
	public java.lang.String getProjectNo(){
		return projectNo;
	}
	public void setProjectNo(java.lang.String projectNo){
		this.projectNo = projectNo;
	}
	public java.lang.String getPreWfNo(){
		return preWfNo;
	}
	public void setPreWfNo(java.lang.String preWfNo){
		this.preWfNo = preWfNo;
	}
	public java.util.Date getStartPlanSDate(){
		return startPlanSDate;
	}
	public void setStartPlanSDate(java.util.Date startPlanSDate){
		this.startPlanSDate = startPlanSDate;
	}
	public java.util.Date getEndPlanSDate(){
		return endPlanSDate;
	}
	public void setEndPlanSDate(java.util.Date endPlanSDate){
		this.endPlanSDate = endPlanSDate;
	}
	public java.util.Date getPlanSDate(){
		return planSDate;
	}
	public void setPlanSDate(java.util.Date planSDate){
		this.planSDate = planSDate;
	}
	public java.util.Date getStartPlanEDate(){
		return startPlanEDate;
	}
	public void setStartPlanEDate(java.util.Date startPlanEDate){
		this.startPlanEDate = startPlanEDate;
	}
	public java.util.Date getEndPlanEDate(){
		return endPlanEDate;
	}
	public void setEndPlanEDate(java.util.Date endPlanEDate){
		this.endPlanEDate = endPlanEDate;
	}
	public java.util.Date getPlanEDate(){
		return planEDate;
	}
	public void setPlanEDate(java.util.Date planEDate){
		this.planEDate = planEDate;
	}
	public java.util.Date getStartFactSDate(){
		return startFactSDate;
	}
	public void setStartFactSDate(java.util.Date startFactSDate){
		this.startFactSDate = startFactSDate;
	}
	public java.util.Date getEndFactSDate(){
		return endFactSDate;
	}
	public void setEndFactSDate(java.util.Date endFactSDate){
		this.endFactSDate = endFactSDate;
	}
	public java.util.Date getFactSDate(){
		return factSDate;
	}
	public void setFactSDate(java.util.Date factSDate){
		this.factSDate = factSDate;
	}
	public java.util.Date getStartFactEDate(){
		return startFactEDate;
	}
	public void setStartFactEDate(java.util.Date startFactEDate){
		this.startFactEDate = startFactEDate;
	}
	public java.util.Date getEndFactEDate(){
		return endFactEDate;
	}
	public void setEndFactEDate(java.util.Date endFactEDate){
		this.endFactEDate = endFactEDate;
	}
	public java.util.Date getFactEDate(){
		return factEDate;
	}
	public void setFactEDate(java.util.Date factEDate){
		this.factEDate = factEDate;
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
	public java.lang.String getWfName(){
		return wfName;
	}
	public void setWfName(java.lang.String wfName){
		this.wfName = wfName;
	}
	public java.lang.String getWfDesc(){
		return wfDesc;
	}
	public void setWfDesc(java.lang.String wfDesc){
		this.wfDesc = wfDesc;
	}
	public java.util.Date getStopDate() {
		return stopDate;
	}
	public void setStopDate(java.util.Date stopDate) {
		this.stopDate = stopDate;
	}
	public java.util.Date getRestartDate() {
		return restartDate;
	}
	public void setRestartDate(java.util.Date restartDate) {
		this.restartDate = restartDate;
	}
	public java.lang.Integer getStopDateNum() {
		return stopDateNum;
	}
	public void setStopDateNum(java.lang.Integer stopDateNum) {
		this.stopDateNum = stopDateNum;
	}
	public java.lang.Integer getRepeatSort() {
		return repeatSort;
	}
	public void setRepeatSort(java.lang.Integer repeatSort) {
		this.repeatSort = repeatSort;
	}
	public java.lang.String getDocCateId() {
		return docCateId;
	}
	public void setDocCateId(java.lang.String docCateId) {
		this.docCateId = docCateId;
	}
	public java.lang.Integer getNeedQues() {
		return needQues;
	}
	public void setNeedQues(java.lang.Integer needQues) {
		this.needQues = needQues;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((wfNo == null) ? 0 : wfNo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasicWfRd other = (BasicWfRd) obj;
		if (wfNo == null) {
			if (other.wfNo != null)
				return false;
		} else if (!wfNo.equals(other.wfNo))
			return false;
		return true;
	}
}