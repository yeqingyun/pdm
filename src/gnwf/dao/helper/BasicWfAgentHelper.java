package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfAgent;

public class BasicWfAgentHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfAgent where 1=1";
	}

	public String getOrderBy() {
		return " order by WfAgent.UserId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfAgent)object);
	}

	public String getSql4Amount(WfAgent wfAgent) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfAgent);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfAgent)object);
	}

	public String getSql4Delete(WfAgent wfAgent) {
		return "delete from WfAgent where 1=1"+getSqlCondition(wfAgent);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfAgent)object,fields);
	}

	public String getSql4All(WfAgent wfAgent, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfAgent)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfAgent)object,pageVO,fields);
	}

	public String getSql4Pages(WfAgent wfAgent,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfAgent.UserId "+ getSqlString()+getSqlCondition(wfAgent)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfAgent)+" and WfAgent.UserId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfAgent)object);
	}

	public String getSqlCondition(WfAgent wfAgent) {
		String sql = "";
		if(null != wfAgent.getUserId())
			sql += " and WfAgent.UserId = '"+wfAgent.getUserId()+"'";
		if(null != wfAgent.getAgentId())
			sql += " and WfAgent.AgentId = '"+wfAgent.getAgentId()+"'";
		if(null != wfAgent.getFlowId())
			sql += " and WfAgent.FlowId = '"+wfAgent.getFlowId()+"'";

		return sql;
	}

	public List<WfAgent> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfAgent> list = new ArrayList<WfAgent>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfAgent wfAgent = new WfAgent();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("UserId") || _fields[i].equals("WfAgent.UserId"))
						wfAgent.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("AgentId") || _fields[i].equals("WfAgent.AgentId"))
						wfAgent.setAgentId(rs.getInt("AgentId"));
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfAgent.FlowId"))
						wfAgent.setFlowId(rs.getInt("FlowId"));

				}
				list.add(wfAgent);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfAgentHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfAgent("+fields.replaceAll("WfAgent\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfAgent wfAgent,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("UserId") || _fields[i].equals("WfAgent.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfAgent.getUserId());
					}
					else if(_fields[i].equals("AgentId") || _fields[i].equals("WfAgent.AgentId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfAgent.getAgentId());
					}
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfAgent.FlowId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfAgent.getFlowId());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfAgentHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfAgent set ";
		String[] _fields = fields.replaceAll("WfAgent\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfAgent wfAgent,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfAgentHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfAgent wfAgent) {
		String _fields = "";
		if(null != wfAgent.getUserId())
			_fields += "WfAgent.UserId,";
		if(null != wfAgent.getAgentId())
			_fields += "WfAgent.AgentId,";
		if(null != wfAgent.getFlowId())
			_fields += "WfAgent.FlowId,";

		return _fields.substring(0, _fields.length()-1);
	}
}