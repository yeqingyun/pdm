package zrprjt.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicSchCfg {
	public static final String SELF_FIELDS = "SchCfg.PredecessorLink,SchCfg.Critical,SchCfg.Summary,SchCfg.Milestone,SchCfg.SchId,SchCfg.Parent,SchCfg.TypId,SchCfg.SchNo,SchCfg.Leve,SchCfg.CfgTime,SchCfg.Status,SchCfg.CreateBy,SchCfg.LastUpd,SchCfg.CreateDate,SchCfg.LastDate,SchCfg.SchNm,SchCfg.Remark";

	private java.lang.Integer schId;
	private java.lang.Integer parent;
	private java.lang.Integer typId;
	private java.lang.Integer schNo;
	private java.lang.Integer leve;
	private java.lang.Integer cfgTime;
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
	private java.lang.String schNm;
	private java.lang.String remark;
	
	private java.lang.String predecessorLink;
	private java.lang.Integer milestone;
	private java.lang.Integer summary;
	private java.lang.Integer critical;
	

	public java.lang.Integer getSchId(){
		return schId;
	}
	public void setSchId(java.lang.Integer schId){
		this.schId = schId;
	}
	public java.lang.Integer getParent(){
		return parent;
	}
	public void setParent(java.lang.Integer parent){
		this.parent = parent;
	}
	public java.lang.Integer getTypId(){
		return typId;
	}
	public void setTypId(java.lang.Integer typId){
		this.typId = typId;
	}
	public java.lang.Integer getLeve(){
		return leve;
	}
	public void setLeve(java.lang.Integer leve){
		this.leve = leve;
	}
	public java.lang.Integer getCfgTime(){
		return cfgTime;
	}
	public void setCfgTime(java.lang.Integer cfgTime){
		this.cfgTime = cfgTime;
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
	public java.lang.String getSchNm(){
		return schNm;
	}
	public void setSchNm(java.lang.String schNm){
		this.schNm = schNm;
	}
	public java.lang.String getRemark(){
		return remark;
	}
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	public java.lang.Integer getMilestone() {
		return milestone;
	}
	public void setMilestone(java.lang.Integer milestone) {
		this.milestone = milestone;
	}
	public java.lang.Integer getSummary() {
		return summary;
	}
	public void setSummary(java.lang.Integer summary) {
		this.summary = summary;
	}
	public java.lang.Integer getCritical() {
		return critical;
	}
	public void setCritical(java.lang.Integer critical) {
		this.critical = critical;
	}
	public java.lang.String getPredecessorLink() {
		return predecessorLink;
	}
	public void setPredecessorLink(java.lang.String predecessorLink) {
		this.predecessorLink = predecessorLink;
	}
	public java.lang.Integer getSchNo() {
		return schNo;
	}
	public void setSchNo(java.lang.Integer schNo) {
		this.schNo = schNo;
	}

}