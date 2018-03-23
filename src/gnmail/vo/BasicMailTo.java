package gnmail.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicMailTo {
	public static final String SELF_FIELDS = "MailTo.MailId,MailTo.ToId,MailTo.ToMail,MailTo.ToType,MailTo.ToName";

	private java.lang.Integer mailId;
	private java.lang.Integer toId;
	private java.lang.String toMail;
	private java.lang.Integer toType;
	private java.lang.String toName;

	public java.lang.Integer getMailId(){
		return mailId;
	}
	public void setMailId(java.lang.Integer mailId){
		this.mailId = mailId;
	}
	public java.lang.Integer getToId(){
		return toId;
	}
	public void setToId(java.lang.Integer toId){
		this.toId = toId;
	}
	public java.lang.String getToMail(){
		return toMail;
	}
	public void setToMail(java.lang.String toMail){
		this.toMail = toMail;
	}
	public java.lang.Integer getToType(){
		return toType;
	}
	public void setToType(java.lang.Integer toType){
		this.toType = toType;
	}
	public java.lang.String getToName(){
		return toName;
	}
	public void setToName(java.lang.String toName){
		this.toName = toName;
	}

}