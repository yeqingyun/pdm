package gnmail.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicMail {
	public static final String SELF_FIELDS = "Mail.MailId,Mail.CfgId,Mail.OexId,Mail.SenderId,Mail.Sender,Mail.AcceptId,Mail.Accept,Mail.Title,Mail.MailText,Mail.MailDate,Mail.Remark,Mail.Status,Mail.CreateBy,Mail.CreateDate,Mail.LastUpd,Mail.LastUpdDate";

	private java.lang.Integer mailId;
	private java.lang.Integer cfgId;
	private java.lang.Integer oexId;
	private java.lang.Integer senderId;
	private java.lang.String sender;
	private java.lang.Integer acceptId;
	private java.lang.String accept;
	private java.lang.String title;
	private java.lang.String mailText;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date mailDate;
	private java.util.Date startMailDate;
	private java.util.Date endMailDate;
	private java.lang.String remark;
	private java.lang.Integer status;
	private java.lang.Integer createBy;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	private java.lang.Integer lastUpd;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastUpdDate;
	private java.util.Date startLastUpdDate;
	private java.util.Date endLastUpdDate;

	public java.lang.Integer getMailId(){
		return mailId;
	}
	public void setMailId(java.lang.Integer mailId){
		this.mailId = mailId;
	}
	public java.lang.Integer getCfgId(){
		return cfgId;
	}
	public void setCfgId(java.lang.Integer cfgId){
		this.cfgId = cfgId;
	}
	public java.lang.Integer getOexId(){
		return oexId;
	}
	public void setOexId(java.lang.Integer oexId){
		this.oexId = oexId;
	}
	public java.lang.Integer getSenderId(){
		return senderId;
	}
	public void setSenderId(java.lang.Integer senderId){
		this.senderId = senderId;
	}
	public java.lang.String getSender(){
		return sender;
	}
	public void setSender(java.lang.String sender){
		this.sender = sender;
	}
	public java.lang.Integer getAcceptId(){
		return acceptId;
	}
	public void setAcceptId(java.lang.Integer acceptId){
		this.acceptId = acceptId;
	}
	public java.lang.String getAccept(){
		return accept;
	}
	public void setAccept(java.lang.String accept){
		this.accept = accept;
	}
	public java.lang.String getTitle(){
		return title;
	}
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	public java.lang.String getMailText(){
		return mailText;
	}
	public void setMailText(java.lang.String mailText){
		this.mailText = mailText;
	}
	public java.util.Date getStartMailDate(){
		return startMailDate;
	}
	public void setStartMailDate(java.util.Date startMailDate){
		this.startMailDate = startMailDate;
	}
	public java.util.Date getEndMailDate(){
		return endMailDate;
	}
	public void setEndMailDate(java.util.Date endMailDate){
		this.endMailDate = endMailDate;
	}
	public java.util.Date getMailDate(){
		return mailDate;
	}
	public void setMailDate(java.util.Date mailDate){
		this.mailDate = mailDate;
	}
	public java.lang.String getRemark(){
		return remark;
	}
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
	public java.lang.Integer getCreateBy(){
		return createBy;
	}
	public void setCreateBy(java.lang.Integer createBy){
		this.createBy = createBy;
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