package zrprjt.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrprjt.dao.TaskDAO;
import zrprjt.dao.helper.TaskHelper;
import zrprjt.vo.Task;

public class TaskFacade {

	public void save(Task task) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskDAO.class).insert(task);
			if(task.getTaskMsgs() != null && task.getTaskMsgs().size() > 0) {
				DAOFactory.getDAO(zrprjt.dao.TaskMsgDAO.class).delete("delete from TaskMsg where TaskMsg.TaskNo = " +task.getTaskNo());
				for(int i=0; i<task.getTaskMsgs().size(); i++) {
					if(task.getTaskMsgs().get(i) != null && task.getTaskMsgs().get(i).getTaskNo() != null) {
						//task.getTaskMsgs().get(i).setTaskNo(task.getTaskNo());
						DAOFactory.getDAO(zrprjt.dao.TaskMsgDAO.class).insert(task.getTaskMsgs().get(i));
					}
				}
			}
			if(task.getTaskWfs() != null && task.getTaskWfs().size() > 0) {
				DAOFactory.getDAO(zrprjt.dao.TaskWfDAO.class).delete("delete from TaskWf where TaskWf.TaskNo = " +task.getTaskNo());
				for(int i=0; i<task.getTaskWfs().size(); i++) {
					if(task.getTaskWfs().get(i) != null && task.getTaskWfs().get(i).getTaskNo() != null) {
						//task.getTaskWfs().get(i).setTaskNo(task.getTaskNo());
						DAOFactory.getDAO(zrprjt.dao.TaskWfDAO.class).insert(task.getTaskWfs().get(i));
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

	public void update(Task task) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskDAO.class).update(task);
			if(task.getTaskMsgs() != null && task.getTaskMsgs().size() > 0) {
				DAOFactory.getDAO(zrprjt.dao.TaskMsgDAO.class).delete("delete from TaskMsg where TaskMsg.TaskNo = " +task.getTaskNo());
				for(int i=0; i<task.getTaskMsgs().size(); i++) {
					if(task.getTaskMsgs().get(i) != null && task.getTaskMsgs().get(i).getTaskNo() != null) {
						//task.getTaskMsgs().get(i).setTaskNo(task.getTaskNo());
						DAOFactory.getDAO(zrprjt.dao.TaskMsgDAO.class).update(task.getTaskMsgs().get(i));
					}
				}
			}
			if(task.getTaskWfs() != null && task.getTaskWfs().size() > 0) {
				DAOFactory.getDAO(zrprjt.dao.TaskWfDAO.class).delete("delete from TaskWf where TaskWf.TaskNo = " +task.getTaskNo());
				for(int i=0; i<task.getTaskWfs().size(); i++) {
					if(task.getTaskWfs().get(i) != null && task.getTaskWfs().get(i).getTaskNo() != null) {
						//task.getTaskWfs().get(i).setTaskNo(task.getTaskNo());
						DAOFactory.getDAO(zrprjt.dao.TaskWfDAO.class).update(task.getTaskWfs().get(i));
					}
				}
			}

			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			e.printStackTrace();
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
			DAOFactory.getDAO(TaskDAO.class).update(sql);
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

	public void submit(Task task) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskDAO.class).update(task);
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

	public void review(Task task) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskDAO.class).update(task);
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

	public void confirm(Task task) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskDAO.class).update(task);
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
			DAOFactory.getDAO(TaskDAO.class).delete(sql);
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

	public void remove(Task task) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskDAO.class).delete(new TaskHelper().getSql4Delete(task));
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

	public Task findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (Task)DAOFactory.getDAO(TaskDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Task findById(Task task) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (Task)DAOFactory.getDAO(TaskDAO.class).load(task);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Task findBy(Task task) throws Exception {
		String sql = SqlUtil.getSql4All(TaskHelper.class,task,Task.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (Task)DAOFactory.getDAO(TaskDAO.class).load(sql,Task.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Task> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (List<Task>)DAOFactory.getDAO(TaskDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Task> findAll(Task task) throws Exception {
		String sql = SqlUtil.getSql4All(TaskHelper.class,task,Task.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<Task>)DAOFactory.getDAO(TaskDAO.class).query(sql, Task.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Task> find(Task task,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(TaskHelper.class,task,pageVO,Task.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<Task>)DAOFactory.getDAO(TaskDAO.class).query(sql, Task.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Task> find(Task task) throws Exception {
		String sql = SqlUtil.getSql4All(TaskHelper.class,task,Task.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<Task>)DAOFactory.getDAO(TaskDAO.class).query(sql, Task.LIST_FIELDS);
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
			return DAOFactory.getDAO(TaskDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(Task task) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(TaskDAO.class).amount(new TaskHelper().getSql4Amount(task));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}