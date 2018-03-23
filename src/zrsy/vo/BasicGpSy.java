package zrsy.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicGpSy {
	public static final String SELF_FIELDS = "GpSy.GpId,GpSy.SyId";

	private java.lang.Integer gpId;
	private java.lang.Integer syId;

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

}