package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfJobUserHelper;
import gnwf.vo.WfJobUser;

public class WfJobUserDAO extends BasicDAO implements GenericDAO {

	private WfJobUserHelper wfJobUserHelper = new WfJobUserHelper();

	public void insert(Object wfJobUser) throws java.sql.SQLException {
		WfJobUser _wfJobUser = (WfJobUser)wfJobUser;
		String fields = wfJobUserHelper.getFields(_wfJobUser);
		String sql = wfJobUserHelper.getInsertSql(fields);
		try {
			wfJobUserHelper.pstmtInsert(_wfJobUser, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfJobUserDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfJobUser) throws java.sql.SQLException {
		WfJobUser _wfJobUser = (WfJobUser)wfJobUser;
		String fields = wfJobUserHelper.getFields(_wfJobUser);
		String sql = wfJobUserHelper.getUpdateSql(fields, "WfJobUser.UserId" ,_wfJobUser.getUserId().toString());
		try {
			wfJobUserHelper.pstmtUpdate(_wfJobUser, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfJobUserDAO.update", e);
			throw e;
		}
	}

	public List<WfJobUser> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfJobUserHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfJobUserDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfJobUser> list = wfJobUserHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfJobUser)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfJobUserDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfJobUser) throws java.sql.SQLException {
		WfJobUser _wfJobUser = (WfJobUser)wfJobUser;
		String sql = "select "+WfJobUser.ALL_FIELDS+wfJobUserHelper.getSqlString()+" and WfJobUser.UserId = '"+_wfJobUser.getUserId()+"'";
		try {
			List<WfJobUser> list = wfJobUserHelper.getQueryList(query(sql),WfJobUser.ALL_FIELDS);
			if(list.size() > 0)
				return (WfJobUser)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfJobUserDAO.load", e);
			throw e;
		}
	}
}