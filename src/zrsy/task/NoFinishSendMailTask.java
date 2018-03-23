package zrsy.task;

import gnmail.facade.MailFacade;
import gnwf.facade.WfRdTaskFacade;
import gnwf.vo.WfRdTask;
import gnwf.ww.workflow.WFUtil;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.apache.log4j.Logger;

import zrsy.vo.Usr;

public class NoFinishSendMailTask implements Job {
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			//清理一周之前的失败邮件
			MailFacade facade = new MailFacade();
			facade.update("update Mail set status = 9 where status = 0 and CreateDate < getdate()-7");
			
			//认证流程最近一周到4个月内未完成任务自动发送催办邮件通知
			String acceptBySql = "select distinct t0.AcceptBy from WfRdTask t0 " +
					"inner join WfRd t1 on t0.WfNo = t1.WfNo " +
					"inner join OPENDATASOURCE ('SQLOLEDB', 'Data Source=16.6.10.17;User ID=pdm;Password=pdmmanager@2014' ).GnSy.dbo.Usr t2 on t0.AcceptBy = t2.id " +
					"inner join OPENDATASOURCE ('SQLOLEDB', 'Data Source=16.6.10.17;User ID=pdm;Password=pdmmanager@2014' ).GnSy.dbo.AddrBook t3 on t2.Id = t3.UserId " +
					"where t0.status in (0,1) and t0.CreateDate between getdate()-120 and getdate()-7 " +
					"and t1.status = 1 and t1.FlowId in (50,51,52) and t2.Status = 1 and t3.MailAddr is not null ";
			
			List<WfRdTask> acceptByList = new WfRdTaskFacade().find(acceptBySql, "AcceptBy");
			
			String noFinishTaskSql = "select t0.AcceptBy,t1.WfNo,t1.WfName as StepName from WfRdTask t0 " +
					"inner join WfRd t1 on t0.WfNo = t1.WfNo " +
					"inner join OPENDATASOURCE ('SQLOLEDB', 'Data Source=16.6.10.17;User ID=pdm;Password=pdmmanager@2014' ).GnSy.dbo.Usr t2 on t0.AcceptBy = t2.id " +
					"inner join OPENDATASOURCE ('SQLOLEDB', 'Data Source=16.6.10.17;User ID=pdm;Password=pdmmanager@2014' ).GnSy.dbo.AddrBook t3 on t2.Id = t3.UserId " +
					"where t0.status in (0,1) and t0.CreateDate between getdate()-120 and getdate()-7 " +
					"and t1.status = 1 and t1.FlowId in (50,51,52) and t2.Status = 1 and t3.MailAddr is not null ";
			
			List<WfRdTask> noFinishTaskList = new WfRdTaskFacade().find(noFinishTaskSql, "AcceptBy,WfNo,StepName");
			
			if(acceptByList != null && acceptByList.size() > 0) {
				for (WfRdTask acceptBy : acceptByList) {
					String WfInfo = "";
					int count = 0;
					for(WfRdTask noFinishTask : noFinishTaskList) {					
						if(acceptBy.getAcceptBy().equals(noFinishTask.getAcceptBy())) {
							WfInfo += noFinishTask.getWfNo() + "(" + noFinishTask.getStepName() + ")，"; 
							count++;
						}
					}
					
					String title = "认证流程工作流未完成任务自动催办通知";
					String content = "尊敬的同事，您好：" +
						"<p>您有" + count + "个未完成任务，工作流编号、工作流名称分别为：" +
						"<p>" + WfInfo + "请尽快办理。" +
						"<p>任务办理链接地址 ： <a href=http://pdm.gionee.com/zrprjt/>http://pdm.gionee.com/zrprjt/</a>";
					List<Usr> userList = new ArrayList<Usr>();
					Usr u = new Usr();
					u.setId(acceptBy.getAcceptBy());
					userList.add(u);
					WFUtil.sendMailByIT(title,content,userList);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error("InsertIntoMail Exception", e);
		}
	}
	
}
