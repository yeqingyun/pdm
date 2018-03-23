package zrprjt.ww.json;

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

import zrprjt.ww.MSG;
import zrprjt.ww.BasicAction;

import zrprjt.facade.DriverRdFacade;
import zrprjt.vo.DriverRd;
import zrprjt.vo.json.DriverRdJson;

public class DriverRdAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<DriverRd> driverRds;
	private DriverRd driverRd = new DriverRd();

	public String execute() throws Exception {
		try {
			if(driverRd != null && driverRd.getLogId() != null) {
				driverRd = new DriverRdFacade().findById(driverRd);
				setJson(JSON.toJSONString(driverRd)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("DriverRdAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(driverRd != null && driverRd.getLogId() != null) {
				//driverRd = new DriverRdFacade().findById(driverRd);
				//setJson(JSON.toJSONString(driverRd)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("DriverRdAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(driverRd != null && driverRd.getLogId() != null) {
				driverRd = new DriverRdFacade().findById(driverRd);
				setJson(JSON.toJSONString(driverRd)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("DriverRdAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(driverRd != null && driverRd.getLogId() != null) {
				driverRd = new DriverRdFacade().findById(driverRd);
				setJson(JSON.toJSONString(driverRd)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("DriverRdAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(driverRd == null) driverRd = new DriverRd();
			int total = new DriverRdFacade().amount(driverRd);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			driverRds = new DriverRdFacade().find(driverRd,getPageVO());
			DriverRdJson driverRdJson = new DriverRdJson();
			driverRdJson.Rows = driverRds;
			driverRdJson.Total = total;
			setJson(JSON.toJSONString(driverRdJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("DriverRdAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//driverRd.setCreateBy(getSession().getUserId());
			//driverRd.setCreateDate(new Date());
			//driverRd.setLastUpd(getSession().getUserId());
			//driverRd.setLastUpdDate(new Date());

			if(driverRd.getLogId() == null)
				new DriverRdFacade().save(driverRd);
			else
				new DriverRdFacade().update(driverRd);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("DriverRdAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//driverRd.setCreateBy(getSession().getUserId());
			//driverRd.setCreateDate(new Date());
			//driverRd.setLastUpd(getSession().getUserId());
			//driverRd.setLastUpdDate(new Date());
			new DriverRdFacade().update(driverRd);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("DriverRdAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(driverRds != null && driverRds.size() > 0) {
				for(int i=0; i<driverRds.size();i++) {
					if(driverRds.get(i) != null) {
						//driverRds.get(i).setLastUpd(getSession().getUserId());
						//driverRds.get(i).setLastUpdDate(new Date());
						new DriverRdFacade().submit(driverRds.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("DriverRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(driverRds != null && driverRds.size() > 0) {
				for(int i=0; i<driverRds.size();i++) {
					if(driverRds.get(i) != null) {
						//driverRds.get(i).setLastUpd(getSession().getUserId());
						//driverRds.get(i).setLastUpdDate(new Date());
						new DriverRdFacade().submit(driverRds.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("DriverRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(driverRds != null && driverRds.size() > 0) {
				for(int i=0; i<driverRds.size();i++) {
					if(driverRds.get(i) != null) {
						//driverRds.get(i).setLastUpd(getSession().getUserId());
						//driverRds.get(i).setLastUpdDate(new Date());
						new DriverRdFacade().update(driverRds.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("DriverRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(driverRds != null && driverRds.size() > 0) {
				for(int i=0; i<driverRds.size();i++) {
					if(driverRds.get(i) != null) {
						//driverRds.get(i).setLastUpd(getSession().getUserId());
						//driverRds.get(i).setLastUpdDate(new Date());
						new DriverRdFacade().submit(driverRds.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("DriverRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(driverRds != null && driverRds.size() > 0) {
				for(int i=0; i<driverRds.size();i++) {
					if(driverRds.get(i) != null) {
						//driverRds.get(i).setLastUpd(getSession().getUserId());
						//driverRds.get(i).setLastUpdDate(new Date());
						new DriverRdFacade().submit(driverRds.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("DriverRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(driverRds != null && driverRds.size() > 0) {
				for(int i=0; i<driverRds.size();i++) {
					if(driverRds.get(i) != null) {
						//driverRds.get(i).setLastUpd(getSession().getUserId());
						//driverRds.get(i).setLastUpdDate(new Date());
						new DriverRdFacade().submit(driverRds.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("DriverRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(driverRds != null && driverRds.size() > 0) {
				for(int i=0; i<driverRds.size();i++) {
					if(driverRds.get(i) != null) {
						//driverRds.get(i).setLastUpd(getSession().getUserId());
						//driverRds.get(i).setLastUpdDate(new Date());
						new DriverRdFacade().submit(driverRds.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("DriverRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(driverRds != null && driverRds.size() > 0) {
				for(int i=0; i<driverRds.size();i++) {
					if(driverRds.get(i) != null){
						//driverRds.get(i).setLastUpd(getSession().getUserId());
						//driverRds.get(i).setLastUpdDate(new Date());
						new DriverRdFacade().submit(driverRds.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("DriverRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(driverRds != null && driverRds.size() > 0) {
				for(int i=0; i<driverRds.size();i++) {
					if(driverRds.get(i) != null){
						//driverRds.get(i).setLastUpd(getSession().getUserId());
						//driverRds.get(i).setLastUpdDate(new Date());
						new DriverRdFacade().review(driverRds.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("DriverRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(driverRds != null && driverRds.size() > 0) {
				for(int i=0; i<driverRds.size();i++) {
					if(driverRds.get(i) != null) {
						//driverRds.get(i).setLastUpd(getSession().getUserId());
						//driverRds.get(i).setLastUpdDate(new Date());
						new DriverRdFacade().review(driverRds.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("DriverRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(driverRds != null && driverRds.size() > 0) {
				for(int i=0; i<driverRds.size();i++) {
					if(driverRds.get(i) != null) {
						//driverRds.get(i).setLastUpd(getSession().getUserId());
						//driverRds.get(i).setLastUpdDate(new Date());
						new DriverRdFacade().confirm(driverRds.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("DriverRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(driverRds != null && driverRds.size() > 0) {
				for(int i=0; i<driverRds.size();i++) {
					if(driverRds.get(i) != null) {
						//driverRds.get(i).setLastUpd(getSession().getUserId());
						//driverRds.get(i).setLastUpdDate(new Date());
						new DriverRdFacade().confirm(driverRds.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("DriverRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(driverRds != null && driverRds.size() > 0) {
					for(int i=0; i<driverRds.size();i++) {
						if(driverRds.get(i) != null) {
							//driverRds.get(i).setLastUpd(getSession().getUserId());
							//driverRds.get(i).setLastUpdDate(new Date());
							new DriverRdFacade().confirm(driverRds.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("DriverRdAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<DriverRd> driverRds = new DriverRdFacade().find(driverRd);
			if(driverRds != null && driverRds.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"记录ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"驱动ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"工作流编号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"驱动时间",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"备注",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"状态",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"创建人",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"创建日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"最后更新",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"更新日期",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<driverRds.size();i++) {
					row++;
					int m = 0;
					if(driverRds.get(i).getLogId() != null) 
						ws.addCell(new jxl.write.Number(m,row,driverRds.get(i).getLogId(),wcformat));
					m++;
					if(driverRds.get(i).getDriverId() != null) 
						ws.addCell(new jxl.write.Number(m,row,driverRds.get(i).getDriverId(),wcformat));
					m++;
					if(driverRds.get(i).getWfNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,driverRds.get(i).getWfNo(),wcformat));
					m++;
					if(driverRds.get(i).getDriverDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,driverRds.get(i).getDriverDate(),wcformat));
					m++;
					if(driverRds.get(i).getRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,driverRds.get(i).getRemark(),wcformat));
					m++;
					if(driverRds.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,driverRds.get(i).getStatus(),wcformat));
					m++;
					if(driverRds.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,driverRds.get(i).getCreateBy(),wcformat));
					m++;
					if(driverRds.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,driverRds.get(i).getCreateDate(),wcformat));
					m++;
					if(driverRds.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,driverRds.get(i).getLastUpd(),wcformat));
					m++;
					if(driverRds.get(i).getLastDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,driverRds.get(i).getLastDate(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("DriverRdListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(driverRds != null && driverRds.size() > 0) {
				for(int i=0; i<driverRds.size();i++) {
					if(driverRds.get(i) != null) {
						//driverRds.get(i).setLastUpd(getSession().getUserId());
						//driverRds.get(i).setLastUpdDate(new Date());
						new DriverRdFacade().confirm(driverRds.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("DriverRdAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(driverRds != null && driverRds.size() > 0) {
				for(int i=0; i<driverRds.size();i++) {
					if(driverRds.get(i) != null) {
						//driverRds.get(i).setLastUpd(getSession().getUserId());
						//driverRds.get(i).setLastUpdDate(new Date());
						new DriverRdFacade().confirm(driverRds.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("DriverRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<DriverRd> getDriverRds() {
		return driverRds;
	}
	public void setDriverRds(List<DriverRd> driverRds) {
		this.driverRds = driverRds;
	}
	public DriverRd getDriverRd() {
		return driverRd;
	}
	public void setDriverRd(DriverRd driverRd) {
		this.driverRd = driverRd;
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