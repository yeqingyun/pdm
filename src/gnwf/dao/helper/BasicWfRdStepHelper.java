package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfRdStep;

public class BasicWfRdStepHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfRdStep where 1=1";
	}

	public String getOrderBy() {
		return " order by WfRdStep.StepId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfRdStep)object);
	}

	public String getSql4Amount(WfRdStep wfRdStep) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfRdStep);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfRdStep)object);
	}

	public String getSql4Delete(WfRdStep wfRdStep) {
		return "delete from WfRdStep where 1=1"+getSqlCondition(wfRdStep);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfRdStep)object,fields);
	}

	public String getSql4All(WfRdStep wfRdStep, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfRdStep)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfRdStep)object,pageVO,fields);
	}

	public String getSql4Pages(WfRdStep wfRdStep,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfRdStep.StepId "+ getSqlString()+getSqlCondition(wfRdStep)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfRdStep)+" and WfRdStep.StepId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfRdStep)object);
	}

	public String getSqlCondition(WfRdStep wfRdStep) {
		String sql = "";
		if(null != wfRdStep.getStepId())
			sql += " and WfRdStep.StepId = '"+wfRdStep.getStepId()+"'";
		if(null != wfRdStep.getUserId())
			sql += " and WfRdStep.UserId = '"+wfRdStep.getUserId()+"'";
		if(null != wfRdStep.getStepUser())
			sql += " and WfRdStep.StepUser = '"+wfRdStep.getStepUser()+"'";
		if(null != wfRdStep.getFlowId() && !wfRdStep.getFlowId().trim().equals(""))
			sql += " and WfRdStep.FlowId = '"+wfRdStep.getFlowId().trim()+"'";
		if(null != wfRdStep.getStartCreateDate()) 
			sql += " and WfRdStep.CreateDate >= '"+GenericUtil.dateFormat(wfRdStep.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRdStep.getEndCreateDate()) 
			sql += " and WfRdStep.CreateDate <= '"+GenericUtil.dateFormat(wfRdStep.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRdStep.getStartLastUpdDate()) 
			sql += " and WfRdStep.LastUpdDate >= '"+GenericUtil.dateFormat(wfRdStep.getStartLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRdStep.getEndLastUpdDate()) 
			sql += " and WfRdStep.LastUpdDate <= '"+GenericUtil.dateFormat(wfRdStep.getEndLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";

		return sql;
	}

	public List<WfRdStep> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfRdStep> list = new ArrayList<WfRdStep>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfRdStep wfRdStep = new WfRdStep();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("StepId") || _fields[i].equals("WfRdStep.StepId"))
						wfRdStep.setStepId(rs.getInt("StepId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfRdStep.UserId"))
						wfRdStep.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("StepUser") || _fields[i].equals("WfRdStep.StepUser"))
						wfRdStep.setStepUser(rs.getInt("StepUser"));
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfRdStep.FlowId"))
						wfRdStep.setFlowId(rs.getString("FlowId"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRdStep.CreateDate"))
						wfRdStep.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfRdStep.LastUpdDate"))
						wfRdStep.setLastUpdDate(rs.getTimestamp("LastUpdDate"));

				}
				list.add(wfRdStep);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdStepHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfRdStep("+fields.replaceAll("WfRdStep\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfRdStep wfRdStep,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("StepId") || _fields[i].equals("WfRdStep.StepId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdStep.getStepId());
					}
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfRdStep.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdStep.getUserId());
					}
					else if(_fields[i].equals("StepUser") || _fields[i].equals("WfRdStep.StepUser")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdStep.getStepUser());
					}
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfRdStep.FlowId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRdStep.getFlowId());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRdStep.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfRdStep.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfRdStep.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfRdStep.getLastUpdDate().getTime()));
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfRdStepHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfRdStep set ";
		String[] _fields = fields.replaceAll("WfRdStep\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRdStep.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfRdStep.LastUpdDate"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfRdStep wfRdStep,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRdStep.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfRdStep.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfRdStep.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfRdStep.getLastUpdDate().getTime()));
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfRdStepHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfRdStep wfRdStep) {
		String _fields = "";
		if(null != wfRdStep.getStepId())
			_fields += "WfRdStep.StepId,";
		if(null != wfRdStep.getUserId())
			_fields += "WfRdStep.UserId,";
		if(null != wfRdStep.getStepUser())
			_fields += "WfRdStep.StepUser,";
		if(null != wfRdStep.getFlowId())
			_fields += "WfRdStep.FlowId,";
		if(null != wfRdStep.getCreateDate())
			_fields += "WfRdStep.CreateDate,";
		if(null != wfRdStep.getLastUpdDate())
			_fields += "WfRdStep.LastUpdDate,";

		return _fields.substring(0, _fields.length()-1);
	}
}