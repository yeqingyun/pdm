package gnwf.ww.json;

import gnwf.dao.WfRiskDAO;
import gnwf.facade.WfCfgFacade;
import gnwf.facade.WfRdFacade;
import gnwf.facade.WfRdRiskFacade;
import gnwf.facade.WfRdTaskFacade;
import gnwf.facade.WfRiskFacade;
import gnwf.facade.WfRiskReplyFacade;
import gnwf.facade.WfStepFacade;
import gnwf.parser.ExcelContext;
import gnwf.parser.ExcelFormatIncorrectException;
import gnwf.vo.WfCfg;
import gnwf.vo.WfRd;
import gnwf.vo.WfRdRisk;
import gnwf.vo.WfRdTask;
import gnwf.vo.WfRisk;
import gnwf.vo.WfRiskReply;
import gnwf.vo.WfStep;
import gnwf.vo.json.WfRiskJson;
import gnwf.ww.MSG;
import gnwf.ww.workflow.WFUtil;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;
import org.apache.tools.ant.util.DateUtils;

import zrprjt.facade.PrjtDefFacade;
import zrprjt.facade.PrjtUsrFacade;
import zrprjt.facade.SchCfgFacade;
import zrprjt.vo.PrjtDef;
import zrprjt.vo.PrjtUsr;
import zrprjt.vo.SchCfg;
import zrsy.facade.AddrBookFacade;
import zrsy.facade.UsrFacade;
import zrsy.vo.AddrBook;
import zrsy.vo.Gp;
import zrsy.vo.Usr;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;

public class WfRiskAction extends gnwf.ww.BasicAction {
	//private static final Logger log = Logger.getLogger(WfRiskAction.class);
	private static final long serialVersionUID = 1L;
	
	private List<SchCfg> schCfgs;
	private WfRisk wfRisk;
	private String syId;
	private String syNm;
	private String usrId;
	private String usrNm;
	private String prjtNo;
	private String responsibleUID;//责任人的ID
	private String tempParams;
	private String fileNo;
	private String fileName;
	private String impQuesIds;
	private Integer reload = 0;
	private String riskIds;
	private String wfNo;
	private Gp gp;
	private String choices;
	private WritableWorkbook workbook;
	private File fileInp;
	private String action;
	private String mailUsrId;
	private List<WfRisk> wfRiskList;
	
	private String				isReloadGrid;		//是否更新父页面grid
	
	public String getIsReloadGrid() {
		return isReloadGrid;
	}

	public void setIsReloadGrid(String isReloadGrid) {
		this.isReloadGrid = isReloadGrid;
	}

	/**
	 * 风险列表页面
	 * @return
	 */
	public String riskListUI() {
		try {
			schCfgs = new SchCfgFacade().find("select " + SchCfg.SELF_FIELDS + " from SchCfg", SchCfg.SELF_FIELDS);
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRiskAction Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return "riskList";
	}
	
	public String wfRdRiskListUI() {
		try {
			schCfgs = new SchCfgFacade().find("select " + SchCfg.SELF_FIELDS + " from SchCfg", SchCfg.SELF_FIELDS);
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRiskAction Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return "wfRdRiskList";
	}
	
	/**
	 * 查询风险
	 * @return
	 * @throws Exception
	 */
	public String riskList() throws Exception {
		try {
			int total = getQueryRiskList();
			WfRiskJson wfRiskJson = new WfRiskJson();
			wfRiskJson.Rows = wfRiskList;
			wfRiskJson.Total = total;
			System.out.println("··········"+JSON.toJSONString(wfRiskJson));
			setJson(JSON.toJSONString(wfRiskJson));
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesAction Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return "app";
	}
	
	
	private int getQueryRiskList() throws Exception {
		if(this.getWfRisk() == null) {
			this.wfRisk = new WfRisk();
		}
		int total = 0;
		if(wfNo == null || "".equals(wfNo.trim())) {
			if (wfRisk.getPrjtNo() == null || wfRisk.getPrjtNo().equals("ALL") || Utils.StringUtils.isEmpty(wfRisk.getPrjtNo())) {
				setPrjtNoList();
				wfRisk.setPrjtNo(null);
			}
			total = new WfRiskFacade().amount(wfRisk);
			if (getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfRiskList = new WfRiskFacade().find(wfRisk, getPageVO());
		}else {
			total = new WfRiskFacade().amount("select count(*) as amount from wfrisk risk left join wfrdrisk rdrisk on risk.riskid = rdrisk.riskid"
												 + " left join wfrd rd on rd.wfno = rdrisk.wfno where rd.wfno = '" + wfNo + "'");
			if (getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			String sql = " from wfrisk wfrisk left join wfrdrisk rdrisk on wfrisk.riskid = rdrisk.riskid"
                     	+ " left join wfrd rd on rd.wfno = rdrisk.wfno where rd.wfno = '" + wfNo + "'";
			if(null != wfRisk.getRiskId())
				sql += " and WfRisk.RiskId = '"+wfRisk.getRiskId()+"'";
			if(null != wfRisk.getRiskCategory() && !"".equals(wfRisk.getRiskCategory()))
				sql += " and WfRisk.RiskCategory LIKE '%"+wfRisk.getRiskCategory()+"%'";
			if(null != wfRisk.getExecutionDate() && !"".equals(wfRisk.getExecutionDate()))
				sql += " and WfRisk.ExecutionDate LIKE '%"+wfRisk.getExecutionDate()+"%'";
			if(null != wfRisk.getDeptName() && !"".equals(wfRisk.getDeptName()))
				sql += " and WfRisk.DeptName LIKE '%"+wfRisk.getDeptName()+"%'";
			if(null != wfRisk.getRiskConsequence() && !"".equals(wfRisk.getRiskConsequence()))
				sql += " and WfRisk.RiskConsequence LIKE '%"+wfRisk.getRiskConsequence()+"%'";
			if(null != wfRisk.getResponsibleUserName() && !"".equals(wfRisk.getResponsibleUserName()))
				sql += " and WfRisk.ResponsibleUserName LIKE '%"+wfRisk.getResponsibleUserName()+"%'";
			wfRiskList = new WfRiskFacade().find(sql, getPageVO());
		}
		return total;
	}
	private void setPrjtNoList() throws Exception {
		Boolean selAllPrjts = false;
		for (Gp e : getUsrSession().getGps()) {
			if (e.getGpName().indexOf("超级用户") > -1) {
				selAllPrjts = true;
				break;
			} else if (e.getGpName().indexOf("不随项目变更角色") > -1) {
				selAllPrjts = true;
				break;
			}
		}
		String sql = null;
		if (selAllPrjts) {
			sql = "select PrjtDef.PrjtNo from PrjtDef where (TypId = ( select TypId from PrjtTyp where TypNm  = '研发管理') )  order by Status asc , CreateDate desc";
		} else {
			sql = "select PrjtDef.PrjtNo from PrjtDef where (PrjtNo in ( select PrjtNo from PrjtUsr where UsrId  = " + getUsrSession().getId() + ") ) or CreateBy = " + getUsrSession().getId() + " order by Status asc , CreateDate desc";
		}
		List<PrjtDef> prjtDefs2 = new PrjtDefFacade().find(sql, "PrjtDef.PrjtNo");
		String prjtNoList = "";
		for (PrjtDef e : prjtDefs2) {
			prjtNoList += ",'" + e.getPrjtNo() + "'";
		}
		prjtNoList = prjtNoList.substring(1, prjtNoList.length());
		wfRisk.setPrjtNoList(prjtNoList);
	}
	/**
	 * 添加风险页面
	 * @return
	 */
	public String addRiskUI() {
		try {
			if (wfRisk != null && wfRisk.getRiskId() != null) {
				wfRisk = new WfRiskFacade().findById(wfRisk);
			}
			syId = String.valueOf(getUsrSession().getSyId());
			syNm = getUsrSession().getSyNm();
			usrId = String.valueOf(getUsrSession().getId());
			usrNm = getUsrSession().getUsrName();
			//usrs = new zrsy.facade.UsrFacade().find("select " + zrsy.vo.Usr.SELF_FIELDS + " from Usr", zrsy.vo.Usr.SELF_FIELDS);
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesAction Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return "addRisk";
	}
	/**
	 * 选择责任人
	 * @return
	 * @throws Exception
	 */
	public String selcResponsible() throws Exception {
		return "selcResponsible";
	}
	/**
	 * 保存风险
	 * @return
	 * @throws Exception
	 */
	public String saveRisk() throws Exception {
		try{
			isReloadGrid = "true";
			wfRisk.setStatus(MSG.WFRISK_STATUS_1);
			wfRisk.setCreateUserId(getUsrSession().getId());
			wfRisk.setCreateDate(new Date());
			wfRisk.setLastUpdateUserId(getUsrSession().getId());
			wfRisk.setLastUpdate(new Date());
			wfRisk.setResponsibleUserId(responsibleUID);
			String riskId = new WfRiskFacade().save(wfRisk);
			setMsg(MSG.S_SAV + ":" + riskId);
		}catch(Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfRiskAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	/**
	 * 上传风险附件
	 */
	public void afterUploadFile() {
		try {
			String riskId = null;
			setFileName(URLDecoder.decode(getFileName(), "UTF-8"));
			setTempParams(URLDecoder.decode(getTempParams(), "UTF-8"));
			if (getFileNo() != null) {
				String sql = null;
				if (getTempParams() != null) {
					String ss[] = getTempParams().split(":");
					if (ss[0].equals("wfRisk.riskId")) {
						riskId = ss[1];
						sql = "update WfRisk set FileName='" + getFileName() + "', FileNo = '" + getFileNo() + "', LastUpdateUserId=" + usrId + ", LastUpdate ='" + DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss") + "' where RiskId=" + riskId;
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
	/**
	 * 风险管理界面
	 * @return
	 * @throws Exception
	 */
	public String managerRisk() throws Exception {
		try {
			syId = String.valueOf(getUsrSession().getSyId());
			syNm = getUsrSession().getSyNm();
			usrId = String.valueOf(getUsrSession().getId());
			usrNm = getUsrSession().getUsrName();
			wfRisk = new WfRiskFacade().findBy(wfRisk);
			List<WfRiskReply> riskReplyList = new ArrayList<WfRiskReply>();
			WfRiskReply wfRiskReply = new WfRiskReply();
			wfRiskReply.setRiskId(wfRisk.getRiskId());
			riskReplyList = new WfRiskReplyFacade().findAll(wfRiskReply);
			wfRisk.setWfRiskReplyList(riskReplyList);
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error("managerRisk WfRiskAction Exception", e);
			return ERROR;
		}
		return "managerRisk";
	}
	/**
	 * 转交风险责任人
	 * @return
	 * @throws Exception
	 */
	public String updateResponsibleUser()throws Exception {
		try {
			if(responsibleUID != null && !"".equals(responsibleUID.trim())) {
				WfRiskFacade wf = new WfRiskFacade();
				wf.update(this.wfRisk);
				this.wfRisk = wf.findById(wfRisk);
				String[] responsibleUserIds = responsibleUID.split(",");
				WfRiskReplyFacade wrf = new WfRiskReplyFacade();
				String riskReplySql = "select Id,ResponsibleUserId from WfRiskReply where riskId = " + this.wfRisk.getRiskId();
				List<WfRiskReply> wfRiskReplyList = wrf.find(riskReplySql, "WfRiskReply.Id,WfRiskReply.ResponsibleUserId");
				if(wfRiskReplyList != null && wfRiskReplyList.size() > 0) {
					StringBuffer deleteReplySql = new StringBuffer("delete from WfRiskReply where Id in (");
					StringBuffer ids = new StringBuffer();
					for(WfRiskReply wr : wfRiskReplyList) {
						ids.append(wr.getId()).append(",");
					}
					ids.deleteCharAt(ids.length() - 1);
					deleteReplySql.append(ids).append(")");
					wrf.remove(deleteReplySql.toString());
				}
				String wfRiskReplySqlId = "select max(Id) as maxid from WfRiskReply";
				WfRiskDAO wd = new WfRiskDAO();
				int wfRiskReplyId = wd.getMaxId(wfRiskReplySqlId) + 1;
				for(String rId : responsibleUserIds) {
					WfRiskReply wfRiskReply = new WfRiskReply();
					wfRiskReply.setId(wfRiskReplyId);
					wfRiskReply.setResponsibleUserId(Integer.valueOf(rId));
					wfRiskReply.setRiskId(wfRisk.getRiskId());
					wfRiskReply.setStatus(1);
					wfRiskReply.setCreateUserId(this.wfRisk.getCreateUserId());
					wfRiskReply.setCreateDate(new Date());
					wrf.save(wfRiskReply);
					wfRiskReplyId++;
				}
				setMsg(MSG.S_SAV);
			}else {
				setMsg(MSG.F_SAV);
			}
		} catch (Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfRiskAction updateResponsibleUser Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return MESSAGE;
	}
	
	/**
	 * 启动风险流程
	 * @return
	 */
	public String goRisk() {
		try {
			if(riskIds != null && !"".equals(riskIds.trim())) {
				riskIds=riskIds.replace(",", "','");
				riskIds = "'"+riskIds+"'";
				WfRiskFacade wrf = new WfRiskFacade();
				List<WfRisk> wfriskList = wrf.find("select " + WfRisk.LIST_FIELDS + " from WfRisk where RiskId in (" + riskIds + ")",WfRisk.LIST_FIELDS);
				for(WfRisk wr : wfriskList) {
					if(wr.getStatus() != 1) {
						setMsg("只能启动未评估的风险！");
						return ERROR;
					}
				}
				int count = wrf.amount("select count(distinct PrjtNo) as amount from Wfrisk where RiskId in (" + riskIds + ")");
				if(count == 1) {
					SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
					String updateRiskSql = "update WfRisk set Status = 2,LastUpdate = '" + sdf.format(new Date()) + "',LastUpdateUserId = " 
							+ getUsrSession().getId() + " where RiskId in (" + riskIds + ")";
					wrf.update(updateRiskSql);
					
					// 新增一条走风险流程
					WfRd wfRd = new WfRd();
					StringBuffer wfName = new StringBuffer("项目《" + wfriskList.get(0).getPrjtNm() + "》走风险流程");
					wfRd.setWfName(wfName.toString());
					wfRd.setProjectNo(wfriskList.get(0).getPrjtNo());
					wfRd.setFlowId(41);
					wfRd.setStatus(MSG.CONST_STATUS_1);
					wfRd.setCreateBy(getUsrSession().getId());
					wfRd.setCreateDate(new Date());
					WfCfg wc = new WfCfg();
					wc.setFlowId(wfRd.getFlowId());
					wc = new WfCfgFacade().findById(wc);
					if(wc != null){
						wfRd.setNeedQues(wc.getNeedQues());
					}
					new WfRdFacade().save(wfRd);

					// 发第一个任务给项目经理、跳转回任务页面
					PrjtUsr pu = new PrjtUsrFacade().findById("select " + PrjtUsr.SELF_FIELDS + " from PrjtUsr PrjtUsr join PrjtRole pr on PrjtUsr.roleid = pr.roleid "
							+ "where PrjtUsr.prjtNo = '" + wfriskList.get(0).getPrjtNo() + "' and pr.roleNm = '项目经理'", PrjtUsr.SELF_FIELDS);
					WfRdTask task = new WfRdTask();
					task.setCreateBy(-1);
					task.setCreateDate(new Date());
					task.setReqDate(new Date());
					task.setAcceptBy(pu.getUsrId());
					task.setAcceptDate(new Date());
					task.setStatus(MSG.OWFTASK_STATUS_1);
					task.setTaskType(MSG.OWFTASK_TYPE_1);
					task.setWfNo(wfRd.getWfNo());

					WfStep step = new WfStep();
					step.setFlowId(wfRd.getFlowId());
					step.setSort(1);
					step = new WfStepFacade().findBy(step);
					if (step != null) {
						task.setStepId(step.getStepId());
						new WfRdTaskFacade().save(task);
					}
					WfRdRiskFacade rrf = new WfRdRiskFacade();
					for(WfRisk wr : wfriskList) {
						WfRdRisk rr = new WfRdRisk();
						rr.setWfNo(wfRd.getWfNo());
						rr.setRiskId(wr.getRiskId());
						rrf.save(rr);
					}
					String task1SQl = "select" +WfRdTask.SELF_FIELDS+  "from WfRdTask   where WfRdTask.WfNo = '" + wfRd.getWfNo() + "' and WfRdTask.StepId = '" + step.getStepId() + "'";
					System.out.println(task1SQl);
					WfRdTask task1 = new WfRdTaskFacade().findById(task1SQl, WfRdTask.SELF_FIELDS);
					//System.out.println("--------task1--------"+task1.getTaskId()+"----wfRd------"+wfRd.getWfNo()+"-----step-----"+step.getStepId());
					setMsg("wfRd:"+ wfRd.getWfNo() +",taskID():"+task1.getTaskId() +",StepId():"+step.getStepId()+ ",message:启动风险成功");
					//setMsg("启动风险成功");
				}else {
					setMsg("必须在同一项目下的风险才能启动风险流程");
				}
			}else {
				setMsg("启动风险失败");
			}
		} catch (Exception e) {
			setMsg("启动风险失败");
			Logger.getLogger(this.getClass()).error("WfRiskAction goRisk Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return MESSAGE;
	}
	/**
	 * 邮件通知项目组成员提风险
	 * @return
	 */
	public String sendMail() {
		try {
			PrjtDef prjtDef = new PrjtDef();
			prjtDef.setPrjtNo(prjtNo);
			prjtDef = new PrjtDefFacade().findById(prjtDef);
			String[] userIds = mailUsrId.split(",");
			List<Usr> usrList = new ArrayList<Usr>();
			for(String userId : userIds) {
				Usr u = new Usr();
				u.setId(Integer.valueOf(userId));
				usrList.add(u);
			}
			PrjtUsr pu = new PrjtUsrFacade().findById("select PrjtUsr.UsrId from PrjtUsr left join PrjtRole "
					+ "on PrjtUsr.RoleId = PrjtRole.RoleId where PrjtRole.RoleNm = '项目经理'"
					+ " and PrjtUsr.PrjtNo = '" + prjtNo + "'", "PrjtUsr.UsrId");
			AddrBook addrBook = new AddrBookFacade().findById("select AddrBook.UserId,AddrBook.MailAddr from AddrBook "
					+ "where AddrBook.UserId = " + pu.getUsrId(), "AddrBook.UserId,AddrBook.MailAddr");
			String title = "项目《" + prjtDef.getPrjtNm() + "》-收集风险通知";
			String content = "尊敬的同事，您好：<p>此项目需要您提出风险。</p>请登入PDM研发管理系统，风险管理模块进行添加风险，登入地址为:" 
			                 + " ： <a href=" + getAppcationURL() +  ">" + getAppcationURL() + "</a>";
			WFUtil.sendMailByUser(addrBook.getUserId(),addrBook.getMailAddr(),title, content, usrList);
			setMsg("发送成功！");
		} catch (Exception e) {
			setMsg("邮件发送失败");
			Logger.getLogger(this.getClass()).error("WfRiskAction sendMail Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return MESSAGE;
	}
	
	/**
	 * 导出风险
	 * @return
	 * @throws Exception
	 */
	public String export() throws Exception {
		List<WfRisk> wfRisks = null;
		try {
			if(choices != null) {
				choices=choices.replace(",", "','");
				choices = "'"+choices+"'";
				String sql = "select " + WfRisk.SELF_FIELDS + " from WfRisk where RiskId in (" + this.choices + ")";
				wfRisks = new WfRiskFacade().find(sql,WfRisk.SELF_FIELDS);
			}else {
				ActionContext ctx = ActionContext.getContext();
				HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
				String responsibleUserName = new String(request.getParameter("responsibleUserName").getBytes("iso8859-1"),"utf-8");
				String riskCategory = new String(request.getParameter("riskCategory").getBytes("iso8859-1"),"utf-8");
				String deptName = new String(request.getParameter("deptName").getBytes("iso8859-1"),"utf-8");
				
				if(wfNo != null && !"".equals(wfNo)) {
					String sql = "select " + WfRisk.LIST_FIELDS + " from WfRisk join WfRdRisk on WfRisk.RiskId = WfRdRisk.RiskId "
							+ "join WfRd on WfRdRisk.WfNo = WfRd.WfNo where WfRd.WfNo = '" + wfNo + "'";
					if(responsibleUserName != null && !"".equals(responsibleUserName)){
						sql += " and WfRisk.responsibleUserName like '%" + responsibleUserName + "%'";
					}
					if(riskCategory != null && !"".equals(riskCategory)){
						sql += " and WfRisk.riskCategory like '%" + riskCategory + "%'";
					}
					if(deptName != null && !"".equals(deptName)){
						sql += " and WfRisk.deptName like '%" + deptName + "%'";
					}
					wfRisks = new WfRiskFacade().find(sql, WfRisk.LIST_FIELDS);
					
					
				}else {
					if(responsibleUserName != null && !"".equals(responsibleUserName))wfRisk.setResponsibleUserName(responsibleUserName);
					if(riskCategory != null && !"".equals(riskCategory))wfRisk.setRiskCategory(riskCategory);
					if(deptName != null && !"".equals(deptName))wfRisk.setDeptName(deptName);
					wfRisks = new WfRiskFacade().findAll(wfRisk);
				}
			}
			
			WritableCellFormat wcformat = new WritableCellFormat();
			wcformat.setAlignment(jxl.format.Alignment.CENTRE);
			wcformat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			wcformat.setBorder(Border.LEFT, BorderLineStyle.THIN);
			wcformat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			wcformat.setBorder(Border.TOP, BorderLineStyle.THIN);
			wcformat.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			wcformat.setWrap(true);
			OutputStream os = getOutputStream();
			workbook = Workbook.createWorkbook(os);
			WritableSheet ws = workbook.createSheet("sheet0", 0);
			int index = 0;

			ws.addCell(new Label(index, 0, "风险编号", wcformat));
			ws.setColumnView(index, 20);
			index++;
			ws.addCell(new Label(index, 0, "提出部门", wcformat));
			ws.setColumnView(index, 20);
			index++;
			ws.addCell(new Label(index, 0, "风险类别", wcformat));
			ws.setColumnView(index, 20);
			index++;
			ws.addCell(new Label(index, 0, "风险状态", wcformat));
			ws.setColumnView(index, 20);
			index++;
			ws.addCell(new Label(index, 0, "问题描述", wcformat));
			ws.setColumnView(index, 20);
			index++;
			ws.addCell(new Label(index, 0, "风险说明及风险后果", wcformat));
			ws.setColumnView(index, 20);
			index++;
			ws.addCell(new Label(index, 0, "拟采取的预防措施", wcformat));
			ws.setColumnView(index, 20);
			index++;
			ws.addCell(new Label(index, 0, "拟导入时间", wcformat));
			ws.setColumnView(index, 20);
			index++;
			/*ws.addCell(new Label(index, 0, "建议措施", wcformat));
			ws.setColumnView(index, 20);
			index++;*/
			
			ws.addCell(new Label(index, 0, "责任人", wcformat));
			ws.setColumnView(index, 20);
			index++;
			ws.addCell(new Label(index, 0, "风险监控结果", wcformat));
			ws.setColumnView(index, 20);
			index++;
			ws.addCell(new Label(index, 0, "确认时间", wcformat));
			ws.setColumnView(index, 20);
			index++;
			ws.addCell(new Label(index, 0, "项目名称", wcformat));
			ws.setColumnView(index, 20);
			index++;
			if (wfRisks != null && wfRisks.size() > 0) {
				int row = 0;
				for (int i = 0; i < wfRisks.size(); i++) {
					row++;
					int m = 0;
					ws.addCell(new jxl.write.Label(m, row, wfRisks.get(i).getRiskId().toString(), wcformat));
					m++;
					if (wfRisks.get(i).getDeptName() != null) ws.addCell(new jxl.write.Label(m, row, wfRisks.get(i).getDeptName(), wcformat));
					m++;
					if (wfRisks.get(i).getRiskCategory() != null) ws.addCell(new jxl.write.Label(m, row, wfRisks.get(i).getRiskCategory(), wcformat));
					m++;
					
					if (wfRisks.get(i).getStatus() != null) 
						if (wfRisks.get(i).getStatus() ==1 ||wfRisks.get(i).getStatus() ==2 || wfRisks.get(i).getStatus() ==3) {
							ws.addCell(new jxl.write.Label(m, row, "OPEN", wcformat));
						} else if (wfRisks.get(i).getStatus() ==4) {
							ws.addCell(new jxl.write.Label(m, row, "作废", wcformat));
						}else if (wfRisks.get(i).getStatus() ==5) {
							ws.addCell(new jxl.write.Label(m, row, "CLOSE", wcformat));
						}
						
					m++;
					
					if (wfRisks.get(i).getDescription() != null) ws.addCell(new jxl.write.Label(m, row, wfRisks.get(i).getDescription(), wcformat));
					m++;
					
					if (wfRisks.get(i).getRiskConsequence() != null) ws.addCell(new jxl.write.Label(m, row, wfRisks.get(i).getRiskConsequence(), wcformat));
					m++;
					if (wfRisks.get(i).getPreventiveMeasures() != null) ws.addCell(new jxl.write.Label(m, row, wfRisks.get(i).getPreventiveMeasures(), wcformat));
					m++;
					if (wfRisks.get(i).getImpTime() != null) 
						ws.addCell(new jxl.write.Label(m, row, (wfRisks.get(i).getImpTime()).toString(), wcformat));
					m++;
					/*if (wfRisks.get(i).getRiskText() != null) ws.addCell(new jxl.write.Label(m, row, wfRisks.get(i).getRiskText(), wcformat));
					m++;
					if(wfRisks.get(i).getExecutionDate() != null)ws.addCell(new jxl.write.Label(m, row, wfRisks.get(i).getExecutionDate(), wcformat));
					m++;*/
					if (wfRisks.get(i).getResponsibleUserName() != null) ws.addCell(new jxl.write.Label(m, row, wfRisks.get(i).getResponsibleUserName(), wcformat));
					m++;
					if (wfRisks.get(i).getRiskMonitor() != null) ws.addCell(new jxl.write.Label(m, row, wfRisks.get(i).getRiskMonitor(), wcformat));
					m++;
					if(wfRisks.get(i).getExecutionDate() != null)ws.addCell(new jxl.write.Label(m, row, wfRisks.get(i).getExecutionDate(), wcformat));
					m++;
					
					if(wfRisks.get(i).getPrjtNo() != null)
						{PrjtDef prjtDef = new PrjtDef();
						prjtDef.setPrjtNo(wfRisks.get(i).getPrjtNo());
						PrjtDef pj= new PrjtDefFacade().findById(prjtDef);
						ws.addCell(new jxl.write.Label(m, row, pj.getPrjtNm(), wcformat)); m++;}
					
				}
			}
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error("WfRiskAction export Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return EXCEL;
	}
	
	/**
	 * 导入风险
	 * @return
	 */
	public String impRisk(){
		boolean flag = true;
		try {
			if(fileInp != null) {
				//WfRiskExcelParser parser = new WfRiskExcelParser();
				//parser.setFile(fileInp);
				ExcelContext<WfRisk> context = new ExcelContext<>(new gnwf.parser.WfRiskExcelParser());
				List<WfRisk> wfRiskList = context.parser(fileInp.getAbsolutePath());
				if(wfRiskList.size() == 0) {
					setMsg("风险Excel模板格式不正确或数据为空");
				}else {
					List<WfRisk> saveOrUpdate = new ArrayList<WfRisk>();
					for(WfRisk risk : wfRiskList) {
						if( risk.getResponsibleUserName() == null) {
							continue;
						}
						System.out.println(risk.getResponsibleUserName());
						if(risk.getRiskId() == null) {
							String[] userNameArray = risk.getResponsibleUserName().replaceAll(" ", "").replace("，",",").split(",");
							StringBuffer ids = new StringBuffer();
							for (int i = 0; i < userNameArray.length; i++) {
								System.out.println(userNameArray[i]);
								List<Usr> usrList = new UsrFacade().find("select Usr.Id from Usr where UsrName = '"+ userNameArray[i] + "'", "Usr.Id");
									//List<Usr> usrList = new zrsy.facade.UsrFacade().find("select distinct Usr.Id from Usr join prjtUsr on Usr.id = prjtUsr.UsrId where Usr.UsrName = '" + userNameArray[i] + "' and ( prjtUsr.PrjtNo = '" + prjtNo + "' or prjtUsr.PrjtNo is null)", "Usr.Id");
									for (Usr usr : usrList) {
										ids.append(usr.getId()).append(",");
									}
							}
							if(ids != null && ids.length()>0) {
								ids = ids.deleteCharAt(ids.length() - 1);
								System.out.println(ids.toString());
								List<PrjtUsr> puList = new PrjtUsrFacade().find("select PrjtUsr.UsrId from PrjtUsr where"
												+ "( prjtUsr.PrjtNo = '" + prjtNo + "' or prjtUsr.PrjtNo is null)"
												+ " and UsrId in (" + ids.toString()
												+ ")", "PrjtUsr.UsrId");
								
								if (puList != null && puList.size() > 0) {
									//risk.setResponsibleUserId(String.valueOf(puList.get(0).getUsrId()));
									risk.setResponsibleUserId(ids.toString());
								} else {
									setMsg("责任人:" + risk.getResponsibleUserName() + "不在此项目组");
									flag = false;
									break;
								}
							}else {
								setMsg("责任人：" + risk.getResponsibleUserName() + "不正确");
								flag = false;
								break;
							}
							 
							risk.setResponsibleUserName(risk.getResponsibleUserName());
							risk.setDeptName(risk.getDeptName());
							risk.setRiskCategory(risk.getRiskCategory());
							risk.setRiskConsequence(risk.getRiskConsequence());
							risk.setPreventiveMeasures(risk.getPreventiveMeasures());
							risk.setImpTime(risk.getImpTime());
							risk.setPrjtNo(prjtNo);
							risk.setStatus(MSG.WFRISK_STATUS_1);
							risk.setCreateDate(new Date());
							risk.setCreateUserId(getUsrSession().getId());
							risk.setLastUpdate(new Date());
							risk.setLastUpdateUserId(getUsrSession().getId());
							saveOrUpdate.add(risk);
						}else {
							WfRisk wr = new WfRisk();
							wr.setRiskId(risk.getRiskId());
							wr.setRiskText(risk.getRiskText());
							wr.setRiskMonitor(risk.getRiskMonitor());
							wr.setLastUpdate(new Date());
							wr.setLastUpdateUserId(getUsrSession().getId());
							//new WfRiskFacade().update(wr);
							saveOrUpdate.add(wr);
						}
					}
					if(flag) {
						new WfRiskFacade().saveOrUpdate(saveOrUpdate);
						setMsg("更新完成" + saveOrUpdate.size() + "条记录");
					}
					return MESSAGE;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			setMsg("IO异常");
			Logger.getLogger(this.getClass()).error("WfQuesAction impRisk Exception", e);
		} catch (ExcelFormatIncorrectException e) {
			e.printStackTrace();
			setMsg("文件不是Excel格式");
			Logger.getLogger(this.getClass()).error("WfQuesAction impRisk Exception", e);
		}catch (Exception e) {
			e.printStackTrace();
			setMsg("数据库访问异常");
			Logger.getLogger(this.getClass()).error("WfQuesAction impRisk Exception", e);
		}
		action = "WfRisk!impRisk.shtml";
		return PGUPL;
	}
	/**
	 * 作废风险
	 * @return
	 */
	public String invalidRisk() {
		try {
			riskIds=riskIds.replace(",", "','");
			riskIds = "'"+riskIds+"'";
			new WfRiskFacade().invalidRisk(riskIds,getUsrSession().getId());
			setMsg("作废成功！");
		} catch (Exception e) {
			setMsg("作废失败！");
			Logger.getLogger(this.getClass()).error("WfRiskAction invalidRisk Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return MESSAGE;
	}
	
	/**
	 * 关闭风险
	 * @return
	 */
	public String closeRisk() {
		try {
			riskIds=riskIds.replace(",", "','");
			riskIds = "'"+riskIds+"'";
			new WfRiskFacade().closeRisk(riskIds,getUsrSession().getId());
			setMsg("关闭成功！");
		} catch (Exception e) {
			setMsg("关闭失败！");
			Logger.getLogger(this.getClass()).error("WfRiskAction invalidRisk Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return MESSAGE;
	}
	/**
	 * 编辑页面
	 * @return
	 */
	public String editorRiskUI() {
		try {
			if (wfRisk != null && wfRisk.getRiskId() != null) {
				wfRisk = new WfRiskFacade().findById("select " + WfRisk.LIST_FIELDS 
						+ " from WfRisk where riskId = '" + wfRisk.getRiskId()+"'",WfRisk.LIST_FIELDS);
			}
			syId = String.valueOf(getUsrSession().getSyId());
			syNm = getUsrSession().getSyNm();
			usrId = String.valueOf(getUsrSession().getId());
			usrNm = getUsrSession().getUsrName();
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesAction editorRiskUI Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return "editorRiskUI";
	}
	
	/**
	 * 编辑风险
	 * @return
	 */
	public String editorRisk() {
		try {
			new WfRiskFacade().update(wfRisk);
			setMsg(MSG.S_SAV);
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesAction editorRisk Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return MESSAGE;
	}
	
	public String selectPrjtUser() {
		return "selcResponsible";
	}

	public List<SchCfg> getSchCfgs() {
		return schCfgs;
	}

	public void setSchCfgs(List<SchCfg> schCfgs) {
		this.schCfgs = schCfgs;
	}

	public WfRisk getWfRisk() {
		return wfRisk;
	}

	public void setWfRisk(WfRisk wfRisk) {
		this.wfRisk = wfRisk;
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

	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getUsrNm() {
		return usrNm;
	}

	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}

	public String getPrjtNo() {
		return prjtNo;
	}

	public void setPrjtNo(String prjtNo) {
		this.prjtNo = prjtNo;
	}

	public String getResponsibleUID() {
		return responsibleUID;
	}

	public void setResponsibleUID(String responsibleUID) {
		this.responsibleUID = responsibleUID;
	}

	public String getTempParams() {
		return tempParams;
	}

	public void setTempParams(String tempParams) {
		this.tempParams = tempParams;
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

	public String getImpQuesIds() {
		return impQuesIds;
	}

	public void setImpQuesIds(String impQuesIds) {
		this.impQuesIds = impQuesIds;
	}

	public Integer getReload() {
		return reload;
	}

	public void setReload(Integer reload) {
		this.reload = reload;
	}

	public String getRiskIds() {
		return riskIds;
	}

	public void setRiskIds(String riskIds) {
		this.riskIds = riskIds;
	}

	public String getWfNo() {
		return wfNo;
	}

	public void setWfNo(String wfNo) {
		this.wfNo = wfNo;
	}
	public Gp getGp() {
		return gp;
	}
	public void setGp(Gp gp) {
		this.gp = gp;
	}
	public String getChoices() {
		return choices;
	}
	public void setChoices(String choices) {
		this.choices = choices;
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
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getMailUsrId() {
		return mailUsrId;
	}
	public void setMailUsrId(String mailUsrId) {
		this.mailUsrId = mailUsrId;
	}

	public List<WfRisk> getWfRiskList() {
		return wfRiskList;
	}

	public void setWfRiskList(List<WfRisk> wfRiskList) {
		this.wfRiskList = wfRiskList;
	}
	
/*************************************android相关接口***********************************************/
	/**
	 * 获取风险管理列表
	 * @return
	 */
	public String androidRiskList() {
		try {
			int total = getQueryRiskList();
			setJson(getAndroidJson(this.wfRiskList, total, new String[]{"riskId","prjtNm","responsibleUserName",
									"riskCategory","status"}));
		} catch (Exception e) {
			setJson(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesAction androidRiskList Exception", e);
			e.printStackTrace();
		}
		return "app";
	}
	/**
	 * 查看风险详情
	 * @return
	 */
	public String androidRiskView() {
		try {
			this.wfRisk = new WfRiskFacade().findById("select " + WfRisk.LIST_FIELDS + " from WfRisk "
					+ "where WfRisk.RiskId = '" + this.wfRisk.getRiskId() + "'",WfRisk.LIST_FIELDS);
			this.wfRiskList = new ArrayList<WfRisk>();
			this.wfRiskList.add(wfRisk);
			String jsonArr = getAndroidJson(this.wfRiskList, null, new String[]{"prjtNm","createDate","createUserName",
					"status","riskCategory","deptName","responsibleUserName","responsibleUserId","executionDate","description",
					"riskConsequence","preventiveMeasures","impTime","riskMonitor"});
			setJson(JSONArray.parseArray(jsonArr).getJSONObject(0).toJSONString());
		} catch (Exception e) {
			setJson(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesAction androidRiskView Exception", e);
			e.printStackTrace();
		}
		return "app";
	}
	
	/**
	 * 保存和编辑
	 * @return
	 */
	public String androidsaveOrEditRisk() {
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			if(this.wfRisk.getRiskId() != null && !"".equals(this.wfRisk.getRiskId())) {
				wfRisk.setLastUpdateUserId(getUsrSession().getId());
				wfRisk.setLastUpdate(new Date());
				new WfRiskFacade().update(wfRisk);
			}else {
				wfRisk.setStatus(MSG.WFRISK_STATUS_1);
				wfRisk.setCreateUserId(getUsrSession().getId());
				wfRisk.setCreateDate(new Date());
				wfRisk.setLastUpdateUserId(getUsrSession().getId());
				wfRisk.setLastUpdate(new Date());
				new WfRiskFacade().save(wfRisk);
			}
			map.put("result",1);
			map.put("msg",MSG.S_UPD);
		}catch(Exception e) {
			e.printStackTrace();
			map.put("result",0);
			map.put("msg",MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfRiskAddAction androidsaveOrEditRisk Exception", e);
		}
		JSONObject jo = new JSONObject(map);
		setJson(jo.toJSONString());
		return "app";
	}
	
	/**
	 * 邮件通知项目组成员提风险
	 * @return
	 */
	public String androidSendMail() {
		Map<String, Object> map = new HashMap<String,Object>();
		try {
			PrjtDef prjtDef = new PrjtDef();
			prjtDef.setPrjtNo(this.wfRisk.getPrjtNo());
			prjtDef = new PrjtDefFacade().findById(prjtDef);
			String[] userIds = mailUsrId.split(",");
			List<Usr> usrList = new ArrayList<Usr>();
			for(String userId : userIds) {
				Usr u = new Usr();
				u.setId(Integer.valueOf(userId));
				usrList.add(u);
			}
			PrjtUsr pu = new PrjtUsrFacade().findById("select PrjtUsr.UsrId from PrjtUsr left join PrjtRole "
					+ "on PrjtUsr.RoleId = PrjtRole.RoleId where PrjtRole.RoleNm = '项目经理'"
					+ " and PrjtUsr.PrjtNo = '" + this.wfRisk.getPrjtNo() + "'", "PrjtUsr.UsrId");
			AddrBook addrBook = new AddrBookFacade().findById("select AddrBook.UserId,AddrBook.MailAddr from AddrBook "
					+ "where AddrBook.UserId = " + pu.getUsrId(), "AddrBook.UserId,AddrBook.MailAddr");
			String title = "项目《" + prjtDef.getPrjtNm() + "》-收集风险通知";
			String content = "尊敬的同事，您好：<p>此项目需要您提出风险。</p>请登入PDM研发管理系统，风险管理模块进行添加风险，登入地址为:" 
			                 + " ： <a href=" + getAppcationURL() +  ">" + getAppcationURL() + "</a>";
			WFUtil.sendMailByUser(addrBook.getUserId(),addrBook.getMailAddr(),title, content, usrList);
			map.put("result", 1);
			map.put("msg", "mail发送成功");
		} catch (Exception e) {
			map.put("result", 0);
			map.put("msg", "mail发送失败");
			Logger.getLogger(this.getClass()).error("WfRiskAction androidSendMail Exception", e);
			e.printStackTrace();
		}
		JSONObject jo = new JSONObject(map);
		setJson(jo.toJSONString());
		return "app";
	}
	
	/**
	 * 作废风险
	 * @return
	 */
	public String androidInvalidRisk() {
		Map<String, Object> map = new HashMap<String,Object>();
		WfRiskFacade facade = new WfRiskFacade();
		boolean flag = true;
		try {
			riskIds=riskIds.replace(",", "','");
			riskIds = "'"+riskIds+"'";
			List<WfRisk> wfRiskList = facade.find("select " + WfRisk.SELF_FIELDS + " from WfRisk where RiskId in ("
					+ riskIds + ")", WfRisk.SELF_FIELDS);
			for(WfRisk risk : wfRiskList) {
				if(risk.getStatus() != 1) {
					map.put("result", 0);
					map.put("msg", "作废失败,只能作废未评估的风险");
					flag = false;
					break;
				}
			}
			if(flag) {
				facade.invalidRisk(riskIds,getUsrSession().getId());
				map.put("result", 1);
				map.put("msg", "作废成功");
			}
		} catch (Exception e) {
			map.put("result", 0);
			map.put("msg", "作废失败");
			Logger.getLogger(this.getClass()).error("WfRiskAction androidInvalidRisk Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		JSONObject jo = new JSONObject(map);
		setJson(jo.toJSONString());
		return "app";
	}
	/**
	 * 启动风险流程
	 * @return
	 */
	public String androidGoRisk() {
		Map<String, Object> map = new HashMap<String,Object>();
		try {
			if(riskIds != null && !"".equals(riskIds.trim())) {
				riskIds=riskIds.replace(",", "','");
				riskIds = "'"+riskIds+"'";
				WfRiskFacade wrf = new WfRiskFacade();
				List<WfRisk> wfriskList = wrf.find("select " + WfRisk.LIST_FIELDS + " from WfRisk where RiskId in (" + riskIds + ")",WfRisk.LIST_FIELDS);
				for(WfRisk wr : wfriskList) {
					if(wr.getStatus() != 1) {
						map.put("result", 0);
						map.put("msg", "只能启动未评估的风险！");
						setJson(JSONObject.toJSONString(map));
						return "app";
					}
				}
				int count = wrf.amount("select count(distinct PrjtNo) as amount from Wfrisk where RiskId in (" + riskIds + ")");
				if(count == 1) {
					SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
					String updateRiskSql = "update WfRisk set Status = 2,LastUpdate = '" + sdf.format(new Date()) + "',LastUpdateUserId = " 
							+ getUsrSession().getId() + " where RiskId in (" + riskIds + ")";
					wrf.update(updateRiskSql);
					
					// 新增一条走风险流程
					WfRd wfRd = new WfRd();
					StringBuffer wfName = new StringBuffer("项目《" + wfriskList.get(0).getPrjtNm() + "》走风险流程");
					wfRd.setWfName(wfName.toString());
					wfRd.setProjectNo(wfriskList.get(0).getPrjtNo());
					wfRd.setFlowId(41);
					wfRd.setStatus(MSG.CONST_STATUS_1);
					wfRd.setCreateBy(getUsrSession().getId());
					wfRd.setCreateDate(new Date());
					WfCfg wc = new WfCfg();
					wc.setFlowId(wfRd.getFlowId());
					wc = new WfCfgFacade().findById(wc);
					if(wc != null){
						wfRd.setNeedQues(wc.getNeedQues());
					}
					new WfRdFacade().save(wfRd);

					// 发第一个任务给项目经理、跳转回任务页面
					PrjtUsr pu = new PrjtUsrFacade().findById("select " + PrjtUsr.SELF_FIELDS + " from PrjtUsr PrjtUsr join PrjtRole pr on PrjtUsr.roleid = pr.roleid "
							+ "where PrjtUsr.prjtNo = '" + wfriskList.get(0).getPrjtNo() + "' and pr.roleNm = '项目经理'", PrjtUsr.SELF_FIELDS);
					WfRdTask task = new WfRdTask();
					task.setCreateBy(-1);
					task.setCreateDate(new Date());
					task.setReqDate(new Date());
					task.setAcceptBy(pu.getUsrId());
					task.setAcceptDate(new Date());
					task.setStatus(MSG.OWFTASK_STATUS_1);
					task.setTaskType(MSG.OWFTASK_TYPE_1);
					task.setWfNo(wfRd.getWfNo());

					WfStep step = new WfStep();
					step.setFlowId(wfRd.getFlowId());
					step.setSort(1);
					step = new WfStepFacade().findBy(step);
					if (step != null) {
						task.setStepId(step.getStepId());
						new WfRdTaskFacade().save(task);
					}
					WfRdRiskFacade rrf = new WfRdRiskFacade();
					for(WfRisk wr : wfriskList) {
						WfRdRisk rr = new WfRdRisk();
						rr.setWfNo(wfRd.getWfNo());
						rr.setRiskId(wr.getRiskId());
						rrf.save(rr);
					}
					map.put("result", 1);
					map.put("msg", "启动风险成功！");
				}else {
					map.put("result", 0);
					map.put("msg", "必须在同一项目下的风险才能启动风险流程！");
				}
			}else {
				map.put("result", 0);
				map.put("msg", "启动风险失败！");
			}
		} catch (Exception e) {
			map.put("result", 0);
			map.put("msg", "启动风险失败！");
			Logger.getLogger(this.getClass()).error("WfRiskAction goRisk Exception", e);
			e.printStackTrace();
		}
		setJson(JSONObject.toJSONString(map));
		return "app";
	}
}