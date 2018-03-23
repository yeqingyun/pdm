package zrsy.vo;

public class BasicGp {
	public static final String SELF_FIELDS = "Gp.GpId,Gp.SyId,Gp.GpName,Gp.Remark";

	private java.lang.Integer gpId;
	private java.lang.Integer syId;
	private java.lang.String gpName;
	private java.lang.String remark;

	public java.lang.Integer getGpId(){
		return gpId;
	}
	public void setGpId(java.lang.Integer gpId){
		this.gpId = gpId;
	}
	public java.lang.Integer getSyId(){
		return syId;
	}
	public void setSyId(java.lang.Integer syId){
		this.syId = syId;
	}
	public java.lang.String getGpName(){
		return gpName;
	}
	public void setGpName(java.lang.String gpName){
		this.gpName = gpName;
	}
	public java.lang.String getRemark(){
		return remark;
	}
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}

}