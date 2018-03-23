package zrprjt.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicPrjtDef {
	public static final String SELF_FIELDS = "PrjtDef.PrjtPointVersion,PrjtDef.NextPoint,PrjtDef.CurrentPoint,PrjtDef.DevDeptNameID,PrjtDef.PrjtDefDocFileNo,PrjtDef.PrjtDefDocFileName,PrjtDef.PrjtTaskFileNo,PrjtDef.PrjtTaskFileName,PrjtDef.TaskVersion,PrjtDef.TypId,PrjtDef.Leve,PrjtDef.Scope,PrjtDef.Status,PrjtDef.CreateBy,PrjtDef.LastUpd,PrjtDef.PlanStaDate,PrjtDef.PlanOveDate,PrjtDef.StaDate,PrjtDef.OveDate,PrjtDef.CreateDate,PrjtDef.LastDate,PrjtDef.PrjtNo,PrjtDef.PrjtNm,PrjtDef.Remark,PrjtDef.Perce";
	
	private java.lang.Float  taskVersion;
	private java.lang.Integer typId;
	private java.lang.Integer leve;
	private java.lang.Integer scope;
	private java.lang.Integer status;
	private java.lang.Integer createBy;
	private java.lang.Integer lastUpd;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date planStaDate;
	private java.util.Date startPlanStaDate;
	private java.util.Date endPlanStaDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date planOveDate;
	private java.util.Date startPlanOveDate;
	private java.util.Date endPlanOveDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date staDate;
	private java.util.Date startStaDate;
	private java.util.Date endStaDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date oveDate;
	private java.util.Date startOveDate;
	private java.util.Date endOveDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastDate;
	private java.util.Date startLastDate;
	private java.util.Date endLastDate;
	private java.lang.String prjtNo;
	private java.lang.String prjtNm;
	private java.lang.String remark;
	private java.lang.String perce;

	private java.lang.String PrjtDefDocFileNo;
	private java.lang.String prjtDefDocFileName;
	private java.lang.String prjtTaskFileNo;
	private java.lang.String prjtTaskFileName;
	
	private java.lang.Integer devDeptNameID;
	
	private java.lang.Integer deptNameID;
	
	
	private java.lang.String currentPoint;
	private java.lang.String nextPoint;
	
	private java.lang.String prjtPointVersion;
	
	
	public java.lang.Integer getDeptNameID() {
		return deptNameID;
	}
	public void setDeptNameID(java.lang.Integer deptNameID) {
		this.deptNameID = deptNameID;
	}
	public java.lang.Integer getDevDeptNameID() {
		return devDeptNameID;
	}
	public void setDevDeptNameID(java.lang.Integer devDeptNameID) {
		this.devDeptNameID = devDeptNameID;
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
	public java.lang.Integer getScope(){
		return scope;
	}
	public void setScope(java.lang.Integer scope){
		this.scope = scope;
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
	public java.util.Date getStartPlanStaDate(){
		return startPlanStaDate;
	}
	public void setStartPlanStaDate(java.util.Date startPlanStaDate){
		this.startPlanStaDate = startPlanStaDate;
	}
	public java.util.Date getEndPlanStaDate(){
		return endPlanStaDate;
	}
	public void setEndPlanStaDate(java.util.Date endPlanStaDate){
		this.endPlanStaDate = endPlanStaDate;
	}
	public java.util.Date getPlanStaDate(){
		return planStaDate;
	}
	public void setPlanStaDate(java.util.Date planStaDate){
		this.planStaDate = planStaDate;
	}
	public java.util.Date getStartPlanOveDate(){
		return startPlanOveDate;
	}
	public void setStartPlanOveDate(java.util.Date startPlanOveDate){
		this.startPlanOveDate = startPlanOveDate;
	}
	public java.util.Date getEndPlanOveDate(){
		return endPlanOveDate;
	}
	public void setEndPlanOveDate(java.util.Date endPlanOveDate){
		this.endPlanOveDate = endPlanOveDate;
	}
	public java.util.Date getPlanOveDate(){
		return planOveDate;
	}
	public void setPlanOveDate(java.util.Date planOveDate){
		this.planOveDate = planOveDate;
	}
	public java.util.Date getStartStaDate(){
		return startStaDate;
	}
	public void setStartStaDate(java.util.Date startStaDate){
		this.startStaDate = startStaDate;
	}
	public java.util.Date getEndStaDate(){
		return endStaDate;
	}
	public void setEndStaDate(java.util.Date endStaDate){
		this.endStaDate = endStaDate;
	}
	public java.util.Date getStaDate(){
		return staDate;
	}
	public void setStaDate(java.util.Date staDate){
		this.staDate = staDate;
	}
	public java.util.Date getStartOveDate(){
		return startOveDate;
	}
	public void setStartOveDate(java.util.Date startOveDate){
		this.startOveDate = startOveDate;
	}
	public java.util.Date getEndOveDate(){
		return endOveDate;
	}
	public void setEndOveDate(java.util.Date endOveDate){
		this.endOveDate = endOveDate;
	}
	public java.util.Date getOveDate(){
		return oveDate;
	}
	public void setOveDate(java.util.Date oveDate){
		this.oveDate = oveDate;
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
	public java.lang.String getPrjtNo(){
		return prjtNo;
	}
	public void setPrjtNo(java.lang.String prjtNo){
		this.prjtNo = prjtNo;
	}
	public java.lang.String getPrjtNm(){
		return prjtNm;
	}
	public void setPrjtNm(java.lang.String prjtNm){
		this.prjtNm = prjtNm;
	}
	public java.lang.String getRemark(){
		return remark;
	}
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	public java.lang.String getPerce(){
		return perce;
	}
	public void setPerce(java.lang.String perce){
		this.perce = perce;
	}
	public java.lang.Float getTaskVersion() {
		return taskVersion;
	}
	public void setTaskVersion(java.lang.Float taskVersion) {
		this.taskVersion = taskVersion;
	}
	public java.lang.String getPrjtDefDocFileNo() {
		return PrjtDefDocFileNo;
	}
	public void setPrjtDefDocFileNo(java.lang.String prjtDefDocFileNo) {
		PrjtDefDocFileNo = prjtDefDocFileNo;
	}
	public java.lang.String getPrjtDefDocFileName() {
		return prjtDefDocFileName;
	}
	public void setPrjtDefDocFileName(java.lang.String prjtDefDocFileName) {
		this.prjtDefDocFileName = prjtDefDocFileName;
	}
	public java.lang.String getPrjtTaskFileNo() {
		return prjtTaskFileNo;
	}
	public void setPrjtTaskFileNo(java.lang.String prjtTaskFileNo) {
		this.prjtTaskFileNo = prjtTaskFileNo;
	}
	public java.lang.String getPrjtTaskFileName() {
		return prjtTaskFileName;
	}
	public void setPrjtTaskFileName(java.lang.String prjtTaskFileName) {
		this.prjtTaskFileName = prjtTaskFileName;
	}
	public java.lang.String getCurrentPoint() {
		return currentPoint;
	}
	public void setCurrentPoint(java.lang.String currentPoint) {
		this.currentPoint = currentPoint;
	}
	public java.lang.String getNextPoint() {
		return nextPoint;
	}
	public void setNextPoint(java.lang.String nextPoint) {
		this.nextPoint = nextPoint;
	}
	public java.lang.String getPrjtPointVersion() {
		return prjtPointVersion;
	}
	public void setPrjtPointVersion(java.lang.String prjtPointVersion) {
		this.prjtPointVersion = prjtPointVersion;
	}

}