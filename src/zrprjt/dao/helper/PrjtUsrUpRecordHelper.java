package zrprjt.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import zrprjt.vo.PrjtUsrUpRecord;

public class PrjtUsrUpRecordHelper extends BasicPrjtUsrUpRecordHelper {
	public String getSqlString() {
		return " from PrjtUsrUpRecord " +
               " inner join PrjtUsr on (PrjtUsrUpRecord.PrjtUsrId = PrjtUsr.Id) " + 
               " inner join PrjtRole on (PrjtRole.RoleId = PrjtUsr.RoleId) " + 
               " inner join Usr on (Usr.Id = PrjtUsrUpRecord.UsrId) " + 
               " where 1=1 ";
	}

	//PrjtRole.RoleNm as RoleNm,Usr.UsrName as UsrName,PrjtUsr.Priority as Priority
	
	public List<PrjtUsrUpRecord> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<PrjtUsrUpRecord> list = new ArrayList<PrjtUsrUpRecord>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				PrjtUsrUpRecord PrjtUsrUpRecord = new PrjtUsrUpRecord();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("PrjtUsrUpRecord.Id"))
						PrjtUsrUpRecord.setId(rs.getInt("Id"));
					if(_fields[i].equals("PrjtUsrId") || _fields[i].equals("PrjtUsrUpRecord.PrjtUsrId"))
						PrjtUsrUpRecord.setPrjtUsrId(rs.getInt("PrjtUsrId"));
					if(_fields[i].equals("UsrId") || _fields[i].equals("PrjtUsrUpRecord.UsrId"))
						PrjtUsrUpRecord.setUsrId(rs.getInt("UsrId"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtUsrUpRecord.CreateBy"))
						PrjtUsrUpRecord.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtUsrUpRecord.CreateDate"))
						PrjtUsrUpRecord.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("UpTyp") || _fields[i].equals("PrjtUsrUpRecord.UpTyp"))
						PrjtUsrUpRecord.setUpTyp(rs.getInt("UpTyp"));
				
					
					//PrjtUsrUpRecord.CreateDate,PrjtUsrUpRecord.UpTyp,PrjtRole.RoleNm as RoleNm,Usr.UsrName as UsrName,PrjtUsr.Priority as Priority";

					if(_fields[i].equals("RoleNm") || _fields[i].equals("PrjtRole.RoleNm as RoleNm"))
						PrjtUsrUpRecord.setRoleNm(rs.getString("RoleNm")); 
					if(_fields[i].equals("UsrName") || _fields[i].equals("Usr.UsrName as UsrName"))
						PrjtUsrUpRecord.setUsrName(rs.getString("UsrName"));
					if(_fields[i].equals("Priority") || _fields[i].equals("PrjtUsr.Priority as Priority"))
						PrjtUsrUpRecord.setPriority(rs.getInt("Priority"));

				}
				list.add(PrjtUsrUpRecord);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtUsrUpRecordHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}