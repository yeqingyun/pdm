package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfField;

public class WfFieldHelper extends BasicWfFieldHelper {
	public String getSqlString() {
		return " from WfField " +
               " inner join WfCfg on (WfCfg.FlowId = WfField.FlowId) " + 

               " where 1=1 ";
	}

	public List<WfField> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfField> list = new ArrayList<WfField>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfField wfField = new WfField();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("FieldId") || _fields[i].equals("WfField.FieldId"))
						wfField.setFieldId(rs.getInt("FieldId"));
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfField.FlowId"))
						wfField.setFlowId(rs.getInt("FlowId"));
					else if(_fields[i].equals("StepId") || _fields[i].equals("WfField.StepId"))
						wfField.setStepId(rs.getInt("StepId"));
					else if(_fields[i].equals("FieldType") || _fields[i].equals("WfField.FieldType"))
						wfField.setFieldType(rs.getInt("FieldType"));
					else if(_fields[i].equals("IsGather") || _fields[i].equals("WfField.IsGather"))
						wfField.setIsGather(rs.getInt("IsGather"));
					else if(_fields[i].equals("IsNull") || _fields[i].equals("WfField.IsNull"))
						wfField.setIsNull(rs.getInt("IsNull"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfField.Status"))
						wfField.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfField.CreateBy"))
						wfField.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfField.LastUpd"))
						wfField.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("IsList") || _fields[i].equals("WfField.IsList"))
						wfField.setIsList(rs.getInt("IsList"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfField.CreateDate"))
						wfField.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfField.LastUpdDate"))
						wfField.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					else if(_fields[i].equals("FieldCode") || _fields[i].equals("WfField.FieldCode"))
						wfField.setFieldCode(rs.getString("FieldCode"));
					else if(_fields[i].equals("FieldName") || _fields[i].equals("WfField.FieldName"))
						wfField.setFieldName(rs.getString("FieldName"));
					else if(_fields[i].equals("FieldSql") || _fields[i].equals("WfField.FieldSql"))
						wfField.setFieldSql(rs.getString("FieldSql"));
					else if(_fields[i].equals("FieldJs") || _fields[i].equals("WfField.FieldJs"))
						wfField.setFieldJs(rs.getString("FieldJs"));
					else if(_fields[i].equals("DefaultValue") || _fields[i].equals("WfField.DefaultValue"))
						wfField.setDefaultValue(rs.getString("DefaultValue"));

					else if(_fields[i].equals("FlowName") || _fields[i].equals("WfCfg.FlowName as FlowName"))
						wfField.setFlowName(rs.getString("FlowName"));
					
					
					else if(_fields[i].equals("FieldTitle") || _fields[i].equals("WfField.FieldTitle"))
						wfField.setFieldTitle(rs.getString("FieldTitle"));
					else if(_fields[i].equals("NeedFilledOnAPP") || _fields[i].equals("WfField.NeedFilledOnAPP"))
						wfField.setNeedFilledOnAPP(rs.getInt("NeedFilledOnAPP"));
					

				}
				list.add(wfField);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfFieldHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}