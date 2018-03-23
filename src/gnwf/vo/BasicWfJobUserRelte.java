package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfJobUserRelte {
	public static final String SELF_FIELDS = "WfJobUserRelte.JobId,WfJobUserRelte.UserId";

	private java.lang.Integer jobId;
	private java.lang.Integer userId;

	public java.lang.Integer getJobId(){
		return jobId;
	}
	public void setJobId(java.lang.Integer jobId){
		this.jobId = jobId;
	}
	public java.lang.Integer getUserId(){
		return userId;
	}
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}

}