package zrprjt.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrprjt.vo.PrjtTyp;

public class PrjtTypHelper extends BasicPrjtTypHelper {
	public String getSqlString() {
		return " from PrjtTyp " +

               " where 1=1 ";
	}

	public List<PrjtTyp> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<PrjtTyp> list = new ArrayList<PrjtTyp>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				PrjtTyp prjtTyp = new PrjtTyp();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("TypId") || _fields[i].equals("PrjtTyp.TypId"))
						prjtTyp.setTypId(rs.getInt("TypId"));
					if(_fields[i].equals("Status") || _fields[i].equals("PrjtTyp.Status"))
						prjtTyp.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtTyp.CreateBy"))
						prjtTyp.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtTyp.LastUpd"))
						prjtTyp.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtTyp.CreateDate"))
						prjtTyp.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtTyp.LastDate"))
						prjtTyp.setLastDate(rs.getTimestamp("LastDate"));
					if(_fields[i].equals("TypNm") || _fields[i].equals("PrjtTyp.TypNm"))
						prjtTyp.setTypNm(rs.getString("TypNm"));
					if(_fields[i].equals("Remark") || _fields[i].equals("PrjtTyp.Remark"))
						prjtTyp.setRemark(rs.getString("Remark"));


				}
				list.add(prjtTyp);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtTypHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}