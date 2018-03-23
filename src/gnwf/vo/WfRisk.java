package gnwf.vo;

import java.util.Date;
import java.util.List;

public class WfRisk extends BasicWfRisk {
	
	
	
	

	public static final String LIST_FIELDS = SELF_FIELDS + 
			",(select u.UsrName from Usr u where WfRisk.CreateUserId = u.Id) AS CreateUsrName" +
			",(select sc.SchNm from SchCfg sc where WfRisk.ScheId = sc.SchId) AS SchNm" +
			",(select pd.PrjtNm from PrjtDef pd where pd.PrjtNo = WfRisk.PrjtNo) AS PrjtNm" +
			",(select wr.WfName from WfRd wr where WfRisk.wfNo = wr.wfNo) AS WfName";


	private static final long serialVersionUID = 7623051666003117953L;
	
	
	
	
	private String fileIcon;
	
	private String createUserName;
	
	private String prjtNm;
	
	private String schNm;
	
	private String wfName;
	
	private List<WfRiskReply> wfRiskReplyList;
	
	private String prjtNoList;
	public WfRisk(){
		super();
	}
	
	public WfRisk(String prjtNo, Integer scheId,
			String quesId, Integer deptId, String responsibleUserName,
			String title, String description, Integer riskLevel,
			Integer createUserId, Date createDate, Date lastUpdate,
			Integer lastUpdateUserId) {
		super(prjtNo, scheId,
				quesId,deptId,responsibleUserName,
				title, description,riskLevel,
				createUserId, createDate,lastUpdate,
				lastUpdateUserId );
	}
	
	/*public WfRisk(String prjtNo, Integer scheId,
			String quesId, Integer deptId, String responsibleUserName,
			String title, String description, Integer riskLevel,
			Integer createUserId, Date createDate, Date lastUpdate,
			Integer lastUpdateUserId,String riskConsequence,String preventiveMeasures,Date impTime) {
		super(prjtNo, scheId,
				quesId,deptId,responsibleUserName,
				title, description,riskLevel,
				createUserId, createDate,lastUpdate,
				lastUpdateUserId ,riskConsequence,preventiveMeasures,impTime);
	}*/

	public String getPrjtNoList() {
		return prjtNoList;
	}

	public void setPrjtNoList(String prjtNoList) {
		this.prjtNoList = prjtNoList;
	}

	public String getFileIcon() {
		return fileIcon;
	}

	public void setFileIcon(String fileIcon) {
		this.fileIcon = fileIcon;
	}

	public List<WfRiskReply> getWfRiskReplyList() {
		return wfRiskReplyList;
	}

	public void setWfRiskReplyList(List<WfRiskReply> wfRiskReplyList) {
		this.wfRiskReplyList = wfRiskReplyList;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getPrjtNm() {
		return prjtNm;
	}

	public void setPrjtNm(String prjtNm) {
		this.prjtNm = prjtNm;
	}

	public String getSchNm() {
		return schNm;
	}

	public void setSchNm(String schNm) {
		this.schNm = schNm;
	}

	public String getWfName() {
		return wfName;
	}

	public void setWfName(String wfName) {
		this.wfName = wfName;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
