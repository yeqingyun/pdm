package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfDoc;
import gnwf.ww.MSG;
import gnwf.ww.workflow.WFUtil;

public class WfDocHelper extends BasicWfDocHelper {
	public String getSqlString() {
		return " from WfDoc " +
               " left join WfRdTask on (WfRdTask.TaskId = WfDoc.TaskId) " + 
               " left join WfRd on (WfRd.WfNo = WfDoc.WfNo) " + 
               " left join WfStep on(WfRdTask.StepId=WfStep.StepId) " +
              // " left join usr on (WfDoc.CreateBy = usr.id) " +
               "left join ( select id uid, Usr.UsrName FROM Usr WHERE Id in "
             + "(SELECT ww.CreateBy FROM WfDoc ww WHERE ww.WfNo =ww.WfNo)) Usr on Usr.uid = WfDoc.CreateBy"+
               " left join WfScheCfgDoc on(WfScheCfgDoc.DocId=WfDoc.CateId) " +
               " left join ProcFile on(ProcFile.FileNo=WfDoc.FileNo) " +
               " where 1=1 ";
	}
	
	public String getOrderBy() {
		return " order by WfDoc.CreateDate desc";
	}

	public List<WfDoc> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfDoc> list = new ArrayList<WfDoc>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfDoc wfDoc = new WfDoc();
				for(int i=0;i<_fields.length;i++) {
					
					if(_fields[i].equals("DocId") || _fields[i].equals("WfDoc.DocId"))
						wfDoc.setDocId(new Integer(rs.getInt("DocId")));
					else if(_fields[i].equals("FileNo") || _fields[i].equals("WfDoc.FileNo"))
						wfDoc.setFileNo(rs.getString("FileNo"));
					else if(_fields[i].equals("downloadStatus"))
						wfDoc.setDownloadStatus(rs.getInt("downloadStatus"));
					else if(_fields[i].equals("TaskId") || _fields[i].equals("WfDoc.TaskId"))
						wfDoc.setTaskId(new Integer(rs.getInt("TaskId")));
					else if(_fields[i].equals("CateId") || _fields[i].equals("WfDoc.CateId"))
						wfDoc.setCateId(new Integer(rs.getInt("CateId")));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfDoc.WfNo"))
						wfDoc.setWfNo(rs.getString("WfNo"));
					else if(_fields[i].equals("DocName") || _fields[i].equals("WfDoc.DocName"))
						wfDoc.setDocName(rs.getString("DocName"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfDoc.Status")|| _fields[i].equals("BaseLib.Status as Status"))
						wfDoc.setStatus(new Integer(rs.getInt("Status")));
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfDoc.FlowId"))
						wfDoc.setFlowId(new Integer(rs.getInt("FlowId")));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfDoc.CreateBy"))
						wfDoc.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfDoc.CreateDate"))
						wfDoc.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfDoc.LastUpd"))
						wfDoc.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfDoc.LastUpdDate"))
						wfDoc.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					else if(_fields[i].equals("UsrName") || _fields[i].equals("Usr.UsrName"))
						wfDoc.setCreateName(rs.getString("UsrName"));
					else if(_fields[i].equals("CateName") || _fields[i].equals("WfScheCfgDoc.DocName as CateName"))
						wfDoc.setCateName(rs.getString("CateName"));
					else if(_fields[i].equals("StepName") || _fields[i].equals("WfStep.StepName"))
						wfDoc.setStepName(rs.getString("StepName"));
					else if(_fields[i].equals("DeptDocId") || _fields[i].equals("WfDoc.DeptDocId"))
						wfDoc.setDeptDocId(rs.getInt("DeptDocId"));
					else if(_fields[i].equals("WfName") || _fields[i].equals("WfRd.WfName as WfName"))
						wfDoc.setWfName(rs.getString("WfName"));
					else if(_fields[i].equals("DocVer") || _fields[i].equals("WfDoc.DocVer"))
						wfDoc.setDocVer(rs.getString("DocVer"));
//					else if(_fields[i].equals("FileVer") || _fields[i].equals("ProcFile.FileVer"))
//						wfDoc.setDocVer(rs.getString("FileVer"));
					else if(_fields[i].equals("ProcFile.Status as IsBase"))
						wfDoc.setIsBase(rs.getInt("IsBase"));
					
					
				
					else if(_fields[i].equals("DocType") || _fields[i].equals("WfDoc.DocType"))
						wfDoc.setDocType(rs.getInt("DocType"));
					
					else if(_fields[i].equals("ProjectNo") || _fields[i].equals("WfDoc.ProjectNo") || _fields[i].equals("WfRd.ProjectNo as ProjectNo"))
						wfDoc.setProjectNo(rs.getString("ProjectNo"));
					
					else if(_fields[i].equals("UriLink") || _fields[i].equals("ProcFile.UriLink")){
						wfDoc.setUriLink(rs.getString("UriLink"));
					}
					
					if(WFUtil.isNull(wfDoc.getUriLink()) && _fields[i].equals("Wfdoc.UriLink")){
						wfDoc.setUriLink(rs.getString("UriLink"));
					}
					
					if(WFUtil.isNotNull(wfDoc.getDocName())){
						String uriLink = wfDoc.getDocName().toLowerCase();
						if(uriLink.endsWith(".doc") || uriLink.endsWith(".docx")){
							wfDoc.setIcon(MSG.OWF_ICON_DOC);
						}else if(uriLink.endsWith(".xls") || uriLink.endsWith(".xlsx")){
							wfDoc.setIcon(MSG.OWF_ICON_XLS);
						}else if(uriLink.endsWith(".ppt") || uriLink.endsWith(".pptx")){
							wfDoc.setIcon(MSG.OWF_ICON_PPT);
						}else if(uriLink.endsWith(".txt")){
							wfDoc.setIcon(MSG.OWF_ICON_TXT);
						}else if(uriLink.endsWith(".pdf")){
							wfDoc.setIcon(MSG.OWF_ICON_PDF);
						}else if(uriLink.endsWith(".jpg") || uriLink.endsWith(".jpeg") 
								|| uriLink.endsWith(".png") || uriLink.endsWith(".bmp") || uriLink.endsWith(".gif")){
							wfDoc.setIcon(MSG.OWF_ICON_JPG);
						}else if(uriLink.endsWith(".rar")){
							wfDoc.setIcon(MSG.OWF_ICON_RAR);
						}else{
							wfDoc.setIcon(MSG.OWF_ICON_FILE);
						}
					}
					
				}
				list.add(wfDoc);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfDocHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}