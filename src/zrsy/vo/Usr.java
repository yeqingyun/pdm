package zrsy.vo;

public class Usr extends BasicUsr {
	public static final String ALL_FIELDS = "Usr.Id,Usr.ComId,Usr.DeptId,Usr.OrgNo,Usr.Status,Usr.CreateBy,Usr.LastUpd,Usr.CreateDate,Usr.LastDate,Usr.Login,Usr.Pwd,Usr.UsrNo,Usr.UsrName,Usr.Email,Usr.Remark,Usr.IsWide,Com.ComNm as ComNm,Dept.DeptNm as DeptNm";
	public static final String LIST_FIELDS = "Usr.Id,Usr.ComId,Usr.DeptId,Usr.OrgNo,Usr.Status,Usr.CreateBy,Usr.LastUpd,Usr.CreateDate,Usr.LastDate,Usr.Login,Usr.Pwd,Usr.UsrNo,Usr.UsrName,Usr.Email,Usr.Remark,Usr.IsWide,Com.ComNm as ComNm,Dept.DeptNm as DeptNm";

	private java.util.List<zrsy.vo.GpUsr> gpUsrs;
	private java.util.List<zrsy.vo.Gp> gps;
	private java.util.List<zrsy.vo.Sco> scos;
	private java.util.List<zrsy.vo.UsrSco> usrScos;
	private java.lang.String comNm;
	private java.lang.String deptNm;
	
	private Integer empId;
	private String derictDepts;
    private String masterDepts;
    private String coms;
    
	private Integer authType;
	private String ischecked;
    
	private Integer stepId;		//工作流步骤id
	private Integer taskType;	//工作流任务类型
	private String email;
	private Integer syId;
	private String checked;
	
	private String gpNames;
	
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	private java.lang.String syNm;
	
	public java.lang.String getSyNm() {
		return syNm;
	}
	public void setSyNm(java.lang.String syNm) {
		this.syNm = syNm;
	}
	public Integer getSyId() {
		return syId;
	}
	public void setSyId(Integer syId) {
		this.syId = syId;
	}
	public java.util.List<zrsy.vo.GpUsr> getGpUsrs() {
		return gpUsrs;
	}
	public void setGpUsrs(java.util.List<zrsy.vo.GpUsr> gpUsrs){
		this.gpUsrs = gpUsrs;
	}
	public java.util.List<zrsy.vo.Gp> getGps() {
		return gps;
	}
	public void setGps(java.util.List<zrsy.vo.Gp> gps) {
		this.gps = gps;
	}
	public void addtoGps(zrsy.vo.Gp gp){
		if(getGps() == null) setGps(new java.util.ArrayList<zrsy.vo.Gp>());
			getGps().add(gp);
	}
	public void addtoGpUsrs(zrsy.vo.GpUsr gpUsr){
		if(getGpUsrs() == null) setGpUsrs(new java.util.ArrayList<zrsy.vo.GpUsr>());
			getGpUsrs().add(gpUsr);
	}

	public java.lang.String getComNm(){
		return comNm;
	}
	public void setComNm(java.lang.String comNm){
		this.comNm = comNm;
	}
	public java.lang.String getDeptNm(){
		return deptNm;
	}
	public void setDeptNm(java.lang.String deptNm){
		this.deptNm = deptNm;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getDerictDepts() {
		return derictDepts;
	}
	public void setDerictDepts(String derictDepts) {
		this.derictDepts = derictDepts;
	}
	public String getMasterDepts() {
		return masterDepts;
	}
	public void setMasterDepts(String masterDepts) {
		this.masterDepts = masterDepts;
	}
	public String getComs() {
		return coms;
	}
	public void setComs(String coms) {
		this.coms = coms;
	}
	public Integer getAuthType() {
		return authType;
	}
	public void setAuthType(Integer authType) {
		this.authType = authType;
	}
	public String getIschecked() {
		return ischecked;
	}
	public void setIschecked(String ischecked) {
		this.ischecked = ischecked;
	}
	public Integer getStepId() {
		return stepId;
	}
	public void setStepId(Integer stepId) {
		this.stepId = stepId;
	}
	public Integer getTaskType() {
		return taskType;
	}
	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public java.util.List<zrsy.vo.Sco> getScos() {
		return scos;
	}
	public void setScos(java.util.List<zrsy.vo.Sco> scos) {
		this.scos = scos;
	}
	public void addtoScos(zrsy.vo.Sco sco){
		if(getScos() == null) setScos(new java.util.ArrayList<zrsy.vo.Sco>());
			getScos().add(sco);
	}
	public void addtoUsrScos(zrsy.vo.UsrSco usrSco){
		if(getUsrScos() == null) setUsrScos(new java.util.ArrayList<zrsy.vo.UsrSco>());
		getUsrScos().add(usrSco);
	}
	public java.util.List<zrsy.vo.UsrSco> getUsrScos() {
		return usrScos;
	}
	public void setUsrScos(java.util.List<zrsy.vo.UsrSco> usrScos) {
		this.usrScos = usrScos;
	}
	public String getGpNames() {
		return gpNames;
	}
	public void setGpNames(String gpNames) {
		this.gpNames = gpNames;
	}
}