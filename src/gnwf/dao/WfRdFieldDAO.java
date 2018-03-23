package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfRdFieldHelper;
import gnwf.vo.WfRdField;

public class WfRdFieldDAO extends BasicDAO implements GenericDAO {

	private WfRdFieldHelper wfRdFieldHelper = new WfRdFieldHelper();

	public void insert(Object wfRdField) throws java.sql.SQLException {
		WfRdField _wfRdField = (WfRdField)wfRdField;
		String fields = wfRdFieldHelper.getFields(_wfRdField);
		String sql = wfRdFieldHelper.getInsertSql(fields);
		try {
			wfRdFieldHelper.pstmtInsert(_wfRdField, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdFieldDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfRdField) throws java.sql.SQLException {
		WfRdField _wfRdField = (WfRdField)wfRdField;
		String fields = wfRdFieldHelper.getFields(_wfRdField);
		String sql = wfRdFieldHelper.getUpdateSql(fields, "WfRdField.FieldId" ,_wfRdField.getFieldId().toString());
		try {
			wfRdFieldHelper.pstmtUpdate(_wfRdField, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdFieldDAO.update", e);
			throw e;
		}
	}

	public List<WfRdField> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfRdFieldHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdFieldDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfRdField> list = wfRdFieldHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfRdField)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdFieldDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfRdField) throws java.sql.SQLException {
		WfRdField _wfRdField = (WfRdField)wfRdField;
		String sql = "select "+WfRdField.ALL_FIELDS+wfRdFieldHelper.getSqlString()+" and WfRdField.FieldId = '"+_wfRdField.getFieldId()+"'";
		try {
			List<WfRdField> list = wfRdFieldHelper.getQueryList(query(sql),WfRdField.ALL_FIELDS);
			if(list.size() > 0)
				return (WfRdField)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdFieldDAO.load", e);
			throw e;
		}
	}
}