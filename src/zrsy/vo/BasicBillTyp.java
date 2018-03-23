package zrsy.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicBillTyp {
	public static final String SELF_FIELDS = "BillTyp.Id,BillTyp.SubsId,BillTyp.TypNm,BillTyp.Code";

	private java.lang.Integer id;
	private java.lang.Integer subsId;
	private java.lang.String typNm;
	private java.lang.String code;

	public java.lang.Integer getId(){
		return id;
	}
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	public java.lang.Integer getSubsId(){
		return subsId;
	}
	public void setSubsId(java.lang.Integer subsId){
		this.subsId = subsId;
	}
	public java.lang.String getTypNm(){
		return typNm;
	}
	public void setTypNm(java.lang.String typNm){
		this.typNm = typNm;
	}
	public java.lang.String getCode(){
		return code;
	}
	public void setCode(java.lang.String code){
		this.code = code;
	}

}