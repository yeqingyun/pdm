package gnwf.vo;

public class WfCfg extends BasicWfCfg {
	public static final String ALL_FIELDS = "WfCfg.NeedQues,WfCfg.FlowId,WfCfg.ComId,WfCfg.DeptId,WfCfg.CateId,WfCfg.IsFirstStep,WfCfg.Sphere,WfCfg.RelateId,WfCfg.Limit,WfCfg.Status,WfCfg.CreateBy,WfCfg.LastUpd,WfCfg.HasQuesMoudle,WfCfg.ScheCfgId,WfCfg.CreateDate,WfCfg.LastUpdDate,WfCfg.FlowDesc,WfCfg.FlowName,WfCfg.FlowUri,WfCfg.PrintUri,WfCfg.SpecialClass,WfCfg.AddRdExtendUri,WfCfg.FlowCode,WfCate.CateName as CateName,Com.ComNm,Dept.DeptNm";
	public static final String LIST_FIELDS = "WfCfg.NeedQues,WfCfg.FlowId,WfCfg.ComId,WfCfg.DeptId,WfCfg.CateId,WfCfg.IsFirstStep,WfCfg.Sphere,WfCfg.RelateId,WfCfg.Limit,WfCfg.Status,WfCfg.CreateBy,WfCfg.LastUpd,WfCfg.HasQuesMoudle,WfCfg.ScheCfgId,WfCfg.CreateDate,WfCfg.LastUpdDate,WfCfg.FlowDesc,WfCfg.FlowName,WfCfg.FlowUri,WfCfg.PrintUri,WfCfg.SpecialClass,WfCfg.AddRdExtendUri,WfCfg.FlowCode,WfCate.CateName as CateName,Com.ComNm,Dept.DeptNm";
	public static final String LIST = "WfCfg.NeedQues,WfCfg.FlowId,WfCfg.ComId,WfCfg.DeptId,WfCfg.CateId,WfCfg.IsFirstStep,WfCfg.Sphere,WfCfg.RelateId,WfCfg.Limit,WfCfg.Status,WfCfg.CreateBy,WfCfg.LastUpd,WfCfg.HasQuesMoudle,WfCfg.ScheCfgId,WfCfg.CreateDate,WfCfg.LastUpdDate,WfCfg.FlowDesc,WfCfg.FlowName,WfCfg.FlowUri,WfCfg.PrintUri,WfCfg.SpecialClass,WfCfg.AddRdExtendUri,WfCfg.FlowCode,WfCate.CateName ";

	private java.util.List<gnwf.vo.WfDept> wfDepts;
	private java.util.List<gnwf.vo.WfField> wfFields;
	private java.util.List<gnwf.vo.WfAgent> wfAgents;
	private java.util.List<gnwf.vo.WfLeader> wfLeaders;
	private java.util.List<gnwf.vo.WfStep> wfSteps;
	private java.util.List<gnwf.vo.WfCfgRelate> wfRelates;
	private java.util.List<gnwf.vo.WfJob> wfJobs;

	private String cateName;
	private String checked;
	private String comName;
	private String deptName;

	public java.util.List<gnwf.vo.WfDept> getWfDepts() {
		return wfDepts;
	}
	public void setWfDepts(java.util.List<gnwf.vo.WfDept> wfDepts){
		this.wfDepts = wfDepts;
	}
	public void addtoWfDepts(gnwf.vo.WfDept wfDept){
		if(getWfDepts() == null) setWfDepts(new java.util.ArrayList<gnwf.vo.WfDept>());
			getWfDepts().add(wfDept);
	}
	public java.util.List<gnwf.vo.WfField> getWfFields() {
		return wfFields;
	}
	public void setWfFields(java.util.List<gnwf.vo.WfField> wfFields){
		this.wfFields = wfFields;
	}
	public void addtoWfFields(gnwf.vo.WfField wfField){
		if(getWfFields() == null) setWfFields(new java.util.ArrayList<gnwf.vo.WfField>());
			getWfFields().add(wfField);
	}
	public java.util.List<gnwf.vo.WfAgent> getWfAgents() {
		return wfAgents;
	}
	public void setWfAgents(java.util.List<gnwf.vo.WfAgent> wfAgents){
		this.wfAgents = wfAgents;
	}
	public void addtoWfAgents(gnwf.vo.WfAgent wfAgent){
		if(getWfAgents() == null) setWfAgents(new java.util.ArrayList<gnwf.vo.WfAgent>());
			getWfAgents().add(wfAgent);
	}
	public java.util.List<gnwf.vo.WfLeader> getWfLeaders() {
		return wfLeaders;
	}
	public void setWfLeaders(java.util.List<gnwf.vo.WfLeader> wfLeaders){
		this.wfLeaders = wfLeaders;
	}
	public void addtoWfLeaders(gnwf.vo.WfLeader wfLeader){
		if(getWfLeaders() == null) setWfLeaders(new java.util.ArrayList<gnwf.vo.WfLeader>());
			getWfLeaders().add(wfLeader);
	}
	public java.util.List<gnwf.vo.WfStep> getWfSteps() {
		return wfSteps;
	}
	public void setWfSteps(java.util.List<gnwf.vo.WfStep> wfSteps){
		this.wfSteps = wfSteps;
	}
	public void addtoWfSteps(gnwf.vo.WfStep wfStep){
		if(getWfSteps() == null) setWfSteps(new java.util.ArrayList<gnwf.vo.WfStep>());
			getWfSteps().add(wfStep);
	}
	public java.util.List<gnwf.vo.WfCfgRelate> getWfRelates() {
		return wfRelates;
	}
	public void setWfRelates(java.util.List<gnwf.vo.WfCfgRelate> wfRelates) {
		this.wfRelates = wfRelates;
	}
	public void addtoWfRelates(gnwf.vo.WfCfgRelate wfRelate){
		if(getWfRelates() == null) setWfRelates(new java.util.ArrayList<gnwf.vo.WfCfgRelate>());
			getWfRelates().add(wfRelate);
	}
	public java.util.List<gnwf.vo.WfJob> getWfJobs() {
		return wfJobs;
	}
	public void setWfJobs(java.util.List<gnwf.vo.WfJob> wfJobs){
		this.wfJobs = wfJobs;
	}
	public void addtoWfJobs(gnwf.vo.WfJob wfJob){
		if(getWfJobs() == null) setWfJobs(new java.util.ArrayList<gnwf.vo.WfJob>());
			getWfJobs().add(wfJob);
	}
	public String getCateName(){
		return cateName;
	}
	public void setCateName(String cateName){
		this.cateName = cateName;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}