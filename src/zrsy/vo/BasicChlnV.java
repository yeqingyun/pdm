package zrsy.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicChlnV {
	public static final String SELF_FIELDS = "ChlnV.FileNm,ChlnV.ChlnNo,ChlnV.CreateBy,ChlnV.CreateDate,ChlnV.LastUpd,ChlnV.LastDate,ChlnV.ChlnTyp";

	private java.lang.String fileNm;
	private java.lang.Integer chlnNo;
	private java.lang.Integer createBy;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	private java.lang.Integer lastUpd;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastDate;
	private java.util.Date startLastDate;
	private java.util.Date endLastDate;
	private java.lang.String chlnTyp;

	public java.lang.String getFileNm(){
		return fileNm;
	}
	public void setFileNm(java.lang.String fileNm){
		this.fileNm = fileNm;
	}
	public java.lang.Integer getChlnNo(){
		return chlnNo;
	}
	public void setChlnNo(java.lang.Integer chlnNo){
		this.chlnNo = chlnNo;
	}
	public java.lang.Integer getCreateBy(){
		return createBy;
	}
	public void setCreateBy(java.lang.Integer createBy){
		this.createBy = createBy;
	}
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
	public java.lang.Integer getLastUpd(){
		return lastUpd;
	}
	public void setLastUpd(java.lang.Integer lastUpd){
		this.lastUpd = lastUpd;
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
	public java.lang.String getChlnTyp(){
		return chlnTyp;
	}
	public void setChlnTyp(java.lang.String chlnTyp){
		this.chlnTyp = chlnTyp;
	}

}