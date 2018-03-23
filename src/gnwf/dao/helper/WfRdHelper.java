package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.frm.comn.GenericUtil;

import gnwf.vo.WfRd;
import gnwf.ww.MSG;
import gnwf.ww.workflow.WFUtil;

public class WfRdHelper extends BasicWfRdHelper {
	public String getSqlString() {
		return " from WfRd " +
				//" left join usr on (WfRd.CreateBy = usr.id) " +
				"LEFT JOIN ( select id uid, Usr.UsrName FROM Usr WHERE Id in "
				+ "( SELECT ww.CreateBy FROM WfRd ww WHERE ww.WfNo =ww.WfNo  ) ) Usr on Usr.uid = WfRd.CreateBy"+
				" left join WfRdTask on(WfRdTask.WfNo=WfRd.WfNo) " +
				" left join WfStep on(WfStep.stepId=WfRdTask.stepId) " +
				" left join WfCfg on(WfCfg.flowId=WfRd.FlowId) " +
				" where 1=1 ";
	}
	
	public String getOrderBy() {
		return " order by WfRd.WfNo Desc";
	}
	
	public String getSqlCondition(WfRd wfRd) {
		String sql = "";
		if(null != wfRd.getScheId())
			sql += " and WfRd.ScheId = "+wfRd.getScheId();
		if(null != wfRd.getFlowId())
			sql += " and WfRd.FlowId = "+wfRd.getFlowId();
		if(null != wfRd.getStatus())
			sql += " and WfRd.Status = "+wfRd.getStatus();
		if(null != wfRd.getCreateBy())
			sql += " and WfRd.CreateBy = "+wfRd.getCreateBy();
		if(null != wfRd.getLastUpd())
			sql += " and WfRd.LastUpd = "+wfRd.getLastUpd();
		if(null != wfRd.getWfNo() && !wfRd.getWfNo().trim().equals(""))
			sql += " and WfRd.WfNo like '%"+wfRd.getWfNo().trim()+"%'";
		if(null != wfRd.getProjectNo() && !wfRd.getProjectNo().trim().equals(""))
			sql += " and WfRd.ProjectNo like '%"+wfRd.getProjectNo().trim()+"%'";
		if(null != wfRd.getPreWfNo() && !wfRd.getPreWfNo().trim().equals(""))
			sql += " and WfRd.PreWfNo = '"+wfRd.getPreWfNo().trim()+"'";
		if(null != wfRd.getStartPlanSDate()) 
			sql += " and WfRd.PlanSDate >= '"+GenericUtil.dateFormat(wfRd.getStartPlanSDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getEndPlanSDate()) 
			sql += " and WfRd.PlanSDate <= '"+GenericUtil.dateFormat(wfRd.getEndPlanSDate(), "yyyy-MM-dd 23:59:59")+"'";
		if(null != wfRd.getStartPlanEDate()) 
			sql += " and WfRd.PlanEDate >= '"+GenericUtil.dateFormat(wfRd.getStartPlanEDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getEndPlanEDate()) 
			sql += " and WfRd.PlanEDate <= '"+GenericUtil.dateFormat(wfRd.getEndPlanEDate(), "yyyy-MM-dd 23:59:59")+"'";
		if(null != wfRd.getStartFactSDate()) 
			sql += " and WfRd.FactSDate >= '"+GenericUtil.dateFormat(wfRd.getStartFactSDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getEndFactSDate()) 
			sql += " and WfRd.FactSDate <= '"+GenericUtil.dateFormat(wfRd.getEndFactSDate(), "yyyy-MM-dd 23:59:59")+"'";
		if(null != wfRd.getStartFactEDate()) 
			sql += " and WfRd.FactEDate >= '"+GenericUtil.dateFormat(wfRd.getStartFactEDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getEndFactEDate()) 
			sql += " and WfRd.FactEDate <= '"+GenericUtil.dateFormat(wfRd.getEndFactEDate(), "yyyy-MM-dd 23:59:59")+"'";
		if(null != wfRd.getStartCreateDate()) 
			sql += " and WfRd.CreateDate >= '"+GenericUtil.dateFormat(wfRd.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getEndCreateDate()) 
			sql += " and WfRd.CreateDate <= '"+GenericUtil.dateFormat(wfRd.getEndCreateDate(), "yyyy-MM-dd 23:59:59")+"'";
		if(null != wfRd.getStartLastUpdDate()) 
			sql += " and WfRd.LastUpdDate >= '"+GenericUtil.dateFormat(wfRd.getStartLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getEndLastUpdDate()) 
			sql += " and WfRd.LastUpdDate <= '"+GenericUtil.dateFormat(wfRd.getEndLastUpdDate(), "yyyy-MM-dd 23:59:59")+"'";
		if(null != wfRd.getWfName() && !wfRd.getWfName().trim().equals(""))
			sql += " and WfRd.WfName like '%"+wfRd.getWfName().trim()+"%'";
		if(null != wfRd.getWfDesc() && !wfRd.getWfDesc().trim().equals(""))
			sql += " and WfRd.WfDesc = '"+wfRd.getWfDesc().trim()+"'";
		if(null != wfRd.getNeedQues())
			sql += " and WfRd.NeedQues = "+wfRd.getNeedQues();
		if(null != wfRd.getQuesId())
			sql += " and WfRd.QuesId = "+wfRd.getQuesId();
		if(null != wfRd.getCreateName() && !wfRd.getCreateName().trim().equals("")){
			sql += " and Usr.UsrName like '%"+wfRd.getCreateName().trim()+"%'";
		}
		if(wfRd.getOnlySelectCurUser()!=null&&wfRd.getGylFlag()==null){
			sql += " and (WfRdTask.acceptBy="+wfRd.getCurrentUserId()+" or WfRdTask.createBy="+wfRd.getCurrentUserId()+")";
		}
		
		if(wfRd.getSelectType()!=0){
			switch(wfRd.getSelectType()){
				case MSG.OWFRD_SELECT_TYPE_1:{
					sql+=" and WfRd.Status<="+MSG.OWFRD_STATUS_1+" and (WfRdTask.acceptBy="+wfRd.getCurrentUserId()+" and WfRdTask.status<="+MSG.OWFTASK_STATUS_1+")";
					break;
				}
				case MSG.OWFRD_SELECT_TYPE_2:{
					wfRd.setCreateBy(wfRd.getCurrentUserId());
					break;
				}
				case MSG.OWFRD_SELECT_TYPE_3:{
					sql+=" and WfRdTask.acceptBy="+wfRd.getCurrentUserId();
					break;
				}
				case MSG.OWFRD_SELECT_TYPE_4:{
					break;
				}
				case MSG.OWFRD_SELECT_TYPE_5:{
					sql+=" and WfRd.WfNo not in (select wfno from WfRdTask where WfRdTask.status<="+MSG.OWFTASK_STATUS_1
						+" and WfRdTask.acceptBy="+wfRd.getCurrentUserId()+")" 
						+" and WfRdTask.acceptBy="+wfRd.getCurrentUserId();
					break;
				}
				default:{
					break;
				}
			}
		}
		if(null != wfRd.getCreateBy())
			sql += " and WfRd.CreateBy = "+wfRd.getCreateBy();

		if(!wfRd.getIsNotChangeRole()){
			//判断是否为不随项目改变角色
			if(!wfRd.getIsOverseasRole()){
			sql += " and ProjectNo in (select distinct prjtNo from gnprjt.dbo.prjtUsr where usrid = " 
					+ wfRd.getCurrentUserId() + ")" + (wfRd.getFlowId() == null ? "" : (" and WfRd.flowid = " + wfRd.getFlowId()));
		
			}else if(wfRd.getGylFlag()!=null&&wfRd.getGylFlag().equals("true")&&(wfRd.getFlowId().equals(43)||wfRd.getFlowId().equals(63))){
				
			}else{
				
				//      select distinct WfNo from WfRd where CreateBy = getUsrSession().getId() or WfNo in ( select distinct WfNo from WfRdTask where AcceptBy = getUsrSession().getId())
				//sql += " and  WfRd.WfNo in (select distinct WfNo from WfRd where flowID >= 40 )" + (wfRd.getFlowId() == null ? "" : (" and WfRd.flowid = " + wfRd.getFlowId()));
				//sql += " and  WfRd.WfNo in ( select distinct WfNo from WfRd where CreateBy ="+ wfRd.getCurrentUserId()+" or WfNo in ( select distinct WfNo from WfRdTask where AcceptBy = "+wfRd.getCurrentUserId()+") )" + (wfRd.getFlowId() == null ? "" : (" and WfRd.flowid = " + wfRd.getFlowId()));
				sql +="and ((WfRdTask.acceptBy="+wfRd.getCurrentUserId()+" or WfRdTask.createBy="+wfRd.getCurrentUserId()+")or WfCfg.Sphere = '0') ";
				//or WfCfg.Sphere = '0'
			}
		}
		/*if(!wfRd.getIsOverseasRole()){
			sql += " or WfRd.WfNo in (select distinct WfNo from WfRd where flowID = 40)" ;
		}*/
		System.out.println("111111111111111111111111122"+sql);
		return sql;
	}

	public List<WfRd> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		String dburl = rs.getStatement().getConnection().getMetaData().getURL();
		String taskurl = "";
		if(dburl == null || dburl.equals("") || !dburl.contains("16.6.10.24")) {
			taskurl = "'http://192.168.189.173:28080/zrprjt/WfRdView!wfTaskView.dhtml?wfRd.wfNo=' + WfRd.WfNo + '&currentTaskId=' + cast(WfRdTask.TaskId as varchar(10)) + '&taskStepId=' + cast(WfStep.StepId as varchar(10)) Url";
		} else {
			taskurl = "'http://pdm.gionee.com/zrprjt/WfRdView!wfTaskView.dhtml?wfRd.wfNo=' + WfRd.WfNo + '&currentTaskId=' + cast(WfRdTask.TaskId as varchar(10)) + '&taskStepId=' + cast(WfStep.StepId as varchar(10)) Url";
		}
		
		ArrayList<WfRd> list = new ArrayList<WfRd>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfRd wfRd = new WfRd();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ScheId") || _fields[i].equals("WfRd.ScheId"))
						wfRd.setScheId(rs.getInt("ScheId"));
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfRd.FlowId"))
						wfRd.setFlowId(rs.getInt("FlowId"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfRd.Status"))
						wfRd.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfRd.CreateBy"))
						wfRd.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfRd.LastUpd"))
						wfRd.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfRd.WfNo"))
						wfRd.setWfNo(rs.getString("WfNo"));
					else if(_fields[i].equals("ProjectNo") || _fields[i].equals("WfRd.ProjectNo"))
						wfRd.setProjectNo(rs.getString("ProjectNo"));
					else if(_fields[i].equals("PreWfNo") || _fields[i].equals("WfRd.PreWfNo"))
						wfRd.setPreWfNo(rs.getString("PreWfNo"));
					else if(_fields[i].equals("PlanSDate") || _fields[i].equals("WfRd.PlanSDate"))
						wfRd.setPlanSDate(rs.getTimestamp("PlanSDate"));
					else if(_fields[i].equals("PlanEDate") || _fields[i].equals("WfRd.PlanEDate"))
						wfRd.setPlanEDate(rs.getTimestamp("PlanEDate"));
					else if(_fields[i].equals("FactSDate") || _fields[i].equals("WfRd.FactSDate"))
						wfRd.setFactSDate(rs.getTimestamp("FactSDate"));
					else if(_fields[i].equals("FactEDate") || _fields[i].equals("WfRd.FactEDate"))
						wfRd.setFactEDate(rs.getTimestamp("FactEDate"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRd.CreateDate"))
						wfRd.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfRd.LastUpdDate"))
						wfRd.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					else if(_fields[i].equals("WfName") || _fields[i].equals("WfRd.WfName"))
						wfRd.setWfName(rs.getString("WfName"));
					else if(_fields[i].equals("WfDesc") || _fields[i].equals("WfRd.WfDesc"))
						wfRd.setWfDesc(rs.getString("WfDesc"));
					else if(_fields[i].equals("StopDate") || _fields[i].equals("WfRd.StopDate"))
						wfRd.setStopDate(rs.getTimestamp("StopDate"));
					else if(_fields[i].equals("RestartDate") || _fields[i].equals("WfRd.RestartDate"))
						wfRd.setRestartDate(rs.getTimestamp("RestartDate"));
					else if(_fields[i].equals("StopDateNum") || _fields[i].equals("WfRd.StopDateNum"))
						wfRd.setStopDateNum(new Integer(rs.getInt("StopDateNum")));
					//else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfRd.CreateBy"))
						//wfRd.setCreateBy(new Integer(rs.getInt("CreateBy")));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRd.CreateDate")){
						wfRd.setCreateDate(rs.getTimestamp("CreateDate"));
						wfRd.setCreateHour(WFUtil.diffHour(wfRd.getCreateDate()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfRd.LastUpd"))
						wfRd.setLastUpd(new Integer(rs.getInt("LastUpd")));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfRd.LastUpdDate"))
						wfRd.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					else if(_fields[i].equals("UsrName") || _fields[i].equals("Usr.UsrName"))
						wfRd.setCreateName(rs.getString("UsrName"));
					else if(_fields[i].equals("StepName") || _fields[i].equals("WfStep.StepName as StepName"))
						wfRd.setStepName(rs.getString("StepName"));
					else if(_fields[i].equals("TaskNm") || _fields[i].equals("SchCfg.SchNm as TaskNm"))
						wfRd.setTaskNm(rs.getString("TaskNm"));
					else if(_fields[i].equals("Parent") || _fields[i].equals("SchCfg.Parent as Parent"))
						wfRd.setParent(rs.getInt("Parent"));
					else if(_fields[i].equals("TaskStepId") || _fields[i].equals("WfStep.StepId as TaskStepId"))
						wfRd.setTaskStepId(rs.getInt("TaskStepId"));
					else if(_fields[i].equals("TaskStatus") || _fields[i].equals("WfRdTask.Status as TaskStatus"))
						wfRd.setTaskStatus(rs.getInt("TaskStatus"));
					else if(_fields[i].equals("TaskId") || _fields[i].equals("WfRdTask.TaskId as TaskId"))
						wfRd.setTaskId(rs.getInt("TaskId"));
					else if(_fields[i].equals("DocCateId") || _fields[i].equals("WfRd.DocCateId"))
						wfRd.setDocCateId(rs.getString("DocCateId"));
					else if(_fields[i].equals("NeedQues") || _fields[i].equals("WfRd.NeedQues"))
						wfRd.setNeedQues(rs.getInt("NeedQues"));
					else if(_fields[i].equals("QuesId") || _fields[i].equals("WfRd.QuesId"))
						wfRd.setQuesId(rs.getInt("QuesId"));
					else if(_fields[i].equals("AS CreateName"))
						wfRd.setCreateName(rs.getString("CreateName"));
					else if( _fields[i].equals("WfRdTask.EndDate as EndDate"))
						wfRd.setEndDate(rs.getTimestamp("EndDate"));
					else if(_fields[i].equals("WfRdTask.CreateDate as AcceptDate"))
						wfRd.setAcceptDate(rs.getTimestamp("AcceptDate"));
					else if(_fields[i].equals("PrjtDef.PrjtNm as PrjtNm"))
						wfRd.setPrjtNm(rs.getString("PrjtNm"));
					
					else if(_fields[i].equals("(select dp.deptNm from Usr us inner join Dept dp on us.OrgNo = dp.deptid where us.id = WfRd.CreateBy ) as deptName"))
						wfRd.setDeptName(rs.getString("DeptName"));
					else if(_fields[i].equals("Url") || _fields[i].equals(taskurl))
						wfRd.setUrl(rs.getString("Url"));
					
				}
				list.add(wfRd);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}