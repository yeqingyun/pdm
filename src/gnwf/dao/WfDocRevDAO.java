package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfDocRevHelper;
import gnwf.vo.WfDocRev;

public class WfDocRevDAO extends BasicDAO implements GenericDAO {

	private WfDocRevHelper wfDocRevHelper = new WfDocRevHelper();

	public void insert(Object wfDocRev) throws java.sql.SQLException {
		WfDocRev _wfDocRev = (WfDocRev)wfDocRev;
		String fields = wfDocRevHelper.getFields(_wfDocRev);
		String sql = wfDocRevHelper.getInsertSql(fields);
		try {
			wfDocRevHelper.pstmtInsert(_wfDocRev, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfDocRevDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfDocRev) throws java.sql.SQLException {
		WfDocRev _wfDocRev = (WfDocRev)wfDocRev;
		String fields = wfDocRevHelper.getFields(_wfDocRev);
		String sql = wfDocRevHelper.getUpdateSql(fields, "WfDocRev.DocId" ,_wfDocRev.getDocId().toString());
		try {
			wfDocRevHelper.pstmtUpdate(_wfDocRev, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfDocRevDAO.update", e);
			throw e;
		}
	}

	public List<WfDocRev> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfDocRevHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfDocRevDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfDocRev> list = wfDocRevHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfDocRev)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfDocRevDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfDocRev) throws java.sql.SQLException {
		WfDocRev _wfDocRev = (WfDocRev)wfDocRev;
		String sql = "select "+WfDocRev.ALL_FIELDS+wfDocRevHelper.getSqlString()+" and WfDocRev.DocId = '"+_wfDocRev.getDocId()+"'";
		try {
			List<WfDocRev> list = wfDocRevHelper.getQueryList(query(sql),WfDocRev.ALL_FIELDS);
			if(list.size() > 0)
				return (WfDocRev)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfDocRevDAO.load", e);
			throw e;
		}
	}
}