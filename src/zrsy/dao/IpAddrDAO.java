package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.IpAddrHelper;
import zrsy.vo.IpAddr;

public class IpAddrDAO extends BasicDAO implements GenericDAO {

	private IpAddrHelper ipAddrHelper = new IpAddrHelper();

	public void insert(Object ipAddr) throws java.sql.SQLException {
		IpAddr _ipAddr = (IpAddr)ipAddr;
		String fields = ipAddrHelper.getFields(_ipAddr);
		String sql = ipAddrHelper.getInsertSql(fields);
		try {
			ipAddrHelper.pstmtInsert(_ipAddr, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("IpAddrDAO.insert", e);
			throw e;
		}
	}

	public void update(Object ipAddr) throws java.sql.SQLException {
		IpAddr _ipAddr = (IpAddr)ipAddr;
		String fields = ipAddrHelper.getFields(_ipAddr);
		String sql = ipAddrHelper.getUpdateSql(fields, "IpAddr.IpId" ,_ipAddr.getIpId().toString());
		try {
			ipAddrHelper.pstmtUpdate(_ipAddr, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("IpAddrDAO.update", e);
			throw e;
		}
	}

	public List<IpAddr> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return ipAddrHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("IpAddrDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<IpAddr> list = ipAddrHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (IpAddr)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("IpAddrDAO.load", e);
			throw e;
		}
	}

	public Object load(Object ipAddr) throws java.sql.SQLException {
		IpAddr _ipAddr = (IpAddr)ipAddr;
		String sql = "select "+IpAddr.ALL_FIELDS+ipAddrHelper.getSqlString()+" and IpAddr.IpId = '"+_ipAddr.getIpId()+"'";
		try {
			List<IpAddr> list = ipAddrHelper.getQueryList(query(sql),IpAddr.ALL_FIELDS);
			if(list.size() > 0)
				return (IpAddr)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("IpAddrDAO.load", e);
			throw e;
		}
	}
}