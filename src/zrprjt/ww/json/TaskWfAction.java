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

import zrprjt.facade.TaskWfFacade;
import zrprjt.vo.TaskWf;
import zrprjt.vo.json.TaskWfJson;

public class TaskWfAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<TaskWf> taskWfs;
	private TaskWf taskWf = new TaskWf();
	private java.util.List<zrprjt.vo.Task> tasks;

	public String execute() throws Exception {
		try {
			if(taskWf != null && taskWf.getSchId() != null) {
				taskWf = new TaskWfFacade().findById(taskWf);
				setJson(JSON.toJSONString(taskWf)); 
			}
			tasks = new zrprjt.facade.TaskFacade().find("select "+zrprjt.vo.Task.SELF_FIELDS+" from Task",zrprjt.vo.Task.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("TaskWfAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(taskWf != null && taskWf.getSchId() != null) {
				//taskWf = new TaskWfFacade().findById(taskWf);
				//setJson(JSON.toJSONString(taskWf)); 
			//}
			tasks = new zrprjt.facade.TaskFacade().find("select "+zrprjt.vo.Task.SELF_FIELDS+" from Task",zrprjt.vo.Task.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("TaskWfAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(taskWf != null && taskWf.getSchId() != null) {
				taskWf = new TaskWfFacade().findById(taskWf);
				setJson(JSON.toJSONString(taskWf)); 
			}
			tasks = new zrprjt.facade.TaskFacade().find("select "+zrprjt.vo.Task.SELF_FIELDS+" from Task",zrprjt.vo.Task.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("TaskWfAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(taskWf != null && taskWf.getSchId() != null) {
				taskWf = new TaskWfFacade().findById(taskWf);
				setJson(JSON.toJSONString(taskWf)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("TaskWfAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(taskWf == null) taskWf = new TaskWf();
			int total = new TaskWfFacade().amount(taskWf);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			taskWfs = new TaskWfFacade().find(taskWf,getPageVO());
			TaskWfJson taskWfJson = new TaskWfJson();
			taskWfJson.Rows = taskWfs;
			taskWfJson.Total = total;
			setJson(JSON.toJSONString(taskWfJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("TaskWfAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			taskWf.setCreateBy(getUsrSession().getId());
			taskWf.setCreateDate(new Date());
			taskWf.setLastUpd(getUsrSession().getId());
			taskWf.setLastDate(new Date());

			new TaskWfFacade().save(taskWf);
//			if(taskWf.getSchId() == null)
//			else
//				new TaskWfFacade().update(taskWf);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("TaskWfAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//taskWf.setCreateBy(getSession().getUserId());
			//taskWf.setCreateDate(new Date());
			//taskWf.setLastUpd(getSession().getUserId());
			//taskWf.setLastUpdDate(new Date());
			new TaskWfFacade().update(taskWf);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("TaskWfAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(taskWfs != null && taskWfs.size() > 0) {
				for(int i=0; i<taskWfs.size();i++) {
					if(taskWfs.get(i) != null) {
						//taskWfs.get(i).setLastUpd(getSession().getUserId());
						//taskWfs.get(i).setLastUpdDate(new Date());
						new TaskWfFacade().submit(taskWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("TaskWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(taskWfs != null && taskWfs.size() > 0) {
				for(int i=0; i<taskWfs.size();i++) {
					if(taskWfs.get(i) != null) {
						//taskWfs.get(i).setLastUpd(getSession().getUserId());
						//taskWfs.get(i).setLastUpdDate(new Date());
						new TaskWfFacade().submit(taskWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("TaskWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(taskWfs != null && taskWfs.size() > 0) {
				for(int i=0; i<taskWfs.size();i++) {
					if(taskWfs.get(i) != null) {
						//taskWfs.get(i).setLastUpd(getSession().getUserId());
						//taskWfs.get(i).setLastUpdDate(new Date());
						new TaskWfFacade().update(taskWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("TaskWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(taskWfs != null && taskWfs.size() > 0) {
				for(int i=0; i<taskWfs.size();i++) {
					if(taskWfs.get(i) != null) {
						//taskWfs.get(i).setLastUpd(getSession().getUserId());
						//taskWfs.get(i).setLastUpdDate(new Date());
						new TaskWfFacade().submit(taskWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("TaskWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(taskWfs != null && taskWfs.size() > 0) {
				for(int i=0; i<taskWfs.size();i++) {
					if(taskWfs.get(i) != null) {
						//taskWfs.get(i).setLastUpd(getSession().getUserId());
						//taskWfs.get(i).setLastUpdDate(new Date());
						new TaskWfFacade().submit(taskWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("TaskWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(taskWfs != null && taskWfs.size() > 0) {
				for(int i=0; i<taskWfs.size();i++) {
					if(taskWfs.get(i) != null) {
						//taskWfs.get(i).setLastUpd(getSession().getUserId());
						//taskWfs.get(i).setLastUpdDate(new Date());
						new TaskWfFacade().submit(taskWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("TaskWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(taskWfs != null && taskWfs.size() > 0) {
				for(int i=0; i<taskWfs.size();i++) {
					if(taskWfs.get(i) != null) {
						//taskWfs.get(i).setLastUpd(getSession().getUserId());
						//taskWfs.get(i).setLastUpdDate(new Date());
						new TaskWfFacade().submit(taskWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("TaskWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(taskWfs != null && taskWfs.size() > 0) {
				for(int i=0; i<taskWfs.size();i++) {
					if(taskWfs.get(i) != null){
						//taskWfs.get(i).setLastUpd(getSession().getUserId());
						//taskWfs.get(i).setLastUpdDate(new Date());
						new TaskWfFacade().submit(taskWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("TaskWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(taskWfs != null && taskWfs.size() > 0) {
				for(int i=0; i<taskWfs.size();i++) {
					if(taskWfs.get(i) != null){
						//taskWfs.get(i).setLastUpd(getSession().getUserId());
						//taskWfs.get(i).setLastUpdDate(new Date());
						new TaskWfFacade().review(taskWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("TaskWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(taskWfs != null && taskWfs.size() > 0) {
				for(int i=0; i<taskWfs.size();i++) {
					if(taskWfs.get(i) != null) {
						//taskWfs.get(i).setLastUpd(getSession().getUserId());
						//taskWfs.get(i).setLastUpdDate(new Date());
						new TaskWfFacade().review(taskWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("TaskWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(taskWfs != null && taskWfs.size() > 0) {
				for(int i=0; i<taskWfs.size();i++) {
					if(taskWfs.get(i) != null) {
						//taskWfs.get(i).setLastUpd(getSession().getUserId());
						//taskWfs.get(i).setLastUpdDate(new Date());
						new TaskWfFacade().confirm(taskWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("TaskWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(taskWfs != null && taskWfs.size() > 0) {
				for(int i=0; i<taskWfs.size();i++) {
					if(taskWfs.get(i) != null) {
						//taskWfs.get(i).setLastUpd(getSession().getUserId());
						//taskWfs.get(i).setLastUpdDate(new Date());
						new TaskWfFacade().confirm(taskWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("TaskWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(taskWfs != null && taskWfs.size() > 0) {
					for(int i=0; i<taskWfs.size();i++) {
						if(taskWfs.get(i) != null) {
							//taskWfs.get(i).setLastUpd(getSession().getUserId());
							//taskWfs.get(i).setLastUpdDate(new Date());
							new TaskWfFacade().confirm(taskWfs.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("TaskWfAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<TaskWf> taskWfs = new TaskWfFacade().find(taskWf);
			if(taskWfs != null && taskWfs.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"任务配置ID",wcformat));
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
					ws.addCell(new Label(index,1,"项目编号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"项目编号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"流程编号",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<taskWfs.size();i++) {
					row++;
					int m = 0;
					if(taskWfs.get(i).getSchId() != null) 
						ws.addCell(new jxl.write.Number(m,row,taskWfs.get(i).getSchId(),wcformat));
					m++;
					if(taskWfs.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,taskWfs.get(i).getStatus(),wcformat));
					m++;
					if(taskWfs.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,taskWfs.get(i).getCreateBy(),wcformat));
					m++;
					if(taskWfs.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,taskWfs.get(i).getLastUpd(),wcformat));
					m++;
					if(taskWfs.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,taskWfs.get(i).getCreateDate(),wcformat));
					m++;
					if(taskWfs.get(i).getLastDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,taskWfs.get(i).getLastDate(),wcformat));
					m++;
					if(taskWfs.get(i).getTaskNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,taskWfs.get(i).getTaskNo(),wcformat));
					m++;
					if(taskWfs.get(i).getPrjtNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,taskWfs.get(i).getPrjtNo(),wcformat));
					m++;
					if(taskWfs.get(i).getWfNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,taskWfs.get(i).getWfNo(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("TaskWfListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(taskWfs != null && taskWfs.size() > 0) {
				for(int i=0; i<taskWfs.size();i++) {
					if(taskWfs.get(i) != null) {
						//taskWfs.get(i).setLastUpd(getSession().getUserId());
						//taskWfs.get(i).setLastUpdDate(new Date());
						new TaskWfFacade().confirm(taskWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("TaskWfAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(taskWfs != null && taskWfs.size() > 0) {
				for(int i=0; i<taskWfs.size();i++) {
					if(taskWfs.get(i) != null) {
						//taskWfs.get(i).setLastUpd(getSession().getUserId());
						//taskWfs.get(i).setLastUpdDate(new Date());
						new TaskWfFacade().confirm(taskWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("TaskWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<TaskWf> getTaskWfs() {
		return taskWfs;
	}
	public void setTaskWfs(List<TaskWf> taskWfs) {
		this.taskWfs = taskWfs;
	}
	public TaskWf getTaskWf() {
		return taskWf;
	}
	public void setTaskWf(TaskWf taskWf) {
		this.taskWf = taskWf;
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
	public java.util.List<zrprjt.vo.Task> getTasks() {
		return tasks;
	}
	public void setTasks(java.util.List<zrprjt.vo.Task> tasks){
		this.tasks = tasks;
	}
	public void addtoTasks(zrprjt.vo.Task task){
		if(getTasks() == null) setTasks(new java.util.ArrayList<zrprjt.vo.Task>());
			getTasks().add(task);
	}

}