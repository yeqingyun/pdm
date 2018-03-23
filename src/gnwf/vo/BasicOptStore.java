package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicOptStore {
	public static final String SELF_FIELDS = "OptStore.MatlNo,OptStore.GpCode,OptStore.MatlRev,OptStore.MatlNm,OptStore.MatlDesc,OptStore.OptTyp,OptStore.MatlTyp,OptStore.LotSize,OptStore.IsPanel,OptStore.Status,OptStore.CreateBy,OptStore.CreateDate,OptStore.LastUpd,OptStore.LastDate";

	private java.lang.String matlNo;
	private java.lang.String gpCode;
	private java.lang.String matlRev;
	private java.lang.String matlNm;
	private java.lang.String matlDesc;
	private java.lang.Integer optTyp;
	private java.lang.Integer matlTyp;
	private java.lang.Integer lotSize;
	private java.lang.Integer isPanel;
	private java.lang.Integer status;
	private java.lang.Integer createBy;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	private java.lang.Integer lastUpd;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastDate;
	private java.util.Date startLastDate;
	private java.util.Date endLastDate;

	public java.lang.String getMatlNo(){
		return matlNo;
	}
	public void setMatlNo(java.lang.String matlNo){
		this.matlNo = matlNo;
	}
	public java.lang.String getGpCode(){
		return gpCode;
	}
	public void setGpCode(java.lang.String gpCode){
		this.gpCode = gpCode;
	}
	public java.lang.String getMatlRev(){
		return matlRev;
	}
	public void setMatlRev(java.lang.String matlRev){
		this.matlRev = matlRev;
	}
	public java.lang.String getMatlNm(){
		return matlNm;
	}
	public void setMatlNm(java.lang.String matlNm){
		this.matlNm = matlNm;
	}
	public java.lang.String getMatlDesc(){
		return matlDesc;
	}
	public void setMatlDesc(java.lang.String matlDesc){
		this.matlDesc = matlDesc;
	}
	public java.lang.Integer getOptTyp(){
		return optTyp;
	}
	public void setOptTyp(java.lang.Integer optTyp){
		this.optTyp = optTyp;
	}
	public java.lang.Integer getMatlTyp(){
		return matlTyp;
	}
	public void setMatlTyp(java.lang.Integer matlTyp){
		this.matlTyp = matlTyp;
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
	public java.lang.Integer getLastUpd(){
		return lastUpd;
	}
	public void setLastUpd(java.lang.Integer lastUpd){
		this.lastUpd = lastUpd;
	}
	public java.util.Date getStartLastDate(){
		return startLastDate;
	}
	public void setStartLastDate(java.util.Date startLastDate){
		this.startLastDate = startLastDate;
	}
	public java.util.Date getEndLastDate(){
		return endLastDate;
	}
	public void setEndLastDate(java.util.Date endLastDate){
		this.endLastDate = endLastDate;
	}
	public java.util.Date getLastDate(){
		return lastDate;
	}
	public void setLastDate(java.util.Date lastDate){
		this.lastDate = lastDate;
	}

}