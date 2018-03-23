package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfMatlEvalHelper;
import gnwf.vo.WfMatlEval;

public class WfMatlEvalDAO extends BasicDAO implements GenericDAO {

	private WfMatlEvalHelper wfMatlEvalHelper = new WfMatlEvalHelper();

	public void insert(Object wfMatlEval) throws java.sql.SQLException {
		WfMatlEval _wfMatlEval = (WfMatlEval)wfMatlEval;
		String fields = wfMatlEvalHelper.getFields(_wfMatlEval);
		String sql = wfMatlEvalHelper.getInsertSql(fields);
		try {
			wfMatlEvalHelper.pstmtInsert(_wfMatlEval, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlEvalDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfMatlEval) throws java.sql.SQLException {
		WfMatlEval _wfMatlEval = (WfMatlEval)wfMatlEval;
		String fields = wfMatlEvalHelper.getFields(_wfMatlEval);
		String sql = wfMatlEvalHelper.getUpdateSql(fields, "WfMatlEval.MatlId" ,_wfMatlEval.getMatlId().toString());
		try {
			wfMatlEvalHelper.pstmtUpdate(_wfMatlEval, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlEvalDAO.update", e);
			throw e;
		}
	}

	public List<WfMatlEval> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfMatlEvalHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlEvalDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfMatlEval> list = wfMatlEvalHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfMatlEval)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlEvalDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfMatlEval) throws java.sql.SQLException {
		WfMatlEval _wfMatlEval = (WfMatlEval)wfMatlEval;
		String sql = "select "+WfMatlEval.ALL_FIELDS+wfMatlEvalHelper.getSqlString()+" and WfMatlEval.MatlId = '"+_wfMatlEval.getMatlId()+"'";
		try {
			List<WfMatlEval> list = wfMatlEvalHelper.getQueryList(query(sql),WfMatlEval.ALL_FIELDS);
			if(list.size() > 0)
				return (WfMatlEval)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlEvalDAO.load", e);
			throw e;
		}
	}
}