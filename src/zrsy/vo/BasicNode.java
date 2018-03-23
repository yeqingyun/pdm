package zrsy.vo;

public class BasicNode {
	public static final String SELF_FIELDS = "Node.Id,Node.SyId,Node.Parent,Node.MenuId,Node.NodeWidth,Node.CheckBox,Node.Leve,Node.Sort,Node.Text,Node.Url";

	private java.lang.Integer id;
	private java.lang.Integer syId;
	private java.lang.Integer parent;
	private java.lang.Integer menuId;
	private java.lang.Integer nodeWidth;
	private java.lang.Integer checkBox;
	private java.lang.Integer leve;
	private java.lang.Integer sort;
	private java.lang.String text;
	private java.lang.String url;

	public java.lang.Integer getId(){
		return id;
	}
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	public java.lang.Integer getSyId(){
		return syId;
	}
	public void setSyId(java.lang.Integer syId){
		this.syId = syId;
	}
	public java.lang.Integer getParent(){
		return parent;
	}
	public void setParent(java.lang.Integer parent){
		this.parent = parent;
	}
	public java.lang.Integer getMenuId(){
		return menuId;
	}
	public void setMenuId(java.lang.Integer menuId){
		this.menuId = menuId;
	}
	public java.lang.Integer getNodeWidth(){
		return nodeWidth;
	}
	public void setNodeWidth(java.lang.Integer nodeWidth){
		this.nodeWidth = nodeWidth;
	}
	public java.lang.Integer getCheckBox(){
		return checkBox;
	}
	public void setCheckBox(java.lang.Integer checkBox){
		this.checkBox = checkBox;
	}
	public java.lang.Integer getLeve(){
		return leve;
	}
	public void setLeve(java.lang.Integer leve){
		this.leve = leve;
	}
	public java.lang.Integer getSort(){
		return sort;
	}
	public void setSort(java.lang.Integer sort){
		this.sort = sort;
	}
	public java.lang.String getText(){
		return text;
	}
	public void setText(java.lang.String text){
		this.text = text;
	}
	public java.lang.String getUrl(){
		return url;
	}
	public void setUrl(java.lang.String url){
		this.url = url;
	}

}