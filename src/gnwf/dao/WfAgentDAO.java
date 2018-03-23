package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfAgentHelper;
import gnwf.vo.WfAgent;

public class WfAgentDAO extends BasicDAO implements GenericDAO {

	private WfAgentHelper wfAgentHelper = new WfAgentHelper();

	public void insert(Object wfAgent) throws java.sql.SQLException {
		WfAgent _wfAgent = (WfAgent)wfAgent;
		String fields = wfAgentHelper.getFields(_wfAgent);
		String sql = wfAgentHelper.getInsertSql(fields);
		try {
			wfAgentHelper.pstmtInsert(_wfAgent, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfAgentDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfAgent) throws java.sql.SQLException {
		WfAgent _wfAgent = (WfAgent)wfAgent;
		String fields = wfAgentHelper.getFields(_wfAgent);
		String sql = wfAgentHelper.getUpdateSql(fields, "WfAgent.UserId" ,_wfAgent.getUserId().toString());
		try {
			wfAgentHelper.pstmtUpdate(_wfAgent, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfAgentDAO.update", e);
			throw e;
		}
	}

	public List<WfAgent> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfAgentHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfAgentDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfAgent> list = wfAgentHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfAgent)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfAgentDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfAgent) throws java.sql.SQLException {
		WfAgent _wfAgent = (WfAgent)wfAgent;
		String sql = "select "+WfAgent.ALL_FIELDS+wfAgentHelper.getSqlString()+" and WfAgent.UserId = '"+_wfAgent.getUserId()+"'";
		try {
			List<WfAgent> list = wfAgentHelper.getQueryList(query(sql),WfAgent.ALL_FIELDS);
			if(list.size() > 0)
				return (WfAgent)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfAgentDAO.load", e);
			throw e;
		}
	}
}