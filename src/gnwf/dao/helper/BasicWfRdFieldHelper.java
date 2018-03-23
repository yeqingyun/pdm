package gnwf.dao.helper;

import gnwf.vo.WfRdField;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

public class BasicWfRdFieldHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfRdField where 1=1";
	}

	public String getOrderBy() {
		return " order by WfRdField.FieldId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfRdField)object);
	}

	public String getSql4Amount(WfRdField wfRdField) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfRdField);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfRdField)object);
	}

	public String getSql4Delete(WfRdField wfRdField) {
		return "delete from WfRdField where 1=1"+getSqlCondition(wfRdField);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfRdField)object,fields);
	}

	public String getSql4All(WfRdField wfRdField, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfRdField)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfRdField)object,pageVO,fields);
	}

	public String getSql4Pages(WfRdField wfRdField,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfRdField.FieldId "+ getSqlString()+getSqlCondition(wfRdField)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfRdField)+" and WfRdField.FieldId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfRdField)object);
	}

	public String getSqlCondition(WfRdField wfRdField) {
		String sql = "";
		if(null != wfRdField.getFieldId())
			sql += " and WfRdField.FieldId = '"+wfRdField.getFieldId()+"'";
		if(null != wfRdField.getWfId())
			sql += " and WfRdField.WfId = '"+wfRdField.getWfId()+"'";
		if(null != wfRdField.getWfNo() && !wfRdField.getWfNo().trim().equals(""))
			sql += " and WfRdField.WfNo = '"+wfRdField.getWfNo().trim()+"'";
		if(null != wfRdField.getFieldText() && !wfRdField.getFieldText().trim().equals(""))
			sql += " and WfRdField.FieldText = '"+wfRdField.getFieldText().trim()+"'";

		return sql;
	}

	public List<WfRdField> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfRdField> list = new ArrayList<WfRdField>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfRdField wfRdField = new WfRdField();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("FieldId") || _fields[i].equals("WfRdField.FieldId"))
						wfRdField.setFieldId(rs.getInt("FieldId"));
					else if(_fields[i].equals("WfId") || _fields[i].equals("WfRdField.WfId"))
						wfRdField.setWfId(rs.getInt("WfId"));
					else if(_fields[i].equals("RowId") || _fields[i].equals("WfRdField.RowId"))
						wfRdField.setRowId(rs.getInt("RowId"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfRdField.WfNo"))
						wfRdField.setWfNo(rs.getString("WfNo"));
					else if(_fields[i].equals("FieldText") || _fields[i].equals("WfRdField.FieldText"))
						wfRdField.setFieldText(rs.getString("FieldText"));

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

	public String getInsertSql(String fields) {
		String sql = "insert into WfRdField("+fields.replaceAll("WfRdField\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfRdField wfRdField,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("FieldId") || _fields[i].equals("WfRdField.FieldId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdField.getFieldId());
					}
					else if(_fields[i].equals("WfId") || _fields[i].equals("WfRdField.WfId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdField.getWfId());
					}
					else if(_fields[i].equals("RowId") || _fields[i].equals("WfRdField.RowId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdField.getRowId());
					}
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfRdField.WfNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRdField.getWfNo());
					}
					else if(_fields[i].equals("FieldText") || _fields[i].equals("WfRdField.FieldText")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRdField.getFieldText());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfRdFieldHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfRdField set ";
		String[] _fields = fields.replaceAll("WfRdField\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("RowId") || _fields[i].equals("WfRdField.RowId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("FieldText") || _fields[i].equals("WfRdField.FieldText"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfRdField wfRdField,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("RowId") || _fields[i].equals("WfRdField.RowId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdField.getRowId());
					}
					else if(_fields[i].equals("FieldText") || _fields[i].equals("WfRdField.FieldText")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRdField.getFieldText());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfRdFieldHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfRdField wfRdField) {
		String _fields = "";
		if(null != wfRdField.getFieldId())
			_fields += "WfRdField.FieldId,";
		if(null != wfRdField.getWfId())
			_fields += "WfRdField.WfId,";
		if(0 != wfRdField.getRowId())
			_fields += "WfRdField.RowId,";
		if(null != wfRdField.getWfNo())
			_fields += "WfRdField.WfNo,";
		if(null != wfRdField.getFieldText())
			_fields += "WfRdField.FieldText,";

		return _fields.substring(0, _fields.length()-1);
	}
}