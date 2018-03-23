package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import org.frm.jdbc.DbConnUtil;

import zrsy.dao.helper.UsrHelper;
import zrsy.vo.Usr;

public class UsrDAO extends BasicDAO implements GenericDAO {

	private UsrHelper usrHelper = new UsrHelper();

	public void insert(Object usr) throws java.sql.SQLException {
		Usr _usr = (Usr)usr;
		String fields = usrHelper.getFields(_usr);
		String sql = usrHelper.getInsertSql(fields);
		try {
			usrHelper.pstmtInsert(_usr, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("UsrDAO.insert", e);
			throw e;
		}
	}

	public void update(Object usr) throws java.sql.SQLException {
		Usr _usr = (Usr)usr;
		String fields = usrHelper.getFields(_usr);
		String sql = usrHelper.getUpdateSql(fields, "Usr.Id" ,_usr.getId().toString());
		try {
			usrHelper.pstmtUpdate(_usr, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("UsrDAO.update", e);
			throw e;
		}
	}

	public List<Usr> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return usrHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("UsrDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<Usr> list = usrHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (Usr)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("UsrDAO.load", e);
			throw e;
		}
	}

	public Object load(Object usr) throws java.sql.SQLException {
		Usr _usr = (Usr)usr;
		String sql = "select "+Usr.ALL_FIELDS+usrHelper.getSqlString()+" and Usr.Id = '"+_usr.getId()+"'";
		try {
			List<Usr> list = usrHelper.getQueryList(query(sql),Usr.ALL_FIELDS);
			if(list.size() > 0)
				return (Usr)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("UsrDAO.load", e);
			throw e;
		}
	}
	
	public Object login(Object usr) throws java.sql.SQLException {
		Usr _usr = (Usr)usr;
		String sql = "select "+Usr.ALL_FIELDS+usrHelper.getSqlString()+" and Usr.Login = ? and Usr.Pwd = ? ";
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			index++;
			DbConnUtil.getDbconn().getCurrentPstmt().setString(index, _usr.getLogin());
			index++;
			DbConnUtil.getDbconn().getCurrentPstmt().setString(index, _usr.getPwd());
			DbConnUtil.getDbconn().getCurrentPstmt().executeQuery();
			List<Usr> list = usrHelper.getQueryList(DbConnUtil.getDbconn().getCurrentPstmt().executeQuery(),Usr.ALL_FIELDS);
			if(list.size() > 0)
				return (Usr)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("UsrDAO.load", e);
			e.printStackTrace();
			throw e;
		}
	}
}