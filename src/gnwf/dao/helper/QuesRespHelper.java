package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.QuesResp;
import gnwf.ww.MSG;
import gnwf.ww.workflow.WFUtil;

public class QuesRespHelper extends BasicQuesRespHelper {
	public String getSqlString() {
		return " from QuesResp " +
				 " left join Usr on QuesResp.UsrId = Usr.Id" +
	             " where 1=1 and QuesResp.RespType = "+MSG.QUESRESP_TYPE_NEW;
	}

	public List<QuesResp> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<QuesResp> list = new ArrayList<QuesResp>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				QuesResp quesResp = new QuesResp();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("QuesResp.LastUpdDate"))
						quesResp.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("QuesResp.CreateBy"))
						quesResp.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("ResultFileNo") || _fields[i].equals("QuesResp.ResultFileNo"))
						quesResp.setResultFileNo(rs.getString("ResultFileNo"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("QuesResp.LastUpd"))
						quesResp.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("QuesId") || _fields[i].equals("QuesResp.QuesId"))
						quesResp.setQuesId(rs.getString("QuesId"));
					else if(_fields[i].equals("Status") || _fields[i].equals("QuesResp.Status"))
						quesResp.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("Result") || _fields[i].equals("QuesResp.Result"))
						quesResp.setResult(rs.getString("Result"));
					
					else if(_fields[i].equals("QuesAnalysis") || _fields[i].equals("QuesResp.QuesAnalysis"))
						quesResp.setQuesAnalysis(rs.getString("QuesAnalysis"));
					
					else if(_fields[i].equals("ResultFileName") || _fields[i].equals("QuesResp.ResultFileName"))
						quesResp.setResultFileName(rs.getString("ResultFileName"));
					else if(_fields[i].equals("UsrId") || _fields[i].equals("QuesResp.UsrId"))
						quesResp.setUsrId(rs.getInt("UsrId"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("QuesResp.CreateDate"))
						quesResp.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("IdtfRes") || _fields[i].equals("QuesResp.IdtfRes"))
						quesResp.setIdtfRes(rs.getString("IdtfRes"));
					else if(_fields[i].equals("IdtfBy") || _fields[i].equals("QuesResp.IdtfBy"))
						quesResp.setIdtfBy(rs.getInt("IdtfBy"));
					else if(_fields[i].equals("IdtfResFileName") || _fields[i].equals("QuesResp.IdtfResFileName"))
						quesResp.setIdtfResFileName(rs.getString("IdtfResFileName"));
					else if(_fields[i].equals("IdtfResFileNo") || _fields[i].equals("QuesResp.IdtfResFileNo"))
						quesResp.setIdtfResFileNo(rs.getString("IdtfResFileNo"));
					else if(_fields[i].equals("IdtfDate") || _fields[i].equals("QuesResp.IdtfDate"))
						quesResp.setIdtfDate(rs.getTimestamp("IdtfDate"));
					else if(_fields[i].equals("ResultDate") || _fields[i].equals("QuesResp.ResultDate"))
						quesResp.setResultDate(rs.getTimestamp("ResultDate"));
					else if(_fields[i].equals("Id") || _fields[i].equals("QuesResp.Id"))
						quesResp.setId(rs.getInt("Id"));
					else if(_fields[i].equals("RemarkFileNo") || _fields[i].equals("QuesResp.RemarkFileNo"))
						quesResp.setRemarkFileNo(rs.getString("RemarkFileNo"));
					else if(_fields[i].equals("RespType") || _fields[i].equals("QuesResp.RespType"))
						quesResp.setRespType(rs.getInt("RespType"));
					else if(_fields[i].equals("RemarkFileName") || _fields[i].equals("QuesResp.RemarkFileName"))
						quesResp.setRemarkFileName(rs.getString("RemarkFileName"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("QuesResp.Remark"))
						quesResp.setRemark(rs.getString("Remark"));
					else if(_fields[i].equals("RushDate") || _fields[i].equals("QuesResp.RushDate"))
						quesResp.setRushDate(rs.getTimestamp("RushDate"));
					else if(_fields[i].equals("SolveNum") || _fields[i].equals("QuesResp.SolveNum"))
						quesResp.setSolveNum(rs.getInt("SolveNum"));
					else if(_fields[i].indexOf("AS UsrName") > -1) {
						quesResp.setUsrName(rs.getString("UsrName"));
					}else if(_fields[i].indexOf("AS IdtfUsrName") > -1) {
						quesResp.setIdtfUsrName(rs.getString("IdtfUsrName"));
					}
					
					if(WFUtil.isNotNull(quesResp.getResultFileName())){
						String uriLink = quesResp.getResultFileName().toLowerCase();
						if(uriLink.endsWith(".doc") || uriLink.endsWith(".docx")){
							quesResp.setResFileIcon(MSG.OWF_ICON_DOC);
						}else if(uriLink.endsWith(".xls") || uriLink.endsWith(".xlsx")){
							quesResp.setResFileIcon(MSG.OWF_ICON_XLS);
						}else if(uriLink.endsWith(".ppt") || uriLink.endsWith(".pptx")){
							quesResp.setResFileIcon(MSG.OWF_ICON_PPT);
						}else if(uriLink.endsWith(".txt")){
							quesResp.setResFileIcon(MSG.OWF_ICON_TXT);
						}else if(uriLink.endsWith(".pdf")){
							quesResp.setResFileIcon(MSG.OWF_ICON_PDF);
						}else if(uriLink.endsWith(".jpg") || uriLink.endsWith(".jpeg") 
								|| uriLink.endsWith(".png") || uriLink.endsWith(".bmp") || uriLink.endsWith(".gif")){
							quesResp.setResFileIcon(MSG.OWF_ICON_JPG);
						}else if(uriLink.endsWith(".rar")){
							quesResp.setResFileIcon(MSG.OWF_ICON_RAR);
						}else{
							quesResp.setResFileIcon(MSG.OWF_ICON_FILE);
						}
					}
					if(WFUtil.isNotNull(quesResp.getIdtfResFileName())){
						String uriLink = quesResp.getIdtfResFileName().toLowerCase();
						if(uriLink.endsWith(".doc") || uriLink.endsWith(".docx")){
							quesResp.setIdFileIcon(MSG.OWF_ICON_DOC);
						}else if(uriLink.endsWith(".xls") || uriLink.endsWith(".xlsx")){
							quesResp.setIdFileIcon(MSG.OWF_ICON_XLS);
						}else if(uriLink.endsWith(".ppt") || uriLink.endsWith(".pptx")){
							quesResp.setIdFileIcon(MSG.OWF_ICON_PPT);
						}else if(uriLink.endsWith(".txt")){
							quesResp.setIdFileIcon(MSG.OWF_ICON_TXT);
						}else if(uriLink.endsWith(".pdf")){
							quesResp.setIdFileIcon(MSG.OWF_ICON_PDF);
						}else if(uriLink.endsWith(".jpg") || uriLink.endsWith(".jpeg") 
								|| uriLink.endsWith(".png") || uriLink.endsWith(".bmp") || uriLink.endsWith(".gif")){
							quesResp.setIdFileIcon(MSG.OWF_ICON_JPG);
						}else if(uriLink.endsWith(".rar")){
							quesResp.setIdFileIcon(MSG.OWF_ICON_RAR);
						}else{
							quesResp.setIdFileIcon(MSG.OWF_ICON_FILE);
						}
					}
					
					if(WFUtil.isNotNull(quesResp.getResultFileName())){
						String uriLink = quesResp.getResultFileName().toLowerCase();
						if(uriLink.endsWith(".doc") || uriLink.endsWith(".docx")){
							quesResp.setRemarkFileIcon(MSG.OWF_ICON_DOC);
						}else if(uriLink.endsWith(".xls") || uriLink.endsWith(".xlsx")){
							quesResp.setRemarkFileIcon(MSG.OWF_ICON_XLS);
						}else if(uriLink.endsWith(".ppt") || uriLink.endsWith(".pptx")){
							quesResp.setRemarkFileIcon(MSG.OWF_ICON_PPT);
						}else if(uriLink.endsWith(".txt")){
							quesResp.setRemarkFileIcon(MSG.OWF_ICON_TXT);
						}else if(uriLink.endsWith(".pdf")){
							quesResp.setRemarkFileIcon(MSG.OWF_ICON_PDF);
						}else if(uriLink.endsWith(".jpg") || uriLink.endsWith(".jpeg") 
								|| uriLink.endsWith(".png") || uriLink.endsWith(".bmp") || uriLink.endsWith(".gif")){
							quesResp.setRemarkFileIcon(MSG.OWF_ICON_JPG);
						}else if(uriLink.endsWith(".rar")){
							quesResp.setRemarkFileIcon(MSG.OWF_ICON_RAR);
						}else{
							quesResp.setRemarkFileIcon(MSG.OWF_ICON_FILE);
						}
					}
				}
				list.add(quesResp);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("QuesRespHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}