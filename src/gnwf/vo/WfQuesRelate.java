package gnwf.vo;

public class WfQuesRelate extends BasicWfQuesRelate {
	public static final String ALL_FIELDS = "WfQuesRelate.QuesId,WfQuesRelate.IsRisk,WfQuesRelate.WfNo,WfQues.QuesId as QuesId,WfRd.WfNo as WfNo";
	public static final String LIST_FIELDS = "WfQuesRelate.QuesId,WfQuesRelate.IsRisk,WfQuesRelate.WfNo,WfQues.QuesId as QuesId,WfRd.WfNo as WfNo";


	private Integer quesId;
	private String wfNo;


	public Integer getQuesId(){
		return quesId;
	}
	public void setQuesId(Integer quesId){
		this.quesId = quesId;
	}
	public String getWfNo(){
		return wfNo;
	}
	public void setWfNo(String wfNo){
		this.wfNo = wfNo;
	}

}