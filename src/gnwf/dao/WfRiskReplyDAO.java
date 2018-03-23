package gnwf.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;

import gnwf.dao.helper.WfRiskReplyHelper;
import gnwf.vo.WfRiskReply;

public class WfRiskReplyDAO extends BasicDAO implements GenericDAO {

	private WfRiskReplyHelper wfRiskReplyHelper = new WfRiskReplyHelper();

	public void insert(Object wfRiskReply) throws java.sql.SQLException {
		WfRiskReply _wfRiskReply = (WfRiskReply)wfRiskReply;
		String fields = wfRiskReplyHelper.getFields(_wfRiskReply);
		String sql = wfRiskReplyHelper.getInsertSql(fields);
		try {
			wfRiskReplyHelper.pstmtInsert(_wfRiskReply, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRiskReplyDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfRiskReply) throws java.sql.SQLException {
		WfRiskReply _wfRiskReply = (WfRiskReply)wfRiskReply;
		String fields = wfRiskReplyHelper.getFields(_wfRiskReply);
		String sql = wfRiskReplyHelper.getUpdateSql(fields, "WfRiskReply.Id" ,_wfRiskReply.getId().toString());
		try {
			wfRiskReplyHelper.pstmtUpdate(_wfRiskReply, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRiskReplyDAO.update", e);
			throw e;
		}
	}

	public List<WfRiskReply> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfRiskReplyHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRiskReplyDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfRiskReply> list = wfRiskReplyHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfRiskReply)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRiskReplyDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfRiskReply) throws java.sql.SQLException {
		WfRiskReply _wfRiskReply = (WfRiskReply)wfRiskReply;
		String sql = "select "+WfRiskReply.SELF_FIELDS+wfRiskReplyHelper.getSqlString()+" and WfRiskReply.RiskId = '"+_wfRiskReply.getRiskId() + "'";
		try {
			List<WfRiskReply> list = wfRiskReplyHelper.getQueryList(query(sql),WfRiskReply.SELF_FIELDS);
			if(list.size() > 0)
				return (WfRiskReply)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRiskReplyDAO.load", e);
			throw e;
		}
	}
}