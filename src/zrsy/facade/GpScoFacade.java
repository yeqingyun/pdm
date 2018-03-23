package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.GpScoDAO;
import zrsy.dao.helper.GpScoHelper;
import zrsy.vo.GpSco;

public class GpScoFacade {

	public void save(GpSco gpSco) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpScoDAO.class).insert(gpSco);

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

	public void update(GpSco gpSco) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpScoDAO.class).update(gpSco);

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
			DAOFactory.getDAO(GpScoDAO.class).update(sql);
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

	public void submit(GpSco gpSco) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpScoDAO.class).update(gpSco);
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

	public void review(GpSco gpSco) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpScoDAO.class).update(gpSco);
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

	public void confirm(GpSco gpSco) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpScoDAO.class).update(gpSco);
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
			DAOFactory.getDAO(GpScoDAO.class).delete(sql);
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

	public void remove(GpSco gpSco) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpScoDAO.class).delete(new GpScoHelper().getSql4Delete(gpSco));
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

	public GpSco findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (GpSco)DAOFactory.getDAO(GpScoDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public GpSco findById(GpSco gpSco) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (GpSco)DAOFactory.getDAO(GpScoDAO.class).load(gpSco);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public GpSco findBy(GpSco gpSco) throws Exception {
		String sql = SqlUtil.getSql4All(GpScoHelper.class,gpSco,GpSco.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (GpSco)DAOFactory.getDAO(GpScoDAO.class).load(sql,GpSco.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpSco> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (List<GpSco>)DAOFactory.getDAO(GpScoDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpSco> findAll(GpSco gpSco) throws Exception {
		String sql = SqlUtil.getSql4All(GpScoHelper.class,gpSco,GpSco.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<GpSco>)DAOFactory.getDAO(GpScoDAO.class).query(sql, GpSco.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpSco> find(GpSco gpSco,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(GpScoHelper.class,gpSco,pageVO,GpSco.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<GpSco>)DAOFactory.getDAO(GpScoDAO.class).query(sql, GpSco.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpSco> find(GpSco gpSco) throws Exception {
		String sql = SqlUtil.getSql4All(GpScoHelper.class,gpSco,GpSco.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<GpSco>)DAOFactory.getDAO(GpScoDAO.class).query(sql, GpSco.LIST_FIELDS);
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
			return DAOFactory.getDAO(GpScoDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(GpSco gpSco) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(GpScoDAO.class).amount(new GpScoHelper().getSql4Amount(gpSco));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}