package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.GpUsrDAO;
import zrsy.dao.helper.GpUsrHelper;
import zrsy.vo.GpUsr;

public class GpUsrFacade {

	public void save(GpUsr gpUsr) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpUsrDAO.class).insert(gpUsr);

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

	public void update(GpUsr gpUsr) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpUsrDAO.class).update(gpUsr);

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
			DAOFactory.getDAO(GpUsrDAO.class).update(sql);
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

	public void submit(GpUsr gpUsr) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpUsrDAO.class).update(gpUsr);
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

	public void review(GpUsr gpUsr) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpUsrDAO.class).update(gpUsr);
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

	public void confirm(GpUsr gpUsr) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpUsrDAO.class).update(gpUsr);
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
			DAOFactory.getDAO(GpUsrDAO.class).delete(sql);
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

	public void remove(GpUsr gpUsr) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpUsrDAO.class).delete(new GpUsrHelper().getSql4Delete(gpUsr));
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

	public GpUsr findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (GpUsr)DAOFactory.getDAO(GpUsrDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public GpUsr findById(GpUsr gpUsr) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (GpUsr)DAOFactory.getDAO(GpUsrDAO.class).load(gpUsr);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public GpUsr findBy(GpUsr gpUsr) throws Exception {
		String sql = SqlUtil.getSql4All(GpUsrHelper.class,gpUsr,GpUsr.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (GpUsr)DAOFactory.getDAO(GpUsrDAO.class).load(sql,GpUsr.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpUsr> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (List<GpUsr>)DAOFactory.getDAO(GpUsrDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpUsr> findAll(GpUsr gpUsr) throws Exception {
		String sql = SqlUtil.getSql4All(GpUsrHelper.class,gpUsr,GpUsr.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<GpUsr>)DAOFactory.getDAO(GpUsrDAO.class).query(sql, GpUsr.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpUsr> find(GpUsr gpUsr,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(GpUsrHelper.class,gpUsr,pageVO,GpUsr.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<GpUsr>)DAOFactory.getDAO(GpUsrDAO.class).query(sql, GpUsr.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpUsr> find(GpUsr gpUsr) throws Exception {
		String sql = SqlUtil.getSql4All(GpUsrHelper.class,gpUsr,GpUsr.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<GpUsr>)DAOFactory.getDAO(GpUsrDAO.class).query(sql, GpUsr.LIST_FIELDS);
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
			return DAOFactory.getDAO(GpUsrDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(GpUsr gpUsr) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return DAOFactory.getDAO(GpUsrDAO.class).amount(new GpUsrHelper().getSql4Amount(gpUsr));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}