package gnwf.vo;

public class WfMatlEval extends BasicWfMatlEval {
	public static final String ALL_FIELDS = "WfMatlEval.MatlId,WfMatlEval.UserId,WfMatlEval.IsPass,WfMatlEval.Evaler,WfMatlEval.status,WfMatlEval.EvalId,WfMatlEval.WfNo,WfMatlEval.EvalDate,WfMatlEval.EvalText,WfMatl.MatlNo as MatlNo";
	public static final String LIST_FIELDS = "WfMatlEval.MatlId,WfMatlEval.UserId,WfMatlEval.IsPass,WfMatlEval.Evaler,WfMatlEval.status,WfMatlEval.EvalId,WfMatlEval.WfNo,WfMatlEval.EvalDate,WfMatlEval.EvalText,WfMatl.MatlNo as MatlNo";


	private String matlNo;


	public String getMatlNo(){
		return matlNo;
	}
	public void setMatlNo(String matlNo){
		this.matlNo = matlNo;
	}

}