package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfLeader;

public class BasicWfLeaderHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfLeader where 1=1";
	}

	public String getOrderBy() {
		return " order by WfLeader.FlowId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfLeader)object);
	}

	public String getSql4Amount(WfLeader wfLeader) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfLeader);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfLeader)object);
	}

	public String getSql4Delete(WfLeader wfLeader) {
		return "delete from WfLeader where 1=1"+getSqlCondition(wfLeader);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfLeader)object,fields);
	}

	public String getSql4All(WfLeader wfLeader, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfLeader)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfLeader)object,pageVO,fields);
	}

	public String getSql4Pages(WfLeader wfLeader,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfLeader.FlowId "+ getSqlString()+getSqlCondition(wfLeader)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfLeader)+" and WfLeader.FlowId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfLeader)object);
	}

	public String getSqlCondition(WfLeader wfLeader) {
		String sql = "";
		if(null != wfLeader.getFlowId())
			sql += " and WfLeader.FlowId = '"+wfLeader.getFlowId()+"'";
		if(null != wfLeader.getUserId())
			sql += " and WfLeader.UserId = '"+wfLeader.getUserId()+"'";

		return sql;
	}

	public List<WfLeader> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfLeader> list = new ArrayList<WfLeader>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfLeader wfLeader = new WfLeader();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("FlowId") || _fields[i].equals("WfLeader.FlowId"))
						wfLeader.setFlowId(rs.getInt("FlowId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfLeader.UserId"))
						wfLeader.setUserId(rs.getInt("UserId"));

				}
				list.add(wfLeader);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfLeaderHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfLeader("+fields.replaceAll("WfLeader\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfLeader wfLeader,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("FlowId") || _fields[i].equals("WfLeader.FlowId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfLeader.getFlowId());
					}
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfLeader.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfLeader.getUserId());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfLeaderHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfLeader set ";
		String[] _fields = fields.replaceAll("WfLeader\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfLeader wfLeader,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfLeaderHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfLeader wfLeader) {
		String _fields = "";
		if(null != wfLeader.getFlowId())
			_fields += "WfLeader.FlowId,";
		if(null != wfLeader.getUserId())
			_fields += "WfLeader.UserId,";

		return _fields.substring(0, _fields.length()-1);
	}
}