package gnwf.vo;

public class CQDefect extends BasicCQDefect {
	private static final long serialVersionUID = -7659145147603195723L;
	
	
	public static final String LIST_FIELDS = SELF_FIELDS + 
			",(select s.name from Statedef s where Defect.State = s.id) AS Statename" + 
			",(select p.Prjname from Project p where p.dbid = Defect.Project) AS PrjName" +
			",(select u.Login_name from Users u where u.Dbid = Defect.Owner) AS Login_name" + 
			",(select p.Platform from Project p where p.dbid = Defect.Project) AS Platform" + 
			",(select u.Login_name from Users u where u.Dbid = Defect.Sw_leader) AS Sw_leader_name" + 
			",(select u.Login_name from Users u where u.Dbid = Defect.Submitter) AS Submitter_name" +
			",(select u.Login_name from Users u where u.Dbid = Defect.Sqa) AS Sqa_name" + 
			",(select u.Login_name from Users u where u.Dbid = Defect.Drvleader) AS Drvleader_name" + 
			",(select u.Phone from Users u where u.Dbid = Defect.Owner) AS Owner_phone";
			
	private String statename;
	private String prjName;
	private String login_name;
	private String platform;
	private String sw_leader_name;
	private String submitter_name;
	
	private String sqa_name;
	private String drvleader_name;
	private String owner_phone;
	public String getStatename() {
		return statename;
	}
	public void setStatename(String statename) {
		this.statename = statename;
	}
	public String getPrjName() {
		return prjName;
	}
	public void setPrjName(String prjName) {
		this.prjName = prjName;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getSw_leader_name() {
		return sw_leader_name;
	}
	public void setSw_leader_name(String sw_leader_name) {
		this.sw_leader_name = sw_leader_name;
	}
	public String getSubmitter_name() {
		return submitter_name;
	}
	public void setSubmitter_name(String submitter_name) {
		this.submitter_name = submitter_name;
	}
	public String getSqa_name() {
		return sqa_name;
	}
	public void setSqa_name(String sqa_name) {
		this.sqa_name = sqa_name;
	}
	public String getDrvleader_name() {
		return drvleader_name;
	}
	public void setDrvleader_name(String drvleader_name) {
		this.drvleader_name = drvleader_name;
	}
	public String getOwner_phone() {
		return owner_phone;
	}
	public void setOwner_phone(String owner_phone) {
		this.owner_phone = owner_phone;
	}
	@Override
	public String toString() {
		return super.toString() + "CQDefect [statename=" + statename + ", prjName=" + prjName
				+ ", login_name=" + login_name + ", platform=" + platform
				+ ", sw_leader_name=" + sw_leader_name + ", submitter_name="
				+ submitter_name + ", sqa_name=" + sqa_name
				+ ", drvleader_name=" + drvleader_name + ", owner_phone="
				+ owner_phone + "]";
	}
}
