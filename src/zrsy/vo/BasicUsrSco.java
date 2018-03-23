package zrsy.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicUsrSco {
	public static final String SELF_FIELDS = "UsrSco.UsrId,UsrSco.ScoId";

	private java.lang.Integer usrId;
	private java.lang.Integer scoId;

	public java.lang.Integer getUsrId(){
		return usrId;
	}
	public void setUsrId(java.lang.Integer usrId){
		this.usrId = usrId;
	}
	public java.lang.Integer getScoId(){
		return scoId;
	}
	public void setScoId(java.lang.Integer scoId){
		this.scoId = scoId;
	}

}