package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.ComDAO;
import zrsy.dao.helper.ComHelper;
import zrsy.vo.Com;

public class ComFacade {

	public void save(Com com) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ComDAO.class).insert(com);
			if(com.getDepts() != null && com.getDepts().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.DeptDAO.class).delete("delete from Dept where Dept.ComId = " +com.getComId());
				for(int i=0; i<com.getDepts().size(); i++) {
					if(com.getDepts().get(i) != null && com.getDepts().get(i).getComId() != null) {
						com.getDepts().get(i).setComId(com.getComId());
						DAOFactory.getDAO(zrsy.dao.DeptDAO.class).insert(com.getDepts().get(i));
					}
				}
			}
			if(com.getUsrs() != null && com.getUsrs().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.UsrDAO.class).delete("delete from Usr where Usr.ComId = " +com.getComId());
				for(int i=0; i<com.getUsrs().size(); i++) {
					if(com.getUsrs().get(i) != null && com.getUsrs().get(i).getComId() != null) {
						com.getUsrs().get(i).setComId(com.getComId());
						DAOFactory.getDAO(zrsy.dao.UsrDAO.class).insert(com.getUsrs().get(i));
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

	public void update(Com com) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ComDAO.class).update(com);
			if(com.getDepts() != null && com.getDepts().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.DeptDAO.class).delete("delete from Dept where Dept.ComId = " +com.getComId());
				for(int i=0; i<com.getDepts().size(); i++) {
					if(com.getDepts().get(i) != null && com.getDepts().get(i).getComId() != null) {
						com.getDepts().get(i).setComId(com.getComId());
						DAOFactory.getDAO(zrsy.dao.DeptDAO.class).insert(com.getDepts().get(i));
					}
				}
			}
			if(com.getUsrs() != null && com.getUsrs().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.UsrDAO.class).delete("delete from Usr where Usr.ComId = " +com.getComId());
				for(int i=0; i<com.getUsrs().size(); i++) {
					if(com.getUsrs().get(i) != null && com.getUsrs().get(i).getComId() != null) {
						com.getUsrs().get(i).setComId(com.getComId());
						DAOFactory.getDAO(zrsy.dao.UsrDAO.class).insert(com.getUsrs().get(i));
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
			DAOFactory.getDAO(ComDAO.class).update(sql);
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

	public void submit(Com com) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ComDAO.class).update(com);
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

	public void review(Com com) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ComDAO.class).update(com);
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

	public void confirm(Com com) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ComDAO.class).update(com);
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
			DAOFactory.getDAO(ComDAO.class).delete(sql);
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

	public void remove(Com com) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ComDAO.class).delete(new ComHelper().getSql4Delete(com));
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

	public Com findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (Com)DAOFactory.getDAO(ComDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Com findById(Com com) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (Com)DAOFactory.getDAO(ComDAO.class).load(com);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Com findBy(Com com) throws Exception {
		String sql = SqlUtil.getSql4All(ComHelper.class,com,Com.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (Com)DAOFactory.getDAO(ComDAO.class).load(sql,Com.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Com> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Com>)DAOFactory.getDAO(ComDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Com> findAll(Com com) throws Exception {
		String sql = SqlUtil.getSql4All(ComHelper.class,com,Com.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Com>)DAOFactory.getDAO(ComDAO.class).query(sql, Com.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Com> find(Com com,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(ComHelper.class,com,pageVO,Com.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Com>)DAOFactory.getDAO(ComDAO.class).query(sql, Com.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Com> find(Com com) throws Exception {
		String sql = SqlUtil.getSql4All(ComHelper.class,com,Com.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Com>)DAOFactory.getDAO(ComDAO.class).query(sql, Com.LIST_FIELDS);
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
			return DAOFactory.getDAO(ComDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(Com com) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return DAOFactory.getDAO(ComDAO.class).amount(new ComHelper().getSql4Amount(com));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}