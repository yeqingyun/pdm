package gnmail.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicMailBook {
	public static final String SELF_FIELDS = "MailBook.BookId,MailBook.UserId,MailBook.GroupId,MailBook.ExtPhone,MailBook.Phone,MailBook.Mobile,MailBook.MailAddr,MailBook.AddrName,MailBook.Remark,MailBook.Status,MailBook.CreateBy,MailBook.CreateDate,MailBook.LastUpd,MailBook.LastUpdDate";

	private java.lang.Integer bookId;
	private java.lang.Integer userId;
	private java.lang.Integer groupId;
	private java.lang.String extPhone;
	private java.lang.String phone;
	private java.lang.String mobile;
	private java.lang.String mailAddr;
	private java.lang.String addrName;
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

	public java.lang.Integer getBookId(){
		return bookId;
	}
	public void setBookId(java.lang.Integer bookId){
		this.bookId = bookId;
	}
	public java.lang.Integer getUserId(){
		return userId;
	}
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}
	public java.lang.Integer getGroupId(){
		return groupId;
	}
	public void setGroupId(java.lang.Integer groupId){
		this.groupId = groupId;
	}
	public java.lang.String getExtPhone(){
		return extPhone;
	}
	public void setExtPhone(java.lang.String extPhone){
		this.extPhone = extPhone;
	}
	public java.lang.String getPhone(){
		return phone;
	}
	public void setPhone(java.lang.String phone){
		this.phone = phone;
	}
	public java.lang.String getMobile(){
		return mobile;
	}
	public void setMobile(java.lang.String mobile){
		this.mobile = mobile;
	}
	public java.lang.String getMailAddr(){
		return mailAddr;
	}
	public void setMailAddr(java.lang.String mailAddr){
		this.mailAddr = mailAddr;
	}
	public java.lang.String getAddrName(){
		return addrName;
	}
	public void setAddrName(java.lang.String addrName){
		this.addrName = addrName;
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