package gnwf.vo;

import java.util.Date;

public class WfRiskReply extends BasicWfRiskReply {

	private static final long serialVersionUID = 8848521194547107037L;
	
	public static final String LIST_FIELDS = SELF_FIELDS  
											+ ",(select u.UsrName from Usr u where WfRiskReply.ResponsibleUserId = u.Id) AS ResponsibleUserName";
	
	private String responsibleUserName;
	
	private String measuresFileIcon;
	private String VerifyFileIcon;
	
	
	
	
	public WfRiskReply() {
		super();
	}
	
	public WfRiskReply(Integer id, Integer responsibleUserId,
			Integer status,
			Integer createUserId, Date createDate,
			Date lastUpdate, Integer lastUpdateUserId, String riskId) {
		super(id,responsibleUserId,
				status,
				createUserId,createDate,
				lastUpdate,lastUpdateUserId,riskId);
	}
	
	
	public String getMeasuresFileIcon() {
		return measuresFileIcon;
	}
	public void setMeasuresFileIcon(String measuresFileIcon) {
		this.measuresFileIcon = measuresFileIcon;
	}
	public String getVerifyFileIcon() {
		return VerifyFileIcon;
	}
	public void setVerifyFileIcon(String verifyFileIcon) {
		VerifyFileIcon = verifyFileIcon;
	}
	public String getResponsibleUserName() {
		return responsibleUserName;
	}
	public void setResponsibleUserName(String responsibleUserName) {
		this.responsibleUserName = responsibleUserName;
	}
	
	

}
