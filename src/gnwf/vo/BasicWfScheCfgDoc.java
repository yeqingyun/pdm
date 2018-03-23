package gnwf.vo;
import org.frm.vo.BasicVO;
@SuppressWarnings("serial")
public class BasicWfScheCfgDoc extends BasicVO {
	public static final String SELF_FIELDS = "WfScheCfgDoc.DocId,WfScheCfgDoc.CfgId,WfScheCfgDoc.StepId,WfScheCfgDoc.DocName,WfScheCfgDoc.Sort,WfScheCfgDoc.IsMust,WfScheCfgDoc.UserRole";
	private java.lang.Integer docId;
	private java.lang.Integer cfgId;
	private java.lang.Integer stepId;
	private java.lang.String docName;
	private java.lang.Integer sort;
	private java.lang.Integer isMust;
	private java.lang.Integer userRole;

	public java.lang.Integer getDocId() {
		return docId;
	}
	public void setDocId(java.lang.Integer docId) {
		this.docId =  docId;
	}
	public java.lang.Integer getCfgId() {
		return cfgId;
	}
	public void setCfgId(java.lang.Integer cfgId) {
		this.cfgId =  cfgId;
	}
	public java.lang.Integer getStepId() {
		return stepId;
	}
	public void setStepId(java.lang.Integer stepId) {
		this.stepId = stepId;
	}
	public java.lang.String getDocName() {
		return docName;
	}
	public void setDocName(java.lang.String docName) {
		this.docName =  docName;
	}
	public java.lang.Integer getSort() {
		return sort;
	}
	public void setSort(java.lang.Integer sort) {
		this.sort =  sort;
	}
	public java.lang.Integer getIsMust() {
		return isMust;
	}
	public void setIsMust(java.lang.Integer isMust) {
		this.isMust = isMust;
	}
	public java.lang.Integer getUserRole() {
		return userRole;
	}
	public void setUserRole(java.lang.Integer userRole) {
		this.userRole = userRole;
	}
	
}
