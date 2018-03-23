package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.GpMenu;

public class BasicGpMenuHelper implements SqlHelper {
	public String getSqlString() {
		return " from GpMenu where 1=1";
	}

	public String getOrderBy() {
		return " order by GpMenu.GpId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((GpMenu)object);
	}

	public String getSql4Amount(GpMenu gpMenu) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(gpMenu);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((GpMenu)object);
	}

	public String getSql4Delete(GpMenu gpMenu) {
		return "delete from GpMenu where 1=1"+getSqlCondition(gpMenu);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((GpMenu)object,fields);
	}

	public String getSql4All(GpMenu gpMenu, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(gpMenu)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((GpMenu)object,pageVO,fields);
	}

	public String getSql4Pages(GpMenu gpMenu,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" GpMenu.GpId "+ getSqlString()+getSqlCondition(gpMenu)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(gpMenu)+" and GpMenu.GpId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((GpMenu)object);
	}

	public String getSqlCondition(GpMenu gpMenu) {
		String sql = "";
		if(null != gpMenu.getGpId())
			sql += " and GpMenu.GpId = '"+gpMenu.getGpId()+"'";
		if(null != gpMenu.getMenuId())
			sql += " and GpMenu.MenuId = '"+gpMenu.getMenuId()+"'";

		return sql;
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

	public String getInsertSql(String fields) {
		String sql = "insert into GpMenu("+fields.replaceAll("GpMenu\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(GpMenu gpMenu,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("GpId") || _fields[i].equals("GpMenu.GpId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, gpMenu.getGpId());
					}
					else if(_fields[i].equals("MenuId") || _fields[i].equals("GpMenu.MenuId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, gpMenu.getMenuId());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("GpMenuHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update GpMenu set ";
		String[] _fields = fields.replaceAll("GpMenu\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(GpMenu gpMenu,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("GpMenuHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(GpMenu gpMenu) {
		String _fields = "";
		if(null != gpMenu.getGpId())
			_fields += "GpMenu.GpId,";
		if(null != gpMenu.getMenuId())
			_fields += "GpMenu.MenuId,";

		return _fields.substring(0, _fields.length()-1);
	}
}