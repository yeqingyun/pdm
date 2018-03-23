package gnmail.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicMailDoc {
	public static final String SELF_FIELDS = "MailDoc.DocId,MailDoc.MailId,MailDoc.DocName,MailDoc.UriLink";

	private java.lang.Integer docId;
	private java.lang.Integer mailId;
	private java.lang.String docName;
	private java.lang.String uriLink;

	public java.lang.Integer getDocId(){
		return docId;
	}
	public void setDocId(java.lang.Integer docId){
		this.docId = docId;
	}
	public java.lang.Integer getMailId(){
		return mailId;
	}
	public void setMailId(java.lang.Integer mailId){
		this.mailId = mailId;
	}
	public java.lang.String getDocName(){
		return docName;
	}
	public void setDocName(java.lang.String docName){
		this.docName = docName;
	}
	public java.lang.String getUriLink(){
		return uriLink;
	}
	public void setUriLink(java.lang.String uriLink){
		this.uriLink = uriLink;
	}

}