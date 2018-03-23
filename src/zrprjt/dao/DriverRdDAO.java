package zrprjt.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrprjt.dao.helper.DriverRdHelper;
import zrprjt.vo.DriverRd;

public class DriverRdDAO extends BasicDAO implements GenericDAO {

	private DriverRdHelper driverRdHelper = new DriverRdHelper();

	public void insert(Object driverRd) throws java.sql.SQLException {
		DriverRd _driverRd = (DriverRd)driverRd;
		String fields = driverRdHelper.getFields(_driverRd);
		String sql = driverRdHelper.getInsertSql(fields);
		try {
			driverRdHelper.pstmtInsert(_driverRd, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DriverRdDAO.insert", e);
			throw e;
		}
	}

	public void update(Object driverRd) throws java.sql.SQLException {
		DriverRd _driverRd = (DriverRd)driverRd;
		String fields = driverRdHelper.getFields(_driverRd);
		String sql = driverRdHelper.getUpdateSql(fields, "DriverRd.LogId" ,_driverRd.getLogId().toString());
		try {
			driverRdHelper.pstmtUpdate(_driverRd, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DriverRdDAO.update", e);
			throw e;
		}
	}

	public List<DriverRd> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return driverRdHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DriverRdDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<DriverRd> list = driverRdHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (DriverRd)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DriverRdDAO.load", e);
			throw e;
		}
	}

	public Object load(Object driverRd) throws java.sql.SQLException {
		DriverRd _driverRd = (DriverRd)driverRd;
		String sql = "select "+DriverRd.ALL_FIELDS+driverRdHelper.getSqlString()+" and DriverRd.LogId = '"+_driverRd.getLogId()+"'";
		try {
			List<DriverRd> list = driverRdHelper.getQueryList(query(sql),DriverRd.ALL_FIELDS);
			if(list.size() > 0)
				return (DriverRd)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DriverRdDAO.load", e);
			throw e;
		}
	}
}