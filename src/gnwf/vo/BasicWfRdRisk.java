package gnwf.vo;

import java.io.Serializable;

public class BasicWfRdRisk implements Serializable {


	private static final long serialVersionUID = 3044164812447938983L;
	
	public static final String SELF_FIELDS = "WfRdRisk.WfNo,WfRdRisk.RiskId";
	/** 流程编号 **/
	private String wfNo;
	/** 风险编号 **/
	private String riskId;
	
	public String getWfNo() {
		return wfNo;
	}
	public void setWfNo(String wfNo) {
		this.wfNo = wfNo;
	}
	public String getRiskId() {
		return riskId;
	}
	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((riskId == null) ? 0 : riskId.hashCode());
		result = prime * result + ((wfNo == null) ? 0 : wfNo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasicWfRdRisk other = (BasicWfRdRisk) obj;
		if (riskId == null) {
			if (other.riskId != null)
				return false;
		} else if (!riskId.equals(other.riskId))
			return false;
		if (wfNo == null) {
			if (other.wfNo != null)
				return false;
		} else if (!wfNo.equals(other.wfNo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BasicWfRdRisk [wfNo=" + wfNo + ", riskId=" + riskId + "]";
	}

}
