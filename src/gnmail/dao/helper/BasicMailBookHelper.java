package gnmail.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnmail.vo.MailBook;

public class BasicMailBookHelper implements SqlHelper {
	public String getSqlString() {
		return " from MailBook where 1=1";
	}

	public String getOrderBy() {
		return " order by MailBook.BookId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((MailBook)object);
	}

	public String getSql4Amount(MailBook mailBook) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(mailBook);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((MailBook)object);
	}

	public String getSql4Delete(MailBook mailBook) {
		return "delete from MailBook where 1=1"+getSqlCondition(mailBook);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((MailBook)object,fields);
	}

	public String getSql4All(MailBook mailBook, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(mailBook)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((MailBook)object,pageVO,fields);
	}

	public String getSql4Pages(MailBook mailBook,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" MailBook.BookId "+ getSqlString()+getSqlCondition(mailBook)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(mailBook)+" and MailBook.BookId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((MailBook)object);
	}

	public String getSqlCondition(MailBook mailBook) {
		String sql = "";
		if(null != mailBook.getBookId())
			sql += " and MailBook.BookId = '"+mailBook.getBookId()+"'";
		if(null != mailBook.getUserId())
			sql += " and MailBook.UserId = '"+mailBook.getUserId()+"'";
		if(null != mailBook.getGroupId())
			sql += " and MailBook.GroupId = '"+mailBook.getGroupId()+"'";
		if(null != mailBook.getExtPhone() && !mailBook.getExtPhone().trim().equals(""))
			sql += " and MailBook.ExtPhone = '"+mailBook.getExtPhone().trim()+"'";
		if(null != mailBook.getPhone() && !mailBook.getPhone().trim().equals(""))
			sql += " and MailBook.Phone = '"+mailBook.getPhone().trim()+"'";
		if(null != mailBook.getMobile() && !mailBook.getMobile().trim().equals(""))
			sql += " and MailBook.Mobile = '"+mailBook.getMobile().trim()+"'";
		if(null != mailBook.getMailAddr() && !mailBook.getMailAddr().trim().equals(""))
			sql += " and MailBook.MailAddr = '"+mailBook.getMailAddr().trim()+"'";
		if(null != mailBook.getAddrName() && !mailBook.getAddrName().trim().equals(""))
			sql += " and MailBook.AddrName = '"+mailBook.getAddrName().trim()+"'";
		if(null != mailBook.getRemark() && !mailBook.getRemark().trim().equals(""))
			sql += " and MailBook.Remark = '"+mailBook.getRemark().trim()+"'";
		if(null != mailBook.getStatus())
			sql += " and MailBook.Status = '"+mailBook.getStatus()+"'";
		if(null != mailBook.getCreateBy())
			sql += " and MailBook.CreateBy = '"+mailBook.getCreateBy()+"'";
		if(null != mailBook.getStartCreateDate()) 
			sql += " and MailBook.CreateDate >= '"+GenericUtil.dateFormat(mailBook.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != mailBook.getEndCreateDate()) 
			sql += " and MailBook.CreateDate <= '"+GenericUtil.dateFormat(mailBook.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != mailBook.getLastUpd())
			sql += " and MailBook.LastUpd = '"+mailBook.getLastUpd()+"'";
		if(null != mailBook.getStartLastUpdDate()) 
			sql += " and MailBook.LastUpdDate >= '"+GenericUtil.dateFormat(mailBook.getStartLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != mailBook.getEndLastUpdDate()) 
			sql += " and MailBook.LastUpdDate <= '"+GenericUtil.dateFormat(mailBook.getEndLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";

		return sql;
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

	public String getInsertSql(String fields) {
		String sql = "insert into MailBook("+fields.replaceAll("MailBook\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(MailBook mailBook,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("BookId") || _fields[i].equals("MailBook.BookId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailBook.getBookId());
					}
					else if(_fields[i].equals("UserId") || _fields[i].equals("MailBook.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailBook.getUserId());
					}
					else if(_fields[i].equals("GroupId") || _fields[i].equals("MailBook.GroupId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailBook.getGroupId());
					}
					else if(_fields[i].equals("ExtPhone") || _fields[i].equals("MailBook.ExtPhone")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailBook.getExtPhone());
					}
					else if(_fields[i].equals("Phone") || _fields[i].equals("MailBook.Phone")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailBook.getPhone());
					}
					else if(_fields[i].equals("Mobile") || _fields[i].equals("MailBook.Mobile")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailBook.getMobile());
					}
					else if(_fields[i].equals("MailAddr") || _fields[i].equals("MailBook.MailAddr")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailBook.getMailAddr());
					}
					else if(_fields[i].equals("AddrName") || _fields[i].equals("MailBook.AddrName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailBook.getAddrName());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("MailBook.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailBook.getRemark());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("MailBook.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailBook.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("MailBook.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailBook.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("MailBook.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mailBook.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("MailBook.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailBook.getLastUpd());
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("MailBook.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mailBook.getLastUpdDate().getTime()));
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("MailBookHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update MailBook set ";
		String[] _fields = fields.replaceAll("MailBook\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("UserId") || _fields[i].equals("MailBook.UserId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("GroupId") || _fields[i].equals("MailBook.GroupId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ExtPhone") || _fields[i].equals("MailBook.ExtPhone"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Phone") || _fields[i].equals("MailBook.Phone"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Mobile") || _fields[i].equals("MailBook.Mobile"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MailAddr") || _fields[i].equals("MailBook.MailAddr"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("AddrName") || _fields[i].equals("MailBook.AddrName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("MailBook.Remark"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("MailBook.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("MailBook.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("MailBook.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("MailBook.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("MailBook.LastUpdDate"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(MailBook mailBook,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("UserId") || _fields[i].equals("MailBook.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailBook.getUserId());
					}
					else if(_fields[i].equals("GroupId") || _fields[i].equals("MailBook.GroupId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailBook.getGroupId());
					}
					else if(_fields[i].equals("ExtPhone") || _fields[i].equals("MailBook.ExtPhone")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailBook.getExtPhone());
					}
					else if(_fields[i].equals("Phone") || _fields[i].equals("MailBook.Phone")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailBook.getPhone());
					}
					else if(_fields[i].equals("Mobile") || _fields[i].equals("MailBook.Mobile")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailBook.getMobile());
					}
					else if(_fields[i].equals("MailAddr") || _fields[i].equals("MailBook.MailAddr")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailBook.getMailAddr());
					}
					else if(_fields[i].equals("AddrName") || _fields[i].equals("MailBook.AddrName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailBook.getAddrName());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("MailBook.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailBook.getRemark());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("MailBook.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailBook.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("MailBook.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailBook.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("MailBook.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mailBook.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("MailBook.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailBook.getLastUpd());
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("MailBook.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mailBook.getLastUpdDate().getTime()));
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("MailBookHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(MailBook mailBook) {
		String _fields = "";
		if(null != mailBook.getBookId())
			_fields += "MailBook.BookId,";
		if(null != mailBook.getUserId())
			_fields += "MailBook.UserId,";
		if(null != mailBook.getGroupId())
			_fields += "MailBook.GroupId,";
		if(null != mailBook.getExtPhone())
			_fields += "MailBook.ExtPhone,";
		if(null != mailBook.getPhone())
			_fields += "MailBook.Phone,";
		if(null != mailBook.getMobile())
			_fields += "MailBook.Mobile,";
		if(null != mailBook.getMailAddr())
			_fields += "MailBook.MailAddr,";
		if(null != mailBook.getAddrName())
			_fields += "MailBook.AddrName,";
		if(null != mailBook.getRemark())
			_fields += "MailBook.Remark,";
		if(null != mailBook.getStatus())
			_fields += "MailBook.Status,";
		if(null != mailBook.getCreateBy())
			_fields += "MailBook.CreateBy,";
		if(null != mailBook.getCreateDate())
			_fields += "MailBook.CreateDate,";
		if(null != mailBook.getLastUpd())
			_fields += "MailBook.LastUpd,";
		if(null != mailBook.getLastUpdDate())
			_fields += "MailBook.LastUpdDate,";

		return _fields.substring(0, _fields.length()-1);
	}
}