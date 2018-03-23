package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.MenuHelper;
import zrsy.vo.Menu;

public class MenuDAO extends BasicDAO implements GenericDAO {

	private MenuHelper menuHelper = new MenuHelper();

	public void insert(Object menu) throws java.sql.SQLException {
		Menu _menu = (Menu)menu;
		String fields = menuHelper.getFields(_menu);
		String sql = menuHelper.getInsertSql(fields);
		try {
			menuHelper.pstmtInsert(_menu, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MenuDAO.insert", e);
			throw e;
		}
	}

	public void update(Object menu) throws java.sql.SQLException {
		Menu _menu = (Menu)menu;
		String fields = menuHelper.getFields(_menu);
		String sql = menuHelper.getUpdateSql(fields, "Menu.Id" ,_menu.getId().toString());
		try {
			menuHelper.pstmtUpdate(_menu, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MenuDAO.update", e);
			throw e;
		}
	}

	public List<Menu> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return menuHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MenuDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<Menu> list = menuHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (Menu)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MenuDAO.load", e);
			throw e;
		}
	}

	public Object load(Object menu) throws java.sql.SQLException {
		Menu _menu = (Menu)menu;
		String sql = "select "+Menu.ALL_FIELDS+menuHelper.getSqlString()+" and Menu.Id = '"+_menu.getId()+"'";
		try {
			List<Menu> list = menuHelper.getQueryList(query(sql),Menu.ALL_FIELDS);
			if(list.size() > 0)
				return (Menu)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MenuDAO.load", e);
			throw e;
		}
	}
}