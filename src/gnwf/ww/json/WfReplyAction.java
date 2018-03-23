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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tools.ant.util.DateUtils;

import zrprjt.facade.PrjtDefFacade;
import zrprjt.facade.SchCfgFacade;
import zrprjt.vo.PrjtDef;
import zrprjt.vo.SchCfg;
import zrsy.vo.Gp;
import zrsy.vo.Usr;

import com.alibaba.fastjson.JSON;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;

import gnwf.ww.MSG;
import gnwf.ww.BasicAction;
import gnwf.ww.workflow.WFUtil;

import gnwf.dao.WfQuesDAO;
import gnwf.dao.WfReplyDAO;
import gnwf.facade.WfQuesFacade;
import gnwf.facade.WfRdFacade;
import gnwf.facade.WfReplyFacade;
import gnwf.vo.WfQues;
import gnwf.vo.WfRd;
import gnwf.vo.WfReply;
import gnwf.vo.json.WfReplyJson;

public class WfReplyAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfReply> wfReplys;
	private WfReply wfReply = new WfReply();
	private java.util.List<gnwf.vo.WfQues> wfQuess;
	private WfQues wfQues;
	private String fileNo;
	private String fileName;
	private String usrId;
	
	private String syId;
	private String syNm;
	private String prjtNo;
	private String prjtNm;
	private String wfId;
	private String wfNm;
	private String usrNm;
	
	private String tempParams;
	
	private int reload = 0;
	

	public String execute() throws Exception {
		try {
			if(wfReply != null && wfReply.getReplyId() != null) {
				wfReply = new WfReplyFacade().findById(wfReply);
				setJson(JSON.toJSONString(wfReply)); 
			}
			wfQuess = new gnwf.facade.WfQuesFacade().find("select "+gnwf.vo.WfQues.SELF_FIELDS+" from WfQues",gnwf.vo.WfQues.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	
	private Gp gp;
	private void setPrjtRole() {
		for( Gp e: getUsrSession().getGps()){
			
			if(e.getGpName().indexOf("超级用户")>-1){
				//isAdmin = true;
				gp = e;
				break;
			}else if(e.getGpName().indexOf("产品经理")>-1){
				gp = e;
				break;
			}else if(e.getGpName().indexOf("项目经理")>-1){
				gp = e;
				break;
			}else if(e.getGpName().indexOf("DQA")>-1){
				gp = e;
				break;
			}
			
		}
	}
	
	public String add() throws Exception {
		try {
			//if(wfReply != null && wfReply.getReplyId() != null) {
				//wfReply = new WfReplyFacade().findById(wfReply);
				//setJson(JSON.toJSONString(wfReply)); 
			//}
			//System.out.println("reload:"+reload);
			syId = String.valueOf(getUsrSession().getSyId());
			syNm = getUsrSession().getSyNm();
			usrId = String.valueOf(getUsrSession().getId());
			usrNm = getUsrSession().getUsrName();
//			prjtNo;
//			private String prjtNm;
//			private String wfId;
//			private String wfNm;
			wfQues = new WfQues();
			wfQues.setQuesId(wfReply.getQuesId());
			wfQues = new WfQuesFacade().findById(wfQues);
			
			if(wfQues.getWfNo()!=null){
				WfRd wf= new WfRd();
				wf.setWfNo(wfQues.getWfNo());
				wf = new WfRdFacade().findById(wf);
				wfId = wf.getWfNo();
				wfNm = wf.getWfName();
				
				if(wf.getProjectNo()!=null){
					PrjtDef pd = new PrjtDef();
					pd.setPrjtNo(wf.getProjectNo());
					pd= new PrjtDefFacade().findById(pd);
					prjtNo = pd.getPrjtNo();
					prjtNm = pd.getPrjtNm();
				}
				
			}
			wfReplys = new WfReplyFacade().find(wfReply);
			setPrjtRole();
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(wfReply != null && wfReply.getReplyId() != null) {
				wfReply = new WfReplyFacade().findById(wfReply);
				setJson(JSON.toJSONString(wfReply)); 
			}
			wfQuess = new gnwf.facade.WfQuesFacade().find("select "+gnwf.vo.WfQues.SELF_FIELDS+" from WfQues",gnwf.vo.WfQues.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	
	public String view() throws Exception {
		try {
			if(wfReply != null && wfReply.getReplyId() != null) {
				wfReply = new WfReplyFacade().findById(wfReply);
				setJson(JSON.toJSONString(wfReply)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(wfReply == null) wfReply = new WfReply();
			int total = new WfReplyFacade().amount(wfReply);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfReplys = new WfReplyFacade().find(wfReply,getPageVO());
			WfReplyJson wfReplyJson = new WfReplyJson();
			wfReplyJson.Rows = wfReplys;
			wfReplyJson.Total = total;
			setJson(JSON.toJSONString(wfReplyJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//wfReply.setCreateBy(getSession().getUserId());
			//wfReply.setCreateDate(new Date());
			//wfReply.setLastUpd(getSession().getUserId());
			//wfReply.setLastUpdDate(new Date());

			wfReply.setLastUpd(getUsrSession().getId());
			wfReply.setLastUpdDate(new Date());
			if(wfReply.getReplyId() == null){
				wfReply.setCreateBy(getUsrSession().getId());
				wfReply.setCreateDate(new Date());
				wfReply.setStatus(1);
				updateAndSendMail();
				if(wfQues.getIdtfBy().intValue()==getUsrSession().getId()){
					String sql = "select max(ReplyId) as ReplyId from WfReply where QuesId = "+wfQues.getQuesId();
					Integer replyId  = new WfReplyDAO().getMaxQuesReplyId(sql);
					if(replyId!=null){
						wfReply.setGroupId(replyId);
					}
				}
				new WfReplyFacade().save(wfReply);
			} else{
				new WfReplyFacade().update(wfReply);
			}
			//在立项阶段，如验证人通过，则马上关闭流程
			
			if(null!=wfQues.getStatus()&&wfQues.getStatus()==MSG.WFQUES_STATUS_11){
				String sql = "select SchNm from SchCfg where SchId = (select Parent from SchCfg left join WfQues on (SchCfg.SchId = WfQues.ScheId) where WfQues.QuesId = "+wfReply.getQuesId()+")";
				String schNm = new WfQuesDAO().findWfQuesScheNm(sql);
				if(null!=schNm&&schNm.equals("立项")){
					String updateWfQuesSQL = "update WfQues set Status = "+MSG.WFQUES_STATUS_30 + " where QuesId ="+ wfReply.getQuesId();
					new WfQuesFacade().update(updateWfQuesSQL);			
				}
			}
			
			new WfQuesFacade().update(wfQues);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfReplyAddAction add Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return MESSAGE;
	}
	
	
	public void afterUploadFile(){
		try{
			String quesId =null;
			setFileName(URLDecoder.decode(getFileName(), "UTF-8"));
			setTempParams(URLDecoder.decode(getTempParams(), "UTF-8"));
			if(getFileNo()!=null){
				String sql =null;
				if (getTempParams() != null){
					String s[] = getTempParams().split(",");
					for(int i = 0;i<s.length;i++){
						 String ss[]=s[i].split(":");
						 if(ss[0].equals("wfReply.quesId")){
							 quesId = ss[1];
						 }
						 
						 if(ss[0].equals("wfReplyType")){
							 
							 if(ss[1].equals("ResultFile")){
								 sql = "update WfQues set ResultFileName='"+getFileName()+"', ResultFileNo = '"+getFileNo()+"', LastUpd="+usrId+", LastUpdDate ='"
											+DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss")+"' where QuesId="+quesId;
							 }else if(ss[1].equals("IdtfResFile")){
								 sql= "update WfQues set IdtfResFileName='"+getFileName()+"', IdtfResFileNo = '"+getFileNo()+"', LastUpd="+usrId+", LastUpdDate ='"
											+DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss")+"' where QuesId="+quesId;
							 }
							 
						   }
					}
				}
				if(quesId!=null){
					new WfQuesFacade().update(sql);
				}
			}else {
				setMsg("上传附件失败");
			}
		} catch (Exception e) {
			setMsg("上传附件失败");
			Logger.getLogger(this.getClass()).error(
					"WfQuesAddAction uploadResultFile Exception", e);
			e.printStackTrace();
		}
		
	}
	

    private int wfRepLyType;
    private void updateAndSendMail()throws Exception{
    	Integer  mailTo =null;
    	String title = null;
    	String content = null;
    	
    	//计算出res_url
    	ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);
		String contextPath = request.getContextPath();
		String url = request.getRequestURL().toString();
		int index = url.indexOf(contextPath);
		String res_url = url.substring(0, index) + contextPath;
		
		
		//
		wfQues.setQuesId(wfReply.getQuesId());
		wfQues.setLastUpd(getUsrSession().getId());
		wfQues.setLastUpdDate(new Date());
		
		WfQues sqlWfQues = new WfQues();  
		sqlWfQues.setQuesId(wfReply.getQuesId());
		sqlWfQues = new WfQuesFacade().findById(sqlWfQues);
		
		if(wfRepLyType==1){
			
			//如果当前是验证人   且给出验证结果为不通过    则发邮件通知问题责任人
			if (sqlWfQues.getIdtfBy() != null&&sqlWfQues.getIdtfBy().intValue()==getUsrSession().getId().intValue()) {
				wfQues.setIdtfRes(wfReply.getReplyText());
				String idtfRes = wfQues.getIdtfRes();
				if (StringUtils.isNotBlank(idtfRes)) {
					wfQues.setIdtfDate(new Date());
				}
				//idtfBy_cur = wfQues.getIdtfBy();
				if(wfQues.getStatus().intValue()==MSG.WFQUES_STATUS_9){
					mailTo = sqlWfQues.getUserId();
					title = "工作流(" + sqlWfQues.getWfNo() + ")-问题验证未通过办理通知";
					content = "尊敬的同事，您好：<p>您的问题解决措施验证未通过,需要您及时办理。"
							+ "<p>问题内容:" + "<p>" + sqlWfQues.getQuesText()
							+ "<p>链接地址 ： <a href=" + res_url
							+ "/WfReply!add.shtml?WfReply.quesId="
							+ sqlWfQues.getQuesId() + ">" + res_url
							+ "/WfReply!add.shtml?WfReply.quesId="
							+ sqlWfQues.getQuesId() + "</a>";
					
					sendMail(mailTo, title, content);
				}
			}
		}
    	
		
		if(wfRepLyType==0){
    	//如果当前是责任人，给出问题解决措施 ，再邮件通知问题验证人
		if (sqlWfQues.getUserId()!= null&&sqlWfQues.getUserId().intValue()==getUsrSession().getId().intValue()) {
			
			wfQues.setResult(wfReply.getReplyText());
			
			mailTo = wfQues.getIdtfBy();
			title = "工作流(" + sqlWfQues.getWfNo() + ")-问题验证通知";
			content = "尊敬的同事，您好：<p>此工作流存在提出问题,需要您验证。"
					+ "<p>问题内容:" + "<p>" + sqlWfQues.getQuesText()
					+ "<p>链接地址 ： <a href=" + res_url
					+ "/WfReply!add.shtml?WfReply.quesId="
					+ sqlWfQues.getQuesId() + ">" + res_url
					+ "/WfReply!add.shtml?WfReply.quesId="
					+ sqlWfQues.getQuesId() + "</a>";
			
			sendMail(mailTo, title, content);
		}
		}
	    new WfQuesFacade().update(wfQues);
    }
    
    /**
	private void updateWfQues() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);
		String contextPath = request.getContextPath();
		String url = request.getRequestURL().toString();
		int index = url.indexOf(contextPath);
		String res_url = url.substring(0, index) + contextPath;
		
		wfQues.setQuesId(wfReply.getQuesId());
		wfQues.setLastUpd(getUsrSession().getId());
		wfQues.setLastUpdDate(new Date());
		
		
		WfQues his_Ques = new WfQues();
		his_Ques = new WfQuesFacade().findById(wfQues);
		String idtfRes_his = his_Ques.getIdtfRes();
		int idtfBy_cur = 0;
		String idtfRes_cur = wfQues.getIdtfRes();
		
		
		//如果当前是验证人   且给出验证结果为不通过    则发邮件通知问题责任人
		if (wfQues.getIdtfBy() != null
				&&wfQues.getIdtfBy().intValue()==getUsrSession().getId().intValue()) {
			wfQues.setIdtfRes(wfReply.getReplyText());
			String idtfRes = wfQues.getIdtfRes();
			if (StringUtils.isNotBlank(idtfRes)) {
				wfQues.setIdtfDate(new Date());
			}
			idtfBy_cur = wfQues.getIdtfBy();
			
			if(wfQues.getStatus().intValue()==MSG.WFQUES_STATUS_10){
				String title = "工作流(" + wfQues.getWfNo() + ")-问题验证未通过办理通知";
				String content = "尊敬的同事，您好：<p>您的问题解决措施验证未通过,需要您及时办理。"
						+ "<p>问题内容:" + "<p>" + wfQues.getQuesText()
						+ "<p>链接地址 ： <a " + res_url
						+ "/WfReply!add.shtml?WfReply.quesId="
						+ wfQues.getQuesId() + ">" + res_url
						+ "/WfReply!add.shtml?WfReply.quesId="
						+ wfQues.getQuesId() + "</a>";
				sendMail(his_Ques.getUserId(), title, content);
			}
			
		}
		
		
		
		//如果当前为第三者（不是问题责任人也不是问题验证人）   给出解决措施后   邮件通知问题责任人
		int usr_his = 0;
		if (wfQues.getUserId() != null&&wfQues.getUserId().intValue()==getUsrSession().getId().intValue()) {
			wfQues.setResult(wfReply.getReplyText());
			usr_his = wfQues.getUserId();
		}
		int usr_cur = his_Ques.getUserId();
		if (usr_cur != 0 && usr_cur != usr_his) {
			String title = "工作流(" + wfQues.getWfNo() + ")-问题办理通知";
			String content = "尊敬的同事，您好：<p>此工作流存在提出问题,需要您及时办理。"
					+ "<p>问题内容:" + "<p>" + wfQues.getQuesText()
					+ "<p>链接地址 ： <a " + res_url
					+ "/WfReply!add.shtml?WfReply.quesId="
					+ wfQues.getQuesId() + ">" + res_url
					+ "/WfReply!add.shtml?WfReply.quesId="
					+ wfQues.getQuesId() + "</a>";
			sendMail(his_Ques.getUserId(), title, content);
		}
		
		
		
		
		// 更新验证信息时,发送邮件通知验证人          如果当前人为责任人，给出问题解决措施 ，再邮件通知问题验证人
		if (idtfBy_cur != 0 && idtfRes_cur != null
				&& !"".equals(idtfRes_cur)
				&& !idtfRes_cur.equals(idtfRes_his)) {
			String title = "工作流(" + wfQues.getWfNo() + ")-问题验证通知";
			String content = "尊敬的同事，您好：<p>此工作流存在提出问题,需要您验证。"
					+ "<p>问题内容:" + "<p>" + wfQues.getQuesText()
					+ "<p>链接地址 ： <a " + res_url
					+ "/WfReply!add.shtml?WfReply.quesId="
					+ wfQues.getQuesId() + ">" + res_url
					+ "/WfReply!add.shtml?WfReply.quesId="
					+ wfQues.getQuesId() + "</a>";
			sendMail(wfQues.getIdtfBy(), title, content);
		}
		
		
		
		new WfQuesFacade().update(wfQues);
	}
	**/
	private void sendMail(int ursId, String title, String content)
			throws Exception {
		List<Usr> list = new ArrayList<Usr>();
		Usr u = new Usr();
		u.setId(ursId);
		list.add(u);

		WFUtil.sendMailByIT(title, content, list);
	}
	
	
	public String upd() throws Exception {
		try {
			//wfReply.setCreateBy(getSession().getUserId());
			//wfReply.setCreateDate(new Date());
			//wfReply.setLastUpd(getSession().getUserId());
			//wfReply.setLastUpdDate(new Date());
			new WfReplyFacade().update(wfReply);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfReplyAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfReplys != null && wfReplys.size() > 0) {
				for(int i=0; i<wfReplys.size();i++) {
					if(wfReplys.get(i) != null) {
						//wfReplys.get(i).setLastUpd(getSession().getUserId());
						//wfReplys.get(i).setLastUpdDate(new Date());
						new WfReplyFacade().submit(wfReplys.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfReplys != null && wfReplys.size() > 0) {
				for(int i=0; i<wfReplys.size();i++) {
					if(wfReplys.get(i) != null) {
						//wfReplys.get(i).setLastUpd(getSession().getUserId());
						//wfReplys.get(i).setLastUpdDate(new Date());
						new WfReplyFacade().submit(wfReplys.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfReplys != null && wfReplys.size() > 0) {
				for(int i=0; i<wfReplys.size();i++) {
					if(wfReplys.get(i) != null) {
						//wfReplys.get(i).setLastUpd(getSession().getUserId());
						//wfReplys.get(i).setLastUpdDate(new Date());
						new WfReplyFacade().update(wfReplys.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfReplys != null && wfReplys.size() > 0) {
				for(int i=0; i<wfReplys.size();i++) {
					if(wfReplys.get(i) != null) {
						//wfReplys.get(i).setLastUpd(getSession().getUserId());
						//wfReplys.get(i).setLastUpdDate(new Date());
						new WfReplyFacade().submit(wfReplys.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfReplys != null && wfReplys.size() > 0) {
				for(int i=0; i<wfReplys.size();i++) {
					if(wfReplys.get(i) != null) {
						//wfReplys.get(i).setLastUpd(getSession().getUserId());
						//wfReplys.get(i).setLastUpdDate(new Date());
						new WfReplyFacade().submit(wfReplys.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfReplys != null && wfReplys.size() > 0) {
				for(int i=0; i<wfReplys.size();i++) {
					if(wfReplys.get(i) != null) {
						//wfReplys.get(i).setLastUpd(getSession().getUserId());
						//wfReplys.get(i).setLastUpdDate(new Date());
						new WfReplyFacade().submit(wfReplys.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfReplys != null && wfReplys.size() > 0) {
				for(int i=0; i<wfReplys.size();i++) {
					if(wfReplys.get(i) != null) {
						//wfReplys.get(i).setLastUpd(getSession().getUserId());
						//wfReplys.get(i).setLastUpdDate(new Date());
						new WfReplyFacade().submit(wfReplys.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfReplys != null && wfReplys.size() > 0) {
				for(int i=0; i<wfReplys.size();i++) {
					if(wfReplys.get(i) != null){
						//wfReplys.get(i).setLastUpd(getSession().getUserId());
						//wfReplys.get(i).setLastUpdDate(new Date());
						new WfReplyFacade().submit(wfReplys.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfReplys != null && wfReplys.size() > 0) {
				for(int i=0; i<wfReplys.size();i++) {
					if(wfReplys.get(i) != null){
						//wfReplys.get(i).setLastUpd(getSession().getUserId());
						//wfReplys.get(i).setLastUpdDate(new Date());
						new WfReplyFacade().review(wfReplys.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfReplys != null && wfReplys.size() > 0) {
				for(int i=0; i<wfReplys.size();i++) {
					if(wfReplys.get(i) != null) {
						//wfReplys.get(i).setLastUpd(getSession().getUserId());
						//wfReplys.get(i).setLastUpdDate(new Date());
						new WfReplyFacade().review(wfReplys.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfReplys != null && wfReplys.size() > 0) {
				for(int i=0; i<wfReplys.size();i++) {
					if(wfReplys.get(i) != null) {
						//wfReplys.get(i).setLastUpd(getSession().getUserId());
						//wfReplys.get(i).setLastUpdDate(new Date());
						new WfReplyFacade().confirm(wfReplys.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfReplys != null && wfReplys.size() > 0) {
				for(int i=0; i<wfReplys.size();i++) {
					if(wfReplys.get(i) != null) {
						//wfReplys.get(i).setLastUpd(getSession().getUserId());
						//wfReplys.get(i).setLastUpdDate(new Date());
						new WfReplyFacade().confirm(wfReplys.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfReplys != null && wfReplys.size() > 0) {
					for(int i=0; i<wfReplys.size();i++) {
						if(wfReplys.get(i) != null) {
							//wfReplys.get(i).setLastUpd(getSession().getUserId());
							//wfReplys.get(i).setLastUpdDate(new Date());
							new WfReplyFacade().confirm(wfReplys.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfReply> wfReplys = new WfReplyFacade().find(wfReply);
			if(wfReplys != null && wfReplys.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"回复ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"问题编号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"用户ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"状态",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"创建人",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"最后更新",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"创建日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"更新日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"回复内容",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfReplys.size();i++) {
					row++;
					int m = 0;
					if(wfReplys.get(i).getReplyId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfReplys.get(i).getReplyId(),wcformat));
					m++;
					if(wfReplys.get(i).getQuesId() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfReplys.get(i).getQuesId(),wcformat));
					m++;
					if(wfReplys.get(i).getUserId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfReplys.get(i).getUserId(),wcformat));
					m++;
					if(wfReplys.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfReplys.get(i).getStatus(),wcformat));
					m++;
					if(wfReplys.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfReplys.get(i).getCreateBy(),wcformat));
					m++;
					if(wfReplys.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfReplys.get(i).getLastUpd(),wcformat));
					m++;
					if(wfReplys.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfReplys.get(i).getCreateDate(),wcformat));
					m++;
					if(wfReplys.get(i).getLastUpdDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfReplys.get(i).getLastUpdDate(),wcformat));
					m++;
					if(wfReplys.get(i).getReplyText() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfReplys.get(i).getReplyText(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfReplyListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfReplys != null && wfReplys.size() > 0) {
				for(int i=0; i<wfReplys.size();i++) {
					if(wfReplys.get(i) != null) {
						//wfReplys.get(i).setLastUpd(getSession().getUserId());
						//wfReplys.get(i).setLastUpdDate(new Date());
						new WfReplyFacade().confirm(wfReplys.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfReplys != null && wfReplys.size() > 0) {
				for(int i=0; i<wfReplys.size();i++) {
					if(wfReplys.get(i) != null) {
						//wfReplys.get(i).setLastUpd(getSession().getUserId());
						//wfReplys.get(i).setLastUpdDate(new Date());
						new WfReplyFacade().confirm(wfReplys.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfReply> getWfReplys() {
		return wfReplys;
	}
	public void setWfReplys(List<WfReply> wfReplys) {
		this.wfReplys = wfReplys;
	}
	public WfReply getWfReply() {
		return wfReply;
	}
	public void setWfReply(WfReply wfReply) {
		this.wfReply = wfReply;
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
	public java.util.List<gnwf.vo.WfQues> getWfQuess() {
		return wfQuess;
	}
	public void setWfQuess(java.util.List<gnwf.vo.WfQues> wfQuess){
		this.wfQuess = wfQuess;
	}
	public WfQues getWfQues() {
		return wfQues;
	}
	public void setWfQues(WfQues wfQues) {
		this.wfQues = wfQues;
	}
	public void addtoWfQuess(gnwf.vo.WfQues wfQues){
		if(getWfQuess() == null) setWfQuess(new java.util.ArrayList<gnwf.vo.WfQues>());
			getWfQuess().add(wfQues);
	}


	public String getSyId() {
		return syId;
	}


	public void setSyId(String syId) {
		this.syId = syId;
	}
	
	public String getUsrId() {
		return usrId;
	}


	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}


	public String getSyNm() {
		return syNm;
	}


	public void setSyNm(String syNm) {
		this.syNm = syNm;
	}


	public String getPrjtNo() {
		return prjtNo;
	}


	public void setPrjtNo(String prjtNo) {
		this.prjtNo = prjtNo;
	}


	public String getWfId() {
		return wfId;
	}


	public void setWfId(String wfId) {
		this.wfId = wfId;
	}


	public String getWfNm() {
		return wfNm;
	}


	public void setWfNm(String wfNm) {
		this.wfNm = wfNm;
	}


	public String getUsrNm() {
		return usrNm;
	}


	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}


	public String getPrjtNm() {
		return prjtNm;
	}


	public void setPrjtNm(String prjtNm) {
		this.prjtNm = prjtNm;
	}


	public int getReload() {
		return reload;
	}


	public void setReload(int reload) {
		this.reload = reload;
	}


	public String getTempParams() {
		return tempParams;
	}


	public void setTempParams(String tempParams) {
		this.tempParams = tempParams;
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


	public int getWfRepLyType() {
		return wfRepLyType;
	}


	public void setWfRepLyType(int wfRepLyType) {
		this.wfRepLyType = wfRepLyType;
	}
	public Gp getGp() {
		return gp;
	}
	public void setGp(Gp gp) {
		this.gp = gp;
	}

}