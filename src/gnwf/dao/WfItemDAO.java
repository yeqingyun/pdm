package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfItemHelper;
import gnwf.vo.WfItem;

public class WfItemDAO extends BasicDAO implements GenericDAO {

	private WfItemHelper wfItemHelper = new WfItemHelper();

	public void insert(Object wfItem) throws java.sql.SQLException {
		WfItem _wfItem = (WfItem)wfItem;
		String fields = wfItemHelper.getFields(_wfItem);
		String sql = wfItemHelper.getInsertSql(fields);
		try {
			wfItemHelper.pstmtInsert(_wfItem, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfItemDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfItem) throws java.sql.SQLException {
		WfItem _wfItem = (WfItem)wfItem;
		String fields = wfItemHelper.getFields(_wfItem);
		String sql = wfItemHelper.getUpdateSql(fields, "WfItem.ItemId" ,_wfItem.getItemId().toString());
		try {
			wfItemHelper.pstmtUpdate(_wfItem, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfItemDAO.update", e);
			throw e;
		}
	}

	public List<WfItem> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfItemHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfItemDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfItem> list = wfItemHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfItem)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfItemDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfItem) throws java.sql.SQLException {
		WfItem _wfItem = (WfItem)wfItem;
		String sql = "select "+WfItem.ALL_FIELDS+wfItemHelper.getSqlString()+" and WfItem.ItemId = '"+_wfItem.getItemId()+"'";
		try {
			List<WfItem> list = wfItemHelper.getQueryList(query(sql),WfItem.ALL_FIELDS);
			if(list.size() > 0)
				return (WfItem)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfItemDAO.load", e);
			throw e;
		}
	}
}