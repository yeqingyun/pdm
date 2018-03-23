package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfStepDAO;
import gnwf.dao.helper.WfStepHelper;
import gnwf.vo.WfStep;
import gnwf.vo.WfStepUser;

public class WfStepFacade {

	public void save(WfStep wfStep) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepDAO.class).insert(wfStep);
			
//			if(wfStep.getWfRdTasks() != null && wfStep.getWfRdTasks().size() > 0) {
//				DAOFactory.getDAO(gnwf.dao.WfRdTaskDAO.class).delete("delete from WfRdTask where WfRdTask.StepId = " +wfStep.getStepId());
//				for(int i=0; i<wfStep.getWfRdTasks().size(); i++) {
//					if(wfStep.getWfRdTasks().get(i) != null && wfStep.getWfRdTasks().get(i).getStepId() != null) {
//						wfStep.getWfRdTasks().get(i).setStepId(wfStep.getStepId());
//						DAOFactory.getDAO(gnwf.dao.WfRdTaskDAO.class).insert(wfStep.getWfRdTasks().get(i));
//					}
//				}
//			}
//			
//			DAOFactory.getDAO(gnwf.dao.WfStepUserDAO.class).delete("delete from WfStepUser where WfStepUser.StepId = " +wfStep.getStepId());
//			if(wfStep.getWfStepUsers() != null && wfStep.getWfStepUsers().size() > 0) {
//				for(int i=0; i<wfStep.getWfStepUsers().size(); i++) {
//					WfStepUser s = wfStep.getWfStepUsers().get(i);
//					if( s!= null && (s.getPrjtRoleId()!=null || s.getUserId()!=null)) {
//						wfStep.getWfStepUsers().get(i).setStepId(wfStep.getStepId());
//						DAOFactory.getDAO(gnwf.dao.WfStepUserDAO.class).insert(wfStep.getWfStepUsers().get(i));
//					}
//				}
//			}
//			
//			DAOFactory.getDAO(gnwf.dao.WfStepNextDAO.class).delete("delete from WfStepNext where WfStepNext.StepId = " +wfStep.getStepId());
//			if(wfStep.getWfStepNexts() != null && wfStep.getWfStepNexts().size() > 0) {
//				for(int i=0; i<wfStep.getWfStepNexts().size(); i++) {
//					if(wfStep.getWfStepNexts().get(i) != null && wfStep.getWfStepNexts().get(i).getStepId() != null) {
//						wfStep.getWfStepNexts().get(i).setStepId(wfStep.getStepId());
//						DAOFactory.getDAO(gnwf.dao.WfStepNextDAO.class).insert(wfStep.getWfStepNexts().get(i));
//					}
//				}
//			}

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

	public void update(WfStep wfStep) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepDAO.class).update(wfStep);
//			if(wfStep.getWfRdTasks() != null && wfStep.getWfRdTasks().size() > 0) {
//				DAOFactory.getDAO(gnwf.dao.WfRdTaskDAO.class).delete("delete from WfRdTask where WfRdTask.StepId = " +wfStep.getStepId());
//				for(int i=0; i<wfStep.getWfRdTasks().size(); i++) {
//					if(wfStep.getWfRdTasks().get(i) != null && wfStep.getWfRdTasks().get(i).getStepId() != null) {
//						wfStep.getWfRdTasks().get(i).setStepId(wfStep.getStepId());
//						DAOFactory.getDAO(gnwf.dao.WfRdTaskDAO.class).insert(wfStep.getWfRdTasks().get(i));
//					}
//				}
//			}
			
			DAOFactory.getDAO(gnwf.dao.WfStepUserDAO.class).delete("delete from WfStepUser where WfStepUser.StepId = " +wfStep.getStepId());
			if(wfStep.getWfStepUsers() != null && wfStep.getWfStepUsers().size() > 0) {
				for(int i=0; i<wfStep.getWfStepUsers().size(); i++) {
					WfStepUser s = wfStep.getWfStepUsers().get(i);
					if( s!= null && (s.getPrjtRoleId()!=null || s.getUserId()!=null)) {
						wfStep.getWfStepUsers().get(i).setStepId(wfStep.getStepId());
						DAOFactory.getDAO(gnwf.dao.WfStepUserDAO.class).insert(wfStep.getWfStepUsers().get(i));
					}
				}
			}
			
			DAOFactory.getDAO(gnwf.dao.WfStepNextDAO.class).delete("delete from WfStepNext where WfStepNext.StepId = " +wfStep.getStepId());
			if(wfStep.getWfStepNexts() != null && wfStep.getWfStepNexts().size() > 0) {
				for(int i=0; i<wfStep.getWfStepNexts().size(); i++) {
					if(wfStep.getWfStepNexts().get(i) != null && wfStep.getWfStepNexts().get(i).getNextId() != null) {
						wfStep.getWfStepNexts().get(i).setStepId(wfStep.getStepId());
						DAOFactory.getDAO(gnwf.dao.WfStepNextDAO.class).insert(wfStep.getWfStepNexts().get(i));
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

	public void update(String sql) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepDAO.class).update(sql);
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

	public void submit(WfStep wfStep) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepDAO.class).update(wfStep);
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

	public void review(WfStep wfStep) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepDAO.class).update(wfStep);
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

	public void confirm(WfStep wfStep) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepDAO.class).update(wfStep);
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
			DAOFactory.getDAO(WfStepDAO.class).delete(sql);
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

	public void remove(WfStep wfStep) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepDAO.class).delete(new WfStepHelper().getSql4Delete(wfStep));
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

	public WfStep findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfStep)DAOFactory.getDAO(WfStepDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfStep findById(WfStep wfStep) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfStep)DAOFactory.getDAO(WfStepDAO.class).load(wfStep);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfStep findBy(WfStep wfStep) throws Exception {
		String sql = SqlUtil.getSql4All(WfStepHelper.class,wfStep,WfStep.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfStep)DAOFactory.getDAO(WfStepDAO.class).load(sql,WfStep.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfStep> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfStep>)DAOFactory.getDAO(WfStepDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfStep> findAll(WfStep wfStep) throws Exception {
		String sql = SqlUtil.getSql4All(WfStepHelper.class,wfStep,WfStep.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfStep>)DAOFactory.getDAO(WfStepDAO.class).query(sql, WfStep.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfStep> find(WfStep wfStep,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfStepHelper.class,wfStep,pageVO,WfStep.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfStep>)DAOFactory.getDAO(WfStepDAO.class).query(sql, WfStep.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfStep> find(WfStep wfStep) throws Exception {
		String sql = SqlUtil.getSql4All(WfStepHelper.class,wfStep,WfStep.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfStep>)DAOFactory.getDAO(WfStepDAO.class).query(sql, WfStep.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfStepDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfStep wfStep) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfStepDAO.class).amount(new WfStepHelper().getSql4Amount(wfStep));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}