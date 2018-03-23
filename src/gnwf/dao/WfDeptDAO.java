package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfDeptHelper;
import gnwf.vo.WfDept;

public class WfDeptDAO extends BasicDAO implements GenericDAO {

	private WfDeptHelper wfDeptHelper = new WfDeptHelper();

	public void insert(Object wfDept) throws java.sql.SQLException {
		WfDept _wfDept = (WfDept)wfDept;
		String fields = wfDeptHelper.getFields(_wfDept);
		String sql = wfDeptHelper.getInsertSql(fields);
		try {
			wfDeptHelper.pstmtInsert(_wfDept, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfDeptDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfDept) throws java.sql.SQLException {
		WfDept _wfDept = (WfDept)wfDept;
		String fields = wfDeptHelper.getFields(_wfDept);
		String sql = wfDeptHelper.getUpdateSql(fields, "WfDept.FlowId" ,_wfDept.getFlowId().toString());
		try {
			wfDeptHelper.pstmtUpdate(_wfDept, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfDeptDAO.update", e);
			throw e;
		}
	}

	public List<WfDept> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfDeptHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfDeptDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfDept> list = wfDeptHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfDept)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfDeptDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfDept) throws java.sql.SQLException {
		WfDept _wfDept = (WfDept)wfDept;
		String sql = "select "+WfDept.ALL_FIELDS+wfDeptHelper.getSqlString()+" and WfDept.FlowId = '"+_wfDept.getFlowId()+"'";
		try {
			List<WfDept> list = wfDeptHelper.getQueryList(query(sql),WfDept.ALL_FIELDS);
			if(list.size() > 0)
				return (WfDept)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfDeptDAO.load", e);
			throw e;
		}
	}
}