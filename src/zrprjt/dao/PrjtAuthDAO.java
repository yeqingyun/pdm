package zrprjt.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrprjt.dao.helper.PrjtAuthHelper;
import zrprjt.vo.PrjtAuth;

public class PrjtAuthDAO extends BasicDAO implements GenericDAO {

	private PrjtAuthHelper prjtAuthHelper = new PrjtAuthHelper();

	public void insert(Object prjtAuth) throws java.sql.SQLException {
		PrjtAuth _prjtAuth = (PrjtAuth)prjtAuth;
		String fields = prjtAuthHelper.getFields(_prjtAuth);
		String sql = prjtAuthHelper.getInsertSql(fields);
		try {
			prjtAuthHelper.pstmtInsert(_prjtAuth, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtAuthDAO.insert", e);
			throw e;
		}
	}

	public void update(Object prjtAuth) throws java.sql.SQLException {
		PrjtAuth _prjtAuth = (PrjtAuth)prjtAuth;
		String fields = prjtAuthHelper.getFields(_prjtAuth);
		String sql = prjtAuthHelper.getUpdateSql(fields, "PrjtAuth.RoleId" ,_prjtAuth.getRoleId().toString());
		try {
			prjtAuthHelper.pstmtUpdate(_prjtAuth, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtAuthDAO.update", e);
			throw e;
		}
	}

	public List<PrjtAuth> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return prjtAuthHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtAuthDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<PrjtAuth> list = prjtAuthHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (PrjtAuth)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtAuthDAO.load", e);
			throw e;
		}
	}

	public Object load(Object prjtAuth) throws java.sql.SQLException {
		PrjtAuth _prjtAuth = (PrjtAuth)prjtAuth;
		String sql = "select "+PrjtAuth.ALL_FIELDS+prjtAuthHelper.getSqlString()+" and PrjtAuth.RoleId = '"+_prjtAuth.getRoleId()+"'";
		try {
			List<PrjtAuth> list = prjtAuthHelper.getQueryList(query(sql),PrjtAuth.ALL_FIELDS);
			if(list.size() > 0)
				return (PrjtAuth)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtAuthDAO.load", e);
			throw e;
		}
	}
}