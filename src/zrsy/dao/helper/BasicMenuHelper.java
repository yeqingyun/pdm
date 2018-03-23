package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.Menu;

public class BasicMenuHelper implements SqlHelper {
	public String getSqlString() {
		return " from Menu where 1=1";
	}

	public String getOrderBy() {
		return " order by Menu.Id";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((Menu)object);
	}

	public String getSql4Amount(Menu menu) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(menu);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((Menu)object);
	}

	public String getSql4Delete(Menu menu) {
		return "delete from Menu where 1=1"+getSqlCondition(menu);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((Menu)object,fields);
	}

	public String getSql4All(Menu menu, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(menu)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((Menu)object,pageVO,fields);
	}

	public String getSql4Pages(Menu menu,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" Menu.Id "+ getSqlString()+getSqlCondition(menu)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(menu)+" and Menu.Id not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((Menu)object);
	}

	public String getSqlCondition(Menu menu) {
		String sql = "";
		if(null != menu.getId())
			sql += " and Menu.Id = '"+menu.getId()+"'";
		if(null != menu.getSyId())
			sql += " and Menu.SyId = '"+menu.getSyId()+"'";
		if(null != menu.getParent())
			sql += " and Menu.Parent = '"+menu.getParent()+"'";
		if(null != menu.getWidth())
			sql += " and Menu.Width = '"+menu.getWidth()+"'";
		if(null != menu.getLeve())
			sql += " and Menu.Leve = '"+menu.getLeve()+"'";
		if(null != menu.getSort())
			sql += " and Menu.Sort = '"+menu.getSort()+"'";
		if(null != menu.getText() && !menu.getText().trim().equals(""))
			sql += " and Menu.Text = '"+menu.getText().trim()+"'";
		if(null != menu.getClick() && !menu.getClick().trim().equals(""))
			sql += " and Menu.Click = '"+menu.getClick().trim()+"'";
		if(null != menu.getIcon() && !menu.getIcon().trim().equals(""))
			sql += " and Menu.Icon = '"+menu.getIcon().trim()+"'";

		return sql;
	}

	public List<Menu> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<Menu> list = new ArrayList<Menu>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				Menu menu = new Menu();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("Menu.Id"))
						menu.setId(rs.getInt("Id"));
					if(_fields[i].equals("SyId") || _fields[i].equals("Menu.SyId"))
						menu.setSyId(rs.getInt("SyId"));
					if(_fields[i].equals("Parent") || _fields[i].equals("Menu.Parent"))
						menu.setParent(rs.getInt("Parent"));
					if(_fields[i].equals("Width") || _fields[i].equals("Menu.Width"))
						menu.setWidth(rs.getInt("Width"));
					if(_fields[i].equals("Leve") || _fields[i].equals("Menu.Leve"))
						menu.setLeve(rs.getInt("Leve"));
					if(_fields[i].equals("Sort") || _fields[i].equals("Menu.Sort"))
						menu.setSort(rs.getInt("Sort"));
					if(_fields[i].equals("Text") || _fields[i].equals("Menu.Text"))
						menu.setText(rs.getString("Text"));
					if(_fields[i].equals("Click") || _fields[i].equals("Menu.Click"))
						menu.setClick(rs.getString("Click"));
					if(_fields[i].equals("Icon") || _fields[i].equals("Menu.Icon"))
						menu.setIcon(rs.getString("Icon"));

				}
				list.add(menu);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MenuHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into Menu("+fields.replaceAll("Menu\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(Menu menu,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("Menu.Id")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, menu.getId());
					}
					else if(_fields[i].equals("SyId") || _fields[i].equals("Menu.SyId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, menu.getSyId());
					}
					else if(_fields[i].equals("Parent") || _fields[i].equals("Menu.Parent")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, menu.getParent());
					}
					else if(_fields[i].equals("Width") || _fields[i].equals("Menu.Width")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, menu.getWidth());
					}
					else if(_fields[i].equals("Leve") || _fields[i].equals("Menu.Leve")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, menu.getLeve());
					}
					else if(_fields[i].equals("Sort") || _fields[i].equals("Menu.Sort")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, menu.getSort());
					}
					else if(_fields[i].equals("Text") || _fields[i].equals("Menu.Text")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, menu.getText());
					}
					else if(_fields[i].equals("Click") || _fields[i].equals("Menu.Click")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, menu.getClick());
					}
					else if(_fields[i].equals("Icon") || _fields[i].equals("Menu.Icon")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, menu.getIcon());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("MenuHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update Menu set ";
		String[] _fields = fields.replaceAll("Menu\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SyId") || _fields[i].equals("Menu.SyId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Parent") || _fields[i].equals("Menu.Parent"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Width") || _fields[i].equals("Menu.Width"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Leve") || _fields[i].equals("Menu.Leve"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Sort") || _fields[i].equals("Menu.Sort"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Text") || _fields[i].equals("Menu.Text"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Click") || _fields[i].equals("Menu.Click"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Icon") || _fields[i].equals("Menu.Icon"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(Menu menu,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SyId") || _fields[i].equals("Menu.SyId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, menu.getSyId());
					}
					else if(_fields[i].equals("Parent") || _fields[i].equals("Menu.Parent")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, menu.getParent());
					}
					else if(_fields[i].equals("Width") || _fields[i].equals("Menu.Width")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, menu.getWidth());
					}
					else if(_fields[i].equals("Leve") || _fields[i].equals("Menu.Leve")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, menu.getLeve());
					}
					else if(_fields[i].equals("Sort") || _fields[i].equals("Menu.Sort")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, menu.getSort());
					}
					else if(_fields[i].equals("Text") || _fields[i].equals("Menu.Text")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, menu.getText());
					}
					else if(_fields[i].equals("Click") || _fields[i].equals("Menu.Click")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, menu.getClick());
					}
					else if(_fields[i].equals("Icon") || _fields[i].equals("Menu.Icon")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, menu.getIcon());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("MenuHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(Menu menu) {
		String _fields = "";
		if(null != menu.getId())
			_fields += "Menu.Id,";
		if(null != menu.getSyId())
			_fields += "Menu.SyId,";
		if(null != menu.getParent())
			_fields += "Menu.Parent,";
		if(null != menu.getWidth())
			_fields += "Menu.Width,";
		if(null != menu.getLeve())
			_fields += "Menu.Leve,";
		if(null != menu.getSort())
			_fields += "Menu.Sort,";
		if(null != menu.getText())
			_fields += "Menu.Text,";
		if(null != menu.getClick())
			_fields += "Menu.Click,";
		if(null != menu.getIcon())
			_fields += "Menu.Icon,";

		return _fields.substring(0, _fields.length()-1);
	}
}