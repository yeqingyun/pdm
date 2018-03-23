package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfRdTaskHelper;
import gnwf.vo.WfRdTask;

public class WfRdTaskDAO extends BasicDAO implements GenericDAO {

	private WfRdTaskHelper wfRdTaskHelper = new WfRdTaskHelper();

	public void insert(Object wfRdTask) throws java.sql.SQLException {
		WfRdTask _wfRdTask = (WfRdTask)wfRdTask;
		String fields = wfRdTaskHelper.getFields(_wfRdTask);
		String sql = wfRdTaskHelper.getInsertSql(fields);
		System.out.println("sql@@@@@@@@@@@@@@@"+sql);
		try {
			wfRdTaskHelper.pstmtInsert(_wfRdTask, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdTaskDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfRdTask) throws java.sql.SQLException {
		WfRdTask _wfRdTask = (WfRdTask)wfRdTask;
		String fields = wfRdTaskHelper.getFields(_wfRdTask);
		String sql = wfRdTaskHelper.getUpdateSql(fields, "WfRdTask.TaskId" ,_wfRdTask.getTaskId().toString());
		try {
			wfRdTaskHelper.pstmtUpdate(_wfRdTask, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdTaskDAO.update", e);
			throw e;
		}
	}

	public List<WfRdTask> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfRdTaskHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdTaskDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfRdTask> list = wfRdTaskHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfRdTask)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdTaskDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfRdTask) throws java.sql.SQLException {
		WfRdTask _wfRdTask = (WfRdTask)wfRdTask;
		String sql = "select "+WfRdTask.ALL_FIELDS+wfRdTaskHelper.getSqlString()+" and WfRdTask.TaskId = '"+_wfRdTask.getTaskId()+"'";
		try {
			List<WfRdTask> list = wfRdTaskHelper.getQueryList(query(sql),WfRdTask.ALL_FIELDS);
			if(list.size() > 0)
				return (WfRdTask)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdTaskDAO.load", e);
			throw e;
		}
	}
}