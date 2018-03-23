package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.ChlnDAO;
import zrsy.dao.helper.ChlnHelper;
import zrsy.vo.Chln;

public class ChlnFacade {

	public void save(Chln chln) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ChlnDAO.class).insert(chln);

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

	public void update(Chln chln) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ChlnDAO.class).update(chln);

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
			DAOFactory.getDAO(ChlnDAO.class).update(sql);
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

	public void submit(Chln chln) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ChlnDAO.class).update(chln);
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

	public void review(Chln chln) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ChlnDAO.class).update(chln);
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

	public void confirm(Chln chln) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ChlnDAO.class).update(chln);
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
			DAOFactory.getDAO(ChlnDAO.class).delete(sql);
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

	public void remove(Chln chln) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ChlnDAO.class).delete(new ChlnHelper().getSql4Delete(chln));
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

	public Chln findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (Chln)DAOFactory.getDAO(ChlnDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Chln findById(Chln chln) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (Chln)DAOFactory.getDAO(ChlnDAO.class).load(chln);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Chln findBy(Chln chln) throws Exception {
		String sql = SqlUtil.getSql4All(ChlnHelper.class,chln,Chln.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (Chln)DAOFactory.getDAO(ChlnDAO.class).load(sql,Chln.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Chln> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (List<Chln>)DAOFactory.getDAO(ChlnDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Chln> findAll(Chln chln) throws Exception {
		String sql = SqlUtil.getSql4All(ChlnHelper.class,chln,Chln.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<Chln>)DAOFactory.getDAO(ChlnDAO.class).query(sql, Chln.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Chln> find(Chln chln,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(ChlnHelper.class,chln,pageVO,Chln.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<Chln>)DAOFactory.getDAO(ChlnDAO.class).query(sql, Chln.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Chln> find(Chln chln) throws Exception {
		String sql = SqlUtil.getSql4All(ChlnHelper.class,chln,Chln.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<Chln>)DAOFactory.getDAO(ChlnDAO.class).query(sql, Chln.LIST_FIELDS);
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
			return DAOFactory.getDAO(ChlnDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(Chln chln) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(ChlnDAO.class).amount(new ChlnHelper().getSql4Amount(chln));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}