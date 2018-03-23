package gnwf.vo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfRisk implements Serializable {

	private static final long serialVersionUID = -1670676388925822911L;
	
	public static final String SELF_FIELDS = "WfRisk.ResponsibleUserId,WfRisk.RiskCategory,WfRisk.ExecutionDate,WfRisk.RiskText,WfRisk.DeptName,WfRisk.RiskId,WfRisk.PrjtNo,WfRisk.ScheId,WfRisk.QuesId,WfRisk.CateId,WfRisk.WfNo,WfRisk.DeptId,WfRisk.ResponsibleUserName,"
											+ "WfRisk.Title,WfRisk.Description,WfRisk.RiskLevel,WfRisk.Status,WfRisk.CreateUserId,WfRisk.CreateDate,WfRisk.LastUpdate,"
											+ "WfRisk.LastUpdateUserId,WfRisk.FileNo,WfRisk.FileName,WfRisk.Remark,WfRisk.RiskConsequence,WfRisk.PreventiveMeasures,WfRisk.ImpTime,WfRisk.RiskMonitor";
	
	
	/** 风险ID **/
	private String riskId;
	/** 项目编号 **/
	private String prjtNo;
	/** 项目阶段ID **/
	private Integer scheId;
	/** 问题ID **/
	private String quesId;
	/** 类别ID **/
	private Integer cateId;
	/** 流程编号 **/
	private String wfNo;
	/** 部门ID **/
	private Integer deptId;
	/** 责任人名字 **/
	private String responsibleUserName;
	/** 风险标题 **/
	private String title;
	/** 风险描述 **/
	private String description;
	/** 风险级别  (1:致命_A,2:严重_B,3:一般_C,4:提示_D)**/
	private Integer riskLevel;
	/** 风险状态 1:新增,2:走风险流程,3:关闭**/
	private Integer status;
	/** 创建人ID **/
	private Integer createUserId;
	/** 创建时间 **/
	@JSONField(format="yyyy-MM-dd")
	private Date createDate;
	/** 最后更新时间 **/
	private Date lastUpdate;
	/** 最后更新人ID **/
	private Integer lastUpdateUserId;
	/** 附件编号 **/
	private String fileNo;
	/** 附件名称 **/
	private String fileName;
	/** 备注 **/
	private String remark;
	
	/** 风险说明及风险后果 **/
	private String riskConsequence;
	/** 拟采取的预防措施 **/
	private String preventiveMeasures;
	/** 拟导入时间 **/
	private String impTime;
	/** 风险监控结果 **/
	private String riskMonitor;
	/**以下为临时添加字段 **/
	
	private String riskCategory;//风险类别
	private String executionDate;//执行日期
	private String riskText;//建议措施
	private String deptName;//部门
	private String responsibleUserId;
	
	public BasicWfRisk(){}
	
	
	public BasicWfRisk(String prjtNo, Integer scheId,
			String quesId, Integer deptId, String responsibleUserName,
			String title, String description, Integer riskLevel,
			Integer createUserId, Date createDate, Date lastUpdate,
			Integer lastUpdateUserId ) {
		this.prjtNo = prjtNo;
		this.scheId = scheId;
		this.quesId = quesId;
		this.deptId = deptId;
		this.responsibleUserName = responsibleUserName;
		this.title = title;
		this.description = description;
		this.riskLevel = riskLevel;
		this.createUserId = createUserId;
		this.createDate = createDate;
		this.lastUpdate = lastUpdate;
		this.lastUpdateUserId = lastUpdateUserId;
		
	}
	
/*	public BasicWfRisk(String prjtNo, Integer scheId,
			String quesId, Integer deptId, String responsibleUserName,
			String title, String description, Integer riskLevel,
			Integer createUserId, Date createDate, Date lastUpdate,
			Integer lastUpdateUserId ,String riskConsequence, String preventiveMeasures, Date impTime) {
		this.prjtNo = prjtNo;
		this.scheId = scheId;
		this.quesId = quesId;
		this.deptId = deptId;
		this.responsibleUserName = responsibleUserName;
		this.title = title;
		this.description = description;
		this.riskLevel = riskLevel;
		this.createUserId = createUserId;
		this.createDate = createDate;
		this.lastUpdate = lastUpdate;
		this.lastUpdateUserId = lastUpdateUserId;
		this.riskConsequence = riskConsequence;
		this.preventiveMeasures = preventiveMeasures;
		this.impTime = impTime;
	}*/
	
	
	public String getRiskId() {
		return riskId;
	}

	public String getRiskMonitor() {
		return riskMonitor;
	}


	public void setRiskMonitor(String riskMonitor) {
		this.riskMonitor = riskMonitor;
	}


	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}
	public String getPrjtNo() {
		return prjtNo;
	}
	public void setPrjtNo(String prjtNo) {
		this.prjtNo = prjtNo;
	}
	public Integer getScheId() {
		return scheId;
	}
	public void setScheId(Integer scheId) {
		this.scheId = scheId;
	}
	public String getQuesId() {
		return quesId;
	}
	public void setQuesId(String quesId) {
		this.quesId = quesId;
	}
	public Integer getCateId() {
		return cateId;
	}
	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}
	public String getWfNo() {
		return wfNo;
	}
	public void setWfNo(String wfNo) {
		this.wfNo = wfNo;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getResponsibleUserName() {
		return responsibleUserName;
	}
	public void setResponsibleUserName(String responsibleUserName) {
		this.responsibleUserName = responsibleUserName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(Integer riskLevel) {
		this.riskLevel = riskLevel;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((riskId == null) ? 0 : riskId.hashCode());
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
		BasicWfRisk other = (BasicWfRisk) obj;
		if (riskId == null) {
			if (other.riskId != null)
				return false;
		} else if (!riskId.equals(other.riskId))
			return false;
		return true;
	}


	public String getRiskCategory() {
		return riskCategory;
	}


	public void setRiskCategory(String riskCategory) {
		this.riskCategory = riskCategory;
	}


	public String getExecutionDate() {
		return executionDate;
	}


	public void setExecutionDate(String executionDate) {
		this.executionDate = executionDate;
	}


	public String getRiskText() {
		return riskText;
	}


	public void setRiskText(String riskText) {
		this.riskText = riskText;
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public String getResponsibleUserId() {
		return responsibleUserId;
	}


	public void setResponsibleUserId(String responsibleUserId) {
		this.responsibleUserId = responsibleUserId;
	}


	public String getRiskConsequence() {
		return riskConsequence;
	}


	public void setRiskConsequence(String riskConsequence) {
		this.riskConsequence = riskConsequence;
	}


	public String getPreventiveMeasures() {
		return preventiveMeasures;
	}


	public void setPreventiveMeasures(String preventiveMeasures) {
		this.preventiveMeasures = preventiveMeasures;
	}


	


	public String getImpTime() {
		return impTime;
	}


	public void setImpTime(String impTime) {
		this.impTime = impTime;
	}


	@Override
	public String toString() {
		return "BasicWfRisk [riskId=" + riskId + ", prjtNo=" + prjtNo
				+ ", scheId=" + scheId + ", quesId=" + quesId + ", cateId="
				+ cateId + ", wfNo=" + wfNo + ", deptId=" + deptId
				+ ", responsibleUserName=" + responsibleUserName + ", title="
				+ title + ", description=" + description + ", riskLevel="
				+ riskLevel + ", status=" + status + ", createUserId="
				+ createUserId + ", createDate=" + createDate + ", lastUpdate="
				+ lastUpdate + ", lastUpdateUserId=" + lastUpdateUserId
				+ ", fileNo=" + fileNo + ", fileName=" + fileName + ", remark="
				+ remark + ", riskCategory=" + riskCategory
				+ ", executionDate=" + executionDate + ", riskText=" + riskText
				+ ", deptName=" + deptName + ", responsibleUserId="
				+ responsibleUserId + " , riskConsequence="+riskConsequence +" ,preventiveMeasures="
				+preventiveMeasures+" ,impTime = "+impTime +"]";
	}


	
}
