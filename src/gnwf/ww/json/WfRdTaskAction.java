package gnwf.ww.json;

import gnwf.facade.WfRdTaskFacade;
import gnwf.vo.WfRdTask;
import gnwf.vo.json.WfRdTaskJson;
import gnwf.ww.BasicAction;
import gnwf.ww.MSG;

import java.io.File;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;

import zrprjt.facade.PrjtUsrFacade;
import zrprjt.vo.PrjtUsr;

import com.alibaba.fastjson.JSON;

public class WfRdTaskAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfRdTask> wfRdTasks;
	private WfRdTask wfRdTask = new WfRdTask();
	private java.util.List<gnwf.vo.WfDoc> wfDocs;
	private java.util.List<gnwf.vo.WfRdSign> wfRdSigns;
	private java.util.List<gnwf.vo.WfStep> wfSteps;
	private java.util.List<gnwf.vo.WfRd> wfRds;

	public String execute() throws Exception {
		try {
			if(wfRdTask != null && wfRdTask.getTaskId() != null) {
				wfRdTask = new WfRdTaskFacade().findById(wfRdTask);
				setJson(JSON.toJSONString(wfRdTask)); 
			}
			wfSteps = new gnwf.facade.WfStepFacade().find("select "+gnwf.vo.WfStep.SELF_FIELDS+" from WfStep",gnwf.vo.WfStep.SELF_FIELDS);
			wfRds = new gnwf.facade.WfRdFacade().find("select "+gnwf.vo.WfRd.SELF_FIELDS+" from WfRd",gnwf.vo.WfRd.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdTaskAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(wfRdTask != null && wfRdTask.getTaskId() != null) {
				//wfRdTask = new WfRdTaskFacade().findById(wfRdTask);
				//setJson(JSON.toJSONString(wfRdTask)); 
			//}
			wfSteps = new gnwf.facade.WfStepFacade().find("select "+gnwf.vo.WfStep.SELF_FIELDS+" from WfStep",gnwf.vo.WfStep.SELF_FIELDS);
			wfRds = new gnwf.facade.WfRdFacade().find("select "+gnwf.vo.WfRd.SELF_FIELDS+" from WfRd",gnwf.vo.WfRd.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdTaskAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(wfRdTask != null && wfRdTask.getTaskId() != null) {
				wfRdTask = new WfRdTaskFacade().findById(wfRdTask);
				setJson(JSON.toJSONString(wfRdTask)); 
			}
			wfSteps = new gnwf.facade.WfStepFacade().find("select "+gnwf.vo.WfStep.SELF_FIELDS+" from WfStep",gnwf.vo.WfStep.SELF_FIELDS);
			wfRds = new gnwf.facade.WfRdFacade().find("select "+gnwf.vo.WfRd.SELF_FIELDS+" from WfRd",gnwf.vo.WfRd.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdTaskAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(wfRdTask != null && wfRdTask.getTaskId() != null) {
				wfRdTask = new WfRdTaskFacade().findById(wfRdTask);
				setJson(JSON.toJSONString(wfRdTask)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdTaskAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(wfRdTask == null) wfRdTask = new WfRdTask();
			int total = new WfRdTaskFacade().amount(wfRdTask);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfRdTasks = new WfRdTaskFacade().find(wfRdTask,getPageVO());
			
			
//			String sql = "select PrjtUsr.UsrId,PrjtRole.RoleNm as RoleNm from PrjtUsr " +
//					" left join PrjtRole on(PrjtRole.roleid=PrjtUsr.roleid) " +
//					" where PrjtUsr.prjtno=(select projectno from wfrd where wfno='"+wfRdTask.getWfNo()+"')" +
//					" or PrjtRole.roletyp=0";
			String sql = "select A.StepId,PrjtRole.roleId,PrjtUsr.UsrId,PrjtRole.RoleNm as RoleNm from PrjtUsr " +
					" left join PrjtRole on(PrjtRole.roleid=PrjtUsr.roleid) " +
					" inner join (select StepId,prjtroleId from WfStepUser where stepid " +
					"   in(select stepId from wfstep where flowid =(select flowId from wfrd where wfno='"+wfRdTask.getWfNo()+"')))A " +
					"   on(PrjtRole.roleid=A.prjtroleId)" +
					" where PrjtUsr.prjtno=(select projectno from wfrd where wfno='"+wfRdTask.getWfNo()+"') or PrjtRole.roletyp=0";
			List<PrjtUsr> roles = new PrjtUsrFacade().find(sql, "A.StepId,PrjtUsr.UsrId,PrjtRole.RoleNm as RoleNm");
			
			//TODO 
			if(roles!=null && roles.size()>0){
				for(int i=0;i<wfRdTasks.size();i++){
					WfRdTask task = wfRdTasks.get(i);
					
					Iterator<PrjtUsr> iter = roles.iterator();
					while(iter.hasNext()){
						PrjtUsr pusr = iter.next();
						
						int usrId = pusr.getUsrId();
						int stepId = pusr.getStepId();
						if(stepId==task.getStepId() && usrId==task.getAcceptBy()){
							task.setAcptRoleName(pusr.getRoleNm());
							iter.remove();
							break;
						}
					}
				}
			}
			
			WfRdTaskJson wfRdTaskJson = new WfRdTaskJson();
			wfRdTaskJson.Rows = wfRdTasks;
			wfRdTaskJson.Total = total;
			System.out.println(JSON.toJSONString(wfRdTaskJson));
			setJson(JSON.toJSONString(wfRdTaskJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdTaskAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//wfRdTask.setCreateBy(getSession().getUserId());
			//wfRdTask.setCreateDate(new Date());
			//wfRdTask.setLastUpd(getSession().getUserId());
			//wfRdTask.setLastUpdDate(new Date());
			wfRdTask.setWfDocs(wfDocs);
			wfRdTask.setWfRdSigns(wfRdSigns);

			if(wfRdTask.getTaskId() == null)
				new WfRdTaskFacade().save(wfRdTask);
			else
				new WfRdTaskFacade().update(wfRdTask);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfRdTaskAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfRdTask.setCreateBy(getSession().getUserId());
			//wfRdTask.setCreateDate(new Date());
			//wfRdTask.setLastUpd(getSession().getUserId());
			//wfRdTask.setLastUpdDate(new Date());
			new WfRdTaskFacade().update(wfRdTask);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfRdTaskAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfRdTasks != null && wfRdTasks.size() > 0) {
				for(int i=0; i<wfRdTasks.size();i++) {
					if(wfRdTasks.get(i) != null) {
						//wfRdTasks.get(i).setLastUpd(getSession().getUserId());
						//wfRdTasks.get(i).setLastUpdDate(new Date());
						new WfRdTaskFacade().submit(wfRdTasks.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfRdTaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfRdTasks != null && wfRdTasks.size() > 0) {
				for(int i=0; i<wfRdTasks.size();i++) {
					if(wfRdTasks.get(i) != null) {
						//wfRdTasks.get(i).setLastUpd(getSession().getUserId());
						//wfRdTasks.get(i).setLastUpdDate(new Date());
						new WfRdTaskFacade().submit(wfRdTasks.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfRdTaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfRdTasks != null && wfRdTasks.size() > 0) {
				for(int i=0; i<wfRdTasks.size();i++) {
					if(wfRdTasks.get(i) != null) {
						//wfRdTasks.get(i).setLastUpd(getSession().getUserId());
						//wfRdTasks.get(i).setLastUpdDate(new Date());
						new WfRdTaskFacade().update(wfRdTasks.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfRdTaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfRdTasks != null && wfRdTasks.size() > 0) {
				for(int i=0; i<wfRdTasks.size();i++) {
					if(wfRdTasks.get(i) != null) {
						//wfRdTasks.get(i).setLastUpd(getSession().getUserId());
						//wfRdTasks.get(i).setLastUpdDate(new Date());
						new WfRdTaskFacade().submit(wfRdTasks.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfRdTaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfRdTasks != null && wfRdTasks.size() > 0) {
				for(int i=0; i<wfRdTasks.size();i++) {
					if(wfRdTasks.get(i) != null) {
						//wfRdTasks.get(i).setLastUpd(getSession().getUserId());
						//wfRdTasks.get(i).setLastUpdDate(new Date());
						new WfRdTaskFacade().submit(wfRdTasks.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfRdTaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfRdTasks != null && wfRdTasks.size() > 0) {
				for(int i=0; i<wfRdTasks.size();i++) {
					if(wfRdTasks.get(i) != null) {
						//wfRdTasks.get(i).setLastUpd(getSession().getUserId());
						//wfRdTasks.get(i).setLastUpdDate(new Date());
						new WfRdTaskFacade().submit(wfRdTasks.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfRdTaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfRdTasks != null && wfRdTasks.size() > 0) {
				for(int i=0; i<wfRdTasks.size();i++) {
					if(wfRdTasks.get(i) != null) {
						//wfRdTasks.get(i).setLastUpd(getSession().getUserId());
						//wfRdTasks.get(i).setLastUpdDate(new Date());
						new WfRdTaskFacade().submit(wfRdTasks.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfRdTaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfRdTasks != null && wfRdTasks.size() > 0) {
				for(int i=0; i<wfRdTasks.size();i++) {
					if(wfRdTasks.get(i) != null){
						//wfRdTasks.get(i).setLastUpd(getSession().getUserId());
						//wfRdTasks.get(i).setLastUpdDate(new Date());
						new WfRdTaskFacade().submit(wfRdTasks.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfRdTaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfRdTasks != null && wfRdTasks.size() > 0) {
				for(int i=0; i<wfRdTasks.size();i++) {
					if(wfRdTasks.get(i) != null){
						//wfRdTasks.get(i).setLastUpd(getSession().getUserId());
						//wfRdTasks.get(i).setLastUpdDate(new Date());
						new WfRdTaskFacade().review(wfRdTasks.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfRdTaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfRdTasks != null && wfRdTasks.size() > 0) {
				for(int i=0; i<wfRdTasks.size();i++) {
					if(wfRdTasks.get(i) != null) {
						//wfRdTasks.get(i).setLastUpd(getSession().getUserId());
						//wfRdTasks.get(i).setLastUpdDate(new Date());
						new WfRdTaskFacade().review(wfRdTasks.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfRdTaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfRdTasks != null && wfRdTasks.size() > 0) {
				for(int i=0; i<wfRdTasks.size();i++) {
					if(wfRdTasks.get(i) != null) {
						//wfRdTasks.get(i).setLastUpd(getSession().getUserId());
						//wfRdTasks.get(i).setLastUpdDate(new Date());
						new WfRdTaskFacade().confirm(wfRdTasks.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfRdTaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfRdTasks != null && wfRdTasks.size() > 0) {
				for(int i=0; i<wfRdTasks.size();i++) {
					if(wfRdTasks.get(i) != null) {
						//wfRdTasks.get(i).setLastUpd(getSession().getUserId());
						//wfRdTasks.get(i).setLastUpdDate(new Date());
						new WfRdTaskFacade().confirm(wfRdTasks.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfRdTaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfRdTasks != null && wfRdTasks.size() > 0) {
					for(int i=0; i<wfRdTasks.size();i++) {
						if(wfRdTasks.get(i) != null) {
							//wfRdTasks.get(i).setLastUpd(getSession().getUserId());
							//wfRdTasks.get(i).setLastUpdDate(new Date());
							new WfRdTaskFacade().confirm(wfRdTasks.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfRdTaskAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfRdTask> wfRdTasks = new WfRdTaskFacade().find(wfRdTask);
			if(wfRdTasks != null && wfRdTasks.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"任务ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"上一任务ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"步骤ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"发送人",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"接收人",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"代理人",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"任务类型",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"状态",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"工作流记录编号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"要求完成时间",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"创建日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"接收日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"代理日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"备注",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfRdTasks.size();i++) {
					row++;
					int m = 0;
					if(wfRdTasks.get(i).getTaskId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfRdTasks.get(i).getTaskId(),wcformat));
					m++;
					if(wfRdTasks.get(i).getPreTaskId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfRdTasks.get(i).getPreTaskId(),wcformat));
					m++;
					if(wfRdTasks.get(i).getStepId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfRdTasks.get(i).getStepId(),wcformat));
					m++;
					if(wfRdTasks.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfRdTasks.get(i).getCreateBy(),wcformat));
					m++;
					if(wfRdTasks.get(i).getAcceptBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfRdTasks.get(i).getAcceptBy(),wcformat));
					m++;
					if(wfRdTasks.get(i).getAgentBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfRdTasks.get(i).getAgentBy(),wcformat));
					m++;
					if(wfRdTasks.get(i).getTaskType() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfRdTasks.get(i).getTaskType(),wcformat));
					m++;
					if(wfRdTasks.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfRdTasks.get(i).getStatus(),wcformat));
					m++;
					if(wfRdTasks.get(i).getIsSystemFinsh() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfRdTasks.get(i).getIsSystemFinsh(),wcformat));
					m++;
					if(wfRdTasks.get(i).getWfNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfRdTasks.get(i).getWfNo(),wcformat));
					m++;
					if(wfRdTasks.get(i).getReqDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfRdTasks.get(i).getReqDate(),wcformat));
					m++;
					if(wfRdTasks.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfRdTasks.get(i).getCreateDate(),wcformat));
					m++;
					if(wfRdTasks.get(i).getAcceptDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfRdTasks.get(i).getAcceptDate(),wcformat));
					m++;
					if(wfRdTasks.get(i).getAgentDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfRdTasks.get(i).getAgentDate(),wcformat));
					m++;
					if(wfRdTasks.get(i).getEndDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfRdTasks.get(i).getEndDate(),wcformat));
					m++;
					if(wfRdTasks.get(i).getRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfRdTasks.get(i).getRemark(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfRdTaskListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfRdTasks != null && wfRdTasks.size() > 0) {
				for(int i=0; i<wfRdTasks.size();i++) {
					if(wfRdTasks.get(i) != null) {
						//wfRdTasks.get(i).setLastUpd(getSession().getUserId());
						//wfRdTasks.get(i).setLastUpdDate(new Date());
						new WfRdTaskFacade().confirm(wfRdTasks.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfRdTaskAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfRdTasks != null && wfRdTasks.size() > 0) {
				for(int i=0; i<wfRdTasks.size();i++) {
					if(wfRdTasks.get(i) != null) {
						//wfRdTasks.get(i).setLastUpd(getSession().getUserId());
						//wfRdTasks.get(i).setLastUpdDate(new Date());
						new WfRdTaskFacade().confirm(wfRdTasks.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfRdTaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfRdTask> getWfRdTasks() {
		return wfRdTasks;
	}
	public void setWfRdTasks(List<WfRdTask> wfRdTasks) {
		this.wfRdTasks = wfRdTasks;
	}
	public WfRdTask getWfRdTask() {
		return wfRdTask;
	}
	public void setWfRdTask(WfRdTask wfRdTask) {
		this.wfRdTask = wfRdTask;
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
	public void setWfDocs(java.util.List<gnwf.vo.WfDoc> wfDocs){
		this.wfDocs = wfDocs;
	}
	public void addtoWfDocs(gnwf.vo.WfDoc wfDoc){
		if(getWfDocs() == null) setWfDocs(new java.util.ArrayList<gnwf.vo.WfDoc>());
			getWfDocs().add(wfDoc);
	}
	public java.util.List<gnwf.vo.WfRdSign> getWfRdSigns() {
		return wfRdSigns;
	}
	public void setWfRdSigns(java.util.List<gnwf.vo.WfRdSign> wfRdSigns){
		this.wfRdSigns = wfRdSigns;
	}
	public void addtoWfRdSigns(gnwf.vo.WfRdSign wfRdSign){
		if(getWfRdSigns() == null) setWfRdSigns(new java.util.ArrayList<gnwf.vo.WfRdSign>());
			getWfRdSigns().add(wfRdSign);
	}
	public java.util.List<gnwf.vo.WfStep> getWfSteps() {
		return wfSteps;
	}
	public void setWfSteps(java.util.List<gnwf.vo.WfStep> wfSteps){
		this.wfSteps = wfSteps;
	}
	public void addtoWfSteps(gnwf.vo.WfStep wfStep){
		if(getWfSteps() == null) setWfSteps(new java.util.ArrayList<gnwf.vo.WfStep>());
			getWfSteps().add(wfStep);
	}
	public java.util.List<gnwf.vo.WfRd> getWfRds() {
		return wfRds;
	}
	public void setWfRds(java.util.List<gnwf.vo.WfRd> wfRds){
		this.wfRds = wfRds;
	}
	public void addtoWfRds(gnwf.vo.WfRd wfRd){
		if(getWfRds() == null) setWfRds(new java.util.ArrayList<gnwf.vo.WfRd>());
			getWfRds().add(wfRd);
	}

}