package zrsy.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicUsr {
	public static final String SELF_FIELDS = "Usr.Id,Usr.ComId,Usr.DeptId,Usr.OrgNo,Usr.Login,Usr.Pwd,Usr.UsrNo,Usr.UsrName,Usr.Email,Usr.Status,Usr.Remark,Usr.CreateBy,Usr.CreateDate,Usr.LastUpd,Usr.LastDate,Usr.IsWide";

	public static final String SELF_DEPT_FIELDS = "Usr.Id,Usr.ComId,Usr.DeptId,Usr.OrgNo,Usr.Login,Usr.Pwd,Usr.UsrNo,Usr.UsrName,Usr.Email,Usr.Status,Usr.Remark,Usr.CreateBy,Usr.CreateDate,Usr.LastUpd,Usr.LastDate,Usr.IsWide,Dept.deptNm as deptNm";

	private java.lang.Integer id;
	private java.lang.Integer comId;
	private java.lang.Integer deptId;
	private java.lang.Integer orgNo;
	private java.lang.String login;
	private java.lang.String pwd;
	private java.lang.String usrNo;
	private java.lang.String usrName;
	private java.lang.String email;
	private java.lang.Integer status;
	private java.lang.String remark;
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
	private java.lang.Integer isWide;

	private java.lang.String deptNm;
	
	
	public java.lang.String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(java.lang.String deptNm) {
		this.deptNm = deptNm;
	}
	public java.lang.Integer getOrgNo() {
		return orgNo;
	}
	public void setOrgNo(java.lang.Integer orgNo) {
		this.orgNo = orgNo;
	}
	public java.lang.Integer getId(){
		return id;
	}
	public void setId(java.lang.Integer id){
		this.id = id;
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
	public java.lang.String getLogin(){
		return login;
	}
	public void setLogin(java.lang.String login){
		this.login = login;
	}
	public java.lang.String getPwd(){
		return pwd;
	}
	public void setPwd(java.lang.String pwd){
		this.pwd = pwd;
	}
	public java.lang.String getUsrNo(){
		return usrNo;
	}
	public void setUsrNo(java.lang.String usrNo){
		this.usrNo = usrNo;
	}
	public java.lang.String getUsrName(){
		return usrName;
	}
	public void setUsrName(java.lang.String usrName){
		this.usrName = usrName;
	}
	public java.lang.String getEmail(){
		return email;
	}
	public void setEmail(java.lang.String email){
		this.email = email;
	}
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
	public java.lang.String getRemark(){
		return remark;
	}
	public void setRemark(java.lang.String remark){
		this.remark = remark;
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
	public java.lang.Integer getIsWide(){
		return isWide;
	}
	public void setIsWide(java.lang.Integer isWide){
		this.isWide = isWide;
	}

}