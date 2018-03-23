package zrsy.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrsy.vo.IpAddr;

public class IpAddrHelper extends BasicIpAddrHelper {
	public String getSqlString() {
		return " from [ZrSy].[dbo].[IpAddr] " +

               " where 1=1 ";
	}

	public List<IpAddr> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<IpAddr> list = new ArrayList<IpAddr>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				IpAddr ipAddr = new IpAddr();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CreateDate") || _fields[i].equals("IpAddr.CreateDate"))
						ipAddr.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastDate") || _fields[i].equals("IpAddr.LastDate"))
						ipAddr.setLastDate(rs.getTimestamp("LastDate"));
					else if(_fields[i].equals("IpId") || _fields[i].equals("IpAddr.IpId"))
						ipAddr.setIpId(rs.getInt("IpId"));
					else if(_fields[i].equals("IsWide") || _fields[i].equals("IpAddr.IsWide"))
						ipAddr.setIsWide(rs.getInt("IsWide"));
					else if(_fields[i].equals("Status") || _fields[i].equals("IpAddr.Status"))
						ipAddr.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("IpAddr.CreateBy"))
						ipAddr.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("IpAddr.LastUpd"))
						ipAddr.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("IpSegment") || _fields[i].equals("IpAddr.IpSegment"))
						ipAddr.setIpSegment(rs.getString("IpSegment"));
					else if(_fields[i].equals("AddrDesc") || _fields[i].equals("IpAddr.AddrDesc"))
						ipAddr.setAddrDesc(rs.getString("AddrDesc"));


				}
				list.add(ipAddr);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("IpAddrHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}