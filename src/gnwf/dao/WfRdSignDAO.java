package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfRdSignHelper;
import gnwf.vo.WfRdSign;

public class WfRdSignDAO extends BasicDAO implements GenericDAO {

	private WfRdSignHelper wfRdSignHelper = new WfRdSignHelper();

	public void insert(Object wfRdSign) throws java.sql.SQLException {
		WfRdSign _wfRdSign = (WfRdSign)wfRdSign;
		String fields = wfRdSignHelper.getFields(_wfRdSign);
		String sql = wfRdSignHelper.getInsertSql(fields);
		try {
			wfRdSignHelper.pstmtInsert(_wfRdSign, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdSignDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfRdSign) throws java.sql.SQLException {
		WfRdSign _wfRdSign = (WfRdSign)wfRdSign;
		String fields = wfRdSignHelper.getFields(_wfRdSign);
		String sql = wfRdSignHelper.getUpdateSql(fields, "WfRdSign.TaskId" ,_wfRdSign.getTaskId().toString());
		try {
			wfRdSignHelper.pstmtUpdate(_wfRdSign, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdSignDAO.update", e);
			throw e;
		}
	}

	public List<WfRdSign> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfRdSignHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdSignDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfRdSign> list = wfRdSignHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfRdSign)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdSignDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfRdSign) throws java.sql.SQLException {
		WfRdSign _wfRdSign = (WfRdSign)wfRdSign;
		String sql = "select "+WfRdSign.ALL_FIELDS+wfRdSignHelper.getSqlString()+" and WfRdSign.TaskId = '"+_wfRdSign.getTaskId()+"'";
		try {
			List<WfRdSign> list = wfRdSignHelper.getQueryList(query(sql),WfRdSign.ALL_FIELDS);
			if(list.size() > 0)
				return (WfRdSign)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdSignDAO.load", e);
			throw e;
		}
	}
}