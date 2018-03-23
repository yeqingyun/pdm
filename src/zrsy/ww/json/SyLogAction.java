package zrsy.ww.json;

import java.io.File;
import java.io.OutputStream;
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

import zrsy.ww.MSG;
import zrsy.ww.BasicAction;

import zrsy.facade.SyLogFacade;
import zrsy.vo.SyLog;
import zrsy.vo.json.SyLogJson;

public class SyLogAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<SyLog> syLogs;
	private SyLog syLog = new SyLog();

	public String execute() throws Exception {
		try {
			if(syLog != null && syLog.getLogId() != null) {
				syLog = new SyLogFacade().findById(syLog);
				setJson(JSON.toJSONString(syLog)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SyLogAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(syLog != null && syLog.getLogId() != null) {
				//syLog = new SyLogFacade().findById(syLog);
				//setJson(JSON.toJSONString(syLog)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SyLogAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(syLog != null && syLog.getLogId() != null) {
				syLog = new SyLogFacade().findById(syLog);
				setJson(JSON.toJSONString(syLog)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SyLogAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(syLog != null && syLog.getLogId() != null) {
				syLog = new SyLogFacade().findById(syLog);
				setJson(JSON.toJSONString(syLog)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SyLogAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(syLog == null) syLog = new SyLog();
			int total = new SyLogFacade().amount(syLog);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			syLogs = new SyLogFacade().find(syLog,getPageVO());
			SyLogJson syLogJson = new SyLogJson();
			syLogJson.Rows = syLogs;
			syLogJson.Total = total;
			setJson(JSON.toJSONString(syLogJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SyLogAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//syLog.setCreateBy(getSession().getUserId());
			//syLog.setCreateDate(new Date());
			//syLog.setLastUpd(getSession().getUserId());
			//syLog.setLastUpdDate(new Date());

			if(syLog.getLogId() == null)
				new SyLogFacade().save(syLog);
			else
				new SyLogFacade().update(syLog);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("SyLogAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//syLog.setCreateBy(getSession().getUserId());
			//syLog.setCreateDate(new Date());
			//syLog.setLastUpd(getSession().getUserId());
			//syLog.setLastUpdDate(new Date());
			new SyLogFacade().update(syLog);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("SyLogAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(syLogs != null && syLogs.size() > 0) {
				for(int i=0; i<syLogs.size();i++) {
					if(syLogs.get(i) != null) {
						//syLogs.get(i).setLastUpd(getSession().getUserId());
						//syLogs.get(i).setLastUpdDate(new Date());
						new SyLogFacade().submit(syLogs.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("SyLogAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(syLogs != null && syLogs.size() > 0) {
				for(int i=0; i<syLogs.size();i++) {
					if(syLogs.get(i) != null) {
						//syLogs.get(i).setLastUpd(getSession().getUserId());
						//syLogs.get(i).setLastUpdDate(new Date());
						new SyLogFacade().submit(syLogs.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("SyLogAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(syLogs != null && syLogs.size() > 0) {
				for(int i=0; i<syLogs.size();i++) {
					if(syLogs.get(i) != null) {
						//syLogs.get(i).setLastUpd(getSession().getUserId());
						//syLogs.get(i).setLastUpdDate(new Date());
						new SyLogFacade().update(syLogs.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("SyLogAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(syLogs != null && syLogs.size() > 0) {
				for(int i=0; i<syLogs.size();i++) {
					if(syLogs.get(i) != null) {
						//syLogs.get(i).setLastUpd(getSession().getUserId());
						//syLogs.get(i).setLastUpdDate(new Date());
						new SyLogFacade().submit(syLogs.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("SyLogAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(syLogs != null && syLogs.size() > 0) {
				for(int i=0; i<syLogs.size();i++) {
					if(syLogs.get(i) != null) {
						//syLogs.get(i).setLastUpd(getSession().getUserId());
						//syLogs.get(i).setLastUpdDate(new Date());
						new SyLogFacade().submit(syLogs.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("SyLogAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(syLogs != null && syLogs.size() > 0) {
				for(int i=0; i<syLogs.size();i++) {
					if(syLogs.get(i) != null) {
						//syLogs.get(i).setLastUpd(getSession().getUserId());
						//syLogs.get(i).setLastUpdDate(new Date());
						new SyLogFacade().submit(syLogs.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("SyLogAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(syLogs != null && syLogs.size() > 0) {
				for(int i=0; i<syLogs.size();i++) {
					if(syLogs.get(i) != null) {
						//syLogs.get(i).setLastUpd(getSession().getUserId());
						//syLogs.get(i).setLastUpdDate(new Date());
						new SyLogFacade().submit(syLogs.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("SyLogAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(syLogs != null && syLogs.size() > 0) {
				for(int i=0; i<syLogs.size();i++) {
					if(syLogs.get(i) != null){
						//syLogs.get(i).setLastUpd(getSession().getUserId());
						//syLogs.get(i).setLastUpdDate(new Date());
						new SyLogFacade().submit(syLogs.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("SyLogAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(syLogs != null && syLogs.size() > 0) {
				for(int i=0; i<syLogs.size();i++) {
					if(syLogs.get(i) != null){
						//syLogs.get(i).setLastUpd(getSession().getUserId());
						//syLogs.get(i).setLastUpdDate(new Date());
						new SyLogFacade().review(syLogs.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("SyLogAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(syLogs != null && syLogs.size() > 0) {
				for(int i=0; i<syLogs.size();i++) {
					if(syLogs.get(i) != null) {
						//syLogs.get(i).setLastUpd(getSession().getUserId());
						//syLogs.get(i).setLastUpdDate(new Date());
						new SyLogFacade().review(syLogs.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("SyLogAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(syLogs != null && syLogs.size() > 0) {
				for(int i=0; i<syLogs.size();i++) {
					if(syLogs.get(i) != null) {
						//syLogs.get(i).setLastUpd(getSession().getUserId());
						//syLogs.get(i).setLastUpdDate(new Date());
						new SyLogFacade().confirm(syLogs.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("SyLogAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(syLogs != null && syLogs.size() > 0) {
				for(int i=0; i<syLogs.size();i++) {
					if(syLogs.get(i) != null) {
						//syLogs.get(i).setLastUpd(getSession().getUserId());
						//syLogs.get(i).setLastUpdDate(new Date());
						new SyLogFacade().confirm(syLogs.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("SyLogAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(syLogs != null && syLogs.size() > 0) {
					for(int i=0; i<syLogs.size();i++) {
						if(syLogs.get(i) != null) {
							//syLogs.get(i).setLastUpd(getSession().getUserId());
							//syLogs.get(i).setLastUpdDate(new Date());
							new SyLogFacade().confirm(syLogs.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("SyLogAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<SyLog> syLogs = new SyLogFacade().find(syLog);
			if(syLogs != null && syLogs.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"日志ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"用户ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"日志代码",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"日志名称",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"日志明细",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"记录时间",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"客户端IP",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<syLogs.size();i++) {
					row++;
					int m = 0;
					if(syLogs.get(i).getLogId() != null) 
						ws.addCell(new jxl.write.Number(m,row,syLogs.get(i).getLogId(),wcformat));
					m++;
					if(syLogs.get(i).getUserId() != null) 
						ws.addCell(new jxl.write.Number(m,row,syLogs.get(i).getUserId(),wcformat));
					m++;
					if(syLogs.get(i).getLogCode() != null) 
						ws.addCell(new jxl.write.Label(m,row,syLogs.get(i).getLogCode(),wcformat));
					m++;
					if(syLogs.get(i).getLogNm() != null) 
						ws.addCell(new jxl.write.Label(m,row,syLogs.get(i).getLogNm(),wcformat));
					m++;
					if(syLogs.get(i).getLogText() != null) 
						ws.addCell(new jxl.write.Label(m,row,syLogs.get(i).getLogText(),wcformat));
					m++;
					if(syLogs.get(i).getLogDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,syLogs.get(i).getLogDate(),wcformat));
					m++;
					if(syLogs.get(i).getIpAddr() != null) 
						ws.addCell(new jxl.write.Label(m,row,syLogs.get(i).getIpAddr(),wcformat));
					m++;
					if(syLogs.get(i).getLogType() != null) 
						ws.addCell(new jxl.write.Number(m,row,syLogs.get(i).getLogType(),wcformat));
					m++;
					if(syLogs.get(i).getRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,syLogs.get(i).getRemark(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("SyLogListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(syLogs != null && syLogs.size() > 0) {
				for(int i=0; i<syLogs.size();i++) {
					if(syLogs.get(i) != null) {
						//syLogs.get(i).setLastUpd(getSession().getUserId());
						//syLogs.get(i).setLastUpdDate(new Date());
						new SyLogFacade().confirm(syLogs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("SyLogAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(syLogs != null && syLogs.size() > 0) {
				for(int i=0; i<syLogs.size();i++) {
					if(syLogs.get(i) != null) {
						//syLogs.get(i).setLastUpd(getSession().getUserId());
						//syLogs.get(i).setLastUpdDate(new Date());
						new SyLogFacade().confirm(syLogs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("SyLogAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<SyLog> getSyLogs() {
		return syLogs;
	}
	public void setSyLogs(List<SyLog> syLogs) {
		this.syLogs = syLogs;
	}
	public SyLog getSyLog() {
		return syLog;
	}
	public void setSyLog(SyLog syLog) {
		this.syLog = syLog;
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

}