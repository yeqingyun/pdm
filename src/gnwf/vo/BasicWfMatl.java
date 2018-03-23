package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfMatl {
	public static final String SELF_FIELDS = "WfMatl.MatlId,WfMatl.MatlType,WfMatl.IsSubs,WfMatl.Risk,WfMatl.LotSize,WfMatl.IsPanel,WfMatl.Status,WfMatl.CreateBy,WfMatl.LastUpd,WfMatl.MatlNo,WfMatl.WfNo,WfMatl.MatlLevel,WfMatl.historyLevel,WfMatl.CreateDate,WfMatl.LastUpdDate,WfMatl.MatlName,WfMatl.MatlDesc,WfMatl.Supplier,WfMatl.Remark,WfMatl.SupNo,WfMatl.MatlEvalDesc";

	private java.lang.Integer matlId;
	private java.lang.Integer matlType;
	private java.lang.Integer isSubs;
	private java.lang.Integer risk;
	private java.lang.Integer lotSize;
	private java.lang.Integer isPanel;
	private java.lang.Integer status;
	private java.lang.Integer createBy;
	private java.lang.Integer lastUpd;
	private java.lang.String matlNo;
	private java.lang.String wfNo;
	private java.lang.String matlLevel;
	private java.lang.String historyLevel;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastUpdDate;
	private java.util.Date startLastUpdDate;
	private java.util.Date endLastUpdDate;
	private java.lang.String matlName;
	private java.lang.String matlDesc;
	private java.lang.String supplier;
	private java.lang.String remark;
	private java.lang.String supNo;
	private java.lang.String matlEvalDesc;

	public java.lang.Integer getMatlId(){
		return matlId;
	}
	public void setMatlId(java.lang.Integer matlId){
		this.matlId = matlId;
	}
	public java.lang.Integer getMatlType(){
		return matlType;
	}
	public void setMatlType(java.lang.Integer matlType){
		this.matlType = matlType;
	}
	public java.lang.Integer getIsSubs(){
		return isSubs;
	}
	public void setIsSubs(java.lang.Integer isSubs){
		this.isSubs = isSubs;
	}
	public java.lang.Integer getRisk(){
		return risk;
	}
	public void setRisk(java.lang.Integer risk){
		this.risk = risk;
	}
	public java.lang.Integer getLotSize(){
		return lotSize;
	}
	public void setLotSize(java.lang.Integer lotSize){
		this.lotSize = lotSize;
	}
	public java.lang.Integer getIsPanel(){
		return isPanel;
	}
	public void setIsPanel(java.lang.Integer isPanel){
		this.isPanel = isPanel;
	}
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
	public java.lang.Integer getCreateBy(){
		return createBy;
	}
	public void setCreateBy(java.lang.Integer createBy){
		this.createBy = createBy;
	}
	public java.lang.Integer getLastUpd(){
		return lastUpd;
	}
	public void setLastUpd(java.lang.Integer lastUpd){
		this.lastUpd = lastUpd;
	}
	public java.lang.String getMatlNo(){
		return matlNo;
	}
	public void setMatlNo(java.lang.String matlNo){
		this.matlNo = matlNo;
	}
	public java.lang.String getWfNo(){
		return wfNo;
	}
	public void setWfNo(java.lang.String wfNo){
		this.wfNo = wfNo;
	}
	public java.lang.String getMatlLevel(){
		return matlLevel;
	}
	public void setMatlLevel(java.lang.String matlLevel){
		this.matlLevel = matlLevel;
	}
	public java.lang.String gethistoryLevel(){
		return historyLevel;
	}
	public void sethistoryLevel(java.lang.String historyLevel){
		this.historyLevel = historyLevel;
	}
	public java.util.Date getStartCreateDate(){
		return startCreateDate;
	}
	public void setStartCreateDate(java.util.Date startCreateDate){
		this.startCreateDate = startCreateDate;
	}
	public java.util.Date getEndCreateDate(){
		return endCreateDate;
	}
	public void setEndCreateDate(java.util.Date endCreateDate){
		this.endCreateDate = endCreateDate;
	}
	public java.util.Date getCreateDate(){
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	public java.util.Date getStartLastUpdDate(){
		return startLastUpdDate;
	}
	public void setStartLastUpdDate(java.util.Date startLastUpdDate){
		this.startLastUpdDate = startLastUpdDate;
	}
	public java.util.Date getEndLastUpdDate(){
		return endLastUpdDate;
	}
	public void setEndLastUpdDate(java.util.Date endLastUpdDate){
		this.endLastUpdDate = endLastUpdDate;
	}
	public java.util.Date getLastUpdDate(){
		return lastUpdDate;
	}
	public void setLastUpdDate(java.util.Date lastUpdDate){
		this.lastUpdDate = lastUpdDate;
	}
	public java.lang.String getMatlName(){
		return matlName;
	}
	public void setMatlName(java.lang.String matlName){
		this.matlName = matlName;
	}
	public java.lang.String getMatlDesc(){
		return matlDesc;
	}
	public void setMatlDesc(java.lang.String matlDesc){
		this.matlDesc = matlDesc;
	}
	public java.lang.String getSupplier(){
		return supplier;
	}
	public void setSupplier(java.lang.String supplier){
		this.supplier = supplier;
	}
	public java.lang.String getRemark(){
		return remark;
	}
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	public java.lang.String getSupNo(){
		return supNo;
	}
	public void setSupNo(java.lang.String supNo){
		this.supNo = supNo;
	}
	public java.lang.String getMatlEvalDesc(){
		return matlEvalDesc;
	}
	public void setMatlEvalDesc(java.lang.String matlEvalDesc){
		this.matlEvalDesc = matlEvalDesc;
	}

}