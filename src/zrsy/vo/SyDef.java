package zrsy.vo;

public class SyDef extends BasicSyDef {
	public static final String ALL_FIELDS = "SyDef.SyId,SyDef.SyName,SyDef.SyText";
	public static final String LIST_FIELDS = "SyDef.SyId,SyDef.SyName,SyDef.SyText";

	private java.util.List<zrsy.vo.Gp> gps;
	private java.util.List<zrsy.vo.Menu> menus;
	private java.util.List<zrsy.vo.Node> nodes;
	private java.util.List<zrsy.vo.Btn> btns;

	private String checked;

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

	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}		
	public java.util.List<zrsy.vo.Btn> getBtns() {
		return btns;
	}
	public void setBtns(java.util.List<zrsy.vo.Btn> btns) {
		this.btns = btns;
	}

}