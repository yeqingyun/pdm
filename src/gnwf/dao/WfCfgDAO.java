package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfCfgHelper;
import gnwf.vo.WfCfg;

public class WfCfgDAO extends BasicDAO implements GenericDAO {

	private WfCfgHelper wfCfgHelper = new WfCfgHelper();

	public void insert(Object wfCfg) throws java.sql.SQLException {
		WfCfg _wfCfg = (WfCfg)wfCfg;
		String fields = wfCfgHelper.getFields(_wfCfg);
		String sql = wfCfgHelper.getInsertSql(fields);
		try {
			wfCfgHelper.pstmtInsert(_wfCfg, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfCfgDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfCfg) throws java.sql.SQLException {
		WfCfg _wfCfg = (WfCfg)wfCfg;
		String fields = wfCfgHelper.getFields(_wfCfg);
		String sql = wfCfgHelper.getUpdateSql(fields, "WfCfg.FlowId" ,_wfCfg.getFlowId().toString());
		try {
			wfCfgHelper.pstmtUpdate(_wfCfg, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfCfgDAO.update", e);
			throw e;
		}
	}

	public List<WfCfg> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfCfgHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfCfgDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfCfg> list = wfCfgHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfCfg)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfCfgDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfCfg) throws java.sql.SQLException {
		WfCfg _wfCfg = (WfCfg)wfCfg;
		String sql = "select "+WfCfg.ALL_FIELDS+wfCfgHelper.getSqlString()+" and WfCfg.FlowId = '"+_wfCfg.getFlowId()+"'";
		try {
			List<WfCfg> list = wfCfgHelper.getQueryList(query(sql),WfCfg.ALL_FIELDS);
			if(list.size() > 0)
				return (WfCfg)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfCfgDAO.load", e);
			throw e;
		}
	}
}