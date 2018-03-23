package zrprjt.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrprjt.dao.helper.ComHelper;
import zrprjt.vo.Com;

public class ComDAO extends BasicDAO implements GenericDAO {

	private ComHelper comHelper = new ComHelper();

	public void insert(Object com) throws java.sql.SQLException {
		Com _com = (Com)com;
		String fields = comHelper.getFields(_com);
		String sql = comHelper.getInsertSql(fields);
		try {
			comHelper.pstmtInsert(_com, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ComDAO.insert", e);
			throw e;
		}
	}

	public void update(Object com) throws java.sql.SQLException {
		Com _com = (Com)com;
		String fields = comHelper.getFields(_com);
		String sql = comHelper.getUpdateSql(fields, "Com.ComId" ,_com.getComId().toString());
		try {
			comHelper.pstmtUpdate(_com, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ComDAO.update", e);
			throw e;
		}
	}

	public List<Com> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return comHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ComDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<Com> list = comHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (Com)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ComDAO.load", e);
			throw e;
		}
	}

	public Object load(Object com) throws java.sql.SQLException {
		Com _com = (Com)com;
		String sql = "select "+Com.ALL_FIELDS+comHelper.getSqlString()+" and Com.ComId = '"+_com.getComId()+"'";
		try {
			List<Com> list = comHelper.getQueryList(query(sql),Com.ALL_FIELDS);
			if(list.size() > 0)
				return (Com)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ComDAO.load", e);
			throw e;
		}
	}
}