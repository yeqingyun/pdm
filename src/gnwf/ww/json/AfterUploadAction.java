package gnwf.ww.json;

import gnwf.facade.QuesRespFacade;
import gnwf.facade.WfCfgFacade;
import gnwf.facade.WfDocFacade;
import gnwf.facade.WfQuesFacade;
import gnwf.facade.WfRdFacade;
import gnwf.facade.WfRdTaskFacade;
import gnwf.facade.WfRiskFacade;
import gnwf.facade.WfStepFacade;
import gnwf.facade.WfStepUserFacade;
import gnwf.vo.QuesResp;
import gnwf.vo.WfCfg;
import gnwf.vo.WfDoc;
import gnwf.vo.WfRd;
import gnwf.vo.WfRdTask;
import gnwf.vo.WfStep;
import gnwf.vo.WfStepUser;
import gnwf.ww.BasicAction;
import gnwf.ww.MSG;
import gnwf.ww.workflow.WFUtil;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.tools.ant.util.DateUtils;

import zrprjt.facade.PrjtDefFacade;
import zrprjt.facade.PrjtUsrFacade;
import zrprjt.vo.PrjtDef;
import zrprjt.vo.PrjtUsr;
import zrsy.vo.Usr;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;

public class AfterUploadAction extends BasicAction {
	
	private static final long serialVersionUID = 1L;
	private String contentType;
	private String fileName;
	private String fileNo;
	private String usrId;
	private String tempParams;
	
	
	
	
	public String wfAfterUpload() throws Exception {
		try {
			tempParams = URLDecoder.decode(tempParams, "UTF-8");
			fileName = URLDecoder.decode(fileName, "UTF-8");
			System.out.println(tempParams);
			String prjtNo = null;
			String wfNo = null;
			String taskId = null;
			String cateId = null;
			Integer docType = null;
			Integer syId=null;
			String flowid=null;

			String s[] = tempParams.split(",");
			for (int i = 0; i < s.length; i++) {
				String ss[] = s[i].split(":");
				if ("wfNo".equalsIgnoreCase(ss[0]) || "wfDoc.wfNo".equalsIgnoreCase(ss[0])) {
					wfNo = ss[1];
				} else if ("cateId".equalsIgnoreCase(ss[0]) || "wfDoc.cateId".equalsIgnoreCase(ss[0])) {
					cateId = ss[1];
				} else if ("taskId".equalsIgnoreCase(ss[0]) || "wfDoc.taskId".equalsIgnoreCase(ss[0])) {
					taskId = ss[1];
				} else if ("prjtNo".equalsIgnoreCase(ss[0]) || "wfDoc.projectNo".equalsIgnoreCase(ss[0])) {
					prjtNo = ss[1];
				} else if ("flowid".equalsIgnoreCase(ss[0])) {
					flowid = ss[1];
				} else if ("syId".equalsIgnoreCase(ss[0]) || "wfDoc.syId".equalsIgnoreCase(ss[0])) {
					syId = Integer.parseInt(ss[1]);
				} else if ("uploadType".equalsIgnoreCase(ss[0])) {
					String uploadType = ss[1];
					if (uploadType.trim().equals("ProcFile")) {
						docType = MSG.WFDOC_DOCTYPE_PROC;
					} else if (uploadType.trim().equals("BaseLib")) {
						docType = MSG.WFDOC_DOCTYPE_BASELIB;
					}else if (uploadType.trim().equals("DefinDos")) {
						docType = MSG.WFDOC_DOCTYPE_DEFINDOS;
					}
				}
			}
			
			System.out.println("--------" + fileNo + "-----" + usrId + "----" + fileName);
			System.out.println("--" + wfNo + "----" + cateId + "--" + taskId + "---" + prjtNo);


//			WfRd oleWfRd = new WfRd();
//			oleWfRd.setWfNo(wfNo);
//			oleWfRd = new WfRdFacade().findById(oleWfRd);

			if (flowid != null && "42".equals(flowid.trim())) {//如果是文档更新流程
				// 启动一个新流程
				// 42 归档文档更新流程

				// 创建流程和第一步骤
				Integer newFlowid = 42;
				WfCfg wc = new WfCfg();
				wc.setFlowId(newFlowid);
				wc = new WfCfgFacade().findById(wc);

				WfCfg wfCfg = new WfCfg();
				wfCfg.setFlowId(newFlowid);
				wfCfg = new WfCfgFacade().findById(wfCfg);

				// 生成流程
				WfRd wfRd = new WfRd();
				wfRd.setFlowId(newFlowid);
				wfRd.setNeedQues(wc.getNeedQues());
				wfRd.setWfName("文档更新");
				wfRd.setProjectNo(prjtNo);
//				wfRd.setScheId(scheId);
				wfRd.setDocCateId(cateId);
				wfRd.setPlanSDate(new Date());
				wfRd.setPlanEDate(new Date());
				wfRd.setCreateBy(syId);
				wfRd.setCreateDate(new Date());
				wfRd.setStatus(MSG.WFDOC_STATUS_DELETED_4);
				String newWfNo = new WfRdFacade().save(wfRd);
				wfRd = new WfRd();
				wfRd.setWfNo(newWfNo);
				wfRd = new WfRdFacade().findById(wfRd);

				// 得到第一个步骤
				WfStep step = new WfStep();
				step.setFlowId(newFlowid);
				step.setSort(1);
				step = new WfStepFacade().findBy(step);

				// 自动下一步
				List<WfRdTask> wfRdTasks = autoSendTask(step.getStepId(), null, wfCfg, wfRd, syId);
				if (!wfRdTasks.isEmpty()) {
					// 更改文档信息
					WfDoc doc = new WfDoc();
					doc.setFileNo(fileNo);
					doc.setDocName(fileName);
					doc.setWfNo(wfRd.getWfNo());
					doc.setProjectNo(prjtNo);
					doc.setDocType(docType);
					if (doc.getDocType() == MSG.WFDOC_DOCTYPE_PROC) {
						doc.setStatus(MSG.WFDOC_STATUS_DELETED_1);
					} else if (doc.getDocType() == MSG.WFDOC_DOCTYPE_BASELIB) {
						doc.setStatus(MSG.WFDOC_STATUS_DELETED_4);
					} else {
						setMsg("doc type is null");
						return ERROR;
					}
					
					doc.setCreateBy(Integer.parseInt(usrId));
					doc.setCreateDate(new Date());

					doc.setTaskId(wfRdTasks.get(0).getTaskId());
					if (WFUtil.isNotNull(cateId)) {
						doc.setCateId(Integer.parseInt(cateId));
					}

					new WfDocFacade().saveForVer(doc);
				}
			}else{
				
				
				WfDoc doc = new WfDoc();
				doc.setFileNo(fileNo);
				doc.setDocName(fileName);
				doc.setWfNo(wfNo);
				doc.setProjectNo(prjtNo);
				doc.setDocType(docType);
				if (doc.getDocType() == MSG.WFDOC_DOCTYPE_PROC) {
					doc.setStatus(MSG.WFDOC_STATUS_DELETED_1);
				} else if (doc.getDocType() == MSG.WFDOC_DOCTYPE_BASELIB) {
					doc.setStatus(MSG.WFDOC_STATUS_DELETED_2);
				}  else if (doc.getDocType() == MSG.WFDOC_DOCTYPE_DEFINDOS) {
					doc.setStatus(MSG.WFDOC_STATUS_DELETED_1);
				}else {
					setMsg("doc type is null");
					return ERROR;
				}
				doc.setCreateBy(Integer.parseInt(usrId));
				doc.setCreateDate(new Date());

				if (WFUtil.isNotNull(taskId)) {
					doc.setTaskId(Integer.parseInt(taskId));
				}
				if (WFUtil.isNotNull(cateId)) {
					doc.setCateId(Integer.parseInt(cateId));
				}

				new WfDocFacade().saveForVer(doc);
				
			}
			
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfDocAction addto Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return null;
	}
	
	
	//产品定义书附件上传
	public String wfSurveyAfterUpload() throws Exception {
		try {
			tempParams = URLDecoder.decode(tempParams, "UTF-8");
			fileName = URLDecoder.decode(fileName, "UTF-8");
			System.out.println(tempParams);
			String prjtNo = null;
			String wfNo = null;
			String taskId = null;
			String cateId = null;
			Integer docType = null;
			Integer syId=null;
			String flowid=null;

			String s[] = tempParams.split(",");
			for (int i = 0; i < s.length; i++) {
				String ss[] = s[i].split(":");
				if ("wfNo".equalsIgnoreCase(ss[0]) || "wfDoc.wfNo".equalsIgnoreCase(ss[0])) {
					wfNo = ss[1];
				} else if ("cateId".equalsIgnoreCase(ss[0]) || "wfDoc.cateId".equalsIgnoreCase(ss[0])) {
					cateId = ss[1];
				} else if ("taskId".equalsIgnoreCase(ss[0]) || "wfDoc.taskId".equalsIgnoreCase(ss[0])) {
					taskId = ss[1];
				} else if ("prjtNo".equalsIgnoreCase(ss[0]) || "wfDoc.projectNo".equalsIgnoreCase(ss[0])) {
					prjtNo = ss[1];
				} else if ("flowid".equalsIgnoreCase(ss[0])) {
					flowid = ss[1];
				} else if ("syId".equalsIgnoreCase(ss[0]) || "wfDoc.syId".equalsIgnoreCase(ss[0])) {
					syId = Integer.parseInt(ss[1]);
				} else if ("uploadType".equalsIgnoreCase(ss[0])) {
					String uploadType = ss[1];
					if (uploadType.trim().equals("ProcFile")) {
						docType = MSG.WFDOC_DOCTYPE_PROC;
					} else if (uploadType.trim().equals("BaseLib")) {
						docType = MSG.WFDOC_DOCTYPE_BASELIB;
					}else if (uploadType.trim().equals("DefinDos")) {
						docType = MSG.WFDOC_DOCTYPE_DEFINDOS;
					}
				}
			}
			
			System.out.println("--------" + fileNo + "-----" + usrId + "----" + fileName);
			System.out.println("--" + wfNo + "----" + cateId + "--" + taskId + "---" + prjtNo);


//			WfRd oleWfRd = new WfRd();
//			oleWfRd.setWfNo(wfNo);
//			oleWfRd = new WfRdFacade().findById(oleWfRd);
			PrjtDef prjtDef = new PrjtDef();
			prjtDef.setPrjtNo(prjtNo);
			String prjtNoName = (new PrjtDefFacade().findById(prjtDef)).getPrjtNm();
			System.out.println("prjtNoName```````````"+prjtNoName);

			if (flowid != null && "42".equals(flowid.trim())) {//如果是文档更新流程
				// 启动一个新流程
				// 42 归档文档更新流程

				// 创建流程和第一步骤
				Integer newFlowid = 42;
				WfCfg wc = new WfCfg();
				wc.setFlowId(newFlowid);
				wc = new WfCfgFacade().findById(wc);

				WfCfg wfCfg = new WfCfg();
				wfCfg.setFlowId(newFlowid);
				wfCfg = new WfCfgFacade().findById(wfCfg);

				// 生成流程
				WfRd wfRd = new WfRd();
				wfRd.setFlowId(newFlowid);
				wfRd.setNeedQues(wc.getNeedQues());
				wfRd.setWfName("文档更新");
				wfRd.setProjectNo(prjtNo);
//				wfRd.setScheId(scheId);
				wfRd.setDocCateId(cateId);
				wfRd.setPlanSDate(new Date());
				wfRd.setPlanEDate(new Date());
				wfRd.setCreateBy(syId);
				wfRd.setCreateDate(new Date());
				wfRd.setStatus(MSG.WFDOC_STATUS_DELETED_4);
				String newWfNo = new WfRdFacade().save(wfRd);
				wfRd = new WfRd();
				wfRd.setWfNo(newWfNo);
				wfRd = new WfRdFacade().findById(wfRd);

				// 得到第一个步骤
				WfStep step = new WfStep();
				step.setFlowId(newFlowid);
				step.setSort(1);
				step = new WfStepFacade().findBy(step);

				// 自动下一步
				List<WfRdTask> wfRdTasks = autoSendTask(step.getStepId(), null, wfCfg, wfRd, syId);
				if (!wfRdTasks.isEmpty()) {
					// 更改文档信息
					WfDoc doc = new WfDoc();
					doc.setFileNo(fileNo);
					doc.setDocName(fileName);
					doc.setWfNo(wfRd.getWfNo());
					doc.setProjectNo(prjtNo);
					doc.setDocType(docType);
					if (doc.getDocType() == MSG.WFDOC_DOCTYPE_PROC) {
						doc.setStatus(MSG.WFDOC_STATUS_DELETED_1);
					} else if (doc.getDocType() == MSG.WFDOC_DOCTYPE_BASELIB) {
						doc.setStatus(MSG.WFDOC_STATUS_DELETED_4);
					} else {
						setMsg("doc type is null");
						return ERROR;
					}
					
					doc.setCreateBy(Integer.parseInt(usrId));
					doc.setCreateDate(new Date());

					doc.setTaskId(wfRdTasks.get(0).getTaskId());
					if (WFUtil.isNotNull(cateId)) {
						doc.setCateId(Integer.parseInt(cateId));
					}

					new WfDocFacade().saveForSurveyVer(doc);
				}
			}else{
				
				
				WfDoc doc = new WfDoc();
				doc.setFileNo(fileNo);
				doc.setDocName(fileName);
				doc.setWfNo(wfNo);
				doc.setProjectNo(prjtNo);
				doc.setDocType(docType);
				if (doc.getDocType() == MSG.WFDOC_DOCTYPE_PROC) {
					doc.setStatus(MSG.WFDOC_STATUS_DELETED_1);
				} else if (doc.getDocType() == MSG.WFDOC_DOCTYPE_BASELIB) {
					doc.setStatus(MSG.WFDOC_STATUS_DELETED_2);
				}  else if (doc.getDocType() == MSG.WFDOC_DOCTYPE_DEFINDOS) {
					doc.setStatus(MSG.WFDOC_STATUS_DELETED_1);
				}else {
					setMsg("doc type is null");
					return ERROR;
				}
				doc.setCreateBy(Integer.parseInt(usrId));
				doc.setCreateDate(new Date());

				if (WFUtil.isNotNull(taskId)) {
					doc.setTaskId(Integer.parseInt(taskId));
				}
				if (WFUtil.isNotNull(cateId)) {
					doc.setCateId(Integer.parseInt(cateId));
				}

				new WfDocFacade().saveForSurveyVer(doc);
				//发送邮件给项目组所有成员
				ActionContext ctx = ActionContext.getContext();
				HttpServletRequest request = (HttpServletRequest) ctx
						.get(ServletActionContext.HTTP_REQUEST);
				String contextPath = request.getContextPath();
				String url = request.getRequestURL().toString();
				int index = url.indexOf(contextPath);
				String res_url = url.substring(0, index) + contextPath;
				StringBuffer title = new StringBuffer();
				title.append("项目《"+prjtNoName+"》的产品定义书已经更新！");
				StringBuffer content = new StringBuffer();
				content.append("尊敬的同事，您好:<p>您参与的项目《"+prjtNoName+"》")
				.append("，产品定义书已经更新！</p>链接地址：<a href=")
				.append(res_url)
				.append("/PrjtDef!view.shtml")
				.append(">")
				.append(res_url)
				.append("/PrjtDef!view.shtml")
				/*.append(this.wfQues.getQuesId())*/
				.append("</a>");
				List<Usr> usrlist = new ArrayList<Usr>();
				List<PrjtUsr> prjtUsrList = new PrjtUsrFacade().find("select PrjtUsr.UsrId from PrjtUsr join PrjtRole on PrjtUsr.RoleId = PrjtRole.RoleId"
                        + " where  PrjtUsr.PrjtNo = '"
                        + prjtNo + "'","PrjtUsr.UsrId");
				for(PrjtUsr pu : prjtUsrList) {
					Usr u = new Usr();
					u.setId(pu.getUsrId());
					usrlist.add(u);
				}
				
				WFUtil.sendMailByIT(title.toString(), content.toString(), usrlist);
			}
			
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfDocAction addto Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return null;
	}
	
	
	//项目进度表附件上传
		public String wfPrjtPointAfterUpload() throws Exception {
			try {
				tempParams = URLDecoder.decode(tempParams, "UTF-8");
				fileName = URLDecoder.decode(fileName, "UTF-8");
				System.out.println(tempParams);
				String prjtNo = null;
				String wfNo = null;
				String taskId = null;
				String cateId = null;
				Integer docType = null;
				Integer syId=null;
				String flowid=null;

				String s[] = tempParams.split(",");
				for (int i = 0; i < s.length; i++) {
					String ss[] = s[i].split(":");
					if ("wfNo".equalsIgnoreCase(ss[0]) || "wfDoc.wfNo".equalsIgnoreCase(ss[0])) {
						wfNo = ss[1];
					} else if ("cateId".equalsIgnoreCase(ss[0]) || "wfDoc.cateId".equalsIgnoreCase(ss[0])) {
						cateId = ss[1];
					} else if ("taskId".equalsIgnoreCase(ss[0]) || "wfDoc.taskId".equalsIgnoreCase(ss[0])) {
						taskId = ss[1];
					} else if ("prjtNo".equalsIgnoreCase(ss[0]) || "wfDoc.projectNo".equalsIgnoreCase(ss[0])) {
						prjtNo = ss[1];
					} else if ("flowid".equalsIgnoreCase(ss[0])) {
						flowid = ss[1];
					} else if ("syId".equalsIgnoreCase(ss[0]) || "wfDoc.syId".equalsIgnoreCase(ss[0])) {
						syId = Integer.parseInt(ss[1]);
					} else if ("uploadType".equalsIgnoreCase(ss[0])) {
						String uploadType = ss[1];
						if (uploadType.trim().equals("ProcFile")) {
							docType = MSG.WFDOC_DOCTYPE_PROC;
						} else if (uploadType.trim().equals("BaseLib")) {
							docType = MSG.WFDOC_DOCTYPE_BASELIB;
						}else if (uploadType.trim().equals("DefinDos")) {
							docType = MSG.WFDOC_DOCTYPE_DEFINDOS;
						}else if (uploadType.trim().equals("PrjtPointDos")) {
							docType = MSG.WFDOC_DOCTYPE_PRJTPOINT;
						}
					}
				}
				
				System.out.println("--------" + fileNo + "-----" + usrId + "----" + fileName);
				System.out.println("--" + wfNo + "----" + cateId + "--" + taskId + "---" + prjtNo);
				PrjtDef prjtDef = new PrjtDef();
				prjtDef.setPrjtNo(prjtNo);
				String prjtNoName = (new PrjtDefFacade().findById(prjtDef)).getPrjtNm();
				System.out.println("prjtNoName```````````"+prjtNoName);

				if (flowid != null && "42".equals(flowid.trim())) {//如果是文档更新流程
					// 启动一个新流程
					// 42 归档文档更新流程

					// 创建流程和第一步骤
					Integer newFlowid = 42;
					WfCfg wc = new WfCfg();
					wc.setFlowId(newFlowid);
					wc = new WfCfgFacade().findById(wc);

					WfCfg wfCfg = new WfCfg();
					wfCfg.setFlowId(newFlowid);
					wfCfg = new WfCfgFacade().findById(wfCfg);

					// 生成流程
					WfRd wfRd = new WfRd();
					wfRd.setFlowId(newFlowid);
					wfRd.setNeedQues(wc.getNeedQues());
					wfRd.setWfName("文档更新");
					wfRd.setProjectNo(prjtNo);
//					wfRd.setScheId(scheId);
					wfRd.setDocCateId(cateId);
					wfRd.setPlanSDate(new Date());
					wfRd.setPlanEDate(new Date());
					wfRd.setCreateBy(syId);
					wfRd.setCreateDate(new Date());
					wfRd.setStatus(MSG.WFDOC_STATUS_DELETED_4);
					String newWfNo = new WfRdFacade().save(wfRd);
					wfRd = new WfRd();
					wfRd.setWfNo(newWfNo);
					wfRd = new WfRdFacade().findById(wfRd);

					// 得到第一个步骤
					WfStep step = new WfStep();
					step.setFlowId(newFlowid);
					step.setSort(1);
					step = new WfStepFacade().findBy(step);

					// 自动下一步
					List<WfRdTask> wfRdTasks = autoSendTask(step.getStepId(), null, wfCfg, wfRd, syId);
					if (!wfRdTasks.isEmpty()) {
						// 更改文档信息
						WfDoc doc = new WfDoc();
						doc.setFileNo(fileNo);
						doc.setDocName(fileName);
						doc.setWfNo(wfRd.getWfNo());
						doc.setProjectNo(prjtNo);
						doc.setDocType(docType);
						if (doc.getDocType() == MSG.WFDOC_DOCTYPE_PROC) {
							doc.setStatus(MSG.WFDOC_STATUS_DELETED_1);
						} else if (doc.getDocType() == MSG.WFDOC_DOCTYPE_BASELIB) {
							doc.setStatus(MSG.WFDOC_STATUS_DELETED_4);
						} else {
							setMsg("doc type is null");
							return ERROR;
						}
						
						doc.setCreateBy(Integer.parseInt(usrId));
						doc.setCreateDate(new Date());

						doc.setTaskId(wfRdTasks.get(0).getTaskId());
						if (WFUtil.isNotNull(cateId)) {
							doc.setCateId(Integer.parseInt(cateId));
						}

						new WfDocFacade().saveForSurveyVer(doc);
					}
				}else{
					
					
					WfDoc doc = new WfDoc();
					doc.setFileNo(fileNo);
					doc.setDocName(fileName);
					doc.setWfNo(wfNo);
					doc.setProjectNo(prjtNo);
					doc.setDocType(docType);
					if (doc.getDocType() == MSG.WFDOC_DOCTYPE_PROC) {
						doc.setStatus(MSG.WFDOC_STATUS_DELETED_1);
					} else if (doc.getDocType() == MSG.WFDOC_DOCTYPE_BASELIB) {
						doc.setStatus(MSG.WFDOC_STATUS_DELETED_2);
					}  else if (doc.getDocType() == MSG.WFDOC_DOCTYPE_DEFINDOS) {
						doc.setStatus(MSG.WFDOC_STATUS_DELETED_1);
					}  else if (doc.getDocType() == MSG.WFDOC_DOCTYPE_PRJTPOINT) {
							doc.setStatus(MSG.WFDOC_STATUS_DELETED_1);
					}else {
						setMsg("doc type is null");
						return ERROR;
					}
					doc.setCreateBy(Integer.parseInt(usrId));
					doc.setCreateDate(new Date());

					if (WFUtil.isNotNull(taskId)) {
						doc.setTaskId(Integer.parseInt(taskId));
					}
					if (WFUtil.isNotNull(cateId)) {
						doc.setCateId(Integer.parseInt(cateId));
					}

					new WfDocFacade().saveForPrjtPointVer(doc);
					/*//发送邮件给项目组所有成员
					ActionContext ctx = ActionContext.getContext();
					HttpServletRequest request = (HttpServletRequest) ctx
							.get(ServletActionContext.HTTP_REQUEST);
					String contextPath = request.getContextPath();
					String url = request.getRequestURL().toString();
					int index = url.indexOf(contextPath);
					String res_url = url.substring(0, index) + contextPath;
					StringBuffer title = new StringBuffer();
					title.append("项目《"+prjtNoName+"》的产品定义书已经更新！");
					StringBuffer content = new StringBuffer();
					content.append("尊敬的同事，您好:<p>您参与的项目《"+prjtNoName+"》")
					.append("，产品定义书已经更新！</p>链接地址：<a href=")
					.append(res_url)
					.append("/PrjtDef!view.shtml")
					.append(">")
					.append(res_url)
					.append("/PrjtDef!view.shtml")
					.append(this.wfQues.getQuesId())
					.append("</a>");
					List<Usr> usrlist = new ArrayList<Usr>();
					List<PrjtUsr> prjtUsrList = new PrjtUsrFacade().find("select PrjtUsr.UsrId from PrjtUsr join PrjtRole on PrjtUsr.RoleId = PrjtRole.RoleId"
	                        + " where  PrjtUsr.PrjtNo = '"
	                        + prjtNo + "'","PrjtUsr.UsrId");
					for(PrjtUsr pu : prjtUsrList) {
						Usr u = new Usr();
						u.setId(pu.getUsrId());
						usrlist.add(u);
					}
					
					WFUtil.sendMailByIT(title.toString(), content.toString(), usrlist);*/
				}
				
			} catch (Exception e) {
				setMsg(MSG.F_SEA);
				Logger.getLogger(this.getClass()).error("WfDocAction addto Exception", e);
				e.printStackTrace();
				return ERROR;
			}
			return null;
		}
	
	
	//新增问题  批量导入问题时 上传附件回调的方法
	public void quesAfterUploadFile() {
		try {
			String quesId = null;
			String impQuesIds = null ;
			setFileName(URLDecoder.decode(getFileName(), "UTF-8"));
			setTempParams(URLDecoder.decode(getTempParams(), "UTF-8"));
			if (getFileNo() != null) {
				String sql = null;
				if (getTempParams() != null) {
					String ss[] = getTempParams().split(":");
					if (ss[0].equals("wfQues.quesId")) {
						quesId = ss[1];
						sql = "update WfQues set FileName='" + getFileName() + "', FileNo = '" + getFileNo() + "', LastUpd=" + usrId + ", LastUpdDate ='" + DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss") + "' where QuesId='" + quesId+"'";
					}

					if (ss[0].equals("impQuesIds")) {
						impQuesIds = ss[1];
						sql = "update WfQues set FileName='" + getFileName() + "', FileNo = '" + getFileNo() + "', LastUpd=" + usrId + ", LastUpdDate ='" + DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss") + "' where QuesId in (" + impQuesIds + ")";
					}
				}
				if (quesId != null || impQuesIds != null) {
					new WfQuesFacade().update(sql);
				}
			} else {
				setMsg("上传附件失败");
			}
		} catch (Exception e) {
			setMsg("上传附件失败");
			Logger.getLogger(this.getClass()).error("WfQuesAddAction uploadResultFile Exception", e);
			e.printStackTrace();
		}
	}
	
	
	public void quesRespAfterUploadFile(){
		try{
			String id =null;
			String upFielType  =null;
			setFileName(URLDecoder.decode(getFileName(), "UTF-8"));
			setTempParams(URLDecoder.decode(getTempParams(), "UTF-8"));
			if(getFileNo()!=null){
				String sql =null;
				if (getTempParams() != null){
					String s[] = getTempParams().split(",");
					for(int i = 0;i<s.length;i++){
						 String ss[]=s[i].split(":");
						 if(ss[0].equals("quesResp.id")){
							 id = ss[1];
						 }
						 if(ss[0].equals("upFielType")){
							 upFielType = ss[1];
						 }
					}
					QuesResp qResp = new QuesResp();
					int qrid =Integer.parseInt(id);
					qResp.setId(qrid);
					QuesResp qr =new QuesRespFacade().findBy(qResp);
					System.out.println(qr.getResultFileNo());
					String  uplodeFileNo = qr.getResultFileNo()+ "||" +getFileNo();
					String  uplodeFileName =qr.getResultFileName()+"||"+getFileName();
					
					if(upFielType.equals("resultFile")){
						sql = "update QuesResp set ResultFileName ='"+getFileName()+"', ResultFileNo = '"+getFileNo()+"', LastUpd="+usrId+", LastUpdDate ='"
								+DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss")+"' where Id="+id;
					}else if(upFielType.equals("idtfFile")){
						sql = "update QuesResp set IdtfResFileName ='"+getFileName()+"', IdtfResFileNo = '"+getFileNo()+"', LastUpd="+usrId+", LastUpdDate ='"
								+DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss")+"' where Id="+id;
					}else if(upFielType.equals("remarkFile")){
						sql = "update QuesResp set RemarkFileName ='"+getFileName()+"', RemarkFileNo = '"+getFileNo()+"', LastUpd="+usrId+", LastUpdDate ='"
								+DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss")+"' where Id="+id;
					}
					
					/*if(upFielType.equals("resultFile")){
						sql = "update QuesResp set ResultFileName ='"+uplodeFileName+"', ResultFileNo = '"+uplodeFileNo+"', LastUpd="+usrId+", LastUpdDate ='"
								+DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss")+"' where Id="+id;
					}else if(upFielType.equals("idtfFile")){
						sql = "update QuesResp set IdtfResFileName ='"+uplodeFileName+"', IdtfResFileNo = '"+uplodeFileNo+"', LastUpd="+usrId+", LastUpdDate ='"
								+DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss")+"' where Id="+id;
					}else if(upFielType.equals("remarkFile")){
						sql = "update QuesResp set RemarkFileName ='"+uplodeFileName+"', RemarkFileNo = '"+uplodeFileNo+"', LastUpd="+usrId+", LastUpdDate ='"
								+DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss")+"' where Id="+id;
					}*/
				}
				if(id!=null){
					new QuesRespFacade().update(sql);
				}
			}else {
				setMsg("上传附件失败");
			}
		} catch (Exception e) {
			setMsg("上传附件失败");
			Logger.getLogger(this.getClass()).error(
					"QuesRespAction uploadResultFile Exception", e);
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	    //TODO 自动流转部分
		//只有一步,查当前步骤
		protected List<WfRdTask> autoSendTask(int currentStepId,String taskId,WfCfg wfCfg,WfRd wfRd,Integer syId)throws Exception {
			List<WfStepUser> autos = null;
			
			if(WFUtil.isNull(autos)){		//先查询项目角色对应者
				String prjt = wfRd.getProjectNo()==null?"":" or prjtNo='"+wfRd.getProjectNo()+"'";
				String autoSql = "select StepId,A.UsrId as UserId,UserType,PrjtRoleId from WfStepUser " +
						" left join " +
						" (select PrjtRole.RoleId,PrjtRole.roleNm,usrId from PrjtRole,PrjtUsr " +
						" where PrjtUsr.RoleId = PrjtRole.RoleId and PrjtUsr.status!=0 and (roletyp=0 "+prjt+"))A " +
						" on (WfStepUser.PrjtRoleId = A.RoleId)" +
						" where stepId ="+currentStepId +" and (UserId>0 or WfStepUser.PrjtRoleId>0)";
				autos = new WfStepUserFacade().find(autoSql,"StepId,PrjtUsr.UsrId as UserId,UserType,WfStepUser.PrjtRoleId");
			}
			
			if(WFUtil.isNull(autos)){		//如果为空，则查询自动流转用户
				String autoSql = "select StepId,UserId,UserType,PrjtRoleId from WfStepUser where stepId =" +currentStepId+" and (UserId>0 or WfStepUser.PrjtRoleId>0)";
				autos = new WfStepUserFacade().find(autoSql,"StepId,UserId,UserType,WfStepUser.PrjtRoleId");
			}
			
			//将角色换成具体的人
			for(int i=0;i<autos.size();i++){
				WfStepUser user = autos.get(i);
				if(user!=null&&(user.getUserId()==null||user.getUserId().intValue()<1)&&user.getPrjtRoleId()!=null&&user.getPrjtRoleId().intValue()>0){
					autos.remove(i);
					i--;

					String roleSql = "select UsrId from PrjtUsr where status>0 and RoleId="+user.getPrjtRoleId();
					if(wfRd.getProjectNo()!=null&&!"".equals(wfRd.getProjectNo())){
						roleSql+=" and PrjtNo='"+wfRd.getProjectNo()+"'";
					}
					List<PrjtUsr> users = new PrjtUsrFacade().find(roleSql,"UsrId");
					if(users!=null){
						for(PrjtUsr pusr:users){
							WfStepUser wsu = new WfStepUser();
							wsu.setStepId(user.getStepId());
							wsu.setUserType(user.getUserType());
							wsu.setUserId(pusr.getUsrId());
							autos.add(wsu);
						}
					}
				}
			}
			
			List<WfRdTask> autoList = new ArrayList<WfRdTask>();
			List<Usr> userList = new ArrayList<Usr>();
			if(WFUtil.isNotNull(autos)){		//不为空则自动转交
				for (int i = 0; i < autos.size(); i++) {
					WfStepUser s = autos.get(i);
					if (s != null && WFUtil.isNotNull(s.getStepId())) {
						// 如果指定的是用户
						if (WFUtil.isNotNull(s.getUserId())) {
							WfRdTask t = createTask(null, syId, null, wfRd.getWfNo());
							t.setAcceptBy(s.getUserId());
							t.setStepId(s.getStepId());
							t.setTaskType(s.getUserType());

							t.setPreTaskId(null);
							autoList.add(t);

							Usr u = new Usr();
							u.setId(s.getUserId());
							userList.add(u);
						}
					}
				}
			}
			
			if(WFUtil.isNotNull(autoList)){		//不为空有自动流转
				new WfRdTaskFacade().saveAll(autoList);
				sendMail(userList,wfCfg,wfRd);
				return autoList;
			}
			
			//无自动流转返回false
			return autoList;
		}
		protected WfRdTask createTask(WfRdTask t,Integer userId,Integer preTaskId,String wfNo) {		//创建任务
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
			t.setCreateBy(userId);
			t.setCreateDate(new Date());
			t.setPreTaskId(preTaskId);
			t.setWfNo(wfNo);
			t.setStatus(MSG.OWFTASK_STATUS_0);
			return t;
		}
		//任务发邮件,提示提醒
		protected void sendMail(List<Usr> userList,WfCfg cfg,WfRd wfRd)throws Exception {
			if(WFUtil.isNotNull(userList)){	
				String webUrl = getWebUrl(wfRd);//+"/index.shtml?wfRd.wfNo="+wfRd.getWfNo();
				String title = "工作流任务--"+cfg.getFlowName()+"："+wfRd.getWfNo();
				String content = "尊敬的同事，您好：" +
					"<p>您有一条《"+cfg.getFlowName()+"》流程的任务需要办理。" +
					"<p>工作流编号为："+wfRd.getWfNo()+"，工作流名称："+wfRd.getWfName()+"，请尽快处理，谢谢!"+
					"<p>任务办理链接地址 ： <a href="+webUrl+">"+webUrl+"</a>";
				//http://localhost:8080/zrprjt/WfRdView!wfTaskView.shtml?wfRd.wfNo=B4315300015&currentTaskId=5413&taskStepId=349
				//ServletActionContext.getServletContext().getInitParameter("server_URL")
				//+"/WfRdView!wfTaskView.shtml?wfRd.wfNo="+wfRd.getWfNo()+"&currentTaskId="++"&taskStepId="+
				WFUtil.sendMailByIT(title, content, userList);
			}
		}
		
	
		/**得到URL*/
	    protected String getWebUrl(WfRd wfRd){
	    	String basePath = getSysWebUrl() + "/index.shtml?wfRd.wfNo="+wfRd.getWfNo();
	    	return basePath;
	    }
	    
	    /**得到URL*/
	    protected String getSysWebUrl(){
	    	String basePath = ServletActionContext.getServletContext().getInitParameter("server_URL");
	    	return basePath;
		}
	
	
	    /**
		 * 上传风险附件
		 */
		public void riskAfterUploadFile() {
			try {
				String riskId = null;
				String impQuesIds = null;
				setFileName(URLDecoder.decode(getFileName(), "UTF-8"));
				setTempParams(URLDecoder.decode(getTempParams(), "UTF-8"));
				if (getFileNo() != null) {
					String sql = null;
					if (getTempParams() != null) {
						String ss[] = getTempParams().split(":");
						if (ss[0].equals("wfRisk.riskId")) {
							riskId = ss[1];
							sql = "update WfRisk set FileName='" + getFileName() + "', FileNo = '" + getFileNo() + "', LastUpdateUserId=" + usrId + ", LastUpdate ='" + DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss") + "' where RiskId='" + riskId+"'";
						}
						if (ss[0].equals("impQuesIds")) {
							impQuesIds = ss[1];
							sql = "update WfQues set FileName='" + getFileName() + "', FileNo = '" + getFileNo() + "', LastUpdateUserId=" + usrId + ", LastUpdate ='" + DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss") + "' where RiskId in (" + impQuesIds + ")";
						}
					}
					if (riskId != null || impQuesIds != null) {
						new WfRiskFacade().update(sql);
					}
				} else {
					setMsg("上传附件失败");
				}
			} catch (Exception e) {
				setMsg("上传附件失败");
				Logger.getLogger(this.getClass()).error("WfRiskAddAction uploadResultFile Exception", e);
				e.printStackTrace();
			}
		}
	
	
	
	
	
	
	
	
	
	
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getTempParams() {
		return tempParams;
	}
	public void setTempParams(String tempParams) {
		this.tempParams = tempParams;
	}
}