package gnmail.vo;

public class Mail extends BasicMail {
	public static final String ALL_FIELDS = "Mail.MailId,Mail.CfgId,Mail.OexId,Mail.SenderId,Mail.Sender,Mail.AcceptId,Mail.Accept,Mail.Title,Mail.MailText,Mail.MailDate,Mail.Remark,Mail.Status,Mail.CreateBy,Mail.CreateDate,Mail.LastUpd,Mail.LastUpdDate,MailCfg.CfgId as CfgId";
	public static final String LIST_FIELDS = "Mail.MailId,Mail.CfgId,Mail.OexId,Mail.SenderId,Mail.Sender,Mail.AcceptId,Mail.Accept,Mail.Title,Mail.MailText,Mail.MailDate,Mail.Remark,Mail.Status,Mail.CreateBy,Mail.CreateDate,Mail.LastUpd,Mail.LastUpdDate,MailCfg.CfgId as CfgId";

	private java.util.List<gnmail.vo.MailAutoM> mailAutoMs;
	private java.util.List<gnmail.vo.MailDoc> mailDocs;

	private Integer cfgId;
	
	private String to;
	private String cc;

	public java.util.List<gnmail.vo.MailAutoM> getMailAutoMs() {
		return mailAutoMs;
	}
	public void setMailAutoMs(java.util.List<gnmail.vo.MailAutoM> mailAutoMs){
		this.mailAutoMs = mailAutoMs;
	}
	public void addtoMailAutoMs(gnmail.vo.MailAutoM mailAutoM){
		if(getMailAutoMs() == null) setMailAutoMs(new java.util.ArrayList<gnmail.vo.MailAutoM>());
			getMailAutoMs().add(mailAutoM);
	}
	public java.util.List<gnmail.vo.MailDoc> getMailDocs() {
		return mailDocs;
	}
	public void setMailDocs(java.util.List<gnmail.vo.MailDoc> mailDocs){
		this.mailDocs = mailDocs;
	}
	public void addtoMailDocs(gnmail.vo.MailDoc mailDoc){
		if(getMailDocs() == null) setMailDocs(new java.util.ArrayList<gnmail.vo.MailDoc>());
			getMailDocs().add(mailDoc);
	}

	public Integer getCfgId(){
		return cfgId;
	}
	public void setCfgId(Integer cfgId){
		this.cfgId = cfgId;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}

}