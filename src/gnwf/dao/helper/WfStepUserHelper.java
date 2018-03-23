package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfStepUser;

public class WfStepUserHelper extends BasicWfStepUserHelper {
	public String getSqlString() {
		return " from WfStepUser " +
               " left join usr on (WfStepUser.userId = usr.id) " + 
               " left join PrjtRole on (WfStepUser.prjtRoleId = PrjtRole.RoleId) " + 
               " where 1=1 ";
	}

	public List<WfStepUser> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfStepUser> list = new ArrayList<WfStepUser>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfStepUser wfStepUser = new WfStepUser();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("StepId") || _fields[i].equals("WfStepUser.StepId"))
						wfStepUser.setStepId(rs.getInt("StepId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfStepUser.UserId") || _fields[i].equals("PrjtUsr.UsrId as UserId"))
						wfStepUser.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("UserType") || _fields[i].equals("WfStepUser.UserType"))
						wfStepUser.setUserType(rs.getInt("UserType"));
					else if(_fields[i].equals("PrjtRoleId") || _fields[i].equals("WfStepUser.PrjtRoleId"))
						wfStepUser.setPrjtRoleId(rs.getInt("PrjtRoleId"));
					
					else if(_fields[i].equals("UsrName") || _fields[i].equals("Usr.UsrName"))
						wfStepUser.setUserName(rs.getString("UsrName"));
					else if(_fields[i].equals("RoleNm") || _fields[i].equals("PrjtRole.RoleNm"))
						wfStepUser.setPrjtRoleName(rs.getString("RoleNm"));

				}
				list.add(wfStepUser);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepUserHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}