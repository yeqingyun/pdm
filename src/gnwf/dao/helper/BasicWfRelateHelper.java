package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfRelate;

public class BasicWfRelateHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfRelate where 1=1";
	}

	public String getOrderBy() {
		return " order by WfRelate.FlowId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfRelate)object);
	}

	public String getSql4Amount(WfRelate wfRelate) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfRelate);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfRelate)object);
	}

	public String getSql4Delete(WfRelate wfRelate) {
		return "delete from WfRelate where 1=1"+getSqlCondition(wfRelate);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfRelate)object,fields);
	}

	public String getSql4All(WfRelate wfRelate, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfRelate)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfRelate)object,pageVO,fields);
	}

	public String getSql4Pages(WfRelate wfRelate,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfRelate.FlowId "+ getSqlString()+getSqlCondition(wfRelate)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfRelate)+" and WfRelate.FlowId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfRelate)object);
	}

	public String getSqlCondition(WfRelate wfRelate) {
		String sql = "";
		if(null != wfRelate.getFlowId())
			sql += " and WfRelate.FlowId = '"+wfRelate.getFlowId()+"'";
		if(null != wfRelate.getRelateId())
			sql += " and WfRelate.RelateId = '"+wfRelate.getRelateId()+"'";

		return sql;
	}

	public List<WfRelate> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfRelate> list = new ArrayList<WfRelate>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfRelate wfRelate = new WfRelate();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("FlowId") || _fields[i].equals("WfRelate.FlowId"))
						wfRelate.setFlowId(rs.getInt("FlowId"));
					else if(_fields[i].equals("RelateId") || _fields[i].equals("WfRelate.RelateId"))
						wfRelate.setRelateId(rs.getInt("RelateId"));

				}
				list.add(wfRelate);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRelateHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfRelate("+fields.replaceAll("WfRelate\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfRelate wfRelate,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("FlowId") || _fields[i].equals("WfRelate.FlowId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRelate.getFlowId());
					}
					else if(_fields[i].equals("RelateId") || _fields[i].equals("WfRelate.RelateId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRelate.getRelateId());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfRelateHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfRelate set ";
		String[] _fields = fields.replaceAll("WfRelate\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfRelate wfRelate,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfRelateHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfRelate wfRelate) {
		String _fields = "";
		if(null != wfRelate.getFlowId())
			_fields += "WfRelate.FlowId,";
		if(null != wfRelate.getRelateId())
			_fields += "WfRelate.RelateId,";

		return _fields.substring(0, _fields.length()-1);
	}
}