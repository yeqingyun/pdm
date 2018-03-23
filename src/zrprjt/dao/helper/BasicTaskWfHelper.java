package zrprjt.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrprjt.vo.TaskWf;

public class BasicTaskWfHelper implements SqlHelper {
	public String getSqlString() {
		return " from TaskWf where 1=1";
	}

	public String getOrderBy() {
		return " order by TaskWf.SchId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((TaskWf)object);
	}

	public String getSql4Amount(TaskWf taskWf) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(taskWf);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((TaskWf)object);
	}

	public String getSql4Delete(TaskWf taskWf) {
		return "delete from TaskWf where 1=1"+getSqlCondition(taskWf);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((TaskWf)object,fields);
	}

	public String getSql4All(TaskWf taskWf, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(taskWf)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((TaskWf)object,pageVO,fields);
	}

	public String getSql4Pages(TaskWf taskWf,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" TaskWf.SchId "+ getSqlString()+getSqlCondition(taskWf)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(taskWf)+" and TaskWf.SchId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((TaskWf)object);
	}

	public String getSqlCondition(TaskWf taskWf) {
		String sql = "";
		if(null != taskWf.getSchId())
			sql += " and TaskWf.SchId = '"+taskWf.getSchId()+"'";
		if(null != taskWf.getStatus())
			sql += " and TaskWf.Status = '"+taskWf.getStatus()+"'";
		if(null != taskWf.getCreateBy())
			sql += " and TaskWf.CreateBy = '"+taskWf.getCreateBy()+"'";
		if(null != taskWf.getLastUpd())
			sql += " and TaskWf.LastUpd = '"+taskWf.getLastUpd()+"'";
		if(null != taskWf.getStartCreateDate()) 
			sql += " and TaskWf.CreateDate >= '"+GenericUtil.dateFormat(taskWf.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != taskWf.getEndCreateDate()) 
			sql += " and TaskWf.CreateDate <= '"+GenericUtil.dateFormat(taskWf.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != taskWf.getStartLastDate()) 
			sql += " and TaskWf.LastDate >= '"+GenericUtil.dateFormat(taskWf.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != taskWf.getEndLastDate()) 
			sql += " and TaskWf.LastDate <= '"+GenericUtil.dateFormat(taskWf.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != taskWf.getTaskNo() && !taskWf.getTaskNo().trim().equals(""))
			sql += " and TaskWf.TaskNo = '"+taskWf.getTaskNo().trim()+"'";
		if(null != taskWf.getPrjtNo() && !taskWf.getPrjtNo().trim().equals(""))
			sql += " and TaskWf.PrjtNo = '"+taskWf.getPrjtNo().trim()+"'";
		if(null != taskWf.getWfNo() && !taskWf.getWfNo().trim().equals(""))
			sql += " and TaskWf.WfNo = '"+taskWf.getWfNo().trim()+"'";

		return sql;
	}

	public List<TaskWf> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<TaskWf> list = new ArrayList<TaskWf>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				TaskWf taskWf = new TaskWf();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SchId") || _fields[i].equals("TaskWf.SchId"))
						taskWf.setSchId(rs.getInt("SchId"));
					if(_fields[i].equals("Status") || _fields[i].equals("TaskWf.Status"))
						taskWf.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("TaskWf.CreateBy"))
						taskWf.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("TaskWf.LastUpd"))
						taskWf.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("TaskWf.CreateDate"))
						taskWf.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("TaskWf.LastDate"))
						taskWf.setLastDate(rs.getTimestamp("LastDate"));
					if(_fields[i].equals("TaskNo") || _fields[i].equals("TaskWf.TaskNo"))
						taskWf.setTaskNo(rs.getString("TaskNo"));
					if(_fields[i].equals("PrjtNo") || _fields[i].equals("TaskWf.PrjtNo"))
						taskWf.setPrjtNo(rs.getString("PrjtNo"));
					if(_fields[i].equals("WfNo") || _fields[i].equals("TaskWf.WfNo"))
						taskWf.setWfNo(rs.getString("WfNo"));

				}
				list.add(taskWf);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskWfHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into TaskWf("+fields.replaceAll("TaskWf\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(TaskWf taskWf,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SchId") || _fields[i].equals("TaskWf.SchId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, taskWf.getSchId());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("TaskWf.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, taskWf.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("TaskWf.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, taskWf.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("TaskWf.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, taskWf.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("TaskWf.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(taskWf.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("TaskWf.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(taskWf.getLastDate().getTime()));
					}
					else if(_fields[i].equals("TaskNo") || _fields[i].equals("TaskWf.TaskNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, taskWf.getTaskNo());
					}
					else if(_fields[i].equals("PrjtNo") || _fields[i].equals("TaskWf.PrjtNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, taskWf.getPrjtNo());
					}
					else if(_fields[i].equals("WfNo") || _fields[i].equals("TaskWf.WfNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, taskWf.getWfNo());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("TaskWfHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update TaskWf set ";
		String[] _fields = fields.replaceAll("TaskWf\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("TaskWf.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("TaskWf.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("TaskWf.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("TaskWf.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("TaskWf.LastDate"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(TaskWf taskWf,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("TaskWf.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, taskWf.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("TaskWf.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, taskWf.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("TaskWf.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, taskWf.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("TaskWf.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(taskWf.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("TaskWf.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(taskWf.getLastDate().getTime()));
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("TaskWfHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(TaskWf taskWf) {
		String _fields = "";
		if(null != taskWf.getSchId())
			_fields += "TaskWf.SchId,";
		if(null != taskWf.getStatus())
			_fields += "TaskWf.Status,";
		if(null != taskWf.getCreateBy())
			_fields += "TaskWf.CreateBy,";
		if(null != taskWf.getLastUpd())
			_fields += "TaskWf.LastUpd,";
		if(null != taskWf.getCreateDate())
			_fields += "TaskWf.CreateDate,";
		if(null != taskWf.getLastDate())
			_fields += "TaskWf.LastDate,";
		if(null != taskWf.getTaskNo())
			_fields += "TaskWf.TaskNo,";
		if(null != taskWf.getPrjtNo())
			_fields += "TaskWf.PrjtNo,";
		if(null != taskWf.getWfNo())
			_fields += "TaskWf.WfNo,";

		return _fields.substring(0, _fields.length()-1);
	}
}