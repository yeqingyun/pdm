package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWFMatlCategory {
	public static final String SELF_FIELDS = "WFMatlCategory.CategoryId,WFMatlCategory.Status,WFMatlCategory.Desc2,WFMatlCategory.CategoryNo,WFMatlCategory.Desc3,WFMatlCategory.CategoryName,WFMatlCategory.Desc1";

	private java.lang.Integer categoryId;
	private java.lang.Integer status;
	private java.lang.String desc2;
	private java.lang.String categoryNo;
	private java.lang.String desc3;
	private java.lang.String categoryName;
	private java.lang.String desc1;

	public java.lang.Integer getCategoryId(){
		return categoryId;
	}
	public void setCategoryId(java.lang.Integer categoryId){
		this.categoryId = categoryId;
	}
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
	public java.lang.String getDesc2(){
		return desc2;
	}
	public void setDesc2(java.lang.String desc2){
		this.desc2 = desc2;
	}
	public java.lang.String getCategoryNo(){
		return categoryNo;
	}
	public void setCategoryNo(java.lang.String categoryNo){
		this.categoryNo = categoryNo;
	}
	public java.lang.String getDesc3(){
		return desc3;
	}
	public void setDesc3(java.lang.String desc3){
		this.desc3 = desc3;
	}
	public java.lang.String getCategoryName(){
		return categoryName;
	}
	public void setCategoryName(java.lang.String categoryName){
		this.categoryName = categoryName;
	}
	public java.lang.String getDesc1(){
		return desc1;
	}
	public void setDesc1(java.lang.String desc1){
		this.desc1 = desc1;
	}

}