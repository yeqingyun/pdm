package zrprjt.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrprjt.dao.helper.PrjtPointHelper;
import zrprjt.vo.PrjtPoint;

public class PrjtPointDAO extends BasicDAO implements GenericDAO {

	private PrjtPointHelper PrjtPointHelper = new PrjtPointHelper();

	public void insert(Object prjtPoint) throws java.sql.SQLException { 
		PrjtPoint _PrjtPoint = (PrjtPoint)prjtPoint;
		String fields = PrjtPointHelper.getFields(_PrjtPoint);
		String sql = PrjtPointHelper.getInsertSql(fields);
		try {
			PrjtPointHelper.pstmtInsert(_PrjtPoint, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtPointDAO.insert", e);
			throw e;
		}
	}

	public void update(Object PrjtPoint) throws java.sql.SQLException {
		PrjtPoint _PrjtPoint = (PrjtPoint)PrjtPoint;
		String fields = PrjtPointHelper.getFields(_PrjtPoint);
		String sql = PrjtPointHelper.getUpdateSql(fields, "PrjtPoint.PointId" ,_PrjtPoint.getPointId().toString());
		try {
			PrjtPointHelper.pstmtUpdate(_PrjtPoint, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtPointDAO.update", e);
			throw e;
		}
	}

	public List<PrjtPoint> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return PrjtPointHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtPointDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<PrjtPoint> list = PrjtPointHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (PrjtPoint)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtPointDAO.load", e);
			throw e;
		}
	}

	public Object load(Object prjtPoint) throws java.sql.SQLException { 
		PrjtPoint _PrjtPoint = (PrjtPoint)prjtPoint;
		String sql = "select "+PrjtPoint.ALL_FIELDS+PrjtPointHelper.getSqlString()+" and PrjtPoint.PointId = '"+_PrjtPoint.getPointId()+"'";
		try {
			List<PrjtPoint> list = PrjtPointHelper.getQueryList(query(sql),PrjtPoint.ALL_FIELDS);
			if(list.size() > 0)
				return (PrjtPoint)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtPointDAO.load", e);
			throw e;
		}
	}
}