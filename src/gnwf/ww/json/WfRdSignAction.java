package gnwf.ww.json;

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

import gnwf.ww.MSG;
import gnwf.ww.BasicAction;

import gnwf.facade.WfRdSignFacade;
import gnwf.vo.WfRdSign;
import gnwf.vo.json.WfRdSignJson;

public class WfRdSignAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfRdSign> wfRdSigns;
	private WfRdSign wfRdSign = new WfRdSign();
	private java.util.List<gnwf.vo.WfRdTask> wfRdTasks;

	public String execute() throws Exception {
		try {
			if(wfRdSign != null && wfRdSign.getTaskId() != null) {
				wfRdSign = new WfRdSignFacade().findById(wfRdSign);
				setJson(JSON.toJSONString(wfRdSign)); 
			}
			wfRdTasks = new gnwf.facade.WfRdTaskFacade().find("select "+gnwf.vo.WfRdTask.SELF_FIELDS+" from WfRdTask",gnwf.vo.WfRdTask.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdSignAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(wfRdSign != null && wfRdSign.getTaskId() != null) {
				//wfRdSign = new WfRdSignFacade().findById(wfRdSign);
				//setJson(JSON.toJSONString(wfRdSign)); 
			//}
			wfRdTasks = new gnwf.facade.WfRdTaskFacade().find("select "+gnwf.vo.WfRdTask.SELF_FIELDS+" from WfRdTask",gnwf.vo.WfRdTask.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdSignAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(wfRdSign != null && wfRdSign.getTaskId() != null) {
				wfRdSign = new WfRdSignFacade().findById(wfRdSign);
				setJson(JSON.toJSONString(wfRdSign)); 
			}
			wfRdTasks = new gnwf.facade.WfRdTaskFacade().find("select "+gnwf.vo.WfRdTask.SELF_FIELDS+" from WfRdTask",gnwf.vo.WfRdTask.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdSignAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(wfRdSign != null && wfRdSign.getTaskId() != null) {
				wfRdSign = new WfRdSignFacade().findById(wfRdSign);
				setJson(JSON.toJSONString(wfRdSign)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdSignAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(wfRdSign == null) wfRdSign = new WfRdSign();
			int total = new WfRdSignFacade().amount(wfRdSign);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfRdSigns = new WfRdSignFacade().find(wfRdSign,getPageVO());
			WfRdSignJson wfRdSignJson = new WfRdSignJson();
			wfRdSignJson.Rows = wfRdSigns;
			wfRdSignJson.Total = total;
			setJson(JSON.toJSONString(wfRdSignJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdSignAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//wfRdSign.setCreateBy(getSession().getUserId());
			//wfRdSign.setCreateDate(new Date());
			//wfRdSign.setLastUpd(getSession().getUserId());
			//wfRdSign.setLastUpdDate(new Date());

			if(wfRdSign.getTaskId() == null)
				new WfRdSignFacade().save(wfRdSign);
			else
				new WfRdSignFacade().update(wfRdSign);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfRdSignAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfRdSign.setCreateBy(getSession().getUserId());
			//wfRdSign.setCreateDate(new Date());
			//wfRdSign.setLastUpd(getSession().getUserId());
			//wfRdSign.setLastUpdDate(new Date());
			new WfRdSignFacade().update(wfRdSign);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfRdSignAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfRdSigns != null && wfRdSigns.size() > 0) {
				for(int i=0; i<wfRdSigns.size();i++) {
					if(wfRdSigns.get(i) != null) {
						//wfRdSigns.get(i).setLastUpd(getSession().getUserId());
						//wfRdSigns.get(i).setLastUpdDate(new Date());
						new WfRdSignFacade().submit(wfRdSigns.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfRdSignAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfRdSigns != null && wfRdSigns.size() > 0) {
				for(int i=0; i<wfRdSigns.size();i++) {
					if(wfRdSigns.get(i) != null) {
						//wfRdSigns.get(i).setLastUpd(getSession().getUserId());
						//wfRdSigns.get(i).setLastUpdDate(new Date());
						new WfRdSignFacade().submit(wfRdSigns.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfRdSignAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfRdSigns != null && wfRdSigns.size() > 0) {
				for(int i=0; i<wfRdSigns.size();i++) {
					if(wfRdSigns.get(i) != null) {
						//wfRdSigns.get(i).setLastUpd(getSession().getUserId());
						//wfRdSigns.get(i).setLastUpdDate(new Date());
						new WfRdSignFacade().update(wfRdSigns.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfRdSignAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfRdSigns != null && wfRdSigns.size() > 0) {
				for(int i=0; i<wfRdSigns.size();i++) {
					if(wfRdSigns.get(i) != null) {
						//wfRdSigns.get(i).setLastUpd(getSession().getUserId());
						//wfRdSigns.get(i).setLastUpdDate(new Date());
						new WfRdSignFacade().submit(wfRdSigns.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfRdSignAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfRdSigns != null && wfRdSigns.size() > 0) {
				for(int i=0; i<wfRdSigns.size();i++) {
					if(wfRdSigns.get(i) != null) {
						//wfRdSigns.get(i).setLastUpd(getSession().getUserId());
						//wfRdSigns.get(i).setLastUpdDate(new Date());
						new WfRdSignFacade().submit(wfRdSigns.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfRdSignAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfRdSigns != null && wfRdSigns.size() > 0) {
				for(int i=0; i<wfRdSigns.size();i++) {
					if(wfRdSigns.get(i) != null) {
						//wfRdSigns.get(i).setLastUpd(getSession().getUserId());
						//wfRdSigns.get(i).setLastUpdDate(new Date());
						new WfRdSignFacade().submit(wfRdSigns.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfRdSignAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfRdSigns != null && wfRdSigns.size() > 0) {
				for(int i=0; i<wfRdSigns.size();i++) {
					if(wfRdSigns.get(i) != null) {
						//wfRdSigns.get(i).setLastUpd(getSession().getUserId());
						//wfRdSigns.get(i).setLastUpdDate(new Date());
						new WfRdSignFacade().submit(wfRdSigns.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfRdSignAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfRdSigns != null && wfRdSigns.size() > 0) {
				for(int i=0; i<wfRdSigns.size();i++) {
					if(wfRdSigns.get(i) != null){
						//wfRdSigns.get(i).setLastUpd(getSession().getUserId());
						//wfRdSigns.get(i).setLastUpdDate(new Date());
						new WfRdSignFacade().submit(wfRdSigns.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfRdSignAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfRdSigns != null && wfRdSigns.size() > 0) {
				for(int i=0; i<wfRdSigns.size();i++) {
					if(wfRdSigns.get(i) != null){
						//wfRdSigns.get(i).setLastUpd(getSession().getUserId());
						//wfRdSigns.get(i).setLastUpdDate(new Date());
						new WfRdSignFacade().review(wfRdSigns.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfRdSignAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfRdSigns != null && wfRdSigns.size() > 0) {
				for(int i=0; i<wfRdSigns.size();i++) {
					if(wfRdSigns.get(i) != null) {
						//wfRdSigns.get(i).setLastUpd(getSession().getUserId());
						//wfRdSigns.get(i).setLastUpdDate(new Date());
						new WfRdSignFacade().review(wfRdSigns.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfRdSignAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfRdSigns != null && wfRdSigns.size() > 0) {
				for(int i=0; i<wfRdSigns.size();i++) {
					if(wfRdSigns.get(i) != null) {
						//wfRdSigns.get(i).setLastUpd(getSession().getUserId());
						//wfRdSigns.get(i).setLastUpdDate(new Date());
						new WfRdSignFacade().confirm(wfRdSigns.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfRdSignAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfRdSigns != null && wfRdSigns.size() > 0) {
				for(int i=0; i<wfRdSigns.size();i++) {
					if(wfRdSigns.get(i) != null) {
						//wfRdSigns.get(i).setLastUpd(getSession().getUserId());
						//wfRdSigns.get(i).setLastUpdDate(new Date());
						new WfRdSignFacade().confirm(wfRdSigns.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfRdSignAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfRdSigns != null && wfRdSigns.size() > 0) {
					for(int i=0; i<wfRdSigns.size();i++) {
						if(wfRdSigns.get(i) != null) {
							//wfRdSigns.get(i).setLastUpd(getSession().getUserId());
							//wfRdSigns.get(i).setLastUpdDate(new Date());
							new WfRdSignFacade().confirm(wfRdSigns.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfRdSignAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfRdSign> wfRdSigns = new WfRdSignFacade().find(wfRdSign);
			if(wfRdSigns != null && wfRdSigns.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"工作任务",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"用户",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"状态",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"工作流编号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"创建日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"会签意见",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfRdSigns.size();i++) {
					row++;
					int m = 0;
					if(wfRdSigns.get(i).getTaskId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfRdSigns.get(i).getTaskId(),wcformat));
					m++;
					if(wfRdSigns.get(i).getUserId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfRdSigns.get(i).getUserId(),wcformat));
					m++;
					if(wfRdSigns.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfRdSigns.get(i).getStatus(),wcformat));
					m++;
					if(wfRdSigns.get(i).getWfNoId() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfRdSigns.get(i).getWfNoId(),wcformat));
					m++;
					if(wfRdSigns.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfRdSigns.get(i).getCreateDate(),wcformat));
					m++;
					if(wfRdSigns.get(i).getSignText() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfRdSigns.get(i).getSignText(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfRdSignListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfRdSigns != null && wfRdSigns.size() > 0) {
				for(int i=0; i<wfRdSigns.size();i++) {
					if(wfRdSigns.get(i) != null) {
						//wfRdSigns.get(i).setLastUpd(getSession().getUserId());
						//wfRdSigns.get(i).setLastUpdDate(new Date());
						new WfRdSignFacade().confirm(wfRdSigns.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfRdSignAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfRdSigns != null && wfRdSigns.size() > 0) {
				for(int i=0; i<wfRdSigns.size();i++) {
					if(wfRdSigns.get(i) != null) {
						//wfRdSigns.get(i).setLastUpd(getSession().getUserId());
						//wfRdSigns.get(i).setLastUpdDate(new Date());
						new WfRdSignFacade().confirm(wfRdSigns.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfRdSignAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfRdSign> getWfRdSigns() {
		return wfRdSigns;
	}
	public void setWfRdSigns(List<WfRdSign> wfRdSigns) {
		this.wfRdSigns = wfRdSigns;
	}
	public WfRdSign getWfRdSign() {
		return wfRdSign;
	}
	public void setWfRdSign(WfRdSign wfRdSign) {
		this.wfRdSign = wfRdSign;
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

}