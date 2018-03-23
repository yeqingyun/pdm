package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfCfgRelateHelper;
import gnwf.vo.WfCfgRelate;

public class WfCfgRelateDAO extends BasicDAO implements GenericDAO {

	private WfCfgRelateHelper wfCfgRelateHelper = new WfCfgRelateHelper();

	public void insert(Object wfCfgRelate) throws java.sql.SQLException {
		WfCfgRelate _wfCfgRelate = (WfCfgRelate)wfCfgRelate;
		String fields = wfCfgRelateHelper.getFields(_wfCfgRelate);
		String sql = wfCfgRelateHelper.getInsertSql(fields);
		try {
			wfCfgRelateHelper.pstmtInsert(_wfCfgRelate, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfCfgRelateDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfCfgRelate) throws java.sql.SQLException {
		WfCfgRelate _wfCfgRelate = (WfCfgRelate)wfCfgRelate;
		String fields = wfCfgRelateHelper.getFields(_wfCfgRelate);
		String sql = wfCfgRelateHelper.getUpdateSql(fields, "WfCfgRelate.RelateId" ,_wfCfgRelate.getRelateId().toString());
		try {
			wfCfgRelateHelper.pstmtUpdate(_wfCfgRelate, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfCfgRelateDAO.update", e);
			throw e;
		}
	}

	public List<WfCfgRelate> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfCfgRelateHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfCfgRelateDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfCfgRelate> list = wfCfgRelateHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfCfgRelate)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfCfgRelateDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfCfgRelate) throws java.sql.SQLException {
		WfCfgRelate _wfCfgRelate = (WfCfgRelate)wfCfgRelate;
		String sql = "select "+WfCfgRelate.ALL_FIELDS+wfCfgRelateHelper.getSqlString()+" and WfCfgRelate.RelateId = '"+_wfCfgRelate.getRelateId()+"'";
		try {
			List<WfCfgRelate> list = wfCfgRelateHelper.getQueryList(query(sql),WfCfgRelate.ALL_FIELDS);
			if(list.size() > 0)
				return (WfCfgRelate)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfCfgRelateDAO.load", e);
			throw e;
		}
	}
}