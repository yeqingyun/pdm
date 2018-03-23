package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

import org.apache.log4j.Logger;
import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfOveSeaUsr;
import gnwf.vo.WfRd;

public class BasicWfOveSeaUsrHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfRd where 1=1";
	}

	public String getOrderBy() {
		return " order by WfRd.WfNo";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfRd)object);
	}

	public String getSql4Amount(WfRd wfRd) {
		return "select count(distinct WfRd.WfNo) as amount "+getSqlString()+getSqlCondition(wfRd);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfRd)object);
	}

	public String getSql4Delete(WfRd wfRd) {
		return "delete from WfRd where 1=1"+getSqlCondition(wfRd);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfRd)object,fields);
	}

	public String getSql4All(WfRd wfRd, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfRd)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfRd)object,pageVO,fields);
	}

	public String getSql4Pages(WfRd wfRd,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select distinct top "+pages+" WfRd.WfNo "+ getSqlString()+getSqlCondition(wfRd)+getOrderBy();
		String sql = "select distinct top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfRd)+" and WfRd.WfNo not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfRd)object);
	}

	public String getSqlCondition(WfRd wfRd) {
		String sql = "";
		if(null != wfRd.getScheId())
			sql += " and WfRd.ScheId = '"+wfRd.getScheId()+"'";
		if(null != wfRd.getFlowId())
			sql += " and WfRd.FlowId = '"+wfRd.getFlowId()+"'";
		if(null != wfRd.getStatus())
			sql += " and WfRd.Status = '"+wfRd.getStatus()+"'";
		if(null != wfRd.getCreateBy())
			sql += " and WfRd.CreateBy = '"+wfRd.getCreateBy()+"'";
		if(null != wfRd.getLastUpd())
			sql += " and WfRd.LastUpd = '"+wfRd.getLastUpd()+"'";
		if(null != wfRd.getWfNo() && !wfRd.getWfNo().trim().equals(""))
			sql += " and WfRd.WfNo = '"+wfRd.getWfNo().trim()+"'";
		if(null != wfRd.getProjectNo() && !wfRd.getProjectNo().trim().equals(""))
			sql += " and WfRd.ProjectNo = '"+wfRd.getProjectNo().trim()+"'";
		if(null != wfRd.getPreWfNo() && !wfRd.getPreWfNo().trim().equals(""))
			sql += " and WfRd.PreWfNo = '"+wfRd.getPreWfNo().trim()+"'";
		if(null != wfRd.getStartPlanSDate()) 
			sql += " and WfRd.PlanSDate >= '"+GenericUtil.dateFormat(wfRd.getStartPlanSDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getEndPlanSDate()) 
			sql += " and WfRd.PlanSDate <= '"+GenericUtil.dateFormat(wfRd.getEndPlanSDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getStartPlanEDate()) 
			sql += " and WfRd.PlanEDate >= '"+GenericUtil.dateFormat(wfRd.getStartPlanEDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getEndPlanEDate()) 
			sql += " and WfRd.PlanEDate <= '"+GenericUtil.dateFormat(wfRd.getEndPlanEDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getStartFactSDate()) 
			sql += " and WfRd.FactSDate >= '"+GenericUtil.dateFormat(wfRd.getStartFactSDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getEndFactSDate()) 
			sql += " and WfRd.FactSDate <= '"+GenericUtil.dateFormat(wfRd.getEndFactSDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getStartFactEDate()) 
			sql += " and WfRd.FactEDate >= '"+GenericUtil.dateFormat(wfRd.getStartFactEDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getEndFactEDate()) 
			sql += " and WfRd.FactEDate <= '"+GenericUtil.dateFormat(wfRd.getEndFactEDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getStartCreateDate()) 
			sql += " and WfRd.CreateDate >= '"+GenericUtil.dateFormat(wfRd.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getEndCreateDate()) 
			sql += " and WfRd.CreateDate <= '"+GenericUtil.dateFormat(wfRd.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getStartLastUpdDate()) 
			sql += " and WfRd.LastUpdDate >= '"+GenericUtil.dateFormat(wfRd.getStartLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getEndLastUpdDate()) 
			sql += " and WfRd.LastUpdDate <= '"+GenericUtil.dateFormat(wfRd.getEndLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getWfName() && !wfRd.getWfName().trim().equals(""))
			sql += " and WfRd.WfName = '"+wfRd.getWfName().trim()+"'";
		if(null != wfRd.getWfDesc() && !wfRd.getWfDesc().trim().equals(""))
			sql += " and WfRd.WfDesc = '"+wfRd.getWfDesc().trim()+"'";
		
		if(null != wfRd.getRepeatSort())
			sql += " and WfRd.RepeatSort = '"+wfRd.getRepeatSort()+"'";
		if(null != wfRd.getNeedQues())
			sql += " and WfRd.NeedQues = '"+wfRd.getNeedQues()+"'";
		if(null != wfRd.getQuesId())
			sql += " and WfRd.QuesId = '"+wfRd.getQuesId()+"'";
		
		
		//WfRd.RepeatSort,
		return sql;
	}

	public List<WfOveSeaUsr> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfOveSeaUsr> list = new ArrayList<WfOveSeaUsr>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfOveSeaUsr wfOveSeaUsr = new WfOveSeaUsr();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("WfUsrID") || _fields[i].equals("WfOveSeaUsr.WfUsrID"))
						wfOveSeaUsr.setWfUsrID(rs.getInt("WfUsrID"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfOveSeaUsr.CreateBy"))
						wfOveSeaUsr.setCreateBy(rs.getInt("CreateBy"));
					
					else if(_fields[i].equals("FlowID") || _fields[i].equals("WfOveSeaUsr.FlowID"))
						wfOveSeaUsr.setFlowID(rs.getInt("FlowID"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfOveSeaUsr.CreateDate"))
						wfOveSeaUsr.setCreateDate(rs.getTimestamp("CreateDate"));
					
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfOveSeaUsr.WfNo"))
						wfOveSeaUsr.setWfNo(rs.getString("WfNo"));
					
					

				}
				list.add(wfOveSeaUsr);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfOveSeaUsrHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfOveSeaUsr("+fields.replaceAll("WfOveSeaUsr\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfOveSeaUsr wfOveSeaUsr,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("WfUsrID") || _fields[i].equals("WfOveSeaUsr.WfUsrID")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfOveSeaUsr.getWfUsrID());
					}
					
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfOveSeaUsr.WfNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfOveSeaUsr.getWfNo());
					}
					
					else if(_fields[i].equals("FlowID") || _fields[i].equals("WfOveSeaUsr.FlowID")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfOveSeaUsr.getFlowID());
					}
					
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfOveSeaUsr.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfOveSeaUsr.getCreateBy());
					}
				
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfOveSeaUsr.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfOveSeaUsr.getCreateDate().getTime()));
					}
			
			
					
					
					

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfRdHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfRd set ";
		String[] _fields = fields.replaceAll("WfRd\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ScheId") || _fields[i].equals("WfRd.ScheId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("FlowId") || _fields[i].equals("WfRd.FlowId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("WfRd.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("WfRd.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("WfRd.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ProjectNo") || _fields[i].equals("WfRd.ProjectNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("PreWfNo") || _fields[i].equals("WfRd.PreWfNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("PlanSDate") || _fields[i].equals("WfRd.PlanSDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("PlanEDate") || _fields[i].equals("WfRd.PlanEDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("FactSDate") || _fields[i].equals("WfRd.FactSDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("FactEDate") || _fields[i].equals("WfRd.FactEDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRd.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfRd.LastUpdDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("WfName") || _fields[i].equals("WfRd.WfName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("WfDesc") || _fields[i].equals("WfRd.WfDesc"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("RepeatSort") || _fields[i].equals("WfRd.RepeatSort"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("NeedQues") || _fields[i].equals("WfRd.NeedQues"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("QuesId") || _fields[i].equals("WfRd.QuesId"))
						sql += _fields[i] + " = ?,";
					

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfRd wfRd,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ScheId") || _fields[i].equals("WfRd.ScheId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRd.getScheId());
					}
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfRd.FlowId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRd.getFlowId());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("WfRd.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRd.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfRd.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRd.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfRd.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRd.getLastUpd());
					}
					else if(_fields[i].equals("ProjectNo") || _fields[i].equals("WfRd.ProjectNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRd.getProjectNo());
					}
					else if(_fields[i].equals("PreWfNo") || _fields[i].equals("WfRd.PreWfNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRd.getPreWfNo());
					}
					else if(_fields[i].equals("PlanSDate") || _fields[i].equals("WfRd.PlanSDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfRd.getPlanSDate().getTime()));
					}
					else if(_fields[i].equals("PlanEDate") || _fields[i].equals("WfRd.PlanEDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfRd.getPlanEDate().getTime()));
					}
					else if(_fields[i].equals("FactSDate") || _fields[i].equals("WfRd.FactSDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfRd.getFactSDate().getTime()));
					}
					else if(_fields[i].equals("FactEDate") || _fields[i].equals("WfRd.FactEDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfRd.getFactEDate().getTime()));
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRd.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfRd.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfRd.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfRd.getLastUpdDate().getTime()));
					}
					else if(_fields[i].equals("WfName") || _fields[i].equals("WfRd.WfName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRd.getWfName());
					}
					else if(_fields[i].equals("WfDesc") || _fields[i].equals("WfRd.WfDesc")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRd.getWfDesc());
					}
					
					else if(_fields[i].equals("RepeatSort") || _fields[i].equals("WfRd.RepeatSort")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRd.getRepeatSort());
					}

					else if(_fields[i].equals("DocCateId") || _fields[i].equals("WfRd.DocCateId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRd.getDocCateId());
					}
					else if(_fields[i].equals("NeedQues") || _fields[i].equals("WfRd.NeedQues")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRd.getNeedQues());
					}
					else if(_fields[i].equals("QuesId") || _fields[i].equals("WfRd.QuesId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRd.getQuesId());
					}
					
					
					
					
			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfRdHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfOveSeaUsr wfOveSeaUsr) {
		String _fields = "";
		if(null != wfOveSeaUsr.getWfNo())
			_fields += "WfOveSeaUsr.WfNo,";
		
		if(null != wfOveSeaUsr.getWfUsrID())
			_fields += "WfOveSeaUsr.WfUsrID,";
		
		if(null != wfOveSeaUsr.getFlowID())
			_fields += "WfOveSeaUsr.FlowID,";
		
		if(null != wfOveSeaUsr.getCreateBy())
			_fields += "WfOveSeaUsr.CreateBy,";
		
		if(null != wfOveSeaUsr.getCreateDate())
			_fields += "WfOveSeaUsr.CreateDate,";
		

		return _fields.substring(0, _fields.length()-1);
	}
}