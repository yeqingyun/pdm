package gnwf.dao.helper;

import gnwf.vo.AddrBook;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public class AddrBookHelper extends BasicAddrBookHelper {
	public String getSqlString() {
		return " from AddrBook inner join BaCom on (AddrBook.ComId = BaCom.ComId)" +
				" left join BaDept on(AddrBook.DeptId = BaDept.DeptId) " +
				" left join HrEmp on(AddrBook.EmpId = HrEmp.EmpId) " +
				" inner join SyUser on(SyUser.UserId = AddrBook.UserId) " +
				" where 1=1 ";
	}

	public String getOrderBy() {
		return " order by AddrBook.ComId,AddrBook.DeptId,AddrBook.UserId";
	}

	public String getSqlCondition(AddrBook addrBook) {
		String sql = super.getSqlCondition(addrBook);
		
		//HrEmp.EmpName
		if(null != addrBook.getEmpName() && !addrBook.getEmpName().trim().equals(""))
			sql += " and HrEmp.EmpName = '"+addrBook.getEmpName().trim()+"'";

		return sql;
	}

	public List<AddrBook> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<AddrBook> list = new ArrayList<AddrBook>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				AddrBook addrBook = new AddrBook();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("BookId") || _fields[i].equals("AddrBook.BookId"))
						addrBook.setBookId(new Integer(rs.getInt("BookId")));
					else if(_fields[i].equals("ComId") || _fields[i].equals("AddrBook.ComId"))
						addrBook.setComId(new Integer(rs.getInt("ComId")));
					else if(_fields[i].equals("DeptId") || _fields[i].equals("AddrBook.DeptId"))
						addrBook.setDeptId(new Integer(rs.getInt("DeptId")));
					else if(_fields[i].equals("UserId") || _fields[i].equals("AddrBook.UserId"))
						addrBook.setUserId(new Integer(rs.getInt("UserId")));
					else if(_fields[i].equals("EmpId") || _fields[i].equals("AddrBook.EmpId"))
						addrBook.setEmpId(new Integer(rs.getInt("EmpId")));
					else if(_fields[i].equals("Phone") || _fields[i].equals("AddrBook.Phone"))
						addrBook.setPhone(rs.getString("Phone"));
					else if(_fields[i].equals("ExtPhone") || _fields[i].equals("AddrBook.ExtPhone"))
						addrBook.setExtPhone(rs.getString("ExtPhone"));
					else if(_fields[i].equals("Mobile") || _fields[i].equals("AddrBook.Mobile"))
						addrBook.setMobile(rs.getString("Mobile"));
					else if(_fields[i].equals("MailAddr") || _fields[i].equals("AddrBook.MailAddr"))
						addrBook.setMailAddr(rs.getString("MailAddr"));
					else if(_fields[i].equals("AddrName") || _fields[i].equals("AddrBook.AddrName"))
						addrBook.setAddrName(rs.getString("AddrName"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("AddrBook.Remark"))
						addrBook.setRemark(rs.getString("Remark"));
					else if(_fields[i].equals("Status") || _fields[i].equals("AddrBook.Status"))
						addrBook.setStatus(new Integer(rs.getInt("Status")));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("AddrBook.CreateBy"))
						addrBook.setCreateBy(new Integer(rs.getInt("CreateBy")));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("AddrBook.CreateDate"))
						addrBook.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("AddrBook.LastUpd"))
						addrBook.setLastUpd(new Integer(rs.getInt("LastUpd")));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("AddrBook.LastUpdDate"))
						addrBook.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					
					//BaCom.ComName as ComName,BaDept.DeptName as DeptName,HrEmp.EmpName as EmpName
					else if(_fields[i].equals("ComName") || _fields[i].equals("BaCom.ComName as ComName"))
						addrBook.setComName(rs.getString("ComName"));
					else if(_fields[i].equals("DeptName") || _fields[i].equals("BaDept.DeptName as DeptName"))
						addrBook.setDeptName(rs.getString("DeptName"));
					else if(_fields[i].equals("EmpName") || _fields[i].equals("HrEmp.EmpName as EmpName"))
						addrBook.setEmpName(rs.getString("EmpName"));
					
					//SyUser.UserId as UserId
					else if(_fields[i].equals("UserId") || _fields[i].equals("SyUser.UserId as UserId"))
						addrBook.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("UserName") || _fields[i].equals("SyUser.UserName as UserName"))
						addrBook.setUserName(rs.getString("UserName"));
				}
				list.add(addrBook);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("AddrBookHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

}
