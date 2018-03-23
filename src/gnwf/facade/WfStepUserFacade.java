package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfStepUserDAO;
import gnwf.dao.helper.WfStepUserHelper;
import gnwf.vo.WfStepUser;

public class WfStepUserFacade {

	public void save(WfStepUser wfStepUser) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepUserDAO.class).insert(wfStepUser);

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

	public void update(WfStepUser wfStepUser) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepUserDAO.class).update(wfStepUser);

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
			DAOFactory.getDAO(WfStepUserDAO.class).update(sql);
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

	public void submit(WfStepUser wfStepUser) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepUserDAO.class).update(wfStepUser);
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

	public void review(WfStepUser wfStepUser) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepUserDAO.class).update(wfStepUser);
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

	public void confirm(WfStepUser wfStepUser) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepUserDAO.class).update(wfStepUser);
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
			DAOFactory.getDAO(WfStepUserDAO.class).delete(sql);
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

	public void remove(WfStepUser wfStepUser) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepUserDAO.class).delete(new WfStepUserHelper().getSql4Delete(wfStepUser));
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

	public WfStepUser findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfStepUser)DAOFactory.getDAO(WfStepUserDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfStepUser findById(WfStepUser wfStepUser) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfStepUser)DAOFactory.getDAO(WfStepUserDAO.class).load(wfStepUser);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfStepUser findBy(WfStepUser wfStepUser) throws Exception {
		String sql = SqlUtil.getSql4All(WfStepUserHelper.class,wfStepUser,WfStepUser.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfStepUser)DAOFactory.getDAO(WfStepUserDAO.class).load(sql,WfStepUser.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfStepUser> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfStepUser>)DAOFactory.getDAO(WfStepUserDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfStepUser> findAll(WfStepUser wfStepUser) throws Exception {
		String sql = SqlUtil.getSql4All(WfStepUserHelper.class,wfStepUser,WfStepUser.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfStepUser>)DAOFactory.getDAO(WfStepUserDAO.class).query(sql, WfStepUser.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfStepUser> find(WfStepUser wfStepUser,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfStepUserHelper.class,wfStepUser,pageVO,WfStepUser.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfStepUser>)DAOFactory.getDAO(WfStepUserDAO.class).query(sql, WfStepUser.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfStepUser> find(WfStepUser wfStepUser) throws Exception {
		String sql = SqlUtil.getSql4All(WfStepUserHelper.class,wfStepUser,WfStepUser.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfStepUser>)DAOFactory.getDAO(WfStepUserDAO.class).query(sql, WfStepUser.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfStepUserDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfStepUser wfStepUser) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfStepUserDAO.class).amount(new WfStepUserHelper().getSql4Amount(wfStepUser));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}