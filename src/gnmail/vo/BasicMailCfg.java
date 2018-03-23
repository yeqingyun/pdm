package gnmail.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicMailCfg {
	public static final String SELF_FIELDS = "MailCfg.CfgId,MailCfg.UserId,MailCfg.MailAddr,MailCfg.Pwd,MailCfg.MailName,MailCfg.MailSign,MailCfg.ServIpAddr,MailCfg.Smpt,MailCfg.Pop3,MailCfg.Remark,MailCfg.Status,MailCfg.CreateBy,MailCfg.CreateDate,MailCfg.LastUpd,MailCfg.LastUpdDate";

	private java.lang.Integer cfgId;
	private java.lang.Integer userId;
	private java.lang.String mailAddr;
	private java.lang.String pwd;
	private java.lang.String mailName;
	private java.lang.String mailSign;
	private java.lang.String servIpAddr;
	private java.lang.String smpt;
	private java.lang.String pop3;
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

	public java.lang.Integer getCfgId(){
		return cfgId;
	}
	public void setCfgId(java.lang.Integer cfgId){
		this.cfgId = cfgId;
	}
	public java.lang.Integer getUserId(){
		return userId;
	}
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}
	public java.lang.String getMailAddr(){
		return mailAddr;
	}
	public void setMailAddr(java.lang.String mailAddr){
		this.mailAddr = mailAddr;
	}
	public java.lang.String getPwd(){
		return pwd;
	}
	public void setPwd(java.lang.String pwd){
		this.pwd = pwd;
	}
	public java.lang.String getMailName(){
		return mailName;
	}
	public void setMailName(java.lang.String mailName){
		this.mailName = mailName;
	}
	public java.lang.String getMailSign(){
		return mailSign;
	}
	public void setMailSign(java.lang.String mailSign){
		this.mailSign = mailSign;
	}
	public java.lang.String getServIpAddr(){
		return servIpAddr;
	}
	public void setServIpAddr(java.lang.String servIpAddr){
		this.servIpAddr = servIpAddr;
	}
	public java.lang.String getSmpt(){
		return smpt;
	}
	public void setSmpt(java.lang.String smpt){
		this.smpt = smpt;
	}
	public java.lang.String getPop3(){
		return pop3;
	}
	public void setPop3(java.lang.String pop3){
		this.pop3 = pop3;
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