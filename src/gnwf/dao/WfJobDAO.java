package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfJobHelper;
import gnwf.vo.WfJob;

public class WfJobDAO extends BasicDAO implements GenericDAO {

	private WfJobHelper wfJobHelper = new WfJobHelper();

	public void insert(Object wfJob) throws java.sql.SQLException {
		WfJob _wfJob = (WfJob)wfJob;
		String fields = wfJobHelper.getFields(_wfJob);
		String sql = wfJobHelper.getInsertSql(fields);
		try {
			wfJobHelper.pstmtInsert(_wfJob, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfJobDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfJob) throws java.sql.SQLException {
		WfJob _wfJob = (WfJob)wfJob;
		String fields = wfJobHelper.getFields(_wfJob);
		String sql = wfJobHelper.getUpdateSql(fields, "WfJob.JobId" ,_wfJob.getJobId().toString());
		try {
			wfJobHelper.pstmtUpdate(_wfJob, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfJobDAO.update", e);
			throw e;
		}
	}

	public List<WfJob> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfJobHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfJobDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfJob> list = wfJobHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfJob)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfJobDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfJob) throws java.sql.SQLException {
		WfJob _wfJob = (WfJob)wfJob;
		String sql = "select "+WfJob.ALL_FIELDS+wfJobHelper.getSqlString()+" and WfJob.JobId = '"+_wfJob.getJobId()+"'";
		try {
			List<WfJob> list = wfJobHelper.getQueryList(query(sql),WfJob.ALL_FIELDS);
			if(list.size() > 0)
				return (WfJob)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfJobDAO.load", e);
			throw e;
		}
	}
}