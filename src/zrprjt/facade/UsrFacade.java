package zrprjt.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrprjt.dao.UsrDAO;
import zrprjt.dao.helper.UsrHelper;
import zrprjt.vo.Usr;

public class UsrFacade {

	public void save(Usr usr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(UsrDAO.class).insert(usr);

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

	public void update(Usr usr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(UsrDAO.class).update(usr);

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
			DAOFactory.getDAO(UsrDAO.class).update(sql);
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

	public void submit(Usr usr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(UsrDAO.class).update(usr);
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

	public void review(Usr usr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(UsrDAO.class).update(usr);
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

	public void confirm(Usr usr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(UsrDAO.class).update(usr);
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
			DAOFactory.getDAO(UsrDAO.class).delete(sql);
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

	public void remove(Usr usr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(UsrDAO.class).delete(new UsrHelper().getSql4Delete(usr));
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

	public Usr findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (Usr)DAOFactory.getDAO(UsrDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Usr findById(Usr usr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (Usr)DAOFactory.getDAO(UsrDAO.class).load(usr);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Usr findBy(Usr usr) throws Exception {
		String sql = SqlUtil.getSql4All(UsrHelper.class,usr,Usr.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (Usr)DAOFactory.getDAO(UsrDAO.class).load(sql,Usr.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usr> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (List<Usr>)DAOFactory.getDAO(UsrDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usr> findAll(Usr usr) throws Exception {
		String sql = SqlUtil.getSql4All(UsrHelper.class,usr,Usr.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<Usr>)DAOFactory.getDAO(UsrDAO.class).query(sql, Usr.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usr> find(Usr usr,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(UsrHelper.class,usr,pageVO,Usr.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<Usr>)DAOFactory.getDAO(UsrDAO.class).query(sql, Usr.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usr> find(Usr usr) throws Exception {
		String sql = SqlUtil.getSql4All(UsrHelper.class,usr,Usr.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<Usr>)DAOFactory.getDAO(UsrDAO.class).query(sql, Usr.LIST_FIELDS);
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
			return DAOFactory.getDAO(UsrDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(Usr usr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(UsrDAO.class).amount(new UsrHelper().getSql4Amount(usr));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}