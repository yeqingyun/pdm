package zrsy.vo;
import org.frm.vo.BasicVO;
@SuppressWarnings("serial")
public class BasicWarrMsg extends BasicVO {
	public static final String SELF_FIELDS = "WarrMsg.MsgId,WarrMsg.Sender,WarrMsg.Accept,WarrMsg.Title,WarrMsg.MsgText,WarrMsg.UriLink,WarrMsg.Status,WarrMsg.CreateBy,WarrMsg.CreateDate,WarrMsg.LastUpd,WarrMsg.LastDate";
	private java.lang.Integer msgId;
	private java.lang.Integer sender;
	private java.lang.Integer accept;
	private java.lang.String title;
	private java.lang.String msgText;
	private java.lang.String uriLink;
	private java.lang.Integer status;
	private java.lang.Integer createBy;
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	private java.lang.Integer lastUpd;
	private java.util.Date lastDate;
	private java.util.Date startLastUpdDate;
	private java.util.Date endLastUpdDate;

	public java.lang.Integer getMsgId() {
		return msgId;
	}
	public void setMsgId(java.lang.Integer msgId) {
		this.msgId =  msgId;
	}
	public java.lang.Integer getSender() {
		return sender;
	}
	public void setSender(java.lang.Integer sender) {
		this.sender =  sender;
	}
	public java.lang.Integer getAccept() {
		return accept;
	}
	public void setAccept(java.lang.Integer accept) {
		this.accept =  accept;
	}
	public java.lang.String getTitle() {
		return title;
	}
	public void setTitle(java.lang.String title) {
		this.title =  title;
	}
	public java.lang.String getMsgText() {
		return msgText;
	}
	public void setMsgText(java.lang.String msgText) {
		this.msgText =  msgText;
	}
	public java.lang.String getUriLink() {
		return uriLink;
	}
	public void setUriLink(java.lang.String uriLink) {
		this.uriLink =  uriLink;
	}
	public java.lang.Integer getStatus() {
		return status;
	}
	public void setStatus(java.lang.Integer status) {
		this.status =  status;
	}
	public java.lang.Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(java.lang.Integer createBy) {
		this.createBy =  createBy;
	}
	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate =  createDate;
	}
	public java.util.Date getStartCreateDate() {
		return startCreateDate;
	}
	public void setStartCreateDate(java.util.Date startCreateDate) {
		this.startCreateDate =  startCreateDate;
	}
	public java.util.Date getEndCreateDate() {
		return endCreateDate;
	}
	public void setEndCreateDate(java.util.Date endCreateDate) {
		this.endCreateDate =  endCreateDate;
	}
	public java.lang.Integer getLastUpd() {
		return lastUpd;
	}
	public void setLastUpd(java.lang.Integer lastUpd) {
		this.lastUpd =  lastUpd;
	}
	public java.util.Date getLastDate() {
		return lastDate;
	}
	public void setLastUpdDate(java.util.Date lastDate) {
		this.lastDate =  lastDate;
	}
	public java.util.Date getStartLastUpdDate() {
		return startLastUpdDate;
	}
	public void setStartLastUpdDate(java.util.Date startLastUpdDate) {
		this.startLastUpdDate =  startLastUpdDate;
	}
	public java.util.Date getEndLastUpdDate() {
		return endLastUpdDate;
	}
	public void setEndLastUpdDate(java.util.Date endLastUpdDate) {
		this.endLastUpdDate =  endLastUpdDate;
	}
}
