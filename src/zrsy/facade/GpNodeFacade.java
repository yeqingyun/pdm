package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.GpNodeDAO;
import zrsy.dao.helper.GpNodeHelper;
import zrsy.vo.GpNode;

public class GpNodeFacade {

	public void save(GpNode gpNode) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpNodeDAO.class).insert(gpNode);

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

	public void update(GpNode gpNode) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpNodeDAO.class).update(gpNode);

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
			DAOFactory.getDAO(GpNodeDAO.class).update(sql);
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

	public void submit(GpNode gpNode) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpNodeDAO.class).update(gpNode);
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

	public void review(GpNode gpNode) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpNodeDAO.class).update(gpNode);
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

	public void confirm(GpNode gpNode) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpNodeDAO.class).update(gpNode);
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
			DAOFactory.getDAO(GpNodeDAO.class).delete(sql);
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

	public void remove(GpNode gpNode) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpNodeDAO.class).delete(new GpNodeHelper().getSql4Delete(gpNode));
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

	public GpNode findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (GpNode)DAOFactory.getDAO(GpNodeDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public GpNode findById(GpNode gpNode) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (GpNode)DAOFactory.getDAO(GpNodeDAO.class).load(gpNode);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public GpNode findBy(GpNode gpNode) throws Exception {
		String sql = SqlUtil.getSql4All(GpNodeHelper.class,gpNode,GpNode.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (GpNode)DAOFactory.getDAO(GpNodeDAO.class).load(sql,GpNode.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpNode> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (List<GpNode>)DAOFactory.getDAO(GpNodeDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpNode> findAll(GpNode gpNode) throws Exception {
		String sql = SqlUtil.getSql4All(GpNodeHelper.class,gpNode,GpNode.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<GpNode>)DAOFactory.getDAO(GpNodeDAO.class).query(sql, GpNode.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpNode> find(GpNode gpNode,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(GpNodeHelper.class,gpNode,pageVO,GpNode.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<GpNode>)DAOFactory.getDAO(GpNodeDAO.class).query(sql, GpNode.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpNode> find(GpNode gpNode) throws Exception {
		String sql = SqlUtil.getSql4All(GpNodeHelper.class,gpNode,GpNode.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<GpNode>)DAOFactory.getDAO(GpNodeDAO.class).query(sql, GpNode.LIST_FIELDS);
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
			return DAOFactory.getDAO(GpNodeDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(GpNode gpNode) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return DAOFactory.getDAO(GpNodeDAO.class).amount(new GpNodeHelper().getSql4Amount(gpNode));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}