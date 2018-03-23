package zrprjt.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrprjt.dao.helper.SchWfHelper;
import zrprjt.vo.SchWf;

public class SchWfDAO extends BasicDAO implements GenericDAO {

	private SchWfHelper schWfHelper = new SchWfHelper();

	public void insert(Object schWf) throws java.sql.SQLException {
		SchWf _schWf = (SchWf)schWf;
		String fields = schWfHelper.getFields(_schWf);
		String sql = schWfHelper.getInsertSql(fields);
		try {
			schWfHelper.pstmtInsert(_schWf, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SchWfDAO.insert", e);
			throw e;
		}
	}

	public void update(Object schWf) throws java.sql.SQLException {
		SchWf _schWf = (SchWf)schWf;
		String fields = schWfHelper.getFields(_schWf);
		String sql = schWfHelper.getUpdateSql(fields, "SchWf.SchId" ,_schWf.getSchId().toString());
		try {
			schWfHelper.pstmtUpdate(_schWf, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SchWfDAO.update", e);
			throw e;
		}
	}

	public List<SchWf> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return schWfHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SchWfDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<SchWf> list = schWfHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (SchWf)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SchWfDAO.load", e);
			throw e;
		}
	}

	public Object load(Object schWf) throws java.sql.SQLException {
		SchWf _schWf = (SchWf)schWf;
		String sql = "select "+SchWf.ALL_FIELDS+schWfHelper.getSqlString()+" and SchWf.SchId = '"+_schWf.getSchId()+"'";
		try {
			List<SchWf> list = schWfHelper.getQueryList(query(sql),SchWf.ALL_FIELDS);
			if(list.size() > 0)
				return (SchWf)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SchWfDAO.load", e);
			throw e;
		}
	}
}