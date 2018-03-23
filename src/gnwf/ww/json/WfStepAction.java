package gnwf.ww.json;

import gnwf.facade.WfCfgFacade;
import gnwf.facade.WfStepFacade;
import gnwf.facade.WfStepUserFacade;
import gnwf.vo.WfCfg;
import gnwf.vo.WfStep;
import gnwf.vo.WfStepUser;
import gnwf.vo.json.WfStepJson;
import gnwf.ww.BasicAction;
import gnwf.ww.MSG;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;

import zrprjt.facade.PrjtRoleFacade;
import zrprjt.vo.PrjtRole;
import zrsy.facade.ComFacade;
import zrsy.facade.GpFacade;
import zrsy.vo.Com;
import zrsy.vo.json.TreeJson;

import com.alibaba.fastjson.JSON;

public class WfStepAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfStep> wfSteps;
	private WfStep wfStep = new WfStep();
	private java.util.List<gnwf.vo.WfRdTask> wfRdTasks;
	private java.util.List<gnwf.vo.WfStepUser> wfStepUsers;
	private java.util.List<gnwf.vo.WfStepNext> wfStepNexts;
	private java.util.List<gnwf.vo.WfCfg> wfCfgs;
	
	private List<Com> companyList;
	private List<WfStep> nextSteps;
	private List<WfStepUser> userList;
	private List<PrjtRole> roleList;
	private java.util.List<zrsy.vo.json.TreeJson> stepJsons;

	public String execute() throws Exception {
		try {
			if(wfStep != null && wfStep.getStepId() != null) {
				wfStep = new WfStepFacade().findById(wfStep);
				setJson(JSON.toJSONString(wfStep)); 
			}
			wfCfgs = new gnwf.facade.WfCfgFacade().find("select "+gnwf.vo.WfCfg.SELF_FIELDS+" from WfCfg",gnwf.vo.WfCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfStepAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	
	public String loadSteps() throws Exception {
		try {
			wfSteps = new WfStepFacade().find("select "+WfStep.SELF_FIELDS+" from WfStep where status=1 and flowid = "+ wfStep.getFlowId(),WfStep.SELF_FIELDS);
			for(int i=0;i<wfSteps.size();i++){
				TreeJson treeJson = new TreeJson();
				treeJson.setId(wfSteps.get(i).getStepId());
				treeJson.setText(wfSteps.get(i).getStepName());
				this.addtoStepJsons(treeJson);
			}
			this.setJson(JSON.toJSONString(stepJsons));
		}
		catch(Exception e) { 
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("IndexAction Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String add() throws Exception {
		try {
			//if(wfStep != null && wfStep.getStepId() != null) {
				//wfStep = new WfStepFacade().findById(wfStep);
				//setJson(JSON.toJSONString(wfStep)); 
			//}
//			wfCfgs = new gnwf.facade.WfCfgFacade().find("select "+gnwf.vo.WfCfg.SELF_FIELDS+" from WfCfg",gnwf.vo.WfCfg.SELF_FIELDS);
			
			//工作流
			WfCfg cfg = new WfCfg();
			cfg.setStatus(MSG.CONST_STATUS_1);
			wfCfgs = new WfCfgFacade().findAll(cfg);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfStepAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		return view();
	}
	
	private java.util.List<zrsy.vo.Gp> gps;
	public String view() throws Exception {
		try {
			
			
			
			
			
			gps = new GpFacade().find("select "+zrsy.vo.Gp.SELF_FIELDS+" from Gp where syId=2",zrsy.vo.Gp.SELF_FIELDS);
			wfStep = new WfStepFacade().findById(wfStep);
			
			//工作流
			WfCfg cfg = new WfCfg();
			cfg.setStatus(MSG.CONST_STATUS_1);
			wfCfgs = new WfCfgFacade().findAll(cfg);
			
			//公司
			//公司部门
			companyList = new ComFacade().find("select "+Com.SELF_FIELDS+" " +
					" from Com where status = 2 ",Com.SELF_FIELDS);
//			companyList = new ComFacade().find("select ComId,ComNm from com where status="+MSG.CONST_STATUS_1,"ComId,ComNm");

			//下一步骤列表		
			String nextStepSql = "select StepId,Sort,StepName,a.nextId as ischecked from WfStep " +
					"left join(select nextId from WfStepNext where stepid="+wfStep.getStepId()+")a on(WfStep.stepId=a.nextId) " +
					"where WfStep.status="+MSG.CONST_STATUS_1+" and WfStep.flowid="+wfStep.getFlowId()+" order by WfStep.Sort";
					//+" and WfStep.stepid!="+wfStep.getStepId();
			System.out.println(nextStepSql);
			nextSteps = new WfStepFacade().find(nextStepSql,"StepId,Sort,StepName,ischecked");
			
			//可选人
			WfStepUser stepUser = new WfStepUser();
			stepUser.setStepId(wfStep.getStepId());
			userList = new WfStepUserFacade().findAll(stepUser);
			
			
			//项目角色
			roleList = new PrjtRoleFacade().findAll(new PrjtRole());
			
			if(wfStep != null && wfStep.getStepId() != null) {
				setJson(JSON.toJSONString(wfStep)); 
			}
			
			
			
			
			
			
			
			
			
//			wfStep = new WfStepFacade().findById(wfStep);
//			
//			//工作流
//			WfCfg cfg = new WfCfg();
//			cfg.setStatus(MSG.CONST_STATUS_1);
//			wfCfgs = new WfCfgFacade().findAll(cfg);
//			
//			//公司
//			companyList = new ComFacade().find("select ComId,ComNm from com where status="+MSG.CONST_STATUS_1,"ComId,ComNm");
//
//			//下一步骤列表		
//			String nextStepSql = "select StepId,Sort,StepName,a.nextId as ischecked from WfStep " +
//					"left join(select nextId from WfStepNext where stepid="+wfStep.getStepId()+")a on(WfStep.stepId=a.nextId) " +
//					"where WfStep.status="+MSG.CONST_STATUS_1+" and WfStep.flowid="+wfStep.getFlowId()+" order by WfStep.Sort";
//					//+" and WfStep.stepid!="+wfStep.getStepId();
//			System.out.println(nextStepSql);
//			nextSteps = new WfStepFacade().find(nextStepSql,"StepId,Sort,StepName,ischecked");
//			
//			//可选人
//			WfStepUser stepUser = new WfStepUser();
//			stepUser.setStepId(wfStep.getStepId());
//			userList = new WfStepUserFacade().findAll(stepUser);
//			
//			//项目角色
//			roleList = new PrjtRoleFacade().findAll(new PrjtRole());
//			
//			
//			if(wfStep != null && wfStep.getStepId() != null) {
//				setJson(JSON.toJSONString(wfStep)); 
//			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfStepAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			WfCfg cfg = new WfCfg();
			cfg.setStatus(MSG.CONST_STATUS_1);
			wfCfgs = new WfCfgFacade().findAll(cfg);
			
			if(wfStep == null) wfStep = new WfStep();
			int total = new WfStepFacade().amount(wfStep);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfSteps = new WfStepFacade().find(wfStep,getPageVO());
			WfStepJson wfStepJson = new WfStepJson();
			wfStepJson.Rows = wfSteps;
			wfStepJson.Total = total;
			setJson(JSON.toJSONString(wfStepJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfStepAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			wfStep.setWfStepUsers(wfStepUsers);
			wfStep.setWfStepNexts(wfStepNexts);
			//wfStep.setWfRdTasks(wfRdTasks);

			if(wfStep.getStepId() == null){
				wfStep.setCreateBy(getUsrSession().getId());
				wfStep.setCreateDate(new Date());
				wfStep.setStatus(MSG.CONST_STATUS_1);
				new WfStepFacade().save(wfStep);
			}
			else{
				wfStep.setLastUpd(getUsrSession().getId());
				wfStep.setLastUpdDate(new Date());
				new WfStepFacade().update(wfStep);
			}
				
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfStepAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfStep.setCreateBy(getSession().getUserId());
			//wfStep.setCreateDate(new Date());
			//wfStep.setLastUpd(getSession().getUserId());
			//wfStep.setLastUpdDate(new Date());
			new WfStepFacade().update(wfStep);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfStepAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfSteps != null && wfSteps.size() > 0) {
				for(int i=0; i<wfSteps.size();i++) {
					if(wfSteps.get(i) != null) {
						//wfSteps.get(i).setLastUpd(getSession().getUserId());
						//wfSteps.get(i).setLastUpdDate(new Date());
						new WfStepFacade().submit(wfSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfSteps != null && wfSteps.size() > 0) {
				for(int i=0; i<wfSteps.size();i++) {
					if(wfSteps.get(i) != null) {
						//wfSteps.get(i).setLastUpd(getSession().getUserId());
						//wfSteps.get(i).setLastUpdDate(new Date());
						new WfStepFacade().submit(wfSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfSteps != null && wfSteps.size() > 0) {
				for(int i=0; i<wfSteps.size();i++) {
					if(wfSteps.get(i) != null) {
						//wfSteps.get(i).setLastUpd(getSession().getUserId());
						//wfSteps.get(i).setLastUpdDate(new Date());
						new WfStepFacade().update(wfSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfSteps != null && wfSteps.size() > 0) {
				for(int i=0; i<wfSteps.size();i++) {
					if(wfSteps.get(i) != null) {
						//wfSteps.get(i).setLastUpd(getSession().getUserId());
						//wfSteps.get(i).setLastUpdDate(new Date());
						new WfStepFacade().submit(wfSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfSteps != null && wfSteps.size() > 0) {
				for(int i=0; i<wfSteps.size();i++) {
					if(wfSteps.get(i) != null) {
						//wfSteps.get(i).setLastUpd(getSession().getUserId());
						//wfSteps.get(i).setLastUpdDate(new Date());
						new WfStepFacade().submit(wfSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfSteps != null && wfSteps.size() > 0) {
				for(int i=0; i<wfSteps.size();i++) {
					if(wfSteps.get(i) != null) {
						//wfSteps.get(i).setLastUpd(getSession().getUserId());
						//wfSteps.get(i).setLastUpdDate(new Date());
						new WfStepFacade().submit(wfSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfSteps != null && wfSteps.size() > 0) {
				for(int i=0; i<wfSteps.size();i++) {
					if(wfSteps.get(i) != null) {
						//wfSteps.get(i).setLastUpd(getSession().getUserId());
						//wfSteps.get(i).setLastUpdDate(new Date());
						new WfStepFacade().submit(wfSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfSteps != null && wfSteps.size() > 0) {
				for(int i=0; i<wfSteps.size();i++) {
					if(wfSteps.get(i) != null){
						//wfSteps.get(i).setLastUpd(getSession().getUserId());
						//wfSteps.get(i).setLastUpdDate(new Date());
						new WfStepFacade().submit(wfSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfSteps != null && wfSteps.size() > 0) {
				for(int i=0; i<wfSteps.size();i++) {
					if(wfSteps.get(i) != null){
						//wfSteps.get(i).setLastUpd(getSession().getUserId());
						//wfSteps.get(i).setLastUpdDate(new Date());
						new WfStepFacade().review(wfSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfSteps != null && wfSteps.size() > 0) {
				for(int i=0; i<wfSteps.size();i++) {
					if(wfSteps.get(i) != null) {
						//wfSteps.get(i).setLastUpd(getSession().getUserId());
						//wfSteps.get(i).setLastUpdDate(new Date());
						new WfStepFacade().review(wfSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfSteps != null && wfSteps.size() > 0) {
				for(int i=0; i<wfSteps.size();i++) {
					if(wfSteps.get(i) != null) {
						//wfSteps.get(i).setLastUpd(getSession().getUserId());
						//wfSteps.get(i).setLastUpdDate(new Date());
						new WfStepFacade().confirm(wfSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfSteps != null && wfSteps.size() > 0) {
				for(int i=0; i<wfSteps.size();i++) {
					if(wfSteps.get(i) != null) {
						//wfSteps.get(i).setLastUpd(getSession().getUserId());
						//wfSteps.get(i).setLastUpdDate(new Date());
						new WfStepFacade().confirm(wfSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfSteps != null && wfSteps.size() > 0) {
					for(int i=0; i<wfSteps.size();i++) {
						if(wfSteps.get(i) != null) {
							//wfSteps.get(i).setLastUpd(getSession().getUserId());
							//wfSteps.get(i).setLastUpdDate(new Date());
							new WfStepFacade().confirm(wfSteps.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfStepAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfStep> wfSteps = new WfStepFacade().find(wfStep);
			if(wfSteps != null && wfSteps.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"步骤ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"工作流定义ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"上一步骤",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"步骤类型",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"排序",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"是否自动流转",wcformat));
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
					ws.addCell(new Label(index,1,"最后步骤",wcformat));
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
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"创建日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"更新日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"步骤名称",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"步骤描述",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"本步骤对应页面",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfSteps.size();i++) {
					row++;
					int m = 0;
					if(wfSteps.get(i).getStepId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfSteps.get(i).getStepId(),wcformat));
					m++;
					if(wfSteps.get(i).getFlowId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfSteps.get(i).getFlowId(),wcformat));
					m++;
					if(wfSteps.get(i).getPreStep() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfSteps.get(i).getPreStep(),wcformat));
					m++;
					if(wfSteps.get(i).getStepType() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfSteps.get(i).getStepType(),wcformat));
					m++;
					if(wfSteps.get(i).getSort() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfSteps.get(i).getSort(),wcformat));
					m++;
					if(wfSteps.get(i).getIsAuto() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfSteps.get(i).getIsAuto(),wcformat));
					m++;
					if(wfSteps.get(i).getIsUpdForm() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfSteps.get(i).getIsUpdForm(),wcformat));
					m++;
					if(wfSteps.get(i).getIsSysFinsh() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfSteps.get(i).getIsSysFinsh(),wcformat));
					m++;
					if(wfSteps.get(i).getTimeQty() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfSteps.get(i).getTimeQty(),wcformat));
					m++;
					if(wfSteps.get(i).getSelectCom() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfSteps.get(i).getSelectCom(),wcformat));
					m++;
					if(wfSteps.get(i).getIsLastStep() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfSteps.get(i).getIsLastStep(),wcformat));
					m++;
					if(wfSteps.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfSteps.get(i).getStatus(),wcformat));
					m++;
					if(wfSteps.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfSteps.get(i).getCreateBy(),wcformat));
					m++;
					if(wfSteps.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfSteps.get(i).getLastUpd(),wcformat));
					m++;
					if(wfSteps.get(i).getSelectDept() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfSteps.get(i).getSelectDept(),wcformat));
					m++;
					if(wfSteps.get(i).getWaitAuxiliary() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfSteps.get(i).getWaitAuxiliary(),wcformat));
					m++;
					if(wfSteps.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfSteps.get(i).getCreateDate(),wcformat));
					m++;
					if(wfSteps.get(i).getLastUpdDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfSteps.get(i).getLastUpdDate(),wcformat));
					m++;
					if(wfSteps.get(i).getStepName() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfSteps.get(i).getStepName(),wcformat));
					m++;
					if(wfSteps.get(i).getStepDesc() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfSteps.get(i).getStepDesc(),wcformat));
					m++;
					if(wfSteps.get(i).getStepUri() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfSteps.get(i).getStepUri(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfStepListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfSteps != null && wfSteps.size() > 0) {
				for(int i=0; i<wfSteps.size();i++) {
					if(wfSteps.get(i) != null) {
						//wfSteps.get(i).setLastUpd(getSession().getUserId());
						//wfSteps.get(i).setLastUpdDate(new Date());
						new WfStepFacade().confirm(wfSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfStepAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfSteps != null && wfSteps.size() > 0) {
				for(int i=0; i<wfSteps.size();i++) {
					if(wfSteps.get(i) != null) {
						//wfSteps.get(i).setLastUpd(getSession().getUserId());
						//wfSteps.get(i).setLastUpdDate(new Date());
						new WfStepFacade().confirm(wfSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfStep> getWfSteps() {
		return wfSteps;
	}
	public void setWfSteps(List<WfStep> wfSteps) {
		this.wfSteps = wfSteps;
	}
	public WfStep getWfStep() {
		return wfStep;
	}
	public void setWfStep(WfStep wfStep) {
		this.wfStep = wfStep;
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
	public java.util.List<gnwf.vo.WfRdTask> getWfRdTasks() {
		return wfRdTasks;
	}
	public void setWfRdTasks(java.util.List<gnwf.vo.WfRdTask> wfRdTasks){
		this.wfRdTasks = wfRdTasks;
	}
	public void addtoWfRdTasks(gnwf.vo.WfRdTask wfRdTask){
		if(getWfRdTasks() == null) setWfRdTasks(new java.util.ArrayList<gnwf.vo.WfRdTask>());
			getWfRdTasks().add(wfRdTask);
	}
	public java.util.List<gnwf.vo.WfStepUser> getWfStepUsers() {
		return wfStepUsers;
	}
	public void setWfStepUsers(java.util.List<gnwf.vo.WfStepUser> wfStepUsers){
		this.wfStepUsers = wfStepUsers;
	}
	public void addtoWfStepUsers(gnwf.vo.WfStepUser wfStepUser){
		if(getWfStepUsers() == null) setWfStepUsers(new java.util.ArrayList<gnwf.vo.WfStepUser>());
			getWfStepUsers().add(wfStepUser);
	}
	public java.util.List<gnwf.vo.WfStepNext> getWfStepNexts() {
		return wfStepNexts;
	}
	public void setWfStepNexts(java.util.List<gnwf.vo.WfStepNext> wfStepNexts){
		this.wfStepNexts = wfStepNexts;
	}
	public void addtoWfStepNexts(gnwf.vo.WfStepNext wfStepNext){
		if(getWfStepNexts() == null) setWfStepNexts(new java.util.ArrayList<gnwf.vo.WfStepNext>());
			getWfStepNexts().add(wfStepNext);
	}
	public java.util.List<gnwf.vo.WfCfg> getWfCfgs() {
		return wfCfgs;
	}
	public void setWfCfgs(java.util.List<gnwf.vo.WfCfg> wfCfgs){
		this.wfCfgs = wfCfgs;
	}
	public void addtoWfCfgs(gnwf.vo.WfCfg wfCfg){
		if(getWfCfgs() == null) setWfCfgs(new java.util.ArrayList<gnwf.vo.WfCfg>());
			getWfCfgs().add(wfCfg);
	}
	public List<Com> getCompanyList() {
		return companyList;
	}
	public void setCompanyList(List<Com> companyList) {
		this.companyList = companyList;
	}
	public List<WfStep> getNextSteps() {
		return nextSteps;
	}
	public void setNextSteps(List<WfStep> nextSteps) {
		this.nextSteps = nextSteps;
	}
	public List<WfStepUser> getUserList() {
		return userList;
	}
	public void setUserList(List<WfStepUser> userList) {
		this.userList = userList;
	}
	public List<PrjtRole> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<PrjtRole> roleList) {
		this.roleList = roleList;
	}

	public java.util.List<zrsy.vo.json.TreeJson> getStepJsons() {
		return stepJsons;
	}

	public void setStepJsons(java.util.List<zrsy.vo.json.TreeJson> stepJsons) {
		this.stepJsons = stepJsons;
	}
	public void addtoStepJsons(zrsy.vo.json.TreeJson treeJson) {
		if(getStepJsons() == null) setStepJsons(new ArrayList<zrsy.vo.json.TreeJson>());
		getStepJsons().add(treeJson);
	}

	public java.util.List<zrsy.vo.Gp> getGps() {
		return gps;
	}

	public void setGps(java.util.List<zrsy.vo.Gp> gps) {
		this.gps = gps;
	}
}