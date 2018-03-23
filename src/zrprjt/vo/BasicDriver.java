package zrprjt.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicDriver {
	public static final String SELF_FIELDS = "Driver.DriveId,Driver.DriveNo,Driver.DriveNm,Driver.FlowId,Driver.Remark,Driver.Status,Driver.CreateBy,Driver.CreateDate,Driver.LastUpd,Driver.LastDate,Driver.StepId";

	private java.lang.Integer driveId;
	private java.lang.String driveNo;
	private java.lang.String driveNm;
	private java.lang.Integer flowId;
	private java.lang.Integer stepId;
	private java.lang.String remark;
	private java.lang.Integer status;
	private java.lang.Integer createBy;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	private java.lang.Integer lastUpd;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastDate;
	private java.util.Date startLastDate;
	private java.util.Date endLastDate;

	public java.lang.Integer getDriveId(){
		return driveId;
	}
	public void setDriveId(java.lang.Integer driveId){
		this.driveId = driveId;
	}
	public java.lang.String getDriveNo(){
		return driveNo;
	}
	public void setDriveNo(java.lang.String driveNo){
		this.driveNo = driveNo;
	}
	public java.lang.String getDriveNm(){
		return driveNm;
	}
	public void setDriveNm(java.lang.String driveNm){
		this.driveNm = driveNm;
	}
	public java.lang.Integer getFlowId(){
		return flowId;
	}
	public void setFlowId(java.lang.Integer flowId){
		this.flowId = flowId;
	}
	public java.lang.String getRemark(){
		return remark;
	}
	public void setRemark(java.lang.String remark){
		this.remark = remark;
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
	public java.lang.Integer getLastUpd(){
		return lastUpd;
	}
	public void setLastUpd(java.lang.Integer lastUpd){
		this.lastUpd = lastUpd;
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
	public java.lang.Integer getStepId() {
		return stepId;
	}
	public void setStepId(java.lang.Integer stepId) {
		this.stepId = stepId;
	}

}