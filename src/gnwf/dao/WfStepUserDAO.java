package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfStepUserHelper;
import gnwf.vo.WfStepUser;

public class WfStepUserDAO extends BasicDAO implements GenericDAO {

	private WfStepUserHelper wfStepUserHelper = new WfStepUserHelper();

	public void insert(Object wfStepUser) throws java.sql.SQLException {
		WfStepUser _wfStepUser = (WfStepUser)wfStepUser;
		String fields = wfStepUserHelper.getFields(_wfStepUser);
		String sql = wfStepUserHelper.getInsertSql(fields);
		try {
			wfStepUserHelper.pstmtInsert(_wfStepUser, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepUserDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfStepUser) throws java.sql.SQLException {
		WfStepUser _wfStepUser = (WfStepUser)wfStepUser;
		String fields = wfStepUserHelper.getFields(_wfStepUser);
		String sql = wfStepUserHelper.getUpdateSql(fields, "WfStepUser.StepId" ,_wfStepUser.getStepId().toString());
		try {
			wfStepUserHelper.pstmtUpdate(_wfStepUser, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepUserDAO.update", e);
			throw e;
		}
	}

	public List<WfStepUser> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfStepUserHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepUserDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfStepUser> list = wfStepUserHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfStepUser)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepUserDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfStepUser) throws java.sql.SQLException {
		WfStepUser _wfStepUser = (WfStepUser)wfStepUser;
		String sql = "select "+WfStepUser.ALL_FIELDS+wfStepUserHelper.getSqlString()+" and WfStepUser.StepId = '"+_wfStepUser.getStepId()+"'";
		try {
			List<WfStepUser> list = wfStepUserHelper.getQueryList(query(sql),WfStepUser.ALL_FIELDS);
			if(list.size() > 0)
				return (WfStepUser)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepUserDAO.load", e);
			throw e;
		}
	}
}