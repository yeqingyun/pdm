package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.GpSyDAO;
import zrsy.dao.helper.GpSyHelper;
import zrsy.vo.GpSy;

public class GpSyFacade {

	public void save(GpSy gpSy) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpSyDAO.class).insert(gpSy);

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

	public void update(GpSy gpSy) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpSyDAO.class).update(gpSy);

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
			DAOFactory.getDAO(GpSyDAO.class).update(sql);
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

	public void submit(GpSy gpSy) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpSyDAO.class).update(gpSy);
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

	public void review(GpSy gpSy) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpSyDAO.class).update(gpSy);
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

	public void confirm(GpSy gpSy) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpSyDAO.class).update(gpSy);
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
			DAOFactory.getDAO(GpSyDAO.class).delete(sql);
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

	public void remove(GpSy gpSy) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpSyDAO.class).delete(new GpSyHelper().getSql4Delete(gpSy));
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

	public GpSy findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (GpSy)DAOFactory.getDAO(GpSyDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public GpSy findById(GpSy gpSy) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (GpSy)DAOFactory.getDAO(GpSyDAO.class).load(gpSy);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public GpSy findBy(GpSy gpSy) throws Exception {
		String sql = SqlUtil.getSql4All(GpSyHelper.class,gpSy,GpSy.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (GpSy)DAOFactory.getDAO(GpSyDAO.class).load(sql,GpSy.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpSy> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (List<GpSy>)DAOFactory.getDAO(GpSyDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpSy> findAll(GpSy gpSy) throws Exception {
		String sql = SqlUtil.getSql4All(GpSyHelper.class,gpSy,GpSy.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<GpSy>)DAOFactory.getDAO(GpSyDAO.class).query(sql, GpSy.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpSy> find(GpSy gpSy,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(GpSyHelper.class,gpSy,pageVO,GpSy.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<GpSy>)DAOFactory.getDAO(GpSyDAO.class).query(sql, GpSy.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpSy> find(GpSy gpSy) throws Exception {
		String sql = SqlUtil.getSql4All(GpSyHelper.class,gpSy,GpSy.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<GpSy>)DAOFactory.getDAO(GpSyDAO.class).query(sql, GpSy.LIST_FIELDS);
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
			return DAOFactory.getDAO(GpSyDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(GpSy gpSy) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return DAOFactory.getDAO(GpSyDAO.class).amount(new GpSyHelper().getSql4Amount(gpSy));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}