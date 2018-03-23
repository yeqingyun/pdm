package zrprjt.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicDriverRd {
	public static final String SELF_FIELDS = "DriverRd.LogId,DriverRd.DriverId,DriverRd.WfNo,DriverRd.DriverDate,DriverRd.Remark,DriverRd.Status,DriverRd.CreateBy,DriverRd.CreateDate,DriverRd.LastUpd,DriverRd.LastDate";

	private java.lang.Integer logId;
	private java.lang.Integer driverId;
	private java.lang.String wfNo;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date driverDate;
	private java.util.Date startDriverDate;
	private java.util.Date endDriverDate;
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

	public java.lang.Integer getLogId(){
		return logId;
	}
	public void setLogId(java.lang.Integer logId){
		this.logId = logId;
	}
	public java.lang.Integer getDriverId(){
		return driverId;
	}
	public void setDriverId(java.lang.Integer driverId){
		this.driverId = driverId;
	}
	public java.lang.String getWfNo(){
		return wfNo;
	}
	public void setWfNo(java.lang.String wfNo){
		this.wfNo = wfNo;
	}
	public java.util.Date getStartDriverDate(){
		return startDriverDate;
	}
	public void setStartDriverDate(java.util.Date startDriverDate){
		this.startDriverDate = startDriverDate;
	}
	public java.util.Date getEndDriverDate(){
		return endDriverDate;
	}
	public void setEndDriverDate(java.util.Date endDriverDate){
		this.endDriverDate = endDriverDate;
	}
	public java.util.Date getDriverDate(){
		return driverDate;
	}
	public void setDriverDate(java.util.Date driverDate){
		this.driverDate = driverDate;
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

}