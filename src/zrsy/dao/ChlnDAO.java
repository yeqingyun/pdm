package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.ChlnHelper;
import zrsy.vo.Chln;

public class ChlnDAO extends BasicDAO implements GenericDAO {

	private ChlnHelper chlnHelper = new ChlnHelper();

	public void insert(Object chln) throws java.sql.SQLException {
		Chln _chln = (Chln)chln;
		String fields = chlnHelper.getFields(_chln);
		String sql = chlnHelper.getInsertSql(fields);
		try {
			chlnHelper.pstmtInsert(_chln, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ChlnDAO.insert", e);
			throw e;
		}
	}

	public void update(Object chln) throws java.sql.SQLException {
		Chln _chln = (Chln)chln;
		String fields = chlnHelper.getFields(_chln);
		String sql = chlnHelper.getUpdateSql(fields, "Chln.Year" ,_chln.getYear().toString());
		try {
			chlnHelper.pstmtUpdate(_chln, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ChlnDAO.update", e);
			throw e;
		}
	}

	public List<Chln> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return chlnHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ChlnDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<Chln> list = chlnHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (Chln)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ChlnDAO.load", e);
			throw e;
		}
	}

	public Object load(Object chln) throws java.sql.SQLException {
		Chln _chln = (Chln)chln;
		String sql = "select "+Chln.ALL_FIELDS+chlnHelper.getSqlString()+" and Chln.Year = '"+_chln.getYear()+"'";
		try {
			List<Chln> list = chlnHelper.getQueryList(query(sql),Chln.ALL_FIELDS);
			if(list.size() > 0)
				return (Chln)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ChlnDAO.load", e);
			throw e;
		}
	}
}