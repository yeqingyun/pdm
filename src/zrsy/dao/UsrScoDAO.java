package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.UsrScoHelper;
import zrsy.vo.UsrSco;

public class UsrScoDAO extends BasicDAO implements GenericDAO {

	private UsrScoHelper usrScoHelper = new UsrScoHelper();

	public void insert(Object usrSco) throws java.sql.SQLException {
		UsrSco _usrSco = (UsrSco)usrSco;
		String fields = usrScoHelper.getFields(_usrSco);
		String sql = usrScoHelper.getInsertSql(fields);
		try {
			usrScoHelper.pstmtInsert(_usrSco, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("UsrScoDAO.insert", e);
			throw e;
		}
	}

	public void update(Object usrSco) throws java.sql.SQLException {
		UsrSco _usrSco = (UsrSco)usrSco;
		String fields = usrScoHelper.getFields(_usrSco);
		String sql = usrScoHelper.getUpdateSql(fields, "UsrSco.UsrId" ,_usrSco.getUsrId().toString());
		try {
			usrScoHelper.pstmtUpdate(_usrSco, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("UsrScoDAO.update", e);
			throw e;
		}
	}

	public List<UsrSco> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return usrScoHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("UsrScoDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<UsrSco> list = usrScoHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (UsrSco)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("UsrScoDAO.load", e);
			throw e;
		}
	}

	public Object load(Object usrSco) throws java.sql.SQLException {
		UsrSco _usrSco = (UsrSco)usrSco;
		String sql = "select "+UsrSco.ALL_FIELDS+usrScoHelper.getSqlString()+" and UsrSco.UsrId = '"+_usrSco.getUsrId()+"'";
		try {
			List<UsrSco> list = usrScoHelper.getQueryList(query(sql),UsrSco.ALL_FIELDS);
			if(list.size() > 0)
				return (UsrSco)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("UsrScoDAO.load", e);
			throw e;
		}
	}
}