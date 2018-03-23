package zrsy.dao;
import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.SyScheHelper;
import zrsy.vo.SySche;
public class SyScheDAO extends BasicDAO implements GenericDAO {

	private SyScheHelper syScheHelper = new SyScheHelper();

	public void insert(Object sySche) throws java.sql.SQLException {
		SySche _sySche = (SySche)sySche;
		String fields = syScheHelper.getFields(_sySche);
		String sql = syScheHelper.getInsertSql(fields);
		try {
			syScheHelper.pstmtInsert(_sySche, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SyScheDAO.insert", e);
			throw e;
		}
	}

	public void update(Object sySche) throws java.sql.SQLException {
		SySche _sySche = (SySche)sySche;
		String fields = syScheHelper.getFields(_sySche);
		String sql = syScheHelper.getUpdateSql(fields, "SySche.ScheId" ,_sySche.getScheId().toString());
		try {
			syScheHelper.pstmtUpdate(_sySche, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SyScheDAO.update", e);
			throw e;
		}
	}

	public List<SySche> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return syScheHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SyScheDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<SySche> list = syScheHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (SySche)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SyScheDAO.load", e);
			throw e;
		}
	}

	public Object load(Object sySche) throws java.sql.SQLException {
		SySche _sySche = (SySche)sySche;
		String sql = "select "+SySche.ALL_FIELDS+syScheHelper.getSqlString()+" and SySche.ScheId = '"+_sySche.getScheId()+"'";
		try {
			List<SySche> list = syScheHelper.getQueryList(query(sql),SySche.ALL_FIELDS);
			if(list.size() > 0)
				return (SySche)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SyScheDAO.load", e);
			throw e;
		}
	}
}
