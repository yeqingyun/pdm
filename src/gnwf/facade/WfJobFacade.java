package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfJobDAO;
import gnwf.dao.WfJobUserDAO;
import gnwf.dao.helper.WfJobHelper;
import gnwf.vo.WfJob;
import gnwf.vo.WfJobUser;

public class WfJobFacade {

	public void save(WfJob wfJob) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			List<WfJob> wfJobs = wfJob.getWfJobs();
			List<WfJobUser> wfJobUsers = wfJob.getWfJobUsers();
			DAOFactory.getDAO(WfJobDAO.class).delete("delete WfJob from WfJob where WfJob.JobId = " +wfJob.getJobId() + ";" +
					"delete WfJobUser from WfJobUser where WfJobUser.JobId = "+wfJob.getJobId());
			if(wfJobs != null) {
				for(WfJob job : wfJobs) {
					if(job != null && job.getFlowId() != null) {
						DAOFactory.getDAO(WfJobDAO.class).insert(job);
					}
				}
			}
			if(wfJobUsers != null) {
				for(WfJobUser usr : wfJobUsers) {
					if(usr != null) {
						DAOFactory.getDAO(WfJobUserDAO.class).insert(usr);
					}
				}
			}
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

	public void update(WfJob wfJob) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfJobDAO.class).update(wfJob);

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
			DAOFactory.getDAO(WfJobDAO.class).update(sql);
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

	public void submit(WfJob wfJob) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfJobDAO.class).update(wfJob);
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

	public void review(WfJob wfJob) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfJobDAO.class).update(wfJob);
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

	public void confirm(WfJob wfJob) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfJobDAO.class).update(wfJob);
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
			DAOFactory.getDAO(WfJobDAO.class).delete(sql);
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

	public void remove(WfJob wfJob) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfJobDAO.class).delete(new WfJobHelper().getSql4Delete(wfJob));
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

	public WfJob findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfJob)DAOFactory.getDAO(WfJobDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfJob findById(WfJob wfJob) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfJob)DAOFactory.getDAO(WfJobDAO.class).load(wfJob);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfJob findBy(WfJob wfJob) throws Exception {
		String sql = SqlUtil.getSql4All(WfJobHelper.class,wfJob,WfJob.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfJob)DAOFactory.getDAO(WfJobDAO.class).load(sql,WfJob.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfJob> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfJob>)DAOFactory.getDAO(WfJobDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfJob> findAll(WfJob wfJob) throws Exception {
		String sql = SqlUtil.getSql4All(WfJobHelper.class,wfJob,WfJob.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfJob>)DAOFactory.getDAO(WfJobDAO.class).query(sql, WfJob.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfJob> find(WfJob wfJob,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfJobHelper.class,wfJob,pageVO,WfJob.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfJob>)DAOFactory.getDAO(WfJobDAO.class).query(sql, WfJob.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfJob> find(WfJob wfJob) throws Exception {
		String sql = SqlUtil.getSql4All(WfJobHelper.class,wfJob,WfJob.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfJob>)DAOFactory.getDAO(WfJobDAO.class).query(sql, WfJob.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfJobDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfJob wfJob) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfJobDAO.class).amount(new WfJobHelper().getSql4Amount(wfJob));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}