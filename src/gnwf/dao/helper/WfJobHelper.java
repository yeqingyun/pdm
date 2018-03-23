package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfJob;

public class WfJobHelper extends BasicWfJobHelper {
	public String getSqlString() {
		return " from WfJob " +
               " inner join WfCfg on (WfCfg.FlowId = WfJob.FlowId) " + 

               " where 1=1 ";
	}

	public List<WfJob> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfJob> list = new ArrayList<WfJob>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfJob wfJob = new WfJob();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("JobId") || _fields[i].equals("WfJob.JobId"))
						wfJob.setJobId(rs.getInt("JobId"));
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfJob.FlowId"))
						wfJob.setFlowId(rs.getInt("FlowId"));
					else if(_fields[i].equals("IsUpdOrLoad") || _fields[i].equals("WfJob.IsUpdOrLoad"))
						wfJob.setIsUpdOrLoad(rs.getInt("IsUpdOrLoad"));
					else if(_fields[i].equals("AnnexUpdOrLoad") || _fields[i].equals("WfJob.AnnexUpdOrLoad"))
						wfJob.setAnnexUpdOrLoad(rs.getString("AnnexUpdOrLoad"));

					else if(_fields[i].equals("FlowName") || _fields[i].equals("WfCfg.FlowName as FlowName"))
						wfJob.setFlowName(rs.getString("FlowName"));

				}
				list.add(wfJob);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfJobHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}