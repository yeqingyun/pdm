package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfRdSign {
	public static final String SELF_FIELDS = "WfRdSign.TaskId,WfRdSign.UserId,WfRdSign.Status,WfRdSign.WfNoId,WfRdSign.CreateDate,WfRdSign.SignText";

	private java.lang.Integer taskId;
	private java.lang.Integer userId;
	private java.lang.Integer status;
	private java.lang.String wfNoId;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	private java.lang.String signText;

	public java.lang.Integer getTaskId(){
		return taskId;
	}
	public void setTaskId(java.lang.Integer taskId){
		this.taskId = taskId;
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
	public java.lang.String getWfNoId(){
		return wfNoId;
	}
	public void setWfNoId(java.lang.String wfNoId){
		this.wfNoId = wfNoId;
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
	public java.lang.String getSignText(){
		return signText;
	}
	public void setSignText(java.lang.String signText){
		this.signText = signText;
	}

}