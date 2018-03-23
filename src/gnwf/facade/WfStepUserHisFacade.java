package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfStepUserHisDAO;
import gnwf.dao.helper.WfStepUserHisHelper;
import gnwf.vo.WfStepUserHis;

public class WfStepUserHisFacade {

	public void save(WfStepUserHis wfStepUserHis) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepUserHisDAO.class).insert(wfStepUserHis);

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

	public void update(WfStepUserHis wfStepUserHis) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepUserHisDAO.class).update(wfStepUserHis);

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
			DAOFactory.getDAO(WfStepUserHisDAO.class).update(sql);
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

	public void submit(WfStepUserHis wfStepUserHis) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepUserHisDAO.class).update(wfStepUserHis);
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

	public void review(WfStepUserHis wfStepUserHis) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepUserHisDAO.class).update(wfStepUserHis);
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

	public void confirm(WfStepUserHis wfStepUserHis) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepUserHisDAO.class).update(wfStepUserHis);
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
			DAOFactory.getDAO(WfStepUserHisDAO.class).delete(sql);
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

	public void remove(WfStepUserHis wfStepUserHis) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepUserHisDAO.class).delete(new WfStepUserHisHelper().getSql4Delete(wfStepUserHis));
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

	public WfStepUserHis findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfStepUserHis)DAOFactory.getDAO(WfStepUserHisDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfStepUserHis findById(WfStepUserHis wfStepUserHis) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfStepUserHis)DAOFactory.getDAO(WfStepUserHisDAO.class).load(wfStepUserHis);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfStepUserHis findBy(WfStepUserHis wfStepUserHis) throws Exception {
		String sql = SqlUtil.getSql4All(WfStepUserHisHelper.class,wfStepUserHis,WfStepUserHis.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfStepUserHis)DAOFactory.getDAO(WfStepUserHisDAO.class).load(sql,WfStepUserHis.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfStepUserHis> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfStepUserHis>)DAOFactory.getDAO(WfStepUserHisDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfStepUserHis> findAll(WfStepUserHis wfStepUserHis) throws Exception {
		String sql = SqlUtil.getSql4All(WfStepUserHisHelper.class,wfStepUserHis,WfStepUserHis.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfStepUserHis>)DAOFactory.getDAO(WfStepUserHisDAO.class).query(sql, WfStepUserHis.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfStepUserHis> find(WfStepUserHis wfStepUserHis,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfStepUserHisHelper.class,wfStepUserHis,pageVO,WfStepUserHis.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfStepUserHis>)DAOFactory.getDAO(WfStepUserHisDAO.class).query(sql, WfStepUserHis.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfStepUserHis> find(WfStepUserHis wfStepUserHis) throws Exception {
		String sql = SqlUtil.getSql4All(WfStepUserHisHelper.class,wfStepUserHis,WfStepUserHis.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfStepUserHis>)DAOFactory.getDAO(WfStepUserHisDAO.class).query(sql, WfStepUserHis.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfStepUserHisDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfStepUserHis wfStepUserHis) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfStepUserHisDAO.class).amount(new WfStepUserHisHelper().getSql4Amount(wfStepUserHis));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}