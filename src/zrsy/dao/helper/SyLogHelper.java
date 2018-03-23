package zrsy.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrsy.vo.SyLog;

public class SyLogHelper extends BasicSyLogHelper {
	public String getSqlString() {
		return " from SyLog " +

               " where 1=1 ";
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
}