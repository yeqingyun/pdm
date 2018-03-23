package zrprjt.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrprjt.dao.PrjtRoleDAO;
import zrprjt.dao.helper.PrjtRoleHelper;
import zrprjt.vo.PrjtRole;

public class PrjtRoleFacade {

	public void save(PrjtRole prjtRole) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtRoleDAO.class).insert(prjtRole);

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

	public void update(PrjtRole prjtRole) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtRoleDAO.class).update(prjtRole);

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
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtRoleDAO.class).update(sql);
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

	public void submit(PrjtRole prjtRole) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtRoleDAO.class).update(prjtRole);
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

	public void review(PrjtRole prjtRole) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtRoleDAO.class).update(prjtRole);
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

	public void confirm(PrjtRole prjtRole) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtRoleDAO.class).update(prjtRole);
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
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtRoleDAO.class).delete(sql);
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

	public void remove(PrjtRole prjtRole) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtRoleDAO.class).delete(new PrjtRoleHelper().getSql4Delete(prjtRole));
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

	public PrjtRole findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (PrjtRole)DAOFactory.getDAO(PrjtRoleDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public PrjtRole findById(PrjtRole prjtRole) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (PrjtRole)DAOFactory.getDAO(PrjtRoleDAO.class).load(prjtRole);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public PrjtRole findBy(PrjtRole prjtRole) throws Exception {
		String sql = SqlUtil.getSql4All(PrjtRoleHelper.class,prjtRole,PrjtRole.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (PrjtRole)DAOFactory.getDAO(PrjtRoleDAO.class).load(sql,PrjtRole.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtRole> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtRole>)DAOFactory.getDAO(PrjtRoleDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtRole> findAll(PrjtRole prjtRole) throws Exception {
		String sql = SqlUtil.getSql4All(PrjtRoleHelper.class,prjtRole,PrjtRole.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtRole>)DAOFactory.getDAO(PrjtRoleDAO.class).query(sql, PrjtRole.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtRole> find(PrjtRole prjtRole,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(PrjtRoleHelper.class,prjtRole,pageVO,PrjtRole.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtRole>)DAOFactory.getDAO(PrjtRoleDAO.class).query(sql, PrjtRole.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtRole> find(PrjtRole prjtRole) throws Exception {
		String sql = SqlUtil.getSql4All(PrjtRoleHelper.class,prjtRole,PrjtRole.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtRole>)DAOFactory.getDAO(PrjtRoleDAO.class).query(sql, PrjtRole.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(PrjtRoleDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(PrjtRole prjtRole) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(PrjtRoleDAO.class).amount(new PrjtRoleHelper().getSql4Amount(prjtRole));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}