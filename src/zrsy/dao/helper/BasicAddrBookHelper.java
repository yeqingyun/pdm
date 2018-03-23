package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.AddrBook;

public class BasicAddrBookHelper implements SqlHelper {
	public String getSqlString() {
		return " from AddrBook where 1=1";
	}

	public String getOrderBy() {
		return " order by AddrBook.BookId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((AddrBook)object);
	}

	public String getSql4Amount(AddrBook addrBook) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(addrBook);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((AddrBook)object);
	}

	public String getSql4Delete(AddrBook addrBook) {
		return "delete from AddrBook where 1=1"+getSqlCondition(addrBook);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((AddrBook)object,fields);
	}

	public String getSql4All(AddrBook addrBook, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(addrBook)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((AddrBook)object,pageVO,fields);
	}

	public String getSql4Pages(AddrBook addrBook,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" AddrBook.BookId "+ getSqlString()+getSqlCondition(addrBook)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(addrBook)+" and AddrBook.BookId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((AddrBook)object);
	}

	public String getSqlCondition(AddrBook addrBook) {
		String sql = "";
		if(null != addrBook.getBookId())
			sql += " and AddrBook.BookId = '"+addrBook.getBookId()+"'";
		if(null != addrBook.getComId())
			sql += " and AddrBook.ComId = '"+addrBook.getComId()+"'";
		if(null != addrBook.getDeptId())
			sql += " and AddrBook.DeptId = '"+addrBook.getDeptId()+"'";
		if(null != addrBook.getUserId())
			sql += " and AddrBook.UserId = '"+addrBook.getUserId()+"'";
		if(null != addrBook.getEmpId())
			sql += " and AddrBook.EmpId = '"+addrBook.getEmpId()+"'";
		if(null != addrBook.getPhone() && !addrBook.getPhone().trim().equals(""))
			sql += " and AddrBook.Phone = '"+addrBook.getPhone().trim()+"'";
		if(null != addrBook.getExtPhone() && !addrBook.getExtPhone().trim().equals(""))
			sql += " and AddrBook.ExtPhone = '"+addrBook.getExtPhone().trim()+"'";
		if(null != addrBook.getMobile() && !addrBook.getMobile().trim().equals(""))
			sql += " and AddrBook.Mobile = '"+addrBook.getMobile().trim()+"'";
		if(null != addrBook.getMailAddr() && !addrBook.getMailAddr().trim().equals(""))
			sql += " and AddrBook.MailAddr = '"+addrBook.getMailAddr().trim()+"'";
		if(null != addrBook.getAddrName() && !addrBook.getAddrName().trim().equals(""))
			sql += " and AddrBook.AddrName = '"+addrBook.getAddrName().trim()+"'";
		if(null != addrBook.getRemark() && !addrBook.getRemark().trim().equals(""))
			sql += " and AddrBook.Remark = '"+addrBook.getRemark().trim()+"'";
		if(null != addrBook.getStatus())
			sql += " and AddrBook.Status = '"+addrBook.getStatus()+"'";
		if(null != addrBook.getCreateBy())
			sql += " and AddrBook.CreateBy = '"+addrBook.getCreateBy()+"'";
		if(null != addrBook.getStartCreateDate()) 
			sql += " and AddrBook.CreateDate >= '"+GenericUtil.dateFormat(addrBook.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != addrBook.getEndCreateDate()) 
			sql += " and AddrBook.CreateDate <= '"+GenericUtil.dateFormat(addrBook.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != addrBook.getLastUpd())
			sql += " and AddrBook.LastUpd = '"+addrBook.getLastUpd()+"'";
		if(null != addrBook.getStartLastDate()) 
			sql += " and AddrBook.LastDate >= '"+GenericUtil.dateFormat(addrBook.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != addrBook.getEndLastDate()) 
			sql += " and AddrBook.LastDate <= '"+GenericUtil.dateFormat(addrBook.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";

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

	public String getInsertSql(String fields) {
		String sql = "insert into AddrBook("+fields.replaceAll("AddrBook\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(AddrBook addrBook,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("BookId") || _fields[i].equals("AddrBook.BookId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, addrBook.getBookId());
					}
					else if(_fields[i].equals("ComId") || _fields[i].equals("AddrBook.ComId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, addrBook.getComId());
					}
					else if(_fields[i].equals("DeptId") || _fields[i].equals("AddrBook.DeptId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, addrBook.getDeptId());
					}
					else if(_fields[i].equals("UserId") || _fields[i].equals("AddrBook.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, addrBook.getUserId());
					}
					else if(_fields[i].equals("EmpId") || _fields[i].equals("AddrBook.EmpId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, addrBook.getEmpId());
					}
					else if(_fields[i].equals("Phone") || _fields[i].equals("AddrBook.Phone")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, addrBook.getPhone());
					}
					else if(_fields[i].equals("ExtPhone") || _fields[i].equals("AddrBook.ExtPhone")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, addrBook.getExtPhone());
					}
					else if(_fields[i].equals("Mobile") || _fields[i].equals("AddrBook.Mobile")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, addrBook.getMobile());
					}
					else if(_fields[i].equals("MailAddr") || _fields[i].equals("AddrBook.MailAddr")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, addrBook.getMailAddr());
					}
					else if(_fields[i].equals("AddrName") || _fields[i].equals("AddrBook.AddrName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, addrBook.getAddrName());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("AddrBook.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, addrBook.getRemark());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("AddrBook.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, addrBook.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("AddrBook.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, addrBook.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("AddrBook.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(addrBook.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("AddrBook.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, addrBook.getLastUpd());
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("AddrBook.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(addrBook.getLastDate().getTime()));
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("AddrBookHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update AddrBook set ";
		String[] _fields = fields.replaceAll("AddrBook\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ComId") || _fields[i].equals("AddrBook.ComId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("DeptId") || _fields[i].equals("AddrBook.DeptId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("EmpId") || _fields[i].equals("AddrBook.EmpId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Phone") || _fields[i].equals("AddrBook.Phone"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ExtPhone") || _fields[i].equals("AddrBook.ExtPhone"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Mobile") || _fields[i].equals("AddrBook.Mobile"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MailAddr") || _fields[i].equals("AddrBook.MailAddr"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("AddrName") || _fields[i].equals("AddrBook.AddrName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("AddrBook.Remark"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("AddrBook.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("AddrBook.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("AddrBook.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("AddrBook.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("AddrBook.LastDate"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(AddrBook addrBook,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ComId") || _fields[i].equals("AddrBook.ComId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, addrBook.getComId());
					}
					else if(_fields[i].equals("DeptId") || _fields[i].equals("AddrBook.DeptId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, addrBook.getDeptId());
					}
					else if(_fields[i].equals("EmpId") || _fields[i].equals("AddrBook.EmpId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, addrBook.getEmpId());
					}
					else if(_fields[i].equals("Phone") || _fields[i].equals("AddrBook.Phone")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, addrBook.getPhone());
					}
					else if(_fields[i].equals("ExtPhone") || _fields[i].equals("AddrBook.ExtPhone")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, addrBook.getExtPhone());
					}
					else if(_fields[i].equals("Mobile") || _fields[i].equals("AddrBook.Mobile")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, addrBook.getMobile());
					}
					else if(_fields[i].equals("MailAddr") || _fields[i].equals("AddrBook.MailAddr")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, addrBook.getMailAddr());
					}
					else if(_fields[i].equals("AddrName") || _fields[i].equals("AddrBook.AddrName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, addrBook.getAddrName());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("AddrBook.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, addrBook.getRemark());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("AddrBook.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, addrBook.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("AddrBook.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, addrBook.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("AddrBook.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(addrBook.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("AddrBook.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, addrBook.getLastUpd());
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("AddrBook.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(addrBook.getLastDate().getTime()));
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("AddrBookHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(AddrBook addrBook) {
		String _fields = "";
		if(null != addrBook.getBookId())
			_fields += "AddrBook.BookId,";
		if(null != addrBook.getComId())
			_fields += "AddrBook.ComId,";
		if(null != addrBook.getDeptId())
			_fields += "AddrBook.DeptId,";
		if(null != addrBook.getUserId())
			_fields += "AddrBook.UserId,";
		if(null != addrBook.getEmpId())
			_fields += "AddrBook.EmpId,";
		if(null != addrBook.getPhone())
			_fields += "AddrBook.Phone,";
		if(null != addrBook.getExtPhone())
			_fields += "AddrBook.ExtPhone,";
		if(null != addrBook.getMobile())
			_fields += "AddrBook.Mobile,";
		if(null != addrBook.getMailAddr())
			_fields += "AddrBook.MailAddr,";
		if(null != addrBook.getAddrName())
			_fields += "AddrBook.AddrName,";
		if(null != addrBook.getRemark())
			_fields += "AddrBook.Remark,";
		if(null != addrBook.getStatus())
			_fields += "AddrBook.Status,";
		if(null != addrBook.getCreateBy())
			_fields += "AddrBook.CreateBy,";
		if(null != addrBook.getCreateDate())
			_fields += "AddrBook.CreateDate,";
		if(null != addrBook.getLastUpd())
			_fields += "AddrBook.LastUpd,";
		if(null != addrBook.getLastDate())
			_fields += "AddrBook.LastDate,";

		return _fields.substring(0, _fields.length()-1);
	}
}