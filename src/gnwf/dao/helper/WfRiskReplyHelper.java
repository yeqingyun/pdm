package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import gnwf.vo.WfRiskReply;
import gnwf.ww.MSG;
import gnwf.ww.workflow.WFUtil;

public class WfRiskReplyHelper extends BasicWfRiskReplyHelper {

	public List<WfRiskReply> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfRiskReply> list = new ArrayList<WfRiskReply>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfRiskReply wfRiskReply = new WfRiskReply();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("WfRiskReply.Id"))
						wfRiskReply.setId(rs.getInt("Id"));
					else if(_fields[i].equals("ResponsibleUserId") || _fields[i].equals("WfRiskReply.ResponsibleUserId"))
						wfRiskReply.setResponsibleUserId(rs.getInt("ResponsibleUserId"));
					else if(_fields[i].equals("Measures") || _fields[i].equals("WfRiskReply.Measures"))
						wfRiskReply.setMeasures(rs.getString("Measures"));
					else if(_fields[i].equals("MeasuresFileNo") || _fields[i].equals("WfRiskReply.MeasuresFileNo"))
						wfRiskReply.setMeasuresFileNo(rs.getString("MeasuresFileNo"));
					else if(_fields[i].equals("MeasuresFileName") || _fields[i].equals("WfRiskReply.MeasuresFileName"))
						wfRiskReply.setMeasuresFileName(rs.getString("MeasuresFileName"));
					else if(_fields[i].equals("MeasuresDate") || _fields[i].equals("WfRiskReply.MeasuresDate"))
						wfRiskReply.setMeasuresDate(rs.getTimestamp("MeasuresDate"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfRiskReply.Status"))
						wfRiskReply.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("VerifyUserId") || _fields[i].equals("WfRiskReply.VerifyUserId"))
						wfRiskReply.setVerifyUserId(rs.getInt("VerifyUserId"));
					else if(_fields[i].equals("VerifyResult") || _fields[i].equals("WfRiskReply.VerifyResult"))
						wfRiskReply.setVerifyResult(rs.getString("VerifyResult"));
					else if(_fields[i].equals("VerifyDate") || _fields[i].equals("WfRiskReply.VerifyDate"))
						wfRiskReply.setVerifyDate(rs.getTimestamp("VerifyDate"));
					else if(_fields[i].equals("VerifyFileNo") || _fields[i].equals("WfRiskReply.VerifyFileNo"))
						wfRiskReply.setVerifyFileNo(rs.getString("VerifyFileNo"));
					else if(_fields[i].equals("VerifyFileName") || _fields[i].equals("WfRiskReply.VerifyFileName"))
						wfRiskReply.setVerifyFileName(rs.getString("VerifyFileName"));
					else if(_fields[i].equals("CreateUserId") || _fields[i].equals("WfRiskReply.CreateUserId"))
						wfRiskReply.setCreateUserId(rs.getInt("CreateUserId"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRiskReply.CreateDate"))
						wfRiskReply.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpdate") || _fields[i].equals("WfRiskReply.LastUpdate"))
						wfRiskReply.setLastUpdate(rs.getTimestamp("LastUpdate"));
					else if(_fields[i].equals("LastUpdateUserId") || _fields[i].equals("WfRiskReply.LastUpdateUserId"))
						wfRiskReply.setLastUpdateUserId(rs.getInt("LastUpdateUserId"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("WfRiskReply.Remark"))
						wfRiskReply.setRemark(rs.getString("Remark"));
					else if(_fields[i].equals("RiskId") || _fields[i].equals("WfRiskReply.RiskId"))
						wfRiskReply.setRiskId(rs.getString("RiskId"));
					else if(_fields[i].indexOf("AS ResponsibleUserName") > -1) {
						wfRiskReply.setResponsibleUserName(rs.getString("ResponsibleUserName"));
					}
					else if(_fields[i].equals("RushDate") || _fields[i].equals("WfRiskReply.RushDate"))
						wfRiskReply.setRushDate(rs.getTimestamp("RushDate"));
					
					
					if(WFUtil.isNotNull(wfRiskReply.getMeasuresFileName())){
						String uriLink = wfRiskReply.getMeasuresFileName().toLowerCase();
						if(uriLink.endsWith(".doc") || uriLink.endsWith(".docx")){
							wfRiskReply.setMeasuresFileIcon(MSG.OWF_ICON_DOC);
						}else if(uriLink.endsWith(".xls") || uriLink.endsWith(".xlsx")){
							wfRiskReply.setMeasuresFileIcon(MSG.OWF_ICON_XLS);
						}else if(uriLink.endsWith(".ppt") || uriLink.endsWith(".pptx")){
							wfRiskReply.setMeasuresFileIcon(MSG.OWF_ICON_PPT);
						}else if(uriLink.endsWith(".txt")){
							wfRiskReply.setMeasuresFileIcon(MSG.OWF_ICON_TXT);
						}else if(uriLink.endsWith(".pdf")){
							wfRiskReply.setMeasuresFileIcon(MSG.OWF_ICON_PDF);
						}else if(uriLink.endsWith(".jpg") || uriLink.endsWith(".jpeg") 
								|| uriLink.endsWith(".png") || uriLink.endsWith(".bmp") || uriLink.endsWith(".gif")){
							wfRiskReply.setMeasuresFileIcon(MSG.OWF_ICON_JPG);
						}else if(uriLink.endsWith(".rar")){
							wfRiskReply.setMeasuresFileIcon(MSG.OWF_ICON_RAR);
						}else{
							wfRiskReply.setMeasuresFileIcon(MSG.OWF_ICON_FILE);
						}
					}
					if(WFUtil.isNotNull(wfRiskReply.getVerifyFileName())){
						String uriLink = wfRiskReply.getVerifyFileName().toLowerCase();
						if(uriLink.endsWith(".doc") || uriLink.endsWith(".docx")){
							wfRiskReply.setVerifyFileIcon(MSG.OWF_ICON_DOC);
						}else if(uriLink.endsWith(".xls") || uriLink.endsWith(".xlsx")){
							wfRiskReply.setVerifyFileIcon(MSG.OWF_ICON_XLS);
						}else if(uriLink.endsWith(".ppt") || uriLink.endsWith(".pptx")){
							wfRiskReply.setVerifyFileIcon(MSG.OWF_ICON_PPT);
						}else if(uriLink.endsWith(".txt")){
							wfRiskReply.setVerifyFileIcon(MSG.OWF_ICON_TXT);
						}else if(uriLink.endsWith(".pdf")){
							wfRiskReply.setVerifyFileIcon(MSG.OWF_ICON_PDF);
						}else if(uriLink.endsWith(".jpg") || uriLink.endsWith(".jpeg") 
								|| uriLink.endsWith(".png") || uriLink.endsWith(".bmp") || uriLink.endsWith(".gif")){
							wfRiskReply.setVerifyFileIcon(MSG.OWF_ICON_JPG);
						}else if(uriLink.endsWith(".rar")){
							wfRiskReply.setVerifyFileIcon(MSG.OWF_ICON_RAR);
						}else{
							wfRiskReply.setVerifyFileIcon(MSG.OWF_ICON_FILE);
						}
					}
				}
				list.add(wfRiskReply);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRiskReplyHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}