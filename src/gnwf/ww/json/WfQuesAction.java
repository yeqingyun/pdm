package gnwf.ww.json;

import gnwf.dao.WfPpReportDAO;
import gnwf.facade.QuesRespFacade;
import gnwf.facade.WfCfgFacade;
import gnwf.facade.WfQuesFacade;
import gnwf.facade.WfRdFacade;
import gnwf.facade.WfRdTaskFacade;
import gnwf.facade.WfRiskFacade;
import gnwf.facade.WfStepFacade;
import gnwf.parser.ExcelContext;
import gnwf.parser.ExcelFormatIncorrectException;
import gnwf.parser.WfQuesExcelParser;
import gnwf.vo.QuesResp;
import gnwf.vo.WfCfg;
import gnwf.vo.WfQues;
import gnwf.vo.WfRd;
import gnwf.vo.WfRdTask;
import gnwf.vo.WfRisk;
import gnwf.vo.WfStep;
import gnwf.vo.json.WfQuesJson;
import gnwf.vo.json.WfRiskJson;
import gnwf.ww.BasicAction;
import gnwf.ww.MSG;
import gnwf.ww.workflow.WFCenter;
import gnwf.ww.workflow.WFUtil;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;













import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tools.ant.util.DateUtils;
import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;

import zrprjt.facade.PrjtDefFacade;
import zrprjt.facade.PrjtUsrFacade;
import zrprjt.facade.SchCfgFacade;
import zrprjt.facade.UsrFacade;
import zrprjt.vo.Dept;
import zrprjt.vo.PrjtDef;
import zrprjt.vo.PrjtUsr;
import zrprjt.vo.SchCfg;
import zrsy.vo.Gp;
import zrsy.vo.Usr;
import Utils.DateUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;
@SuppressWarnings(value={"rawtypes","unchecked"})
public class WfQuesAction extends BasicAction {
	private static final Logger log = Logger.getLogger(WfQuesAction.class);
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private org.apache.poi.ss.usermodel.Workbook workbookpoi;
	private File fileInp;
	private List<File[]> 			files;				//附件上传
	private String choices;
	private List<WfQues> wfQuess;
	private WfQues wfQues = new WfQues();
	private java.util.List<gnwf.vo.WfQuesRelate> wfQuesRelates;
	private java.util.List<gnwf.vo.WfReply> wfReplys;
	private java.util.List<gnwf.vo.WfQuesDtl> wfQuesDtls;
	private java.util.List<zrsy.vo.Usr> usrs;
	private java.util.List<gnwf.vo.WfQues> quesCount;
	private java.util.List<SchCfg> schCfgs;

	
	private Integer taskId;
	private String wfNo;
	private String quesId;
	private WFCenter center;						//中心处理接口
	
	private String fileNameStr;                    //下载新增问题附件对应的附件名称
	
	private String				isReloadGrid;		//是否更新父页面grid

	
	public String getIsReloadGrid() {
		return isReloadGrid;
	}


	public void setIsReloadGrid(String isReloadGrid) {
		this.isReloadGrid = isReloadGrid;
	}


	public String getFileNameStr() {
		return fileNameStr;
	}


	public void setFileNameStr(String fileNameStr) {
		this.fileNameStr = fileNameStr;
	}


	public WFCenter getCenter() {
		return center;
	}


	public void setCenter(WFCenter center) {
		this.center = center;
	}


	public String getQuesId() {
		return quesId;
	}


	public void setQuesId(String quesId) {
		this.quesId = quesId;
	}

	private String isRisk;
	private String prjtNo;
	private List<PrjtDef> prjtDefs;
	private WfRd wfRd;

	private String fileNo;
	private String fileName;
	private String tempParams;

	private String usrId;
	private String syId;
	private String syNm;
	private String usrNm;
	private int reload = 0;

	private Gp gp;
	private String isDQA;

	private void setPrjtRole() {
		String gpName = "";
		for (Gp e : getUsrSession().getGps()) {
			if (e.getGpName().indexOf("超级用户") > -1) {
				gpName = gpName + "超级用户";
			}
			if (e.getGpName().indexOf("产品经理") > -1) {
				gpName = gpName + "产品经理";
			}
			if (e.getGpName().indexOf("项目经理") > -1) {
				gpName = gpName + "项目经理";
			}
			if (e.getGpName().indexOf("DQA") > -1) {
				gpName = gpName + "DQA";
				isDQA = "DQA";
			}
			gp = new Gp();
			gp.setGpName(gpName);
		}
	}

	private String text;
	private String textId;
	private Integer wfRdStatus;

	public String managerQues() throws Exception {
		try {
			if (text != null) {
				text = URLDecoder.decode(text, "UTF-8");
			}
			syId = String.valueOf(getUsrSession().getSyId());
			syNm = getUsrSession().getSyNm();
			usrId = String.valueOf(getUsrSession().getId());
			usrNm = getUsrSession().getUsrName();
			wfQues = new WfQuesFacade().findById(wfQues);
			System.out.println(wfQues.getFileIcon()+"```"+wfQues.getFileName()+wfQues.getFileNo());
//			String[] fileNameList = wfQues.getFileName().split("||");
			
//			ActionContext ac = ActionContext.getContext();
//			HttpServletRequest request =(HttpServletRequest)ac.get(ServletActionContext.HTTP_REQUEST);
//			request.setAttribute("fileNameList", fileNameList);
					
//			Map map = new HashMap();
//			map.put("data", fileNameList);
//			HttpServletResponse response = ServletActionContext.getResponse(); 
//			response.setHeader("Content-type", "text/html;charset=UTF-8");  
//			response.setCharacterEncoding("UTF-8");
//			PrintWriter out = response.getWriter();
//			out.print(JSON.toJSONString(map));
		
			if (wfQues != null) {
				QuesResp quesResp = new QuesResp();
				quesResp.setQuesId(wfQues.getQuesId());
				List<QuesResp> qrs = new QuesRespFacade().find(quesResp);
				wfQues.setQuesRespList(qrs);
			}
			setPrjtRole();
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
			return ERROR;
		}
		return "managerQues";
	}
	
	
	//转风险审批流程
	public String managerQuesGoRiskTask() throws Exception {
		try {
			if (text != null) {
				text = URLDecoder.decode(text, "UTF-8");
			}
			syId = String.valueOf(getUsrSession().getSyId());
			syNm = getUsrSession().getSyNm();
			usrId = String.valueOf(getUsrSession().getId());
			usrNm = getUsrSession().getUsrName();
			wfQues = new WfQuesFacade().findById(wfQues);

			if (wfQues != null) {
				QuesResp quesResp = new QuesResp();
				quesResp.setQuesId(wfQues.getQuesId());
				List<QuesResp> qrs = new QuesRespFacade().find(quesResp);
				wfQues.setQuesRespList(qrs);
			}
			setPrjtRole();
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
			return ERROR;
		}
		return "managerQuesGoRiskTask";
	}
	


	public String execute() throws Exception {
		try {
			usrs = new zrsy.facade.UsrFacade().find("select " + zrsy.vo.Usr.SELF_FIELDS + " from Usr", zrsy.vo.Usr.SELF_FIELDS);
			schCfgs = new SchCfgFacade().find("select " + SchCfg.SELF_FIELDS + " from SchCfg", SchCfg.SELF_FIELDS);
			prjtDefs = new PrjtDefFacade().find("select " + PrjtDef.SELF_FIELDS + " from PrjtDef", PrjtDef.SELF_FIELDS);
			if (wfQues != null && wfQues.getQuesId() != null) {
				wfQues = new WfQuesFacade().findById(wfQues);
				setJson(JSON.toJSONString(wfQues));
			}

		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}

	public String quesManager() throws Exception {
		try {
			obtainUsrs();
			//System.out.println("111111111111111111111111222222222222");
			//usrs = new zrsy.facade.UsrFacade().find("select " + zrsy.vo.Usr.SELF_FIELDS + " from Usr", zrsy.vo.Usr.SELF_FIELDS);
			//schCfgs = new SchCfgFacade().find("select " + SchCfg.SELF_FIELDS + " from SchCfg", SchCfg.SELF_FIELDS);
			getQuesSchCfgs();
			prjtDefs = new PrjtDefFacade().find("select " + PrjtDef.SELF_FIELDS + " from PrjtDef", PrjtDef.SELF_FIELDS);
			if (wfQues != null && wfQues.getQuesId() != null) {
				System.out.println(wfQues.getSelType()+"111111111111");
				wfQues = new WfQuesFacade().findById(wfQues);
				setJson(JSON.toJSONString(wfQues));
				//System.out.println("4443333333333333344444"+wfQues.getWfNo());
			}
			//System.out.println("44444444444444"+wfQues.getWfNo());
			setPrjtRole();
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error("WfQuesAction Exception", e);
			return ERROR;
		}
		return "quesManager";
	}
	/**
	 * 获取当前用户所在项目组的所以项目组成员 
	 * @throws Exception 
	 */
	private void obtainUsrs() throws Exception {
		int flag = judgeRole();
		String sql = "";
		if(flag == 1) {
			String sqlPrjtUser = "select distinct UsrId from PrjtUsr where prjtNo is null or prjtNo in(" 
					  + "select distinct PrjtNo from PrjtUsr where PrjtTypId = "
	                  + "(select TypId from PrjtTyp where TypNm = '研发管理'))";
			List<PrjtUsr> pus = new PrjtUsrFacade().find(sqlPrjtUser, "PrjtUsr.UsrId");
			StringBuffer userIds = new StringBuffer();
			for(PrjtUsr pu : pus){
				userIds.append(pu.getUsrId()).append(",");
			}
			userIds.deleteCharAt(userIds.length() - 1);
			sql = "select " + Usr.SELF_FIELDS + " from Usr where Id in(" + userIds.toString() + ")";
		}else {
			sql = "select " + Usr.SELF_FIELDS + " from Usr where Id in(select distinct UsrId from PrjtUsr where prjtNo is null or prjtNo in (" +
                  "select distinct PrjtNo from PrjtUsr where UsrId = " + getUsrSession().getId() + "))";
		}
		usrs = new zrsy.facade.UsrFacade().find(sql, Usr.SELF_FIELDS);
	}

	/**
	 * 判断当前用户是否在项目组成员列表，有就显示除查询外的功能
	 * @throws Exception 
	 */
//	private void prjtUsrList() throws Exception {
//		
//	}
	
	//wfQuesManager项目总览问题弹出的窗口
	public String wfQuesManager() throws Exception {
		try {
			usrs = new zrsy.facade.UsrFacade().find("select " + zrsy.vo.Usr.SELF_FIELDS + " from Usr", zrsy.vo.Usr.SELF_FIELDS);
			schCfgs = new SchCfgFacade().find("select " + SchCfg.SELF_FIELDS + " from SchCfg", SchCfg.SELF_FIELDS);
			prjtDefs = new PrjtDefFacade().find("select " + PrjtDef.SELF_FIELDS + " from PrjtDef", PrjtDef.SELF_FIELDS);
			if (wfQues != null && wfQues.getQuesId() != null) {
				wfQues = new WfQuesFacade().findById(wfQues);
				setJson(JSON.toJSONString(wfQues));
			}

		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesAction Exception", e);
			return ERROR;
		}
		return "wfQuesManager";
	}
	
	public String myTaskList() throws Exception {
		syId = String.valueOf(getUsrSession().getSyId());
		syNm = getUsrSession().getSyNm();
		usrId = String.valueOf(getUsrSession().getId());
		usrNm = getUsrSession().getUsrName();
		return "mytasklist";
	}
	
	private void getQuesSchCfgs() throws Exception{
			schCfgs = new SchCfgFacade().find("select " + SchCfg.SELF_FIELDS + " from SchCfg where SchCfg.SchNm in ("+MSG.WfQues_SchCfg+")", SchCfg.SELF_FIELDS);
	}

	private Integer isFromWf;
	public String add() throws Exception {
		try {
			
			getQuesSchCfgs();
			
			if (wfQues != null && wfQues.getQuesId() != null) {
				wfQues = new WfQuesFacade().findById(wfQues);
			}
			if(isFromWf == 1){
				WfRd wfRd = new WfRdFacade().findById("select WfRd.WfName,WfRd.CreateBy,(select usrName from Usr u where u.id = WfRd.CreateBy) AS CreateName" + 
						 " from WfRd where WfNo = '" + this.wfQues.getWfNo() + "'", "WfRd.WfName,WfRd.CreateBy,AS CreateName");
				//this.responsibleUID = String.valueOf(wfRd.getCreateBy());
				//this.wfQues.setUsrName(wfRd.getCreateName());
				this.wfQues.setWfName(wfRd.getWfName());
			}
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesAction Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		System.out.println("isFromWf->" + isFromWf);
		return PGADD;
	}
	/**
	 * 获取责任人姓名和ID
	 * @return
	 */
	public String getResponsibleMsgByWfNo() {
		Map<String, String> jsonMap = new HashMap<String, String>();
		try {
			WfRd wfRd = new WfRdFacade().findById("select WfRd.CreateBy,(select usrName from Usr u where u.id = WfRd.CreateBy) AS CreateName " + 
					 " from WfRd where WfNo = '" + this.wfNo + "'", "WfRd.CreateBy,AS CreateName");
			/*, (select dp.deptNm from Usr us inner join Dept dp on us.OrgNo = dp.deptid where us.id = WfRd.CreateBy ) as deptName
			jsonMap.put("responsibledeptName", wfRd.getDeptName());*/
			jsonMap.put("responsibleName", wfRd.getCreateName());
			jsonMap.put("responsibleId", String.valueOf(wfRd.getCreateBy()));
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error("WfQuesAction getResponsibleMsgByWfNo", e);
			/*jsonMap.put("responsibledeptName", "");*/
			jsonMap.put("responsibleName", "");
			jsonMap.put("responsibleId", "");
			e.printStackTrace();
		}
		String json = JSON.toJSONString(jsonMap);
		System.out.println("json->" + json);
		setJson(json);
		return SUCCESS;
	}

	public void afterUploadFile() {
		try {
			String quesId = null;
			setFileName(URLDecoder.decode(getFileName(), "UTF-8"));
			setTempParams(URLDecoder.decode(getTempParams(), "UTF-8"));
			if (getFileNo() != null) {
				String sql = null;
				if (getTempParams() != null) {
					// String s[] = getTempParams().split(",");
					// for(int i = 0;i<s.length;i++){
					String ss[] = getTempParams().split(":");
					if (ss[0].equals("wfQues.quesId")) {
						quesId = ss[1];
						sql = "update WfQues set FileName='" + getFileName() + "', FileNo = '" + getFileNo() + "', LastUpd=" + usrId + ", LastUpdDate ='" + DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss") + "' where QuesId=" + quesId;
					}

					if (ss[0].equals("impQuesIds")) {
						impQuesIds = ss[1];
						sql = "update WfQues set FileName='" + getFileName() + "', FileNo = '" + getFileNo() + "', LastUpd=" + usrId + ", LastUpdDate ='" + DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss") + "' where QuesId in (" + impQuesIds + ")";
					}

					// }
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

	/**
	 * 编辑问题页面
	 * @return
	 * @throws Exception
	 */
	public String editQuesUI(){
		try {
			wfQues = new WfQuesFacade().findById(wfQues);
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesAction editQuesUI Exception", e);
			return ERROR;
		}
		return "editQuesUI";
	}
	/**
	 * 编辑问题
	 * @return
	 */
	public String editQues() {
		try {
			
			isReloadGrid = "true";
			WfQues wq = new WfQuesFacade().findById(this.wfQues);
			
			if(files!=null){
				String fileNameString = wq.getFileName();
				String fileNoString = wq.getFileNo();
				String relativePath  = fileNoString;
				String savePath = getRequest().getSession().getServletContext().getRealPath(relativePath);	
				for(int i=0;i<files.size();i++){
					if (files.get(i) != null ) {
					File attach =files.get(i)[0]; 
					
						uploadFile(attach, savePath);
						if (i==0) {
							fileNameString += files.get(i)[0].getName();
						}else {
							fileNameString += "||"+files.get(i)[0].getName();
						}
					} 
					
				}
				wfQues.setFileNo(relativePath);
				wfQues.setFileName(fileNameString);
			}
			
			
/*			if(fileInp != null) {
				relativePath = "/images/wfQues/" + DateUtil.format(new Date(), "yyyy/MM/dd");
				String savePath = getRequest().getSession().getServletContext().getRealPath(relativePath);
				uploadFile(fileInp,savePath);
			}
			if(relativePath != null) {
				this.wfQues.setFileNo(relativePath);
				this.wfQues.setFileName(fileInp.getName());
			}**/
			new WfQuesFacade().update(wfQues);
			setMsg(MSG.S_UPD);
		} catch (Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfQuesAction editQues Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String flowQuesList(){
		try {

			String wfNos = "";
			if (wfQues == null) wfQues = new WfQues();
			if (taskId != null) {
				wfQues.setTaskId(taskId);
			} 
			if (wfNo != null && wfNo.length() > 0) {
				wfQues.setWfNo(wfNo);
			}
			if (isRisk != null && isRisk.length() > 0) {
				wfQues.setIsRisk(isRisk);
			} 
			if (prjtNo != null && prjtNo.length() > 0) {
				wfNos = new WfQuesFacade().getPrjtTaksIds(prjtNo);// taskIds
				if ("".equals(wfNos)) {
					return PGLIS;
				}
				wfQues.setWfNos(wfNos);
			}

			if (wfQues.getPrjtNo() == null || wfQues.getPrjtNo().equals("ALL") || Utils.StringUtils.isEmpty(wfQues.getPrjtNo())) {
				setPrjtNoList();
				wfQues.setPrjtNo(null);
			}

			System.out.println(wfQues.getSelectType());
			wfQues.setCurrentUsr(getUsrSession().getId());

			int total = new WfQuesFacade().amount(wfQues);
			if (getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfQuess = new WfQuesFacade().find(wfQues, getPageVO());
			WfQuesJson wfQuesJson = new WfQuesJson();
			wfQuesJson.Rows = wfQuess;
			wfQuesJson.Total = total;
			System.out.println(JSON.toJSONString(wfQuesJson));
			setJson(JSON.toJSONString(wfQuesJson));
			schCfgs = new SchCfgFacade().find("select " + SchCfg.SELF_FIELDS + " from SchCfg", SchCfg.SELF_FIELDS);
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	
	/**
	 * 查询所有的问题（分页查询）
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		try {
			if (wfQues.getPrjtNo() == null || wfQues.getPrjtNo().equals("ALL") || Utils.StringUtils.isEmpty(wfQues.getPrjtNo())) {
				setPrjtNoList();	
				wfQues.setPrjtNo(null);
			}
			if(this.wfQues.getScheId() != null) {
				StringBuffer schIds = new StringBuffer();
				schIds.append(this.wfQues.getScheId()).append(",");
				String schSql = "select SchCfg.SchId from SchCfg where Parent = " + this.wfQues.getScheId();
				List<SchCfg> scs = new SchCfgFacade().find(schSql, "SchCfg.SchId");
				if(scs != null && scs.size() > 0) {
					for(SchCfg sc : scs) {
						schIds.append(sc.getSchId()).append(",");
					}
				}
				schIds.deleteCharAt(schIds.length() - 1);
				this.wfQues.setScheIds(schIds.toString());
			}
			wfQues.setCurrentUsr(getUsrSession().getId());
			int total = new WfQuesFacade().amount(wfQues);
			if (getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			//责任部门
			//System.out.println("########this.wfQues.getQuesId()"+this.wfQues.getQuesId());
			
			//wfQues.setDeptNm("");
			wfQuess = new WfQuesFacade().find(wfQues, getPageVO());
			System.out.println(wfQuess.toString());
			WfQuesJson wfQuesJson = new WfQuesJson();
			wfQuesJson.Rows = getWfQuesList(wfQuess);
			wfQuesJson.Total = total;
			System.out.println("------"+JSON.toJSONString(wfQuesJson));
			setJson(JSON.toJSONString(wfQuesJson));
			schCfgs = new SchCfgFacade().find("select " + SchCfg.SELF_FIELDS + " from SchCfg", SchCfg.SELF_FIELDS);
			//System.out.println(wfQues.getWfNo());
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesAction Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return PGLIS;
	}
	
	/**
	 * 根据项目编号获取项目组成员
	 * @return
	 */
	public String getUserByPrjtNo() {
		try {
			usrs = new zrsy.facade.UsrFacade().find("select " + Usr.SELF_FIELDS + " from Usr left join Dept on (Dept.DeptId = Usr.DeptId)  where Id in(" 
                    + "select distinct UsrId from PrjtUsr where prjtNo is null or prjtNo = '" + prjtNo + "')", Usr.SELF_FIELDS);
			String json = JSON.toJSONString(usrs);
			//System.out.println("8888888888887"+json);
			setJson(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PGLIS;
	}
	
	//查询项目登录人员的项目组角色，如果不是项目组角色返回fales，如果是该项目组角色返回RoleID
	public String findProjectRoleID() {
		try {
			usrs = new zrsy.facade.UsrFacade().find("select " + Usr.SELF_FIELDS + " from Usr left join Dept on (Dept.DeptId = Usr.DeptId)  where Id in(" 
                    + "select distinct UsrId from PrjtUsr where prjtNo is null or prjtNo = '" + prjtNo + "')", Usr.SELF_FIELDS);
			//String json = JSON.toJSONString(usrs);
			boolean flag = false; 
			Integer roleIDStatus = null;
			Usr user = null;
			for(int i = 0;  i < usrs.size();i++){
			        user = usrs.get(i);
			      if(getUsrSession().getId().equals(user.getId())||getUsrSession().getId()==user.getId()){
			                flag = true;
			                break;
			      }
			}
			if (flag) {
				PrjtUsr pUsr = new PrjtUsrFacade().findById("select PrjtUsr.RoleId from PrjtUsr where PrjtUsr.PrjtNo = '" + prjtNo + "' and PrjtUsr.UsrId = '" + getUsrSession().getId() + "' ", "PrjtUsr.RoleId");
				System.out.println("pUsr"+pUsr+"!!!!!!!!!!!!"+pUsr.getRoleId());
				roleIDStatus = pUsr.getRoleId();
			}else {
				roleIDStatus = 0;
			}
			//System.out.println(JSON.toJSONString(roleIDStatus)+"!!!!!!!!!!");
			setJson(JSON.toJSONString(roleIDStatus));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PGLIS;
	}
	
	/**
	 * 根据项目编号获取项目提交成员
	 * @return
	 */
	public String getCreatUserByPrjtNo() {
		try {
			usrs = new zrsy.facade.UsrFacade().find("select " + Usr.SELF_FIELDS + " from Usr left join Dept on (Dept.DeptId = Usr.DeptId)  where Id in(" 
                    + "select distinct CreateBy from PrjtUsr where prjtNo is null or prjtNo = '" + prjtNo + "')", Usr.SELF_FIELDS);
			String json = JSON.toJSONString(usrs);
			System.out.println("7777"+json);
			setJson(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PGLIS;
	}
	public String getQuesCountByPrjtNo() {
		try {
			/*",(select count(QuesId) from WfQues  where WfQues.Status = '21' or WfQues.Status = '1'or WfQues.Status = '9'or WfQues.Status = '10'or WfQues.Status = '11') AS QuesOpen" +
			",(select count(QuesId) from WfQues  where WfQues.Status = '30') AS QuesClose" +
			",(select count(QuesId) from WfQues  where WfQues.Status = '40') AS QuesRisk" +*/
			String QuesOpensql = "(select count(QuesId) from WfQues wf where wf.Status in ( '21' ,'1','9','10','11' )and WfQues.PrjtNo =wf.PrjtNo) AS QuesOpen, ";
			String QuesClosesql = "(select count(QuesId) from WfQues wf where wf.Status = '30'and WfQues.PrjtNo =wf.PrjtNo) AS QuesClose,";
			String QuesRisksql = "(select count(QuesId) from WfQues wf where wf.Status = '40'and WfQues.PrjtNo =wf.PrjtNo) AS QuesRisk";
			
			System.out.println("prjtNo"+prjtNo);
			quesCount = new WfQuesFacade().find("select "+QuesOpensql+QuesClosesql+QuesRisksql+" from WfQues where WfQues.PrjtNo = '" + prjtNo + "' group by WfQues.PrjtNo ", QuesOpensql+QuesClosesql+QuesRisksql);

			String json = JSON.toJSONString(quesCount);
			//System.out.println("77777777777777"+"select "+QuesOpensql+QuesClosesql+QuesRisksql+" from WfQues where WfQues.PrjtNo = '" + prjtNo + "' ");
			System.out.println(json);
			setJson(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PGLIS;
	}
	/**
	 * 判断是否是（超级用户，不随项目变更角色，DQA，项目经理）角色
	 * @return 0表示不是以上四种角色，1表示（超级用户，不随项目变更角色），2表示（DQA，项目经理）
	 */
	private int judgeRole() {
		int flag = 0;
		String gpNames = getUsrSession().getGpNames();
		if(gpNames == null){
			List<Gp> gps = getUsrSession().getGps();
			StringBuffer gpNamesBuffer = new StringBuffer();
			for(Gp gp : gps) {
				gpNamesBuffer.append(gp.getGpName());
			}
			gpNames = gpNamesBuffer.toString();
		}
		if(gpNames.contains("超级用户") || gpNames.contains("不随项目变更角色")) {
			flag = 1;
		}else if(gpNames.contains("DQA") || gpNames.contains("项目经理")) {
			flag = 2;
		}
		return flag;
	}

	private void setPrjtNoList() throws Exception {
		int selAllPrjts = judgeRole();
		String sql = null;
		if (selAllPrjts == 1) {//超级用户，不随项目变更角色
			sql = "select PrjtDef.PrjtNo from PrjtDef where (TypId = ( select TypId from PrjtTyp where TypNm  = '研发管理') )  order by Status asc , CreateDate desc";
		} else if(selAllPrjts == 2){//DQA,项目经理
			sql = "select PrjtDef.PrjtNo from PrjtDef where (PrjtNo in ( select PrjtNo from PrjtUsr where UsrId  = " + getUsrSession().getId() + ") ) or CreateBy = " + getUsrSession().getId() + " order by Status asc , CreateDate desc";
		}else {//其他普通用户
			this.wfQues.setUsrName(getUsrSession().getUsrName());
			sql = "select PrjtDef.PrjtNo from PrjtDef where (PrjtNo in ( select PrjtNo from PrjtUsr where UsrId  = " + getUsrSession().getId() + ") ) or CreateBy = " + getUsrSession().getId() + " order by Status asc , CreateDate desc";
		}
		List<PrjtDef> prjtDefs2 = new PrjtDefFacade().find(sql, "PrjtDef.PrjtNo");
		String prjtNoList = "";
		for (PrjtDef e : prjtDefs2) {
			prjtNoList += ",'" + e.getPrjtNo() + "'";
		}
		prjtNoList = prjtNoList.substring(1, prjtNoList.length());
		wfQues.setPrjtNoList(prjtNoList);
	}

	public String mylist() throws Exception {
		String fieds = WfQues.SELF_FIELDS + ",(select u.UsrName from Usr u where WfQues.CreateBy = u.Id) AS CreateUsr"
				       + ",(select pd.PrjtNm from PrjtDef pd where pd.PrjtNo = WfQues.PrjtNo) AS PrjtNm"
				       //+ ",(select QR.QuesAnalysis from QuesResp QR where QR.QuesId  = WfQues.QuesId) AS QuesAnalysis"
				       +",(select top 1 dp.deptNm from QuesResp qr, Dept dp ,Usr us where qr.QuesId = WfQues.QuesId and qr.UsrId = us.Id and us.OrgNo = dp.deptid) AS DeptNm"
				       +",(select sc.SchNm from SchCfg sc where WfQues.ScheId = sc.SchId) AS SchNm" 
				       + ",(select wr.WfName from WfRd wr where WfQues.wfNo = wr.wfNo) AS WfName";
		String conditionSQL = "";
		if (wfQues.getSelectType() == 3) {// 我提出的问题
			//conditionSQL = " from WfQues join QuesResp on WfQues.QuesId = QuesResp.QuesId where WfQues.CreateBy = " + getUsrSession().getId();
			conditionSQL = " from WfQues where WfQues.CreateBy = " + getUsrSession().getId();
		} else if (wfQues.getSelectType() == 2) {// 我已解决的问题
			/*conditionSQL = " from WfQues left join Usr on WfQues.UserId = Usr.Id inner join  QuesResp on WfQues.QuesId = QuesResp.QuesId " + "where QuesResp.UsrId = " + getUsrSession().getId() 
					+ " and (QuesResp.Status =1 or QuesResp.Status =2 or QuesResp.Status = 3 or WfQues.status = 40) and QuesResp.RespType=1";*/
			conditionSQL = " from WfQues join QuesResp on WfQues.QuesId = QuesResp.QuesId where QuesResp.UsrId = "
					       + getUsrSession().getId() 
					       + " and (QuesResp.Status = 1 or QuesResp.Status =2 or QuesResp.Status = 3 "
					       + "or QuesResp.Status = 5) and QuesResp.RespType = 1";
		} else if (wfQues.getSelectType() == 1) {// 待我解决问题
			/*conditionSQL = " from WfQues left join Usr on WfQues.UserId = Usr.Id inner join  QuesResp on "
					    + "WfQues.QuesId = QuesResp.QuesId where (QuesResp.UsrId = " 
					    + getUsrSession().getId() + " and (QuesResp.Status =0 or QuesResp.Status =-1"
					    + ") and QuesResp.RespType=1 and WfQues.Status != 30 and WfQues.Status != 40)";*/
			conditionSQL = " from WfQues join QuesResp on WfQues.QuesId = QuesResp.QuesId where QuesResp.UsrId = "
					       + getUsrSession().getId() + " and (QuesResp.Status = 0 or QuesResp.Status = -1)"
					       + " and QuesResp.RespType = 1 and WfQues.Status != 30 and WfQues.Status != 40";
		} else if(wfQues.getSelectType() == 4) {//待我验证的问题
			/*conditionSQL = " from WfQues left join Usr on WfQues.UserId = Usr.Id inner join  QuesResp on "
				           + "WfQues.QuesId = QuesResp.QuesId where QuesResp.IdtfBy = " + getUsrSession().getId()
				           + " and (QuesResp.Status = 1 or QuesResp.Status = 3) and QuesResp.RespType=1 and WfQues.Status != 30";*/
			conditionSQL = " from WfQues join QuesResp on WfQues.QuesId = QuesResp.QuesId where QuesResp.IdtfBy = "
					       + getUsrSession().getId() + " and (QuesResp.Status = 1 or QuesResp.Status = 3)"
					       + " and QuesResp.RespType=1 and WfQues.Status != 30 and WfQues.Status != 40";
			fieds += ",QuesResp.Id AS QuesRespId,QuesResp.IdtfDate AS QuesRespIdtfDate,QuesResp.Result AS QuesRespResult,QuesResp.QuesAnalysis AS QuesAnalysis";
		}
		if (wfQues.getPrjtNo() != null && !wfQues.getPrjtNo().trim().isEmpty() && !wfQues.getPrjtNo().trim().equals("ALL")) {
			if (wfQues.getPrjtNo().trim().equals("NO_PRJT")) {
				conditionSQL += " and (WfQues.PrjtNo is null or WfQues.PrjtNo = '')";
			} else {
				conditionSQL += " and WfQues.PrjtNo = '" + wfQues.getPrjtNo().trim() + "'";
			}
		}
		try {
			String amountSQL = "select count(WfQues.QuesId) as amount " + conditionSQL;
			int total = new WfQuesFacade().amount(amountSQL);
			if (getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			//wfQuess = new WfQuesFacade().findmy(conditionSQL, getPageVO());
			
			WfQuesJson wfQuesJson = new WfQuesJson();
			if(wfQues.getSelectType() == 4){
				this.wfQuess = new WfQuesFacade().getPageBeanRepeat(conditionSQL, fieds, getPageVO());
				wfQuesJson.Rows = wfQuess;
			}else {
				this.wfQuess = new WfQuesFacade().getPageBean(conditionSQL, fieds, getPageVO());
				wfQuesJson.Rows = getWfQuesList(wfQuess);
			}
			wfQuesJson.Total = total;
			System.out.println("`````````````````"+JSON.toJSONString(wfQuesJson));
			setJson(JSON.toJSONString(wfQuesJson));
			//schCfgs = new SchCfgFacade().find("select " + SchCfg.SELF_FIELDS + " from SchCfg", SchCfg.SELF_FIELDS);
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesAction Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return PGLIS;
	}
	/**
	 * 组拼措施和原因分析
	 * @param wfQuesList
	 * @return
	 * @throws Exception
	 */
	private List<WfQues> getWfQuesList(List<WfQues> wfQuesList) throws Exception {
		List<WfQues> newWfQuess = new ArrayList<WfQues>();
		QuesRespFacade qrf = new QuesRespFacade();
		for(WfQues wq : wfQuesList) {
			List<QuesResp> qrList = qrf.find("select QuesResp.Result,QuesResp.IdtfDate from QuesResp where RespType = 1 and QuesId = '" + wq.getQuesId()+"'", "QuesResp.Result,QuesResp.IdtfDate");
			StringBuffer quesMeasures = new StringBuffer();
			
			List<QuesResp> qrQuesAnalysisList = qrf.find("select QuesResp.QuesAnalysis from QuesResp where RespType = 1 and QuesId = '" + wq.getQuesId()+"'", "QuesResp.QuesAnalysis");
			StringBuffer quesAnalysis = new StringBuffer();
			
			List<QuesResp> qrIdtfResList = qrf.find("select QuesResp.IdtfRes from QuesResp where RespType = 1 and QuesId = '" + wq.getQuesId()+"'", "QuesResp.IdtfRes");
			StringBuffer IdtfRes = new StringBuffer();

			
			if(qrIdtfResList!= null && qrIdtfResList.size() > 0) {
				if(qrIdtfResList.size() == 1) {
					if(qrIdtfResList.get(0).getIdtfRes() != null)IdtfRes.append(qrIdtfResList.get(0).getIdtfRes()).append("||");
					//newDate = qrList.get(0).getIdtfDate();
				}else {
					for(int i=0;i<qrIdtfResList.size();i++) {
						if(qrIdtfResList.get(i).getIdtfRes() != null)IdtfRes.append(qrIdtfResList.get(i).getIdtfRes()).append("||");
						
					}
				}
				if(!"".equals(IdtfRes.toString())){
					wq.setIdtfRes(IdtfRes.substring(0, IdtfRes.length() - 2));
				}
			}
			
			if(qrQuesAnalysisList!= null && qrQuesAnalysisList.size() > 0) {
				if(qrQuesAnalysisList.size() == 1) {
					if(qrQuesAnalysisList.get(0).getQuesAnalysis() != null)quesAnalysis.append(qrQuesAnalysisList.get(0).getQuesAnalysis()).append("||");
					//newDate = qrList.get(0).getIdtfDate();
				}else {
					for(int i=0;i<qrQuesAnalysisList.size();i++) {
						if(qrQuesAnalysisList.get(i).getQuesAnalysis() != null)quesAnalysis.append(qrQuesAnalysisList.get(i).getQuesAnalysis()).append("||");
						
					}
				}
				if(!"".equals(quesAnalysis.toString())){
					wq.setQuesAnalysis(quesAnalysis.substring(0, quesAnalysis.length() - 2));
				}
			}
			Date newDate = null;
			if(qrList!= null && qrList.size() > 0) {
				if(qrList.size() == 1) {
					if(qrList.get(0).getResult() != null)quesMeasures.append(qrList.get(0).getResult()).append("||");
					newDate = qrList.get(0).getIdtfDate();
				}else {
					for(int i=0;i<qrList.size();i++) {
						if(qrList.get(i).getResult() != null)quesMeasures.append(qrList.get(i).getResult()).append("||");
						for(int j=i+1;j<qrList.size();j++) {
							if(qrList.get(i).getIdtfDate() != null && qrList.get(j).getIdtfDate() != null) {
								if(qrList.get(i).getIdtfDate().after(qrList.get(j).getIdtfDate())) {
									newDate = qrList.get(i).getIdtfDate();
								}else {
									newDate = qrList.get(j).getIdtfDate();
								}
							}
						}
					}
				}
				if(!"".equals(quesMeasures.toString())){
					wq.setQuesMeasures(quesMeasures.substring(0, quesMeasures.length() - 2));
				}
				wq.setIdtfDate(newDate);
			}
			newWfQuess.add(wq);
		}
		return newWfQuess;
	}

	public String queslist() throws Exception {
		try {
			String wfNos = "";
			if (wfQues == null) wfQues = new WfQues();
			if (taskId != null) {
				wfQues.setTaskId(taskId);
			} else if (wfNo != null && wfNo.length() > 0) {
				wfQues.setWfNo(wfNo);
			} else if (isRisk != null && isRisk.length() > 0) {
				wfQues.setIsRisk(isRisk);
			} else if (prjtNo != null && prjtNo.length() > 0) {
				wfNos = new WfQuesFacade().getPrjtTaksIds(prjtNo);// taskIds
																	// 由多个TaskId逗号分隔组合
				// taskIds = "1";
				if ("".equals(wfNos)) {
					return PGLIS;
				}
				wfQues.setWfNos(wfNos);
			}

			System.out.println(wfQues.getSelectType());
			wfQues.setCurrentUsr(getUsrSession().getId());

			int total = new WfQuesFacade().amount(wfQues);
			if (getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfQuess = new WfQuesFacade().find(wfQues, getPageVO());
			WfQuesJson wfQuesJson = new WfQuesJson();
			wfQuesJson.Rows = wfQuess;
			wfQuesJson.Total = total;
			System.out.println(JSON.toJSONString(wfQuesJson));
			setJson(JSON.toJSONString(wfQuesJson));

			schCfgs = new SchCfgFacade().find("select " + SchCfg.SELF_FIELDS + " from SchCfg", SchCfg.SELF_FIELDS);
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}

	public String taskList() throws Exception {//"&quesId=" + wfQues.getQuesId()
		//System.out.println(quesId+"quesID");
		//WfQues wr = new WfQuesFacade().findById("select WfQues.PrjtNo from WfQues where QuesId = '" + quesId + "'", "WfQues.PrjtNo");
		/*usrs = new zrsy.facade.UsrFacade().find("select " + Usr.SELF_FIELDS + " from Usr where Id in(select distinct UsrId from PrjtUsr where prjtNo = '"
                                                + wr.getProjectNo() + "')", Usr.SELF_FIELDS);*/
		//prjtNo = wr.getPrjtNo();
		obtainUsrs();
		return "tasklist";
	}

	public String tasklistmgr() {
		return "tasklistmgr";
	}

	public String scheList() {
		try {
			schCfgs = new SchCfgFacade().find("select " + SchCfg.SELF_FIELDS + " from SchCfg", SchCfg.SELF_FIELDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "schelist";
	}


	private String responsibleUID;
	//在问题编辑页面点击转交责任人
	public String updateResponers() {
		try {
			if (responsibleUID != null && responsibleUID.length() > 0) {
				WfQues wq = new WfQuesFacade().findById(wfQues);
				String[] rspons = responsibleUID.split(",");
				QuesResp quesResp = new QuesResp();
				quesResp.setQuesId(wfQues.getQuesId());
				List<QuesResp> qrs = new QuesRespFacade().find(quesResp);
				
				String oldQuesAnalysis = "";
				String oldResult = "";
				// 将被移除的负责人状态改成0
				boolean willdel = true;
				String delIds = "";
				for (QuesResp old : qrs) {
					for (int i = 0; i < rspons.length; i++) {
						if (rspons[i].equals(old.getQuesId())) {// 证明此人已经是责任人
							willdel = false;
						}
					}
					if (willdel) {
						delIds = delIds + old.getId() + ",";
					}
					if (old.getQuesAnalysis().length() != 0) {
						oldQuesAnalysis = oldQuesAnalysis + old.getQuesAnalysis() + "--"+old.getUsrName()+"（转交前记录）";
					}
					if (old.getResult().length() != 0) {
						oldResult = oldResult + old.getResult() + "--"+old.getUsrName()+"（转交前记录）";
					}
				}
				// 增加新的责任人
				List<QuesResp> addQuesRsps = new ArrayList<QuesResp>();
				boolean willAdd = false;
				for (int i = 0; i < rspons.length; i++) {
					for (QuesResp old : qrs) {
						if (rspons[i].equals(old.getQuesId())) {// 证明此人已经是责任人
							willAdd = true;
						}
					}
					if (!willAdd) {
						QuesResp qs = new QuesResp();
						qs.setQuesId(wfQues.getQuesId());
						qs.setUsrId(Integer.valueOf(rspons[i]));
						qs.setStatus(0);
						qs.setRespType(1);
						qs.setCreateBy(getUsrSession().getId());
						qs.setCreateDate(new Date());
						qs.setLastUpd(getUsrSession().getId());
						qs.setLastUpdDate(new Date());
						qs.setIdtfBy(wq.getIdtfBy());
						qs.setQuesAnalysis(oldQuesAnalysis);
						qs.setResult(oldResult);
						addQuesRsps.add(qs);
					}
				}
				if(!"".equals(delIds)) {
					delIds = delIds.substring(0, delIds.length() - 1);
				}else {
					delIds = "-1";
				}
				//转交责任人后问题状态改回"待解决"
				wfQues.setStatus(MSG.WFQUES_STATUS_1);
				new WfQuesFacade().updateResponers(wfQues, getUsrSession().getId(), delIds, addQuesRsps);
				setMsg(MSG.S_SAV);
			}
		} catch (Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfQuesAction updateResponers Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return MESSAGE;
	}
	
	private String updateQuesIds;
	/**
	 * 批量转交责任人
	 * @return
	 */
	public String bathUpdateResponers() {
		try {
			String[] updateQuesIdsArr = updateQuesIds.split(",");
			String[] responsibleUIDArr = responsibleUID.split(",");
			new WfQuesFacade().bathUpdateResponers(updateQuesIdsArr,wfQues.getUsrName(),responsibleUIDArr,getUsrSession().getId());
			setMsg(MSG.S_SAV);
		} catch (Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfQuesAction bathUpdateResponers Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return MESSAGE;
	}

	
	/**
	 * 保存问题
	 * @return
	 * @throws Exception
	 */
	public String saveQues() throws Exception {
		try {
			isReloadGrid = "true";
			System.out.println("SAVEques!");
			System.out.println(wfQues.getTestItemID()+"!!!!!"+wfQues.getTestItemName());
			if(files!=null){
				System.out.println(files.size());
				String relativePath = "/images/wfQues/" + DateUtil.format(new Date(), "yyyy/MM/dd");
				String savePath = getRequest().getSession().getServletContext().getRealPath(relativePath);
				String fileNameString = "";
				for(int i=0;i<files.size();i++){
					File attach =files.get(i)[0]; 
					uploadFile(attach, savePath);
					if (i==0) {
						fileNameString += files.get(i)[0].getName();
					}else {
						fileNameString += "||"+files.get(i)[0].getName();
					}
				}
				wfQues.setFileNo(relativePath);
				wfQues.setFileName(fileNameString);
			}
			
			String res_url = getAppcationURL();
			String title = "";
			WfRd wr = new WfRd();
			//PrjtDef pd = new PrjtDef();
			String wfquNo = wfQues.getWfNo();
			
			if ("".equals(wfquNo)) {
				System.out.println(wfQues.getPrjtNo());
				PrjtDef prjtDef = new PrjtDef();
				prjtDef.setPrjtNo(wfQues.getPrjtNo());
				PrjtDef prjt =  new PrjtDefFacade().findById(prjtDef);
				
				title = "项目(" +prjt.getPrjtNm() + ")-新增问题通知";
				
			}else {
				System.out.println("wfQues.getWfNo()!="+wfQues.getWfNo());
				wr.setWfNo(wfQues.getWfNo());
				wfQues.setScheId(wr.getScheId());
				wr = new WfRdFacade().findById(wr);
				 title = "工作流(" + wr.getWfName() + ")-新增问题通知";
				 
			}
			
			wfQues.setIdtfBy(getUsrSession().getId());
			wfQues.setCreateBy(getUsrSession().getId());
			wfQues.setCreateDate(new Date());
			wfQues.setStatus(1);
			wfQues.setLastUpd(getUsrSession().getId());
			wfQues.setLastUpdDate(new Date());
			
			List<QuesResp> qsList = new ArrayList<QuesResp>();
			List<Usr> userList = new ArrayList<Usr>();
			if (responsibleUID != null && responsibleUID.length() > 0) {
				String[] rspons = responsibleUID.split(",");
				for (int i = 0; i < rspons.length; i++) {
					QuesResp qs = new QuesResp();
					qs.setQuesId(wfQues.getQuesId());
					qs.setUsrId(Integer.valueOf(rspons[i]));
					qs.setIdtfBy(getUsrSession().getId());
					qs.setCreateBy(getUsrSession().getId());
					qs.setCreateDate(new Date());
					qs.setLastUpd(getUsrSession().getId());
					qs.setLastUpdDate(new Date());
					qs.setStatus(MSG.QUESRESP_STATUS_0);
					qs.setRespType(MSG.QUESRESP_TYPE_NEW);
					qs.setCreateBy(1);
					qsList.add(qs);
					Usr u = new Usr();
					u.setId(Integer.valueOf(rspons[i]));
					userList.add(u);
				}
			}
			wfQues.setQuesRespList(qsList);
			new WfQuesFacade().save(wfQues);
			System.out.println();
			
			int quesSourceID = wfQues.getSourceID();
			String quesSource = "";
			if (quesSourceID == 1) {
				quesSource = "硬件测试";
			}else if (quesSourceID == 2) {
				quesSource = "试产组装";
			}else if (quesSourceID == 3) {
				quesSource = "试产贴片";
			}else if (quesSourceID == 4) {
				quesSource = "白盒测试";
			}else if (quesSourceID == 5) {
				quesSource = "整机测试";
			}else if (quesSourceID == 6) {
				quesSource = "工厂测试";
			}
			String quesTestItemNameString = wfQues.getTestItemName();
			PrjtDef prjtDef = new PrjtDef();
			prjtDef.setPrjtNo(wfQues.getPrjtNo());
			PrjtDef prjt =  new PrjtDefFacade().findById(prjtDef);
			System.out.println(wfQues.getQuesId()+"---------"+prjt.getPrjtNm()+"---------"+wfQues.getPrjtNo());
			String content = "尊敬的同事，您好：<p>项目《"+prjt.getPrjtNm() +"》新增了一个问题,请您及时关注并处理。</p>"
							+ "<p>问题内容：" + wfQues.getQuesText() + "</p></br><p>问题来源:"+quesSource+"</p></br><p>测试项:"+quesTestItemNameString+"</p></br>链接地址 ： <a href=" + res_url + "/WfQues!managerQues.shtml?wfQues.quesId="
							+  wfQues.getQuesId() + ">"
							+res_url+"/WfQues!/managerQues.shtml?wfQues.quesId="+wfQues.getQuesId() +"</a>";
			
			//发邮件给DQA
			List<PrjtUsr> prjtUsrList = new PrjtUsrFacade().find("select PrjtUsr.UsrId from PrjtUsr join PrjtRole on PrjtUsr.RoleId = PrjtRole.RoleId"
                     + " where PrjtRole.RoleNm in('DQA') and PrjtUsr.PrjtNo = '"
                    + wfQues.getPrjtNo() + "'","PrjtUsr.UsrId");
			for(PrjtUsr pu : prjtUsrList) {
				Usr u = new Usr();
				u.setId(pu.getUsrId());
				userList.add(u);
			}
			//发邮件通知责任人
			WFUtil.sendMailByIT(title, content, userList);
			setMsg(MSG.S_SAV);
		} catch (Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfQuesAddAction saveQues Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}

	private File imgFile;
	/**
	 * 文件名称
	 */
	private String imgFileFileName;

	/**
	 * 图片宽度
	 */
	private String imgWidth;

	/**
	 * 图片高度
	 */
	private String imgHeight;

	/**
	 * 图片对齐方式
	 */
	private String align;

	/**
	 * 图片标题
	 */
	private String imgTitle;
	public String uploadImg() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		String path = "upload/ques_image/";
		// 文件保存目录路径
		String savePath = ServletActionContext.getServletContext().getRealPath("/") + path;
		// 文件保存目录URL
		String saveUrl = ServletActionContext.getRequest().getContextPath() + "/" + path;
		// 定义允许上传的文件扩展名
		String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };
		// 最大文件大小
		long maxSize = 2 * 1024 * 1024;
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			log.error(e1);
		}

		if (imgFile == null) {
			out.println(getError("请选择文件。"));
			return null;
		}

		// 检查目录
		System.out.println(savePath);
		File uploadDir = new File(savePath);
		if(!uploadDir.exists()){
			uploadDir.mkdirs();
		}
		if (!uploadDir.isDirectory()) {
			out.println(getError("上传目录不存在。"));
			return null;
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			out.println(getError("上传目录没有写权限。"));
			return null;
		}
		// 创建文件夹
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		String fileExt = imgFileFileName.substring(imgFileFileName.lastIndexOf(".") + 1).toLowerCase();
		if (!Arrays.<String> asList(fileTypes).contains(fileExt)) {
			out.println(getError("上传文件扩展名[" + fileExt + "]是不允许的扩展名。"));
			return null;
		}
		if (imgFile.length() > maxSize) {
			out.println(getError("[ " + imgFileFileName + " ]超过单个文件大小限制，文件大小[ " + imgFile.length() + " ]，限制为[ " + maxSize + " ] "));
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
		File uploadedFile = new File(savePath, newFileName);
		JSONObject obj = new JSONObject();
		try {
			FileUtils.copyFile(imgFile, uploadedFile);
			obj.put("error", 0);
			obj.put("url", saveUrl + newFileName);
			log.debug(obj);
			log.debug("上传图片:[" + uploadedFile.getName() + "]" + ">>>[" + newFileName + "]成功");
		} catch (IOException e) {
			log.error("图片上传失败:" + e);
			obj.put("error", 1);
			obj.put("message", "添加失败");
		}
		out.println(obj.toString());
		setJson(obj.toString());
		return PGJSON;
	}
	
	
	public String fileManager(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		String path = "upload/ques_image/";
		// 文件保存目录路径
		String savePath = ServletActionContext.getServletContext().getRealPath("/") + path;
		// 文件保存目录URL
		String saveUrl = ServletActionContext.getRequest().getContextPath() + "/" + path;
		// 定义允许上传的文件扩展名
		final String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };
		// 最大文件大小
		final long maxSize = 2 * 1024 * 1024;
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			log.error(e1);
		}

		// 检查目录
		System.out.println(savePath);
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			out.println(getError("上传目录不存在。"));
			return null;
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			out.println(getError("上传目录没有写权限。"));
			return null;
		}
		// 创建文件夹
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()||!dirFile.isDirectory()) {
			//dirFile.mkdirs();
			return PGJSON;
		}
		File[] files = dirFile.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				if (pathname.isDirectory()) {
					return false;
				}
				String fileExt = pathname.getName().substring(pathname.getName().lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.<String> asList(fileTypes).contains(fileExt)) {// 上传文件扩展名
					return false;
				}
				if (pathname.length() > maxSize) {// 文件大小
					return false;
				}
				return true;
			}
		});
		// 遍历目录取的文件信息
		List<Hashtable> fileList = new ArrayList<Hashtable>();
		if (files != null) {
			for (File file : files) {
				Hashtable obj = new Hashtable();
				String fileName=file.getName();
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				obj.put("is_dir", false);
				obj.put("has_file", false);
				obj.put("filesize", file.length());
				obj.put("is_photo", true);
				obj.put("filetype", fileExt);
				obj.put("filename", fileName);
				obj.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
				fileList.add(obj);
			}
		}
		// 排序形式，name or size or type
		String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";
		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}
		JSONObject result = new JSONObject();
		result.put("dir_path", "");
		result.put("moveup_dir_path", "");
		result.put("current_dir_path", "");
		result.put("current_url", saveUrl);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);
		try {
			response.getWriter().println(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public class NameComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filename")).compareTo((String) hashB.get("filename"));
			}
		}
	}

	public class SizeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long) hashA.get("filesize")) > ((Long) hashB.get("filesize"))) {
					return 1;
				} else if (((Long) hashA.get("filesize")) < ((Long) hashB.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	public class TypeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filetype")).compareTo((String) hashB.get("filetype"));
			}
		}
	}

	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toString();
	}

	private void sendMail(int ursId, String title, String content) throws Exception {
		List<Usr> list = new ArrayList<Usr>();
		Usr u = new Usr();
		u.setId(ursId);
		list.add(u);

		WFUtil.sendMailByIT(title, content, list);
	}

	public String retrans() throws Exception {
		wfRd = new WfRd();
		wfRd.setWfNo(wfQues.getWfNo());
		wfRd = new WfRdFacade().findBy(wfRd);
		return "retrans";
	}

	public String selcResponsible() throws Exception {
		if (isFromWf.intValue() == 1) {
			wfRd = new WfRd();
			wfRd.setWfNo(wfQues.getWfNo());
			wfRd = new WfRdFacade().findBy(wfRd);
			prjtNo = wfRd.getProjectNo();
		}
		return "selcResponsible";
	}

	public String upd() throws Exception {
		try {
			new WfQuesFacade().update(wfQues);
			setMsg(MSG.S_UPD);
		} catch (Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfQuesAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}


	private QuesResp quesResp;

	public String hangUp() throws Exception {
		try {
			wfQues.setStatus(MSG.WFQUES_STATUS_21);
			wfQues.setLastUpd(getUsrSession().getId());
			wfQues.setLastUpdDate(new Date());
			new WfQuesFacade().update(wfQues);

			quesResp.setQuesId(wfQues.getQuesId());
			// quesResp.setCreateBy(getUsrSession().getId());
			// quesResp.setCreateDate(new Date());
			quesResp.setLastUpd(getUsrSession().getId());
			quesResp.setLastUpdDate(new Date());
			new QuesRespFacade().update(quesResp);
			setMsg("已挂起");
		} catch (Exception e) {
			setMsg("挂起失败");
			Logger.getLogger(this.getClass()).error("WfQuesAction Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return MESSAGE;
	}

	/**
	 * 问题催办
	 * 
	 * @return
	 * @throws Exception
	 */
	public String rush() throws Exception {
		StringBuffer json = new StringBuffer("{\"msg\":");
		Date rushDate = new Date();
		
		String reshRemark = quesResp.getRemark();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String wfQuesSql = "select " + WfQues.SELF_FIELDS + " from GnWf.dbo.WfQues where QuesId = '" + wfQues.getQuesId() + "'";
		zrprjt.vo.Usr usr = new zrprjt.vo.Usr();
		usr.setId(quesResp.getUsrId());
		try {
			this.quesResp.setRushDate(rushDate);
			new QuesRespFacade().update(quesResp);
			// 获取路径
			ActionContext ctx = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
			String contextPath = request.getContextPath();
			String url = request.getRequestURL().toString();
			int index = url.indexOf(contextPath);
			String res_url = url.substring(0, index) + contextPath;

			// 给问题责任人发送邮件
			usr = (zrprjt.vo.Usr) new UsrFacade().findById(usr);
			wfQues = new WfQuesFacade().findById(wfQuesSql, WfQues.SELF_FIELDS);
			String prjectString = wfQues.getPrjtNm();
			StringBuffer title = new StringBuffer("催办问题通知");
			//title.append(this.wfQues.getTitle()).append("》的通知");
			StringBuffer content = new StringBuffer("尊敬的").append(usr.getUsrName()).append("先生/小姐:<p>您收到一个关于项目《").append(prjectString).append("》催办通知，</p></br>").append("问题描述：").append(wfQues.getQuesText()).append("</br>催办原因如下： ").append(reshRemark).append("请尽快解决，谢谢!</br>链接地址：<a href=").append(res_url).append("/WfQues!managerQues.shtml?wfQues.quesId=").append(this.wfQues.getQuesId()).append(">").append(res_url).append("/WfQues!managerQues.shtml?wfQues.quesId=").append(this.wfQues.getQuesId()).append("</a>");
			
			sendMail(usr.getId(), title.toString(), content.toString());
			String rushRemarkAndData =reshRemark + "---"+sdf.format(rushDate);
			json.append("\"已催办\"").append(",\"rushDate\":\"").append(rushRemarkAndData).append("\"}");
			setJson(json.toString());
			System.out.println(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
			json.append("\"催办异常\"}");
			setJson(json.toString());
		}
		return PGLIS;
	}
	/**
	 * 我的任务问题催办责任人
	 * 多责任人情况，先查处全部“待解决”和“验证未通过”的责任人，然后遍历发送邮件
	 * @return
	 * @throws Exception
	 */
	public String myRush() throws Exception {
		StringBuffer json = new StringBuffer("{\"msg\":");
		Date rushDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String wfQuesSql = "select " + WfQues.SELF_FIELDS + " from GnWf.dbo.WfQues where QuesId = " + wfQues.getQuesId();
		
		String sql = "SELECT  " + QuesResp.ALL_FIELDS + "  FROM dbo.QuesResp where (status = -1 or status = 0 ) and QuesId = "+ wfQues.getQuesId()+ " order by QuesId " ;
		
		zrprjt.vo.Usr usr = new zrprjt.vo.Usr();
		quesResp = new QuesRespFacade().findById(sql, QuesResp.ALL_FIELDS);
		List<QuesResp> userIds = new QuesRespFacade().find(sql, QuesResp.UsrId);
		List<Integer> nameStrs = new ArrayList<Integer>();
		
		try {
		for(QuesResp s:userIds){
		     nameStrs.add(s.getUsrId());
		     System.out.println("----------------------"+s.getUsrId()+"----------------------");
		
		     usr.setId(s.getUsrId());
		     this.quesResp.setRushDate(rushDate);
				new QuesRespFacade().update(quesResp);
				// 获取路径
				ActionContext ctx = ActionContext.getContext();
				HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
				String contextPath = request.getContextPath();
				String url = request.getRequestURL().toString();
				int index = url.indexOf(contextPath);
				String res_url = url.substring(0, index) + contextPath;

				// 给问题多个责任人发送邮件
				usr = (zrprjt.vo.Usr) new UsrFacade().findById(usr);
				wfQues = new WfQuesFacade().findById(wfQuesSql, WfQues.SELF_FIELDS);
				StringBuffer title = new StringBuffer("关于加快解决问题《");
				title.append(this.wfQues.getTitle()).append("》的通知");
				StringBuffer content = new StringBuffer("尊敬的").append(usr.getUsrName()).append("先生/小姐:<p>此问题提出已有一段时间了，请尽快解决，谢谢!</p>链接地址：<a href=").append(res_url).append("/WfQues!managerQues.shtml?wfQues.quesId=").append(this.wfQues.getQuesId()).append(">").append(res_url).append("/WfQues!managerQues.shtml?wfQues.quesId=").append(this.wfQues.getQuesId()).append("</a>");
				;
				sendMail(usr.getId(), title.toString(), content.toString());
		}
				json.append("\"已催办全部责任人\"").append(",\"rushDate\":\"").append(sdf.format(rushDate)).append("\"}");
				setJson(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
			json.append("\"催办异常\"}");
			setJson(json.toString());
		}
		return PGLIS;
	}

	
	/**
	 * 我的任务问题催办问题提出者
	 * 
	 * @return
	 * @throws Exception
	 */
	public String RushForIdtfBy() throws Exception {
			StringBuffer json = new StringBuffer("{\"msg\":");
			Date rushDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String wfQuesSql = "select " + WfQues.SELF_FIELDS + " from GnWf.dbo.WfQues where QuesId = " + wfQues.getQuesId();
			wfQues = new WfQuesFacade().findById(wfQuesSql, WfQues.SELF_FIELDS);
			zrprjt.vo.Usr usr = new zrprjt.vo.Usr();
			usr.setId(wfQues.getCreateBy());
			try {
				this.quesResp.setRushDate(rushDate);
				new QuesRespFacade().update(quesResp);
				// 获取路径
				ActionContext ctx = ActionContext.getContext();
				HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
				String contextPath = request.getContextPath();
				String url = request.getRequestURL().toString();
				int index = url.indexOf(contextPath);
				String res_url = url.substring(0, index) + contextPath;

				// 给问题责任人发送邮件
				usr = (zrprjt.vo.Usr) new UsrFacade().findById(usr);
				
				StringBuffer title = new StringBuffer("关于加快解决问题《");
				title.append(this.wfQues.getTitle()).append("》的通知");
				StringBuffer content = new StringBuffer("尊敬的").append(usr.getUsrName()).append("先生/小姐:<p>此问题提出已有一段时间了，请尽快解决，谢谢!</p>链接地址：<a href=").append(res_url).append("/WfQues!managerQues.shtml?wfQues.quesId=").append(this.wfQues.getQuesId()).append(">").append(res_url).append("/WfQues!managerQues.shtml?wfQues.quesId=").append(this.wfQues.getQuesId()).append("</a>");
				;
				sendMail(usr.getId(), title.toString(), content.toString());
				json.append("\"已催办问题提出者\"").append(",\"rushDate\":\"").append(sdf.format(rushDate)).append("\"}");
				setJson(json.toString());
			} catch (Exception e) {
				e.printStackTrace();
				json.append("\"催办异常\"}");
				setJson(json.toString());
			}
			return PGLIS;
		}
	
	// 问题提出人可以删除问题
	public String del() throws Exception {
		try {
			if(choices!=null){
				choices=choices.replace(",", "','");
				choices = "'"+choices+"'";
			}
			String sql = "select " + WfQues.SELF_FIELDS + " from WfQues where QuesId in (" + choices + ")";
			wfQuess = new WfQuesFacade().find(sql, WfQues.SELF_FIELDS);
			if (wfQuess != null && wfQuess.size() > 0) {
				for (int i = 0; i < wfQuess.size(); i++) {
					if (wfQuess.get(i) != null) {
						System.out.println(wfQuess.get(i).getCreateBy().intValue());
						System.out.println(getUsrSession().getId().intValue());
						if (wfQuess.get(i).getCreateBy().intValue() == getUsrSession().getId().intValue()) {
							if (wfQuess.get(i).getStatus() != 21 && wfQuess.get(i).getStatus() != 30 && wfQuess.get(i).getStatus() != 40) {
								new WfQuesFacade().del(wfQuess.get(i));
								if(wfQuess.get(i).getFileNo() != null && wfQuess.get(i).getFileNo().contains("/")) {
									ActionContext ctx = ActionContext.getContext();
									HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
									File deleteFile = new File(request.getSession().getServletContext().getRealPath
											(wfQuess.get(i).getFileNo() + "/" + wfQuess.get(i).getFileName()));
									deleteFile.delete();
								}
							} else {
								setMsg("只能删除未关闭的问题");
								return ERROR;
							}
						} else {
							setMsg("只有问题提出人可以删除问题");
							return ERROR;
						}
					}
				}
			}
			setMsg(MSG.S_DEL);
		} catch (Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfQuesAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}

	/**
	 * 单个关闭问题
	 * @return
	 * @throws Exception
	 */
	/*public String closeQues() throws Exception {
		try {
			if (wfQues != null && wfQues.getStatus() == MSG.WFQUES_STATUS_11) {
				wfQues.setStatus(MSG.WFQUES_STATUS_30);
				wfQues.setLastUpd(getUsrSession().getId());
				wfQues.setLastUpdDate(new Date());
				new WfQuesFacade().update(wfQues);
			} else {
				setMsg("问题未验证通过，不能关闭");
				return MESSAGE;
			}
			setMsg("已关闭");
		} catch (Exception e) {
			setMsg("关闭失败");
			Logger.getLogger(this.getClass()).error("WfQuesAction closeQues Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return MESSAGE;
	}*/
	
	
	
	/**
	 * 批量关闭问题
	 * @return
	 */
	/*public String bathCloseQues() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String[] quesids = this.choices.split(",");
		StringBuffer choicesBuffer = new StringBuffer();
		for(String quesid : quesids) {
			choicesBuffer.append("'").append(quesid).append("',");
		}
		choicesBuffer.deleteCharAt(choicesBuffer.length() - 1);
		try {
			String updateSql = "update WfQues set LastUpd = " + getUsrSession().getId() + ",LastUpdDate = '" 
								+ sdf.format(new Date()) + "',status = " + MSG.WFQUES_STATUS_30 
								+ " where QuesId in (" + choicesBuffer.toString() + ")";
			new WfQuesFacade().update(updateSql);
			setMsg("已关闭");
		} catch (Exception e) {
			setMsg("关闭失败");
			Logger.getLogger(this.getClass()).error("WfQuesAction bathCloseQues Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return MESSAGE;
	}*/
	//关闭问题弹出填写关闭原因页面
		public String managerCloseQuesReason() throws Exception {
			try {
				if (text != null) {
					text = URLDecoder.decode(text, "UTF-8");
				}
				syId = String.valueOf(getUsrSession().getSyId());
				syNm = getUsrSession().getSyNm();
				usrId = String.valueOf(getUsrSession().getId());
				usrNm = getUsrSession().getUsrName();
				wfQues = new WfQuesFacade().findById(wfQues);

				if (wfQues != null) {
					QuesResp quesResp = new QuesResp();
					quesResp.setQuesId(wfQues.getQuesId());
					List<QuesResp> qrs = new QuesRespFacade().find(quesResp);
					wfQues.setQuesRespList(qrs);
				}
				setPrjtRole();
			} catch (Exception e) {
				setMsg(MSG.F_SEA);
				e.printStackTrace();
				Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
				return ERROR;
			}
			return "managerCloseQuesReason";
		}
		/**
		 * 填写关闭原因并关闭问题
		 * @return
		 */
		public String labelQuesStatus() {
			try {
				String CloseQuesReason = ServletActionContext.getRequest().getParameter("wfQues.closeQuesReason");
				System.out.println(CloseQuesReason+"!!!!!!!!!!!");
				
				// 修改问题状态
				//WfQuesFacade wf = new WfQuesFacade();
				//System.out.println(wfQues.getQuesId()+"~~~~~~~~~~~~~");
				wfQues.setCloseQuesReason(CloseQuesReason);
				wfQues.setStatus(MSG.WFQUES_STATUS_30);
				wfQues.setLastUpd(getUsrSession().getId());
				wfQues.setLastUpdDate(new Date());
				new WfQuesFacade().update(wfQues);
				//跟新问题后跳转页面
				//System.out.println(task2.getTaskId()+"``333333333333``"+wfRd.getWfNo()+"``3333333333333``"+ws2.getStepId());
				
				setMsg("问题关闭成功");
			} catch (Exception e) {
				setMsg("问题转风险流程启动失败");
				e.printStackTrace();
				Logger.getLogger(this.getClass()).error("WfQuesAction labelRiskStatus Exception", e);
				return ERROR;
			}
			return MESSAGE;
		}
		
		//批量关闭问题弹出填写关闭原因页面
				public String batchManagerCloseQuesReason() throws Exception {
					try {
						if (text != null) {
							text = URLDecoder.decode(text, "UTF-8");
						}
						syId = String.valueOf(getUsrSession().getSyId());
						syNm = getUsrSession().getSyNm();
						usrId = String.valueOf(getUsrSession().getId());
						usrNm = getUsrSession().getUsrName();
						wfQues = new WfQuesFacade().findById(wfQues);

						if (wfQues != null) {
							QuesResp quesResp = new QuesResp();
							quesResp.setQuesId(wfQues.getQuesId());
							List<QuesResp> qrs = new QuesRespFacade().find(quesResp);
							wfQues.setQuesRespList(qrs);
						}
						String wfQuesIDs = ServletActionContext.getRequest().getParameter("wfQuesIDs");
						
						HttpSession session1 =ServletActionContext.getRequest().getSession();
						session1.setAttribute("wfQuesIDs",wfQuesIDs);
						
						System.out.println(wfQuesIDs);
						setPrjtRole();
					} catch (Exception e) {
						setMsg(MSG.F_SEA);
						e.printStackTrace();
						Logger.getLogger(this.getClass()).error("WfReplyAction Exception", e);
						return ERROR;
					}
					return "batchManagerCloseQuesReason";
				}
				/**
				 * 填写批量关闭原因并关闭问题
				 * @return
				 */
				public String batchLabelQuesStatus() {
					try {
						isReloadGrid = "true";
						String CloseQuesReason = ServletActionContext.getRequest().getParameter("wfQues.closeQuesReason");
						System.out.println(CloseQuesReason+"!!!!!!!!!!!");
						/*String CloseQuesReason = new String(CloseQuesReason1.getBytes("ISO-8859-1"),"UTF-8");
						System.out.println(CloseQuesReason+"!!!!!!!!!!!");*/
						String wfQuesIDs = ServletActionContext.getRequest().getParameter("wfQuesIDs");
						//System.out.println(wfQuesIDs+"!!!!!!!!!!!");
						
					    String[] wfQuesIDsList = wfQuesIDs.split(",");
					    
					    for (int i = 0; i < wfQuesIDsList.length; i++) {
				            System.out.println(wfQuesIDsList[i]);
				            WfQuesFacade wf = new WfQuesFacade();
				            wfQues.setQuesId(wfQuesIDsList[i]);
				            WfQues wq = wf.findById(wfQues);
				            wq.setStatus(MSG.WFQUES_STATUS_30);
				            wq.setCloseQuesReason(CloseQuesReason);
				            wq.setLastUpd(getUsrSession().getId());
				            wq.setLastUpdDate(new Date());
				            new WfQuesFacade().update(wq);
				        }
					    setMsg("问题关闭成功");
						
					} catch (Exception e) {
						setMsg("问题转风险流程启动失败");
						e.printStackTrace();
						Logger.getLogger(this.getClass()).error("WfQuesAction labelRiskStatus Exception", e);
						return ERROR;
					}
					return MESSAGE;
				}
			
	
	/**
	 * 批量开启问题
	 * @return
	 */
	public String bathOpenQues() {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String[] quesids = this.choices.split(",");
		StringBuffer choicesBuffer = new StringBuffer();
		for(String quesid : quesids) {
			choicesBuffer.append("'").append(quesid).append("',");
		}
		choicesBuffer.deleteCharAt(choicesBuffer.length() - 1);
		try {
			String updateSql = "update WfQues set status = " + MSG.WFQUES_STATUS_10 
								+ " where QuesId in (" + choicesBuffer.toString() + ")";
			new WfQuesFacade().update(updateSql);
			String updateQuesRespSql = "update QuesResp set status = " + MSG.QUESRESP_STATUS_1 
					+ " where QuesId in (" + choicesBuffer.toString() + ")";
			new QuesRespFacade().update(updateQuesRespSql);
			setMsg("问题已开启，该问题将回到待验证状态！");
		} catch (Exception e) {
			setMsg("开启失败");
			Logger.getLogger(this.getClass()).error("WfQuesAction bathCloseQues Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return MESSAGE;
	}
	/**
	 * 批量问题转风险
	 * @return
	 */
	
	
	
	/**
	 * 问题转风险评估，但未启动风险流程
	 * @return
	 */
/*	public String labelRiskStatus() {
		try {
			// 修改问题状态
			WfQuesFacade wf = new WfQuesFacade();
			wfQues.setStatus(MSG.WFQUES_STATUS_40);
			wfQues.setIsRiskQues(1);
			wfQues.setLastUpd(getUsrSession().getId());
			wfQues.setLastUpdDate(new Date());
			this.quesResp.setStatus(5);
			WfQues wq = wf.findById(wfQues);
			WfRisk wfRisk = new WfRisk(wq.getPrjtNo(), wq.getScheId(), wq.getQuesId(), wq.getDeptId(), 
					wq.getUsrName(), wq.getTitle(), wq.getQuesText(), wq.getCateId(), getUsrSession().getId(), 
					new Date(), new Date(),getUsrSession().getId());
			wfRisk.setStatus(MSG.WFRISK_STATUS_1);
			wfRisk.setWfNo(wq.getWfNo());
			if(wq.getFileNo() != null && wq.getFileName() != null) {
				wfRisk.setFileNo(wq.getFileNo());
				wfRisk.setFileName(wq.getFileName());
			}
			wfRisk.setRemark("此风险由问题转化过来的，问题的ID=" + wq.getQuesId());
			List<QuesResp> qrs = new QuesRespFacade().find("select QuesResp.UsrId"
					+ " from QuesResp where QuesResp.RespType = 1 and QuesResp.QuesId = '" 
					+ this.wfQues.getQuesId() + "'", "QuesResp.UsrId");
			StringBuffer usrIds = new StringBuffer();
			for(QuesResp qr : qrs) {
				usrIds.append(qr.getUsrId()).append(",");
			}
			usrIds.deleteCharAt(usrIds.length() - 1);
			wfRisk.setResponsibleUserId(usrIds.toString());
			wf.updateQuesAndSaveRisk(wfQues,this.quesResp,wfRisk);
			setMsg("问题转风险成功");
		} catch (Exception e) {
			setMsg("问题转风险失败");
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error("WfQuesAction labelRiskStatus Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}*/
	
	
	public String gotoRisk() {
		try {
		
		String[] quesids = this.choices.split(",");
		StringBuffer choicesBuffer = new StringBuffer();
		for(String quesid : quesids) {
			choicesBuffer.append("'").append(quesid).append("',");
		}
		choicesBuffer.deleteCharAt(choicesBuffer.length() - 1);
		System.out.println("quesids"+quesids);
		System.out.println("choicesBuffer"+choicesBuffer);
		for (int i=0;i<quesids.length;i++){
			 System.out.println(quesids[i]);
			 WfQuesFacade wf = new WfQuesFacade();
				wfQues.setStatus(MSG.WFQUES_STATUS_40);
				wfQues.setIsRiskQues(1);
				wfQues.setLastUpd(getUsrSession().getId());
				wfQues.setLastUpdDate(new Date());
				wfQues.setQuesId(quesids[i]);
				//wfQues.setQuesId(quesids[i]);
				QuesRespFacade qr1 = new QuesRespFacade();
				QuesResp qrs1 = qr1.findById("select "+QuesResp.ALL_FIELDS +" from QuesResp where QuesResp.QuesId = '"+quesids[i]+"'", QuesResp.ALL_FIELDS);
				qrs1.setStatus(5);
				qrs1.setQuesId(quesids[i]);
				//WfQues wq = wf.findById(wfQues);
				WfQues wq = wf.findById("select "+WfQues.ALL_LIST_FIELDS +" from WfQues where WfQues.QuesId = '"+quesids[i]+"'", WfQues.ALL_LIST_FIELDS);
				WfRisk wfRisk = new WfRisk(wq.getPrjtNo(), wq.getScheId(), wq.getQuesId(), wq.getDeptId(), 
						wq.getUsrName(), wq.getTitle(), wq.getQuesText(), wq.getCateId(), getUsrSession().getId(), 
						new Date(), new Date(),getUsrSession().getId());
				wfRisk.setStatus(MSG.WFRISK_STATUS_1);
				wfRisk.setWfNo(wq.getWfNo());
				if(wq.getFileNo() != null && wq.getFileName() != null) {
					wfRisk.setFileNo(wq.getFileNo());
					wfRisk.setFileName(wq.getFileName());
				}
				wfRisk.setRemark("此风险由问题转化过来的，问题的ID=" + quesids[i]);
				List<QuesResp> qrs = new QuesRespFacade().find("select QuesResp.UsrId"
						+ " from QuesResp where QuesResp.RespType = 1 and QuesResp.QuesId = '" 
						+ quesids[i] + "'", "QuesResp.UsrId");
				StringBuffer usrIds = new StringBuffer();
				for(QuesResp qr : qrs) {
					usrIds.append(qr.getUsrId()).append(",");
				}
				usrIds.deleteCharAt(usrIds.length() - 1);
				wfRisk.setResponsibleUserId(usrIds.toString());
				wf.updateQuesAndSaveRisk(wfQues,qrs1,wfRisk);
			}
		setMsg("问题转风险成功");
		} catch (Exception e) {
			setMsg("问题转风险失败");
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error("WfQuesAction labelRiskStatus Exception", e);
			return ERROR;
		}
		return MESSAGE;
		/*try {
			String updateSql = "update WfQues set LastUpd = " + getUsrSession().getId() + ",LastUpdDate = '" 
								+ sdf.format(new Date()) + "',status = " + MSG.WFQUES_STATUS_30 
								+ " where QuesId in (" + choicesBuffer.toString() + ")";
			new WfQuesFacade().update(updateSql);
			setMsg("已关闭");
		} catch (Exception e) {
			setMsg("关闭失败");
			Logger.getLogger(this.getClass()).error("WfQuesAction bathCloseQues Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return MESSAGE;*/
		
	}
	
	
	/**
	 * 风险问题列表页面
	 * @return
	 */
	public String riskQuesManagerUI() {
		try {
			schCfgs = new SchCfgFacade().find("select " + SchCfg.SELF_FIELDS + " from SchCfg", SchCfg.SELF_FIELDS);
			
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesAction Exception", e);
			return ERROR;
		}
		return "riskQuesManager";
		
	}
	/**
	 * 问题转风险审批流程，新增风险为作废状态，但未启动风险流程
	 * @return
	 */
	public String labelRiskStatus() {
		try {
			
			WfCfg wCfg1 = new WfCfgFacade().findById("select * from WfCfg where FlowCode = '57'", WfCfg.LIST);
			// 修改问题状态
			WfQuesFacade wf = new WfQuesFacade();
			wfQues.setStatus(MSG.WFQUES_STATUS_40);
			wfQues.setIsRiskQues(1);
			wfQues.setLastUpd(getUsrSession().getId());
			wfQues.setLastUpdDate(new Date());
			this.quesResp.setStatus(5);
			WfQues wq = wf.findById(wfQues);
			WfRisk wfRisk = new WfRisk(wq.getPrjtNo(), wq.getScheId(), wq.getQuesId(), wq.getDeptId(), 
					wq.getUsrName(), wq.getTitle(), wq.getQuesText(), wq.getCateId(), getUsrSession().getId(), 
					new Date(), new Date(),getUsrSession().getId());
			//```新增流程
			PrjtDef prjtDef = new PrjtDef();
			prjtDef.setPrjtNo(wq.getPrjtNo());
			PrjtDef pd = new PrjtDefFacade().findById(prjtDef);

			WfRd wfRd = new WfRd();
			StringBuffer wfName = new StringBuffer("项目《" + pd.getPrjtNm() + "》走风险审批流程");
			wfRd.setWfName(wfName.toString());
			wfRd.setProjectNo(wq.getPrjtNo());
			wfRd.setFlowId(wCfg1.getFlowId());
			wfRd.setStatus(MSG.CONST_STATUS_1);
			wfRd.setCreateBy(getUsrSession().getId());
			wfRd.setCreateDate(new Date());
			WfCfg wc = new WfCfg();
			wc.setFlowId(55);
			wc = new WfCfgFacade().findById(wc);
			if(wc != null){
				wfRd.setNeedQues(wc.getNeedQues());
			}
			new WfRdFacade().save(wfRd);
			
			WfRd wr = new WfRdFacade().findById(wfRd);
			
			//wfRisk.setStatus(MSG.WFRISK_STATUS_1);
			wfRisk.setStatus(MSG.WFRISK_STATUS_4);
			wfRisk.setWfNo(wr.getWfNo());
			
			
			String ImpTime = ServletActionContext.getRequest().getParameter("wfRisk.impTime");
			wfRisk.setImpTime(ImpTime);
			
			String RiskConsequence = ServletActionContext.getRequest().getParameter("wfRisk.riskConsequence");
			wfRisk.setRiskConsequence(RiskConsequence);
			Integer deptManagerID =Integer.parseInt( ServletActionContext.getRequest().getParameter("deptManagerID"));
			String PreventiveMeasures = ServletActionContext.getRequest().getParameter("wfRisk.preventiveMeasures");
			wfRisk.setPreventiveMeasures(PreventiveMeasures);
			
			
			
		
			
			
			if(wq.getFileNo() != null && wq.getFileName() != null) {
				wfRisk.setFileNo(wq.getFileNo());
				wfRisk.setFileName(wq.getFileName());
			}
			wfRisk.setRemark("此风险由问题转化过来的，问题的ID=" + wq.getQuesId());
			List<QuesResp> qrs = new QuesRespFacade().find("select QuesResp.UsrId"
					+ " from QuesResp where QuesResp.RespType = 1 and QuesResp.QuesId = '" 
					+ this.wfQues.getQuesId() + "'", "QuesResp.UsrId");
			StringBuffer usrIds = new StringBuffer();
			StringBuffer deptNms = new StringBuffer();
			for(QuesResp qr : qrs) {
				usrIds.append(qr.getUsrId()).append(",");
			}
			
			String str = usrIds.toString();
			str =str.substring(0,str.lastIndexOf(","));
	    	System.out.println("usrIds.toString()"+str); 
	    	String deptsqlString = "select Dept.DeptNm from dept INNER JOIN Usr ON Dept.deptid = Usr.OrgNo where Usr.id in ("+str+")";
			System.out.println("deptsqlString"+deptsqlString);
	    	List<Dept> depts = new zrprjt.facade.DeptFacade().find(deptsqlString, "Dept.DeptNm");
			System.out.println("depts"+depts.toString());
			 for (Dept dp : depts) {
		            System.out.println(dp.getDeptNm()+"````````````````"); 
		        }
			
			for(Dept dp : depts) {
				deptNms.append(dp.getDeptNm()).append(",");
			}
			System.out.println("11111444444444411111"+deptNms.toString());
			wfRisk.setDeptName(deptNms.toString());
			usrIds.deleteCharAt(usrIds.length() - 1);
			wfRisk.setResponsibleUserId(usrIds.toString());
			
			
			
			wf.updateQuesAndSaveRisk(wfQues,this.quesResp,wfRisk);
			
			
			// 新增一条走风险审批流程并走完第一步		
			// 发第一个任务给项目经理、跳转回任务页面
			//新增流程第一步
			WfRdTask task = new WfRdTask();
			task.setCreateBy(-1);
			task.setCreateDate(new Date());
			task.setReqDate(new Date());
			task.setEndDate(new Date());
			task.setAcceptBy(getUsrSession().getId());
			task.setAcceptDate(new Date());
			task.setStatus(MSG.OWFTASK_STATUS_2);
			task.setTaskType(MSG.OWFTASK_TYPE_1);
			task.setWfNo(wfRd.getWfNo());
			
			/*WfCfg wCfg = new WfCfg();
			wCfg.setFlowCode("57");*/
			
			
			WfStep ws = new WfStepFacade().findById("select "+WfStep.LIST +" from WfStep where FlowId = "+wCfg1.getFlowId()+" and Sort = 1", WfStep.LIST);
			WfStep ws2 = new WfStepFacade().findById("select "+WfStep.LIST +" from WfStep where FlowId = "+wCfg1.getFlowId()+" and Sort = 2", WfStep.LIST);
			//System.out.println("1111"+ws.getStepId());
			task.setStepId(ws.getStepId());
			
			 new WfRdTaskFacade().save(task);
			
			 task = new WfRdTaskFacade().findBy(task);
			
			/*WfRdTask wt = new WfRdTaskFacade().findById(task);*/
			//新增流程第二步
			WfRdTask task2 = new WfRdTask();
			task2.setCreateBy(getUsrSession().getId());
			task2.setCreateDate(new Date());
			task2.setReqDate(new Date());
			task2.setPreTaskId(task.getTaskId());
			task2.setAcceptBy(deptManagerID);
			
			task2.setAcceptDate(new Date());
			task2.setStatus(MSG.OWFTASK_STATUS_1);
			task2.setTaskType(MSG.OWFTASK_TYPE_1);
			task2.setWfNo(wfRd.getWfNo());
			task2.setStepId(ws2.getStepId());
			new WfRdTaskFacade().save(task2);
			
			task2 = new WfRdTaskFacade().findBy(task2);
			System.out.println(task2.getTaskId()+"``333333333333``"+wfRd.getWfNo()+"``3333333333333``"+ws2.getStepId());
			//this.sendMessage(MSG.S_SAV, "WfRdView!wfTaskView.shtml?wfRd.wfNo="+ wfRd.getWfNo()+"&currentTaskId="+task2.getTaskId()+"&taskStepId="+task2.getStepId());
			//+"&currentTaskId="+task2.getTaskId()+"&taskStepId="+task2.getStepId()+
			
			setMsg("message:问题转风险流程启动成功,getWfNo:"+wfRd.getWfNo()+",getTaskId:"+task2.getTaskId()+",getStepId:"+ws2.getStepId());
		} catch (Exception e) {
			setMsg("问题转风险流程启动失败");
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error("WfQuesAction labelRiskStatus Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	
	
	// 查找出风险审批流程的问题信息
			public String quesAndRisk() throws Exception {
				String wfNo = ServletActionContext.getRequest().getParameter("wfNo");
				System.out.println(wfNo);
				WfRisk wfRisk = new WfRiskFacade().findById("select * from WfRisk where WfRisk.WfNo = '"+wfNo+"'", WfRisk.SELF_FIELDS);
				String quesID = wfRisk.getQuesId();
				System.out.println("quesID55555555555"+quesID);
				WfQues wfQues = new WfQues();
				wfQues.setQuesId(quesID);
				//.findById(sql, WfQues.ALL_FIELDS)
				List<WfQues> wfQ = new WfQuesFacade().findAll(wfQues);
				WfQuesJson wqj = new WfQuesJson();
				wqj.Rows = wfQ;
				wqj.Total = wfQ.size();
			
				setJson(JSON.toJSONString(wqj));
				System.out.println("````````````````````"+JSON.toJSONString(wqj));
				return PGLIS;
			}
			// 查找出风险审批流程的问题信息和风险信息
			public String quesAndRisk2() throws Exception {
							String wfNo = ServletActionContext.getRequest().getParameter("wfNo");
							System.out.println(wfNo);
							
							/*WfRisk wRisk = new WfRisk();
							wRisk.setWfNo(wfNo);*/
							List<WfRisk> wfRisk = new WfRiskFacade().find("select * from WfRisk where WfRisk.WfNo = '"+wfNo+"'", WfRisk.SELF_FIELDS);
							
							
							WfRiskJson wfRiskJson = new WfRiskJson();
							wfRiskJson.Rows =  wfRisk;
							wfRiskJson.Total=wfRisk.size();
							
							
							setJson(JSON.toJSONString(wfRiskJson));
							System.out.println("````````````````````"+JSON.toJSONString(wfRiskJson));
							return PGLIS;
						}
				

	private String impQuesIds;
	private String action;
	/**
	 * 导入新问题   杨辉写的
	
	public String importNewQues(){
		try {
			if (fileInp != null) {
				ExcelContext<WfQues> context = new ExcelContext<WfQues>(new gnwf.parser.WfQuesExcelParser());
				List<WfQues> wfQuesList = context.parser(fileInp.getAbsolutePath());
				if(wfQuesList.size() == 0) {
					setMsg("问题Excel模板格式不正确或数据为空");
				}else {
					syId = String.valueOf(getUsrSession().getSyId());
					syNm = getUsrSession().getSyNm();
					usrId = String.valueOf(getUsrSession().getId());
					usrNm = getUsrSession().getUsrName();
					WfRd rd = new WfRd();
					rd.setWfNo(wfQues.getWfNo());
					rd = new WfRdFacade().findById(rd);
					List<Usr> usrlist = new ArrayList<Usr>();
					List<WfQues> saveList = new ArrayList<WfQues>();
					for(WfQues ques : wfQuesList) {
						if(ques.getTitle() == null || ques.getQuesText() == null || ques.getCateId() == null) {
							continue;
						}
						List<WfQues> qlist = new WfQuesFacade().find("select WfQues.QuesText,WfQues.Title"
								+ " from WfQues where WfQues.PrjtNo = '" + rd.getProjectNo() + "'", "WfQues.QuesText,WfQues.Title");
						for(WfQues q : qlist) {
							if(ques.getQuesText().equals(q.getQuesText())) {
								action = "WfQues!importNewQues.shtml";
								setMsg("不能重复导入！");
								return PGUPL;
							}
						}
						ques.setPrjtNo(rd.getProjectNo());
						ques.setWfNo(rd.getWfNo());
						ques.setScheId(rd.getScheId());
						ques.setCreateBy(getUsrSession().getId());
						ques.setIdtfBy(getUsrSession().getId());
						ques.setCreateDate(new Date());
						ques.setStatus(1);
						ques.setLastUpd(getUsrSession().getId());
						ques.setLastUpdDate(new Date());
						
						StringBuffer countSql = new StringBuffer("select count(schid) as amount from schcfg where schid =")
						.append(rd.getScheId())
						.append(" and parent in(select schid from schcfg where SchNm in ('小批试产','中批试产'))");
						int count = new SchCfgFacade().amount(countSql.toString());
						
						String userName  = ques.getUsrName();
						if(!Utils.StringUtils.isEmpty(userName)) {
							List<Usr> usrList = new zrsy.facade.UsrFacade().find("select distinct Usr.Id from Usr join prjtUsr on Usr.id = prjtUsr.UsrId where Usr.UsrName = '" + userName + "' and prjtUsr.PrjtNo = '" + rd.getProjectNo() + "'", "Usr.Id");
							if(usrList != null && usrList.size() == 1) {
								ques.setResponsibleIds(String.valueOf(usrList.get(0).getId()));
							}
						}
						if(ques.getResponsibleIds() == null) {
							if(count > 0) {//如果是小批试产和中批试产阶段，问题责任人为空就默认交给DQA
								List<PrjtUsr> prjtUsrList = new PrjtUsrFacade().find("select PrjtUsr.UsrId from PrjtUsr join PrjtRole on PrjtUsr.RoleId = PrjtRole.RoleId" +
										" where PrjtRole.RoleNm = 'DQA' and PrjtUsr.PrjtNo = '" + rd.getProjectNo() + "'", "PrjtUsr.UsrId");
								Usr usr = new zrsy.facade.UsrFacade().findById("select UsrName from Usr where id = " + prjtUsrList.get(0).getUsrId(), "Usr.UsrName");
								ques.setResponsibleIds(String.valueOf(prjtUsrList.get(0).getUsrId()));
								ques.setUsrName(usr.getUsrName());
							}else {//设计阶段问题 默认交给流程发起人
								ques.setResponsibleIds(String.valueOf(rd.getCreateBy()));
								Usr usr;
								if(rd.getCreateBy()==-1){//此流程为系统驱动开启
									List<PrjtUsr> prjtUsrList = new PrjtUsrFacade().find("select PrjtUsr.UsrId from PrjtUsr join PrjtRole on PrjtUsr.RoleId = PrjtRole.RoleId" +
											" where PrjtRole.RoleNm = '项目经理' and PrjtUsr.PrjtNo = '" + rd.getProjectNo() + "'", "PrjtUsr.UsrId");
									usr = new zrsy.facade.UsrFacade().findById("select UsrName from Usr where id = " + prjtUsrList.get(0).getUsrId(), "Usr.UsrName");
								}else{
									 usr = new zrsy.facade.UsrFacade().findById("select UsrName from Usr where id = " + rd.getCreateBy(), "Usr.UsrName");
								}
								ques.setUsrName(usr.getUsrName());
							}
						}
						Usr usr = new Usr();
						usr.setId(Integer.valueOf(ques.getResponsibleIds()));
						usrlist.add(usr);
						
						saveList.add(ques);
					}
					ActionContext ctx = ActionContext.getContext();
					HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
					impQuesIds = new WfQuesFacade().save(saveList,request);
					List<PrjtUsr> prjtUsrList = new PrjtUsrFacade().find("select PrjtUsr.UsrId from PrjtUsr join PrjtRole on PrjtUsr.RoleId = PrjtRole.RoleId"
	                         + " where PrjtRole.RoleNm in('DQA','项目经理') and PrjtUsr.PrjtNo = '"
	                         + rd.getProjectNo() + "'","PrjtUsr.UsrId");
					for(PrjtUsr pu : prjtUsrList) {
						Usr u = new Usr();
						u.setId(pu.getUsrId());
						usrlist.add(u);
					}
					String title = "工作流(" + rd.getWfName() + ")-新增问题通知";
					String content = "尊敬的同事，您好：<p>此工作流新增了" + saveList.size() + "个问题,请您及时关注。</p>链接地址 ： <a href=" + getAppcationURL() + "/WfQues!taskList.shtml?prjtNo="
									+ rd.getProjectNo() + "&wfNo=" + wfQues.getWfNo() + ">点击进入</a><p>问题编号为:" + impQuesIds + "</p>";
					WFUtil.sendMailByIT(title.toString(), content.toString(), usrlist);
					setMsg(MSG.S_IMP + "(共导入" + saveList.size() + "条问题)");
				}
			}
		}catch(IOException e) {
			setMsg("IO异常");
			Logger.getLogger(this.getClass()).error("WfQuesAction importNewQues IOException",e);
			e.printStackTrace();
		} catch (ExcelFormatIncorrectException e) {
			setMsg("文件不是Excel格式");
			Logger.getLogger(this.getClass()).error("WfQuesAction importNewQues ExcelFormatIncorrectException", e);
			e.printStackTrace();
		} catch (Exception e) {
			setMsg("数据库访问出错");
			Logger.getLogger(this.getClass()).error("WfQuesAction importNewQues Exception", e);
			e.printStackTrace();
		}
		action = "WfQues!importNewQues.shtml";
		return PGUPL;
	}
	 */
	
	/**
	 * 导入新问题王小红
	 * @return
	 */
	public String importNewQues2222(){
		try {
			boolean flag = true;
			//收集导入的所有责任人
			List<String> quesUserNamelist = new  ArrayList<String>();
			if (fileInp != null) {
				ExcelContext<WfQues> context = new ExcelContext<WfQues>(new gnwf.parser.WfQuesExcelParser());
				List<WfQues> wfQuesList = context.parser(fileInp.getAbsolutePath());
				if(wfQuesList.size() == 0) {
					setMsg("问题Excel模板格式不正确或数据为空");
				}else {
					syId = String.valueOf(getUsrSession().getSyId());
					syNm = getUsrSession().getSyNm();
					usrId = String.valueOf(getUsrSession().getId());
					usrNm = getUsrSession().getUsrName();
//					WfRd rd = new WfRd();
//					rd.setWfNo(wfQues.getWfNo());
//					rd = new WfRdFacade().findById(rd);
					List<Usr> usrlist = new ArrayList<Usr>();
					List<WfQues> saveList = new ArrayList<WfQues>();
					for(WfQues ques : wfQuesList) {
						if(ques.getQuesText() == null ) {
							continue;
						}
						if(ques.getCateId() == null) {
							setMsg("问题等级不能为空");
							flag = false;
							break;
						}
						if(ques.getScheId() == null) {
							setMsg("提出阶段不能为空");
							flag = false;
							break;
						}
						/*if(ques.getUsrName() == null) {
							setMsg("责任人不能为空");
							flag = false;
							break;
						}*/
						if(ques.getSourceID() == null) {
							setMsg("问题来源不能为空");
							flag = false;
							break;
						}
						if(ques.getQuesTypeID() == null) {
							setMsg("问题类别不能为空");
							flag = false;
							break;
						}
						quesUserNamelist.add(ques.getUsrName());
						List<WfQues> qlist = new WfQuesFacade().find("select WfQues.QuesText,WfQues.Title"
								+ " from WfQues where WfQues.PrjtNo = '" + wfQues.getPrjtNo() + "'", "WfQues.QuesText,WfQues.Title");
						for(WfQues q : qlist) {
							if(ques.getQuesText().equals(q.getQuesText())) {
								action = "WfQues!importNewQues.shtml";
								setMsg("不能重复导入！");
								return PGUPL;
							}
						}
						ques.setPrjtNo(wfQues.getPrjtNo());
						//ques.setWfNo(wfQues.getWfNo());
						//ques.setScheId(wfQues.getScheId());
						ques.setCreateBy(getUsrSession().getId());
						ques.setIdtfBy(getUsrSession().getId());
						ques.setCreateDate(new Date());
						//System.out.println("@@@@@@@@@@@@"+ques.getQuesAnalysis());
						if (ques.getQuesAnalysis() == null) {
							ques.setStatus(1);
						}else {
							ques.setStatus(10);
						}
						//ques.setStatus(1);
						ques.setLastUpd(getUsrSession().getId());
						ques.setLastUpdDate(new Date());
//						StringBuffer countSql = new StringBuffer("select count(schid) as amount from schcfg where schid =")
//						.append(wfQues.getScheId())
//						.append(" and parent in(select schid from schcfg where SchNm in ('小批试产','中批试产'))");
//						int count = new SchCfgFacade().amount(countSql.toString());
						
						String userName  = ques.getUsrName();
						if(!Utils.StringUtils.isEmpty(userName)) {
							List<Usr> usrList = new zrsy.facade.UsrFacade().find("select distinct Usr.Id from Usr join prjtUsr on Usr.id = prjtUsr.UsrId where Usr.UsrName = '" + userName + "' and prjtUsr.PrjtNo = '" + wfQues.getPrjtNo() + "'", "Usr.Id");
							if(usrList != null && usrList.size() == 1) {
								ques.setResponsibleIds(String.valueOf(usrList.get(0).getId()));
							}
						}
						if(ques.getResponsibleIds() == null) {
							//问题责任人为空就默认交给项目经理
								List<PrjtUsr> prjtUsrList = new PrjtUsrFacade().find("select PrjtUsr.UsrId from PrjtUsr join PrjtRole on PrjtUsr.RoleId = PrjtRole.RoleId" +
										" where PrjtRole.RoleNm = '项目经理' and PrjtUsr.PrjtNo = '" + wfQues.getPrjtNo() + "'", "PrjtUsr.UsrId");
								Usr usr = new zrsy.facade.UsrFacade().findById("select UsrName from Usr where id = " + prjtUsrList.get(0).getUsrId(), "Usr.UsrName");
								ques.setResponsibleIds(String.valueOf(prjtUsrList.get(0).getUsrId()));
								ques.setUsrName(usr.getUsrName());
						}
						Usr usr = new Usr();
						usr.setId(Integer.valueOf(ques.getResponsibleIds()));
						usrlist.add(usr);
						
						saveList.add(ques);
					}
					System.out.println(quesUserNamelist.toString()+"~~~~~责任人~~~~~");
					if (flag) {
						ActionContext ctx = ActionContext.getContext();
						HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
						impQuesIds = new WfQuesFacade().save(saveList,request);
						List<PrjtUsr> prjtUsrList = new PrjtUsrFacade().find("select PrjtUsr.UsrId from PrjtUsr join PrjtRole on PrjtUsr.RoleId = PrjtRole.RoleId"
		                         + " where PrjtRole.RoleNm in('DQA','项目经理') and PrjtUsr.PrjtNo = '"
		                         + wfQues.getPrjtNo() + "'","PrjtUsr.UsrId");
						for(PrjtUsr pu : prjtUsrList) {
							Usr u = new Usr();
							u.setId(pu.getUsrId());
							usrlist.add(u);
						}
						String title = "新增问题通知";
						String content = "尊敬的同事，您好：<p>项目"+wfQues.getPrjtNm()+"新增了" + saveList.size() + "个问题,请您及时关注。</p>链接地址 ： <a href=" + getAppcationURL() + "/WfQues!taskList.shtml?prjtNo="
										+ wfQues.getPrjtNo() + ">点击进入</a><p>问题编号为:" + impQuesIds + "</p>";
						WFUtil.sendMailByIT(title.toString(), content.toString(), usrlist);
						setMsg(MSG.S_IMP + "(共导入" + saveList.size() + "条问题)");
					}else {
						return "message";
					}
					
				}
			}
		}catch(IOException e) {
			setMsg("IO异常");
			Logger.getLogger(this.getClass()).error("WfQuesAction importNewQues IOException",e);
			e.printStackTrace();
		} catch (ExcelFormatIncorrectException e) {
			setMsg("文件不是Excel格式");
			Logger.getLogger(this.getClass()).error("WfQuesAction importNewQues ExcelFormatIncorrectException", e);
			e.printStackTrace();
		} catch (Exception e) {
			setMsg("数据库访问出错");
			Logger.getLogger(this.getClass()).error("WfQuesAction importNewQues Exception", e);
			e.printStackTrace();
		}
		action = "WfQues!importNewQues.shtml";
		return PGUPL;
	}
	
	
	/**
	 * 批量导入新问题
	 * @return
	 */
	public String importNewQues(){
		try {
			boolean flag = true;
			//List<String> userEmailList = new ArrayList<String>();
			if (fileInp != null) {
				ExcelContext<WfQues> context = new ExcelContext<WfQues>(new gnwf.parser.WfQuesExcelParser());
				List<WfQues> wfQuesList = context.parser(fileInp.getAbsolutePath());
				if(wfQuesList.size() == 0) {
					setMsg("问题Excel模板格式不正确或数据为空");
				}else {
					syId = String.valueOf(getUsrSession().getSyId());
					syNm = getUsrSession().getSyNm();
					usrId = String.valueOf(getUsrSession().getId());
					usrNm = getUsrSession().getUsrName();
//					WfRd rd = new WfRd();
//					rd.setWfNo(wfQues.getWfNo());
//					rd = new WfRdFacade().findById(rd);
					List<Usr> usrlist = new ArrayList<Usr>();
					List<WfQues> saveList = new ArrayList<WfQues>();
					for(WfQues ques : wfQuesList) {
						if(ques.getQuesText() == null ) {
							continue;
						}
						if(ques.getCateId() == null) {
							setMsg("等级不能为空");
							flag = false;
							break;
						}
						if(ques.getScheId() == null) {
							setMsg("阶段不能为空");
							flag = false;
							break;
						}
						if(ques.getSourceID() == null) {
							setMsg("来源不能为空");
							flag = false;
							break;
						}
						if(ques.getQuesTypeID() == null) {
							setMsg("分类不能为空");
							flag = false;
							break;
						}
						//判断是否重复导入问题
						List<WfQues> qlist = new WfQuesFacade().find("select WfQues.QuesText,WfQues.Title"
								+ " from WfQues where WfQues.PrjtNo = '" + wfQues.getPrjtNo() + "'", "WfQues.QuesText,WfQues.Title");
						for(WfQues q : qlist) {
							if(ques.getQuesText().equals(q.getQuesText())) {
								action = "WfQues!importNewQues.shtml";
								setMsg("不能重复导入！");
								return PGUPL;
							}
						}
						ques.setPrjtNo(wfQues.getPrjtNo());
						//ques.setWfNo(wfQues.getWfNo());
						//ques.setScheId(wfQues.getScheId());
						ques.setCreateBy(getUsrSession().getId());
						System.out.println(ques.getIdtfName());
						//提交人如果为空则验证人为问题提交人，否则验证人为提交人栏上该提交栏上的人
						if (ques.getIdtfName() == null) {
							ques.setIdtfBy(getUsrSession().getId());
						}else {
							List<Usr> usrList12 = new zrsy.facade.UsrFacade().find("select distinct Usr.Id from Usr join prjtUsr on Usr.id = prjtUsr.UsrId where Usr.UsrName = '" + ques.getIdtfName() + "' and prjtUsr.PrjtNo = '" + wfQues.getPrjtNo() + "'", "Usr.Id");
							if(usrList12.size() == 0) {
								setMsg("提交人需要在项目组中");
								flag = false;
								break;
							}else {
								ques.setIdtfBy(Integer.valueOf(usrList12.get(0).getId()));
							}	
							
						}
						
						//ques.setIdtfBy(getUsrSession().getId());
						ques.setCreateDate(new Date());
						ques.setLastUpd(getUsrSession().getId());
						ques.setLastUpdDate(new Date());
						//判断导入的问题是否已经带解决措施Result
						//System.out.println(ques.getQuesMeasures());
						if (ques.getQuesMeasures() == null) {
							ques.setStatus(1);
						}else {
							ques.setStatus(10);
						}
						
						String userName  = ques.getUsrName();
						
				        
				       // List<String> usList = new ArrayList<>();
						if(!Utils.StringUtils.isEmpty(userName)) {
							List<Usr> usrList = new zrsy.facade.UsrFacade().find("select distinct Usr.Id from Usr join prjtUsr on Usr.id = prjtUsr.UsrId where Usr.UsrName = '" + userName + "' and prjtUsr.PrjtNo = '" + wfQues.getPrjtNo() + "'", "Usr.Id");
							if(usrList != null && usrList.size() == 1) {
								ques.setResponsibleIds(String.valueOf(usrList.get(0).getId()));
								Usr usr = new Usr();
								usr.setId(Integer.valueOf(ques.getResponsibleIds()));
								usrlist.add(usr);
							}else if (usrList.size() > 1) {
								String[] userNameArray = userName.replace("，", ",").split(",");
								for (int i = 0; i < userNameArray.length; i++) {
						            System.out.println(userNameArray[i]);
										List<Usr> usrList2 = new zrsy.facade.UsrFacade().find("select distinct Usr.Id from Usr join prjtUsr on Usr.id = prjtUsr.UsrId where Usr.UsrName = '" + userNameArray[i] + "' and ( prjtUsr.PrjtNo = '" + wfQues.getPrjtNo() + "' or prjtUsr.PrjtNo is null)", "Usr.Id");
										System.out.println(usrList2.get(0).getId());
										ques.setResponsibleIds(String.valueOf(usrList2.get(0).getId()));
										Usr usr = new Usr();
										usr.setId(Integer.valueOf(ques.getResponsibleIds()));
										usrlist.add(usr);
						        }
							}
						}
				        
						if(ques.getResponsibleIds() == null) {
							//问题责任人为空就默认交给项目经理
								List<PrjtUsr> prjtUsrList = new PrjtUsrFacade().find("select PrjtUsr.UsrId from PrjtUsr join PrjtRole on PrjtUsr.RoleId = PrjtRole.RoleId" +
										" where PrjtRole.RoleNm = '项目经理' and PrjtUsr.PrjtNo = '" + wfQues.getPrjtNo() + "'", "PrjtUsr.UsrId");
								Usr usr = new zrsy.facade.UsrFacade().findById("select UsrName from Usr where id = " + prjtUsrList.get(0).getUsrId(), "Usr.UsrName");
								ques.setResponsibleIds(String.valueOf(prjtUsrList.get(0).getUsrId()));
								ques.setUsrName(usr.getUsrName());
								Usr usr1 = new Usr();
								usr1.setId(Integer.valueOf(ques.getResponsibleIds()));
								usrlist.add(usr1);
						}
						System.out.println(usrlist.toString()+"@@@usrlist.toString()");
						saveList.add(ques);
					}
				
					if (flag) {
						ActionContext ctx = ActionContext.getContext();
						HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
						impQuesIds = new WfQuesFacade().save(saveList,request);
						//发邮件通知DQA
						List<PrjtUsr> prjtUsrList = new PrjtUsrFacade().find("select PrjtUsr.UsrId from PrjtUsr join PrjtRole on PrjtUsr.RoleId = PrjtRole.RoleId"
		                         + " where PrjtRole.RoleNm in('DQA') and PrjtUsr.PrjtNo = '"
		                         + wfQues.getPrjtNo() + "'","PrjtUsr.UsrId");
						for(PrjtUsr pu : prjtUsrList) {
							Usr u = new Usr();
							u.setId(pu.getUsrId());
							usrlist.add(u);
						}
						
						String title = "新增问题通知";
						/*String content = "尊敬的同事，您好：<p>项目"+wfQues.getPrjtNm()+"新增了" + saveList.size() + "个问题,请您及时关注。</p>链接地址 ： <a href=" + getAppcationURL() + "/WfQues!taskList.shtml?prjtNo="
										+ wfQues.getPrjtNo() + ">点击进入</a><p>问题编号为:" + impQuesIds + "</p>";*/
						String content = "尊敬的同事，您好：<p>项目"+wfQues.getPrjtNm()+"新增了问题,请您及时关注。</p>链接地址 ： <a href=" + getAppcationURL() + "/WfQues!taskList.shtml?prjtNo="
								+ wfQues.getPrjtNo() + ">点击进入</a><p>问题编号为:" + impQuesIds + "</p>";
						WFUtil.sendMailByIT(title.toString(), content.toString(), usrlist);
						setMsg(MSG.S_IMP + "(共导入" + saveList.size() + "条问题)");
					}else {
						return "message";
					}
					
				}
			}
		}catch(IOException e) {
			setMsg("IO异常");
			Logger.getLogger(this.getClass()).error("WfQuesAction importNewQues IOException",e);
			e.printStackTrace();
		} catch (ExcelFormatIncorrectException e) {
			setMsg("文件不是Excel格式");
			Logger.getLogger(this.getClass()).error("WfQuesAction importNewQues ExcelFormatIncorrectException", e);
			e.printStackTrace();
		} catch (Exception e) {
			setMsg("数据库访问出错");
			Logger.getLogger(this.getClass()).error("WfQuesAction importNewQues Exception", e);
			e.printStackTrace();
		}
		action = "WfQues!importNewQues.shtml";
		return PGUPL;
	}
	
	
	
	
	private String fromQuesManger;
	/**
	 * 导入解决措施
	 * @return
	 */
	public String impSovleQues(){
		try {
			if(fileInp != null) {
				WfQuesFacade wf = new WfQuesFacade();
				boolean flag = true;
				ExcelContext<WfQues> context = new ExcelContext<WfQues>(new gnwf.parser.WfQuesExcelParser());
				List<WfQues> wfQuesList = context.parser(fileInp.getAbsolutePath());
				if(wfQuesList.size() == 0) {
					setMsg("问题Excel模板格式不正确或数据为空");
				}else {
					List<QuesResp> quesRespList = new ArrayList<QuesResp>();
					List<WfQues> saveList = new ArrayList<WfQues>();
					for(WfQues wq : wfQuesList) {
						if(Utils.StringUtil.isNullOrEmpty(wq.getQuesMeasures())) {
							continue;
						}
						if(wq.getQuesId() == null) {
							setMsg("问题ID不能为空");
							flag = false;
							break;
						}
						WfQues wqSnap = new WfQues();
						wqSnap.setQuesId(wq.getQuesId());
						wqSnap = wf.findById(wqSnap);
						if(!wq.getUsrName().equals(getUsrSession().getUsrName())){
							setMsg("责任人不是当前用户");
							flag = false;
							break;
						}else if(wqSnap== null || wqSnap.getStatus() == 30 || wqSnap.getStatus() == 40){
							setMsg("问题ID不存在或问题已关闭或转风险");
							flag = false;
							break;
						}
						WfQues saveQues = new WfQues();
						saveQues.setQuesId(wq.getQuesId());
						saveQues.setLastUpd(getUsrSession().getId());
						saveQues.setLastUpdDate(new Date());
						saveList.add(saveQues);
						QuesResp qr = new QuesResp();
						qr.setResult(wq.getQuesMeasures());
						qr.setQuesAnalysis(wq.getQuesAnalysis());
						qr.setResultDate(new Date());
						qr.setIdtfRes("");
						qr.setLastUpd(getUsrSession().getId());
						qr.setLastUpdDate(new Date());
						qr.setQuesId(wq.getQuesId());
						qr.setUsrId(getUsrSession().getId());
						qr.setStatus(MSG.QUESRESP_STATUS_1);
						quesRespList.add(qr);
					}
					if(flag && saveList.size() > 0) {
						wf.updateWfQuesAndQuesResp(saveList,quesRespList);
						setMsg("导入成功(共" + saveList.size() + "条记录)");
					}else if(flag && saveList.size() == 0){
						setMsg("请检查是否没有填写解决措施");
					}
					return "message";
				}
			}
		} catch(IOException e) {
			setMsg("IO异常");
			Logger.getLogger(this.getClass()).error("WfQuesAction impSovleQues IOException",e);
			e.printStackTrace();
		}catch (ExcelFormatIncorrectException e) {
			setMsg("文件不是Excel格式");
			Logger.getLogger(this.getClass()).error("WfQuesAction impSovleQues ExcelFormatIncorrectException", e);
			e.printStackTrace();
		}catch (Exception e) {
			setMsg("导入失败");
			Logger.getLogger(this.getClass()).error("WfQuesAction impSovleQues Exception", e);
			e.printStackTrace();
		}
		action = "WfQues!impSovleQues.shtml";
		return PGUPL;
	}

	/**
	 * DQA批量导入解决措施（允许DQA在不是责任人情况下批量导入解决措施）
	 * @return
	 */
	public String impSovleQuesByDQA(){
		try {
			if(fileInp != null) {
				WfQuesFacade wf = new WfQuesFacade();
				boolean flag = true;
				ExcelContext<WfQues> context = new ExcelContext<WfQues>(new gnwf.parser.WfQuesExcelParser());
				List<WfQues> wfQuesList = context.parser(fileInp.getAbsolutePath());
				if(wfQuesList.size() == 0) {
					setMsg("问题Excel模板格式不正确或数据为空");
				}else {
					List<QuesResp> quesRespList = new ArrayList<QuesResp>();
					List<WfQues> saveList = new ArrayList<WfQues>();
					for(WfQues wq : wfQuesList) {
						if(Utils.StringUtil.isNullOrEmpty(wq.getQuesMeasures())) {
							continue;
						}
						if(wq.getQuesId() == null) {
							setMsg("问题ID不能为空");
							flag = false;
							break;
						}
						WfQues wqSnap = new WfQues();
						wqSnap.setQuesId(wq.getQuesId());
						wqSnap = wf.findById(wqSnap);
						if(wqSnap== null || wqSnap.getStatus() == 30 || wqSnap.getStatus() == 40){
							setMsg("问题ID不存在或问题已关闭或转风险");
							flag = false;
							break;
						}
						WfQues saveQues = new WfQues();
						saveQues.setQuesId(wq.getQuesId());
						saveQues.setLastUpd(getUsrSession().getId());
						saveQues.setLastUpdDate(new Date());
						saveList.add(saveQues);
						QuesResp qr = new QuesResp();
						qr.setResult(wq.getQuesMeasures());
						qr.setQuesAnalysis(wq.getQuesAnalysis());
						qr.setResultDate(new Date());
						qr.setIdtfRes("");
						qr.setLastUpd(getUsrSession().getId());
						qr.setLastUpdDate(new Date());
						qr.setQuesId(wq.getQuesId());
						//qr.setUsrId(getUsrSession().getId());
						qr.setStatus(MSG.QUESRESP_STATUS_1);
						quesRespList.add(qr);
					}
					if(flag && saveList.size() > 0) {
						wf.updateWfQuesAndQuesRespByDQA(saveList,quesRespList);
						setMsg("导入成功(共" + saveList.size() + "条记录)");
					}else if(flag && saveList.size() == 0){
						setMsg("请检查是否没有填写解决措施");
					}
					return "message";
				}
			}
		} catch(IOException e) {
			setMsg("IO异常");
			Logger.getLogger(this.getClass()).error("WfQuesAction impSovleQues IOException",e);
			e.printStackTrace();
		}catch (ExcelFormatIncorrectException e) {
			setMsg("文件不是Excel格式");
			Logger.getLogger(this.getClass()).error("WfQuesAction impSovleQues ExcelFormatIncorrectException", e);
			e.printStackTrace();
		}catch (Exception e) {
			setMsg("导入失败");
			Logger.getLogger(this.getClass()).error("WfQuesAction impSovleQues Exception", e);
			e.printStackTrace();
		}
		action = "WfQues!impSovleQuesByDQA.shtml";
		return PGUPL;
	}
	/**
	 * 导出问题
	 * @return
	 * @throws Exception
	 */
	public String export() throws Exception {
		List<WfQues> wfQuess = null;
		String sql = "";
		try {
			if(this.choices == null) {
				if(this.wfQues.getSelectType() != null) {
					if(this.wfQues.getSelectType() == 1) {
						sql = "select "+ WfQues.WFQUES_QUESRESP + " from WfQues join  QuesResp on "
							    + "WfQues.QuesId = QuesResp.QuesId where (QuesResp.UsrId = " 
							    + getUsrSession().getId() + " and (QuesResp.Status =0 or QuesResp.Status =-1"
							    + ") and QuesResp.RespType=1 and WfQues.Status != 30 and WfQues.Status != 40)";
					}else if(this.wfQues.getSelectType() == 2) {
						sql = "select " + WfQues.WFQUES_QUESRESP + " from WfQues join  QuesResp on WfQues.QuesId = QuesResp.QuesId " + "where QuesResp.UsrId = " + getUsrSession().getId() 
								+ " and (QuesResp.Status = 1 or QuesResp.Status =2 or QuesResp.Status = 3 or WfQues.status = 40) and QuesResp.RespType=1";
					}else if(this.wfQues.getSelectType() == 3) {
						sql = "select " + WfQues.WFQUES_QUESRESP + " from wfques join quesresp on wfques.quesid = quesresp.quesid where WfQues.CreateBy = " + getUsrSession().getId();
					}else if(this.wfQues.getSelectType() == 4) {
						sql = "select " + WfQues.WFQUES_QUESRESP + " from WfQues join  QuesResp on "
						           + "WfQues.QuesId = QuesResp.QuesId where QuesResp.IdtfBy = " + getUsrSession().getId()
						           + " and (QuesResp.Status = 1 or QuesResp.Status = 3) and QuesResp.RespType=1 and WfQues.Status != 30";
					}
					if (wfQues.getPrjtNo() != null && !wfQues.getPrjtNo().trim().isEmpty() && !wfQues.getPrjtNo().trim().equals("ALL")) {
						sql += " and WfQues.PrjtNo = '" + wfQues.getPrjtNo().trim() + "'";
					}
					sql += " order by quesid asc";
					wfQuess = new WfQuesFacade().find(sql,WfQues.WFQUES_QUESRESP);
				}else {
					ActionContext ctx = ActionContext.getContext();
					HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
					String title = new String(request.getParameter("title").getBytes("iso8859-1"),"utf-8");
					String usrName = new String(request.getParameter("usrName").getBytes("iso8859-1"),"utf-8");
					if(this.wfQues == null)this.wfQues = new WfQues();
					if(title != null && !"".equals(title))this.wfQues.setTitle(title);
					if(usrName != null && !"".equals(usrName))this.wfQues.setUsrName(usrName);
					if (wfQues.getPrjtNo() == null || wfQues.getPrjtNo().equals("ALL") || Utils.StringUtils.isEmpty(wfQues.getPrjtNo())) {
						setPrjtNoList();	
						wfQues.setPrjtNo(null);
					}
					if(this.wfQues.getScheId() != null) {
						StringBuffer schIds = new StringBuffer();
						schIds.append(this.wfQues.getScheId()).append(",");
						String schSql = "select SchCfg.SchId from SchCfg where Parent = " + this.wfQues.getScheId();
						List<SchCfg> scs = new SchCfgFacade().find(schSql, "SchCfg.SchId");
						if(scs != null && scs.size() > 0) {
							for(SchCfg sc : scs) {
								schIds.append(sc.getSchId()).append(",");
							}
						}
						schIds.deleteCharAt(schIds.length() - 1);
						this.wfQues.setScheIds(schIds.toString());
					}
					wfQues.setCurrentUsr(getUsrSession().getId());
					wfQuess = getWfQuesList(new WfQuesFacade().find(wfQues));
				}
			}else {
				choices=choices.replace(",", "','");
				choices = "'"+choices+"'";
				sql = "select " + WfQues.WFQUES_QUESRESP + " from WfQues left join quesresp on wfques.quesid = quesresp.quesid where QuesResp.RespType = 1 and WfQues.QuesId in (" + this.choices + ")";
				if(this.wfQues.getSelectType() != null && this.wfQues.getSelectType() == 1) {
					sql += "and quesresp.usrid = " + getUsrSession().getId();
				}
				System.out.println(sql);
				wfQuess = new WfQuesFacade().find(sql,WfQues.WFQUES_QUESRESP);
			}
			ActionContext ctx = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
			for(WfQues q : wfQuess) {
				if(q.getFileNo() != null && q.getFileNo().contains("/")) {
					String path = request.getSession().getServletContext().getRealPath(q.getFileNo() + "/" + q.getFileName());
					q.setFileNo(path);
				}
			}
			ExcelContext<WfQues> context = new ExcelContext<WfQues>(new WfQuesExcelParser());
			workbookpoi = context.create(wfQuess, "xls");

		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error("WfQuesListAction export Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		fileName = "wfQues" + DateUtil.format(new Date(), "yyyyMMddHHmmss");
		return "excelpoi";
	}
	private String contentType;
	private InputStream inputStream;
	public String downloadFile() {
		try {
			ActionContext ctx = ActionContext.getContext(); 
			HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST); 
			
			System.out.println(this.fileNameStr.trim());
			fileName = this.fileNameStr.trim();
			this.wfQues = new WfQuesFacade().findById(wfQues);
			File file = new File(getRequest().getSession().getServletContext().getRealPath(this.wfQues.getFileNo() + "/"+fileName));
			contentType ="application/octet-stream;charset=UTF-8";
			inputStream = new FileInputStream(file);
			
			 String agent = request.getHeader("USER-AGENT");  
		        if (null != agent){  
		            if (-1 != agent.indexOf("Firefox")) {//Firefox  
		            	fileName = "=?UTF-8?B?" + (new String(org.apache.commons.codec.binary.Base64.encodeBase64(fileName.getBytes("UTF-8"))))+ "?=";  
		            }else if (-1 != agent.indexOf("Chrome")) {//Chrome  
		            	fileName = new String(fileName.getBytes(), "ISO8859-1");  
		            } else {//IE7+  
		            	fileName = java.net.URLEncoder.encode(fileName, "UTF-8");  
		            	fileName = StringUtils.replace(fileName, "+", "%20");//替换空格  
		            }  
		        }
			
			
			
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error("WfQuesListAction downloadFile Exception", e);
			e.printStackTrace();
		}
		return "download";
	}
	
	
	public String viewPic() {
		try {
			this.wfQues = new WfQuesFacade().findById(wfQues);
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error("WfQuesListAction viewPic Exception", e);
			e.printStackTrace();
		}
		return "viewpic";
	}

	public List<WfQues> getWfQuess() {
		return wfQuess;
	}

	public void setWfQuess(List<WfQues> wfQuess) {
		this.wfQuess = wfQuess;
	}

	public WfQues getWfQues() {
		return wfQues;
	}

	public void setWfQues(WfQues wfQues) {
		this.wfQues = wfQues;
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

	public java.util.List<gnwf.vo.WfQuesRelate> getWfQuesRelates() {
		return wfQuesRelates;
	}

	public void setWfQuesRelates(java.util.List<gnwf.vo.WfQuesRelate> wfQuesRelates) {
		this.wfQuesRelates = wfQuesRelates;
	}

	public void addtoWfQuesRelates(gnwf.vo.WfQuesRelate wfQuesRelate) {
		if (getWfQuesRelates() == null) setWfQuesRelates(new java.util.ArrayList<gnwf.vo.WfQuesRelate>());
		getWfQuesRelates().add(wfQuesRelate);
	}

	public java.util.List<gnwf.vo.WfReply> getWfReplys() {
		return wfReplys;
	}

	public void setWfReplys(java.util.List<gnwf.vo.WfReply> wfReplys) {
		this.wfReplys = wfReplys;
	}

	public void addtoWfReplys(gnwf.vo.WfReply wfReply) {
		if (getWfReplys() == null) setWfReplys(new java.util.ArrayList<gnwf.vo.WfReply>());
		getWfReplys().add(wfReply);
	}

	public java.util.List<gnwf.vo.WfQuesDtl> getWfQuesDtls() {
		return wfQuesDtls;
	}

	public void setWfQuesDtls(java.util.List<gnwf.vo.WfQuesDtl> wfQuesDtls) {
		this.wfQuesDtls = wfQuesDtls;
	}

	public void addtoWfQuesDtls(gnwf.vo.WfQuesDtl wfQuesDtl) {
		if (getWfQuesDtls() == null) setWfQuesDtls(new java.util.ArrayList<gnwf.vo.WfQuesDtl>());
		getWfQuesDtls().add(wfQuesDtl);
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getPrjtNo() {
		return prjtNo;
	}

	public void setPrjtNo(String prjtNo) {
		this.prjtNo = prjtNo;
	}

	public java.util.List<zrsy.vo.Usr> getUsrs() {
		return usrs;
	}

	public void setUsrs(java.util.List<zrsy.vo.Usr> usrs) {
		this.usrs = usrs;
	}

	public String getWfNo() {
		return wfNo;
	}

	public void setWfNo(String wfNo) {
		this.wfNo = wfNo;
	}

	public String getIsRisk() {
		return isRisk;
	}

	public void setIsRisk(String isRisk) {
		this.isRisk = isRisk;
	}

	public WfRd getWfRd() {
		return wfRd;
	}

	public void setWfRd(WfRd wfRd) {
		this.wfRd = wfRd;
	}

	public java.util.List<SchCfg> getSchCfgs() {
		return schCfgs;
	}

	public void setSchCfgs(java.util.List<SchCfg> schCfgs) {
		this.schCfgs = schCfgs;
	}

	public List<PrjtDef> getPrjtDefs() {
		return prjtDefs;
	}

	public void setPrjtDefs(List<PrjtDef> prjtDefs) {
		this.prjtDefs = prjtDefs;
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public String getUpdateQuesIds() {
		return updateQuesIds;
	}

	public void setUpdateQuesIds(String updateQuesIds) {
		this.updateQuesIds = updateQuesIds;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}

	public String getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(String imgWidth) {
		this.imgWidth = imgWidth;
	}

	public String getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(String imgHeight) {
		this.imgHeight = imgHeight;
	}

	public String getImgTitle() {
		return imgTitle;
	}

	public void setImgTitle(String imgTitle) {
		this.imgTitle = imgTitle;
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

	public org.apache.poi.ss.usermodel.Workbook getWorkbookpoi() {
		return workbookpoi;
	}

	public void setWorkbookpoi(org.apache.poi.ss.usermodel.Workbook workbookpoi) {
		this.workbookpoi = workbookpoi;
	}

	public String getSyId() {
		return syId;
	}

	public Integer getWfRdStatus() {
		return wfRdStatus;
	}

	public void setWfRdStatus(Integer wfRdStatus) {
		this.wfRdStatus = wfRdStatus;
	}

	public void setSyId(String syId) {
		this.syId = syId;
	}

	public int getReload() {
		return reload;
	}

	public void setReload(int reload) {
		this.reload = reload;
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

	public String getResponsibleUID() {
		return responsibleUID;
	}

	public void setResponsibleUID(String responsibleUID) {
		this.responsibleUID = responsibleUID;
	}

	public Integer getIsFromWf() {
		return isFromWf;
	}

	public void setIsFromWf(Integer isFromWf) {
		this.isFromWf = isFromWf;
	}

	public Gp getGp() {
		return gp;
	}

	public void setGp(Gp gp) {
		this.gp = gp;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public QuesResp getQuesResp() {
		return quesResp;
	}

	public void setQuesResp(QuesResp quesResp) {
		this.quesResp = quesResp;
	}


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTextId() {
		return textId;
	}

	public void setTextId(String textId) {
		this.textId = textId;
	}

	public String getImpQuesIds() {
		return impQuesIds;
	}

	public void setImpQuesIds(String impQuesIds) {
		this.impQuesIds = impQuesIds;
	}

	public String getAction() {
		return action;
	}


	public String getIsDQA() {
		return isDQA;
	}

	public void setIsDQA(String isDQA) {
		this.isDQA = isDQA;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getFromQuesManger() {
		return fromQuesManger;
	}

	public void setFromQuesManger(String fromQuesManger) {
		this.fromQuesManger = fromQuesManger;
	}
	
//	public List<File> getFiles() {
//		return files;
//	}
//
//	public void setFiles(List<File> files) {
//		this.files = files;
//	}
	
	



public List<File[]> getFiles() {
		return files;
	}


	public void setFiles(List<File[]> files) {
		this.files = files;
	}

/****************************************android端接口***********************************************************/
	private Integer viewType;
	
	
	
	//我的任务待解决数量
	
			public void androidMyQuestionListAmount()throws Exception {
				
				Integer usrId=getUsrSession().getId();
				
				//DbConnUtil.buildDbconn(3);
				String sql ="";
				//System.out.println("prjtNo"+prjtNo);
				DbConnUtil.buildDbconn(3);
				try {
					//System.out.println(schid);
					if ( usrId !=null  ) {
						sql="select count(WfQues.QuesId) as amount from WfQues join QuesResp on WfQues.QuesId = QuesResp.QuesId where QuesResp.UsrId ="+ usrId+" and QuesResp.RespType = 1 and (QuesResp.Status = 0 or QuesResp.Status = -1) and WfQues.Status != 30 and WfQues.Status != 40";
				         System.out.println("sql"+sql);
						
					}
					
					Object amount = ((WfPpReportDAO) DAOFactory.getDAO(WfPpReportDAO.class))
							.queryALL(sql);
				
					//Map map = new HashMap();
					//map.put("success", true);
					//map.put("success", true);
					//map.put("amount", amount);
					HttpServletResponse response = ServletActionContext.getResponse(); 
					response.setHeader("Content-type", "text/html;charset=UTF-8");  
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
					out.print(JSON.toJSONString(amount));
				} catch (Exception e) {
					throw e;
				}finally {
					DbConnUtil.closeDbconn();
				}
			}
	
	
	
	/**
	 * 我的问题列表
	 * @return
	 */
	public String androidMyQuestionList() {
		String queryFieds = "WfQues.QuesId,WfQues.QuesText,WfQues.CateId,WfQues.CompletedDate,WfQues.UsrName,WfQues.Status"
				+ ",(select pd.PrjtNm from PrjtDef pd  where pd.PrjtNo = WfQues.PrjtNo) AS PrjtNm";
		StringBuffer conditionSQL = new StringBuffer();
		if(this.wfQues.getSelectType() == 1) {//待我解决的问题
			conditionSQL.append(" from WfQues join QuesResp on WfQues.QuesId = QuesResp.QuesId where QuesResp.UsrId = ")
			            .append(getUsrSession().getId())
			            .append(" and QuesResp.RespType = 1 and (QuesResp.Status = 0 or QuesResp.Status = -1) and "
			            		+ "WfQues.Status != 30 and WfQues.Status != 40");
		}else if(this.wfQues.getSelectType() == 2) {//我已解决的问题
			conditionSQL.append(" from WfQues join QuesResp on WfQues.QuesId = QuesResp.QuesId where QuesResp.UsrId = ")
						.append(getUsrSession().getId())
						.append(" and (QuesResp.Status = 1 or QuesResp.Status = 2 or QuesResp.Status = 3 or "
								+ "WfQues.status = 40) and QuesResp.RespType = 1");
		}else if(this.wfQues.getSelectType() == 3) {//我提出的问题
			conditionSQL.append(" from WfQues where WfQues.CreateBy = ")
			            .append(getUsrSession().getId());
		}else if(this.wfQues.getSelectType() == 4){//待我验证的问题
			conditionSQL.append(" from WfQues join QuesResp on WfQues.QuesId = QuesResp.QuesId where QuesResp.IdtfBy = ")
						.append(getUsrSession().getId())
						.append(" and (QuesResp.Status = 1 or QuesResp.Status = 3) and QuesResp.RespType=1 "
								+ "and WfQues.Status != 30 and WfQues.Status != 40");
		}
		if(this.wfQues.getPrjtNo() != null && !"".equals(this.wfQues.getPrjtNo())) {
			conditionSQL.append(" and WfQues.PrjtNo = '").append(this.wfQues.getPrjtNo()).append("'");
		}
		if(this.wfQues.getCreateBy() != null) {
			conditionSQL.append(" and WfQues.CreateBy = ").append(this.wfQues.getCreateBy());
		}
		if(this.wfQues.getUsrName() != null && !"".equals(this.wfQues.getUsrName())) {
			conditionSQL.append(" and WfQues.UsrName like '%").append(this.wfQues.getUsrName()).append("%'");
		}
		if(this.wfQues.getCompletedDate() != null) {
			conditionSQL.append(" and WfQues.CompletedDate = '")
						.append(DateUtil.format(this.wfQues.getCompletedDate(), "yyyy-MM-dd"))
						.append("'");
		}
		if(this.wfQues.getCateId() != null) {
			conditionSQL.append(" and WfQues.CateId = ").append(this.wfQues.getCateId());
		}
		try {
			if(this.wfQues.getScheId() != null) {
				StringBuffer schIds = new StringBuffer();
				schIds.append(this.wfQues.getScheId()).append(",");
				String schSql = "select SchCfg.SchId from SchCfg where Parent = " + this.wfQues.getScheId();
				List<SchCfg> scs = new SchCfgFacade().find(schSql, "SchCfg.SchId");
				if(scs != null && scs.size() > 0) {
					for(SchCfg sc : scs) {
						schIds.append(sc.getSchId()).append(",");
					}
				}
				schIds.deleteCharAt(schIds.length() - 1);
				this.wfQues.setScheIds(schIds.toString());
				conditionSQL.append(" and WfQues.ScheId in (").append(schIds.toString()).append(")");
			}
			String amountSql = "select count(WfQues.QuesId) as amount " + conditionSQL;
			WfQuesFacade facade = new WfQuesFacade();
			int total = facade.amount(amountSql);
			if (getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			if(this.wfQues.getSelectType() == 4) {
				this.wfQuess = facade.getPageBeanRepeat(conditionSQL.toString(),queryFieds,getPageVO());
			}else {
				this.wfQuess = facade.getPageBean(conditionSQL.toString(),queryFieds,getPageVO());
			}
			setJson(getAndroidJson(this.wfQuess, total, new String[]{"quesId","prjtNm","quesText",
					"cateId","completedDate","usrName","status"}));
		} catch (Exception e) {
			setJson(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesAction androidMyQuestionList Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return PGLIS;
	}
	/**
	 * 查询全部的问题列表
	 * @return
	 */
	public String androidWfRdQuestionList() {
		WfQuesFacade facade = new WfQuesFacade();
		try {
			int total = facade.amount(wfQues);
			if (getPage() == null) {
				setPage(1);
				setPagesize(10);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			this.wfQuess = facade.find(wfQues, getPageVO());
			setJson(getAndroidJson(this.wfQuess, total,  new String[]{"prjtNm","QuesText","cateId","quesId","status","prjtNm","datetimeforques","usrName"}));
		} catch (Exception e) {
			setJson(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesAction androidWfRdQuestionList Exception", e);
			e.printStackTrace();
		}
		return PGLIS;
	}
	/**
	 * 查看问题详情
	 * @return
	 */
	public String androidWfQuesView() {
		try {
			if(this.viewType == 1) {//查看问题详情
				this.wfQues = new WfQuesFacade().findById(this.wfQues);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("quesId", this.wfQues.getQuesId());
				map.put("prjtNm", this.wfQues.getPrjtNm());
				map.put("prjtNo", this.wfQues.getPrjtNo());
				map.put("wfName", this.wfQues.getWfName());
				if(this.wfQues.getCreateDate() != null) {
					map.put("createDate", DateUtil.format(this.wfQues.getCreateDate(),"yyyy-MM-dd hh:mm:ss"));
				}else {
					map.put("createDate","");
				}
				map.put("status", this.wfQues.getStatus());
				map.put("createUsr",this.wfQues.getCreateUsr());
				map.put("quesText", this.wfQues.getQuesText());
				map.put("usrName", this.wfQues.getUsrName());
				map.put("scheId", this.wfQues.getScheId());
				if (this.wfQues.getFractionDefective() != null) {
					map.put("fractionDefective",  this.wfQues.getFractionDefective());
				} else {
					map.put("fractionDefective", "");
				}
				map.put("quesTypeID", this.wfQues.getQuesTypeID());
				if(this.wfQues.getCompletedDate() != null) {
					map.put("completedDate", DateUtil.format(this.wfQues.getCompletedDate(),"yyyy-MM-dd"));
				}else {
					map.put("completedDate", "");
				}
				JSONObject jo = new JSONObject(map);
				setJson(jo.toJSONString());
			}else if(this.viewType == 2) {
				List<QuesResp> quesRespList = new QuesRespFacade().find("select " + QuesResp.LIST_FIELDS + 
						" from QuesResp where QuesResp.RespType = 1 and QuesResp.QuesId = '" + this.wfQues.getQuesId() + "'", QuesResp.LIST_FIELDS);
				setJson(getAndroidJson(quesRespList, null, new String[]{"quesId","id","result","usrName","resultDate","status",
						"idtfRes","idtfUsrName","idtfDate"}));
			}
		} catch (Exception e) {
			setJson(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesAction androidWfQuesView Exception", e);
			e.printStackTrace();
		}
		return PGLIS;
	}
	/**
	 * 问题验证
	 * @return
	 */
	public String androidWfQuesverification() {
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		this.wfQues.setLastUpd(getUsrSession().getId());
		this.wfQues.setLastUpdDate(new Date());
		this.quesResp.setLastUpd(getUsrSession().getId());
		this.quesResp.setLastUpdDate(new Date());
		this.quesResp.setIdtfDate(new Date());
		try {
			if(this.quesResp.getStatus() == 2) {//验证有效
				new QuesRespFacade().approve(this.wfQues, this.quesResp);
			}else if(this.quesResp.getStatus() == -1) {
				QuesRespFacade qf = new QuesRespFacade();
				qf.reject(this.wfQues, this.quesResp); 
				boolean isDQA = false;
				for (Gp e : getUsrSession().getGps()) {
					if (e.getGpName().indexOf("DQA") > -1) {
						isDQA = true;
						break;
					}
				}
				this.wfQues = new WfQuesFacade().findById("select " + WfQues.SELF_FIELDS + " from WfQues where"
						+ " WfQues.QuesId = '" + this.wfQues.getQuesId() + "'", WfQues.SELF_FIELDS);
				this.quesResp = qf.findById("select " + QuesResp.SELF_FIELDS + " from QuesResp where"
						+ " QuesResp.id = " + this.quesResp.getId(),QuesResp.SELF_FIELDS);
				String res_url = getAppcationURL();
				String title = "问题《" + wfQues.getQuesId() + "》-解决措施验证无效通知";
				String content = "";
				if(isDQA) {
					content ="尊敬的同事，您好：<p>您给的关于问题《" + wfQues.getQuesText()+"》解决措施已被DQA验证为无效，请从新给出新的解决措施。"
							+ "<p>链接地址 ： <a href=" + res_url
							+ "/WfQues!managerQues.shtml?wfQues.quesId="
							+ wfQues.getQuesId() + ">" + res_url
							+ "/WfQues!managerQues.shtml?wfQues.quesId="
							+ wfQues.getQuesId() + "</a>";
				}else {
					content = "尊敬的同事，您好：<p>您给的关于问题《"+wfQues.getQuesText()+"》解决措施已被验证为无效，请从新给出新的解决措施。"
							+ "<p>链接地址 ： <a href=" + res_url
							+ "/WfQues!managerQues.shtml?wfQues.quesId="
							+ wfQues.getQuesId() + ">" + res_url
							+ "/WfQues!managerQues.shtml?wfQues.quesId="
							+ wfQues.getQuesId() + "</a>";
				}
				List<Usr> userList = new ArrayList<Usr>();
				Usr u = new Usr();
				u.setId(this.quesResp.getUsrId());
				userList.add(u);
				WFUtil.sendMailByIT(title, content, userList);
			}else if(this.quesResp.getStatus() == -2) {
				QuesRespFacade qf = new QuesRespFacade();
				qf.returnQues(this.wfQues, this.quesResp); 
				boolean isDQA = false;
				for (Gp e : getUsrSession().getGps()) {
					if (e.getGpName().indexOf("DQA") > -1) {
						isDQA = true;
						break;
					}
				}
				this.wfQues = new WfQuesFacade().findById("select " + WfQues.SELF_FIELDS + " from WfQues where"
						+ " WfQues.QuesId = '" + this.wfQues.getQuesId() + "'", WfQues.SELF_FIELDS);
				this.quesResp = qf.findById("select " + QuesResp.SELF_FIELDS + " from QuesResp where"
						+ " QuesResp.id = " + this.quesResp.getId(),QuesResp.SELF_FIELDS);
				String res_url = getAppcationURL();
				String title = "问题《" + wfQues.getQuesId() + "》-解决措施验证退回通知";
				String content = "";
				if(isDQA) {
					content ="尊敬的同事，您好：<p>您给的关于问题《" + wfQues.getQuesText()+"》解决措施已被DQA验证为退回，请从新给出新的解决措施。"
							+ "<p>链接地址 ： <a href=" + res_url
							+ "/WfQues!managerQues.shtml?wfQues.quesId="
							+ wfQues.getQuesId() + ">" + res_url
							+ "/WfQues!managerQues.shtml?wfQues.quesId="
							+ wfQues.getQuesId() + "</a>";
				}else {
					content = "尊敬的同事，您好：<p>您给的关于问题《"+wfQues.getQuesText()+"》解决措施已被验证为退回，请从新给出新的解决措施。"
							+ "<p>链接地址 ： <a href=" + res_url
							+ "/WfQues!managerQues.shtml?wfQues.quesId="
							+ wfQues.getQuesId() + ">" + res_url
							+ "/WfQues!managerQues.shtml?wfQues.quesId="
							+ wfQues.getQuesId() + "</a>";
				}
				List<Usr> userList = new ArrayList<Usr>();
				Usr u = new Usr();
				u.setId(this.quesResp.getUsrId());
				userList.add(u);
				WFUtil.sendMailByIT(title, content, userList);
			}
			jsonMap.put("result", 1);
			jsonMap.put("msg", "验证成功");
		} catch (Exception e) {
			jsonMap.put("result", 0);
			jsonMap.put("msg", "验证失败");
			Logger.getLogger(this.getClass()).error("WfQuesAction androidWfQuesverification Exception", e);
			e.printStackTrace();
		}
		JSONObject jo = new JSONObject(jsonMap);
		setJson(jo.toJSONString());
		return PGLIS;
	}
	/**
	 * 获取项目组成员
	 * @return
	 */
	public String androidGetPrjtUser() {
		try {
			PrjtDef  prjtDef = new PrjtDef();
			prjtDef.setPrjtNo(this.prjtNo);
			prjtDef = new PrjtDefFacade().findById(prjtDef);
			String sqlString =  "select " + PrjtUsr.LIST_FIELDS + " from PrjtUsr " +
					" left join PrjtDef on (PrjtDef.PrjtNo = PrjtUsr.PrjtNo) " + 
					" inner join PrjtRole on (PrjtRole.RoleId = PrjtUsr.RoleId) " + 
					" inner join Usr on (Usr.Id = PrjtUsr.UsrId) " +
					" left join PrjtTyp on (PrjtTyp.TypId = PrjtUsr.PrjtTypId) " ;
			String conDitionSQl = " where PrjtUsr.PrjtTypId = " + prjtDef.getTypId() +
					" and (PrjtUsr.PrjtNo = '" + this.prjtNo + "' or prjtUsr.PrjtNo is null)";
			sqlString = sqlString+conDitionSQl;
			List<PrjtUsr> prjtUsrs = new PrjtUsrFacade().find(sqlString, PrjtUsr.LIST_FIELDS);
			if(prjtUsrs != null && prjtUsrs.size() > 0) {
				setJson(getAndroidJson(prjtUsrs, null, new String[]{"usrId","roleNm","usrName"}));
			}
		} catch (Exception e) {
			setJson(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesAction androidGetPrjtUser Exception", e);
			e.printStackTrace();
		}
		return PGLIS;
	}
	
	/**
	 * 责任人转交
	 * @return
	 */
	public String androidUpdateResponsibles() {
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		String[] responsibleUIDArr = responsibleUID.split(",");
		try {
			new WfQuesFacade().bathUpdateResponers(new String[]{this.wfQues.getQuesId()},
					this.wfQues.getUsrName(), responsibleUIDArr, getUsrSession().getId());
			jsonMap.put("result", 1);
			jsonMap.put("msg", MSG.S_UPD);
		} catch (Exception e) {
			jsonMap.put("result", 0);
			jsonMap.put("msg", MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfQuesAction androidUpdateResponsibles Exception", e);
			e.printStackTrace();
		}
		JSONObject jo = new JSONObject(jsonMap);
		setJson(jo.toJSONString());
		return PGLIS;
	}
	/**
	 * 获取问题管理列表
	 * @return
	 */
	public String androidQuestionManagerList() {
		try {
			if (wfQues.getPrjtNo() == null || wfQues.getPrjtNo().equals("ALL") || Utils.StringUtils.isEmpty(wfQues.getPrjtNo())) {
				setPrjtNoList();	
				wfQues.setPrjtNo(null);
			}
			if(this.wfQues.getScheId() != null) {
				StringBuffer schIds = new StringBuffer();
				schIds.append(this.wfQues.getScheId()).append(",");
				String schSql = "select SchCfg.SchId from SchCfg where Parent = " + this.wfQues.getScheId();
				List<SchCfg> scs = new SchCfgFacade().find(schSql, "SchCfg.SchId");
				if(scs != null && scs.size() > 0) {
					for(SchCfg sc : scs) {
						schIds.append(sc.getSchId()).append(",");
					}
				}
				schIds.deleteCharAt(schIds.length() - 1);
				this.wfQues.setScheIds(schIds.toString());
			}
			wfQues.setCurrentUsr(getUsrSession().getId());
			int total = new WfQuesFacade().amount(wfQues);
			if (getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfQuess = new WfQuesFacade().find(wfQues, getPageVO());
			setJson(getAndroidJson(wfQuess, total, new String[]{"quesId","prjtNm","quesText",
					"cateId","completedDate","usrName","status"}));
		} catch (Exception e) {
			setJson(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesAction androidQuestionManagerList Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return PGLIS;
	}
	/**
	 * 获取项目的阶段
	 * @return
	 */
	public String androidGettSchCfgs() {
		try {
			schCfgs = new SchCfgFacade().find("select " + SchCfg.SELF_FIELDS + " from SchCfg", SchCfg.SELF_FIELDS);
			setJson(getAndroidJson(schCfgs, null, new String[]{"schId","schNm"}));
		} catch (Exception e) {
			setJson(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesAction getPrjtSchCfgs Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return PGLIS;
	}
	
	
	public Integer getViewType() {
		return viewType;
	}

	public void setViewType(Integer viewType) {
		this.viewType = viewType;
	}
	
	
	//**************CQ对接接口****************
	/**
	 * 获取所有项目
	 * @return
	 */
	public String  cQPrjtlist() {
		try {
			prjtDefs = new PrjtDefFacade().find("select " + PrjtDef.SELF_FIELDS + " from PrjtDef", PrjtDef.SELF_FIELDS);
			setJson(getAndroidJson(prjtDefs, null, new String[]{"prjtNo","prjtNm","DevDeptNameID"}));
		} catch (Exception e) {
			setJson(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesAction getPrjtSchCfgs Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return PGLIS;
	}
	/**
	 * CQ插入数据同步到PDM系统
	 * @return
	 */

	public String cQQuesSave() throws Exception {
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		try {
			ActionContext ac = ActionContext.getContext();
			HttpServletRequest request =(HttpServletRequest)ac.get(ServletActionContext.HTTP_REQUEST);
			String quesText = request.getParameter("wfQues.quesText");
			quesText=URLDecoder.decode(quesText,"utf-8");
			String prjtNo = request.getParameter("wfQues.prjtNo");
			prjtNo=URLDecoder.decode(prjtNo,"utf-8");
			System.out.println(quesText);
			System.out.println(prjtNo);
			wfQues.setQuesText(quesText);
			String res_url = getAppcationURL();
			String title = "";
			PrjtDef prjtDef = new PrjtDef();
			prjtDef.setPrjtNo(prjtNo);
			PrjtDef prjt =  new PrjtDefFacade().findById(prjtDef);
			title = "CQ系统在项目(" +prjt.getPrjtNm() + ")-同步问题通知";
				
			
			List<Usr> userList = new ArrayList<Usr>();
			//发邮件给DQA
			List<PrjtUsr> prjtUsrList = new PrjtUsrFacade().find("select PrjtUsr.UsrId from PrjtUsr join PrjtRole on PrjtUsr.RoleId = PrjtRole.RoleId"
                     + " where PrjtRole.RoleNm in('DQA') and PrjtUsr.PrjtNo = '"
                    + prjtNo + "'","PrjtUsr.UsrId");
			for(PrjtUsr pu : prjtUsrList) {
				Usr u = new Usr();
				u.setId(pu.getUsrId());
				userList.add(u);
			}
			PrjtUsr prjtUsr = prjtUsrList.get(0);
			int userID = prjtUsr.getUsrId();
			System.out.println(userID);
			wfQues.setIdtfBy(userID);
			wfQues.setCreateBy(userID);
			wfQues.setLastUpd(userID);
			
			wfQues.setCreateDate(new Date());
			wfQues.setStatus(1);
			wfQues.setLastUpdDate(new Date());
			
			List<QuesResp> qsList = new ArrayList<QuesResp>();
					QuesResp qs = new QuesResp();
					qs.setQuesId(wfQues.getQuesId());
					qs.setUsrId(userID);
					
					qs.setIdtfBy(userID);
					qs.setCreateBy(userID);
					qs.setLastUpd(userID);
					
					qs.setCreateDate(new Date());
					qs.setLastUpdDate(new Date());
					qs.setStatus(MSG.QUESRESP_STATUS_0);
					qs.setRespType(MSG.QUESRESP_TYPE_NEW);
					qs.setCreateBy(1);
					qsList.add(qs);
			wfQues.setQuesRespList(qsList);
			String responsibleUserID =String.valueOf(userID);
			wfQues.setResponsibleIds(responsibleUserID);
			Usr us = new Usr();
			us.setId(userID);
			Usr usrID = new zrsy.facade.UsrFacade().findById(us);
			
			System.out.println(usrID.getUsrName());
			wfQues.setUsrName(usrID.getUsrName());
			new WfQuesFacade().save(wfQues);
			System.out.println(wfQues.getQuesId()+"!!!!!!"+prjt.getPrjtNm());
			String content = "尊敬的同事，您好：<p>CQ系统在PDM系统项目《"+prjt.getPrjtNm() +"》同步了一个问题,请您及时关注并处理。</p>"
							+ "<p>问题内容：" + quesText + "</p>链接地址 ： <a href=" + res_url + "/WfQues!/managerQues.shtml?wfQues.quesId="
							+  wfQues.getQuesId() + ">"
							+res_url+"/WfQues!/managerQues.shtml?wfQues.quesId="+wfQues.getQuesId() +"</a>";
			
			
			//发邮件通知责任人
			WFUtil.sendMailByIT(title, content, userList);
			jsonMap.put("result", 1);
			jsonMap.put("msg", "验证成功");
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("result", 0);
			jsonMap.put("msg", "验证失败");
			Logger.getLogger(this.getClass()).error("WfQuesAddAction saveQues Exception", e);
			return ERROR;
		}
		JSONObject jo = new JSONObject(jsonMap);
		setJson(jo.toJSONString());
		return PGLIS;
	}
	
}





