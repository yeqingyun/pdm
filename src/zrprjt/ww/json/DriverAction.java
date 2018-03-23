package zrprjt.ww.json;

import gnwf.facade.WfStepFacade;
import gnwf.vo.WfStep;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;

import zrprjt.facade.DriverFacade;
import zrprjt.vo.Driver;
import zrprjt.vo.DriverDtl;
import zrprjt.vo.json.DriverJson;
import zrprjt.ww.BasicAction;
import zrprjt.ww.MSG;

import com.alibaba.fastjson.JSON;

public class DriverAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<Driver> drivers;
	private List<DriverDtl> driverDtls;
	private Driver driver = new Driver();
	private java.util.List<gnwf.vo.WfCfg> wfCfgs;
	
	public String execute() throws Exception {
		try {
			wfCfgs = new gnwf.facade.WfCfgFacade().find("select "+gnwf.vo.WfCfg.SELF_FIELDS+" from WfCfg",gnwf.vo.WfCfg.SELF_FIELDS);
			if(driver != null && driver.getDriveId() != null) {
				driver = new DriverFacade().findById(driver);
				setJson(JSON.toJSONString(driver)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("DriverAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			wfCfgs = new gnwf.facade.WfCfgFacade().find("select "+gnwf.vo.WfCfg.SELF_FIELDS+" from WfCfg",gnwf.vo.WfCfg.SELF_FIELDS);
			//if(driver != null && driver.getDriveId() != null) {
				//driver = new DriverFacade().findById(driver);
				//setJson(JSON.toJSONString(driver)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("DriverAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			wfCfgs = new gnwf.facade.WfCfgFacade().find("select "+gnwf.vo.WfCfg.SELF_FIELDS+" from WfCfg",gnwf.vo.WfCfg.SELF_FIELDS);
			if(driver != null && driver.getDriveId() != null) {
				driver = new DriverFacade().findById(driver);
				setJson(JSON.toJSONString(driver)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("DriverAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(driver != null && driver.getDriveId() != null) {
				driver = new DriverFacade().findById(driver);
				setJson(JSON.toJSONString(driver)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("DriverAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(driver == null) driver = new Driver();
			int total = new DriverFacade().amount(driver);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			drivers = new DriverFacade().find(driver,getPageVO());
			DriverJson driverJson = new DriverJson();
			driverJson.Rows = drivers;
			driverJson.Total = total;
			setJson(JSON.toJSONString(driverJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("DriverAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//driver.setCreateBy(getSession().getUserId());
			//driver.setCreateDate(new Date());
			//driver.setLastUpd(getSession().getUserId());
			//driver.setLastUpdDate(new Date());
			driver.setDriverDtls(driverDtls);
			if(driver.getDriveId() == null) {
				driver.setCreateBy(getUsrSession().getId());
				
				driver.setCreateDate(new java.util.Date());
				driver.setStatus(1);
			}
			driver.setLastDate(new java.util.Date());
			driver.setLastUpd(getUsrSession().getId());
			if(driver.getDriveId() == null)
				new DriverFacade().save(driver);
			else
				new DriverFacade().update(driver);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("DriverAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//driver.setCreateBy(getSession().getUserId());
			//driver.setCreateDate(new Date());
			//driver.setLastUpd(getSession().getUserId());
			//driver.setLastUpdDate(new Date());
			new DriverFacade().update(driver);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("DriverAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(drivers != null && drivers.size() > 0) {
				for(int i=0; i<drivers.size();i++) {
					if(drivers.get(i) != null) {
						//drivers.get(i).setLastUpd(getSession().getUserId());
						//drivers.get(i).setLastUpdDate(new Date());
						new DriverFacade().submit(drivers.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("DriverAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(drivers != null && drivers.size() > 0) {
				for(int i=0; i<drivers.size();i++) {
					if(drivers.get(i) != null) {
						//drivers.get(i).setLastUpd(getSession().getUserId());
						//drivers.get(i).setLastUpdDate(new Date());
						new DriverFacade().submit(drivers.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("DriverAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(drivers != null && drivers.size() > 0) {
				for(int i=0; i<drivers.size();i++) {
					if(drivers.get(i) != null) {
						//drivers.get(i).setLastUpd(getSession().getUserId());
						//drivers.get(i).setLastUpdDate(new Date());
						new DriverFacade().update(drivers.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("DriverAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(drivers != null && drivers.size() > 0) {
				for(int i=0; i<drivers.size();i++) {
					if(drivers.get(i) != null) {
						//drivers.get(i).setLastUpd(getSession().getUserId());
						//drivers.get(i).setLastUpdDate(new Date());
						new DriverFacade().submit(drivers.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("DriverAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(drivers != null && drivers.size() > 0) {
				for(int i=0; i<drivers.size();i++) {
					if(drivers.get(i) != null) {
						//drivers.get(i).setLastUpd(getSession().getUserId());
						//drivers.get(i).setLastUpdDate(new Date());
						new DriverFacade().submit(drivers.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("DriverAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(drivers != null && drivers.size() > 0) {
				for(int i=0; i<drivers.size();i++) {
					if(drivers.get(i) != null) {
						//drivers.get(i).setLastUpd(getSession().getUserId());
						//drivers.get(i).setLastUpdDate(new Date());
						new DriverFacade().submit(drivers.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("DriverAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(drivers != null && drivers.size() > 0) {
				for(int i=0; i<drivers.size();i++) {
					if(drivers.get(i) != null) {
						//drivers.get(i).setLastUpd(getSession().getUserId());
						//drivers.get(i).setLastUpdDate(new Date());
						new DriverFacade().submit(drivers.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("DriverAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(drivers != null && drivers.size() > 0) {
				for(int i=0; i<drivers.size();i++) {
					if(drivers.get(i) != null){
						//drivers.get(i).setLastUpd(getSession().getUserId());
						//drivers.get(i).setLastUpdDate(new Date());
						new DriverFacade().submit(drivers.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("DriverAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(drivers != null && drivers.size() > 0) {
				for(int i=0; i<drivers.size();i++) {
					if(drivers.get(i) != null){
						//drivers.get(i).setLastUpd(getSession().getUserId());
						//drivers.get(i).setLastUpdDate(new Date());
						new DriverFacade().review(drivers.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("DriverAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(drivers != null && drivers.size() > 0) {
				for(int i=0; i<drivers.size();i++) {
					if(drivers.get(i) != null) {
						//drivers.get(i).setLastUpd(getSession().getUserId());
						//drivers.get(i).setLastUpdDate(new Date());
						new DriverFacade().review(drivers.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("DriverAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(drivers != null && drivers.size() > 0) {
				for(int i=0; i<drivers.size();i++) {
					if(drivers.get(i) != null) {
						//drivers.get(i).setLastUpd(getSession().getUserId());
						//drivers.get(i).setLastUpdDate(new Date());
						new DriverFacade().confirm(drivers.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("DriverAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(drivers != null && drivers.size() > 0) {
				for(int i=0; i<drivers.size();i++) {
					if(drivers.get(i) != null) {
						//drivers.get(i).setLastUpd(getSession().getUserId());
						//drivers.get(i).setLastUpdDate(new Date());
						new DriverFacade().confirm(drivers.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("DriverAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(drivers != null && drivers.size() > 0) {
					for(int i=0; i<drivers.size();i++) {
						if(drivers.get(i) != null) {
							//drivers.get(i).setLastUpd(getSession().getUserId());
							//drivers.get(i).setLastUpdDate(new Date());
							new DriverFacade().confirm(drivers.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("DriverAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<Driver> drivers = new DriverFacade().find(driver);
			if(drivers != null && drivers.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"驱动Id",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"驱动编号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"驱动名称",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"被驱工作流Id",wcformat));
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
				for(int i=0; i<drivers.size();i++) {
					row++;
					int m = 0;
					if(drivers.get(i).getDriveId() != null) 
						ws.addCell(new jxl.write.Number(m,row,drivers.get(i).getDriveId(),wcformat));
					m++;
					if(drivers.get(i).getDriveNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,drivers.get(i).getDriveNo(),wcformat));
					m++;
					if(drivers.get(i).getDriveNm() != null) 
						ws.addCell(new jxl.write.Label(m,row,drivers.get(i).getDriveNm(),wcformat));
					m++;
					if(drivers.get(i).getFlowId() != null) 
						ws.addCell(new jxl.write.Number(m,row,drivers.get(i).getFlowId(),wcformat));
					m++;
					if(drivers.get(i).getRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,drivers.get(i).getRemark(),wcformat));
					m++;
					if(drivers.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,drivers.get(i).getStatus(),wcformat));
					m++;
					if(drivers.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,drivers.get(i).getCreateBy(),wcformat));
					m++;
					if(drivers.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,drivers.get(i).getCreateDate(),wcformat));
					m++;
					if(drivers.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,drivers.get(i).getLastUpd(),wcformat));
					m++;
					if(drivers.get(i).getLastDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,drivers.get(i).getLastDate(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("DriverListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(drivers != null && drivers.size() > 0) {
				for(int i=0; i<drivers.size();i++) {
					if(drivers.get(i) != null) {
						//drivers.get(i).setLastUpd(getSession().getUserId());
						//drivers.get(i).setLastUpdDate(new Date());
						new DriverFacade().confirm(drivers.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("DriverAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(drivers != null && drivers.size() > 0) {
				for(int i=0; i<drivers.size();i++) {
					if(drivers.get(i) != null) {
						//drivers.get(i).setLastUpd(getSession().getUserId());
						//drivers.get(i).setLastUpdDate(new Date());
						new DriverFacade().confirm(drivers.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("DriverAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<Driver> getDrivers() {
		return drivers;
	}
	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
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
	public List<DriverDtl> getDriverDtls() {
		return driverDtls;
	}
	public void setDriverDtls(List<DriverDtl> driverDtls) {
		this.driverDtls = driverDtls;
	}
	public java.util.List<gnwf.vo.WfCfg> getWfCfgs() {
		return wfCfgs;
	}
	public void setWfCfgs(java.util.List<gnwf.vo.WfCfg> wfCfgs) {
		this.wfCfgs = wfCfgs;
	}

}