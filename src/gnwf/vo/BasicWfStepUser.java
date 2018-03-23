package gnwf.vo;

public class BasicWfStepUser {
	public static final String SELF_FIELDS = "WfStepUser.StepId,WfStepUser.UserId,WfStepUser.UserType,WfStepUser.PrjtRoleId";

	private java.lang.Integer stepId;
	private java.lang.Integer userId;			//可选用户ID，当有项目角色时，可为空
	private java.lang.Integer userType;			//是否主办
	private java.lang.Integer prjtRoleId;		//项目角色ID，当有可选用户时，可为空

	public java.lang.Integer getStepId(){
		return stepId;
	}
	public void setStepId(java.lang.Integer stepId){
		this.stepId = stepId;
	}
	public java.lang.Integer getUserId(){
		return userId;
	}
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}
	public java.lang.Integer getUserType(){
		return userType;
	}
	public void setUserType(java.lang.Integer userType){
		this.userType = userType;
	}
	public java.lang.Integer getPrjtRoleId() {
		return prjtRoleId;
	}
	public void setPrjtRoleId(java.lang.Integer prjtRoleId) {
		this.prjtRoleId = prjtRoleId;
	}

}