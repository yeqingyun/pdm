package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfQuesDtl {
	public static final String SELF_FIELDS = "WfQuesDtl.QuesDtlId,WfQuesDtl.QuesId,WfQuesDtl.UserId,WfQuesDtl.Status,WfQuesDtl.CreateBy,WfQuesDtl.LastUpd,WfQuesDtl.CreateDate,WfQuesDtl.LastUpdDate,WfQuesDtl.QuesTxt,WfQuesDtl.Title";

	private java.lang.Integer quesDtlId;
	private java.lang.Integer quesId;
	private java.lang.Integer userId;
	private java.lang.Integer status;
	private java.lang.Integer createBy;
	private java.lang.Integer lastUpd;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastUpdDate;
	private java.util.Date startLastUpdDate;
	private java.util.Date endLastUpdDate;
	private java.lang.String quesTxt;
	private java.lang.String title;

	public java.lang.Integer getQuesDtlId(){
		return quesDtlId;
	}
	public void setQuesDtlId(java.lang.Integer quesDtlId){
		this.quesDtlId = quesDtlId;
	}
	public java.lang.Integer getQuesId(){
		return quesId;
	}
	public void setQuesId(java.lang.Integer quesId){
		this.quesId = quesId;
	}
	public java.lang.Integer getUserId(){
		return userId;
	}
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
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
	public java.util.Date getStartLastUpdDate(){
		return startLastUpdDate;
	}
	public void setStartLastUpdDate(java.util.Date startLastUpdDate){
		this.startLastUpdDate = startLastUpdDate;
	}
	public java.util.Date getEndLastUpdDate(){
		return endLastUpdDate;
	}
	public void setEndLastUpdDate(java.util.Date endLastUpdDate){
		this.endLastUpdDate = endLastUpdDate;
	}
	public java.util.Date getLastUpdDate(){
		return lastUpdDate;
	}
	public void setLastUpdDate(java.util.Date lastUpdDate){
		this.lastUpdDate = lastUpdDate;
	}
	public java.lang.String getQuesTxt(){
		return quesTxt;
	}
	public void setQuesTxt(java.lang.String quesTxt){
		this.quesTxt = quesTxt;
	}
	public java.lang.String getTitle(){
		return title;
	}
	public void setTitle(java.lang.String title){
		this.title = title;
	}

}