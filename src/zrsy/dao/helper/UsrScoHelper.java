package zrsy.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrsy.vo.UsrSco;

public class UsrScoHelper extends BasicUsrScoHelper {
	public String getSqlString() {
		return " from UsrSco " +

               " where 1=1 ";
	}

	public List<UsrSco> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<UsrSco> list = new ArrayList<UsrSco>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				UsrSco usrSco = new UsrSco();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("UsrId") || _fields[i].equals("UsrSco.UsrId"))
						usrSco.setUsrId(rs.getInt("UsrId"));
					else if(_fields[i].equals("ScoId") || _fields[i].equals("UsrSco.ScoId"))
						usrSco.setScoId(rs.getInt("ScoId"));


				}
				list.add(usrSco);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("UsrScoHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}