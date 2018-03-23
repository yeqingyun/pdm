package zrprjt.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrprjt.vo.DriverRd;

public class BasicDriverRdHelper implements SqlHelper {
	public String getSqlString() {
		return " from DriverRd where 1=1";
	}

	public String getOrderBy() {
		return " order by DriverRd.LogId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((DriverRd)object);
	}

	public String getSql4Amount(DriverRd driverRd) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(driverRd);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((DriverRd)object);
	}

	public String getSql4Delete(DriverRd driverRd) {
		return "delete from DriverRd where 1=1"+getSqlCondition(driverRd);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((DriverRd)object,fields);
	}

	public String getSql4All(DriverRd driverRd, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(driverRd)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((DriverRd)object,pageVO,fields);
	}

	public String getSql4Pages(DriverRd driverRd,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" DriverRd.LogId "+ getSqlString()+getSqlCondition(driverRd)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(driverRd)+" and DriverRd.LogId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((DriverRd)object);
	}

	public String getSqlCondition(DriverRd driverRd) {
		String sql = "";
		if(null != driverRd.getLogId())
			sql += " and DriverRd.LogId = '"+driverRd.getLogId()+"'";
		if(null != driverRd.getDriverId())
			sql += " and DriverRd.DriverId = '"+driverRd.getDriverId()+"'";
		if(null != driverRd.getWfNo() && !driverRd.getWfNo().trim().equals(""))
			sql += " and DriverRd.WfNo = '"+driverRd.getWfNo().trim()+"'";
		if(null != driverRd.getStartDriverDate()) 
			sql += " and DriverRd.DriverDate >= '"+GenericUtil.dateFormat(driverRd.getStartDriverDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != driverRd.getEndDriverDate()) 
			sql += " and DriverRd.DriverDate <= '"+GenericUtil.dateFormat(driverRd.getEndDriverDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != driverRd.getRemark() && !driverRd.getRemark().trim().equals(""))
			sql += " and DriverRd.Remark = '"+driverRd.getRemark().trim()+"'";
		if(null != driverRd.getStatus())
			sql += " and DriverRd.Status = '"+driverRd.getStatus()+"'";
		if(null != driverRd.getCreateBy())
			sql += " and DriverRd.CreateBy = '"+driverRd.getCreateBy()+"'";
		if(null != driverRd.getStartCreateDate()) 
			sql += " and DriverRd.CreateDate >= '"+GenericUtil.dateFormat(driverRd.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != driverRd.getEndCreateDate()) 
			sql += " and DriverRd.CreateDate <= '"+GenericUtil.dateFormat(driverRd.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != driverRd.getLastUpd())
			sql += " and DriverRd.LastUpd = '"+driverRd.getLastUpd()+"'";
		if(null != driverRd.getStartLastDate()) 
			sql += " and DriverRd.LastDate >= '"+GenericUtil.dateFormat(driverRd.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != driverRd.getEndLastDate()) 
			sql += " and DriverRd.LastDate <= '"+GenericUtil.dateFormat(driverRd.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";

		return sql;
	}

	public List<DriverRd> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<DriverRd> list = new ArrayList<DriverRd>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				DriverRd driverRd = new DriverRd();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("LogId") || _fields[i].equals("DriverRd.LogId"))
						driverRd.setLogId(rs.getInt("LogId"));
					else if(_fields[i].equals("DriverId") || _fields[i].equals("DriverRd.DriverId"))
						driverRd.setDriverId(rs.getInt("DriverId"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("DriverRd.WfNo"))
						driverRd.setWfNo(rs.getString("WfNo"));
					else if(_fields[i].equals("DriverDate") || _fields[i].equals("DriverRd.DriverDate"))
						driverRd.setDriverDate(rs.getTimestamp("DriverDate"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("DriverRd.Remark"))
						driverRd.setRemark(rs.getString("Remark"));
					else if(_fields[i].equals("Status") || _fields[i].equals("DriverRd.Status"))
						driverRd.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("DriverRd.CreateBy"))
						driverRd.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("DriverRd.CreateDate"))
						driverRd.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("DriverRd.LastUpd"))
						driverRd.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("LastDate") || _fields[i].equals("DriverRd.LastDate"))
						driverRd.setLastDate(rs.getTimestamp("LastDate"));

				}
				list.add(driverRd);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DriverRdHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into DriverRd("+fields.replaceAll("DriverRd\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(DriverRd driverRd,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("LogId") || _fields[i].equals("DriverRd.LogId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, driverRd.getLogId());
					}
					else if(_fields[i].equals("DriverId") || _fields[i].equals("DriverRd.DriverId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, driverRd.getDriverId());
					}
					else if(_fields[i].equals("WfNo") || _fields[i].equals("DriverRd.WfNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, driverRd.getWfNo());
					}
					else if(_fields[i].equals("DriverDate") || _fields[i].equals("DriverRd.DriverDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(driverRd.getDriverDate().getTime()));
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("DriverRd.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, driverRd.getRemark());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("DriverRd.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, driverRd.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("DriverRd.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, driverRd.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("DriverRd.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(driverRd.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("DriverRd.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, driverRd.getLastUpd());
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("DriverRd.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(driverRd.getLastDate().getTime()));
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("DriverRdHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update DriverRd set ";
		String[] _fields = fields.replaceAll("DriverRd\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DriverId") || _fields[i].equals("DriverRd.DriverId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("WfNo") || _fields[i].equals("DriverRd.WfNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("DriverDate") || _fields[i].equals("DriverRd.DriverDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("DriverRd.Remark"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("DriverRd.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("DriverRd.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("DriverRd.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("DriverRd.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("DriverRd.LastDate"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(DriverRd driverRd,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DriverId") || _fields[i].equals("DriverRd.DriverId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, driverRd.getDriverId());
					}
					else if(_fields[i].equals("WfNo") || _fields[i].equals("DriverRd.WfNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, driverRd.getWfNo());
					}
					else if(_fields[i].equals("DriverDate") || _fields[i].equals("DriverRd.DriverDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(driverRd.getDriverDate().getTime()));
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("DriverRd.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, driverRd.getRemark());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("DriverRd.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, driverRd.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("DriverRd.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, driverRd.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("DriverRd.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(driverRd.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("DriverRd.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, driverRd.getLastUpd());
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("DriverRd.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(driverRd.getLastDate().getTime()));
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("DriverRdHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(DriverRd driverRd) {
		String _fields = "";
		if(null != driverRd.getLogId())
			_fields += "DriverRd.LogId,";
		if(null != driverRd.getDriverId())
			_fields += "DriverRd.DriverId,";
		if(null != driverRd.getWfNo())
			_fields += "DriverRd.WfNo,";
		if(null != driverRd.getDriverDate())
			_fields += "DriverRd.DriverDate,";
		if(null != driverRd.getRemark())
			_fields += "DriverRd.Remark,";
		if(null != driverRd.getStatus())
			_fields += "DriverRd.Status,";
		if(null != driverRd.getCreateBy())
			_fields += "DriverRd.CreateBy,";
		if(null != driverRd.getCreateDate())
			_fields += "DriverRd.CreateDate,";
		if(null != driverRd.getLastUpd())
			_fields += "DriverRd.LastUpd,";
		if(null != driverRd.getLastDate())
			_fields += "DriverRd.LastDate,";

		return _fields.substring(0, _fields.length()-1);
	}
}