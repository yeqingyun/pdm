package zrprjt.vo;

import java.util.List;

public class SchCfg extends BasicSchCfg {
	public static final String ALL_FIELDS = "SchCfg.ManualStart,SchCfg.PredecessorLink,SchCfg.Critical,SchCfg.Summary,SchCfg.Milestone,SchCfg.SchId,SchCfg.Parent,SchCfg.TypId,SchCfg.SchNo,SchCfg.Leve,SchCfg.CfgTime,SchCfg.Status,SchCfg.CreateBy,SchCfg.LastUpd,SchCfg.CreateDate,SchCfg.LastDate,SchCfg.SchNm,SchCfg.Remark,PrjtTyp.TypNm as prjtTypNm,SchWf.FlowId as FlowId,WfCfg.FlowName as FlowName";
	public static final String LIST_FIELDS = "SchCfg.ManualStart,SchCfg.PredecessorLink,SchCfg.Critical,SchCfg.Summary,SchCfg.Milestone,SchCfg.SchId,SchCfg.Parent,SchCfg.TypId,SchCfg.SchNo,SchCfg.Leve,SchCfg.CfgTime,SchCfg.Status,SchCfg.CreateBy,SchCfg.LastUpd,SchCfg.CreateDate,SchCfg.LastDate,SchCfg.SchNm,SchCfg.Remark,PrjtTyp.TypNm as prjtTypNm,SchWf.FlowId as FlowId,WfCfg.FlowName as FlowName";

	private java.util.List<zrprjt.vo.SchDtl> schDtls;
	private java.util.List<zrprjt.vo.SchWf> schWfs;
	private java.util.List<zrprjt.vo.Task> tasks;
	
	
	private zrprjt.vo.SchWf schWf;
	
	private List<SchCfg> children;
	
	private String prjtTypNm;
	
	private String flowName;
	
	private Integer manualStart;



	public String getFlowName() {
		return flowName;
	}



	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	


	public java.util.List<zrprjt.vo.SchDtl> getSchDtls() {
		return schDtls;
	}
	public void setSchDtls(java.util.List<zrprjt.vo.SchDtl> schDtls){
		this.schDtls = schDtls;
	}
	public void addtoSchDtls(zrprjt.vo.SchDtl schDtl){
		if(getSchDtls() == null) setSchDtls(new java.util.ArrayList<zrprjt.vo.SchDtl>());
			getSchDtls().add(schDtl);
	}
	public java.util.List<zrprjt.vo.SchWf> getSchWfs() {
		return schWfs;
	}
	public void setSchWfs(java.util.List<zrprjt.vo.SchWf> schWfs){
		this.schWfs = schWfs;
	}
	public void addtoSchWfs(zrprjt.vo.SchWf schWf){
		if(getSchWfs() == null) setSchWfs(new java.util.ArrayList<zrprjt.vo.SchWf>());
			getSchWfs().add(schWf);
	}
	public java.util.List<zrprjt.vo.Task> getTasks() {
		return tasks;
	}
	public void setTasks(java.util.List<zrprjt.vo.Task> tasks){
		this.tasks = tasks;
	}
	public void addtoTasks(zrprjt.vo.Task task){
		if(getTasks() == null) setTasks(new java.util.ArrayList<zrprjt.vo.Task>());
			getTasks().add(task);
	}
	public List<SchCfg> getChildren() {
		return children;
	}
	public void setChildren(List<SchCfg> children) {
		this.children = children;
	}
	public String getPrjtTypNm() {
		return prjtTypNm;
	}
	public void setPrjtTypNm(String prjtTypNm) {
		this.prjtTypNm = prjtTypNm;
	}
	public zrprjt.vo.SchWf getSchWf() {
		return schWf;
	}
	public void setSchWf(zrprjt.vo.SchWf schWf) {
		this.schWf = schWf;
	}



	public Integer getManualStart() {
		return manualStart;
	}



	public void setManualStart(Integer manualStart) {
		this.manualStart = manualStart;
	}


}