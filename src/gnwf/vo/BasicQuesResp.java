package gnwf.vo;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class BasicQuesResp {
	public static final String SELF_FIELDS = " QuesResp.QuesAnalysis,QuesResp.SolveNum,QuesResp.LastUpdDate,QuesResp.CreateBy,QuesResp.ResultFileNo,QuesResp.LastUpd,QuesResp.QuesId,QuesResp.Status,QuesResp.Result,QuesResp.ResultFileName,QuesResp.UsrId,QuesResp.CreateDate,QuesResp.IdtfRes,QuesResp.IdtfBy,QuesResp.IdtfResFileName,QuesResp.IdtfResFileNo,QuesResp.IdtfDate,QuesResp.ResultDate,QuesResp.Id,QuesResp.RemarkFileNo,QuesResp.RespType,QuesResp.RemarkFileName,QuesResp.Remark,QuesResp.RushDate";

	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastUpdDate;
	private java.util.Date startLastUpdDate;
	private java.util.Date endLastUpdDate;
	private java.lang.Integer createBy;
	private java.lang.String resultFileNo;
	private java.lang.Integer lastUpd;
	private java.lang.String quesId;
	private java.lang.Integer status;
	private java.lang.String result;
	
	private java.lang.String quesAnalysis;
	
	private java.lang.String resultFileName;
	private java.lang.Integer usrId;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	private java.lang.String idtfRes;
	private java.lang.Integer idtfBy;
	private java.lang.String idtfResFileName;
	private java.lang.String idtfResFileNo;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date idtfDate;
	private java.util.Date startIdtfDate;
	private java.util.Date endIdtfDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date resultDate;
	private java.util.Date startResultDate;
	private java.util.Date endResultDate;
	private java.lang.Integer id;
	private java.lang.String remarkFileNo;
	private java.lang.Integer respType;
	private java.lang.String remarkFileName;
	private java.lang.String remark;
	@JSONField(format="yyyy-MM-dd")
	private Date rushDate;
    private Integer solveNum;
	public Date getRushDate() {
		return rushDate;
	}
	public void setRushDate(Date rushDate) {
		this.rushDate = rushDate;
	}

	
	public java.lang.String getQuesAnalysis() {
		return quesAnalysis;
	}
	public void setQuesAnalysis(java.lang.String quesAnalysis) {
		this.quesAnalysis = quesAnalysis;
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
	public java.lang.Integer getCreateBy(){
		return createBy;
	}
	public void setCreateBy(java.lang.Integer createBy){
		this.createBy = createBy;
	}
	public java.lang.String getResultFileNo(){
		return resultFileNo;
	}
	public void setResultFileNo(java.lang.String resultFileNo){
		this.resultFileNo = resultFileNo;
	}
	public java.lang.Integer getLastUpd(){
		return lastUpd;
	}
	public void setLastUpd(java.lang.Integer lastUpd){
		this.lastUpd = lastUpd;
	}
	public java.lang.String getQuesId(){
		return quesId;
	}
	public void setQuesId(java.lang.String quesId){
		this.quesId = quesId;
	}
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
	public java.lang.String getResult(){
		return result;
	}
	public void setResult(java.lang.String result){
		this.result = result;
	}
	public java.lang.String getResultFileName(){
		return resultFileName;
	}
	public void setResultFileName(java.lang.String resultFileName){
		this.resultFileName = resultFileName;
	}
	public java.lang.Integer getUsrId(){
		return usrId;
	}
	public void setUsrId(java.lang.Integer usrId){
		this.usrId = usrId;
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
	public java.lang.String getIdtfRes(){
		return idtfRes;
	}
	public void setIdtfRes(java.lang.String idtfRes){
		this.idtfRes = idtfRes;
	}
	public java.lang.Integer getIdtfBy(){
		return idtfBy;
	}
	public void setIdtfBy(java.lang.Integer idtfBy){
		this.idtfBy = idtfBy;
	}
	public java.lang.String getIdtfResFileName(){
		return idtfResFileName;
	}
	public void setIdtfResFileName(java.lang.String idtfResFileName){
		this.idtfResFileName = idtfResFileName;
	}
	public java.lang.String getIdtfResFileNo(){
		return idtfResFileNo;
	}
	public void setIdtfResFileNo(java.lang.String idtfResFileNo){
		this.idtfResFileNo = idtfResFileNo;
	}
	public java.util.Date getStartIdtfDate(){
		return startIdtfDate;
	}
	public void setStartIdtfDate(java.util.Date startIdtfDate){
		this.startIdtfDate = startIdtfDate;
	}
	public java.util.Date getEndIdtfDate(){
		return endIdtfDate;
	}
	public void setEndIdtfDate(java.util.Date endIdtfDate){
		this.endIdtfDate = endIdtfDate;
	}
	public java.util.Date getIdtfDate(){
		return idtfDate;
	}
	public void setIdtfDate(java.util.Date idtfDate){
		this.idtfDate = idtfDate;
	}
	public java.util.Date getStartResultDate(){
		return startResultDate;
	}
	public void setStartResultDate(java.util.Date startResultDate){
		this.startResultDate = startResultDate;
	}
	public java.util.Date getEndResultDate(){
		return endResultDate;
	}
	public void setEndResultDate(java.util.Date endResultDate){
		this.endResultDate = endResultDate;
	}
	public java.util.Date getResultDate(){
		return resultDate;
	}
	public void setResultDate(java.util.Date resultDate){
		this.resultDate = resultDate;
	}
	public java.lang.Integer getId(){
		return id;
	}
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	public java.lang.String getRemarkFileNo(){
		return remarkFileNo;
	}
	public void setRemarkFileNo(java.lang.String remarkFileNo){
		this.remarkFileNo = remarkFileNo;
	}
	public java.lang.Integer getRespType(){
		return respType;
	}
	public void setRespType(java.lang.Integer respType){
		this.respType = respType;
	}
	public java.lang.String getRemarkFileName(){
		return remarkFileName;
	}
	public void setRemarkFileName(java.lang.String remarkFileName){
		this.remarkFileName = remarkFileName;
	}
	public java.lang.String getRemark(){
		return remark;
	}
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	public Integer getSolveNum() {
		return solveNum;
	}
	public void setSolveNum(Integer solveNum) {
		this.solveNum = solveNum;
	}
	@Override
	public String toString() {
		return "BasicQuesResp [lastUpdDate=" + lastUpdDate +", quesAnalysis =" 
				+ quesAnalysis +", startLastUpdDate=" + startLastUpdDate
				+ ", endLastUpdDate=" + endLastUpdDate + ", createBy="
				+ createBy + ", resultFileNo=" + resultFileNo + ", lastUpd="
				+ lastUpd + ", quesId=" + quesId + ", status=" + status
				+ ", result=" + result + ", resultFileName=" + resultFileName
				+ ", usrId=" + usrId + ", createDate=" + createDate
				+ ", startCreateDate=" + startCreateDate + ", endCreateDate="
				+ endCreateDate + ", idtfRes=" + idtfRes + ", idtfBy=" + idtfBy
				+ ", idtfResFileName=" + idtfResFileName + ", idtfResFileNo="
				+ idtfResFileNo + ", idtfDate=" + idtfDate + ", startIdtfDate="
				+ startIdtfDate + ", endIdtfDate=" + endIdtfDate
				+ ", resultDate=" + resultDate + ", startResultDate="
				+ startResultDate + ", endResultDate=" + endResultDate
				+ ", id=" + id + ", remarkFileNo=" + remarkFileNo
				+ ", respType=" + respType + ", remarkFileName="
				+ remarkFileName + ", remark=" + remark + ", rushDate="
				+ rushDate + ", solveNum=" + solveNum + "]";
	}

}