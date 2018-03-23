package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.UsrScoDAO;
import zrsy.dao.helper.UsrScoHelper;
import zrsy.vo.UsrSco;

public class UsrScoFacade {

	public void save(UsrSco usrSco) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(UsrScoDAO.class).insert(usrSco);

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

	public void update(UsrSco usrSco) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(UsrScoDAO.class).update(usrSco);

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
			DAOFactory.getDAO(UsrScoDAO.class).update(sql);
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

	public void submit(UsrSco usrSco) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(UsrScoDAO.class).update(usrSco);
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

	public void review(UsrSco usrSco) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(UsrScoDAO.class).update(usrSco);
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

	public void confirm(UsrSco usrSco) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(UsrScoDAO.class).update(usrSco);
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
			DAOFactory.getDAO(UsrScoDAO.class).delete(sql);
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

	public void remove(UsrSco usrSco) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(UsrScoDAO.class).delete(new UsrScoHelper().getSql4Delete(usrSco));
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

	public UsrSco findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (UsrSco)DAOFactory.getDAO(UsrScoDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public UsrSco findById(UsrSco usrSco) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (UsrSco)DAOFactory.getDAO(UsrScoDAO.class).load(usrSco);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public UsrSco findBy(UsrSco usrSco) throws Exception {
		String sql = SqlUtil.getSql4All(UsrScoHelper.class,usrSco,UsrSco.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (UsrSco)DAOFactory.getDAO(UsrScoDAO.class).load(sql,UsrSco.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<UsrSco> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (List<UsrSco>)DAOFactory.getDAO(UsrScoDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<UsrSco> findAll(UsrSco usrSco) throws Exception {
		String sql = SqlUtil.getSql4All(UsrScoHelper.class,usrSco,UsrSco.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<UsrSco>)DAOFactory.getDAO(UsrScoDAO.class).query(sql, UsrSco.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<UsrSco> find(UsrSco usrSco,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(UsrScoHelper.class,usrSco,pageVO,UsrSco.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<UsrSco>)DAOFactory.getDAO(UsrScoDAO.class).query(sql, UsrSco.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<UsrSco> find(UsrSco usrSco) throws Exception {
		String sql = SqlUtil.getSql4All(UsrScoHelper.class,usrSco,UsrSco.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<UsrSco>)DAOFactory.getDAO(UsrScoDAO.class).query(sql, UsrSco.LIST_FIELDS);
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
			return DAOFactory.getDAO(UsrScoDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(UsrSco usrSco) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return DAOFactory.getDAO(UsrScoDAO.class).amount(new UsrScoHelper().getSql4Amount(usrSco));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}