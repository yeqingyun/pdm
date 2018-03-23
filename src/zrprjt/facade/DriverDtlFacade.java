package zrprjt.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrprjt.dao.DriverDtlDAO;
import zrprjt.dao.helper.DriverDtlHelper;
import zrprjt.vo.DriverDtl;

public class DriverDtlFacade {

	public void save(DriverDtl driverDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DriverDtlDAO.class).insert(driverDtl);

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

	public void update(DriverDtl driverDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DriverDtlDAO.class).update(driverDtl);

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
			DAOFactory.getDAO(DriverDtlDAO.class).update(sql);
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

	public void submit(DriverDtl driverDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DriverDtlDAO.class).update(driverDtl);
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

	public void review(DriverDtl driverDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DriverDtlDAO.class).update(driverDtl);
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

	public void confirm(DriverDtl driverDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DriverDtlDAO.class).update(driverDtl);
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
			DAOFactory.getDAO(DriverDtlDAO.class).delete(sql);
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

	public void remove(DriverDtl driverDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DriverDtlDAO.class).delete(new DriverDtlHelper().getSql4Delete(driverDtl));
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

	public DriverDtl findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (DriverDtl)DAOFactory.getDAO(DriverDtlDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public DriverDtl findById(DriverDtl driverDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (DriverDtl)DAOFactory.getDAO(DriverDtlDAO.class).load(driverDtl);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public DriverDtl findBy(DriverDtl driverDtl) throws Exception {
		String sql = SqlUtil.getSql4All(DriverDtlHelper.class,driverDtl,DriverDtl.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (DriverDtl)DAOFactory.getDAO(DriverDtlDAO.class).load(sql,DriverDtl.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<DriverDtl> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (List<DriverDtl>)DAOFactory.getDAO(DriverDtlDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<DriverDtl> findAll(DriverDtl driverDtl) throws Exception {
		String sql = SqlUtil.getSql4All(DriverDtlHelper.class,driverDtl,DriverDtl.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<DriverDtl>)DAOFactory.getDAO(DriverDtlDAO.class).query(sql, DriverDtl.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<DriverDtl> find(DriverDtl driverDtl,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(DriverDtlHelper.class,driverDtl,pageVO,DriverDtl.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<DriverDtl>)DAOFactory.getDAO(DriverDtlDAO.class).query(sql, DriverDtl.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<DriverDtl> find(DriverDtl driverDtl) throws Exception {
		String sql = SqlUtil.getSql4All(DriverDtlHelper.class,driverDtl,DriverDtl.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<DriverDtl>)DAOFactory.getDAO(DriverDtlDAO.class).query(sql, DriverDtl.LIST_FIELDS);
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
			return DAOFactory.getDAO(DriverDtlDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(DriverDtl driverDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(DriverDtlDAO.class).amount(new DriverDtlHelper().getSql4Amount(driverDtl));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}