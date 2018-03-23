package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfRdSign;

public class BasicWfRdSignHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfRdSign where 1=1";
	}

	public String getOrderBy() {
		return " order by WfRdSign.TaskId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfRdSign)object);
	}

	public String getSql4Amount(WfRdSign wfRdSign) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfRdSign);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfRdSign)object);
	}

	public String getSql4Delete(WfRdSign wfRdSign) {
		return "delete from WfRdSign where 1=1"+getSqlCondition(wfRdSign);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfRdSign)object,fields);
	}

	public String getSql4All(WfRdSign wfRdSign, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfRdSign)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfRdSign)object,pageVO,fields);
	}

	public String getSql4Pages(WfRdSign wfRdSign,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfRdSign.TaskId "+ getSqlString()+getSqlCondition(wfRdSign)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfRdSign)+" and WfRdSign.TaskId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfRdSign)object);
	}

	public String getSqlCondition(WfRdSign wfRdSign) {
		String sql = "";
		if(null != wfRdSign.getTaskId())
			sql += " and WfRdSign.TaskId = '"+wfRdSign.getTaskId()+"'";
		if(null != wfRdSign.getUserId())
			sql += " and WfRdSign.UserId = '"+wfRdSign.getUserId()+"'";
		if(null != wfRdSign.getStatus())
			sql += " and WfRdSign.Status = '"+wfRdSign.getStatus()+"'";
		if(null != wfRdSign.getWfNoId() && !wfRdSign.getWfNoId().trim().equals(""))
			sql += " and WfRdSign.WfNoId = '"+wfRdSign.getWfNoId().trim()+"'";
		if(null != wfRdSign.getStartCreateDate()) 
			sql += " and WfRdSign.CreateDate >= '"+GenericUtil.dateFormat(wfRdSign.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRdSign.getEndCreateDate()) 
			sql += " and WfRdSign.CreateDate <= '"+GenericUtil.dateFormat(wfRdSign.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRdSign.getSignText() && !wfRdSign.getSignText().trim().equals(""))
			sql += " and WfRdSign.SignText = '"+wfRdSign.getSignText().trim()+"'";

		return sql;
	}

	public List<WfRdSign> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfRdSign> list = new ArrayList<WfRdSign>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfRdSign wfRdSign = new WfRdSign();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("TaskId") || _fields[i].equals("WfRdSign.TaskId"))
						wfRdSign.setTaskId(rs.getInt("TaskId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfRdSign.UserId"))
						wfRdSign.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfRdSign.Status"))
						wfRdSign.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("WfNoId") || _fields[i].equals("WfRdSign.WfNoId"))
						wfRdSign.setWfNoId(rs.getString("WfNoId"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRdSign.CreateDate"))
						wfRdSign.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("SignText") || _fields[i].equals("WfRdSign.SignText"))
						wfRdSign.setSignText(rs.getString("SignText"));

				}
				list.add(wfRdSign);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdSignHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfRdSign("+fields.replaceAll("WfRdSign\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfRdSign wfRdSign,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("TaskId") || _fields[i].equals("WfRdSign.TaskId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdSign.getTaskId());
					}
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfRdSign.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdSign.getUserId());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("WfRdSign.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdSign.getStatus());
					}
					else if(_fields[i].equals("WfNoId") || _fields[i].equals("WfRdSign.WfNoId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRdSign.getWfNoId());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRdSign.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfRdSign.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("SignText") || _fields[i].equals("WfRdSign.SignText")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRdSign.getSignText());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfRdSignHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfRdSign set ";
		String[] _fields = fields.replaceAll("WfRdSign\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("WfRdSign.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRdSign.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("SignText") || _fields[i].equals("WfRdSign.SignText"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfRdSign wfRdSign,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("WfRdSign.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfRdSign.getStatus());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRdSign.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfRdSign.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("SignText") || _fields[i].equals("WfRdSign.SignText")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRdSign.getSignText());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfRdSignHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfRdSign wfRdSign) {
		String _fields = "";
		if(null != wfRdSign.getTaskId())
			_fields += "WfRdSign.TaskId,";
		if(null != wfRdSign.getUserId())
			_fields += "WfRdSign.UserId,";
		if(null != wfRdSign.getStatus())
			_fields += "WfRdSign.Status,";
		if(null != wfRdSign.getWfNoId())
			_fields += "WfRdSign.WfNoId,";
		if(null != wfRdSign.getCreateDate())
			_fields += "WfRdSign.CreateDate,";
		if(null != wfRdSign.getSignText())
			_fields += "WfRdSign.SignText,";

		return _fields.substring(0, _fields.length()-1);
	}
}