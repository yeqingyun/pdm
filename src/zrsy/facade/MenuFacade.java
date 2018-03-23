package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.MenuDAO;
import zrsy.dao.helper.MenuHelper;
import zrsy.vo.Menu;

public class MenuFacade {

	public void save(Menu menu) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MenuDAO.class).insert(menu);
			if(menu.getGpMenus() != null && menu.getGpMenus().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.GpMenuDAO.class).delete("delete from GpMenu where GpMenu.MenuId = " +menu.getId());
				for(int i=0; i<menu.getGpMenus().size(); i++) {
					if(menu.getGpMenus().get(i) != null && menu.getGpMenus().get(i).getMenuId() != null) {
						menu.getGpMenus().get(i).setMenuId(menu.getId());
						DAOFactory.getDAO(zrsy.dao.GpMenuDAO.class).insert(menu.getGpMenus().get(i));
					}
				}
			}
			if(menu.getNodes() != null && menu.getNodes().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.NodeDAO.class).delete("delete from Node where Node.MenuId = " +menu.getId());
				for(int i=0; i<menu.getNodes().size(); i++) {
					if(menu.getNodes().get(i) != null && menu.getNodes().get(i).getMenuId() != null) {
						menu.getNodes().get(i).setMenuId(menu.getId());
						DAOFactory.getDAO(zrsy.dao.NodeDAO.class).insert(menu.getNodes().get(i));
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

	public void update(Menu menu) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MenuDAO.class).update(menu);
			if(menu.getGpMenus() != null && menu.getGpMenus().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.GpMenuDAO.class).delete("delete from GpMenu where GpMenu.MenuId = " +menu.getId());
				for(int i=0; i<menu.getGpMenus().size(); i++) {
					if(menu.getGpMenus().get(i) != null && menu.getGpMenus().get(i).getMenuId() != null) {
						menu.getGpMenus().get(i).setMenuId(menu.getId());
						DAOFactory.getDAO(zrsy.dao.GpMenuDAO.class).insert(menu.getGpMenus().get(i));
					}
				}
			}
			if(menu.getNodes() != null && menu.getNodes().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.NodeDAO.class).delete("delete from Node where Node.MenuId = " +menu.getId());
				for(int i=0; i<menu.getNodes().size(); i++) {
					if(menu.getNodes().get(i) != null && menu.getNodes().get(i).getMenuId() != null) {
						menu.getNodes().get(i).setMenuId(menu.getId());
						DAOFactory.getDAO(zrsy.dao.NodeDAO.class).insert(menu.getNodes().get(i));
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
			DAOFactory.getDAO(MenuDAO.class).update(sql);
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

	public void submit(Menu menu) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MenuDAO.class).update(menu);
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

	public void review(Menu menu) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MenuDAO.class).update(menu);
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

	public void confirm(Menu menu) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MenuDAO.class).update(menu);
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
			DAOFactory.getDAO(MenuDAO.class).delete(sql);
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

	public void remove(Menu menu) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MenuDAO.class).delete(new MenuHelper().getSql4Delete(menu));
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

	public Menu findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (Menu)DAOFactory.getDAO(MenuDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Menu findById(Menu menu) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (Menu)DAOFactory.getDAO(MenuDAO.class).load(menu);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Menu findBy(Menu menu) throws Exception {
		String sql = SqlUtil.getSql4All(MenuHelper.class,menu,Menu.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (Menu)DAOFactory.getDAO(MenuDAO.class).load(sql,Menu.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Menu> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Menu>)DAOFactory.getDAO(MenuDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Menu> findAll(Menu menu) throws Exception {
		String sql = SqlUtil.getSql4All(MenuHelper.class,menu,Menu.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Menu>)DAOFactory.getDAO(MenuDAO.class).query(sql, Menu.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Menu> find(Menu menu,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(MenuHelper.class,menu,pageVO,Menu.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Menu>)DAOFactory.getDAO(MenuDAO.class).query(sql, Menu.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Menu> find(Menu menu) throws Exception {
		String sql = SqlUtil.getSql4All(MenuHelper.class,menu,Menu.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Menu>)DAOFactory.getDAO(MenuDAO.class).query(sql, Menu.LIST_FIELDS);
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
			return DAOFactory.getDAO(MenuDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(Menu menu) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return DAOFactory.getDAO(MenuDAO.class).amount(new MenuHelper().getSql4Amount(menu));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}