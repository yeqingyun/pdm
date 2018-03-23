package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfFieldHelper;
import gnwf.vo.WfField;

public class WfFieldDAO extends BasicDAO implements GenericDAO {

	private WfFieldHelper wfFieldHelper = new WfFieldHelper();

	public void insert(Object wfField) throws java.sql.SQLException {
		WfField _wfField = (WfField)wfField;
		String fields = wfFieldHelper.getFields(_wfField);
		String sql = wfFieldHelper.getInsertSql(fields);
		try {
			wfFieldHelper.pstmtInsert(_wfField, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfFieldDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfField) throws java.sql.SQLException {
		WfField _wfField = (WfField)wfField;
		String fields = wfFieldHelper.getFields(_wfField);
		String sql = wfFieldHelper.getUpdateSql(fields, "WfField.FieldId" ,_wfField.getFieldId().toString());
		try {
			wfFieldHelper.pstmtUpdate(_wfField, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfFieldDAO.update", e);
			throw e;
		}
	}

	public List<WfField> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfFieldHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfFieldDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfField> list = wfFieldHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfField)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfFieldDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfField) throws java.sql.SQLException {
		WfField _wfField = (WfField)wfField;
		String sql = "select "+WfField.ALL_FIELDS+wfFieldHelper.getSqlString()+" and WfField.FieldId = '"+_wfField.getFieldId()+"'";
		try {
			List<WfField> list = wfFieldHelper.getQueryList(query(sql),WfField.ALL_FIELDS);
			if(list.size() > 0)
				return (WfField)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfFieldDAO.load", e);
			throw e;
		}
	}
}