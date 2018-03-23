package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

import org.apache.log4j.Logger;
import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import Utils.DateUtil;
import gnwf.vo.WfQues;

public class BasicWfQuesHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfQues where 1=1";
	}

	public String getOrderBy() {
		return " order by WfQues.QuesId desc";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfQues)object);
	}

	public String getSql4Amount(WfQues wfQues) {
	  System.out.println("select count(*) as amount "+getSqlString()+getSqlCondition(wfQues));
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfQues);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfQues)object);
	}

	public String getSql4Delete(WfQues wfQues) {
		return "delete from WfQues where 1=1"+getSqlCondition(wfQues);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfQues)object,fields);
	}

	public String getSql4All(WfQues wfQues, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfQues)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfQues)object,pageVO,fields);
	}

	public String getSql4Pages(WfQues wfQues,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfQues.QuesId "+ getSqlString()+getSqlCondition(wfQues)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfQues)+" and WfQues.QuesId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfQues)object);
	}

	public String getSqlCondition(WfQues wfQues) {
		String sql = "";
		if(null != wfQues.getQuesId()&&!wfQues.getQuesId().toString().isEmpty())
			sql += " and WfQues.QuesId = '"+wfQues.getQuesId()+"'";
		if(null != wfQues.getScheId())
			sql += " and exists(select 1 from SchCfg where WfQues.ScheId =  SchCfg.SchId and SchCfg.SchId in ("+wfQues.getScheIds()+"))";
		if(null != wfQues.getTaskId())
			sql += " and WfQues.TaskId = '"+wfQues.getTaskId()+"'";
		if(null != wfQues.getCateId())
			sql += " and WfQues.CateId = '"+wfQues.getCateId()+"'";
		if(null != wfQues.getComId())
			sql += " and WfQues.ComId = '"+wfQues.getComId()+"'";
		if(null != wfQues.getDeptId())
			sql += " and WfQues.DeptId = '"+wfQues.getDeptId()+"'";
		if(null != wfQues.getUserId())
			sql += " and WfQues.UserId = '"+wfQues.getUserId()+"'";
		if(null != wfQues.getRyUsrs())
			sql += " and WfQues.RyUsrs = '"+wfQues.getRyUsrs()+"'";
		if(null != wfQues.getTitle() && !"".equals(wfQues.getTitle()))
			sql += " and WfQues.Title LIKE '%"+wfQues.getTitle()+"%'";
		
		
		if(null != wfQues.getQuesLevel())
			sql += " and WfQues.QuesLevel = '"+wfQues.getQuesLevel()+"'";
		if(null != wfQues.getStatus() && wfQues.getStatus() != 200){
			if(wfQues.getStatus()==100){ //查询未关闭的问题
				sql += " and WfQues.Status in( 1,9,10,11)";
			}else{
				sql += " and WfQues.Status = '"+wfQues.getStatus()+"'";
			}
		}
		if ( wfQues.getTestItemName().length() != 0) {
			
			sql += " and WfQues.TestItemName in( "+wfQues.getTestItemName() +")";
			
		}
		
		if(null != wfQues.getCategory() && wfQues.getCategory() != 0) {
			if(wfQues.getCategory() == 1) {
				sql += " and WfQues.CrdefectId is not null";
			}else if(wfQues.getCategory() == 2) {
				sql += " and WfQues.CrdefectId is null";
			}
		}
		if(null != wfQues.getIdtfBy())
			sql += " and WfQues.IdtfBy = '"+wfQues.getIdtfBy()+"'";
		if(null != wfQues.getCreateBy())
			sql += " and WfQues.CreateBy = '"+wfQues.getCreateBy()+"'";
		if(null != wfQues.getLastUpd())
			sql += " and WfQues.LastUpd = '"+wfQues.getLastUpd()+"'";
		if(null != wfQues.getWfNo() && !wfQues.getWfNo().trim().equals(""))
			sql += " and (WfQues.WfNo = '"+wfQues.getWfNo().trim()+"' or exists (select 1 from WfRd rd where rd.ProjectNo = rd.ProjectNo and rd.ScheId = rd.ScheId and rd.WfNo = '"+wfQues.getWfNo().trim()+"')) ";//����
		if(null != wfQues.getIdtfRes() && !wfQues.getIdtfRes().trim().equals(""))
			sql += " and WfQues.IdtfRes = '"+wfQues.getIdtfRes().trim()+"'";
		if(null != wfQues.getIsRisk() && !wfQues.getIsRisk().trim().equals(""))
			sql += " and WfQues.IsRisk = '"+wfQues.getIsRisk().trim()+"'";
		if(null != wfQues.getStartCreateDate()) 
			sql += " and WfQues.CreateDate >= '"+GenericUtil.dateFormat(wfQues.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfQues.getEndCreateDate()) 
			sql += " and WfQues.CreateDate <= '"+GenericUtil.dateFormat(wfQues.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfQues.getStartLastUpdDate()) 
			sql += " and WfQues.LastUpdDate >= '"+GenericUtil.dateFormat(wfQues.getStartLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfQues.getEndLastUpdDate()) 
			sql += " and WfQues.LastUpdDate <= '"+GenericUtil.dateFormat(wfQues.getEndLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfQues.getQuesText() && !wfQues.getQuesText().trim().equals(""))
		{	
			String checkthesplit= wfQues.getQuesText().trim();
			String[] sourceStrArray = checkthesplit.split(" ");
			System.out.println(sourceStrArray.length );
			if (sourceStrArray.length ==1) {
				sql += " and WfQues.QuesText like '%"+wfQues.getQuesText().trim()+"%'";
			} else {
				for (int i = 0; i < sourceStrArray.length; i++) {
					System.out.println(sourceStrArray[i]);
					sql += " and WfQues.QuesText like '%"+sourceStrArray[i]+"%'";
				}
			}
			
			
		}
		if(null != wfQues.getResult() && !wfQues.getResult().trim().equals(""))
			sql += " and WfQues.Result = '"+wfQues.getResult().trim()+"'";
		
		if(null != wfQues.getIdtfResFileNo() && !wfQues.getIdtfResFileNo().trim().equals(""))
			sql += " and WfQues.IdtfResFileNo = '"+wfQues.getIdtfResFileNo().trim()+"'";
		
		if(null != wfQues.getResultFileNo() && !wfQues.getResultFileNo().trim().equals(""))
			sql += " and WfQues.ResultFileNo = '"+wfQues.getResultFileNo().trim()+"'";
		
		if(null != wfQues.getIdtfResFileName() && !wfQues.getIdtfResFileName().trim().equals(""))
			sql += " and WfQues.IdtfResFileName = '"+wfQues.getIdtfResFileName().trim()+"'";
		
		if(null != wfQues.getResultFileName() && !wfQues.getResultFileName().trim().equals(""))
			sql += " and WfQues.ResultFileName = '"+wfQues.getResultFileName().trim()+"'";
		if(null != wfQues.getFileNo() && !wfQues.getFileNo().trim().equals(""))
			sql += " and WfQues.FileNo = '"+wfQues.getFileNo().trim()+"'";
		if(null != wfQues.getFileName() && !wfQues.getFileName().trim().equals(""))
			sql += " and WfQues.FileName = '"+wfQues.getFileName().trim()+"'";
		if(null != wfQues.getPrjtNo() && !wfQues.getPrjtNo().trim().equals(""))
			sql += " and WfQues.PrjtNo = '"+wfQues.getPrjtNo().trim()+"'";
		if(null != wfQues.getPrjtNoList() && !wfQues.getPrjtNoList().trim().equals(""))
			sql += " and WfQues.PrjtNo in ("+wfQues.getPrjtNoList().trim()+")";
		if(null != wfQues.getRemark() && !wfQues.getRemark().trim().equals(""))
			sql += " and WfQues.Remark( = '"+wfQues.getRemark().trim()+"'";
		if(null != wfQues.getUsrName() && !"".equals(wfQues.getUsrName()))
			sql += " and WfQues.UsrName LIKE '%"+wfQues.getUsrName()+"%'";
		if(null != wfQues.getIsRiskQues())
			sql += " and WfQues.IsRiskQues = '"+wfQues.getIsRiskQues()+"'";
		if(null != wfQues.getCompletedDate())
			sql += " and WfQues.CompletedDate = '"+DateUtil.format(wfQues.getCompletedDate(),"yyyy-MM-dd")+"'";
		if(null != wfQues.getPicXY() && !"".equals(wfQues.getPicXY()))
			sql += " and WfQues.PicXY LIKE '%"+wfQues.getPicXY()+"%'";
		if(null != wfQues.getCrDefectId() && !"".equals(wfQues.getCrDefectId()))
			sql += " and WfQues.CrDefectId = '"+wfQues.getCrDefectId()+"'";
		
	
		if(null != wfQues.getDeptNm() && !wfQues.getDeptNm().trim().equals(""))
			sql += " and (select dp.deptNm from Dept dp ,Usr us where WfQues.IdtfBy = us.Id and us.OrgNo = dp.deptno)  like '%"+wfQues.getDeptNm().trim()+"%'";
		
	
		
		if(null != wfQues.getSourceID())
			sql += " and WfQues.SourceID = '"+wfQues.getSourceID()+"'";
		if(null != wfQues.getQuesTypeID())
			sql += " and WfQues.QuesTypeID = '"+wfQues.getQuesTypeID()+"'";
		
		if(null != wfQues.getCloseQuesReason())
			sql += " and WfQues.CloseQuesReason = '"+wfQues.getCloseQuesReason()+"'";
		
		return sql;
	}

	public List<WfQues> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfQues> list = new ArrayList<WfQues>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfQues wfQues = new WfQues();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("QuesId") || _fields[i].equals("WfQues.QuesId"))
						wfQues.setQuesId(rs.getString("QuesId"));
					else if(_fields[i].equals("ScheId") || _fields[i].equals("WfQues.ScheId"))
						wfQues.setScheId(rs.getInt("ScheId"));
					else if(_fields[i].equals("TaskId") || _fields[i].equals("WfQues.TaskId"))
						wfQues.setTaskId(rs.getInt("TaskId"));
					else if(_fields[i].equals("CateId") || _fields[i].equals("WfQues.CateId"))
						wfQues.setCateId(rs.getInt("CateId"));
					else if(_fields[i].equals("ComId") || _fields[i].equals("WfQues.ComId"))
						wfQues.setComId(rs.getInt("ComId"));
					else if(_fields[i].equals("DeptId") || _fields[i].equals("WfQues.DeptId"))
						wfQues.setDeptId(rs.getInt("DeptId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfQues.UserId"))
						wfQues.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("Title") || _fields[i].equals("WfQues.Title"))
						wfQues.setTitle(rs.getString("Title"));
					else if(_fields[i].equals("RyUsrs") || _fields[i].equals("WfQues.RyUsrs"))
						wfQues.setTitle(rs.getString("RyUsrs"));
					else if(_fields[i].equals("QuesLevel") || _fields[i].equals("WfQues.QuesLevel"))
						wfQues.setQuesLevel(rs.getInt("QuesLevel"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfQues.Status"))
						wfQues.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("IdtfRes") || _fields[i].equals("WfQues.IdtfRes"))
						wfQues.setIdtfRes(rs.getString("IdtfRes"));
					else if(_fields[i].equals("IdtfBy") || _fields[i].equals("WfQues.IdtfBy"))
						wfQues.setIdtfBy(rs.getInt("IdtfBy"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfQues.CreateBy"))
						wfQues.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfQues.LastUpd"))
						wfQues.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfQues.WfNo"))
						wfQues.setWfNo(rs.getString("WfNo"));
					else if(_fields[i].equals("IsRisk") || _fields[i].equals("WfQues.IsRisk"))
						wfQues.setIsRisk(rs.getString("IsRisk"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfQues.CreateDate"))
						wfQues.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("IdtfDate") || _fields[i].equals("WfQues.IdtfDate"))
						wfQues.setIdtfDate(rs.getTimestamp("IdtfDate"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfQues.LastUpdDate"))
						wfQues.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					else if(_fields[i].equals("QuesText") || _fields[i].equals("WfQues.QuesText"))
						wfQues.setQuesText(rs.getString("QuesText"));
					else if(_fields[i].equals("Result") || _fields[i].equals("WfQues.Result"))
						wfQues.setResult(rs.getString("Result"));
					else if(_fields[i].equals("IdtfResFileNo") || _fields[i].equals("WfQues.IdtfResFileNo"))
						wfQues.setIdtfResFileNo(rs.getString("IdtfResFileNo"));
					else if(_fields[i].equals("ResultFileNo") || _fields[i].equals("WfQues.ResultFileNo"))
						wfQues.setResultFileNo(rs.getString("ResultFileNo"));
					else if(_fields[i].equals("IdtfResFileName") || _fields[i].equals("WfQues.IdtfResFileName"))
						wfQues.setIdtfResFileName(rs.getString("IdtfResFileName"));
					else if(_fields[i].equals("ResultFileName") || _fields[i].equals("WfQues.ResultFileName"))
						wfQues.setResultFileName(rs.getString("ResultFileName"));
					else if(_fields[i].equals("FileNo") || _fields[i].equals("WfQues.FileNo"))
						wfQues.setFileNo(rs.getString("FileNo"));
					else if(_fields[i].equals("FileName") || _fields[i].equals("WfQues.FileName"))
						wfQues.setFileName(rs.getString("FileName"));
					else if(_fields[i].equals("PrjtNo") || _fields[i].equals("WfQues.PrjtNo"))
						wfQues.setPrjtNo(rs.getString("PrjtNo"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("WfQues.Remark"))
						wfQues.setRemark(rs.getString("Remark"));
					else if(_fields[i].equals("UsrName") || _fields[i].equals("WfQues.UsrName"))
						wfQues.setUsrName(rs.getString("UsrName"));
					else if(_fields[i].equals("IsRiskQues") || _fields[i].equals("WfQues.IsRiskQues"))
						wfQues.setIsRiskQues(rs.getInt("IsRiskQues"));
					else if(_fields[i].equals("CompletedDate") || _fields[i].equals("WfQues.CompletedDate"))
						wfQues.setCompletedDate(rs.getTimestamp("CompletedDate"));
					else if(_fields[i].equals("PicXY") || _fields[i].equals("WfQues.PicXY"))
						wfQues.setPicXY(rs.getString("PicXY"));
					else if(_fields[i].equals("CrDefectId") || _fields[i].equals("WfQues.CrDefectId"))
						wfQues.setCrDefectId(rs.getString("CrDefectId"));
					
					else if(_fields[i].equals("SourceID") || _fields[i].equals("WfQues.SourceID"))
						wfQues.setSourceID(rs.getInt("SourceID"));
					else if(_fields[i].equals("QuesTypeID") || _fields[i].equals("WfQues.QuesTypeID"))
						wfQues.setQuesTypeID(rs.getInt("QuesTypeID"));
					
					else if(_fields[i].equals("CloseQuesReason") || _fields[i].equals("WfQues.CloseQuesReason"))
						wfQues.setCloseQuesReason(rs.getString("CloseQuesReason"));
					
					else if(_fields[i].equals("FractionDefective") || _fields[i].equals("WfQues.FractionDefective"))
						wfQues.setFractionDefective(rs.getString("FractionDefective"));
					
					else if(_fields[i].equals("TestItemID") || _fields[i].equals("WfQues.TestItemID"))
						wfQues.setTestItemID(rs.getString("TestItemID"));
					else if(_fields[i].equals("TestItemName") || _fields[i].equals("WfQues.TestItemName"))
						wfQues.setTestItemName(rs.getString("TestItemName"));
					
					else if(_fields[i].indexOf(" AS DeptNm")> -1 )
						wfQues.setDeptNm(rs.getString("DeptNm"));
					
				}
				list.add(wfQues);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfQuesHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfQues("+fields.replaceAll("WfQues\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfQues wfQues,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("QuesId") || _fields[i].equals("WfQues.QuesId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getQuesId());
					}
					else if(_fields[i].equals("ScheId") || _fields[i].equals("WfQues.ScheId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getScheId());
					}
					else if(_fields[i].equals("TaskId") || _fields[i].equals("WfQues.TaskId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getTaskId());
					}
					else if(_fields[i].equals("CateId") || _fields[i].equals("WfQues.CateId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getCateId());
					}
					else if(_fields[i].equals("ComId") || _fields[i].equals("WfQues.ComId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getComId());
					}
					else if(_fields[i].equals("DeptId") || _fields[i].equals("WfQues.DeptId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getDeptId());
					}
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfQues.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getUserId());
					}
					else if(_fields[i].equals("RyUsrs") || _fields[i].equals("WfQues.RyUsrs")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getRyUsrs());
					}
					else if(_fields[i].equals("Title") || _fields[i].equals("WfQues.Title")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getTitle());
					}
					else if(_fields[i].equals("QuesLevel") || _fields[i].equals("WfQues.QuesLevel")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getQuesLevel());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("WfQues.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfQues.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getCreateBy());
					}
					else if(_fields[i].equals("IdtfBy") || _fields[i].equals("WfQues.IdtfBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getIdtfBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfQues.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getLastUpd());
					}
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfQues.WfNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getWfNo());
					}
					else if(_fields[i].equals("IdtfRes") || _fields[i].equals("WfQues.IdtfRes")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getIdtfRes());
					}
					else if(_fields[i].equals("IsRisk") || _fields[i].equals("WfQues.IsRisk")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getIsRisk());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfQues.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfQues.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("IdtfDate") || _fields[i].equals("WfQues.IdtfDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfQues.getIdtfDate().getTime()));
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfQues.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfQues.getLastUpdDate().getTime()));
					}
					else if(_fields[i].equals("QuesText") || _fields[i].equals("WfQues.QuesText")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getQuesText());
					}
					else if(_fields[i].equals("Result") || _fields[i].equals("WfQues.Result")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getResult());
					}
					
					else if(_fields[i].equals("IdtfResFileNo") || _fields[i].equals("WfQues.IdtfResFileNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getIdtfResFileNo());
					}
					else if(_fields[i].equals("ResultFileNo") || _fields[i].equals("WfQues.ResultFileNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getResultFileNo());
					}
					else if(_fields[i].equals("IdtfResFileName") || _fields[i].equals("WfQues.IdtfResFileName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getIdtfResFileName());
					}
					else if(_fields[i].equals("ResultFileName") || _fields[i].equals("WfQues.ResultFileName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getResultFileName());
					}
					
					else if(_fields[i].equals("FileNo") || _fields[i].equals("WfQues.FileNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getFileNo());
					}
					else if(_fields[i].equals("FileName") || _fields[i].equals("WfQues.FileName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getFileName());
					}
					
					
					else if(_fields[i].equals("PrjtNo") || _fields[i].equals("WfQues.PrjtNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getPrjtNo());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("WfQues.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getRemark());
					}
					
					else if(_fields[i].equals("UsrName") || _fields[i].equals("WfQues.UsrName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getUsrName());
					}
					else if(_fields[i].equals("IsRiskQues") || _fields[i].equals("WfQues.IsRiskQues")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getIsRiskQues());
					}
					else if(_fields[i].equals("CompletedDate") || _fields[i].equals("WfQues.CompletedDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfQues.getCompletedDate().getTime()));
					}
					else if(_fields[i].equals("PicXY") || _fields[i].equals("WfQues.PicXY")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getPicXY());
					}
					else if(_fields[i].equals("CrDefectId") || _fields[i].equals("WfQues.CrDefectId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getCrDefectId());
					}
					
					else if(_fields[i].equals("SourceID") || _fields[i].equals("WfQues.SourceID")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getSourceID());
					}
					else if(_fields[i].equals("QuesTypeID") || _fields[i].equals("WfQues.QuesTypeID")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getQuesTypeID());
					}
					
					else if(_fields[i].equals("CloseQuesReason") || _fields[i].equals("WfQues.CloseQuesReason")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getCloseQuesReason());
					}
					
					else if(_fields[i].equals("FractionDefective") || _fields[i].equals("WfQues.FractionDefective")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getFractionDefective());
					}
					
					else if(_fields[i].equals("TestItemID") || _fields[i].equals("WfQues.TestItemID")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getTestItemID());
					}
					else if(_fields[i].equals("TestItemName") || _fields[i].equals("WfQues.TestItemName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getTestItemName());
					}
			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfQuesHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfQues set ";
		String[] _fields = fields.replaceAll("WfQues\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ScheId") || _fields[i].equals("WfQues.ScheId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("TaskId") || _fields[i].equals("WfQues.TaskId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CateId") || _fields[i].equals("WfQues.CateId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ComId") || _fields[i].equals("WfQues.ComId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("DeptId") || _fields[i].equals("WfQues.DeptId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("UserId") || _fields[i].equals("WfQues.UserId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Title") || _fields[i].equals("WfQues.Title"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("RyUsrs") || _fields[i].equals("WfQues.RyUsrs"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("QuesLevel") || _fields[i].equals("WfQues.QuesLevel"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("WfQues.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("WfQues.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("WfQues.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("WfNo") || _fields[i].equals("WfQues.WfNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsRisk") || _fields[i].equals("WfQues.IsRisk"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("WfQues.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfQues.LastUpdDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("QuesText") || _fields[i].equals("WfQues.QuesText"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Result") || _fields[i].equals("WfQues.Result"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IdtfBy") || _fields[i].equals("WfQues.IdtfBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IdtfRes") || _fields[i].equals("WfQues.IdtfRes"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IdtfDate") || _fields[i].equals("WfQues.IdtfDate"))
						sql += _fields[i] + " = ?,";

					
					if(_fields[i].equals("IdtfResFileNo") || _fields[i].equals("WfQues.IdtfResFileNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ResultFileNo") || _fields[i].equals("WfQues.ResultFileNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IdtfResFileName") || _fields[i].equals("WfQues.IdtfResFileName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ResultFileName") || _fields[i].equals("WfQues.ResultFileName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("FileNo") || _fields[i].equals("WfQues.FileNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("FileName") || _fields[i].equals("WfQues.FileName"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("PrjtNo") || _fields[i].equals("WfQues.PrjtNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("WfQues.Remark"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("UsrName") || _fields[i].equals("WfQues.UsrName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsRiskQues") || _fields[i].equals("WfQues.IsRiskQues"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IncludeQuesNum") || _fields[i].equals("WfQues.IncludeQuesNum"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("SolveQuesNum") || _fields[i].equals("WfQues.SolveQuesNum"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CompletedDate") || _fields[i].equals("WfQues.CompletedDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("PicXY") || _fields[i].equals("WfQues.PicXY"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CrDefectId") || _fields[i].equals("WfQues.CrDefectId"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("SourceID") || _fields[i].equals("WfQues.SourceID"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("QuesTypeID") || _fields[i].equals("WfQues.QuesTypeID"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("CloseQuesReason") || _fields[i].equals("WfQues.CloseQuesReason"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("FractionDefective") || _fields[i].equals("WfQues.FractionDefective"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("TestItemID") || _fields[i].equals("WfQues.TestItemID"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("TestItemName") || _fields[i].equals("WfQues.TestItemName"))
						sql += _fields[i] + " = ?,";
		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfQues wfQues,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ScheId") || _fields[i].equals("WfQues.ScheId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getScheId());
					}
					else if(_fields[i].equals("TaskId") || _fields[i].equals("WfQues.TaskId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getTaskId());
					}
					else if(_fields[i].equals("CateId") || _fields[i].equals("WfQues.CateId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getCateId());
					}
					else if(_fields[i].equals("ComId") || _fields[i].equals("WfQues.ComId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getComId());
					}
					else if(_fields[i].equals("DeptId") || _fields[i].equals("WfQues.DeptId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getDeptId());
					}
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfQues.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getUserId());
					}
					else if(_fields[i].equals("RyUsrs") || _fields[i].equals("WfQues.RyUsrs")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getRyUsrs());
					}
					else if(_fields[i].equals("Title") || _fields[i].equals("WfQues.Title")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getTitle());
					}
					else if(_fields[i].equals("QuesLevel") || _fields[i].equals("WfQues.QuesLevel")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getQuesLevel());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("WfQues.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfQues.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfQues.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getLastUpd());
					}
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfQues.WfNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getWfNo());
					}
					else if(_fields[i].equals("IsRisk") || _fields[i].equals("WfQues.IsRisk")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getIsRisk());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfQues.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfQues.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfQues.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfQues.getLastUpdDate().getTime()));
					}
					else if(_fields[i].equals("QuesText") || _fields[i].equals("WfQues.QuesText")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getQuesText());
					}
					else if(_fields[i].equals("Result") || _fields[i].equals("WfQues.Result")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getResult());
					}
					else if(_fields[i].equals("IdtfBy") || _fields[i].equals("WfQues.IdtfBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getIdtfBy());
					}
					else if(_fields[i].equals("IdtfRes") || _fields[i].equals("WfQues.IdtfRes")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getIdtfRes());
					}
					else if(_fields[i].equals("IdtfDate") || _fields[i].equals("WfQues.IdtfDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfQues.getIdtfDate().getTime()));
					}
					
					else if(_fields[i].equals("IdtfResFileNo") || _fields[i].equals("WfQues.IdtfResFileNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getIdtfResFileNo());
					}
					else if(_fields[i].equals("ResultFileNo") || _fields[i].equals("WfQues.ResultFileNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getResultFileNo());
					}
					
					else if(_fields[i].equals("IdtfResFileName") || _fields[i].equals("WfQues.IdtfResFileName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getIdtfResFileName());
					}
					else if(_fields[i].equals("ResultFileName") || _fields[i].equals("WfQues.ResultFileName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getResultFileName());
					}
					
					else if(_fields[i].equals("FileNo") || _fields[i].equals("WfQues.FileNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getFileNo());
					}
					else if(_fields[i].equals("FileName") || _fields[i].equals("WfQues.FileName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getFileName());
					}
					else if(_fields[i].equals("PrjtNo") || _fields[i].equals("WfQues.PrjtNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getPrjtNo());
					}
					
					else if(_fields[i].equals("Remark") || _fields[i].equals("WfQues.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getRemark());
					}
					
					else if(_fields[i].equals("UsrName") || _fields[i].equals("WfQues.UsrName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getUsrName());
					}
					else if(_fields[i].equals("IsRiskQues") || _fields[i].equals("WfQues.IsRiskQues")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getIsRiskQues());
					}
					else if(_fields[i].equals("CompletedDate") || _fields[i].equals("WfQues.CompletedDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfQues.getCompletedDate().getTime()));
					}
					else if(_fields[i].equals("PicXY") || _fields[i].equals("WfQues.PicXY")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getPicXY());
					}
					else if(_fields[i].equals("CrDefectId") || _fields[i].equals("WfQues.CrDefectId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getCrDefectId());
					}
					
					else if(_fields[i].equals("SourceID") || _fields[i].equals("WfQues.SourceID")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getSourceID());
					}
					else if(_fields[i].equals("QuesTypeID") || _fields[i].equals("WfQues.QuesTypeID")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQues.getQuesTypeID());
					}
					else if(_fields[i].equals("CloseQuesReason") || _fields[i].equals("WfQues.CloseQuesReason")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getCloseQuesReason());
					}
					
					else if(_fields[i].equals("FractionDefective") || _fields[i].equals("WfQues.FractionDefective")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getFractionDefective());
					}
					
					else if(_fields[i].equals("TestItemID") || _fields[i].equals("WfQues.TestItemID")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getTestItemID());
					}
					else if(_fields[i].equals("TestItemName") || _fields[i].equals("WfQues.TestItemName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQues.getTestItemName());
					}
			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfQuesHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfQues wfQues) {
		String _fields = "";
		if(null != wfQues.getQuesId())
			_fields += "WfQues.QuesId,";
		if(null != wfQues.getScheId())
			_fields += "WfQues.ScheId,";
		if(null != wfQues.getTaskId())
			_fields += "WfQues.TaskId,";
		if(null != wfQues.getCateId())
			_fields += "WfQues.CateId,";
		if(null != wfQues.getComId())
			_fields += "WfQues.ComId,";
		if(null != wfQues.getDeptId())
			_fields += "WfQues.DeptId,";
		if(null != wfQues.getUserId())
			_fields += "WfQues.UserId,";
		if(null != wfQues.getTitle())
			_fields += "WfQues.Title,";
		if(null != wfQues.getRyUsrs())
			_fields += "WfQues.RyUsrs,";
		if(null != wfQues.getQuesLevel())
			_fields += "WfQues.QuesLevel,";
		if(null != wfQues.getStatus())
			_fields += "WfQues.Status,";
		if(null != wfQues.getCreateBy())
			_fields += "WfQues.CreateBy,";
		if(null != wfQues.getLastUpd())
			_fields += "WfQues.LastUpd,";
		if(null != wfQues.getWfNo())
			_fields += "WfQues.WfNo,";
		if(null != wfQues.getIsRisk())
			_fields += "WfQues.IsRisk,";
		if(null != wfQues.getCreateDate())
			_fields += "WfQues.CreateDate,";
		if(null != wfQues.getLastUpdDate())
			_fields += "WfQues.LastUpdDate,";
		if(null != wfQues.getQuesText())
			_fields += "WfQues.QuesText,";
		if(null != wfQues.getResult())
			_fields += "WfQues.Result,";
		if(null != wfQues.getIdtfBy())
			_fields += "WfQues.IdtfBy,";
		if(null != wfQues.getIdtfRes())
			_fields += "WfQues.IdtfRes,";
		if(null != wfQues.getIdtfDate())
			_fields += "WfQues.IdtfDate,";
		
		if(null != wfQues.getIdtfResFileNo())
			_fields += "WfQues.IdtfResFileNo,";
		if(null != wfQues.getResultFileNo())
			_fields += "WfQues.ResultFileNo,";
		if(null != wfQues.getIdtfResFileName())
			_fields += "WfQues.IdtfResFileName,";
		if(null != wfQues.getResultFileName())
			_fields += "WfQues.ResultFileName,";
		if(null != wfQues.getFileNo())
			_fields += "WfQues.FileNo,";
		if(null != wfQues.getFileName())
			_fields += "WfQues.FileName,";
		if(null != wfQues.getPrjtNo())
			_fields += "WfQues.PrjtNo,";
		if(null != wfQues.getRemark())
			_fields += "WfQues.Remark,";
		
		if(null != wfQues.getUsrName())
			_fields += "WfQues.UsrName,";
		
		if(null != wfQues.getIsRiskQues())
			_fields += "WfQues.IsRiskQues,";

		if(null != wfQues.getCompletedDate())
			_fields += "WfQues.CompletedDate,";
		if(null != wfQues.getPicXY())
			_fields += "WfQues.PicXY,";
		if(null != wfQues.getCrDefectId())
			_fields += "WfQues.CrDefectId,";
		
		if(null != wfQues.getSourceID())
			_fields += "WfQues.SourceID,";
		if(null != wfQues.getQuesTypeID())
			_fields += "WfQues.QuesTypeID,";
		if(null != wfQues.getCloseQuesReason())
			_fields += "WfQues.CloseQuesReason,";
		if(null != wfQues.getFractionDefective())
			_fields += "WfQues.FractionDefective,";
			
		if(null != wfQues.getTestItemID())
			_fields += "WfQues.TestItemID,";
		if(null != wfQues.getTestItemName())
			_fields += "WfQues.TestItemName,";
		return _fields.substring(0, _fields.length()-1);
	}
}