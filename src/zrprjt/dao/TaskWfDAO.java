package zrprjt.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrprjt.dao.helper.TaskWfHelper;
import zrprjt.vo.TaskWf;

public class TaskWfDAO extends BasicDAO implements GenericDAO {

	private TaskWfHelper taskWfHelper = new TaskWfHelper();

	public void insert(Object taskWf) throws java.sql.SQLException {
		TaskWf _taskWf = (TaskWf)taskWf;
		String fields = taskWfHelper.getFields(_taskWf);
		String sql = taskWfHelper.getInsertSql(fields);
		try {
			taskWfHelper.pstmtInsert(_taskWf, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskWfDAO.insert", e);
			throw e;
		}
	}

	public void update(Object taskWf) throws java.sql.SQLException {
		TaskWf _taskWf = (TaskWf)taskWf;
		String fields = taskWfHelper.getFields(_taskWf);
		String sql = taskWfHelper.getUpdateSql(fields, "TaskWf.SchId" ,_taskWf.getSchId().toString());
		try {
			taskWfHelper.pstmtUpdate(_taskWf, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskWfDAO.update", e);
			throw e;
		}
	}

	public List<TaskWf> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return taskWfHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskWfDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<TaskWf> list = taskWfHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (TaskWf)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskWfDAO.load", e);
			throw e;
		}
	}

	public Object load(Object taskWf) throws java.sql.SQLException {
		TaskWf _taskWf = (TaskWf)taskWf;
		String sql = "select "+TaskWf.ALL_FIELDS+taskWfHelper.getSqlString()+" and TaskWf.SchId = '"+_taskWf.getSchId()+"'";
		try {
			List<TaskWf> list = taskWfHelper.getQueryList(query(sql),TaskWf.ALL_FIELDS);
			if(list.size() > 0)
				return (TaskWf)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskWfDAO.load", e);
			throw e;
		}
	}
}