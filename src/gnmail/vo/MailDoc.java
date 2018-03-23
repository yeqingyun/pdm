package gnmail.vo;

public class MailDoc extends BasicMailDoc {
	public static final String ALL_FIELDS = "MailDoc.DocId,MailDoc.MailId,MailDoc.DocName,MailDoc.UriLink,Mail.MailId as MailId";
	public static final String LIST_FIELDS = "MailDoc.DocId,MailDoc.MailId,MailDoc.DocName,MailDoc.UriLink,Mail.MailId as MailId";


	private Integer mailId;


	public Integer getMailId(){
		return mailId;
	}
	public void setMailId(Integer mailId){
		this.mailId = mailId;
	}

}