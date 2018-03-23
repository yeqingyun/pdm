package zrsy.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrsy.vo.GpSco;

public class GpScoHelper extends BasicGpScoHelper {
	public String getSqlString() {
		return " from GpSco " +
               " inner join Gp on (Gp.GpId = GpSco.GpId) " + 
               " inner join Sco on (Sco.ScoId = GpSco.ScoId) " + 

               " where 1=1 ";
	}

	public List<GpSco> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<GpSco> list = new ArrayList<GpSco>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				GpSco gpSco = new GpSco();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("GpId") || _fields[i].equals("GpSco.GpId"))
						gpSco.setGpId(rs.getInt("GpId"));
					else if(_fields[i].equals("ScoId") || _fields[i].equals("GpSco.ScoId"))
						gpSco.setScoId(rs.getInt("ScoId"));

					else if(_fields[i].equals("GpId") || _fields[i].equals("Gp.GpId as GpId"))
						gpSco.setGpId(rs.getInt("GpId"));
					else if(_fields[i].equals("ScoId") || _fields[i].equals("Sco.ScoId as ScoId"))
						gpSco.setScoId(rs.getInt("ScoId"));

				}
				list.add(gpSco);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpScoHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}