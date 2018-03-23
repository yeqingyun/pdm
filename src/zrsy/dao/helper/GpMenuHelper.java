package zrsy.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrsy.vo.GpMenu;

public class GpMenuHelper extends BasicGpMenuHelper {
	public String getSqlString() {
		return " from GpMenu " +
               " inner join Gp on (Gp.GpId = GpMenu.GpId) " + 
               " inner join Menu on (Menu.Id = GpMenu.MenuId) " + 

               " where 1=1 ";
	}

	public List<GpMenu> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<GpMenu> list = new ArrayList<GpMenu>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				GpMenu gpMenu = new GpMenu();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("GpId") || _fields[i].equals("GpMenu.GpId"))
						gpMenu.setGpId(rs.getInt("GpId"));
					if(_fields[i].equals("MenuId") || _fields[i].equals("GpMenu.MenuId"))
						gpMenu.setMenuId(rs.getInt("MenuId"));

					if(_fields[i].equals("GpName") || _fields[i].equals("Gp.GpName as GpName"))
						gpMenu.setGpName(rs.getString("GpName"));
					if(_fields[i].equals("MenuNm") || _fields[i].equals("Menu.Text as MenuNm"))
						gpMenu.setMenuNm(rs.getString("MenuNm"));

				}
				list.add(gpMenu);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpMenuHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}