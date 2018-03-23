package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfMatlApplyHelper;
import gnwf.vo.WfMatlApply;

public class WfMatlApplyDAO extends BasicDAO implements GenericDAO {

	private WfMatlApplyHelper wfMatlApplyHelper = new WfMatlApplyHelper();

	public void insert(Object wfMatlApply) throws java.sql.SQLException {
		WfMatlApply _wfMatlApply = (WfMatlApply)wfMatlApply;
		String fields = wfMatlApplyHelper.getFields(_wfMatlApply);
		String sql = wfMatlApplyHelper.getInsertSql(fields);
		try {
			wfMatlApplyHelper.pstmtInsert(_wfMatlApply, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlApplyDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfMatlApply) throws java.sql.SQLException {
		WfMatlApply _wfMatlApply = (WfMatlApply)wfMatlApply;
		String fields = wfMatlApplyHelper.getFields(_wfMatlApply);
		String sql = wfMatlApplyHelper.getUpdateSql(fields, "WfMatlApply.MatlId" ,_wfMatlApply.getMatlId().toString());
		try {
			wfMatlApplyHelper.pstmtUpdate(_wfMatlApply, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlApplyDAO.update", e);
			throw e;
		}
	}

	public List<WfMatlApply> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfMatlApplyHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlApplyDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfMatlApply> list = wfMatlApplyHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfMatlApply)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlApplyDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfMatlApply) throws java.sql.SQLException {
		WfMatlApply _wfMatlApply = (WfMatlApply)wfMatlApply;
		String sql = "select "+WfMatlApply.ALL_FIELDS+wfMatlApplyHelper.getSqlString()+" and WfMatlApply.MatlId = '"+_wfMatlApply.getMatlId()+"'";
		try {
			List<WfMatlApply> list = wfMatlApplyHelper.getQueryList(query(sql),WfMatlApply.ALL_FIELDS);
			if(list.size() > 0)
				return (WfMatlApply)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlApplyDAO.load", e);
			throw e;
		}
	}
}