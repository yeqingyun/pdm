package zrsy.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrsy.vo.GpUsr;

public class GpUsrHelper extends BasicGpUsrHelper {
	public String getSqlString() {
		return " from GpUsr " +
               " inner join Gp on (Gp.GpId = GpUsr.GpId) " + 
               " inner join Usr on (Usr.Id = GpUsr.UsrId) " + 

               " where 1=1 ";
	}

	public List<GpUsr> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<GpUsr> list = new ArrayList<GpUsr>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				GpUsr gpUsr = new GpUsr();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("GpId") || _fields[i].equals("GpUsr.GpId"))
						gpUsr.setGpId(rs.getInt("GpId"));
					if(_fields[i].equals("UsrId") || _fields[i].equals("GpUsr.UsrId"))
						gpUsr.setUsrId(rs.getInt("UsrId"));

					if(_fields[i].equals("GpName") || _fields[i].equals("Gp.GpName as GpName"))
						gpUsr.setGpName(rs.getString("GpName"));
					if(_fields[i].equals("UsrName") || _fields[i].equals("Usr.UsrName as UsrName"))
						gpUsr.setUsrName(rs.getString("UsrName"));

				}
				list.add(gpUsr);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpUsrHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}