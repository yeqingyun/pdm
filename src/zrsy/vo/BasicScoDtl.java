package zrsy.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicScoDtl {
	public static final String SELF_FIELDS = "ScoDtl.ScoId,ScoDtl.ComId,ScoDtl.DetpId,ScoDtl.UsrId";

	private java.lang.Integer scoId;
	private java.lang.Integer comId;
	private java.lang.Integer detpId;
	private java.lang.Integer usrId;

	public java.lang.Integer getScoId(){
		return scoId;
	}
	public void setScoId(java.lang.Integer scoId){
		this.scoId = scoId;
	}
	public java.lang.Integer getComId(){
		return comId;
	}
	public void setComId(java.lang.Integer comId){
		this.comId = comId;
	}
	public java.lang.Integer getDetpId(){
		return detpId;
	}
	public void setDetpId(java.lang.Integer detpId){
		this.detpId = detpId;
	}
	public java.lang.Integer getUsrId(){
		return usrId;
	}
	public void setUsrId(java.lang.Integer usrId){
		this.usrId = usrId;
	}

}