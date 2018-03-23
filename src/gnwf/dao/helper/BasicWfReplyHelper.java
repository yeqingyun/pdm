package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfReply;

public class BasicWfReplyHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfReply where 1=1";
	}

	public String getOrderBy() {
		return " order by WfReply.ReplyId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfReply)object);
	}

	public String getSql4Amount(WfReply wfReply) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfReply);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfReply)object);
	}

	public String getSql4Delete(WfReply wfReply) {
		return "delete from WfReply where 1=1"+getSqlCondition(wfReply);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfReply)object,fields);
	}

	public String getSql4All(WfReply wfReply, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfReply)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfReply)object,pageVO,fields);
	}

	public String getSql4Pages(WfReply wfReply,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfReply.ReplyId "+ getSqlString()+getSqlCondition(wfReply)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfReply)+" and WfReply.ReplyId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfReply)object);
	}

	public String getSqlCondition(WfReply wfReply) {
		String sql = "";
		if(null != wfReply.getReplyId())
			sql += " and WfReply.ReplyId = '"+wfReply.getReplyId()+"'";
		if(null != wfReply.getQuesId())
			sql += " and WfReply.QuesId = '"+wfReply.getQuesId()+"'";
		if(null != wfReply.getUserId())
			sql += " and WfReply.UserId = '"+wfReply.getUserId()+"'";
		if(null != wfReply.getStatus())
			sql += " and WfReply.Status = '"+wfReply.getStatus()+"'";
		if(null != wfReply.getCreateBy())
			sql += " and WfReply.CreateBy = '"+wfReply.getCreateBy()+"'";
		if(null != wfReply.getLastUpd())
			sql += " and WfReply.LastUpd = '"+wfReply.getLastUpd()+"'";
		if(null != wfReply.getStartCreateDate()) 
			sql += " and WfReply.CreateDate >= '"+GenericUtil.dateFormat(wfReply.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfReply.getEndCreateDate()) 
			sql += " and WfReply.CreateDate <= '"+GenericUtil.dateFormat(wfReply.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfReply.getStartLastUpdDate()) 
			sql += " and WfReply.LastUpdDate >= '"+GenericUtil.dateFormat(wfReply.getStartLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfReply.getEndLastUpdDate()) 
			sql += " and WfReply.LastUpdDate <= '"+GenericUtil.dateFormat(wfReply.getEndLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfReply.getReplyText() && !wfReply.getReplyText().trim().equals(""))
			sql += " and WfReply.ReplyText = '"+wfReply.getReplyText().trim()+"'";
		
		if(null != wfReply.getFileNo() && !wfReply.getFileNo().trim().equals(""))
			sql += " and WfReply.FileNo = '"+wfReply.getFileNo().trim()+"'";
		if(null != wfReply.getFileName() && !wfReply.getFileName().trim().equals(""))
			sql += " and WfReply.FileName = '"+wfReply.getFileName().trim()+"'";
		
		
		if(null != wfReply.getGroupId())
			sql += " and WfReply.GroupId = '"+wfReply.getGroupId()+"'";
		if(null != wfReply.getRepLyType())
			sql += " and WfReply.RepLyType = '"+wfReply.getRepLyType()+"'";
		

		return sql;
	}

	public List<WfReply> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfReply> list = new ArrayList<WfReply>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfReply wfReply = new WfReply();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ReplyId") || _fields[i].equals("WfReply.ReplyId"))
						wfReply.setReplyId(rs.getInt("ReplyId"));
					else if(_fields[i].equals("QuesId") || _fields[i].equals("WfReply.QuesId"))
						wfReply.setQuesId(rs.getString("QuesId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfReply.UserId"))
						wfReply.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfReply.Status"))
						wfReply.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfReply.CreateBy"))
						wfReply.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfReply.LastUpd"))
						wfReply.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfReply.CreateDate"))
						wfReply.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfReply.LastUpdDate"))
						wfReply.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					else if(_fields[i].equals("ReplyText") || _fields[i].equals("WfReply.ReplyText"))
						wfReply.setReplyText(rs.getString("ReplyText"));
					
					else if(_fields[i].equals("FileNo") || _fields[i].equals("WfReply.FileNo"))
						wfReply.setFileNo(rs.getString("FileNo"));
					else if(_fields[i].equals("FileName") || _fields[i].equals("WfReply.FileName"))
						wfReply.setFileName(rs.getString("FileName"));

					else if(_fields[i].equals("GroupId") || _fields[i].equals("WfReply.GroupId"))
						wfReply.setGroupId(rs.getInt("GroupId"));
					else if(_fields[i].equals("RepLyType") || _fields[i].equals("WfReply.RepLyType"))
						wfReply.setRepLyType(rs.getInt("RepLyType"));
				
					
					
				}
				list.add(wfReply);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfReplyHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfReply("+fields.replaceAll("WfReply\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfReply wfReply,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ReplyId") || _fields[i].equals("WfReply.ReplyId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfReply.getReplyId());
					}
					else if(_fields[i].equals("QuesId") || _fields[i].equals("WfReply.QuesId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfReply.getQuesId());
					}
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfReply.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfReply.getUserId());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("WfReply.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfReply.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfReply.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfReply.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfReply.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfReply.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfReply.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfReply.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfReply.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfReply.getLastUpdDate().getTime()));
					}
					else if(_fields[i].equals("ReplyText") || _fields[i].equals("WfReply.ReplyText")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfReply.getReplyText());
					}
					
					else if(_fields[i].equals("FileNo") || _fields[i].equals("WfReply.FileNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfReply.getFileNo());
					}
					else if(_fields[i].equals("FileName") || _fields[i].equals("WfReply.FileName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfReply.getFileName());
					}

					else if(_fields[i].equals("GroupId") || _fields[i].equals("WfReply.GroupId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfReply.getGroupId());
					}
					else if(_fields[i].equals("RepLyType") || _fields[i].equals("WfReply.RepLyType")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfReply.getRepLyType());
					}
					
					
			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfReplyHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfReply set ";
		String[] _fields = fields.replaceAll("WfReply\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("UserId") || _fields[i].equals("WfReply.UserId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("WfReply.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("WfReply.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("WfReply.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("WfReply.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfReply.LastUpdDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ReplyText") || _fields[i].equals("WfReply.ReplyText"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("FileNo") || _fields[i].equals("WfReply.FileNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("FileName") || _fields[i].equals("WfReply.FileName"))
						sql += _fields[i] + " = ?,";

					
					if(_fields[i].equals("GroupId") || _fields[i].equals("WfReply.GroupId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("RepLyType") || _fields[i].equals("WfReply.RepLyType"))
						sql += _fields[i] + " = ?,";
					
					
					
		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfReply wfReply,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("UserId") || _fields[i].equals("WfReply.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfReply.getUserId());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("WfReply.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfReply.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfReply.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfReply.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfReply.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfReply.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfReply.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfReply.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfReply.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfReply.getLastUpdDate().getTime()));
					}
					else if(_fields[i].equals("ReplyText") || _fields[i].equals("WfReply.ReplyText")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfReply.getReplyText());
					}
					
					else if(_fields[i].equals("FileNo") || _fields[i].equals("WfReply.FileNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfReply.getFileNo());
					}
					
					else if(_fields[i].equals("FileName") || _fields[i].equals("WfReply.FileName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfReply.getFileName());
					}
					
					else if(_fields[i].equals("GroupId") || _fields[i].equals("WfReply.GroupId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfReply.getGroupId());
					}
					else if(_fields[i].equals("RepLyType") || _fields[i].equals("WfReply.RepLyType")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfReply.getRepLyType());
					}
					

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfReplyHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfReply wfReply) {
		String _fields = "";
		if(null != wfReply.getReplyId())
			_fields += "WfReply.ReplyId,";
		if(null != wfReply.getQuesId())
			_fields += "WfReply.QuesId,";
		if(null != wfReply.getUserId())
			_fields += "WfReply.UserId,";
		if(null != wfReply.getStatus())
			_fields += "WfReply.Status,";
		if(null != wfReply.getCreateBy())
			_fields += "WfReply.CreateBy,";
		if(null != wfReply.getLastUpd())
			_fields += "WfReply.LastUpd,";
		if(null != wfReply.getCreateDate())
			_fields += "WfReply.CreateDate,";
		if(null != wfReply.getLastUpdDate())
			_fields += "WfReply.LastUpdDate,";
		if(null != wfReply.getReplyText())
			_fields += "WfReply.ReplyText,";
		
		
		if(null != wfReply.getFileNo())
			_fields += "WfReply.FileNo,";
		if(null != wfReply.getFileName())
			_fields += "WfReply.FileName,";
		if(null != wfReply.getGroupId())
			_fields += "WfReply.GroupId,";
		if(null != wfReply.getRepLyType())
			_fields += "WfReply.RepLyType,";
		
		
		

		return _fields.substring(0, _fields.length()-1);
	}
}