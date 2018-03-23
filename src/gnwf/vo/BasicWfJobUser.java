package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfJobUser {
	public static final String SELF_FIELDS = "WfJobUser.UserId,WfJobUser.JobId";

	private java.lang.Integer userId;
	private java.lang.Integer jobId;

	public java.lang.Integer getUserId(){
		return userId;
	}
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}
	public java.lang.Integer getJobId(){
		return jobId;
	}
	public void setJobId(java.lang.Integer jobId){
		this.jobId = jobId;
	}

}