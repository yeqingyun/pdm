package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfCfgRelate;

public class BasicWfCfgRelateHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfCfgRelate where 1=1";
	}

	public String getOrderBy() {
		return " order by WfCfgRelate.RelateId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfCfgRelate)object);
	}

	public String getSql4Amount(WfCfgRelate wfCfgRelate) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfCfgRelate);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfCfgRelate)object);
	}

	public String getSql4Delete(WfCfgRelate wfCfgRelate) {
		return "delete from WfCfgRelate where 1=1"+getSqlCondition(wfCfgRelate);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfCfgRelate)object,fields);
	}

	public String getSql4All(WfCfgRelate wfCfgRelate, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfCfgRelate)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfCfgRelate)object,pageVO,fields);
	}

	public String getSql4Pages(WfCfgRelate wfCfgRelate,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfCfgRelate.RelateId "+ getSqlString()+getSqlCondition(wfCfgRelate)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfCfgRelate)+" and WfCfgRelate.RelateId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfCfgRelate)object);
	}

	public String getSqlCondition(WfCfgRelate wfCfgRelate) {
		String sql = "";
		if(null != wfCfgRelate.getRelateId())
			sql += " and WfCfgRelate.RelateId = '"+wfCfgRelate.getRelateId()+"'";
		if(null != wfCfgRelate.getFlowId())
			sql += " and WfCfgRelate.FlowId = '"+wfCfgRelate.getFlowId()+"'";

		return sql;
	}

	public List<WfCfgRelate> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfCfgRelate> list = new ArrayList<WfCfgRelate>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfCfgRelate wfCfgRelate = new WfCfgRelate();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("RelateId") || _fields[i].equals("WfCfgRelate.RelateId"))
						wfCfgRelate.setRelateId(rs.getInt("RelateId"));
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfCfgRelate.FlowId"))
						wfCfgRelate.setFlowId(rs.getInt("FlowId"));

				}
				list.add(wfCfgRelate);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfCfgRelateHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfCfgRelate("+fields.replaceAll("WfCfgRelate\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfCfgRelate wfCfgRelate,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("RelateId") || _fields[i].equals("WfCfgRelate.RelateId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfgRelate.getRelateId());
					}
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfCfgRelate.FlowId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfgRelate.getFlowId());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfCfgRelateHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfCfgRelate set ";
		String[] _fields = fields.replaceAll("WfCfgRelate\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfCfgRelate wfCfgRelate,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfCfgRelateHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfCfgRelate wfCfgRelate) {
		String _fields = "";
		if(null != wfCfgRelate.getRelateId())
			_fields += "WfCfgRelate.RelateId,";
		if(null != wfCfgRelate.getFlowId())
			_fields += "WfCfgRelate.FlowId,";

		return _fields.substring(0, _fields.length()-1);
	}
}