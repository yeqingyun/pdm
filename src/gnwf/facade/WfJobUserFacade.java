package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfJobUserDAO;
import gnwf.dao.helper.WfJobUserHelper;
import gnwf.vo.WfJobUser;

public class WfJobUserFacade {

	public void save(WfJobUser wfJobUser) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfJobUserDAO.class).insert(wfJobUser);

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

	public void update(WfJobUser wfJobUser) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfJobUserDAO.class).update(wfJobUser);

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
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfJobUserDAO.class).update(sql);
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

	public void submit(WfJobUser wfJobUser) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfJobUserDAO.class).update(wfJobUser);
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

	public void review(WfJobUser wfJobUser) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfJobUserDAO.class).update(wfJobUser);
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

	public void confirm(WfJobUser wfJobUser) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfJobUserDAO.class).update(wfJobUser);
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
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfJobUserDAO.class).delete(sql);
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

	public void remove(WfJobUser wfJobUser) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfJobUserDAO.class).delete(new WfJobUserHelper().getSql4Delete(wfJobUser));
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

	public WfJobUser findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfJobUser)DAOFactory.getDAO(WfJobUserDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfJobUser findById(WfJobUser wfJobUser) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfJobUser)DAOFactory.getDAO(WfJobUserDAO.class).load(wfJobUser);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfJobUser findBy(WfJobUser wfJobUser) throws Exception {
		String sql = SqlUtil.getSql4All(WfJobUserHelper.class,wfJobUser,WfJobUser.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfJobUser)DAOFactory.getDAO(WfJobUserDAO.class).load(sql,WfJobUser.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfJobUser> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfJobUser>)DAOFactory.getDAO(WfJobUserDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfJobUser> findAll(WfJobUser wfJobUser) throws Exception {
		String sql = SqlUtil.getSql4All(WfJobUserHelper.class,wfJobUser,WfJobUser.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfJobUser>)DAOFactory.getDAO(WfJobUserDAO.class).query(sql, WfJobUser.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfJobUser> find(WfJobUser wfJobUser,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfJobUserHelper.class,wfJobUser,pageVO,WfJobUser.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfJobUser>)DAOFactory.getDAO(WfJobUserDAO.class).query(sql, WfJobUser.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfJobUser> find(WfJobUser wfJobUser) throws Exception {
		String sql = SqlUtil.getSql4All(WfJobUserHelper.class,wfJobUser,WfJobUser.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfJobUser>)DAOFactory.getDAO(WfJobUserDAO.class).query(sql, WfJobUser.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfJobUserDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfJobUser wfJobUser) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfJobUserDAO.class).amount(new WfJobUserHelper().getSql4Amount(wfJobUser));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}