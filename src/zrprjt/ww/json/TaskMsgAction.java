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

import zrprjt.facade.TaskMsgFacade;
import zrprjt.vo.TaskMsg;
import zrprjt.vo.json.TaskMsgJson;

public class TaskMsgAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<TaskMsg> taskMsgs;
	private TaskMsg taskMsg = new TaskMsg();
	private java.util.List<zrprjt.vo.Task> tasks;

	public String execute() throws Exception {
		try {
			if(taskMsg != null && taskMsg.getMsgId() != null) {
				taskMsg = new TaskMsgFacade().findById(taskMsg);
				setJson(JSON.toJSONString(taskMsg)); 
			}
			tasks = new zrprjt.facade.TaskFacade().find("select "+zrprjt.vo.Task.SELF_FIELDS+" from Task",zrprjt.vo.Task.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("TaskMsgAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(taskMsg != null && taskMsg.getMsgId() != null) {
				//taskMsg = new TaskMsgFacade().findById(taskMsg);
				//setJson(JSON.toJSONString(taskMsg)); 
			//}
			tasks = new zrprjt.facade.TaskFacade().find("select "+zrprjt.vo.Task.SELF_FIELDS+" from Task",zrprjt.vo.Task.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("TaskMsgAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(taskMsg != null && taskMsg.getMsgId() != null) {
				taskMsg = new TaskMsgFacade().findById(taskMsg);
				setJson(JSON.toJSONString(taskMsg)); 
			}
			tasks = new zrprjt.facade.TaskFacade().find("select "+zrprjt.vo.Task.SELF_FIELDS+" from Task",zrprjt.vo.Task.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("TaskMsgAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(taskMsg != null && taskMsg.getMsgId() != null) {
				taskMsg = new TaskMsgFacade().findById(taskMsg);
				setJson(JSON.toJSONString(taskMsg)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("TaskMsgAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(taskMsg == null) taskMsg = new TaskMsg();
			int total = new TaskMsgFacade().amount(taskMsg);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			taskMsgs = new TaskMsgFacade().find(taskMsg,getPageVO());
			TaskMsgJson taskMsgJson = new TaskMsgJson();
			taskMsgJson.Rows = taskMsgs;
			taskMsgJson.Total = total;
			setJson(JSON.toJSONString(taskMsgJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("TaskMsgAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			taskMsg.setCreateBy(getUsrSession().getId());
			taskMsg.setCreateDate(new Date());
			taskMsg.setLastUpd(getUsrSession().getId());
			taskMsg.setLastDate(new Date());

			new TaskMsgFacade().save(taskMsg);
//			if(taskMsg.getMsgId() == null)
//			else
//				new TaskMsgFacade().update(taskMsg);
//			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("TaskMsgAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//taskMsg.setCreateBy(getSession().getUserId());
			//taskMsg.setCreateDate(new Date());
			//taskMsg.setLastUpd(getSession().getUserId());
			//taskMsg.setLastUpdDate(new Date());
			new TaskMsgFacade().update(taskMsg);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("TaskMsgAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(taskMsgs != null && taskMsgs.size() > 0) {
				for(int i=0; i<taskMsgs.size();i++) {
					if(taskMsgs.get(i) != null) {
						//taskMsgs.get(i).setLastUpd(getSession().getUserId());
						//taskMsgs.get(i).setLastUpdDate(new Date());
						new TaskMsgFacade().submit(taskMsgs.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("TaskMsgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(taskMsgs != null && taskMsgs.size() > 0) {
				for(int i=0; i<taskMsgs.size();i++) {
					if(taskMsgs.get(i) != null) {
						//taskMsgs.get(i).setLastUpd(getSession().getUserId());
						//taskMsgs.get(i).setLastUpdDate(new Date());
						new TaskMsgFacade().submit(taskMsgs.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("TaskMsgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(taskMsgs != null && taskMsgs.size() > 0) {
				for(int i=0; i<taskMsgs.size();i++) {
					if(taskMsgs.get(i) != null) {
						//taskMsgs.get(i).setLastUpd(getSession().getUserId());
						//taskMsgs.get(i).setLastUpdDate(new Date());
						new TaskMsgFacade().update(taskMsgs.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("TaskMsgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(taskMsgs != null && taskMsgs.size() > 0) {
				for(int i=0; i<taskMsgs.size();i++) {
					if(taskMsgs.get(i) != null) {
						//taskMsgs.get(i).setLastUpd(getSession().getUserId());
						//taskMsgs.get(i).setLastUpdDate(new Date());
						new TaskMsgFacade().submit(taskMsgs.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("TaskMsgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(taskMsgs != null && taskMsgs.size() > 0) {
				for(int i=0; i<taskMsgs.size();i++) {
					if(taskMsgs.get(i) != null) {
						//taskMsgs.get(i).setLastUpd(getSession().getUserId());
						//taskMsgs.get(i).setLastUpdDate(new Date());
						new TaskMsgFacade().submit(taskMsgs.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("TaskMsgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(taskMsgs != null && taskMsgs.size() > 0) {
				for(int i=0; i<taskMsgs.size();i++) {
					if(taskMsgs.get(i) != null) {
						//taskMsgs.get(i).setLastUpd(getSession().getUserId());
						//taskMsgs.get(i).setLastUpdDate(new Date());
						new TaskMsgFacade().submit(taskMsgs.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("TaskMsgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(taskMsgs != null && taskMsgs.size() > 0) {
				for(int i=0; i<taskMsgs.size();i++) {
					if(taskMsgs.get(i) != null) {
						//taskMsgs.get(i).setLastUpd(getSession().getUserId());
						//taskMsgs.get(i).setLastUpdDate(new Date());
						new TaskMsgFacade().submit(taskMsgs.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("TaskMsgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(taskMsgs != null && taskMsgs.size() > 0) {
				for(int i=0; i<taskMsgs.size();i++) {
					if(taskMsgs.get(i) != null){
						//taskMsgs.get(i).setLastUpd(getSession().getUserId());
						//taskMsgs.get(i).setLastUpdDate(new Date());
						new TaskMsgFacade().submit(taskMsgs.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("TaskMsgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(taskMsgs != null && taskMsgs.size() > 0) {
				for(int i=0; i<taskMsgs.size();i++) {
					if(taskMsgs.get(i) != null){
						//taskMsgs.get(i).setLastUpd(getSession().getUserId());
						//taskMsgs.get(i).setLastUpdDate(new Date());
						new TaskMsgFacade().review(taskMsgs.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("TaskMsgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(taskMsgs != null && taskMsgs.size() > 0) {
				for(int i=0; i<taskMsgs.size();i++) {
					if(taskMsgs.get(i) != null) {
						//taskMsgs.get(i).setLastUpd(getSession().getUserId());
						//taskMsgs.get(i).setLastUpdDate(new Date());
						new TaskMsgFacade().review(taskMsgs.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("TaskMsgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(taskMsgs != null && taskMsgs.size() > 0) {
				for(int i=0; i<taskMsgs.size();i++) {
					if(taskMsgs.get(i) != null) {
						//taskMsgs.get(i).setLastUpd(getSession().getUserId());
						//taskMsgs.get(i).setLastUpdDate(new Date());
						new TaskMsgFacade().confirm(taskMsgs.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("TaskMsgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(taskMsgs != null && taskMsgs.size() > 0) {
				for(int i=0; i<taskMsgs.size();i++) {
					if(taskMsgs.get(i) != null) {
						//taskMsgs.get(i).setLastUpd(getSession().getUserId());
						//taskMsgs.get(i).setLastUpdDate(new Date());
						new TaskMsgFacade().confirm(taskMsgs.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("TaskMsgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(taskMsgs != null && taskMsgs.size() > 0) {
					for(int i=0; i<taskMsgs.size();i++) {
						if(taskMsgs.get(i) != null) {
							//taskMsgs.get(i).setLastUpd(getSession().getUserId());
							//taskMsgs.get(i).setLastUpdDate(new Date());
							new TaskMsgFacade().confirm(taskMsgs.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("TaskMsgAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<TaskMsg> taskMsgs = new TaskMsgFacade().find(taskMsg);
			if(taskMsgs != null && taskMsgs.size() > 0) {
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
					ws.addCell(new Label(index,1,"null",wcformat));
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
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<taskMsgs.size();i++) {
					row++;
					int m = 0;
					if(taskMsgs.get(i).getMsgId() != null) 
						ws.addCell(new jxl.write.Number(m,row,taskMsgs.get(i).getMsgId(),wcformat));
					m++;
					if(taskMsgs.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,taskMsgs.get(i).getStatus(),wcformat));
					m++;
					if(taskMsgs.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,taskMsgs.get(i).getCreateBy(),wcformat));
					m++;
					if(taskMsgs.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,taskMsgs.get(i).getLastUpd(),wcformat));
					m++;
					if(taskMsgs.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,taskMsgs.get(i).getCreateDate(),wcformat));
					m++;
					if(taskMsgs.get(i).getLastDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,taskMsgs.get(i).getLastDate(),wcformat));
					m++;
					if(taskMsgs.get(i).getReply() != null) 
						ws.addCell(new jxl.write.Label(m,row,taskMsgs.get(i).getReply(),wcformat));
					m++;
					if(taskMsgs.get(i).getTaskNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,taskMsgs.get(i).getTaskNo(),wcformat));
					m++;
					if(taskMsgs.get(i).getPrjtNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,taskMsgs.get(i).getPrjtNo(),wcformat));
					m++;
					if(taskMsgs.get(i).getWfNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,taskMsgs.get(i).getWfNo(),wcformat));
					m++;
					if(taskMsgs.get(i).getTitle() != null) 
						ws.addCell(new jxl.write.Label(m,row,taskMsgs.get(i).getTitle(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("TaskMsgListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(taskMsgs != null && taskMsgs.size() > 0) {
				for(int i=0; i<taskMsgs.size();i++) {
					if(taskMsgs.get(i) != null) {
						//taskMsgs.get(i).setLastUpd(getSession().getUserId());
						//taskMsgs.get(i).setLastUpdDate(new Date());
						new TaskMsgFacade().confirm(taskMsgs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("TaskMsgAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(taskMsgs != null && taskMsgs.size() > 0) {
				for(int i=0; i<taskMsgs.size();i++) {
					if(taskMsgs.get(i) != null) {
						//taskMsgs.get(i).setLastUpd(getSession().getUserId());
						//taskMsgs.get(i).setLastUpdDate(new Date());
						new TaskMsgFacade().confirm(taskMsgs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("TaskMsgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<TaskMsg> getTaskMsgs() {
		return taskMsgs;
	}
	public void setTaskMsgs(List<TaskMsg> taskMsgs) {
		this.taskMsgs = taskMsgs;
	}
	public TaskMsg getTaskMsg() {
		return taskMsg;
	}
	public void setTaskMsg(TaskMsg taskMsg) {
		this.taskMsg = taskMsg;
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