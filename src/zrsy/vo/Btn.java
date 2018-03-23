package zrsy.vo;

public class Btn extends BasicBtn {
	public static final String ALL_FIELDS = "Btn.Id,Btn.SyId,Btn.Line,Btn.Disable,Btn.Sort,Btn.Text,Btn.Click,Btn.Icon,Btn.Img";
	public static final String LIST_FIELDS = "Btn.Id,Btn.SyId,Btn.Line,Btn.Disable,Btn.Sort,Btn.Text,Btn.Click,Btn.Icon,Btn.Img";

	private java.util.List<zrsy.vo.GpBtn> gpBtns;
	
	private String checked;

	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}

	public java.util.List<zrsy.vo.GpBtn> getGpBtns() {
		return gpBtns;
	}
	public void setGpBtns(java.util.List<zrsy.vo.GpBtn> gpBtns){
		this.gpBtns = gpBtns;
	}
	public void addtoGpBtns(zrsy.vo.GpBtn gpBtn){
		if(getGpBtns() == null) setGpBtns(new java.util.ArrayList<zrsy.vo.GpBtn>());
			getGpBtns().add(gpBtn);
	}


}