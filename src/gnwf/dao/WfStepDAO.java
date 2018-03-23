package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfStepHelper;
import gnwf.vo.WfStep;

public class WfStepDAO extends BasicDAO implements GenericDAO {

	private WfStepHelper wfStepHelper = new WfStepHelper();

	public void insert(Object wfStep) throws java.sql.SQLException {
		WfStep _wfStep = (WfStep)wfStep;
		String fields = wfStepHelper.getFields(_wfStep);
		String sql = wfStepHelper.getInsertSql(fields);
		try {
			wfStepHelper.pstmtInsert(_wfStep, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfStep) throws java.sql.SQLException {
		WfStep _wfStep = (WfStep)wfStep;
		String fields = wfStepHelper.getFields(_wfStep);
		String sql = wfStepHelper.getUpdateSql(fields, "WfStep.StepId" ,_wfStep.getStepId().toString());
		try {
			wfStepHelper.pstmtUpdate(_wfStep, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepDAO.update", e);
			throw e;
		}
	}

	public List<WfStep> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfStepHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfStep> list = wfStepHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfStep)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfStep) throws java.sql.SQLException {
		WfStep _wfStep = (WfStep)wfStep;
		String sql = "select "+WfStep.ALL_FIELDS+wfStepHelper.getSqlString()+" and WfStep.StepId = '"+_wfStep.getStepId()+"'";
		try {
			List<WfStep> list = wfStepHelper.getQueryList(query(sql),WfStep.ALL_FIELDS);
			if(list.size() > 0)
				return (WfStep)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepDAO.load", e);
			throw e;
		}
	}
}