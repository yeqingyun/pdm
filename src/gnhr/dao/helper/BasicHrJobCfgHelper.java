package gnhr.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnhr.vo.HrJobCfg;

public class BasicHrJobCfgHelper implements SqlHelper {
	public String getSqlString() {
		return " from HrJobCfg where 1=1";
	}

	public String getOrderBy() {
		return " order by HrJobCfg.JobId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((HrJobCfg)object);
	}

	public String getSql4Amount(HrJobCfg hrJobCfg) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(hrJobCfg);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((HrJobCfg)object);
	}

	public String getSql4Delete(HrJobCfg hrJobCfg) {
		return "delete from HrJobCfg where 1=1"+getSqlCondition(hrJobCfg);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((HrJobCfg)object,fields);
	}

	public String getSql4All(HrJobCfg hrJobCfg, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(hrJobCfg)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((HrJobCfg)object,pageVO,fields);
	}

	public String getSql4Pages(HrJobCfg hrJobCfg,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" HrJobCfg.JobId "+ getSqlString()+getSqlCondition(hrJobCfg)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(hrJobCfg)+" and HrJobCfg.JobId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((HrJobCfg)object);
	}

	public String getSqlCondition(HrJobCfg hrJobCfg) {
		String sql = "";
		if(null != hrJobCfg.getJobId())
			sql += " and HrJobCfg.JobId = '"+hrJobCfg.getJobId()+"'";
		if(null != hrJobCfg.getComId())
			sql += " and HrJobCfg.ComId = '"+hrJobCfg.getComId()+"'";
		if(null != hrJobCfg.getDeptId())
			sql += " and HrJobCfg.DeptId = '"+hrJobCfg.getDeptId()+"'";
		if(null != hrJobCfg.getJobName() && !hrJobCfg.getJobName().trim().equals(""))
			sql += " and HrJobCfg.JobName = '"+hrJobCfg.getJobName().trim()+"'";
		if(null != hrJobCfg.getDefQty())
			sql += " and HrJobCfg.DefQty = '"+hrJobCfg.getDefQty()+"'";
		if(null != hrJobCfg.getFactQty())
			sql += " and HrJobCfg.FactQty = '"+hrJobCfg.getFactQty()+"'";
		if(null != hrJobCfg.getRemark() && !hrJobCfg.getRemark().trim().equals(""))
			sql += " and HrJobCfg.Remark = '"+hrJobCfg.getRemark().trim()+"'";
		if(null != hrJobCfg.getStatus())
			sql += " and HrJobCfg.Status = '"+hrJobCfg.getStatus()+"'";
		if(null != hrJobCfg.getCreateBy())
			sql += " and HrJobCfg.CreateBy = '"+hrJobCfg.getCreateBy()+"'";
		if(null != hrJobCfg.getStartCreateDate()) 
			sql += " and HrJobCfg.CreateDate >= '"+GenericUtil.dateFormat(hrJobCfg.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != hrJobCfg.getEndCreateDate()) 
			sql += " and HrJobCfg.CreateDate <= '"+GenericUtil.dateFormat(hrJobCfg.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != hrJobCfg.getLastUpd())
			sql += " and HrJobCfg.LastUpd = '"+hrJobCfg.getLastUpd()+"'";
		if(null != hrJobCfg.getStartLastUpdDate()) 
			sql += " and HrJobCfg.LastUpdDate >= '"+GenericUtil.dateFormat(hrJobCfg.getStartLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != hrJobCfg.getEndLastUpdDate()) 
			sql += " and HrJobCfg.LastUpdDate <= '"+GenericUtil.dateFormat(hrJobCfg.getEndLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";

		return sql;
	}

	public List<HrJobCfg> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<HrJobCfg> list = new ArrayList<HrJobCfg>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				HrJobCfg hrJobCfg = new HrJobCfg();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("JobId") || _fields[i].equals("HrJobCfg.JobId"))
						hrJobCfg.setJobId(rs.getInt("JobId"));
					else if(_fields[i].equals("ComId") || _fields[i].equals("HrJobCfg.ComId"))
						hrJobCfg.setComId(rs.getInt("ComId"));
					else if(_fields[i].equals("DeptId") || _fields[i].equals("HrJobCfg.DeptId"))
						hrJobCfg.setDeptId(rs.getInt("DeptId"));
					else if(_fields[i].equals("JobName") || _fields[i].equals("HrJobCfg.JobName"))
						hrJobCfg.setJobName(rs.getString("JobName"));
					else if(_fields[i].equals("DefQty") || _fields[i].equals("HrJobCfg.DefQty"))
						hrJobCfg.setDefQty(rs.getInt("DefQty"));
					else if(_fields[i].equals("FactQty") || _fields[i].equals("HrJobCfg.FactQty"))
						hrJobCfg.setFactQty(rs.getInt("FactQty"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("HrJobCfg.Remark"))
						hrJobCfg.setRemark(rs.getString("Remark"));
					else if(_fields[i].equals("Status") || _fields[i].equals("HrJobCfg.Status"))
						hrJobCfg.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("HrJobCfg.CreateBy"))
						hrJobCfg.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("HrJobCfg.CreateDate"))
						hrJobCfg.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("HrJobCfg.LastUpd"))
						hrJobCfg.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("HrJobCfg.LastUpdDate"))
						hrJobCfg.setLastUpdDate(rs.getTimestamp("LastUpdDate"));

				}
				list.add(hrJobCfg);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("HrJobCfgHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into HrJobCfg("+fields.replaceAll("HrJobCfg\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(HrJobCfg hrJobCfg,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("JobId") || _fields[i].equals("HrJobCfg.JobId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, hrJobCfg.getJobId());
					}
					else if(_fields[i].equals("ComId") || _fields[i].equals("HrJobCfg.ComId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, hrJobCfg.getComId());
					}
					else if(_fields[i].equals("DeptId") || _fields[i].equals("HrJobCfg.DeptId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, hrJobCfg.getDeptId());
					}
					else if(_fields[i].equals("JobName") || _fields[i].equals("HrJobCfg.JobName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, hrJobCfg.getJobName());
					}
					else if(_fields[i].equals("DefQty") || _fields[i].equals("HrJobCfg.DefQty")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, hrJobCfg.getDefQty());
					}
					else if(_fields[i].equals("FactQty") || _fields[i].equals("HrJobCfg.FactQty")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, hrJobCfg.getFactQty());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("HrJobCfg.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, hrJobCfg.getRemark());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("HrJobCfg.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, hrJobCfg.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("HrJobCfg.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, hrJobCfg.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("HrJobCfg.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(hrJobCfg.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("HrJobCfg.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, hrJobCfg.getLastUpd());
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("HrJobCfg.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(hrJobCfg.getLastUpdDate().getTime()));
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("HrJobCfgHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update HrJobCfg set ";
		String[] _fields = fields.replaceAll("HrJobCfg\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ComId") || _fields[i].equals("HrJobCfg.ComId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("JobName") || _fields[i].equals("HrJobCfg.JobName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("DefQty") || _fields[i].equals("HrJobCfg.DefQty"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("FactQty") || _fields[i].equals("HrJobCfg.FactQty"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("HrJobCfg.Remark"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("HrJobCfg.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("HrJobCfg.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("HrJobCfg.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("HrJobCfg.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("HrJobCfg.LastUpdDate"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(HrJobCfg hrJobCfg,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ComId") || _fields[i].equals("HrJobCfg.ComId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, hrJobCfg.getComId());
					}
					else if(_fields[i].equals("JobName") || _fields[i].equals("HrJobCfg.JobName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, hrJobCfg.getJobName());
					}
					else if(_fields[i].equals("DefQty") || _fields[i].equals("HrJobCfg.DefQty")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, hrJobCfg.getDefQty());
					}
					else if(_fields[i].equals("FactQty") || _fields[i].equals("HrJobCfg.FactQty")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, hrJobCfg.getFactQty());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("HrJobCfg.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, hrJobCfg.getRemark());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("HrJobCfg.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, hrJobCfg.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("HrJobCfg.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, hrJobCfg.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("HrJobCfg.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(hrJobCfg.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("HrJobCfg.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, hrJobCfg.getLastUpd());
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("HrJobCfg.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(hrJobCfg.getLastUpdDate().getTime()));
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("HrJobCfgHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(HrJobCfg hrJobCfg) {
		String _fields = "";
		if(null != hrJobCfg.getJobId())
			_fields += "HrJobCfg.JobId,";
		if(null != hrJobCfg.getComId())
			_fields += "HrJobCfg.ComId,";
		if(null != hrJobCfg.getDeptId())
			_fields += "HrJobCfg.DeptId,";
		if(null != hrJobCfg.getJobName())
			_fields += "HrJobCfg.JobName,";
		if(null != hrJobCfg.getDefQty())
			_fields += "HrJobCfg.DefQty,";
		if(null != hrJobCfg.getFactQty())
			_fields += "HrJobCfg.FactQty,";
		if(null != hrJobCfg.getRemark())
			_fields += "HrJobCfg.Remark,";
		if(null != hrJobCfg.getStatus())
			_fields += "HrJobCfg.Status,";
		if(null != hrJobCfg.getCreateBy())
			_fields += "HrJobCfg.CreateBy,";
		if(null != hrJobCfg.getCreateDate())
			_fields += "HrJobCfg.CreateDate,";
		if(null != hrJobCfg.getLastUpd())
			_fields += "HrJobCfg.LastUpd,";
		if(null != hrJobCfg.getLastUpdDate())
			_fields += "HrJobCfg.LastUpdDate,";

		return _fields.substring(0, _fields.length()-1);
	}
}