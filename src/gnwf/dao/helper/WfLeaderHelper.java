package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfLeader;

public class WfLeaderHelper extends BasicWfLeaderHelper {
	public String getSqlString() {
		return " from WfLeader " +
               " left join usr on (WfLeader.userId = usr.id) " + 
               " where 1=1 ";
	}

	public List<WfLeader> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfLeader> list = new ArrayList<WfLeader>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfLeader wfLeader = new WfLeader();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("FlowId") || _fields[i].equals("WfLeader.FlowId"))
						wfLeader.setFlowId(rs.getInt("FlowId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfLeader.UserId"))
						wfLeader.setUserId(rs.getInt("UserId"));

					else if(_fields[i].equals("UsrName") || _fields[i].equals("Usr.UsrName"))
						wfLeader.setUserName(rs.getString("UsrName"));

				}
				list.add(wfLeader);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfLeaderHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}