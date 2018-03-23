package zrprjt.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrprjt.dao.helper.DriverDtlHelper;
import zrprjt.vo.DriverDtl;

public class DriverDtlDAO extends BasicDAO implements GenericDAO {

	private DriverDtlHelper driverDtlHelper = new DriverDtlHelper();

	public void insert(Object driverDtl) throws java.sql.SQLException {
		DriverDtl _driverDtl = (DriverDtl)driverDtl;
		String fields = driverDtlHelper.getFields(_driverDtl);
		String sql = driverDtlHelper.getInsertSql(fields);
		try {
			driverDtlHelper.pstmtInsert(_driverDtl, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DriverDtlDAO.insert", e);
			throw e;
		}
	}

	public void update(Object driverDtl) throws java.sql.SQLException {
		DriverDtl _driverDtl = (DriverDtl)driverDtl;
		String fields = driverDtlHelper.getFields(_driverDtl);
		String sql = driverDtlHelper.getUpdateSql(fields, "DriverDtl.DriveId" ,_driverDtl.getDriveId().toString());
		try {
			driverDtlHelper.pstmtUpdate(_driverDtl, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DriverDtlDAO.update", e);
			throw e;
		}
	}

	public List<DriverDtl> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return driverDtlHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DriverDtlDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<DriverDtl> list = driverDtlHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (DriverDtl)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DriverDtlDAO.load", e);
			throw e;
		}
	}

	public Object load(Object driverDtl) throws java.sql.SQLException {
		DriverDtl _driverDtl = (DriverDtl)driverDtl;
		String sql = "select "+DriverDtl.ALL_FIELDS+driverDtlHelper.getSqlString()+" and DriverDtl.DriveId = '"+_driverDtl.getDriveId()+"'";
		try {
			List<DriverDtl> list = driverDtlHelper.getQueryList(query(sql),DriverDtl.ALL_FIELDS);
			if(list.size() > 0)
				return (DriverDtl)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DriverDtlDAO.load", e);
			throw e;
		}
	}
}