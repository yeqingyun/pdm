package gnwf.dao;


import java.util.List;

import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;


import gnwf.dao.helper.WfRdRiskHelper;

import gnwf.vo.WfRdRisk;


public class WfRdRiskDAO extends BasicDAO implements GenericDAO {

	private WfRdRiskHelper wfRdRiskHelper = new WfRdRiskHelper();

	public void insert(Object wfRdRisk) throws java.sql.SQLException {
		WfRdRisk _wfRdRisk = (WfRdRisk)wfRdRisk;
		String fields = wfRdRiskHelper.getFields(_wfRdRisk);
		String sql = wfRdRiskHelper.getInsertSql(fields);
		try {
			wfRdRiskHelper.pstmtInsert(_wfRdRisk, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdRiskDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfRdRisk) throws java.sql.SQLException {
		WfRdRisk _wfRdRisk = (WfRdRisk)wfRdRisk;
		String fields = wfRdRiskHelper.getFields(_wfRdRisk);
		String sql = wfRdRiskHelper.getUpdateSql(fields, "WfRisk.RiskId" ,_wfRdRisk.getRiskId().toString());
		try {
			wfRdRiskHelper.pstmtUpdate(_wfRdRisk, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdRiskDAO.update", e);
			throw e;
		}
	}

	public List<WfRdRisk> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfRdRiskHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdRiskDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfRdRisk> list = wfRdRiskHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfRdRisk)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdRiskDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfRdRisk) throws java.sql.SQLException {
		WfRdRisk _wfRdRisk = (WfRdRisk)wfRdRisk;
		String sql = "select "+WfRdRisk.SELF_FIELDS+wfRdRiskHelper.getSqlString()+" and WfRdRisk.WfNo = '"+_wfRdRisk.getWfNo()+"'";
		try {
			List<WfRdRisk> list = wfRdRiskHelper.getQueryList(query(sql),WfRdRisk.SELF_FIELDS);
			if(list.size() > 0)
				return (WfRdRisk)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdRiskDAO.load", e);
			throw e;
		}
	}
}