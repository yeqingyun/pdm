package zrprjt.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrprjt.vo.SchCfg;
import zrprjt.vo.Task;

public class TaskHelper extends BasicTaskHelper {
	public String getSqlString() {
		return " from Task " +
               " inner join PrjtDef on (PrjtDef.PrjtNo = Task.PrjtNo) " + 
               " inner join SchCfg on (SchCfg.SchId = Task.SchId) " + 

               " where 1=1 ";
	}

	public List<Task> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<Task> list = new ArrayList<Task>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				Task task = new Task();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SchId") || _fields[i].equals("Task.SchId"))
						task.setSchId(rs.getInt("SchId"));
					if(_fields[i].equals("Tasker") || _fields[i].equals("Task.Tasker"))
						task.setTasker(rs.getInt("Tasker"));
					if(_fields[i].equals("Sender") || _fields[i].equals("Task.Sender"))
						task.setSender(rs.getInt("Sender"));
					if(_fields[i].equals("SchNo") || _fields[i].equals("Task.SchNo"))
						task.setSchNo(rs.getInt("SchNo"));
					if(_fields[i].equals("Leve") || _fields[i].equals("Task.Leve"))
						task.setLeve(rs.getInt("Leve"));
					if(_fields[i].equals("Grade") || _fields[i].equals("Task.Grade"))
						task.setGrade(rs.getInt("Grade"));
					if(_fields[i].equals("Perce") || _fields[i].equals("Task.Perce"))
						task.setPerce(rs.getInt("Perce"));
					if(_fields[i].equals("Status") || _fields[i].equals("Task.Status"))
						task.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("Task.CreateBy"))
						task.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("Task.LastUpd"))
						task.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("PlanStaDate") || _fields[i].equals("Task.PlanStaDate"))
						task.setPlanStaDate(rs.getTimestamp("PlanStaDate"));
					if(_fields[i].equals("PlanOveDate") || _fields[i].equals("Task.PlanOveDate"))
						task.setPlanOveDate(rs.getTimestamp("PlanOveDate"));
					if(_fields[i].equals("StaDate") || _fields[i].equals("Task.StaDate"))
						task.setStaDate(rs.getTimestamp("StaDate"));
					if(_fields[i].equals("OveDate") || _fields[i].equals("Task.OveDate"))
						task.setOveDate(rs.getTimestamp("OveDate"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("Task.CreateDate"))
						task.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("Task.LastDate"))
						task.setLastDate(rs.getTimestamp("LastDate"));
					if(_fields[i].equals("TaskNo") || _fields[i].equals("Task.TaskNo"))
						task.setTaskNo(rs.getInt("TaskNo"));
					if(_fields[i].equals("PrjtNo") || _fields[i].equals("Task.PrjtNo"))
						task.setPrjtNo(rs.getString("PrjtNo"));
					if(_fields[i].equals("Parent") || _fields[i].equals("Task.Parent"))
						task.setParent(rs.getInt("Parent"));
					if(_fields[i].equals("TaskNm") || _fields[i].equals("Task.TaskNm"))
						task.setTaskNm(rs.getString("TaskNm"));
					if(_fields[i].equals("Remark") || _fields[i].equals("Task.Remark"))
						task.setRemark(rs.getString("Remark"));

					
					if(_fields[i].equals("PlanDuration") || _fields[i].equals("Task.PlanDuration"))
						task.setPlanDuration(rs.getInt("PlanDuration"));
					if(_fields[i].equals("PredecessorLink") || _fields[i].equals("Task.PredecessorLink"))
						task.setPredecessorLink(rs.getString("PredecessorLink"));
					if(_fields[i].equals("Critical") || _fields[i].equals("Task.Critical"))
						task.setCritical(rs.getInt("Critical"));
					if(_fields[i].equals("Summary") || _fields[i].equals("Task.Summary"))
						task.setSummary(rs.getInt("Summary"));
					if(_fields[i].equals("Milestone") || _fields[i].equals("Task.Milestone"))
						task.setMilestone(rs.getInt("Milestone"));
					
					if(_fields[i].equals("ManualStart") || _fields[i].equals("SchCfg.ManualStart as ManualStart"))
						task.setManualStart(rs.getInt("ManualStart"));
					
				}
				list.add(task);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}