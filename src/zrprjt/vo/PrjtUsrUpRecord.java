package zrprjt.vo;

public class PrjtUsrUpRecord extends BasicPrjtUsrUpRecord {
	public static final String ALL_FIELDS = "PrjtUsrUpRecord.Id,PrjtUsrUpRecord.PrjtUsrId,PrjtUsrUpRecord.UsrId,PrjtUsrUpRecord.CreateBy,PrjtUsrUpRecord.CreateDate,PrjtUsrUpRecord.UpTyp,PrjtRole.RoleNm as RoleNm,Usr.UsrName as UsrName,PrjtUsr.Priority as Priority";

	public static final String LIST_FIELDS = "PrjtUsrUpRecord.Id,PrjtUsrUpRecord.PrjtUsrId,PrjtUsrUpRecord.UsrId,PrjtUsrUpRecord.CreateBy,PrjtUsrUpRecord.CreateDate,PrjtUsrUpRecord.UpTyp,PrjtRole.RoleNm as RoleNm,Usr.UsrName as UsrName,PrjtUsr.Priority as Priority";


private String  roleNm;
private String  usrName;
private Integer Priority;



public String getRoleNm() {
	return roleNm;
}


public void setRoleNm(String roleNm) {
	this.roleNm = roleNm;
}


public String getUsrName() {
	return usrName;
}


public void setUsrName(String usrName) {
	this.usrName = usrName;
}


public Integer getPriority() {
	return Priority;
}


public void setPriority(Integer priority) {
	Priority = priority;
}


//public String getPrjtNm() {
//	return prjtNm;
//}
//
//
//public void setPrjtNm(String prjtNm) {
//	this.prjtNm = prjtNm;
//}






}