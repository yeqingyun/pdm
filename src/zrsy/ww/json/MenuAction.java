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

import zrsy.facade.MenuFacade;
import zrsy.vo.Menu;
import zrsy.vo.json.MenuJson;

public class MenuAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<Menu> menus;
	private Menu menu = new Menu();
	private java.util.List<zrsy.vo.GpMenu> gpMenus;
	private java.util.List<zrsy.vo.Node> nodes;
	private java.util.List<zrsy.vo.SyDef> syDefs;

	public String execute() throws Exception {
		try {
			if(menu != null && menu.getId() != null) {
				menu = new MenuFacade().findById(menu);
				setJson(JSON.toJSONString(menu)); 
			}
			syDefs = new zrsy.facade.SyDefFacade().find("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef",zrsy.vo.SyDef.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MenuAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(menu != null && menu.getId() != null) {
				//menu = new MenuFacade().findById(menu);
				//setJson(JSON.toJSONString(menu)); 
			//}
			syDefs = new zrsy.facade.SyDefFacade().find("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef where SyId = " + getUsrSession().getSyId(),zrsy.vo.SyDef.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MenuAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(menu != null && menu.getId() != null) {
				menu = new MenuFacade().findById(menu);
				setJson(JSON.toJSONString(menu)); 
			}
			syDefs = new zrsy.facade.SyDefFacade().find("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef where SyId = " + getUsrSession().getSyId(),zrsy.vo.SyDef.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MenuAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(menu != null && menu.getId() != null) {
				menu = new MenuFacade().findById(menu);
				setJson(JSON.toJSONString(menu)); 
			}
			syDefs = new zrsy.facade.SyDefFacade().find("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef",zrsy.vo.SyDef.SELF_FIELDS);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MenuAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(menu == null) menu = new Menu();
			menu.setSyId(getUsrSession().getSyId());
			int total = new MenuFacade().amount(menu);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			menu.setSyId(getUsrSession().getSyId());
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			menus = new MenuFacade().find(menu,getPageVO());
			MenuJson menuJson = new MenuJson();
			menuJson.Rows = menus;
			menuJson.Total = total;
			setJson(JSON.toJSONString(menuJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MenuAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//menu.setCreateBy(getSession().getUserId());
			//menu.setCreateDate(new Date());
			//menu.setLastUpd(getSession().getUserId());
			//menu.setLastUpdDate(new Date());
			menu.setGpMenus(gpMenus);
			menu.setNodes(nodes);

			if(menu.getId() == null)
				new MenuFacade().save(menu);
			else
				new MenuFacade().update(menu);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("MenuAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//menu.setCreateBy(getSession().getUserId());
			//menu.setCreateDate(new Date());
			//menu.setLastUpd(getSession().getUserId());
			//menu.setLastUpdDate(new Date());
			new MenuFacade().update(menu);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("MenuAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(menus != null && menus.size() > 0) {
				for(int i=0; i<menus.size();i++) {
					if(menus.get(i) != null) {
						//menus.get(i).setLastUpd(getSession().getUserId());
						//menus.get(i).setLastUpdDate(new Date());
						new MenuFacade().submit(menus.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("MenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(menus != null && menus.size() > 0) {
				for(int i=0; i<menus.size();i++) {
					if(menus.get(i) != null) {
						//menus.get(i).setLastUpd(getSession().getUserId());
						//menus.get(i).setLastUpdDate(new Date());
						new MenuFacade().submit(menus.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("MenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(menus != null && menus.size() > 0) {
				for(int i=0; i<menus.size();i++) {
					if(menus.get(i) != null) {
						//menus.get(i).setLastUpd(getSession().getUserId());
						//menus.get(i).setLastUpdDate(new Date());
						new MenuFacade().update(menus.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("MenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(menus != null && menus.size() > 0) {
				for(int i=0; i<menus.size();i++) {
					if(menus.get(i) != null) {
						//menus.get(i).setLastUpd(getSession().getUserId());
						//menus.get(i).setLastUpdDate(new Date());
						new MenuFacade().submit(menus.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("MenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(menus != null && menus.size() > 0) {
				for(int i=0; i<menus.size();i++) {
					if(menus.get(i) != null) {
						//menus.get(i).setLastUpd(getSession().getUserId());
						//menus.get(i).setLastUpdDate(new Date());
						new MenuFacade().submit(menus.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("MenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(menus != null && menus.size() > 0) {
				for(int i=0; i<menus.size();i++) {
					if(menus.get(i) != null) {
						//menus.get(i).setLastUpd(getSession().getUserId());
						//menus.get(i).setLastUpdDate(new Date());
						new MenuFacade().submit(menus.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("MenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(menus != null && menus.size() > 0) {
				for(int i=0; i<menus.size();i++) {
					if(menus.get(i) != null) {
						//menus.get(i).setLastUpd(getSession().getUserId());
						//menus.get(i).setLastUpdDate(new Date());
						new MenuFacade().submit(menus.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("MenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(menus != null && menus.size() > 0) {
				for(int i=0; i<menus.size();i++) {
					if(menus.get(i) != null){
						//menus.get(i).setLastUpd(getSession().getUserId());
						//menus.get(i).setLastUpdDate(new Date());
						new MenuFacade().submit(menus.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("MenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(menus != null && menus.size() > 0) {
				for(int i=0; i<menus.size();i++) {
					if(menus.get(i) != null){
						//menus.get(i).setLastUpd(getSession().getUserId());
						//menus.get(i).setLastUpdDate(new Date());
						new MenuFacade().review(menus.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("MenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(menus != null && menus.size() > 0) {
				for(int i=0; i<menus.size();i++) {
					if(menus.get(i) != null) {
						//menus.get(i).setLastUpd(getSession().getUserId());
						//menus.get(i).setLastUpdDate(new Date());
						new MenuFacade().review(menus.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("MenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(menus != null && menus.size() > 0) {
				for(int i=0; i<menus.size();i++) {
					if(menus.get(i) != null) {
						//menus.get(i).setLastUpd(getSession().getUserId());
						//menus.get(i).setLastUpdDate(new Date());
						new MenuFacade().confirm(menus.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("MenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(menus != null && menus.size() > 0) {
				for(int i=0; i<menus.size();i++) {
					if(menus.get(i) != null) {
						//menus.get(i).setLastUpd(getSession().getUserId());
						//menus.get(i).setLastUpdDate(new Date());
						new MenuFacade().confirm(menus.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("MenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(menus != null && menus.size() > 0) {
					for(int i=0; i<menus.size();i++) {
						if(menus.get(i) != null) {
							//menus.get(i).setLastUpd(getSession().getUserId());
							//menus.get(i).setLastUpdDate(new Date());
							new MenuFacade().confirm(menus.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("MenuAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<Menu> menus = new MenuFacade().find(menu);
			if(menus != null && menus.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"ID号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"系统",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"上级ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"宽度",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"级别",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"排序",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"菜单名",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"单击事件",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"图标",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<menus.size();i++) {
					row++;
					int m = 0;
					if(menus.get(i).getId() != null) 
						ws.addCell(new jxl.write.Number(m,row,menus.get(i).getId(),wcformat));
					m++;
					if(menus.get(i).getSyId() != null) 
						ws.addCell(new jxl.write.Number(m,row,menus.get(i).getSyId(),wcformat));
					m++;
					if(menus.get(i).getParent() != null) 
						ws.addCell(new jxl.write.Number(m,row,menus.get(i).getParent(),wcformat));
					m++;
					if(menus.get(i).getWidth() != null) 
						ws.addCell(new jxl.write.Number(m,row,menus.get(i).getWidth(),wcformat));
					m++;
					if(menus.get(i).getLeve() != null) 
						ws.addCell(new jxl.write.Number(m,row,menus.get(i).getLeve(),wcformat));
					m++;
					if(menus.get(i).getSort() != null) 
						ws.addCell(new jxl.write.Number(m,row,menus.get(i).getSort(),wcformat));
					m++;
					if(menus.get(i).getText() != null) 
						ws.addCell(new jxl.write.Label(m,row,menus.get(i).getText(),wcformat));
					m++;
					if(menus.get(i).getClick() != null) 
						ws.addCell(new jxl.write.Label(m,row,menus.get(i).getClick(),wcformat));
					m++;
					if(menus.get(i).getIcon() != null) 
						ws.addCell(new jxl.write.Label(m,row,menus.get(i).getIcon(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("MenuListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(menus != null && menus.size() > 0) {
				for(int i=0; i<menus.size();i++) {
					if(menus.get(i) != null) {
						//menus.get(i).setLastUpd(getSession().getUserId());
						//menus.get(i).setLastUpdDate(new Date());
						new MenuFacade().confirm(menus.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("MenuAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(menus != null && menus.size() > 0) {
				for(int i=0; i<menus.size();i++) {
					if(menus.get(i) != null) {
						//menus.get(i).setLastUpd(getSession().getUserId());
						//menus.get(i).setLastUpdDate(new Date());
						new MenuFacade().confirm(menus.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("MenuAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
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
	public java.util.List<zrsy.vo.GpMenu> getGpMenus() {
		return gpMenus;
	}
	public void setGpMenus(java.util.List<zrsy.vo.GpMenu> gpMenus){
		this.gpMenus = gpMenus;
	}
	public void addtoGpMenus(zrsy.vo.GpMenu gpMenu){
		if(getGpMenus() == null) setGpMenus(new java.util.ArrayList<zrsy.vo.GpMenu>());
			getGpMenus().add(gpMenu);
	}
	public java.util.List<zrsy.vo.Node> getNodes() {
		return nodes;
	}
	public void setNodes(java.util.List<zrsy.vo.Node> nodes){
		this.nodes = nodes;
	}
	public void addtoNodes(zrsy.vo.Node node){
		if(getNodes() == null) setNodes(new java.util.ArrayList<zrsy.vo.Node>());
			getNodes().add(node);
	}
	public java.util.List<zrsy.vo.SyDef> getSyDefs() {
		return syDefs;
	}
	public void setSyDefs(java.util.List<zrsy.vo.SyDef> syDefs){
		this.syDefs = syDefs;
	}
	public void addtoSyDefs(zrsy.vo.SyDef syDef){
		if(getSyDefs() == null) setSyDefs(new java.util.ArrayList<zrsy.vo.SyDef>());
			getSyDefs().add(syDef);
	}

}