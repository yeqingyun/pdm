package zrprjt.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrprjt.dao.helper.SchDtlHelper;
import zrprjt.vo.SchDtl;

public class SchDtlDAO extends BasicDAO implements GenericDAO {

	private SchDtlHelper schDtlHelper = new SchDtlHelper();

	public void insert(Object schDtl) throws java.sql.SQLException {
		SchDtl _schDtl = (SchDtl)schDtl;
		String fields = schDtlHelper.getFields(_schDtl);
		String sql = schDtlHelper.getInsertSql(fields);
		try {
			schDtlHelper.pstmtInsert(_schDtl, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SchDtlDAO.insert", e);
			throw e;
		}
	}

	public void update(Object schDtl) throws java.sql.SQLException {
		SchDtl _schDtl = (SchDtl)schDtl;
		String fields = schDtlHelper.getFields(_schDtl);
		String sql = schDtlHelper.getUpdateSql(fields, "SchDtl.SchDtlId" ,_schDtl.getSchDtlId().toString());
		try {
			schDtlHelper.pstmtUpdate(_schDtl, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SchDtlDAO.update", e);
			throw e;
		}
	}

	public List<SchDtl> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return schDtlHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SchDtlDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<SchDtl> list = schDtlHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (SchDtl)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SchDtlDAO.load", e);
			throw e;
		}
	}

	public Object load(Object schDtl) throws java.sql.SQLException {
		SchDtl _schDtl = (SchDtl)schDtl;
		String sql = "select "+SchDtl.ALL_FIELDS+schDtlHelper.getSqlString()+" and SchDtl.SchDtlId = '"+_schDtl.getSchDtlId()+"'";
		try {
			List<SchDtl> list = schDtlHelper.getQueryList(query(sql),SchDtl.ALL_FIELDS);
			if(list.size() > 0)
				return (SchDtl)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SchDtlDAO.load", e);
			throw e;
		}
	}
}