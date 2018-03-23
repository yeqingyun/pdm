package zrprjt.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrprjt.vo.PrjtPoint;


public class BasicPrjtPointHelper implements SqlHelper {
	public String getSqlString() {
		return " from PrjtPoint where 1=1";
	}

	public String getOrderBy() {
		return " order by PrjtPoint.Index";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((PrjtPoint)object);
	}

	public String getSql4Amount(PrjtPoint prjtPoint) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(prjtPoint);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((PrjtPoint)object);
	}

	public String getSql4Delete(PrjtPoint prjtPoint) { 
		return "delete from PrjtPoint where 1=1"+getSqlCondition(prjtPoint);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((PrjtPoint)object,fields);
	}
 
	public String getSql4All(PrjtPoint prjtPoint, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(prjtPoint)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((PrjtPoint)object,pageVO,fields);
	}

	public String getSql4Pages(PrjtPoint prjtPoint,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" PrjtPoint.PointId "+ getSqlString()+getSqlCondition(prjtPoint)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(prjtPoint)+" and PrjtPoint.PointId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((PrjtPoint)object);
	}

	
	
//	"PrjtPoint.PointId,PrjtPoint.Index,PrjtPoint.PointName,PrjtPoint.PrjtNo,PrjtPoint.Version," +
//  "PrjtPoint.OriginalPlanOveDate,PrjtPoint.ActualOverDate,PrjtPoint.PlanOverDate,PrjtPoint.Leve," +
//  "PrjtPoint.Parent,PrjtPoint.ResponsUser,PrjtPoint.DelayTime,PrjtPoint.DelayReson,PrjtPoint.Status," +
//  "PrjtPoint.Remark,PrjtPoint.CreateBy,PrjtPoint.CreateDate,PrjtPoint.LastUpd,PrjtPoint.LastDate";
	public String getSqlCondition(PrjtPoint prjtPoint) { 
		String sql = "";
		if(null != prjtPoint.getPointId())
			sql += " and PrjtPoint.PointId = '"+prjtPoint.getPointId()+"'";
		if(null != prjtPoint.getPointIndex())
			sql += " and PrjtPoint.PointIndex = '"+prjtPoint.getPointIndex()+"'";
		if(null != prjtPoint.getPointName())
			sql += " and PrjtPoint.PointName = '"+prjtPoint.getPointName()+"'";
		if(null != prjtPoint.getPrjtNo())
			sql += " and PrjtPoint.PrjtNo = '"+prjtPoint.getPrjtNo()+"'";
		if(null != prjtPoint.getVersion())
			sql += " and PrjtPoint.Version = '"+prjtPoint.getVersion()+"'";
		
		if(null != prjtPoint.getOriginalPlanOveDate()) 
		{
			sql += " and PrjtPoint.OriginalPlanOveDate >= '"+GenericUtil.dateFormat(prjtPoint.getOriginalPlanOveDate(), "yyyy-MM-dd 00:00:00")+"'";
			sql += " and PrjtPoint.OriginalPlanOveDate <= '"+GenericUtil.dateFormat(prjtPoint.getOriginalPlanOveDate(), "yyyy-MM-dd 23:59:59")+"'";
		}
		if(null != prjtPoint.getActualOverDate()) 
		{
			sql += " and PrjtPoint.ActualOverDate >= '"+GenericUtil.dateFormat(prjtPoint.getActualOverDate(), "yyyy-MM-dd 00:00:00")+"'";
			sql += " and PrjtPoint.ActualOverDate <= '"+GenericUtil.dateFormat(prjtPoint.getActualOverDate(), "yyyy-MM-dd 23:59:59")+"'";
		}
		if(null != prjtPoint.getPlanOverDate()) 
		{
			sql += " and PrjtPoint.PlanOverDate >= '"+GenericUtil.dateFormat(prjtPoint.getPlanOverDate(), "yyyy-MM-dd 00:00:00")+"'";
			sql += " and PrjtPoint.PlanOverDate <= '"+GenericUtil.dateFormat(prjtPoint.getPlanOverDate(), "yyyy-MM-dd 23:59:59")+"'";
		}
		if(null != prjtPoint.getLeve())
			sql += " and PrjtPoint.Leve = '"+prjtPoint.getLeve()+"'";
		
		
	//	"PrjtPoint.Parent,PrjtPoint.ResponsUser,PrjtPoint.DelayTime,PrjtPoint.DelayReson,PrjtPoint.Status," +
	//  "PrjtPoint.Remark,PrjtPoint.CreateBy,PrjtPoint.CreateDate,PrjtPoint.LastUpd,PrjtPoint.LastDate";
		if(null != prjtPoint.getParent())
			sql += " and PrjtPoint.Parent = '"+prjtPoint.getParent()+"'";
		if(null != prjtPoint.getResponsUser())
			sql += " and PrjtPoint.ResponsUser = '"+prjtPoint.getResponsUser()+"'";
		if(null != prjtPoint.getDelayTime())
			sql += " and PrjtPoint.DelayTime = '"+prjtPoint.getDelayTime()+"'";
		if(null != prjtPoint.getDelayReson())
			sql += " and PrjtPoint.DelayReson = '"+prjtPoint.getDelayReson()+"'";
		if(null != prjtPoint.getStatus())
			sql += " and PrjtPoint.Status = '"+prjtPoint.getStatus()+"'";
		if(null != prjtPoint.getRemark() && !prjtPoint.getRemark().trim().equals(""))
			sql += " and PrjtPoint.Remark = '"+prjtPoint.getRemark().trim()+"'";
		if(null != prjtPoint.getCreateBy())
			sql += " and PrjtPoint.CreateBy = '"+prjtPoint.getCreateBy()+"'";
		if(null != prjtPoint.getLastUpd())
			sql += " and PrjtPoint.LastUpd = '"+prjtPoint.getLastUpd()+"'";
		if(null != prjtPoint.getCreateDate()) 
		{
			sql += " and PrjtPoint.CreateDate >= '"+GenericUtil.dateFormat(prjtPoint.getCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
			sql += " and PrjtPoint.CreateDate <= '"+GenericUtil.dateFormat(prjtPoint.getCreateDate(), "yyyy-MM-dd 23:59:59")+"'";
		}
		if(null != prjtPoint.getLastDate()) 
		{
			sql += " and PrjtPoint.LastDate >= '"+GenericUtil.dateFormat(prjtPoint.getLastDate(), "yyyy-MM-dd 00:00:00")+"'";
			sql += " and PrjtPoint.LastDate <= '"+GenericUtil.dateFormat(prjtPoint.getLastDate(), "yyyy-MM-dd 23:59:59")+"'";
		}
		return sql;
	}

	

	public List<PrjtPoint> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<PrjtPoint> list = new ArrayList<PrjtPoint>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				PrjtPoint PrjtPoint = new PrjtPoint();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("PointId") || _fields[i].equals("PrjtPoint.PointId"))
						PrjtPoint.setPointId(rs.getInt("PointId"));
					if(_fields[i].equals("Index") || _fields[i].equals("PrjtPoint.Index"))
						PrjtPoint.setPointIndex(rs.getInt("PointIndex"));
					if(_fields[i].equals("PointName") || _fields[i].equals("PrjtPoint.PointName"))
						PrjtPoint.setPointName(rs.getString("PointName"));
					if(_fields[i].equals("PrjtNo") || _fields[i].equals("PrjtPoint.PrjtNo"))
						PrjtPoint.setPrjtNo(rs.getString("PrjtNo"));
					if(_fields[i].equals("Version") || _fields[i].equals("PrjtPoint.Version"))
						PrjtPoint.setVersion(rs.getString("Version"));
					
				//  "PrjtPoint.OriginalPlanOveDate,PrjtPoint.ActualOverDate,PrjtPoint.PlanOverDate,PrjtPoint.Leve," +
					if(_fields[i].equals("OriginalPlanOveDate") || _fields[i].equals("PrjtPoint.OriginalPlanOveDate"))
						PrjtPoint.setOriginalPlanOveDate(rs.getTimestamp("OriginalPlanOveDate"));
					if(_fields[i].equals("ActualOverDate") || _fields[i].equals("PrjtPoint.ActualOverDate"))
						PrjtPoint.setActualOverDate(rs.getTimestamp("ActualOverDate"));
					if(_fields[i].equals("PlanOverDate") || _fields[i].equals("PrjtPoint.PlanOverDate"))
						PrjtPoint.setPlanOverDate(rs.getTimestamp("PlanOverDate"));
					if(_fields[i].equals("Leve") || _fields[i].equals("PrjtPoint.Leve"))
						PrjtPoint.setLeve(rs.getInt("Leve"));
					
					//"PrjtPoint.Parent,PrjtPoint.ResponsUser,PrjtPoint.DelayTime,PrjtPoint.DelayReson,PrjtPoint.Status," +
					if(_fields[i].equals("Parent") || _fields[i].equals("PrjtPoint.Parent"))
						PrjtPoint.setParent(rs.getInt("Parent"));
					if(_fields[i].equals("ResponsUser") || _fields[i].equals("PrjtPoint.ResponsUser"))
						PrjtPoint.setResponsUser(rs.getString("ResponsUser"));
					if(_fields[i].equals("DelayTime") || _fields[i].equals("PrjtPoint.DelayTime"))
						PrjtPoint.setDelayTime(rs.getInt("DelayTime"));
					if(_fields[i].equals("DelayReson") || _fields[i].equals("PrjtPoint.DelayReson"))
						PrjtPoint.setDelayReson(rs.getString("DelayReson"));
					if(_fields[i].equals("Status") || _fields[i].equals("PrjtPoint.Status"))
						PrjtPoint.setStatus(rs.getInt("Status"));
					
				//  "PrjtPoint.Remark,PrjtPoint.CreateBy,PrjtPoint.CreateDate,PrjtPoint.LastUpd,PrjtPoint.LastDate";
					if(_fields[i].equals("Remark") || _fields[i].equals("PrjtPoint.Remark"))
						PrjtPoint.setRemark(rs.getString("Remark"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtPoint.CreateBy"))
						PrjtPoint.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtPoint.CreateDate"))
						PrjtPoint.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtPoint.LastUpd"))
						PrjtPoint.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtPoint.LastDate"))
						PrjtPoint.setLastDate(rs.getTimestamp("LastDate"));
					
				}
				list.add(PrjtPoint);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtPointHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into PrjtPoint("+fields.replaceAll("PrjtPoint\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}
	


	public void pstmtInsert(PrjtPoint prjtPoint,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
				
				
				if(_fields[i].equals("PointIndex") || _fields[i].equals("PrjtPoint.PointIndex")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtPoint.getPointIndex());
				}
				else if(_fields[i].equals("PointName") || _fields[i].equals("PrjtPoint.PointName")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtPoint.getPointName());
				}
				else if(_fields[i].equals("PrjtNo") || _fields[i].equals("PrjtPoint.PrjtNo")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtPoint.getPrjtNo());
				}
				else if(_fields[i].equals("Version") || _fields[i].equals("PrjtPoint.Version")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtPoint.getVersion());
				}
				else if(_fields[i].equals("OriginalPlanOveDate") || _fields[i].equals("PrjtPoint.OriginalPlanOveDate")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtPoint.getOriginalPlanOveDate().getTime()));
				}
				else if(_fields[i].equals("ActualOverDate") || _fields[i].equals("PrjtPoint.ActualOverDate")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtPoint.getActualOverDate().getTime()));
				}
				else if(_fields[i].equals("PlanOverDate") || _fields[i].equals("PrjtPoint.PlanOverDate")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtPoint.getPlanOverDate().getTime()));
				}
				else if(_fields[i].equals("Leve") || _fields[i].equals("PrjtPoint.Leve")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtPoint.getLeve());
				}
				
			//  "PrjtPoint.Parent,PrjtPoint.ResponsUser,PrjtPoint.DelayTime,PrjtPoint.DelayReson,PrjtPoint.Status," +
				else if(_fields[i].equals("Parent") || _fields[i].equals("PrjtPoint.Parent")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtPoint.getParent());
				}
				else if(_fields[i].equals("ResponsUser") || _fields[i].equals("PrjtPoint.ResponsUser")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtPoint.getResponsUser());
				}
				else if(_fields[i].equals("DelayTime") || _fields[i].equals("PrjtPoint.DelayTime")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtPoint.getDelayTime());
				}
				else if(_fields[i].equals("DelayReson") || _fields[i].equals("PrjtPoint.DelayReson")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtPoint.getDelayReson());
				}
				else if(_fields[i].equals("Status") || _fields[i].equals("PrjtPoint.Status")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtPoint.getStatus());
				}
				//"PrjtPoint.Remark,PrjtPoint.CreateBy,PrjtPoint.CreateDate,PrjtPoint.LastUpd,PrjtPoint.LastDate";
				else if(_fields[i].equals("Remark") || _fields[i].equals("PrjtPoint.Remark")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtPoint.getRemark());
				}
				else if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtPoint.CreateBy")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtPoint.getCreateBy());
				}
				else if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtPoint.CreateDate")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtPoint.getCreateDate().getTime()));
				}
				else if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtPoint.LastUpd")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtPoint.getLastUpd());
				}
				else if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtPoint.LastDate")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtPoint.getLastDate().getTime()));
				}
					
			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("PrjtPointHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update PrjtPoint set ";
		String[] _fields = fields.replaceAll("PrjtPoint\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Index") || _fields[i].equals("PrjtPoint.Index"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("PointName") || _fields[i].equals("PrjtPoint.PointName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("PrjtNo") || _fields[i].equals("PrjtPoint.PrjtNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Version") || _fields[i].equals("PrjtPoint.Version"))
						sql += _fields[i] + " = ?,";
				//  "PrjtPoint.OriginalPlanOveDate,PrjtPoint.ActualOverDate,PrjtPoint.PlanOverDate,PrjtPoint.Leve," +
					if(_fields[i].equals("OriginalPlanOveDate") || _fields[i].equals("PrjtPoint.OriginalPlanOveDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ActualOverDate") || _fields[i].equals("PrjtPoint.ActualOverDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("PlanOverDate") || _fields[i].equals("PrjtPoint.PlanOverDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Leve") || _fields[i].equals("PrjtPoint.Leve"))
						sql += _fields[i] + " = ?,";
				//  "PrjtPoint.Parent,PrjtPoint.ResponsUser,PrjtPoint.DelayTime,PrjtPoint.DelayReson,PrjtPoint.Status," +
					if(_fields[i].equals("Parent") || _fields[i].equals("PrjtPoint.Parent"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ResponsUser") || _fields[i].equals("PrjtPoint.ResponsUser"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("DelayTime") || _fields[i].equals("PrjtPoint.DelayTime"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("DelayReson") || _fields[i].equals("PrjtPoint.DelayReson"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("PrjtPoint.Status"))
						sql += _fields[i] + " = ?,";
					
					//"PrjtPoint.Remark,PrjtPoint.CreateBy,PrjtPoint.CreateDate,PrjtPoint.LastUpd,PrjtPoint.LastDate";
					if(_fields[i].equals("Remark") || _fields[i].equals("PrjtPoint.Remark"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtPoint.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtPoint.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtPoint.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtPoint.LastDate"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}
	
	

	public void pstmtUpdate(PrjtPoint prjtPoint,String sql,String fields) throws java.sql.SQLException { 
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("PointIndex") || _fields[i].equals("PrjtPoint.PointIndex")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtPoint.getPointIndex());
					}
					else if(_fields[i].equals("PointName") || _fields[i].equals("PrjtPoint.PointName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtPoint.getPointName());
					}
					else if(_fields[i].equals("PrjtNo") || _fields[i].equals("PrjtPoint.PrjtNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtPoint.getPrjtNo());
					}
					else if(_fields[i].equals("Version") || _fields[i].equals("PrjtPoint.Version")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtPoint.getVersion());
					}
					else if(_fields[i].equals("OriginalPlanOveDate") || _fields[i].equals("PrjtPoint.OriginalPlanOveDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtPoint.getOriginalPlanOveDate().getTime()));
					}
					else if(_fields[i].equals("ActualOverDate") || _fields[i].equals("PrjtPoint.ActualOverDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtPoint.getActualOverDate().getTime()));
					}
					else if(_fields[i].equals("PlanOverDate") || _fields[i].equals("PrjtPoint.PlanOverDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtPoint.getPlanOverDate().getTime()));
					}
					else if(_fields[i].equals("Leve") || _fields[i].equals("PrjtPoint.Leve")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtPoint.getLeve());
					}
					
				//  "PrjtPoint.Parent,PrjtPoint.ResponsUser,PrjtPoint.DelayTime,PrjtPoint.DelayReson,PrjtPoint.Status," +
					else if(_fields[i].equals("Parent") || _fields[i].equals("PrjtPoint.Parent")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtPoint.getParent());
					}
					else if(_fields[i].equals("ResponsUser") || _fields[i].equals("PrjtPoint.ResponsUser")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtPoint.getResponsUser());
					}
					else if(_fields[i].equals("DelayTime") || _fields[i].equals("PrjtPoint.DelayTime")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtPoint.getDelayTime());
					}
					else if(_fields[i].equals("DelayReson") || _fields[i].equals("PrjtPoint.DelayReson")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtPoint.getDelayReson());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("PrjtPoint.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtPoint.getStatus());
					}
					//"PrjtPoint.Remark,PrjtPoint.CreateBy,PrjtPoint.CreateDate,PrjtPoint.LastUpd,PrjtPoint.LastDate";
					else if(_fields[i].equals("Remark") || _fields[i].equals("PrjtPoint.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtPoint.getRemark());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtPoint.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtPoint.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtPoint.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtPoint.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtPoint.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtPoint.getLastUpd());
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtPoint.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtPoint.getLastDate().getTime()));
					}
			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("PrjtPointHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}
	
	
	

	public String getFields(PrjtPoint PrjtPoint) {
		String _fields = "";
		//if(null != PrjtPoint.getPointId())
		//	_fields += "PrjtPoint.SchId,";
		if(null != PrjtPoint.getPointIndex())
			_fields += "PrjtPoint.PointIndex,";
		if(null != PrjtPoint.getPointName())
			_fields += "PrjtPoint.PointName,";
		if(null != PrjtPoint.getPrjtNo())
			_fields += "PrjtPoint.PrjtNo,";
		if(null != PrjtPoint.getVersion())
			_fields += "PrjtPoint.Version,";
		if(null != PrjtPoint.getOriginalPlanOveDate())
			_fields += "PrjtPoint.OriginalPlanOveDate,";
		if(null != PrjtPoint.getActualOverDate())
			_fields += "PrjtPoint.ActualOverDate,";
		if(null != PrjtPoint.getPlanOverDate())
			_fields += "PrjtPoint.PlanOverDate,";
		if(null != PrjtPoint.getLeve())
			_fields += "PrjtPoint.Leve,";
		if(null != PrjtPoint.getParent())
			_fields += "PrjtPoint.Parent,";
		if(null != PrjtPoint.getResponsUser())
			_fields += "PrjtPoint.ResponsUser,";
		if(null != PrjtPoint.getDelayTime())
			_fields += "PrjtPoint.DelayTime,";
		if(null != PrjtPoint.getDelayReson())
			_fields += "PrjtPoint.DelayReson,";
		if(null != PrjtPoint.getStatus())
			_fields += "PrjtPoint.Status,";
		if(null != PrjtPoint.getRemark())
			_fields += "PrjtPoint.Remark,";
		if(null != PrjtPoint.getCreateBy())
			_fields += "PrjtPoint.CreateBy,";
		if(null != PrjtPoint.getCreateDate())
			_fields += "PrjtPoint.CreateDate,";
		if(null != PrjtPoint.getLastUpd())
			_fields += "PrjtPoint.LastUpd,";
		if(null != PrjtPoint.getLastDate())
			_fields += "PrjtPoint.LastDate,";
		
		

		return _fields.substring(0, _fields.length()-1);
	}
}