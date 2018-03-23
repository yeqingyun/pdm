package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.GpMenuHelper;
import zrsy.vo.GpMenu;

public class GpMenuDAO extends BasicDAO implements GenericDAO {

	private GpMenuHelper gpMenuHelper = new GpMenuHelper();

	public void insert(Object gpMenu) throws java.sql.SQLException {
		GpMenu _gpMenu = (GpMenu)gpMenu;
		String fields = gpMenuHelper.getFields(_gpMenu);
		String sql = gpMenuHelper.getInsertSql(fields);
		try {
			gpMenuHelper.pstmtInsert(_gpMenu, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpMenuDAO.insert", e);
			throw e;
		}
	}

	public void update(Object gpMenu) throws java.sql.SQLException {
		GpMenu _gpMenu = (GpMenu)gpMenu;
		String fields = gpMenuHelper.getFields(_gpMenu);
		String sql = gpMenuHelper.getUpdateSql(fields, "GpMenu.GpId" ,_gpMenu.getGpId().toString());
		try {
			gpMenuHelper.pstmtUpdate(_gpMenu, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpMenuDAO.update", e);
			throw e;
		}
	}

	public List<GpMenu> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return gpMenuHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpMenuDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<GpMenu> list = gpMenuHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (GpMenu)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpMenuDAO.load", e);
			throw e;
		}
	}

	public Object load(Object gpMenu) throws java.sql.SQLException {
		GpMenu _gpMenu = (GpMenu)gpMenu;
		String sql = "select "+GpMenu.ALL_FIELDS+gpMenuHelper.getSqlString()+" and GpMenu.GpId = '"+_gpMenu.getGpId()+"'";
		try {
			List<GpMenu> list = gpMenuHelper.getQueryList(query(sql),GpMenu.ALL_FIELDS);
			if(list.size() > 0)
				return (GpMenu)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpMenuDAO.load", e);
			throw e;
		}
	}
}