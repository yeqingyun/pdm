package gnwf.vo;

public class BasicWfRdField {
	public static final String SELF_FIELDS = "WfRdField.FieldId,WfRdField.WfId,WfRdField.RowId,WfRdField.WfNo,WfRdField.FieldText";

	private java.lang.Integer fieldId;
	private java.lang.Integer wfId;
	private int rowId;
	private java.lang.String wfNo;
	private java.lang.String fieldText;

	public java.lang.Integer getFieldId(){
		return fieldId;
	}
	public void setFieldId(java.lang.Integer fieldId){
		this.fieldId = fieldId;
	}
	public java.lang.Integer getWfId(){
		return wfId;
	}
	public void setWfId(java.lang.Integer wfId){
		this.wfId = wfId;
	}
	public int getRowId() {
		return rowId;
	}
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}
	public java.lang.String getWfNo(){
		return wfNo;
	}
	public void setWfNo(java.lang.String wfNo){
		this.wfNo = wfNo;
	}
	public java.lang.String getFieldText(){
		return fieldText;
	}
	public void setFieldText(java.lang.String fieldText){
		this.fieldText = fieldText;
	}

}