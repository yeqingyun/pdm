package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfField;

public class BasicWfFieldHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfField where 1=1";
	}

	public String getOrderBy() {
		return " order by WfField.FieldId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfField)object);
	}

	public String getSql4Amount(WfField wfField) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfField);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfField)object);
	}

	public String getSql4Delete(WfField wfField) {
		return "delete from WfField where 1=1"+getSqlCondition(wfField);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfField)object,fields);
	}

	public String getSql4All(WfField wfField, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfField)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfField)object,pageVO,fields);
	}

	public String getSql4Pages(WfField wfField,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfField.FieldId "+ getSqlString()+getSqlCondition(wfField)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfField)+" and WfField.FieldId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfField)object);
	}

	public String getSqlCondition(WfField wfField) {
		String sql = "";
		if(null != wfField.getFieldId())
			sql += " and WfField.FieldId = '"+wfField.getFieldId()+"'";
		if(null != wfField.getFlowId())
			sql += " and WfField.FlowId = '"+wfField.getFlowId()+"'";
		if(null != wfField.getStepId())
			sql += " and WfField.StepId = '"+wfField.getStepId()+"'";
		if(null != wfField.getFieldType())
			sql += " and WfField.FieldType = '"+wfField.getFieldType()+"'";
		if(null != wfField.getIsGather())
			sql += " and WfField.IsGather = '"+wfField.getIsGather()+"'";
		if(null != wfField.getIsNull())
			sql += " and WfField.IsNull = '"+wfField.getIsNull()+"'";
		if(null != wfField.getStatus())
			sql += " and WfField.Status = '"+wfField.getStatus()+"'";
		if(null != wfField.getCreateBy())
			sql += " and WfField.CreateBy = '"+wfField.getCreateBy()+"'";
		if(null != wfField.getLastUpd())
			sql += " and WfField.LastUpd = '"+wfField.getLastUpd()+"'";
		if(null != wfField.getIsList())
			sql += " and WfField.IsList = '"+wfField.getIsList()+"'";
		if(null != wfField.getStartCreateDate()) 
			sql += " and WfField.CreateDate >= '"+GenericUtil.dateFormat(wfField.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfField.getEndCreateDate()) 
			sql += " and WfField.CreateDate <= '"+GenericUtil.dateFormat(wfField.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfField.getStartLastUpdDate()) 
			sql += " and WfField.LastUpdDate >= '"+GenericUtil.dateFormat(wfField.getStartLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfField.getEndLastUpdDate()) 
			sql += " and WfField.LastUpdDate <= '"+GenericUtil.dateFormat(wfField.getEndLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfField.getFieldCode() && !wfField.getFieldCode().trim().equals(""))
			sql += " and WfField.FieldCode = '"+wfField.getFieldCode().trim()+"'";
		if(null != wfField.getFieldName() && !wfField.getFieldName().trim().equals(""))
			sql += " and WfField.FieldName = '"+wfField.getFieldName().trim()+"'";
		if(null != wfField.getFieldSql() && !wfField.getFieldSql().trim().equals(""))
			sql += " and WfField.FieldSql = '"+wfField.getFieldSql().trim()+"'";
		if(null != wfField.getFieldJs() && !wfField.getFieldJs().trim().equals(""))
			sql += " and WfField.FieldJs = '"+wfField.getFieldJs().trim()+"'";
		if(null != wfField.getDefaultValue() && !wfField.getDefaultValue().trim().equals(""))
			sql += " and WfField.DefaultValue = '"+wfField.getDefaultValue().trim()+"'";
		
		
		if(null != wfField.getFieldTitle() && !wfField.getFieldTitle().trim().equals(""))
			sql += " and WfField.FieldTitle = '"+wfField.getFieldTitle().trim()+"'";
		if(null != wfField.getNeedFilledOnAPP())
			sql += " and WfField.NeedFilledOnAPP = '"+wfField.getNeedFilledOnAPP()+"'";
		
		

		return sql;
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

	public String getInsertSql(String fields) {
		String sql = "insert into WfField("+fields.replaceAll("WfField\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfField wfField,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("FieldId") || _fields[i].equals("WfField.FieldId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfField.getFieldId());
					}
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfField.FlowId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfField.getFlowId());
					}
					else if(_fields[i].equals("StepId") || _fields[i].equals("WfField.StepId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfField.getStepId());
					}
					else if(_fields[i].equals("FieldType") || _fields[i].equals("WfField.FieldType")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfField.getFieldType());
					}
					else if(_fields[i].equals("IsGather") || _fields[i].equals("WfField.IsGather")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfField.getIsGather());
					}
					else if(_fields[i].equals("IsNull") || _fields[i].equals("WfField.IsNull")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfField.getIsNull());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("WfField.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfField.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfField.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfField.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfField.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfField.getLastUpd());
					}
					else if(_fields[i].equals("IsList") || _fields[i].equals("WfField.IsList")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfField.getIsList());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfField.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfField.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfField.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfField.getLastUpdDate().getTime()));
					}
					else if(_fields[i].equals("FieldCode") || _fields[i].equals("WfField.FieldCode")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfField.getFieldCode());
					}
					else if(_fields[i].equals("FieldName") || _fields[i].equals("WfField.FieldName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfField.getFieldName());
					}
					else if(_fields[i].equals("FieldSql") || _fields[i].equals("WfField.FieldSql")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfField.getFieldSql());
					}
					else if(_fields[i].equals("FieldJs") || _fields[i].equals("WfField.FieldJs")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfField.getFieldJs());
					}
					else if(_fields[i].equals("DefaultValue") || _fields[i].equals("WfField.DefaultValue")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfField.getDefaultValue());
					}
					
					else if(_fields[i].equals("FieldTitle") || _fields[i].equals("WfField.FieldTitle")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfField.getFieldTitle());
					}
					else if(_fields[i].equals("NeedFilledOnAPP") || _fields[i].equals("WfField.NeedFilledOnAPP")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfField.getNeedFilledOnAPP());
					}
					
					
				
			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfFieldHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfField set ";
		String[] _fields = fields.replaceAll("WfField\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("StepId") || _fields[i].equals("WfField.StepId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("FlowId") || _fields[i].equals("WfField.FlowId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("FieldType") || _fields[i].equals("WfField.FieldType"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsGather") || _fields[i].equals("WfField.IsGather"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsNull") || _fields[i].equals("WfField.IsNull"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("WfField.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("WfField.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("WfField.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsList") || _fields[i].equals("WfField.IsList"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("WfField.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfField.LastUpdDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("FieldCode") || _fields[i].equals("WfField.FieldCode"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("FieldName") || _fields[i].equals("WfField.FieldName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("FieldSql") || _fields[i].equals("WfField.FieldSql"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("FieldJs") || _fields[i].equals("WfField.FieldJs"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("DefaultValue") || _fields[i].equals("WfField.DefaultValue"))
						sql += _fields[i] + " = ?,";
					
					
					if(_fields[i].equals("FieldTitle") || _fields[i].equals("WfField.FieldTitle"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("NeedFilledOnAPP") || _fields[i].equals("WfField.NeedFilledOnAPP"))
						sql += _fields[i] + " = ?,";
					
					

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfField wfField,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("StepId") || _fields[i].equals("WfField.StepId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfField.getStepId());
					}
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfField.FlowId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfField.getFlowId());
					}
					else if(_fields[i].equals("FieldType") || _fields[i].equals("WfField.FieldType")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfField.getFieldType());
					}
					else if(_fields[i].equals("IsGather") || _fields[i].equals("WfField.IsGather")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfField.getIsGather());
					}
					else if(_fields[i].equals("IsNull") || _fields[i].equals("WfField.IsNull")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfField.getIsNull());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("WfField.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfField.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfField.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfField.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfField.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfField.getLastUpd());
					}
					else if(_fields[i].equals("IsList") || _fields[i].equals("WfField.IsList")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfField.getIsList());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfField.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfField.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfField.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfField.getLastUpdDate().getTime()));
					}
					else if(_fields[i].equals("FieldCode") || _fields[i].equals("WfField.FieldCode")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfField.getFieldCode());
					}
					else if(_fields[i].equals("FieldName") || _fields[i].equals("WfField.FieldName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfField.getFieldName());
					}
					else if(_fields[i].equals("FieldSql") || _fields[i].equals("WfField.FieldSql")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfField.getFieldSql());
					}
					else if(_fields[i].equals("FieldJs") || _fields[i].equals("WfField.FieldJs")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfField.getFieldJs());
					}
					else if(_fields[i].equals("DefaultValue") || _fields[i].equals("WfField.DefaultValue")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfField.getDefaultValue());
					}
					
					else if(_fields[i].equals("FieldTitle") || _fields[i].equals("WfField.FieldTitle")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfField.getFieldTitle());
					}
					else if(_fields[i].equals("NeedFilledOnAPP") || _fields[i].equals("WfField.NeedFilledOnAPP")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfField.getNeedFilledOnAPP());
					}
					
				
			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfFieldHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfField wfField) {
		String _fields = "";
		if(null != wfField.getFieldId())
			_fields += "WfField.FieldId,";
		if(null != wfField.getFlowId())
			_fields += "WfField.FlowId,";
		if(null != wfField.getStepId())
			_fields += "WfField.StepId,";
		if(null != wfField.getFieldType())
			_fields += "WfField.FieldType,";
		if(null != wfField.getIsGather())
			_fields += "WfField.IsGather,";
		if(null != wfField.getIsNull())
			_fields += "WfField.IsNull,";
		if(null != wfField.getStatus())
			_fields += "WfField.Status,";
		if(null != wfField.getCreateBy())
			_fields += "WfField.CreateBy,";
		if(null != wfField.getLastUpd())
			_fields += "WfField.LastUpd,";
		if(null != wfField.getIsList())
			_fields += "WfField.IsList,";
		if(null != wfField.getCreateDate())
			_fields += "WfField.CreateDate,";
		if(null != wfField.getLastUpdDate())
			_fields += "WfField.LastUpdDate,";
		if(null != wfField.getFieldCode())
			_fields += "WfField.FieldCode,";
		if(null != wfField.getFieldName())
			_fields += "WfField.FieldName,";
		if(null != wfField.getFieldSql())
			_fields += "WfField.FieldSql,";
		if(null != wfField.getFieldJs())
			_fields += "WfField.FieldJs,";
		if(null != wfField.getDefaultValue())
			_fields += "WfField.DefaultValue,";
		
		if(null != wfField.getFieldTitle())
			_fields += "WfField.FieldTitle,";
		if(null != wfField.getNeedFilledOnAPP())
			_fields += "WfField.NeedFilledOnAPP,";
		
		

		return _fields.substring(0, _fields.length()-1);
	}
}