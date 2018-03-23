package gnwf.ww.json;

import java.io.File;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.write.WritableWorkbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;

import org.apache.log4j.Logger;
import org.apache.tools.ant.util.DateUtils;

import zrprjt.facade.PrjtUsrFacade;
import zrprjt.vo.PrjtUsr;
import zrsy.vo.Gp;
import zrsy.vo.Usr;

import com.alibaba.fastjson.JSON;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;

import gnwf.ww.MSG;
import gnwf.ww.BasicAction;
import gnwf.ww.workflow.WFUtil;
import gnwf.dao.WfQuesDAO;
import gnwf.facade.QuesRespFacade;
import gnwf.facade.WfQuesFacade;
import gnwf.vo.QuesResp;
import gnwf.vo.WfQues;
import gnwf.vo.json.QuesRespJson;

public class QuesRespAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<QuesResp> quesResps;
	private QuesResp quesResp = new QuesResp();
	private String quesRespIds;

	public String execute() throws Exception {
		try {
			if(quesResp != null && quesResp.getQuesId() != null) {
				quesResp = new QuesRespFacade().findById(quesResp);
				setJson(JSON.toJSONString(quesResp)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("QuesRespAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(quesResp != null && quesResp.getQuesId() != null) {
				//quesResp = new QuesRespFacade().findById(quesResp);
				//setJson(JSON.toJSONString(quesResp)); 
			//}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("QuesRespAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(quesResp != null && quesResp.getQuesId() != null) {
				quesResp = new QuesRespFacade().findById(quesResp);
				setJson(JSON.toJSONString(quesResp)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("QuesRespAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(quesResp != null && quesResp.getQuesId() != null) {
				quesResp = new QuesRespFacade().findById(quesResp);
				setJson(JSON.toJSONString(quesResp)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("QuesRespAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(quesResp == null) quesResp = new QuesResp();
			int total = new QuesRespFacade().amount(quesResp);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			quesResps = new QuesRespFacade().find(quesResp,getPageVO());
			QuesRespJson quesRespJson = new QuesRespJson();
			quesRespJson.Rows = quesResps;
			quesRespJson.Total = total;
			setJson(JSON.toJSONString(quesRespJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("QuesRespAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	
	
	
	private String fileNo;
	private String fileName;
	private String tempParams;
	
	private String usrId;
	private String syId;
	private String syNm;
	private String usrNm;
	public void afterUploadFile(){
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
	
	
	public String sav() throws Exception {
		try {
			quesResp.setCreateBy(getUsrSession().getId());
			quesResp.setCreateDate(new Date());
			quesResp.setLastUpd(getUsrSession().getId());
			quesResp.setLastUpdDate(new Date());

			if(quesResp.getId() == null){
				new QuesRespFacade().save(quesResp);
			}
			else{
				new QuesRespFacade().update(quesResp);
			
			}
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("QuesRespAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	
	private WfQues wfQues;
	public String upd() throws Exception {
		try {
			if(quesResp.getStatus().intValue() == 1){//责任人提出解决措施
				if(this.wfQues == null)this.wfQues = new WfQues();
				this.wfQues.setQuesId(this.quesResp.getQuesId());
				WfQuesFacade wfQuesFacade = new WfQuesFacade();
				this.wfQues = wfQuesFacade.findById(wfQues);
				QuesRespFacade qrf = new QuesRespFacade();
				String quesAnalysisString = this.quesResp.getQuesAnalysis();
				String quesResult = this.quesResp.getResult();
				quesResp.setIdtfRes("");
				quesResp.setResultDate(new Date());
				quesResp.setLastUpd(getUsrSession().getId());
				quesResp.setLastUpdDate(new Date());
				qrf.update(quesResp);
				
				this.wfQues.setStatus(getWfQuesStatus(this.quesResp.getQuesId()));
				wfQuesFacade.update(this.wfQues);
				
				ActionContext ctx = ActionContext.getContext();
				HttpServletRequest request = (HttpServletRequest) ctx
						.get(ServletActionContext.HTTP_REQUEST);
				String contextPath = request.getContextPath();
				String url = request.getRequestURL().toString();
				int index = url.indexOf(contextPath);
				String res_url = url.substring(0, index) + contextPath;
				StringBuffer title = new StringBuffer();
				title.append("问题编号《").append(this.wfQues.getQuesId()).append("》的解决措施已给出通知！");
				StringBuffer content = new StringBuffer();
				content.append("尊敬的同事，您好:<p>您提出的问题《")
				.append(this.wfQues.getQuesText()).append("》，我已经给出了相关解决措施，请尽快验证！</p></br><p>原因分析："+quesAnalysisString +"</p></br><p>解决措施："+ quesResult+"</p></br>链接地址：<a href="+ getAppcationURL())
				//.append(res_url)
				.append("/WfQues!managerQues.shtml?wfQues.quesId=").append(this.wfQues.getQuesId())
				.append(">")
				.append(res_url)
				.append("/WfQues!managerQues.shtml?wfQues.quesId=")
				.append(this.wfQues.getQuesId())
				.append("</a>");
				List<Usr> userList = new ArrayList<Usr>();
				Usr u = new Usr();
				u.setId(this.wfQues.getCreateBy());
				userList.add(u);
				//发邮件通知DQA
				List<PrjtUsr> prjtUsrList = new PrjtUsrFacade().find("select PrjtUsr.UsrId from PrjtUsr join PrjtRole on PrjtUsr.RoleId = PrjtRole.RoleId"
                         + " where PrjtRole.RoleNm in('DQA') and PrjtUsr.PrjtNo = '"
                         + wfQues.getPrjtNo() + "'","PrjtUsr.UsrId");
				for(PrjtUsr pu : prjtUsrList) {
					Usr u1 = new Usr();
					u.setId(pu.getUsrId());
					userList.add(u1);
				}
				WFUtil.sendMailByIT(title.toString(), content.toString(), userList);
			}else if(quesResp.getStatus().intValue() == -1){//验证人 给出无效验证结果
				boolean isDQA = false;
				for (Gp e : getUsrSession().getGps()) {
					if (e.getGpName().indexOf("DQA") > -1) {
						isDQA = true;
						break;
					}
				}
				quesResp.setIdtfDate(new Date());
				new QuesRespFacade().update(quesResp);
				String upSql = "update  WfQues set Status = " + getWfQuesStatus(quesResp.getQuesId()) + " where quesId = '" + quesResp.getQuesId()+"'";
				new WfQuesFacade().update(upSql);
				
				ActionContext ctx = ActionContext.getContext();
				HttpServletRequest request = (HttpServletRequest) ctx
						.get(ServletActionContext.HTTP_REQUEST);
				String contextPath = request.getContextPath();
				String url = request.getRequestURL().toString();
				int index = url.indexOf(contextPath);
				String res_url = url.substring(0, index) + contextPath;
				String title = "问题《" + wfQues.getQuesId() + "》-解决措施验证无效通知";
				String content = "";
				if(isDQA) {
					content ="尊敬的同事，您好：<p>您给的关于问题《"+wfQues.getQuesText()+"》</br>解决措施已被DQA验证为无效，请从新给出新的解决措施。"
							+ "</br><p>链接地址 ： <a href=" + res_url
							+ "/WfQues!managerQues.shtml?wfQues.quesId="
							+ wfQues.getQuesId() + ">" + res_url
							+ "/WfQues!managerQues.shtml?wfQues.quesId="
							+ wfQues.getQuesId() + "</a>";
				}else {
					content = "尊敬的同事，您好：<p>您给的关于问题《"+wfQues.getQuesText()+"》</br>解决措施已被验证为无效，请从新给出新的解决措施。"
							+ "</br><p>链接地址 ： <a href=" + res_url
							+ "/WfQues!managerQues.shtml?wfQues.quesId="
							+ wfQues.getQuesId() + ">" + res_url
							+ "/WfQues!managerQues.shtml?wfQues.quesId="
							+ wfQues.getQuesId() + "</a>";
				}
				List<Usr> userList = new ArrayList<Usr>();
				Usr u = new Usr();
				u.setId(quesResp.getUsrId());
				userList.add(u);
				WFUtil.sendMailByIT(title, content, userList);
			}else if(quesResp.getStatus().intValue() == -2){//验证人 驳回措施，该措施无法定义是否有效
				boolean isDQA = false;
				for (Gp e : getUsrSession().getGps()) {
					if (e.getGpName().indexOf("DQA") > -1) {
						isDQA = true;
						break;
					}
				}
				quesResp.setIdtfDate(new Date());
				new QuesRespFacade().update(quesResp);
				String upSql = "update  WfQues set Status = " + getWfQuesStatus(quesResp.getQuesId()) + " where quesId = '" + quesResp.getQuesId()+"'";
				new WfQuesFacade().update(upSql);
				
				ActionContext ctx = ActionContext.getContext();
				HttpServletRequest request = (HttpServletRequest) ctx
						.get(ServletActionContext.HTTP_REQUEST);
				String contextPath = request.getContextPath();
				String url = request.getRequestURL().toString();
				int index = url.indexOf(contextPath);
				String res_url = url.substring(0, index) + contextPath;
				String title = "问题《" + wfQues.getQuesId() + "》-解决措施验证被退回通知";
				String content = "";
				if(isDQA) {
					content ="尊敬的同事，您好：<p>您给的关于问题《"+wfQues.getQuesText()+"》</br>解决措施已被DQA退回，请从新给出新的解决措施。"
							+ "</br><p>链接地址 ： <a href=" + res_url
							+ "/WfQues!managerQues.shtml?wfQues.quesId="
							+ wfQues.getQuesId() + ">" + res_url
							+ "/WfQues!managerQues.shtml?wfQues.quesId="
							+ wfQues.getQuesId() + "</a>";
				}else {
					content = "尊敬的同事，您好：<p>您给的关于问题《"+wfQues.getQuesText()+"》</br>解决措施已被退回，请从新给出新的解决措施。"
							+ "</br><p>链接地址 ： <a href=" + res_url
							+ "/WfQues!managerQues.shtml?wfQues.quesId="
							+ wfQues.getQuesId() + ">" + res_url
							+ "/WfQues!managerQues.shtml?wfQues.quesId="
							+ wfQues.getQuesId() + "</a>";
				}
				List<Usr> userList = new ArrayList<Usr>();
				Usr u = new Usr();
				u.setId(quesResp.getUsrId());
				userList.add(u);
				WFUtil.sendMailByIT(title, content, userList);
			}else if(quesResp.getStatus().intValue() == 2){//验证人 给出有效验证结果
				quesResp.setIdtfDate(new Date());
				new QuesRespFacade().update(quesResp);
				int status = getWfQuesStatus(quesResp.getQuesId());
				String sql = "select SchNm from SchCfg where SchId = (select Parent from SchCfg left join WfQues on (SchCfg.SchId = WfQues.ScheId) where WfQues.QuesId = '"+quesResp.getQuesId()+"')";
				String schNm = new WfQuesDAO().findWfQuesScheNm(sql);
				if(null!=schNm && status == MSG.WFQUES_STATUS_11){
					if(schNm.equals("预研")||schNm.equals("预立项") || schNm.equals("开发")){
						status = MSG.WFQUES_STATUS_30;
					}
				}
				String upSql = "update  WfQues set Status = " + status + " where quesId = '" + quesResp.getQuesId()+"'";
				new WfQuesFacade().update(upSql);
			}
			setMsg(MSG.S_UPD);
		}catch(Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("QuesRespAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	
	/**
	 * 批量验证无效
	 * @return
	 */
	public String batchReject() {
		try {
			String[] quesRespIdArry = quesRespIds.split(",");
			new QuesRespFacade().batchReject(quesRespIdArry,getUsrSession().getId());
			setMsg(MSG.S_UPD);
		}catch(Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("QuesRespAction batchReject Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	/**
	 * 批量验证有效
	 * @return
	 */
	public String  batchApprove() {
		try {
			String[] quesRespIdArry = quesRespIds.split(",");
			new QuesRespFacade().batchApprove(quesRespIdArry, getUsrSession().getId());
			setMsg(MSG.S_UPD);
		}catch(Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("QuesRespAction batchApprove Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	/**
	 * 批量挂起
	 * @return
	 * @throws Exception
	 */
	public String batchHangup(){
		try {
			String[] quesIdArry = choices.split(",");
			QuesRespFacade qrf = new QuesRespFacade();
			WfQuesFacade wf = new WfQuesFacade();
			for(String quesIdStr : quesIdArry) {
				WfQues wq = new WfQues();
				wq.setQuesId(quesIdStr);
				wq.setStatus(MSG.WFQUES_STATUS_21);
				wq.setLastUpd(getUsrSession().getId());
				wq.setLastUpdDate(new Date());
				wq.setIdtfDate(new Date());
				wf.update(wq);
				List<QuesResp> qrList = qrf.find("select " + QuesResp.SELF_FIELDS + " from QuesResp where QuesResp.QuesId = '"
												+ quesIdStr+"'", QuesResp.SELF_FIELDS);
				if(qrList != null) {
					for(QuesResp qr : qrList) {
						if(qr.getStatus().intValue() == MSG.QUESRESP_STATUS_1 || qr.getStatus().intValue() == MSG.QUESRESP_STATUS_2
								|| qr.getStatus().intValue() == MSG.QUESRESP_STATUS_DISABLE) {
							qr.setStatus(MSG.QUESRESP_STATUS_3);
							qr.setIdtfDate(new Date());
							qr.setLastUpd(getUsrSession().getId());
							qr.setLastUpdDate(new Date());
							qrf.update(qr);
						}
					}
				}
			}
			setMsg("已挂起");
		} catch (Exception e) {
			setMsg("挂起失败");
			Logger.getLogger(this.getClass()).error("WfQuesAction Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return MESSAGE;
	}
	
	public String voi() throws Exception {
		try {
			if(quesResps != null && quesResps.size() > 0) {
				for(int i=0; i<quesResps.size();i++) {
					if(quesResps.get(i) != null) {
						new QuesRespFacade().submit(quesResps.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("QuesRespAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(quesResps != null && quesResps.size() > 0) {
				for(int i=0; i<quesResps.size();i++) {
					if(quesResps.get(i) != null) {
						//quesResps.get(i).setLastUpd(getSession().getUserId());
						//quesResps.get(i).setLastUpdDate(new Date());
						new QuesRespFacade().submit(quesResps.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("QuesRespAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(quesResps != null && quesResps.size() > 0) {
				for(int i=0; i<quesResps.size();i++) {
					if(quesResps.get(i) != null) {
						//quesResps.get(i).setLastUpd(getSession().getUserId());
						//quesResps.get(i).setLastUpdDate(new Date());
						new QuesRespFacade().update(quesResps.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("QuesRespAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(quesResps != null && quesResps.size() > 0) {
				for(int i=0; i<quesResps.size();i++) {
					if(quesResps.get(i) != null) {
						//quesResps.get(i).setLastUpd(getSession().getUserId());
						//quesResps.get(i).setLastUpdDate(new Date());
						new QuesRespFacade().submit(quesResps.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("QuesRespAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(quesResps != null && quesResps.size() > 0) {
				for(int i=0; i<quesResps.size();i++) {
					if(quesResps.get(i) != null) {
						//quesResps.get(i).setLastUpd(getSession().getUserId());
						//quesResps.get(i).setLastUpdDate(new Date());
						new QuesRespFacade().submit(quesResps.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("QuesRespAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(quesResps != null && quesResps.size() > 0) {
				for(int i=0; i<quesResps.size();i++) {
					if(quesResps.get(i) != null) {
						//quesResps.get(i).setLastUpd(getSession().getUserId());
						//quesResps.get(i).setLastUpdDate(new Date());
						new QuesRespFacade().submit(quesResps.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("QuesRespAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(quesResps != null && quesResps.size() > 0) {
				for(int i=0; i<quesResps.size();i++) {
					if(quesResps.get(i) != null) {
						//quesResps.get(i).setLastUpd(getSession().getUserId());
						//quesResps.get(i).setLastUpdDate(new Date());
						new QuesRespFacade().submit(quesResps.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("QuesRespAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(quesResps != null && quesResps.size() > 0) {
				for(int i=0; i<quesResps.size();i++) {
					if(quesResps.get(i) != null){
						//quesResps.get(i).setLastUpd(getSession().getUserId());
						//quesResps.get(i).setLastUpdDate(new Date());
						new QuesRespFacade().submit(quesResps.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("QuesRespAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(quesResps != null && quesResps.size() > 0) {
				for(int i=0; i<quesResps.size();i++) {
					if(quesResps.get(i) != null){
						//quesResps.get(i).setLastUpd(getSession().getUserId());
						//quesResps.get(i).setLastUpdDate(new Date());
						new QuesRespFacade().review(quesResps.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("QuesRespAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(quesResps != null && quesResps.size() > 0) {
				for(int i=0; i<quesResps.size();i++) {
					if(quesResps.get(i) != null) {
						//quesResps.get(i).setLastUpd(getSession().getUserId());
						//quesResps.get(i).setLastUpdDate(new Date());
						new QuesRespFacade().review(quesResps.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("QuesRespAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(quesResps != null && quesResps.size() > 0) {
				for(int i=0; i<quesResps.size();i++) {
					if(quesResps.get(i) != null) {
						//quesResps.get(i).setLastUpd(getSession().getUserId());
						//quesResps.get(i).setLastUpdDate(new Date());
						new QuesRespFacade().confirm(quesResps.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("QuesRespAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(quesResps != null && quesResps.size() > 0) {
				for(int i=0; i<quesResps.size();i++) {
					if(quesResps.get(i) != null) {
						//quesResps.get(i).setLastUpd(getSession().getUserId());
						//quesResps.get(i).setLastUpdDate(new Date());
						new QuesRespFacade().confirm(quesResps.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("QuesRespAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(quesResps != null && quesResps.size() > 0) {
					for(int i=0; i<quesResps.size();i++) {
						if(quesResps.get(i) != null) {
							//quesResps.get(i).setLastUpd(getSession().getUserId());
							//quesResps.get(i).setLastUpdDate(new Date());
							new QuesRespFacade().confirm(quesResps.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("QuesRespAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<QuesResp> quesResps = new QuesRespFacade().find(quesResp);
			if(quesResps != null && quesResps.size() > 0) {
				WritableCellFormat wcformat = new WritableCellFormat();
				wcformat.setAlignment(jxl.format.Alignment.CENTRE);
				wcformat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				wcformat.setBorder(Border.LEFT,BorderLineStyle.THIN);
				wcformat.setBorder(Border.RIGHT,BorderLineStyle.THIN);
				wcformat.setBorder(Border.TOP,BorderLineStyle.THIN);
				wcformat.setBorder(Border.BOTTOM,BorderLineStyle.THIN);
				wcformat.setWrap(true);
				OutputStream os = getOutputStream();
				workbook = Workbook.createWorkbook(os);
				WritableSheet ws = workbook.createSheet("sheet0", 0);
				int index = 0;
				
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<quesResps.size();i++) {
					row++;
					int m = 0;
					if(quesResps.get(i).getLastUpdDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,quesResps.get(i).getLastUpdDate(),wcformat));
					m++;
					if(quesResps.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,quesResps.get(i).getCreateBy(),wcformat));
					m++;
					if(quesResps.get(i).getResultFileNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,quesResps.get(i).getResultFileNo(),wcformat));
					m++;
					if(quesResps.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,quesResps.get(i).getLastUpd(),wcformat));
					m++;
					if(quesResps.get(i).getQuesId() != null) 
						ws.addCell(new jxl.write.Label(m,row,quesResps.get(i).getQuesId(),wcformat));
					m++;
					if(quesResps.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,quesResps.get(i).getStatus(),wcformat));
					m++;
					if(quesResps.get(i).getResult() != null) 
						ws.addCell(new jxl.write.Label(m,row,quesResps.get(i).getResult(),wcformat));
					m++;
					if(quesResps.get(i).getResultFileName() != null) 
						ws.addCell(new jxl.write.Label(m,row,quesResps.get(i).getResultFileName(),wcformat));
					m++;
					if(quesResps.get(i).getUsrId() != null) 
						ws.addCell(new jxl.write.Number(m,row,quesResps.get(i).getUsrId(),wcformat));
					m++;
					if(quesResps.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,quesResps.get(i).getCreateDate(),wcformat));
					m++;
					if(quesResps.get(i).getIdtfRes() != null) 
						ws.addCell(new jxl.write.Label(m,row,quesResps.get(i).getIdtfRes(),wcformat));
					m++;
					if(quesResps.get(i).getIdtfBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,quesResps.get(i).getIdtfBy(),wcformat));
					m++;
					if(quesResps.get(i).getIdtfResFileName() != null) 
						ws.addCell(new jxl.write.Label(m,row,quesResps.get(i).getIdtfResFileName(),wcformat));
					m++;
					if(quesResps.get(i).getIdtfResFileNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,quesResps.get(i).getIdtfResFileNo(),wcformat));
					m++;
					if(quesResps.get(i).getIdtfDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,quesResps.get(i).getIdtfDate(),wcformat));
					m++;
					if(quesResps.get(i).getResultDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,quesResps.get(i).getResultDate(),wcformat));
					m++;
					if(quesResps.get(i).getId() != null) 
						ws.addCell(new jxl.write.Number(m,row,quesResps.get(i).getId(),wcformat));
					m++;
					if(quesResps.get(i).getRemarkFileNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,quesResps.get(i).getRemarkFileNo(),wcformat));
					m++;
					if(quesResps.get(i).getRespType() != null) 
						ws.addCell(new jxl.write.Number(m,row,quesResps.get(i).getRespType(),wcformat));
					m++;
					if(quesResps.get(i).getRemarkFileName() != null) 
						ws.addCell(new jxl.write.Label(m,row,quesResps.get(i).getRemarkFileName(),wcformat));
					m++;
					if(quesResps.get(i).getRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,quesResps.get(i).getRemark(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("QuesRespListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(quesResps != null && quesResps.size() > 0) {
				for(int i=0; i<quesResps.size();i++) {
					if(quesResps.get(i) != null) {
						//quesResps.get(i).setLastUpd(getSession().getUserId());
						//quesResps.get(i).setLastUpdDate(new Date());
						new QuesRespFacade().confirm(quesResps.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("QuesRespAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(quesResps != null && quesResps.size() > 0) {
				for(int i=0; i<quesResps.size();i++) {
					if(quesResps.get(i) != null) {
						//quesResps.get(i).setLastUpd(getSession().getUserId());
						//quesResps.get(i).setLastUpdDate(new Date());
						new QuesRespFacade().confirm(quesResps.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("QuesRespAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<QuesResp> getQuesResps() {
		return quesResps;
	}
	public void setQuesResps(List<QuesResp> quesResps) {
		this.quesResps = quesResps;
	}
	public QuesResp getQuesResp() {
		return quesResp;
	}
	public void setQuesResp(QuesResp quesResp) {
		this.quesResp = quesResp;
	}
	public WritableWorkbook getWorkbook() {
		return workbook;
	}
	public void setWorkbook(WritableWorkbook workbook) {
		this.workbook = workbook;
	}
	public File getFileInp() {
		return fileInp;
	}
	public void setFileInp(File fileInp) {
		this.fileInp = fileInp;
	}
	public String getChoices() {
		return choices;
	}
	public void setChoices(String choices) {
		this.choices = choices;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getTempParams() {
		return tempParams;
	}
	public void setTempParams(String tempParams) {
		this.tempParams = tempParams;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getSyId() {
		return syId;
	}
	public void setSyId(String syId) {
		this.syId = syId;
	}
	public String getSyNm() {
		return syNm;
	}
	public void setSyNm(String syNm) {
		this.syNm = syNm;
	}
	public String getUsrNm() {
		return usrNm;
	}
	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}
	public WfQues getWfQues() {
		return wfQues;
	}
	public void setWfQues(WfQues wfQues) {
		this.wfQues = wfQues;
	}
	public String getQuesRespIds() {
		return quesRespIds;
	}
	public void setQuesRespIds(String quesRespIds) {
		this.quesRespIds = quesRespIds;
	}
}