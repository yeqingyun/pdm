package zrprjt.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrprjt.vo.TaskMsg;

public class BasicTaskMsgHelper implements SqlHelper {
	public String getSqlString() {
		return " from TaskMsg where 1=1";
	}

	public String getOrderBy() {
		return " order by TaskMsg.MsgId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((TaskMsg)object);
	}

	public String getSql4Amount(TaskMsg taskMsg) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(taskMsg);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((TaskMsg)object);
	}

	public String getSql4Delete(TaskMsg taskMsg) {
		return "delete from TaskMsg where 1=1"+getSqlCondition(taskMsg);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((TaskMsg)object,fields);
	}

	public String getSql4All(TaskMsg taskMsg, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(taskMsg)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((TaskMsg)object,pageVO,fields);
	}

	public String getSql4Pages(TaskMsg taskMsg,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" TaskMsg.MsgId "+ getSqlString()+getSqlCondition(taskMsg)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(taskMsg)+" and TaskMsg.MsgId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((TaskMsg)object);
	}

	public String getSqlCondition(TaskMsg taskMsg) {
		String sql = "";
		if(null != taskMsg.getMsgId())
			sql += " and TaskMsg.MsgId = '"+taskMsg.getMsgId()+"'";
		if(null != taskMsg.getStatus())
			sql += " and TaskMsg.Status = '"+taskMsg.getStatus()+"'";
		if(null != taskMsg.getCreateBy())
			sql += " and TaskMsg.CreateBy = '"+taskMsg.getCreateBy()+"'";
		if(null != taskMsg.getLastUpd())
			sql += " and TaskMsg.LastUpd = '"+taskMsg.getLastUpd()+"'";
		if(null != taskMsg.getStartCreateDate()) 
			sql += " and TaskMsg.CreateDate >= '"+GenericUtil.dateFormat(taskMsg.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != taskMsg.getEndCreateDate()) 
			sql += " and TaskMsg.CreateDate <= '"+GenericUtil.dateFormat(taskMsg.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != taskMsg.getStartLastDate()) 
			sql += " and TaskMsg.LastDate >= '"+GenericUtil.dateFormat(taskMsg.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != taskMsg.getEndLastDate()) 
			sql += " and TaskMsg.LastDate <= '"+GenericUtil.dateFormat(taskMsg.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != taskMsg.getReply() && !taskMsg.getReply().trim().equals(""))
			sql += " and TaskMsg.Reply = '"+taskMsg.getReply().trim()+"'";
		if(null != taskMsg.getTaskNo() && !taskMsg.getTaskNo().trim().equals(""))
			sql += " and TaskMsg.TaskNo = '"+taskMsg.getTaskNo().trim()+"'";
		if(null != taskMsg.getPrjtNo() && !taskMsg.getPrjtNo().trim().equals(""))
			sql += " and TaskMsg.PrjtNo = '"+taskMsg.getPrjtNo().trim()+"'";
		if(null != taskMsg.getWfNo() && !taskMsg.getWfNo().trim().equals(""))
			sql += " and TaskMsg.WfNo = '"+taskMsg.getWfNo().trim()+"'";
		if(null != taskMsg.getTitle() && !taskMsg.getTitle().trim().equals(""))
			sql += " and TaskMsg.Title = '"+taskMsg.getTitle().trim()+"'";

		return sql;
	}

	public List<TaskMsg> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<TaskMsg> list = new ArrayList<TaskMsg>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				TaskMsg taskMsg = new TaskMsg();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MsgId") || _fields[i].equals("TaskMsg.MsgId"))
						taskMsg.setMsgId(rs.getInt("MsgId"));
					if(_fields[i].equals("Status") || _fields[i].equals("TaskMsg.Status"))
						taskMsg.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("TaskMsg.CreateBy"))
						taskMsg.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("TaskMsg.LastUpd"))
						taskMsg.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("TaskMsg.CreateDate"))
						taskMsg.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("TaskMsg.LastDate"))
						taskMsg.setLastDate(rs.getTimestamp("LastDate"));
					if(_fields[i].equals("Reply") || _fields[i].equals("TaskMsg.Reply"))
						taskMsg.setReply(rs.getString("Reply"));
					if(_fields[i].equals("TaskNo") || _fields[i].equals("TaskMsg.TaskNo"))
						taskMsg.setTaskNo(rs.getString("TaskNo"));
					if(_fields[i].equals("PrjtNo") || _fields[i].equals("TaskMsg.PrjtNo"))
						taskMsg.setPrjtNo(rs.getString("PrjtNo"));
					if(_fields[i].equals("WfNo") || _fields[i].equals("TaskMsg.WfNo"))
						taskMsg.setWfNo(rs.getString("WfNo"));
					if(_fields[i].equals("Title") || _fields[i].equals("TaskMsg.Title"))
						taskMsg.setTitle(rs.getString("Title"));

				}
				list.add(taskMsg);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskMsgHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into TaskMsg("+fields.replaceAll("TaskMsg\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(TaskMsg taskMsg,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MsgId") || _fields[i].equals("TaskMsg.MsgId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, taskMsg.getMsgId());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("TaskMsg.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, taskMsg.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("TaskMsg.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, taskMsg.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("TaskMsg.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, taskMsg.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("TaskMsg.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(taskMsg.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("TaskMsg.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(taskMsg.getLastDate().getTime()));
					}
					else if(_fields[i].equals("Reply") || _fields[i].equals("TaskMsg.Reply")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, taskMsg.getReply());
					}
					else if(_fields[i].equals("TaskNo") || _fields[i].equals("TaskMsg.TaskNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, taskMsg.getTaskNo());
					}
					else if(_fields[i].equals("PrjtNo") || _fields[i].equals("TaskMsg.PrjtNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, taskMsg.getPrjtNo());
					}
					else if(_fields[i].equals("WfNo") || _fields[i].equals("TaskMsg.WfNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, taskMsg.getWfNo());
					}
					else if(_fields[i].equals("Title") || _fields[i].equals("TaskMsg.Title")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, taskMsg.getTitle());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("TaskMsgHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update TaskMsg set ";
		String[] _fields = fields.replaceAll("TaskMsg\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("TaskMsg.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("TaskMsg.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("TaskMsg.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("TaskMsg.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("TaskMsg.LastDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Reply") || _fields[i].equals("TaskMsg.Reply"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("WfNo") || _fields[i].equals("TaskMsg.WfNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Title") || _fields[i].equals("TaskMsg.Title"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(TaskMsg taskMsg,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("TaskMsg.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, taskMsg.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("TaskMsg.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, taskMsg.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("TaskMsg.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, taskMsg.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("TaskMsg.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(taskMsg.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("TaskMsg.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(taskMsg.getLastDate().getTime()));
					}
					else if(_fields[i].equals("Reply") || _fields[i].equals("TaskMsg.Reply")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, taskMsg.getReply());
					}
					else if(_fields[i].equals("WfNo") || _fields[i].equals("TaskMsg.WfNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, taskMsg.getWfNo());
					}
					else if(_fields[i].equals("Title") || _fields[i].equals("TaskMsg.Title")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, taskMsg.getTitle());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("TaskMsgHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(TaskMsg taskMsg) {
		String _fields = "";
		if(null != taskMsg.getMsgId())
			_fields += "TaskMsg.MsgId,";
		if(null != taskMsg.getStatus())
			_fields += "TaskMsg.Status,";
		if(null != taskMsg.getCreateBy())
			_fields += "TaskMsg.CreateBy,";
		if(null != taskMsg.getLastUpd())
			_fields += "TaskMsg.LastUpd,";
		if(null != taskMsg.getCreateDate())
			_fields += "TaskMsg.CreateDate,";
		if(null != taskMsg.getLastDate())
			_fields += "TaskMsg.LastDate,";
		if(null != taskMsg.getReply())
			_fields += "TaskMsg.Reply,";
		if(null != taskMsg.getTaskNo())
			_fields += "TaskMsg.TaskNo,";
		if(null != taskMsg.getPrjtNo())
			_fields += "TaskMsg.PrjtNo,";
		if(null != taskMsg.getWfNo())
			_fields += "TaskMsg.WfNo,";
		if(null != taskMsg.getTitle())
			_fields += "TaskMsg.Title,";

		return _fields.substring(0, _fields.length()-1);
	}
}