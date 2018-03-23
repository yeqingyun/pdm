package zrprjt.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicTaskMsg {
	public static final String SELF_FIELDS = "TaskMsg.MsgId,TaskMsg.Status,TaskMsg.CreateBy,TaskMsg.LastUpd,TaskMsg.CreateDate,TaskMsg.LastDate,TaskMsg.Reply,TaskMsg.TaskNo,TaskMsg.PrjtNo,TaskMsg.WfNo,TaskMsg.Title";

	private java.lang.Integer msgId;
	private java.lang.Integer status;
	private java.lang.Integer createBy;
	private java.lang.Integer lastUpd;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastDate;
	private java.util.Date startLastDate;
	private java.util.Date endLastDate;
	private java.lang.String reply;
	private java.lang.String taskNo;
	private java.lang.String prjtNo;
	private java.lang.String wfNo;
	private java.lang.String title;

	public java.lang.Integer getMsgId(){
		return msgId;
	}
	public void setMsgId(java.lang.Integer msgId){
		this.msgId = msgId;
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
	public java.lang.Integer getLastUpd(){
		return lastUpd;
	}
	public void setLastUpd(java.lang.Integer lastUpd){
		this.lastUpd = lastUpd;
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
	public java.util.Date getStartLastDate(){
		return startLastDate;
	}
	public void setStartLastDate(java.util.Date startLastDate){
		this.startLastDate = startLastDate;
	}
	public java.util.Date getEndLastDate(){
		return endLastDate;
	}
	public void setEndLastDate(java.util.Date endLastDate){
		this.endLastDate = endLastDate;
	}
	public java.util.Date getLastDate(){
		return lastDate;
	}
	public void setLastDate(java.util.Date lastDate){
		this.lastDate = lastDate;
	}
	public java.lang.String getReply(){
		return reply;
	}
	public void setReply(java.lang.String reply){
		this.reply = reply;
	}
	public java.lang.String getTaskNo(){
		return taskNo;
	}
	public void setTaskNo(java.lang.String taskNo){
		this.taskNo = taskNo;
	}
	public java.lang.String getPrjtNo(){
		return prjtNo;
	}
	public void setPrjtNo(java.lang.String prjtNo){
		this.prjtNo = prjtNo;
	}
	public java.lang.String getWfNo(){
		return wfNo;
	}
	public void setWfNo(java.lang.String wfNo){
		this.wfNo = wfNo;
	}
	public java.lang.String getTitle(){
		return title;
	}
	public void setTitle(java.lang.String title){
		this.title = title;
	}

}