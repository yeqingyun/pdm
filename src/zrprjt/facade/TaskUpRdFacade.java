package zrprjt.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrprjt.dao.TaskUpRdDAO;
import zrprjt.dao.helper.TaskUpRdHelper;
import zrprjt.vo.TaskUpRd;

public class TaskUpRdFacade {

	public void save(TaskUpRd taskUpRd) throws Exception { 
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskUpRdDAO.class).insert(taskUpRd);

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

	public void update(TaskUpRd taskUpRd) throws Exception { 
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskUpRdDAO.class).update(taskUpRd);

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
			DAOFactory.getDAO(TaskUpRdDAO.class).update(sql);
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

	public void submit(TaskUpRd taskUpRd) throws Exception { 
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskUpRdDAO.class).update(taskUpRd);
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

	public void review(TaskUpRd taskUpRd) throws Exception { 
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskUpRdDAO.class).update(taskUpRd);
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

	public void confirm(TaskUpRd taskUpRd) throws Exception { 
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskUpRdDAO.class).update(taskUpRd);
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
			DAOFactory.getDAO(TaskUpRdDAO.class).delete(sql);
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

	public void remove(TaskUpRd taskUpRd) throws Exception { 
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(TaskUpRdDAO.class).delete(new TaskUpRdHelper().getSql4Delete(taskUpRd));
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

	public TaskUpRd findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (TaskUpRd)DAOFactory.getDAO(TaskUpRdDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public TaskUpRd findById(TaskUpRd taskUpRd) throws Exception { 
		DbConnUtil.buildDbconn(1);
		try {
			return (TaskUpRd)DAOFactory.getDAO(TaskUpRdDAO.class).load(taskUpRd);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public TaskUpRd findBy(TaskUpRd TaskUpRd) throws Exception {
		String sql = SqlUtil.getSql4All(TaskUpRdHelper.class,TaskUpRd,TaskUpRd.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (TaskUpRd)DAOFactory.getDAO(TaskUpRdDAO.class).load(sql,TaskUpRd.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<TaskUpRd> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (List<TaskUpRd>)DAOFactory.getDAO(TaskUpRdDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<TaskUpRd> findAll(TaskUpRd TaskUpRd) throws Exception {
		String sql = SqlUtil.getSql4All(TaskUpRdHelper.class,TaskUpRd,TaskUpRd.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<TaskUpRd>)DAOFactory.getDAO(TaskUpRdDAO.class).query(sql, TaskUpRd.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<TaskUpRd> find(TaskUpRd TaskUpRd,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(TaskUpRdHelper.class,TaskUpRd,pageVO,TaskUpRd.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<TaskUpRd>)DAOFactory.getDAO(TaskUpRdDAO.class).query(sql, TaskUpRd.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	
//	public String getSql4Pages(TaskUpRd TaskUpRd,PageVO pageVO, String fields,String sqlString,String conDitionSQl) {
//		int pageSize = pageVO.getPageSize();
//		int pages = pageSize*pageVO.getPage()-pageSize;
//		String mstr = "select top "+pages+" TaskUpRd.Id"+ sqlString+conDitionSQl+getOrderBy();
//		String sql = "select top "+pageSize+" "+sqlString + conDitionSQl +" and TaskUpRd.Id not in("+mstr+") "+getOrderBy();
//
//		return sql;
//	}
	
	@SuppressWarnings("unchecked")
	public List<TaskUpRd> find(PageVO pageVO,String sqlString, String conDitionSQl) throws Exception {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" TaskUpRd.Id"+" "+sqlString+" "+conDitionSQl+" order by TaskUpRd.RoleId ";
		String sql = "select top "+pageSize+" "+TaskUpRd.LIST_FIELDS+" "+sqlString +" "+conDitionSQl +" and TaskUpRd.Id not in("+mstr+") "+" order by TaskUpRd.RoleId ";
		//String sql = SqlUtil.getSql4Pages(TaskUpRdHelper.class,TaskUpRd,pageVO,TaskUpRd.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<TaskUpRd>)DAOFactory.getDAO(TaskUpRdDAO.class).query(sql, TaskUpRd.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<TaskUpRd> find(TaskUpRd TaskUpRd) throws Exception {
		String sql = SqlUtil.getSql4All(TaskUpRdHelper.class,TaskUpRd,zrprjt.vo.TaskUpRd.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<TaskUpRd>)DAOFactory.getDAO(TaskUpRdDAO.class).query(sql, zrprjt.vo.TaskUpRd.LIST_FIELDS);
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
			return DAOFactory.getDAO(TaskUpRdDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(TaskUpRd taskUpRd) throws Exception { 
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(TaskUpRdDAO.class).amount(new TaskUpRdHelper().getSql4Amount(taskUpRd));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

}