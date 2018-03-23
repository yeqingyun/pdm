package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfStepUserHis;

public class WfStepUserHisHelper extends BasicWfStepUserHisHelper {
	public String getSqlString() {
		return " from WfStepUserHis " +

               " where 1=1 ";
	}

	public List<WfStepUserHis> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfStepUserHis> list = new ArrayList<WfStepUserHis>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfStepUserHis wfStepUserHis = new WfStepUserHis();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("StepID") || _fields[i].equals("WfStepUserHis.StepID"))
						wfStepUserHis.setStepID(rs.getInt("StepID"));
					else if(_fields[i].equals("Owner") || _fields[i].equals("WfStepUserHis.Owner"))
						wfStepUserHis.setOwner(rs.getInt("Owner"));
					else if(_fields[i].equals("TaskType") || _fields[i].equals("WfStepUserHis.TaskType"))
						wfStepUserHis.setTaskType(rs.getInt("TaskType"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfStepUserHis.UserId"))
						wfStepUserHis.setUserId(rs.getInt("UserId"));


				}
				list.add(wfStepUserHis);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepUserHisHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}