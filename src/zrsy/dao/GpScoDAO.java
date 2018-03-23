package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.GpScoHelper;
import zrsy.vo.GpSco;

public class GpScoDAO extends BasicDAO implements GenericDAO {

	private GpScoHelper gpScoHelper = new GpScoHelper();

	public void insert(Object gpSco) throws java.sql.SQLException {
		GpSco _gpSco = (GpSco)gpSco;
		String fields = gpScoHelper.getFields(_gpSco);
		String sql = gpScoHelper.getInsertSql(fields);
		try {
			gpScoHelper.pstmtInsert(_gpSco, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpScoDAO.insert", e);
			throw e;
		}
	}

	public void update(Object gpSco) throws java.sql.SQLException {
		GpSco _gpSco = (GpSco)gpSco;
		String fields = gpScoHelper.getFields(_gpSco);
		String sql = gpScoHelper.getUpdateSql(fields, "GpSco.GpId" ,_gpSco.getGpId().toString());
		try {
			gpScoHelper.pstmtUpdate(_gpSco, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpScoDAO.update", e);
			throw e;
		}
	}

	public List<GpSco> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return gpScoHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpScoDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<GpSco> list = gpScoHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (GpSco)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpScoDAO.load", e);
			throw e;
		}
	}

	public Object load(Object gpSco) throws java.sql.SQLException {
		GpSco _gpSco = (GpSco)gpSco;
		String sql = "select "+GpSco.ALL_FIELDS+gpScoHelper.getSqlString()+" and GpSco.GpId = '"+_gpSco.getGpId()+"'";
		try {
			List<GpSco> list = gpScoHelper.getQueryList(query(sql),GpSco.ALL_FIELDS);
			if(list.size() > 0)
				return (GpSco)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpScoDAO.load", e);
			throw e;
		}
	}
}