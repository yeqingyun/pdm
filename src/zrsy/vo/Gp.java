package zrsy.vo;

import java.util.List;

public class Gp extends BasicGp {
	public static final String ALL_FIELDS = "Gp.GpId,Gp.SyId,Gp.GpName,Gp.Remark,SyDef.SyName as SyName";
	public static final String LIST_FIELDS = "Gp.GpId,Gp.SyId,Gp.GpName,Gp.Remark,SyDef.SyName as SyName";

	private java.util.List<zrsy.vo.GpBtn> gpBtns;
	private java.util.List<zrsy.vo.GpMenu> gpMenus;
	private java.util.List<zrsy.vo.GpNode> gpNodes;
	private java.util.List<zrsy.vo.GpUsr> gpUsrs;
	private List<GpSy> gpSys;
	

	private java.lang.String syName;
	private String checked;
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public java.util.List<zrsy.vo.GpBtn> getGpBtns() {
		return gpBtns;
	}
	public void setGpBtns(java.util.List<zrsy.vo.GpBtn> gpBtns) {
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

	public java.lang.String getSyName(){
		return syName;
	}
	public void setSyName(java.lang.String syName){
		this.syName = syName;
	}
	public List<GpSy> getGpSys() {
		return gpSys;
	}
	public void setGpSys(List<GpSy> gpSys) {
		this.gpSys = gpSys;
	}
}