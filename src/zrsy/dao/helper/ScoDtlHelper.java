package zrsy.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrsy.vo.ScoDtl;

public class ScoDtlHelper extends BasicScoDtlHelper {
	public String getSqlString() {
		return " from ScoDtl " +
               " inner join Sco on (Sco.ScoId = ScoDtl.ScoId) " + 

               " where 1=1 ";
	}

	public List<ScoDtl> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<ScoDtl> list = new ArrayList<ScoDtl>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				ScoDtl scoDtl = new ScoDtl();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ScoId") || _fields[i].equals("ScoDtl.ScoId"))
						scoDtl.setScoId(rs.getInt("ScoId"));
					else if(_fields[i].equals("ComId") || _fields[i].equals("ScoDtl.ComId"))
						scoDtl.setComId(rs.getInt("ComId"));
					else if(_fields[i].equals("DetpId") || _fields[i].equals("ScoDtl.DetpId"))
						scoDtl.setDetpId(rs.getInt("DetpId"));
					else if(_fields[i].equals("UsrId") || _fields[i].equals("ScoDtl.UsrId"))
						scoDtl.setUsrId(rs.getInt("UsrId"));

					else if(_fields[i].equals("ScoId") || _fields[i].equals("Sco.ScoId as ScoId"))
						scoDtl.setScoId(rs.getInt("ScoId"));

				}
				list.add(scoDtl);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ScoDtlHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}