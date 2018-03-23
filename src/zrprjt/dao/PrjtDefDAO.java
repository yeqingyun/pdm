package zrprjt.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrprjt.dao.helper.PrjtDefHelper;
import zrprjt.vo.PrjtDef;

public class PrjtDefDAO extends BasicDAO implements GenericDAO {

	private PrjtDefHelper prjtDefHelper = new PrjtDefHelper();

	public void insert(Object prjtDef) throws java.sql.SQLException {
		PrjtDef _prjtDef = (PrjtDef)prjtDef;
		String fields = prjtDefHelper.getFields(_prjtDef);
		String sql = prjtDefHelper.getInsertSql(fields);
		try {
			prjtDefHelper.pstmtInsert(_prjtDef, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtDefDAO.insert", e);
			throw e;
		}
	}

	public void update(Object prjtDef) throws java.sql.SQLException {
		PrjtDef _prjtDef = (PrjtDef)prjtDef;
		String fields = prjtDefHelper.getFields(_prjtDef);
		System.out.println("update的fields数据"+fields);
		String sql = prjtDefHelper.getUpdateSql(fields, "PrjtDef.PrjtNo" ,_prjtDef.getPrjtNo());

		System.out.println("update的sql数据"+sql);
		try {
			prjtDefHelper.pstmtUpdate(_prjtDef, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtDefDAO.update", e);
			throw e;
		}
	}

	public List<PrjtDef> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return prjtDefHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtDefDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<PrjtDef> list = prjtDefHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (PrjtDef)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtDefDAO.load", e);
			throw e;
		}
	}

	public Object load(Object prjtDef) throws java.sql.SQLException {
		PrjtDef _prjtDef = (PrjtDef)prjtDef;
		String sql = "select "+PrjtDef.ALL_FIELDS+prjtDefHelper.getSqlString()+" and PrjtDef.PrjtNo = '"+_prjtDef.getPrjtNo()+"'";
		try {
			List<PrjtDef> list = prjtDefHelper.getQueryList(query(sql),PrjtDef.ALL_FIELDS);
			if(list.size() > 0)
				return (PrjtDef)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtDefDAO.load", e);
			throw e;
		}
	}
}