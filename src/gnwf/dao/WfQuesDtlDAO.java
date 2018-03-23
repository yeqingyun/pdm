package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfQuesDtlHelper;
import gnwf.vo.WfQuesDtl;

public class WfQuesDtlDAO extends BasicDAO implements GenericDAO {

	private WfQuesDtlHelper wfQuesDtlHelper = new WfQuesDtlHelper();

	public void insert(Object wfQuesDtl) throws java.sql.SQLException {
		WfQuesDtl _wfQuesDtl = (WfQuesDtl)wfQuesDtl;
		String fields = wfQuesDtlHelper.getFields(_wfQuesDtl);
		String sql = wfQuesDtlHelper.getInsertSql(fields);
		try {
			wfQuesDtlHelper.pstmtInsert(_wfQuesDtl, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfQuesDtlDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfQuesDtl) throws java.sql.SQLException {
		WfQuesDtl _wfQuesDtl = (WfQuesDtl)wfQuesDtl;
		String fields = wfQuesDtlHelper.getFields(_wfQuesDtl);
		String sql = wfQuesDtlHelper.getUpdateSql(fields, "WfQuesDtl.QuesDtlId" ,_wfQuesDtl.getQuesDtlId().toString());
		try {
			wfQuesDtlHelper.pstmtUpdate(_wfQuesDtl, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfQuesDtlDAO.update", e);
			throw e;
		}
	}

	public List<WfQuesDtl> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfQuesDtlHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfQuesDtlDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfQuesDtl> list = wfQuesDtlHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfQuesDtl)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfQuesDtlDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfQuesDtl) throws java.sql.SQLException {
		WfQuesDtl _wfQuesDtl = (WfQuesDtl)wfQuesDtl;
		String sql = "select "+WfQuesDtl.ALL_FIELDS+wfQuesDtlHelper.getSqlString()+" and WfQuesDtl.QuesDtlId = '"+_wfQuesDtl.getQuesDtlId()+"'";
		try {
			List<WfQuesDtl> list = wfQuesDtlHelper.getQueryList(query(sql),WfQuesDtl.ALL_FIELDS);
			if(list.size() > 0)
				return (WfQuesDtl)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfQuesDtlDAO.load", e);
			throw e;
		}
	}
}