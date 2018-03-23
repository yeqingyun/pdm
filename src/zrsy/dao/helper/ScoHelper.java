package zrsy.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrsy.vo.Sco;

public class ScoHelper extends BasicScoHelper {
	public String getSqlString() {
		return " from [ZrSy].[dbo].[Sco] " +

               " where 1=1 ";
	}

	public List<Sco> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<Sco> list = new ArrayList<Sco>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				Sco sco = new Sco();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CreateDate") || _fields[i].equals("Sco.CreateDate"))
						sco.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastDate") || _fields[i].equals("Sco.LastDate"))
						sco.setLastDate(rs.getTimestamp("LastDate"));
					else if(_fields[i].equals("ScoId") || _fields[i].equals("Sco.ScoId"))
						sco.setScoId(rs.getInt("ScoId"));
					else if(_fields[i].equals("Scope") || _fields[i].equals("Sco.Scope"))
						sco.setScope(rs.getInt("Scope"));
					else if(_fields[i].equals("Status") || _fields[i].equals("Sco.Status"))
						sco.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("Sco.CreateBy"))
						sco.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("Sco.LastUpd"))
						sco.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("ScoNm") || _fields[i].equals("Sco.ScoNm"))
						sco.setScoNm(rs.getString("ScoNm"));


				}
				list.add(sco);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ScoHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}