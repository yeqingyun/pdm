package gnwf.vo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfRiskReply implements Serializable {

	private static final long serialVersionUID = -731360293404967601L;
	
	public static final String SELF_FIELDS = "WfRiskReply.Id,WfRiskReply.ResponsibleUserId,WfRiskReply.Measures,WfRiskReply.MeasuresFileNo,"
									         + "WfRiskReply.MeasuresFileName,WfRiskReply.MeasuresDate,WfRiskReply.Status,WfRiskReply.VerifyUserId,"
									         + "WfRiskReply.VerifyResult,WfRiskReply.VerifyDate,WfRiskReply.VerifyFileNo,"
									         + "WfRiskReply.VerifyFileName,WfRiskReply.CreateUserId,WfRiskReply.CreateDate,"
									         + "WfRiskReply.LastUpdate,WfRiskReply.LastUpdateUserId,WfRiskReply.Remark,WfRiskReply.RiskId,WfRiskReply.RushDate";
	
	
	/** 风险回复ID **/
	private Integer id;
	/** 责任人ID **/
	private Integer responsibleUserId;
	/** 解决措施 **/
	private String measures;
	/** 措施附件编号 **/
	private String measuresFileNo;
	/** 措施附件名称  **/
	private String measuresFileName;
	/** 给出措施的时间 **/
	@JSONField(format="yyyy-MM-dd")
	private Date measuresDate;
	/** 状态  **/
	private Integer status;
	/** 验证人ID  **/
	private Integer verifyUserId;
	/** 验证结果 **/
	private String verifyResult;
	/** 验证时间  **/
	@JSONField(format="yyyy-MM-dd")
	private Date verifyDate;
	/** 验证附件编号 **/
	private String verifyFileNo;
	/** 验证附件名称  **/
	private String verifyFileName;
	/** 创建人ＩＤ**/
	private Integer createUserId;
	/** 创建时间　 **/
	@JSONField(format="yyyy-MM-dd")
	private Date createDate;
	/** 最后更新时间 **/
	private Date lastUpdate;
	/** 最后更新人ＩＤ **/
	private Integer lastUpdateUserId;
	/** 备注 **/
	private String remark;
	/** 风险ＩＤ **/
	private String riskId;
	/** 催办时间 **/
	@JSONField(format="yyyy-MM-dd")
	private Date rushDate;
	
	public BasicWfRiskReply(){
		
	}
	
	public BasicWfRiskReply(Integer id, Integer responsibleUserId,
			Integer status,
			Integer createUserId, Date createDate,
			Date lastUpdate, Integer lastUpdateUserId, String riskId) {
		this.id = id;
		this.responsibleUserId = responsibleUserId;
		this.status = status;
		this.createUserId = createUserId;
		this.createDate = createDate;
		this.lastUpdate = lastUpdate;
		this.lastUpdateUserId = lastUpdateUserId;
		this.riskId = riskId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getResponsibleUserId() {
		return responsibleUserId;
	}
	public void setResponsibleUserId(Integer responsibleUserId) {
		this.responsibleUserId = responsibleUserId;
	}
	public String getMeasures() {
		return measures;
	}
	public void setMeasures(String measures) {
		this.measures = measures;
	}
	public String getMeasuresFileNo() {
		return measuresFileNo;
	}
	public void setMeasuresFileNo(String measuresFileNo) {
		this.measuresFileNo = measuresFileNo;
	}
	public String getMeasuresFileName() {
		return measuresFileName;
	}
	public void setMeasuresFileName(String measuresFileName) {
		this.measuresFileName = measuresFileName;
	}
	public Date getMeasuresDate() {
		return measuresDate;
	}
	public void setMeasuresDate(Date measuresDate) {
		this.measuresDate = measuresDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getVerifyUserId() {
		return verifyUserId;
	}
	public void setVerifyUserId(Integer verifyUserId) {
		this.verifyUserId = verifyUserId;
	}
	public String getVerifyResult() {
		return verifyResult;
	}
	public void setVerifyResult(String verifyResult) {
		this.verifyResult = verifyResult;
	}
	public Date getVerifyDate() {
		return verifyDate;
	}
	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}
	public String getVerifyFileNo() {
		return verifyFileNo;
	}
	public void setVerifyFileNo(String verifyFileNo) {
		this.verifyFileNo = verifyFileNo;
	}
	public String getVerifyFileName() {
		return verifyFileName;
	}
	public void setVerifyFileName(String verifyFileName) {
		this.verifyFileName = verifyFileName;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public Integer getLastUpdateUserId() {
		return lastUpdateUserId;
	}
	public void setLastUpdateUserId(Integer lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRiskId() {
		return riskId;
	}
	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}
	
	public Date getRushDate() {
		return rushDate;
	}

	public void setRushDate(Date rushDate) {
		this.rushDate = rushDate;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		BasicWfRiskReply other = (BasicWfRiskReply) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BasicWfRiskReply [id=" + id + ", responsibleUserId="
				+ responsibleUserId + ", measures=" + measures
				+ ", measuresFileNo=" + measuresFileNo + ", measuresFileName="
				+ measuresFileName + ", measuresDate=" + measuresDate
				+ ", status=" + status + ", verifyUserId=" + verifyUserId
				+ ", verifyResult=" + verifyResult + ", verifyDate="
				+ verifyDate + ", verifyFileNo=" + verifyFileNo
				+ ", verifyFileName=" + verifyFileName + ", createUserId="
				+ createUserId + ", createDate=" + createDate + ", lastUpdate="
				+ lastUpdate + ", lastUpdateUserId=" + lastUpdateUserId
				+ ", remark=" + remark + ", riskId=" + riskId + ", rushDate="
				+ rushDate + "]";
	}
}
