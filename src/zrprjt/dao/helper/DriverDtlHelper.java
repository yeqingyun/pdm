package zrprjt.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrprjt.vo.DriverDtl;

public class DriverDtlHelper extends BasicDriverDtlHelper {
	public String getSqlString() {
		return " from DriverDtl " +
			   " inner join GnWf.dbo.WfStep ws on ws.StepId = DriverDtl.StepId " +
			   " inner join WfCfg on (WfCfg.FlowId = DriverDtl.FlowId) " + 
               " where 1=1 ";
	}

	public List<DriverDtl> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<DriverDtl> list = new ArrayList<DriverDtl>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				DriverDtl driverDtl = new DriverDtl();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DriveId") || _fields[i].equals("DriverDtl.DriveId"))
						driverDtl.setDriveId(rs.getInt("DriveId"));
					else if(_fields[i].equals("FlowId") || _fields[i].equals("DriverDtl.FlowId"))
						driverDtl.setFlowId(rs.getInt("FlowId"));
					else if(_fields[i].equals("StepId") || _fields[i].equals("DriverDtl.StepId"))
						driverDtl.setStepId(rs.getInt("StepId"));

					else if(_fields[i].equals("FlowName") || _fields[i].equals("WfCfg.FlowName"))
						driverDtl.setFlowName(rs.getString("FlowName"));
					else if(_fields[i].equals("ws.StepName"))
						driverDtl.setStepName(rs.getString("StepName"));
					else if(_fields[i].equals("ws.StepType"))
						driverDtl.setStepType(rs.getInt("StepType"));
				}
				list.add(driverDtl);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DriverDtlHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}