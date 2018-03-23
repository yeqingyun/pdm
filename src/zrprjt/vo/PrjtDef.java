package zrprjt.vo;

import gnwf.vo.WfDoc;

public class PrjtDef extends BasicPrjtDef {
	public static final String ALL_FIELDS = "PrjtDef.PrjtPointVersion,PrjtDef.NextPoint,PrjtDef.CurrentPoint,PrjtDef.DevDeptNameID,PrjtDef.PrjtDefDocFileNo,PrjtDef.PrjtDefDocFileName,PrjtDef.PrjtTaskFileNo,PrjtDef.PrjtTaskFileName,PrjtDef.TaskVersion,PrjtDef.TypId,PrjtDef.Leve,PrjtDef.Scope,PrjtDef.Status,PrjtDef.CreateBy,PrjtDef.LastUpd,PrjtDef.PlanStaDate,PrjtDef.PlanOveDate,PrjtDef.StaDate,PrjtDef.OveDate,PrjtDef.CreateDate,PrjtDef.LastDate,PrjtDef.PrjtNo,PrjtDef.PrjtNm,PrjtDef.Remark,PrjtDef.Perce,PrjtTyp.TypNm as projtTypeNm";
	public static final String LIST_FIELDS = "PrjtDef.PrjtPointVersion,PrjtDef.NextPoint,PrjtDef.CurrentPoint,PrjtDef.DevDeptNameID,PrjtDef.PrjtDefDocFileName,PrjtDef.PrjtTaskFileNo,PrjtDef.PrjtTaskFileName,PrjtDef.TaskVersion,PrjtDef.TypId,PrjtDef.Leve,PrjtDef.Scope,PrjtDef.Status,PrjtDef.CreateBy,PrjtDef.LastUpd,PrjtDef.PlanStaDate,PrjtDef.PlanOveDate,PrjtDef.StaDate,PrjtDef.OveDate,PrjtDef.CreateDate,PrjtDef.LastDate,PrjtDef.PrjtNo,PrjtDef.PrjtNm,PrjtDef.Remark,PrjtDef.Perce,PrjtTyp.TypNm as projtTypeNm";

	private java.util.List<zrprjt.vo.PrjtAuth> prjtAuths;
	private java.util.List<zrprjt.vo.PrjtRole> prjtRoles;
	private java.util.List<zrprjt.vo.PrjtUsr> prjtUsrs;
	private java.util.List<zrprjt.vo.Task> tasks;
	
	private java.util.List<zrprjt.vo.PrjtPoint> prjtPoints;
	
	private String projtTypeNm;
	
	private String iconUri;
	
	private Integer pmUsrId;
	
	private WfDoc wfDoc;
	
	private String PrjtTaskFileIcon;
	private String PrjtDefDocFileIcon;
	private Integer DevDeptNameID;
	
	
	
	public Integer getDevDeptNameID() {
		return DevDeptNameID;
	}
	public void setDevDeptNameID(Integer devDeptNameID) {
		DevDeptNameID = devDeptNameID;
	}
	public java.util.List<zrprjt.vo.PrjtAuth> getPrjtAuths() {
		return prjtAuths;
	}
	public void setPrjtAuths(java.util.List<zrprjt.vo.PrjtAuth> prjtAuths){
		this.prjtAuths = prjtAuths;
	}
	public void addtoPrjtAuths(zrprjt.vo.PrjtAuth prjtAuth){
		if(getPrjtAuths() == null) setPrjtAuths(new java.util.ArrayList<zrprjt.vo.PrjtAuth>());
			getPrjtAuths().add(prjtAuth);
	}
	public java.util.List<zrprjt.vo.PrjtRole> getPrjtRoles() {
		return prjtRoles;
	}
	public void setPrjtRoles(java.util.List<zrprjt.vo.PrjtRole> prjtRoles){
		this.prjtRoles = prjtRoles;
	}
	public void addtoPrjtRoles(zrprjt.vo.PrjtRole prjtRole){
		if(getPrjtRoles() == null) setPrjtRoles(new java.util.ArrayList<zrprjt.vo.PrjtRole>());
			getPrjtRoles().add(prjtRole);
	}
	public java.util.List<zrprjt.vo.PrjtUsr> getPrjtUsrs() {
		return prjtUsrs;
	}
	public void setPrjtUsrs(java.util.List<zrprjt.vo.PrjtUsr> prjtUsrs){
		this.prjtUsrs = prjtUsrs;
	}
	public void addtoPrjtUsrs(zrprjt.vo.PrjtUsr prjtUsr){
		if(getPrjtUsrs() == null) setPrjtUsrs(new java.util.ArrayList<zrprjt.vo.PrjtUsr>());
			getPrjtUsrs().add(prjtUsr);
	}
	public java.util.List<zrprjt.vo.Task> getTasks() {
		return tasks;
	}
	public void setTasks(java.util.List<zrprjt.vo.Task> tasks){
		this.tasks = tasks;
	}
	public void addtoTasks(zrprjt.vo.Task task){
		if(getTasks() == null) setTasks(new java.util.ArrayList<zrprjt.vo.Task>());
			getTasks().add(task);
	}
	public void setProjtTypeNm(String projtTypeNm) {
		this.projtTypeNm = projtTypeNm;
	}
	public String getProjtTypeNm() {
		return projtTypeNm;
	}
	public String getIconUri() {
		return iconUri;
	}
	public void setIconUri(String iconUri) {
		this.iconUri = iconUri;
	}
	public Integer getPmUsrId() {
		return pmUsrId;
	}
	public void setPmUsrId(Integer pmUsrId) {
		this.pmUsrId = pmUsrId;
	}
	public WfDoc getWfDoc() {
		return wfDoc;
	}
	public void setWfDoc(WfDoc wfDoc) {
		this.wfDoc = wfDoc;
	}
	public String getPrjtTaskFileIcon() {
		return PrjtTaskFileIcon;
	}
	public void setPrjtTaskFileIcon(String prjtTaskFileIcon) {
		PrjtTaskFileIcon = prjtTaskFileIcon;
	}
	public String getPrjtDefDocFileIcon() {
		return PrjtDefDocFileIcon;
	}
	public void setPrjtDefDocFileIcon(String prjtDefDocFileIcon) {
		PrjtDefDocFileIcon = prjtDefDocFileIcon;
	}
	public java.util.List<zrprjt.vo.PrjtPoint> getPrjtPoints() {
		return prjtPoints;
	}
	public void setPrjtPoints(java.util.List<zrprjt.vo.PrjtPoint> prjtPoints) {
		this.prjtPoints = prjtPoints;
	}


}