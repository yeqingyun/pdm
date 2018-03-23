package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfFieldStepRelate;

public class BasicWfFieldStepRelateHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfFieldStepRelate where 1=1";
	}

	public String getOrderBy() {
		return " order by WfFieldStepRelate.FieldId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfFieldStepRelate)object);
	}

	public String getSql4Amount(WfFieldStepRelate wfFieldStepRelate) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfFieldStepRelate);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfFieldStepRelate)object);
	}

	public String getSql4Delete(WfFieldStepRelate wfFieldStepRelate) {
		return "delete from WfFieldStepRelate where 1=1"+getSqlCondition(wfFieldStepRelate);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfFieldStepRelate)object,fields);
	}

	public String getSql4All(WfFieldStepRelate wfFieldStepRelate, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfFieldStepRelate)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfFieldStepRelate)object,pageVO,fields);
	}

	public String getSql4Pages(WfFieldStepRelate wfFieldStepRelate,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfFieldStepRelate.FieldId "+ getSqlString()+getSqlCondition(wfFieldStepRelate)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfFieldStepRelate)+" and WfFieldStepRelate.FieldId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfFieldStepRelate)object);
	}

	public String getSqlCondition(WfFieldStepRelate wfFieldStepRelate) {
		String sql = "";
		if(null != wfFieldStepRelate.getFieldId())
			sql += " and WfFieldStepRelate.FieldId = '"+wfFieldStepRelate.getFieldId()+"'";
		if(null != wfFieldStepRelate.getStepId())
			sql += " and WfFieldStepRelate.StepId = '"+wfFieldStepRelate.getStepId()+"'";

		return sql;
	}

	public List<WfFieldStepRelate> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfFieldStepRelate> list = new ArrayList<WfFieldStepRelate>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfFieldStepRelate wfFieldStepRelate = new WfFieldStepRelate();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("FieldId") || _fields[i].equals("WfFieldStepRelate.FieldId"))
						wfFieldStepRelate.setFieldId(rs.getInt("FieldId"));
					else if(_fields[i].equals("StepId") || _fields[i].equals("WfFieldStepRelate.StepId"))
						wfFieldStepRelate.setStepId(rs.getInt("StepId"));

				}
				list.add(wfFieldStepRelate);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfFieldStepRelateHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfFieldStepRelate("+fields.replaceAll("WfFieldStepRelate\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfFieldStepRelate wfFieldStepRelate,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("FieldId") || _fields[i].equals("WfFieldStepRelate.FieldId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfFieldStepRelate.getFieldId());
					}
					else if(_fields[i].equals("StepId") || _fields[i].equals("WfFieldStepRelate.StepId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfFieldStepRelate.getStepId());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfFieldStepRelateHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfFieldStepRelate set ";
		String[] _fields = fields.replaceAll("WfFieldStepRelate\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfFieldStepRelate wfFieldStepRelate,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfFieldStepRelateHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfFieldStepRelate wfFieldStepRelate) {
		String _fields = "";
		if(null != wfFieldStepRelate.getFieldId())
			_fields += "WfFieldStepRelate.FieldId,";
		if(null != wfFieldStepRelate.getStepId())
			_fields += "WfFieldStepRelate.StepId,";

		return _fields.substring(0, _fields.length()-1);
	}
}