package zrsy.vo;

import java.util.ArrayList;
import java.util.List;

public class Menu extends BasicMenu {
	public static final String ALL_FIELDS = "Menu.Id,Menu.SyId,Menu.Parent,Menu.Width,Menu.Leve,Menu.Sort,Menu.Text,Menu.Click,Menu.Icon,SyDef.SyName as SyName";
	public static final String LIST_FIELDS = "Menu.Id,Menu.SyId,Menu.Parent,Menu.Width,Menu.Leve,Menu.Sort,Menu.Text,Menu.Click,Menu.Icon,SyDef.SyName as SyName";

	private java.util.List<zrsy.vo.GpMenu> gpMenus;
	private java.util.List<zrsy.vo.Node> nodes;

	private java.lang.String syName;
	
	private String checked;
	private List<Menu> subMenus;

	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public List<Menu> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<Menu> subMenus) {
		this.subMenus = subMenus;
	}
	public void addtoSubMenus(Menu subMenu) {
		if(getSubMenus() == null) setSubMenus(new ArrayList<Menu>());
		getSubMenus().add(subMenu);
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

	public java.lang.String getSyName(){
		return syName;
	}
	public void setSyName(java.lang.String syName){
		this.syName = syName;
	}

}