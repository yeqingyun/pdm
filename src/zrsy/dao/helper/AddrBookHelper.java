package zrsy.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrsy.vo.AddrBook;

public class AddrBookHelper extends BasicAddrBookHelper {
	public String getSqlString() {
		return " from AddrBook " +
               " inner join UsrView Usr on (Usr.Id = AddrBook.UserId) " + 
               " left join Com on (Com.ComId = AddrBook.ComId)" +
               " left join Dept on (Dept.DeptId = AddrBook.DeptId)" +
               " where 1=1 ";
	}

	public List<AddrBook> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<AddrBook> list = new ArrayList<AddrBook>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				AddrBook addrBook = new AddrBook();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("BookId") || _fields[i].equals("AddrBook.BookId"))
						addrBook.setBookId(rs.getInt("BookId"));
					else if(_fields[i].equals("ComId") || _fields[i].equals("AddrBook.ComId"))
						addrBook.setComId(rs.getInt("ComId"));
					else if(_fields[i].equals("DeptId") || _fields[i].equals("AddrBook.DeptId"))
						addrBook.setDeptId(rs.getInt("DeptId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("AddrBook.UserId"))
						addrBook.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("EmpId") || _fields[i].equals("AddrBook.EmpId"))
						addrBook.setEmpId(rs.getInt("EmpId"));
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
						addrBook.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("AddrBook.CreateBy"))
						addrBook.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("AddrBook.CreateDate"))
						addrBook.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("AddrBook.LastUpd"))
						addrBook.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("LastDate") || _fields[i].equals("AddrBook.LastDate"))
						addrBook.setLastDate(rs.getTimestamp("LastDate"));

					else if(_fields[i].equals("Id") || _fields[i].equals("Usr.Id as Id"))
						addrBook.setId(rs.getInt("Id"));

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