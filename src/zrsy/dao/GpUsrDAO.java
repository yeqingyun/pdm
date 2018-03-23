package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.GpUsrHelper;
import zrsy.vo.GpUsr;

public class GpUsrDAO extends BasicDAO implements GenericDAO {

	private GpUsrHelper gpUsrHelper = new GpUsrHelper();

	public void insert(Object gpUsr) throws java.sql.SQLException {
		GpUsr _gpUsr = (GpUsr)gpUsr;
		String fields = gpUsrHelper.getFields(_gpUsr);
		String sql = gpUsrHelper.getInsertSql(fields);
		try {
			gpUsrHelper.pstmtInsert(_gpUsr, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpUsrDAO.insert", e);
			throw e;
		}
	}

	public void update(Object gpUsr) throws java.sql.SQLException {
		GpUsr _gpUsr = (GpUsr)gpUsr;
		String fields = gpUsrHelper.getFields(_gpUsr);
		String sql = gpUsrHelper.getUpdateSql(fields, "GpUsr.GpId" ,_gpUsr.getGpId().toString());
		try {
			gpUsrHelper.pstmtUpdate(_gpUsr, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpUsrDAO.update", e);
			throw e;
		}
	}

	public List<GpUsr> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return gpUsrHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpUsrDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<GpUsr> list = gpUsrHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (GpUsr)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpUsrDAO.load", e);
			throw e;
		}
	}

	public Object load(Object gpUsr) throws java.sql.SQLException {
		GpUsr _gpUsr = (GpUsr)gpUsr;
		String sql = "select "+GpUsr.ALL_FIELDS+gpUsrHelper.getSqlString()+" and GpUsr.GpId = '"+_gpUsr.getGpId()+"'";
		try {
			List<GpUsr> list = gpUsrHelper.getQueryList(query(sql),GpUsr.ALL_FIELDS);
			if(list.size() > 0)
				return (GpUsr)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpUsrDAO.load", e);
			throw e;
		}
	}
}