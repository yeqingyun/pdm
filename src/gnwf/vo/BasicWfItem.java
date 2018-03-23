package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfItem {
	public static final String SELF_FIELDS = "WfItem.ItemId,WfItem.Status,WfItem.FlowId,WfItem.OrderBy,WfItem.ItemNo,WfItem.Desc2,WfItem.Desc5,WfItem.ItemName,WfItem.Desc3,WfItem.Desc4,WfItem.Desc1";

	private java.lang.Integer itemId;
	private java.lang.Integer status;
	private java.lang.Integer flowId;
	private java.lang.String orderBy;
	private java.lang.String itemNo;
	private java.lang.String desc2;
	private java.lang.String desc5;
	private java.lang.String itemName;
	private java.lang.String desc3;
	private java.lang.String desc4;
	private java.lang.String desc1;

	public java.lang.Integer getItemId(){
		return itemId;
	}
	public void setItemId(java.lang.Integer itemId){
		this.itemId = itemId;
	}
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
	public java.lang.Integer getFlowId(){
		return flowId;
	}
	public void setFlowId(java.lang.Integer flowId){
		this.flowId = flowId;
	}
	public java.lang.String getOrderBy(){
		return orderBy;
	}
	public void setOrderBy(java.lang.String orderBy){
		this.orderBy = orderBy;
	}
	public java.lang.String getItemNo(){
		return itemNo;
	}
	public void setItemNo(java.lang.String itemNo){
		this.itemNo = itemNo;
	}
	public java.lang.String getDesc2(){
		return desc2;
	}
	public void setDesc2(java.lang.String desc2){
		this.desc2 = desc2;
	}
	public java.lang.String getDesc5(){
		return desc5;
	}
	public void setDesc5(java.lang.String desc5){
		this.desc5 = desc5;
	}
	public java.lang.String getItemName(){
		return itemName;
	}
	public void setItemName(java.lang.String itemName){
		this.itemName = itemName;
	}
	public java.lang.String getDesc3(){
		return desc3;
	}
	public void setDesc3(java.lang.String desc3){
		this.desc3 = desc3;
	}
	public java.lang.String getDesc4(){
		return desc4;
	}
	public void setDesc4(java.lang.String desc4){
		this.desc4 = desc4;
	}
	public java.lang.String getDesc1(){
		return desc1;
	}
	public void setDesc1(java.lang.String desc1){
		this.desc1 = desc1;
	}

}