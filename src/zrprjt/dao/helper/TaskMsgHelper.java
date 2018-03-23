package zrprjt.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrprjt.vo.TaskMsg;

public class TaskMsgHelper extends BasicTaskMsgHelper {
	public String getSqlString() {
		return " from TaskMsg " +
               " inner join Task on (Task.TaskNo = TaskMsg.TaskNo) " + 

               " where 1=1 ";
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
}