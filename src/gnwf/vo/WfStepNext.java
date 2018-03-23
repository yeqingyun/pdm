package gnwf.vo;

public class WfStepNext extends BasicWfStepNext {
	public static final String ALL_FIELDS = "WfStepNext.StepId,WfStepNext.NextId,WfStep.StepId as StepName";
	public static final String LIST_FIELDS = "WfStepNext.StepId,WfStepNext.NextId,WfStep.StepId as StepName";

	private String stepName;
	
	private Integer sort;
	private Integer stepType;


	public String getStepName(){
		return stepName;
	}
	public void setStepName(String stepName){
		this.stepName = stepName;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getStepType() {
		return stepType;
	}
	public void setStepType(Integer stepType) {
		this.stepType = stepType;
	}

}