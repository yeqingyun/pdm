package zrprjt.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrprjt.vo.Driver;

public class DriverHelper extends BasicDriverHelper {
	public String getSqlString() {
		return " from Driver " +
				" inner join WfCfg on (WfCfg.FlowId = Driver.FlowId) " + 
                " where 1=1 ";
	}

	public List<Driver> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<Driver> list = new ArrayList<Driver>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				Driver driver = new Driver();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DriveId") || _fields[i].equals("Driver.DriveId"))
						driver.setDriveId(rs.getInt("DriveId"));
					else if(_fields[i].equals("DriveNo") || _fields[i].equals("Driver.DriveNo"))
						driver.setDriveNo(rs.getString("DriveNo"));
					else if(_fields[i].equals("DriveNm") || _fields[i].equals("Driver.DriveNm"))
						driver.setDriveNm(rs.getString("DriveNm"));
					else if(_fields[i].equals("FlowId") || _fields[i].equals("Driver.FlowId"))
						driver.setFlowId(rs.getInt("FlowId"));
					else if(_fields[i].equals("StepId") || _fields[i].equals("Driver.StepId"))
						driver.setStepId(rs.getInt("StepId"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("Driver.Remark"))
						driver.setRemark(rs.getString("Remark"));
					else if(_fields[i].equals("Status") || _fields[i].equals("Driver.Status"))
						driver.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("Driver.CreateBy"))
						driver.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("Driver.CreateDate"))
						driver.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("Driver.LastUpd"))
						driver.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("LastDate") || _fields[i].equals("Driver.LastDate"))
						driver.setLastDate(rs.getTimestamp("LastDate"));
					else if(_fields[i].equals("WfCfg.FlowName"))
						driver.setFlowName(rs.getString("FlowName"));

				}
				list.add(driver);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DriverHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}