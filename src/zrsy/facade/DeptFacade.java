package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.DeptDAO;
import zrsy.dao.helper.DeptHelper;
import zrsy.vo.Dept;

public class DeptFacade {

	public void save(Dept dept) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DeptDAO.class).insert(dept);
			if(dept.getUsrs() != null && dept.getUsrs().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.UsrDAO.class).delete("delete from Usr where Usr.DeptId = " +dept.getDeptId());
				for(int i=0; i<dept.getUsrs().size(); i++) {
					if(dept.getUsrs().get(i) != null && dept.getUsrs().get(i).getDeptId() != null) {
						dept.getUsrs().get(i).setDeptId(dept.getDeptId());
						DAOFactory.getDAO(zrsy.dao.UsrDAO.class).insert(dept.getUsrs().get(i));
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

	public void update(Dept dept) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DeptDAO.class).update(dept);
			if(dept.getUsrs() != null && dept.getUsrs().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.UsrDAO.class).delete("delete from Usr where Usr.DeptId = " +dept.getDeptId());
				for(int i=0; i<dept.getUsrs().size(); i++) {
					if(dept.getUsrs().get(i) != null && dept.getUsrs().get(i).getDeptId() != null) {
						dept.getUsrs().get(i).setDeptId(dept.getDeptId());
						DAOFactory.getDAO(zrsy.dao.UsrDAO.class).insert(dept.getUsrs().get(i));
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
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DeptDAO.class).update(sql);
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

	public void submit(Dept dept) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DeptDAO.class).update(dept);
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

	public void review(Dept dept) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DeptDAO.class).update(dept);
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

	public void confirm(Dept dept) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DeptDAO.class).update(dept);
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
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DeptDAO.class).delete(sql);
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

	public void remove(Dept dept) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DeptDAO.class).delete(new DeptHelper().getSql4Delete(dept));
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

	public Dept findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (Dept)DAOFactory.getDAO(DeptDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Dept findById(Dept dept) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (Dept)DAOFactory.getDAO(DeptDAO.class).load(dept);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Dept findBy(Dept dept) throws Exception {
		String sql = SqlUtil.getSql4All(DeptHelper.class,dept,Dept.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (Dept)DAOFactory.getDAO(DeptDAO.class).load(sql,Dept.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Dept> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Dept>)DAOFactory.getDAO(DeptDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Dept> findAll(Dept dept) throws Exception {
		String sql = SqlUtil.getSql4All(DeptHelper.class,dept,Dept.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		System.out.println(sql);
		try {
			return (List<Dept>)DAOFactory.getDAO(DeptDAO.class).query(sql, Dept.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Dept> find(Dept dept,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(DeptHelper.class,dept,pageVO,Dept.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Dept>)DAOFactory.getDAO(DeptDAO.class).query(sql, Dept.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Dept> find(Dept dept) throws Exception {
		String sql = SqlUtil.getSql4All(DeptHelper.class,dept,Dept.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Dept>)DAOFactory.getDAO(DeptDAO.class).query(sql, Dept.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return DAOFactory.getDAO(DeptDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(Dept dept) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return DAOFactory.getDAO(DeptDAO.class).amount(new DeptHelper().getSql4Amount(dept));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}