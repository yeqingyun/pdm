package gnwf.vo;

@SuppressWarnings("serial")

public class Mail1 extends BasicMail {

	public Mail1() {}
 
	public static final String ALL_FIELDS = "Mail.MailId,Mail.CfgId,Mail.OexId,Mail.SenderId,Mail.Sender,Mail.AcceptId,Mail.Accept,Mail.Title,Mail.MailText,Mail.MailDate,Mail.Remark,Mail.Status,Mail.CreateBy,Mail.CreateDate,Mail.LastUpd,Mail.LastUpdDate";
	public static final String LIST_FIELDS = "Mail.MailId,Mail.CfgId,Mail.OexId,Mail.SenderId,Mail.Sender,Mail.AcceptId,Mail.Accept,Mail.Title,Mail.MailText,Mail.MailDate,Mail.Remark,Mail.Status,Mail.CreateBy,Mail.CreateDate,Mail.LastUpd,Mail.LastUpdDate";
	public static final String XLS_FIELDS = "Mail.MailId,Mail.CfgId,Mail.OexId,Mail.SenderId,Mail.Sender,Mail.AcceptId,Mail.Accept,Mail.Title,Mail.MailText,Mail.MailDate,Mail.Remark,Mail.Status,Mail.CreateBy,Mail.CreateDate,Mail.LastUpd,Mail.LastUpdDate";
	
	private String to;
	private String cc;
	
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
