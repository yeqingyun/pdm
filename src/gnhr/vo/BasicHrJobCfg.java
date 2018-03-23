package gnhr.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicHrJobCfg {
	public static final String SELF_FIELDS = "HrJobCfg.JobId,HrJobCfg.ComId,HrJobCfg.DeptId,HrJobCfg.JobName,HrJobCfg.DefQty,HrJobCfg.FactQty,HrJobCfg.Remark,HrJobCfg.Status,HrJobCfg.CreateBy,HrJobCfg.CreateDate,HrJobCfg.LastUpd,HrJobCfg.LastUpdDate";

	private java.lang.Integer jobId;
	private java.lang.Integer comId;
	private java.lang.Integer deptId;
	private java.lang.String jobName;
	private java.lang.Integer defQty;
	private java.lang.Integer factQty;
	private java.lang.String remark;
	private java.lang.Integer status;
	private java.lang.Integer createBy;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	private java.lang.Integer lastUpd;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastUpdDate;
	private java.util.Date startLastUpdDate;
	private java.util.Date endLastUpdDate;

	public java.lang.Integer getJobId(){
		return jobId;
	}
	public void setJobId(java.lang.Integer jobId){
		this.jobId = jobId;
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
	public java.lang.String getJobName(){
		return jobName;
	}
	public void setJobName(java.lang.String jobName){
		this.jobName = jobName;
	}
	public java.lang.Integer getDefQty(){
		return defQty;
	}
	public void setDefQty(java.lang.Integer defQty){
		this.defQty = defQty;
	}
	public java.lang.Integer getFactQty(){
		return factQty;
	}
	public void setFactQty(java.lang.Integer factQty){
		this.factQty = factQty;
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

}