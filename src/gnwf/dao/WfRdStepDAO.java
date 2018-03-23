package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfRdStepHelper;
import gnwf.vo.WfRdStep;

public class WfRdStepDAO extends BasicDAO implements GenericDAO {

	private WfRdStepHelper wfRdStepHelper = new WfRdStepHelper();

	public void insert(Object wfRdStep) throws java.sql.SQLException {
		WfRdStep _wfRdStep = (WfRdStep)wfRdStep;
		String fields = wfRdStepHelper.getFields(_wfRdStep);
		String sql = wfRdStepHelper.getInsertSql(fields);
		try {
			wfRdStepHelper.pstmtInsert(_wfRdStep, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdStepDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfRdStep) throws java.sql.SQLException {
		WfRdStep _wfRdStep = (WfRdStep)wfRdStep;
		String fields = wfRdStepHelper.getFields(_wfRdStep);
		String sql = wfRdStepHelper.getUpdateSql(fields, "WfRdStep.StepId" ,_wfRdStep.getStepId().toString());
		try {
			wfRdStepHelper.pstmtUpdate(_wfRdStep, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdStepDAO.update", e);
			throw e;
		}
	}

	public List<WfRdStep> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfRdStepHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdStepDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfRdStep> list = wfRdStepHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfRdStep)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdStepDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfRdStep) throws java.sql.SQLException {
		WfRdStep _wfRdStep = (WfRdStep)wfRdStep;
		String sql = "select "+WfRdStep.ALL_FIELDS+wfRdStepHelper.getSqlString()+" and WfRdStep.StepId = '"+_wfRdStep.getStepId()+"'";
		try {
			List<WfRdStep> list = wfRdStepHelper.getQueryList(query(sql),WfRdStep.ALL_FIELDS);
			if(list.size() > 0)
				return (WfRdStep)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdStepDAO.load", e);
			throw e;
		}
	}
}