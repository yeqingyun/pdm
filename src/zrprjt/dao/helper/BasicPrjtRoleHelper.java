package zrprjt.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrprjt.vo.PrjtRole;

public class BasicPrjtRoleHelper implements SqlHelper {
	public String getSqlString() {
		return " from PrjtRole where 1=1";
	}

	public String getOrderBy() {
		return " order by PrjtRole.Sort";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((PrjtRole)object);
	}

	public String getSql4Amount(PrjtRole prjtRole) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(prjtRole);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((PrjtRole)object);
	}

	public String getSql4Delete(PrjtRole prjtRole) {
		return "delete from PrjtRole where 1=1"+getSqlCondition(prjtRole);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((PrjtRole)object,fields);
	}

	public String getSql4All(PrjtRole prjtRole, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(prjtRole)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((PrjtRole)object,pageVO,fields);
	}

	public String getSql4Pages(PrjtRole prjtRole,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" PrjtRole.RoleId "+ getSqlString()+getSqlCondition(prjtRole)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(prjtRole)+" and PrjtRole.RoleId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((PrjtRole)object);
	}

	public String getSqlCondition(PrjtRole prjtRole) {
		String sql = "";
		if(null != prjtRole.getRoleId())
			sql += " and PrjtRole.RoleId = '"+prjtRole.getRoleId()+"'";
		if(null != prjtRole.getStatus())
			sql += " and PrjtRole.Status = '"+prjtRole.getStatus()+"'";
		if(null != prjtRole.getIsRead())
			sql += " and PrjtRole.IsRead = '"+prjtRole.getIsRead()+"'";
		if(null != prjtRole.getCreateBy())
			sql += " and PrjtRole.CreateBy = '"+prjtRole.getCreateBy()+"'";
		if(null != prjtRole.getLastUpd())
			sql += " and PrjtRole.LastUpd = '"+prjtRole.getLastUpd()+"'";
		if(null != prjtRole.getStartCreateDate()) 
			sql += " and PrjtRole.CreateDate >= '"+GenericUtil.dateFormat(prjtRole.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtRole.getEndCreateDate()) 
			sql += " and PrjtRole.CreateDate <= '"+GenericUtil.dateFormat(prjtRole.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtRole.getStartLastDate()) 
			sql += " and PrjtRole.LastDate >= '"+GenericUtil.dateFormat(prjtRole.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtRole.getEndLastDate()) 
			sql += " and PrjtRole.LastDate <= '"+GenericUtil.dateFormat(prjtRole.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtRole.getPrjtTypId())
			sql += " and PrjtRole.PrjtTypId = '"+prjtRole.getPrjtTypId()+"'";
		if(null != prjtRole.getRoleNm() && !prjtRole.getRoleNm().trim().equals(""))
			sql += " and PrjtRole.RoleNm = '"+prjtRole.getRoleNm().trim()+"'";
		if(null != prjtRole.getRemark() && !prjtRole.getRemark().trim().equals(""))
			sql += " and PrjtRole.Remark = '"+prjtRole.getRemark().trim()+"'";
		
		if(null != prjtRole.getRoleTyp())
			sql += " and PrjtRole.RoleTyp = '"+prjtRole.getRoleTyp()+"'";
		if(null != prjtRole.getSort())
			sql += " and PrjtRole.Sort = '"+prjtRole.getSort()+"'";
		

		return sql;
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

	public String getInsertSql(String fields) {
		String sql = "insert into PrjtRole("+fields.replaceAll("PrjtRole\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(PrjtRole prjtRole,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("RoleId") || _fields[i].equals("PrjtRole.RoleId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtRole.getRoleId());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("PrjtRole.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtRole.getStatus());
					}
					else if(_fields[i].equals("IsRead") || _fields[i].equals("PrjtRole.IsRead")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtRole.getIsRead());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtRole.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtRole.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtRole.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtRole.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtRole.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtRole.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtRole.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtRole.getLastDate().getTime()));
					}
					else if(_fields[i].equals("PrjtTypId") || _fields[i].equals("PrjtRole.PrjtTypId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtRole.getPrjtTypId());
					}
					else if(_fields[i].equals("RoleNm") || _fields[i].equals("PrjtRole.RoleNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtRole.getRoleNm());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("PrjtRole.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtRole.getRemark());
					}
					
					else if(_fields[i].equals("RoleTyp") || _fields[i].equals("PrjtRole.RoleTyp")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtRole.getRoleTyp());
					}
					
					else if(_fields[i].equals("Sort") || _fields[i].equals("PrjtRole.Sort")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtRole.getSort());
					}
					

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error("PrjtRoleHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update PrjtRole set ";
		String[] _fields = fields.replaceAll("PrjtRole\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("PrjtRole.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsRead") || _fields[i].equals("PrjtRole.IsRead"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtRole.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtRole.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtRole.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtRole.LastDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("RoleNm") || _fields[i].equals("PrjtRole.RoleNm"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("PrjtRole.Remark"))
						sql += _fields[i] + " = ?,";

					
					if(_fields[i].equals("RoleTyp") || _fields[i].equals("PrjtRole.RoleTyp"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("Sort") || _fields[i].equals("PrjtRole.Sort"))
						sql += _fields[i] + " = ?,";
					
		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(PrjtRole prjtRole,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("PrjtRole.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtRole.getStatus());
					}
					else if(_fields[i].equals("IsRead") || _fields[i].equals("PrjtRole.IsRead")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtRole.getIsRead());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtRole.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtRole.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtRole.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtRole.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtRole.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtRole.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtRole.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtRole.getLastDate().getTime()));
					}
					else if(_fields[i].equals("RoleNm") || _fields[i].equals("PrjtRole.RoleNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtRole.getRoleNm());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("PrjtRole.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtRole.getRemark());
					}

					else if(_fields[i].equals("RoleTyp") || _fields[i].equals("PrjtRole.RoleTyp")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtRole.getRoleTyp());
					}
					else if(_fields[i].equals("Sort") || _fields[i].equals("PrjtRole.Sort")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtRole.getSort());
					}
					
			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("PrjtRoleHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(PrjtRole prjtRole) {
		String _fields = "";
		if(null != prjtRole.getRoleId())
			_fields += "PrjtRole.RoleId,";
		if(null != prjtRole.getStatus())
			_fields += "PrjtRole.Status,";
		if(null != prjtRole.getIsRead())
			_fields += "PrjtRole.IsRead,";
		if(null != prjtRole.getCreateBy())
			_fields += "PrjtRole.CreateBy,";
		if(null != prjtRole.getLastUpd())
			_fields += "PrjtRole.LastUpd,";
		if(null != prjtRole.getCreateDate())
			_fields += "PrjtRole.CreateDate,";
		if(null != prjtRole.getLastDate())
			_fields += "PrjtRole.LastDate,";
		if(null != prjtRole.getPrjtTypId())
			_fields += "PrjtRole.PrjtTypId,";
		if(null != prjtRole.getRoleNm())
			_fields += "PrjtRole.RoleNm,";
		if(null != prjtRole.getRemark())
			_fields += "PrjtRole.Remark,";

		if(null != prjtRole.getRoleTyp())
			_fields += "PrjtRole.RoleTyp,";
		if(null != prjtRole.getSort())
			_fields += "PrjtRole.Sort,";
		
		return _fields.substring(0, _fields.length()-1);
	}
}