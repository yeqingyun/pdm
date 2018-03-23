package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfDept {
	public static final String SELF_FIELDS = "WfDept.FlowId,WfDept.DeptId";

	private java.lang.Integer flowId;
	private java.lang.Integer deptId;

	public java.lang.Integer getFlowId(){
		return flowId;
	}
	public void setFlowId(java.lang.Integer flowId){
		this.flowId = flowId;
	}
	public java.lang.Integer getDeptId(){
		return deptId;
	}
	public void setDeptId(java.lang.Integer deptId){
		this.deptId = deptId;
	}

}