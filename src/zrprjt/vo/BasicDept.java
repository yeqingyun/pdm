package zrprjt.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicDept {
	public static final String SELF_FIELDS = "Dept.DeptId,Dept.ComId,Dept.Parent,Dept.Leve,Dept.Status,Dept.CreateBy,Dept.LastUpd,Dept.CreateDate,Dept.LastDate,Dept.DeptNo,Dept.DeptNm,Dept.Remark";

	private java.lang.Integer deptId;
	private java.lang.Integer comId;
	private java.lang.Integer parent;
	private java.lang.Integer leve;
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
	private java.lang.String deptNo;
	private java.lang.String deptNm;
	private java.lang.String remark;

	public java.lang.Integer getDeptId(){
		return deptId;
	}
	public void setDeptId(java.lang.Integer deptId){
		this.deptId = deptId;
	}
	public java.lang.Integer getComId(){
		return comId;
	}
	public void setComId(java.lang.Integer comId){
		this.comId = comId;
	}
	public java.lang.Integer getParent(){
		return parent;
	}
	public void setParent(java.lang.Integer parent){
		this.parent = parent;
	}
	public java.lang.Integer getLeve(){
		return leve;
	}
	public void setLeve(java.lang.Integer leve){
		this.leve = leve;
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
	public java.lang.String getDeptNo(){
		return deptNo;
	}
	public void setDeptNo(java.lang.String deptNo){
		this.deptNo = deptNo;
	}
	public java.lang.String getDeptNm(){
		return deptNm;
	}
	public void setDeptNm(java.lang.String deptNm){
		this.deptNm = deptNm;
	}
	public java.lang.String getRemark(){
		return remark;
	}
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}

}