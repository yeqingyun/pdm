package zrprjt.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicPrjtAuth {
	public static final String SELF_FIELDS = "PrjtAuth.RoleId,PrjtAuth.IsRead,PrjtAuth.IsLoad,PrjtAuth.Status,PrjtAuth.CreateBy,PrjtAuth.LastUpd,PrjtAuth.CreateDate,PrjtAuth.LastDate,PrjtAuth.PrjtNo";

	private java.lang.Integer roleId;
	private java.lang.Integer isRead;
	private java.lang.Integer isLoad;
	private java.lang.Integer status;
	private java.lang.Integer createBy;
	private java.lang.Integer lastUpd;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastDate;
	private java.util.Date startLastDate;
	private java.util.Date endLastDate;
	private java.lang.String prjtNo;

	public java.lang.Integer getRoleId(){
		return roleId;
	}
	public void setRoleId(java.lang.Integer roleId){
		this.roleId = roleId;
	}
	public java.lang.Integer getIsRead(){
		return isRead;
	}
	public void setIsRead(java.lang.Integer isRead){
		this.isRead = isRead;
	}
	public java.lang.Integer getIsLoad(){
		return isLoad;
	}
	public void setIsLoad(java.lang.Integer isLoad){
		this.isLoad = isLoad;
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
	public java.lang.String getPrjtNo(){
		return prjtNo;
	}
	public void setPrjtNo(java.lang.String prjtNo){
		this.prjtNo = prjtNo;
	}

}