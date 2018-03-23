package zrprjt.ww.liger;

import gnwf.vo.WfRd;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import zrprjt.facade.PrjtDefFacade;
import zrprjt.vo.PrjtDef;
import zrsy.facade.GpFacade;
import zrsy.facade.MenuFacade;
import zrsy.facade.NodeFacade;
import zrsy.vo.Gp;
import zrsy.vo.Menu;
import zrsy.vo.Node;
import zrsy.vo.json.LigerTree;
import zrsy.vo.liger.LiMenu;
import zrsy.vo.liger.LiMenuBar;
import zrsy.vo.liger.LiMenuItem;
import zrsy.vo.liger.LiNodeData;
import zrsy.ww.BasicAction;
import zrsy.ww.MSG;

import com.alibaba.fastjson.JSON;

public class IndexAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	
	private LiMenuBar menuBar = new LiMenuBar();
	private java.util.List<zrsy.vo.SyDef> syDefs;
	private List<PrjtDef> prjtDefs;
	
	private Gp gp;
	private WfRd wfRd = new WfRd(); 
	

	public String execute() throws Exception {
		try {
			syDefs = new zrsy.facade.SyDefFacade().find("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef ",zrsy.vo.SyDef.SELF_FIELDS);
			
			List<Menu> menus = new MenuFacade().find("select distinct "+Menu.SELF_FIELDS+" from Menu " +
					" inner join GpMenu on(Menu.Id = GpMenu.MenuId) " +
					" inner join GpUsr on(GpMenu.GpId = GpUsr.GpId) " +
					" inner join GpSy on(GpUsr.GpId = GpSy.GpId and GpSy.SyId = Menu.SyId) " +
					" Where Menu.Leve = 0 " +
					" and GpSy.SyId = " + this.getUsrSession().getSyId() +
					" and GpUsr.UsrId = " + this.getUsrSession().getId() + 
					" order by Menu.sort ",Menu.SELF_FIELDS);
			
			List<Menu> chrs = new MenuFacade().find("select distinct "+Menu.SELF_FIELDS+" from Menu " +
					" inner join GpMenu on(Menu.Id = GpMenu.MenuId) " +
					" inner join GpUsr on(GpMenu.GpId = GpUsr.GpId) " +
					" inner join GpSy on(GpUsr.GpId = GpSy.GpId and GpSy.SyId = Menu.SyId)" +
					" Where Menu.Leve = 2 " +
					" and GpSy.SyId = " + this.getUsrSession().getSyId() +
					" and GpUsr.UsrId = " + this.getUsrSession().getId()+ 
					" order by Menu.sort ",Menu.SELF_FIELDS);

			prjtDefs = new PrjtDefFacade().find("select "+PrjtDef.SELF_FIELDS+" from PrjtDef order by PrjtNo desc", PrjtDef.SELF_FIELDS);
			
			for(int i=0; i<menus.size(); i++) {
				List<LiMenuItem> items = new ArrayList<LiMenuItem>();
				
				List<Menu> subs = new MenuFacade().find("select distinct "+Menu.SELF_FIELDS+" from Menu " +
					" inner join GpMenu on(Menu.Id = GpMenu.MenuId) " +
					" inner join GpUsr on(GpMenu.GpId = GpUsr.GpId) " +
					" inner join GpSy on(GpUsr.GpId = GpSy.GpId and GpSy.SyId = Menu.SyId)" +
					" Where Menu.Leve = 1 " +
					" and GpSy.SyId = " + this.getUsrSession().getSyId() +
					" and GpUsr.UsrId = " + this.getUsrSession().getId()+ " "+
					" and Menu.Parent = "+menus.get(i).getId()+ 
					" order by Menu.sort ",Menu.SELF_FIELDS);
				
				for(int j=0; j<subs.size(); j++) {
					List<LiMenuItem> children = new ArrayList<LiMenuItem>();
					
					for(int k=0; k<chrs.size(); k++) {
						if(chrs.get(k).getParent().equals(subs.get(j).getId())) {
							
							if(chrs.get(k).getId().equals(24)) {
								List<LiMenuItem> prjts = new ArrayList<LiMenuItem>();
								
								for(int ii=0; ii<prjtDefs.size(); ii++) {
									prjts.add(new LiMenuItem(chrs.get(k).getId().toString(),prjtDefs.get(ii).getPrjtNm(),null,chrs.get(k).getClick(),null,prjtDefs.get(ii).getPrjtNo()));
								}
								
								if(prjts.size() > 0)
									children.add(new LiMenuItem(chrs.get(k).getId().toString(),chrs.get(k).getText(),null,chrs.get(k).getClick(),null,prjts));
							}
							else
								children.add(new LiMenuItem(chrs.get(k).getId().toString(),chrs.get(k).getText(),null,chrs.get(k).getClick(),null));
						}
					}
					if(children.size() > 0)
						items.add(new LiMenuItem(subs.get(j).getId().toString(),subs.get(j).getText(),null,subs.get(j).getClick(),null,children));
					else
						items.add(new LiMenuItem(subs.get(j).getId().toString(),subs.get(j).getText(),null,subs.get(j).getClick(),null));
				}

				menuBar.addtoItems(new LiMenu(menus.get(i).getText(),menus.get(i).getWidth(),items));
			}

			List<LiNodeData> data = new ArrayList<LiNodeData>();
			
			List<Node> nodes = new NodeFacade().find("select distinct "+Node.SELF_FIELDS+" from Node " +
					" inner join GpNode on(GpNode.NodeId = Node.Id) " +
					" inner join GpUsr on(GpUsr.GpId = GpNode.GpId) " +
					" inner join GpSy on(GpUsr.GpId = GpSy.GpId and GpSy.SyId = Node.SyId)" +
					" where GpUsr.UsrId = " + this.getUsrSession().getId()+ " "+
					" 	and GpSy.SyId = " + this.getUsrSession().getSyId() +
					" 	and Node.MenuId in(" +
					"		select Id as MenuId from Menu where Text ='我的控制台' and SyId = " + getUsrSession().getSyId() +
					")" +
					" order by Node.sort ",Node.SELF_FIELDS);

			for(int i=0; i<nodes.size(); i++) {
				data.add(new LiNodeData(nodes.get(i).getId().toString(),nodes.get(i).getText(),nodes.get(i).getUrl()));
			}
			
			LigerTree tree = new LigerTree(false,data,"nodeclk");

			setJson(JSON.toJSONString(menuBar));
			setJson1(JSON.toJSONString(tree));
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("IndexAction Exception", e);
			return ERROR;
		}
		
		
//		String sql ="select "+Gp.SELF_FIELDS+" from Gp inner join GpUsr on (Gp.GpId = GpUsr.GpId) where GpUsr.UsrId = "+getUsrSession().getId();
//		List<Gp> gps = new  ArrayList<Gp>();
//		gps= new GpFacade().find(sql, Gp.SELF_FIELDS);
//		gpNames = "";
//		for( Gp e: gps){
//			gpNames=gpNames+","+e.getGpName();
//		}
		if(getUsrSession().getGpNames().indexOf("超级用户")>-1){
			String sql ="select "+Gp.SELF_FIELDS+" from Gp inner join GpUsr on (Gp.GpId = GpUsr.GpId) where Gp.SyId = 6 and Gp.GpName = '超级用户' and "
					+ " GpUsr.UsrId = "+getUsrSession().getId();
			List<Gp> gps = new GpFacade().find(sql, Gp.SELF_FIELDS);
			if(gps == null || gps.size() == 0) {
				return INITIALIZES;
			} else { //SyId=6代表pdm系统,必须是pdm系统的超级用户才能进入系统管理配置界面
				return "adminInitializes";
			}		
		}
		return INITIALIZES;
		
	
	}
	
	private  String  gpNames;

	public java.util.List<zrsy.vo.SyDef> getSyDefs() {
		return syDefs;
	}
	public void setSyDefs(java.util.List<zrsy.vo.SyDef> syDefs) {
		this.syDefs = syDefs;
	}	
	public List<PrjtDef> getPrjtDefs() {
		return prjtDefs;
	}
	public void setPrjtDefs(List<PrjtDef> prjtDefs) {
		this.prjtDefs = prjtDefs;
	}

	public Gp getGp() {
		return gp;
	}

	public void setGp(Gp gp) {
		this.gp = gp;
	}

	public WfRd getWfRd() {
		return wfRd;
	}
	public String getGpNames() {
		return gpNames;
	}
	public void setGpNames(String gpNames) {
		this.gpNames = gpNames;
	}
	
}
