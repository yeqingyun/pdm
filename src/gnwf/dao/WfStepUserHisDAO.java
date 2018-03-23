package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfStepUserHisHelper;
import gnwf.vo.WfStepUserHis;

public class WfStepUserHisDAO extends BasicDAO implements GenericDAO {

	private WfStepUserHisHelper wfStepUserHisHelper = new WfStepUserHisHelper();

	public void insert(Object wfStepUserHis) throws java.sql.SQLException {
		WfStepUserHis _wfStepUserHis = (WfStepUserHis)wfStepUserHis;
		String fields = wfStepUserHisHelper.getFields(_wfStepUserHis);
		String sql = wfStepUserHisHelper.getInsertSql(fields);
		try {
			wfStepUserHisHelper.pstmtInsert(_wfStepUserHis, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepUserHisDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfStepUserHis) throws java.sql.SQLException {
		WfStepUserHis _wfStepUserHis = (WfStepUserHis)wfStepUserHis;
		String fields = wfStepUserHisHelper.getFields(_wfStepUserHis);
		String sql = wfStepUserHisHelper.getUpdateSql(fields, "WfStepUserHis.StepID" ,_wfStepUserHis.getStepID().toString());
		try {
			wfStepUserHisHelper.pstmtUpdate(_wfStepUserHis, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepUserHisDAO.update", e);
			throw e;
		}
	}

	public List<WfStepUserHis> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfStepUserHisHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepUserHisDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfStepUserHis> list = wfStepUserHisHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfStepUserHis)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepUserHisDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfStepUserHis) throws java.sql.SQLException {
		WfStepUserHis _wfStepUserHis = (WfStepUserHis)wfStepUserHis;
		String sql = "select "+WfStepUserHis.ALL_FIELDS+wfStepUserHisHelper.getSqlString()+" and WfStepUserHis.StepID = '"+_wfStepUserHis.getStepID()+"'";
		try {
			List<WfStepUserHis> list = wfStepUserHisHelper.getQueryList(query(sql),WfStepUserHis.ALL_FIELDS);
			if(list.size() > 0)
				return (WfStepUserHis)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepUserHisDAO.load", e);
			throw e;
		}
	}
}