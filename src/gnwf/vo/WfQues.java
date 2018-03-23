package gnwf.vo;

import java.util.List;



public class WfQues extends BasicWfQues {
	/*public static final String ALL_FIELDS = "WfQues.CrDefectId,WfQues.PicXY,WfQues.CompletedDate,WfQues.IsRiskQues,WfQues.UsrName,WfQues.PrjtNo,WfQues.FileName,WfQues.FileNo,WfQues.IdtfResFileName,WfQues.ResultFileName,WfQues.IdtfResFileNo,WfQues.ResultFileNo,WfQues.QuesId,WfQues.ScheId,WfQues.TaskId,WfQues.CateId,WfQues.ComId,WfQues.DeptId,WfQues.UserId,WfQues.QuesLevel,WfQues.Status,WfQues.CreateBy,WfQues.LastUpd,WfQues.WfNo,WfQues.IsRisk,WfQues.CreateDate,WfQues.LastUpdDate,WfQues.QuesText,WfQues.Result,WfQues.Title,WfQues.RyUsrs,WfQues.IdtfBy,WfQues.IdtfDate,WfQues.IdtfRes" +
			",(select u.UsrName from Usr u where WfQues.CreateBy = u.Id) AS CreateUsr" +
			",(select sc.SchNm from SchCfg sc where WfQues.ScheId = sc.SchId) AS SchNm" +
			",(select pu.UsrId from SchCfg sc,PrjtRole pr,PrjtUsr pu where WfQues.ScheId = sc.SchId and WfRd.ProjectNo = pu.PrjtNo and pr.PrjtTypId = sc.TypId and pr.RoleId = pu.RoleId and pr.RoleNm = 'DQA') AS RoleDQAId" +
			",(select pt.TypNm from SchCfg sc,PrjtTyp pt where WfQues.ScheId = sc.SchId and pt.TypId = sc.TypId) AS TypNm" +
			",(select pd.PrjtNm from PrjtDef pd  where pd.PrjtNo = WfRd.ProjectNo) AS PrjtNm" +
			",(select u.UsrName from Usr u where WfQues.IdtfBy = u.Id) AS IdtfUsr"+
			",(select wr.WfName from WfRd wr where WfQues.wfNo = wr.wfNo) AS WfName";*/
	public static final String ALL_FIELDS = "WfQues.QuesTypeID,WfQues.SourceID,WfQues.TestItemID,WfQues.TestItemName,WfQues.FractionDefective,WfQues.CloseQuesReason,WfQues.CrDefectId,WfQues.PicXY,WfQues.CompletedDate,WfQues.IsRiskQues,WfQues.UsrName,WfQues.PrjtNo,WfQues.FileName,WfQues.FileNo,WfQues.IdtfResFileName,WfQues.ResultFileName,WfQues.IdtfResFileNo,WfQues.ResultFileNo,WfQues.QuesId,WfQues.ScheId,WfQues.TaskId,WfQues.CateId,WfQues.ComId,WfQues.DeptId,WfQues.UserId,WfQues.QuesLevel,WfQues.Status,WfQues.CreateBy,WfQues.LastUpd,WfQues.WfNo,WfQues.IsRisk,WfQues.CreateDate,WfQues.LastUpdDate,WfQues.QuesText,WfQues.Result,WfQues.Title,WfQues.RyUsrs,WfQues.IdtfBy,WfQues.IdtfDate,WfQues.IdtfRes" +
			//查询解决时长，如果未关闭，则计算开始时间到当前时间，如果已经关闭，则计算开始时间到关闭时间天数
			",( CASE WHEN WfQues.Status <> '30'  then datediff(day,WfQues.CreateDate,getdate()) ELSE datediff(day,WfQues.CreateDate,WfQues.LastUpdDate) END) AS Datetimeforques"+
			",(select u.UsrName from Usr u where WfQues.CreateBy = u.Id) AS CreateUsr" +
			",(select sc.SchNm from SchCfg sc where WfQues.ScheId = sc.SchId) AS SchNm" +
			",(select pu.UsrId from SchCfg sc,PrjtRole pr,PrjtUsr pu where WfQues.ScheId = sc.SchId and WfQues.PrjtNo = pu.PrjtNo and pr.PrjtTypId = sc.TypId and pr.RoleId = pu.RoleId and pr.RoleNm = 'DQA') AS RoleDQAId" +
			",(select pt.TypNm from SchCfg sc,PrjtTyp pt where WfQues.ScheId = sc.SchId and pt.TypId = sc.TypId) AS TypNm" +
			",(select pd.PrjtNm from PrjtDef pd where pd.PrjtNo = WfQues.PrjtNo) AS PrjtNm" +
			",(select u.UsrName from Usr u where WfQues.IdtfBy = u.Id) AS IdtfUsr"+
			/*",(select count(QuesId) from WfQues  where WfQues.Status = '21' or WfQues.Status = '1'or WfQues.Status = '9'or WfQues.Status = '10'or WfQues.Status = '11') AS QuesOpen" +
			",(select count(QuesId) from WfQues  where WfQues.Status = '30') AS QuesClose" +
			",(select count(QuesId) from WfQues  where WfQues.Status = '40') AS QuesRisk" +*/
			",(select top 1 dp.deptNm from Dept dp ,Usr us where WfQues.UsrName = us.UsrName and us.OrgNo = dp.deptno) AS DeptNm"+
			//",(select qr.QuesAnalysis from QuesResp qr where WfQues.QuesId = qr.QuesId) as QuesAnalysis"+
			
			",(select wr.WfName from WfRd wr where WfQues.wfNo = wr.wfNo) AS WfName";
	
	public static final String LIST_FIELDS = "WfQues.QuesTypeID,WfQues.SourceID,WfQues.TestItemID,WfQues.TestItemName,WfQues.FractionDefective,WfQues.CloseQuesReason,WfQues.CrDefectId,WfQues.PicXY,WfQues.CompletedDate,WfQues.IsRiskQues,WfQues.UsrName,WfQues.PrjtNo,WfQues.FileName,WfQues.FileNo,WfQues.IdtfResFileName,WfQues.ResultFileName,WfQues.IdtfResFileNo,WfQues.ResultFileNo,WfQues.QuesId,WfQues.ScheId,WfQues.TaskId,WfQues.CateId,WfQues.ComId,WfQues.DeptId,WfQues.UserId,WfQues.QuesLevel,WfQues.Status,WfQues.CreateBy,WfQues.LastUpd,WfQues.WfNo,WfQues.IsRisk,WfQues.CreateDate,WfQues.LastUpdDate,WfQues.QuesText,WfQues.Result,WfQues.Title,WfQues.RyUsrs,WfQues.IdtfBy,WfQues.IdtfDate,WfQues.IdtfRes" +
			//查询解决时长，如果未关闭，则计算开始时间到当前时间，如果已经关闭，则计算开始时间到关闭时间天数
			",( CASE WHEN WfQues.Status <> '30'  then datediff(day,WfQues.CreateDate,getdate()) ELSE datediff(day,WfQues.CreateDate,WfQues.LastUpdDate) END) AS Datetimeforques"+
			",(select u.UsrName from Usr u where WfQues.CreateBy = u.Id) AS CreateUsr" +
			",(select sc.SchNm from SchCfg sc where WfQues.ScheId = sc.SchId) AS SchNm" +
			",(select pu.UsrId from SchCfg sc,PrjtRole pr,PrjtUsr pu where WfQues.ScheId = sc.SchId and WfQues.PrjtNo = pu.PrjtNo and pr.PrjtTypId = sc.TypId and pr.RoleId = pu.RoleId and pr.RoleNm = 'DQA') AS RoleDQAId" +
			",(select pt.TypNm from SchCfg sc,PrjtTyp pt where WfQues.ScheId = sc.SchId and pt.TypId = sc.TypId) AS TypNm" +
			",(select pd.PrjtNm from PrjtDef pd where pd.PrjtNo = WfQues.PrjtNo) AS PrjtNm" +
			",(select u.UsrName from Usr u where WfQues.IdtfBy = u.Id) AS IdtfUsr"+
			/*",(select count(QuesId) from WfQues  where WfQues.Status = '21' or WfQues.Status = '1'or WfQues.Status = '9'or WfQues.Status = '10'or WfQues.Status = '11') AS QuesOpen" +
			",(select count(QuesId) from WfQues wfq where wfq.Status = '30' ) AS QuesClose" +
			",(select count(QuesId) from WfQues  wfq where wfq.Status = '40'  ) AS QuesRisk" +*/
			",(select top 1 dp.deptNm from Dept dp ,Usr us where WfQues.UsrName = us.UsrName and us.OrgNo = dp.deptno) AS DeptNm"+
			//",(select qr.QuesAnalysis from QuesResp qr where WfQues.QuesId = qr.QuesId) as QuesAnalysis"+
			",(select wr.WfName from WfRd wr where WfQues.wfNo = wr.wfNo) AS WfName";
	//查询全部问题
	public static final String ALL_LIST_FIELDS = "WfQues.QuesTypeID,WfQues.SourceID,WfQues.TestItemID,WfQues.TestItemName,WfQues.FractionDefective,WfQues.CloseQuesReason,WfQues.CrDefectId,WfQues.PicXY,WfQues.CompletedDate,WfQues.IsRiskQues,WfQues.UsrName,WfQues.PrjtNo,WfQues.FileName,WfQues.FileNo,WfQues.IdtfResFileName,WfQues.ResultFileName,WfQues.IdtfResFileNo,WfQues.ResultFileNo,WfQues.QuesId,WfQues.ScheId,WfQues.TaskId,WfQues.CateId,WfQues.ComId,WfQues.DeptId,WfQues.UserId,WfQues.QuesLevel,WfQues.Status,WfQues.CreateBy,WfQues.LastUpd,WfQues.WfNo,WfQues.IsRisk,WfQues.CreateDate,WfQues.LastUpdDate,WfQues.QuesText,WfQues.Result,WfQues.Title,WfQues.RyUsrs,WfQues.IdtfBy,WfQues.IdtfDate,WfQues.IdtfRes" +
			//查询解决时长，如果未关闭，则计算开始时间到当前时间，如果已经关闭，则计算开始时间到关闭时间天数
			",( CASE WHEN WfQues.Status <> '30'  then datediff(day,WfQues.CreateDate,getdate()) ELSE datediff(day,WfQues.CreateDate,WfQues.LastUpdDate) END) AS Datetimeforques"+
			",(select u.UsrName from Usr u where WfQues.CreateBy = u.Id) AS CreateUsr" +
			",(select sc.SchNm from SchCfg sc where WfQues.ScheId = sc.SchId) AS SchNm" +
			",(select pu.UsrId from SchCfg sc,PrjtRole pr,PrjtUsr pu where WfQues.ScheId = sc.SchId and WfQues.PrjtNo = pu.PrjtNo and pr.PrjtTypId = sc.TypId and pr.RoleId = pu.RoleId and pr.RoleNm = 'DQA') AS RoleDQAId" +
			",(select pt.TypNm from SchCfg sc,PrjtTyp pt where WfQues.ScheId = sc.SchId and pt.TypId = sc.TypId) AS TypNm" +
			",(select pd.PrjtNm from PrjtDef pd where pd.PrjtNo = WfQues.PrjtNo) AS PrjtNm" +
			",(select u.UsrName from Usr u where WfQues.IdtfBy = u.Id) AS IdtfUsr"+
			/*",(select count(QuesId) from WfQues  where WfQues.Status = '21' or WfQues.Status = '1'or WfQues.Status = '9'or WfQues.Status = '10'or WfQues.Status = '11') AS QuesOpen" +
			",(select count(QuesId) from WfQues  where WfQues.Status = '30' where WfQues.WfNo IN ("+wfQues.getWfNos()+")) AS QuesClose" +
			",(select count(QuesId) from WfQues  where WfQues.Status = '40') AS QuesRisk" +*/
			",(select top 1 dp.deptNm from Dept dp ,Usr us where WfQues.UsrName = us.UsrName and us.OrgNo = dp.deptno) AS DeptNm"+
			//",(select qr.QuesAnalysis from QuesResp qr where WfQues.QuesId = qr.QuesId) as QuesAnalysis"+
			//",(select ( CASE WHEN WfQues.Status <> '30'  then datediff(day,CreateDate,getdate()) ELSE datediff(day,CreateDate,LastUpdDate) END) from WfQues wqs where wqs.QuesId = WfQues.QuesId)AS Datetimeforques"+
			",(select wr.WfName from WfRd wr where WfQues.wfNo = wr.wfNo) AS WfName";
	
	public static final String WFQUES_QUESRESP = WfQues.SELF_FIELDS + ",QuesResp.UsrId AS QuesRespUserId" +
			//查询解决时长，如果未关闭，则计算开始时间到当前时间，如果已经关闭，则计算开始时间到关闭时间天数
			",( CASE WHEN WfQues.Status <> '30'  then datediff(day,WfQues.CreateDate,getdate()) ELSE datediff(day,WfQues.CreateDate,WfQues.LastUpdDate) END) AS Datetimeforques"+
			",(select u.UsrName from Usr u where WfQues.CreateBy = u.Id) AS CreateUsr" +
			",(select pd.PrjtNm from PrjtDef pd where pd.PrjtNo = WfQues.PrjtNo) AS PrjtNm" +
			",(select u.UsrName from Usr u where WfQues.IdtfBy = u.Id) AS IdtfUsr"+
			/*",(select count(QuesId) from WfQues  where WfQues.Status = '21' or WfQues.Status = '1'or WfQues.Status = '9'or WfQues.Status = '10'or WfQues.Status = '11') AS QuesOpen" +
			",(select count(QuesId) from WfQues  where WfQues.Status = '30') AS QuesClose" +
			",(select count(QuesId) from WfQues  where WfQues.Status = '40') AS QuesRisk" +*/
			",(select top 1 dp.deptNm from Dept dp ,Usr us where WfQues.UsrName = us.UsrName and us.OrgNo = dp.deptno) AS DeptNm"+
			//",(select qr.QuesAnalysis from QuesResp qr where WfQues.QuesId = qr.QuesId) as QuesAnalysis"+
			//",(select ( CASE WHEN WfQues.Status <> '30'  then datediff(day,CreateDate,getdate()) ELSE datediff(day,CreateDate,LastUpdDate) END) from WfQues wqs where wqs.QuesId = WfQues.QuesId)AS Datetimeforques"+
			",(select wr.WfName from WfRd wr where WfQues.wfNo = wr.wfNo) AS WfName";
	public static final String WFQUES_QUESRESP2 = WfQues.SELF_FIELDS + ",QuesResp.Id AS QuesRespId,QuesResp.IdtfDate AS QuesRespIdtfDate,QuesResp.Result AS QuesRespResult,QuesResp.QuesAnalysis AS QuesRespAnalysis";
	private java.util.List<gnwf.vo.WfReply> wfReplys;
	
	private String comNm;
	private int flowId;
	private String deptNm;
	private String wfNos;
	private String ryUsrNms;
	private String createUsr;
	private String idtfUsr;
	private String schNm;
	private String typNm;
	private Integer typId;
	private Integer roleDQAId;
	private Integer selectType;
	private Integer currentUsr;
	private String prjtNm;
	private String wfId;
	private String idFileIcon;
	private Boolean showIdFileIcon;
	private String resFileIcon;
	private Boolean showResFileIcon;
	private String wfName;
	private String ScheIds;
	private String prjtNoList;
	private List<QuesResp> quesRespList;
	private String quesMeasures;
	private String responsibleIds;
	private Integer quesRespId;
	private byte[] picture;
	private Integer category;
	
	private String quesAnalysis;
	
	private Integer selType;//问题是否带流程状态
	
	private Integer quesOpen;
	private Integer quesClose;
	private Integer quesRisk;
	
	private Integer datetimeforques;
	
	private String idtfName; //提交人名字
	
	
	
	
	public String getIdtfName() {
		return idtfName;
	}
	public void setIdtfName(String idtfName) {
		this.idtfName = idtfName;
	}
	public Integer getDatetimeforques() {
		return datetimeforques;
	}
	public void setDatetimeforques(Integer datetimeforques) {
		this.datetimeforques = datetimeforques;
	}
	public Integer getQuesOpen() {
		return quesOpen;
	}
	public void setQuesOpen(Integer quesOpen) {
		this.quesOpen = quesOpen;
	}
	public Integer getQuesClose() {
		return quesClose;
	}
	public void setQuesClose(Integer quesClose) {
		this.quesClose = quesClose;
	}
	public Integer getQuesRisk() {
		return quesRisk;
	}
	public void setQuesRisk(Integer quesRisk) {
		this.quesRisk = quesRisk;
	}
	public String getQuesAnalysis() {
		return quesAnalysis;
	}
	public void setQuesAnalysis(String quesAnalysis) {
		this.quesAnalysis = quesAnalysis;
	}
	public Integer getSelType() {
		return selType;
	}
	public void setSelType(Integer selType) {
		this.selType = selType;
	}
	public String getWfId() {
		return wfId;
	}
	public void setWfId(String wfId) {
		this.wfId = wfId;
	}
	
	private String fileIcon;
	
	public java.util.List<gnwf.vo.WfReply> getWfReplys() {
		return wfReplys;
	}
	public void setWfReplys(java.util.List<gnwf.vo.WfReply> wfReplys){
		this.wfReplys = wfReplys;
	}
	public void addtoWfReplys(gnwf.vo.WfReply wfReply){
		if(getWfReplys() == null) setWfReplys(new java.util.ArrayList<gnwf.vo.WfReply>());
			getWfReplys().add(wfReply);
	}
	public String getComNm() {
		return comNm;
	}
	public void setComNm(String comNm) {
		this.comNm = comNm;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	public String getWfNos() {
		return wfNos;
	}
	public void setWfNos(String wfNos) {
		this.wfNos = wfNos;
	}
	public String getRyUsrNms() {
		return ryUsrNms;
	}
	public void setRyUsrNms(String ryUsrNms) {
		this.ryUsrNms = ryUsrNms;
	}
	public int getFlowId() {
		return flowId;
	}
	public void setFlowId(int flowId) {
		this.flowId = flowId;
	}
	public String getCreateUsr() {
		return createUsr;
	}
	public void setCreateUsr(String createUsr) {
		this.createUsr = createUsr;
	}
	public String getIdtfUsr() {
		return idtfUsr;
	}
	public void setIdtfUsr(String idtfUsr) {
		this.idtfUsr = idtfUsr;
	}
	public String getSchNm() {
		return schNm;
	}
	public void setSchNm(String schNm) {
		this.schNm = schNm;
	}
	public String getTypNm() {
		return typNm;
	}
	public void setTypNm(String typNm) {
		this.typNm = typNm;
	}
	public Integer getTypId() {
		return typId;
	}
	public void setTypId(Integer typId) {
		this.typId = typId;
	}
	public Integer getRoleDQAId() {
		return roleDQAId;
	}
	public void setRoleDQAId(Integer roleDQAId) {
		this.roleDQAId = roleDQAId;
	}
	public Integer getSelectType() {
		return selectType;
	}
	public void setSelectType(Integer selectType) {
		this.selectType = selectType;
	}
	public Integer getCurrentUsr() {
		return currentUsr;
	}
	public void setCurrentUsr(Integer currentUsr) {
		this.currentUsr = currentUsr;
	}
	public String getPrjtNm() {
		return prjtNm;
	}
	public void setPrjtNm(String prjtNm) {
		this.prjtNm = prjtNm;
	}
	public String getIdFileIcon() {
		return idFileIcon;
	}
	public void setIdFileIcon(String idFileIcon) {
		this.idFileIcon = idFileIcon;
	}
	public String getResFileIcon() {
		return resFileIcon;
	}
	public void setResFileIcon(String resFileIcon) {
		this.resFileIcon = resFileIcon;
	}
	public Boolean getShowIdFileIcon() {
		return showIdFileIcon;
	}
	public void setShowIdFileIcon(Boolean showIdFileIcon) {
		this.showIdFileIcon = showIdFileIcon;
	}
	public Boolean getShowResFileIcon() {
		return showResFileIcon;
	}
	public void setShowResFileIcon(Boolean showResFileIcon) {
		this.showResFileIcon = showResFileIcon;
	}
	
	public String getFileIcon() {
		return fileIcon;
	}
	public void setFileIcon(String fileIcon) {
		this.fileIcon = fileIcon;
	}
	public List<QuesResp> getQuesRespList() {
		return quesRespList;
	}
	public void setQuesRespList(List<QuesResp> quesRespList) {
		this.quesRespList = quesRespList;
	}
	public String getWfName() {
		return wfName;
	}
	public void setWfName(String wfName) {
		this.wfName = wfName;
	}
	public String getPrjtNoList() {
		return prjtNoList;
	}
	public void setPrjtNoList(String prjtNoList) {
		this.prjtNoList = prjtNoList;
	}
	
	
	public String getScheIds() {
		return ScheIds;
	}
	public void setScheIds(String scheIds) {
		ScheIds = scheIds;
	}
	public String getQuesMeasures() {
		return quesMeasures;
	}
	public void setQuesMeasures(String quesMeasures) {
		this.quesMeasures = quesMeasures;
	}
	
	
	public String getResponsibleIds() {
		return responsibleIds;
	}
	public void setResponsibleIds(String responsibleIds) {
		this.responsibleIds = responsibleIds;
	}
	
	public Integer getQuesRespId() {
		return quesRespId;
	}
	public void setQuesRespId(Integer quesRespId) {
		this.quesRespId = quesRespId;
	}
	
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return super.toString() + "WfQues [selectType=" + selectType + ", quesAnalysis=" + quesAnalysis + ",quesMeasures="
				+ quesMeasures + "]";
	}
}