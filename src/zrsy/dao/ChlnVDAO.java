package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.ChlnVHelper;
import zrsy.vo.ChlnV;

public class ChlnVDAO extends BasicDAO implements GenericDAO {

	private ChlnVHelper chlnVHelper = new ChlnVHelper();

	public void insert(Object chlnV) throws java.sql.SQLException {
		ChlnV _chlnV = (ChlnV)chlnV;
		String fields = chlnVHelper.getFields(_chlnV);
		String sql = chlnVHelper.getInsertSql(fields);
		try {
			chlnVHelper.pstmtInsert(_chlnV, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ChlnVDAO.insert", e);
			throw e;
		}
	}

	public void update(Object chlnV) throws java.sql.SQLException {
		ChlnV _chlnV = (ChlnV)chlnV;
		String fields = chlnVHelper.getFields(_chlnV);
		String sql = chlnVHelper.getUpdateSql(fields, "ChlnV.FileNm" ,_chlnV.getFileNm().toString());
		try {
			chlnVHelper.pstmtUpdate(_chlnV, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ChlnVDAO.update", e);
			throw e;
		}
	}

	public List<ChlnV> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return chlnVHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ChlnVDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<ChlnV> list = chlnVHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (ChlnV)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ChlnVDAO.load", e);
			throw e;
		}
	}

	public Object load(Object chlnV) throws java.sql.SQLException {
		ChlnV _chlnV = (ChlnV)chlnV;
		String sql = "select "+ChlnV.ALL_FIELDS+chlnVHelper.getSqlString()+" and ChlnV.FileNm = '"+_chlnV.getFileNm()+"'";
		try {
			List<ChlnV> list = chlnVHelper.getQueryList(query(sql),ChlnV.ALL_FIELDS);
			if(list.size() > 0)
				return (ChlnV)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ChlnVDAO.load", e);
			throw e;
		}
	}
}