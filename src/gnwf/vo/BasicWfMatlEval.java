package gnwf.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicWfMatlEval {
	public static final String SELF_FIELDS = "WfMatlEval.MatlId,WfMatlEval.UserId,WfMatlEval.IsPass,WfMatlEval.Evaler,WfMatlEval.status,WfMatlEval.EvalId,WfMatlEval.WfNo,WfMatlEval.EvalDate,WfMatlEval.EvalText";

	private java.lang.Integer matlId;
	private java.lang.Integer userId;
	private java.lang.Integer isPass;
	private java.lang.Integer evaler;
	private java.lang.Integer status;
	private java.lang.Integer evalId;
	private java.lang.String wfNo;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date evalDate;
	private java.util.Date startEvalDate;
	private java.util.Date endEvalDate;
	private java.lang.String evalText;

	public java.lang.Integer getMatlId(){
		return matlId;
	}
	public void setMatlId(java.lang.Integer matlId){
		this.matlId = matlId;
	}
	public java.lang.Integer getUserId(){
		return userId;
	}
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}
	public java.lang.Integer getIsPass(){
		return isPass;
	}
	public void setIsPass(java.lang.Integer isPass){
		this.isPass = isPass;
	}
	public java.lang.Integer getEvaler(){
		return evaler;
	}
	public void setEvaler(java.lang.Integer evaler){
		this.evaler = evaler;
	}
	public java.lang.Integer getstatus(){
		return status;
	}
	public void setstatus(java.lang.Integer status){
		this.status = status;
	}
	public java.lang.Integer getEvalId(){
		return evalId;
	}
	public void setEvalId(java.lang.Integer evalId){
		this.evalId = evalId;
	}
	public java.lang.String getWfNo(){
		return wfNo;
	}
	public void setWfNo(java.lang.String wfNo){
		this.wfNo = wfNo;
	}
	public java.util.Date getStartEvalDate(){
		return startEvalDate;
	}
	public void setStartEvalDate(java.util.Date startEvalDate){
		this.startEvalDate = startEvalDate;
	}
	public java.util.Date getEndEvalDate(){
		return endEvalDate;
	}
	public void setEndEvalDate(java.util.Date endEvalDate){
		this.endEvalDate = endEvalDate;
	}
	public java.util.Date getEvalDate(){
		return evalDate;
	}
	public void setEvalDate(java.util.Date evalDate){
		this.evalDate = evalDate;
	}
	public java.lang.String getEvalText(){
		return evalText;
	}
	public void setEvalText(java.lang.String evalText){
		this.evalText = evalText;
	}

}