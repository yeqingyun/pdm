package gnmail.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicMailTmplG {
	public static final String SELF_FIELDS = "MailTmplG.TmplId,MailTmplG.GroupId";

	private java.lang.Integer tmplId;
	private java.lang.Integer groupId;

	public java.lang.Integer getTmplId(){
		return tmplId;
	}
	public void setTmplId(java.lang.Integer tmplId){
		this.tmplId = tmplId;
	}
	public java.lang.Integer getGroupId(){
		return groupId;
	}
	public void setGroupId(java.lang.Integer groupId){
		this.groupId = groupId;
	}

}