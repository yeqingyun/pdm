package zrsy.vo;

public class GpBtn extends BasicGpBtn {
	public static final String ALL_FIELDS = "GpBtn.GpId,GpBtn.NodeId,GpBtn.BtnId,Btn.Text as BtnNm,Gp.GpName as GpName";
	public static final String LIST_FIELDS = "GpBtn.GpId,GpBtn.NodeId,GpBtn.BtnId,Btn.Text as BtnNm,Gp.GpName as GpName";


	private java.lang.String btnNm;
	private java.lang.String gpName;

	private String nodeBtnId;

	public String getNodeBtnId() {
		return nodeBtnId;
	}
	public void setNodeBtnId(String nodeBtnId) {
		this.nodeBtnId = nodeBtnId;
	}
	
	public java.lang.String getBtnNm(){
		return btnNm;
	}
	public void setBtnNm(java.lang.String btnNm){
		this.btnNm = btnNm;
	}
	public java.lang.String getGpName(){
		return gpName;
	}
	public void setGpName(java.lang.String gpName){
		this.gpName = gpName;
	}

}