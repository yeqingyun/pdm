package gnmail.vo;

public class MailAutoM extends BasicMailAutoM {
	public static final String ALL_FIELDS = "MailAutoM.MailId,MailAutoM.WfNO,MailAutoM.TaskId,MailAutoM.CreateBy,MailAutoM.CreateDate,MailAutoM.AcceptBy,MailAutoM.AcceptDate,MailAutoM.Title,MailAutoM.MailText,MailAutoM.TaskUri,MailAutoM.Status,MailAutoM.Remark,Mail.MailId as MailId";
	public static final String LIST_FIELDS = "MailAutoM.MailId,MailAutoM.WfNO,MailAutoM.TaskId,MailAutoM.CreateBy,MailAutoM.CreateDate,MailAutoM.AcceptBy,MailAutoM.AcceptDate,MailAutoM.Title,MailAutoM.MailText,MailAutoM.TaskUri,MailAutoM.Status,MailAutoM.Remark,Mail.MailId as MailId";


	private Integer mailId;


	public Integer getMailId(){
		return mailId;
	}
	public void setMailId(Integer mailId){
		this.mailId = mailId;
	}

}