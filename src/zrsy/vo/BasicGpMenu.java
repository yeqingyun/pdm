package zrsy.vo;

public class BasicGpMenu {
	public static final String SELF_FIELDS = "GpMenu.GpId,GpMenu.MenuId";

	private java.lang.Integer gpId;
	private java.lang.Integer menuId;

	public java.lang.Integer getGpId(){
		return gpId;
	}
	public void setGpId(java.lang.Integer gpId){
		this.gpId = gpId;
	}
	public java.lang.Integer getMenuId(){
		return menuId;
	}
	public void setMenuId(java.lang.Integer menuId){
		this.menuId = menuId;
	}

}