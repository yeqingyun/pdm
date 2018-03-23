package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.ChlnVDAO;
import zrsy.dao.helper.ChlnVHelper;
import zrsy.vo.ChlnV;

public class ChlnVFacade {

	public void save(ChlnV chlnV) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ChlnVDAO.class).insert(chlnV);

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

	public void update(ChlnV chlnV) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ChlnVDAO.class).update(chlnV);

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
			DAOFactory.getDAO(ChlnVDAO.class).update(sql);
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

	public void submit(ChlnV chlnV) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ChlnVDAO.class).update(chlnV);
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

	public void review(ChlnV chlnV) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ChlnVDAO.class).update(chlnV);
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

	public void confirm(ChlnV chlnV) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ChlnVDAO.class).update(chlnV);
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
			DAOFactory.getDAO(ChlnVDAO.class).delete(sql);
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

	public void remove(ChlnV chlnV) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ChlnVDAO.class).delete(new ChlnVHelper().getSql4Delete(chlnV));
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

	public ChlnV findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (ChlnV)DAOFactory.getDAO(ChlnVDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public ChlnV findById(ChlnV chlnV) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (ChlnV)DAOFactory.getDAO(ChlnVDAO.class).load(chlnV);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public ChlnV findBy(ChlnV chlnV) throws Exception {
		String sql = SqlUtil.getSql4All(ChlnVHelper.class,chlnV,ChlnV.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (ChlnV)DAOFactory.getDAO(ChlnVDAO.class).load(sql,ChlnV.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ChlnV> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (List<ChlnV>)DAOFactory.getDAO(ChlnVDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ChlnV> findAll(ChlnV chlnV) throws Exception {
		String sql = SqlUtil.getSql4All(ChlnVHelper.class,chlnV,ChlnV.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<ChlnV>)DAOFactory.getDAO(ChlnVDAO.class).query(sql, ChlnV.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ChlnV> find(ChlnV chlnV,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(ChlnVHelper.class,chlnV,pageVO,ChlnV.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<ChlnV>)DAOFactory.getDAO(ChlnVDAO.class).query(sql, ChlnV.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ChlnV> find(ChlnV chlnV) throws Exception {
		String sql = SqlUtil.getSql4All(ChlnVHelper.class,chlnV,ChlnV.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<ChlnV>)DAOFactory.getDAO(ChlnVDAO.class).query(sql, ChlnV.LIST_FIELDS);
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
			return DAOFactory.getDAO(ChlnVDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(ChlnV chlnV) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return DAOFactory.getDAO(ChlnVDAO.class).amount(new ChlnVHelper().getSql4Amount(chlnV));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}