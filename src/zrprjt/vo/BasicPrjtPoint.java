package zrprjt.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicPrjtPoint {
	public static final String SELF_FIELDS = "PrjtPoint.PointId,PrjtPoint.PointIndex,PrjtPoint.PointName,PrjtPoint.PrjtNo,PrjtPoint.Version," +
			                                 "PrjtPoint.OriginalPlanOveDate,PrjtPoint.ActualOverDate,PrjtPoint.PlanOverDate,PrjtPoint.Leve," +
			                                 "PrjtPoint.Parent,PrjtPoint.ResponsUser,PrjtPoint.DelayTime,PrjtPoint.DelayReson,PrjtPoint.Status," +
			                                 "PrjtPoint.Remark,PrjtPoint.CreateBy,PrjtPoint.CreateDate,PrjtPoint.LastUpd,PrjtPoint.LastDate";
	
	private java.lang.Integer pointId;
	private java.lang.Integer pointIndex;
	private java.lang.String pointName;
	private java.lang.String prjtNo;
	private java.lang.String version;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date originalPlanOveDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date actualOverDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date planOverDate;
	private java.lang.Integer leve;
	private java.lang.Integer parent;
	private java.lang.String responsUser;
	private java.lang.Integer delayTime;
	private java.lang.String delayReson;
	private java.lang.Integer status;
	private java.lang.String remark;
	private java.lang.Integer createBy;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.lang.Integer lastUpd;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastDate;
	
	public java.lang.Integer getPointId() {
		return pointId;
	}
	public void setPointId(java.lang.Integer pointId) {
		this.pointId = pointId;
	}
	public java.lang.String getPointName() {
		return pointName;
	}
	public void setPointName(java.lang.String pointName) {
		this.pointName = pointName;
	}
	public java.lang.String getPrjtNo() {
		return prjtNo;
	}
	public void setPrjtNo(java.lang.String prjtNo) {
		this.prjtNo = prjtNo;
	}
	public java.lang.String getVersion() {
		return version;
	}
	public void setVersion(java.lang.String version) {
		this.version = version;
	}
	public java.util.Date getOriginalPlanOveDate() {
		return originalPlanOveDate;
	}
	public void setOriginalPlanOveDate(java.util.Date originalPlanOveDate) {
		this.originalPlanOveDate = originalPlanOveDate;
	}
	public java.util.Date getActualOverDate() {
		return actualOverDate;
	}
	public void setActualOverDate(java.util.Date actualOverDate) {
		this.actualOverDate = actualOverDate;
	}
	public java.util.Date getPlanOverDate() {
		return planOverDate;
	}
	public void setPlanOverDate(java.util.Date planOverDate) {
		this.planOverDate = planOverDate;
	}
	public java.lang.Integer getLeve() {
		return leve;
	}
	public void setLeve(java.lang.Integer leve) {
		this.leve = leve;
	}
	public java.lang.Integer getParent() {
		return parent;
	}
	public void setParent(java.lang.Integer parent) {
		this.parent = parent;
	}
	public java.lang.String getResponsUser() {
		return responsUser;
	}
	public void setResponsUser(java.lang.String responsUser) {
		this.responsUser = responsUser;
	}
	public java.lang.Integer getDelayTime() {
		return delayTime;
	}
	public void setDelayTime(java.lang.Integer delayTime) {
		this.delayTime = delayTime;
	}
	public java.lang.String getDelayReson() {
		return delayReson;
	}
	public void setDelayReson(java.lang.String delayReson) {
		this.delayReson = delayReson;
	}
	public java.lang.Integer getStatus() {
		return status;
	}
	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	public java.lang.Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(java.lang.Integer createBy) {
		this.createBy = createBy;
	}
	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	public java.lang.Integer getLastUpd() {
		return lastUpd;
	}
	public void setLastUpd(java.lang.Integer lastUpd) {
		this.lastUpd = lastUpd;
	}
	public java.util.Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(java.util.Date lastDate) {
		this.lastDate = lastDate;
	}
	public static String getSelfFields() {
		return SELF_FIELDS;
	}
	public java.lang.Integer getPointIndex() {
		return pointIndex;
	}
	public void setPointIndex(java.lang.Integer pointIndex) {
		this.pointIndex = pointIndex;
	}
	


}