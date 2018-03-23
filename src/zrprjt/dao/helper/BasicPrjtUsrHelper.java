package zrprjt.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrprjt.vo.PrjtUsr;

public class BasicPrjtUsrHelper implements SqlHelper {
	public String getSqlString() {
		return " from PrjtUsr where 1=1";
	}

	public String getOrderBy() {
		return " order by PrjtUsr.RoleId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((PrjtUsr)object);
	}

	public String getSql4Amount(PrjtUsr prjtUsr) {
		//System.out.println("select count(*) as amount "+getSqlString()+getSqlCondition(prjtUsr));
		return "select count(*) as amount "+getSqlString()+getSqlCondition(prjtUsr);
	}
	

	public String getSql4Delete(Object object) {
		return getSql4Delete((PrjtUsr)object);
	}

	public String getSql4Delete(PrjtUsr prjtUsr) {
		return "delete from PrjtUsr where 1=1"+getSqlCondition(prjtUsr);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((PrjtUsr)object,fields);
	}

	public String getSql4All(PrjtUsr prjtUsr, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(prjtUsr)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((PrjtUsr)object,pageVO,fields);
	}

	public String getSql4Pages(PrjtUsr prjtUsr,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" PrjtUsr.Id"+ getSqlString()+getSqlCondition(prjtUsr)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(prjtUsr)+" and PrjtUsr.Id not in("+mstr+") "+getOrderBy();

		return sql;
	}
	
	public String getSql4Pages(PrjtUsr prjtUsr,PageVO pageVO, String fields,String sqlString,String conDitionSQl) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" PrjtUsr.Id"+ sqlString+conDitionSQl+getOrderBy();
		String sql = "select top "+pageSize+" "+sqlString + conDitionSQl +" and PrjtUsr.Id not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((PrjtUsr)object);
	}

	public String getSqlCondition(PrjtUsr prjtUsr) {
		String sql = "";
		if(null != prjtUsr.getId())
			sql += " and PrjtUsr.Id = '"+prjtUsr.getId()+"'";
		if(null != prjtUsr.getRoleId())
			sql += " and PrjtUsr.RoleId = '"+prjtUsr.getRoleId()+"'";
		if(null != prjtUsr.getUsrId())
			sql += " and PrjtUsr.UsrId = '"+prjtUsr.getUsrId()+"'";
		if(null != prjtUsr.getStatus())
			sql += " and PrjtUsr.Status = '"+prjtUsr.getStatus()+"'";
		if(null != prjtUsr.getCreateBy())
			sql += " and PrjtUsr.CreateBy = '"+prjtUsr.getCreateBy()+"'";
		if(null != prjtUsr.getLastUpd())
			sql += " and PrjtUsr.LastUpd = '"+prjtUsr.getLastUpd()+"'";
		if(null != prjtUsr.getStartCreateDate()) 
			sql += " and PrjtUsr.CreateDate >= '"+GenericUtil.dateFormat(prjtUsr.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtUsr.getEndCreateDate()) 
			sql += " and PrjtUsr.CreateDate <= '"+GenericUtil.dateFormat(prjtUsr.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtUsr.getStartLastDate()) 
			sql += " and PrjtUsr.LastDate >= '"+GenericUtil.dateFormat(prjtUsr.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtUsr.getEndLastDate()) 
			sql += " and PrjtUsr.LastDate <= '"+GenericUtil.dateFormat(prjtUsr.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtUsr.getPrjtNo() && !prjtUsr.getPrjtNo().trim().equals(""))
			sql += " and PrjtUsr.PrjtNo = '"+prjtUsr.getPrjtNo().trim()+"'";
		
		if(null != prjtUsr.getPriority())
			sql += " and PrjtUsr.Priority = '"+prjtUsr.getPriority()+"'";
		
		if(null != prjtUsr.getPrjtTypId())
			sql += " and PrjtUsr.PrjtTypId = '"+prjtUsr.getPrjtTypId()+"'";
//		if(null != prjtUsr.getUsrType())
//			sql += " and PrjtUsr.UsrType = '"+prjtUsr.getUsrType()+"'";
		
		return sql;
	}

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
					
					if(_fields[i].equals("Priority") || _fields[i].equals("PrjtUsr.Priority"))
						prjtUsr.setPriority(rs.getInt("Priority"));
					
					if(_fields[i].equals("PrjtTypId") || _fields[i].equals("PrjtUsr.PrjtTypId"))
						prjtUsr.setPrjtTypId(rs.getInt("PrjtTypId"));
//					if(_fields[i].equals("UsrType") || _fields[i].equals("PrjtUsr.UsrType"))
//						prjtUsr.setUsrType(rs.getInt("UsrType"));

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

	public String getInsertSql(String fields) {
		String sql = "insert into PrjtUsr("+fields.replaceAll("PrjtUsr\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(PrjtUsr prjtUsr,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("PrjtUsr.Id")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtUsr.getId());
					}
					if(_fields[i].equals("RoleId") || _fields[i].equals("PrjtUsr.RoleId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtUsr.getRoleId());
					}
					else if(_fields[i].equals("UsrId") || _fields[i].equals("PrjtUsr.UsrId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtUsr.getUsrId());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("PrjtUsr.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtUsr.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtUsr.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtUsr.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtUsr.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtUsr.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtUsr.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtUsr.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtUsr.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtUsr.getLastDate().getTime()));
					}
					else if(_fields[i].equals("PrjtNo") || _fields[i].equals("PrjtUsr.PrjtNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtUsr.getPrjtNo());
					}
					
					if(_fields[i].equals("Priority") || _fields[i].equals("PrjtUsr.Priority")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtUsr.getPriority());
					}
					
					if(_fields[i].equals("PrjtTypId") || _fields[i].equals("PrjtUsr.PrjtTypId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtUsr.getPrjtTypId());
					}
//					if(_fields[i].equals("UsrType") || _fields[i].equals("PrjtUsr.UsrType")) {
//						index++;
//						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtUsr.getUsrType());
//					}
					
					
					
			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error("PrjtUsrHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update PrjtUsr set ";
		String[] _fields = fields.replaceAll("PrjtUsr\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("PrjtUsr.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtUsr.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtUsr.LastDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("UsrId") || _fields[i].equals("PrjtUsr.UsrId"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(PrjtUsr prjtUsr,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("PrjtUsr.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtUsr.getStatus());
					}
					if(_fields[i].equals("UsrId") || _fields[i].equals("PrjtUsr.UsrId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtUsr.getUsrId());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtUsr.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtUsr.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtUsr.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtUsr.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtUsr.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtUsr.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtUsr.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtUsr.getLastDate().getTime()));
					}

					if(_fields[i].equals("Priority") || _fields[i].equals("PrjtUsr.Priority")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtUsr.getPriority());
					}
					
//					if(_fields[i].equals("PrjtTypId") || _fields[i].equals("PrjtUsr.PrjtTypId")) {
//						index++;
//						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtUsr.getPrjtTypId());
//					}
					
//					if(_fields[i].equals("UsrType") || _fields[i].equals("PrjtUsr.UsrType")) {
//						index++;
//						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtUsr.getUsrType());
//					}
					
					
					
					
			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error("PrjtUsrHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(PrjtUsr prjtUsr) {
		String _fields = "";
		if(null != prjtUsr.getId())
			_fields += "PrjtUsr.Id,";
		if(null != prjtUsr.getRoleId())
			_fields += "PrjtUsr.RoleId,";
		if(null != prjtUsr.getUsrId())
			_fields += "PrjtUsr.UsrId,";
		if(null != prjtUsr.getStatus())
			_fields += "PrjtUsr.Status,";
		if(null != prjtUsr.getCreateBy())
			_fields += "PrjtUsr.CreateBy,";
		if(null != prjtUsr.getLastUpd())
			_fields += "PrjtUsr.LastUpd,";
		if(null != prjtUsr.getCreateDate())
			_fields += "PrjtUsr.CreateDate,";
		if(null != prjtUsr.getLastDate())
			_fields += "PrjtUsr.LastDate,";
		if(null != prjtUsr.getPrjtNo())
			_fields += "PrjtUsr.PrjtNo,";
		
		if(null != prjtUsr.getPriority())
			_fields += "PrjtUsr.Priority,";
		
		if(null != prjtUsr.getPrjtTypId())
			_fields += "PrjtUsr.PrjtTypId,";
//		if(null != prjtUsr.getUsrType())
//			_fields += "PrjtUsr.UsrType,";
//		

		return _fields.substring(0, _fields.length()-1);
	}
}