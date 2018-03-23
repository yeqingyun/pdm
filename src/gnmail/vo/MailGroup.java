package gnmail.vo;

public class MailGroup extends BasicMailGroup {
	public static final String ALL_FIELDS = "MailGroup.GroupId,MailGroup.UserId,MailGroup.GroupParent,MailGroup.GroupName,MailGroup.GroupLevel,MailGroup.Status,MailGroup.CreateDate,MailGroup.LastUpd,MailGroup.LastUpdDate";
	public static final String LIST_FIELDS = "MailGroup.GroupId,MailGroup.UserId,MailGroup.GroupParent,MailGroup.GroupName,MailGroup.GroupLevel,MailGroup.Status,MailGroup.CreateDate,MailGroup.LastUpd,MailGroup.LastUpdDate";

	private java.util.List<gnmail.vo.MailBook> mailBooks;
	private java.util.List<gnmail.vo.MailTmplG> mailTmplGs;


	public java.util.List<gnmail.vo.MailBook> getMailBooks() {
		return mailBooks;
	}
	public void setMailBooks(java.util.List<gnmail.vo.MailBook> mailBooks){
		this.mailBooks = mailBooks;
	}
	public void addtoMailBooks(gnmail.vo.MailBook mailBook){
		if(getMailBooks() == null) setMailBooks(new java.util.ArrayList<gnmail.vo.MailBook>());
			getMailBooks().add(mailBook);
	}
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


}