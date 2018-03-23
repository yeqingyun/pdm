package zrprjt.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicPrjtUsr {
	public static final String SELF_FIELDS = "PrjtUsr.Id,PrjtUsr.PrjtTypId,PrjtUsr.RoleId,PrjtUsr.UsrId,PrjtUsr.Status,PrjtUsr.CreateBy,PrjtUsr.LastUpd,PrjtUsr.CreateDate,PrjtUsr.LastDate,PrjtUsr.PrjtNo,PrjtUsr.Priority";

	private java.lang.Integer id;
	private java.lang.Integer roleId;
	private java.lang.Integer usrId;
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
	
	private java.lang.Integer priority;
	private java.lang.Integer prjtTypId;
//	private java.lang.Integer  usrType;

	public java.lang.Integer getRoleId(){
		return roleId;
	}
	public void setRoleId(java.lang.Integer roleId){
		this.roleId = roleId;
	}
	public java.lang.Integer getUsrId(){
		return usrId;
	}
	public void setUsrId(java.lang.Integer usrId){
		this.usrId = usrId;
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
	public java.lang.Integer getPriority() {
		return priority;
	}
	public void setPriority(java.lang.Integer priority) {
		this.priority = priority;
	}
//	public java.lang.Integer  getUsrType() {
//		return usrType;
//	}
//	public void setUsrType(java.lang.Integer  usrType) {
//		this.usrType = usrType;
//	}
	public java.lang.Integer getPrjtTypId() {
		return prjtTypId;
	}
	public void setPrjtTypId(java.lang.Integer prjtTypId) {
		this.prjtTypId = prjtTypId;
	}
	public java.lang.Integer getId() {
		return id;
	}
	public void setId(java.lang.Integer id) {
		this.id = id;
	}

}