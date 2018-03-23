package gnwf.vo;

import java.util.List;

public class WfField extends BasicWfField {
	public static final String ALL_FIELDS = "WfField.NeedFilledOnAPP,WfField.FieldTitle,WfField.FieldId,WfField.FlowId,WfField.StepId,WfField.FieldType,WfField.IsGather,WfField.IsNull,WfField.Status,WfField.CreateBy,WfField.LastUpd,WfField.IsList,WfField.CreateDate,WfField.LastUpdDate,WfField.FieldCode,WfField.FieldName,WfField.FieldSql,WfField.FieldJs,WfField.DefaultValue,WfCfg.FlowName as FlowName";
	public static final String LIST_FIELDS = "WfField.NeedFilledOnAPP,WfField.FieldTitle,WfField.FieldId,WfField.FlowId,WfField.StepId,WfField.FieldType,WfField.IsGather,WfField.IsNull,WfField.Status,WfField.CreateBy,WfField.LastUpd,WfField.IsList,WfField.CreateDate,WfField.LastUpdDate,WfField.FieldCode,WfField.FieldName,WfField.FieldSql,WfField.FieldJs,WfField.DefaultValue,WfCfg.FlowName as FlowName";

	private java.util.List<gnwf.vo.WfRdField> wfRdFields;
	
	
	private String flowName;
	private List<WfFieldStepRelate> relates;

	public java.util.List<gnwf.vo.WfRdField> getWfRdFields() {
		return wfRdFields;
	}
	public void setWfRdFields(java.util.List<gnwf.vo.WfRdField> wfRdFields){
		this.wfRdFields = wfRdFields;
	}
	public void addtoWfRdFields(gnwf.vo.WfRdField wfRdField){
		if(getWfRdFields() == null) setWfRdFields(new java.util.ArrayList<gnwf.vo.WfRdField>());
			getWfRdFields().add(wfRdField);
	}

	public String getFlowName(){
		return flowName;
	}
	public void setFlowName(String flowName){
		this.flowName = flowName;
	}
	public List<WfFieldStepRelate> getRelates() {
		return relates;
	}
	public void setRelates(List<WfFieldStepRelate> relates) {
		this.relates = relates;
	}

}