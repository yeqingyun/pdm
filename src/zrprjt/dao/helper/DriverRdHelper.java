package zrprjt.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrprjt.vo.DriverRd;

public class DriverRdHelper extends BasicDriverRdHelper {
	public String getSqlString() {
		return " from DriverRd " +

               " where 1=1 ";
	}

	public List<DriverRd> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<DriverRd> list = new ArrayList<DriverRd>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				DriverRd driverRd = new DriverRd();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("LogId") || _fields[i].equals("DriverRd.LogId"))
						driverRd.setLogId(rs.getInt("LogId"));
					else if(_fields[i].equals("DriverId") || _fields[i].equals("DriverRd.DriverId"))
						driverRd.setDriverId(rs.getInt("DriverId"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("DriverRd.WfNo"))
						driverRd.setWfNo(rs.getString("WfNo"));
					else if(_fields[i].equals("DriverDate") || _fields[i].equals("DriverRd.DriverDate"))
						driverRd.setDriverDate(rs.getTimestamp("DriverDate"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("DriverRd.Remark"))
						driverRd.setRemark(rs.getString("Remark"));
					else if(_fields[i].equals("Status") || _fields[i].equals("DriverRd.Status"))
						driverRd.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("DriverRd.CreateBy"))
						driverRd.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("DriverRd.CreateDate"))
						driverRd.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("DriverRd.LastUpd"))
						driverRd.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("LastDate") || _fields[i].equals("DriverRd.LastDate"))
						driverRd.setLastDate(rs.getTimestamp("LastDate"));


				}
				list.add(driverRd);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DriverRdHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}