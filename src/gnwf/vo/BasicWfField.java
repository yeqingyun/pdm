package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfField {
	public static final String SELF_FIELDS = "WfField.NeedFilledOnAPP,WfField.FieldTitle,WfField.FieldId,WfField.FlowId,WfField.StepId,WfField.FieldType,WfField.IsGather,WfField.IsNull,WfField.Status,WfField.CreateBy,WfField.LastUpd,WfField.IsList,WfField.CreateDate,WfField.LastUpdDate,WfField.FieldCode,WfField.FieldName,WfField.FieldSql,WfField.FieldJs,WfField.DefaultValue";

	private java.lang.Integer fieldId;
	private java.lang.Integer flowId;
	private java.lang.Integer stepId;
	private java.lang.Integer fieldType;
	private java.lang.Integer isGather;
	private java.lang.Integer isNull;
	private java.lang.Integer status;
	private java.lang.Integer createBy;
	private java.lang.Integer lastUpd;
	private java.lang.Integer isList;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastUpdDate;
	private java.util.Date startLastUpdDate;
	private java.util.Date endLastUpdDate;
	private java.lang.String fieldCode;
	private java.lang.String fieldName;
	private java.lang.String fieldSql;
	private java.lang.String fieldJs;
	private java.lang.String defaultValue;
	
	
	private java.lang.String fieldTitle;  //标题
	private java.lang.Integer needFilledOnAPP; //是否需要在APP上填写值

	public java.lang.Integer getFieldId(){
		return fieldId;
	}
	public void setFieldId(java.lang.Integer fieldId){
		this.fieldId = fieldId;
	}
	public java.lang.Integer getFlowId(){
		return flowId;
	}
	public void setFlowId(java.lang.Integer flowId){
		this.flowId = flowId;
	}
	public java.lang.Integer getStepId(){
		return stepId;
	}
	public void setStepId(java.lang.Integer stepId){
		this.stepId = stepId;
	}
	public java.lang.Integer getFieldType(){
		return fieldType;
	}
	public void setFieldType(java.lang.Integer fieldType){
		this.fieldType = fieldType;
	}
	public java.lang.Integer getIsGather(){
		return isGather;
	}
	public void setIsGather(java.lang.Integer isGather){
		this.isGather = isGather;
	}
	public java.lang.Integer getIsNull(){
		return isNull;
	}
	public void setIsNull(java.lang.Integer isNull){
		this.isNull = isNull;
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
	public java.lang.Integer getIsList(){
		return isList;
	}
	public void setIsList(java.lang.Integer isList){
		this.isList = isList;
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
	public java.lang.String getFieldCode(){
		return fieldCode;
	}
	public void setFieldCode(java.lang.String fieldCode){
		this.fieldCode = fieldCode;
	}
	public java.lang.String getFieldName(){
		return fieldName;
	}
	public void setFieldName(java.lang.String fieldName){
		this.fieldName = fieldName;
	}
	public java.lang.String getFieldSql(){
		return fieldSql;
	}
	public void setFieldSql(java.lang.String fieldSql){
		this.fieldSql = fieldSql;
	}
	public java.lang.String getFieldJs(){
		return fieldJs;
	}
	public void setFieldJs(java.lang.String fieldJs){
		this.fieldJs = fieldJs;
	}
	public java.lang.String getDefaultValue(){
		return defaultValue;
	}
	public void setDefaultValue(java.lang.String defaultValue){
		this.defaultValue = defaultValue;
	}
	public java.lang.String getFieldTitle() {
		return fieldTitle;
	}
	public void setFieldTitle(java.lang.String fieldTitle) {
		this.fieldTitle = fieldTitle;
	}
	public java.lang.Integer getNeedFilledOnAPP() {
		return needFilledOnAPP;
	}
	public void setNeedFilledOnAPP(java.lang.Integer needFilledOnAPP) {
		this.needFilledOnAPP = needFilledOnAPP;
	}

}