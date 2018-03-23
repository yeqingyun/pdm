package zrprjt.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrprjt.vo.TaskWf;

public class TaskWfHelper extends BasicTaskWfHelper {
	public String getSqlString() {
		return " from TaskWf " +
               " inner join Task on (Task.TaskNo = TaskWf.TaskNo) " + 

               " where 1=1 ";
	}

	public List<TaskWf> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<TaskWf> list = new ArrayList<TaskWf>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				TaskWf taskWf = new TaskWf();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SchId") || _fields[i].equals("TaskWf.SchId"))
						taskWf.setSchId(rs.getInt("SchId"));
					if(_fields[i].equals("Status") || _fields[i].equals("TaskWf.Status"))
						taskWf.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("TaskWf.CreateBy"))
						taskWf.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("TaskWf.LastUpd"))
						taskWf.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("TaskWf.CreateDate"))
						taskWf.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("TaskWf.LastDate"))
						taskWf.setLastDate(rs.getTimestamp("LastDate"));
					if(_fields[i].equals("TaskNo") || _fields[i].equals("TaskWf.TaskNo"))
						taskWf.setTaskNo(rs.getString("TaskNo"));
					if(_fields[i].equals("PrjtNo") || _fields[i].equals("TaskWf.PrjtNo"))
						taskWf.setPrjtNo(rs.getString("PrjtNo"));
					if(_fields[i].equals("WfNo") || _fields[i].equals("TaskWf.WfNo"))
						taskWf.setWfNo(rs.getString("WfNo"));


				}
				list.add(taskWf);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskWfHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}