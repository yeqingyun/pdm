package zrsy.vo;

public class AddrBook extends BasicAddrBook {
	public static final String ALL_FIELDS = "Com.comNm,Dept.deptNm,Usr.usrName,AddrBook.BookId,AddrBook.ComId,AddrBook.DeptId,AddrBook.UserId,AddrBook.EmpId,AddrBook.Phone,AddrBook.ExtPhone,AddrBook.Mobile,AddrBook.MailAddr,AddrBook.AddrName,AddrBook.Remark,AddrBook.Status,AddrBook.CreateBy,AddrBook.CreateDate,AddrBook.LastUpd,AddrBook.LastDate,Usr.Id as Id";
	public static final String LIST_FIELDS = "Com.comNm,Dept.deptNm,Usr.usrName,AddrBook.BookId,AddrBook.ComId,AddrBook.DeptId,AddrBook.UserId,AddrBook.EmpId,AddrBook.Phone,AddrBook.ExtPhone,AddrBook.Mobile,AddrBook.MailAddr,AddrBook.AddrName,AddrBook.Remark,AddrBook.Status,AddrBook.CreateBy,AddrBook.CreateDate,AddrBook.LastUpd,AddrBook.LastDate,Usr.Id as Id";
	
	private String comNm;
	private String deptNm;
	private String userNm;

	private Integer id;


	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getComNm() {
		return comNm;
	}
	public void setComNm(String comNm) {
		this.comNm = comNm;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

}