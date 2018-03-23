package zrprjt.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicPrjtUsrUpRecord {
	public static final String SELF_FIELDS = "PrjtUsrUpRecord.Id,PrjtUsrUpRecord.PrjtUsrId,PrjtUsrUpRecord.UsrId,PrjtUsrUpRecord.CreateBy,PrjtUsrUpRecord.createDate,PrjtUsrUpRecord.upTyp";

	private java.lang.Integer id;
	private java.lang.Integer prjtUsrId;
	private java.lang.Integer usrId;
	private java.lang.Integer createBy;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createDate;
	private java.lang.Integer upTyp;

	public java.lang.Integer getId(){
		return id;
	}
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	public java.lang.Integer getPrjtUsrId() {
		return prjtUsrId;
	}
	public void setPrjtUsrId(java.lang.Integer prjtUsrId) {
		this.prjtUsrId = prjtUsrId;
	}
	public java.lang.Integer getUsrId() {
		return usrId;
	}
	public void setUsrId(java.lang.Integer usrId) {
		this.usrId = usrId;
	}
	public java.lang.Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(java.lang.Integer createBy) {
		this.createBy = createBy;
	}
	public java.lang.Integer getUpTyp() {
		return upTyp;
	}
	public void setUpTyp(java.lang.Integer upTyp) {
		this.upTyp = upTyp;
	}
	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

}