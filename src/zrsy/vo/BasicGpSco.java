package zrsy.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicGpSco {
	public static final String SELF_FIELDS = "GpSco.GpId,GpSco.ScoId";

	private java.lang.Integer gpId;
	private java.lang.Integer scoId;

	public java.lang.Integer getGpId(){
		return gpId;
	}
	public void setGpId(java.lang.Integer gpId){
		this.gpId = gpId;
	}
	public java.lang.Integer getScoId(){
		return scoId;
	}
	public void setScoId(java.lang.Integer scoId){
		this.scoId = scoId;
	}

}