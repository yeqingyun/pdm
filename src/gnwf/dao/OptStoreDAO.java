package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.OptStoreHelper;
import gnwf.vo.OptStore;

public class OptStoreDAO extends BasicDAO implements GenericDAO {

	private OptStoreHelper optStoreHelper = new OptStoreHelper();

	public void insert(Object optStore) throws java.sql.SQLException {
		OptStore _optStore = (OptStore)optStore;
		String fields = optStoreHelper.getFields(_optStore);
		String sql = optStoreHelper.getInsertSql(fields);
		try {
			optStoreHelper.pstmtInsert(_optStore, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("OptStoreDAO.insert", e);
			throw e;
		}
	}

	public void update(Object optStore) throws java.sql.SQLException {
		OptStore _optStore = (OptStore)optStore;
		String fields = optStoreHelper.getFields(_optStore);
		String sql = optStoreHelper.getUpdateSql(fields, "OptStore.MatlNo" ,_optStore.getMatlNo().toString());
		try {
			optStoreHelper.pstmtUpdate(_optStore, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("OptStoreDAO.update", e);
			throw e;
		}
	}

	public List<OptStore> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return optStoreHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("OptStoreDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<OptStore> list = optStoreHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (OptStore)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("OptStoreDAO.load", e);
			throw e;
		}
	}

	public Object load(Object optStore) throws java.sql.SQLException {
		OptStore _optStore = (OptStore)optStore;
		String sql = "select "+OptStore.ALL_FIELDS+optStoreHelper.getSqlString()+" and OptStore.MatlNo = '"+_optStore.getMatlNo()+"'";
		try {
			List<OptStore> list = optStoreHelper.getQueryList(query(sql),OptStore.ALL_FIELDS);
			if(list.size() > 0)
				return (OptStore)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("OptStoreDAO.load", e);
			throw e;
		}
	}
}