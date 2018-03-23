package gnwf.vo;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfQues {
	public static final String SELF_FIELDS = "WfQues.QuesTypeID,WfQues.SourceID,WfQues.CloseQuesReason,WfQues.FractionDefective,WfQues.CrDefectId,WfQues.PicXY,WfQues.CompletedDate,WfQues.IsRiskQues,WfQues.UsrName,WfQues.Remark,WfQues.PrjtNo,WfQues.FileName,WfQues.FileNo,WfQues.IdtfResFileName,WfQues.ResultFileName,WfQues.IdtfResFileNo,WfQues.ResultFileNo,WfQues.QuesId,WfQues.ScheId,WfQues.TaskId,WfQues.CateId,WfQues.ComId,WfQues.DeptId,WfQues.UserId,WfQues.QuesLevel,WfQues.Status,WfQues.CreateBy,WfQues.LastUpd,WfQues.WfNo,WfQues.Title,WfQues.RyUsrs,WfQues.IsRisk,WfQues.CreateDate,WfQues.LastUpdDate,WfQues.QuesText,WfQues.Result,WfQues.IdtfBy,WfQues.TestItemID,WfQues.TestItemName,WfQues.IdtfDate,WfQues.IdtfRes";

	private java.lang.String quesId;
	private java.lang.Integer scheId;
	private java.lang.Integer taskId;
	private java.lang.Integer cateId;
	private java.lang.Integer comId;
	private java.lang.Integer deptId;
	private java.lang.Integer userId;
	private java.lang.Integer quesLevel;
	private java.lang.Integer status;
	private java.lang.Integer createBy;
	private java.lang.Integer idtfBy;
	private java.lang.Integer lastUpd;
	private java.lang.String wfNo;
	private java.lang.String isRisk;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date idtfDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastUpdDate;
	private java.util.Date startLastUpdDate;
	private java.util.Date endLastUpdDate;
	private java.lang.String quesText;
	private java.lang.String result;
	private java.lang.String title;
	private java.lang.String ryUsrs;
	private java.lang.String IdtfRes;
	private java.lang.String prjtNo;
	
	private java.lang.String resultFileNo;
	private java.lang.String idtfResFileNo;
	private java.lang.String resultFileName;
	private java.lang.String idtfResFileName;
	
	private java.lang.String fileNo;
	private java.lang.String fileName;
	
	private java.lang.String remark;
	private String usrName;
	
	private java.lang.Integer sourceID;
	private java.lang.Integer quesTypeID;
	
	private java.lang.String CloseQuesReason;
	

	private java.lang.String fractionDefective;//不良比例
	
	private java.lang.String testItemID; //测试项目ID
	private java.lang.String testItemName; //测试项目名称
	
	
	public java.lang.String getTestItemName() {
		return testItemName;
	}
	public void setTestItemName(java.lang.String testItemName) {
		this.testItemName = testItemName;
	}
	public java.lang.String getTestItemID() {
		return testItemID;
	}
	public void setTestItemID(java.lang.String testItemID) {
		this.testItemID = testItemID;
	}
	public java.lang.String getFractionDefective() {
		return fractionDefective;
	}
	public void setFractionDefective(java.lang.String fractionDefective) {
		this.fractionDefective = fractionDefective;
	}
	/** 问题是否是风险问题 **/
	private Integer isRiskQues;
	@JSONField(format="yyyy-MM-dd")
	private Date completedDate;
	
	private String picXY;
	private String crDefectId;
	
	
	public java.lang.String getCloseQuesReason() {
		return CloseQuesReason;
	}
	public void setCloseQuesReason(java.lang.String closeQuesReason) {
		CloseQuesReason = closeQuesReason;
	}
	public java.lang.Integer getSourceID() {
		return sourceID;
	}
	public void setSourceID(java.lang.Integer sourceID) {
		this.sourceID = sourceID;
	}
	public java.lang.Integer getQuesTypeID() {
		return quesTypeID;
	}
	public void setQuesTypeID(java.lang.Integer quesTypeID) {
		this.quesTypeID = quesTypeID;
	}
	public java.lang.String getQuesId(){
		return quesId;
	}
	public void setQuesId(java.lang.String quesId){
		this.quesId = quesId;
	}
	public java.lang.Integer getScheId(){
		return scheId;
	}
	public void setScheId(java.lang.Integer scheId){
		this.scheId = scheId;
	}
	public java.lang.Integer getTaskId(){
		return taskId;
	}
	public void setTaskId(java.lang.Integer taskId){
		this.taskId = taskId;
	}
	public java.lang.Integer getCateId(){
		return cateId;
	}
	public void setCateId(java.lang.Integer cateId){
		this.cateId = cateId;
	}
	public java.lang.Integer getComId(){
		return comId;
	}
	public void setComId(java.lang.Integer comId){
		this.comId = comId;
	}
	public java.lang.Integer getDeptId(){
		return deptId;
	}
	public void setDeptId(java.lang.Integer deptId){
		this.deptId = deptId;
	}
	public java.lang.Integer getUserId(){
		return userId;
	}
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}
	public java.lang.Integer getQuesLevel(){
		return quesLevel;
	}
	public void setQuesLevel(java.lang.Integer quesLevel){
		this.quesLevel = quesLevel;
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
	public java.lang.String getIsRisk(){
		return isRisk;
	}
	public void setIsRisk(java.lang.String isRisk){
		this.isRisk = isRisk;
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
	public java.lang.String getQuesText(){
		return quesText;
	}
	public void setQuesText(java.lang.String quesText){
		this.quesText = quesText;
	}
	public java.lang.String getResult(){
		return result;
	}
	public void setResult(java.lang.String result){
		this.result = result;
	}
	public java.lang.String getTitle() {
		return title;
	}
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	public java.lang.String getRyUsrs() {
		return ryUsrs;
	}
	public void setRyUsrs(java.lang.String ryUsrs) {
		this.ryUsrs = ryUsrs;
	}
	public java.lang.Integer getIdtfBy() {
		return idtfBy;
	}
	public void setIdtfBy(java.lang.Integer idtfBy) {
		this.idtfBy = idtfBy;
	}
	public java.util.Date getIdtfDate() {
		return idtfDate;
	}
	public void setIdtfDate(java.util.Date idtfDate) {
		this.idtfDate = idtfDate;
	}
	public java.lang.String getIdtfRes() {
		return IdtfRes;
	}
	public void setIdtfRes(java.lang.String idtfRes) {
		IdtfRes = idtfRes;
	}
	public java.lang.String getResultFileNo() {
		return resultFileNo;
	}
	public void setResultFileNo(java.lang.String resultFileNo) {
		this.resultFileNo = resultFileNo;
	}
	public java.lang.String getIdtfResFileNo() {
		return idtfResFileNo;
	}
	public void setIdtfResFileNo(java.lang.String idtfResFileNo) {
		this.idtfResFileNo = idtfResFileNo;
	}
	public java.lang.String getResultFileName() {
		return resultFileName;
	}
	public void setResultFileName(java.lang.String resultFileName) {
		this.resultFileName = resultFileName;
	}
	public java.lang.String getIdtfResFileName() {
		return idtfResFileName;
	}
	public void setIdtfResFileName(java.lang.String idtfResFileName) {
		this.idtfResFileName = idtfResFileName;
	}
	public java.lang.String getFileNo() {
		return fileNo;
	}
	public void setFileNo(java.lang.String fileNo) {
		this.fileNo = fileNo;
	}
	public java.lang.String getFileName() {
		return fileName;
	}
	public void setFileName(java.lang.String fileName) {
		this.fileName = fileName;
	}
	public java.lang.String getPrjtNo() {
		return prjtNo;
	}
	public void setPrjtNo(java.lang.String prjtNo) {
		this.prjtNo = prjtNo;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public Integer getIsRiskQues() {
		return isRiskQues;
	}
	public void setIsRiskQues(Integer isRiskQues) {
		this.isRiskQues = isRiskQues;
	}
	
	public Date getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}
	
	public String getPicXY() {
		return picXY;
	}
	public void setPicXY(String picXY) {
		this.picXY = picXY;
	}
	
	public String getCrDefectId() {
		return crDefectId;
	}
	public void setCrDefectId(String crDefectId) {
		this.crDefectId = crDefectId;
	}
	@Override
	public String toString() {
		return "BasicWfQues [quesId=" + quesId + ", scheId=" + scheId
				+ ", taskId=" + taskId + ", cateId=" + cateId + ", comId="
				+ comId + ", deptId=" + deptId + ", userId=" + userId
				+ ", quesLevel=" + quesLevel + ", status=" + status
				+ ", createBy=" + createBy + ", idtfBy=" + idtfBy
				+ ", lastUpd=" + lastUpd + ", wfNo=" + wfNo + ", isRisk="
				+ isRisk + ", createDate=" + createDate + ", idtfDate="
				+ idtfDate + ", startCreateDate=" + startCreateDate
				+ ", endCreateDate=" + endCreateDate + ", lastUpdDate="
				+ lastUpdDate + ", startLastUpdDate=" + startLastUpdDate
				+ ", endLastUpdDate=" + endLastUpdDate + ", quesText="
				+ quesText + ", result=" + result + ", title=" + title
				+ ", ryUsrs=" + ryUsrs + ", IdtfRes=" + IdtfRes + ", prjtNo="
				+ prjtNo + ", resultFileNo=" + resultFileNo
				+ ", idtfResFileNo=" + idtfResFileNo + ", resultFileName="
				+ resultFileName + ", idtfResFileName=" + idtfResFileName
				+ ", fileNo=" + fileNo + ", fileName=" + fileName + ", remark="
				+ remark + ", usrName=" + usrName + ", isRiskQues="
				+ isRiskQues + ", completedDate=" + completedDate + ", picXY="
				+ picXY + ", crDefectId=" + crDefectId + ", sourceID="
				+ sourceID + ", quesTypeID=" + quesTypeID + ", fractionDefective=" 
				+ fractionDefective + ", CloseQuesReason=" + CloseQuesReason + "]";
	}
}