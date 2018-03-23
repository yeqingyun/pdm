package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfFieldStepRelateHelper;
import gnwf.vo.WfFieldStepRelate;

public class WfFieldStepRelateDAO extends BasicDAO implements GenericDAO {

	private WfFieldStepRelateHelper wfFieldStepRelateHelper = new WfFieldStepRelateHelper();

	public void insert(Object wfFieldStepRelate) throws java.sql.SQLException {
		WfFieldStepRelate _wfFieldStepRelate = (WfFieldStepRelate)wfFieldStepRelate;
		String fields = wfFieldStepRelateHelper.getFields(_wfFieldStepRelate);
		String sql = wfFieldStepRelateHelper.getInsertSql(fields);
		try {
			wfFieldStepRelateHelper.pstmtInsert(_wfFieldStepRelate, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfFieldStepRelateDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfFieldStepRelate) throws java.sql.SQLException {
		WfFieldStepRelate _wfFieldStepRelate = (WfFieldStepRelate)wfFieldStepRelate;
		String fields = wfFieldStepRelateHelper.getFields(_wfFieldStepRelate);
		String sql = wfFieldStepRelateHelper.getUpdateSql(fields, "WfFieldStepRelate.FieldId" ,_wfFieldStepRelate.getFieldId().toString());
		try {
			wfFieldStepRelateHelper.pstmtUpdate(_wfFieldStepRelate, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfFieldStepRelateDAO.update", e);
			throw e;
		}
	}

	public List<WfFieldStepRelate> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfFieldStepRelateHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfFieldStepRelateDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfFieldStepRelate> list = wfFieldStepRelateHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfFieldStepRelate)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfFieldStepRelateDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfFieldStepRelate) throws java.sql.SQLException {
		WfFieldStepRelate _wfFieldStepRelate = (WfFieldStepRelate)wfFieldStepRelate;
		String sql = "select "+WfFieldStepRelate.ALL_FIELDS+wfFieldStepRelateHelper.getSqlString()+" and WfFieldStepRelate.FieldId = '"+_wfFieldStepRelate.getFieldId()+"'";
		try {
			List<WfFieldStepRelate> list = wfFieldStepRelateHelper.getQueryList(query(sql),WfFieldStepRelate.ALL_FIELDS);
			if(list.size() > 0)
				return (WfFieldStepRelate)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfFieldStepRelateDAO.load", e);
			throw e;
		}
	}
}