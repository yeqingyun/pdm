package zrprjt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import org.frm.jdbc.DbConnUtil;

import zrprjt.dao.helper.SchCfgHelper;
import zrprjt.vo.SchCfg;

public class SchCfgDAO extends BasicDAO implements GenericDAO {

	private SchCfgHelper schCfgHelper = new SchCfgHelper();

	public void insert(Object schCfg) throws java.sql.SQLException {
		SchCfg _schCfg = (SchCfg)schCfg;
		String fields = schCfgHelper.getFields(_schCfg);
		String sql = schCfgHelper.getInsertSql(fields);
		try {
			schCfgHelper.pstmtInsert(_schCfg, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SchCfgDAO.insert", e);
			throw e;
		}
	}

	public void update(Object schCfg) throws java.sql.SQLException {
		SchCfg _schCfg = (SchCfg)schCfg;
		String fields = schCfgHelper.getFields(_schCfg);
		String sql = schCfgHelper.getUpdateSql(fields, "SchCfg.SchId" ,_schCfg.getSchId().toString());
		try {
			schCfgHelper.pstmtUpdate(_schCfg, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SchCfgDAO.update", e);
			throw e;
		}
	}

	public List<SchCfg> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return schCfgHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error("SchCfgDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<SchCfg> list = schCfgHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (SchCfg)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SchCfgDAO.load", e);
			throw e;
		}
	}

	public Object load(Object schCfg) throws java.sql.SQLException {
		SchCfg _schCfg = (SchCfg)schCfg;
		String sql = "select "+SchCfg.ALL_FIELDS+schCfgHelper.getSqlString()+" and SchCfg.SchId = '"+_schCfg.getSchId()+"'";
		try {
			List<SchCfg> list = schCfgHelper.getQueryList(query(sql),SchCfg.ALL_FIELDS);
			if(list.size() > 0)
				return (SchCfg)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SchCfgDAO.load", e);
			throw e;
		}
	}

	
	
	public int getMaxId (String sql) throws Exception{
		int maxId =0;
		try {
			DbConnUtil.buildDbconn(1);
			ResultSet  rs = query(sql);
			while(rs.next()){
				maxId = rs.getInt("maxid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maxId;
	}
}