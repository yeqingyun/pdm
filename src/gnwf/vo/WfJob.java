package gnwf.vo;

import java.util.List;

public class WfJob extends BasicWfJob {
	public static final String ALL_FIELDS = "WfJob.JobId,WfJob.FlowId,WfJob.IsUpdOrLoad,WfJob.AnnexUpdOrLoad,WfCfg.FlowName as FlowName";
	public static final String LIST_FIELDS = "WfJob.JobId,WfJob.FlowId,WfJob.IsUpdOrLoad,WfJob.AnnexUpdOrLoad,WfCfg.FlowName as FlowName";

	private List<WfJobUser> wfJobUsers;
	private List<WfJob> wfJobs;
	private String checked;
	private String flowName;

	public String getFlowName(){
		return flowName;
	}
	public void setFlowName(String flowName){
		this.flowName = flowName;
	}
	public List<WfJobUser> getWfJobUsers() {
		return wfJobUsers;
	}
	public void setWfJobUsers(List<WfJobUser> wfJobUsers) {
		this.wfJobUsers = wfJobUsers;
	}
	public List<WfJob> getWfJobs() {
		return wfJobs;
	}
	public void setWfJobs(List<WfJob> wfJobs) {
		this.wfJobs = wfJobs;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	
}