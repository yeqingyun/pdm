package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfJobUserRelteHelper;
import gnwf.vo.WfJobUserRelte;

public class WfJobUserRelteDAO extends BasicDAO implements GenericDAO {

	private WfJobUserRelteHelper wfJobUserRelteHelper = new WfJobUserRelteHelper();

	public void insert(Object wfJobUserRelte) throws java.sql.SQLException {
		WfJobUserRelte _wfJobUserRelte = (WfJobUserRelte)wfJobUserRelte;
		String fields = wfJobUserRelteHelper.getFields(_wfJobUserRelte);
		String sql = wfJobUserRelteHelper.getInsertSql(fields);
		try {
			wfJobUserRelteHelper.pstmtInsert(_wfJobUserRelte, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfJobUserRelteDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfJobUserRelte) throws java.sql.SQLException {
		WfJobUserRelte _wfJobUserRelte = (WfJobUserRelte)wfJobUserRelte;
		String fields = wfJobUserRelteHelper.getFields(_wfJobUserRelte);
		String sql = wfJobUserRelteHelper.getUpdateSql(fields, "WfJobUserRelte.JobId" ,_wfJobUserRelte.getJobId().toString());
		try {
			wfJobUserRelteHelper.pstmtUpdate(_wfJobUserRelte, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfJobUserRelteDAO.update", e);
			throw e;
		}
	}

	public List<WfJobUserRelte> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfJobUserRelteHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfJobUserRelteDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfJobUserRelte> list = wfJobUserRelteHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfJobUserRelte)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfJobUserRelteDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfJobUserRelte) throws java.sql.SQLException {
		WfJobUserRelte _wfJobUserRelte = (WfJobUserRelte)wfJobUserRelte;
		String sql = "select "+WfJobUserRelte.ALL_FIELDS+wfJobUserRelteHelper.getSqlString()+" and WfJobUserRelte.JobId = '"+_wfJobUserRelte.getJobId()+"'";
		try {
			List<WfJobUserRelte> list = wfJobUserRelteHelper.getQueryList(query(sql),WfJobUserRelte.ALL_FIELDS);
			if(list.size() > 0)
				return (WfJobUserRelte)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfJobUserRelteDAO.load", e);
			throw e;
		}
	}
}