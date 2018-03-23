package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfMatlApplyDAO;
import gnwf.dao.helper.WfMatlApplyHelper;
import gnwf.vo.WfMatlApply;

public class WfMatlApplyFacade {

	public void save(WfMatlApply wfMatlApply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlApplyDAO.class).insert(wfMatlApply);

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

	public void update(WfMatlApply wfMatlApply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlApplyDAO.class).update(wfMatlApply);

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
			DAOFactory.getDAO(WfMatlApplyDAO.class).update(sql);
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

	public void submit(WfMatlApply wfMatlApply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlApplyDAO.class).update(wfMatlApply);
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

	public void review(WfMatlApply wfMatlApply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlApplyDAO.class).update(wfMatlApply);
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

	public void confirm(WfMatlApply wfMatlApply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlApplyDAO.class).update(wfMatlApply);
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
			DAOFactory.getDAO(WfMatlApplyDAO.class).delete(sql);
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

	public void remove(WfMatlApply wfMatlApply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlApplyDAO.class).delete(new WfMatlApplyHelper().getSql4Delete(wfMatlApply));
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

	public WfMatlApply findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfMatlApply)DAOFactory.getDAO(WfMatlApplyDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfMatlApply findById(WfMatlApply wfMatlApply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfMatlApply)DAOFactory.getDAO(WfMatlApplyDAO.class).load(wfMatlApply);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfMatlApply findBy(WfMatlApply wfMatlApply) throws Exception {
		String sql = SqlUtil.getSql4All(WfMatlApplyHelper.class,wfMatlApply,WfMatlApply.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfMatlApply)DAOFactory.getDAO(WfMatlApplyDAO.class).load(sql,WfMatlApply.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfMatlApply> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfMatlApply>)DAOFactory.getDAO(WfMatlApplyDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfMatlApply> findAll(WfMatlApply wfMatlApply) throws Exception {
		String sql = SqlUtil.getSql4All(WfMatlApplyHelper.class,wfMatlApply,WfMatlApply.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfMatlApply>)DAOFactory.getDAO(WfMatlApplyDAO.class).query(sql, WfMatlApply.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfMatlApply> find(WfMatlApply wfMatlApply,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfMatlApplyHelper.class,wfMatlApply,pageVO,WfMatlApply.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfMatlApply>)DAOFactory.getDAO(WfMatlApplyDAO.class).query(sql, WfMatlApply.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfMatlApply> find(WfMatlApply wfMatlApply) throws Exception {
		String sql = SqlUtil.getSql4All(WfMatlApplyHelper.class,wfMatlApply,WfMatlApply.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfMatlApply>)DAOFactory.getDAO(WfMatlApplyDAO.class).query(sql, WfMatlApply.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfMatlApplyDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfMatlApply wfMatlApply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfMatlApplyDAO.class).amount(new WfMatlApplyHelper().getSql4Amount(wfMatlApply));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}