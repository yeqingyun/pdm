package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfRdTask;

public class BasicWfRdTaskHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfRdTask where 1=1";
	}

	public String getOrderBy() {
		return " order by WfRdTask.TaskId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfRdTask)object);
	}

	public String getSql4Amount(WfRdTask wfRdTask) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfRdTask);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfRdTask)object);
	}

	public String getSql4Delete(WfRdTask wfRdTask) {
		return "delete from WfRdTask where 1=1"+getSqlCondition(wfRdTask);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfRdTask)object,fields);
	}

	public String getSql4All(WfRdTask wfRdTask, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfRdTask)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfRdTask)object,pageVO,fields);
	}

	public String getSql4Pages(WfRdTask wfRdTask,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfRdTask.TaskId "+ getSqlString()+getSqlCondition(wfRdTask)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfRdTask)+" and WfRdTask.TaskId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfRdTask)object);
	}

	public String getSqlCondition(WfRdTask wfRdTask) {
		String sql = "";
		if(null != wfRdTask.getTaskId())
			sql += " and WfRdTask.TaskId = '"+wfRdTask.getTaskId()+"'";
		if(null != wfRdTask.getPreTaskId())
			sql += " and WfRdTask.PreTaskId = '"+wfRdTask.getPreTaskId()+"'";
		if(null != wfRdTask.getStepId())
			sql += " and WfRdTask.StepId = '"+wfRdTask.getStepId()+"'";
		if(null != wfRdTask.getCreateBy())
			sql += " and WfRdTask.CreateBy = '"+wfRdTask.getCreateBy()+"'";
		if(null != wfRdTask.getAcceptBy())
			sql += " and WfRdTask.AcceptBy = '"+wfRdTask.getAcceptBy()+"'";
		if(null != wfRdTask.getAgentBy())
			sql += " and WfRdTask.AgentBy = '"+wfRdTask.getAgentBy()+"'";
		if(null != wfRdTask.getTaskType())
			sql += " and WfRdTask.TaskType = '"+wfRdTask.getTaskType()+"'";
		if(null != wfRdTask.getStatus())
			sql += " and WfRdTask.Status = '"+wfRdTask.getStatus()+"'";
		if(null != wfRdTask.getIsSystemFinsh())
			sql += " and WfRdTask.IsSystemFinsh = '"+wfRdTask.getIsSystemFinsh()+"'";
		if(null != wfRdTask.getWfNo() && !wfRdTask.getWfNo().trim().equals(""))
			sql += " and WfRdTask.WfNo = '"+wfRdTask.getWfNo().trim()+"'";
		if(null != wfRdTask.getStartReqDate()) 
			sql += " and WfRdTask.ReqDate >= '"+GenericUtil.dateFormat(wfRdTask.getStartReqDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRdTask.getEndReqDate()) 
			sql += " and WfRdTask.ReqDate <= '"+GenericUtil.dateFormat(wfRdTask.getEndReqDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRdTask.getStartCreateDate()) 
			sql += " and WfRdTask.CreateDate >= '"+GenericUtil.dateFormat(wfRdTask.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRdTask.getEndCreateDate()) 
			sql += " and WfRdTask.CreateDate <= '"+GenericUtil.dateFormat(wfRdTask.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRdTask.getStartAcceptDate()) 
			sql += " and WfRdTask.AcceptDate >= '"+GenericUtil.dateFormat(wfRdTask.getStartAcceptDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRdTask.getEndAcceptDate()) 
			sql += " and WfRdTask.AcceptDate <= '"+GenericUtil.dateFormat(wfRdTask.getEndAcceptDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRdTask.getStartAgentDate()) 
			sql += " and WfRdTask.AgentDate >= '"+GenericUtil.dateFormat(wfRdTask.getStartAgentDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRdTask.getEndAgentDate()) 
			sql += " and WfRdTask.AgentDate <= '"+GenericUtil.dateFormat(wfRdTask.getEndAgentDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRdTask.getStartEndDate()) 
			sql += " and WfRdTask.EndDate >= '"+GenericUtil.dateFormat(wfRdTask.getStartEndDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRdTask.getEndEndDate()) 
			sql += " and WfRdTask.EndDate <= '"+GenericUtil.dateFormat(wfRdTask.getEndEndDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRdTask.getRemark() && !wfRdTask.getRemark().trim().equals(""))
			sql += " and WfRdTask.Remark = '"+wfRdTask.getRemark().trim()+"'";

		return sql;
	}

	public List<WfRdTask> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfRdTask> list = new ArrayList<WfRdTask>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfRdTask wfRdTask = new WfRdTask();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("TaskId") || _fields[i].equals("WfRdTask.TaskId"))
						wfRdTask.setTaskId(rs.getInt("TaskId"));
					else if(_fields[i].equals("PreTaskId") || _fields[i].equals("WfRdTask.PreTaskId"))
						wfRdTask.setPreTaskId(rs.getInt("PreTaskId"));
					else if(_fields[i].equals("StepId") || _fields[i].equals("WfRdTask.StepId"))
						wfRdTask.setStepId(rs.getInt("StepId"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfRdTask.CreateBy"))
						wfRdTask.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("AcceptBy") || _fields[i].equals("WfRdTask.AcceptBy"))
						wfRdTask.setAcceptBy(rs.getInt("AcceptBy"));
					else if(_fields[i].equals("AgentBy") || _fields[i].equals("WfRdTask.AgentBy"))
						wfRdTask.setAgentBy(rs.getInt("AgentBy"));
					else if(_fields[i].equals("TaskType") || _fields[i].equals("WfRdTask.TaskType"))
						wfRdTask.setTaskType(rs.getInt("TaskType"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfRdTask.Status"))
						wfRdTask.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("IsSystemFinsh") || _fields[i].equals("WfRdTask.IsSystemFinsh"))
						wfRdTask.setIsSystemFinsh(rs.getInt("IsSystemFinsh"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfRdTask.WfNo"))
						wfRdTask.setWfNo(rs.getString("WfNo"));
					else if(_fields[i].equals("ReqDate") || _fields[i].equals("WfRdTask.ReqDate"))
						wfRdTask.setReqDate(rs.getTimestamp("ReqDate"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRdTask.CreateDate"))
						wfRdTask.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("AcceptDate") || _fields[i].equals("WfRdTask.AcceptDate"))
						wfRdTask.setAcceptDate(rs.getTimestamp("AcceptDate"));
					else if(_fields[i].equals("AgentDate") || _fields[i].equals("WfRdTask.AgentDate"))
						wfRdTask.setAgentDate(rs.getTimestamp("AgentDate"));
					else if(_fields[i].equals("EndDate") || _fields[i].equals("WfRdTask.EndDate"))
						wfRdTask.setEndDate(rs.getTimestamp("EndDate"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("WfRdTask.Remark"))
						wfRdTask.setRemark(rs.getString("Remark"));
					
					else if(_fields[i].equals("DocCateId") || _fields[i].equals("WfRdTask.DocCateId"))
						wfRdTask.setDocCateId(rs.getString("DocCateId"));
					
				

				}
				list.add(wfRdTask);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdTaskHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfRdTask("+fields.replaceAll("WfRdTask\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfRdTask wfRdTask,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("TaskId") || _fields[i].equals("WfRdTask.TaskId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdTask.getTaskId());
					}
					else if(_fields[i].equals("PreTaskId") || _fields[i].equals("WfRdTask.PreTaskId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdTask.getPreTaskId());
					}
					else if(_fields[i].equals("StepId") || _fields[i].equals("WfRdTask.StepId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdTask.getStepId());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfRdTask.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdTask.getCreateBy());
					}
					else if(_fields[i].equals("AcceptBy") || _fields[i].equals("WfRdTask.AcceptBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdTask.getAcceptBy());
					}
					else if(_fields[i].equals("AgentBy") || _fields[i].equals("WfRdTask.AgentBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdTask.getAgentBy());
					}
					else if(_fields[i].equals("TaskType") || _fields[i].equals("WfRdTask.TaskType")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdTask.getTaskType());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("WfRdTask.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdTask.getStatus());
					}
					else if(_fields[i].equals("IsSystemFinsh") || _fields[i].equals("WfRdTask.IsSystemFinsh")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdTask.getIsSystemFinsh());
					}
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfRdTask.WfNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRdTask.getWfNo());
					}
					else if(_fields[i].equals("ReqDate") || _fields[i].equals("WfRdTask.ReqDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfRdTask.getReqDate().getTime()));
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRdTask.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfRdTask.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("AcceptDate") || _fields[i].equals("WfRdTask.AcceptDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfRdTask.getAcceptDate().getTime()));
					}
					else if(_fields[i].equals("AgentDate") || _fields[i].equals("WfRdTask.AgentDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfRdTask.getAgentDate().getTime()));
					}
					else if(_fields[i].equals("EndDate") || _fields[i].equals("WfRdTask.EndDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfRdTask.getEndDate().getTime()));
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("WfRdTask.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRdTask.getRemark());
					}
					
					else if(_fields[i].equals("DocCateId") || _fields[i].equals("WfRdTask.DocCateId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRdTask.getDocCateId());
					}
					
					

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfRdTaskHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfRdTask set ";
		String[] _fields = fields.replaceAll("WfRdTask\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("PreTaskId") || _fields[i].equals("WfRdTask.PreTaskId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("WfRdTask.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("AcceptBy") || _fields[i].equals("WfRdTask.AcceptBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("AgentBy") || _fields[i].equals("WfRdTask.AgentBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("TaskType") || _fields[i].equals("WfRdTask.TaskType"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("WfRdTask.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsSystemFinsh") || _fields[i].equals("WfRdTask.IsSystemFinsh"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ReqDate") || _fields[i].equals("WfRdTask.ReqDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRdTask.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("AcceptDate") || _fields[i].equals("WfRdTask.AcceptDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("AgentDate") || _fields[i].equals("WfRdTask.AgentDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("EndDate") || _fields[i].equals("WfRdTask.EndDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("WfRdTask.Remark"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("DocCateId") || _fields[i].equals("WfRdTask.DocCateId"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfRdTask wfRdTask,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("PreTaskId") || _fields[i].equals("WfRdTask.PreTaskId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdTask.getPreTaskId());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfRdTask.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdTask.getCreateBy());
					}
					else if(_fields[i].equals("AcceptBy") || _fields[i].equals("WfRdTask.AcceptBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdTask.getAcceptBy());
					}
					else if(_fields[i].equals("AgentBy") || _fields[i].equals("WfRdTask.AgentBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdTask.getAgentBy());
					}
					else if(_fields[i].equals("TaskType") || _fields[i].equals("WfRdTask.TaskType")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdTask.getTaskType());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("WfRdTask.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdTask.getStatus());
					}
					else if(_fields[i].equals("IsSystemFinsh") || _fields[i].equals("WfRdTask.IsSystemFinsh")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdTask.getIsSystemFinsh());
					}
					else if(_fields[i].equals("ReqDate") || _fields[i].equals("WfRdTask.ReqDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfRdTask.getReqDate().getTime()));
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRdTask.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfRdTask.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("AcceptDate") || _fields[i].equals("WfRdTask.AcceptDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfRdTask.getAcceptDate().getTime()));
					}
					else if(_fields[i].equals("AgentDate") || _fields[i].equals("WfRdTask.AgentDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfRdTask.getAgentDate().getTime()));
					}
					else if(_fields[i].equals("EndDate") || _fields[i].equals("WfRdTask.EndDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfRdTask.getEndDate().getTime()));
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("WfRdTask.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRdTask.getRemark());
					}
					else if(_fields[i].equals("DocCateId") || _fields[i].equals("WfRdTask.DocCateId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRdTask.getDocCateId());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfRdTaskHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfRdTask wfRdTask) {
		String _fields = "";
		if(null != wfRdTask.getTaskId())
			_fields += "WfRdTask.TaskId,";
		if(null != wfRdTask.getPreTaskId())
			_fields += "WfRdTask.PreTaskId,";
		if(null != wfRdTask.getStepId())
			_fields += "WfRdTask.StepId,";
		if(null != wfRdTask.getCreateBy())
			_fields += "WfRdTask.CreateBy,";
		if(null != wfRdTask.getAcceptBy())
			_fields += "WfRdTask.AcceptBy,";
		if(null != wfRdTask.getAgentBy())
			_fields += "WfRdTask.AgentBy,";
		if(null != wfRdTask.getTaskType())
			_fields += "WfRdTask.TaskType,";
		if(null != wfRdTask.getStatus())
			_fields += "WfRdTask.Status,";
		if(null != wfRdTask.getIsSystemFinsh())
			_fields += "WfRdTask.IsSystemFinsh,";
		if(null != wfRdTask.getWfNo())
			_fields += "WfRdTask.WfNo,";
		if(null != wfRdTask.getReqDate())
			_fields += "WfRdTask.ReqDate,";
		if(null != wfRdTask.getCreateDate())
			_fields += "WfRdTask.CreateDate,";
		if(null != wfRdTask.getAcceptDate())
			_fields += "WfRdTask.AcceptDate,";
		if(null != wfRdTask.getAgentDate())
			_fields += "WfRdTask.AgentDate,";
		if(null != wfRdTask.getEndDate())
			_fields += "WfRdTask.EndDate,";
		if(null != wfRdTask.getRemark())
			_fields += "WfRdTask.Remark,";
		if(null != wfRdTask.getDocCateId())
			_fields += "WfRdTask.DocCateId,";
		

		return _fields.substring(0, _fields.length()-1);
	}
}