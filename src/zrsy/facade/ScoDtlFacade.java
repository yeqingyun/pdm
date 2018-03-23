package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.ScoDtlDAO;
import zrsy.dao.helper.ScoDtlHelper;
import zrsy.vo.ScoDtl;

public class ScoDtlFacade {

	public void save(ScoDtl scoDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ScoDtlDAO.class).insert(scoDtl);

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

	public void update(ScoDtl scoDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ScoDtlDAO.class).update(scoDtl);

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
			DAOFactory.getDAO(ScoDtlDAO.class).update(sql);
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

	public void submit(ScoDtl scoDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ScoDtlDAO.class).update(scoDtl);
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

	public void review(ScoDtl scoDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ScoDtlDAO.class).update(scoDtl);
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

	public void confirm(ScoDtl scoDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ScoDtlDAO.class).update(scoDtl);
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
			DAOFactory.getDAO(ScoDtlDAO.class).delete(sql);
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

	public void remove(ScoDtl scoDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ScoDtlDAO.class).delete(new ScoDtlHelper().getSql4Delete(scoDtl));
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

	public ScoDtl findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (ScoDtl)DAOFactory.getDAO(ScoDtlDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public ScoDtl findById(ScoDtl scoDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (ScoDtl)DAOFactory.getDAO(ScoDtlDAO.class).load(scoDtl);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public ScoDtl findBy(ScoDtl scoDtl) throws Exception {
		String sql = SqlUtil.getSql4All(ScoDtlHelper.class,scoDtl,ScoDtl.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (ScoDtl)DAOFactory.getDAO(ScoDtlDAO.class).load(sql,ScoDtl.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ScoDtl> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (List<ScoDtl>)DAOFactory.getDAO(ScoDtlDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ScoDtl> findAll(ScoDtl scoDtl) throws Exception {
		String sql = SqlUtil.getSql4All(ScoDtlHelper.class,scoDtl,ScoDtl.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<ScoDtl>)DAOFactory.getDAO(ScoDtlDAO.class).query(sql, ScoDtl.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ScoDtl> find(ScoDtl scoDtl,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(ScoDtlHelper.class,scoDtl,pageVO,ScoDtl.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<ScoDtl>)DAOFactory.getDAO(ScoDtlDAO.class).query(sql, ScoDtl.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ScoDtl> find(ScoDtl scoDtl) throws Exception {
		String sql = SqlUtil.getSql4All(ScoDtlHelper.class,scoDtl,ScoDtl.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<ScoDtl>)DAOFactory.getDAO(ScoDtlDAO.class).query(sql, ScoDtl.LIST_FIELDS);
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
			return DAOFactory.getDAO(ScoDtlDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(ScoDtl scoDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(ScoDtlDAO.class).amount(new ScoDtlHelper().getSql4Amount(scoDtl));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}