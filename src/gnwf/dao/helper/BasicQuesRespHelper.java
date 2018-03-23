package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.QuesResp;

public class BasicQuesRespHelper implements SqlHelper {
	public String getSqlString() {
		return " from QuesResp where 1=1";
	}

	public String getOrderBy() {
		return " order by QuesResp.QuesId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((QuesResp)object);
	}

	public String getSql4Amount(QuesResp quesResp) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(quesResp);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((QuesResp)object);
	}

	public String getSql4Delete(QuesResp quesResp) {
		return "delete from QuesResp where 1=1"+getSqlCondition(quesResp);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((QuesResp)object,fields);
	}

	public String getSql4All(QuesResp quesResp, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(quesResp)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((QuesResp)object,pageVO,fields);
	}

	public String getSql4Pages(QuesResp quesResp,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" QuesResp.QuesId "+ getSqlString()+getSqlCondition(quesResp)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(quesResp)+" and QuesResp.QuesId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((QuesResp)object);
	}

	public String getSqlCondition(QuesResp quesResp) {
		String sql = "";
		if(null != quesResp.getStartLastUpdDate()) 
			sql += " and QuesResp.LastUpdDate >= '"+GenericUtil.dateFormat(quesResp.getStartLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != quesResp.getEndLastUpdDate()) 
			sql += " and QuesResp.LastUpdDate <= '"+GenericUtil.dateFormat(quesResp.getEndLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != quesResp.getCreateBy())
			sql += " and QuesResp.CreateBy = '"+quesResp.getCreateBy()+"'";
		if(null != quesResp.getResultFileNo() && !quesResp.getResultFileNo().trim().equals(""))
			sql += " and QuesResp.ResultFileNo = '"+quesResp.getResultFileNo().trim()+"'";
		if(null != quesResp.getLastUpd())
			sql += " and QuesResp.LastUpd = '"+quesResp.getLastUpd()+"'";
		if(null != quesResp.getQuesId())
			sql += " and QuesResp.QuesId = '"+quesResp.getQuesId()+"'";
		if(null != quesResp.getStatus())
			sql += " and QuesResp.Status = '"+quesResp.getStatus()+"'";
		if(null != quesResp.getResult() && !quesResp.getResult().trim().equals(""))
			sql += " and QuesResp.Result = '"+quesResp.getResult().trim()+"'";
		
		//原因分析
		if(null != quesResp.getQuesAnalysis() && !quesResp.getQuesAnalysis().trim().equals(""))
			sql += " and QuesResp.QuesAnalysis = '"+quesResp.getQuesAnalysis().trim()+"'";
		
		if(null != quesResp.getResultFileName() && !quesResp.getResultFileName().trim().equals(""))
			sql += " and QuesResp.ResultFileName = '"+quesResp.getResultFileName().trim()+"'";
		if(null != quesResp.getUsrId())
			sql += " and QuesResp.UsrId = '"+quesResp.getUsrId()+"'";
		if(null != quesResp.getStartCreateDate()) 
			sql += " and QuesResp.CreateDate >= '"+GenericUtil.dateFormat(quesResp.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != quesResp.getEndCreateDate()) 
			sql += " and QuesResp.CreateDate <= '"+GenericUtil.dateFormat(quesResp.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != quesResp.getIdtfRes() && !quesResp.getIdtfRes().trim().equals(""))
			sql += " and QuesResp.IdtfRes = '"+quesResp.getIdtfRes().trim()+"'";
		if(null != quesResp.getIdtfBy())
			sql += " and QuesResp.IdtfBy = '"+quesResp.getIdtfBy()+"'";
		if(null != quesResp.getIdtfResFileName() && !quesResp.getIdtfResFileName().trim().equals(""))
			sql += " and QuesResp.IdtfResFileName = '"+quesResp.getIdtfResFileName().trim()+"'";
		if(null != quesResp.getIdtfResFileNo() && !quesResp.getIdtfResFileNo().trim().equals(""))
			sql += " and QuesResp.IdtfResFileNo = '"+quesResp.getIdtfResFileNo().trim()+"'";
		if(null != quesResp.getStartIdtfDate()) 
			sql += " and QuesResp.IdtfDate >= '"+GenericUtil.dateFormat(quesResp.getStartIdtfDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != quesResp.getEndIdtfDate()) 
			sql += " and QuesResp.IdtfDate <= '"+GenericUtil.dateFormat(quesResp.getEndIdtfDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != quesResp.getStartResultDate()) 
			sql += " and QuesResp.ResultDate >= '"+GenericUtil.dateFormat(quesResp.getStartResultDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != quesResp.getEndResultDate()) 
			sql += " and QuesResp.ResultDate <= '"+GenericUtil.dateFormat(quesResp.getEndResultDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != quesResp.getId())
			sql += " and QuesResp.Id = '"+quesResp.getId()+"'";
		if(null != quesResp.getRemarkFileNo() && !quesResp.getRemarkFileNo().trim().equals(""))
			sql += " and QuesResp.RemarkFileNo = '"+quesResp.getRemarkFileNo().trim()+"'";
		if(null != quesResp.getRespType())
			sql += " and QuesResp.RespType = '"+quesResp.getRespType()+"'";
		if(null != quesResp.getRemarkFileName() && !quesResp.getRemarkFileName().trim().equals(""))
			sql += " and QuesResp.RemarkFileName = '"+quesResp.getRemarkFileName().trim()+"'";
		if(null != quesResp.getRemark() && !quesResp.getRemark().trim().equals(""))
			sql += " and QuesResp.Remark = '"+quesResp.getRemark().trim()+"'";
		if(null != quesResp.getRushDate())
			sql += " and QuesResp.RushDate <= '"+GenericUtil.dateFormat(quesResp.getRushDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != quesResp.getSolveNum())
			sql += " and QuesResp.SolveNum = '"+quesResp.getSolveNum()+"'";
		return sql;
	}

	public List<QuesResp> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<QuesResp> list = new ArrayList<QuesResp>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				QuesResp quesResp = new QuesResp();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("QuesResp.LastUpdDate"))
						quesResp.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("QuesResp.CreateBy"))
						quesResp.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("ResultFileNo") || _fields[i].equals("QuesResp.ResultFileNo"))
						quesResp.setResultFileNo(rs.getString("ResultFileNo"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("QuesResp.LastUpd"))
						quesResp.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("QuesId") || _fields[i].equals("QuesResp.QuesId"))
						quesResp.setQuesId(rs.getString("QuesId"));
					else if(_fields[i].equals("Status") || _fields[i].equals("QuesResp.Status"))
						quesResp.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("Result") || _fields[i].equals("QuesResp.Result"))
						quesResp.setResult(rs.getString("Result"));
					
					else if(_fields[i].equals("QuesAnalysis") || _fields[i].equals("QuesResp.QuesAnalysis"))
						quesResp.setQuesAnalysis(rs.getString("QuesAnalysis"));
					
					else if(_fields[i].equals("ResultFileName") || _fields[i].equals("QuesResp.ResultFileName"))
						quesResp.setResultFileName(rs.getString("ResultFileName"));
					else if(_fields[i].equals("UsrId") || _fields[i].equals("QuesResp.UsrId"))
						quesResp.setUsrId(rs.getInt("UsrId"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("QuesResp.CreateDate"))
						quesResp.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("IdtfRes") || _fields[i].equals("QuesResp.IdtfRes"))
						quesResp.setIdtfRes(rs.getString("IdtfRes"));
					else if(_fields[i].equals("IdtfBy") || _fields[i].equals("QuesResp.IdtfBy"))
						quesResp.setIdtfBy(rs.getInt("IdtfBy"));
					else if(_fields[i].equals("IdtfResFileName") || _fields[i].equals("QuesResp.IdtfResFileName"))
						quesResp.setIdtfResFileName(rs.getString("IdtfResFileName"));
					else if(_fields[i].equals("IdtfResFileNo") || _fields[i].equals("QuesResp.IdtfResFileNo"))
						quesResp.setIdtfResFileNo(rs.getString("IdtfResFileNo"));
					else if(_fields[i].equals("IdtfDate") || _fields[i].equals("QuesResp.IdtfDate"))
						quesResp.setIdtfDate(rs.getTimestamp("IdtfDate"));
					else if(_fields[i].equals("ResultDate") || _fields[i].equals("QuesResp.ResultDate"))
						quesResp.setResultDate(rs.getTimestamp("ResultDate"));
					else if(_fields[i].equals("Id") || _fields[i].equals("QuesResp.Id"))
						quesResp.setId(rs.getInt("Id"));
					else if(_fields[i].equals("RemarkFileNo") || _fields[i].equals("QuesResp.RemarkFileNo"))
						quesResp.setRemarkFileNo(rs.getString("RemarkFileNo"));
					else if(_fields[i].equals("RespType") || _fields[i].equals("QuesResp.RespType"))
						quesResp.setRespType(rs.getInt("RespType"));
					else if(_fields[i].equals("RemarkFileName") || _fields[i].equals("QuesResp.RemarkFileName"))
						quesResp.setRemarkFileName(rs.getString("RemarkFileName"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("QuesResp.Remark"))
						quesResp.setRemark(rs.getString("Remark"));
					else if(_fields[i].equals("RushDate") || _fields[i].equals("QuesResp.RushDate"))
						quesResp.setRushDate(rs.getTimestamp("RushDate"));
					else if(_fields[i].equals("SolveNum") || _fields[i].equals("QuesResp.SolveNum"))
						quesResp.setSolveNum(rs.getInt("SolveNum"));
				}
				list.add(quesResp);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("QuesRespHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into QuesResp("+fields.replaceAll("QuesResp\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(QuesResp quesResp,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("QuesResp.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(quesResp.getLastUpdDate().getTime()));
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("QuesResp.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, quesResp.getCreateBy());
					}
					else if(_fields[i].equals("ResultFileNo") || _fields[i].equals("QuesResp.ResultFileNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, quesResp.getResultFileNo());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("QuesResp.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, quesResp.getLastUpd());
					}
					else if(_fields[i].equals("QuesId") || _fields[i].equals("QuesResp.QuesId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, quesResp.getQuesId());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("QuesResp.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, quesResp.getStatus());
					}
					else if(_fields[i].equals("Result") || _fields[i].equals("QuesResp.Result")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, quesResp.getResult());
					}
					
					else if(_fields[i].equals("QuesAnalysis") || _fields[i].equals("QuesResp.QuesAnalysis")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, quesResp.getQuesAnalysis());
					}
					
					
					else if(_fields[i].equals("ResultFileName") || _fields[i].equals("QuesResp.ResultFileName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, quesResp.getResultFileName());
					}
					else if(_fields[i].equals("UsrId") || _fields[i].equals("QuesResp.UsrId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, quesResp.getUsrId());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("QuesResp.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(quesResp.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("IdtfRes") || _fields[i].equals("QuesResp.IdtfRes")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, quesResp.getIdtfRes());
					}
					else if(_fields[i].equals("IdtfBy") || _fields[i].equals("QuesResp.IdtfBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, quesResp.getIdtfBy());
					}
					else if(_fields[i].equals("IdtfResFileName") || _fields[i].equals("QuesResp.IdtfResFileName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, quesResp.getIdtfResFileName());
					}
					else if(_fields[i].equals("IdtfResFileNo") || _fields[i].equals("QuesResp.IdtfResFileNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, quesResp.getIdtfResFileNo());
					}
					else if(_fields[i].equals("IdtfDate") || _fields[i].equals("QuesResp.IdtfDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(quesResp.getIdtfDate().getTime()));
					}
					else if(_fields[i].equals("ResultDate") || _fields[i].equals("QuesResp.ResultDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(quesResp.getResultDate().getTime()));
					}
					else if(_fields[i].equals("Id") || _fields[i].equals("QuesResp.Id")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, quesResp.getId());
					}
					else if(_fields[i].equals("RemarkFileNo") || _fields[i].equals("QuesResp.RemarkFileNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, quesResp.getRemarkFileNo());
					}
					else if(_fields[i].equals("RespType") || _fields[i].equals("QuesResp.RespType")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, quesResp.getRespType());
					}
					else if(_fields[i].equals("RemarkFileName") || _fields[i].equals("QuesResp.RemarkFileName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, quesResp.getRemarkFileName());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("QuesResp.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, quesResp.getRemark());
					}
					else if(_fields[i].equals("RushDate") || _fields[i].equals("QuesResp.RushDate")){
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(quesResp.getRushDate().getTime()));
					}
					else if(_fields[i].equals("SolveNum") || _fields[i].equals("QuesResp.SolveNum")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, quesResp.getSolveNum());
					}	

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("QuesRespHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update QuesResp set ";
		String[] _fields = fields.replaceAll("QuesResp\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("QuesResp.LastUpdDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("QuesResp.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ResultFileNo") || _fields[i].equals("QuesResp.ResultFileNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("QuesResp.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("QuesResp.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Result") || _fields[i].equals("QuesResp.Result"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("QuesAnalysis") || _fields[i].equals("QuesResp.QuesAnalysis"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("ResultFileName") || _fields[i].equals("QuesResp.ResultFileName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("QuesResp.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IdtfRes") || _fields[i].equals("QuesResp.IdtfRes"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IdtfBy") || _fields[i].equals("QuesResp.IdtfBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IdtfResFileName") || _fields[i].equals("QuesResp.IdtfResFileName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IdtfResFileNo") || _fields[i].equals("QuesResp.IdtfResFileNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IdtfDate") || _fields[i].equals("QuesResp.IdtfDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ResultDate") || _fields[i].equals("QuesResp.ResultDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("RemarkFileNo") || _fields[i].equals("QuesResp.RemarkFileNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("RespType") || _fields[i].equals("QuesResp.RespType"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("RemarkFileName") || _fields[i].equals("QuesResp.RemarkFileName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("QuesResp.Remark"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("RushDate") || _fields[i].equals("QuesResp.RushDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("SolveNum") || _fields[i].equals("QuesResp.SolveNum"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(QuesResp quesResp,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("QuesResp.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(quesResp.getLastUpdDate().getTime()));
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("QuesResp.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, quesResp.getCreateBy());
					}
					else if(_fields[i].equals("ResultFileNo") || _fields[i].equals("QuesResp.ResultFileNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, quesResp.getResultFileNo());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("QuesResp.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, quesResp.getLastUpd());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("QuesResp.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, quesResp.getStatus());
					}
					else if(_fields[i].equals("Result") || _fields[i].equals("QuesResp.Result")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, quesResp.getResult());
					}
					
					else if(_fields[i].equals("QuesAnalysis") || _fields[i].equals("QuesResp.QuesAnalysis")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, quesResp.getQuesAnalysis());
					}
					
					else if(_fields[i].equals("ResultFileName") || _fields[i].equals("QuesResp.ResultFileName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, quesResp.getResultFileName());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("QuesResp.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(quesResp.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("IdtfRes") || _fields[i].equals("QuesResp.IdtfRes")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, quesResp.getIdtfRes());
					}
					else if(_fields[i].equals("IdtfBy") || _fields[i].equals("QuesResp.IdtfBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, quesResp.getIdtfBy());
					}
					else if(_fields[i].equals("IdtfResFileName") || _fields[i].equals("QuesResp.IdtfResFileName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, quesResp.getIdtfResFileName());
					}
					else if(_fields[i].equals("IdtfResFileNo") || _fields[i].equals("QuesResp.IdtfResFileNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, quesResp.getIdtfResFileNo());
					}
					else if(_fields[i].equals("IdtfDate") || _fields[i].equals("QuesResp.IdtfDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(quesResp.getIdtfDate().getTime()));
					}
					else if(_fields[i].equals("ResultDate") || _fields[i].equals("QuesResp.ResultDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(quesResp.getResultDate().getTime()));
					}
					else if(_fields[i].equals("RemarkFileNo") || _fields[i].equals("QuesResp.RemarkFileNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, quesResp.getRemarkFileNo());
					}
					else if(_fields[i].equals("RespType") || _fields[i].equals("QuesResp.RespType")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, quesResp.getRespType());
					}
					else if(_fields[i].equals("RemarkFileName") || _fields[i].equals("QuesResp.RemarkFileName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, quesResp.getRemarkFileName());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("QuesResp.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, quesResp.getRemark());
					}
					else if(_fields[i].equals("RushDate") || _fields[i].equals("QuesResp.RushDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(quesResp.getRushDate().getTime()));
					}
					else if(_fields[i].equals("SolveNum") || _fields[i].equals("QuesResp.SolveNum")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, quesResp.getSolveNum());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("QuesRespHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(QuesResp quesResp) {
		String _fields = "";
		if(null != quesResp.getLastUpdDate())
			_fields += "QuesResp.LastUpdDate,";
		if(null != quesResp.getCreateBy())
			_fields += "QuesResp.CreateBy,";
		if(null != quesResp.getResultFileNo())
			_fields += "QuesResp.ResultFileNo,";
		if(null != quesResp.getLastUpd())
			_fields += "QuesResp.LastUpd,";
		if(null != quesResp.getQuesId())
			_fields += "QuesResp.QuesId,";
		if(null != quesResp.getStatus())
			_fields += "QuesResp.Status,";
		if(null != quesResp.getResult())
			_fields += "QuesResp.Result,";
		
		if(null != quesResp.getQuesAnalysis())
			_fields += "QuesResp.QuesAnalysis,";
		
		if(null != quesResp.getResultFileName())
			_fields += "QuesResp.ResultFileName,";
		if(null != quesResp.getUsrId())
			_fields += "QuesResp.UsrId,";
		if(null != quesResp.getCreateDate())
			_fields += "QuesResp.CreateDate,";
		if(null != quesResp.getIdtfRes())
			_fields += "QuesResp.IdtfRes,";
		if(null != quesResp.getIdtfBy())
			_fields += "QuesResp.IdtfBy,";
		if(null != quesResp.getIdtfResFileName())
			_fields += "QuesResp.IdtfResFileName,";
		if(null != quesResp.getIdtfResFileNo())
			_fields += "QuesResp.IdtfResFileNo,";
		if(null != quesResp.getIdtfDate())
			_fields += "QuesResp.IdtfDate,";
		if(null != quesResp.getResultDate())
			_fields += "QuesResp.ResultDate,";
		if(null != quesResp.getId())
			_fields += "QuesResp.Id,";
		if(null != quesResp.getRemarkFileNo())
			_fields += "QuesResp.RemarkFileNo,";
		if(null != quesResp.getRespType())
			_fields += "QuesResp.RespType,";
		if(null != quesResp.getRemarkFileName())
			_fields += "QuesResp.RemarkFileName,";
		if(null != quesResp.getRemark())
			_fields += "QuesResp.Remark,";
		if(null != quesResp.getRushDate())
			_fields += "QuesResp.RushDate,";
		if(null != quesResp.getSolveNum())
			_fields += "QuesResp.SolveNum,";

		return _fields.substring(0, _fields.length()-1);
	}
}