package zrprjt.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrprjt.vo.Task;

public class BasicTaskHelper implements SqlHelper {
	public String getSqlString() {
		return " from Task where 1=1";
	}

	public String getOrderBy() {
		return " order by Task.TaskNo";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((Task)object);
	}

	public String getSql4Amount(Task task) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(task);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((Task)object);
	}

	public String getSql4Delete(Task task) {
		return "delete from Task where 1=1"+getSqlCondition(task);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((Task)object,fields);
	}

	public String getSql4All(Task task, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(task)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((Task)object,pageVO,fields);
	}

	public String getSql4Pages(Task task,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" Task.TaskNo "+ getSqlString()+getSqlCondition(task)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(task)+" and Task.TaskNo not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((Task)object);
	}

	public String getSqlCondition(Task task) {
		String sql = "";
		if(null != task.getSchId())
			sql += " and Task.SchId = '"+task.getSchId()+"'";
		if(null != task.getTasker())
			sql += " and Task.Tasker = '"+task.getTasker()+"'";
		if(null != task.getSender())
			sql += " and Task.Sender = '"+task.getSender()+"'";
		if(null != task.getSchNo())
			sql += " and Task.SchNo = '"+task.getSchNo()+"'";
		if(null != task.getLeve())
			sql += " and Task.Leve = '"+task.getLeve()+"'";
		if(null != task.getGrade())
			sql += " and Task.Grade = '"+task.getGrade()+"'";
		if(null != task.getPerce())
			sql += " and Task.Perce = '"+task.getPerce()+"'";
		if(null != task.getStatus())
			sql += " and Task.Status = '"+task.getStatus()+"'";
		if(null != task.getCreateBy())
			sql += " and Task.CreateBy = '"+task.getCreateBy()+"'";
		if(null != task.getLastUpd())
			sql += " and Task.LastUpd = '"+task.getLastUpd()+"'";
		if(null != task.getStartPlanStaDate()) 
			sql += " and Task.PlanStaDate >= '"+GenericUtil.dateFormat(task.getStartPlanStaDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != task.getEndPlanStaDate()) 
			sql += " and Task.PlanStaDate <= '"+GenericUtil.dateFormat(task.getEndPlanStaDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != task.getStartPlanOveDate()) 
			sql += " and Task.PlanOveDate >= '"+GenericUtil.dateFormat(task.getStartPlanOveDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != task.getEndPlanOveDate()) 
			sql += " and Task.PlanOveDate <= '"+GenericUtil.dateFormat(task.getEndPlanOveDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != task.getStartStaDate()) 
			sql += " and Task.StaDate >= '"+GenericUtil.dateFormat(task.getStartStaDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != task.getEndStaDate()) 
			sql += " and Task.StaDate <= '"+GenericUtil.dateFormat(task.getEndStaDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != task.getStartOveDate()) 
			sql += " and Task.OveDate >= '"+GenericUtil.dateFormat(task.getStartOveDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != task.getEndOveDate()) 
			sql += " and Task.OveDate <= '"+GenericUtil.dateFormat(task.getEndOveDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != task.getStartCreateDate()) 
			sql += " and Task.CreateDate >= '"+GenericUtil.dateFormat(task.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != task.getEndCreateDate()) 
			sql += " and Task.CreateDate <= '"+GenericUtil.dateFormat(task.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != task.getStartLastDate()) 
			sql += " and Task.LastDate >= '"+GenericUtil.dateFormat(task.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != task.getEndLastDate()) 
			sql += " and Task.LastDate <= '"+GenericUtil.dateFormat(task.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != task.getTaskNo())
			sql += " and Task.TaskNo = '"+task.getTaskNo()+"'";
		if(null != task.getPrjtNo() && !task.getPrjtNo().trim().equals(""))
			sql += " and Task.PrjtNo = '"+task.getPrjtNo().trim()+"'";
		if(null != task.getParent())
			sql += " and Task.Parent = '"+task.getParent()+"'";
		if(null != task.getTaskNm() && !task.getTaskNm().trim().equals(""))
			sql += " and Task.TaskNm = '"+task.getTaskNm().trim()+"'";
		if(null != task.getRemark() && !task.getRemark().trim().equals(""))
			sql += " and Task.Remark = '"+task.getRemark().trim()+"'";

		if(null != task.getPlanDuration())
			sql += " and Task.PlanDuration = '"+task.getPlanDuration()+"'";
		
		if(null != task.getPredecessorLink() && !task.getPredecessorLink().trim().equals(""))
			sql += " and Task.PredecessorLink = '"+task.getPredecessorLink().trim()+"'";
		if(null != task.getCritical())
			sql += " and Task.Critical = '"+task.getCritical()+"'";
		if(null != task.getSummary())
			sql += " and Task.Summary = '"+task.getSummary()+"'";
		if(null != task.getMilestone())
			sql += " and Task.Milestone = '"+task.getMilestone()+"'";
		
		
		return sql;
	}

	public List<Task> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<Task> list = new ArrayList<Task>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				Task task = new Task();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SchId") || _fields[i].equals("Task.SchId"))
						task.setSchId(rs.getInt("SchId"));
					if(_fields[i].equals("Tasker") || _fields[i].equals("Task.Tasker"))
						task.setTasker(rs.getInt("Tasker"));
					if(_fields[i].equals("Sender") || _fields[i].equals("Task.Sender"))
						task.setSender(rs.getInt("Sender"));
					if(_fields[i].equals("SchNo") || _fields[i].equals("Task.SchNo"))
						task.setSchNo(rs.getInt("SchNo"));
					if(_fields[i].equals("Leve") || _fields[i].equals("Task.Leve"))
						task.setLeve(rs.getInt("Leve"));
					if(_fields[i].equals("Grade") || _fields[i].equals("Task.Grade"))
						task.setGrade(rs.getInt("Grade"));
					if(_fields[i].equals("Perce") || _fields[i].equals("Task.Perce"))
						task.setPerce(rs.getInt("Perce"));
					if(_fields[i].equals("Status") || _fields[i].equals("Task.Status"))
						task.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("Task.CreateBy"))
						task.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("Task.LastUpd"))
						task.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("PlanStaDate") || _fields[i].equals("Task.PlanStaDate"))
						task.setPlanStaDate(rs.getTimestamp("PlanStaDate"));
					if(_fields[i].equals("PlanOveDate") || _fields[i].equals("Task.PlanOveDate"))
						task.setPlanOveDate(rs.getTimestamp("PlanOveDate"));
					if(_fields[i].equals("StaDate") || _fields[i].equals("Task.StaDate"))
						task.setStaDate(rs.getTimestamp("StaDate"));
					if(_fields[i].equals("OveDate") || _fields[i].equals("Task.OveDate"))
						task.setOveDate(rs.getTimestamp("OveDate"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("Task.CreateDate"))
						task.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("Task.LastDate"))
						task.setLastDate(rs.getTimestamp("LastDate"));
					if(_fields[i].equals("TaskNo") || _fields[i].equals("Task.TaskNo"))
						task.setTaskNo(rs.getInt("TaskNo"));
					if(_fields[i].equals("PrjtNo") || _fields[i].equals("Task.PrjtNo"))
						task.setPrjtNo(rs.getString("PrjtNo"));
					if(_fields[i].equals("Parent") || _fields[i].equals("Task.Parent"))
						task.setParent(rs.getInt("Parent"));
					if(_fields[i].equals("TaskNm") || _fields[i].equals("Task.TaskNm"))
						task.setTaskNm(rs.getString("TaskNm"));
					if(_fields[i].equals("Remark") || _fields[i].equals("Task.Remark"))
						task.setRemark(rs.getString("Remark"));

					if(_fields[i].equals("PlanDuration") || _fields[i].equals("Task.PlanDuration"))
						task.setPlanDuration(rs.getInt("PlanDuration"));
					
					if(_fields[i].equals("PredecessorLink") || _fields[i].equals("Task.PredecessorLink"))
						task.setPredecessorLink(rs.getString("PredecessorLink"));
					if(_fields[i].equals("Critical") || _fields[i].equals("Task.Critical"))
						task.setCritical(rs.getInt("Critical"));
					if(_fields[i].equals("Summary") || _fields[i].equals("Task.Summary"))
						task.setSummary(rs.getInt("Summary"));
					if(_fields[i].equals("Milestone") || _fields[i].equals("Task.Milestone"))
						task.setMilestone(rs.getInt("Milestone"));
					
				}
				list.add(task);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into Task("+fields.replaceAll("Task\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(Task task,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SchId") || _fields[i].equals("Task.SchId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getSchId());
					}
					else if(_fields[i].equals("Tasker") || _fields[i].equals("Task.Tasker")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getTasker());
					}
					else if(_fields[i].equals("Sender") || _fields[i].equals("Task.Sender")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getSender());
					}
					else if(_fields[i].equals("SchNo") || _fields[i].equals("Task.SchNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getSchNo());
					}
					else if(_fields[i].equals("Leve") || _fields[i].equals("Task.Leve")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getLeve());
					}
					else if(_fields[i].equals("Grade") || _fields[i].equals("Task.Grade")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getGrade());
					}
					else if(_fields[i].equals("Perce") || _fields[i].equals("Task.Perce")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getPerce());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("Task.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("Task.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("Task.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getLastUpd());
					}
					else if(_fields[i].equals("PlanStaDate") || _fields[i].equals("Task.PlanStaDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(task.getPlanStaDate().getTime()));
					}
					else if(_fields[i].equals("PlanOveDate") || _fields[i].equals("Task.PlanOveDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(task.getPlanOveDate().getTime()));
					}
					else if(_fields[i].equals("StaDate") || _fields[i].equals("Task.StaDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(task.getStaDate().getTime()));
					}
					else if(_fields[i].equals("OveDate") || _fields[i].equals("Task.OveDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(task.getOveDate().getTime()));
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("Task.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(task.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("Task.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(task.getLastDate().getTime()));
					}
					else if(_fields[i].equals("TaskNo") || _fields[i].equals("Task.TaskNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getTaskNo());
					}
					else if(_fields[i].equals("PrjtNo") || _fields[i].equals("Task.PrjtNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, task.getPrjtNo());
					}
					else if(_fields[i].equals("Parent") || _fields[i].equals("Task.Parent")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getParent());
					}
					else if(_fields[i].equals("TaskNm") || _fields[i].equals("Task.TaskNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, task.getTaskNm());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("Task.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, task.getRemark());
					}

					else if(_fields[i].equals("PlanDuration") || _fields[i].equals("Task.PlanDuration")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getPlanDuration());
					}
					
					else if(_fields[i].equals("PredecessorLink") || _fields[i].equals("Task.PredecessorLink")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, task.getPredecessorLink());
					}
					
					else if(_fields[i].equals("Critical") || _fields[i].equals("Task.Critical")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getCritical());
					}
					
					else if(_fields[i].equals("Summary") || _fields[i].equals("Task.Summary")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getSummary());
					}
					
					else if(_fields[i].equals("Milestone") || _fields[i].equals("Task.Milestone")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getMilestone());
					}
					
					
			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("TaskHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update Task set ";
		String[] _fields = fields.replaceAll("Task\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Tasker") || _fields[i].equals("Task.Tasker"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Sender") || _fields[i].equals("Task.Sender"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("SchNo") || _fields[i].equals("Task.SchNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Leve") || _fields[i].equals("Task.Leve"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Grade") || _fields[i].equals("Task.Grade"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Perce") || _fields[i].equals("Task.Perce"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("Task.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("Task.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("Task.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("PlanStaDate") || _fields[i].equals("Task.PlanStaDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("PlanOveDate") || _fields[i].equals("Task.PlanOveDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("StaDate") || _fields[i].equals("Task.StaDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("OveDate") || _fields[i].equals("Task.OveDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("Task.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("Task.LastDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Parent") || _fields[i].equals("Task.Parent"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("TaskNm") || _fields[i].equals("Task.TaskNm"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("Task.Remark"))
						sql += _fields[i] + " = ?,";
					
					
					if(_fields[i].equals("PlanDuration") || _fields[i].equals("Task.PlanDuration"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("PredecessorLink") || _fields[i].equals("Task.PredecessorLink"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("Critical") || _fields[i].equals("Task.Critical"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("Summary") || _fields[i].equals("Task.Summary"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("Milestone") || _fields[i].equals("Task.Milestone"))
						sql += _fields[i] + " = ?,";
					
					
					

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(Task task,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Tasker") || _fields[i].equals("Task.Tasker")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getTasker());
					}
					else if(_fields[i].equals("Sender") || _fields[i].equals("Task.Sender")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getSender());
					}
					else if(_fields[i].equals("SchNo") || _fields[i].equals("Task.SchNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getSchNo());
					}
					else if(_fields[i].equals("Leve") || _fields[i].equals("Task.Leve")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getLeve());
					}
					else if(_fields[i].equals("Grade") || _fields[i].equals("Task.Grade")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getGrade());
					}
					else if(_fields[i].equals("Perce") || _fields[i].equals("Task.Perce")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getPerce());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("Task.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("Task.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("Task.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getLastUpd());
					}
					else if(_fields[i].equals("PlanStaDate") || _fields[i].equals("Task.PlanStaDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(task.getPlanStaDate().getTime()));
					}
					else if(_fields[i].equals("PlanOveDate") || _fields[i].equals("Task.PlanOveDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(task.getPlanOveDate().getTime()));
					}
					else if(_fields[i].equals("StaDate") || _fields[i].equals("Task.StaDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(task.getStaDate().getTime()));
					}
					else if(_fields[i].equals("OveDate") || _fields[i].equals("Task.OveDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(task.getOveDate().getTime()));
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("Task.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(task.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("Task.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(task.getLastDate().getTime()));
					}
					else if(_fields[i].equals("Parent") || _fields[i].equals("Task.Parent")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getParent());
					}
					else if(_fields[i].equals("TaskNm") || _fields[i].equals("Task.TaskNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, task.getTaskNm());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("Task.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, task.getRemark());
					}

					else if(_fields[i].equals("PlanDuration") || _fields[i].equals("Task.PlanDuration")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getPlanDuration());
					}
					
					else if(_fields[i].equals("PredecessorLink") || _fields[i].equals("Task.PredecessorLink")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, task.getPredecessorLink());
					}
					else if(_fields[i].equals("Critical") || _fields[i].equals("Task.Critical")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getCritical());
					}
					else if(_fields[i].equals("Summary") || _fields[i].equals("Task.Summary")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getSummary());
					}
					else if(_fields[i].equals("Milestone") || _fields[i].equals("Task.Milestone")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, task.getMilestone());
					}
					
					
					
			}
			//System.out.println(DbConnUtil.getDbconn().getCurrentPstmt().toString());
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("TaskHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(Task task) {
		String _fields = "";
		if(null != task.getSchId())
			_fields += "Task.SchId,";
		if(null != task.getTasker())
			_fields += "Task.Tasker,";
		if(null != task.getSender())
			_fields += "Task.Sender,";
		if(null != task.getSchNo())
			_fields += "Task.SchNo,";
		if(null != task.getLeve())
			_fields += "Task.Leve,";
		if(null != task.getGrade())
			_fields += "Task.Grade,";
		if(null != task.getPerce())
			_fields += "Task.Perce,";
		if(null != task.getStatus())
			_fields += "Task.Status,";
		if(null != task.getCreateBy())
			_fields += "Task.CreateBy,";
		if(null != task.getLastUpd())
			_fields += "Task.LastUpd,";
		if(null != task.getPlanStaDate())
			_fields += "Task.PlanStaDate,";
		if(null != task.getPlanOveDate())
			_fields += "Task.PlanOveDate,";
		if(null != task.getStaDate())
			_fields += "Task.StaDate,";
		if(null != task.getOveDate())
			_fields += "Task.OveDate,";
		if(null != task.getCreateDate())
			_fields += "Task.CreateDate,";
		if(null != task.getLastDate())
			_fields += "Task.LastDate,";
		if(null != task.getTaskNo())
			_fields += "Task.TaskNo,";
		if(null != task.getPrjtNo())
			_fields += "Task.PrjtNo,";
		if(null != task.getParent())
			_fields += "Task.Parent,";
		if(null != task.getTaskNm())
			_fields += "Task.TaskNm,";
		if(null != task.getRemark())
			_fields += "Task.Remark,";
		
		if(null != task.getPlanDuration())
			_fields += "Task.PlanDuration,";
		
		if(null != task.getPredecessorLink())
			_fields += "Task.PredecessorLink,";
		if(null != task.getCritical())
			_fields += "Task.Critical,";
		if(null != task.getSummary())
			_fields += "Task.Summary,";
		if(null != task.getMilestone())
			_fields += "Task.Milestone,";
		

		return _fields.substring(0, _fields.length()-1);
	}
}