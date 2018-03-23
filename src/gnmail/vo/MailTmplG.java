package gnmail.vo;

public class MailTmplG extends BasicMailTmplG {
	public static final String ALL_FIELDS = "MailTmplG.TmplId,MailTmplG.GroupId,MailGroup.GroupId as GroupId,MailTmpl.TmplId as TmplId";
	public static final String LIST_FIELDS = "MailTmplG.TmplId,MailTmplG.GroupId,MailGroup.GroupId as GroupId,MailTmpl.TmplId as TmplId";


	private Integer groupId;
	private Integer tmplId;


	public Integer getGroupId(){
		return groupId;
	}
	public void setGroupId(Integer groupId){
		this.groupId = groupId;
	}
	public Integer getTmplId(){
		return tmplId;
	}
	public void setTmplId(Integer tmplId){
		this.tmplId = tmplId;
	}

}