package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.GpHelper;
import zrsy.vo.Gp;

public class GpDAO extends BasicDAO implements GenericDAO {

	private GpHelper gpHelper = new GpHelper();

	public void insert(Object gp) throws java.sql.SQLException {
		Gp _gp = (Gp)gp;
		String fields = gpHelper.getFields(_gp);
		String sql = gpHelper.getInsertSql(fields);
		try {
			gpHelper.pstmtInsert(_gp, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpDAO.insert", e);
			throw e;
		}
	}

	public void update(Object gp) throws java.sql.SQLException {
		Gp _gp = (Gp)gp;
		String fields = gpHelper.getFields(_gp);
		String sql = gpHelper.getUpdateSql(fields, "Gp.GpId" ,_gp.getGpId().toString());
		try {
			gpHelper.pstmtUpdate(_gp, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpDAO.update", e);
			throw e;
		}
	}

	public List<Gp> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return gpHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<Gp> list = gpHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (Gp)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpDAO.load", e);
			throw e;
		}
	}

	public Object load(Object gp) throws java.sql.SQLException {
		Gp _gp = (Gp)gp;
		String sql = "select "+Gp.ALL_FIELDS+gpHelper.getSqlString()+" and Gp.GpId = '"+_gp.getGpId()+"'";
		try {
			List<Gp> list = gpHelper.getQueryList(query(sql),Gp.ALL_FIELDS);
			if(list.size() > 0)
				return (Gp)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpDAO.load", e);
			throw e;
		}
	}
}