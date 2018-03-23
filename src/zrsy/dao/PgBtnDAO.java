package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.PgBtnHelper;
import zrsy.vo.PgBtn;

public class PgBtnDAO extends BasicDAO implements GenericDAO {

	private PgBtnHelper pgBtnHelper = new PgBtnHelper();

	public void insert(Object pgBtn) throws java.sql.SQLException {
		PgBtn _pgBtn = (PgBtn)pgBtn;
		String fields = pgBtnHelper.getFields(_pgBtn);
		String sql = pgBtnHelper.getInsertSql(fields);
		try {
			pgBtnHelper.pstmtInsert(_pgBtn, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PgBtnDAO.insert", e);
			throw e;
		}
	}

	public void update(Object pgBtn) throws java.sql.SQLException {
		PgBtn _pgBtn = (PgBtn)pgBtn;
		String fields = pgBtnHelper.getFields(_pgBtn);
		String sql = pgBtnHelper.getUpdateSql(fields, "PgBtn.NodeId" ,_pgBtn.getNodeId().toString());
		try {
			pgBtnHelper.pstmtUpdate(_pgBtn, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PgBtnDAO.update", e);
			throw e;
		}
	}

	public List<PgBtn> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return pgBtnHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PgBtnDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<PgBtn> list = pgBtnHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (PgBtn)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PgBtnDAO.load", e);
			throw e;
		}
	}

	public Object load(Object pgBtn) throws java.sql.SQLException {
		PgBtn _pgBtn = (PgBtn)pgBtn;
		String sql = "select "+PgBtn.ALL_FIELDS+pgBtnHelper.getSqlString()+" and PgBtn.NodeId = '"+_pgBtn.getNodeId()+"'";
		try {
			List<PgBtn> list = pgBtnHelper.getQueryList(query(sql),PgBtn.ALL_FIELDS);
			if(list.size() > 0)
				return (PgBtn)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PgBtnDAO.load", e);
			throw e;
		}
	}
}