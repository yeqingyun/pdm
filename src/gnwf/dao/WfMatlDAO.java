package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfMatlHelper;
import gnwf.vo.WfMatl;

public class WfMatlDAO extends BasicDAO implements GenericDAO {

	private WfMatlHelper wfMatlHelper = new WfMatlHelper();

	public void insert(Object wfMatl) throws java.sql.SQLException {
		WfMatl _wfMatl = (WfMatl)wfMatl;
		String fields = wfMatlHelper.getFields(_wfMatl);
		String sql = wfMatlHelper.getInsertSql(fields);
		try {
			wfMatlHelper.pstmtInsert(_wfMatl, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfMatl) throws java.sql.SQLException {
		WfMatl _wfMatl = (WfMatl)wfMatl;
		String fields = wfMatlHelper.getFields(_wfMatl);
		String sql = wfMatlHelper.getUpdateSql(fields, "WfMatl.MatlId" ,_wfMatl.getMatlId().toString());
		try {
			wfMatlHelper.pstmtUpdate(_wfMatl, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlDAO.update", e);
			throw e;
		}
	}

	public List<WfMatl> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfMatlHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfMatl> list = wfMatlHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfMatl)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfMatl) throws java.sql.SQLException {
		WfMatl _wfMatl = (WfMatl)wfMatl;
		String sql = "select "+WfMatl.ALL_FIELDS+wfMatlHelper.getSqlString()+" and WfMatl.MatlId = '"+_wfMatl.getMatlId()+"'";
		try {
			List<WfMatl> list = wfMatlHelper.getQueryList(query(sql),WfMatl.ALL_FIELDS);
			if(list.size() > 0)
				return (WfMatl)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlDAO.load", e);
			throw e;
		}
	}
}