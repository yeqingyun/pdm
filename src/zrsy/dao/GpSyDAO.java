package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.GpSyHelper;
import zrsy.vo.GpSy;

public class GpSyDAO extends BasicDAO implements GenericDAO {

	private GpSyHelper gpSyHelper = new GpSyHelper();

	public void insert(Object gpSy) throws java.sql.SQLException {
		GpSy _gpSy = (GpSy)gpSy;
		String fields = gpSyHelper.getFields(_gpSy);
		String sql = gpSyHelper.getInsertSql(fields);
		try {
			gpSyHelper.pstmtInsert(_gpSy, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpSyDAO.insert", e);
			throw e;
		}
	}

	public void update(Object gpSy) throws java.sql.SQLException {
		GpSy _gpSy = (GpSy)gpSy;
		String fields = gpSyHelper.getFields(_gpSy);
		String sql = gpSyHelper.getUpdateSql(fields, "GpSy.GpId" ,_gpSy.getGpId().toString());
		try {
			gpSyHelper.pstmtUpdate(_gpSy, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpSyDAO.update", e);
			throw e;
		}
	}

	public List<GpSy> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return gpSyHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpSyDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<GpSy> list = gpSyHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (GpSy)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpSyDAO.load", e);
			throw e;
		}
	}

	public Object load(Object gpSy) throws java.sql.SQLException {
		GpSy _gpSy = (GpSy)gpSy;
		String sql = "select "+GpSy.ALL_FIELDS+gpSyHelper.getSqlString()+" and GpSy.GpId = '"+_gpSy.getGpId()+"'";
		try {
			List<GpSy> list = gpSyHelper.getQueryList(query(sql),GpSy.ALL_FIELDS);
			if(list.size() > 0)
				return (GpSy)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpSyDAO.load", e);
			throw e;
		}
	}
}