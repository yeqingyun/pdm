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

import zrsy.facade.GpMenuFacade;
import zrsy.vo.GpMenu;
import zrsy.vo.json.GpMenuJson;

public class GpMenuAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<GpMenu> gpMenus;
	private GpMenu gpMenu = new GpMenu();
	private java.util.List<zrsy.vo.Gp> gps;
	private java.util.List<zrsy.vo.Menu> menus;

	public String execute() throws Exception {
		try {
			if(gpMenu != null && gpMenu.getGpId() != null) {
				gpMenu = new GpMenuFacade().findById(gpMenu);
				setJson(JSON.toJSONString(gpMenu)); 
			}
			gps = new zrsy.facade.GpFacade().find("select "+zrsy.vo.Gp.SELF_FIELDS+" from Gp",zrsy.vo.Gp.SELF_FIELDS);
			menus = new zrsy.facade.MenuFacade().find("select "+zrsy.vo.Menu.SELF_FIELDS+" from Menu",zrsy.vo.Menu.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpMenuAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(gpMenu != null && gpMenu.getGpId() != null) {
				//gpMenu = new GpMenuFacade().findById(gpMenu);
				//setJson(JSON.toJSONString(gpMenu)); 
			//}
			gps = new zrsy.facade.GpFacade().find("select "+zrsy.vo.Gp.SELF_FIELDS+" from Gp",zrsy.vo.Gp.SELF_FIELDS);
			menus = new zrsy.facade.MenuFacade().find("select "+zrsy.vo.Menu.SELF_FIELDS+" from Menu",zrsy.vo.Menu.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpMenuAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(gpMenu != null && gpMenu.getGpId() != null) {
				gpMenu = new GpMenuFacade().findById(gpMenu);
				setJson(JSON.toJSONString(gpMenu)); 
			}
			gps = new zrsy.facade.GpFacade().find("select "+zrsy.vo.Gp.SELF_FIELDS+" from Gp",zrsy.vo.Gp.SELF_FIELDS);
			menus = new zrsy.facade.MenuFacade().find("select "+zrsy.vo.Menu.SELF_FIELDS+" from Menu",zrsy.vo.Menu.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpMenuAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(gpMenu != null && gpMenu.getGpId() != null) {
				gpMenu = new GpMenuFacade().findById(gpMenu);
				setJson(JSON.toJSONString(gpMenu)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpMenuAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(gpMenu == null) gpMenu = new GpMenu();
			int total = new GpMenuFacade().amount(gpMenu);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			gpMenus = new GpMenuFacade().find(gpMenu,getPageVO());
			GpMenuJson gpMenuJson = new GpMenuJson();
			gpMenuJson.Rows = gpMenus;
			gpMenuJson.Total = total;
			setJson(JSON.toJSONString(gpMenuJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpMenuAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//gpMenu.setCreateBy(getSession().getUserId());
			//gpMenu.setCreateDate(new Date());
			//gpMenu.setLastUpd(getSession().getUserId());
			//gpMenu.setLastUpdDate(new Date());

			if(gpMenu.getGpId() == null)
				new GpMenuFacade().save(gpMenu);
			else
				new GpMenuFacade().update(gpMenu);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("GpMenuAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//gpMenu.setCreateBy(getSession().getUserId());
			//gpMenu.setCreateDate(new Date());
			//gpMenu.setLastUpd(getSession().getUserId());
			//gpMenu.setLastUpdDate(new Date());
			new GpMenuFacade().update(gpMenu);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("GpMenuAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(gpMenus != null && gpMenus.size() > 0) {
				for(int i=0; i<gpMenus.size();i++) {
					if(gpMenus.get(i) != null) {
						//gpMenus.get(i).setLastUpd(getSession().getUserId());
						//gpMenus.get(i).setLastUpdDate(new Date());
						new GpMenuFacade().submit(gpMenus.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("GpMenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(gpMenus != null && gpMenus.size() > 0) {
				for(int i=0; i<gpMenus.size();i++) {
					if(gpMenus.get(i) != null) {
						//gpMenus.get(i).setLastUpd(getSession().getUserId());
						//gpMenus.get(i).setLastUpdDate(new Date());
						new GpMenuFacade().submit(gpMenus.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("GpMenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(gpMenus != null && gpMenus.size() > 0) {
				for(int i=0; i<gpMenus.size();i++) {
					if(gpMenus.get(i) != null) {
						//gpMenus.get(i).setLastUpd(getSession().getUserId());
						//gpMenus.get(i).setLastUpdDate(new Date());
						new GpMenuFacade().update(gpMenus.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("GpMenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(gpMenus != null && gpMenus.size() > 0) {
				for(int i=0; i<gpMenus.size();i++) {
					if(gpMenus.get(i) != null) {
						//gpMenus.get(i).setLastUpd(getSession().getUserId());
						//gpMenus.get(i).setLastUpdDate(new Date());
						new GpMenuFacade().submit(gpMenus.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("GpMenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(gpMenus != null && gpMenus.size() > 0) {
				for(int i=0; i<gpMenus.size();i++) {
					if(gpMenus.get(i) != null) {
						//gpMenus.get(i).setLastUpd(getSession().getUserId());
						//gpMenus.get(i).setLastUpdDate(new Date());
						new GpMenuFacade().submit(gpMenus.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("GpMenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(gpMenus != null && gpMenus.size() > 0) {
				for(int i=0; i<gpMenus.size();i++) {
					if(gpMenus.get(i) != null) {
						//gpMenus.get(i).setLastUpd(getSession().getUserId());
						//gpMenus.get(i).setLastUpdDate(new Date());
						new GpMenuFacade().submit(gpMenus.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("GpMenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(gpMenus != null && gpMenus.size() > 0) {
				for(int i=0; i<gpMenus.size();i++) {
					if(gpMenus.get(i) != null) {
						//gpMenus.get(i).setLastUpd(getSession().getUserId());
						//gpMenus.get(i).setLastUpdDate(new Date());
						new GpMenuFacade().submit(gpMenus.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("GpMenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(gpMenus != null && gpMenus.size() > 0) {
				for(int i=0; i<gpMenus.size();i++) {
					if(gpMenus.get(i) != null){
						//gpMenus.get(i).setLastUpd(getSession().getUserId());
						//gpMenus.get(i).setLastUpdDate(new Date());
						new GpMenuFacade().submit(gpMenus.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("GpMenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(gpMenus != null && gpMenus.size() > 0) {
				for(int i=0; i<gpMenus.size();i++) {
					if(gpMenus.get(i) != null){
						//gpMenus.get(i).setLastUpd(getSession().getUserId());
						//gpMenus.get(i).setLastUpdDate(new Date());
						new GpMenuFacade().review(gpMenus.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("GpMenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(gpMenus != null && gpMenus.size() > 0) {
				for(int i=0; i<gpMenus.size();i++) {
					if(gpMenus.get(i) != null) {
						//gpMenus.get(i).setLastUpd(getSession().getUserId());
						//gpMenus.get(i).setLastUpdDate(new Date());
						new GpMenuFacade().review(gpMenus.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("GpMenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(gpMenus != null && gpMenus.size() > 0) {
				for(int i=0; i<gpMenus.size();i++) {
					if(gpMenus.get(i) != null) {
						//gpMenus.get(i).setLastUpd(getSession().getUserId());
						//gpMenus.get(i).setLastUpdDate(new Date());
						new GpMenuFacade().confirm(gpMenus.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("GpMenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(gpMenus != null && gpMenus.size() > 0) {
				for(int i=0; i<gpMenus.size();i++) {
					if(gpMenus.get(i) != null) {
						//gpMenus.get(i).setLastUpd(getSession().getUserId());
						//gpMenus.get(i).setLastUpdDate(new Date());
						new GpMenuFacade().confirm(gpMenus.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("GpMenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(gpMenus != null && gpMenus.size() > 0) {
					for(int i=0; i<gpMenus.size();i++) {
						if(gpMenus.get(i) != null) {
							//gpMenus.get(i).setLastUpd(getSession().getUserId());
							//gpMenus.get(i).setLastUpdDate(new Date());
							new GpMenuFacade().confirm(gpMenus.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("GpMenuAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<GpMenu> gpMenus = new GpMenuFacade().find(gpMenu);
			if(gpMenus != null && gpMenus.size() > 0) {
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
				for(int i=0; i<gpMenus.size();i++) {
					row++;
					int m = 0;
					if(gpMenus.get(i).getGpId() != null) 
						ws.addCell(new jxl.write.Number(m,row,gpMenus.get(i).getGpId(),wcformat));
					m++;
					if(gpMenus.get(i).getMenuId() != null) 
						ws.addCell(new jxl.write.Number(m,row,gpMenus.get(i).getMenuId(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("GpMenuListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(gpMenus != null && gpMenus.size() > 0) {
				for(int i=0; i<gpMenus.size();i++) {
					if(gpMenus.get(i) != null) {
						//gpMenus.get(i).setLastUpd(getSession().getUserId());
						//gpMenus.get(i).setLastUpdDate(new Date());
						new GpMenuFacade().confirm(gpMenus.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("GpMenuAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(gpMenus != null && gpMenus.size() > 0) {
				for(int i=0; i<gpMenus.size();i++) {
					if(gpMenus.get(i) != null) {
						//gpMenus.get(i).setLastUpd(getSession().getUserId());
						//gpMenus.get(i).setLastUpdDate(new Date());
						new GpMenuFacade().confirm(gpMenus.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("GpMenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<GpMenu> getGpMenus() {
		return gpMenus;
	}
	public void setGpMenus(List<GpMenu> gpMenus) {
		this.gpMenus = gpMenus;
	}
	public GpMenu getGpMenu() {
		return gpMenu;
	}
	public void setGpMenu(GpMenu gpMenu) {
		this.gpMenu = gpMenu;
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
	public java.util.List<zrsy.vo.Menu> getMenus() {
		return menus;
	}
	public void setMenus(java.util.List<zrsy.vo.Menu> menus){
		this.menus = menus;
	}
	public void addtoMenus(zrsy.vo.Menu menu){
		if(getMenus() == null) setMenus(new java.util.ArrayList<zrsy.vo.Menu>());
			getMenus().add(menu);
	}

}