package gnwf.vo;

public class WfMatl extends BasicWfMatl {
	public static final String ALL_FIELDS = "WfMatl.MatlId,WfMatl.MatlType,WfMatl.IsSubs,WfMatl.Risk,WfMatl.LotSize,WfMatl.IsPanel,WfMatl.Status,WfMatl.CreateBy,WfMatl.LastUpd,WfMatl.MatlNo,WfMatl.WfNo,WfMatl.MatlLevel,WfMatl.historyLevel,WfMatl.CreateDate,WfMatl.LastUpdDate,WfMatl.MatlName,WfMatl.MatlDesc,WfMatl.Supplier,WfMatl.Remark,WfMatl.SupNo,WfMatl.MatlEvalDesc,WfRd.WfNo as WfNo";
	public static final String LIST_FIELDS = "WfMatl.MatlId,WfMatl.MatlType,WfMatl.IsSubs,WfMatl.Risk,WfMatl.LotSize,WfMatl.IsPanel,WfMatl.Status,WfMatl.CreateBy,WfMatl.LastUpd,WfMatl.MatlNo,WfMatl.WfNo,WfMatl.MatlLevel,WfMatl.historyLevel,WfMatl.CreateDate,WfMatl.LastUpdDate,WfMatl.MatlName,WfMatl.MatlDesc,WfMatl.Supplier,WfMatl.Remark,WfMatl.SupNo,WfMatl.MatlEvalDesc,WfRd.WfNo as WfNo";

	private java.util.List<gnwf.vo.WfMatlEval> wfMatlEvals;

	private String wfNo;

	public java.util.List<gnwf.vo.WfMatlEval> getWfMatlEvals() {
		return wfMatlEvals;
	}
	public void setWfMatlEvals(java.util.List<gnwf.vo.WfMatlEval> wfMatlEvals){
		this.wfMatlEvals = wfMatlEvals;
	}
	public void addtoWfMatlEvals(gnwf.vo.WfMatlEval wfMatlEval){
		if(getWfMatlEvals() == null) setWfMatlEvals(new java.util.ArrayList<gnwf.vo.WfMatlEval>());
			getWfMatlEvals().add(wfMatlEval);
	}

	public String getWfNo(){
		return wfNo;
	}
	public void setWfNo(String wfNo){
		this.wfNo = wfNo;
	}

}