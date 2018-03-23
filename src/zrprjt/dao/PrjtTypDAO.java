package zrprjt.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrprjt.dao.helper.PrjtTypHelper;
import zrprjt.vo.PrjtTyp;

public class PrjtTypDAO extends BasicDAO implements GenericDAO {

	private PrjtTypHelper prjtTypHelper = new PrjtTypHelper();

	public void insert(Object prjtTyp) throws java.sql.SQLException {
		PrjtTyp _prjtTyp = (PrjtTyp)prjtTyp;
		String fields = prjtTypHelper.getFields(_prjtTyp);
		String sql = prjtTypHelper.getInsertSql(fields);
		try {
			prjtTypHelper.pstmtInsert(_prjtTyp, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtTypDAO.insert", e);
			throw e;
		}
	}

	public void update(Object prjtTyp) throws java.sql.SQLException {
		PrjtTyp _prjtTyp = (PrjtTyp)prjtTyp;
		String fields = prjtTypHelper.getFields(_prjtTyp);
		String sql = prjtTypHelper.getUpdateSql(fields, "PrjtTyp.TypId" ,_prjtTyp.getTypId().toString());
		try {
			prjtTypHelper.pstmtUpdate(_prjtTyp, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtTypDAO.update", e);
			throw e;
		}
	}

	public List<PrjtTyp> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return prjtTypHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtTypDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<PrjtTyp> list = prjtTypHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (PrjtTyp)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtTypDAO.load", e);
			throw e;
		}
	}

	public Object load(Object prjtTyp) throws java.sql.SQLException {
		PrjtTyp _prjtTyp = (PrjtTyp)prjtTyp;
		String sql = "select "+PrjtTyp.ALL_FIELDS+prjtTypHelper.getSqlString()+" and PrjtTyp.TypId = '"+_prjtTyp.getTypId()+"'";
		try {
			List<PrjtTyp> list = prjtTypHelper.getQueryList(query(sql),PrjtTyp.ALL_FIELDS);
			if(list.size() > 0)
				return (PrjtTyp)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtTypDAO.load", e);
			throw e;
		}
	}
}