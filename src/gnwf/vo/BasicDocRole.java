package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicDocRole {
	public static final String SELF_FIELDS = "DocRole.DocCateId,DocRole.PrjtRoleId,DocRole.Type,DocRole.Status,DocRole.CreateBy,DocRole.CreateDate,DocRole.LastUpd,DocRole.LastUpdDate,DocRole.Remark";

	/**外键连接到WfScheCfgDoc.DocId*/
	private java.lang.Integer docCateId;
	private java.lang.Integer prjtRoleId;
	private java.lang.Integer type;
	private java.lang.Integer status;
	private java.lang.Integer createBy;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.lang.Integer lastUpd;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date LastUpdDate;
	private java.lang.String remark;
	

	public java.lang.Integer getDocCateId() {
		return docCateId;
	}
	public void setDocCateId(java.lang.Integer docCateId) {
		this.docCateId = docCateId;
	}
	public java.lang.Integer getPrjtRoleId() {
		return prjtRoleId;
	}
	public void setPrjtRoleId(java.lang.Integer prjtRoleId) {
		this.prjtRoleId = prjtRoleId;
	}
	public java.lang.Integer getType() {
		return type;
	}
	public void setType(java.lang.Integer type) {
		this.type = type;
	}
	public java.lang.Integer getStatus() {
		return status;
	}
	public void setStatus(java.lang.Integer status) {
		this.status = status;
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
	public java.lang.Integer getLastUpd() {
		return lastUpd;
	}
	public void setLastUpd(java.lang.Integer lastUpd) {
		this.lastUpd = lastUpd;
	}
	public java.util.Date getLastUpdDate() {
		return LastUpdDate;
	}
	public void setLastUpdDate(java.util.Date lastUpdDate) {
		LastUpdDate = lastUpdDate;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

}