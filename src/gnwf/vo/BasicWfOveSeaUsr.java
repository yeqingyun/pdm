package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfOveSeaUsr {
	public static final String SELF_FIELDS = " WfOveSeaUsr.WfUsrID , WfOveSeaUsr.WfNo , WfOveSeaUsr.FlowID , WfOveSeaUsr.CreateBy , WfOveSeaUsr.CreateDate";
	
	private java.lang.Integer wfUsrID;
	private java.lang.String wfNo;
	private java.lang.Integer flowID;
	private java.lang.Integer createBy;

	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;

	

	public java.lang.Integer getWfUsrID() {
		return wfUsrID;
	}

	public void setWfUsrID(java.lang.Integer wfUsrID) {
		this.wfUsrID = wfUsrID;
	}

	public java.lang.String getWfNo() {
		return wfNo;
	}

	public void setWfNo(java.lang.String wfNo) {
		this.wfNo = wfNo;
	}

	public java.lang.Integer getFlowID() {
		return flowID;
	}

	public void setFlowID(java.lang.Integer flowID) {
		this.flowID = flowID;
	}

	public java.lang.Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(java.lang.Integer createBy) {
		this.createBy = createBy;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	
	
	
}