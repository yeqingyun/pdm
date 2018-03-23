package zrprjt.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicSchDtl {
	public static final String SELF_FIELDS = "SchDtl.SchDtlId,SchDtl.SchId,SchDtl.Status,SchDtl.CreateBy,SchDtl.LastUpd,SchDtl.CreateDate,SchDtl.LastDate,SchDtl.DocNm,SchDtl.Remark";

	private java.lang.Integer schDtlId;
	private java.lang.Integer schId;
	private java.lang.Integer status;
	private java.lang.Integer createBy;
	private java.lang.Integer lastUpd;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastDate;
	private java.util.Date startLastDate;
	private java.util.Date endLastDate;
	private java.lang.String docNm;
	private java.lang.String remark;

	public java.lang.Integer getSchDtlId(){
		return schDtlId;
	}
	public void setSchDtlId(java.lang.Integer schDtlId){
		this.schDtlId = schDtlId;
	}
	public java.lang.Integer getSchId(){
		return schId;
	}
	public void setSchId(java.lang.Integer schId){
		this.schId = schId;
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
	public java.lang.String getDocNm(){
		return docNm;
	}
	public void setDocNm(java.lang.String docNm){
		this.docNm = docNm;
	}
	public java.lang.String getRemark(){
		return remark;
	}
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}

}