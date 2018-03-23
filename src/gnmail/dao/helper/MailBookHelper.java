package gnmail.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnmail.vo.MailBook;

public class MailBookHelper extends BasicMailBookHelper {
	public String getSqlString() {
		return " from MailBook " +
               //" inner join MailGroup on (MailGroup.GroupId = MailBook.GroupId) " + 
               
				" inner join Usr on(Usr.id = MailBook.UserId) " +
               	" left join Com on (Usr.ComId = Com.ComId)" +
				" left join Dept on(Usr.DeptId = Dept.DeptId) " +
				" where 1=1 ";
	}

	public List<MailBook> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<MailBook> list = new ArrayList<MailBook>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				MailBook mailBook = new MailBook();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("BookId") || _fields[i].equals("MailBook.BookId"))
						mailBook.setBookId(rs.getInt("BookId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("MailBook.UserId"))
						mailBook.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("GroupId") || _fields[i].equals("MailBook.GroupId"))
						mailBook.setGroupId(rs.getInt("GroupId"));
					else if(_fields[i].equals("ExtPhone") || _fields[i].equals("MailBook.ExtPhone"))
						mailBook.setExtPhone(rs.getString("ExtPhone"));
					else if(_fields[i].equals("Phone") || _fields[i].equals("MailBook.Phone"))
						mailBook.setPhone(rs.getString("Phone"));
					else if(_fields[i].equals("Mobile") || _fields[i].equals("MailBook.Mobile"))
						mailBook.setMobile(rs.getString("Mobile"));
					else if(_fields[i].equals("MailAddr") || _fields[i].equals("MailBook.MailAddr"))
						mailBook.setMailAddr(rs.getString("MailAddr"));
					else if(_fields[i].equals("AddrName") || _fields[i].equals("MailBook.AddrName"))
						mailBook.setAddrName(rs.getString("AddrName"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("MailBook.Remark"))
						mailBook.setRemark(rs.getString("Remark"));
					else if(_fields[i].equals("Status") || _fields[i].equals("MailBook.Status"))
						mailBook.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("MailBook.CreateBy"))
						mailBook.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("MailBook.CreateDate"))
						mailBook.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("MailBook.LastUpd"))
						mailBook.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("MailBook.LastUpdDate"))
						mailBook.setLastUpdDate(rs.getTimestamp("LastUpdDate"));

					else if(_fields[i].equals("GroupId") || _fields[i].equals("MailGroup.GroupId as GroupId"))
						mailBook.setGroupId(rs.getInt("GroupId"));
					
					else if(_fields[i].equals("ComNm") || _fields[i].equals("Com.ComNm"))
						mailBook.setComName(rs.getString("ComNm"));
					else if(_fields[i].equals("DeptNm") || _fields[i].equals("Dept.DeptNm"))
						mailBook.setDeptName(rs.getString("DeptNm"));
					else if(_fields[i].equals("UsrName") || _fields[i].equals("Usr.UsrName"))
						mailBook.setUserName(rs.getString("UsrName"));

				}
				list.add(mailBook);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailBookHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}