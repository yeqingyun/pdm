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

import zrsy.facade.GpScoFacade;
import zrsy.vo.GpSco;
import zrsy.vo.json.GpScoJson;

public class GpScoAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<GpSco> gpScos;
	private GpSco gpSco = new GpSco();
	private java.util.List<zrsy.vo.Gp> gps;
	private java.util.List<zrsy.vo.Sco> scos;

	public String execute() throws Exception {
		try {
			if(gpSco != null && gpSco.getGpId() != null) {
				gpSco = new GpScoFacade().findById(gpSco);
				setJson(JSON.toJSONString(gpSco)); 
			}
			gps = new zrsy.facade.GpFacade().find("select "+zrsy.vo.Gp.SELF_FIELDS+" from Gp",zrsy.vo.Gp.SELF_FIELDS);
			scos = new zrsy.facade.ScoFacade().find("select "+zrsy.vo.Sco.SELF_FIELDS+" from Sco",zrsy.vo.Sco.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpScoAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(gpSco != null && gpSco.getGpId() != null) {
				//gpSco = new GpScoFacade().findById(gpSco);
				//setJson(JSON.toJSONString(gpSco)); 
			//}
			gps = new zrsy.facade.GpFacade().find("select "+zrsy.vo.Gp.SELF_FIELDS+" from Gp",zrsy.vo.Gp.SELF_FIELDS);
			scos = new zrsy.facade.ScoFacade().find("select "+zrsy.vo.Sco.SELF_FIELDS+" from Sco",zrsy.vo.Sco.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpScoAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(gpSco != null && gpSco.getGpId() != null) {
				gpSco = new GpScoFacade().findById(gpSco);
				setJson(JSON.toJSONString(gpSco)); 
			}
			gps = new zrsy.facade.GpFacade().find("select "+zrsy.vo.Gp.SELF_FIELDS+" from Gp",zrsy.vo.Gp.SELF_FIELDS);
			scos = new zrsy.facade.ScoFacade().find("select "+zrsy.vo.Sco.SELF_FIELDS+" from Sco",zrsy.vo.Sco.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpScoAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(gpSco != null && gpSco.getGpId() != null) {
				gpSco = new GpScoFacade().findById(gpSco);
				setJson(JSON.toJSONString(gpSco)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpScoAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(gpSco == null) gpSco = new GpSco();
			int total = new GpScoFacade().amount(gpSco);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			gpScos = new GpScoFacade().find(gpSco,getPageVO());
			GpScoJson gpScoJson = new GpScoJson();
			gpScoJson.Rows = gpScos;
			gpScoJson.Total = total;
			setJson(JSON.toJSONString(gpScoJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpScoAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//gpSco.setCreateBy(getSession().getUserId());
			//gpSco.setCreateDate(new Date());
			//gpSco.setLastUpd(getSession().getUserId());
			//gpSco.setLastUpdDate(new Date());

			if(gpSco.getGpId() == null)
				new GpScoFacade().save(gpSco);
			else
				new GpScoFacade().update(gpSco);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("GpScoAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//gpSco.setCreateBy(getSession().getUserId());
			//gpSco.setCreateDate(new Date());
			//gpSco.setLastUpd(getSession().getUserId());
			//gpSco.setLastUpdDate(new Date());
			new GpScoFacade().update(gpSco);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("GpScoAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(gpScos != null && gpScos.size() > 0) {
				for(int i=0; i<gpScos.size();i++) {
					if(gpScos.get(i) != null) {
						//gpScos.get(i).setLastUpd(getSession().getUserId());
						//gpScos.get(i).setLastUpdDate(new Date());
						new GpScoFacade().submit(gpScos.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("GpScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(gpScos != null && gpScos.size() > 0) {
				for(int i=0; i<gpScos.size();i++) {
					if(gpScos.get(i) != null) {
						//gpScos.get(i).setLastUpd(getSession().getUserId());
						//gpScos.get(i).setLastUpdDate(new Date());
						new GpScoFacade().submit(gpScos.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("GpScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(gpScos != null && gpScos.size() > 0) {
				for(int i=0; i<gpScos.size();i++) {
					if(gpScos.get(i) != null) {
						//gpScos.get(i).setLastUpd(getSession().getUserId());
						//gpScos.get(i).setLastUpdDate(new Date());
						new GpScoFacade().update(gpScos.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("GpScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(gpScos != null && gpScos.size() > 0) {
				for(int i=0; i<gpScos.size();i++) {
					if(gpScos.get(i) != null) {
						//gpScos.get(i).setLastUpd(getSession().getUserId());
						//gpScos.get(i).setLastUpdDate(new Date());
						new GpScoFacade().submit(gpScos.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("GpScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(gpScos != null && gpScos.size() > 0) {
				for(int i=0; i<gpScos.size();i++) {
					if(gpScos.get(i) != null) {
						//gpScos.get(i).setLastUpd(getSession().getUserId());
						//gpScos.get(i).setLastUpdDate(new Date());
						new GpScoFacade().submit(gpScos.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("GpScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(gpScos != null && gpScos.size() > 0) {
				for(int i=0; i<gpScos.size();i++) {
					if(gpScos.get(i) != null) {
						//gpScos.get(i).setLastUpd(getSession().getUserId());
						//gpScos.get(i).setLastUpdDate(new Date());
						new GpScoFacade().submit(gpScos.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("GpScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(gpScos != null && gpScos.size() > 0) {
				for(int i=0; i<gpScos.size();i++) {
					if(gpScos.get(i) != null) {
						//gpScos.get(i).setLastUpd(getSession().getUserId());
						//gpScos.get(i).setLastUpdDate(new Date());
						new GpScoFacade().submit(gpScos.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("GpScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(gpScos != null && gpScos.size() > 0) {
				for(int i=0; i<gpScos.size();i++) {
					if(gpScos.get(i) != null){
						//gpScos.get(i).setLastUpd(getSession().getUserId());
						//gpScos.get(i).setLastUpdDate(new Date());
						new GpScoFacade().submit(gpScos.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("GpScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(gpScos != null && gpScos.size() > 0) {
				for(int i=0; i<gpScos.size();i++) {
					if(gpScos.get(i) != null){
						//gpScos.get(i).setLastUpd(getSession().getUserId());
						//gpScos.get(i).setLastUpdDate(new Date());
						new GpScoFacade().review(gpScos.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("GpScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(gpScos != null && gpScos.size() > 0) {
				for(int i=0; i<gpScos.size();i++) {
					if(gpScos.get(i) != null) {
						//gpScos.get(i).setLastUpd(getSession().getUserId());
						//gpScos.get(i).setLastUpdDate(new Date());
						new GpScoFacade().review(gpScos.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("GpScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(gpScos != null && gpScos.size() > 0) {
				for(int i=0; i<gpScos.size();i++) {
					if(gpScos.get(i) != null) {
						//gpScos.get(i).setLastUpd(getSession().getUserId());
						//gpScos.get(i).setLastUpdDate(new Date());
						new GpScoFacade().confirm(gpScos.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("GpScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(gpScos != null && gpScos.size() > 0) {
				for(int i=0; i<gpScos.size();i++) {
					if(gpScos.get(i) != null) {
						//gpScos.get(i).setLastUpd(getSession().getUserId());
						//gpScos.get(i).setLastUpdDate(new Date());
						new GpScoFacade().confirm(gpScos.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("GpScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(gpScos != null && gpScos.size() > 0) {
					for(int i=0; i<gpScos.size();i++) {
						if(gpScos.get(i) != null) {
							//gpScos.get(i).setLastUpd(getSession().getUserId());
							//gpScos.get(i).setLastUpdDate(new Date());
							new GpScoFacade().confirm(gpScos.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("GpScoAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<GpSco> gpScos = new GpScoFacade().find(gpSco);
			if(gpScos != null && gpScos.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"用户组",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"范围",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<gpScos.size();i++) {
					row++;
					int m = 0;
					if(gpScos.get(i).getGpId() != null) 
						ws.addCell(new jxl.write.Number(m,row,gpScos.get(i).getGpId(),wcformat));
					m++;
					if(gpScos.get(i).getScoId() != null) 
						ws.addCell(new jxl.write.Number(m,row,gpScos.get(i).getScoId(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("GpScoListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(gpScos != null && gpScos.size() > 0) {
				for(int i=0; i<gpScos.size();i++) {
					if(gpScos.get(i) != null) {
						//gpScos.get(i).setLastUpd(getSession().getUserId());
						//gpScos.get(i).setLastUpdDate(new Date());
						new GpScoFacade().confirm(gpScos.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("GpScoAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(gpScos != null && gpScos.size() > 0) {
				for(int i=0; i<gpScos.size();i++) {
					if(gpScos.get(i) != null) {
						//gpScos.get(i).setLastUpd(getSession().getUserId());
						//gpScos.get(i).setLastUpdDate(new Date());
						new GpScoFacade().confirm(gpScos.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("GpScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<GpSco> getGpScos() {
		return gpScos;
	}
	public void setGpScos(List<GpSco> gpScos) {
		this.gpScos = gpScos;
	}
	public GpSco getGpSco() {
		return gpSco;
	}
	public void setGpSco(GpSco gpSco) {
		this.gpSco = gpSco;
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
	public java.util.List<zrsy.vo.Sco> getScos() {
		return scos;
	}
	public void setScos(java.util.List<zrsy.vo.Sco> scos){
		this.scos = scos;
	}
	public void addtoScos(zrsy.vo.Sco sco){
		if(getScos() == null) setScos(new java.util.ArrayList<zrsy.vo.Sco>());
			getScos().add(sco);
	}

}