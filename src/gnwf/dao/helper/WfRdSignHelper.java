package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfRdSign;

public class WfRdSignHelper extends BasicWfRdSignHelper {
	public String getSqlString() {
		return " from WfRdSign " +
               " left join WfRdTask on (WfRdTask.TaskId = WfRdSign.TaskId) " + 
				" left join WfStep on(WfRdTask.StepId=WfStep.StepId) " +
				" left join Usr on(Usr.id=WfRdSign.userId) " +
				" left join Dept on(Usr.deptId=Dept.deptId) " +
               " where 1=1 ";
	}
	
	public String getOrderBy() {
		return " order by WfRdSign.WfNoId";
	}

	public List<WfRdSign> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfRdSign> list = new ArrayList<WfRdSign>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfRdSign wfRdSign = new WfRdSign();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("TaskId") || _fields[i].equals("WfRdSign.TaskId"))
						wfRdSign.setTaskId(rs.getInt("TaskId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfRdSign.UserId"))
						wfRdSign.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfRdSign.Status"))
						wfRdSign.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("WfNoId") || _fields[i].equals("WfRdSign.WfNoId"))
						wfRdSign.setWfNoId(rs.getString("WfNoId"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRdSign.CreateDate"))
						wfRdSign.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("SignText") || _fields[i].equals("WfRdSign.SignText"))
						wfRdSign.setSignText(rs.getString("SignText"));

//					else if(_fields[i].equals("TaskId") || _fields[i].equals("WfRdTask.TaskId as TaskId"))
//						wfRdSign.setTaskId(rs.getInt("TaskId"));
					
					else if(_fields[i].equals("StepName") || _fields[i].equals("WfStep.StepName"))
						wfRdSign.setStepName(rs.getString("StepName"));
					else if(_fields[i].equals("UsrName") || _fields[i].equals("Usr.UsrName"))
						wfRdSign.setUserName(rs.getString("UsrName"));
					else if(_fields[i].equals("DeptNm") || _fields[i].equals("Dept.DeptNm"))
						wfRdSign.setDeptName(rs.getString("DeptNm"));

				}
				list.add(wfRdSign);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdSignHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}