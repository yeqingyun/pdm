package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfRdStepDAO;
import gnwf.dao.helper.WfRdStepHelper;
import gnwf.vo.WfRdStep;

public class WfRdStepFacade {

	public void save(WfRdStep wfRdStep) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdStepDAO.class).insert(wfRdStep);

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

	public void update(WfRdStep wfRdStep) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdStepDAO.class).update(wfRdStep);

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
			DAOFactory.getDAO(WfRdStepDAO.class).update(sql);
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

	public void submit(WfRdStep wfRdStep) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdStepDAO.class).update(wfRdStep);
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

	public void review(WfRdStep wfRdStep) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdStepDAO.class).update(wfRdStep);
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

	public void confirm(WfRdStep wfRdStep) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdStepDAO.class).update(wfRdStep);
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
			DAOFactory.getDAO(WfRdStepDAO.class).delete(sql);
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

	public void remove(WfRdStep wfRdStep) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdStepDAO.class).delete(new WfRdStepHelper().getSql4Delete(wfRdStep));
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

	public WfRdStep findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRdStep)DAOFactory.getDAO(WfRdStepDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfRdStep findById(WfRdStep wfRdStep) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRdStep)DAOFactory.getDAO(WfRdStepDAO.class).load(wfRdStep);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfRdStep findBy(WfRdStep wfRdStep) throws Exception {
		String sql = SqlUtil.getSql4All(WfRdStepHelper.class,wfRdStep,WfRdStep.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRdStep)DAOFactory.getDAO(WfRdStepDAO.class).load(sql,WfRdStep.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRdStep> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRdStep>)DAOFactory.getDAO(WfRdStepDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRdStep> findAll(WfRdStep wfRdStep) throws Exception {
		String sql = SqlUtil.getSql4All(WfRdStepHelper.class,wfRdStep,WfRdStep.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRdStep>)DAOFactory.getDAO(WfRdStepDAO.class).query(sql, WfRdStep.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRdStep> find(WfRdStep wfRdStep,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfRdStepHelper.class,wfRdStep,pageVO,WfRdStep.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRdStep>)DAOFactory.getDAO(WfRdStepDAO.class).query(sql, WfRdStep.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRdStep> find(WfRdStep wfRdStep) throws Exception {
		String sql = SqlUtil.getSql4All(WfRdStepHelper.class,wfRdStep,WfRdStep.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRdStep>)DAOFactory.getDAO(WfRdStepDAO.class).query(sql, WfRdStep.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfRdStepDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfRdStep wfRdStep) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfRdStepDAO.class).amount(new WfRdStepHelper().getSql4Amount(wfRdStep));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}