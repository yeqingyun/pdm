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

import zrsy.facade.GpUsrFacade;
import zrsy.vo.GpUsr;
import zrsy.vo.json.GpUsrJson;

public class GpUsrAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<GpUsr> gpUsrs;
	private GpUsr gpUsr = new GpUsr();
	private java.util.List<zrsy.vo.Gp> gps;
	private java.util.List<zrsy.vo.Usr> usrs;

	public String execute() throws Exception {
		try {
			if(gpUsr != null && gpUsr.getGpId() != null) {
				gpUsr = new GpUsrFacade().findById(gpUsr);
				setJson(JSON.toJSONString(gpUsr)); 
			}
			gps = new zrsy.facade.GpFacade().find("select "+zrsy.vo.Gp.SELF_FIELDS+" from Gp",zrsy.vo.Gp.SELF_FIELDS);
			usrs = new zrsy.facade.UsrFacade().find("select "+zrsy.vo.Usr.SELF_FIELDS+" from Usr",zrsy.vo.Usr.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpUsrAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(gpUsr != null && gpUsr.getGpId() != null) {
				//gpUsr = new GpUsrFacade().findById(gpUsr);
				//setJson(JSON.toJSONString(gpUsr)); 
			//}
			gps = new zrsy.facade.GpFacade().find("select "+zrsy.vo.Gp.SELF_FIELDS+" from Gp",zrsy.vo.Gp.SELF_FIELDS);
			usrs = new zrsy.facade.UsrFacade().find("select "+zrsy.vo.Usr.SELF_FIELDS+" from Usr",zrsy.vo.Usr.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpUsrAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(gpUsr != null && gpUsr.getGpId() != null) {
				gpUsr = new GpUsrFacade().findById(gpUsr);
				setJson(JSON.toJSONString(gpUsr)); 
			}
			gps = new zrsy.facade.GpFacade().find("select "+zrsy.vo.Gp.SELF_FIELDS+" from Gp",zrsy.vo.Gp.SELF_FIELDS);
			usrs = new zrsy.facade.UsrFacade().find("select "+zrsy.vo.Usr.SELF_FIELDS+" from Usr",zrsy.vo.Usr.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpUsrAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(gpUsr != null && gpUsr.getGpId() != null) {
				gpUsr = new GpUsrFacade().findById(gpUsr);
				setJson(JSON.toJSONString(gpUsr)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpUsrAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(gpUsr == null) gpUsr = new GpUsr();
			int total = new GpUsrFacade().amount(gpUsr);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			gpUsrs = new GpUsrFacade().find(gpUsr,getPageVO());
			GpUsrJson gpUsrJson = new GpUsrJson();
			gpUsrJson.Rows = gpUsrs;
			gpUsrJson.Total = total;
			setJson(JSON.toJSONString(gpUsrJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpUsrAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//gpUsr.setCreateBy(getSession().getUserId());
			//gpUsr.setCreateDate(new Date());
			//gpUsr.setLastUpd(getSession().getUserId());
			//gpUsr.setLastUpdDate(new Date());

			if(gpUsr.getGpId() == null)
				new GpUsrFacade().save(gpUsr);
			else
				new GpUsrFacade().update(gpUsr);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("GpUsrAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//gpUsr.setCreateBy(getSession().getUserId());
			//gpUsr.setCreateDate(new Date());
			//gpUsr.setLastUpd(getSession().getUserId());
			//gpUsr.setLastUpdDate(new Date());
			new GpUsrFacade().update(gpUsr);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("GpUsrAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(gpUsrs != null && gpUsrs.size() > 0) {
				for(int i=0; i<gpUsrs.size();i++) {
					if(gpUsrs.get(i) != null) {
						//gpUsrs.get(i).setLastUpd(getSession().getUserId());
						//gpUsrs.get(i).setLastUpdDate(new Date());
						new GpUsrFacade().submit(gpUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("GpUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(gpUsrs != null && gpUsrs.size() > 0) {
				for(int i=0; i<gpUsrs.size();i++) {
					if(gpUsrs.get(i) != null) {
						//gpUsrs.get(i).setLastUpd(getSession().getUserId());
						//gpUsrs.get(i).setLastUpdDate(new Date());
						new GpUsrFacade().submit(gpUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("GpUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(gpUsrs != null && gpUsrs.size() > 0) {
				for(int i=0; i<gpUsrs.size();i++) {
					if(gpUsrs.get(i) != null) {
						//gpUsrs.get(i).setLastUpd(getSession().getUserId());
						//gpUsrs.get(i).setLastUpdDate(new Date());
						new GpUsrFacade().update(gpUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("GpUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(gpUsrs != null && gpUsrs.size() > 0) {
				for(int i=0; i<gpUsrs.size();i++) {
					if(gpUsrs.get(i) != null) {
						//gpUsrs.get(i).setLastUpd(getSession().getUserId());
						//gpUsrs.get(i).setLastUpdDate(new Date());
						new GpUsrFacade().submit(gpUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("GpUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(gpUsrs != null && gpUsrs.size() > 0) {
				for(int i=0; i<gpUsrs.size();i++) {
					if(gpUsrs.get(i) != null) {
						//gpUsrs.get(i).setLastUpd(getSession().getUserId());
						//gpUsrs.get(i).setLastUpdDate(new Date());
						new GpUsrFacade().submit(gpUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("GpUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(gpUsrs != null && gpUsrs.size() > 0) {
				for(int i=0; i<gpUsrs.size();i++) {
					if(gpUsrs.get(i) != null) {
						//gpUsrs.get(i).setLastUpd(getSession().getUserId());
						//gpUsrs.get(i).setLastUpdDate(new Date());
						new GpUsrFacade().submit(gpUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("GpUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(gpUsrs != null && gpUsrs.size() > 0) {
				for(int i=0; i<gpUsrs.size();i++) {
					if(gpUsrs.get(i) != null) {
						//gpUsrs.get(i).setLastUpd(getSession().getUserId());
						//gpUsrs.get(i).setLastUpdDate(new Date());
						new GpUsrFacade().submit(gpUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("GpUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(gpUsrs != null && gpUsrs.size() > 0) {
				for(int i=0; i<gpUsrs.size();i++) {
					if(gpUsrs.get(i) != null){
						//gpUsrs.get(i).setLastUpd(getSession().getUserId());
						//gpUsrs.get(i).setLastUpdDate(new Date());
						new GpUsrFacade().submit(gpUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("GpUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(gpUsrs != null && gpUsrs.size() > 0) {
				for(int i=0; i<gpUsrs.size();i++) {
					if(gpUsrs.get(i) != null){
						//gpUsrs.get(i).setLastUpd(getSession().getUserId());
						//gpUsrs.get(i).setLastUpdDate(new Date());
						new GpUsrFacade().review(gpUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("GpUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(gpUsrs != null && gpUsrs.size() > 0) {
				for(int i=0; i<gpUsrs.size();i++) {
					if(gpUsrs.get(i) != null) {
						//gpUsrs.get(i).setLastUpd(getSession().getUserId());
						//gpUsrs.get(i).setLastUpdDate(new Date());
						new GpUsrFacade().review(gpUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("GpUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(gpUsrs != null && gpUsrs.size() > 0) {
				for(int i=0; i<gpUsrs.size();i++) {
					if(gpUsrs.get(i) != null) {
						//gpUsrs.get(i).setLastUpd(getSession().getUserId());
						//gpUsrs.get(i).setLastUpdDate(new Date());
						new GpUsrFacade().confirm(gpUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("GpUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(gpUsrs != null && gpUsrs.size() > 0) {
				for(int i=0; i<gpUsrs.size();i++) {
					if(gpUsrs.get(i) != null) {
						//gpUsrs.get(i).setLastUpd(getSession().getUserId());
						//gpUsrs.get(i).setLastUpdDate(new Date());
						new GpUsrFacade().confirm(gpUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("GpUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(gpUsrs != null && gpUsrs.size() > 0) {
					for(int i=0; i<gpUsrs.size();i++) {
						if(gpUsrs.get(i) != null) {
							//gpUsrs.get(i).setLastUpd(getSession().getUserId());
							//gpUsrs.get(i).setLastUpdDate(new Date());
							new GpUsrFacade().confirm(gpUsrs.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("GpUsrAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<GpUsr> gpUsrs = new GpUsrFacade().find(gpUsr);
			if(gpUsrs != null && gpUsrs.size() > 0) {
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
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<gpUsrs.size();i++) {
					row++;
					int m = 0;
					if(gpUsrs.get(i).getGpId() != null) 
						ws.addCell(new jxl.write.Number(m,row,gpUsrs.get(i).getGpId(),wcformat));
					m++;
					if(gpUsrs.get(i).getUsrId() != null) 
						ws.addCell(new jxl.write.Number(m,row,gpUsrs.get(i).getUsrId(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("GpUsrListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(gpUsrs != null && gpUsrs.size() > 0) {
				for(int i=0; i<gpUsrs.size();i++) {
					if(gpUsrs.get(i) != null) {
						//gpUsrs.get(i).setLastUpd(getSession().getUserId());
						//gpUsrs.get(i).setLastUpdDate(new Date());
						new GpUsrFacade().confirm(gpUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("GpUsrAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(gpUsrs != null && gpUsrs.size() > 0) {
				for(int i=0; i<gpUsrs.size();i++) {
					if(gpUsrs.get(i) != null) {
						//gpUsrs.get(i).setLastUpd(getSession().getUserId());
						//gpUsrs.get(i).setLastUpdDate(new Date());
						new GpUsrFacade().confirm(gpUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("GpUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<GpUsr> getGpUsrs() {
		return gpUsrs;
	}
	public void setGpUsrs(List<GpUsr> gpUsrs) {
		this.gpUsrs = gpUsrs;
	}
	public GpUsr getGpUsr() {
		return gpUsr;
	}
	public void setGpUsr(GpUsr gpUsr) {
		this.gpUsr = gpUsr;
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
	public java.util.List<zrsy.vo.Gp> getGps() {
		return gps;
	}
	public void setGps(java.util.List<zrsy.vo.Gp> gps){
		this.gps = gps;
	}
	public void addtoGps(zrsy.vo.Gp gp){
		if(getGps() == null) setGps(new java.util.ArrayList<zrsy.vo.Gp>());
			getGps().add(gp);
	}
	public java.util.List<zrsy.vo.Usr> getUsrs() {
		return usrs;
	}
	public void setUsrs(java.util.List<zrsy.vo.Usr> usrs){
		this.usrs = usrs;
	}
	public void addtoUsrs(zrsy.vo.Usr usr){
		if(getUsrs() == null) setUsrs(new java.util.ArrayList<zrsy.vo.Usr>());
			getUsrs().add(usr);
	}

}