package gnwf.vo;

public class WfStepUser extends BasicWfStepUser {
	public static final String ALL_FIELDS = "WfStepUser.StepId,WfStepUser.UserId,WfStepUser.UserType,WfStepUser.PrjtRoleId,Usr.UsrName,PrjtRole.RoleNm";
	public static final String LIST_FIELDS = "WfStepUser.StepId,WfStepUser.UserId,WfStepUser.UserType,WfStepUser.PrjtRoleId,Usr.UsrName,PrjtRole.RoleNm";


	private String stepName;
	private String userName;
	private String PrjtRoleName;

	public String getStepName(){
		return stepName;
	}
	public void setStepName(String stepName){
		this.stepName = stepName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPrjtRoleName() {
		return PrjtRoleName;
	}
	public void setPrjtRoleName(String prjtRoleName) {
		PrjtRoleName = prjtRoleName;
	}

}