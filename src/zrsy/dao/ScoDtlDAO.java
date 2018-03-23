package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.ScoDtlHelper;
import zrsy.vo.ScoDtl;

public class ScoDtlDAO extends BasicDAO implements GenericDAO {

	private ScoDtlHelper scoDtlHelper = new ScoDtlHelper();

	public void insert(Object scoDtl) throws java.sql.SQLException {
		ScoDtl _scoDtl = (ScoDtl)scoDtl;
		String fields = scoDtlHelper.getFields(_scoDtl);
		String sql = scoDtlHelper.getInsertSql(fields);
		try {
			scoDtlHelper.pstmtInsert(_scoDtl, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ScoDtlDAO.insert", e);
			throw e;
		}
	}

	public void update(Object scoDtl) throws java.sql.SQLException {
		ScoDtl _scoDtl = (ScoDtl)scoDtl;
		String fields = scoDtlHelper.getFields(_scoDtl);
		String sql = scoDtlHelper.getUpdateSql(fields, "ScoDtl.ScoId" ,_scoDtl.getScoId().toString());
		try {
			scoDtlHelper.pstmtUpdate(_scoDtl, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ScoDtlDAO.update", e);
			throw e;
		}
	}

	public List<ScoDtl> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return scoDtlHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ScoDtlDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<ScoDtl> list = scoDtlHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (ScoDtl)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ScoDtlDAO.load", e);
			throw e;
		}
	}

	public Object load(Object scoDtl) throws java.sql.SQLException {
		ScoDtl _scoDtl = (ScoDtl)scoDtl;
		String sql = "select "+ScoDtl.ALL_FIELDS+scoDtlHelper.getSqlString()+" and ScoDtl.ScoId = '"+_scoDtl.getScoId()+"'";
		try {
			List<ScoDtl> list = scoDtlHelper.getQueryList(query(sql),ScoDtl.ALL_FIELDS);
			if(list.size() > 0)
				return (ScoDtl)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ScoDtlDAO.load", e);
			throw e;
		}
	}
}