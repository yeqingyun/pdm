package zrsy.vo;

public class BasicMenu {
	public static final String SELF_FIELDS = "Menu.Id,Menu.SyId,Menu.Parent,Menu.Width,Menu.Leve,Menu.Sort,Menu.Text,Menu.Click,Menu.Icon";

	private java.lang.Integer id;
	private java.lang.Integer syId;
	private java.lang.Integer parent;
	private java.lang.Integer width;
	private java.lang.Integer leve;
	private java.lang.Integer sort;
	private java.lang.String text;
	private java.lang.String click;
	private java.lang.String icon;

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
	public java.lang.Integer getWidth(){
		return width;
	}
	public void setWidth(java.lang.Integer width){
		this.width = width;
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
	public java.lang.String getClick(){
		return click;
	}
	public void setClick(java.lang.String click){
		this.click = click;
	}
	public java.lang.String getIcon(){
		return icon;
	}
	public void setIcon(java.lang.String icon){
		this.icon = icon;
	}

}