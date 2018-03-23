package zrsy.vo;

public class Sco extends BasicSco {
	public static final String ALL_FIELDS = "Sco.CreateDate,Sco.LastDate,Sco.ScoId,Sco.Scope,Sco.Status,Sco.CreateBy,Sco.LastUpd,Sco.ScoNm";
	public static final String LIST_FIELDS = "Sco.CreateDate,Sco.LastDate,Sco.ScoId,Sco.Scope,Sco.Status,Sco.CreateBy,Sco.LastUpd,Sco.ScoNm";

	private java.util.List<zrsy.vo.GpSco> gpScos;
	private java.util.List<zrsy.vo.ScoDtl> scoDtls;
	private String checked;
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}

	public java.util.List<zrsy.vo.GpSco> getGpScos() {
		return gpScos;
	}
	public void setGpScos(java.util.List<zrsy.vo.GpSco> gpScos){
		this.gpScos = gpScos;
	}
	public void addtoGpScos(zrsy.vo.GpSco gpSco){
		if(getGpScos() == null) setGpScos(new java.util.ArrayList<zrsy.vo.GpSco>());
			getGpScos().add(gpSco);
	}
	public java.util.List<zrsy.vo.ScoDtl> getScoDtls() {
		return scoDtls;
	}
	public void setScoDtls(java.util.List<zrsy.vo.ScoDtl> scoDtls){
		this.scoDtls = scoDtls;
	}
	public void addtoScoDtls(zrsy.vo.ScoDtl scoDtl){
		if(getScoDtls() == null) setScoDtls(new java.util.ArrayList<zrsy.vo.ScoDtl>());
			getScoDtls().add(scoDtl);
	}


}