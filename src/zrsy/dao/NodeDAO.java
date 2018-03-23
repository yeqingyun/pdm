package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.NodeHelper;
import zrsy.vo.Node;

public class NodeDAO extends BasicDAO implements GenericDAO {

	private NodeHelper nodeHelper = new NodeHelper();

	public void insert(Object node) throws java.sql.SQLException {
		Node _node = (Node)node;
		String fields = nodeHelper.getFields(_node);
		String sql = nodeHelper.getInsertSql(fields);
		try {
			nodeHelper.pstmtInsert(_node, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("NodeDAO.insert", e);
			throw e;
		}
	}

	public void update(Object node) throws java.sql.SQLException {
		Node _node = (Node)node;
		String fields = nodeHelper.getFields(_node);
		String sql = nodeHelper.getUpdateSql(fields, "Node.Id" ,_node.getId().toString());
		try {
			nodeHelper.pstmtUpdate(_node, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("NodeDAO.update", e);
			throw e;
		}
	}

	public List<Node> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return nodeHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("NodeDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<Node> list = nodeHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (Node)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("NodeDAO.load", e);
			throw e;
		}
	}

	public Object load(Object node) throws java.sql.SQLException {
		Node _node = (Node)node;
		String sql = "select "+Node.ALL_FIELDS+nodeHelper.getSqlString()+" and Node.Id = '"+_node.getId()+"'";
		try {
			List<Node> list = nodeHelper.getQueryList(query(sql),Node.ALL_FIELDS);
			if(list.size() > 0)
				return (Node)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("NodeDAO.load", e);
			throw e;
		}
	}
}