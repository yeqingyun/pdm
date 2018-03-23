package gnwf.ww.workflow;

import gnmail.facade.MailFacade;
import gnmail.vo.Mail;
import gnwf.dao.helper.WfDocHelper;
import gnwf.facade.WfCfgFacade;
import gnwf.facade.WfDocFacade;
import gnwf.facade.WfRdFacade;
import gnwf.facade.WfRdTaskFacade;
import gnwf.facade.WfStepFacade;
import gnwf.facade.WfStepUserFacade;
import gnwf.vo.WfCfg;
import gnwf.vo.WfDoc;
import gnwf.vo.WfRd;
import gnwf.vo.WfRdField;
import gnwf.vo.WfRdTask;
import gnwf.vo.WfStep;
import gnwf.vo.WfStepUser;
import gnwf.vo.json.WfDocJson;
import gnwf.ww.MSG;
import gnwf.ww.json.WfRdViewAction;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.frm.comn.GenericUtil;
import org.safehaus.uuid.UUIDGenerator;

import Utils.SendMailUtil;
import zrprjt.facade.TaskFacade;
import zrprjt.vo.Task;
import zrsy.vo.Usr;

public class WFUtil {

	public static WFCenter genWFCenter(WfRdViewAction action) throws Exception {
		WFCenter wfCenter = null;
		String className = "gnwf.ww.workflow.WFHandler";
		WfCfg cfg = action.getCfg();

		if (cfg != null && isNotNull(cfg.getSpecialClass())) {
			className = "gnwf.ww.workflow." + cfg.getSpecialClass();
		}

		Class<?> cls = Class.forName(className);
		Constructor<?> cons[] = cls.getConstructors();
		wfCenter = (WFCenter) cons[0].newInstance(action);
		return wfCenter;
	}

	//
//	public static WFCenter genWFCenter(WfRdXlsAction action) throws Exception {
//		WFCenter wfCenter = null;
//		return wfCenter;
//	}
	
	public static String UUID() {
		return UUIDGenerator.getInstance().generateRandomBasedUUID().toString();
	}

	public static boolean isNotNull(String str) {
		if (str != null && !"".equals(str.trim())) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotNull(Integer str) {
		if (str != null && str!=0) {
			return true;
		}
		return false;
	}

	public static boolean isNotNull(List<?> list) {
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotNull(Set<?> set) {
		if (set != null && set.size() > 0) {
			return true;
		}
		return false;
	}

	public static boolean isNull(String str) {
		return !isNotNull(str);
	}
	
	public static boolean isNull(Integer str) {
		return !isNotNull(str);
	}

	public static boolean isNull(List<?> list) {
		return !isNotNull(list);
	}

	public static String diffTime(Date end, Date begin) {	//取时间差
		if (begin == null) {
			return null;
		}
		end = end==null ? new Date() : end;
		
		long l = end.getTime() - begin.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

		StringBuffer buf = new StringBuffer();
		if (day > 0) {
			buf.append(day + "天");
		}
		if (hour > 0) {
			buf.append(hour + "小时");
		}
		if (min > 0) {
			buf.append(min + "分");
		}
		if (s > 0) {
			buf.append(s + "秒");
		}
		return buf.length() > 0 ? buf.toString() : null;
	}
	
	public static int diffHour(Date begin){					//取时间差小时数
		if (begin == null) {
			return 0;
		}
		long l = new Date().getTime() - begin.getTime();
		int hour = (int) (l / (60 * 60 * 1000));
		return hour;
	}
	
	public static Integer diffDate(Date begin,Date end){		//取时间差天数
		if (begin == null) {
			return null;
		}
		end = end==null ? new Date() : end;
		
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(begin);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(end);
		
		int count = cal2.get(Calendar.DAY_OF_YEAR) - cal1.get(Calendar.DAY_OF_YEAR);
		if(count>0){
			return count;
		}
		return null;
	}
	
	
	//组扩展字段row
	public static List<Row> genFieldRows(List<WfRdField> fieldContents) {		
		if(WFUtil.isNotNull(fieldContents)){
			List<WfRdField> copyList = new ArrayList<WfRdField>();
			for(int i=0;i<fieldContents.size();i++){
				WfRdField f = fieldContents.get(i);
				if(f!=null && f.getRowId()>0){	//过滤单值的扩展字段
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
	
	//邮件单个发送
	public static void sendMail(int userID,String sendAddr,String title,String content,String mailAddr) throws Exception{
		/*Mail mail = createMail(userID,sendAddr,title,content,mailAddr);
		new MailFacade().saveSingleMail(mail);*/
		
		String addressList = mailAddr;
		SendMailUtil.sendMailByAddress(title, content, addressList);
	}
	
	//邮件单个发送-信息部
	public static void sendMailByIT(String title,String content,String mailAddr) throws Exception{
		/*int itUser = MSG.ITDEPT_MAIL_ID;
		String sender = "xinxibu@gionee.com";
		sendMail(itUser,sender,title,content,mailAddr);*/
		
		String addressList = mailAddr;
		SendMailUtil.sendMailByAddress(title, content, addressList);
	}
	
	//邮件群发-信息部
	public static void sendMailByIT(String title,String content,List<Usr> userList) throws Exception{
		/*int itUser = MSG.ITDEPT_MAIL_ID;
		String sender = MSG.ITDEPT_MAIL;
		Mail mail = createMail(itUser,sender,title,content,null);
		new MailFacade().save(mail,userList);*/
		
		if(userList == null || userList.size() == 0) {
			return;
		}
		
		String userStrList = "";
		for(Usr usr : userList) {
			userStrList += usr.getId() + ",";
		}
		userStrList = userStrList.substring(0, userStrList.length()-1);
		SendMailUtil.sendMailByUsrId(title, content, userStrList);
	}
	
	
	public static void sendMailByUser(int userId,String sender,String title,String content,List<Usr> userList) throws Exception{
		/*Mail mail = createMail(userId,sender,title,content,null);
		new MailFacade().save(mail,userList);*/
		
		if(userList == null || userList.size() == 0) {
			return;
		}
		
		String userStrList = "";
		for(Usr usr : userList) {
			userStrList += usr.getId() + ",";
		}
		userStrList = userStrList.substring(0, userStrList.length()-1);
		SendMailUtil.sendMailByUsrId(title, content, userStrList);
	}
	
	
	
	//创建email
	public static Mail createMail(int userID,String sender,String title,String content,String mailAddr) {
		Mail mail = new Mail();
		mail.setSenderId(userID);
		mail.setSender(sender);
		mail.setAccept(mailAddr);
		mail.setMailDate(new Date());
		mail.setCreateBy(userID);
		mail.setCreateDate(new Date());
		mail.setLastUpd(userID);
		mail.setLastUpdDate(new Date());
		mail.setStatus(MSG.MAIL_STATUS_0);			//待发送
		mail.setTitle(title);
		mail.setMailText(content);
		return mail;
	}
	
//	//给项目组成员发邮件
//	public static void sendMail(int userID, String sender,String title,String content,List<Usr> userList) throws Exception{
//		Mail mail = createMail(userID, sender, title, content, null);
//		new MailFacade().save(mail,userList);
//	}
	
	/**
	 * 将工作流中的文档在部门文档管理中保存一条记录
	 * @param comId 公司ID   not null
	 * @param deptId 部门ID  not null
	 * @param docName 文档名称  not null
	 * @param uriLink 当前版本文档保存的路径  not null
	 * @param fileName 文件名称  not null
	 * @return docId  返回docId
	 * @throws Exception 
	 */
	public static Integer copyDocToDeptDoc(int comId,int deptId,String docName,String uriLink,String fileName) throws Exception {
		
		try {
//			Doc doc = new Doc();
//			int docId = 0;
//			String sql = "select max(DocId) as maxid from Doc "; 
//			docId = new DocRecordDAO().getMaxId(sql)+1;
//			doc.setDocId(docId);
//			doc.setComId(comId);
//			doc.setDeptId(deptId);
//			doc.setDocCode("WF_"+String.valueOf(docId));
//			if(docName==null){
//				throw new Exception("docName is null");
//			}else{
//				doc.setDocName(docName);
//			}
//			if(uriLink==null){
//				throw new Exception("uriLink is null");
//			}else{
//				doc.setUriLink(uriLink);
//			}
//			doc.setStatus(0);
//			doc.setDocVar(String.valueOf(1));
// 
//			//保存DocRecord
//			DocRecord docRecord = new DocRecord();
//			docRecord.setDocId(doc.getDocId());
//			docRecord.setUriLink(doc.getUriLink());
//			docRecord.setVersion(1);
//			if(fileName==null){
//				throw new Exception("fileName is null");
//			}else{
//				docRecord.setDocRecordName(fileName);
//			}
//			
//			//默认只允许文档所属部门查看
//			List<WfDept> wfDepts = new ArrayList<WfDept>();
//			WfDept wfd = new WfDept();
//			wfd.setDeptId(deptId);
//			wfDepts.add(wfd);
//			
//			new DocFacade().save(doc,docRecord,wfDepts);
//			return doc.getDocId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
//		eg: WFUtil.copyDocToDeptDoc(getSession().getComId(), getSession().getDeptId(), "测试ＷＦdoc01", "/upload/doc/20121207/root/0a4bf6a38f94426c9ec6f3265369c1f6/ExtJS学习心得.pdf", "ExtJS学习心得.pdf");
		
	}
	
	//Doc邮件群发-信息部
	public static void sendDocMailByIT(int OexId,String title,String content,List<Usr> userList) throws Exception{
		/*int itUser = MSG.ITDEPT_MAIL_ID;
		String sender = MSG.ITDEPT_MAIL;
		Mail mail = createDocMail(itUser,sender,title,content,null,OexId);
		new MailFacade().save(mail,userList);*/
		
		if(userList == null || userList.size() == 0) {
			return;
		}
		
		String userStrList = "";
		for(Usr usr : userList) {
			userStrList += usr.getId() + ",";
		}
		userStrList = userStrList.substring(0, userStrList.length()-1);
		SendMailUtil.sendMailByUsrId(title, content, userStrList);
	}
	
	//创建docemail
	public static Mail createDocMail(int userID,String sender,String title,String content,String mailAddr,int oexId) {
		Mail mail = createMail(userID,sender,title,content,mailAddr);
		mail.setRemark("doc");
		mail.setOexId(oexId);
		return mail;
	}
	
	/**
	 * 创建一条风险流程
	 * @param projectNo 研发项目编号		Not Null
	 * @param scheId 	研发阶段ID		Not Null
	 * @param flowName  风险流程名称		Not Null
	 * @param userId  	创建人ID			Not Null
	 * @param endDate   计划结束时间
	 * @return wfNo  	返回流程编号
	 * @throws Exception 
	 */
	public static String createQuesWfRd(String projectNo,int scheId,String flowName,int userId,Date endDate) throws Exception{
		int quesFlowId = 21;
		String wfNo = null;
		wfNo = createWfRd(projectNo,scheId,quesFlowId,flowName,userId,new Date(),endDate,null,userId);
		return wfNo;
	}
	
	/** TODO
	 * 创建项目阶段工作流
	 * @param projectNo 研发项目编号		Not Null
	 * @param scheId 	研发阶段ID		Not Null
	 * @param flowName  风险流程名称		Not Null
	 * @param startDate 计划开始时间
	 * @param endDate   计划结束时间
	 * @return wfNo  	返回流程编号
	 * @throws Exception 
	 */
	public static String createScheWfRd(String projectNo,int scheId,int flowId,String flowName,Date startDate,Date endDate,int usrId) throws Exception{
		String sql = "select StepId,PrjtUsr.UsrId as UserId from WfStepUser " +
				" left join PrjtRole on(WfStepUser.PrjtRoleId = PrjtRole.RoleId) " +
				" left join PrjtUsr on(PrjtUsr.RoleId = PrjtRole.RoleId) " +
				" where stepId=(select stepId from wfstep where flowId="+flowId+" and sort=1)" +
				" and PrjtUsr.prjtno='"+projectNo+"'";
		WfStepUser sUser = new WfStepUserFacade().findById(sql, "StepId,PrjtUsr.UsrId as UserId");
		
		if(sUser==null || isNull(sUser.getUserId())){
			return null;
		}
		
		return createWfRd(projectNo,scheId,flowId,flowName,sUser.getUserId(),startDate,endDate,null,usrId);
	}
	
	
	// 创建工作流记录及第一个任务
	public static String createWfRd(String projectNo,int scheId,int flowId,String flowName,int userId,Date startDate,Date endDate) throws Exception{
		return createWfRd(projectNo,scheId,flowId,flowName,userId,startDate,endDate,null,userId);
	}
	public static String createWfRd(String projectNo,int scheId,int flowId,String flowName,int userId,Date start,Date end,String MailMsg,int createBy) throws Exception{
		
		WfCfg wc = new WfCfg();
		wc.setFlowId(flowId);
		wc = new WfCfgFacade().findById(wc);
		
		//生成流程
		WfRd wfRd = new WfRd();
		wfRd.setFlowId(flowId);
		wfRd.setNeedQues(wc.getNeedQues());
		wfRd.setWfName(flowName);
		wfRd.setProjectNo(projectNo);
		wfRd.setScheId(scheId);
		wfRd.setPlanSDate(start);
		wfRd.setPlanEDate(end);
		wfRd.setCreateBy(createBy);
		wfRd.setCreateDate(new Date());
		wfRd.setStatus(MSG.OWFRD_STATUS_0);
		new WfRdFacade().save(wfRd);
		
		//生成第一任务
		WfRdTask task = new WfRdTask();
		task.setCreateBy(-1);
		task.setCreateDate(new Date());
		task.setReqDate(new Date());
		task.setAcceptBy(userId);
		task.setAcceptDate(new Date());
		task.setStatus(MSG.OWFTASK_STATUS_1);
		task.setTaskType(MSG.OWFTASK_TYPE_1);
		task.setWfNo(wfRd.getWfNo());
		
		WfStep step = new WfStep();
		step.setFlowId(wfRd.getFlowId());
		step.setSort(1);
		step = new WfStepFacade().findBy(step);
		task.setStepId(step.getStepId());
		new WfRdTaskFacade().save(task);
		
		//邮件
		if(isNotNull(MailMsg)){
		}
		
		return wfRd.getWfNo();
	}
	
	/**
	 * 根据研发项目或项目阶段查询工作流
	 * @param projectNo 研发项目编号		Not Null
	 * @param scheId 	研发阶段ID
	 * @return wfRds  	返回流程List
	 * @throws Exception 
	 */
	public static List<WfRd> selectWfRd(String projectNo,int scheId) throws Exception{
		List<WfRd> wfRds = null;
		if(isNotNull(projectNo)){
			WfRd wfRd = new WfRd();
			wfRd.setProjectNo(projectNo);
			wfRd.setScheId(scheId);
			wfRds = new WfRdFacade().find(wfRd);
		}
		return wfRds;
	}
	
	//TODO 项目查找工作流附件
	public static List<WfDoc> selectWfDoc(String projectNo,Integer scheId) throws Exception{
		List<WfDoc> docs = null;
		if(isNull(projectNo)){
			return null;
		}
		
		String str = "";
		if(isNotNull(scheId)){
			str = " and scheid="+scheId;
		}
		
		String sql = "select "+WfDoc.LIST_FIELDS+" " +new WfDocHelper().getSqlString()+
				" and wfrdtask.wfno in(select distinct wfrd.wfno from wfrd where wfrd.projectNo='"+projectNo+"' "+str+") or WfDoc.ProjectNo = '"+projectNo+"' ";
		docs = new WfDocFacade().find(sql,WfDoc.LIST_FIELDS);
		
		return docs;
	}
	
	
	
	/**
	 * 修改项目任务
	 * @param  PrjtNo  项目编号
	 * @param schId    Not Null
	 * @param tasker 	任务办理
	 * @param perce 	项目任务进度
	 * @param oveDate 	项目任务结束日期
	 * @param status 	项目任务状态
	 * @return void  	
	 * @throws Exception 
	 */
	public static void updateProjtTask(String prjtNo,int schId,Integer tasker,Integer perce,Date oveDate,int status) throws Exception{
		
		if(prjtNo==null){
			return;
		}
		
		String sql = "update Task "+" set Task.Status = "+status;
		
		if(perce!=null){
			sql +=","+"Task.Perce = "+perce;
		}
		
		if(tasker!=null){
			sql +=","+"Task.Tasker = "+tasker;
		}
		
		if(oveDate!=null){
			sql +=","+"Task.OveDate = '"+GenericUtil.dateFormat(oveDate, "yyyy-MM-dd HH:mm")+"'";
		}
		
		
		sql+=" where Task.PrjtNo = '" +prjtNo.trim() + "' and Task.SchId = "+schId;
		new TaskFacade().update(sql);
	}
	
}
