package gnwf.ww.workflow;

import gnmail.facade.MailFacade;
import gnmail.vo.Mail;
import gnwf.dao.helper.WfRdTaskHelper;
import gnwf.facade.WfCfgRelateFacade;
import gnwf.facade.WfDocFacade;
import gnwf.facade.WfQuesFacade;
import gnwf.facade.WfRdFacade;
import gnwf.facade.WfRdFieldFacade;
import gnwf.facade.WfRdSignFacade;
import gnwf.facade.WfRdTaskFacade;
import gnwf.facade.WfRiskFacade;
import gnwf.facade.WfScheCfgDocFacade;
import gnwf.facade.WfStepFacade;
import gnwf.facade.WfStepNextFacade;
import gnwf.facade.WfStepUserFacade;
import gnwf.vo.WfCfg;
import gnwf.vo.WfCfgRelate;
import gnwf.vo.WfDoc;
import gnwf.vo.WfQues;
import gnwf.vo.WfRd;
import gnwf.vo.WfRdField;
import gnwf.vo.WfRdSign;
import gnwf.vo.WfRdTask;
import gnwf.vo.WfRisk;
import gnwf.vo.WfScheCfgDoc;
import gnwf.vo.WfStep;
import gnwf.vo.WfStepNext;
import gnwf.vo.WfStepUser;
import gnwf.ww.MSG;
import gnwf.ww.json.WfRdViewAction;

import java.io.File;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import jxl.write.WritableWorkbook;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.opensymphony.webwork.ServletActionContext;

import zrsy.dao.helper.UsrHelper;
import zrsy.facade.AddrBookFacade;
import zrsy.facade.DeptFacade;
import zrsy.facade.UsrFacade;
import zrsy.vo.AddrBook;
import zrsy.vo.Dept;
import zrsy.vo.Usr;


public class WFHandler extends WFAdapter {
	
	private static final long serialVersionUID = 6984838168095376231L;
	protected WfRdViewAction action;
	protected WfRd wfRd;
	protected WfCfg cfg;
	protected WritableWorkbook 		workbook;
	protected File 					impfile;
	protected Usr					user;				//当前用户
	protected WfRdTask 				unAcceptTask;		//未接收任务
	protected WfRdTask 				currentTask;		//当前任务
	protected List<WfRdSign>		signList;			//会签List
	protected List<WfCfgRelate>		relateFlows;		//可触发的相关流程
	protected List<WfRd> 			childList;			//子流程
	protected List<WfRdTask>		taskList;			//任务进度
	protected List<File> 			files;				//附件上传
	protected List<Integer>			fileCates;			//附件类别
	protected List<WfDoc>			docList;			//附件集
	protected List<WfScheCfgDoc> 	docCates;			//本流程需上传文档类别
	protected WfDoc					wfDoc;
	protected List<WfRdField> 		fieldContents;		//表单内容值
	protected WfRdSign				wfRdSign;			//会签意见内容
	protected List<String>			ajaxList;			//ajax接受值
	protected String 				ajaxShowInfo;		//AJAX提示内容
	protected String 				includeJspUri;		//包含页uri
	protected List<WfStep> 			nextSteps;			//需转交下一步骤集合
	protected List<WfRdTask>		wfTasks;			//需转交任务
	protected List<WfStep>			allSteps;			//本流程全部步骤
	protected List<WfStepNext>		allNextSteps;		//对应下一步步骤列表
	protected List<Row> 			rows;				//列表扩展字段集合
	protected int 					taskCount;			//计数
	protected String 				tips;				//提示
	protected Mail					mail;				//邮件
	
	protected List<WfQues>			quesList;			//问题集
	protected Set<WfStep> 			imgStepList;		//画图进度
	
	 
	protected List<WfRdTask>		backTaskssList;			//会签List
	
	public WFHandler(WfRdViewAction action){
		this.action = action;
		this.wfRd = action.getWfRd();
		this.cfg = action.getCfg();
		this.currentTask = action.getCurrentTask();
		this.signList = action.getSignList();
		this.childList = action.getChildList();
		this.taskList = action.getTaskList();
		this.docList = action.getDocList();
		this.fieldContents = action.getFieldContents();
		this.ajaxList = action.getAjaxList();
		this.ajaxShowInfo = action.getAjaxShowInfo();
		this.user = action.getUsrSession();
		this.wfTasks = action.getWfTasks();
		this.nextSteps = action.getNextSteps();
		this.rows = action.getRows();
		this.wfRdSign = action.getWfRdSign();
		this.unAcceptTask = action.getUnAcceptTask();
		this.relateFlows = action.getRelateFlows();
		this.files = action.getFiles();
		this.wfDoc = action.getWfDoc();
		this.taskCount = action.getTaskCount();
		this.docCates = action.getDocCates();
		this.quesList = action.getQuesList();
		this.fileCates = action.getFileCates();
		this.workbook = action.getWorkbook();
		this.impfile = action.getImpfile();
		this.mail = action.getMail();
		this.imgStepList = action.getImgStepList();
	}
	
	@Override
	public void previewExec() throws Exception {	//进入预览页
		//当前未接收任务
		WfRdTask _task = new WfRdTask();
		_task.setWfNo(wfRd.getWfNo());
		_task.setStatus(MSG.OWFTASK_STATUS_0);
		_task.setAcceptBy(user.getId());
		unAcceptTask = new WfRdTaskFacade().findBy(_task);
		
		//当前办理中任务
		currentTask = getRdTask();
		
		//进度
		StringBuffer sb = new StringBuffer();
		sb.append("select ").append(WfRdTask.LIST_FIELDS).append(" from WfRdTask");
	/*	sb.append(" left join WfStep on(WfStep.StepId = WfRdTask.StepId) ");
		sb.append(" left join Usr Usr1 on(WfRdTask.AcceptBy = Usr1.id) ");
		sb.append(" left join Usr Usr2 on(WfRdTask.CreateBy = Usr2.id) ");
		sb.append(" where WfRdTask.WfNo='").append(wfRd.getWfNo()).append("' order by WfStep.Sort");*/
		
		
		sb.append(" left join WfStep on(WfStep.StepId = WfRdTask.StepId) ");
		sb.append(" LEFT JOIN ("
				+ "select id uid, Usr.UsrName FROM Usr WHERE Id in (SELECT ww.AcceptBy FROM WfRdTask ww WHERE ww.WfNo = '"+wfRd.getWfNo()+"')) Usr1 "
				+ "on Usr1.uid = WfRdTask.AcceptBy");
		sb.append(" left join ("
				+ "select id uid, Usr.UsrName FROM Usr WHERE Id in (SELECT ww.CreateBy FROM WfRdTask ww WHERE ww.WfNo = '"+wfRd.getWfNo()+"')) Usr2 "
				+ "on Usr2.uid = WfRdTask.CreateBy");
		sb.append(" where WfRdTask.WfNo='").append(wfRd.getWfNo()).append("' order by WfStep.Sort");
		taskList = new WfRdTaskFacade().find(sb.toString(), WfRdTask.LIST_FIELDS);
		
		//画图进度
		imgStepList = selectImgStepList(wfRd.getFlowId(),wfRd.getWfNo());
		
		//会签
		WfRdSign sign = new WfRdSign();
		sign.setWfNoId(wfRd.getWfNo());
		signList = new WfRdSignFacade().findAll(sign);
		
		//表单
		if(cfg!=null && WFUtil.isNotNull(cfg.getFlowUri())){
			includeJspUri = cfg.getFlowUri();
		}
		
		//扩展字段查询
		String extendSql = "select WfField.FieldId,FieldCode,FieldName,FieldType,NeedFilledOnAPP,FieldTitle,a.FieldText as FieldText,RowId " +
				" from WfField(nolock) left join (select WfNo,FieldId,RowId,FieldText from WfRdField(nolock) " +
				" where wfno='"+wfRd.getWfNo()+"')a " +
				" on(WfField.FieldId=a.FieldId) where flowid="+cfg.getFlowId()+" and status=1" +
				" and WfField.FieldId in(select fieldid from WfFieldStepRelate(nolock) where stepId!=0) order by RowId";
		//System.out.println(extendSql);
		//long s = System.currentTimeMillis();
		fieldContents = new WfRdFieldFacade().find(extendSql, "NeedFilledOnAPP,FieldTitle,FieldId,FieldCode,FieldName,FieldType,FieldText,RowId");
		//System.out.println("work5："+(System.currentTimeMillis()-s));
		
		rows = genFieldRows(fieldContents);
		
		
		//可触发子流程
		WfCfgRelate relateFlow = new WfCfgRelate();
		relateFlow.setFlowId(cfg.getFlowId());
		relateFlows = new WfCfgRelateFacade().findAll(relateFlow);
		
		//子流程
		String preSql = "select "+WfRd.ALL_FIELDS+
				" from WfRd left join Usr on(Usr.Id=WfRd.CreateBy)" +
				" where WfRd.PreWfNo='"+wfRd.getWfNo()+"'";
		childList = new WfRdFacade().find(preSql,WfRd.ALL_FIELDS);
		
		//附件
		WfDoc wfDoc = new WfDoc();
		wfDoc.setWfNo(wfRd.getWfNo());
		wfDoc.setStatus(MSG.CONST_STATUS_1);
		docList = new WfDocFacade().findAll(wfDoc);
		
		//文档类别
//		String sql = "select WfScheCfgDoc.DocId,WfScheCfgDoc.CfgId,WfScheCfgDoc.DocName from WfScheCfgDoc " +
//				"where cfgId in(select cfgId from WfScheCfg where flowId="+cfg.getFlowId()+")";
//		docCates = new WfScheCfgDocFacade().find(sql,"WfScheCfgDoc.DocId,WfScheCfgDoc.DocName");
		
//		String docsql = "select SchDtlId,SchId,DocNm from SchDtl " +
//				"where schid in (select schid from schwf where flowid="+cfg.getFlowId()+")";
//		docCates = new SchDtlFacade().find(docsql, "SchDtlId,SchId,DocNm");
		
		//问题
		if(cfg.getHasQuesMoudle()==1){
			//quesList = GenericUtil.getQuesList(wfRd.getWfNo());
		}
		resetActionValue();
	}
	
	protected String genDocName(List<WfScheCfgDoc> docCates) {
		if(docCates==null || docCates.size()<=0){
			return null;
		}
		StringBuffer str = new StringBuffer();
		for(int i=0;i<docCates.size();i++){
			str.append(docCates.get(i).getDocName()+"、");
		}
		return str.substring(0, str.length()-1);
	}
	

	@Override
	public void acceptTask() throws Exception {							//接收任务
		currentTask.setStatus(MSG.OWFTASK_STATUS_1);
		currentTask.setAcceptDate(new Date());
		new WfRdTaskFacade().update(currentTask);
	}
	
	@Override
	public String rejectTask(int taskStatus) throws Exception {			//退回任务（收回）
		String sql = "select count(*) as amount from WfRdTask where " +
			"preTaskId=(select preTaskId from WfRdTask where taskId="+currentTask.getTaskId()+") " +
			"and taskId!="+currentTask.getTaskId()+" and taskType="+MSG.OWFTASK_TYPE_1+" and status<="+MSG.OWFTASK_STATUS_2;
		
		int count = new WfRdTaskFacade().amount(sql);
		System.out.println(sql);
		if(count>0){
			return "并发类任务，不允许收回/退回。";
		}
		int StepId = currentTask.getStepId();
		System.out.println(StepId+"StepIdStepIdStepIdStepId");
		if(wfRd.getStatus()<MSG.OWFRD_STATUS_2){	//流程办理中
			WfRdTask task = new WfRdTask();
			task.setTaskId(currentTask.getTaskId());
			//task.setRemark(currentTask.getRemark());	//退回原因
			task.setEndDate(new Date());
			task.setStatus(taskStatus);
			new WfRdTaskFacade().rejectTask(task);
			//判断是不是问题转风险审批流程第二步，是就退回并修改问题状态为”待解决-0“
			if (StepId == 362) {
				WfRisk wfRisk = new WfRiskFacade().findById("select * from WfRisk where WfRisk.WfNo = '"+currentTask.getWfNo()+"'", WfRisk.SELF_FIELDS);
				String quesID = wfRisk.getQuesId();
				System.out.println("quesIDquesIDquesIDquesID"+quesID);
				new WfQuesFacade().update("UPDATE WfQues SET WfQues.Status = "+MSG.WFQUES_STATUS_1+" WHERE WfQues.QuesId = '"+quesID+"' ");
			}
			
			//TODO 邮件
			if(taskStatus == MSG.OWFTASK_STATUS_6){		//退回状态
				String mailSql = "select AddrBook.MailAddr from AddrBook where userId =" +
						"(select createby from wfrdtask where taskid="+currentTask.getTaskId()+")";
				AddrBook mail = new AddrBookFacade().findById(mailSql, "AddrBook.MailAddr");
			
				if(mail!=null && mail.getMailAddr()!=null && mail.getMailAddr().matches("\\w+@\\w+\\.\\w+")){
					String webUrl = action.getWebUrl();//+"/index.shtml?wfRd.wfNo="+wfRd.getWfNo();
					String title = "工作流退回任务："+wfRd.getWfNo();
					String content = "尊敬的同事，您好：" +
						"<p>您有一条《"+cfg.getFlowName()+"》流程退回来的任务。" +
						"<p>工作流编号为："+wfRd.getWfNo()+"，工作流名称："+wfRd.getWfName()+"，请尽快办理!"+
						"<p>任务办理链接地址 ： <a href="+webUrl+">"+webUrl+"</a>";
						
					WFUtil.sendMailByIT(title, content, mail.getMailAddr());
				}
			}
		}else{
			return "流程状态已结束办理，不可退回。";
		}
		return null;
	}
	@Override
	public String backTask(int taskStatus) throws Exception {			//收回任务

		String sql = "select count(*) as amount from WfRdTask where " +
			"preTaskId=(select preTaskId from WfRdTask where taskId=( select top 1 taskId  from WfRdTask where  WfNo='"+currentTask.getWfNo()+"' order by taskId desc )) " +
			"and taskId!= ( select top 1 taskId  from WfRdTask where  WfNo='"+currentTask.getWfNo()+"' order by taskId desc ) and WfNo='"+currentTask.getWfNo()+"' and taskType="+MSG.OWFTASK_TYPE_1+" and status<="+MSG.OWFTASK_STATUS_2;

		String top1sql = " select top 1 *  from WfRdTask where  WfNo='"+currentTask.getWfNo()+"' order by taskId desc ";
			
		int count = new WfRdTaskFacade().amount(sql);
		
		
		//System.out.println(sql);
		if(count>0){
			return "并发类任务，不允许收回/退回。";
		}
		
		/*if (currentTask.getStatus() == 1) {
			return "改主办人在处理中，不允许收回任务！";
		}*/
		WfRdTask wTask =new WfRdTaskFacade().findById(top1sql, "WfRdTask.TaskId");
		//System.out.println(wTask.getTaskId());
		int top1taskID = wTask.getTaskId();
		int StepId = currentTask.getStepId();
		//System.out.println(top1taskID+"top1taskID");
		if(wfRd.getStatus()<MSG.OWFRD_STATUS_2){	//流程办理中
			WfRdTask task = new WfRdTask();
			task.setTaskId(top1taskID);
			
			task.setEndDate(new Date());
			task.setStatus(taskStatus);
			new WfRdTaskFacade().rejectTask(task);
			
			
		}else{
			return "流程状态已结束办理，不可退回。";
		}
		return null;
	}
	@Override
	public boolean isHasJob() throws Exception {		//是否存在已接收任务
		if(wfRd.getStatus()<MSG.OWFRD_STATUS_2 && getRdTask()!=null){
			return true;
		}
		return false;
	}
	
	protected WfRdTask getRdTask() throws Exception {	//查当前已接收任务
//		WfRdTask task = new WfRdTask();
//		task.setWfNo(wfRd.getWfNo());
//		task.setAcceptBy(user.getId());
//		task.setStatus(MSG.OWFTASK_STATUS_1);
		
		String sql = "select "+WfRdTask.LIST_FIELDS + new WfRdTaskHelper().getSqlString() +
				" and WfRdTask.wfno='"+wfRd.getWfNo()+"'" +
				" and WfRdTask.status="+MSG.OWFTASK_STATUS_1+
				" and (WfRdTask.AcceptBy="+user.getId()+" or WfRdTask.AgentBy="+user.getId()+")";
		
		List<WfRdTask> all = new WfRdTaskFacade().find(sql,WfRdTask.LIST_FIELDS);
		if(WFUtil.isNotNull(all)){
			return all.iterator().next();
		}
		return null;
	}
	
	@Override
	public void processExec() throws Exception {		//进入办理页
		//当前任务
		//currentTask = getRdTask();
		//currentTask = new WfRdTaskFacade().findById(currentTask);
		
		if(cfg!=null && WFUtil.isNotNull(cfg.getFlowUri())){
			includeJspUri = cfg.getFlowUri();
		}
		if(currentTask!=null && WFUtil.isNotNull(currentTask.getStepUri())){
			includeJspUri = currentTask.getStepUri();
		}
		
		int editFlag = 1;
		if(currentTask.getTaskType()==MSG.OWFTASK_TYPE_2 && currentTask.getIsUpdForm()==0){	//协办，并且协办不可更改表单
			editFlag = 0;
		}
		
		if (currentTask.getStatus() == 0) {
			currentTask.setStatus(1);
			String sql = "  update WfRdTask set Status = '1' where TaskId = '"+currentTask.getTaskId() +"'";
			new WfRdTaskFacade().update(sql);
		}
		//扩展字段查询
		String extendSql = "select WfField.FieldId,FieldCode,FieldName,NeedFilledOnAPP,FieldTitle,FieldType,IsNull,a.FieldText as FieldText,RowId,'0' as isEdit " +
			" from WfField left join (select * from WfRdField(nolock) where wfno='"+wfRd.getWfNo()+"')a " +
			" on(WfField.FieldId=a.FieldId) where flowid="+cfg.getFlowId()+" and status=1 " +
			" and WfField.FieldId in(select fieldid from WfFieldStepRelate where stepId!=0 and stepId!="+currentTask.getStepId()+") " +
			" union " +
			"select WfField.FieldId,FieldCode,FieldName,NeedFilledOnAPP,FieldTitle,FieldType,IsNull,a.FieldText as FieldText,RowId,'"+editFlag+"' as isEdit " +
			" from WfField left join (select * from WfRdField(nolock) where wfno='"+wfRd.getWfNo()+"')a " +
			" on(WfField.FieldId=a.FieldId) where flowid="+cfg.getFlowId()+" and status=1 " +
			" and WfField.FieldId in(select fieldid from WfFieldStepRelate where stepId="+currentTask.getStepId()+")" +
			" order by RowId";
		
		System.out.println(extendSql);
		fieldContents = new WfRdFieldFacade().find(extendSql, "NeedFilledOnAPP,FieldTitle,FieldId,FieldCode,FieldName,FieldType,IsNull,FieldText,RowId,IsEdit");
		
		//long start = System.currentTimeMillis();
		rows = genFieldRows(fieldContents);
		//long end = System.currentTimeMillis();
		//System.out.println("用时："+(end-start));
		
		//进度
		WfRdTask task = new WfRdTask();
		task.setWfNo(wfRd.getWfNo());
		taskList = new WfRdTaskFacade().findAll(task);
		
		//画图进度
		imgStepList = selectImgStepList(wfRd.getFlowId(),wfRd.getWfNo());
		
		//本人会签意见
		WfRdSign sign = new WfRdSign();
		sign.setTaskId(currentTask.getTaskId());
		sign.setUserId(user.getId());
		sign.setWfNoId(wfRd.getWfNo());
		wfRdSign = new WfRdSignFacade().findBy(sign);
		
		//会签List
		WfRdSign s = new WfRdSign();
		s.setWfNoId(wfRd.getWfNo());
		signList = new WfRdSignFacade().findAll(s);
		
		//退回原因集
		WfRdTask back = new WfRdTask();
		//back.setStatus(MSG.OWFTASK_STATUS_6);
		back.setStatus(MSG.OWFTASK_STATUS_6);
		back.setPreTaskId(currentTask.getTaskId());
		List<WfRdTask> backTasks = new WfRdTaskFacade().findAll(back);
		backTaskssList = new WfRdTaskFacade().findAll(back);
		WfRdTask backTask = null;
		/* for (int i = 0; i < backTasks.size(); i++) {
			 backTask = backTasks.get(i);
	            System.out.println(backTask.getRemark());
	        }*/
		currentTask.setBackTasks(backTasks);
		
		//可触发子流程
		WfCfgRelate relateFlow = new WfCfgRelate();
		relateFlow.setFlowId(cfg.getFlowId());
		relateFlows = new WfCfgRelateFacade().findAll(relateFlow);
		
		//已有子流程列表
		String preSql = "select "+WfRd.ALL_FIELDS+
				" from WfRd left join Usr on(Usr.id=WfRd.CreateBy)" +
				" where WfRd.PreWfNo='"+wfRd.getWfNo()+"'";
		childList = new WfRdFacade().find(preSql,WfRd.ALL_FIELDS);
		
		//附件
		WfDoc wfDoc = new WfDoc();
		wfDoc.setWfNo(wfRd.getWfNo());
		wfDoc.setStatus(MSG.CONST_STATUS_1);
		docList = new WfDocFacade().findAll(wfDoc);
		
		//问题
		//quesList = GenericUtil.getQuesList(wfRd.getWfNo());
		//需转交的下一步骤
				nextSteps = getNextStepList(currentTask.getTaskId());
		//查历史转给的用户
				String sqlUser = "select Usr.Id,UsrName,StepId,TaskType from Usr," +
					"(select userId,stepId,taskType from WfStepUserHis where owner="+user.getId()+
					" and stepId in(select nextId from WfStepNext where stepId="+currentTask.getStepId()+"))a " +
					" where Usr.Id = a.userId;";
				System.out.println(sqlUser);
				List<Usr> hisUsers = new UsrFacade().find(sqlUser, "Usr.Id,UsrName,StepId,TaskType");
				
				taskCount = genHisUser(hisUsers);
		//文档类别
		String sql = "select WfScheCfgDoc.DocId,WfScheCfgDoc.DocName from WfScheCfgDoc " +
					" where stepId="+currentTask.getStepId();
		docCates = new WfScheCfgDocFacade().find(sql,"WfScheCfgDoc.DocId,WfScheCfgDoc.DocName");
		currentTask.setDocNames(genDocName(docCates));
		
		//需转交的下一步骤
		//nextSteps = getNextStepList(currentTask.getTaskId());
		
		resetActionValue();
	}
	
	//组扩展字段row
	private List<Row> genFieldRows(List<WfRdField> fieldContents) {		
		if(WFUtil.isNotNull(fieldContents)){
			List<WfRdField> copyList = new ArrayList<WfRdField>();
			for(int i=0;i<fieldContents.size();i++){
				WfRdField f = fieldContents.get(i);
				f.setCount(i);
				if(f.getRowId()>0){	//过滤单值的扩展字段
					copyList.add(f);
				}
			}
			
			List<Row> rows = null;
			if(WFUtil.isNotNull(copyList)){
				rows = new ArrayList<Row>();
				for(int i=1;copyList.size()>0;i++){
					Row r = new Row(i);
					
					Iterator<WfRdField> iter = copyList.iterator();
					while(iter.hasNext()){
						WfRdField f = iter.next();
						if(f.getRowId()!=i){
							continue;
						}
						if(r.addField(f)){
							iter.remove();
							continue;
						}
					}
					if(WFUtil.isNotNull(r.getFileds())){
						rows.add(r);
					}
				}
			}
			return rows;
		}
		return null;
	}
	

	@Override
	public boolean nextStepPage() throws Exception {		//下一步骤页面,true为需要进入转交页,false提示任务完成
		//long ss = System.currentTimeMillis();
		currentTask = new WfRdTaskFacade().findById(currentTask);
		
		System.out.println("@@@@@@@@"+currentTask.getTaskId());
		
		if(currentTask.getIsLastStep()==1){
//			String mailSql = "select MailName,MailAddr from MailCfg where userid="+wfRd.getCreateBy();
//			MailCfg m = new MailCfgFacade().findById(mailSql, "MailName,MailAddr");
//			if(m!=null && m.getMailAddr()!=null){
//				action.setMailName(m.getMailName()+"<"+m.getMailAddr()+">");
//			}
			currentTask.setStatus(MSG.OWFTASK_STATUS_2);
			currentTask.setEndDate(new Date());
			new WfRdTaskFacade().update(currentTask);
			
			if(currentTask.getTaskType()==MSG.OWFTASK_TYPE_1 ){	//最后一步只有主办人完成后才更新整个工作流的状态为完成
			//if(currentTask.getIsLastStep()==1 && WFUtil.isNull(list)){
			    //更改流程 和进度的状态
				String sql = "update WfRd set status="+MSG.OWFRD_STATUS_2+",FactEDate=getdate() where status >=1  and WfNo='"+wfRd.getWfNo()+"'";
				new WfRdFacade().update(sql);
				WFUtil.updateProjtTask(wfRd.getProjectNo(),wfRd.getScheId(),user.getId(),null,new Date(),MSG.PROJTASK_STATUS_3);
			//}
			}
			
			return false;
		}
		
		//协办无需转交
		if(currentTask!=null && currentTask.getTaskType()==MSG.OWFTASK_TYPE_2){
			currentTask.setStatus(MSG.OWFTASK_STATUS_2);
			currentTask.setEndDate(new Date());
			new WfRdTaskFacade().update(currentTask);
			return false;
		}
		
		//其它并发任务未完成，无需转交
		if(currentTask.getPreTaskId()!=null && currentTask.getPreTaskId()!=0){
			String sql = "select count(*) as amount from WfRdTask inner join WfStep on(WfRdTask.stepId=WfStep.stepId) " +
				" where wfno='"+currentTask.getWfNo()+"' and preTaskId="+currentTask.getPreTaskId()+
				" and taskId!="+currentTask.getTaskId()+" and WfStep.stepType!="+MSG.OWFSTEP_TYPE_1+
				" and WfRdTask.tasktype="+MSG.OWFTASK_TYPE_1+" and WfRdTask.status<"+MSG.OWFTASK_STATUS_2;
			
			int count = new WfRdTaskFacade().amount(sql);
			if(count>0){
				currentTask.setStatus(MSG.OWFTASK_STATUS_2);
				currentTask.setEndDate(new Date());
				new WfRdTaskFacade().update(currentTask);
				return false;
			}
		}
		
		
		//自动流转部分
		boolean autoFlag = autoSendTask(currentTask.getStepId(),wfRd.getProjectNo(),true);
		if(autoFlag){
			currentTask.setStatus(MSG.OWFTASK_STATUS_2);
			currentTask.setEndDate(new Date());
			new WfRdTaskFacade().update(currentTask);
			
			//启动流程
			if(currentTask.getCreateBy()==-1){
				String sql = "update WfRd set status="+MSG.OWFRD_STATUS_1+",FactSDate=getdate() where (status is null or status = 0)  and WfNo='"+wfRd.getWfNo()+"'";
				new WfRdFacade().update(sql);
			}
			
			return false;
		}
		
		//进入转交页部分--预览全部步骤
		WfStep wfStep = new WfStep();
		wfStep.setFlowId(wfRd.getFlowId());
		wfStep.setStatus(MSG.CONST_STATUS_1);
		allSteps = new WfStepFacade().findAll(wfStep);
		
		//全部步骤--对应下一步的步骤
		String nextSql = "select wfstepnext.stepId,wfstepnext.nextId,WfStep.Sort,WfStep.StepType from wfstepnext " +
				"left join wfstep on(wfstepnext.nextId = wfstep.stepId) " +
				"where wfstep.flowId="+wfRd.getFlowId();
		allNextSteps= new WfStepNextFacade().find(nextSql,"WfStepNext.StepId,WfStepNext.NextId,WfStep.Sort,WfStep.StepType");
		
		
		//需转交的下一步骤
		nextSteps = getNextStepList(currentTask.getTaskId());
		
		//查历史转给的用户
		String sql = "select Usr.Id,UsrName,StepId,TaskType from Usr," +
			"(select userId,stepId,taskType from WfStepUserHis where owner="+user.getId()+
			" and stepId in(select nextId from WfStepNext where stepId="+currentTask.getStepId()+"))a " +
			" where Usr.Id = a.userId;";
		List<Usr> hisUsers = new UsrFacade().find(sql, "Usr.Id,UsrName,StepId,TaskType");
		
		taskCount = genHisUser(hisUsers);
		
		//最后一步阅知申请人
		
		
		resetActionValue();
		//System.out.println(System.currentTimeMillis()-ss);
		return true;
	}
	
	@Override
	public boolean needSpecifyNext() throws Exception {
		
        currentTask = new WfRdTaskFacade().findById(currentTask);
		if(currentTask.getIsLastStep()==1){
			return false;
		}
		
		//协办无需转交
		if(currentTask!=null && currentTask.getTaskType()==MSG.OWFTASK_TYPE_2){
			return false;
		}
		
		//其它并发任务未完成，无需转交
		if(currentTask.getPreTaskId()!=null && currentTask.getPreTaskId()!=0){
			String sql = "select count(*) as amount from WfRdTask inner join WfStep on(WfRdTask.stepId=WfStep.stepId) " +
				" where wfno='"+currentTask.getWfNo()+"' and preTaskId="+currentTask.getPreTaskId()+
				" and taskId!="+currentTask.getTaskId()+" and WfStep.stepType!="+MSG.OWFSTEP_TYPE_1+
				" and WfRdTask.tasktype="+MSG.OWFTASK_TYPE_1+" and WfRdTask.status<"+MSG.OWFTASK_STATUS_2;
			int count = new WfRdTaskFacade().amount(sql);
			if(count>0){
				return false;
			}
		}
		
		//自动流转部分
		boolean autoFlag = autoSendTask(currentTask.getStepId(),wfRd.getProjectNo(),false);
		if(autoFlag){
			return false;
		}
		//进入转交页部分--预览全部步骤
		WfStep wfStep = new WfStep();
		wfStep.setFlowId(wfRd.getFlowId());
		wfStep.setStatus(MSG.CONST_STATUS_1);
		allSteps = new WfStepFacade().findAll(wfStep);
		
		//全部步骤--对应下一步的步骤
		String nextSql = "select wfstepnext.stepId,wfstepnext.nextId,WfStep.Sort,WfStep.StepType from wfstepnext " +
				"left join wfstep on(wfstepnext.nextId = wfstep.stepId) " +
				"where wfstep.flowId="+wfRd.getFlowId();
		allNextSteps= new WfStepNextFacade().find(nextSql,"WfStepNext.StepId,WfStepNext.NextId,WfStep.Sort,WfStep.StepType");
		
		//DCC流程材料认证部工程师审步骤需要判断物料等级是否降级,降级下一步只能走策略工程师审核    
		if(currentTask!=null&&currentTask.getStepId()!=null&&currentTask.getStepId()==356){
			System.out.println("材料工程师验证::::::::::::::::::::");
			List<String>E5 =new ArrayList<String>();
			List<String>E15 =new ArrayList<String>();
			List<WfRdField> file=action.getFieldContents();
			System.out.println(file.size());
			if(file!=null&&file.size()>0){
				for (int i = 0; i < file.size(); i++) {
					if(file.get(i).getFieldName().equals("E5")){
						if(!StringUtils.isEmpty(file.get(i).getFieldText())){
							E5.add(file.get(i).getFieldText());
						}
					}else if(file.get(i).getFieldName().equals("E15")){
						if(!StringUtils.isEmpty(file.get(i).getFieldText())){
							E15.add(file.get(i).getFieldText());
						}
						//E15.add(file.get(i).getFieldText());
					}
				}
			}
			if(E5.size()>0){
				for (int i = 0; i < E5.size(); i++) {//2类情况 A改为C或D或E 和 C改为D或E
					if(!StringUtils.isEmpty(E5.get(i))&&!StringUtils.isEmpty(E15.get(i))&&E5.get(i).equals("A")){
						if(E15.get(i).equals("C")||E15.get(i).equals("D")||E15.get(i).equals("E")){
							action.setFlagDcc("1");
						}
					}else if(!StringUtils.isEmpty(E5.get(i))&&!StringUtils.isEmpty(E15.get(i))&&E5.get(i).equals("C")){
						if(E15.get(i).equals("D")||E15.get(i).equals("E")){
							action.setFlagDcc("1");
						}
					}
				}
			}
		
		}
		//需转交的下一步骤
		nextSteps = getNextStepList(currentTask.getTaskId());
		
		//查历史转给的用户
		String sql = "select Usr.Id,UsrName,StepId,TaskType from Usr," +
			"(select userId,stepId,taskType from WfStepUserHis where owner="+user.getId()+
			" and stepId in(select nextId from WfStepNext where stepId="+currentTask.getStepId()+"))a " +
			" where Usr.Id = a.userId;";
		List<Usr> hisUsers = new UsrFacade().find(sql, "Usr.Id,UsrName,StepId,TaskType");
		
		taskCount = genHisUser(hisUsers);
		
		//最后一步阅知申请人
		
		resetActionValue();
		//System.out.println(System.currentTimeMillis()-ss);
		return true;
	}
	
	//TODO 自动流转部分
	protected boolean autoSendTask(int currentStepId,String projectNo,boolean needSendTask)throws Exception {
		List<WfStepUser> autos = null;
		
		if(WFUtil.isNull(autos)){		//先查询是否发给创建者
			String autoSql = "select StepId,A.createby as UserId,1 as UserType from WfStep," +
					" (select createby from wfrd where wfno='"+currentTask.getWfNo()+"')A " +
					" where issysstartup!=1 and isAuto=1 and isSendToCreate=1 " +
					" and stepId in(select nextId from WfStepNext where stepId="+currentStepId+")";
			autos = new WfStepUserFacade().find(autoSql,"StepId,UserId,UserType");
		}
		
		if(projectNo!=null&&!projectNo.trim().isEmpty()){
			
			if(WFUtil.isNull(autos)){		//先查询项目角色对应者
				String prjt = projectNo==null?"":" or prjtNo='"+projectNo+"'";
				String autoSql = "select StepId,A.UsrId as UserId,UserType from WfStepUser " +
						" left join " +
						" (select PrjtRole.RoleId,PrjtRole.roleNm,usrId from PrjtRole,PrjtUsr " +
						" where PrjtUsr.RoleId = PrjtRole.RoleId and PrjtUsr.status!=0 and (roletyp=0 "+prjt+"))A " +
						" on (WfStepUser.PrjtRoleId = A.RoleId)" +
						" where stepId in(select stepId from WfStep where stepId " +
						"   in(select nextId from WfStepNext where stepId="+currentStepId+") and issysstartup!=1 and isAuto=1)";
				autos = new WfStepUserFacade().find(autoSql,"StepId,PrjtUsr.UsrId as UserId,UserType");
			}
			
		}
		if(WFUtil.isNull(autos)){		//如果为空，则查询自动流转用户
			String autoSql = "select StepId,UserId,UserType from WfStepUser where stepId in(" +
							"select stepId from WfStep where issysstartup!=1 and stepId in(" +
							"select nextId from WfStepNext where stepId="+currentStepId+" ))";
			autos = new WfStepUserFacade().find(autoSql,"StepId,UserId,UserType");
		}
		
		List<WfRdTask> autoList = new ArrayList<WfRdTask>();
		List<Usr> userList = new ArrayList<Usr>();
		if(WFUtil.isNotNull(autos)){		//不为空则自动转交
			for(int i=0;i<autos.size();i++){
				WfStepUser s = autos.get(i);
				if(s!=null && WFUtil.isNotNull(s.getUserId()) && WFUtil.isNotNull(s.getStepId())){
					WfRdTask t = createTask(null);
					t.setAcceptBy(s.getUserId());
					t.setStepId(s.getStepId());
					t.setTaskType(s.getUserType());
					
					t.setPreTaskId(currentTask.getTaskId());
					autoList.add(t);
					
					Usr u = new Usr();
					u.setId(s.getUserId());
					userList.add(u);
				}
			}
		}
		
		if(WFUtil.isNotNull(autoList)){		//不为空有自动流转
			if(needSendTask){
				new WfRdTaskFacade().saveAll(autoList);
				sendMail(userList);
			}
			return true;
		}
		
		//无自动流转返回false
		return false;
	}
	

	//查图片式进度List
	protected Set<WfStep> selectImgStepList(int flowId,String wfno)throws Exception {
		Set<WfStep> steps = new TreeSet<WfStep>();
		
		//所有步骤
//		String stepSql = "select WfStep.StepId,WfStep.Sort,StepName,B.NextId,A.EndDate from wfstep " +
//				"left join (select stepid,enddate from wfrdtask where taskid in(" +
//				" select min(taskid) from wfrdtask " +
//				" where wfrdtask.wfno='"+wfno+"' and status=2 and tasktype=1 and enddate is not null " +
//				" group by wfrdtask.stepid))A on(A.stepId=wfstep.stepId) " +
//				"left join (select stepId,nextId from wfstepnext group by stepId,nextId)B " +
//				" on(wfstep.stepId=B.stepId)  " +
//				"where wfstep.flowid="+flowId+" order by WfStep.sort";
		String stepSql = "select WfStep.StepId,WfStep.Sort,WfStep.StepName,A.EndDate,B.NextId,B.sort,B.nextenddate from wfstep " +
				" left join (select stepid,enddate from wfrdtask where taskid " +
				" in( select min(taskid) from wfrdtask  where wfrdtask.wfno='"+wfno+"' " +
				" and status=2 and tasktype=1 and enddate is not null  group by wfrdtask.stepid))A on(A.stepId=wfstep.stepId) " +
				" left join (select C.stepId as stepId,C.nextId as nextId,wfstep.sort as sort,A2.EndDate as nextenddate from wfstep " +
				" left join (select stepid,enddate from wfrdtask where taskid " +
				" in( select min(taskid) from wfrdtask  where wfrdtask.wfno='"+wfno+"' " +
				" and status=2 and tasktype=1 and enddate is not null  group by wfrdtask.stepid))A2 on(A2.stepId=wfstep.stepid) " +
				" left join (select stepId,nextId from wfstepnext group by stepId,nextId)C " +
				" on (wfstep.stepid=C.nextId) where wfstep.flowid="+flowId+")B  on(wfstep.stepId=B.stepId and wfstep.sort<B.sort)  " +
				" where wfstep.flowid="+flowId+" order by WfStep.sort,B.nextenddate desc";
		List<WfStep> all = new WfStepFacade().find(stepSql,"WfStep.StepId,WfStep.Sort,StepName,A.EndDate,B.NextId");
		
		//当前在办步骤
		String sql = "select top 1 WfRdTask.TaskId,WfRdTask.StepId,WfStep.StepName,WfStep.Sort from wfrdtask " +
				" left join WfStep on(wfstep.stepid=wfrdtask.stepid) " +
				" where wfrdtask.wfno='"+wfno+"' and wfrdtask.status=1 order by WfRdTask.TaskId";
		WfRdTask task = new WfRdTaskFacade().findById(sql, "WfRdTask.TaskId,WfRdTask.StepId,WfStep.StepName,WfStep.Sort");
		
		int currSortId = -1;
		if(task!=null){
			currSortId = task.getSort();
			WfStep step = new WfStep(task.getStepId(),task.getStepName(),task.getSort(),1);
			steps.add(step);
		}
		
		if(WFUtil.isNotNull(all)){
			if(all.get(0)!=null && all.get(0).getSort()!=currSortId){
				steps.add(all.get(0));
			}
			genStepList(steps, all,all.get(0),currSortId);
		}	
		
//		for(WfStep s:steps){
//			System.out.println(s.getStepName()+"---"+s.getTaskTime()+"---"+s.getIsCurrent());
//		}
		
		return steps;
	}
	
	protected void genStepList(Set<WfStep> result,List<WfStep> all,WfStep node,int currSortId){
		for(WfStep s:all){
			if(node.getNextStepId()==s.getStepId()){
				if(currSortId!=s.getSort()){
					result.add(s);
				}
				
				int sortId = s.getSort();
				if(node.getSort()!=sortId){
					genStepList(result,all,s,currSortId);
				}
				
				break;
			}
		}
		
	}
	
	protected void genStepList(List<WfStep> result,List<WfStep> all,WfStep node,int currSortId){
		for(WfStep s:all){
			if(node.getNextStepId()==s.getStepId()){
				if(currSortId!=s.getSort()){
					result.add(s);
				}
				
				int sortId = s.getSort();
				if(node.getSort()!=sortId){
					genStepList(result,all,s,currSortId);
				}
				
				break;
			}
		}
		
	}
	
	protected int genHisUser(List<Usr> hisUsers) {	//组值--历史转交用户
		int count = 0;
		if(WFUtil.isNotNull(nextSteps) && WFUtil.isNotNull(hisUsers)){
			for(int i=0;i<nextSteps.size();i++){
				WfStep s = nextSteps.get(i);
				
				StringBuffer idStr = new StringBuffer();
				StringBuffer nameStr = new StringBuffer();
				StringBuffer taskStr = new StringBuffer();
				StringBuffer idStr2 = new StringBuffer();
				StringBuffer nameStr2 = new StringBuffer();
				StringBuffer taskStr2 = new StringBuffer();
				for(int j=0;j<hisUsers.size();j++){
					Usr u = hisUsers.get(j);
					String sql = "select " + Usr.SELF_DEPT_FIELDS
							+ " from UsrView Usr INNER JOIN   Dept ON Dept.DeptId = Usr.DeptId where 1=1  "
							+ "And Usr.ID = " + u.getId();
					if(s!=null && u!=null && s.getStepId().equals(u.getStepId())){
						if(u.getTaskType()==1){			//主办
							idStr.append(u.getId()+",");
							
							String deptname = GetDeptName(sql);
							nameStr.append(u.getUsrName()+"--"+deptname+",");
							taskStr.append("<input type=\"hidden\" name=\"wfTasks["+count+"].stepId\" value=\""+u.getStepId()+"\">" +
									"<input type=\"hidden\" name=\"wfTasks["+count+"].acceptBy\" value=\""+u.getId()+"\">" +
									"<input type=\"hidden\" name=\"wfTasks["+count+"].taskType\" value=\"1\"> ");
							count++;
						}
						/*else if (u.getTaskType()==2) {	//协办
							String deptname = GetDeptName(sql);
							idStr2.append(u.getId()+"--"+deptname+",");
							nameStr2.append(u.getUsrName()+",");
							taskStr2.append("<input type=\"hidden\" name=\"wfTasks["+count+"].stepId\" value=\""+u.getStepId()+"\">" +
									"<input type=\"hidden\" name=\"wfTasks["+count+"].acceptBy\" value=\""+u.getId()+"\">" +
									"<input type=\"hidden\" name=\"wfTasks["+count+"].taskType\" value=\"2\"> ");
							count++;
						}*/
					}
				}
				
				if(idStr.length()>0){
					s.setIdText(idStr.substring(0,idStr.length()-1).toString());
				}
				if(nameStr.length()>0){
					s.setNameText(nameStr.substring(0,nameStr.length()-1).toString());
				}
				if(taskStr.length()>0){
					s.setTaskText(taskStr.substring(0,taskStr.length()-1).toString());
				}
				/*if(idStr2.length()>0){
					s.setIdText2(idStr2.substring(0,idStr2.length()-1).toString());
				}
				if(nameStr2.length()>0){
					s.setNameText2(nameStr2.substring(0,nameStr2.length()-1).toString());
				}
				if(taskStr2.length()>0){
					s.setTaskText2(taskStr2.substring(0,taskStr2.length()-1).toString());
				}*/
			}
		}
		
		return count;
	}
	
	
	private String GetDeptName(String sql) {
		String deptname = null;
		try {
			Dept dept2 = new DeptFacade().findById(sql, "Dept.DeptNm");
			if(dept2 != null) {
			 deptname = dept2.getDeptNm();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deptname;
	}

	//取需转交的下一步骤
	protected List<WfStep> getNextStepList(int taskId) throws Exception {
		String sql = "select WfStep.StepId,FlowId,StepType,StepName,Sort,isAuto from wfstepnext " +
			" left join wfstep on (wfstepnext.nextid=wfstep.stepid) " +
			" where wfstep.isSysStartUp!=1  and  wfstepnext.stepid=(select stepId from wfrdtask where taskid="+taskId+");";
		List<WfStep> list = new WfStepFacade().find(sql, "WfStep.StepId,FlowId,StepType,StepName,Sort,isAuto");
		return list;
	}

	@Override
	public boolean sendTask() throws Exception {			//转交任务
		List<WfStep> mustSteps = getNextStepList(currentTask.getTaskId());
		
		//不是最后一个步骤,有要转交的步骤,但并未勾转交任务
		if(currentTask.getIsLastStep()!=1 && WFUtil.isNotNull(mustSteps) && (WFUtil.isNull(wfTasks) || WFUtil.isNull(nextSteps))){
			System.out.println("wfTasks"+wfTasks);
			System.out.println("nextSteps"+nextSteps);
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
		
		//结束流程(最后一个步骤,并且没转交任务)
		if(currentTask.getIsLastStep()==1 && WFUtil.isNull(list)){
			String sql = "update WfRd set status="+MSG.OWFRD_STATUS_2+",FactEDate=getdate() where status >=1  and WfNo='"+wfRd.getWfNo()+"'";
			new WfRdFacade().update(sql);
			
			WFUtil.updateProjtTask(wfRd.getProjectNo(),wfRd.getScheId(),user.getId(),null,new Date(),MSG.PROJTASK_STATUS_3);
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
	
	//任务发邮件,提示提醒
	
	
	protected void sendMail(List<Usr> userList)throws Exception {
		
		/*过滤掉不发邮件通知的人*/
		List<Usr> removeUsrs= new ArrayList<Usr>();
		for(Usr u:userList){
			for(int i=0;i<MSG.NoSendMail_UserIDS.length;i++){
				if(Integer.valueOf(MSG.NoSendMail_UserIDS[i]).intValue()==u.getId().intValue()){
					System.out.println(u.getId().intValue());
					removeUsrs.add(u);
				}
			}
		}
		userList.removeAll(removeUsrs);
		
		
		if(WFUtil.isNotNull(userList)){	
			
			for(Usr u:userList){
				
				System.out.println(u.getId().intValue());
				
			}
			
			String webUrl = action.getWebUrl();//+"/index.shtml?wfRd.wfNo="+wfRd.getWfNo();
			String title = "工作流任务--"+cfg.getFlowName()+"："+wfRd.getWfNo();
			String content = "尊敬的同事，您好：" +
				"<p>您有一条《"+cfg.getFlowName()+"》流程的任务需要办理。" +
				"<p>工作流编号为："+wfRd.getWfNo()+"，工作流名称："+wfRd.getWfName()+"，请尽快处理，谢谢!"+
				"<p>任务办理链接地址 ： <a href="+webUrl+">"+webUrl+"</a>";
			
			WFUtil.sendMailByIT(title, content, userList);
		}
	}
	
	protected Date checkStepType(int stepId) {	//为空则表示没有勾选此任务
		if(WFUtil.isNotNull(nextSteps)){
			for(int j=0;j<nextSteps.size();j++){
				WfStep step = nextSteps.get(j);
				if(step!=null && step.getStepId()!=null && stepId == step.getStepId()){
					if(step.getTaskTime()==null){
						step.setTaskTime(new Date());
					}
					return step.getTaskTime();
				}
			}
		}
		return null;
	}
	
	protected WfRdTask createTask(WfRdTask t) {		//创建任务
		if(t==null){
			t = new WfRdTask();
		}
		if(t.getReqDate()==null){
			t.setReqDate(new Date());
		}else{
			if(WFUtil.diffDate(new Date(),t.getReqDate())==null){
				t.setReqDate(new Date());
			}
		}
		t.setCreateBy(user.getId());
		t.setCreateDate(new Date());
		t.setPreTaskId(currentTask.getTaskId());
		t.setWfNo(wfRd.getWfNo());
		t.setStatus(MSG.OWFTASK_STATUS_0);
		return t;
	}
	
	@Override
	public void saveJob() throws Exception {			//保存任务
		//System.out.println(fieldContents);
		if(WFUtil.isNotNull(fieldContents)){
			new WfRdFieldFacade().saveAll(fieldContents,wfRd.getWfNo());
		}
		saveSign();
		saveFile();
		//action.setTips("任务保存成功。。");
	}
	
	protected void saveSign()throws Exception {//保存会签意见
		if(wfRdSign!=null){
			WfRdSign sign = new WfRdSign(wfRdSign.getSignText(),wfRd.getWfNo(),currentTask.getTaskId(),user.getId());
			new WfRdSignFacade().saveAndDel(sign);
		}
	}
	
	protected void saveFile()throws Exception {//保存附件
		if(files!=null){
			for(int i=0;i<files.size();i++){
				File attach = files.get(i);
				
				if(attach != null) {
					String path="/upload/wfdoc/"+new SimpleDateFormat("yyyyMMdd").format(new Date())+"/"+user.getLogin()+"/"+WFUtil.UUID().replaceAll("-", "");
					File tempfile = new File(action.getServPath()+path+"/"+attach.getName());

					tempfile.getParentFile().mkdirs(); 
					FileUtils.copyFile(files.get(i),tempfile);
					//GenericUtil.ConvertToSwf(action.getServPath()+path+"/",attach.getName());
					
					WfDoc wfDoc = new WfDoc();
					wfDoc.setDocName(attach.getName());
					wfDoc.setWfNo(wfRd.getWfNo());
					wfDoc.setUriLink(path+"/"+attach.getName());
					wfDoc.setStatus(MSG.CONST_STATUS_1);
					wfDoc.setCreateBy(user.getId());
					wfDoc.setCreateDate(new Date());
					wfDoc.setCateId(fileCates.get(i));			//类别
					if(currentTask!=null && currentTask.getTaskId()!=null){
						wfDoc.setTaskId(currentTask.getTaskId());
					}
					wfRd.addtoWfDocs(wfDoc);
				}
			}
			new WfDocFacade().saveAll(wfRd.getWfNo(),wfRd.getWfDocs());
		}
	}
	
	@Override
	public void addFiles()throws Exception {
		saveFile();
	}

	@Override
	public void print() throws Exception {				//打印
		//扩展字段查询
		String extendSql = "select WfField.FieldId,FieldCode,FieldName,NeedFilledOnAPP,FieldTitle,FieldType,IsNull,a.FieldText as FieldText,RowId,'0' as isEdit " +
			" from WfField left join (select * from WfRdField where wfno='"+wfRd.getWfNo()+"')a " +
			" on(WfField.FieldId=a.FieldId) where flowid="+cfg.getFlowId()+" and status=1 " +
			" and WfField.FieldId in(select fieldid from WfFieldStepRelate where stepId!=0) order by RowId";
		
		fieldContents = new WfRdFieldFacade().find(extendSql, "NeedFilledOnAPP,FieldTitle,FieldId,FieldCode,FieldName,FieldType,IsNull,FieldText,RowId,IsEdit");
		rows = genFieldRows(fieldContents);
		
		WfRdTask task = new WfRdTask();
		task.setWfNo(wfRd.getWfNo());
		taskList = new WfRdTaskFacade().findAll(task);
		
		if(cfg!=null && WFUtil.isNotNull(cfg.getPrintUri())){
			includeJspUri = cfg.getPrintUri();
		}
		resetActionValue();
	}
	
	@Override
	public String genAjaxInfo() throws Exception {		//ajax提示通用处理
		ajaxShowInfo = "true";
		
//		if(WFUtil.isNotNull(ajaxList)){
//			for(int i=0;i<ajaxList.size();i++){
//				System.out.println(ajaxList.get(i));
//			}
//		}
		
		if(WFUtil.isNotNull(ajaxList)){
			if("countQues".equals(ajaxList.get(0))){
				String sql = "select count(quesid) as amount from wfques " +
						" where createby="+user.getId()+" and wfno='"+ajaxList.get(1)+"';";
				System.out.println(sql);
				int count = new WfRdFacade().amount(sql);
				ajaxShowInfo = count+"";
			}
			else if("countDQA".equals(ajaxList.get(0))){
				String sql = "select count(quesid) as amount from wfques " +
						" where wfno='"+ajaxList.get(1)+"' and status<="+MSG.WFQUES_STATUS_11 +" and status<>0";
				System.out.println(sql);
				int count = new WfRdFacade().amount(sql);
				ajaxShowInfo = count+"";
			}
		}
		
		return ajaxShowInfo;
	}
	
	@Override
	public String exportXls()throws Exception {							//导出
		return null;
	}

	@Override
	public String importXls()throws Exception {							//导入
		return null;
	}
	
	protected void resetActionValue() {
		action.setTaskCount(taskCount);
		action.setCurrentTask(currentTask);
		action.setIncludeJspUri(includeJspUri);
		action.setFieldContents(fieldContents);
		action.setNextSteps(nextSteps);
		action.setTaskList(taskList);
		action.setSignList(signList);
		action.setChildList(childList);
		action.setAllNextSteps(allNextSteps);
		action.setAllSteps(allSteps);
		action.setRows(rows);
		action.setWfRdSign(wfRdSign);
		action.setUser(user);
		action.setUnAcceptTask(unAcceptTask);
		action.setDocList(docList);
		action.setRelateFlows(relateFlows);
		action.setFiles(files);
		action.setWfDoc(wfDoc);
		action.setTaskCount(taskCount);
		action.setDocCates(docCates);
		action.setQuesList(quesList);
		action.setImgStepList(imgStepList);
		action.setBackTaskssList(backTaskssList);
	}
	
	public WfRdViewAction getAction() {
		return action;
	}
	public void setAction(WfRdViewAction action) {
		this.action = action;
	}
	public WfRd getwfRd() {
		return wfRd;
	}
	public void setwfRd(WfRd wfRd) {
		this.wfRd = wfRd;
	}
	public WfCfg getCfg() {
		return cfg;
	}
	public void setCfg(WfCfg cfg) {
		this.cfg = cfg;
	}
	public List<WfRdSign> getSignList() {
		return signList;
	}
	public void setSignList(List<WfRdSign> signList) {
		this.signList = signList;
	}
	public List<WfRd> getChildList() {
		return childList;
	}
	public void setChildList(List<WfRd> childList) {
		this.childList = childList;
	}
	public List<WfRdTask> getTaskList() {
		return taskList;
	}
	public void setTaskList(List<WfRdTask> taskList) {
		this.taskList = taskList;
	}
	public List<WfRdField> getFieldContents() {
		return fieldContents;
	}
	public void setFieldContents(List<WfRdField> fieldContents) {
		this.fieldContents = fieldContents;
	}
	public String getAjaxShowInfo() {
		return ajaxShowInfo;
	}
	public void setAjaxShowInfo(String ajaxShowInfo) {
		this.ajaxShowInfo = ajaxShowInfo;
	}
	public WfRdTask getCurrentTask() {
		return currentTask;
	}
	public void setCurrentTask(WfRdTask currentTask) {
		this.currentTask = currentTask;
	}
	public String getIncludeJspUri() {
		return includeJspUri;
	}
	public void setIncludeJspUri(String includeJspUri) {
		this.includeJspUri = includeJspUri;
	}
	public List<WfStep> getNextSteps() {
		return nextSteps;
	}
	public void setNextSteps(List<WfStep> nextSteps) {
		this.nextSteps = nextSteps;
	}
	public List<WfRdTask> getWfTasks() {
		return wfTasks;
	}
	public void setWfTasks(List<WfRdTask> wfTasks) {
		this.wfTasks = wfTasks;
	}
	public List<WfStep> getAllSteps() {
		return allSteps;
	}
	public void setAllSteps(List<WfStep> allSteps) {
		this.allSteps = allSteps;
	}
	public List<WfStepNext> getAllNextSteps() {
		return allNextSteps;
	}
	public void setAllNextSteps(List<WfStepNext> allNextSteps) {
		this.allNextSteps = allNextSteps;
	}
	public List<Row> getRows() {
		return rows;
	}
	public void setRows(List<Row> rows) {
		this.rows = rows;
	}
	public WfRdSign getWfRdSign() {
		return wfRdSign;
	}
	public void setWfRdSign(WfRdSign wfRdSign) {
		this.wfRdSign = wfRdSign;
	}
	public WfRdTask getUnAcceptTask() {
		return unAcceptTask;
	}
	public void setUnAcceptTask(WfRdTask unAcceptTask) {
		this.unAcceptTask = unAcceptTask;
	}
	public List<WfDoc> getDocList() {
		return docList;
	}
	public void setDocList(List<WfDoc> docList) {
		this.docList = docList;
	}
	public List<WfCfgRelate> getRelateFlows() {
		return relateFlows;
	}
	public void setRelateFlows(List<WfCfgRelate> relateFlows) {
		this.relateFlows = relateFlows;
	}
	public List<File> getFiles() {
		return files;
	}
	public void setFiles(List<File> files) {
		this.files = files;
	}
	public WfDoc getWfDoc() {
		return wfDoc;
	}
	public void setWfDoc(WfDoc wfDoc) {
		this.wfDoc = wfDoc;
	}
	public int getTaskCount() {
		return taskCount;
	}
	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public List<WfQues> getQuesList() {
		return quesList;
	}
	
	public void setQuesList(List<WfQues> quesList) {
		this.quesList = quesList;
	}
	public List<Integer> getFileCates() {
		return fileCates;
	}
	public void setFileCates(List<Integer> fileCates) {
		this.fileCates = fileCates;
	}

	public Set<WfStep> getImgStepList() {
		return imgStepList;
	}

	public void setImgStepList(Set<WfStep> imgStepList) {
		this.imgStepList = imgStepList;
	}

	public List<WfScheCfgDoc> getDocCates() {
		return docCates;
	}

	public void setDocCates(List<WfScheCfgDoc> docCates) {
		this.docCates = docCates;
	}
	

}
