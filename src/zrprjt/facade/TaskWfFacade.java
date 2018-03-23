package zrprjt.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrprjt.dao.TaskWfDAO;
import zrprjt.dao.helper.TaskWfHelper;
import zrprjt.vo.TaskWf;

public class TaskWfFacade {

	public void save(TaskWf taskWf) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskWfDAO.class).insert(taskWf);

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

	public void update(TaskWf taskWf) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskWfDAO.class).update(taskWf);

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
			DAOFactory.getDAO(TaskWfDAO.class).update(sql);
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

	public void submit(TaskWf taskWf) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskWfDAO.class).update(taskWf);
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

	public void review(TaskWf taskWf) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskWfDAO.class).update(taskWf);
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

	public void confirm(TaskWf taskWf) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskWfDAO.class).update(taskWf);
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
			DAOFactory.getDAO(TaskWfDAO.class).delete(sql);
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

	public void remove(TaskWf taskWf) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskWfDAO.class).delete(new TaskWfHelper().getSql4Delete(taskWf));
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

	public TaskWf findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (TaskWf)DAOFactory.getDAO(TaskWfDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public TaskWf findById(TaskWf taskWf) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (TaskWf)DAOFactory.getDAO(TaskWfDAO.class).load(taskWf);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public TaskWf findBy(TaskWf taskWf) throws Exception {
		String sql = SqlUtil.getSql4All(TaskWfHelper.class,taskWf,TaskWf.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (TaskWf)DAOFactory.getDAO(TaskWfDAO.class).load(sql,TaskWf.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<TaskWf> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (List<TaskWf>)DAOFactory.getDAO(TaskWfDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<TaskWf> findAll(TaskWf taskWf) throws Exception {
		String sql = SqlUtil.getSql4All(TaskWfHelper.class,taskWf,TaskWf.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<TaskWf>)DAOFactory.getDAO(TaskWfDAO.class).query(sql, TaskWf.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<TaskWf> find(TaskWf taskWf,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(TaskWfHelper.class,taskWf,pageVO,TaskWf.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<TaskWf>)DAOFactory.getDAO(TaskWfDAO.class).query(sql, TaskWf.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<TaskWf> find(TaskWf taskWf) throws Exception {
		String sql = SqlUtil.getSql4All(TaskWfHelper.class,taskWf,TaskWf.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<TaskWf>)DAOFactory.getDAO(TaskWfDAO.class).query(sql, TaskWf.LIST_FIELDS);
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
			return DAOFactory.getDAO(TaskWfDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(TaskWf taskWf) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(TaskWfDAO.class).amount(new TaskWfHelper().getSql4Amount(taskWf));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}