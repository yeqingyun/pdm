package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfMatlProHelper;
import gnwf.vo.WfMatlPro;

public class WfMatlProDAO extends BasicDAO implements GenericDAO {

	private WfMatlProHelper wfMatlProHelper = new WfMatlProHelper();

	public void insert(Object wfMatlPro) throws java.sql.SQLException {
		WfMatlPro _wfMatlPro = (WfMatlPro)wfMatlPro;
		String fields = wfMatlProHelper.getFields(_wfMatlPro);
		String sql = wfMatlProHelper.getInsertSql(fields);
		try {
			wfMatlProHelper.pstmtInsert(_wfMatlPro, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlProDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfMatlPro) throws java.sql.SQLException {
		WfMatlPro _wfMatlPro = (WfMatlPro)wfMatlPro;
		String fields = wfMatlProHelper.getFields(_wfMatlPro);
		String sql = wfMatlProHelper.getUpdateSql(fields, "WfMatlPro.MatlProId" ,_wfMatlPro.getMatlProId().toString());
		try {
			wfMatlProHelper.pstmtUpdate(_wfMatlPro, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlProDAO.update", e);
			throw e;
		}
	}

	public List<WfMatlPro> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfMatlProHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlProDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfMatlPro> list = wfMatlProHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfMatlPro)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlProDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfMatlPro) throws java.sql.SQLException {
		WfMatlPro _wfMatlPro = (WfMatlPro)wfMatlPro;
		String sql = "select "+WfMatlPro.ALL_FIELDS+wfMatlProHelper.getSqlString()+" and WfMatlPro.MatlProId = '"+_wfMatlPro.getMatlProId()+"'";
		try {
			List<WfMatlPro> list = wfMatlProHelper.getQueryList(query(sql),WfMatlPro.ALL_FIELDS);
			if(list.size() > 0)
				return (WfMatlPro)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlProDAO.load", e);
			throw e;
		}
	}
}