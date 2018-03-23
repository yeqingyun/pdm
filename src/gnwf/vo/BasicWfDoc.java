package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfDoc {
	public static final String SELF_FIELDS = "WfDoc.DocType,WfDoc.DocId,WfDoc.TaskId,WfDoc.ProjectNo,WfDoc.Status,WfDoc.CreateBy,WfDoc.LastUpd,WfDoc.CateId,WfDoc.FlowId,WfDoc.DeptDocId,WfDoc.WfNo,WfDoc.UriLink,WfDoc.CreateDate,WfDoc.LastUpdDate,WfDoc.DocName,WfDoc.DocVer";

	private java.lang.Integer docId;
	private java.lang.String  fileNo;
	private java.lang.Integer taskId;
	private java.lang.Integer status;
	private java.lang.Integer createBy;
	private java.lang.Integer lastUpd;
	private java.lang.Integer cateId;
	private java.lang.Integer flowId;
	private java.lang.String wfNo;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastUpdDate;
	private java.util.Date startLastUpdDate;
	private java.util.Date endLastUpdDate;
	private java.lang.String docName;
	
	private java.lang.String uriLink;
	private java.lang.Integer deptDocId;
	private java.lang.String docVer;
	
	private java.lang.Integer docType;

	public java.lang.Integer getDocId(){
		return docId;
	}
	public void setDocId(java.lang.Integer docId){
		this.docId = docId;
	}
	public java.lang.Integer getTaskId(){
		return taskId;
	}
	public void setTaskId(java.lang.Integer taskId){
		this.taskId = taskId;
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
	public java.lang.Integer getCateId(){
		return cateId;
	}
	public void setCateId(java.lang.Integer cateId){
		this.cateId = cateId;
	}
	public java.lang.Integer getFlowId(){
		return flowId;
	}
	public void setFlowId(java.lang.Integer flowId){
		this.flowId = flowId;
	}
	public java.lang.Integer getDeptDocId(){
		return deptDocId;
	}
	public void setDeptDocId(java.lang.Integer deptDocId){
		this.deptDocId = deptDocId;
	}
	public java.lang.String getWfNo(){
		return wfNo;
	}
	public void setWfNo(java.lang.String wfNo){
		this.wfNo = wfNo;
	}
	public java.lang.String getUriLink(){
		return uriLink;
	}
	public void setUriLink(java.lang.String uriLink){
		this.uriLink = uriLink;
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
	public java.lang.String getDocName(){
		return docName;
	}
	public void setDocName(java.lang.String docName){
		this.docName = docName;
	}
	public java.lang.String getDocVer(){
		return docVer;
	}
	public void setDocVer(java.lang.String docVer){
		this.docVer = docVer;
	}
	public java.lang.String getFileNo() {
		return fileNo;
	}
	public void setFileNo(java.lang.String fileNo) {
		this.fileNo = fileNo;
	}
	public java.lang.Integer getDocType() {
		return docType;
	}
	public void setDocType(java.lang.Integer docType) {
		this.docType = docType;
	}

}