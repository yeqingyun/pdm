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

import zrsy.facade.GpSyFacade;
import zrsy.vo.GpSy;
import zrsy.vo.json.GpSyJson;

public class GpSyAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<GpSy> gpSys;
	private GpSy gpSy = new GpSy();

	public String execute() throws Exception {
		try {
			if(gpSy != null && gpSy.getGpId() != null) {
				gpSy = new GpSyFacade().findById(gpSy);
				setJson(JSON.toJSONString(gpSy)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpSyAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(gpSy != null && gpSy.getGpId() != null) {
				//gpSy = new GpSyFacade().findById(gpSy);
				//setJson(JSON.toJSONString(gpSy)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpSyAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(gpSy != null && gpSy.getGpId() != null) {
				gpSy = new GpSyFacade().findById(gpSy);
				setJson(JSON.toJSONString(gpSy)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpSyAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(gpSy != null && gpSy.getGpId() != null) {
				gpSy = new GpSyFacade().findById(gpSy);
				setJson(JSON.toJSONString(gpSy)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpSyAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(gpSy == null) gpSy = new GpSy();
			int total = new GpSyFacade().amount(gpSy);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			gpSys = new GpSyFacade().find(gpSy,getPageVO());
			GpSyJson gpSyJson = new GpSyJson();
			gpSyJson.Rows = gpSys;
			gpSyJson.Total = total;
			setJson(JSON.toJSONString(gpSyJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpSyAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//gpSy.setCreateBy(getSession().getUserId());
			//gpSy.setCreateDate(new Date());
			//gpSy.setLastUpd(getSession().getUserId());
			//gpSy.setLastUpdDate(new Date());

			if(gpSy.getGpId() == null)
				new GpSyFacade().save(gpSy);
			else
				new GpSyFacade().update(gpSy);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("GpSyAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//gpSy.setCreateBy(getSession().getUserId());
			//gpSy.setCreateDate(new Date());
			//gpSy.setLastUpd(getSession().getUserId());
			//gpSy.setLastUpdDate(new Date());
			new GpSyFacade().update(gpSy);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("GpSyAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(gpSys != null && gpSys.size() > 0) {
				for(int i=0; i<gpSys.size();i++) {
					if(gpSys.get(i) != null) {
						//gpSys.get(i).setLastUpd(getSession().getUserId());
						//gpSys.get(i).setLastUpdDate(new Date());
						new GpSyFacade().submit(gpSys.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("GpSyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(gpSys != null && gpSys.size() > 0) {
				for(int i=0; i<gpSys.size();i++) {
					if(gpSys.get(i) != null) {
						//gpSys.get(i).setLastUpd(getSession().getUserId());
						//gpSys.get(i).setLastUpdDate(new Date());
						new GpSyFacade().submit(gpSys.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("GpSyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(gpSys != null && gpSys.size() > 0) {
				for(int i=0; i<gpSys.size();i++) {
					if(gpSys.get(i) != null) {
						//gpSys.get(i).setLastUpd(getSession().getUserId());
						//gpSys.get(i).setLastUpdDate(new Date());
						new GpSyFacade().update(gpSys.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("GpSyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(gpSys != null && gpSys.size() > 0) {
				for(int i=0; i<gpSys.size();i++) {
					if(gpSys.get(i) != null) {
						//gpSys.get(i).setLastUpd(getSession().getUserId());
						//gpSys.get(i).setLastUpdDate(new Date());
						new GpSyFacade().submit(gpSys.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("GpSyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(gpSys != null && gpSys.size() > 0) {
				for(int i=0; i<gpSys.size();i++) {
					if(gpSys.get(i) != null) {
						//gpSys.get(i).setLastUpd(getSession().getUserId());
						//gpSys.get(i).setLastUpdDate(new Date());
						new GpSyFacade().submit(gpSys.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("GpSyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(gpSys != null && gpSys.size() > 0) {
				for(int i=0; i<gpSys.size();i++) {
					if(gpSys.get(i) != null) {
						//gpSys.get(i).setLastUpd(getSession().getUserId());
						//gpSys.get(i).setLastUpdDate(new Date());
						new GpSyFacade().submit(gpSys.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("GpSyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(gpSys != null && gpSys.size() > 0) {
				for(int i=0; i<gpSys.size();i++) {
					if(gpSys.get(i) != null) {
						//gpSys.get(i).setLastUpd(getSession().getUserId());
						//gpSys.get(i).setLastUpdDate(new Date());
						new GpSyFacade().submit(gpSys.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("GpSyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(gpSys != null && gpSys.size() > 0) {
				for(int i=0; i<gpSys.size();i++) {
					if(gpSys.get(i) != null){
						//gpSys.get(i).setLastUpd(getSession().getUserId());
						//gpSys.get(i).setLastUpdDate(new Date());
						new GpSyFacade().submit(gpSys.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("GpSyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(gpSys != null && gpSys.size() > 0) {
				for(int i=0; i<gpSys.size();i++) {
					if(gpSys.get(i) != null){
						//gpSys.get(i).setLastUpd(getSession().getUserId());
						//gpSys.get(i).setLastUpdDate(new Date());
						new GpSyFacade().review(gpSys.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("GpSyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(gpSys != null && gpSys.size() > 0) {
				for(int i=0; i<gpSys.size();i++) {
					if(gpSys.get(i) != null) {
						//gpSys.get(i).setLastUpd(getSession().getUserId());
						//gpSys.get(i).setLastUpdDate(new Date());
						new GpSyFacade().review(gpSys.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("GpSyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(gpSys != null && gpSys.size() > 0) {
				for(int i=0; i<gpSys.size();i++) {
					if(gpSys.get(i) != null) {
						//gpSys.get(i).setLastUpd(getSession().getUserId());
						//gpSys.get(i).setLastUpdDate(new Date());
						new GpSyFacade().confirm(gpSys.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("GpSyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(gpSys != null && gpSys.size() > 0) {
				for(int i=0; i<gpSys.size();i++) {
					if(gpSys.get(i) != null) {
						//gpSys.get(i).setLastUpd(getSession().getUserId());
						//gpSys.get(i).setLastUpdDate(new Date());
						new GpSyFacade().confirm(gpSys.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("GpSyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(gpSys != null && gpSys.size() > 0) {
					for(int i=0; i<gpSys.size();i++) {
						if(gpSys.get(i) != null) {
							//gpSys.get(i).setLastUpd(getSession().getUserId());
							//gpSys.get(i).setLastUpdDate(new Date());
							new GpSyFacade().confirm(gpSys.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("GpSyAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<GpSy> gpSys = new GpSyFacade().find(gpSy);
			if(gpSys != null && gpSys.size() > 0) {
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
				for(int i=0; i<gpSys.size();i++) {
					row++;
					int m = 0;
					if(gpSys.get(i).getGpId() != null) 
						ws.addCell(new jxl.write.Number(m,row,gpSys.get(i).getGpId(),wcformat));
					m++;
					if(gpSys.get(i).getSyId() != null) 
						ws.addCell(new jxl.write.Number(m,row,gpSys.get(i).getSyId(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("GpSyListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(gpSys != null && gpSys.size() > 0) {
				for(int i=0; i<gpSys.size();i++) {
					if(gpSys.get(i) != null) {
						//gpSys.get(i).setLastUpd(getSession().getUserId());
						//gpSys.get(i).setLastUpdDate(new Date());
						new GpSyFacade().confirm(gpSys.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("GpSyAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(gpSys != null && gpSys.size() > 0) {
				for(int i=0; i<gpSys.size();i++) {
					if(gpSys.get(i) != null) {
						//gpSys.get(i).setLastUpd(getSession().getUserId());
						//gpSys.get(i).setLastUpdDate(new Date());
						new GpSyFacade().confirm(gpSys.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("GpSyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<GpSy> getGpSys() {
		return gpSys;
	}
	public void setGpSys(List<GpSy> gpSys) {
		this.gpSys = gpSys;
	}
	public GpSy getGpSy() {
		return gpSy;
	}
	public void setGpSy(GpSy gpSy) {
		this.gpSy = gpSy;
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