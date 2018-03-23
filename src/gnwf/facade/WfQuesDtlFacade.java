package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfQuesDtlDAO;
import gnwf.dao.helper.WfQuesDtlHelper;
import gnwf.vo.WfQuesDtl;

public class WfQuesDtlFacade {

	public void save(WfQuesDtl wfQuesDtl) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfQuesDtlDAO.class).insert(wfQuesDtl);

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

	public void update(WfQuesDtl wfQuesDtl) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfQuesDtlDAO.class).update(wfQuesDtl);

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
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfQuesDtlDAO.class).update(sql);
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

	public void submit(WfQuesDtl wfQuesDtl) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfQuesDtlDAO.class).update(wfQuesDtl);
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

	public void review(WfQuesDtl wfQuesDtl) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfQuesDtlDAO.class).update(wfQuesDtl);
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

	public void confirm(WfQuesDtl wfQuesDtl) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfQuesDtlDAO.class).update(wfQuesDtl);
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
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfQuesDtlDAO.class).delete(sql);
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

	public void remove(WfQuesDtl wfQuesDtl) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfQuesDtlDAO.class).delete(new WfQuesDtlHelper().getSql4Delete(wfQuesDtl));
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

	public WfQuesDtl findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfQuesDtl)DAOFactory.getDAO(WfQuesDtlDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfQuesDtl findById(WfQuesDtl wfQuesDtl) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfQuesDtl)DAOFactory.getDAO(WfQuesDtlDAO.class).load(wfQuesDtl);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfQuesDtl findBy(WfQuesDtl wfQuesDtl) throws Exception {
		String sql = SqlUtil.getSql4All(WfQuesDtlHelper.class,wfQuesDtl,WfQuesDtl.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfQuesDtl)DAOFactory.getDAO(WfQuesDtlDAO.class).load(sql,WfQuesDtl.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfQuesDtl> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfQuesDtl>)DAOFactory.getDAO(WfQuesDtlDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfQuesDtl> findAll(WfQuesDtl wfQuesDtl) throws Exception {
		String sql = SqlUtil.getSql4All(WfQuesDtlHelper.class,wfQuesDtl,WfQuesDtl.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfQuesDtl>)DAOFactory.getDAO(WfQuesDtlDAO.class).query(sql, WfQuesDtl.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfQuesDtl> find(WfQuesDtl wfQuesDtl,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfQuesDtlHelper.class,wfQuesDtl,pageVO,WfQuesDtl.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfQuesDtl>)DAOFactory.getDAO(WfQuesDtlDAO.class).query(sql, WfQuesDtl.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfQuesDtl> find(WfQuesDtl wfQuesDtl) throws Exception {
		String sql = SqlUtil.getSql4All(WfQuesDtlHelper.class,wfQuesDtl,WfQuesDtl.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfQuesDtl>)DAOFactory.getDAO(WfQuesDtlDAO.class).query(sql, WfQuesDtl.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfQuesDtlDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfQuesDtl wfQuesDtl) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfQuesDtlDAO.class).amount(new WfQuesDtlHelper().getSql4Amount(wfQuesDtl));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}