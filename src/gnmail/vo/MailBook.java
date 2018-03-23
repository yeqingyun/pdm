package gnmail.vo;

public class MailBook extends BasicMailBook {
	public static final String ALL_FIELDS = "MailBook.BookId,MailBook.UserId,MailBook.GroupId,MailBook.ExtPhone,MailBook.Phone,MailBook.Mobile,MailBook.MailAddr,MailBook.AddrName,MailBook.Remark,MailBook.Status,MailBook.CreateBy,MailBook.CreateDate,MailBook.LastUpd,MailBook.LastUpdDate,MailGroup.GroupId as GroupId";
	public static final String LIST_FIELDS = "MailBook.BookId,MailBook.UserId,MailBook.GroupId,MailBook.ExtPhone,MailBook.Phone,MailBook.Mobile,MailBook.MailAddr,MailBook.AddrName,MailBook.Remark,MailBook.Status,MailBook.CreateBy,MailBook.CreateDate,MailBook.LastUpd,MailBook.LastUpdDate,Com.ComNm,Dept.DeptNm,Usr.UsrName";


	private Integer groupId;
	
	private Integer comId;
	private Integer deptId;
	private String comName;
	private String deptName;
	private String empName;
	private String userName;

	public Integer getGroupId(){
		return groupId;
	}
	public void setGroupId(Integer groupId){
		this.groupId = groupId;
	}
	public Integer getComId() {
		return comId;
	}
	public void setComId(Integer comId) {
		this.comId = comId;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}