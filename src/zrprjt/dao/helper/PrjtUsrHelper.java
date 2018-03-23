package zrprjt.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import zrprjt.vo.Dept;
import zrprjt.vo.PrjtUsr;

public class PrjtUsrHelper extends BasicPrjtUsrHelper {
	public String getSqlString() {
		return " from PrjtUsr " +
               " left join PrjtDef on (PrjtDef.PrjtNo = PrjtUsr.PrjtNo) " + 
               " inner join PrjtRole on (PrjtRole.RoleId = PrjtUsr.RoleId) " + 
               " inner join Usr on (Usr.Id = PrjtUsr.UsrId) " + 
               "INNER JOIN Dept ON (Usr.OrgNo = Dept.deptid)"+
               " left join PrjtTyp on (PrjtTyp.TypId = PrjtUsr.PrjtTypId) " + 
               //" inner join PrjtDef on (PrjtDef.PrjtNo = PrjtUsr.PrjtNo) " + 
               " where 1=1 and PrjtRole.RoleTyp = 0";
	}
//PrjtRole.RoleNm as RoleNm
	public List<PrjtUsr> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<PrjtUsr> list = new ArrayList<PrjtUsr>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				PrjtUsr prjtUsr = new PrjtUsr();
				
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("PrjtUsr.Id"))
						prjtUsr.setId(rs.getInt("Id"));
					if(_fields[i].equals("RoleId") || _fields[i].equals("PrjtUsr.RoleId"))
						prjtUsr.setRoleId(rs.getInt("RoleId"));
					if(_fields[i].equals("UsrId") || _fields[i].equals("PrjtUsr.UsrId"))
						prjtUsr.setUsrId(rs.getInt("UsrId"));
					if(_fields[i].equals("Status") || _fields[i].equals("PrjtUsr.Status"))
						prjtUsr.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtUsr.CreateBy"))
						prjtUsr.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtUsr.LastUpd"))
						prjtUsr.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtUsr.CreateDate"))
						prjtUsr.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtUsr.LastDate"))
						prjtUsr.setLastDate(rs.getTimestamp("LastDate"));
					if(_fields[i].equals("PrjtNo") || _fields[i].equals("PrjtUsr.PrjtNo"))
						prjtUsr.setPrjtNo(rs.getString("PrjtNo"));

					
					if(_fields[i].equals("RoleNm") || _fields[i].equals("PrjtRole.RoleNm as RoleNm"))
						prjtUsr.setRoleNm(rs.getString("RoleNm"));
					if(_fields[i].equals("UsrName") || _fields[i].equals("Usr.UsrName as UsrName"))
						prjtUsr.setUsrName(rs.getString("UsrName"));
					
					
					if(_fields[i].equals("DeptNm") || _fields[i].equals("Dept.DeptNm as DeptNm"))
						prjtUsr.setDeptNm(rs.getString("DeptNm"));
//					if(_fields[i].equals("PrjtNm") || _fields[i].equals("PrjtDef as PrjtNm"))
//						prjtUsr.setPrjtNm(rs.getString("PrjtNm"));
					
					if(_fields[i].equals("Priority") || _fields[i].equals("PrjtUsr.Priority"))
						prjtUsr.setPriority(rs.getInt("Priority"));
					
					if(_fields[i].equals("PrjtTypId") || _fields[i].equals("PrjtUsr.PrjtTypId"))
						prjtUsr.setPrjtTypId(rs.getInt("PrjtTypId"));
					if(_fields[i].equals("RoleTyp") || _fields[i].equals("PrjtRole.RoleTyp as RoleTyp"))
						prjtUsr.setRoleTyp(rs.getInt("RoleTyp"));
					
					if(_fields[i].equals("PrjtTypNm") || _fields[i].equals("PrjtTyp.TypNm as PrjtTypNm"))
						prjtUsr.setPrjtTypNm(rs.getString("PrjtTypNm"));
					
					if(_fields[i].equals("StepId") || _fields[i].equals("A.StepId"))
						prjtUsr.setStepId(rs.getInt("StepId"));

					//_AddrBook.Mobile as Mobile,_AddrBook.MailAddr as MailAddr
					if(_fields[i].equals("Mobile") || _fields[i].equals("_AddrBook.Mobile as Mobile"))
						prjtUsr.setMobile(rs.getString("Mobile"));
					if(_fields[i].equals("MailAddr") || _fields[i].equals("_AddrBook.MailAddr as MailAddr"))
						prjtUsr.setMailAddr(rs.getString("MailAddr"));
				}
				list.add(prjtUsr);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtUsrHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}