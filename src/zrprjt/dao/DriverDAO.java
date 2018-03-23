package zrprjt.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrprjt.dao.helper.DriverHelper;
import zrprjt.vo.Driver;

public class DriverDAO extends BasicDAO implements GenericDAO {

	private DriverHelper driverHelper = new DriverHelper();

	public void insert(Object driver) throws java.sql.SQLException {
		Driver _driver = (Driver)driver;
		String fields = driverHelper.getFields(_driver);
		String sql = driverHelper.getInsertSql(fields);
		try {
			driverHelper.pstmtInsert(_driver, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DriverDAO.insert", e);
			throw e;
		}
	}

	public void update(Object driver) throws java.sql.SQLException {
		Driver _driver = (Driver)driver;
		String fields = driverHelper.getFields(_driver);
		String sql = driverHelper.getUpdateSql(fields, "Driver.DriveId" ,_driver.getDriveId().toString());
		try {
			driverHelper.pstmtUpdate(_driver, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DriverDAO.update", e);
			throw e;
		}
	}

	public List<Driver> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return driverHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DriverDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<Driver> list = driverHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (Driver)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DriverDAO.load", e);
			throw e;
		}
	}

	public Object load(Object driver) throws java.sql.SQLException {
		Driver _driver = (Driver)driver;
		String sql = "select "+Driver.ALL_FIELDS+driverHelper.getSqlString()+" and Driver.DriveId = '"+_driver.getDriveId()+"'";
		try {
			List<Driver> list = driverHelper.getQueryList(query(sql),Driver.ALL_FIELDS);
			if(list.size() > 0)
				return (Driver)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DriverDAO.load", e);
			throw e;
		}
	}
}