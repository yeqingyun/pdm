package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfQuesRelate {
	public static final String SELF_FIELDS = "WfQuesRelate.QuesId,WfQuesRelate.IsRisk,WfQuesRelate.WfNo";

	private java.lang.Integer quesId;
	private java.lang.Integer isRisk;
	private java.lang.String wfNo;

	public java.lang.Integer getQuesId(){
		return quesId;
	}
	public void setQuesId(java.lang.Integer quesId){
		this.quesId = quesId;
	}
	public java.lang.Integer getIsRisk(){
		return isRisk;
	}
	public void setIsRisk(java.lang.Integer isRisk){
		this.isRisk = isRisk;
	}
	public java.lang.String getWfNo(){
		return wfNo;
	}
	public void setWfNo(java.lang.String wfNo){
		this.wfNo = wfNo;
	}

}