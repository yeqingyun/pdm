package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfReply;
import gnwf.ww.MSG;
import gnwf.ww.workflow.WFUtil;

public class WfReplyHelper extends BasicWfReplyHelper {
	public String getSqlString() {
		return " from WfReply " +
               " inner join WfQues on (WfQues.QuesId = WfReply.QuesId) " + 
               " inner join Usr on Usr.Id = WfReply.CreateBy" +
               " where 1=1 ";
	}

	public List<WfReply> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfReply> list = new ArrayList<WfReply>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfReply wfReply = new WfReply();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ReplyId") || _fields[i].equals("WfReply.ReplyId"))
						wfReply.setReplyId(rs.getInt("ReplyId"));
					else if(_fields[i].equals("QuesId") || _fields[i].equals("WfReply.QuesId"))
						wfReply.setQuesId(rs.getString("QuesId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfReply.UserId"))
						wfReply.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfReply.Status"))
						wfReply.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfReply.CreateBy"))
						wfReply.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfReply.LastUpd"))
						wfReply.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfReply.CreateDate"))
						wfReply.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfReply.LastUpdDate"))
						wfReply.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					else if(_fields[i].equals("ReplyText") || _fields[i].equals("WfReply.ReplyText"))
						wfReply.setReplyText(rs.getString("ReplyText"));

					else if(_fields[i].equals("QuesIdFk") || _fields[i].equals("WfQues.QuesId as QuesIdFk"))
						wfReply.setQuesIdFk(rs.getInt("QuesIdFk"));
					else if(_fields[i].equals("UsrName") || _fields[i].equals("Usr.UsrName"))
						wfReply.setUsrName(rs.getString("UsrName"));
					
					else if(_fields[i].equals("FileNo") || _fields[i].equals("WfReply.FileNo"))
						wfReply.setFileNo(rs.getString("FileNo"));
					else if(_fields[i].equals("FileName") || _fields[i].equals("WfReply.FileName"))
						wfReply.setFileName(rs.getString("FileName"));
					else if(_fields[i].equals("GroupId") || _fields[i].equals("WfReply.GroupId"))
						wfReply.setGroupId(rs.getInt("GroupId"));
					else if(_fields[i].equals("RepLyType") || _fields[i].equals("WfReply.RepLyType"))
						wfReply.setRepLyType(rs.getInt("RepLyType"));
					
					
					if(WFUtil.isNotNull(wfReply.getFileName())){
						wfReply.setShowFileIcon(true);
						String uriLink = wfReply.getFileName().toLowerCase();
						if(uriLink.endsWith(".doc") || uriLink.endsWith(".docx")){
							wfReply.setFileIcon(MSG.OWF_ICON_DOC);
						}else if(uriLink.endsWith(".xls") || uriLink.endsWith(".xlsx")){
							wfReply.setFileIcon(MSG.OWF_ICON_XLS);
						}else if(uriLink.endsWith(".ppt") || uriLink.endsWith(".pptx")){
							wfReply.setFileIcon(MSG.OWF_ICON_PPT);
						}else if(uriLink.endsWith(".txt")){
							wfReply.setFileIcon(MSG.OWF_ICON_TXT);
						}else if(uriLink.endsWith(".pdf")){
							wfReply.setFileIcon(MSG.OWF_ICON_PDF);
						}else if(uriLink.endsWith(".jpg") || uriLink.endsWith(".jpeg") 
								|| uriLink.endsWith(".png") || uriLink.endsWith(".bmp") || uriLink.endsWith(".gif")){
							wfReply.setFileIcon(MSG.OWF_ICON_JPG);
						}else if(uriLink.endsWith(".rar")){
							wfReply.setFileIcon(MSG.OWF_ICON_RAR);
						}else{
							wfReply.setFileIcon(MSG.OWF_ICON_FILE);
						}
					}

				}
				list.add(wfReply);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfReplyHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}