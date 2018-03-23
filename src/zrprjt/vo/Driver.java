package zrprjt.vo;

import java.util.List;

public class Driver extends BasicDriver {
	public static final String ALL_FIELDS = "Driver.DriveId,Driver.DriveNo,Driver.DriveNm,Driver.FlowId,Driver.Remark,Driver.Status,Driver.CreateBy,Driver.CreateDate,Driver.LastUpd,Driver.LastDate,WfCfg.FlowName,Driver.StepId";
	public static final String LIST_FIELDS = "Driver.DriveId,Driver.DriveNo,Driver.DriveNm,Driver.FlowId,Driver.Remark,Driver.Status,Driver.CreateBy,Driver.CreateDate,Driver.LastUpd,Driver.LastDate,WfCfg.FlowName,Driver.StepId";

	private String flowName;
	private List<DriverDtl> driverDtls;

	public List<DriverDtl> getDriverDtls() {
		return driverDtls;
	}

	public void setDriverDtls(List<DriverDtl> driverDtls) {
		this.driverDtls = driverDtls;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
}