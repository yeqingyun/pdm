package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfAgent;

public class WfAgentHelper extends BasicWfAgentHelper {
	public String getSqlString() {
		return " from WfAgent " +
               " inner join WfCfg on (WfCfg.FlowId = WfAgent.FlowId) " + 

               " where 1=1 ";
	}

	public List<WfAgent> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfAgent> list = new ArrayList<WfAgent>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfAgent wfAgent = new WfAgent();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("UserId") || _fields[i].equals("WfAgent.UserId"))
						wfAgent.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("AgentId") || _fields[i].equals("WfAgent.AgentId"))
						wfAgent.setAgentId(rs.getInt("AgentId"));
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfAgent.FlowId"))
						wfAgent.setFlowId(rs.getInt("FlowId"));

					else if(_fields[i].equals("FlowName") || _fields[i].equals("WfCfg.FlowName as FlowName"))
						wfAgent.setFlowName(rs.getString("FlowName"));

				}
				list.add(wfAgent);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfAgentHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}