package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.frm.comn.GenericUtil;

import gnwf.vo.WfOveSeaUsr;
import gnwf.vo.WfRd;
import gnwf.ww.MSG;
import gnwf.ww.workflow.WFUtil;

public class WfOveSeaUsrHelper extends BasicWfOveSeaUsrHelper {
	public String getSqlString() {
		return " from WfOveSeaUsr ";
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
			sql += " and WfRd.WfNo = '"+wfRd.getWfNo().trim()+"'";
		if(null != wfRd.getProjectNo() && !wfRd.getProjectNo().trim().equals(""))
			sql += " and WfRd.ProjectNo like '%"+wfRd.getProjectNo().trim()+"%'";
		if(null != wfRd.getPreWfNo() && !wfRd.getPreWfNo().trim().equals(""))
			sql += " and WfRd.PreWfNo = '"+wfRd.getPreWfNo().trim()+"'";
		if(null != wfRd.getStartPlanSDate()) 
			sql += " and WfRd.PlanSDate >= '"+GenericUtil.dateFormat(wfRd.getStartPlanSDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getEndPlanSDate()) 
			sql += " and WfRd.PlanSDate <= '"+GenericUtil.dateFormat(wfRd.getEndPlanSDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getStartPlanEDate()) 
			sql += " and WfRd.PlanEDate >= '"+GenericUtil.dateFormat(wfRd.getStartPlanEDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getEndPlanEDate()) 
			sql += " and WfRd.PlanEDate <= '"+GenericUtil.dateFormat(wfRd.getEndPlanEDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getStartFactSDate()) 
			sql += " and WfRd.FactSDate >= '"+GenericUtil.dateFormat(wfRd.getStartFactSDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getEndFactSDate()) 
			sql += " and WfRd.FactSDate <= '"+GenericUtil.dateFormat(wfRd.getEndFactSDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getStartFactEDate()) 
			sql += " and WfRd.FactEDate >= '"+GenericUtil.dateFormat(wfRd.getStartFactEDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getEndFactEDate()) 
			sql += " and WfRd.FactEDate <= '"+GenericUtil.dateFormat(wfRd.getEndFactEDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getStartCreateDate()) 
			sql += " and WfRd.CreateDate >= '"+GenericUtil.dateFormat(wfRd.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getEndCreateDate()) 
			sql += " and WfRd.CreateDate <= '"+GenericUtil.dateFormat(wfRd.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getStartLastUpdDate()) 
			sql += " and WfRd.LastUpdDate >= '"+GenericUtil.dateFormat(wfRd.getStartLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfRd.getEndLastUpdDate()) 
			sql += " and WfRd.LastUpdDate <= '"+GenericUtil.dateFormat(wfRd.getEndLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
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
		if(wfRd.getOnlySelectCurUser()!=null){
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
			//判断是否为海外研发角色
			if(!wfRd.getIsOverseasRole()){
			sql += " and ProjectNo in (select distinct prjtNo from gnprjt.dbo.prjtUsr where usrid = " 
					+ wfRd.getCurrentUserId() + ")" + (wfRd.getFlowId() == null ? "" : (" and WfRd.flowid = " + wfRd.getFlowId()));
		
			}else{
				sql += " and  WfRd.WfNo in (select distinct WfNo from WfRd where flowID >= 40 )" + (wfRd.getFlowId() == null ? "" : (" and WfRd.flowid = " + wfRd.getFlowId()));
			
			}
		}
		/*if(!wfRd.getIsOverseasRole()){
			sql += " or WfRd.WfNo in (select distinct WfNo from WfRd where flowID = 40)" ;
					
		}*/
		System.out.println("111111111111111111111111122"+sql);
		return sql;
	}

	public List<WfOveSeaUsr> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfOveSeaUsr> list = new ArrayList<WfOveSeaUsr>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfOveSeaUsr wfOveSeaUsr = new WfOveSeaUsr();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("WfUsrID") || _fields[i].equals("WfOveSeaUsr.WfUsrID"))
						wfOveSeaUsr.setWfUsrID(rs.getInt("WfUsrID"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfOveSeaUsr.CreateBy"))
						wfOveSeaUsr.setCreateBy(rs.getInt("CreateBy"));
					
					else if(_fields[i].equals("FlowID") || _fields[i].equals("WfOveSeaUsr.FlowID"))
						wfOveSeaUsr.setFlowID(rs.getInt("FlowID"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfOveSeaUsr.CreateDate"))
						wfOveSeaUsr.setCreateDate(rs.getTimestamp("CreateDate"));
					
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfOveSeaUsr.WfNo"))
						wfOveSeaUsr.setWfNo(rs.getString("WfNo"));
					
					
				}
				list.add(wfOveSeaUsr);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("wfOveSeaUsrHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}