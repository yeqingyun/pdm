package gnwf.vo;

public class WfLeader extends BasicWfLeader {
	public static final String ALL_FIELDS = "WfLeader.FlowId,WfLeader.UserId,Usr.UsrName";
	public static final String LIST_FIELDS = "WfLeader.FlowId,WfLeader.UserId,Usr.UsrName";


	private String userName;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}