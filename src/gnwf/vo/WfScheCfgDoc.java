package gnwf.vo;

import java.util.List;

import zrprjt.vo.PrjtRole;

@SuppressWarnings("serial")

public class WfScheCfgDoc extends BasicWfScheCfgDoc {

	public WfScheCfgDoc() {}
 
	public static final String ALL_FIELDS = "WfScheCfgDoc.DocId,WfScheCfgDoc.CfgId,WfScheCfgDoc.StepId,WfScheCfgDoc.DocName,WfScheCfgDoc.Sort,WfScheCfgDoc.IsMust,WfScheCfg.CfgName as CfgName";
	public static final String LIST_FIELDS = "WfScheCfgDoc.DocId,WfScheCfgDoc.CfgId,WfScheCfgDoc.StepId,WfScheCfgDoc.DocName,WfScheCfgDoc.Sort,WfScheCfgDoc.IsMust,WfScheCfg.CfgName as CfgName";
	public static final String XLS_FIELDS = "WfScheCfgDoc.DocId,WfScheCfgDoc.CfgId,WfScheCfgDoc.StepId,WfScheCfgDoc.DocName,WfScheCfgDoc.Sort,WfScheCfgDoc.IsMust,WfScheCfg.CfgName as CfgName";
	public static final String DOC_FIELDS="WfScheCfgDoc.DocId,WfScheCfgDoc.DocName,WfScheCfgDoc.UserRole,Usr.UsrName,Usr.Id";
	
	//,WfScheCfg.CfgName as CfgName
	private String cfgName;
	private String usrName;
	private Integer usrId;
	private List<PrjtRole> prjtRoles;

	public String getCfgName() {
		return cfgName;
	}
	public void setCfgName(String cfgName) {
		this.cfgName = cfgName;
	}
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public Integer getUsrId() {
		return usrId;
	}
	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}
	public List<PrjtRole> getPrjtRoles() {
		return prjtRoles;
	}
	public void setPrjtRoles(List<PrjtRole> prjtRoles) {
		this.prjtRoles = prjtRoles;
	}
}
