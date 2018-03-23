package zrprjt.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrprjt.vo.PrjtAuth;

public class PrjtAuthHelper extends BasicPrjtAuthHelper {
	public String getSqlString() {
		return " from PrjtAuth " +
               " inner join PrjtDef on (PrjtDef.PrjtNo = PrjtAuth.PrjtNo) " + 

               " where 1=1 ";
	}

	public List<PrjtAuth> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<PrjtAuth> list = new ArrayList<PrjtAuth>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				PrjtAuth prjtAuth = new PrjtAuth();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("RoleId") || _fields[i].equals("PrjtAuth.RoleId"))
						prjtAuth.setRoleId(rs.getInt("RoleId"));
					if(_fields[i].equals("IsRead") || _fields[i].equals("PrjtAuth.IsRead"))
						prjtAuth.setIsRead(rs.getInt("IsRead"));
					if(_fields[i].equals("IsLoad") || _fields[i].equals("PrjtAuth.IsLoad"))
						prjtAuth.setIsLoad(rs.getInt("IsLoad"));
					if(_fields[i].equals("Status") || _fields[i].equals("PrjtAuth.Status"))
						prjtAuth.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtAuth.CreateBy"))
						prjtAuth.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtAuth.LastUpd"))
						prjtAuth.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtAuth.CreateDate"))
						prjtAuth.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtAuth.LastDate"))
						prjtAuth.setLastDate(rs.getTimestamp("LastDate"));
					if(_fields[i].equals("PrjtNo") || _fields[i].equals("PrjtAuth.PrjtNo"))
						prjtAuth.setPrjtNo(rs.getString("PrjtNo"));


				}
				list.add(prjtAuth);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtAuthHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}