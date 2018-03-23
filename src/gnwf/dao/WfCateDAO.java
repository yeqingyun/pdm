package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfCateHelper;
import gnwf.vo.WfCate;

public class WfCateDAO extends BasicDAO implements GenericDAO {

	private WfCateHelper wfCateHelper = new WfCateHelper();

	public void insert(Object wfCate) throws java.sql.SQLException {
		WfCate _wfCate = (WfCate)wfCate;
		String fields = wfCateHelper.getFields(_wfCate);
		String sql = wfCateHelper.getInsertSql(fields);
		try {
			wfCateHelper.pstmtInsert(_wfCate, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfCateDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfCate) throws java.sql.SQLException {
		WfCate _wfCate = (WfCate)wfCate;
		String fields = wfCateHelper.getFields(_wfCate);
		String sql = wfCateHelper.getUpdateSql(fields, "WfCate.CateId" ,_wfCate.getCateId().toString());
		try {
			wfCateHelper.pstmtUpdate(_wfCate, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfCateDAO.update", e);
			throw e;
		}
	}

	public List<WfCate> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfCateHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfCateDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfCate> list = wfCateHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfCate)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfCateDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfCate) throws java.sql.SQLException {
		WfCate _wfCate = (WfCate)wfCate;
		String sql = "select "+WfCate.ALL_FIELDS+wfCateHelper.getSqlString()+" and WfCate.CateId = '"+_wfCate.getCateId()+"'";
		try {
			List<WfCate> list = wfCateHelper.getQueryList(query(sql),WfCate.ALL_FIELDS);
			if(list.size() > 0)
				return (WfCate)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfCateDAO.load", e);
			throw e;
		}
	}
}