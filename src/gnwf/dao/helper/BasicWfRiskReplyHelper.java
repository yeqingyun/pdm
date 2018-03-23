package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfRiskReply;

public class BasicWfRiskReplyHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfRiskReply where 1=1";
	}

	public String getOrderBy() {
		return " order by WfRiskReply.RiskId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfRiskReply)object);
	}

	public String getSql4Amount(WfRiskReply wfRiskReply) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfRiskReply);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfRiskReply)object);
	}

	public String getSql4Delete(WfRiskReply wfRiskReply) {
		return "delete from WfRiskReply where 1=1"+getSqlCondition(wfRiskReply);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfRiskReply)object,fields);
	}

	public String getSql4All(WfRiskReply wfRiskReply, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfRiskReply)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfRiskReply)object,pageVO,fields);
	}

	public String getSql4Pages(WfRiskReply wfRiskReply,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfRiskReply.RiskId "+ getSqlString()+getSqlCondition(wfRiskReply)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfRiskReply)+" and WfRiskReply.RiskId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfRiskReply)object);
	}

	public String getSqlCondition(WfRiskReply wfRiskReply) {
		String sql = "";
		if(null != wfRiskReply.getId())
			sql += " and WfRiskReply.Id = '"+wfRiskReply.getId()+"'";
		if(null != wfRiskReply.getResponsibleUserId())
			sql += " and WfRiskReply.ResponsibleUserId = '"+wfRiskReply.getResponsibleUserId()+"'";
		if(null != wfRiskReply.getMeasures() && !wfRiskReply.getMeasures().trim().equals(""))
			sql += " and WfRiskReply.Measures = '"+wfRiskReply.getMeasures().trim()+"'";
		if(null != wfRiskReply.getMeasuresFileNo() && !wfRiskReply.getMeasuresFileNo().trim().equals(""))
			sql += " and WfRiskReply.MeasuresFileNo = '"+wfRiskReply.getMeasuresFileNo().trim()+"'";
		if(null != wfRiskReply.getMeasuresFileName() && !wfRiskReply.getMeasuresFileName().trim().equals(""))
			sql += " and WfRiskReply.MeasuresFileName = '"+wfRiskReply.getMeasuresFileName().trim()+"'";
		if(null != wfRiskReply.getStatus())
			sql += " and WfRiskReply.Status = '"+wfRiskReply.getStatus()+"'";
		if(null != wfRiskReply.getVerifyUserId())
			sql += " and WfRiskReply.VerifyUserId = '"+wfRiskReply.getVerifyUserId()+"'";
		if(null != wfRiskReply.getVerifyResult() && !wfRiskReply.getVerifyResult().trim().equals(""))
			sql += " and WfRiskReply.VerifyResult = '"+wfRiskReply.getVerifyResult().trim()+"'";
		if(null != wfRiskReply.getVerifyFileNo() && !wfRiskReply.getVerifyFileNo().trim().equals(""))
			sql += " and WfRiskReply.VerifyFileNo = '"+wfRiskReply.getVerifyFileNo().trim()+"'";
		if(null != wfRiskReply.getVerifyFileName() && !wfRiskReply.getVerifyFileName().trim().equals(""))
			sql += " and WfRiskReply.VerifyFileName = '"+wfRiskReply.getVerifyFileName().trim()+"'";
		if(null != wfRiskReply.getCreateUserId()) 
			sql += " and WfRiskReply.CreateUserId = '"+wfRiskReply.getCreateUserId()+"'";
		if(null != wfRiskReply.getLastUpdateUserId())
			sql += " and WfRiskReply.LastUpdateUserId = '"+wfRiskReply.getLastUpdateUserId()+"'";
		if(null != wfRiskReply.getRemark() && !wfRiskReply.getRemark().trim().equals(""))
			sql += " and WfRiskReply.Remark = '"+wfRiskReply.getRemark().trim()+"'";
		if(null != wfRiskReply.getRiskId())
			sql += " and wfRiskReply.RiskId = '"+wfRiskReply.getRiskId()+"'";
		return sql;
	}

	public List<WfRiskReply> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfRiskReply> list = new ArrayList<WfRiskReply>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfRiskReply wfRiskReply = new WfRiskReply();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("WfRiskReply.Id"))
						wfRiskReply.setId(rs.getInt("Id"));
					else if(_fields[i].equals("ResponsibleUserId") || _fields[i].equals("WfRiskReply.ResponsibleUserId"))
						wfRiskReply.setResponsibleUserId(rs.getInt("ResponsibleUserId"));
					else if(_fields[i].equals("Measures") || _fields[i].equals("WfRiskReply.Measures"))
						wfRiskReply.setMeasures(rs.getString("Measures"));
					else if(_fields[i].equals("MeasuresFileNo") || _fields[i].equals("WfRiskReply.MeasuresFileNo"))
						wfRiskReply.setMeasuresFileNo(rs.getString("MeasuresFileNo"));
					else if(_fields[i].equals("MeasuresFileName") || _fields[i].equals("WfRiskReply.MeasuresFileName"))
						wfRiskReply.setMeasuresFileName(rs.getString("MeasuresFileName"));
					else if(_fields[i].equals("MeasuresDate") || _fields[i].equals("WfRiskReply.MeasuresDate"))
						wfRiskReply.setMeasuresDate(rs.getTimestamp("MeasuresDate"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfRiskReply.Status"))
						wfRiskReply.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("VerifyUserId") || _fields[i].equals("WfRiskReply.VerifyUserId"))
						wfRiskReply.setVerifyUserId(rs.getInt("VerifyUserId"));
					else if(_fields[i].equals("VerifyResult") || _fields[i].equals("WfRiskReply.VerifyResult"))
						wfRiskReply.setVerifyResult(rs.getString("VerifyResult"));
					else if(_fields[i].equals("VerifyDate") || _fields[i].equals("WfRiskReply.VerifyDate"))
						wfRiskReply.setVerifyDate(rs.getTimestamp("VerifyDate"));
					else if(_fields[i].equals("VerifyFileNo") || _fields[i].equals("WfRiskReply.VerifyFileNo"))
						wfRiskReply.setVerifyFileNo(rs.getString("VerifyFileNo"));
					else if(_fields[i].equals("VerifyFileName") || _fields[i].equals("WfRiskReply.VerifyFileName"))
						wfRiskReply.setVerifyFileName(rs.getString("VerifyFileName"));
					else if(_fields[i].equals("CreateUserId") || _fields[i].equals("WfRiskReply.CreateUserId"))
						wfRiskReply.setCreateUserId(rs.getInt("CreateUserId"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRiskReply.CreateDate"))
						wfRiskReply.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpdate") || _fields[i].equals("WfRiskReply.LastUpdate"))
						wfRiskReply.setLastUpdate(rs.getTimestamp("LastUpdate"));
					else if(_fields[i].equals("LastUpdateUserId") || _fields[i].equals("WfRiskReply.LastUpdateUserId"))
						wfRiskReply.setLastUpdateUserId(rs.getInt("LastUpdateUserId"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("WfRiskReply.Remark"))
						wfRiskReply.setRemark(rs.getString("Remark"));
					else if(_fields[i].equals("RiskId") || _fields[i].equals("WfRiskReply.RiskId"))
						wfRiskReply.setRiskId(rs.getString("RiskId"));
					else if(_fields[i].equals("RushDate") || _fields[i].equals("WfRiskReply.RushDate"))
						wfRiskReply.setRushDate(rs.getTimestamp("RushDate"));
				}
				list.add(wfRiskReply);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRiskReplyHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfRiskReply("+fields.replaceAll("WfRiskReply\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfRiskReply wfRiskReply,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
				if(_fields[i].equals("Id") || _fields[i].equals("WfRiskReply.Id")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRiskReply.getId());
				}
				else if(_fields[i].equals("ResponsibleUserId") || _fields[i].equals("WfRiskReply.ResponsibleUserId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRiskReply.getResponsibleUserId());
				}
				else if(_fields[i].equals("Measures") || _fields[i].equals("WfRiskReply.Measures")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRiskReply.getMeasures());
				}
				else if(_fields[i].equals("MeasuresFileNo") || _fields[i].equals("WfRiskReply.MeasuresFileNo")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRiskReply.getMeasuresFileNo());
				}
				else if(_fields[i].equals("MeasuresFileName") || _fields[i].equals("WfRiskReply.MeasuresFileName")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRiskReply.getMeasuresFileName());
				}
				else if(_fields[i].equals("MeasuresDate") || _fields[i].equals("WfRiskReply.MeasuresDate")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new Timestamp(wfRiskReply.getMeasuresDate().getTime()));
				}
				else if(_fields[i].equals("Status") || _fields[i].equals("WfRiskReply.Status")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRiskReply.getStatus());
				}
				else if(_fields[i].equals("VerifyUserId") || _fields[i].equals("WfRiskReply.VerifyUserId")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRiskReply.getVerifyUserId());
				}
				else if(_fields[i].equals("VerifyResult") || _fields[i].equals("WfRiskReply.VerifyResult")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRiskReply.getVerifyResult());
				}
				else if(_fields[i].equals("VerifyDate") || _fields[i].equals("WfRiskReply.VerifyDate")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new Timestamp(wfRiskReply.getVerifyDate().getTime()));
				}
				else if(_fields[i].equals("VerifyFileNo") || _fields[i].equals("WfRiskReply.VerifyFileNo")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRiskReply.getVerifyFileNo());
				}
				else if(_fields[i].equals("VerifyFileName") || _fields[i].equals("WfRiskReply.VerifyFileName")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRiskReply.getVerifyFileName());
				}
				else if(_fields[i].equals("CreateUserId") || _fields[i].equals("WfRiskReply.CreateUserId")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRiskReply.getCreateUserId());
				}
				else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRiskReply.CreateDate")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new Timestamp(wfRiskReply.getCreateDate().getTime()));
				}
				else if(_fields[i].equals("LastUpdate") || _fields[i].equals("WfRiskReply.LastUpdate")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new Timestamp(wfRiskReply.getLastUpdate().getTime()));
				}
				else if(_fields[i].equals("LastUpdateUserId") || _fields[i].equals("WfRiskReply.LastUpdateUserId")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRiskReply.getLastUpdateUserId());
				}
				else if(_fields[i].equals("Remark") || _fields[i].equals("WfRiskReply.Remark")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRiskReply.getRemark());
				}
				else if(_fields[i].equals("RiskId") || _fields[i].equals("WfRiskReply.RiskId")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRiskReply.getRiskId());
				}
				else if(_fields[i].equals("RushDate") || _fields[i].equals("WfRiskReply.RushDate")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new Timestamp(wfRiskReply.getRushDate().getTime()));
				}
			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfRiskReplyHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfRiskReply set ";
		String[] _fields = fields.replaceAll("WfRiskReply\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
			if(_fields[i].equals("ResponsibleUserId") || _fields[i].equals("WfRiskReply.ResponsibleUserId"))
				sql += _fields[i] + " = ?,";
			else if(_fields[i].equals("Measures") || _fields[i].equals("WfRiskReply.Measures"))
				sql += _fields[i] + " = ?,";
			else if(_fields[i].equals("MeasuresFileNo") || _fields[i].equals("WfRiskReply.MeasuresFileNo"))
				sql += _fields[i] + " = ?,";
			else if(_fields[i].equals("MeasuresFileName") || _fields[i].equals("WfRiskReply.MeasuresFileName"))
				sql += _fields[i] + " = ?,";
			else if(_fields[i].equals("MeasuresDate") || _fields[i].equals("WfRiskReply.MeasuresDate"))
				sql += _fields[i] + " = ?,";
			else if(_fields[i].equals("Status") || _fields[i].equals("WfRiskReply.Status"))
				sql += _fields[i] + " = ?,";
			else if(_fields[i].equals("VerifyUserId") || _fields[i].equals("WfRiskReply.VerifyUserId"))
				sql += _fields[i] + " = ?,";
			else if(_fields[i].equals("VerifyResult") || _fields[i].equals("WfRiskReply.VerifyResult"))
				sql += _fields[i] + " = ?,";
			else if(_fields[i].equals("VerifyDate") || _fields[i].equals("WfRiskReply.VerifyDate"))
				sql += _fields[i] + " = ?,";
			else if(_fields[i].equals("VerifyFileNo") || _fields[i].equals("WfRiskReply.VerifyFileNo"))
				sql += _fields[i] + " = ?,";
			else if(_fields[i].equals("VerifyFileName") || _fields[i].equals("WfRiskReply.VerifyFileName"))
				sql += _fields[i] + " = ?,";
			else if(_fields[i].equals("CreateUserId") || _fields[i].equals("WfRiskReply.CreateUserId"))
				sql += _fields[i] + " = ?,";
			else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRiskReply.CreateDate"))
				sql += _fields[i] + " = ?,";
			else if(_fields[i].equals("LastUpdate") || _fields[i].equals("WfRiskReply.LastUpdate"))
				sql += _fields[i] + " = ?,";
			else if(_fields[i].equals("LastUpdateUserId") || _fields[i].equals("WfRiskReply.LastUpdateUserId"))
				sql += _fields[i] + " = ?,";
			else if(_fields[i].equals("Remark") || _fields[i].equals("WfRiskReply.Remark"))
				sql += _fields[i] + " = ?,";
			else if(_fields[i].equals("RiskId") || _fields[i].equals("WfRiskReply.RiskId"))
				sql += _fields[i] + " = ?,";
			else if(_fields[i].equals("RushDate") || _fields[i].equals("WfRiskReply.RushDate"))
				sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfRiskReply wfRiskReply,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
				if(_fields[i].equals("ResponsibleUserId") || _fields[i].equals("WfRiskReply.ResponsibleUserId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRiskReply.getResponsibleUserId());
				}
				else if(_fields[i].equals("Measures") || _fields[i].equals("WfRiskReply.Measures")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRiskReply.getMeasures());
				}
				else if(_fields[i].equals("MeasuresFileNo") || _fields[i].equals("WfRiskReply.MeasuresFileNo")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRiskReply.getMeasuresFileNo());
				}
				else if(_fields[i].equals("MeasuresFileName") || _fields[i].equals("WfRiskReply.MeasuresFileName")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRiskReply.getMeasuresFileName());
				}
				else if(_fields[i].equals("MeasuresDate") || _fields[i].equals("WfRiskReply.MeasuresDate")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new Timestamp(wfRiskReply.getMeasuresDate().getTime()));
				}
				else if(_fields[i].equals("Status") || _fields[i].equals("WfRiskReply.Status")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRiskReply.getStatus());
				}
				else if(_fields[i].equals("VerifyUserId") || _fields[i].equals("WfRiskReply.VerifyUserId")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRiskReply.getVerifyUserId());
				}
				else if(_fields[i].equals("VerifyResult") || _fields[i].equals("WfRiskReply.VerifyResult")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRiskReply.getVerifyResult());
				}
				else if(_fields[i].equals("VerifyDate") || _fields[i].equals("WfRiskReply.VerifyDate")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new Timestamp(wfRiskReply.getVerifyDate().getTime()));
				}
				else if(_fields[i].equals("VerifyFileNo") || _fields[i].equals("WfRiskReply.VerifyFileNo")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRiskReply.getVerifyFileNo());
				}
				else if(_fields[i].equals("VerifyFileName") || _fields[i].equals("WfRiskReply.VerifyFileName")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRiskReply.getVerifyFileName());
				}
				else if(_fields[i].equals("CreateUserId") || _fields[i].equals("WfRiskReply.CreateUserId")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRiskReply.getCreateUserId());
				}
				else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRiskReply.CreateDate")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new Timestamp(wfRiskReply.getCreateDate().getTime()));
				}
				else if(_fields[i].equals("LastUpdate") || _fields[i].equals("WfRiskReply.LastUpdate")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new Timestamp(wfRiskReply.getLastUpdate().getTime()));
				}
				else if(_fields[i].equals("LastUpdateUserId") || _fields[i].equals("WfRiskReply.LastUpdateUserId")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRiskReply.getLastUpdateUserId());
				}
				else if(_fields[i].equals("Remark") || _fields[i].equals("WfRiskReply.Remark")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRiskReply.getRemark());
				}
				else if(_fields[i].equals("RiskId") || _fields[i].equals("WfRiskReply.RiskId")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRiskReply.getRiskId());
				}
				else if(_fields[i].equals("RushDate") || _fields[i].equals("WfRiskReply.RushDate")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new Timestamp(wfRiskReply.getRushDate().getTime()));
				}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfRiskReplyHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfRiskReply wfRiskReply) {
		String _fields = "";
		if(null != wfRiskReply.getId())
			_fields += "WfRiskReply.Id,";
		if(null != wfRiskReply.getResponsibleUserId())
			_fields += "WfRiskReply.ResponsibleUserId,";
		if(null != wfRiskReply.getMeasures())
			_fields += "WfRiskReply.Measures,";
		if(null != wfRiskReply.getMeasuresFileNo())
			_fields += "WfRiskReply.MeasuresFileNo,";
		if(null != wfRiskReply.getMeasuresFileName())
			_fields += "WfRiskReply.MeasuresFileName,";
		if(null != wfRiskReply.getMeasuresDate())
			_fields += "WfRiskReply.MeasuresDate,";
		if(null != wfRiskReply.getStatus())
			_fields += "WfRiskReply.Status,";
		if(null != wfRiskReply.getVerifyUserId())
			_fields += "WfRiskReply.VerifyUserId,";
		if(null != wfRiskReply.getVerifyResult())
			_fields += "WfRiskReply.VerifyResult,";
		if(null != wfRiskReply.getVerifyDate())
			_fields += "WfRiskReply.VerifyDate,";
		if(null != wfRiskReply.getVerifyFileNo())
			_fields += "WfRiskReply.VerifyFileNo,";
		if(null != wfRiskReply.getVerifyFileName())
			_fields += "WfRiskReply.VerifyFileName,";
		if(null != wfRiskReply.getCreateUserId())
			_fields += "WfRiskReply.CreateUserId,";
		if(null != wfRiskReply.getCreateDate())
			_fields += "WfRiskReply.CreateDate,";
		if(null != wfRiskReply.getLastUpdateUserId())
			_fields += "WfRiskReply.LastUpdateUserId,";
		if(null != wfRiskReply.getLastUpdate())
			_fields += "WfRiskReply.LastUpdate,";
		if(null != wfRiskReply.getRemark())
			_fields += "WfRiskReply.Remark,";
		if(null != wfRiskReply.getRiskId())
			_fields += "WfRiskReply.RiskId,";
		if(null != wfRiskReply.getRushDate())
			_fields += "WfRiskReply.RushDate,";
		return _fields.substring(0, _fields.length()-1);
	}
}