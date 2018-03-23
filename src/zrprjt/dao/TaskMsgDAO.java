package zrprjt.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrprjt.dao.helper.TaskMsgHelper;
import zrprjt.vo.TaskMsg;

public class TaskMsgDAO extends BasicDAO implements GenericDAO {

	private TaskMsgHelper taskMsgHelper = new TaskMsgHelper();

	public void insert(Object taskMsg) throws java.sql.SQLException {
		TaskMsg _taskMsg = (TaskMsg)taskMsg;
		String fields = taskMsgHelper.getFields(_taskMsg);
		String sql = taskMsgHelper.getInsertSql(fields);
		try {
			taskMsgHelper.pstmtInsert(_taskMsg, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskMsgDAO.insert", e);
			throw e;
		}
	}

	public void update(Object taskMsg) throws java.sql.SQLException {
		TaskMsg _taskMsg = (TaskMsg)taskMsg;
		String fields = taskMsgHelper.getFields(_taskMsg);
		String sql = taskMsgHelper.getUpdateSql(fields, "TaskMsg.MsgId" ,_taskMsg.getMsgId().toString());
		try {
			taskMsgHelper.pstmtUpdate(_taskMsg, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskMsgDAO.update", e);
			throw e;
		}
	}

	public List<TaskMsg> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return taskMsgHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskMsgDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<TaskMsg> list = taskMsgHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (TaskMsg)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskMsgDAO.load", e);
			throw e;
		}
	}

	public Object load(Object taskMsg) throws java.sql.SQLException {
		TaskMsg _taskMsg = (TaskMsg)taskMsg;
		String sql = "select "+TaskMsg.ALL_FIELDS+taskMsgHelper.getSqlString()+" and TaskMsg.MsgId = '"+_taskMsg.getMsgId()+"'";
		try {
			List<TaskMsg> list = taskMsgHelper.getQueryList(query(sql),TaskMsg.ALL_FIELDS);
			if(list.size() > 0)
				return (TaskMsg)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskMsgDAO.load", e);
			throw e;
		}
	}
}