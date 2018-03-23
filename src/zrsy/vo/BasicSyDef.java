package zrsy.vo;

public class BasicSyDef {
	public static final String SELF_FIELDS = "SyDef.SyId,SyDef.SyName,SyDef.SyText";

	private java.lang.Integer syId;
	private java.lang.String syName;
	private java.lang.String syText;

	public java.lang.Integer getSyId(){
		return syId;
	}
	public void setSyId(java.lang.Integer syId){
		this.syId = syId;
	}
	public java.lang.String getSyName(){
		return syName;
	}
	public void setSyName(java.lang.String syName){
		this.syName = syName;
	}
	public java.lang.String getSyText(){
		return syText;
	}
	public void setSyText(java.lang.String syText){
		this.syText = syText;
	}

}