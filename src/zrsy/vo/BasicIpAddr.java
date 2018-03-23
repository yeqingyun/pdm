package zrsy.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicIpAddr {
	public static final String SELF_FIELDS = "IpAddr.CreateDate,IpAddr.LastDate,IpAddr.IpId,IpAddr.IsWide,IpAddr.Status,IpAddr.CreateBy,IpAddr.LastUpd,IpAddr.IpSegment,IpAddr.AddrDesc";

	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastDate;
	private java.util.Date startLastDate;
	private java.util.Date endLastDate;
	private java.lang.Integer ipId;
	private java.lang.Integer isWide;
	private java.lang.Integer status;
	private java.lang.Integer createBy;
	private java.lang.Integer lastUpd;
	private java.lang.String ipSegment;
	private java.lang.String addrDesc;

	public java.util.Date getStartCreateDate(){
		return startCreateDate;
	}
	public void setStartCreateDate(java.util.Date startCreateDate){
		this.startCreateDate = startCreateDate;
	}
	public java.util.Date getEndCreateDate(){
		return endCreateDate;
	}
	public void setEndCreateDate(java.util.Date endCreateDate){
		this.endCreateDate = endCreateDate;
	}
	public java.util.Date getCreateDate(){
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	public java.util.Date getStartLastDate(){
		return startLastDate;
	}
	public void setStartLastDate(java.util.Date startLastDate){
		this.startLastDate = startLastDate;
	}
	public java.util.Date getEndLastDate(){
		return endLastDate;
	}
	public void setEndLastDate(java.util.Date endLastDate){
		this.endLastDate = endLastDate;
	}
	public java.util.Date getLastDate(){
		return lastDate;
	}
	public void setLastDate(java.util.Date lastDate){
		this.lastDate = lastDate;
	}
	public java.lang.Integer getIpId(){
		return ipId;
	}
	public void setIpId(java.lang.Integer ipId){
		this.ipId = ipId;
	}
	public java.lang.Integer getIsWide(){
		return isWide;
	}
	public void setIsWide(java.lang.Integer isWide){
		this.isWide = isWide;
	}
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
	public java.lang.Integer getCreateBy(){
		return createBy;
	}
	public void setCreateBy(java.lang.Integer createBy){
		this.createBy = createBy;
	}
	public java.lang.Integer getLastUpd(){
		return lastUpd;
	}
	public void setLastUpd(java.lang.Integer lastUpd){
		this.lastUpd = lastUpd;
	}
	public java.lang.String getIpSegment(){
		return ipSegment;
	}
	public void setIpSegment(java.lang.String ipSegment){
		this.ipSegment = ipSegment;
	}
	public java.lang.String getAddrDesc(){
		return addrDesc;
	}
	public void setAddrDesc(java.lang.String addrDesc){
		this.addrDesc = addrDesc;
	}

}