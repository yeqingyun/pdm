package gnmail.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicMailGroup {
	public static final String SELF_FIELDS = "MailGroup.GroupId,MailGroup.UserId,MailGroup.GroupParent,MailGroup.GroupName,MailGroup.GroupLevel,MailGroup.Status,MailGroup.CreateDate,MailGroup.LastUpd,MailGroup.LastUpdDate";

	private java.lang.Integer groupId;
	private java.lang.Integer userId;
	private java.lang.Integer groupParent;
	private java.lang.String groupName;
	private java.lang.Integer groupLevel;
	private java.lang.Integer status;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	private java.lang.Integer lastUpd;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastUpdDate;
	private java.util.Date startLastUpdDate;
	private java.util.Date endLastUpdDate;

	public java.lang.Integer getGroupId(){
		return groupId;
	}
	public void setGroupId(java.lang.Integer groupId){
		this.groupId = groupId;
	}
	public java.lang.Integer getUserId(){
		return userId;
	}
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}
	public java.lang.Integer getGroupParent(){
		return groupParent;
	}
	public void setGroupParent(java.lang.Integer groupParent){
		this.groupParent = groupParent;
	}
	public java.lang.String getGroupName(){
		return groupName;
	}
	public void setGroupName(java.lang.String groupName){
		this.groupName = groupName;
	}
	public java.lang.Integer getGroupLevel(){
		return groupLevel;
	}
	public void setGroupLevel(java.lang.Integer groupLevel){
		this.groupLevel = groupLevel;
	}
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
	public java.util.Date getStartCreateDate(){
		return startCreateDate;
	}
	public void setStartCreateDate(java.util.Date startCreateDate){
		this.startCreateDate = startCreateDate;
	}
	public java.util.Date getEndCreateDate(){
		return endCreateDate;
	}
	public void setEndCreateDate(java.util.Date endCreateDate){
		this.endCreateDate = endCreateDate;
	}
	public java.util.Date getCreateDate(){
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	public java.lang.Integer getLastUpd(){
		return lastUpd;
	}
	public void setLastUpd(java.lang.Integer lastUpd){
		this.lastUpd = lastUpd;
	}
	public java.util.Date getStartLastUpdDate(){
		return startLastUpdDate;
	}
	public void setStartLastUpdDate(java.util.Date startLastUpdDate){
		this.startLastUpdDate = startLastUpdDate;
	}
	public java.util.Date getEndLastUpdDate(){
		return endLastUpdDate;
	}
	public void setEndLastUpdDate(java.util.Date endLastUpdDate){
		this.endLastUpdDate = endLastUpdDate;
	}
	public java.util.Date getLastUpdDate(){
		return lastUpdDate;
	}
	public void setLastUpdDate(java.util.Date lastUpdDate){
		this.lastUpdDate = lastUpdDate;
	}

}