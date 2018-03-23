package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.SyDefHelper;
import zrsy.vo.SyDef;

public class SyDefDAO extends BasicDAO implements GenericDAO {

	private SyDefHelper syDefHelper = new SyDefHelper();

	public void insert(Object syDef) throws java.sql.SQLException {
		SyDef _syDef = (SyDef)syDef;
		String fields = syDefHelper.getFields(_syDef);
		String sql = syDefHelper.getInsertSql(fields);
		try {
			syDefHelper.pstmtInsert(_syDef, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SyDefDAO.insert", e);
			throw e;
		}
	}

	public void update(Object syDef) throws java.sql.SQLException {
		SyDef _syDef = (SyDef)syDef;
		String fields = syDefHelper.getFields(_syDef);
		String sql = syDefHelper.getUpdateSql(fields, "SyDef.SyId" ,_syDef.getSyId().toString());
		try {
			syDefHelper.pstmtUpdate(_syDef, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SyDefDAO.update", e);
			throw e;
		}
	}

	public List<SyDef> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return syDefHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SyDefDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<SyDef> list = syDefHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (SyDef)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SyDefDAO.load", e);
			throw e;
		}
	}

	public Object load(Object syDef) throws java.sql.SQLException {
		SyDef _syDef = (SyDef)syDef;
		String sql = "select "+SyDef.ALL_FIELDS+syDefHelper.getSqlString()+" and SyDef.SyId = '"+_syDef.getSyId()+"'";
		try {
			List<SyDef> list = syDefHelper.getQueryList(query(sql),SyDef.ALL_FIELDS);
			if(list.size() > 0)
				return (SyDef)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SyDefDAO.load", e);
			throw e;
		}
	}
}