package zrsy.vo;

public class Dept extends BasicDept {
	public static final String ALL_FIELDS = "Dept.DeptId,Dept.Parent,Dept.Leve,Dept.Status,Dept.CreateBy,Dept.LastUpd,Dept.CreateDate,Dept.LastDate,Dept.DeptNo,Dept.DeptNm,Dept.Remark,Com.ComNm as ComNm";
	public static final String LIST_FIELDS = "Dept.DeptId,Dept.Parent,Dept.Leve,Dept.Status,Dept.CreateBy,Dept.LastUpd,Dept.CreateDate,Dept.LastDate,Dept.DeptNo,Dept.DeptNm,Dept.Remark,Com.ComNm as ComNm";
	public static final String FIELDS ="Dept.DeptId,Dept.Parent,Dept.Leve,Dept.Status,Dept.CreateBy,Dept.LastUpd,Dept.CreateDate,Dept.LastDate,Dept.DeptNo,Dept.DeptNm,Dept.Remark";
	private java.util.List<zrsy.vo.Usr> usrs;

	private java.lang.String comNm;
	private String checked;
	private String ischecked;
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public java.util.List<zrsy.vo.Usr> getUsrs() {
		return usrs;
	}
	public void setUsrs(java.util.List<zrsy.vo.Usr> usrs){
		this.usrs = usrs;
	}
	public void addtoUsrs(zrsy.vo.Usr usr){
		if(getUsrs() == null) setUsrs(new java.util.ArrayList<zrsy.vo.Usr>());
			getUsrs().add(usr);
	}

	public java.lang.String getComNm(){
		return comNm;
	}
	public void setComNm(java.lang.String comNm){
		this.comNm = comNm;
	}
	public String getIschecked() {
		return ischecked;
	}
	public void setIschecked(String ischecked) {
		this.ischecked = ischecked;
	}

}