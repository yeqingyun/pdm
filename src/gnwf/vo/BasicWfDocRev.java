package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfDocRev {
	public static final String SELF_FIELDS = "WfDocRev.DocId,WfDocRev.TaskId,WfDocRev.CreateBy,WfDocRev.WfNo,WfDocRev.CreateDate,WfDocRev.RevText,WfDocRev.RevId";

	private java.lang.Integer docId;
	private java.lang.Integer taskId;
	private java.lang.Integer createBy;
	private java.lang.String wfNo;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	private java.lang.String revText;
	private java.lang.String revId;

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
	public java.lang.Integer getCreateBy(){
		return createBy;
	}
	public void setCreateBy(java.lang.Integer createBy){
		this.createBy = createBy;
	}
	public java.lang.String getWfNo(){
		return wfNo;
	}
	public void setWfNo(java.lang.String wfNo){
		this.wfNo = wfNo;
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
	public java.lang.String getRevText(){
		return revText;
	}
	public void setRevText(java.lang.String revText){
		this.revText = revText;
	}
	public java.lang.String getRevId(){
		return revId;
	}
	public void setRevId(java.lang.String revId){
		this.revId = revId;
	}

}