package zrsy.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrsy.vo.Gp;

public class GpHelper extends BasicGpHelper {
	public String getSqlString() {
		return " from Gp " +
               " left join SyDef on (SyDef.SyId = Gp.SyId) " + 
               " where 1=1 ";
               //" where 1=1 and GpName <> '超级用户' ";
	}

	public List<Gp> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<Gp> list = new ArrayList<Gp>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				Gp gp = new Gp();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("GpId") || _fields[i].equals("Gp.GpId"))
						gp.setGpId(rs.getInt("GpId"));
					if(_fields[i].equals("SyId") || _fields[i].equals("Gp.SyId"))
						gp.setSyId(rs.getInt("SyId"));
					if(_fields[i].equals("GpName") || _fields[i].equals("Gp.GpName"))
						gp.setGpName(rs.getString("GpName"));
					if(_fields[i].equals("Remark") || _fields[i].equals("Gp.Remark"))
						gp.setRemark(rs.getString("Remark"));

					if(_fields[i].equals("SyName") || _fields[i].equals("SyDef.SyName as SyName"))
						gp.setSyName(rs.getString("SyName"));

				}
				list.add(gp);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}