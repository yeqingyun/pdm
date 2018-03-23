package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfStepNextHelper;
import gnwf.vo.WfStepNext;

public class WfStepNextDAO extends BasicDAO implements GenericDAO {

	private WfStepNextHelper wfStepNextHelper = new WfStepNextHelper();

	public void insert(Object wfStepNext) throws java.sql.SQLException {
		WfStepNext _wfStepNext = (WfStepNext)wfStepNext;
		String fields = wfStepNextHelper.getFields(_wfStepNext);
		String sql = wfStepNextHelper.getInsertSql(fields);
		try {
			wfStepNextHelper.pstmtInsert(_wfStepNext, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepNextDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfStepNext) throws java.sql.SQLException {
		WfStepNext _wfStepNext = (WfStepNext)wfStepNext;
		String fields = wfStepNextHelper.getFields(_wfStepNext);
		String sql = wfStepNextHelper.getUpdateSql(fields, "WfStepNext.StepId" ,_wfStepNext.getStepId().toString());
		try {
			wfStepNextHelper.pstmtUpdate(_wfStepNext, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepNextDAO.update", e);
			throw e;
		}
	}

	public List<WfStepNext> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfStepNextHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepNextDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfStepNext> list = wfStepNextHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfStepNext)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepNextDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfStepNext) throws java.sql.SQLException {
		WfStepNext _wfStepNext = (WfStepNext)wfStepNext;
		String sql = "select "+WfStepNext.ALL_FIELDS+wfStepNextHelper.getSqlString()+" and WfStepNext.StepId = '"+_wfStepNext.getStepId()+"'";
		try {
			List<WfStepNext> list = wfStepNextHelper.getQueryList(query(sql),WfStepNext.ALL_FIELDS);
			if(list.size() > 0)
				return (WfStepNext)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepNextDAO.load", e);
			throw e;
		}
	}
}