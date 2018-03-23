package gnwf.dao.helper;

import gnwf.vo.WfRdTask;
import gnwf.ww.MSG;
import gnwf.ww.workflow.WFUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class WfRdTaskHelper extends BasicWfRdTaskHelper {
	public String getSqlString() {
		return " from WfRdTask " +
               " left join WfStep on(WfStep.StepId = WfRdTask.StepId) " 
              /* " left join Usr Usr1 on(WfRdTask.AcceptBy = Usr1.id) " + 
               " left join Usr Usr2 on(WfRdTask.CreateBy = Usr2.id) " + */
               +"LEFT JOIN (SELECT id uid,Usr.UsrName FROM Usr WHERE Id IN ( SELECT ww.AcceptBy FROM WfRdTask ww "
               + "WHERE ww.WfNo = ww.WfNo ) )  Usr1 ON (WfRdTask.AcceptBy = Usr1.uid)"
              +"LEFT JOIN (SELECT id uid,Usr.UsrName FROM Usr WHERE Id IN "
              + "( SELECT ww.AcceptBy FROM WfRdTask ww WHERE ww.WfNo = ww.WfNo ) )  Usr2 ON (WfRdTask.CreateBy = Usr2.uid)"	
               // " left join Usr Usr3 on(WfRdTask.AgentBy = Usr3.id) " + 
               +" where 1=1 ";
	}

	public List<WfRdTask> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfRdTask> list = new ArrayList<WfRdTask>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfRdTask wfRdTask = new WfRdTask();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("TaskId") || _fields[i].equals("WfRdTask.TaskId"))
						wfRdTask.setTaskId(rs.getInt("TaskId"));
					else if(_fields[i].equals("PreTaskId") || _fields[i].equals("WfRdTask.PreTaskId"))
						wfRdTask.setPreTaskId(rs.getInt("PreTaskId"));
					else if(_fields[i].equals("StepId") || _fields[i].equals("WfRdTask.StepId"))
						wfRdTask.setStepId(rs.getInt("StepId"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfRdTask.CreateBy"))
						wfRdTask.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("AcceptBy") || _fields[i].equals("WfRdTask.AcceptBy"))
						wfRdTask.setAcceptBy(rs.getInt("AcceptBy"));
					else if(_fields[i].equals("AgentBy") || _fields[i].equals("WfRdTask.AgentBy"))
						wfRdTask.setAgentBy(rs.getInt("AgentBy"));
					else if(_fields[i].equals("TaskType") || _fields[i].equals("WfRdTask.TaskType"))
						wfRdTask.setTaskType(rs.getInt("TaskType"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfRdTask.Status"))
						wfRdTask.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("IsSystemFinsh") || _fields[i].equals("WfRdTask.IsSystemFinsh"))
						wfRdTask.setIsSystemFinsh(rs.getInt("IsSystemFinsh"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfRdTask.WfNo"))
						wfRdTask.setWfNo(rs.getString("WfNo"));
					else if(_fields[i].equals("ReqDate") || _fields[i].equals("WfRdTask.ReqDate"))
						wfRdTask.setReqDate(rs.getTimestamp("ReqDate"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRdTask.CreateDate"))
						wfRdTask.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("AcceptDate") || _fields[i].equals("WfRdTask.AcceptDate"))
						wfRdTask.setAcceptDate(rs.getTimestamp("AcceptDate"));
					else if(_fields[i].equals("AgentDate") || _fields[i].equals("WfRdTask.AgentDate"))
						wfRdTask.setAgentDate(rs.getTimestamp("AgentDate"));
					else if(_fields[i].equals("EndDate") || _fields[i].equals("WfRdTask.EndDate"))
						wfRdTask.setEndDate(rs.getTimestamp("EndDate"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("WfRdTask.Remark"))
						wfRdTask.setRemark(rs.getString("Remark"));
					
					else if(_fields[i].equals("DocCateId") || _fields[i].equals("WfRdTask.DocCateId"))
						wfRdTask.setDocCateId(rs.getString("DocCateId"));
					

//					else if(_fields[i].equals("StepName") || _fields[i].equals("WfStep.StepId as StepName"))
//						wfRdTask.setStepName(rs.getString("StepName"));
//					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfRd.WfNo as WfNo"))
//						wfRdTask.setWfNo(rs.getString("WfNo"));
					
					else if(_fields[i].equals("StepId") || _fields[i].equals("WfStep.StepId"))
						wfRdTask.setStepId(new Integer(rs.getInt("StepId")));
					else if(_fields[i].equals("StepType") || _fields[i].equals("WfStep.StepType"))
						wfRdTask.setStepType(new Integer(rs.getInt("StepType")));
					else if(_fields[i].equals("IsAuto") || _fields[i].equals("WfStep.IsAuto"))
						wfRdTask.setStepIsAuto(new Integer(rs.getInt("IsAuto")));
					else if(_fields[i].equals("WaitAuxiliary") || _fields[i].equals("WfStep.WaitAuxiliary"))
						wfRdTask.setWaitAuxiliary(new Integer(rs.getInt("WaitAuxiliary")));
					else if(_fields[i].equals("IsFillQues") || _fields[i].equals("WfStep.IsFillQues"))
						wfRdTask.setIsFillQues(rs.getInt("IsFillQues"));
					else if(_fields[i].equals("IsDQAJob") || _fields[i].equals("WfStep.IsDQAJob"))
						wfRdTask.setIsDQAJob(rs.getInt("IsDQAJob"));
					
					else if(_fields[i].equals("IsLastStep") || _fields[i].equals("WfStep.IsLastStep"))
						wfRdTask.setIsLastStep(new Integer(rs.getInt("IsLastStep")));
					else if(_fields[i].equals("IsUpdForm") || _fields[i].equals("WfStep.IsUpdForm"))
						wfRdTask.setIsUpdForm(new Integer(rs.getInt("IsUpdForm")));
					else if(_fields[i].equals("Sort") || _fields[i].equals("WfStep.Sort"))
						wfRdTask.setSort(new Integer(rs.getInt("Sort")));
					else if(_fields[i].equals("StepName") || _fields[i].equals("WfStep.StepName"))
						wfRdTask.setStepName(rs.getString("StepName"));
					else if(_fields[i].equals("StepUri") || _fields[i].equals("WfStep.StepUri"))
						wfRdTask.setStepUri(rs.getString("StepUri"));
					else if(_fields[i].equals("StepDesc") || _fields[i].equals("WfStep.StepDesc"))
						wfRdTask.setStepDesc(rs.getString("StepDesc"));
					
					
					else if(_fields[i].equals("CreaterName") || _fields[i].equals("Usr2.UsrName as CreaterName")){
						wfRdTask.setCreaterName(rs.getString("CreaterName"));
						if(wfRdTask.getCreateBy()==-1){
							wfRdTask.setCreaterName("系统");
						} 
					}
					else if(_fields[i].equals("AcceptName") || _fields[i].equals("Usr1.UsrName as AcceptName"))
						wfRdTask.setAcceptName(rs.getString("AcceptName"));
//					else if(_fields[i].equals("AgentName") || _fields[i].equals("Usr3.UsrName as AgentName"))
//						wfRdTask.setAgentName(rs.getString("AgentName"));
					
					if(wfRdTask.getAcceptDate()!=null){
						String str = WFUtil.diffTime(wfRdTask.getEndDate(), wfRdTask.getAcceptDate());
						wfRdTask.setUseTime(str);
					}else if(wfRdTask.getStatus()!=null && wfRdTask.getStatus()==MSG.OWFTASK_STATUS_5){		//任务已收回
						String str = WFUtil.diffTime(wfRdTask.getEndDate(), wfRdTask.getCreateDate());
						wfRdTask.setUseTime(str);
						wfRdTask.setAcceptDate(wfRdTask.getCreateDate());
					}

				}
				list.add(wfRdTask);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdTaskHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}