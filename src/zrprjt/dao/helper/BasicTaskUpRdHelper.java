package zrprjt.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrprjt.vo.TaskUpRd;


public class BasicTaskUpRdHelper implements SqlHelper {
	public String getSqlString() {
		return " from TaskUpRd where 1=1";
	}

	public String getOrderBy() {
		return " order by TaskUpRd.Version";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((TaskUpRd)object);
	}

	public String getSql4Amount(TaskUpRd taskUpRd) {  
		//System.out.println("select count(*) as amount "+getSqlString()+getSqlCondition(taskUpRd));
		return "select count(*) as amount "+getSqlString()+getSqlCondition(taskUpRd);
	}
	

	public String getSql4Delete(Object object) {
		return getSql4Delete((TaskUpRd)object);
	}

	public String getSql4Delete(TaskUpRd taskUpRd) {  
		return "delete from TaskUpRd where 1=1"+getSqlCondition(taskUpRd);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((TaskUpRd)object,fields);
	}
 
	public String getSql4All(TaskUpRd taskUpRd, String fields) { 
		return "select " + fields + getSqlString()+getSqlCondition(taskUpRd)+getOrderBy(); 
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((TaskUpRd)object,pageVO,fields);
	}

	public String getSql4Pages(TaskUpRd taskUpRd,PageVO pageVO, String fields) {  
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" TaskUpRd.Id"+ getSqlString()+getSqlCondition(taskUpRd)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(taskUpRd)+" and TaskUpRd.Id not in("+mstr+") "+getOrderBy();

		return sql;
	}
	 
	public String getSql4Pages(TaskUpRd taskUpRd,PageVO pageVO, String fields,String sqlString,String conDitionSQl) {
		int pageSize = pageVO.getPageSize(); 
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" TaskUpRd.Id"+ sqlString+conDitionSQl+getOrderBy();
		String sql = "select top "+pageSize+" "+sqlString + conDitionSQl +" and TaskUpRd.Id not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((TaskUpRd)object);
	}

	
	
	public String getSqlCondition(TaskUpRd taskUpRd) {  
		String sql = "";
		if(null != taskUpRd.getVersion())
			sql += " and TaskUpRd.Version = '"+taskUpRd.getVersion()+"'";
		if(null != taskUpRd.getTaskId())
			sql += " and TaskUpRd.TaskId = '"+taskUpRd.getTaskId()+"'";
		if(null != taskUpRd.getTaskId())
			sql += " and TaskUpRd.TaskId = '"+taskUpRd.getTaskId()+"'";
//		
//		if(null != TaskUpRd.getCreateBy())
//			sql += " and TaskUpRd.CreateBy = '"+TaskUpRd.getCreateBy()+"'";
//			sql += " and TaskUpRd.CreateDate <= '"+GenericUtil.dateFormat(TaskUpRd.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
//		if(null != taskUpRd.getUpTyp())
//			sql += " and TaskUpRd.UpTyp = '"+taskUpRd.getUpTyp()+"'";
//		
		
		return sql;
	}

	
	public List<TaskUpRd> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<TaskUpRd> list = new ArrayList<TaskUpRd>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				TaskUpRd TaskUpRd = new TaskUpRd();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Version") || _fields[i].equals("TaskUpRd.Version"))
						TaskUpRd.setVersion(rs.getFloat("Id"));
					if(_fields[i].equals("TaskId") || _fields[i].equals("TaskUpRd.TaskId"))
						TaskUpRd.setTaskId(rs.getInt("TaskId"));
					if(_fields[i].equals("PlanDuration") || _fields[i].equals("TaskUpRd.PlanDuration"))
						TaskUpRd.setPlanDuration(rs.getInt("PlanDuration"));
					if(_fields[i].equals("PlanStaDate") || _fields[i].equals("TaskUpRd.PlanStaDate"))
						TaskUpRd.setPlanStaDate(rs.getTimestamp("PlanStaDate"));
					if(_fields[i].equals("planOveDate") || _fields[i].equals("TaskUpRd.planOveDate"))
						TaskUpRd.setPlanOveDate(rs.getTimestamp("planOveDate"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("TaskUpRd.CreateBy"))
						TaskUpRd.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("TaskUpRd.CreateDate"))
						TaskUpRd.setCreateDate(rs.getTimestamp("CreateDate"));

				}
				list.add(TaskUpRd);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskUpRdHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into TaskUpRd("+fields.replaceAll("TaskUpRd\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}


	
	
	public void pstmtInsert(TaskUpRd taskUpRd,String sql,String fields) throws java.sql.SQLException {
		int index = 0; 
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Version") || _fields[i].equals("TaskUpRd.Version")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setFloat(index, taskUpRd.getVersion());
					}
					else if(_fields[i].equals("TaskId") || _fields[i].equals("TaskUpRd.TaskId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, taskUpRd.getTaskId());
					}
					else if(_fields[i].equals("PlanDuration") || _fields[i].equals("TaskUpRd.PlanDuration")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, taskUpRd.getPlanDuration());
					}
					
					else if(_fields[i].equals("PlanStaDate") || _fields[i].equals("TaskUpRd.PlanStaDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(taskUpRd.getPlanStaDate().getTime()));
					}
					
					else if(_fields[i].equals("PlanOveDate") || _fields[i].equals("TaskUpRd.PlanOveDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(taskUpRd.getPlanOveDate().getTime()));
					}
					
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("TaskUpRd.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, taskUpRd.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("TaskUpRd.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(taskUpRd.getCreateDate().getTime()));
					}
					
					
			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error("TaskUpRdHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		//TODO
//		String sql = "update TaskUpRd set ";
//		String[] _fields = fields.replaceAll("TaskUpRd\\.", "").split(",");
//		for(int i=0;i<_fields.length;i++) {
//					if(_fields[i].equals("Status") || _fields[i].equals("TaskUpRd.Status"))
//						sql += _fields[i] + " = ?,";
//					if(_fields[i].equals("CreateBy") || _fields[i].equals("TaskUpRd.CreateBy"))
//						sql += _fields[i] + " = ?,";
//					if(_fields[i].equals("LastUpd") || _fields[i].equals("TaskUpRd.LastUpd"))
//						sql += _fields[i] + " = ?,";
//					if(_fields[i].equals("CreateDate") || _fields[i].equals("TaskUpRd.CreateDate"))
//						sql += _fields[i] + " = ?,";
//					if(_fields[i].equals("LastDate") || _fields[i].equals("TaskUpRd.LastDate"))
//						sql += _fields[i] + " = ?,";
//					if(_fields[i].equals("Priority") || _fields[i].equals("TaskUpRd.Priority"))
//						sql += _fields[i] + " = ?,";
//					
//					if(_fields[i].equals("Priority") || _fields[i].equals("TaskUpRd.Priority"))
//						sql += _fields[i] + " = ?,";
//					
//					if(_fields[i].equals("PrjtTypId") || _fields[i].equals("TaskUpRd.PrjtTypId"))
//						sql += _fields[i] + " = ?,";
//					
//					if(_fields[i].equals("UsrType") || _fields[i].equals("TaskUpRd.UsrType"))
//						sql += _fields[i] + " = ?,";
//					
//					
//
//
//		}
//		sql = sql.substring(0, sql.length()-1);
//		sql += " where "+key+" = '"+keyValue+"'";
		return null;
	}

	public void pstmtUpdate(TaskUpRd TaskUpRd,String sql,String fields) throws java.sql.SQLException {
		//TODO
//		String[] _fields = fields.split(",");
//		int index = 0;
//		try {
//			DbConnUtil.getDbconn().buildPreparedStatement(sql);
//			for(int i=0;i<_fields.length;i++) {
//					if(_fields[i].equals("Status") || _fields[i].equals("TaskUpRd.Status")) {
//						index++;
//						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, TaskUpRd.getStatus());
//					}
//					else if(_fields[i].equals("CreateBy") || _fields[i].equals("TaskUpRd.CreateBy")) {
//						index++;
//						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, TaskUpRd.getCreateBy());
//					}
//					else if(_fields[i].equals("LastUpd") || _fields[i].equals("TaskUpRd.LastUpd")) {
//						index++;
//						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, TaskUpRd.getLastUpd());
//					}
//					else if(_fields[i].equals("CreateDate") || _fields[i].equals("TaskUpRd.CreateDate")) {
//						index++;
//						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(TaskUpRd.getCreateDate().getTime()));
//					}
//					else if(_fields[i].equals("LastDate") || _fields[i].equals("TaskUpRd.LastDate")) {
//						index++;
//						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(TaskUpRd.getLastDate().getTime()));
//					}
//
//					if(_fields[i].equals("Priority") || _fields[i].equals("TaskUpRd.Priority")) {
//						index++;
//						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, TaskUpRd.getPriority());
//					}
//					
//					if(_fields[i].equals("PrjtTypId") || _fields[i].equals("TaskUpRd.PrjtTypId")) {
//						index++;
//						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, TaskUpRd.getPrjtTypId());
//					}
//					
////					if(_fields[i].equals("UsrType") || _fields[i].equals("TaskUpRd.UsrType")) {
////						index++;
////						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, TaskUpRd.getUsrType());
////					}
//					
//					
//					
//					
//			}
//		DbConnUtil.getDbconn().getCurrentPstmt().execute();
//		}
//		catch(Exception e) {
//			Logger.getLogger(this.getClass()).error("TaskUpRdHelper.pstmtUpdate SQLException", e);
//			throw new java.sql.SQLException();
//		}
	}

	
	//"TaskUpRd.Id,TaskUpRd.TaskId,TaskUpRd.PlanDuration,TaskUpRd.PlanStaDate,TaskUpRd.PlanOveDate,TaskUpRd.CreateBy,TaskUpRd.createDate";

	
	public String getFields(TaskUpRd TaskUpRd) {
		String _fields = "";
		if(null != TaskUpRd.getVersion())
			_fields += "TaskUpRd.Version,";
		if(null != TaskUpRd.getTaskId())
			_fields += "TaskUpRd.TaskId,";
		if(null != TaskUpRd.getPlanDuration())
			_fields += "TaskUpRd.PlanDuration,";
		if(null != TaskUpRd.getPlanStaDate())
			_fields += "TaskUpRd.PlanStaDate,";
		if(null != TaskUpRd.getPlanOveDate())
			_fields += "TaskUpRd.PlanOveDate,";
		if(null != TaskUpRd.getCreateBy())
			_fields += "TaskUpRd.CreateBy,";
		if(null != TaskUpRd.getCreateDate())
			_fields += "TaskUpRd.CreateDate,";

		return _fields.substring(0, _fields.length()-1);
	}
}