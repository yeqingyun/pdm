package zrprjt.vo;

public class PrjtUsr extends BasicPrjtUsr {
	public static final String ALL_FIELDS = "Dept.DeptNm as DeptNm,PrjtUsr.Id,PrjtTyp.TypNm as PrjtTypNm,PrjtRole.RoleTyp as RoleTyp,PrjtUsr.PrjtTypId,PrjtUsr.RoleId,PrjtUsr.UsrId,PrjtUsr.Status,PrjtUsr.CreateBy,PrjtUsr.LastUpd,PrjtUsr.CreateDate,PrjtUsr.LastDate,PrjtUsr.PrjtNo,PrjtRole.RoleNm as RoleNm,Usr.UsrName as UsrName,PrjtUsr.Priority";
	public static final String LIST_FIELDS = "Dept.DeptNm as DeptNm, PrjtUsr.Id,PrjtTyp.TypNm as PrjtTypNm,PrjtRole.RoleTyp as RoleTyp,PrjtUsr.PrjtTypId,PrjtUsr.RoleId,PrjtUsr.UsrId,PrjtUsr.Status,PrjtUsr.CreateBy,PrjtUsr.LastUpd,PrjtUsr.CreateDate,PrjtUsr.LastDate,PrjtUsr.PrjtNo,PrjtRole.RoleNm as RoleNm,Usr.UsrName as UsrName,PrjtUsr.Priority";

	public static final String LIST_FIELDS_FOR = " PrjtUsr.Id,PrjtTyp.TypNm as PrjtTypNm,PrjtRole.RoleTyp as RoleTyp,PrjtUsr.PrjtTypId,PrjtUsr.RoleId,PrjtUsr.UsrId,PrjtUsr.Status,PrjtUsr.CreateBy,PrjtUsr.LastUpd,PrjtUsr.CreateDate,PrjtUsr.LastDate,PrjtUsr.PrjtNo,PrjtRole.RoleNm as RoleNm,Usr.UsrName as UsrName,PrjtUsr.Priority";

	public static final String ADDRBOOK_LIST_FIELDS = "_AddrBook.Mobile as Mobile,_AddrBook.MailAddr as MailAddr,PrjtUsr.Id,PrjtTyp.TypNm as PrjtTypNm,PrjtRole.RoleTyp as RoleTyp,PrjtUsr.PrjtTypId,PrjtUsr.RoleId,PrjtUsr.UsrId,PrjtUsr.Status,PrjtUsr.CreateBy,PrjtUsr.LastUpd,PrjtUsr.CreateDate,PrjtUsr.LastDate,PrjtUsr.PrjtNo,PrjtRole.RoleNm as RoleNm,Usr.UsrName as UsrName,PrjtUsr.Priority";

	
	
	private String roleNm;
	private String usrName;
	// private String prjtNm;
	private String prjtTypNm;
	private Integer oldUsrId;
	private Integer oldStatus;
	private java.lang.Integer roleTyp;

	private String DeptNm;
	
	private int stepId; // ����������Id,����ƥ���ɫ���
	
	private String mobile;
	private String mailAddr;

	
	public String getDeptNm() {
		return DeptNm;
	}

	public void setDeptNm(String deptNm) {
		DeptNm = deptNm;
	}

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

	public java.lang.Integer getRoleTyp() {
		return roleTyp;
	}

	public void setRoleTyp(java.lang.Integer roleTyp) {
		this.roleTyp = roleTyp;
	}

	public String getPrjtTypNm() {
		return prjtTypNm;
	}

	public void setPrjtTypNm(String prjtTypNm) {
		this.prjtTypNm = prjtTypNm;
	}

	public Integer getOldUsrId() {
		return oldUsrId;
	}

	public void setOldUsrId(Integer oldUsrId) {
		this.oldUsrId = oldUsrId;
	}

	public Integer getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(Integer oldStatus) {
		this.oldStatus = oldStatus;
	}

	public int getStepId() {
		return stepId;
	}

	public void setStepId(int stepId) {
		this.stepId = stepId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMailAddr() {
		return mailAddr;
	}

	public void setMailAddr(String mailAddr) {
		this.mailAddr = mailAddr;
	}

	// public String getPrjtNm() {
	// return prjtNm;
	// }
	//
	//
	// public void setPrjtNm(String prjtNm) {
	// this.prjtNm = prjtNm;
	// }

}