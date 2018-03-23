package zrsy.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrsy.vo.Menu;

public class MenuHelper extends BasicMenuHelper {
	public String getSqlString() {
		return " from Menu " +
               " inner join SyDef on (SyDef.SyId = Menu.SyId) " + 

               " where 1=1 ";
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

					if(_fields[i].equals("SyName") || _fields[i].equals("SyDef.SyName as SyName"))
						menu.setSyName(rs.getString("SyName"));

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
}