package zrprjt.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrprjt.vo.PrjtRole;

public class PrjtRoleHelper extends BasicPrjtRoleHelper {
	public String getSqlString() {
		return " from PrjtRole " +
               " inner join PrjtTyp on (PrjtTyp.TypId = PrjtRole.PrjtTypId) " + 

               " where 1=1 and PrjtRole.Status = 1 ";
	}

	public List<PrjtRole> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<PrjtRole> list = new ArrayList<PrjtRole>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				PrjtRole prjtRole = new PrjtRole();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("RoleId") || _fields[i].equals("PrjtRole.RoleId"))
						prjtRole.setRoleId(rs.getInt("RoleId"));
					if(_fields[i].equals("Status") || _fields[i].equals("PrjtRole.Status"))
						prjtRole.setStatus(rs.getInt("Status"));
					
					if(_fields[i].equals("IsRead") || _fields[i].equals("PrjtRole.IsRead"))
						prjtRole.setIsRead(rs.getInt("IsRead"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtRole.CreateBy"))
						prjtRole.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtRole.LastUpd"))
						prjtRole.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtRole.CreateDate"))
						prjtRole.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtRole.LastDate"))
						prjtRole.setLastDate(rs.getTimestamp("LastDate"));
					if(_fields[i].equals("PrjtTypId") || _fields[i].equals("PrjtRole.PrjtTypId"))
						prjtRole.setPrjtTypId(rs.getInt("PrjtTypId"));
					if(_fields[i].equals("RoleNm") || _fields[i].equals("PrjtRole.RoleNm"))
						prjtRole.setRoleNm(rs.getString("RoleNm"));
					if(_fields[i].equals("Remark") || _fields[i].equals("PrjtRole.Remark"))
						prjtRole.setRemark(rs.getString("Remark"));

					
					if(_fields[i].equals("TypNm") || _fields[i].equals("PrjtTyp.TypNm as TypNm"))
						prjtRole.setTypNm(rs.getString("TypNm"));
					
					if(_fields[i].equals("RoleTyp") || _fields[i].equals("PrjtRole.RoleTyp"))
						prjtRole.setRoleTyp(rs.getInt("RoleTyp"));
					if(_fields[i].equals("Sort") || _fields[i].equals("PrjtRole.Sort"))
						prjtRole.setSort(rs.getInt("Sort"));

				}
				list.add(prjtRole);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtRoleHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}