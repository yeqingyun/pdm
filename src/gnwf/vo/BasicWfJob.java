package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfJob {
	public static final String SELF_FIELDS = "WfJob.JobId,WfJob.FlowId,WfJob.IsUpdOrLoad,WfJob.AnnexUpdOrLoad";

	private java.lang.Integer jobId;
	private java.lang.Integer flowId;
	private java.lang.Integer isUpdOrLoad;
	private java.lang.String annexUpdOrLoad;

	public java.lang.Integer getJobId(){
		return jobId;
	}
	public void setJobId(java.lang.Integer jobId){
		this.jobId = jobId;
	}
	public java.lang.Integer getFlowId(){
		return flowId;
	}
	public void setFlowId(java.lang.Integer flowId){
		this.flowId = flowId;
	}
	public java.lang.Integer getIsUpdOrLoad(){
		return isUpdOrLoad;
	}
	public void setIsUpdOrLoad(java.lang.Integer isUpdOrLoad){
		this.isUpdOrLoad = isUpdOrLoad;
	}
	public java.lang.String getAnnexUpdOrLoad(){
		return annexUpdOrLoad;
	}
	public void setAnnexUpdOrLoad(java.lang.String annexUpdOrLoad){
		this.annexUpdOrLoad = annexUpdOrLoad;
	}

}