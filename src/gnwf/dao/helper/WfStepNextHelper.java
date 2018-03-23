package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfStepNext;

public class WfStepNextHelper extends BasicWfStepNextHelper {
	public String getSqlString() {
		return " from WfStepNext " +
               " inner join WfStep on (WfStep.StepId = WfStepNext.StepId) " + 

               " where 1=1 ";
	}

	public List<WfStepNext> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfStepNext> list = new ArrayList<WfStepNext>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfStepNext wfStepNext = new WfStepNext();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("StepId") || _fields[i].equals("WfStepNext.StepId"))
						wfStepNext.setStepId(rs.getInt("StepId"));
					else if(_fields[i].equals("NextId") || _fields[i].equals("WfStepNext.NextId"))
						wfStepNext.setNextId(rs.getInt("NextId"));

					else if(_fields[i].equals("StepName") || _fields[i].equals("WfStep.StepId as StepName"))
						wfStepNext.setStepName(rs.getString("StepName"));
					
					else if(_fields[i].equals("Sort") || _fields[i].equals("WfStep.Sort"))
						wfStepNext.setSort(new Integer(rs.getInt("Sort")));
					else if(_fields[i].equals("StepType") || _fields[i].equals("WfStep.StepType"))
						wfStepNext.setStepType(new Integer(rs.getInt("StepType")));

				}
				list.add(wfStepNext);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepNextHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}