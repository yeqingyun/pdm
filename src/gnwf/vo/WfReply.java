package gnwf.vo;


public class WfReply extends BasicWfReply {
	public static final String ALL_FIELDS = "WfReply.GroupId,WfReply.RepLyType,WfReply.FileName,WfReply.FileNo,WfReply.ReplyId,WfReply.QuesId,WfReply.UserId,WfReply.Status,WfReply.CreateBy,WfReply.LastUpd,WfReply.CreateDate,WfReply.LastUpdDate,WfReply.ReplyText,WfQues.QuesId as QuesIdFk,Usr.UsrName";
	public static final String LIST_FIELDS = "WfReply.GroupId,WfReply.RepLyType,WfReply.FileName,WfReply.FileNo,WfReply.ReplyId,WfReply.QuesId,WfReply.UserId,WfReply.Status,WfReply.CreateBy,WfReply.LastUpd,WfReply.CreateDate,WfReply.LastUpdDate,WfReply.ReplyText,WfQues.QuesId as QuesIdFk,Usr.UsrName";


	private Integer quesIdFk;
	private String usrName;
	private String fileIcon;
	private Boolean showFileIcon;
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public Integer getQuesIdFk() {
		return quesIdFk;
	}
	public void setQuesIdFk(Integer quesIdFk) {
		this.quesIdFk = quesIdFk;
	}
	public String getFileIcon() {
		return fileIcon;
	}
	public void setFileIcon(String fileIcon) {
		this.fileIcon = fileIcon;
	}
	public Boolean getShowFileIcon() {
		return showFileIcon;
	}
	public void setShowFileIcon(Boolean showFileIcon) {
		this.showFileIcon = showFileIcon;
	}

}