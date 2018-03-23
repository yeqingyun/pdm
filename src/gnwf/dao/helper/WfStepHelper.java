package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfStep;

public class WfStepHelper extends BasicWfStepHelper {
	public String getSqlString() {
		return " from WfStep " +
               " inner join WfCfg on (WfCfg.FlowId = WfStep.FlowId) " + 

               " where 1=1 ";
	}
	
	public String getOrderBy() {
		return " order by WfStep.flowId,WfStep.sort";
	}

	public List<WfStep> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfStep> list = new ArrayList<WfStep>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfStep wfStep = new WfStep();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("StepId") || _fields[i].equals("WfStep.StepId"))
						wfStep.setStepId(rs.getInt("StepId"));
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfStep.FlowId"))
						wfStep.setFlowId(rs.getInt("FlowId"));
					else if(_fields[i].equals("PreStep") || _fields[i].equals("WfStep.PreStep"))
						wfStep.setPreStep(rs.getInt("PreStep"));
					else if(_fields[i].equals("StepType") || _fields[i].equals("WfStep.StepType"))
						wfStep.setStepType(rs.getInt("StepType"));
					else if(_fields[i].equals("Sort") || _fields[i].equals("WfStep.Sort"))
						wfStep.setSort(rs.getInt("Sort"));
					else if(_fields[i].equals("IsAuto") || _fields[i].equals("WfStep.IsAuto"))
						wfStep.setIsAuto(rs.getInt("IsAuto"));
					else if(_fields[i].equals("IsUpdForm") || _fields[i].equals("WfStep.IsUpdForm"))
						wfStep.setIsUpdForm(rs.getInt("IsUpdForm"));
					else if(_fields[i].equals("IsSysStartUp") || _fields[i].equals("WfStep.IsSysStartUp"))
						wfStep.setIsSysStartUp(rs.getInt("IsSysStartUp"));
					else if(_fields[i].equals("IsSysFinsh") || _fields[i].equals("WfStep.IsSysFinsh"))
						wfStep.setIsSysFinsh(rs.getInt("IsSysFinsh"));
					else if(_fields[i].equals("TimeQty") || _fields[i].equals("WfStep.TimeQty"))
						wfStep.setTimeQty(rs.getInt("TimeQty"));
					else if(_fields[i].equals("SelectCom") || _fields[i].equals("WfStep.SelectCom"))
						wfStep.setSelectCom(rs.getInt("SelectCom"));
					else if(_fields[i].equals("IsLastStep") || _fields[i].equals("WfStep.IsLastStep"))
						wfStep.setIsLastStep(rs.getInt("IsLastStep"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfStep.Status"))
						wfStep.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfStep.CreateBy"))
						wfStep.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfStep.LastUpd"))
						wfStep.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("SelectDept") || _fields[i].equals("WfStep.SelectDept"))
						wfStep.setSelectDept(rs.getInt("SelectDept"));
					else if(_fields[i].equals("WaitAuxiliary") || _fields[i].equals("WfStep.WaitAuxiliary"))
						wfStep.setWaitAuxiliary(rs.getInt("WaitAuxiliary"));
					else if(_fields[i].equals("IsFillQues") || _fields[i].equals("WfStep.IsFillQues"))
						wfStep.setIsFillQues(rs.getInt("IsFillQues"));
					else if(_fields[i].equals("IsDQAJob") || _fields[i].equals("WfStep.IsDQAJob"))
						wfStep.setIsDQAJob(rs.getInt("IsDQAJob"));
					
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfStep.CreateDate"))
						wfStep.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfStep.LastUpdDate"))
						wfStep.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					else if(_fields[i].equals("StepName") || _fields[i].equals("WfStep.StepName"))
						wfStep.setStepName(rs.getString("StepName"));
					else if(_fields[i].equals("StepDesc") || _fields[i].equals("WfStep.StepDesc"))
						wfStep.setStepDesc(rs.getString("StepDesc"));
					else if(_fields[i].equals("StepUri") || _fields[i].equals("WfStep.StepUri"))
						wfStep.setStepUri(rs.getString("StepUri"));
					
					else if(_fields[i].equals("NextId") || _fields[i].equals("B.NextId"))
						wfStep.setNextStepId(rs.getInt("NextId"));
					else if(_fields[i].equals("EndDate") || _fields[i].equals("A.EndDate"))
						wfStep.setTaskTime(rs.getTimestamp("EndDate"));
					else if(_fields[i].equals("FlowName") || _fields[i].equals("WfCfg.FlowName as FlowName"))
						wfStep.setFlowName(rs.getString("FlowName"));
					else if(_fields[i].equals("DocName") || _fields[i].equals("WfStep.DocName"))
						wfStep.setDocName(rs.getString("DocName"));
					else if(_fields[i].equals("UploadSize") || _fields[i].equals("WfStep.UploadSize"))
						wfStep.setUploadSize(rs.getInt("UploadSize"));
					
					else if(_fields[i].equals("ischecked") || _fields[i].equals("WfStep.ischecked")){
						if(rs.getInt("ischecked")!=0){
							wfStep.setIsChecked("checked");
						}
					}

				}
				list.add(wfStep);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}