package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.SyLog;

public class BasicSyLogHelper implements SqlHelper {
	public String getSqlString() {
		return " from SyLog where 1=1";
	}

	public String getOrderBy() {
		return " order by SyLog.LogId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((SyLog)object);
	}

	public String getSql4Amount(SyLog syLog) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(syLog);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((SyLog)object);
	}

	public String getSql4Delete(SyLog syLog) {
		return "delete from SyLog where 1=1"+getSqlCondition(syLog);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((SyLog)object,fields);
	}

	public String getSql4All(SyLog syLog, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(syLog)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((SyLog)object,pageVO,fields);
	}

	public String getSql4Pages(SyLog syLog,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" SyLog.LogId "+ getSqlString()+getSqlCondition(syLog)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(syLog)+" and SyLog.LogId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((SyLog)object);
	}

	public String getSqlCondition(SyLog syLog) {
		String sql = "";
		if(null != syLog.getLogId())
			sql += " and SyLog.LogId = '"+syLog.getLogId()+"'";
		if(null != syLog.getUserId())
			sql += " and SyLog.UserId = '"+syLog.getUserId()+"'";
		if(null != syLog.getLogCode() && !syLog.getLogCode().trim().equals(""))
			sql += " and SyLog.LogCode = '"+syLog.getLogCode().trim()+"'";
		if(null != syLog.getLogNm() && !syLog.getLogNm().trim().equals(""))
			sql += " and SyLog.LogNm = '"+syLog.getLogNm().trim()+"'";
		if(null != syLog.getLogText() && !syLog.getLogText().trim().equals(""))
			sql += " and SyLog.LogText = '"+syLog.getLogText().trim()+"'";
		if(null != syLog.getStartLogDate()) 
			sql += " and SyLog.LogDate >= '"+GenericUtil.dateFormat(syLog.getStartLogDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != syLog.getEndLogDate()) 
			sql += " and SyLog.LogDate <= '"+GenericUtil.dateFormat(syLog.getEndLogDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != syLog.getIpAddr() && !syLog.getIpAddr().trim().equals(""))
			sql += " and SyLog.IpAddr = '"+syLog.getIpAddr().trim()+"'";
		if(null != syLog.getLogType())
			sql += " and SyLog.LogType = '"+syLog.getLogType()+"'";
		if(null != syLog.getRemark() && !syLog.getRemark().trim().equals(""))
			sql += " and SyLog.Remark = '"+syLog.getRemark().trim()+"'";

		return sql;
	}

	public List<SyLog> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<SyLog> list = new ArrayList<SyLog>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				SyLog syLog = new SyLog();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("LogId") || _fields[i].equals("SyLog.LogId"))
						syLog.setLogId(rs.getInt("LogId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("SyLog.UserId"))
						syLog.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("LogCode") || _fields[i].equals("SyLog.LogCode"))
						syLog.setLogCode(rs.getString("LogCode"));
					else if(_fields[i].equals("LogNm") || _fields[i].equals("SyLog.LogNm"))
						syLog.setLogNm(rs.getString("LogNm"));
					else if(_fields[i].equals("LogText") || _fields[i].equals("SyLog.LogText"))
						syLog.setLogText(rs.getString("LogText"));
					else if(_fields[i].equals("LogDate") || _fields[i].equals("SyLog.LogDate"))
						syLog.setLogDate(rs.getTimestamp("LogDate"));
					else if(_fields[i].equals("IpAddr") || _fields[i].equals("SyLog.IpAddr"))
						syLog.setIpAddr(rs.getString("IpAddr"));
					else if(_fields[i].equals("LogType") || _fields[i].equals("SyLog.LogType"))
						syLog.setLogType(rs.getInt("LogType"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("SyLog.Remark"))
						syLog.setRemark(rs.getString("Remark"));

				}
				list.add(syLog);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SyLogHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into SyLog("+fields.replaceAll("SyLog\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(SyLog syLog,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("LogId") || _fields[i].equals("SyLog.LogId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, syLog.getLogId());
					}
					else if(_fields[i].equals("UserId") || _fields[i].equals("SyLog.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, syLog.getUserId());
					}
					else if(_fields[i].equals("LogCode") || _fields[i].equals("SyLog.LogCode")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, syLog.getLogCode());
					}
					else if(_fields[i].equals("LogNm") || _fields[i].equals("SyLog.LogNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, syLog.getLogNm());
					}
					else if(_fields[i].equals("LogText") || _fields[i].equals("SyLog.LogText")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, syLog.getLogText());
					}
					else if(_fields[i].equals("LogDate") || _fields[i].equals("SyLog.LogDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(syLog.getLogDate().getTime()));
					}
					else if(_fields[i].equals("IpAddr") || _fields[i].equals("SyLog.IpAddr")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, syLog.getIpAddr());
					}
					else if(_fields[i].equals("LogType") || _fields[i].equals("SyLog.LogType")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, syLog.getLogType());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("SyLog.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, syLog.getRemark());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("SyLogHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update SyLog set ";
		String[] _fields = fields.replaceAll("SyLog\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("UserId") || _fields[i].equals("SyLog.UserId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LogCode") || _fields[i].equals("SyLog.LogCode"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LogNm") || _fields[i].equals("SyLog.LogNm"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LogText") || _fields[i].equals("SyLog.LogText"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LogDate") || _fields[i].equals("SyLog.LogDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IpAddr") || _fields[i].equals("SyLog.IpAddr"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LogType") || _fields[i].equals("SyLog.LogType"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("SyLog.Remark"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(SyLog syLog,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("UserId") || _fields[i].equals("SyLog.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, syLog.getUserId());
					}
					else if(_fields[i].equals("LogCode") || _fields[i].equals("SyLog.LogCode")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, syLog.getLogCode());
					}
					else if(_fields[i].equals("LogNm") || _fields[i].equals("SyLog.LogNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, syLog.getLogNm());
					}
					else if(_fields[i].equals("LogText") || _fields[i].equals("SyLog.LogText")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, syLog.getLogText());
					}
					else if(_fields[i].equals("LogDate") || _fields[i].equals("SyLog.LogDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(syLog.getLogDate().getTime()));
					}
					else if(_fields[i].equals("IpAddr") || _fields[i].equals("SyLog.IpAddr")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, syLog.getIpAddr());
					}
					else if(_fields[i].equals("LogType") || _fields[i].equals("SyLog.LogType")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, syLog.getLogType());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("SyLog.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, syLog.getRemark());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("SyLogHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(SyLog syLog) {
		String _fields = "";
		if(null != syLog.getLogId())
			_fields += "SyLog.LogId,";
		if(null != syLog.getUserId())
			_fields += "SyLog.UserId,";
		if(null != syLog.getLogCode())
			_fields += "SyLog.LogCode,";
		if(null != syLog.getLogNm())
			_fields += "SyLog.LogNm,";
		if(null != syLog.getLogText())
			_fields += "SyLog.LogText,";
		if(null != syLog.getLogDate())
			_fields += "SyLog.LogDate,";
		if(null != syLog.getIpAddr())
			_fields += "SyLog.IpAddr,";
		if(null != syLog.getLogType())
			_fields += "SyLog.LogType,";
		if(null != syLog.getRemark())
			_fields += "SyLog.Remark,";

		return _fields.substring(0, _fields.length()-1);
	}
}