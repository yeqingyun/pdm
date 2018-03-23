package zrprjt.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrprjt.dao.helper.PrjtRoleHelper;
import zrprjt.vo.PrjtRole;

public class PrjtRoleDAO extends BasicDAO implements GenericDAO {

	private PrjtRoleHelper prjtRoleHelper = new PrjtRoleHelper();

	public void insert(Object prjtRole) throws java.sql.SQLException {
		PrjtRole _prjtRole = (PrjtRole)prjtRole;
		String fields = prjtRoleHelper.getFields(_prjtRole);
		String sql = prjtRoleHelper.getInsertSql(fields);
		try {
			prjtRoleHelper.pstmtInsert(_prjtRole, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtRoleDAO.insert", e);
			throw e;
		}
	}

	public void update(Object prjtRole) throws java.sql.SQLException {
		PrjtRole _prjtRole = (PrjtRole)prjtRole;
		String fields = prjtRoleHelper.getFields(_prjtRole);
		String sql = prjtRoleHelper.getUpdateSql(fields, "PrjtRole.RoleId" ,_prjtRole.getRoleId().toString());
		try {
			prjtRoleHelper.pstmtUpdate(_prjtRole, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtRoleDAO.update", e);
			throw e;
		}
	}

	public List<PrjtRole> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return prjtRoleHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtRoleDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<PrjtRole> list = prjtRoleHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (PrjtRole)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtRoleDAO.load", e);
			throw e;
		}
	}

	public Object load(Object prjtRole) throws java.sql.SQLException {
		PrjtRole _prjtRole = (PrjtRole)prjtRole;
		String sql = "select "+PrjtRole.ALL_FIELDS+prjtRoleHelper.getSqlString()+" and PrjtRole.RoleId = '"+_prjtRole.getRoleId()+"'";
		try {
			List<PrjtRole> list = prjtRoleHelper.getQueryList(query(sql),PrjtRole.ALL_FIELDS);
			if(list.size() > 0)
				return (PrjtRole)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtRoleDAO.load", e);
			throw e;
		}
	}
}