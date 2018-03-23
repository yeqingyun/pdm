package gnwf.ww.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;
import gnwf.dao.WfOveSeaUsrDAO;
import gnwf.facade.*;
import gnwf.vo.*;
import gnwf.vo.json.AjaxJson;
import gnwf.vo.json.WfQuesJson;
import gnwf.vo.json.WfRdJson;
import gnwf.ww.BasicAction;
import gnwf.ww.MSG;
import gnwf.ww.workflow.WFUtil;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import zrprjt.facade.PrjtDefFacade;
import zrprjt.vo.PrjtDef;
import zrprjt.vo.SchCfg;
import zrsy.vo.Gp;
import zrsy.vo.Usr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class WfRdAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfRd> wfRds;
	private WfRd wfRd = new WfRd();
	private java.util.List<gnwf.vo.WfDoc> wfDocs;
	private java.util.List<gnwf.vo.WfQuesRelate> wfQuesRelates;
	private java.util.List<gnwf.vo.WfMatl> wfMatls;
	private java.util.List<gnwf.vo.WfRdTask> wfRdTasks;
	private java.util.List<gnwf.vo.WfRdField> wfRdFields;
	private List<WfRd> childList; // 子流程
	private int isForFlow = 0;//是否为flow提交的表单，0：不是，1：是

	private WfCfg wfCfg;

	private WfCfg cfg;
	private int isLeader = 0;
	private List<WfCfg> wfCfgs;
	private List<PrjtDef> projectList;
	private List<SchCfg> schList;
	
	private String  wfOveSeaUsrList;

	private String userNameIDIt;

	private String				isReloadGrid;		//是否更新父页面grid

	
	public String getIsReloadGrid() {
		return isReloadGrid;
	}



	public void setIsReloadGrid(String isReloadGrid) {
		this.isReloadGrid = isReloadGrid;
	}



	public String getUserNameIDIt() {
		return userNameIDIt;
	}



	public void setUserNameIDIt(String userNameIDIt) {
		this.userNameIDIt = userNameIDIt;
	}



	public String getWfOveSeaUsrList() {
		return wfOveSeaUsrList;
	}



	public void setWfOveSeaUsrList(String wfOveSeaUsrList) {
		this.wfOveSeaUsrList = wfOveSeaUsrList;
	}



	public String execute() throws Exception {
		try {
			// 工作流种类
			
		
			
			WfCfg cfg = new WfCfg();
			cfg.setStatus(MSG.CONST_STATUS_1);
			wfCfgs = new WfCfgFacade().findAll(cfg);

		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	
	//添加权限,只有产品经理才可以添加和修改
	private boolean addGH=false;
	private boolean editGH=false;
	public String guihauList() throws Exception {
//		try {
//			// 工作流种类
//			WfCfg cfg = new WfCfg();
//			cfg.setStatus(MSG.CONST_STATUS_1);
//			wfCfgs = new WfCfgFacade().findAll(cfg);
//
//		} catch (Exception e) {
//			setMsg(MSG.F_SEA);
//			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
//			return ERROR;
//		}
		for(Gp gp:getUsrSession().getGps()){
			if("产品经理".equals(gp.getGpName())){
				addGH=true;
				editGH=true;
			}
		}
		return "guihauList";
	}
	
	

	public String myTaskList() throws Exception {
		try {
			// 工作流种类
			WfCfg cfg = new WfCfg();
			cfg.setStatus(MSG.CONST_STATUS_1);
			wfCfgs = new WfCfgFacade().findAll(cfg);
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return "mytasklist";
	}

	public String rickList() throws Exception {
		try {
			// SchCfg c = new SchCfg();
			// c.setTypId(1);
			// c.setParent(0);
			// schList = new SchCfgFacade().findAll(c);

			// 项目
			// PrjtDef p = new PrjtDef();
			// p.setTypId(1);
			// projectList = new PrjtDefFacade().findAll(p);
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return "risklist";
	}

	public String getProjWfRds() throws Exception {
		String findSQL = "";
		try {
			if(wfRd.getNeedQues()!= null) {
				findSQL = "select " + WfRd.SELF_FIELDS + " from  WfRd where  WfRd.ProjectNo = '" 
						+ wfRd.getProjectNo() + "' and needQues = " + wfRd.getNeedQues() 
						//+ "and WfRd.ScheId not in (select SchId from SchCfg where parent "
						//+ "in(select schid from schcfg where SchNm in ('小批试产','中批试产')))"
						+ " order by WfRd.ScheId asc";
			} else {
				findSQL = "select " + WfRd.SELF_FIELDS + " from  WfRd where  WfRd.ProjectNo = '" 
						+ wfRd.getProjectNo() + "' order by WfRd.WfNo Desc ";
			}
			List<WfRd> wfRds = new WfRdFacade().find(findSQL, WfRd.SELF_FIELDS);
			this.setJson(JSON.toJSONString(wfRds));
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("getProjWfRds Exception", e);
			return ERROR;
		}
		return PGLIS;
	}

	public String add() throws Exception {
		try {
			if (wfRd.getFlowId() != null) {
				wfRd.setPlanSDate(new Date());
				wfRd.setPlanEDate(new Date());
			} else {
				this.sendMessage("请选流程类别后，再点击新增按钮", "");
				return ERROR;
			}
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	
	public String addForFlow() throws Exception {
		AjaxJson ajaxJson = new AjaxJson();
		try {
			if (wfRd.getFlowId() != null) {
				wfRd.setPlanSDate(new Date());
				wfRd.setPlanEDate(new Date());
			} else {
				this.sendMessage("请选流程类别后，再点击新增按钮", "");
				ajaxJson.setSuccess(false);
				ajaxJson.setMsg(this.getMsg());
				this.setJson(JSON.toJSONString(ajaxJson));
				return PGJSON;
			}
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg(this.getMsg());
			this.setJson(JSON.toJSONString(ajaxJson));
			return PGJSON;
		}
		return "addForFlow";
	}
	public String edit() throws Exception {
		try {
			// if(wfRd != null && wfRd.getWfNo() != null) {
			// wfRd = new WfRdFacade().findById(wfRd);
			// setJson(JSON.toJSONString(wfRd));
			// }
			if (wfRd == null || wfRd.getWfNo() == null) {
				// this.sendMessage("请选择其中一条工作流记录进行编辑。", "WfRd!rickList.shtml?wfRd.flowId="+wfRd.getFlowId());
				setMsg("请选择其中一条工作流记录进行编辑。");
				return ERROR;
			}
			wfRd = new WfRdFacade().findById(wfRd);

			cfg = new WfCfg();
			cfg.setFlowId(wfRd.getFlowId());
			cfg = new WfCfgFacade().findById(cfg);

			WfLeader leader = new WfLeader();
			leader.setFlowId(cfg.getFlowId());
			List<WfLeader> leaders = new WfLeaderFacade().find(leader);
			//判断是否流程主导人
			for (WfLeader l : leaders) {
				if (getUsrSession().getId().equals(l.getUserId())) {
					isLeader = 1;
					break;
				}
			}
			String wfrdtaskSQL = "  select * from WfRdTask where WfNo = '"+ wfRd.getWfNo() +"' and ( PreTaskId = '0' or PreTaskId  is null)and Status = '1'";
			WfRdTask wTask = new WfRdTaskFacade().findById(wfrdtaskSQL, "WfRdTask.TaskId");
			//判断是否创建人并且为第一步
			//if (wfRd.getCreateBy().equals(getUsrSession().getId()) && wTask !=null) {
			//需求更改,创建人可以在任何步骤关闭流程 2015-08-12
			if (wfRd.getCreateBy().equals(getUsrSession().getId())) {
				isLeader = 1;
			}
			
			if (isLeader != 1 ) {
				// this.sendMessage("只有主导人和创建者可以更改流程", "WfRd!rickList.shtml?wfRd.flowId="+wfRd.getFlowId());
				//setMsg("非创建者不能修改流程状态，创建者只有第一步可以更改流程状态，其他情况请找流程主导人！");
				setMsg("只有主导人和创建者可以更改流程");
				return ERROR;
			}

		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}

	public String view() throws Exception {
		try {
			if (wfRd != null && wfRd.getWfNo() != null) {
				wfRd = new WfRdFacade().findById(wfRd);
				setJson(JSON.toJSONString(wfRd));
			}
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}

	public String list() throws Exception {
		try {
			// 查可看范围
			
			WfCfg cfg = new WfCfg();
			cfg.setFlowId(wfRd.getFlowId());
			wfCfg = new WfCfgFacade().findBy(cfg);
			System.out.println(wfCfg.getSphere());
			if (wfCfg != null && wfCfg.getSphere() == MSG.OWFCFG_SPHERE_1) { // 是否只查本人有关
				wfRd.setOnlySelectCurUser(MSG.OWFCFG_SPHERE_1);
			}

			// 是否是主导人
			WfLeader leader = new WfLeader();
			leader.setFlowId(cfg.getFlowId());
			List<WfLeader> leaders = new WfLeaderFacade().find(leader);

			for (WfLeader l : leaders) {
				if (getUsrSession().getId().equals(l.getUserId())) {
					isLeader = 1;
					break;
				}
			}

			boolean isNotChangeRole = false;//是否是《不随项目变更角色》。
			List<Gp> gps = getUsrSession().getGps();
			for(Gp gp : gps) {
				if(gp.getGpName().indexOf("不随项目变更角色") > -1) {
					isNotChangeRole = true;
					break;
				}
			}
			wfRd.setIsNotChangeRole(isNotChangeRole);
			
			
			boolean isOverseasRole = false;//是否是《海外项目人员角色》。
			List<Gp> gps1 = getUsrSession().getGps();
			for(Gp gp : gps1) {
				if(gp.getGpName().indexOf("海外项目人员") > -1) {
					isOverseasRole = true;
					break;
				}
			}
			for(Gp gp : gps1) {
				System.out.println(gp.getGpName());
				if(gp.getGpName().indexOf("供应链角色") > -1) {
					wfRd.setGylFlag("true");
					break;
				}
			}
			
			
			wfRd.setIsOverseasRole(isOverseasRole);
			
			
			if (wfRd == null) {
				wfRd = new WfRd();
			}
			//供应链开放只有特定ID可以看到
			if(wfRd.getFlowId()!=null&&wfRd.getGylFlag()!=null&&wfRd.getGylFlag().equals("true")){
				if(wfRd.getFlowId().equals(43)||wfRd.getFlowId().equals(63)){
					wfRd.setOnlySelectCurUser(14941);
					wfRd.setCurrentUserId(getUsrSession().getId());
				}else{
					wfRd.setCurrentUserId(getUsrSession().getId());
				}
			}else{
					wfRd.setCurrentUserId(getUsrSession().getId());
			}
			//wfRd.setCurrentUserId(getUsrSession().getId()); // 当前用户id
			int total = new WfRdFacade().amount(wfRd);
			if (getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			
			wfRds = new WfRdFacade().find(wfRd, getPageVO());
			PrjtDefFacade prjtDefFacade = new PrjtDefFacade();
			for(WfRd wr : wfRds) {
				PrjtDef pd = new PrjtDef();
				pd.setPrjtNo(wr.getProjectNo());
				pd = prjtDefFacade.findById(pd);
				if(null!=pd){
					wr.setPrjtNm(pd.getPrjtNm());
				}
			}
			WfRdJson wfRdJson = new WfRdJson();
			wfRdJson.Rows = wfRds;
			wfRdJson.Total = total;
			setJson(JSON.toJSONString(wfRdJson));
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return PGLIS;
	}
	
	
	public String guihualist() throws Exception {
		try {
			// 查可看范围
			wfRd.setFlowId(4);
			int total = new WfRdFacade().amount(wfRd);
			if (getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			
			wfRds = new WfRdFacade().find(wfRd, getPageVO());
			PrjtDefFacade prjtDefFacade = new PrjtDefFacade();
			for(WfRd wr : wfRds) {
				PrjtDef pd = new PrjtDef();
				pd.setPrjtNo(wr.getProjectNo());
				pd = prjtDefFacade.findById(pd);
				if(null!=pd){
					wr.setPrjtNm(pd.getPrjtNm());
				}
			}
			WfRdJson wfRdJson = new WfRdJson();
			wfRdJson.Rows = wfRds;
			wfRdJson.Total = total;
			setJson(JSON.toJSONString(wfRdJson));
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return PGLIS;
	}
	

	public String myTaskAndQues() {
		try {
			// if(wfRd == null) wfRd = new WfRd();
			// wfRd.setCurrentUserId(getUsrSession().getId());
			// wfRd.setSelectType(1);
			// wfRds = new WfRdFacade().find(wfRd);
			// WfRdJson wfRdJson = new WfRdJson();
			// wfRdJson.Rows = wfRds;
			// wfRdJson.Total = wfRds.size();
			// System.out.println(JSON.toJSONString(wfRdJson));

			// 查找待我完成的任务
			String wfRdConditionSQL = "where 1=1 and WfRd.Status<=1 and (WfRdTask.acceptBy=" + getUsrSession().getId() + " and WfRdTask.status<=1) ";
			
			String findSQL = "select " + WfRd.TASK_FIELDS + " from WfRdTask " + " left join WfRd on(WfRdTask.WfNo=WfRd.WfNo) " + " left join usr on (WfRd.CreateBy = usr.id) " + " left join WfStep on(WfStep.stepId=WfRdTask.stepId) " +" left join PrjtDef on(WfRd.ProjectNo=PrjtDef.PrjtNo) " + wfRdConditionSQL + " order by WfRdTask.TaskId ";

			wfRds = new WfRdFacade().find(findSQL, WfRd.TASK_FIELDS);
			WfRdJson wfRdJson = new WfRdJson();
			wfRdJson.Rows = wfRds;
			wfRdJson.Total = wfRds.size();
			System.out.println(JSON.toJSONString(wfRdJson));

			// 查找待我完成的问题
			String wfQuesConditionSQL = " from WfQues left join Usr on WfQues.UserId = Usr.Id inner join  QuesResp on WfQues.QuesId = QuesResp.QuesId where ((QuesResp.UsrId = " 
										+ getUsrSession().getId() + " and (QuesResp.Status =0 or QuesResp.Status =-1) and QuesResp.RespType=1 and WfQues.Status != 30 and WfQues.Status != 40)" 
										+ "or (QuesResp.IdtfBy = " + getUsrSession().getId() + " and QuesResp.Status = 1 and QuesResp.RespType=1 and WfQues.Status != 30 and WfQues.Status != 40))";
			
			List<WfQues> wfQuess = new WfQuesFacade().findmy(wfQuesConditionSQL);
			if (wfQuess != null && wfQuess.size() > 0) {
				for (WfQues wq : wfQuess) {
					wq.setQuesText(null);
					wq.setResult(null);
				}
			}
			WfQuesJson wfQuesJson = new WfQuesJson();
			wfQuesJson.Rows = wfQuess;
			wfQuesJson.Total = wfQuess.size();
			System.out.println(JSON.toJSONString(wfQuesJson));

			String rjson = "{\"Task\":" + JSON.toJSONString(wfRdJson) + ",\"Ques\":" + JSON.toJSONString(wfQuesJson) + "}";
			System.out.println(rjson);
			setJson(rjson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return PGLIS;
	}

	//android获取未完成任务未解决问题个数接口
	public String andrMyTaskAndQuesQunt() {
		try {
			

			// 查找待我完成的任务
			String wfRdConditionSQL = "where 1=1 and WfRd.Status<=1 and (WfRdTask.acceptBy=" + getUsrSession().getId() + " and WfRdTask.status<=1) ";
			
			String findSQL = "select " + WfRd.TASK_FIELDS + " from WfRdTask " + " left join WfRd on(WfRdTask.WfNo=WfRd.WfNo) " + " left join usr on (WfRd.CreateBy = usr.id) " + " left join WfStep on(WfStep.stepId=WfRdTask.stepId) " +" left join PrjtDef on(WfRd.ProjectNo=PrjtDef.PrjtNo) " + wfRdConditionSQL + " order by WfRdTask.TaskId ";

			wfRds = new WfRdFacade().find(findSQL, WfRd.TASK_FIELDS);
			WfRdJson wfRdJson = new WfRdJson();
			wfRdJson.Rows = wfRds;
			wfRdJson.Total = wfRds.size();
			System.out.println(JSON.toJSONString(wfRdJson));

			// 查找待我完成的问题
			String wfQuesConditionSQL = " from WfQues left join Usr on WfQues.UserId = Usr.Id inner join  QuesResp on WfQues.QuesId = QuesResp.QuesId where ((QuesResp.UsrId = " 
										+ getUsrSession().getId() + " and (QuesResp.Status =0 or QuesResp.Status =-1) and QuesResp.RespType=1 and WfQues.Status != 30 and WfQues.Status != 40)" 
										+ "or (QuesResp.IdtfBy = " + getUsrSession().getId() + " and QuesResp.Status = 1 and QuesResp.RespType=1 and WfQues.Status != 30 and WfQues.Status != 40))";
			
			List<WfQues> wfQuess = new WfQuesFacade().findmy(wfQuesConditionSQL);
			if (wfQuess != null && wfQuess.size() > 0) {
				for (WfQues wq : wfQuess) {
					wq.setQuesText(null);
					wq.setResult(null);
				}
			}
			WfQuesJson wfQuesJson = new WfQuesJson();
			wfQuesJson.Rows = wfQuess;
			wfQuesJson.Total = wfQuess.size();
			System.out.println(JSON.toJSONString(wfQuesJson));

			String rjson = "{\"TaskQunt\":" + JSON.toJSONString(wfRdJson.Total) + ",\"QuesQunt\":" + JSON.toJSONString(wfQuesJson.Total) + "}";
			System.out.println(rjson);
			setJson(rjson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return PGLIS;
	}
	
	public String eoaMyTaskAndQues() {
		try {
			// if(wfRd == null) wfRd = new WfRd();
			// wfRd.setCurrentUserId(getUsrSession().getId());
			// wfRd.setSelectType(1);
			// wfRds = new WfRdFacade().find(wfRd);
			// WfRdJson wfRdJson = new WfRdJson();
			// wfRdJson.Rows = wfRds;
			// wfRdJson.Total = wfRds.size();
			// System.out.println(JSON.toJSONString(wfRdJson));
			
			int userId = -1;
			ActionContext ctx = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
			if(request.getParameter("userId") != null) {
				try {
					userId = Integer.parseInt(request.getParameter("userId"));
				} catch(Exception e) {
					userId = -1;
				}
			}
			
			if(userId == -1) {
				String rjson = "{\"Task\":{\"Rows\":[],\"Total\":0},\"Ques\":{\"Rows\":[],\"Total\":0}}";
				setJson(rjson);
			} else {				
				// 查找待我完成的任务
				String wfRdConditionSQL = "where 1=1 and WfRd.Status<=1 and (WfRdTask.acceptBy=" + userId + " and WfRdTask.status<=1) ";
				
				String findSQL = "select " + WfRd.TASK_FIELDS + " from WfRdTask " + " left join WfRd on(WfRdTask.WfNo=WfRd.WfNo) " + " left join usr on (WfRd.CreateBy = usr.id) " + " left join WfStep on(WfStep.stepId=WfRdTask.stepId) " +" left join PrjtDef on(WfRd.ProjectNo=PrjtDef.PrjtNo) " + wfRdConditionSQL + " order by WfRdTask.TaskId ";
	
				wfRds = new WfRdFacade().find(findSQL, WfRd.TASK_FIELDS);
				WfRdJson wfRdJson = new WfRdJson();
				wfRdJson.Rows = wfRds;
				wfRdJson.Total = wfRds.size();
				System.out.println(JSON.toJSONString(wfRdJson));
	
				// 查找待我完成的问题
				String wfQuesConditionSQL = " from WfQues left join Usr on WfQues.UserId = Usr.Id inner join  QuesResp on WfQues.QuesId = QuesResp.QuesId where ((QuesResp.UsrId = " 
											+ userId + " and (QuesResp.Status =0 or QuesResp.Status =-1) and QuesResp.RespType=1 and WfQues.Status != 30 and WfQues.Status != 40)" 
											+ "or (QuesResp.IdtfBy = " + userId + " and QuesResp.Status = 1 and QuesResp.RespType=1 and WfQues.Status != 30 and WfQues.Status != 40))";
				
				List<WfQues> wfQuess = new WfQuesFacade().findmy(wfQuesConditionSQL);
				if (wfQuess != null && wfQuess.size() > 0) {
					for (WfQues wq : wfQuess) {
						wq.setQuesText(null);
						wq.setResult(null);
					}
				}
				WfQuesJson wfQuesJson = new WfQuesJson();
				wfQuesJson.Rows = wfQuess;
				wfQuesJson.Total = wfQuess.size();
				System.out.println(JSON.toJSONString(wfQuesJson));
	
				String rjson = "{\"Task\":" + JSON.toJSONString(wfRdJson) + ",\"Ques\":" + JSON.toJSONString(wfQuesJson) + "}";
				System.out.println(rjson);
				setJson(rjson);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return PGLIS;
	}
	
	public String eoaMyTask() {
		try {
			int userId = -1;
			ActionContext ctx = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
			if(request.getParameter("userId") != null) {
				try {
					userId = Integer.parseInt(request.getParameter("userId"));
				} catch(Exception e) {
					userId = -1;
				}
			}
			
			String wfNo = null;
			String wfName = null;
			String beginDate = null;
			String endDate = null;
			
			if(request.getParameter("wfNo") != null && !request.getParameter("wfNo").trim().equals("")) {
				wfNo = request.getParameter("wfNo").trim();
			}
			if(request.getParameter("wfName") != null && !request.getParameter("wfName").trim().equals("")) {
				wfName = request.getParameter("wfName").trim();
			}
			if(request.getParameter("beginDate") != null && !request.getParameter("beginDate").trim().equals("")) {
				beginDate = request.getParameter("beginDate").trim() + " 00:00:00";
			}
			if(request.getParameter("endDate") != null && !request.getParameter("endDate").trim().equals("")) {
				endDate = request.getParameter("endDate").trim() + " 23:59:59";
			}
			
			int page = -1;
			if(request.getParameter("page") != null) {
				try {
					page = Integer.parseInt(request.getParameter("page"));
				} catch(Exception e) {
					page = -1;
				}
			}
			
			int pageSize = -1;
			if(request.getParameter("pageSize") != null) {
				try {
					pageSize = Integer.parseInt(request.getParameter("pageSize"));
				} catch(Exception e) {
					pageSize = -1;
				}
			}
		
			if(userId == -1) {
				String rjson = "{\"rows\":[],\"total\":0}";
				setJson(rjson);
			} else {				
				// 查找待我完成的任务
				String wfRdConditionSQL = " where 1=1 and WfRd.Status<=1 and WfRdTask.acceptBy=" + userId + " and WfRdTask.status<=1 ";
				if(wfNo != null) {
					wfRdConditionSQL += " and WfRdTask.WfNo='" + wfNo + "' ";
				}
				if(wfName != null) {
					wfRdConditionSQL += " and WfRd.WfName like '%" + wfName + "%' ";
				}
				if(beginDate != null) {
					wfRdConditionSQL += " and WfRd.CreateDate >= '" + beginDate + "' ";
				}
				if(endDate != null) {
					wfRdConditionSQL += " and WfRd.CreateDate <= '" + endDate + "' ";
				}
				
				String dburl = "";
				DbConnUtil.buildDbconn(3);
				try {
					dburl = DbConnUtil.getDbconn().getConn().getMetaData().getURL();
				}
				catch(Exception e) {
					throw e;
				}
				finally {
					DbConnUtil.closeDbconn();
				}
				String taskurl = "";
				if(dburl == null || dburl.equals("") || !dburl.contains("16.6.10.24")) {
					taskurl = ",'http://192.168.189.173:28080/zrprjt/WfRdView!wfTaskView.dhtml?wfRd.wfNo=' + WfRd.WfNo + '&currentTaskId=' + cast(WfRdTask.TaskId as varchar(10)) + '&taskStepId=' + cast(WfStep.StepId as varchar(10)) Url";
				} else {
					taskurl = ",'http://pdm.gionee.com/zrprjt/WfRdView!wfTaskView.dhtml?wfRd.wfNo=' + WfRd.WfNo + '&currentTaskId=' + cast(WfRdTask.TaskId as varchar(10)) + '&taskStepId=' + cast(WfStep.StepId as varchar(10)) Url";
				}
				
				String findSQL = "select " + WfRd.TASK_FIELDS + taskurl + " from WfRdTask " + " left join WfRd on(WfRdTask.WfNo=WfRd.WfNo) " + " left join usr on (WfRd.CreateBy = usr.id) " + " left join WfStep on(WfStep.stepId=WfRdTask.stepId) " +" left join PrjtDef on(WfRd.ProjectNo=PrjtDef.PrjtNo) " + wfRdConditionSQL;
							
				int lastRow = (page - 1) * pageSize;
				int nextRow = page * pageSize;

				String taskSQL = "SELECT SUB.* FROM (SELECT RES.*,ROW_NUMBER() OVER (ORDER BY CreateDate DESC) RNK FROM (" + findSQL + ") RES) SUB WHERE SUB.RNK > " + lastRow + " AND SUB.RNK <= " + nextRow;
				wfRds = new WfRdFacade().find(taskSQL, WfRd.TASK_FIELDS + taskurl);
				
				String countSQL = "SELECT COUNT(1) AMOUNT FROM (" + findSQL + ") T";
				int total = new WfRdFacade().amount(countSQL);
					
				String rows = JSON.toJSONString(wfRds, true);	
				JSONArray jsonArray = JSONArray.parseArray(rows);		
				
				String jsonStr = "{\"rows\":"+jsonArray+",\"total\":" + total + "}";
				
				setJson(jsonStr);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return PGLIS;
	}
	
	public String selectTask() throws Exception {
		try {
			// 查可看范围
			WfCfg cfg = new WfCfg();
			cfg.setFlowId(wfRd.getFlowId());
			wfCfg = new WfCfgFacade().findBy(cfg);
			if (wfCfg != null && wfCfg.getSphere() == MSG.OWFCFG_SPHERE_1) {
				wfRd.setOnlySelectCurUser(MSG.OWFCFG_SPHERE_1);
			}
			// 是否是主导人
			WfLeader leader = new WfLeader();
			leader.setFlowId(cfg.getFlowId());
			List<WfLeader> leaders = new WfLeaderFacade().find(leader);

			for (WfLeader l : leaders) {
				if (getUsrSession().getId().equals(l.getUserId())) {
					isLeader = 1;
					break;
				}
			}

			if (wfRd == null) wfRd = new WfRd();
			wfRd.setCurrentUserId(getUsrSession().getId()); // 当前用户id

			int total = new WfRdFacade().amount(wfRd);
			if (getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfRds = new WfRdFacade().find4Ques(wfRd, getPageVO());

			WfRdJson wfRdJson = new WfRdJson();
			wfRdJson.Rows = wfRds;
			wfRdJson.Total = total;
			setJson(JSON.toJSONString(wfRdJson));
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}

	private boolean noPages;
	public String selectMyTask() throws Exception {
		try {
			// 查可看范围
			WfCfg cfg = new WfCfg();
			cfg.setFlowId(wfRd.getFlowId());
			wfCfg = new WfCfgFacade().findBy(cfg);
			if (wfCfg != null && wfCfg.getSphere() == MSG.OWFCFG_SPHERE_1) {
				wfRd.setOnlySelectCurUser(MSG.OWFCFG_SPHERE_1);
			}
			// 是否是主导人
			WfLeader leader = new WfLeader();
			leader.setFlowId(cfg.getFlowId());
			List<WfLeader> leaders = new WfLeaderFacade().find(leader);

			for (WfLeader l : leaders) {
				if (getUsrSession().getId().equals(l.getUserId())) {
					isLeader = 1;
					break;
				}
			}

			if (wfRd == null) wfRd = new WfRd();
			wfRd.setCurrentUserId(getUsrSession().getId()); // 当前用户id

			String conditionSQL = "";
			int selectType = wfRd.getSelectType();
			if (wfRd.getSelectType() == 1) {
				conditionSQL = " and (WfRdTask.acceptBy=" + getUsrSession().getId() + " and WfRdTask.status<=1)";
			} else if (wfRd.getSelectType() == 5) {
				/*conditionSQL = " and WfRdTask.TaskId not in " +
			      " (select TaskId from WfRdTask where WfRdTask.status<=1 ) and WfRdTask.acceptBy= " + getUsrSession().getId();*/
				conditionSQL = " and  WfRdTask.status >1 and WfRdTask.acceptBy= " + getUsrSession().getId();
			}
			
			if(wfRd.getProjectNo()!=null&&!wfRd.getProjectNo().trim().isEmpty()&&!wfRd.getProjectNo().trim().equals("ALL")){
				if(wfRd.getProjectNo().trim().equals("NO_PRJT")){
					conditionSQL+=" and( WfRd.ProjectNo is null or WfRd.ProjectNo ='')";
				}else{
					conditionSQL+=" and WfRd.ProjectNo = '"+wfRd.getProjectNo().trim()+"'";
				}
			}
			
			
			
			WfRdJson wfRdJson = new WfRdJson();
			
//			if(isNoPages()){//如果是手机ＡＰＰ调用则不分页
//				wfRds = new WfRdFacade().findNoPages(conditionSQL);
//				wfRdJson.Rows = wfRds;
//				wfRdJson.Total = wfRds.size();
//				setJson(JSON.toJSONString(wfRdJson));
//			}else{
				String amoutSQL = " select count(distinct WfRdTask.TaskId) as amount  from WfRdTask " 
		                + " left join WfRd on(WfRdTask.WfNo=WfRd.WfNo) " 
				        + " left join usr on (WfRd.CreateBy = usr.id) " 
		                + " left join WfStep on(WfStep.stepId=WfRdTask.stepId) " 
				        + " where 1=1 " ;
		                
		        if (wfRd.getSelectType() == 1) {
		        	conditionSQL+=" and WfRd.Status<=1 ";
				} 
				amoutSQL+= conditionSQL;
		
				int total = new WfRdFacade().amount(amoutSQL);
				if (getPage() == null) {
					setPage(1);
					setPagesize(20);
				}
				getPageVO().setPage(this.getPage());
				getPageVO().setPageSize(this.getPagesize());
				getPageVO().setTotal(total);
				System.out.println("conditionSQL----------"+conditionSQL);
				wfRds = new WfRdFacade().find4Ques(selectType, conditionSQL, getPageVO());
		
				
				wfRdJson.Rows = wfRds;
				wfRdJson.Total = total;
				setJson(JSON.toJSONString(wfRdJson));
				System.out.println("3242532636"+JSON.toJSONString(wfRdJson));
//		   }
			
			
			
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}

	public String countTask() throws Exception {
		try {
			if (wfRd == null) wfRd = new WfRd();
			wfRd.setSelectType(MSG.OWFRD_SELECT_TYPE_1);
			wfRd.setCurrentUserId(getUsrSession().getId()); // 当前用户id

			int total = new WfRdFacade().amount(wfRd);

			WfRdJson wfRdJson = new WfRdJson();
			wfRdJson.Total = total;
			setJson(JSON.toJSONString(wfRdJson));
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}

	// 查子流程
	public String selChildList() throws Exception {
		try {
			String preSql = "select " + WfRd.ALL_FIELDS + " from WfRd left join Usr on(Usr.Id=WfRd.CreateBy)" + " where WfRd.PreWfNo='" + wfRd.getWfNo() + "'";
			childList = new WfRdFacade().find(preSql, WfRd.ALL_FIELDS);

			WfRdJson wfRdJson = new WfRdJson();
			wfRdJson.Rows = childList;
			setJson(JSON.toJSONString(wfRdJson));
		} catch (Exception e) {
			this.sendMessage(MSG.F_SAV, "WfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
			Logger.getLogger(this.getClass()).error("WfRdViewAction sentTask Exception", e);
			return ERROR;
		}
		return PGLIS;
	}

	//查询选择项目人员列表

	public String OveSeaUsrList() throws Exception {
		System.out.println("OveSeaUsrListRequst"+getRequest());
		String wfNo = (String)getRequest().getAttribute("wfRd.wfNo");
		System.out.println("------shangmian---------"+wfNo);
		/*if (wfNo != null) {
			System.out.println("---------------"+wfNo);
			String Usrlist =  WfOveSeaUsr.LIST_FIELDS;
			System.out.println("select "+ Usrlist +" from WfOveSeaUsr where WfOveSeaUsr.WfNo = "+ wfNo);
			wfOveSeaUsrList =new WfOveSeaUsrFacade().findAllUsr("select   Usr.UsrName, "+ Usrlist +" from   dbo.WfOveSeaUsr INNER JOIN dbo.Usr ON dbo.WfOveSeaUsr.WfUsrID = dbo.Usr.Id where WfOveSeaUsr.WfNo = '"+wfNo+"' order by WfOveSeaUsr.WfNo");
			
		}*/
		
        return "OveSeaUsrList";
}
	//添加下一步主办人人员列表

		public String addNextStepUsers() throws Exception {
			String userNameIDIt = (String)getRequest().getAttribute("userNameIDIt");
			System.out.println(userNameIDIt);
	        return "AddNextStepUsers";
	}
	//添加下一步协办人人员列表

		public String addNextStepAssistpUsers() throws Exception {
			String userNameIDIt = (String)getRequest().getAttribute("userNameIDIt");
			System.out.println(userNameIDIt);
	        return "AddNextStepAssistpUsers";
	}
	
	public String OveSeaUsrList1() throws Exception {
		//System.out.println(getRequest());
		//String wfNo = (String)getRequest().getAttribute("wfRd.wfNo");
		//System.out.println("555555"+(String)getRequest().getAttribute("abc"));
		String wfNo = (String)getRequest().getParameter("wfRdwfNo");
		System.out.println("wfNo------"+wfNo);
		//String wfNo ="B4214700020";
		DbConnUtil.buildDbconn(3);
		try {
			
			System.out.println("--------dianjiewuzhiga!!!!-------"+wfNo);
			String Usrlist =  WfOveSeaUsr.LIST_FIELDS;
			String sql ="select   Usr.UsrName, "+ Usrlist +" from   dbo.WfOveSeaUsr INNER JOIN dbo.Usr ON dbo.WfOveSeaUsr.WfUsrID = dbo.Usr.Id where WfOveSeaUsr.WfNo = '"+wfNo+"' order by WfOveSeaUsr.WfNo";
			Object allUsr = ((WfOveSeaUsrDAO) DAOFactory.getDAO(WfOveSeaUsrDAO.class))
					.queryALL(sql);
			System.out.println("222222222222"+sql);
			Map map = new HashMap();
			map.put("success", true);
			map.put("allUsr", allUsr);
			
			HttpServletResponse response = ServletActionContext.getResponse(); 
			response.setHeader("Content-type", "text/html;charset=UTF-8");  
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			//out.print(JSON.toJSONString(map));
			out.println(JSONObject.fromObject(map).toString());
			/*System.out.println(JSON.toJSONString(map));
			System.out.println(JSONObject.fromObject(map).toString());*/
//			return JSONObject.fromObject(map).toString();
			return null;
			
			
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
		
		
		
}
	
	public String sav() throws Exception {
		AjaxJson ajaxJson = new AjaxJson();
		try {
			isReloadGrid = "true";
			if (wfRd.getWfNo() == null) { // 新增
				wfRd.setStatus(MSG.CONST_STATUS_1);
				wfRd.setCreateBy(getUsrSession().getId());
				wfRd.setCreateDate(new Date());
				wfRd.setFactSDate(new Date());
				WfCfg wc = new WfCfg();
				wc.setFlowId(wfRd.getFlowId());
			    wc = new WfCfgFacade().findById(wc);
			    wfRd.setNeedQues(wc.getNeedQues());
				new WfRdFacade().save(wfRd);

				//如果是海外流程，把选择的项目组成员保存到WfOveSeaUsr表里面
				if (wfRd.getFlowId()==40||wfRd.getFlowId()==42) {
					
				
				WfOveSeaUsr oveSeaUsr = new WfOveSeaUsr();
				String[] usrIds = ((String)getRequest().getAttribute("usrIds")).split(",");
				for (int i = 0; i < usrIds.length; i++) {
				oveSeaUsr.setWfNo(wfRd.getWfNo());
				oveSeaUsr.setCreateBy(getUsrSession().getId());
				//oveSeaUsr.setCreateData(wfRd.getCreateDate());
				oveSeaUsr.setFlowID(wfRd.getFlowId());
				int udrId  = new Integer(usrIds[i]).intValue();
				oveSeaUsr.setWfUsrID(udrId);
				new WfOveSeaUsrFacade().save(oveSeaUsr);
					}
				}
				
				// 发第一个任务、跳转回任务页面
				WfRdTask task = new WfRdTask();
				task.setCreateBy(-1);
				task.setCreateDate(new Date());
				task.setReqDate(new Date());
				task.setAcceptBy(getUsrSession().getId());
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
				
				task = new WfRdTaskFacade().findBy(task);

				// 附件

				// 测试
				// WFUtil.createScheWfRd("PD1300008", 1, 1, "bbbb", new Date(), new Date());

				//this.sendMessage(MSG.S_SAV, "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
				
				this.sendMessage(MSG.S_SAV, "WfRdView!wfTaskView.shtml?wfRd.wfNo="+ wfRd.getWfNo()+"&currentTaskId="+task.getTaskId()+"&taskStepId="+task.getStepId());
				
				 
				if(isForFlow == 0){
					return MESSAGE;
				}else{
					ajaxJson.setSuccess(false);
					ajaxJson.setMsg(this.getMsg());
					this.setJson(JSON.toJSONString(ajaxJson));
					return PGJSON;
				}
			} else { // 更改
				if (wfRd != null && wfRd.getStatus() != null && wfRd.getStatus() == MSG.OWFRD_STATUS_2) {
					wfRd.setEndFactEDate(new Date());
				}
				
				/*HttpServletResponse response = ServletActionContext.getResponse(); 
				response.setHeader("Content-type", "text/html;charset=UTF-8");  
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				
				System.out.println(new String(wfRd.getWfName().getBytes("ISO-8859-1"),"UTF8"));*/
				wfRd.setWfName(wfRd.getWfName());
				if (wfRd.getWfDesc() != null) {
					wfRd.setWfDesc(wfRd.getWfDesc());
				}
				if (wfRd.getStatus() == 3 || wfRd.getStatus() == 2) {
					wfRd.setFactEDate(new Date());
				}
				wfRd.setLastUpd(getUsrSession().getId());
				wfRd.setLastUpdDate(new Date());
				new WfRdFacade().update(wfRd);
				setMsg(MSG.S_SAV);
				if(isForFlow == 0){
					return "msg";
				}else{
					ajaxJson.setSuccess(false);
					ajaxJson.setMsg(this.getMsg());
					this.setJson(JSON.toJSONString(ajaxJson));
					return PGJSON;
				}
			}
		} catch (Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfRdAddAction add Exception", e);
			if(isForFlow == 0){
				return ERROR;
			}else{
				ajaxJson.setSuccess(false);
				ajaxJson.setMsg(this.getMsg());
				this.setJson(JSON.toJSONString(ajaxJson));
				return PGJSON;
			}
		}
	}

	public String upd() throws Exception {
		try {
			// wfRd.setCreateBy(getSession().getUserId());
			// wfRd.setCreateDate(new Date());
			// wfRd.setLastUpd(getSession().getUserId());
			// wfRd.setLastUpdDate(new Date());
			new WfRdFacade().update(wfRd);
			setMsg(MSG.S_UPD);
		} catch (Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfRdAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String voi() throws Exception {
		try {
			if (wfRds != null && wfRds.size() > 0) {
				for (int i = 0; i < wfRds.size(); i++) {
					if (wfRds.get(i) != null) {
						// wfRds.get(i).setLastUpd(getSession().getUserId());
						// wfRds.get(i).setLastUpdDate(new Date());
						new WfRdFacade().submit(wfRds.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		} catch (Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String can() throws Exception {
		try {
			if (wfRds != null && wfRds.size() > 0) {
				for (int i = 0; i < wfRds.size(); i++) {
					if (wfRds.get(i) != null) {
						// wfRds.get(i).setLastUpd(getSession().getUserId());
						// wfRds.get(i).setLastUpdDate(new Date());
						new WfRdFacade().submit(wfRds.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		} catch (Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String del() throws Exception {
		try {
			if (wfRds != null && wfRds.size() > 0) {
				for (int i = 0; i < wfRds.size(); i++) {
					if (wfRds.get(i) != null) {
						// wfRds.get(i).setLastUpd(getSession().getUserId());
						// wfRds.get(i).setLastUpdDate(new Date());
						new WfRdFacade().update(wfRds.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		} catch (Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String sub() throws Exception {
		try {
			if (wfRds != null && wfRds.size() > 0) {
				for (int i = 0; i < wfRds.size(); i++) {
					if (wfRds.get(i) != null) {
						// wfRds.get(i).setLastUpd(getSession().getUserId());
						// wfRds.get(i).setLastUpdDate(new Date());
						new WfRdFacade().submit(wfRds.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		} catch (Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String sta() throws Exception {
		try {
			if (wfRds != null && wfRds.size() > 0) {
				for (int i = 0; i < wfRds.size(); i++) {
					if (wfRds.get(i) != null) {
						// wfRds.get(i).setLastUpd(getSession().getUserId());
						// wfRds.get(i).setLastUpdDate(new Date());
						new WfRdFacade().submit(wfRds.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		} catch (Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String loa() throws Exception {
		try {
			if (wfRds != null && wfRds.size() > 0) {
				for (int i = 0; i < wfRds.size(); i++) {
					if (wfRds.get(i) != null) {
						// wfRds.get(i).setLastUpd(getSession().getUserId());
						// wfRds.get(i).setLastUpdDate(new Date());
						new WfRdFacade().submit(wfRds.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		} catch (Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String stp() throws Exception {
		try {
			if (wfRds != null && wfRds.size() > 0) {
				for (int i = 0; i < wfRds.size(); i++) {
					if (wfRds.get(i) != null) {
						// wfRds.get(i).setLastUpd(getSession().getUserId());
						// wfRds.get(i).setLastUpdDate(new Date());
						new WfRdFacade().submit(wfRds.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		} catch (Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String ove() throws Exception {
		try {
			if (wfRds != null && wfRds.size() > 0) {
				for (int i = 0; i < wfRds.size(); i++) {
					if (wfRds.get(i) != null) {
						// wfRds.get(i).setLastUpd(getSession().getUserId());
						// wfRds.get(i).setLastUpdDate(new Date());
						new WfRdFacade().submit(wfRds.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		} catch (Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String chk() throws Exception {
		try {
			if (wfRds != null && wfRds.size() > 0) {
				for (int i = 0; i < wfRds.size(); i++) {
					if (wfRds.get(i) != null) {
						// wfRds.get(i).setLastUpd(getSession().getUserId());
						// wfRds.get(i).setLastUpdDate(new Date());
						new WfRdFacade().review(wfRds.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		} catch (Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String rev() throws Exception {
		try {
			if (wfRds != null && wfRds.size() > 0) {
				for (int i = 0; i < wfRds.size(); i++) {
					if (wfRds.get(i) != null) {
						// wfRds.get(i).setLastUpd(getSession().getUserId());
						// wfRds.get(i).setLastUpdDate(new Date());
						new WfRdFacade().review(wfRds.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		} catch (Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String con() throws Exception {
		try {
			if (wfRds != null && wfRds.size() > 0) {
				for (int i = 0; i < wfRds.size(); i++) {
					if (wfRds.get(i) != null) {
						// wfRds.get(i).setLastUpd(getSession().getUserId());
						// wfRds.get(i).setLastUpdDate(new Date());
						new WfRdFacade().confirm(wfRds.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		} catch (Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String iss() throws Exception {
		try {
			if (wfRds != null && wfRds.size() > 0) {
				for (int i = 0; i < wfRds.size(); i++) {
					if (wfRds.get(i) != null) {
						// wfRds.get(i).setLastUpd(getSession().getUserId());
						// wfRds.get(i).setLastUpdDate(new Date());
						new WfRdFacade().confirm(wfRds.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		} catch (Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String imp() throws Exception {
		try {
			if (fileInp != null) {
				// if(wfRds != null && wfRds.size() > 0) {
				// for(int i=0; i<wfRds.size();i++) {
				// if(wfRds.get(i) != null) {
				// //wfRds.get(i).setLastUpd(getSession().getUserId());
				// //wfRds.get(i).setLastUpdDate(new Date());
				// new WfRdFacade().confirm(wfRds.get(i));
				// }
				// }
				// }
				setMsg(MSG.S_IMP);
			}
		} catch (Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}

	public String exp() throws Exception {
		try {
			List<WfRd> wfRds = new WfRdFacade().find(wfRd);
			if (wfRds != null && wfRds.size() > 0) {
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

				ws.addCell(new Label(index, 1, "进度任务", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "工作流定义ID", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "状态", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "创建人", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "更新人", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "工作流编号", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "项目编号", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "上级工作流", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "计划开始时间", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "计划完成时间", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "实际开始时间", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "实际完成时间", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "创建日期", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "更新日期", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "工作流标题", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "工作流内容", wcformat));
				ws.setColumnView(index, 20);
				index++;

				int row = 2;
				for (int i = 0; i < wfRds.size(); i++) {
					row++;
					int m = 0;
					if (wfRds.get(i).getScheId() != null) ws.addCell(new jxl.write.Number(m, row, wfRds.get(i).getScheId(), wcformat));
					m++;
					if (wfRds.get(i).getFlowId() != null) ws.addCell(new jxl.write.Number(m, row, wfRds.get(i).getFlowId(), wcformat));
					m++;
					if (wfRds.get(i).getStatus() != null) ws.addCell(new jxl.write.Number(m, row, wfRds.get(i).getStatus(), wcformat));
					m++;
					if (wfRds.get(i).getCreateBy() != null) ws.addCell(new jxl.write.Number(m, row, wfRds.get(i).getCreateBy(), wcformat));
					m++;
					if (wfRds.get(i).getLastUpd() != null) ws.addCell(new jxl.write.Number(m, row, wfRds.get(i).getLastUpd(), wcformat));
					m++;
					if (wfRds.get(i).getWfNo() != null) ws.addCell(new jxl.write.Label(m, row, wfRds.get(i).getWfNo(), wcformat));
					m++;
					if (wfRds.get(i).getProjectNo() != null) ws.addCell(new jxl.write.Label(m, row, wfRds.get(i).getProjectNo(), wcformat));
					m++;
					if (wfRds.get(i).getPreWfNo() != null) ws.addCell(new jxl.write.Label(m, row, wfRds.get(i).getPreWfNo(), wcformat));
					m++;
					if (wfRds.get(i).getPlanSDate() != null) ws.addCell(new jxl.write.DateTime(m, row, wfRds.get(i).getPlanSDate(), wcformat));
					m++;
					if (wfRds.get(i).getPlanEDate() != null) ws.addCell(new jxl.write.DateTime(m, row, wfRds.get(i).getPlanEDate(), wcformat));
					m++;
					if (wfRds.get(i).getFactSDate() != null) ws.addCell(new jxl.write.DateTime(m, row, wfRds.get(i).getFactSDate(), wcformat));
					m++;
					if (wfRds.get(i).getFactEDate() != null) ws.addCell(new jxl.write.DateTime(m, row, wfRds.get(i).getFactEDate(), wcformat));
					m++;
					if (wfRds.get(i).getCreateDate() != null) ws.addCell(new jxl.write.DateTime(m, row, wfRds.get(i).getCreateDate(), wcformat));
					m++;
					if (wfRds.get(i).getLastUpdDate() != null) ws.addCell(new jxl.write.DateTime(m, row, wfRds.get(i).getLastUpdDate(), wcformat));
					m++;
					if (wfRds.get(i).getWfName() != null) ws.addCell(new jxl.write.Label(m, row, wfRds.get(i).getWfName(), wcformat));
					m++;
					if (wfRds.get(i).getWfDesc() != null) ws.addCell(new jxl.write.Label(m, row, wfRds.get(i).getWfDesc(), wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		} catch (Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfRdListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}

	public String prn() throws Exception {
		try {
			if (wfRds != null && wfRds.size() > 0) {
				for (int i = 0; i < wfRds.size(); i++) {
					if (wfRds.get(i) != null) {
						// wfRds.get(i).setLastUpd(getSession().getUserId());
						// wfRds.get(i).setLastUpdDate(new Date());
						new WfRdFacade().confirm(wfRds.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		} catch (Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}

	public String dow() throws Exception {
		try {
			if (wfRds != null && wfRds.size() > 0) {
				for (int i = 0; i < wfRds.size(); i++) {
					if (wfRds.get(i) != null) {
						// wfRds.get(i).setLastUpd(getSession().getUserId());
						// wfRds.get(i).setLastUpdDate(new Date());
						new WfRdFacade().confirm(wfRds.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		} catch (Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}

	public List<WfRd> getWfRds() {
		return wfRds;
	}

	public void setWfRds(List<WfRd> wfRds) {
		this.wfRds = wfRds;
	}

	public WfRd getWfRd() {
		return wfRd;
	}

	public void setWfRd(WfRd wfRd) {
		this.wfRd = wfRd;
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

	public java.util.List<gnwf.vo.WfDoc> getWfDocs() {
		return wfDocs;
	}

	public void setWfDocs(java.util.List<gnwf.vo.WfDoc> wfDocs) {
		this.wfDocs = wfDocs;
	}

	public void addtoWfDocs(gnwf.vo.WfDoc wfDoc) {
		if (getWfDocs() == null) setWfDocs(new java.util.ArrayList<gnwf.vo.WfDoc>());
		getWfDocs().add(wfDoc);
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

	public java.util.List<gnwf.vo.WfMatl> getWfMatls() {
		return wfMatls;
	}

	public void setWfMatls(java.util.List<gnwf.vo.WfMatl> wfMatls) {
		this.wfMatls = wfMatls;
	}

	public void addtoWfMatls(gnwf.vo.WfMatl wfMatl) {
		if (getWfMatls() == null) setWfMatls(new java.util.ArrayList<gnwf.vo.WfMatl>());
		getWfMatls().add(wfMatl);
	}

	public java.util.List<gnwf.vo.WfRdTask> getWfRdTasks() {
		return wfRdTasks;
	}

	public void setWfRdTasks(java.util.List<gnwf.vo.WfRdTask> wfRdTasks) {
		this.wfRdTasks = wfRdTasks;
	}

	public void addtoWfRdTasks(gnwf.vo.WfRdTask wfRdTask) {
		if (getWfRdTasks() == null) setWfRdTasks(new java.util.ArrayList<gnwf.vo.WfRdTask>());
		getWfRdTasks().add(wfRdTask);
	}

	public java.util.List<gnwf.vo.WfRdField> getWfRdFields() {
		return wfRdFields;
	}

	public void setWfRdFields(java.util.List<gnwf.vo.WfRdField> wfRdFields) {
		this.wfRdFields = wfRdFields;
	}

	public void addtoWfRdFields(gnwf.vo.WfRdField wfRdField) {
		if (getWfRdFields() == null) setWfRdFields(new java.util.ArrayList<gnwf.vo.WfRdField>());
		getWfRdFields().add(wfRdField);
	}

	public WfCfg getWfCfg() {
		return wfCfg;
	}

	public void setWfCfg(WfCfg wfCfg) {
		this.wfCfg = wfCfg;
	}

	public int getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(int isLeader) {
		this.isLeader = isLeader;
	}

	public List<WfCfg> getWfCfgs() {
		return wfCfgs;
	}

	public void setWfCfgs(List<WfCfg> wfCfgs) {
		this.wfCfgs = wfCfgs;
	}

	public List<PrjtDef> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<PrjtDef> projectList) {
		this.projectList = projectList;
	}

	public List<SchCfg> getSchList() {
		return schList;
	}

	public void setSchList(List<SchCfg> schList) {
		this.schList = schList;
	}

	/**
	 * 任务列表
	 * 
	 * <pre>
	 * android login
	 * logCode=e4a14d23-4162-4e5a-9223-7ca1a014d4bc AndrServLogin.login uuid
	 * wfrd.selectType check type
	 * pagesize=10& page=1 page
	 * </pre>
	 * 
	 * @return json {rows,total}
	 * @throws Exception
	 */
	
	
	public String andrTaskList() throws Exception {
//		noPages = true;
		return selectMyTask();
	}

	/**
	 * 流程列表
	 */
	public String andrWfList() throws Exception {
		return list();
	}

	public String andrSav() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", 0);
		try {
			if (wfRd.getWfNo() == null) { // 新增
				wfRd.setStatus(MSG.CONST_STATUS_1);
				wfRd.setCreateBy(getUsrSession().getId());
				wfRd.setCreateDate(new Date());
				new WfRdFacade().save(wfRd);

				// 发第一个任务、跳转回任务页面
				WfRdTask task = new WfRdTask();
				task.setCreateBy(-1);
				task.setCreateDate(new Date());
				task.setReqDate(new Date());
				task.setAcceptBy(getUsrSession().getId());
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

			} else { // 更改
				if (wfRd != null && wfRd.getStatus() != null && wfRd.getStatus() == MSG.OWFRD_STATUS_2) {
					wfRd.setEndFactEDate(new Date());
				}
				wfRd.setLastUpd(getUsrSession().getId());
				wfRd.setLastUpdDate(new Date());
				new WfRdFacade().update(wfRd);
			}
			map.put("result", 1);
			map.put("msg", MSG.S_SAV);
			Map<String, Object> wfRdMap = new HashMap<String, Object>();
			wfRdMap.put("wfNo", wfRd.getWfNo());
			map.put("recordPk", wfRdMap);
		} catch (Exception e) {
			map.put("msg", MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfRdAddAction add Exception", e);
		}
		System.out.println(JSON.toJSONString(map));
		setJson(JSON.toJSONString(map));
		return PGLIS;
	}

	public String andrUpd() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", 0);
		try {
			// wfRd.setCreateBy(getSession().getUserId());
			// wfRd.setCreateDate(new Date());
			// wfRd.setLastUpd(getSession().getUserId());
			// wfRd.setLastUpdDate(new Date());
			new WfRdFacade().update(wfRd);
			map.put("result", 1);
			map.put("msg", MSG.S_UPD);
			Map<String, Object> wfRdMap = new HashMap<String, Object>();
			wfRdMap.put("wfNo", wfRd.getWfNo());
			map.put("recordPk", wfRdMap);
		} catch (Exception e) {
			map.put("msg", MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfRdAddAction add Exception", e);
		}
		System.out.println(JSON.toJSONString(map));
		setJson(JSON.toJSONString(map));
		return PGLIS;
	}

	/**流程说明,描述*/
	public String desc() throws Exception {
		try {
			// 工作流种类
			WfCfg cfg = new WfCfg();
			cfg.setStatus(MSG.CONST_STATUS_1);
			wfCfgs = new WfCfgFacade().findAll(cfg);

		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return "desc";
	}
	

	/**email select user*/
	private String usrIds;
	/**email title*/
	private String mailTitle;
	/**email content*/
	private String mailContent;
	private PrjtDef prjtDef;
	/**open send email window*/
	public String showSendMail() throws Exception {
		System.out.println(usrIds);
//		System.out.println(usrString);
		return "showSendMail";
	}
	//打开阅知窗口
	public String showSendMailForReader() throws Exception {
		System.out.println(usrIds);
		System.out.println(wfRd.getWfNo());
		return "showSendMailForReader";
	}
	
	/**send email*/
	public String sendMail(){
		try {
			ArrayList<Usr> mailUserList = new ArrayList<Usr>();
			String usrs[] =  usrIds.split(",");
			for(int i=0;i<usrs.length;i++){
				if(usrs[i].trim().length()>0){
					Usr u = new Usr();
					u.setId(Integer.valueOf(usrs[i]));
					mailUserList.add(u);
				}
			}
			if(mailUserList.isEmpty()){
				setMsg("请选择接收人");
				return "msg";
			}
			WFUtil.sendMailByIT(mailTitle, mailContent, mailUserList);
			//WFUtil.sendMail(getUsrSession().getId(), getUsrSession().getEmail(),mailTitle, mailContent, mailUserList);
			//WFUtil.sendMail(getUsrSession().getId(), getUsrSession().getEmail(),mailTitle, mailContent,mailUserList.);
			setMsg("发送完成");
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdAction sendMail Exception",
					e);
			return "msg";
		}
		return "msg";
	}
	
	//取登录地址如 http://192.168.0.6:8080/oa
		public String getSysWebUrl(){
	    	String basePath = ServletActionContext.getServletContext().getInitParameter("server_URL");
	    	return basePath;
		}
	//阅知发送邮件
	public String sendMailForReader(){
		try {
			String url = "WfRdView!wfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo();
			ArrayList<Usr> mailUserList = new ArrayList<Usr>();
			String usrs[] =  usrIds.split(",");
			for(int i=0;i<usrs.length;i++){
				if(usrs[i].trim().length()>0){
					Usr u = new Usr();
					u.setId(Integer.valueOf(usrs[i]));
					if(u.getId().equals(15129)){
						continue;
					}else{
						mailUserList.add(u);
					}
				}
			}
			if(mailUserList.isEmpty()){
				setMsg("请选择接收人");
				return "msg";
			}
			WfRd wfRdlists = new WfRdFacade().findById(wfRd);
			WfCfg wfCfglist = new WfCfg();
			wfCfglist.setFlowId(wfRdlists.getFlowId());
			WfCfg wfCfglists = new WfCfgFacade().findById(wfCfglist);
			String webUrl = getSysWebUrl()  + url;
			String title = "工作流《 " + wfCfglists.getFlowName() + "》流程的任务阅知通知";
			String content = "尊敬的同事，您好：" +
				"<p>您有一条《" + wfRdlists.getWfName() + "》流程的任务阅知通知。" +
				"<p>工作流编号为：" + wfRd.getWfNo() + "，工作流名称：" + wfRdlists.getWfName() + "，请尽快办理!" +
				"<p>任务办理链接地址 ： <a href=" + webUrl + ">" + webUrl + "</a>";
			WFUtil.sendMailByIT(title, content, mailUserList);
			//WFUtil.sendMail(getUsrSession().getId(), getUsrSession().getEmail(),mailTitle, mailContent, mailUserList);
			//WFUtil.sendMail(getUsrSession().getId(), getUsrSession().getEmail(),mailTitle, mailContent,mailUserList.);
			setMsg("发送完成");
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdAction sendMail Exception",
					e);
			return "msg";
		}
		return "msg";
	}

	public String getUsrIds() {
		return usrIds;
	}

	public void setUsrIds(String usrIds) {
		this.usrIds = usrIds;
	}

	public PrjtDef getPrjtDef() {
		return prjtDef;
	}

	public void setPrjtDef(PrjtDef prjtDef) {
		this.prjtDef = prjtDef;
	}

	public String getMailTitle() {
		return mailTitle;
	}

	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}



	public boolean isAddGH() {
		return addGH;
	}



	public void setAddGH(boolean addGH) {
		this.addGH = addGH;
	}



	public boolean isEditGH() {
		return editGH;
	}



	public void setEditGH(boolean editGH) {
		this.editGH = editGH;
	}



	public boolean isNoPages() {
		return noPages;
	}



	public void setNoPages(boolean noPages) {
		this.noPages = noPages;
	}



	public int getIsForFlow() {
		return isForFlow;
	}



	public void setIsForFlow(int isForFlow) {
		this.isForFlow = isForFlow;
	}
	
}