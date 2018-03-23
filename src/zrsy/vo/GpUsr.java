package zrsy.vo;

public class GpUsr extends BasicGpUsr {
	public static final String ALL_FIELDS = "GpUsr.GpId,GpUsr.UsrId,Gp.GpName as GpName,Usr.UsrName as UsrName";
	public static final String LIST_FIELDS = "GpUsr.GpId,GpUsr.UsrId,Gp.GpName as GpName,Usr.UsrName as UsrName";


	private java.lang.String gpName;
	private java.lang.String usrName;


	public java.lang.String getGpName(){
		return gpName;
	}
	public void setGpName(java.lang.String gpName){
		this.gpName = gpName;
	}
	public java.lang.String getUsrName(){
		return usrName;
	}
	public void setUsrName(java.lang.String usrName){
		this.usrName = usrName;
	}

}