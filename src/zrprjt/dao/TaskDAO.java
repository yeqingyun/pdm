package zrprjt.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrprjt.dao.helper.TaskHelper;
import zrprjt.vo.Task;

public class TaskDAO extends BasicDAO implements GenericDAO {

	private TaskHelper taskHelper = new TaskHelper();

	public void insert(Object task) throws java.sql.SQLException {
		Task _task = (Task)task;
		String fields = taskHelper.getFields(_task);
		String sql = taskHelper.getInsertSql(fields);
		try {
			taskHelper.pstmtInsert(_task, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskDAO.insert", e);
			throw e;
		}
	}

	public void update(Object task) throws java.sql.SQLException {
		Task _task = (Task)task;
		String fields = taskHelper.getFields(_task);
		String sql = taskHelper.getUpdateSql(fields, "Task.TaskNo" ,_task.getTaskNo().toString());
		try {
			taskHelper.pstmtUpdate(_task, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskDAO.update", e);
			throw e;
		}
	}

	public List<Task> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return taskHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<Task> list = taskHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (Task)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskDAO.load", e);
			throw e;
		}
	}

	public Object load(Object task) throws java.sql.SQLException {
		Task _task = (Task)task;
		String sql = "select "+Task.ALL_FIELDS+taskHelper.getSqlString()+" and Task.TaskNo = '"+_task.getTaskNo()+"'";
		try {
			List<Task> list = taskHelper.getQueryList(query(sql),Task.ALL_FIELDS);
			if(list.size() > 0)
				return (Task)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskDAO.load", e);
			throw e;
		}
	}
}