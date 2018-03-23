package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfDept;

public class BasicWfDeptHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfDept where 1=1";
	}

	public String getOrderBy() {
		return " order by WfDept.FlowId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfDept)object);
	}

	public String getSql4Amount(WfDept wfDept) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfDept);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfDept)object);
	}

	public String getSql4Delete(WfDept wfDept) {
		return "delete from WfDept where 1=1"+getSqlCondition(wfDept);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfDept)object,fields);
	}

	public String getSql4All(WfDept wfDept, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfDept)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfDept)object,pageVO,fields);
	}

	public String getSql4Pages(WfDept wfDept,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfDept.FlowId "+ getSqlString()+getSqlCondition(wfDept)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfDept)+" and WfDept.FlowId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfDept)object);
	}

	public String getSqlCondition(WfDept wfDept) {
		String sql = "";
		if(null != wfDept.getFlowId())
			sql += " and WfDept.FlowId = '"+wfDept.getFlowId()+"'";
		if(null != wfDept.getDeptId())
			sql += " and WfDept.DeptId = '"+wfDept.getDeptId()+"'";

		return sql;
	}

	public List<WfDept> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfDept> list = new ArrayList<WfDept>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfDept wfDept = new WfDept();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("FlowId") || _fields[i].equals("WfDept.FlowId"))
						wfDept.setFlowId(rs.getInt("FlowId"));
					else if(_fields[i].equals("DeptId") || _fields[i].equals("WfDept.DeptId"))
						wfDept.setDeptId(rs.getInt("DeptId"));

				}
				list.add(wfDept);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfDeptHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfDept("+fields.replaceAll("WfDept\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfDept wfDept,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("FlowId") || _fields[i].equals("WfDept.FlowId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfDept.getFlowId());
					}
					else if(_fields[i].equals("DeptId") || _fields[i].equals("WfDept.DeptId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfDept.getDeptId());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfDeptHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfDept set ";
		String[] _fields = fields.replaceAll("WfDept\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfDept wfDept,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfDeptHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfDept wfDept) {
		String _fields = "";
		if(null != wfDept.getFlowId())
			_fields += "WfDept.FlowId,";
		if(null != wfDept.getDeptId())
			_fields += "WfDept.DeptId,";

		return _fields.substring(0, _fields.length()-1);
	}
}