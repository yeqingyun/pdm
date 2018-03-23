package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.GpMenuDAO;
import zrsy.dao.helper.GpMenuHelper;
import zrsy.vo.GpMenu;

public class GpMenuFacade {

	public void save(GpMenu gpMenu) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpMenuDAO.class).insert(gpMenu);

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

	public void update(GpMenu gpMenu) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpMenuDAO.class).update(gpMenu);

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
			DAOFactory.getDAO(GpMenuDAO.class).update(sql);
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

	public void submit(GpMenu gpMenu) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpMenuDAO.class).update(gpMenu);
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

	public void review(GpMenu gpMenu) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpMenuDAO.class).update(gpMenu);
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

	public void confirm(GpMenu gpMenu) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpMenuDAO.class).update(gpMenu);
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
			DAOFactory.getDAO(GpMenuDAO.class).delete(sql);
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

	public void remove(GpMenu gpMenu) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpMenuDAO.class).delete(new GpMenuHelper().getSql4Delete(gpMenu));
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

	public GpMenu findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (GpMenu)DAOFactory.getDAO(GpMenuDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public GpMenu findById(GpMenu gpMenu) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (GpMenu)DAOFactory.getDAO(GpMenuDAO.class).load(gpMenu);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public GpMenu findBy(GpMenu gpMenu) throws Exception {
		String sql = SqlUtil.getSql4All(GpMenuHelper.class,gpMenu,GpMenu.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (GpMenu)DAOFactory.getDAO(GpMenuDAO.class).load(sql,GpMenu.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpMenu> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (List<GpMenu>)DAOFactory.getDAO(GpMenuDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpMenu> findAll(GpMenu gpMenu) throws Exception {
		String sql = SqlUtil.getSql4All(GpMenuHelper.class,gpMenu,GpMenu.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<GpMenu>)DAOFactory.getDAO(GpMenuDAO.class).query(sql, GpMenu.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpMenu> find(GpMenu gpMenu,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(GpMenuHelper.class,gpMenu,pageVO,GpMenu.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<GpMenu>)DAOFactory.getDAO(GpMenuDAO.class).query(sql, GpMenu.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<GpMenu> find(GpMenu gpMenu) throws Exception {
		String sql = SqlUtil.getSql4All(GpMenuHelper.class,gpMenu,GpMenu.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<GpMenu>)DAOFactory.getDAO(GpMenuDAO.class).query(sql, GpMenu.LIST_FIELDS);
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
			return DAOFactory.getDAO(GpMenuDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(GpMenu gpMenu) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return DAOFactory.getDAO(GpMenuDAO.class).amount(new GpMenuHelper().getSql4Amount(gpMenu));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}