package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.PgBtnDAO;
import zrsy.dao.helper.PgBtnHelper;
import zrsy.vo.PgBtn;

public class PgBtnFacade {

	public void save(PgBtn pgBtn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PgBtnDAO.class).insert(pgBtn);

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

	public void update(PgBtn pgBtn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PgBtnDAO.class).update(pgBtn);

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
			DAOFactory.getDAO(PgBtnDAO.class).update(sql);
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

	public void submit(PgBtn pgBtn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PgBtnDAO.class).update(pgBtn);
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

	public void review(PgBtn pgBtn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PgBtnDAO.class).update(pgBtn);
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

	public void confirm(PgBtn pgBtn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PgBtnDAO.class).update(pgBtn);
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
			DAOFactory.getDAO(PgBtnDAO.class).delete(sql);
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

	public void remove(PgBtn pgBtn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PgBtnDAO.class).delete(new PgBtnHelper().getSql4Delete(pgBtn));
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

	public PgBtn findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (PgBtn)DAOFactory.getDAO(PgBtnDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public PgBtn findById(PgBtn pgBtn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (PgBtn)DAOFactory.getDAO(PgBtnDAO.class).load(pgBtn);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public PgBtn findBy(PgBtn pgBtn) throws Exception {
		String sql = SqlUtil.getSql4All(PgBtnHelper.class,pgBtn,PgBtn.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (PgBtn)DAOFactory.getDAO(PgBtnDAO.class).load(sql,PgBtn.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PgBtn> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (List<PgBtn>)DAOFactory.getDAO(PgBtnDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PgBtn> findAll(PgBtn pgBtn) throws Exception {
		String sql = SqlUtil.getSql4All(PgBtnHelper.class,pgBtn,PgBtn.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<PgBtn>)DAOFactory.getDAO(PgBtnDAO.class).query(sql, PgBtn.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PgBtn> find(PgBtn pgBtn,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(PgBtnHelper.class,pgBtn,pageVO,PgBtn.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<PgBtn>)DAOFactory.getDAO(PgBtnDAO.class).query(sql, PgBtn.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PgBtn> find(PgBtn pgBtn) throws Exception {
		String sql = SqlUtil.getSql4All(PgBtnHelper.class,pgBtn,PgBtn.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<PgBtn>)DAOFactory.getDAO(PgBtnDAO.class).query(sql, PgBtn.LIST_FIELDS);
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
			return DAOFactory.getDAO(PgBtnDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(PgBtn pgBtn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return DAOFactory.getDAO(PgBtnDAO.class).amount(new PgBtnHelper().getSql4Amount(pgBtn));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}