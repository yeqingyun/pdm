package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfLeaderHelper;
import gnwf.vo.WfLeader;

public class WfLeaderDAO extends BasicDAO implements GenericDAO {

	private WfLeaderHelper wfLeaderHelper = new WfLeaderHelper();

	public void insert(Object wfLeader) throws java.sql.SQLException {
		WfLeader _wfLeader = (WfLeader)wfLeader;
		String fields = wfLeaderHelper.getFields(_wfLeader);
		String sql = wfLeaderHelper.getInsertSql(fields);
		try {
			wfLeaderHelper.pstmtInsert(_wfLeader, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfLeaderDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfLeader) throws java.sql.SQLException {
		WfLeader _wfLeader = (WfLeader)wfLeader;
		String fields = wfLeaderHelper.getFields(_wfLeader);
		String sql = wfLeaderHelper.getUpdateSql(fields, "WfLeader.FlowId" ,_wfLeader.getFlowId().toString());
		try {
			wfLeaderHelper.pstmtUpdate(_wfLeader, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfLeaderDAO.update", e);
			throw e;
		}
	}

	public List<WfLeader> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfLeaderHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfLeaderDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfLeader> list = wfLeaderHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfLeader)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfLeaderDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfLeader) throws java.sql.SQLException {
		WfLeader _wfLeader = (WfLeader)wfLeader;
		String sql = "select "+WfLeader.ALL_FIELDS+wfLeaderHelper.getSqlString()+" and WfLeader.FlowId = '"+_wfLeader.getFlowId()+"'";
		try {
			List<WfLeader> list = wfLeaderHelper.getQueryList(query(sql),WfLeader.ALL_FIELDS);
			if(list.size() > 0)
				return (WfLeader)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfLeaderDAO.load", e);
			throw e;
		}
	}
}