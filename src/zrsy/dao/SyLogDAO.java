package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.SyLogHelper;
import zrsy.vo.SyLog;

public class SyLogDAO extends BasicDAO implements GenericDAO {

	private SyLogHelper syLogHelper = new SyLogHelper();

	public void insert(Object syLog) throws java.sql.SQLException {
		SyLog _syLog = (SyLog)syLog;
		String fields = syLogHelper.getFields(_syLog);
		String sql = syLogHelper.getInsertSql(fields);
		try {
			syLogHelper.pstmtInsert(_syLog, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SyLogDAO.insert", e);
			throw e;
		}
	}

	public void update(Object syLog) throws java.sql.SQLException {
		SyLog _syLog = (SyLog)syLog;
		String fields = syLogHelper.getFields(_syLog);
		String sql = syLogHelper.getUpdateSql(fields, "SyLog.LogId" ,_syLog.getLogId().toString());
		try {
			syLogHelper.pstmtUpdate(_syLog, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SyLogDAO.update", e);
			throw e;
		}
	}

	public List<SyLog> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return syLogHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SyLogDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<SyLog> list = syLogHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (SyLog)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SyLogDAO.load", e);
			throw e;
		}
	}

	public Object load(Object syLog) throws java.sql.SQLException {
		SyLog _syLog = (SyLog)syLog;
		String sql = "select "+SyLog.ALL_FIELDS+syLogHelper.getSqlString()+" and SyLog.LogId = '"+_syLog.getLogId()+"'";
		try {
			List<SyLog> list = syLogHelper.getQueryList(query(sql),SyLog.ALL_FIELDS);
			if(list.size() > 0)
				return (SyLog)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SyLogDAO.load", e);
			throw e;
		}
	}
}