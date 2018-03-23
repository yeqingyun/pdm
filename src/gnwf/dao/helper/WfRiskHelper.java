package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import gnwf.vo.WfRisk;
import gnwf.ww.MSG;
import gnwf.ww.workflow.WFUtil;

public class WfRiskHelper extends BasicWfRiskHelper {
	@Override
	public String getSqlCondition(WfRisk wfRisk) {
		String sql = super.getSqlCondition(wfRisk);
		return sql;
	}
	
	public List<WfRisk> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfRisk> list = new ArrayList<WfRisk>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfRisk wfRisk = new WfRisk();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("RiskId") || _fields[i].equals("WfRisk.RiskId"))
						wfRisk.setRiskId(rs.getString("RiskId"));
					else if(_fields[i].equals("ScheId") || _fields[i].equals("WfRisk.ScheId"))
						wfRisk.setScheId(rs.getInt("ScheId"));
					else if(_fields[i].equals("PrjtNo") || _fields[i].equals("WfRisk.PrjtNo"))
						wfRisk.setPrjtNo(rs.getString("PrjtNo"));
					else if(_fields[i].equals("QuesId") || _fields[i].equals("WfRisk.QuesId"))
						wfRisk.setQuesId(rs.getString("QuesId"));
					else if(_fields[i].equals("CateId") || _fields[i].equals("WfRisk.CateId"))
						wfRisk.setCateId(rs.getInt("CateId"));
					else if(_fields[i].equals("DeptId") || _fields[i].equals("WfRisk.DeptId"))
						wfRisk.setDeptId(rs.getInt("DeptId"));
					else if(_fields[i].equals("ResponsibleUserName") || _fields[i].equals("WfRisk.ResponsibleUserName"))
						wfRisk.setResponsibleUserName(rs.getString("ResponsibleUserName"));
					else if(_fields[i].equals("Title") || _fields[i].equals("WfRisk.Title"))
						wfRisk.setTitle(rs.getString("Title"));
					else if(_fields[i].equals("Description") || _fields[i].equals("WfRisk.Description"))
						wfRisk.setDescription(rs.getString("Description"));
					else if(_fields[i].equals("RiskLevel") || _fields[i].equals("WfRisk.RiskLevel"))
						wfRisk.setRiskLevel(rs.getInt("RiskLevel"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfRisk.Status"))
						wfRisk.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateUserId") || _fields[i].equals("WfRisk.CreateUserId"))
						wfRisk.setCreateUserId(rs.getInt("CreateUserId"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfRisk.WfNo"))
						wfRisk.setWfNo(rs.getString("WfNo"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfRisk.CreateDate"))
						wfRisk.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpdate") || _fields[i].equals("WfRisk.LastUpdate"))
						wfRisk.setLastUpdate(rs.getTimestamp("LastUpdate"));
					else if(_fields[i].equals("LastUpdateUserId") || _fields[i].equals("WfRisk.LastUpdateUserId"))
						wfRisk.setLastUpdateUserId(rs.getInt("LastUpdateUserId"));
					else if(_fields[i].equals("FileNo") || _fields[i].equals("WfRisk.FileNo"))
						wfRisk.setFileNo(rs.getString("FileNo"));
					else if(_fields[i].equals("FileName") || _fields[i].equals("WfRisk.FileName"))
						wfRisk.setFileName(rs.getString("FileName"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("WfRisk.Remark"))
						wfRisk.setRemark(rs.getString("Remark"));
					
					else if(_fields[i].equals("RiskConsequence") || _fields[i].equals("WfRisk.RiskConsequence"))
						wfRisk.setRiskConsequence(rs.getString("RiskConsequence"));
					else if(_fields[i].equals("PreventiveMeasures") || _fields[i].equals("WfRisk.PreventiveMeasures"))
						wfRisk.setPreventiveMeasures(rs.getString("PreventiveMeasures"));
					else if(_fields[i].equals("ImpTime") || _fields[i].equals("WfRisk.ImpTime"))
						wfRisk.setImpTime(rs.getString("ImpTime"));
					
					else if(_fields[i].equals("RiskMonitor") || _fields[i].equals("WfRisk.RiskMonitor"))
						wfRisk.setRiskMonitor(rs.getString("RiskMonitor"));
					
					else if(_fields[i].equals("RiskCategory") || _fields[i].equals("WfRisk.RiskCategory"))
						wfRisk.setRiskCategory(rs.getString("RiskCategory"));
					else if(_fields[i].equals("ExecutionDate") || _fields[i].equals("WfRisk.ExecutionDate"))
						wfRisk.setExecutionDate(rs.getString("ExecutionDate"));
					else if(_fields[i].equals("RiskText") || _fields[i].equals("WfRisk.RiskText"))
						wfRisk.setRiskText(rs.getString("RiskText"));
					else if(_fields[i].equals("DeptName") || _fields[i].equals("WfRisk.DeptName"))
						wfRisk.setDeptName(rs.getString("DeptName"));
					else if(_fields[i].equals("ResponsibleUserId") || _fields[i].equals("WfRisk.ResponsibleUserId"))
						wfRisk.setResponsibleUserId(rs.getString("ResponsibleUserId"));
					
					else if(_fields[i].indexOf("AS CreateUsrName") > -1) {
						wfRisk.setCreateUserName(rs.getString("CreateUsrName"));
					}
					else if(_fields[i].indexOf("AS SchNm") > -1) {
						wfRisk.setSchNm(rs.getString("SchNm"));
					}
					else if(_fields[i].indexOf("AS PrjtNm") > -1) {
						wfRisk.setPrjtNm(rs.getString("PrjtNm"));
					}
					else if(_fields[i].indexOf("AS WfName") > -1) {
						wfRisk.setWfName(rs.getString("WfName"));
					}
					
					if(WFUtil.isNotNull(wfRisk.getFileName())){
						String uriLink = wfRisk.getFileName().toLowerCase();
						if(uriLink.endsWith(".doc") || uriLink.endsWith(".docx")){
							wfRisk.setFileIcon(MSG.OWF_ICON_DOC);
						}else if(uriLink.endsWith(".xls") || uriLink.endsWith(".xlsx")){
							wfRisk.setFileIcon(MSG.OWF_ICON_XLS);
						}else if(uriLink.endsWith(".ppt") || uriLink.endsWith(".pptx")){
							wfRisk.setFileIcon(MSG.OWF_ICON_PPT);
						}else if(uriLink.endsWith(".txt")){
							wfRisk.setFileIcon(MSG.OWF_ICON_TXT);
						}else if(uriLink.endsWith(".pdf")){
							wfRisk.setFileIcon(MSG.OWF_ICON_PDF);
						}else if(uriLink.endsWith(".jpg") || uriLink.endsWith(".jpeg") 
								|| uriLink.endsWith(".png") || uriLink.endsWith(".bmp") || uriLink.endsWith(".gif")){
							wfRisk.setFileIcon(MSG.OWF_ICON_JPG);
						}else if(uriLink.endsWith(".rar")){
							wfRisk.setFileIcon(MSG.OWF_ICON_RAR);
						}else{
							wfRisk.setFileIcon(MSG.OWF_ICON_FILE);
						}
					}
				}
				list.add(wfRisk);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfQuesHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}