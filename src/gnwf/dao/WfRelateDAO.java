package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfRelateHelper;
import gnwf.vo.WfRelate;

public class WfRelateDAO extends BasicDAO implements GenericDAO {

	private WfRelateHelper wfRelateHelper = new WfRelateHelper();

	public void insert(Object wfRelate) throws java.sql.SQLException {
		WfRelate _wfRelate = (WfRelate)wfRelate;
		String fields = wfRelateHelper.getFields(_wfRelate);
		String sql = wfRelateHelper.getInsertSql(fields);
		try {
			wfRelateHelper.pstmtInsert(_wfRelate, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRelateDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfRelate) throws java.sql.SQLException {
		WfRelate _wfRelate = (WfRelate)wfRelate;
		String fields = wfRelateHelper.getFields(_wfRelate);
		String sql = wfRelateHelper.getUpdateSql(fields, "WfRelate.FlowId" ,_wfRelate.getFlowId().toString());
		try {
			wfRelateHelper.pstmtUpdate(_wfRelate, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRelateDAO.update", e);
			throw e;
		}
	}

	public List<WfRelate> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfRelateHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRelateDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfRelate> list = wfRelateHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfRelate)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRelateDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfRelate) throws java.sql.SQLException {
		WfRelate _wfRelate = (WfRelate)wfRelate;
		String sql = "select "+WfRelate.ALL_FIELDS+wfRelateHelper.getSqlString()+" and WfRelate.FlowId = '"+_wfRelate.getFlowId()+"'";
		try {
			List<WfRelate> list = wfRelateHelper.getQueryList(query(sql),WfRelate.ALL_FIELDS);
			if(list.size() > 0)
				return (WfRelate)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRelateDAO.load", e);
			throw e;
		}
	}
}