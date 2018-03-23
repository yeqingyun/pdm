package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfMatlPro {
	public static final String SELF_FIELDS = "WfMatlPro.MatlProId,WfMatlPro.IsPurPass,WfMatlPro.IsMatPass,WfMatlPro.IsManagerPass,WfMatlPro.PurRevDate,WfMatlPro.MatRevDate,WfMatlPro.ManagerRevDate,WfMatlPro.WfNo,WfMatlPro.ProName,WfMatlPro.DesignName,WfMatlPro.ManageName,WfMatlPro.ProDesc,WfMatlPro.CurStep,WfMatlPro.ManagerReview,WfMatlPro.ManagerRemark,WfMatlPro.CurVersion,WfMatlPro.ProCost,WfMatlPro.PurReview,WfMatlPro.PurRemark,WfMatlPro.MatReveiw,WfMatlPro.MatRemark";

	private java.lang.Integer matlProId;
	private java.lang.Integer isPurPass;
	private java.lang.Integer isMatPass;
	private java.lang.Integer isManagerPass;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date purRevDate;
	private java.util.Date startPurRevDate;
	private java.util.Date endPurRevDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date matRevDate;
	private java.util.Date startMatRevDate;
	private java.util.Date endMatRevDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date managerRevDate;
	private java.util.Date startManagerRevDate;
	private java.util.Date endManagerRevDate;
	private java.lang.String wfNo;
	private java.lang.String proName;
	private java.lang.String designName;
	private java.lang.String manageName;
	private java.lang.String proDesc;
	private java.lang.String curStep;
	private java.lang.String managerReview;
	private java.lang.String managerRemark;
	private java.lang.String curVersion;
	private java.lang.String proCost;
	private java.lang.String purReview;
	private java.lang.String purRemark;
	private java.lang.String matReveiw;
	private java.lang.String matRemark;

	public java.lang.Integer getMatlProId(){
		return matlProId;
	}
	public void setMatlProId(java.lang.Integer matlProId){
		this.matlProId = matlProId;
	}
	public java.lang.Integer getIsPurPass(){
		return isPurPass;
	}
	public void setIsPurPass(java.lang.Integer isPurPass){
		this.isPurPass = isPurPass;
	}
	public java.lang.Integer getIsMatPass(){
		return isMatPass;
	}
	public void setIsMatPass(java.lang.Integer isMatPass){
		this.isMatPass = isMatPass;
	}
	public java.lang.Integer getIsManagerPass(){
		return isManagerPass;
	}
	public void setIsManagerPass(java.lang.Integer isManagerPass){
		this.isManagerPass = isManagerPass;
	}
	public java.util.Date getStartPurRevDate(){
		return startPurRevDate;
	}
	public void setStartPurRevDate(java.util.Date startPurRevDate){
		this.startPurRevDate = startPurRevDate;
	}
	public java.util.Date getEndPurRevDate(){
		return endPurRevDate;
	}
	public void setEndPurRevDate(java.util.Date endPurRevDate){
		this.endPurRevDate = endPurRevDate;
	}
	public java.util.Date getPurRevDate(){
		return purRevDate;
	}
	public void setPurRevDate(java.util.Date purRevDate){
		this.purRevDate = purRevDate;
	}
	public java.util.Date getStartMatRevDate(){
		return startMatRevDate;
	}
	public void setStartMatRevDate(java.util.Date startMatRevDate){
		this.startMatRevDate = startMatRevDate;
	}
	public java.util.Date getEndMatRevDate(){
		return endMatRevDate;
	}
	public void setEndMatRevDate(java.util.Date endMatRevDate){
		this.endMatRevDate = endMatRevDate;
	}
	public java.util.Date getMatRevDate(){
		return matRevDate;
	}
	public void setMatRevDate(java.util.Date matRevDate){
		this.matRevDate = matRevDate;
	}
	public java.util.Date getStartManagerRevDate(){
		return startManagerRevDate;
	}
	public void setStartManagerRevDate(java.util.Date startManagerRevDate){
		this.startManagerRevDate = startManagerRevDate;
	}
	public java.util.Date getEndManagerRevDate(){
		return endManagerRevDate;
	}
	public void setEndManagerRevDate(java.util.Date endManagerRevDate){
		this.endManagerRevDate = endManagerRevDate;
	}
	public java.util.Date getManagerRevDate(){
		return managerRevDate;
	}
	public void setManagerRevDate(java.util.Date managerRevDate){
		this.managerRevDate = managerRevDate;
	}
	public java.lang.String getWfNo(){
		return wfNo;
	}
	public void setWfNo(java.lang.String wfNo){
		this.wfNo = wfNo;
	}
	public java.lang.String getProName(){
		return proName;
	}
	public void setProName(java.lang.String proName){
		this.proName = proName;
	}
	public java.lang.String getDesignName(){
		return designName;
	}
	public void setDesignName(java.lang.String designName){
		this.designName = designName;
	}
	public java.lang.String getManageName(){
		return manageName;
	}
	public void setManageName(java.lang.String manageName){
		this.manageName = manageName;
	}
	public java.lang.String getProDesc(){
		return proDesc;
	}
	public void setProDesc(java.lang.String proDesc){
		this.proDesc = proDesc;
	}
	public java.lang.String getCurStep(){
		return curStep;
	}
	public void setCurStep(java.lang.String curStep){
		this.curStep = curStep;
	}
	public java.lang.String getManagerReview(){
		return managerReview;
	}
	public void setManagerReview(java.lang.String managerReview){
		this.managerReview = managerReview;
	}
	public java.lang.String getManagerRemark(){
		return managerRemark;
	}
	public void setManagerRemark(java.lang.String managerRemark){
		this.managerRemark = managerRemark;
	}
	public java.lang.String getCurVersion(){
		return curVersion;
	}
	public void setCurVersion(java.lang.String curVersion){
		this.curVersion = curVersion;
	}
	public java.lang.String getProCost(){
		return proCost;
	}
	public void setProCost(java.lang.String proCost){
		this.proCost = proCost;
	}
	public java.lang.String getPurReview(){
		return purReview;
	}
	public void setPurReview(java.lang.String purReview){
		this.purReview = purReview;
	}
	public java.lang.String getPurRemark(){
		return purRemark;
	}
	public void setPurRemark(java.lang.String purRemark){
		this.purRemark = purRemark;
	}
	public java.lang.String getMatReveiw(){
		return matReveiw;
	}
	public void setMatReveiw(java.lang.String matReveiw){
		this.matReveiw = matReveiw;
	}
	public java.lang.String getMatRemark(){
		return matRemark;
	}
	public void setMatRemark(java.lang.String matRemark){
		this.matRemark = matRemark;
	}

}