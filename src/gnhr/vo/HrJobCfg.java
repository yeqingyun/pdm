package gnhr.vo;

public class HrJobCfg extends BasicHrJobCfg {
	public static final String ALL_FIELDS = "HrJobCfg.JobId,HrJobCfg.ComId,HrJobCfg.DeptId,HrJobCfg.JobName,HrJobCfg.DefQty,HrJobCfg.FactQty,HrJobCfg.Remark,HrJobCfg.Status,HrJobCfg.CreateBy,HrJobCfg.CreateDate,HrJobCfg.LastUpd,HrJobCfg.LastUpdDate,Dept.DeptNm,Com.ComNm";
	public static final String LIST_FIELDS = "HrJobCfg.JobId,HrJobCfg.ComId,HrJobCfg.DeptId,HrJobCfg.JobName,HrJobCfg.DefQty,HrJobCfg.FactQty,HrJobCfg.Remark,HrJobCfg.Status,HrJobCfg.CreateBy,HrJobCfg.CreateDate,HrJobCfg.LastUpd,HrJobCfg.LastUpdDate,Dept.DeptNm,Com.ComNm";

	private String deptNm;
	private String comNm;
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	public String getComNm() {
		return comNm;
	}
	public void setComNm(String comNm) {
		this.comNm = comNm;
	}


}