package zrsy.vo;

import java.util.ArrayList;
import java.util.List;

public class Node extends BasicNode {
	public static final String ALL_FIELDS = "Node.Id,Node.SyId,Node.Parent,Node.MenuId,Node.NodeWidth,Node.CheckBox,Node.Leve,Node.Sort,Node.Text,Node.Url,Menu.Text as MenuNm,SyDef.SyName as SyName";
	public static final String LIST_FIELDS = "Node.Id,Node.SyId,Node.Parent,Node.MenuId,Node.NodeWidth,Node.CheckBox,Node.Leve,Node.Sort,Node.Text,Node.Url,Menu.Text as MenuNm,SyDef.SyName as SyName";

	private java.util.List<zrsy.vo.GpNode> gpNodes;

	private java.lang.String menuNm;
	private java.lang.String syName;
	
	private String checked;
	private List<Node> subNodes;
	private List<Btn> btns;
	private java.util.List<zrsy.vo.PgBtn> pgBtns;
	public java.util.List<zrsy.vo.PgBtn> getPgBtns() {
		return pgBtns;
	}
	public void setPgBtns(java.util.List<zrsy.vo.PgBtn> pgBtns) {
		this.pgBtns = pgBtns;
	}
	public void addtoNodes(PgBtn pgBtn) {
		if(getPgBtns() == null) setPgBtns(new ArrayList<PgBtn>());
		getPgBtns().add(pgBtn);
	}	
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public List<Node> getSubNodes() {
		return subNodes;
	}
	public void setSubNodes(List<Node> subNodes) {
		this.subNodes = subNodes;
	}
	public void addtoNodes(Node node) {
		if(getSubNodes() == null) setSubNodes(new ArrayList<Node>());
		getSubNodes().add(node);
	}	
	public List<Btn> getBtns() {
		return btns;
	}
	public void setBtns(List<Btn> btns) {
		this.btns = btns;
	}
	public void addtoBtns(Btn btn) {
		if(getBtns() == null) setBtns(new ArrayList<Btn>());
		getBtns().add(btn);
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

	public java.lang.String getMenuNm(){
		return menuNm;
	}
	public void setMenuNm(java.lang.String menuNm){
		this.menuNm = menuNm;
	}
	public java.lang.String getSyName(){
		return syName;
	}
	public void setSyName(java.lang.String syName){
		this.syName = syName;
	}

}