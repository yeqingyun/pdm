package zrprjt.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrprjt.vo.Driver;

public class BasicDriverHelper implements SqlHelper {
	public String getSqlString() {
		return " from Driver where 1=1";
	}

	public String getOrderBy() {
		return " order by Driver.DriveId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((Driver)object);
	}

	public String getSql4Amount(Driver driver) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(driver);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((Driver)object);
	}

	public String getSql4Delete(Driver driver) {
		return "delete from Driver where 1=1"+getSqlCondition(driver);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((Driver)object,fields);
	}

	public String getSql4All(Driver driver, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(driver)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((Driver)object,pageVO,fields);
	}

	public String getSql4Pages(Driver driver,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" Driver.DriveId "+ getSqlString()+getSqlCondition(driver)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(driver)+" and Driver.DriveId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((Driver)object);
	}

	public String getSqlCondition(Driver driver) {
		String sql = "";
		if(null != driver.getDriveId())
			sql += " and Driver.DriveId = '"+driver.getDriveId()+"'";
		if(null != driver.getDriveNo() && !driver.getDriveNo().trim().equals(""))
			sql += " and Driver.DriveNo = '"+driver.getDriveNo().trim()+"'";
		if(null != driver.getDriveNm() && !driver.getDriveNm().trim().equals(""))
			sql += " and Driver.DriveNm = '"+driver.getDriveNm().trim()+"'";
		if(null != driver.getFlowId())
			sql += " and Driver.FlowId = '"+driver.getFlowId()+"'";
		if(null != driver.getStepId())
			sql += " and Driver.StepId = '"+driver.getStepId()+"'";
		if(null != driver.getRemark() && !driver.getRemark().trim().equals(""))
			sql += " and Driver.Remark = '"+driver.getRemark().trim()+"'";
		if(null != driver.getStatus())
			sql += " and Driver.Status = '"+driver.getStatus()+"'";
		if(null != driver.getCreateBy())
			sql += " and Driver.CreateBy = '"+driver.getCreateBy()+"'";
		if(null != driver.getStartCreateDate()) 
			sql += " and Driver.CreateDate >= '"+GenericUtil.dateFormat(driver.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != driver.getEndCreateDate()) 
			sql += " and Driver.CreateDate <= '"+GenericUtil.dateFormat(driver.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != driver.getLastUpd())
			sql += " and Driver.LastUpd = '"+driver.getLastUpd()+"'";
		if(null != driver.getStartLastDate()) 
			sql += " and Driver.LastDate >= '"+GenericUtil.dateFormat(driver.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != driver.getEndLastDate()) 
			sql += " and Driver.LastDate <= '"+GenericUtil.dateFormat(driver.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";

		return sql;
	}

	public List<Driver> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<Driver> list = new ArrayList<Driver>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				Driver driver = new Driver();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DriveId") || _fields[i].equals("Driver.DriveId"))
						driver.setDriveId(rs.getInt("DriveId"));
					else if(_fields[i].equals("DriveNo") || _fields[i].equals("Driver.DriveNo"))
						driver.setDriveNo(rs.getString("DriveNo"));
					else if(_fields[i].equals("DriveNm") || _fields[i].equals("Driver.DriveNm"))
						driver.setDriveNm(rs.getString("DriveNm"));
					else if(_fields[i].equals("FlowId") || _fields[i].equals("Driver.FlowId"))
						driver.setFlowId(rs.getInt("FlowId"));
					else if(_fields[i].equals("StepId") || _fields[i].equals("Driver.StepId"))
						driver.setStepId(rs.getInt("StepId"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("Driver.Remark"))
						driver.setRemark(rs.getString("Remark"));
					else if(_fields[i].equals("Status") || _fields[i].equals("Driver.Status"))
						driver.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("Driver.CreateBy"))
						driver.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("Driver.CreateDate"))
						driver.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("Driver.LastUpd"))
						driver.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("LastDate") || _fields[i].equals("Driver.LastDate"))
						driver.setLastDate(rs.getTimestamp("LastDate"));

				}
				list.add(driver);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DriverHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into Driver("+fields.replaceAll("Driver\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(Driver driver,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DriveId") || _fields[i].equals("Driver.DriveId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, driver.getDriveId());
					}
					else if(_fields[i].equals("DriveNo") || _fields[i].equals("Driver.DriveNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, driver.getDriveNo());
					}
					else if(_fields[i].equals("DriveNm") || _fields[i].equals("Driver.DriveNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, driver.getDriveNm());
					}
					else if(_fields[i].equals("FlowId") || _fields[i].equals("Driver.FlowId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, driver.getFlowId());
					}
					else if(_fields[i].equals("StepId") || _fields[i].equals("Driver.StepId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, driver.getStepId());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("Driver.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, driver.getRemark());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("Driver.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, driver.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("Driver.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, driver.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("Driver.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(driver.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("Driver.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, driver.getLastUpd());
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("Driver.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(driver.getLastDate().getTime()));
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("DriverHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update Driver set ";
		String[] _fields = fields.replaceAll("Driver\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DriveNo") || _fields[i].equals("Driver.DriveNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("DriveNm") || _fields[i].equals("Driver.DriveNm"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("FlowId") || _fields[i].equals("Driver.FlowId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("StepId") || _fields[i].equals("Driver.StepId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("Driver.Remark"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("Driver.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("Driver.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("Driver.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("Driver.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("Driver.LastDate"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(Driver driver,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DriveNo") || _fields[i].equals("Driver.DriveNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, driver.getDriveNo());
					}
					else if(_fields[i].equals("DriveNm") || _fields[i].equals("Driver.DriveNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, driver.getDriveNm());
					}
					else if(_fields[i].equals("FlowId") || _fields[i].equals("Driver.FlowId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, driver.getFlowId());
					}
					else if(_fields[i].equals("StepId") || _fields[i].equals("Driver.StepId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, driver.getStepId());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("Driver.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, driver.getRemark());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("Driver.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, driver.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("Driver.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, driver.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("Driver.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(driver.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("Driver.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, driver.getLastUpd());
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("Driver.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(driver.getLastDate().getTime()));
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("DriverHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(Driver driver) {
		String _fields = "";
		if(null != driver.getDriveId())
			_fields += "Driver.DriveId,";
		if(null != driver.getDriveNo())
			_fields += "Driver.DriveNo,";
		if(null != driver.getDriveNm())
			_fields += "Driver.DriveNm,";
		if(null != driver.getFlowId())
			_fields += "Driver.FlowId,";
		if(null != driver.getStepId())
			_fields += "Driver.StepId,";
		if(null != driver.getRemark())
			_fields += "Driver.Remark,";
		if(null != driver.getStatus())
			_fields += "Driver.Status,";
		if(null != driver.getCreateBy())
			_fields += "Driver.CreateBy,";
		if(null != driver.getCreateDate())
			_fields += "Driver.CreateDate,";
		if(null != driver.getLastUpd())
			_fields += "Driver.LastUpd,";
		if(null != driver.getLastDate())
			_fields += "Driver.LastDate,";

		return _fields.substring(0, _fields.length()-1);
	}
}