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

import zrsy.facade.BtnFacade;
import zrsy.facade.GpBtnFacade;
import zrsy.facade.GpFacade;
import zrsy.facade.GpMenuFacade;
import zrsy.facade.GpNodeFacade;
import zrsy.facade.GpSyFacade;
import zrsy.facade.MenuFacade;
import zrsy.facade.NodeFacade;
import zrsy.facade.PgBtnFacade;
import zrsy.vo.Btn;
import zrsy.vo.Gp;
import zrsy.vo.GpBtn;
import zrsy.vo.GpMenu;
import zrsy.vo.GpNode;
import zrsy.vo.GpSy;
import zrsy.vo.Menu;
import zrsy.vo.Node;
import zrsy.vo.PgBtn;
import zrsy.vo.json.GpJson;

public class GpAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<Gp> gps;
	private Gp gp = new Gp();
	
	private zrsy.vo.SyDef syDef;
	private java.util.List<zrsy.vo.GpBtn> gpBtns;
	private java.util.List<zrsy.vo.GpMenu> gpMenus;
	private java.util.List<zrsy.vo.GpNode> gpNodes;
	private java.util.List<zrsy.vo.GpUsr> gpUsrs;
	private java.util.List<zrsy.vo.SyDef> syDefs;
	private List<GpSy> gpSys;

	private java.util.List<zrsy.vo.Menu> menus;
	private java.util.List<zrsy.vo.Menu> subMenus;
	private java.util.List<zrsy.vo.Node> nodes;
	private java.util.List<zrsy.vo.Btn> btns;

	public String execute() throws Exception {
		try {
			if(gp != null && gp.getGpId() != null) {
				gp = new GpFacade().findById(gp);
				setJson(JSON.toJSONString(gp)); 
			}
			syDefs = new zrsy.facade.SyDefFacade().find("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef",zrsy.vo.SyDef.SELF_FIELDS);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(gp != null && gp.getGpId() != null) {
				//gp = new GpFacade().findById(gp);
				//setJson(JSON.toJSONString(gp)); 
			//}
			syDefs = new zrsy.facade.SyDefFacade().find("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef",zrsy.vo.SyDef.SELF_FIELDS);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(gp != null && gp.getGpId() != null) {
				gp = new GpFacade().findById(gp);
				setJson(JSON.toJSONString(gp)); 
			}
			syDefs = new zrsy.facade.SyDefFacade().find("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef ",zrsy.vo.SyDef.SELF_FIELDS);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String index() throws Exception {
		try {
			if(gp != null && gp.getGpId() != null) {
				gp = new GpFacade().findById(gp);
				setJson(JSON.toJSONString(gp)); 
			}
			syDefs = new zrsy.facade.SyDefFacade().find("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef ",zrsy.vo.SyDef.SELF_FIELDS);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpAction Exception", e);
			return ERROR;
		}
		return "index";
	}
	public String auth() throws Exception {
		try {
			if(gp != null && gp.getGpId() != null) {
				gp = new GpFacade().findById(gp);
				setJson(JSON.toJSONString(gp)); 
			}
			syDef = new zrsy.facade.SyDefFacade().findById("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef where SyDef.SyId = "+this.getUsrSession().getSyId(),zrsy.vo.SyDef.SELF_FIELDS);
			
			int amount = new GpSyFacade().amount("select count(*) as amount from GpSy where SyId = "+syDef.getSyId());
			if(amount > 0) syDef.setChecked("checked");
			
			menus = new MenuFacade().find("select distinct "+Menu.SELF_FIELDS+" from Menu where Leve = 0 and Menu.SyId = " + syDef.getSyId(),Menu.SELF_FIELDS);
			
			List<Menu> _subMenus = new MenuFacade().find("select distinct "+Menu.SELF_FIELDS+" from Menu " +
					" inner join GpSy on(GpSy.SyId = Menu.SyId) " +
					" where Menu.Leve = 1 " +
					" and GpSy.SyId = " + syDef.getSyId() +
					" and GpSy.GpId = "+gp.getGpId() + 
					" order by sort ",Menu.SELF_FIELDS);
			
			subMenus = new MenuFacade().find("select distinct "+Menu.SELF_FIELDS+" from Menu " +
					" left join GpMenu on(GpMenu.MenuId = Menu.Id)" +
					" left join GpSy on(GpMenu.GpId = GpSy.GpId and GpSy.SyId = Menu.SyId)" +
					" where Menu.Leve = 1 " +
					" and GpSy.SyId = " + syDef.getSyId() +
					" and GpMenu.GpId = "+gp.getGpId() + 
					" order by sort ",Menu.SELF_FIELDS);
			
			List<Menu> _subs = new MenuFacade().find("select distinct "+Menu.SELF_FIELDS+" from Menu " +
					" inner join GpSy on(GpSy.SyId = Menu.SyId) " +
					" where Menu.Leve = 2 " +
					" and GpSy.SyId = " + syDef.getSyId() +
					" and GpSy.GpId = "+gp.getGpId() + 
					" order by sort ",Menu.SELF_FIELDS);

			java.util.List<zrsy.vo.Menu> subs = new MenuFacade().find("select distinct "+Menu.SELF_FIELDS+" from Menu " +
					" left join GpMenu on(GpMenu.MenuId = Menu.Id)" +
					" left join GpSy on(GpMenu.GpId = GpSy.GpId and GpSy.SyId = Menu.SyId)" +
					" where Menu.Leve = 2 " +
					" and Menu.SyId = " + syDef.getSyId() +
					//" and GpMenu.GpId = "+gp.getGpId() + 
					" order by sort ",Menu.SELF_FIELDS);

			List<Node> _nodes = new NodeFacade().find("select distinct "+Node.SELF_FIELDS+" from Node" +
					" inner join GpSy on(GpSy.SyId = Node.SyId)" +
					" where GpSy.GpId = "+gp.getGpId() +
					" and GpSy.SyId = " + syDef.getSyId(),Node.SELF_FIELDS);
			
			nodes = new NodeFacade().find("select distinct "+Node.SELF_FIELDS+" from Node " +
					" inner join GpNode on(GpNode.NodeId = Node.Id)" +
					" inner join GpSy on(GpNode.GpId = GpSy.GpId and GpSy.SyId = Node.SyId)" +
					" where GpNode.GpId = "+gp.getGpId() +
					" and GpSy.SyId = " + syDef.getSyId(),Node.SELF_FIELDS);
			
			btns = new BtnFacade().find("select "+Btn.SELF_FIELDS+" from Btn where SyId = " + syDef.getSyId(),Btn.SELF_FIELDS);
			
			List<PgBtn> pgBtns = new PgBtnFacade().find("select "+PgBtn.SELF_FIELDS+" from PgBtn inner join Node on(Node.Id = PgBtn.NodeId) where Node.SyId = "+syDef.getSyId(),PgBtn.SELF_FIELDS);
			
			List<GpMenu> gpMenus = new GpMenuFacade().find("select "+GpMenu.SELF_FIELDS+" from GpMenu where GpMenu.GpId = "+gp.getGpId(),GpMenu.SELF_FIELDS);
			List<GpNode> gpNodes = new GpNodeFacade().find("select "+GpNode.SELF_FIELDS+" from GpNode where GpNode.GpId = "+gp.getGpId(),GpNode.SELF_FIELDS);
			List<GpBtn> gpBtns = new GpBtnFacade().find("select "+GpBtn.SELF_FIELDS+" from GpBtn where GpBtn.GpId = "+gp.getGpId(),GpBtn.SELF_FIELDS);

			for(int j=0; j<_nodes.size(); j++) {
				for(int m=0; m<gpNodes.size(); m++) {
					if(_nodes.get(j).getId().equals(gpNodes.get(m).getNodeId()))
						_nodes.get(j).setChecked("checked");
				}
			}
			
			for(int j=0; j<nodes.size(); j++) {
				for(int m=0; m<gpNodes.size(); m++) {
					if(nodes.get(j).getId().equals(gpNodes.get(m).getNodeId()))
						nodes.get(j).setChecked("checked");
				}

				for(int n=0; n<btns.size(); n++) {
					Btn btn = new Btn();
					btn.setId(getBtns().get(n).getId());
					btn.setText(getBtns().get(n).getText());
					
					for(int k=0; k<gpBtns.size(); k++) {
						if(btns.get(n).getId().equals(gpBtns.get(k).getBtnId()) 
								&& gpBtns.get(k).getNodeId().equals(nodes.get(j).getId()))
							btn.setChecked("checked");
					}
					boolean b = false;
					for(int k=0; k<pgBtns.size(); k++) {
						if(pgBtns.get(k).getBtnId().equals(btns.get(n).getId()) &&
								pgBtns.get(k).getNodeId().equals(nodes.get(j).getId()))
							b = true;
					}
					if(b) nodes.get(j).addtoBtns(btn);
				}
			}
			
			for(int i=0; i<subs.size(); i++) {
				for(int j=0; j<_nodes.size(); j++) {
					if(subs.get(i).getId().equals(_nodes.get(j).getMenuId()))
						subs.get(i).addtoNodes(_nodes.get(j));
				}
				
				for(int m=0; m<gpMenus.size(); m++) {
					if(subs.get(i).getId().equals(gpMenus.get(m).getMenuId()))
							subs.get(i).setChecked("checked");
				}
			}			
			
			for(int i=0; i<subMenus.size(); i++) {
				for(int k=0; k<subs.size(); k++) {
					if(subMenus.get(i).getId().equals(subs.get(k).getParent()))	{
						subMenus.get(i).addtoSubMenus(subs.get(k));
					}
				}
					
				for(int j=0; j<_nodes.size(); j++) {
					if(subMenus.get(i).getId().equals(_nodes.get(j).getMenuId()))
						subMenus.get(i).addtoNodes(_nodes.get(j));
				}
				
				for(int m=0; m<gpMenus.size(); m++) {
					if(subMenus.get(i).getId().equals(gpMenus.get(m).getMenuId()))
							subMenus.get(i).setChecked("checked");
				}
			}
			
			for(int i=0; i<_subMenus.size(); i++) {
				for(int k=0; k<_subs.size(); k++) {
					if(_subMenus.get(i).getId().equals(_subs.get(k).getParent()))	{
						_subMenus.get(i).addtoSubMenus(_subs.get(k));
					}
					
					for(int m=0; m<gpMenus.size(); m++) {
						if(_subs.get(k).getId().equals(gpMenus.get(m).getMenuId()))
							_subs.get(k).setChecked("checked");
					}
				}
			}
			
			for(int i=0; i<menus.size(); i++) {
				for(int j=0; j<_subMenus.size(); j++) {
					if(menus.get(i).getId().equals(_subMenus.get(j).getParent()))
						menus.get(i).addtoSubMenus(_subMenus.get(j));
					
					for(int m=0; m<gpMenus.size(); m++) {
						if(_subMenus.get(j).getId().equals(gpMenus.get(m).getMenuId()))
							_subMenus.get(j).setChecked("checked");
					}
				}

				for(int m=0; m<gpMenus.size(); m++) {
					if(menus.get(i).getId().equals(gpMenus.get(m).getMenuId()))
						menus.get(i).setChecked("checked");
				}
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpAction Exception", e);
			return ERROR;
		}
		return "author";
	}
	
	public String view() throws Exception {
		try {
			if(gp != null && gp.getGpId() != null) {
				gp.setSyId(getUsrSession().getSyId());
				gp = new GpFacade().findById(gp);
				setJson(JSON.toJSONString(gp)); 
			}
			syDef = new zrsy.facade.SyDefFacade().findById("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef where SyDef.SyId = "+this.getUsrSession().getSyId(),zrsy.vo.SyDef.SELF_FIELDS);
			
			int amount = new GpSyFacade().amount("select count(*) as amount from GpSy where SyId = "+syDef.getSyId());
			if(amount > 0) syDef.setChecked("checked");
			
			menus = new MenuFacade().find("select distinct "+Menu.SELF_FIELDS+" from Menu where Leve = 0 and Menu.SyId = " + syDef.getSyId(),Menu.SELF_FIELDS);
			
			List<Menu> _subMenus = new MenuFacade().find("select distinct "+Menu.SELF_FIELDS+" from Menu " +
					" inner join GpSy on(GpSy.SyId = Menu.SyId) " +
					" where Menu.Leve = 1 " +
					" and GpSy.SyId = " + syDef.getSyId() +
					" and GpSy.GpId = "+gp.getGpId() + 
					" order by sort ",Menu.SELF_FIELDS);
			
			subMenus = new MenuFacade().find("select distinct "+Menu.SELF_FIELDS+" from Menu " +
					" left join GpMenu on(GpMenu.MenuId = Menu.Id)" +
					" left join GpSy on(GpMenu.GpId = GpSy.GpId and GpSy.SyId = Menu.SyId)" +
					" where Menu.Leve = 1 " +
					" and GpSy.SyId = " + syDef.getSyId() +
					" and GpMenu.GpId = "+gp.getGpId() + 
					" order by sort ",Menu.SELF_FIELDS);
			
			List<Menu> _subs = new MenuFacade().find("select distinct "+Menu.SELF_FIELDS+" from Menu " +
					" inner join GpSy on(GpSy.SyId = Menu.SyId) " +
					" where Menu.Leve = 2 " +
					" and GpSy.SyId = " + syDef.getSyId() +
					" and GpSy.GpId = "+gp.getGpId() + 
					" order by sort ",Menu.SELF_FIELDS);

			java.util.List<zrsy.vo.Menu> subs = new MenuFacade().find("select distinct "+Menu.SELF_FIELDS+" from Menu " +
					" left join GpMenu on(GpMenu.MenuId = Menu.Id)" +
					" left join GpSy on(GpMenu.GpId = GpSy.GpId and GpSy.SyId = Menu.SyId)" +
					" where Menu.Leve = 2 " +
					" and Menu.SyId = " + syDef.getSyId() +
					//" and GpMenu.GpId = "+gp.getGpId() + 
					" order by sort ",Menu.SELF_FIELDS);

			List<Node> _nodes = new NodeFacade().find("select distinct "+Node.SELF_FIELDS+" from Node" +
					" inner join GpSy on(GpSy.SyId = Node.SyId)" +
					" where GpSy.GpId = "+gp.getGpId() +
					" and GpSy.SyId = " + syDef.getSyId(),Node.SELF_FIELDS);
			
			nodes = new NodeFacade().find("select distinct "+Node.SELF_FIELDS+" from Node " +
					" inner join GpNode on(GpNode.NodeId = Node.Id)" +
					" inner join GpSy on(GpNode.GpId = GpSy.GpId and GpSy.SyId = Node.SyId)" +
					" where GpNode.GpId = "+gp.getGpId() +
					" and GpSy.SyId = " + syDef.getSyId(),Node.SELF_FIELDS);
			
			btns = new BtnFacade().find("select "+Btn.SELF_FIELDS+" from Btn where SyId = " + syDef.getSyId(),Btn.SELF_FIELDS);
			
			List<GpMenu> gpMenus = new GpMenuFacade().find("select "+GpMenu.SELF_FIELDS+" from GpMenu where GpMenu.GpId = "+gp.getGpId(),GpMenu.SELF_FIELDS);
			List<GpNode> gpNodes = new GpNodeFacade().find("select "+GpNode.SELF_FIELDS+" from GpNode where GpNode.GpId = "+gp.getGpId(),GpNode.SELF_FIELDS);
			List<GpBtn> gpBtns = new GpBtnFacade().find("select "+GpBtn.SELF_FIELDS+" from GpBtn where GpBtn.GpId = "+gp.getGpId(),GpBtn.SELF_FIELDS);

			for(int j=0; j<_nodes.size(); j++) {
				for(int m=0; m<gpNodes.size(); m++) {
					if(_nodes.get(j).getId().equals(gpNodes.get(m).getNodeId()))
						_nodes.get(j).setChecked("checked");
				}
			}
			
			for(int j=0; j<nodes.size(); j++) {
				for(int m=0; m<gpNodes.size(); m++) {
					if(nodes.get(j).getId().equals(gpNodes.get(m).getNodeId()))
						nodes.get(j).setChecked("checked");
				}

				for(int n=0; n<btns.size(); n++) {
					Btn btn = new Btn();
					btn.setId(getBtns().get(n).getId());
					btn.setText(getBtns().get(n).getText());
					
					for(int k=0; k<gpBtns.size(); k++) {
						if(btns.get(n).getId().equals(gpBtns.get(k).getBtnId()) 
								&& gpBtns.get(k).getNodeId().equals(nodes.get(j).getId()))
							btn.setChecked("checked");
					}
					nodes.get(j).addtoBtns(btn);
				}
			}
			
			for(int i=0; i<subs.size(); i++) {
				for(int j=0; j<_nodes.size(); j++) {
					if(subs.get(i).getId().equals(_nodes.get(j).getMenuId()))
						subs.get(i).addtoNodes(_nodes.get(j));
				}
				
				for(int m=0; m<gpMenus.size(); m++) {
					if(subs.get(i).getId().equals(gpMenus.get(m).getMenuId()))
							subs.get(i).setChecked("checked");
				}
			}			
			
			for(int i=0; i<subMenus.size(); i++) {
				for(int k=0; k<subs.size(); k++) {
					if(subMenus.get(i).getId().equals(subs.get(k).getParent()))	{
						subMenus.get(i).addtoSubMenus(subs.get(k));
					}
				}
					
				for(int j=0; j<_nodes.size(); j++) {
					if(subMenus.get(i).getId().equals(_nodes.get(j).getMenuId()))
						subMenus.get(i).addtoNodes(_nodes.get(j));
				}
				
				for(int m=0; m<gpMenus.size(); m++) {
					if(subMenus.get(i).getId().equals(gpMenus.get(m).getMenuId()))
							subMenus.get(i).setChecked("checked");
				}
			}
			
			for(int i=0; i<_subMenus.size(); i++) {
				for(int k=0; k<_subs.size(); k++) {
					if(_subMenus.get(i).getId().equals(_subs.get(k).getParent()))	{
						_subMenus.get(i).addtoSubMenus(_subs.get(k));
					}
					
					for(int m=0; m<gpMenus.size(); m++) {
						if(_subs.get(k).getId().equals(gpMenus.get(m).getMenuId()))
							_subs.get(k).setChecked("checked");
					}
				}
			}
			
			for(int i=0; i<menus.size(); i++) {
				for(int j=0; j<_subMenus.size(); j++) {
					if(menus.get(i).getId().equals(_subMenus.get(j).getParent()))
						menus.get(i).addtoSubMenus(_subMenus.get(j));
					
					for(int m=0; m<gpMenus.size(); m++) {
						if(_subMenus.get(j).getId().equals(gpMenus.get(m).getMenuId()))
							_subMenus.get(j).setChecked("checked");
					}
				}

				for(int m=0; m<gpMenus.size(); m++) {
					if(menus.get(i).getId().equals(gpMenus.get(m).getMenuId()))
						menus.get(i).setChecked("checked");
				}
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpAction Exception", e);
			return ERROR;
		}
		return "author";
	}
	
	public String list() throws Exception {
		try {
			if(gp == null) gp = new Gp();
			gp.setSyId(getUsrSession().getSyId());
			int total = new GpFacade().amount(gp);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			gp.setSyId(this.getUsrSession().getSyId());
			
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			gps = new GpFacade().find(gp,getPageVO());
			GpJson gpJson = new GpJson();
			gpJson.Rows = gps;
			gpJson.Total = total;
			setJson(JSON.toJSONString(gpJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("GpAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	
	public String sav() throws Exception {
		try {
			//gp.setCreateBy(getSession().getUserId());
			//gp.setCreateDate(new Date());
			//gp.setLastUpd(getSession().getUserId());
			//gp.setLastUpdDate(new Date());
			gp.setGpBtns(gpBtns);
			gp.setGpMenus(gpMenus);
			gp.setGpNodes(gpNodes);
			gp.setGpUsrs(gpUsrs);
			gp.setGpSys(gpSys);

			if(gp.getGpId() == null)
				new GpFacade().save(gp);
			else
				new GpFacade().update(gp);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("GpAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//gp.setCreateBy(getSession().getUserId());
			//gp.setCreateDate(new Date());
			//gp.setLastUpd(getSession().getUserId());
			//gp.setLastUpdDate(new Date());
			new GpFacade().update(gp);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("GpAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(gps != null && gps.size() > 0) {
				for(int i=0; i<gps.size();i++) {
					if(gps.get(i) != null) {
						//gps.get(i).setLastUpd(getSession().getUserId());
						//gps.get(i).setLastUpdDate(new Date());
						new GpFacade().submit(gps.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("GpAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(gps != null && gps.size() > 0) {
				for(int i=0; i<gps.size();i++) {
					if(gps.get(i) != null) {
						//gps.get(i).setLastUpd(getSession().getUserId());
						//gps.get(i).setLastUpdDate(new Date());
						new GpFacade().submit(gps.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("GpAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(gps != null && gps.size() > 0) {
				for(int i=0; i<gps.size();i++) {
					if(gps.get(i) != null) {
						//gps.get(i).setLastUpd(getSession().getUserId());
						//gps.get(i).setLastUpdDate(new Date());
						new GpFacade().update(gps.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("GpAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(gps != null && gps.size() > 0) {
				for(int i=0; i<gps.size();i++) {
					if(gps.get(i) != null) {
						//gps.get(i).setLastUpd(getSession().getUserId());
						//gps.get(i).setLastUpdDate(new Date());
						new GpFacade().submit(gps.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("GpAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(gps != null && gps.size() > 0) {
				for(int i=0; i<gps.size();i++) {
					if(gps.get(i) != null) {
						//gps.get(i).setLastUpd(getSession().getUserId());
						//gps.get(i).setLastUpdDate(new Date());
						new GpFacade().submit(gps.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("GpAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(gps != null && gps.size() > 0) {
				for(int i=0; i<gps.size();i++) {
					if(gps.get(i) != null) {
						//gps.get(i).setLastUpd(getSession().getUserId());
						//gps.get(i).setLastUpdDate(new Date());
						new GpFacade().submit(gps.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("GpAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(gps != null && gps.size() > 0) {
				for(int i=0; i<gps.size();i++) {
					if(gps.get(i) != null) {
						//gps.get(i).setLastUpd(getSession().getUserId());
						//gps.get(i).setLastUpdDate(new Date());
						new GpFacade().submit(gps.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("GpAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(gps != null && gps.size() > 0) {
				for(int i=0; i<gps.size();i++) {
					if(gps.get(i) != null){
						//gps.get(i).setLastUpd(getSession().getUserId());
						//gps.get(i).setLastUpdDate(new Date());
						new GpFacade().submit(gps.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("GpAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(gps != null && gps.size() > 0) {
				for(int i=0; i<gps.size();i++) {
					if(gps.get(i) != null){
						//gps.get(i).setLastUpd(getSession().getUserId());
						//gps.get(i).setLastUpdDate(new Date());
						new GpFacade().review(gps.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("GpAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(gps != null && gps.size() > 0) {
				for(int i=0; i<gps.size();i++) {
					if(gps.get(i) != null) {
						//gps.get(i).setLastUpd(getSession().getUserId());
						//gps.get(i).setLastUpdDate(new Date());
						new GpFacade().review(gps.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("GpAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(gps != null && gps.size() > 0) {
				for(int i=0; i<gps.size();i++) {
					if(gps.get(i) != null) {
						//gps.get(i).setLastUpd(getSession().getUserId());
						//gps.get(i).setLastUpdDate(new Date());
						new GpFacade().confirm(gps.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("GpAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(gps != null && gps.size() > 0) {
				for(int i=0; i<gps.size();i++) {
					if(gps.get(i) != null) {
						//gps.get(i).setLastUpd(getSession().getUserId());
						//gps.get(i).setLastUpdDate(new Date());
						new GpFacade().confirm(gps.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("GpAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(gps != null && gps.size() > 0) {
					for(int i=0; i<gps.size();i++) {
						if(gps.get(i) != null) {
							//gps.get(i).setLastUpd(getSession().getUserId());
							//gps.get(i).setLastUpdDate(new Date());
							new GpFacade().confirm(gps.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("GpAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<Gp> gps = new GpFacade().find(gp);
			if(gps != null && gps.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"组Id",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"系统",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"组名称",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"说明",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<gps.size();i++) {
					row++;
					int m = 0;
					if(gps.get(i).getGpId() != null) 
						ws.addCell(new jxl.write.Number(m,row,gps.get(i).getGpId(),wcformat));
					m++;
					if(gps.get(i).getSyId() != null) 
						ws.addCell(new jxl.write.Number(m,row,gps.get(i).getSyId(),wcformat));
					m++;
					if(gps.get(i).getGpName() != null) 
						ws.addCell(new jxl.write.Label(m,row,gps.get(i).getGpName(),wcformat));
					m++;
					if(gps.get(i).getRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,gps.get(i).getRemark(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("GpListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(gps != null && gps.size() > 0) {
				for(int i=0; i<gps.size();i++) {
					if(gps.get(i) != null) {
						//gps.get(i).setLastUpd(getSession().getUserId());
						//gps.get(i).setLastUpdDate(new Date());
						new GpFacade().confirm(gps.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("GpAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(gps != null && gps.size() > 0) {
				for(int i=0; i<gps.size();i++) {
					if(gps.get(i) != null) {
						//gps.get(i).setLastUpd(getSession().getUserId());
						//gps.get(i).setLastUpdDate(new Date());
						new GpFacade().confirm(gps.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("GpAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<Gp> getGps() {
		return gps;
	}
	public void setGps(List<Gp> gps) {
		this.gps = gps;
	}
	public Gp getGp() {
		return gp;
	}
	public void setGp(Gp gp) {
		this.gp = gp;
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
	public java.util.List<zrsy.vo.GpBtn> getGpBtns() {
		return gpBtns;
	}
	public void setGpBtns(java.util.List<zrsy.vo.GpBtn> gpBtns){
		this.gpBtns = gpBtns;
	}
	public void addtoGpBtns(zrsy.vo.GpBtn gpBtn){
		if(getGpBtns() == null) setGpBtns(new java.util.ArrayList<zrsy.vo.GpBtn>());
			getGpBtns().add(gpBtn);
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
	public java.util.List<zrsy.vo.GpNode> getGpNodes() {
		return gpNodes;
	}
	public void setGpNodes(java.util.List<zrsy.vo.GpNode> gpNodes){
		this.gpNodes = gpNodes;
	}
	public void addtoGpNodes(zrsy.vo.GpNode gpNode){
		if(getGpNodes() == null) setGpNodes(new java.util.ArrayList<zrsy.vo.GpNode>());
			getGpNodes().add(gpNode);
	}
	public java.util.List<zrsy.vo.GpUsr> getGpUsrs() {
		return gpUsrs;
	}
	public void setGpUsrs(java.util.List<zrsy.vo.GpUsr> gpUsrs){
		this.gpUsrs = gpUsrs;
	}
	public void addtoGpUsrs(zrsy.vo.GpUsr gpUsr){
		if(getGpUsrs() == null) setGpUsrs(new java.util.ArrayList<zrsy.vo.GpUsr>());
			getGpUsrs().add(gpUsr);
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
	public java.util.List<zrsy.vo.Menu> getMenus() {
		return menus;
	}
	public void setMenus(java.util.List<zrsy.vo.Menu> menus) {
		this.menus = menus;
	}
	public java.util.List<zrsy.vo.Menu> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(java.util.List<zrsy.vo.Menu> subMenus) {
		this.subMenus = subMenus;
	}
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	public java.util.List<zrsy.vo.Btn> getBtns() {
		return btns;
	}
	public void setBtns(java.util.List<zrsy.vo.Btn> btns) {
		this.btns = btns;
	}
	public List<GpSy> getGpSys() {
		return gpSys;
	}
	public void setGpSys(List<GpSy> gpSys) {
		this.gpSys = gpSys;
	}
	public zrsy.vo.SyDef getSyDef() {
		return syDef;
	}
	public void setSyDef(zrsy.vo.SyDef syDef) {
		this.syDef = syDef;
	}
}