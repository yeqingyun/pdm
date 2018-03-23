package zrprjt.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrprjt.vo.SchCfg;

public class BasicSchCfgHelper implements SqlHelper {
	public String getSqlString() {
		return " from SchCfg where 1=1";
	}

	public String getOrderBy() {
		return " order by SchCfg.SchId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((SchCfg)object);
	}

	public String getSql4Amount(SchCfg schCfg) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(schCfg);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((SchCfg)object);
	}

	public String getSql4Delete(SchCfg schCfg) {
		return "delete from SchCfg where 1=1"+getSqlCondition(schCfg);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((SchCfg)object,fields);
	}

	public String getSql4All(SchCfg schCfg, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(schCfg)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((SchCfg)object,pageVO,fields);
	}

	public String getSql4Pages(SchCfg schCfg,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" SchCfg.SchId "+ getSqlString()+getSqlCondition(schCfg)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(schCfg)+" and SchCfg.SchId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((SchCfg)object);
	}

	public String getSqlCondition(SchCfg schCfg) {
		String sql = "";
		if(null != schCfg.getSchId())
			sql += " and SchCfg.SchId = '"+schCfg.getSchId()+"'";
		if(null != schCfg.getParent())
			sql += " and SchCfg.Parent = '"+schCfg.getParent()+"'";
//		if(null != schCfg.getFlowId())
//			sql += " and SchCfg.FlowId = '"+schCfg.getFlowId()+"'";
		if(null != schCfg.getTypId())
			sql += " and SchCfg.TypId = '"+schCfg.getTypId()+"'";
		if(null != schCfg.getSchNo())
			sql += " and SchCfg.SchNo = '"+schCfg.getSchNo()+"'";
		if(null != schCfg.getLeve())
			sql += " and SchCfg.Leve = '"+schCfg.getLeve()+"'";
		if(null != schCfg.getCfgTime())
			sql += " and SchCfg.CfgTime = '"+schCfg.getCfgTime()+"'";
		if(null != schCfg.getStatus())
			sql += " and SchCfg.Status = '"+schCfg.getStatus()+"'";
		if(null != schCfg.getCreateBy())
			sql += " and SchCfg.CreateBy = '"+schCfg.getCreateBy()+"'";
		if(null != schCfg.getLastUpd())
			sql += " and SchCfg.LastUpd = '"+schCfg.getLastUpd()+"'";
		if(null != schCfg.getStartCreateDate()) 
			sql += " and SchCfg.CreateDate >= '"+GenericUtil.dateFormat(schCfg.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != schCfg.getEndCreateDate()) 
			sql += " and SchCfg.CreateDate <= '"+GenericUtil.dateFormat(schCfg.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != schCfg.getStartLastDate()) 
			sql += " and SchCfg.LastDate >= '"+GenericUtil.dateFormat(schCfg.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != schCfg.getEndLastDate()) 
			sql += " and SchCfg.LastDate <= '"+GenericUtil.dateFormat(schCfg.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != schCfg.getSchNm() && !schCfg.getSchNm().trim().equals(""))
			sql += " and SchCfg.SchNm = '"+schCfg.getSchNm().trim()+"'";
		if(null != schCfg.getRemark() && !schCfg.getRemark().trim().equals(""))
			sql += " and SchCfg.Remark = '"+schCfg.getRemark().trim()+"'";
		
		if(null != schCfg.getPredecessorLink() && !schCfg.getPredecessorLink().trim().equals(""))
			sql += " and SchCfg.PredecessorLink = '"+schCfg.getPredecessorLink().trim()+"'";
		if(null != schCfg.getCritical())
			sql += " and SchCfg.Critical = '"+schCfg.getCritical()+"'";
		if(null != schCfg.getSummary())
			sql += " and SchCfg.Summary = '"+schCfg.getSummary()+"'";
		if(null != schCfg.getMilestone())
			sql += " and SchCfg.Milestone = '"+schCfg.getMilestone()+"'";
		
		
		if(null != schCfg.getManualStart())
			sql += " and SchCfg.ManualStart = '"+schCfg.getManualStart()+"'";
		
		

		return sql;
	}

	public List<SchCfg> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<SchCfg> list = new ArrayList<SchCfg>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				SchCfg schCfg = new SchCfg();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SchId") || _fields[i].equals("SchCfg.SchId"))
						schCfg.setSchId(rs.getInt("SchId"));
					if(_fields[i].equals("Parent") || _fields[i].equals("SchCfg.Parent"))
						schCfg.setParent(rs.getInt("Parent"));
//					if(_fields[i].equals("FlowId") || _fields[i].equals("SchCfg.FlowId"))
//						schCfg.setFlowId(rs.getInt("FlowId"));
					if(_fields[i].equals("TypId") || _fields[i].equals("SchCfg.TypId"))
						schCfg.setTypId(rs.getInt("TypId"));
					if(_fields[i].equals("SchNo") || _fields[i].equals("SchCfg.SchNo"))
						schCfg.setSchNo(rs.getInt("SchNo"));
					if(_fields[i].equals("Leve") || _fields[i].equals("SchCfg.Leve"))
						schCfg.setLeve(rs.getInt("Leve"));
					if(_fields[i].equals("CfgTime") || _fields[i].equals("SchCfg.CfgTime"))
						schCfg.setCfgTime(rs.getInt("CfgTime"));
					if(_fields[i].equals("Status") || _fields[i].equals("SchCfg.Status"))
						schCfg.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("SchCfg.CreateBy"))
						schCfg.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("SchCfg.LastUpd"))
						schCfg.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("SchCfg.CreateDate"))
						schCfg.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("SchCfg.LastDate"))
						schCfg.setLastDate(rs.getTimestamp("LastDate"));
					if(_fields[i].equals("SchNm") || _fields[i].equals("SchCfg.SchNm"))
						schCfg.setSchNm(rs.getString("SchNm"));
					if(_fields[i].equals("Remark") || _fields[i].equals("SchCfg.Remark"))
						schCfg.setRemark(rs.getString("Remark"));
					
					if(_fields[i].equals("PredecessorLink") || _fields[i].equals("SchCfg.PredecessorLink"))
						schCfg.setPredecessorLink(rs.getString("PredecessorLink"));
					if(_fields[i].equals("Critical") || _fields[i].equals("SchCfg.Critical"))
						schCfg.setCritical(rs.getInt("Critical"));
					if(_fields[i].equals("Summary") || _fields[i].equals("SchCfg.Summary"))
						schCfg.setSummary(rs.getInt("Summary"));
					if(_fields[i].equals("Milestone") || _fields[i].equals("SchCfg.Milestone"))
						schCfg.setMilestone(rs.getInt("Milestone"));
					
					
					if(_fields[i].equals("ManualStart") || _fields[i].equals("SchCfg.ManualStart"))
						schCfg.setStatus(rs.getInt("ManualStart"));
					
					
					
				}
				list.add(schCfg);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SchCfgHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into SchCfg("+fields.replaceAll("SchCfg\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(SchCfg schCfg,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SchId") || _fields[i].equals("SchCfg.SchId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getSchId());
					}
					else if(_fields[i].equals("Parent") || _fields[i].equals("SchCfg.Parent")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getParent());
					}
//					else if(_fields[i].equals("FlowId") || _fields[i].equals("SchCfg.FlowId")) {
//						index++;
//						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getFlowId());
//					}
					else if(_fields[i].equals("TypId") || _fields[i].equals("SchCfg.TypId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getTypId());
					}
					else if(_fields[i].equals("SchNo") || _fields[i].equals("SchCfg.SchNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getSchNo());
					}
					else if(_fields[i].equals("Leve") || _fields[i].equals("SchCfg.Leve")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getLeve());
					}
					else if(_fields[i].equals("CfgTime") || _fields[i].equals("SchCfg.CfgTime")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getCfgTime());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("SchCfg.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("SchCfg.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("SchCfg.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("SchCfg.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(schCfg.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("SchCfg.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(schCfg.getLastDate().getTime()));
					}
					else if(_fields[i].equals("SchNm") || _fields[i].equals("SchCfg.SchNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, schCfg.getSchNm());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("SchCfg.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, schCfg.getRemark());
					}
					
					else if(_fields[i].equals("PredecessorLink") || _fields[i].equals("SchCfg.PredecessorLink")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, schCfg.getPredecessorLink());
					}
					
					else if(_fields[i].equals("Critical") || _fields[i].equals("SchCfg.Critical")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getCritical());
					}
					
					else if(_fields[i].equals("Summary") || _fields[i].equals("SchCfg.Summary")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getSummary());
					}
					
					else if(_fields[i].equals("Milestone") || _fields[i].equals("SchCfg.Milestone")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getMilestone());
					}
					else if(_fields[i].equals("ManualStart") || _fields[i].equals("SchCfg.ManualStart")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getManualStart());
					}
					
					

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("SchCfgHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update SchCfg set ";
		String[] _fields = fields.replaceAll("SchCfg\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Parent") || _fields[i].equals("SchCfg.Parent"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("FlowId") || _fields[i].equals("SchCfg.FlowId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("SchNo") || _fields[i].equals("SchCfg.SchNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Leve") || _fields[i].equals("SchCfg.Leve"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CfgTime") || _fields[i].equals("SchCfg.CfgTime"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("SchCfg.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("SchCfg.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("SchCfg.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("SchCfg.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("SchCfg.LastDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("SchNm") || _fields[i].equals("SchCfg.SchNm"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("SchCfg.Remark"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("PredecessorLink") || _fields[i].equals("SchCfg.PredecessorLink"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("Critical") || _fields[i].equals("SchCfg.Critical"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("Summary") || _fields[i].equals("SchCfg.Summary"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("Milestone") || _fields[i].equals("SchCfg.Milestone"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("ManualStart") || _fields[i].equals("SchCfg.ManualStart"))
						sql += _fields[i] + " = ?,";
					
					

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(SchCfg schCfg,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Parent") || _fields[i].equals("SchCfg.Parent")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getParent());
					}
//					else if(_fields[i].equals("FlowId") || _fields[i].equals("SchCfg.FlowId")) {
//						index++;
//						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getFlowId());
//					}
					else if(_fields[i].equals("SchNo") || _fields[i].equals("SchCfg.SchNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getSchNo());
					}
					else if(_fields[i].equals("Leve") || _fields[i].equals("SchCfg.Leve")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getLeve());
					}
					else if(_fields[i].equals("CfgTime") || _fields[i].equals("SchCfg.CfgTime")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getCfgTime());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("SchCfg.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("SchCfg.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("SchCfg.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("SchCfg.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(schCfg.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("SchCfg.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(schCfg.getLastDate().getTime()));
					}
					else if(_fields[i].equals("SchNm") || _fields[i].equals("SchCfg.SchNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, schCfg.getSchNm());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("SchCfg.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, schCfg.getRemark());
					}
					
					else if(_fields[i].equals("PredecessorLink") || _fields[i].equals("SchCfg.PredecessorLink")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, schCfg.getPredecessorLink());
					}
					
					else if(_fields[i].equals("Critical") || _fields[i].equals("SchCfg.Critical")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getCritical());
					}
					
					else if(_fields[i].equals("Summary") || _fields[i].equals("SchCfg.Summary")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getSummary());
					}
					
					else if(_fields[i].equals("Milestone") || _fields[i].equals("SchCfg.Milestone")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getMilestone());
					}
					
					else if(_fields[i].equals("ManualStart") || _fields[i].equals("SchCfg.ManualStart")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schCfg.getManualStart());
					}
					
					
					

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("SchCfgHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(SchCfg schCfg) {
		String _fields = "";
		if(null != schCfg.getSchId())
			_fields += "SchCfg.SchId,";
		if(null != schCfg.getParent())
			_fields += "SchCfg.Parent,";
//		if(null != schCfg.getFlowId())
//			_fields += "SchCfg.FlowId,";
		if(null != schCfg.getTypId())
			_fields += "SchCfg.TypId,";
		if(null != schCfg.getSchNo())
			_fields += "SchCfg.SchNo,";
		if(null != schCfg.getLeve())
			_fields += "SchCfg.Leve,";
		if(null != schCfg.getCfgTime())
			_fields += "SchCfg.CfgTime,";
		if(null != schCfg.getStatus())
			_fields += "SchCfg.Status,";
		if(null != schCfg.getCreateBy())
			_fields += "SchCfg.CreateBy,";
		if(null != schCfg.getLastUpd())
			_fields += "SchCfg.LastUpd,";
		if(null != schCfg.getCreateDate())
			_fields += "SchCfg.CreateDate,";
		if(null != schCfg.getLastDate())
			_fields += "SchCfg.LastDate,";
		if(null != schCfg.getSchNm())
			_fields += "SchCfg.SchNm,";
		if(null != schCfg.getRemark())
			_fields += "SchCfg.Remark,";
		
		if(null != schCfg.getPredecessorLink())
			_fields += "SchCfg.PredecessorLink,";
		
		if(null != schCfg.getCritical())
			_fields += "SchCfg.Critical,";
		
		if(null != schCfg.getSummary())
			_fields += "SchCfg.Summary,";
		
		if(null != schCfg.getMilestone())
			_fields += "SchCfg.Milestone,";
		
		if(null != schCfg.getManualStart())
			_fields += "SchCfg.ManualStart,";
		
		
		

		return _fields.substring(0, _fields.length()-1);
	}
}