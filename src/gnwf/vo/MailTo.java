package gnwf.vo;

@SuppressWarnings("serial")

public class MailTo extends BasicMailTo {

	public MailTo() {}
 
	public static final String ALL_FIELDS = "MailTo.MailId,MailTo.ToId,MailTo.ToMail,MailTo.ToType,MailTo.ToName";
	public static final String LIST_FIELDS = "MailTo.MailId,MailTo.ToId,MailTo.ToMail,MailTo.ToType,MailTo.ToName";
	public static final String XLS_FIELDS = "MailTo.MailId,MailTo.ToId,MailTo.ToMail,MailTo.ToType,MailTo.ToName";
}
