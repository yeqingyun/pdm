package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfCate {
	public static final String SELF_FIELDS = "WfCate.Sort,WfCate.CateId,WfCate.CateParent,WfCate.CateLevel,WfCate.Status,WfCate.CreateBy,WfCate.LastUpd,WfCate.CreateDate,WfCate.LastUpdDate,WfCate.CateName";

	private java.lang.Integer cateId;
	private java.lang.Integer cateParent;
	private java.lang.Integer cateLevel;
	private java.lang.Integer status;
	private java.lang.Integer createBy;
	private java.lang.Integer lastUpd;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date createDate;
	private java.util.Date startCreateDate;
	private java.util.Date endCreateDate;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date lastUpdDate;
	private java.util.Date startLastUpdDate;
	private java.util.Date endLastUpdDate;
	private java.lang.String cateName;
	
	private java.lang.Integer sort;

	public java.lang.Integer getCateId(){
		return cateId;
	}
	public void setCateId(java.lang.Integer cateId){
		this.cateId = cateId;
	}
	public java.lang.Integer getCateParent(){
		return cateParent;
	}
	public void setCateParent(java.lang.Integer cateParent){
		this.cateParent = cateParent;
	}
	public java.lang.Integer getCateLevel(){
		return cateLevel;
	}
	public void setCateLevel(java.lang.Integer cateLevel){
		this.cateLevel = cateLevel;
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
	public java.lang.String getCateName(){
		return cateName;
	}
	public void setCateName(java.lang.String cateName){
		this.cateName = cateName;
	}
	public java.lang.Integer getSort() {
		return sort;
	}
	public void setSort(java.lang.Integer sort) {
		this.sort = sort;
	}

}