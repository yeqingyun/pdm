package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfCfg {
	public static final String SELF_FIELDS = "WfCfg.NeedQues,WfCfg.FlowId,WfCfg.ComId,WfCfg.DeptId,WfCfg.CateId,WfCfg.IsFirstStep,WfCfg.Sphere,WfCfg.RelateId,WfCfg.Limit,WfCfg.Status,WfCfg.CreateBy,WfCfg.LastUpd,WfCfg.HasQuesMoudle,WfCfg.ScheCfgId,WfCfg.CreateDate,WfCfg.LastUpdDate,WfCfg.FlowDesc,WfCfg.FlowName,WfCfg.FlowUri,WfCfg.PrintUri,WfCfg.SpecialClass,WfCfg.AddRdExtendUri,WfCfg.FlowCode";

	private java.lang.Integer flowId;
	private java.lang.Integer comId;
	private java.lang.Integer deptId;
	private java.lang.Integer cateId;
	private java.lang.Integer isFirstStep;
	private java.lang.Integer sphere;
	private java.lang.Integer relateId;
	private java.lang.Integer limit;
	private java.lang.Integer status;
	private java.lang.Integer createBy;
	private java.lang.Integer lastUpd;
	private java.lang.Integer hasQuesMoudle;
	private java.lang.Integer scheCfgId;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastUpdDate;
	private java.util.Date startLastUpdDate;
	private java.util.Date endLastUpdDate;
	private java.lang.String flowDesc;
	private java.lang.String flowName;
	private java.lang.String flowUri;
	private java.lang.String printUri;
	private java.lang.String specialClass;
	private java.lang.String addRdExtendUri;
	private java.lang.String flowCode;
	private java.lang.Integer needQues;

	public java.lang.Integer getFlowId(){
		return flowId;
	}
	public void setFlowId(java.lang.Integer flowId){
		this.flowId = flowId;
	}
	public java.lang.Integer getComId(){
		return comId;
	}
	public void setComId(java.lang.Integer comId){
		this.comId = comId;
	}
	public java.lang.Integer getDeptId(){
		return deptId;
	}
	public void setDeptId(java.lang.Integer deptId){
		this.deptId = deptId;
	}
	public java.lang.Integer getCateId(){
		return cateId;
	}
	public void setCateId(java.lang.Integer cateId){
		this.cateId = cateId;
	}
	public java.lang.Integer getIsFirstStep(){
		return isFirstStep;
	}
	public void setIsFirstStep(java.lang.Integer isFirstStep){
		this.isFirstStep = isFirstStep;
	}
	public java.lang.Integer getSphere(){
		return sphere;
	}
	public void setSphere(java.lang.Integer sphere){
		this.sphere = sphere;
	}
	public java.lang.Integer getRelateId(){
		return relateId;
	}
	public void setRelateId(java.lang.Integer relateId){
		this.relateId = relateId;
	}
	public java.lang.Integer getLimit(){
		return limit;
	}
	public void setLimit(java.lang.Integer limit){
		this.limit = limit;
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
	public java.lang.Integer getHasQuesMoudle(){
		return hasQuesMoudle;
	}
	public void setHasQuesMoudle(java.lang.Integer hasQuesMoudle){
		this.hasQuesMoudle = hasQuesMoudle;
	}
	public java.lang.Integer getScheCfgId(){
		return scheCfgId;
	}
	public void setScheCfgId(java.lang.Integer scheCfgId){
		this.scheCfgId = scheCfgId;
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
	public java.lang.String getFlowDesc(){
		return flowDesc;
	}
	public void setFlowDesc(java.lang.String flowDesc){
		this.flowDesc = flowDesc;
	}
	public java.lang.String getFlowName(){
		return flowName;
	}
	public void setFlowName(java.lang.String flowName){
		this.flowName = flowName;
	}
	public java.lang.String getFlowUri(){
		return flowUri;
	}
	public void setFlowUri(java.lang.String flowUri){
		this.flowUri = flowUri;
	}
	public java.lang.String getPrintUri(){
		return printUri;
	}
	public void setPrintUri(java.lang.String printUri){
		this.printUri = printUri;
	}
	public java.lang.String getSpecialClass(){
		return specialClass;
	}
	public void setSpecialClass(java.lang.String specialClass){
		this.specialClass = specialClass;
	}
	public java.lang.String getAddRdExtendUri(){
		return addRdExtendUri;
	}
	public void setAddRdExtendUri(java.lang.String addRdExtendUri){
		this.addRdExtendUri = addRdExtendUri;
	}
	public java.lang.String getFlowCode(){
		return flowCode;
	}
	public void setFlowCode(java.lang.String flowCode){
		this.flowCode = flowCode;
	}
	public java.lang.Integer getNeedQues() {
		return needQues;
	}
	public void setNeedQues(java.lang.Integer needQues) {
		this.needQues = needQues;
	}

}