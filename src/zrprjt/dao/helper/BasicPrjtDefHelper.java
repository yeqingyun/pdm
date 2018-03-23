package zrprjt.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrprjt.vo.PrjtDef;

public class BasicPrjtDefHelper implements SqlHelper {
	public String getSqlString() {
		return " from PrjtDef where 1=1";
	}

	public String getOrderBy() {
		return " order by PrjtDef.PrjtNo desc";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((PrjtDef)object);
	}

	public String getSql4Amount(PrjtDef prjtDef) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(prjtDef);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((PrjtDef)object);
	}

	public String getSql4Delete(PrjtDef prjtDef) {
		return "delete from PrjtDef where 1=1"+getSqlCondition(prjtDef);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((PrjtDef)object,fields);
	}

	public String getSql4All(PrjtDef prjtDef, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(prjtDef)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((PrjtDef)object,pageVO,fields);
	}

	public String getSql4Pages(PrjtDef prjtDef,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" PrjtDef.PrjtNo "+ getSqlString()+getSqlCondition(prjtDef)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(prjtDef)+" and PrjtDef.PrjtNo not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((PrjtDef)object);
	}

	public String getSqlCondition(PrjtDef prjtDef) {
		String sql = "";
		if(null != prjtDef.getTypId())
			sql += " and PrjtDef.TypId = '"+prjtDef.getTypId()+"'";
		if(null != prjtDef.getLeve())
			sql += " and PrjtDef.Leve = '"+prjtDef.getLeve()+"'";
		if(null != prjtDef.getScope())
			sql += " and PrjtDef.Scope = '"+prjtDef.getScope()+"'";
		if(null != prjtDef.getStatus())
			sql += " and PrjtDef.Status = '"+prjtDef.getStatus()+"'";
		if(null != prjtDef.getCreateBy())
			sql += " and PrjtDef.CreateBy = '"+prjtDef.getCreateBy()+"'";
		if(null != prjtDef.getLastUpd())
			sql += " and PrjtDef.LastUpd = '"+prjtDef.getLastUpd()+"'";
		if(null != prjtDef.getStartPlanStaDate()) 
			sql += " and PrjtDef.PlanStaDate >= '"+GenericUtil.dateFormat(prjtDef.getStartPlanStaDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtDef.getEndPlanStaDate()) 
			sql += " and PrjtDef.PlanStaDate <= '"+GenericUtil.dateFormat(prjtDef.getEndPlanStaDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtDef.getStartPlanOveDate()) 
			sql += " and PrjtDef.PlanOveDate >= '"+GenericUtil.dateFormat(prjtDef.getStartPlanOveDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtDef.getEndPlanOveDate()) 
			sql += " and PrjtDef.PlanOveDate <= '"+GenericUtil.dateFormat(prjtDef.getEndPlanOveDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtDef.getStartStaDate()) 
			sql += " and PrjtDef.StaDate >= '"+GenericUtil.dateFormat(prjtDef.getStartStaDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtDef.getEndStaDate()) 
			sql += " and PrjtDef.StaDate <= '"+GenericUtil.dateFormat(prjtDef.getEndStaDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtDef.getStartOveDate()) 
			sql += " and PrjtDef.OveDate >= '"+GenericUtil.dateFormat(prjtDef.getStartOveDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtDef.getEndOveDate()) 
			sql += " and PrjtDef.OveDate <= '"+GenericUtil.dateFormat(prjtDef.getEndOveDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtDef.getStartCreateDate()) 
			sql += " and PrjtDef.CreateDate >= '"+GenericUtil.dateFormat(prjtDef.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtDef.getEndCreateDate()) 
			sql += " and PrjtDef.CreateDate <= '"+GenericUtil.dateFormat(prjtDef.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtDef.getStartLastDate()) 
			sql += " and PrjtDef.LastDate >= '"+GenericUtil.dateFormat(prjtDef.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtDef.getEndLastDate()) 
			sql += " and PrjtDef.LastDate <= '"+GenericUtil.dateFormat(prjtDef.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtDef.getPrjtNo() && !prjtDef.getPrjtNo().trim().equals(""))
			sql += " and PrjtDef.PrjtNo = '"+prjtDef.getPrjtNo().trim()+"'";
		if(null != prjtDef.getPrjtNm() && !prjtDef.getPrjtNm().trim().equals(""))
			sql += " and PrjtDef.PrjtNm = '"+prjtDef.getPrjtNm().trim()+"'";
		if(null != prjtDef.getRemark() && !prjtDef.getRemark().trim().equals(""))
			sql += " and PrjtDef.Remark = '"+prjtDef.getRemark().trim()+"'";
		if(null != prjtDef.getPerce() && !prjtDef.getPerce().trim().equals(""))
			sql += " and PrjtDef.Perce = '"+prjtDef.getPerce().trim()+"'";
		
		if(null != prjtDef.getTaskVersion())
			sql += " and PrjtDef.TaskVersion = '"+prjtDef.getTaskVersion()+"'";
		if(null != prjtDef.getPrjtDefDocFileNo())
			sql += " and PrjtDef.PrjtDefDocFileNo = '"+prjtDef.getPrjtDefDocFileNo()+"'";
		if(null != prjtDef.getPrjtDefDocFileName())
			sql += " and PrjtDef.PrjtDefDocFileName = '"+prjtDef.getPrjtDefDocFileName()+"'";
		if(null != prjtDef.getPrjtTaskFileNo())
			sql += " and PrjtDef.PrjtTaskFileNo = '"+prjtDef.getPrjtTaskFileNo()+"'";
		if(null != prjtDef.getPrjtTaskFileName())
			sql += " and PrjtDef.PrjtTaskFileName = '"+prjtDef.getPrjtTaskFileName()+"'";
		
		if(null != prjtDef.getDevDeptNameID())
			sql += " and PrjtDef.DevDeptNameID = '"+prjtDef.getDevDeptNameID()+"'";
		
		if(null != prjtDef.getCurrentPoint())
			sql += " and PrjtDef.CurrentPoint = '"+prjtDef.getCurrentPoint()+"'";
		if(null != prjtDef.getNextPoint())
			sql += " and PrjtDef.NextPoint = '"+prjtDef.getNextPoint()+"'";
		if(null != prjtDef.getNextPoint())
			sql += " and PrjtDef.PrjtPointVersion = '"+prjtDef.getPrjtPointVersion()+"'";
		
		
		


		return sql;
	}

	public List<PrjtDef> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<PrjtDef> list = new ArrayList<PrjtDef>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				PrjtDef prjtDef = new PrjtDef();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("TypId") || _fields[i].equals("PrjtDef.TypId"))
						prjtDef.setTypId(rs.getInt("TypId"));
					if(_fields[i].equals("Leve") || _fields[i].equals("PrjtDef.Leve"))
						prjtDef.setLeve(rs.getInt("Leve"));
					if(_fields[i].equals("Scope") || _fields[i].equals("PrjtDef.Scope"))
						prjtDef.setScope(rs.getInt("Scope"));
					if(_fields[i].equals("Status") || _fields[i].equals("PrjtDef.Status"))
						prjtDef.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtDef.CreateBy"))
						prjtDef.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtDef.LastUpd"))
						prjtDef.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("PlanStaDate") || _fields[i].equals("PrjtDef.PlanStaDate"))
						prjtDef.setPlanStaDate(rs.getTimestamp("PlanStaDate"));
					if(_fields[i].equals("PlanOveDate") || _fields[i].equals("PrjtDef.PlanOveDate"))
						prjtDef.setPlanOveDate(rs.getTimestamp("PlanOveDate"));
					if(_fields[i].equals("StaDate") || _fields[i].equals("PrjtDef.StaDate"))
						prjtDef.setStaDate(rs.getTimestamp("StaDate"));
					if(_fields[i].equals("OveDate") || _fields[i].equals("PrjtDef.OveDate"))
						prjtDef.setOveDate(rs.getTimestamp("OveDate"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtDef.CreateDate"))
						prjtDef.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtDef.LastDate"))
						prjtDef.setLastDate(rs.getTimestamp("LastDate"));
					if(_fields[i].equals("PrjtNo") || _fields[i].equals("PrjtDef.PrjtNo"))
						prjtDef.setPrjtNo(rs.getString("PrjtNo"));
					if(_fields[i].equals("PrjtNm") || _fields[i].equals("PrjtDef.PrjtNm"))
						prjtDef.setPrjtNm(rs.getString("PrjtNm"));
					if(_fields[i].equals("Remark") || _fields[i].equals("PrjtDef.Remark"))
						prjtDef.setRemark(rs.getString("Remark"));
					if(_fields[i].equals("Perce") || _fields[i].equals("PrjtDef.Perce"))
						prjtDef.setPerce(rs.getString("Perce"));
					
					if(_fields[i].equals("TaskVersion") || _fields[i].equals("PrjtDef.TaskVersion"))
						prjtDef.setTaskVersion(rs.getFloat("TaskVersion"));
					if(_fields[i].equals("PrjtDefDocFileNo") || _fields[i].equals("PrjtDef.PrjtDefDocFileNo"))
						prjtDef.setPrjtDefDocFileNo(rs.getString("PrjtDefDocFileNo"));
					if(_fields[i].equals("PrjtDefDocFileName") || _fields[i].equals("PrjtDef.PrjtDefDocFileName"))
						prjtDef.setPrjtDefDocFileName(rs.getString("PrjtDefDocFileName"));
					if(_fields[i].equals("PrjtTaskFileNo") || _fields[i].equals("PrjtDef.PrjtTaskFileNo"))
						prjtDef.setPrjtTaskFileNo(rs.getString("PrjtTaskFileNo"));
					if(_fields[i].equals("PrjtTaskFileName") || _fields[i].equals("PrjtDef.PrjtTaskFileName"))
						prjtDef.setPrjtTaskFileName(rs.getString("PrjtTaskFileName"));
					
					if(_fields[i].equals("DevDeptNameID") || _fields[i].equals("PrjtDef.DevDeptNameID"))
						prjtDef.setDevDeptNameID(rs.getInt("DevDeptNameID"));
					
					
					if(_fields[i].equals("CurrentPoint") || _fields[i].equals("PrjtDef.CurrentPoint"))
						prjtDef.setCurrentPoint(rs.getString("CurrentPoint"));
					if(_fields[i].equals("NextPoint") || _fields[i].equals("PrjtDef.NextPoint"))
						prjtDef.setNextPoint(rs.getString("NextPoint"));
					if(_fields[i].equals("PrjtPointVersion") || _fields[i].equals("PrjtDef.PrjtPointVersion"))
						prjtDef.setPrjtPointVersion(rs.getString("PrjtPointVersion"));
					
					
					
				}
				list.add(prjtDef);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtDefHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into PrjtDef("+fields.replaceAll("PrjtDef\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(PrjtDef prjtDef,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("TypId") || _fields[i].equals("PrjtDef.TypId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtDef.getTypId());
					}
					else if(_fields[i].equals("Leve") || _fields[i].equals("PrjtDef.Leve")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtDef.getLeve());
					}
					else if(_fields[i].equals("Scope") || _fields[i].equals("PrjtDef.Scope")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtDef.getScope());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("PrjtDef.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtDef.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtDef.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtDef.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtDef.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtDef.getLastUpd());
					}
					else if(_fields[i].equals("PlanStaDate") || _fields[i].equals("PrjtDef.PlanStaDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtDef.getPlanStaDate().getTime()));
					}
					else if(_fields[i].equals("PlanOveDate") || _fields[i].equals("PrjtDef.PlanOveDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtDef.getPlanOveDate().getTime()));
					}
					else if(_fields[i].equals("StaDate") || _fields[i].equals("PrjtDef.StaDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtDef.getStaDate().getTime()));
					}
					else if(_fields[i].equals("OveDate") || _fields[i].equals("PrjtDef.OveDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtDef.getOveDate().getTime()));
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtDef.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtDef.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtDef.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtDef.getLastDate().getTime()));
					}
					else if(_fields[i].equals("PrjtNo") || _fields[i].equals("PrjtDef.PrjtNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtDef.getPrjtNo());
					}
					else if(_fields[i].equals("PrjtNm") || _fields[i].equals("PrjtDef.PrjtNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtDef.getPrjtNm());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("PrjtDef.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtDef.getRemark());
					}
					else if(_fields[i].equals("Perce") || _fields[i].equals("PrjtDef.Perce")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtDef.getPerce());
					}
					
					else if(_fields[i].equals("TaskVersion") || _fields[i].equals("PrjtDef.TaskVersion")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setFloat(index, prjtDef.getTaskVersion());
					}
					else if(_fields[i].equals("PrjtDefDocFileNo") || _fields[i].equals("PrjtDef.PrjtDefDocFileNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtDef.getPrjtDefDocFileNo());
					}
					else if(_fields[i].equals("PrjtDefDocFileName") || _fields[i].equals("PrjtDef.PrjtDefDocFileName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtDef.getPrjtDefDocFileName());
					}
					else if(_fields[i].equals("PrjtTaskFileNo") || _fields[i].equals("PrjtDef.PrjtTaskFileNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtDef.getPrjtTaskFileNo());
					}
					else if(_fields[i].equals("PrjtTaskFileName") || _fields[i].equals("PrjtDef.PrjtTaskFileName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtDef.getPrjtTaskFileName());
					}
					
					else if(_fields[i].equals("DevDeptNameID") || _fields[i].equals("PrjtDef.DevDeptNameID")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtDef.getDevDeptNameID());
					}
					
					
					else if(_fields[i].equals("CurrentPoint") || _fields[i].equals("PrjtDef.CurrentPoint")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtDef.getCurrentPoint());
					}
					else if(_fields[i].equals("NextPoint") || _fields[i].equals("PrjtDef.NextPoint")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtDef.getNextPoint());
					}
					
					else if(_fields[i].equals("PrjtPointVersion") || _fields[i].equals("PrjtDef.PrjtPointVersion")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtDef.getPrjtPointVersion());
					}
					
					
					
					

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("PrjtDefHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update PrjtDef set ";
		String[] _fields = fields.replaceAll("PrjtDef\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Leve") || _fields[i].equals("PrjtDef.Leve"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Scope") || _fields[i].equals("PrjtDef.Scope"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("PrjtDef.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtDef.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtDef.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("PlanStaDate") || _fields[i].equals("PrjtDef.PlanStaDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("PlanOveDate") || _fields[i].equals("PrjtDef.PlanOveDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("StaDate") || _fields[i].equals("PrjtDef.StaDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("OveDate") || _fields[i].equals("PrjtDef.OveDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtDef.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtDef.LastDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("PrjtNm") || _fields[i].equals("PrjtDef.PrjtNm"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("PrjtDef.Remark"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Perce") || _fields[i].equals("PrjtDef.Perce"))
						sql += _fields[i] + " = ?,";

					if(_fields[i].equals("TaskVersion") || _fields[i].equals("PrjtDef.TaskVersion"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("PrjtDefDocFileNo") || _fields[i].equals("PrjtDef.PrjtDefDocFileNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("PrjtDefDocFileName") || _fields[i].equals("PrjtDef.PrjtDefDocFileName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("PrjtTaskFileNo") || _fields[i].equals("PrjtDef.PrjtTaskFileNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("PrjtTaskFileName") || _fields[i].equals("PrjtDef.PrjtTaskFileName"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("DevDeptNameID") || _fields[i].equals("PrjtDef.DevDeptNameID"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("CurrentPoint") || _fields[i].equals("PrjtDef.CurrentPoint"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("NextPoint") || _fields[i].equals("PrjtDef.NextPoint"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("PrjtPointVersion") || _fields[i].equals("PrjtDef.PrjtPointVersion"))
						sql += _fields[i] + " = ?,";
					
					
					
		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(PrjtDef prjtDef,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Leve") || _fields[i].equals("PrjtDef.Leve")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtDef.getLeve());
					}
					else if(_fields[i].equals("Scope") || _fields[i].equals("PrjtDef.Scope")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtDef.getScope());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("PrjtDef.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtDef.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtDef.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtDef.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtDef.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtDef.getLastUpd());
					}
					else if(_fields[i].equals("PlanStaDate") || _fields[i].equals("PrjtDef.PlanStaDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtDef.getPlanStaDate().getTime()));
					}
					else if(_fields[i].equals("PlanOveDate") || _fields[i].equals("PrjtDef.PlanOveDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtDef.getPlanOveDate().getTime()));
					}
					else if(_fields[i].equals("StaDate") || _fields[i].equals("PrjtDef.StaDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtDef.getStaDate().getTime()));
					}
					else if(_fields[i].equals("OveDate") || _fields[i].equals("PrjtDef.OveDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtDef.getOveDate().getTime()));
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtDef.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtDef.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtDef.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtDef.getLastDate().getTime()));
					}
					else if(_fields[i].equals("PrjtNm") || _fields[i].equals("PrjtDef.PrjtNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtDef.getPrjtNm());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("PrjtDef.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtDef.getRemark());
					}
					else if(_fields[i].equals("Perce") || _fields[i].equals("PrjtDef.Perce")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtDef.getPerce());
					}
					
					
					else if(_fields[i].equals("TaskVersion") || _fields[i].equals("PrjtDef.TaskVersion")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setFloat(index, prjtDef.getTaskVersion());
					}
					
					else if(_fields[i].equals("PrjtDefDocFileNo") || _fields[i].equals("PrjtDef.PrjtDefDocFileNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtDef.getPrjtDefDocFileNo());
					}
					else if(_fields[i].equals("PrjtDefDocFileName") || _fields[i].equals("PrjtDef.PrjtDefDocFileName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtDef.getPrjtDefDocFileName());
					}
					else if(_fields[i].equals("PrjtTaskFileNo") || _fields[i].equals("PrjtDef.PrjtTaskFileNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtDef.getPrjtTaskFileNo());
					}
					else if(_fields[i].equals("PrjtTaskFileName") || _fields[i].equals("PrjtDef.PrjtTaskFileName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtDef.getPrjtTaskFileName());
					}
					
					else if(_fields[i].equals("DevDeptNameID") || _fields[i].equals("PrjtDef.DevDeptNameID")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtDef.getDevDeptNameID());
					}

					else if(_fields[i].equals("CurrentPoint") || _fields[i].equals("PrjtDef.CurrentPoint")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtDef.getCurrentPoint());
					}
					else if(_fields[i].equals("NextPoint") || _fields[i].equals("PrjtDef.NextPoint")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtDef.getNextPoint());
					}
					else if(_fields[i].equals("PrjtPointVersion") || _fields[i].equals("PrjtDef.PrjtPointVersion")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtDef.getPrjtPointVersion());
					}
					
					
					
			}
			System.out.println(DbConnUtil.getDbconn().getCurrentPstmt().toString());
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("PrjtDefHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(PrjtDef prjtDef) {
		String _fields = "";
		if(null != prjtDef.getTypId())
			_fields += "PrjtDef.TypId,";
		if(null != prjtDef.getLeve())
			_fields += "PrjtDef.Leve,";
		if(null != prjtDef.getScope())
			_fields += "PrjtDef.Scope,";
		if(null != prjtDef.getStatus())
			_fields += "PrjtDef.Status,";
		if(null != prjtDef.getCreateBy())
			_fields += "PrjtDef.CreateBy,";
		if(null != prjtDef.getLastUpd())
			_fields += "PrjtDef.LastUpd,";
		if(null != prjtDef.getPlanStaDate())
			_fields += "PrjtDef.PlanStaDate,";
		if(null != prjtDef.getPlanOveDate())
			_fields += "PrjtDef.PlanOveDate,";
		if(null != prjtDef.getStaDate())
			_fields += "PrjtDef.StaDate,";
		if(null != prjtDef.getOveDate())
			_fields += "PrjtDef.OveDate,";
		if(null != prjtDef.getCreateDate())
			_fields += "PrjtDef.CreateDate,";
		if(null != prjtDef.getLastDate())
			_fields += "PrjtDef.LastDate,";
		if(null != prjtDef.getPrjtNo())
			_fields += "PrjtDef.PrjtNo,";
		if(null != prjtDef.getPrjtNm())
			_fields += "PrjtDef.PrjtNm,";
		if(null != prjtDef.getRemark())
			_fields += "PrjtDef.Remark,";
		if(null != prjtDef.getPerce())
			_fields += "PrjtDef.Perce,";
		
		if(null != prjtDef.getTaskVersion())
			_fields += "PrjtDef.TaskVersion,";
		
		if(null != prjtDef.getPrjtDefDocFileNo())
			_fields += "PrjtDef.PrjtDefDocFileNo,";
		if(null != prjtDef.getPrjtDefDocFileName())
			_fields += "PrjtDef.PrjtDefDocFileName,";
		if(null != prjtDef.getPrjtTaskFileNo())
			_fields += "PrjtDef.PrjtTaskFileNo,";
		if(null != prjtDef.getPrjtTaskFileName())
			_fields += "PrjtDef.PrjtTaskFileName,";
		
		if(null != prjtDef.getDevDeptNameID())
			_fields += "PrjtDef.DevDeptNameID,";
		
		if(null != prjtDef.getCurrentPoint())
			_fields += "PrjtDef.CurrentPoint,";
		if(null != prjtDef.getNextPoint())
			_fields += "PrjtDef.NextPoint,";
		if(null != prjtDef.getPrjtPointVersion())
			_fields += "PrjtDef.PrjtPointVersion,";
		

		return _fields.substring(0, _fields.length()-1);
	}
}