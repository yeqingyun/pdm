package gnmail.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicMailTmpl {
	public static final String SELF_FIELDS = "MailTmpl.TmplId,MailTmpl.CfgId,MailTmpl.UserId,MailTmpl.TmplName,MailTmpl.TmplText,MailTmpl.Type,MailTmpl.IsAuto,MailTmpl.AutoStart,MailTmpl.AutoCycle,MailTmpl.Status,MailTmpl.CreateBy,MailTmpl.CreateDate,MailTmpl.LastUpd,MailTmpl.LastUpdDate";

	private java.lang.Integer tmplId;
	private java.lang.Integer cfgId;
	private java.lang.Integer userId;
	private java.lang.String tmplName;
	private java.lang.String tmplText;
	private java.lang.Integer type;
	private java.lang.Integer isAuto;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date autoStart;
	private java.util.Date startAutoStart;
	private java.util.Date endAutoStart;
	private java.lang.Integer autoCycle;
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

	public java.lang.Integer getTmplId(){
		return tmplId;
	}
	public void setTmplId(java.lang.Integer tmplId){
		this.tmplId = tmplId;
	}
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
	public java.lang.String getTmplName(){
		return tmplName;
	}
	public void setTmplName(java.lang.String tmplName){
		this.tmplName = tmplName;
	}
	public java.lang.String getTmplText(){
		return tmplText;
	}
	public void setTmplText(java.lang.String tmplText){
		this.tmplText = tmplText;
	}
	public java.lang.Integer getType(){
		return type;
	}
	public void setType(java.lang.Integer type){
		this.type = type;
	}
	public java.lang.Integer getIsAuto(){
		return isAuto;
	}
	public void setIsAuto(java.lang.Integer isAuto){
		this.isAuto = isAuto;
	}
	public java.util.Date getStartAutoStart(){
		return startAutoStart;
	}
	public void setStartAutoStart(java.util.Date startAutoStart){
		this.startAutoStart = startAutoStart;
	}
	public java.util.Date getEndAutoStart(){
		return endAutoStart;
	}
	public void setEndAutoStart(java.util.Date endAutoStart){
		this.endAutoStart = endAutoStart;
	}
	public java.util.Date getAutoStart(){
		return autoStart;
	}
	public void setAutoStart(java.util.Date autoStart){
		this.autoStart = autoStart;
	}
	public java.lang.Integer getAutoCycle(){
		return autoCycle;
	}
	public void setAutoCycle(java.lang.Integer autoCycle){
		this.autoCycle = autoCycle;
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