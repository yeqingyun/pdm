package zrprjt.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrprjt.dao.TaskMsgDAO;
import zrprjt.dao.helper.TaskMsgHelper;
import zrprjt.vo.TaskMsg;

public class TaskMsgFacade {

	public void save(TaskMsg taskMsg) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskMsgDAO.class).insert(taskMsg);

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

	public void update(TaskMsg taskMsg) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskMsgDAO.class).update(taskMsg);

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
			DAOFactory.getDAO(TaskMsgDAO.class).update(sql);
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

	public void submit(TaskMsg taskMsg) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskMsgDAO.class).update(taskMsg);
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

	public void review(TaskMsg taskMsg) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskMsgDAO.class).update(taskMsg);
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

	public void confirm(TaskMsg taskMsg) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskMsgDAO.class).update(taskMsg);
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
			DAOFactory.getDAO(TaskMsgDAO.class).delete(sql);
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

	public void remove(TaskMsg taskMsg) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskMsgDAO.class).delete(new TaskMsgHelper().getSql4Delete(taskMsg));
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

	public TaskMsg findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (TaskMsg)DAOFactory.getDAO(TaskMsgDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public TaskMsg findById(TaskMsg taskMsg) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (TaskMsg)DAOFactory.getDAO(TaskMsgDAO.class).load(taskMsg);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public TaskMsg findBy(TaskMsg taskMsg) throws Exception {
		String sql = SqlUtil.getSql4All(TaskMsgHelper.class,taskMsg,TaskMsg.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (TaskMsg)DAOFactory.getDAO(TaskMsgDAO.class).load(sql,TaskMsg.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<TaskMsg> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (List<TaskMsg>)DAOFactory.getDAO(TaskMsgDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<TaskMsg> findAll(TaskMsg taskMsg) throws Exception {
		String sql = SqlUtil.getSql4All(TaskMsgHelper.class,taskMsg,TaskMsg.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<TaskMsg>)DAOFactory.getDAO(TaskMsgDAO.class).query(sql, TaskMsg.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<TaskMsg> find(TaskMsg taskMsg,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(TaskMsgHelper.class,taskMsg,pageVO,TaskMsg.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<TaskMsg>)DAOFactory.getDAO(TaskMsgDAO.class).query(sql, TaskMsg.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<TaskMsg> find(TaskMsg taskMsg) throws Exception {
		String sql = SqlUtil.getSql4All(TaskMsgHelper.class,taskMsg,TaskMsg.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<TaskMsg>)DAOFactory.getDAO(TaskMsgDAO.class).query(sql, TaskMsg.LIST_FIELDS);
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
			return DAOFactory.getDAO(TaskMsgDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(TaskMsg taskMsg) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(TaskMsgDAO.class).amount(new TaskMsgHelper().getSql4Amount(taskMsg));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}