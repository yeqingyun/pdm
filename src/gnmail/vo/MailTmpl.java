package gnmail.vo;

public class MailTmpl extends BasicMailTmpl {
	public static final String ALL_FIELDS = "MailTmpl.TmplId,MailTmpl.CfgId,MailTmpl.UserId,MailTmpl.TmplName,MailTmpl.TmplText,MailTmpl.Type,MailTmpl.IsAuto,MailTmpl.AutoStart,MailTmpl.AutoCycle,MailTmpl.Status,MailTmpl.CreateBy,MailTmpl.CreateDate,MailTmpl.LastUpd,MailTmpl.LastUpdDate,MailCfg.CfgId as CfgId";
	public static final String LIST_FIELDS = "MailTmpl.TmplId,MailTmpl.CfgId,MailTmpl.UserId,MailTmpl.TmplName,MailTmpl.TmplText,MailTmpl.Type,MailTmpl.IsAuto,MailTmpl.AutoStart,MailTmpl.AutoCycle,MailTmpl.Status,MailTmpl.CreateBy,MailTmpl.CreateDate,MailTmpl.LastUpd,MailTmpl.LastUpdDate,MailCfg.CfgId as CfgId";

	private java.util.List<gnmail.vo.MailTmplG> mailTmplGs;

	private Integer cfgId;

	public java.util.List<gnmail.vo.MailTmplG> getMailTmplGs() {
		return mailTmplGs;
	}
	public void setMailTmplGs(java.util.List<gnmail.vo.MailTmplG> mailTmplGs){
		this.mailTmplGs = mailTmplGs;
	}
	public void addtoMailTmplGs(gnmail.vo.MailTmplG mailTmplG){
		if(getMailTmplGs() == null) setMailTmplGs(new java.util.ArrayList<gnmail.vo.MailTmplG>());
			getMailTmplGs().add(mailTmplG);
	}

	public Integer getCfgId(){
		return cfgId;
	}
	public void setCfgId(Integer cfgId){
		this.cfgId = cfgId;
	}

}