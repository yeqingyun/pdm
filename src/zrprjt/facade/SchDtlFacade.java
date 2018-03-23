package zrprjt.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrprjt.dao.SchDtlDAO;
import zrprjt.dao.helper.SchDtlHelper;
import zrprjt.vo.SchDtl;

public class SchDtlFacade {

	public void save(SchDtl schDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SchDtlDAO.class).insert(schDtl);

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

	public void update(SchDtl schDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SchDtlDAO.class).update(schDtl);

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
			DAOFactory.getDAO(SchDtlDAO.class).update(sql);
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

	public void submit(SchDtl schDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SchDtlDAO.class).update(schDtl);
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

	public void review(SchDtl schDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SchDtlDAO.class).update(schDtl);
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

	public void confirm(SchDtl schDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SchDtlDAO.class).update(schDtl);
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
			DAOFactory.getDAO(SchDtlDAO.class).delete(sql);
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

	public void remove(SchDtl schDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SchDtlDAO.class).delete(new SchDtlHelper().getSql4Delete(schDtl));
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

	public SchDtl findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (SchDtl)DAOFactory.getDAO(SchDtlDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public SchDtl findById(SchDtl schDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (SchDtl)DAOFactory.getDAO(SchDtlDAO.class).load(schDtl);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public SchDtl findBy(SchDtl schDtl) throws Exception {
		String sql = SqlUtil.getSql4All(SchDtlHelper.class,schDtl,SchDtl.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (SchDtl)DAOFactory.getDAO(SchDtlDAO.class).load(sql,SchDtl.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SchDtl> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (List<SchDtl>)DAOFactory.getDAO(SchDtlDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SchDtl> findAll(SchDtl schDtl) throws Exception {
		String sql = SqlUtil.getSql4All(SchDtlHelper.class,schDtl,SchDtl.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<SchDtl>)DAOFactory.getDAO(SchDtlDAO.class).query(sql, SchDtl.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SchDtl> find(SchDtl schDtl,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(SchDtlHelper.class,schDtl,pageVO,SchDtl.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<SchDtl>)DAOFactory.getDAO(SchDtlDAO.class).query(sql, SchDtl.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SchDtl> find(SchDtl schDtl) throws Exception {
		String sql = SqlUtil.getSql4All(SchDtlHelper.class,schDtl,SchDtl.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<SchDtl>)DAOFactory.getDAO(SchDtlDAO.class).query(sql, SchDtl.LIST_FIELDS);
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
			return DAOFactory.getDAO(SchDtlDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(SchDtl schDtl) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(SchDtlDAO.class).amount(new SchDtlHelper().getSql4Amount(schDtl));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}