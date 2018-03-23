package zrsy.vo;

public class Com extends BasicCom {
	public static final String ALL_FIELDS = "Com.ComId,Com.Parent,Com.Leve,Com.Status,Com.CreateBy,Com.LastUpd,Com.CreateDate,Com.LastDate,Com.ComNo,Com.ComNm,Com.Remark";
	public static final String LIST_FIELDS = "Com.ComId,Com.Parent,Com.Leve,Com.Status,Com.CreateBy,Com.LastUpd,Com.CreateDate,Com.LastDate,Com.ComNo,Com.ComNm,Com.Remark";

	private java.util.List<zrsy.vo.Dept> depts;
	private java.util.List<zrsy.vo.Usr> usrs;
	private String checked;
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}

	public java.util.List<zrsy.vo.Dept> getDepts() {
		return depts;
	}
	public void setDepts(java.util.List<zrsy.vo.Dept> depts){
		this.depts = depts;
	}
	public void addtoDepts(zrsy.vo.Dept dept){
		if(getDepts() == null) setDepts(new java.util.ArrayList<zrsy.vo.Dept>());
			getDepts().add(dept);
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


}