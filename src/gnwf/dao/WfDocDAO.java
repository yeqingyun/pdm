package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfDocHelper;
import gnwf.vo.WfDoc;

public class WfDocDAO extends BasicDAO implements GenericDAO {

	private WfDocHelper wfDocHelper = new WfDocHelper();

	public void insert(Object wfDoc) throws java.sql.SQLException {
		WfDoc _wfDoc = (WfDoc)wfDoc;
		String fields = wfDocHelper.getFields(_wfDoc);
		String sql = wfDocHelper.getInsertSql(fields);
		try {
			wfDocHelper.pstmtInsert(_wfDoc, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfDocDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfDoc) throws java.sql.SQLException {
		WfDoc _wfDoc = (WfDoc)wfDoc;
		String fields = wfDocHelper.getFields(_wfDoc);
		String sql = wfDocHelper.getUpdateSql(fields, "WfDoc.DocId" ,_wfDoc.getDocId().toString());
		try {
			wfDocHelper.pstmtUpdate(_wfDoc, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfDocDAO.update", e);
			throw e;
		}
	}

	public List<WfDoc> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfDocHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfDocDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfDoc> list = wfDocHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfDoc)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfDocDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfDoc) throws java.sql.SQLException {
		WfDoc _wfDoc = (WfDoc)wfDoc;
		String sql = "select "+WfDoc.ALL_FIELDS+wfDocHelper.getSqlString()+" and WfDoc.DocId = '"+_wfDoc.getDocId()+"'";
		try {
			List<WfDoc> list = wfDocHelper.getQueryList(query(sql),WfDoc.ALL_FIELDS);
			if(list.size() > 0)
				return (WfDoc)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfDocDAO.load", e);
			throw e;
		}
	}
}