package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.GpNodeHelper;
import zrsy.vo.GpNode;

public class GpNodeDAO extends BasicDAO implements GenericDAO {

	private GpNodeHelper gpNodeHelper = new GpNodeHelper();

	public void insert(Object gpNode) throws java.sql.SQLException {
		GpNode _gpNode = (GpNode)gpNode;
		String fields = gpNodeHelper.getFields(_gpNode);
		String sql = gpNodeHelper.getInsertSql(fields);
		try {
			gpNodeHelper.pstmtInsert(_gpNode, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpNodeDAO.insert", e);
			throw e;
		}
	}

	public void update(Object gpNode) throws java.sql.SQLException {
		GpNode _gpNode = (GpNode)gpNode;
		String fields = gpNodeHelper.getFields(_gpNode);
		String sql = gpNodeHelper.getUpdateSql(fields, "GpNode.GpId" ,_gpNode.getGpId().toString());
		try {
			gpNodeHelper.pstmtUpdate(_gpNode, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpNodeDAO.update", e);
			throw e;
		}
	}

	public List<GpNode> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return gpNodeHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpNodeDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<GpNode> list = gpNodeHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (GpNode)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpNodeDAO.load", e);
			throw e;
		}
	}

	public Object load(Object gpNode) throws java.sql.SQLException {
		GpNode _gpNode = (GpNode)gpNode;
		String sql = "select "+GpNode.ALL_FIELDS+gpNodeHelper.getSqlString()+" and GpNode.GpId = '"+_gpNode.getGpId()+"'";
		try {
			List<GpNode> list = gpNodeHelper.getQueryList(query(sql),GpNode.ALL_FIELDS);
			if(list.size() > 0)
				return (GpNode)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpNodeDAO.load", e);
			throw e;
		}
	}
}