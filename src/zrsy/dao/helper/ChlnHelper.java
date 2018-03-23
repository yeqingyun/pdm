package zrsy.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrsy.vo.Chln;

public class ChlnHelper extends BasicChlnHelper {
	public String getSqlString() {
		return " from Chln " +

               " where 1=1 ";
	}

	public List<Chln> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<Chln> list = new ArrayList<Chln>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				Chln chln = new Chln();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Year") || _fields[i].equals("Chln.Year"))
						chln.setYear(rs.getInt("Year"));
					else if(_fields[i].equals("Month") || _fields[i].equals("Chln.Month"))
						chln.setMonth(rs.getInt("Month"));
					else if(_fields[i].equals("Day") || _fields[i].equals("Chln.Day"))
						chln.setDay(rs.getInt("Day"));
					else if(_fields[i].equals("ChlnNo") || _fields[i].equals("Chln.ChlnNo"))
						chln.setChlnNo(rs.getInt("ChlnNo"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("Chln.CreateBy"))
						chln.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("Chln.LastUpd"))
						chln.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("Chln.CreateDate"))
						chln.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastDate") || _fields[i].equals("Chln.LastDate"))
						chln.setLastDate(rs.getTimestamp("LastDate"));
					else if(_fields[i].equals("ChlnTyp") || _fields[i].equals("Chln.ChlnTyp"))
						chln.setChlnTyp(rs.getString("ChlnTyp"));


				}
				list.add(chln);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ChlnHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}