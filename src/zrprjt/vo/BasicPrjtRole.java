package zrprjt.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicPrjtRole {
	public static final String SELF_FIELDS = "PrjtRole.Sort,PrjtRole.RoleTyp,PrjtRole.RoleId,PrjtRole.Status,PrjtRole.CreateBy,PrjtRole.LastUpd,PrjtRole.CreateDate,PrjtRole.LastDate,PrjtRole.PrjtTypId,PrjtRole.RoleNm,PrjtRole.Remark,PrjtRole.IsRead";

	private java.lang.Integer roleId;
	private java.lang.Integer status;
	private java.lang.Integer isRead;
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
	//private java.lang.String prjtNo;
	private java.lang.String roleNm;
	private java.lang.String remark;
	
	private java.lang.Integer prjtTypId;
	
	private java.lang.Integer roleTyp;
	
	private java.lang.Integer  sort;

	public java.lang.Integer getRoleId(){
		return roleId;
	}
	public void setRoleId(java.lang.Integer roleId){
		this.roleId = roleId;
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
//	public java.lang.String getPrjtNo(){
//		return prjtNo;
//	}
//	public void setPrjtNo(java.lang.String prjtNo){
//		this.prjtNo = prjtNo;
//	}
	public java.lang.String getRoleNm(){
		return roleNm;
	}
	public void setRoleNm(java.lang.String roleNm){
		this.roleNm = roleNm;
	}
	public java.lang.String getRemark(){
		return remark;
	}
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	public java.lang.Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(java.lang.Integer isRead) {
		this.isRead = isRead;
	}
	public java.lang.Integer getPrjtTypId() {
		return prjtTypId;
	}
	public void setPrjtTypId(java.lang.Integer prjtTypId) {
		this.prjtTypId = prjtTypId;
	}
	public java.lang.Integer getRoleTyp() {
		return roleTyp;
	}
	public void setRoleTyp(java.lang.Integer roleTyp) {
		this.roleTyp = roleTyp;
	}
	public java.lang.Integer getSort() {
		return sort;
	}
	public void setSort(java.lang.Integer sort) {
		this.sort = sort;
	}

}