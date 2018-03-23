package gnwf.dao;
import gnwf.dao.helper.WfScheCfgDocHelper;
import gnwf.vo.WfScheCfgDoc;

import java.util.List;

import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;

public class WfScheCfgDocDAO extends BasicDAO implements GenericDAO {

	private WfScheCfgDocHelper wfScheCfgDocHelper = new WfScheCfgDocHelper();

	public void insert(Object wfScheCfgDoc) throws java.sql.SQLException {
		WfScheCfgDoc _wfScheCfgDoc = (WfScheCfgDoc)wfScheCfgDoc;
		String fields = wfScheCfgDocHelper.getFields(_wfScheCfgDoc);
		String sql = wfScheCfgDocHelper.getInsertSql(fields);
		try {
			wfScheCfgDocHelper.pstmtInsert(_wfScheCfgDoc, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfScheCfgDocDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfScheCfgDoc) throws java.sql.SQLException {
		WfScheCfgDoc _wfScheCfgDoc = (WfScheCfgDoc)wfScheCfgDoc;
		String fields = wfScheCfgDocHelper.getFields(_wfScheCfgDoc);
		String sql = wfScheCfgDocHelper.getUpdateSql(fields, "WfScheCfgDoc.DocId" ,_wfScheCfgDoc.getDocId().toString());
		try {
			wfScheCfgDocHelper.pstmtUpdate(_wfScheCfgDoc, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfScheCfgDocDAO.update", e);
			throw e;
		}
	}

	public List<WfScheCfgDoc> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfScheCfgDocHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfScheCfgDocDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfScheCfgDoc> list = wfScheCfgDocHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfScheCfgDoc)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfScheCfgDocDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfScheCfgDoc) throws java.sql.SQLException {
		WfScheCfgDoc _wfScheCfgDoc = (WfScheCfgDoc)wfScheCfgDoc;
		String sql = "select "+WfScheCfgDoc.ALL_FIELDS+wfScheCfgDocHelper.getSqlString()+" and WfScheCfgDoc.DocId = '"+_wfScheCfgDoc.getDocId()+"'";
		try {
			List<WfScheCfgDoc> list = wfScheCfgDocHelper.getQueryList(query(sql),WfScheCfgDoc.ALL_FIELDS);
			if(list.size() > 0)
				return (WfScheCfgDoc)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfScheCfgDocDAO.load", e);
			throw e;
		}
	}
}
