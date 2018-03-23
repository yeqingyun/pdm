package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.NodeDAO;
import zrsy.dao.helper.NodeHelper;
import zrsy.vo.Node;

public class NodeFacade {

	public void save(Node node) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(NodeDAO.class).insert(node);
			if(node.getGpNodes() != null && node.getGpNodes().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.GpNodeDAO.class).delete("delete from GpNode where GpNode.NodeId = " +node.getId());
				for(int i=0; i<node.getGpNodes().size(); i++) {
					if(node.getGpNodes().get(i) != null && node.getGpNodes().get(i).getNodeId() != null) {
						node.getGpNodes().get(i).setNodeId(node.getId());
						DAOFactory.getDAO(zrsy.dao.GpNodeDAO.class).insert(node.getGpNodes().get(i));
					}
				}
			}

			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void update(Node node) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(NodeDAO.class).update(node);
			if(node.getGpNodes() != null && node.getGpNodes().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.GpNodeDAO.class).delete("delete from GpNode where GpNode.NodeId = " +node.getId());
				for(int i=0; i<node.getGpNodes().size(); i++) {
					if(node.getGpNodes().get(i) != null && node.getGpNodes().get(i).getNodeId() != null) {
						node.getGpNodes().get(i).setNodeId(node.getId());
						DAOFactory.getDAO(zrsy.dao.GpNodeDAO.class).insert(node.getGpNodes().get(i));
					}
				}
			}
			if(node.getPgBtns() != null && node.getPgBtns().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.PgBtnDAO.class).delete("delete from PgBtn where PgBtn.NodeId = " +node.getId());
				for(int i=0; i<node.getPgBtns().size(); i++) {
					if(node.getPgBtns().get(i) != null&&node.getPgBtns().get(i).getBtnId()!=null) {
						node.getPgBtns().get(i).setNodeId(node.getId());
						DAOFactory.getDAO(zrsy.dao.PgBtnDAO.class).insert(node.getPgBtns().get(i));
					}
				}
			}
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void update(String sql) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(NodeDAO.class).update(sql);
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void submit(Node node) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(NodeDAO.class).update(node);
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void review(Node node) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(NodeDAO.class).update(node);
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void confirm(Node node) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(NodeDAO.class).update(node);
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void remove(String sql) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(NodeDAO.class).delete(sql);
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void remove(Node node) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(NodeDAO.class).delete(new NodeHelper().getSql4Delete(node));
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Node findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (Node)DAOFactory.getDAO(NodeDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Node findById(Node node) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (Node)DAOFactory.getDAO(NodeDAO.class).load(node);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Node findBy(Node node) throws Exception {
		String sql = SqlUtil.getSql4All(NodeHelper.class,node,Node.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (Node)DAOFactory.getDAO(NodeDAO.class).load(sql,Node.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Node> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Node>)DAOFactory.getDAO(NodeDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Node> findAll(Node node) throws Exception {
		String sql = SqlUtil.getSql4All(NodeHelper.class,node,Node.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Node>)DAOFactory.getDAO(NodeDAO.class).query(sql, Node.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Node> find(Node node,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(NodeHelper.class,node,pageVO,Node.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Node>)DAOFactory.getDAO(NodeDAO.class).query(sql, Node.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Node> find(Node node) throws Exception {
		String sql = SqlUtil.getSql4All(NodeHelper.class,node,Node.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Node>)DAOFactory.getDAO(NodeDAO.class).query(sql, Node.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return DAOFactory.getDAO(NodeDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(Node node) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return DAOFactory.getDAO(NodeDAO.class).amount(new NodeHelper().getSql4Amount(node));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}