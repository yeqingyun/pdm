package zrsy.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrsy.vo.GpSy;

public class GpSyHelper extends BasicGpSyHelper {
	public String getSqlString() {
		return " from GpSy " +

               " where 1=1 ";
	}

	public List<GpSy> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<GpSy> list = new ArrayList<GpSy>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				GpSy gpSy = new GpSy();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("GpId") || _fields[i].equals("GpSy.GpId"))
						gpSy.setGpId(rs.getInt("GpId"));
					else if(_fields[i].equals("SyId") || _fields[i].equals("GpSy.SyId"))
						gpSy.setSyId(rs.getInt("SyId"));


				}
				list.add(gpSy);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpSyHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}