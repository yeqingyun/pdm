package gnmail.vo;

public class MailCfg extends BasicMailCfg {
	public static final String ALL_FIELDS = "MailCfg.CfgId,MailCfg.UserId,MailCfg.MailAddr,MailCfg.Pwd,MailCfg.MailName,MailCfg.MailSign,MailCfg.ServIpAddr,MailCfg.Smpt,MailCfg.Pop3,MailCfg.Remark,MailCfg.Status,MailCfg.CreateBy,MailCfg.CreateDate,MailCfg.LastUpd,MailCfg.LastUpdDate";
	public static final String LIST_FIELDS = "MailCfg.CfgId,MailCfg.UserId,MailCfg.MailAddr,MailCfg.Pwd,MailCfg.MailName,MailCfg.MailSign,MailCfg.ServIpAddr,MailCfg.Smpt,MailCfg.Pop3,MailCfg.Remark,MailCfg.Status,MailCfg.CreateBy,MailCfg.CreateDate,MailCfg.LastUpd,MailCfg.LastUpdDate";

	private java.util.List<gnmail.vo.Mail> mails;
	private java.util.List<gnmail.vo.MailTmpl> mailTmpls;


	public java.util.List<gnmail.vo.Mail> getMails() {
		return mails;
	}
	public void setMails(java.util.List<gnmail.vo.Mail> mails){
		this.mails = mails;
	}
	public void addtoMails(gnmail.vo.Mail mail){
		if(getMails() == null) setMails(new java.util.ArrayList<gnmail.vo.Mail>());
			getMails().add(mail);
	}
	public java.util.List<gnmail.vo.MailTmpl> getMailTmpls() {
		return mailTmpls;
	}
	public void setMailTmpls(java.util.List<gnmail.vo.MailTmpl> mailTmpls){
		this.mailTmpls = mailTmpls;
	}
	public void addtoMailTmpls(gnmail.vo.MailTmpl mailTmpl){
		if(getMailTmpls() == null) setMailTmpls(new java.util.ArrayList<gnmail.vo.MailTmpl>());
			getMailTmpls().add(mailTmpl);
	}


}