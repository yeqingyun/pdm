package gnwf.vo;
public class QuesResp extends BasicQuesResp {
	public static final String ALL_FIELDS = "QuesResp.SolveNum,QuesResp.LastUpdDate,QuesResp.CreateBy,QuesResp.ResultFileNo,QuesResp.LastUpd,QuesResp.QuesId,QuesResp.Status,QuesResp.Result,QuesResp.ResultFileName,QuesResp.UsrId,QuesResp.CreateDate,QuesResp.IdtfRes,QuesResp.IdtfBy,QuesResp.IdtfResFileName,QuesResp.IdtfResFileNo,QuesResp.IdtfDate,QuesResp.ResultDate,QuesResp.Id,QuesResp.RemarkFileNo,QuesResp.RespType,QuesResp.RemarkFileName,QuesResp.Remark,QuesResp.RushDate,QuesResp.QuesAnalysis"
			                              +",(select u.UsrName from Usr u where QuesResp.UsrId = u.Id) AS UsrName"
	                                      +",(select u.UsrName from Usr u where QuesResp.IdtfBy = u.Id) AS IdtfUsrName";
	public static final String LIST_FIELDS = "QuesResp.SolveNum,QuesResp.LastUpdDate,QuesResp.CreateBy,QuesResp.ResultFileNo,QuesResp.LastUpd,QuesResp.QuesId,QuesResp.Status,QuesResp.Result,QuesResp.ResultFileName,QuesResp.UsrId,QuesResp.CreateDate,QuesResp.IdtfRes,QuesResp.IdtfBy,QuesResp.IdtfResFileName,QuesResp.IdtfResFileNo,QuesResp.IdtfDate,QuesResp.ResultDate,QuesResp.Id,QuesResp.RemarkFileNo,QuesResp.RespType,QuesResp.RemarkFileName,QuesResp.Remark,QuesResp.RushDate,QuesResp.QuesAnalysis"
										 +",(select u.UsrName from Usr u where QuesResp.UsrId = u.Id) AS UsrName"
									     +",(select u.UsrName from Usr u where QuesResp.IdtfBy = u.Id) AS IdtfUsrName";

	public static final String UsrId="QuesResp.UsrId";
	
	
	private String usrName;
    private String idtfUsrName;
    
    private String remarkFileIcon;
    private String idFileIcon;
	private String resFileIcon;
	
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public String getIdtfUsrName() {
		return idtfUsrName;
	}
	public void setIdtfUsrName(String idtfUsrName) {
		this.idtfUsrName = idtfUsrName;
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
	public String getRemarkFileIcon() {
		return remarkFileIcon;
	}
	public void setRemarkFileIcon(String remarkFileIcon) {
		this.remarkFileIcon = remarkFileIcon;
	}


}