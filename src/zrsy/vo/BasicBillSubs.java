package zrsy.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicBillSubs {
	public static final String SELF_FIELDS = "BillSubs.Id,BillSubs.SyId,BillSubs.Subs,BillSubs.CurrSn,BillSubs.State";

	private java.lang.Integer id;
	private java.lang.Integer syId;
	private java.lang.String subs;
	private java.lang.String currSn;
	private java.lang.Integer state;

	public java.lang.Integer getId(){
		return id;
	}
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	public java.lang.Integer getSyId(){
		return syId;
	}
	public void setSyId(java.lang.Integer syId){
		this.syId = syId;
	}
	public java.lang.String getSubs(){
		return subs;
	}
	public void setSubs(java.lang.String subs){
		this.subs = subs;
	}
	public java.lang.String getCurrSn(){
		return currSn;
	}
	public void setCurrSn(java.lang.String currSn){
		this.currSn = currSn;
	}
	public java.lang.Integer getState(){
		return state;
	}
	public void setState(java.lang.Integer state){
		this.state = state;
	}

}