package gnwf.facade;

import gnwf.dao.WfRdTaskDAO;
import gnwf.dao.helper.WfRdTaskHelper;
import gnwf.vo.WfRdTask;
import gnwf.ww.MSG;
import gnwf.ww.workflow.WFUtil;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

public class WfRdTaskFacade {
	
	public void saveAll(List<WfRdTask> list,int owner) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			
			if(WFUtil.isNotNull(list)){
				StringBuffer str = new StringBuffer();
				StringBuffer str2 = new StringBuffer();
				
				for(WfRdTask t:list){
					str.append("delete from WfStepUserHis where owner="+owner+" and stepId="+t.getStepId()+";");
					str2.append("insert into WfStepUserHis values("+owner+","+t.getStepId()+","+t.getAcceptBy()+","+t.getTaskType()+");");
					DAOFactory.getDAO(WfRdTaskDAO.class).insert(t);
				}
				
				if(str.length()>0){
					DAOFactory.getDAO(WfRdTaskDAO.class).update(str.toString());
				}
				if(str2.length()>0){
					DAOFactory.getDAO(WfRdTaskDAO.class).update(str2.toString());
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
	
	public void saveAll(List<WfRdTask> list) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			if(WFUtil.isNotNull(list)){
				for(WfRdTask t:list){
					DAOFactory.getDAO(WfRdTaskDAO.class).insert(t);
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
	
	public void rejectTask(WfRdTask wfRdTask) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			
			String sql = "update wfrdtask set status="+MSG.OWFTASK_STATUS_1+
				" where taskid=(select pretaskid from wfrdtask where taskid="+wfRdTask.getTaskId()+")";
			
			DAOFactory.getDAO(WfRdTaskDAO.class).update(sql);
			DAOFactory.getDAO(WfRdTaskDAO.class).update(wfRdTask);
			
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

	public void save(WfRdTask wfRdTask) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdTaskDAO.class).insert(wfRdTask);
//			if(wfRdTask.getWfDocs() != null && wfRdTask.getWfDocs().size() > 0) {
//				DAOFactory.getDAO(gnwf.dao.WfDocDAO.class).delete("delete from WfDoc where WfDoc.TaskId = " +wfRdTask.getTaskId());
//				for(int i=0; i<wfRdTask.getWfDocs().size(); i++) {
//					if(wfRdTask.getWfDocs().get(i) != null && wfRdTask.getWfDocs().get(i).getTaskId() != null) {
//						wfRdTask.getWfDocs().get(i).setTaskId(wfRdTask.getTaskId());
//						DAOFactory.getDAO(gnwf.dao.WfDocDAO.class).insert(wfRdTask.getWfDocs().get(i));
//					}
//				}
//			}
//			if(wfRdTask.getWfRdSigns() != null && wfRdTask.getWfRdSigns().size() > 0) {
//				DAOFactory.getDAO(gnwf.dao.WfRdSignDAO.class).delete("delete from WfRdSign where WfRdSign.TaskId = " +wfRdTask.getTaskId());
//				for(int i=0; i<wfRdTask.getWfRdSigns().size(); i++) {
//					if(wfRdTask.getWfRdSigns().get(i) != null && wfRdTask.getWfRdSigns().get(i).getTaskId() != null) {
//						wfRdTask.getWfRdSigns().get(i).setTaskId(wfRdTask.getTaskId());
//						DAOFactory.getDAO(gnwf.dao.WfRdSignDAO.class).insert(wfRdTask.getWfRdSigns().get(i));
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

	public void update(WfRdTask wfRdTask) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdTaskDAO.class).update(wfRdTask);
//			if(wfRdTask.getWfDocs() != null && wfRdTask.getWfDocs().size() > 0) {
//				DAOFactory.getDAO(gnwf.dao.WfDocDAO.class).delete("delete from WfDoc where WfDoc.TaskId = " +wfRdTask.getTaskId());
//				for(int i=0; i<wfRdTask.getWfDocs().size(); i++) {
//					if(wfRdTask.getWfDocs().get(i) != null && wfRdTask.getWfDocs().get(i).getTaskId() != null) {
//						wfRdTask.getWfDocs().get(i).setTaskId(wfRdTask.getTaskId());
//						DAOFactory.getDAO(gnwf.dao.WfDocDAO.class).insert(wfRdTask.getWfDocs().get(i));
//					}
//				}
//			}
//			if(wfRdTask.getWfRdSigns() != null && wfRdTask.getWfRdSigns().size() > 0) {
//				DAOFactory.getDAO(gnwf.dao.WfRdSignDAO.class).delete("delete from WfRdSign where WfRdSign.TaskId = " +wfRdTask.getTaskId());
//				for(int i=0; i<wfRdTask.getWfRdSigns().size(); i++) {
//					if(wfRdTask.getWfRdSigns().get(i) != null && wfRdTask.getWfRdSigns().get(i).getTaskId() != null) {
//						wfRdTask.getWfRdSigns().get(i).setTaskId(wfRdTask.getTaskId());
//						DAOFactory.getDAO(gnwf.dao.WfRdSignDAO.class).insert(wfRdTask.getWfRdSigns().get(i));
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

	public void update(String sql) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdTaskDAO.class).update(sql);
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

	public void submit(WfRdTask wfRdTask) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdTaskDAO.class).update(wfRdTask);
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

	public void review(WfRdTask wfRdTask) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdTaskDAO.class).update(wfRdTask);
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

	public void confirm(WfRdTask wfRdTask) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdTaskDAO.class).update(wfRdTask);
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
			DAOFactory.getDAO(WfRdTaskDAO.class).delete(sql);
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

	public void remove(WfRdTask wfRdTask) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdTaskDAO.class).delete(new WfRdTaskHelper().getSql4Delete(wfRdTask));
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

	public WfRdTask findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRdTask)DAOFactory.getDAO(WfRdTaskDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfRdTask findById(WfRdTask wfRdTask) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRdTask)DAOFactory.getDAO(WfRdTaskDAO.class).load(wfRdTask);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfRdTask findBy(WfRdTask wfRdTask) throws Exception {
		String sql = SqlUtil.getSql4All(WfRdTaskHelper.class,wfRdTask,WfRdTask.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRdTask)DAOFactory.getDAO(WfRdTaskDAO.class).load(sql,WfRdTask.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRdTask> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRdTask>)DAOFactory.getDAO(WfRdTaskDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRdTask> findAll(WfRdTask wfRdTask) throws Exception {
		String sql = SqlUtil.getSql4All(WfRdTaskHelper.class,wfRdTask,WfRdTask.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRdTask>)DAOFactory.getDAO(WfRdTaskDAO.class).query(sql, WfRdTask.LIST_FIELDS);
		
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRdTask> find(WfRdTask wfRdTask,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfRdTaskHelper.class,wfRdTask,pageVO,WfRdTask.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRdTask>)DAOFactory.getDAO(WfRdTaskDAO.class).query(sql, WfRdTask.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRdTask> find(WfRdTask wfRdTask) throws Exception {
		String sql = SqlUtil.getSql4All(WfRdTaskHelper.class,wfRdTask,WfRdTask.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRdTask>)DAOFactory.getDAO(WfRdTaskDAO.class).query(sql, WfRdTask.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfRdTaskDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfRdTask wfRdTask) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfRdTaskDAO.class).amount(new WfRdTaskHelper().getSql4Amount(wfRdTask));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}