package zrprjt.ww.json;

import java.io.File;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.write.WritableWorkbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import zrprjt.ww.MSG;
import zrprjt.ww.BasicAction;

import zrprjt.facade.TaskFacade;
import zrprjt.vo.Task;
import zrprjt.vo.json.TaskJson;

public class TaskAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<Task> tasks;
	private Task task = new Task();
	private java.util.List<zrprjt.vo.TaskMsg> taskMsgs;
	private java.util.List<zrprjt.vo.TaskWf> taskWfs;
	private java.util.List<zrprjt.vo.PrjtDef> prjtDefs;
	private java.util.List<zrprjt.vo.SchCfg> schCfgs;

	public String execute() throws Exception {
		try {
			if(task != null && task.getSchId() != null) {
				task = new TaskFacade().findById(task);
				setJson(JSON.toJSONString(task)); 
			}
			prjtDefs = new zrprjt.facade.PrjtDefFacade().find("select "+zrprjt.vo.PrjtDef.SELF_FIELDS+" from PrjtDef",zrprjt.vo.PrjtDef.SELF_FIELDS);
			schCfgs = new zrprjt.facade.SchCfgFacade().find("select "+zrprjt.vo.SchCfg.SELF_FIELDS+" from SchCfg",zrprjt.vo.SchCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("TaskAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(task != null && task.getSchId() != null) {
				//task = new TaskFacade().findById(task);
				//setJson(JSON.toJSONString(task)); 
			//}
			prjtDefs = new zrprjt.facade.PrjtDefFacade().find("select "+zrprjt.vo.PrjtDef.SELF_FIELDS+" from PrjtDef",zrprjt.vo.PrjtDef.SELF_FIELDS);
			schCfgs = new zrprjt.facade.SchCfgFacade().find("select "+zrprjt.vo.SchCfg.SELF_FIELDS+" from SchCfg",zrprjt.vo.SchCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("TaskAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(task != null && task.getSchId() != null) {
				task = new TaskFacade().findById(task);
				setJson(JSON.toJSONString(task)); 
			}
			prjtDefs = new zrprjt.facade.PrjtDefFacade().find("select "+zrprjt.vo.PrjtDef.SELF_FIELDS+" from PrjtDef",zrprjt.vo.PrjtDef.SELF_FIELDS);
			schCfgs = new zrprjt.facade.SchCfgFacade().find("select "+zrprjt.vo.SchCfg.SELF_FIELDS+" from SchCfg",zrprjt.vo.SchCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("TaskAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(task != null && task.getSchId() != null) {
				task = new TaskFacade().findById(task);
				setJson(JSON.toJSONString(task)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("TaskAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(task == null) task = new Task();
			int total = new TaskFacade().amount(task);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			tasks = new TaskFacade().find(task,getPageVO());
			TaskJson taskJson = new TaskJson();
			taskJson.Rows = tasks;
			taskJson.Total = total;
			setJson(JSON.toJSONString(taskJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("TaskAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			task.setCreateBy(getUsrSession().getId());
			task.setCreateDate(new Date());
			task.setLastUpd(getUsrSession().getId());
			task.setLastDate(new Date());
//			task.setTaskMsgs(taskMsgs);
//			task.setTaskWfs(taskWfs);

			new TaskFacade().save(task);
//			if(task.getSchId() == null)
//			else
//				new TaskFacade().update(task);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("TaskAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//task.setCreateBy(getSession().getUserId());
			//task.setCreateDate(new Date());
			//task.setLastUpd(getSession().getUserId());
			//task.setLastUpdDate(new Date());
			new TaskFacade().update(task);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("TaskAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(tasks != null && tasks.size() > 0) {
				for(int i=0; i<tasks.size();i++) {
					if(tasks.get(i) != null) {
						//tasks.get(i).setLastUpd(getSession().getUserId());
						//tasks.get(i).setLastUpdDate(new Date());
						new TaskFacade().submit(tasks.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("TaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(tasks != null && tasks.size() > 0) {
				for(int i=0; i<tasks.size();i++) {
					if(tasks.get(i) != null) {
						//tasks.get(i).setLastUpd(getSession().getUserId());
						//tasks.get(i).setLastUpdDate(new Date());
						new TaskFacade().submit(tasks.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("TaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(tasks != null && tasks.size() > 0) {
				for(int i=0; i<tasks.size();i++) {
					if(tasks.get(i) != null) {
						//tasks.get(i).setLastUpd(getSession().getUserId());
						//tasks.get(i).setLastUpdDate(new Date());
						new TaskFacade().update(tasks.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("TaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(tasks != null && tasks.size() > 0) {
				for(int i=0; i<tasks.size();i++) {
					if(tasks.get(i) != null) {
						//tasks.get(i).setLastUpd(getSession().getUserId());
						//tasks.get(i).setLastUpdDate(new Date());
						new TaskFacade().submit(tasks.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("TaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(tasks != null && tasks.size() > 0) {
				for(int i=0; i<tasks.size();i++) {
					if(tasks.get(i) != null) {
						//tasks.get(i).setLastUpd(getSession().getUserId());
						//tasks.get(i).setLastUpdDate(new Date());
						new TaskFacade().submit(tasks.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("TaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(tasks != null && tasks.size() > 0) {
				for(int i=0; i<tasks.size();i++) {
					if(tasks.get(i) != null) {
						//tasks.get(i).setLastUpd(getSession().getUserId());
						//tasks.get(i).setLastUpdDate(new Date());
						new TaskFacade().submit(tasks.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("TaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(tasks != null && tasks.size() > 0) {
				for(int i=0; i<tasks.size();i++) {
					if(tasks.get(i) != null) {
						//tasks.get(i).setLastUpd(getSession().getUserId());
						//tasks.get(i).setLastUpdDate(new Date());
						new TaskFacade().submit(tasks.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("TaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(tasks != null && tasks.size() > 0) {
				for(int i=0; i<tasks.size();i++) {
					if(tasks.get(i) != null){
						//tasks.get(i).setLastUpd(getSession().getUserId());
						//tasks.get(i).setLastUpdDate(new Date());
						new TaskFacade().submit(tasks.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("TaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(tasks != null && tasks.size() > 0) {
				for(int i=0; i<tasks.size();i++) {
					if(tasks.get(i) != null){
						//tasks.get(i).setLastUpd(getSession().getUserId());
						//tasks.get(i).setLastUpdDate(new Date());
						new TaskFacade().review(tasks.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("TaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(tasks != null && tasks.size() > 0) {
				for(int i=0; i<tasks.size();i++) {
					if(tasks.get(i) != null) {
						//tasks.get(i).setLastUpd(getSession().getUserId());
						//tasks.get(i).setLastUpdDate(new Date());
						new TaskFacade().review(tasks.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("TaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(tasks != null && tasks.size() > 0) {
				for(int i=0; i<tasks.size();i++) {
					if(tasks.get(i) != null) {
						//tasks.get(i).setLastUpd(getSession().getUserId());
						//tasks.get(i).setLastUpdDate(new Date());
						new TaskFacade().confirm(tasks.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("TaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(tasks != null && tasks.size() > 0) {
				for(int i=0; i<tasks.size();i++) {
					if(tasks.get(i) != null) {
						//tasks.get(i).setLastUpd(getSession().getUserId());
						//tasks.get(i).setLastUpdDate(new Date());
						new TaskFacade().confirm(tasks.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("TaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(tasks != null && tasks.size() > 0) {
					for(int i=0; i<tasks.size();i++) {
						if(tasks.get(i) != null) {
							//tasks.get(i).setLastUpd(getSession().getUserId());
							//tasks.get(i).setLastUpdDate(new Date());
							new TaskFacade().confirm(tasks.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("TaskAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<Task> tasks = new TaskFacade().find(task);
			if(tasks != null && tasks.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"配置ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"任务办理",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"任务分派",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"序号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"级别",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"等级",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"百分比",wcformat));
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
					ws.addCell(new Label(index,1,"计划开始",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"计划结束",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"实际开始",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"实际结束",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"创建日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"更新日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"项目编号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"任务名称",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"备注",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<tasks.size();i++) {
					row++;
					int m = 0;
					if(tasks.get(i).getSchId() != null) 
						ws.addCell(new jxl.write.Number(m,row,tasks.get(i).getSchId(),wcformat));
					m++;
					if(tasks.get(i).getTasker() != null) 
						ws.addCell(new jxl.write.Number(m,row,tasks.get(i).getTasker(),wcformat));
					m++;
					if(tasks.get(i).getSender() != null) 
						ws.addCell(new jxl.write.Number(m,row,tasks.get(i).getSender(),wcformat));
					m++;
					if(tasks.get(i).getSchNo() != null) 
						ws.addCell(new jxl.write.Number(m,row,tasks.get(i).getSchNo(),wcformat));
					m++;
					if(tasks.get(i).getLeve() != null) 
						ws.addCell(new jxl.write.Number(m,row,tasks.get(i).getLeve(),wcformat));
					m++;
					if(tasks.get(i).getGrade() != null) 
						ws.addCell(new jxl.write.Number(m,row,tasks.get(i).getGrade(),wcformat));
					m++;
					if(tasks.get(i).getPerce() != null) 
						ws.addCell(new jxl.write.Number(m,row,tasks.get(i).getPerce(),wcformat));
					m++;
					if(tasks.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,tasks.get(i).getStatus(),wcformat));
					m++;
					if(tasks.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,tasks.get(i).getCreateBy(),wcformat));
					m++;
					if(tasks.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,tasks.get(i).getLastUpd(),wcformat));
					m++;
					if(tasks.get(i).getPlanStaDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,tasks.get(i).getPlanStaDate(),wcformat));
					m++;
					if(tasks.get(i).getPlanOveDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,tasks.get(i).getPlanOveDate(),wcformat));
					m++;
					if(tasks.get(i).getStaDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,tasks.get(i).getStaDate(),wcformat));
					m++;
					if(tasks.get(i).getOveDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,tasks.get(i).getOveDate(),wcformat));
					m++;
					if(tasks.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,tasks.get(i).getCreateDate(),wcformat));
					m++;
					if(tasks.get(i).getLastDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,tasks.get(i).getLastDate(),wcformat));
					m++;
					if(tasks.get(i).getTaskNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,String.valueOf(tasks.get(i).getTaskNo()),wcformat));
					m++;
					if(tasks.get(i).getPrjtNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,tasks.get(i).getPrjtNo(),wcformat));
					m++;
					if(tasks.get(i).getParent() != null) 
						ws.addCell(new jxl.write.Label(m,row,String.valueOf(tasks.get(i).getParent()),wcformat));
					m++;
					if(tasks.get(i).getTaskNm() != null) 
						ws.addCell(new jxl.write.Label(m,row,tasks.get(i).getTaskNm(),wcformat));
					m++;
					if(tasks.get(i).getRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,tasks.get(i).getRemark(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("TaskListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(tasks != null && tasks.size() > 0) {
				for(int i=0; i<tasks.size();i++) {
					if(tasks.get(i) != null) {
						//tasks.get(i).setLastUpd(getSession().getUserId());
						//tasks.get(i).setLastUpdDate(new Date());
						new TaskFacade().confirm(tasks.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("TaskAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(tasks != null && tasks.size() > 0) {
				for(int i=0; i<tasks.size();i++) {
					if(tasks.get(i) != null) {
						//tasks.get(i).setLastUpd(getSession().getUserId());
						//tasks.get(i).setLastUpdDate(new Date());
						new TaskFacade().confirm(tasks.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("TaskAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
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
	public java.util.List<zrprjt.vo.TaskMsg> getTaskMsgs() {
		return taskMsgs;
	}
	public void setTaskMsgs(java.util.List<zrprjt.vo.TaskMsg> taskMsgs){
		this.taskMsgs = taskMsgs;
	}
	public void addtoTaskMsgs(zrprjt.vo.TaskMsg taskMsg){
		if(getTaskMsgs() == null) setTaskMsgs(new java.util.ArrayList<zrprjt.vo.TaskMsg>());
			getTaskMsgs().add(taskMsg);
	}
	public java.util.List<zrprjt.vo.TaskWf> getTaskWfs() {
		return taskWfs;
	}
	public void setTaskWfs(java.util.List<zrprjt.vo.TaskWf> taskWfs){
		this.taskWfs = taskWfs;
	}
	public void addtoTaskWfs(zrprjt.vo.TaskWf taskWf){
		if(getTaskWfs() == null) setTaskWfs(new java.util.ArrayList<zrprjt.vo.TaskWf>());
			getTaskWfs().add(taskWf);
	}
	public java.util.List<zrprjt.vo.PrjtDef> getPrjtDefs() {
		return prjtDefs;
	}
	public void setPrjtDefs(java.util.List<zrprjt.vo.PrjtDef> prjtDefs){
		this.prjtDefs = prjtDefs;
	}
	public void addtoPrjtDefs(zrprjt.vo.PrjtDef prjtDef){
		if(getPrjtDefs() == null) setPrjtDefs(new java.util.ArrayList<zrprjt.vo.PrjtDef>());
			getPrjtDefs().add(prjtDef);
	}
	public java.util.List<zrprjt.vo.SchCfg> getSchCfgs() {
		return schCfgs;
	}
	public void setSchCfgs(java.util.List<zrprjt.vo.SchCfg> schCfgs){
		this.schCfgs = schCfgs;
	}
	public void addtoSchCfgs(zrprjt.vo.SchCfg schCfg){
		if(getSchCfgs() == null) setSchCfgs(new java.util.ArrayList<zrprjt.vo.SchCfg>());
			getSchCfgs().add(schCfg);
	}

}