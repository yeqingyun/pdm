package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.ScoHelper;
import zrsy.vo.Sco;

public class ScoDAO extends BasicDAO implements GenericDAO {

	private ScoHelper scoHelper = new ScoHelper();

	public void insert(Object sco) throws java.sql.SQLException {
		Sco _sco = (Sco)sco;
		String fields = scoHelper.getFields(_sco);
		String sql = scoHelper.getInsertSql(fields);
		try {
			scoHelper.pstmtInsert(_sco, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ScoDAO.insert", e);
			throw e;
		}
	}

	public void update(Object sco) throws java.sql.SQLException {
		Sco _sco = (Sco)sco;
		String fields = scoHelper.getFields(_sco);
		String sql = scoHelper.getUpdateSql(fields, "Sco.ScoId" ,_sco.getScoId().toString());
		try {
			scoHelper.pstmtUpdate(_sco, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ScoDAO.update", e);
			throw e;
		}
	}

	public List<Sco> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return scoHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ScoDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<Sco> list = scoHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (Sco)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ScoDAO.load", e);
			throw e;
		}
	}

	public Object load(Object sco) throws java.sql.SQLException {
		Sco _sco = (Sco)sco;
		String sql = "select "+Sco.ALL_FIELDS+scoHelper.getSqlString()+" and Sco.ScoId = '"+_sco.getScoId()+"'";
		try {
			List<Sco> list = scoHelper.getQueryList(query(sql),Sco.ALL_FIELDS);
			if(list.size() > 0)
				return (Sco)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ScoDAO.load", e);
			throw e;
		}
	}
}