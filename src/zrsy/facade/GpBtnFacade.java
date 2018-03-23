package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.GpBtnDAO;
import zrsy.dao.helper.GpBtnHelper;
import zrsy.vo.GpBtn;

public class GpBtnFacade {

	public void save(GpBtn gpBtn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpBtnDAO.class).insert(gpBtn);

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

	public void update(GpBtn gpBtn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpBtnDAO.class).update(gpBtn);

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
			DAOFactory.getDAO(GpBtnDAO.class).update(sql);
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

	public void submit(GpBtn gpBtn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpBtnDAO.class).update(gpBtn);
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

	public void review(GpBtn gpBtn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpBtnDAO.class).update(gpBtn);
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

	public void confirm(GpBtn gpBtn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpBtnDAO.class).update(gpBtn);
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
			DAOFactory.getDAO(GpBtnDAO.class).delete(sql);
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

	public void remove(GpBtn gpBtn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpBtnDAO.class).delete(new GpBtnHelper().getSql4Delete(gpBtn));
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

	public GpBtn findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (GpBtn)DAOFactory.getDAO(GpBtnDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public GpBtn findById(GpBtn gpBtn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (GpBtn)DAOFactory.getDAO(GpBtnDAO.class).load(gpBtn);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public GpBtn findBy(GpBtn gpBtn) throws Exception {
		String sql = SqlUtil.getSql4All(GpBtnHelper.class,gpBtn,GpBtn.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (GpBtn)DAOFactory.getDAO(GpBtnDAO.class).load(sql,GpBtn.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpBtn> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (List<GpBtn>)DAOFactory.getDAO(GpBtnDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpBtn> findAll(GpBtn gpBtn) throws Exception {
		String sql = SqlUtil.getSql4All(GpBtnHelper.class,gpBtn,GpBtn.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<GpBtn>)DAOFactory.getDAO(GpBtnDAO.class).query(sql, GpBtn.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpBtn> find(GpBtn gpBtn,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(GpBtnHelper.class,gpBtn,pageVO,GpBtn.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<GpBtn>)DAOFactory.getDAO(GpBtnDAO.class).query(sql, GpBtn.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpBtn> find(GpBtn gpBtn) throws Exception {
		String sql = SqlUtil.getSql4All(GpBtnHelper.class,gpBtn,GpBtn.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<GpBtn>)DAOFactory.getDAO(GpBtnDAO.class).query(sql, GpBtn.LIST_FIELDS);
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
			return DAOFactory.getDAO(GpBtnDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(GpBtn gpBtn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return DAOFactory.getDAO(GpBtnDAO.class).amount(new GpBtnHelper().getSql4Amount(gpBtn));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}