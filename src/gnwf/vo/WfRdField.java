package gnwf.vo;

public class WfRdField extends BasicWfRdField {
	public static final String ALL_FIELDS = "WfRdField.FieldId,WfRdField.WfId,WfRdField.RowId,WfRdField.WfNo,WfRdField.FieldText,WfField.FieldId as FieldName,WfRd.WfNo as WfNo ,WfField.FieldType,WfField.IsNull,WfField.FieldCode,";
	public static final String LIST_FIELDS = "WfRdField.FieldId,WfRdField.WfId,WfRdField.RowId,WfRdField.WfNo,WfRdField.FieldText,WfField.FieldId as FieldName,WfRd.WfNo as WfNo,WfField.FieldType,WfField.IsNull,WfField.FieldCode,";
	public static final String XLS_FIELDS = "WfRd.WfNo,WfField.FieldId,WfField.FieldName,WfField.FieldType,WfField.IsNull,WfField.FieldCode,WfRdField.RowId,WfRdField.FieldText";
	

	private String wfNo;
	
	private int 	stepId;			//owffield.stepid=0 ������������ ��չ�ֶ�
	private String 	fieldName;
	private String 	fieldCode;
	private Integer fieldType;
	private String 	isNull;
	private Integer flowId;
	private int 	count;				//����
	private int 	isEdit;
	
	
	private java.lang.String fieldTitle;
	private java.lang.Integer needFilledOnAPP;


	public String getFieldName(){
		return fieldName;
	}
	public void setFieldName(String fieldName){
		this.fieldName = fieldName;
	}
	public String getWfNo(){
		return wfNo;
	}
	public void setWfNo(String wfNo){
		this.wfNo = wfNo;
	}
	public int getStepId() {
		return stepId;
	}
	public void setStepId(int stepId) {
		this.stepId = stepId;
	}
	public String getFieldCode() {
		return fieldCode;
	}
	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}
	public Integer getFieldType() {
		return fieldType;
	}
	public void setFieldType(Integer fieldType) {
		this.fieldType = fieldType;
	}
	public String getIsNull() {
		return isNull;
	}
	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}
	public Integer getFlowId() {
		return flowId;
	}
	public void setFlowId(Integer flowId) {
		this.flowId = flowId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getIsEdit() {
		return isEdit;
	}
	public void setIsEdit(int isEdit) {
		this.isEdit = isEdit;
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