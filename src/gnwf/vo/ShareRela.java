package gnwf.vo;

public class ShareRela extends BasicShareRela {
	public static final String ALL_FIELDS = "ShareRela.WfDocId,ShareRela.UsrId,ShareRela.Remark,ShareRela.Status,ShareRela.CreateBy,ShareRela.CreateDate,ShareRela.LastUpd,ShareRela.LastDate";
	public static final String LIST_FIELDS = "ShareRela.WfDocId,ShareRela.UsrId,ShareRela.Remark,ShareRela.Status,ShareRela.CreateBy,ShareRela.CreateDate,ShareRela.LastUpd,ShareRela.LastDate";
	public static final String LIST_FIELDS_WF = "ShareRela.WfDocId,ShareRela.UsrId,ShareRela.Remark,ShareRela.Status,ShareRela.CreateBy,ShareRela.CreateDate,ShareRela.LastUpd,ShareRela.LastDate,UsrName";

	private String usrName;
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	
}