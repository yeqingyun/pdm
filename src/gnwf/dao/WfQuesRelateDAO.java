package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WfQuesRelateHelper;
import gnwf.vo.WfQuesRelate;

public class WfQuesRelateDAO extends BasicDAO implements GenericDAO {

	private WfQuesRelateHelper wfQuesRelateHelper = new WfQuesRelateHelper();

	public void insert(Object wfQuesRelate) throws java.sql.SQLException {
		WfQuesRelate _wfQuesRelate = (WfQuesRelate)wfQuesRelate;
		String fields = wfQuesRelateHelper.getFields(_wfQuesRelate);
		String sql = wfQuesRelateHelper.getInsertSql(fields);
		try {
			wfQuesRelateHelper.pstmtInsert(_wfQuesRelate, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfQuesRelateDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfQuesRelate) throws java.sql.SQLException {
		WfQuesRelate _wfQuesRelate = (WfQuesRelate)wfQuesRelate;
		String fields = wfQuesRelateHelper.getFields(_wfQuesRelate);
		String sql = wfQuesRelateHelper.getUpdateSql(fields, "WfQuesRelate.QuesId" ,_wfQuesRelate.getQuesId().toString());
		try {
			wfQuesRelateHelper.pstmtUpdate(_wfQuesRelate, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfQuesRelateDAO.update", e);
			throw e;
		}
	}

	public List<WfQuesRelate> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfQuesRelateHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfQuesRelateDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfQuesRelate> list = wfQuesRelateHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfQuesRelate)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfQuesRelateDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfQuesRelate) throws java.sql.SQLException {
		WfQuesRelate _wfQuesRelate = (WfQuesRelate)wfQuesRelate;
		String sql = "select "+WfQuesRelate.ALL_FIELDS+wfQuesRelateHelper.getSqlString()+" and WfQuesRelate.QuesId = '"+_wfQuesRelate.getQuesId()+"'";
		try {
			List<WfQuesRelate> list = wfQuesRelateHelper.getQueryList(query(sql),WfQuesRelate.ALL_FIELDS);
			if(list.size() > 0)
				return (WfQuesRelate)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfQuesRelateDAO.load", e);
			throw e;
		}
	}
}