package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfRdField;

public class WfRdFieldHelper extends BasicWfRdFieldHelper {
	public String getSqlString() {
		return " from WfRdField " +
               " inner join WfField on (WfField.FieldId = WfRdField.FieldId) " + 
               " inner join WfRd on (WfRd.WfNo = WfRdField.WfNo) " + 

               " where 1=1 ";
	}

	public List<WfRdField> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfRdField> list = new ArrayList<WfRdField>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfRdField wfRdField = new WfRdField();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("WfId") || _fields[i].equals("WfRdField.WfId"))
						wfRdField.setWfId(rs.getInt("WfId"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfRdField.WfNo"))
						wfRdField.setWfNo(rs.getString("WfNo"));
					else if(_fields[i].equals("FieldId") || _fields[i].equals("WfRdField.FieldId"))
						wfRdField.setFieldId(rs.getInt("FieldId"));
					else if(_fields[i].equals("FieldText") || _fields[i].equals("WfRdField.FieldText"))
						wfRdField.setFieldText(rs.getString("FieldText"));

					else if(_fields[i].equals("FieldCode") || _fields[i].equals("WfField.FieldCode"))
						wfRdField.setFieldCode(rs.getString("FieldCode"));
					else if(_fields[i].equals("FieldName") || _fields[i].equals("WfField.FieldName"))
						wfRdField.setFieldName(rs.getString("FieldName"));
					
					else if(_fields[i].equals("FieldType") || _fields[i].equals("WfField.FieldType"))
						wfRdField.setFieldType(new Integer(rs.getInt("FieldType")));
					else if(_fields[i].equals("IsNull") || _fields[i].equals("WfField.IsNull")){
						if(rs.getInt("IsNull")==0){
							wfRdField.setIsNull("notNull");
						}
					}
					
					else if(_fields[i].equals("RowId") || _fields[i].equals("WfRdField.RowId"))
						wfRdField.setRowId(rs.getInt("RowId"));
					else if(_fields[i].equals("StepId") || _fields[i].equals("WfField.StepId"))
						wfRdField.setStepId(rs.getInt("StepId"));
					else if(_fields[i].equals("IsEdit"))
						wfRdField.setIsEdit(rs.getInt("IsEdit"));
					
					
					
					else if(_fields[i].equals("NeedFilledOnAPP") || _fields[i].equals("WfField.NeedFilledOnAPP"))
						wfRdField.setNeedFilledOnAPP(new Integer(rs.getInt("NeedFilledOnAPP")));
					else if(_fields[i].equals("FieldTitle") || _fields[i].equals("WfField.FieldTitle"))
						wfRdField.setFieldTitle(rs.getString("FieldTitle"));
					

				}
				list.add(wfRdField);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdFieldHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}