package zrprjt.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrprjt.vo.PrjtAuth;

public class BasicPrjtAuthHelper implements SqlHelper {
	public String getSqlString() {
		return " from PrjtAuth where 1=1";
	}

	public String getOrderBy() {
		return " order by PrjtAuth.RoleId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((PrjtAuth)object);
	}

	public String getSql4Amount(PrjtAuth prjtAuth) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(prjtAuth);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((PrjtAuth)object);
	}

	public String getSql4Delete(PrjtAuth prjtAuth) {
		return "delete from PrjtAuth where 1=1"+getSqlCondition(prjtAuth);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((PrjtAuth)object,fields);
	}

	public String getSql4All(PrjtAuth prjtAuth, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(prjtAuth)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((PrjtAuth)object,pageVO,fields);
	}

	public String getSql4Pages(PrjtAuth prjtAuth,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" PrjtAuth.RoleId "+ getSqlString()+getSqlCondition(prjtAuth)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(prjtAuth)+" and PrjtAuth.RoleId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((PrjtAuth)object);
	}

	public String getSqlCondition(PrjtAuth prjtAuth) {
		String sql = "";
		if(null != prjtAuth.getRoleId())
			sql += " and PrjtAuth.RoleId = '"+prjtAuth.getRoleId()+"'";
		if(null != prjtAuth.getIsRead())
			sql += " and PrjtAuth.IsRead = '"+prjtAuth.getIsRead()+"'";
		if(null != prjtAuth.getIsLoad())
			sql += " and PrjtAuth.IsLoad = '"+prjtAuth.getIsLoad()+"'";
		if(null != prjtAuth.getStatus())
			sql += " and PrjtAuth.Status = '"+prjtAuth.getStatus()+"'";
		if(null != prjtAuth.getCreateBy())
			sql += " and PrjtAuth.CreateBy = '"+prjtAuth.getCreateBy()+"'";
		if(null != prjtAuth.getLastUpd())
			sql += " and PrjtAuth.LastUpd = '"+prjtAuth.getLastUpd()+"'";
		if(null != prjtAuth.getStartCreateDate()) 
			sql += " and PrjtAuth.CreateDate >= '"+GenericUtil.dateFormat(prjtAuth.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtAuth.getEndCreateDate()) 
			sql += " and PrjtAuth.CreateDate <= '"+GenericUtil.dateFormat(prjtAuth.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtAuth.getStartLastDate()) 
			sql += " and PrjtAuth.LastDate >= '"+GenericUtil.dateFormat(prjtAuth.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtAuth.getEndLastDate()) 
			sql += " and PrjtAuth.LastDate <= '"+GenericUtil.dateFormat(prjtAuth.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtAuth.getPrjtNo() && !prjtAuth.getPrjtNo().trim().equals(""))
			sql += " and PrjtAuth.PrjtNo = '"+prjtAuth.getPrjtNo().trim()+"'";

		return sql;
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

	public String getInsertSql(String fields) {
		String sql = "insert into PrjtAuth("+fields.replaceAll("PrjtAuth\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(PrjtAuth prjtAuth,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("RoleId") || _fields[i].equals("PrjtAuth.RoleId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtAuth.getRoleId());
					}
					else if(_fields[i].equals("IsRead") || _fields[i].equals("PrjtAuth.IsRead")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtAuth.getIsRead());
					}
					else if(_fields[i].equals("IsLoad") || _fields[i].equals("PrjtAuth.IsLoad")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtAuth.getIsLoad());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("PrjtAuth.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtAuth.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtAuth.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtAuth.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtAuth.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtAuth.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtAuth.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtAuth.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtAuth.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtAuth.getLastDate().getTime()));
					}
					else if(_fields[i].equals("PrjtNo") || _fields[i].equals("PrjtAuth.PrjtNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtAuth.getPrjtNo());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("PrjtAuthHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update PrjtAuth set ";
		String[] _fields = fields.replaceAll("PrjtAuth\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("IsRead") || _fields[i].equals("PrjtAuth.IsRead"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsLoad") || _fields[i].equals("PrjtAuth.IsLoad"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("PrjtAuth.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtAuth.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtAuth.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtAuth.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtAuth.LastDate"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(PrjtAuth prjtAuth,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("IsRead") || _fields[i].equals("PrjtAuth.IsRead")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtAuth.getIsRead());
					}
					else if(_fields[i].equals("IsLoad") || _fields[i].equals("PrjtAuth.IsLoad")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtAuth.getIsLoad());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("PrjtAuth.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtAuth.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtAuth.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtAuth.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtAuth.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtAuth.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtAuth.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtAuth.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtAuth.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtAuth.getLastDate().getTime()));
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("PrjtAuthHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(PrjtAuth prjtAuth) {
		String _fields = "";
		if(null != prjtAuth.getRoleId())
			_fields += "PrjtAuth.RoleId,";
		if(null != prjtAuth.getIsRead())
			_fields += "PrjtAuth.IsRead,";
		if(null != prjtAuth.getIsLoad())
			_fields += "PrjtAuth.IsLoad,";
		if(null != prjtAuth.getStatus())
			_fields += "PrjtAuth.Status,";
		if(null != prjtAuth.getCreateBy())
			_fields += "PrjtAuth.CreateBy,";
		if(null != prjtAuth.getLastUpd())
			_fields += "PrjtAuth.LastUpd,";
		if(null != prjtAuth.getCreateDate())
			_fields += "PrjtAuth.CreateDate,";
		if(null != prjtAuth.getLastDate())
			_fields += "PrjtAuth.LastDate,";
		if(null != prjtAuth.getPrjtNo())
			_fields += "PrjtAuth.PrjtNo,";

		return _fields.substring(0, _fields.length()-1);
	}
}