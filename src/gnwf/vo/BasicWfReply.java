package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfReply {
	public static final String SELF_FIELDS = "WfReply.GroupId,WfReply.RepLyType,WfReply.FileName,WfReply.FileNo,WfReply.ReplyId,WfReply.QuesId,WfReply.UserId,WfReply.Status,WfReply.CreateBy,WfReply.LastUpd,WfReply.CreateDate,WfReply.LastUpdDate,WfReply.ReplyText";

	private java.lang.Integer replyId;
	private java.lang.String quesId;
	private java.lang.Integer userId;
	private java.lang.Integer status;
	private java.lang.Integer createBy;
	private java.lang.Integer lastUpd;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastUpdDate;
	private java.util.Date startLastUpdDate;
	private java.util.Date endLastUpdDate;
	private java.lang.String replyText;
	
	private java.lang.String fileNo;
	private java.lang.String fileName;
	
	private java.lang.Integer groupId;
	private java.lang.Integer repLyType;

	public java.lang.Integer getReplyId(){
		return replyId;
	}
	public void setReplyId(java.lang.Integer replyId){
		this.replyId = replyId;
	}
	public java.lang.String getQuesId(){
		return quesId;
	}
	public void setQuesId(java.lang.String quesId){
		this.quesId = quesId;
	}
	public java.lang.Integer getUserId(){
		return userId;
	}
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
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
	public java.lang.String getReplyText(){
		return replyText;
	}
	public void setReplyText(java.lang.String replyText){
		this.replyText = replyText;
	}
	public java.lang.String getFileNo() {
		return fileNo;
	}
	public void setFileNo(java.lang.String fileNo) {
		this.fileNo = fileNo;
	}
	public java.lang.String getFileName() {
		return fileName;
	}
	public void setFileName(java.lang.String fileName) {
		this.fileName = fileName;
	}
	public java.lang.Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(java.lang.Integer groupId) {
		this.groupId = groupId;
	}
	public java.lang.Integer getRepLyType() {
		return repLyType;
	}
	public void setRepLyType(java.lang.Integer repLyType) {
		this.repLyType = repLyType;
	}

}