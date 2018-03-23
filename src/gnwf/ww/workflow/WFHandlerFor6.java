package gnwf.ww.workflow;

import gnmail.facade.MailFacade;
import gnmail.vo.Mail;
import gnwf.facade.WfRdFacade;
import gnwf.facade.WfRdTaskFacade;
import gnwf.vo.WfRdTask;
import gnwf.vo.WfStep;
import gnwf.ww.MSG;
import gnwf.ww.json.WfRdViewAction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import zrsy.vo.Usr;


public class WFHandlerFor6 extends WFHandler {

	private static final long serialVersionUID = 4742884725585682821L;
	
	public WFHandlerFor6(WfRdViewAction action) {
		super(action);
	}
	
	@Override
	public boolean sendTask() throws Exception {			//转交任务
		List<WfStep> mustSteps = getNextStepList(currentTask.getTaskId());
		
		//不是最后一个步骤,有要转交的步骤,但并未勾转交任务
		if(currentTask.getIsLastStep()!=1 && WFUtil.isNotNull(mustSteps) && (WFUtil.isNull(wfTasks) || WFUtil.isNull(nextSteps))){
			return false;
		}
		
		List<Usr> userList = null;
		
		//转交任务
		List<WfRdTask> list = new ArrayList<WfRdTask>();
		if(WFUtil.isNotNull(wfTasks)){
			userList = new ArrayList<Usr>();	//邮件提醒list
			
			for(int i=0;i<wfTasks.size();i++){
				WfRdTask t = wfTasks.get(i);
				
				if(t!=null && t.getAcceptBy()!=null){
					Date taskTime = checkStepType(t.getStepId());
					if(taskTime!=null){	
						t.setReqDate(taskTime);
						createTask(t);
						list.add(t);
						
						Usr u = new Usr();
						u.setId(t.getAcceptBy());
						userList.add(u);
					}
				}
			}
			
			if(WFUtil.isNotNull(list)){		//保存任务
				new WfRdTaskFacade().saveAll(list,user.getId());
			}
		}
		
		//更新任务状态
		currentTask.setStatus(MSG.OWFTASK_STATUS_2);
		currentTask.setEndDate(new Date());
		if(currentTask.getAgentBy() == user.getId()){
			currentTask.setAgentDate(new Date());
		}
		new WfRdTaskFacade().update(currentTask);
		
		//启动流程
		if(currentTask.getCreateBy()==-1){
			String sql = "update WfRd set status="+MSG.OWFRD_STATUS_1+",FactSDate=getdate() where (status is null or status = 0)  and WfNo='"+wfRd.getWfNo()+"'";
			new WfRdFacade().update(sql);
		}
		
		//结束流程(最后一个步骤,并且没转交任务)----第三步骤，不能update
		if(currentTask.getSort()!=3){
			if(currentTask.getIsLastStep()==1 && WFUtil.isNull(list)){
				String sql = "update WfRd set status="+MSG.OWFRD_STATUS_2+",FactEDate=getdate() where status >=1  and WfNo='"+wfRd.getWfNo()+"'";
				new WfRdFacade().update(sql);
				
				WFUtil.updateProjtTask(wfRd.getProjectNo(),wfRd.getScheId(),user.getId(),null,new Date(),MSG.PROJTASK_STATUS_3);
			}
		}
		
		//根据任务发送邮件
		sendMail(userList);
		
		//TODO 阅知邮件
		if(mail!=null && WFUtil.isNotNull(mail.getTo())){
			String webUrl = action.getWebUrl();//+"/index.shtml?wfRd.wfNo="+wfRd.getWfNo();
			String title = "工作流阅知："+wfRd.getWfNo();
			String content = "尊敬的同事，您好：" +
				"<p>有一条《"+cfg.getFlowName()+"》流程通知您。" +
				"<p>工作流编号为："+wfRd.getWfNo()+"，工作流名称："+wfRd.getWfName()+"，有空请查看!"+
				"<p>任务办理链接地址 ： <a href="+webUrl+">"+webUrl+"</a>";
			
			Mail _mail = WFUtil.createMail(MSG.ITDEPT_MAIL_ID, MSG.ITDEPT_MAIL, title, content, null);
			_mail.setTo(mail.getTo());
			_mail.setCc(mail.getCc());
			new MailFacade().save(_mail);
		}
		
		return true;
	}
	
//	@Override
//	public boolean nextStepPage() throws Exception{
//		boolean flag = super.nextStepPage();
//		if(currentTask.getSort()==2){
//			//需转交的下一步骤
//			nextSteps = getNextStepList(currentTask.getTaskId());
//			
//			
//			String sql = "select StepId,A.UsrId as UserId,Usr.UsrName,UserType from WfStepUser " +
//					" inner join  (select PrjtRole.RoleId,PrjtRole.roleNm,usrId from PrjtRole,PrjtUsr " +
//					" where PrjtUsr.RoleId = PrjtRole.RoleId and PrjtUsr.status!=0 and prjtNo='"+wfRd.getProjectNo()+"')A " +
//					" on (WfStepUser.PrjtRoleId = A.RoleId) " +
//					" left join usr on(usr.Id = A.UsrId) where stepId=64";
//			System.out.println(sql);
//			List<WfStepUser> autos = new WfStepUserFacade().find(sql,"StepId,UserId,Usr.UsrName,UserType");
//			
//			if(WFUtil.isNotNull(autos)){
//				WfStepUser u = autos.get(0);
//				
//				List<Usr> hisUsers = new ArrayList<Usr>();
//				Usr u1 = new Usr();
//				u1.setId(u.getUserId());
//				u1.setUsrName(u.getUserName());
//				u1.setStepId(u.getStepId());
//				u1.setTaskType(u.getUserType());
//				hisUsers.add(u1);
//				
//				taskCount = genHisUser(hisUsers);
//			}
//			
//			resetActionValue();
//			return true;
//		}
//		return flag;
//	}
}
