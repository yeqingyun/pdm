package zrsy.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrsy.vo.SyDef;

public class SyDefHelper extends BasicSyDefHelper {
	public String getSqlString() {
		return " from SyDef " +

               " where SyId > 1 ";
	}

	public List<SyDef> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<SyDef> list = new ArrayList<SyDef>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				SyDef syDef = new SyDef();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SyId") || _fields[i].equals("SyDef.SyId"))
						syDef.setSyId(rs.getInt("SyId"));
					if(_fields[i].equals("SyName") || _fields[i].equals("SyDef.SyName"))
						syDef.setSyName(rs.getString("SyName"));
					if(_fields[i].equals("SyText") || _fields[i].equals("SyDef.SyText"))
						syDef.setSyText(rs.getString("SyText"));


				}
				list.add(syDef);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SyDefHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}