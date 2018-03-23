package gnwf.vo;

public class WfQuesDtl extends BasicWfQuesDtl {
	public static final String ALL_FIELDS = "WfQuesDtl.QuesDtlId,WfQuesDtl.QuesId,WfQuesDtl.UserId,WfQuesDtl.Status,WfQuesDtl.CreateBy,WfQuesDtl.LastUpd,WfQuesDtl.CreateDate,WfQuesDtl.LastUpdDate,WfQuesDtl.QuesTxt,WfQuesDtl.Title,WfQues.QuesId as QuesId";
	public static final String LIST_FIELDS = "WfQuesDtl.QuesDtlId,WfQuesDtl.QuesId,WfQuesDtl.UserId,WfQuesDtl.Status,WfQuesDtl.CreateBy,WfQuesDtl.LastUpd,WfQuesDtl.CreateDate,WfQuesDtl.LastUpdDate,WfQuesDtl.QuesTxt,WfQuesDtl.Title,WfQues.QuesId as QuesId";


	private Integer quesId;


	public Integer getQuesId(){
		return quesId;
	}
	public void setQuesId(Integer quesId){
		this.quesId = quesId;
	}

}