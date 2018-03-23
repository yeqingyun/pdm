package zrsy.vo;

public class GpMenu extends BasicGpMenu {
	public static final String ALL_FIELDS = "GpMenu.GpId,GpMenu.MenuId,Gp.GpName as GpName,Menu.Text as MenuNm";
	public static final String LIST_FIELDS = "GpMenu.GpId,GpMenu.MenuId,Gp.GpName as GpName,Menu.Text as MenuNm";


	private java.lang.String gpName;
	private java.lang.String menuNm;


	public java.lang.String getGpName(){
		return gpName;
	}
	public void setGpName(java.lang.String gpName){
		this.gpName = gpName;
	}
	public java.lang.String getMenuNm(){
		return menuNm;
	}
	public void setMenuNm(java.lang.String menuNm){
		this.menuNm = menuNm;
	}

}