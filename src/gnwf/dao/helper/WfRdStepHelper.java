package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfRdStep;

public class WfRdStepHelper extends BasicWfRdStepHelper {
	public String getSqlString() {
		return " from WfRdStep " +

               " where 1=1 ";
	}

	public List<WfRdStep> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfRdStep> list = new ArrayList<WfRdStep>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfRdStep wfRdStep = new WfRdStep();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("StepId") || _fields[i].equals("WfRdStep.StepId"))
						wfRdStep.setStepId(rs.getInt("StepId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfRdStep.UserId"))
						wfRdStep.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("StepUser") || _fields[i].equals("WfRdStep.StepUser"))
						wfRdStep.setStepUser(rs.getInt("StepUser"));
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfRdStep.FlowId"))
						wfRdStep.setFlowId(rs.getString("FlowId"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRdStep.CreateDate"))
						wfRdStep.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfRdStep.LastUpdDate"))
						wfRdStep.setLastUpdDate(rs.getTimestamp("LastUpdDate"));


				}
				list.add(wfRdStep);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdStepHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}