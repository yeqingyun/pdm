package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfRisk;

public class BasicWfRiskHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfRisk where 1=1";
	}

	public String getOrderBy() {
		return " order by WfRisk.RiskId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfRisk)object);
	}

	public String getSql4Amount(WfRisk wfRisk) {
		System.out.println("select count(*) as amount "+getSqlString()+getSqlCondition(wfRisk));
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfRisk);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfRisk)object);
	}

	public String getSql4Delete(WfRisk wfRisk) {
		return "delete from WfRisk where 1=1"+getSqlCondition(wfRisk);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfRisk)object,fields);
	}

	public String getSql4All(WfRisk wfRisk, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfRisk)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfRisk)object,pageVO,fields);
	}

	public String getSql4Pages(WfRisk wfRisk,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfRisk.RiskId "+ getSqlString()+getSqlCondition(wfRisk)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfRisk)+" and WfRisk.RiskId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfRisk)object);
	}

	public String getSqlCondition(WfRisk wfRisk) {
		String sql = "";
		if(null != wfRisk.getRiskId())
			sql += " and WfRisk.RiskId = '"+wfRisk.getRiskId()+"'";
		if(null != wfRisk.getScheId())
			sql += " and exists(select 1 from SchCfg where WfRisk.ScheId =  SchCfg.SchId and SchCfg.SchId = "+wfRisk.getScheId()+")";
		if(null != wfRisk.getCateId())
			sql += " and WfRisk.CateId = '"+wfRisk.getCateId()+"'";
		if(null != wfRisk.getDeptId())
			sql += " and WfRisk.DeptId = '"+wfRisk.getDeptId()+"'";
		if(null != wfRisk.getQuesId())
			sql += " and WfRisk = '"+wfRisk.getQuesId()+"'";
		if(null != wfRisk.getResponsibleUserName())
			sql += " and WfRisk.ResponsibleUserName LIKE '%"+wfRisk.getResponsibleUserName()+"%'";
		if(null != wfRisk.getTitle() && !"".equals(wfRisk.getTitle()))
			sql += " and WfRisk.Title LIKE '%"+wfRisk.getTitle()+"%'";
		if(null != wfRisk.getRiskLevel())
			sql += " and WfRisk.RiskLevel = '"+wfRisk.getRiskLevel()+"'";
		if(null != wfRisk.getStatus())
			sql += " and WfRisk.Status = '"+wfRisk.getStatus()+"'";
		if(null != wfRisk.getWfNo() && !wfRisk.getWfNo().trim().equals(""))
			sql += " and (WfRisk.WfNo = '"+wfRisk.getWfNo().trim()+"' or exists (select 1 from WfRd rd where WfRd.ProjectNo = rd.ProjectNo and WfRd.ScheId = rd.ScheId and rd.WfNo = '"+wfRisk.getWfNo().trim()+"')) ";//����
		if(null != wfRisk.getDescription() && !wfRisk.getDescription().trim().equals(""))
			sql += " and WfRisk.Description = '"+wfRisk.getDescription().trim()+"'";
		if(null != wfRisk.getCreateUserId()) 
			sql += " and WfRisk.CreateUserId = '"+wfRisk.getCreateUserId()+"'";
		if(null != wfRisk.getLastUpdateUserId())
			sql += " and WfRisk.LastUpdateUserId = '"+wfRisk.getLastUpdateUserId()+"'";
		if(null != wfRisk.getFileNo() && !wfRisk.getFileNo().trim().equals(""))
			sql += " and WfRisk.FileNo = '"+wfRisk.getFileNo().trim()+"'";
		if(null != wfRisk.getFileName() && !wfRisk.getFileName().trim().equals(""))
			sql += " and WfRisk.FileName = '"+wfRisk.getFileName().trim()+"'";
		if(null != wfRisk.getPrjtNo() && !wfRisk.getPrjtNo().trim().equals(""))
			sql += " and WfRisk.PrjtNo = '"+wfRisk.getPrjtNo().trim()+"'";
		if(null != wfRisk.getRemark() && !wfRisk.getRemark().trim().equals(""))
			sql += " and WfRisk.Remark = '"+wfRisk.getRemark().trim()+"'";
		if(null != wfRisk.getPrjtNoList() && !wfRisk.getPrjtNoList().trim().equals(""))
			sql += " and WfRisk.PrjtNo in ("+wfRisk.getPrjtNoList().trim()+")";
		
		
		if(null != wfRisk.getRiskCategory() && !"".equals(wfRisk.getRiskCategory()))
			sql += " and WfRisk.RiskCategory LIKE '%"+wfRisk.getRiskCategory()+"%'";
		if(null != wfRisk.getExecutionDate() && !"".equals(wfRisk.getExecutionDate()))
			sql += " and WfRisk.ExecutionDate LIKE '%"+wfRisk.getExecutionDate()+"%'";
		if(null != wfRisk.getRiskText() && !"".equals(wfRisk.getRiskText()))
			sql += " and WfRisk.RiskText LIKE '%"+wfRisk.getRiskText()+"%'";
		if(null != wfRisk.getDeptName() && !"".equals(wfRisk.getDeptName()))
			sql += " and WfRisk.DeptName LIKE '%"+wfRisk.getDeptName()+"%'";
		if(null != wfRisk.getResponsibleUserId() && !"".equals(wfRisk.getResponsibleUserId()))
			sql += " and WfRisk.ResponsibleUserId LIKE '%"+wfRisk.getResponsibleUserId()+"%'";
		if(null != wfRisk.getRiskConsequence() && !"".equals(wfRisk.getRiskConsequence()))
			sql += " and WfRisk.RiskConsequence LIKE '%"+wfRisk.getRiskConsequence()+"%'";
		
		if(null != wfRisk.getRiskMonitor() && !"".equals(wfRisk.getRiskMonitor()))
			sql += " and WfRisk.RiskMonitor LIKE '%"+wfRisk.getRiskMonitor()+"%'";
		
		return sql;
	}

	public List<WfRisk> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfRisk> list = new ArrayList<WfRisk>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfRisk wfRisk = new WfRisk();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("RiskId") || _fields[i].equals("WfRisk.RiskId"))
						wfRisk.setRiskId(rs.getString("RiskId"));
					else if(_fields[i].equals("ScheId") || _fields[i].equals("WfRisk.ScheId"))
						wfRisk.setScheId(rs.getInt("ScheId"));
					else if(_fields[i].equals("PrjtNo") || _fields[i].equals("WfRisk.PrjtNo"))
						wfRisk.setPrjtNo(rs.getString("PrjtNo"));
					else if(_fields[i].equals("QuesId") || _fields[i].equals("WfRisk.QuesId"))
						wfRisk.setQuesId(rs.getString("QuesId"));
					else if(_fields[i].equals("CateId") || _fields[i].equals("WfRisk.CateId"))
						wfRisk.setCateId(rs.getInt("CateId"));
					else if(_fields[i].equals("DeptId") || _fields[i].equals("WfRisk.DeptId"))
						wfRisk.setDeptId(rs.getInt("DeptId"));
					else if(_fields[i].equals("ResponsibleUserName") || _fields[i].equals("WfRisk.ResponsibleUserName"))
						wfRisk.setResponsibleUserName(rs.getString("ResponsibleUserName"));
					else if(_fields[i].equals("Title") || _fields[i].equals("WfRisk.Title"))
						wfRisk.setTitle(rs.getString("Title"));
					else if(_fields[i].equals("Description") || _fields[i].equals("WfRisk.Description"))
						wfRisk.setDescription(rs.getString("Description"));
					else if(_fields[i].equals("RiskLevel") || _fields[i].equals("WfRisk.RiskLevel"))
						wfRisk.setRiskLevel(rs.getInt("RiskLevel"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfRisk.Status"))
						wfRisk.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateUserId") || _fields[i].equals("WfRisk.CreateUserId"))
						wfRisk.setCreateUserId(rs.getInt("CreateUserId"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfRisk.WfNo"))
						wfRisk.setWfNo(rs.getString("WfNo"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRisk.CreateDate"))
						wfRisk.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpdate") || _fields[i].equals("WfRisk.LastUpdate"))
						wfRisk.setLastUpdate(rs.getTimestamp("IdtfDate"));
					else if(_fields[i].equals("LastUpdateUserId") || _fields[i].equals("WfRisk.LastUpdateUserId"))
						wfRisk.setLastUpdateUserId(rs.getInt("LastUpdateUserId"));
					else if(_fields[i].equals("FileNo") || _fields[i].equals("WfRisk.FileNo"))
						wfRisk.setFileNo(rs.getString("FileNo"));
					else if(_fields[i].equals("FileName") || _fields[i].equals("WfRisk.FileName"))
						wfRisk.setFileName(rs.getString("FileName"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("WfRisk.Remark"))
						wfRisk.setRemark(rs.getString("Remark"));
					
					else if(_fields[i].equals("RiskCategory") || _fields[i].equals("WfRisk.RiskCategory"))
						wfRisk.setRiskCategory(rs.getString("RiskCategory"));
					else if(_fields[i].equals("ExecutionDate") || _fields[i].equals("WfRisk.ExecutionDate"))
						wfRisk.setExecutionDate(rs.getString("ExecutionDate"));
					else if(_fields[i].equals("RiskText") || _fields[i].equals("WfRisk.RiskText"))
						wfRisk.setRiskText(rs.getString("RiskText"));
					else if(_fields[i].equals("DeptName") || _fields[i].equals("WfRisk.DeptName"))
						wfRisk.setDeptName(rs.getString("DeptName"));
					else if(_fields[i].equals("ResponsibleUserId") || _fields[i].equals("WfRisk.ResponsibleUserId"))
						wfRisk.setResponsibleUserId(rs.getString("ResponsibleUserId"));
					
					else if(_fields[i].equals("RiskConsequence") || _fields[i].equals("WfRisk.RiskConsequence"))
						wfRisk.setRiskConsequence(rs.getString("RiskConsequence"));
					else if(_fields[i].equals("PreventiveMeasures") || _fields[i].equals("WfRisk.PreventiveMeasures"))
						wfRisk.setPreventiveMeasures(rs.getString("PreventiveMeasures"));
					else if(_fields[i].equals("ImpTime") || _fields[i].equals("WfRisk.ImpTime"))
						wfRisk.setImpTime(rs.getString("ImpTime"));
					else if(_fields[i].equals("RiskMonitor") || _fields[i].equals("WfRisk.RiskMonitor"))
						wfRisk.setRiskMonitor(rs.getString("RiskMonitor"));
				}
				list.add(wfRisk);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRiskHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfRisk("+fields.replaceAll("WfRisk\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		System.out.println("0000"+sql);
		return sql;
	}

	public void pstmtInsert(WfRisk wfRisk,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
				if(_fields[i].equals("RiskId") || _fields[i].equals("WfRisk.RiskId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getRiskId());
				}
				else if(_fields[i].equals("ScheId") || _fields[i].equals("WfRisk.ScheId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRisk.getScheId());
				}
				else if(_fields[i].equals("QuesId") || _fields[i].equals("WfRisk.QuesId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getQuesId());
				}
				else if(_fields[i].equals("CateId") || _fields[i].equals("WfRisk.CateId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRisk.getCateId());
				}
				else if(_fields[i].equals("DeptId") || _fields[i].equals("WfRisk.DeptId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRisk.getDeptId());
				}
				else if(_fields[i].equals("PrjtNo") || _fields[i].equals("WfRisk.PrjtNo")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getPrjtNo());
				}
				else if(_fields[i].equals("WfNo") || _fields[i].equals("WfRisk.WfNo")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getWfNo());
				}
				else if(_fields[i].equals("ResponsibleUserName") || _fields[i].equals("WfRisk.ResponsibleUserName")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getResponsibleUserName());
				}
				else if(_fields[i].equals("Title") || _fields[i].equals("WfRisk.Title")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getTitle());
				}
				else if(_fields[i].equals("Description") || _fields[i].equals("WfRisk.Description")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getDescription());
				}
				else if(_fields[i].equals("Status") || _fields[i].equals("WfRisk.Status")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRisk.getStatus());
				}
				else if(_fields[i].equals("RiskLevel") || _fields[i].equals("WfRisk.RiskLevel")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRisk.getRiskLevel());
				}
				else if(_fields[i].equals("CreateUserId") || _fields[i].equals("WfRisk.CreateUserId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRisk.getCreateUserId());
				}
				else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRisk.CreateDate")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new Timestamp(wfRisk.getCreateDate().getTime()));
				}
				else if(_fields[i].equals("LastUpdate") || _fields[i].equals("WfRisk.LastUpdate")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new Timestamp(wfRisk.getLastUpdate().getTime()));
				}
				else if(_fields[i].equals("LastUpdateUserId") || _fields[i].equals("WfRisk.LastUpdateUserId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index,wfRisk.getLastUpdateUserId());
				}
				else if(_fields[i].equals("FileNo") || _fields[i].equals("WfRisk.FileNo")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getFileNo());
				}
				else if(_fields[i].equals("FileName") || _fields[i].equals("WfRisk.FileName")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getFileName());
				}
				else if(_fields[i].equals("Remark") || _fields[i].equals("WfRisk.Remark")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getRemark());
				}
				
				
				else if(_fields[i].equals("RiskCategory") || _fields[i].equals("WfRisk.RiskCategory")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getRiskCategory());
				}
				else if(_fields[i].equals("ExecutionDate") || _fields[i].equals("WfRisk.ExecutionDate")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getExecutionDate());
				}
				else if(_fields[i].equals("RiskText") || _fields[i].equals("WfRisk.RiskText")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getRiskText());
				}
				else if(_fields[i].equals("DeptName") || _fields[i].equals("WfRisk.DeptName")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getDeptName());
				}
				else if(_fields[i].equals("ResponsibleUserId") || _fields[i].equals("WfRisk.ResponsibleUserId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getResponsibleUserId());
				}
				
				
				else if(_fields[i].equals("RiskConsequence") || _fields[i].equals("WfRisk.RiskConsequence")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getRiskConsequence());
				}
				else if(_fields[i].equals("PreventiveMeasures") || _fields[i].equals("WfRisk.PreventiveMeasures")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getPreventiveMeasures());
				}
				else if(_fields[i].equals("ImpTime") || _fields[i].equals("WfRisk.ImpTime")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getImpTime());
				}
				else if(_fields[i].equals("RiskMonitor") || _fields[i].equals("WfRisk.RiskMonitor")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getRiskMonitor());
				}
			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfRiskHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}
	
	public String pstmtInsert(String sql,WfRisk wfRisk,String fields) throws java.sql.SQLException {
		String riskId = null;
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			PreparedStatement ps = DbConnUtil.getDbconn().getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			for(int i=0;i<_fields.length;i++) {
				if(_fields[i].equals("RiskId") || _fields[i].equals("WfRisk.RiskId")) {
					index++;
					ps.setString(index, wfRisk.getRiskId());
				}
				else if(_fields[i].equals("ScheId") || _fields[i].equals("WfRisk.ScheId")) {
					index++;
					ps.setInt(index, wfRisk.getScheId());
				}
				else if(_fields[i].equals("QuesId") || _fields[i].equals("WfRisk.QuesId")) {
					index++;
					ps.setString(index, wfRisk.getQuesId());
				}
				else if(_fields[i].equals("CateId") || _fields[i].equals("WfRisk.CateId")) {
					index++;
					ps.setInt(index, wfRisk.getCateId());
				}
				else if(_fields[i].equals("DeptId") || _fields[i].equals("WfRisk.DeptId")) {
					index++;
					ps.setInt(index, wfRisk.getDeptId());
				}
				else if(_fields[i].equals("PrjtNo") || _fields[i].equals("WfRisk.PrjtNo")) {
					index++;
					ps.setString(index, wfRisk.getPrjtNo());
				}
				else if(_fields[i].equals("WfNo") || _fields[i].equals("WfRisk.WfNo")) {
					index++;
					ps.setString(index, wfRisk.getWfNo());
				}
				else if(_fields[i].equals("ResponsibleUserName") || _fields[i].equals("WfRisk.ResponsibleUserName")) {
					index++;
					ps.setString(index, wfRisk.getResponsibleUserName());
				}
				else if(_fields[i].equals("Title") || _fields[i].equals("WfRisk.Title")) {
					index++;
					ps.setString(index, wfRisk.getTitle());
				}
				else if(_fields[i].equals("Description") || _fields[i].equals("WfRisk.Description")) {
					index++;
					ps.setString(index, wfRisk.getDescription());
				}
				else if(_fields[i].equals("Status") || _fields[i].equals("WfRisk.Status")) {
					index++;
					ps.setInt(index, wfRisk.getStatus());
				}
				else if(_fields[i].equals("RiskLevel") || _fields[i].equals("WfRisk.RiskLevel")) {
					index++;
					ps.setInt(index, wfRisk.getRiskLevel());
				}
				else if(_fields[i].equals("CreateUserId") || _fields[i].equals("WfRisk.CreateUserId")) {
					index++;
					ps.setInt(index, wfRisk.getCreateUserId());
				}
				else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRisk.CreateDate")) {
					index++;
					ps.setTimestamp(index, new Timestamp(wfRisk.getCreateDate().getTime()));
				}
				else if(_fields[i].equals("LastUpdate") || _fields[i].equals("WfRisk.LastUpdate")) {
					index++;
					ps.setTimestamp(index, new Timestamp(wfRisk.getLastUpdate().getTime()));
				}
				else if(_fields[i].equals("LastUpdateUserId") || _fields[i].equals("WfRisk.LastUpdateUserId")) {
					index++;
					ps.setInt(index,wfRisk.getLastUpdateUserId());
				}
				else if(_fields[i].equals("FileNo") || _fields[i].equals("WfRisk.FileNo")) {
					index++;
					ps.setString(index, wfRisk.getFileNo());
				}
				else if(_fields[i].equals("FileName") || _fields[i].equals("WfRisk.FileName")) {
					index++;
					ps.setString(index, wfRisk.getFileName());
				}
				else if(_fields[i].equals("Remark") || _fields[i].equals("WfRisk.Remark")) {
					index++;
					ps.setString(index, wfRisk.getRemark());
				}
				
				
				else if(_fields[i].equals("RiskCategory") || _fields[i].equals("WfRisk.RiskCategory")) {
					index++;
					ps.setString(index, wfRisk.getRiskCategory());
				}
				else if(_fields[i].equals("ExecutionDate") || _fields[i].equals("WfRisk.ExecutionDate")) {
					index++;
					ps.setString(index, wfRisk.getExecutionDate());
				}
				else if(_fields[i].equals("RiskText") || _fields[i].equals("WfRisk.RiskText")) {
					index++;
					ps.setString(index, wfRisk.getRiskText());
				}
				else if(_fields[i].equals("DeptName") || _fields[i].equals("WfRisk.DeptName")) {
					index++;
					ps.setString(index, wfRisk.getDeptName());
				}
				else if(_fields[i].equals("ResponsibleUserId") || _fields[i].equals("WfRisk.ResponsibleUserId")) {
					index++;
					ps.setString(index, wfRisk.getResponsibleUserId());
				}
				
				
				else if(_fields[i].equals("RiskConsequence") || _fields[i].equals("WfRisk.RiskConsequence")) {
					index++;
					ps.setString(index, wfRisk.getRiskConsequence());
				}
				else if(_fields[i].equals("PreventiveMeasures") || _fields[i].equals("WfRisk.PreventiveMeasures")) {
					index++;
					ps.setString(index, wfRisk.getPreventiveMeasures());
				}
				else if(_fields[i].equals("ImpTime") || _fields[i].equals("WfRisk.ImpTime")) {
					index++;
					ps.setString(index, wfRisk.getImpTime());
				}
				else if(_fields[i].equals("RiskMonitor") || _fields[i].equals("WfRisk.RiskMonitor")) {
					index++;
					ps.setString(index, wfRisk.getRiskMonitor());
				}
			}
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				riskId = rs.getString(1);
			}
			ps.close();
		}catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfRiskHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
		return riskId;
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfRisk set ";
		String[] _fields = fields.replaceAll("WfRisk\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
			if(_fields[i].equals("ScheId") || _fields[i].equals("WfRisk.ScheId")) {
				sql += _fields[i] + " = ?,";
			}
			else if(_fields[i].equals("QuesId") || _fields[i].equals("WfRisk.QuesId")) {
				sql += _fields[i] + " = ?,";
			}
			else if(_fields[i].equals("CateId") || _fields[i].equals("WfRisk.CateId")) {
				sql += _fields[i] + " = ?,";
			}
			else if(_fields[i].equals("DeptId") || _fields[i].equals("WfRisk.DeptId")) {
				sql += _fields[i] + " = ?,";
			}
			else if(_fields[i].equals("PrjtNo") || _fields[i].equals("WfRisk.PrjtNo")) {
				sql += _fields[i] + " = ?,";
			}
			else if(_fields[i].equals("WfNo") || _fields[i].equals("WfRisk.WfNo")) {
				sql += _fields[i] + " = ?,";
			}
			else if(_fields[i].equals("ResponsibleUserName") || _fields[i].equals("WfRisk.ResponsibleUserName")) {
				sql += _fields[i] + " = ?,";
			}
			else if(_fields[i].equals("Title") || _fields[i].equals("WfRisk.Title")) {
				sql += _fields[i] + " = ?,";
			}
			else if(_fields[i].equals("Description") || _fields[i].equals("WfRisk.Description")) {
				sql += _fields[i] + " = ?,";
			}
			else if(_fields[i].equals("Status") || _fields[i].equals("WfRisk.Status")) {
				sql += _fields[i] + " = ?,";
			}
			else if(_fields[i].equals("RiskLevel") || _fields[i].equals("WfRisk.RiskLevel")) {
				sql += _fields[i] + " = ?,";
			}
			else if(_fields[i].equals("CreateUserId") || _fields[i].equals("WfRisk.CreateUserId")) {
				sql += _fields[i] + " = ?,";
			}
			else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRisk.CreateDate")) {
				sql += _fields[i] + " = ?,";
			}
			else if(_fields[i].equals("LastUpdate") || _fields[i].equals("WfRisk.LastUpdate")) {
				sql += _fields[i] + " = ?,";
			}
			else if(_fields[i].equals("LastUpdateUserId") || _fields[i].equals("WfRisk.LastUpdateUserId")) {
				sql += _fields[i] + " = ?,";
			}
			else if(_fields[i].equals("FileNo") || _fields[i].equals("WfRisk.FileNo")) {
				sql += _fields[i] + " = ?,";
			}
			else if(_fields[i].equals("FileName") || _fields[i].equals("WfRisk.FileName")) {
				sql += _fields[i] + " = ?,";
			}
			else if(_fields[i].equals("Remark") || _fields[i].equals("WfRisk.Remark")) {
				sql += _fields[i] + " = ?,";
			}
			
			else if(_fields[i].equals("RiskCategory") || _fields[i].equals("WfRisk.RiskCategory")) {
				sql += _fields[i] + " = ?,";
			}
			else if(_fields[i].equals("ExecutionDate") || _fields[i].equals("WfRisk.ExecutionDate")) {
				sql += _fields[i] + " = ?,";
			}
			else if(_fields[i].equals("RiskText") || _fields[i].equals("WfRisk.RiskText")) {
				sql += _fields[i] + " = ?,";
			}
			else if(_fields[i].equals("DeptName") || _fields[i].equals("WfRisk.DeptName")) {
				sql += _fields[i] + " = ?,";
			}
			else if(_fields[i].equals("ResponsibleUserId") || _fields[i].equals("WfRisk.ResponsibleUserId")) {
				sql += _fields[i] + " = ?,";
			}
			else if (_fields[i].equals("RiskConsequence")
					|| _fields[i].equals("WfRisk.RiskConsequence")) {
				sql += _fields[i] + " = ?,";
			} else if (_fields[i].equals("PreventiveMeasures")
					|| _fields[i].equals("WfRisk.PreventiveMeasures")) {
				sql += _fields[i] + " = ?,";
			} else if (_fields[i].equals("ImpTime")
					|| _fields[i].equals("WfRisk.ImpTime")) {
				sql += _fields[i] + " = ?,";
			} else if (_fields[i].equals("RiskMonitor")
					|| _fields[i].equals("WfRisk.RiskMonitor")) {
				sql += _fields[i] + " = ?,";
			}
		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfRisk wfRisk,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
				if(_fields[i].equals("ScheId") || _fields[i].equals("WfRisk.ScheId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRisk.getScheId());
				}
				else if(_fields[i].equals("QuesId") || _fields[i].equals("WfRisk.QuesId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getQuesId());
				}
				else if(_fields[i].equals("CateId") || _fields[i].equals("WfRisk.CateId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRisk.getCateId());
				}
				else if(_fields[i].equals("DeptId") || _fields[i].equals("WfRisk.DeptId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRisk.getDeptId());
				}
				else if(_fields[i].equals("PrjtNo") || _fields[i].equals("WfRisk.PrjtNo")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getPrjtNo());
				}
				else if(_fields[i].equals("WfNo") || _fields[i].equals("WfRisk.WfNo")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getWfNo());
				}
				else if(_fields[i].equals("ResponsibleUserName") || _fields[i].equals("WfRisk.ResponsibleUserName")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getResponsibleUserName());
				}
				else if(_fields[i].equals("Title") || _fields[i].equals("WfRisk.Title")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getTitle());
				}
				else if(_fields[i].equals("Description") || _fields[i].equals("WfRisk.Description")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getDescription());
				}
				else if(_fields[i].equals("Status") || _fields[i].equals("WfRisk.Status")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRisk.getStatus());
				}
				else if(_fields[i].equals("RiskLevel") || _fields[i].equals("WfRisk.RiskLevel")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRisk.getRiskLevel());
				}
				else if(_fields[i].equals("CreateUserId") || _fields[i].equals("WfRisk.CreateUserId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRisk.getCreateUserId());
				}
				else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRisk.CreateDate")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new Timestamp(wfRisk.getCreateDate().getTime()));
				}
				else if(_fields[i].equals("LastUpdate") || _fields[i].equals("WfRisk.LastUpdate")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new Timestamp(wfRisk.getLastUpdate().getTime()));
				}
				else if(_fields[i].equals("LastUpdateUserId") || _fields[i].equals("WfRisk.LastUpdateUserId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index,wfRisk.getLastUpdateUserId());
				}
				else if(_fields[i].equals("FileNo") || _fields[i].equals("WfRisk.FileNo")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getFileNo());
				}
				else if(_fields[i].equals("FileName") || _fields[i].equals("WfRisk.FileName")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getFileName());
				}
				else if(_fields[i].equals("Remark") || _fields[i].equals("WfRisk.Remark")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getRemark());
				}
				
				
				
				else if(_fields[i].equals("RiskCategory") || _fields[i].equals("WfRisk.RiskCategory")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getRiskCategory());
				}
				else if(_fields[i].equals("ExecutionDate") || _fields[i].equals("WfRisk.ExecutionDate")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getExecutionDate());
				}
				else if(_fields[i].equals("RiskText") || _fields[i].equals("WfRisk.RiskText")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getRiskText());
				}
				else if(_fields[i].equals("DeptName") || _fields[i].equals("WfRisk.DeptName")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getDeptName());
				}
				else if(_fields[i].equals("ResponsibleUserId") || _fields[i].equals("WfRisk.ResponsibleUserId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getResponsibleUserId());
				}
				
				
				else if(_fields[i].equals("RiskConsequence") || _fields[i].equals("WfRisk.RiskConsequence")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getRiskConsequence());
				}
				else if(_fields[i].equals("PreventiveMeasures") || _fields[i].equals("WfRisk.PreventiveMeasures")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getPreventiveMeasures());
				}
				else if(_fields[i].equals("ImpTime") || _fields[i].equals("WfRisk.ImpTime")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getImpTime());
				}
				else if(_fields[i].equals("RiskMonitor") || _fields[i].equals("WfRisk.RiskMonitor")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRisk.getRiskMonitor());
				}
			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfRiskHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfRisk wfRisk) {
		String _fields = "";
		if(null != wfRisk.getRiskId())
			_fields += "WfRisk.RiskId,";
		if(null != wfRisk.getScheId())
			_fields += "WfRisk.ScheId,";
		if(null != wfRisk.getQuesId())
			_fields += "WfRisk.QuesId,";
		if(null != wfRisk.getCateId())
			_fields += "WfRisk.CateId,";
		if(null != wfRisk.getDeptId())
			_fields += "WfRisk.DeptId,";
		if(null != wfRisk.getPrjtNo())
			_fields += "WfRisk.PrjtNo,";
		if(null != wfRisk.getWfNo())
			_fields += "WfRisk.WfNo,";
		if(null != wfRisk.getResponsibleUserName())
			_fields += "WfRisk.ResponsibleUserName,";
		if(null != wfRisk.getTitle())
			_fields += "WfRisk.Title,";
		if(null != wfRisk.getDescription())
			_fields += "WfRisk.Description,";
		if(null != wfRisk.getRiskLevel())
			_fields += "WfRisk.RiskLevel,";
		if(null != wfRisk.getStatus())
			_fields += "WfRisk.Status,";
		if(null != wfRisk.getCreateUserId())
			_fields += "WfRisk.CreateUserId,";
		if(null != wfRisk.getCreateDate())
			_fields += "WfRisk.CreateDate,";
		if(null != wfRisk.getLastUpdate())
			_fields += "WfRisk.LastUpdate,";
		if(null != wfRisk.getLastUpdateUserId())
			_fields += "WfRisk.LastUpdateUserId,";
		if(null != wfRisk.getFileNo())
			_fields += "WfRisk.FileNo,";
		if(null != wfRisk.getFileName())
			_fields += "WfRisk.FileName,";
		if(null != wfRisk.getRemark())
			_fields += "WfRisk.Remark,";
		
		if(null != wfRisk.getRiskCategory())
			_fields += "WfRisk.RiskCategory,";
		if(null != wfRisk.getExecutionDate())
			_fields += "WfRisk.ExecutionDate,";
		if(null != wfRisk.getRiskText())
			_fields += "WfRisk.RiskText,";
		if(null != wfRisk.getDeptName())
			_fields += "WfRisk.DeptName,";
		if(null != wfRisk.getResponsibleUserId())
			_fields += "WfRisk.ResponsibleUserId,";
		
		if(null != wfRisk.getRiskConsequence())
			_fields += "WfRisk.RiskConsequence,";
		if(null != wfRisk.getPreventiveMeasures())
			_fields += "WfRisk.PreventiveMeasures,";
		if(null != wfRisk.getImpTime())
			_fields += "WfRisk.ImpTime,";
		
		if(null != wfRisk.getRiskMonitor())
			_fields += "WfRisk.RiskMonitor,";
		return _fields.substring(0, _fields.length()-1);
	}
}