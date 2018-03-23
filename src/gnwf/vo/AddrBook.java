package gnwf.vo;

@SuppressWarnings("serial")

public class AddrBook extends BasicAddrBook {

	public AddrBook() {}
 
	public static final String ALL_FIELDS = "AddrBook.BookId,AddrBook.ComId,AddrBook.DeptId,AddrBook.EmpId,AddrBook.Phone,AddrBook.ExtPhone,AddrBook.Mobile,AddrBook.MailAddr,AddrBook.AddrName,AddrBook.Remark,AddrBook.Status,AddrBook.CreateBy,AddrBook.CreateDate,AddrBook.LastUpd,AddrBook.LastUpdDate,BaCom.ComName as ComName,BaDept.DeptName as DeptName,HrEmp.EmpName as EmpName,SyUser.UserId as UserId,SyUser.UserName as UserName";
	public static final String LIST_FIELDS = "AddrBook.BookId,AddrBook.ComId,AddrBook.DeptId,AddrBook.EmpId,AddrBook.Phone,AddrBook.ExtPhone,AddrBook.Mobile,AddrBook.MailAddr,AddrBook.AddrName,AddrBook.Remark,AddrBook.Status,AddrBook.CreateBy,AddrBook.CreateDate,AddrBook.LastUpd,AddrBook.LastUpdDate,BaCom.ComName as ComName,BaDept.DeptName as DeptName,HrEmp.EmpName as EmpName,SyUser.UserId as UserId,SyUser.UserName as UserName";
	public static final String XLS_FIELDS = "AddrBook.BookId,AddrBook.ComId,AddrBook.DeptId,AddrBook.EmpId,AddrBook.Phone,AddrBook.ExtPhone,AddrBook.Mobile,AddrBook.MailAddr,AddrBook.AddrName,AddrBook.Remark,AddrBook.Status,AddrBook.CreateBy,AddrBook.CreateDate,AddrBook.LastUpd,AddrBook.LastUpdDate,BaCom.ComName as ComName,BaDept.DeptName as DeptName,HrEmp.EmpName as EmpName,SyUser.UserId as UserId,SyUser.UserName as UserName";
	
	//BaCom.ComName as ComName,BaDept.DeptName as DeptName,HrEmp.EmpName as EmpName
	private String comName;
	private String deptName;
	private String empName;
	private String userName;

	private Integer userId;

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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
