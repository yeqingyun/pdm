package zrsy.vo;

public class BasicBtn {
	public static final String SELF_FIELDS = "Btn.Id,Btn.SyId,Btn.Line,Btn.Disable,Btn.Sort,Btn.Text,Btn.Click,Btn.Icon,Btn.Img";

	private java.lang.Integer id;
	private java.lang.Integer syId;
	private java.lang.Integer line;
	private java.lang.Integer disable;
	private java.lang.Integer sort;
	private java.lang.String text;
	private java.lang.String click;
	private java.lang.String icon;
	private java.lang.String img;

	public java.lang.String getImg() {
		return img;
	}
	public void setImg(java.lang.String img) {
		this.img = img;
	}
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
	public java.lang.Integer getLine(){
		return line;
	}
	public void setLine(java.lang.Integer line){
		this.line = line;
	}
	public java.lang.Integer getDisable(){
		return disable;
	}
	public void setDisable(java.lang.Integer disable){
		this.disable = disable;
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