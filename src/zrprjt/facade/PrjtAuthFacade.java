package zrprjt.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrprjt.dao.PrjtAuthDAO;
import zrprjt.dao.helper.PrjtAuthHelper;
import zrprjt.vo.PrjtAuth;

public class PrjtAuthFacade {

	public void save(PrjtAuth prjtAuth) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtAuthDAO.class).insert(prjtAuth);

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

	public void update(PrjtAuth prjtAuth) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtAuthDAO.class).update(prjtAuth);

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
			DAOFactory.getDAO(PrjtAuthDAO.class).update(sql);
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

	public void submit(PrjtAuth prjtAuth) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtAuthDAO.class).update(prjtAuth);
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

	public void review(PrjtAuth prjtAuth) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtAuthDAO.class).update(prjtAuth);
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

	public void confirm(PrjtAuth prjtAuth) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtAuthDAO.class).update(prjtAuth);
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
			DAOFactory.getDAO(PrjtAuthDAO.class).delete(sql);
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

	public void remove(PrjtAuth prjtAuth) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtAuthDAO.class).delete(new PrjtAuthHelper().getSql4Delete(prjtAuth));
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

	public PrjtAuth findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (PrjtAuth)DAOFactory.getDAO(PrjtAuthDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public PrjtAuth findById(PrjtAuth prjtAuth) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (PrjtAuth)DAOFactory.getDAO(PrjtAuthDAO.class).load(prjtAuth);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public PrjtAuth findBy(PrjtAuth prjtAuth) throws Exception {
		String sql = SqlUtil.getSql4All(PrjtAuthHelper.class,prjtAuth,PrjtAuth.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (PrjtAuth)DAOFactory.getDAO(PrjtAuthDAO.class).load(sql,PrjtAuth.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtAuth> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtAuth>)DAOFactory.getDAO(PrjtAuthDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtAuth> findAll(PrjtAuth prjtAuth) throws Exception {
		String sql = SqlUtil.getSql4All(PrjtAuthHelper.class,prjtAuth,PrjtAuth.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtAuth>)DAOFactory.getDAO(PrjtAuthDAO.class).query(sql, PrjtAuth.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtAuth> find(PrjtAuth prjtAuth,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(PrjtAuthHelper.class,prjtAuth,pageVO,PrjtAuth.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtAuth>)DAOFactory.getDAO(PrjtAuthDAO.class).query(sql, PrjtAuth.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtAuth> find(PrjtAuth prjtAuth) throws Exception {
		String sql = SqlUtil.getSql4All(PrjtAuthHelper.class,prjtAuth,PrjtAuth.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtAuth>)DAOFactory.getDAO(PrjtAuthDAO.class).query(sql, PrjtAuth.LIST_FIELDS);
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
			return DAOFactory.getDAO(PrjtAuthDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(PrjtAuth prjtAuth) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(PrjtAuthDAO.class).amount(new PrjtAuthHelper().getSql4Amount(prjtAuth));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}