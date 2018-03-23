package gnmail.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicMailAutoM {
	public static final String SELF_FIELDS = "MailAutoM.MailId,MailAutoM.WfNO,MailAutoM.TaskId,MailAutoM.CreateBy,MailAutoM.CreateDate,MailAutoM.AcceptBy,MailAutoM.AcceptDate,MailAutoM.Title,MailAutoM.MailText,MailAutoM.TaskUri,MailAutoM.Status,MailAutoM.Remark";

	private java.lang.Integer mailId;
	private java.lang.String wfNO;
	private java.lang.Integer taskId;
	private java.lang.Integer createBy;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	private java.lang.Integer acceptBy;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date acceptDate;
	private java.util.Date startAcceptDate;
	private java.util.Date endAcceptDate;
	private java.lang.String title;
	private java.lang.String mailText;
	private java.lang.String taskUri;
	private java.lang.Integer status;
	private java.lang.String remark;

	public java.lang.Integer getMailId(){
		return mailId;
	}
	public void setMailId(java.lang.Integer mailId){
		this.mailId = mailId;
	}
	public java.lang.String getWfNO(){
		return wfNO;
	}
	public void setWfNO(java.lang.String wfNO){
		this.wfNO = wfNO;
	}
	public java.lang.Integer getTaskId(){
		return taskId;
	}
	public void setTaskId(java.lang.Integer taskId){
		this.taskId = taskId;
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
	public java.lang.Integer getAcceptBy(){
		return acceptBy;
	}
	public void setAcceptBy(java.lang.Integer acceptBy){
		this.acceptBy = acceptBy;
	}
	public java.util.Date getStartAcceptDate(){
		return startAcceptDate;
	}
	public void setStartAcceptDate(java.util.Date startAcceptDate){
		this.startAcceptDate = startAcceptDate;
	}
	public java.util.Date getEndAcceptDate(){
		return endAcceptDate;
	}
	public void setEndAcceptDate(java.util.Date endAcceptDate){
		this.endAcceptDate = endAcceptDate;
	}
	public java.util.Date getAcceptDate(){
		return acceptDate;
	}
	public void setAcceptDate(java.util.Date acceptDate){
		this.acceptDate = acceptDate;
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
	public java.lang.String getTaskUri(){
		return taskUri;
	}
	public void setTaskUri(java.lang.String taskUri){
		this.taskUri = taskUri;
	}
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
	public java.lang.String getRemark(){
		return remark;
	}
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}

}