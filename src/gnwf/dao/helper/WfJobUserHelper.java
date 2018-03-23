package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfJobUser;

public class WfJobUserHelper extends BasicWfJobUserHelper {
	public String getSqlString() {
		return " from WfJobUser " +

               " where 1=1 ";
	}

	public List<WfJobUser> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfJobUser> list = new ArrayList<WfJobUser>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfJobUser wfJobUser = new WfJobUser();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("UserId") || _fields[i].equals("WfJobUser.UserId"))
						wfJobUser.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("JobId") || _fields[i].equals("WfJobUser.JobId"))
						wfJobUser.setJobId(rs.getInt("JobId"));


				}
				list.add(wfJobUser);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfJobUserHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}