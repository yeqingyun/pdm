package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfQues;
import gnwf.ww.MSG;
import gnwf.ww.workflow.WFUtil;

public class WfQuesHelper extends BasicWfQuesHelper {
	/*//获取带项目流程问题
	public String getSqlString2() {
		return " from WfQues " +
			   " left join Usr on WfQues.UserId = Usr.Id" +
			   " inner join WfRd on WfQues.WfNo = WfRd.WfNo" +
               " where 1=1 and WfQues.Status <>"+MSG.WFQUES_STATUS_0;
	}*/
	//获取全部问题
	
	public String getSqlString() {
		
		return " from WfQues " +
			   " left join Usr on WfQues.UserId = Usr.Id" +
               " where 1=1 and WfQues.Status <>"+MSG.WFQUES_STATUS_0;
	}
	/*//获取不带项目流程问题
		public String getSqlString1() {
			return " from WfQues " +
				   " left join Usr on WfQues.UserId = Usr.Id" +
	               " where 1=1 and WfQues.WfNo = ' ' and WfQues.Status <>"+MSG.WFQUES_STATUS_0;
		}*/
	@Override
	public String getSqlCondition(WfQues wfQues) {
		String sql = super.getSqlCondition(wfQues);
		if(null != wfQues.getWfNos() && !wfQues.getWfNos().trim().equals(""))
			sql += " and WfQues.WfNo IN ("+wfQues.getWfNos()+")";
		if(null != wfQues.getTypId())
			sql += " and exists (select 1 from Task sc,PrjtDef pd where " +
					"WfQues.ScheId = sc.TaskNo and sc.PrjtNo = pd.PrjtNo  and pd.TypId = "+wfQues.getTypId()+")";
		if( null != wfQues.getSelType() ){
			if(wfQues.getSelType()==1){ //查询未关闭的问题
				sql += " ";
			}else if (wfQues.getSelType()==2) {
				sql += "and WfQues.WfNo <> ' ' ";
			}else if (wfQues.getSelType()==3) {
				sql += " and WfQues.WfNo = ' ' ";
			}
		}
		
	/*	if(null != wfQues.getDeptNm())
			sql += " and deptNm like '%"+wfQues.getDeptNm()+"%' ";*/
		
		if(wfQues.getSelectType()!=null ){
			switch(wfQues.getSelectType()){
				case MSG.WFQUES_SELECT_TYPE_0:{
					sql+=" and WfQues.UserId="+wfQues.getCurrentUsr()+" or WfQues.CreateBy="+wfQues.getCurrentUsr();
					break;
				}
				case MSG.WFQUES_SELECT_TYPE_1:{
					sql+=" and WfQues.Status<"+MSG.WFQUES_STATUS_10+" and WfQues.Status>"+MSG.WFQUES_STATUS_0 +" and WfQues.UserId="+wfQues.getCurrentUsr();
					break;
				}
				case MSG.WFQUES_SELECT_TYPE_2:{
					sql+=" and WfQues.Status>="+MSG.WFQUES_STATUS_10+" and WfQues.UserId="+wfQues.getCurrentUsr();
					break;
				}
				case MSG.WFQUES_SELECT_TYPE_3:{
					sql+=" and WfQues.CreateBy="+wfQues.getCurrentUsr();
					break;
				}
				case 4:{
					sql+=" and (( WfQues.Status<"+MSG.WFQUES_STATUS_10+" and WfQues.Status>"+MSG.WFQUES_STATUS_0 +" and WfQues.UserId="+wfQues.getCurrentUsr()+") "
					+" or (WfQues.CreateBy="+wfQues.getCurrentUsr()+" and WfQues.Status="+MSG.WFQUES_STATUS_10+"))";
					break;
				}
				default:{
					break;
				}
			}
		}
		
		return sql;
	}
	
	public List<WfQues> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfQues> list = new ArrayList<WfQues>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfQues wfQues = new WfQues();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("QuesId") || _fields[i].equals("WfQues.QuesId"))
						wfQues.setQuesId(rs.getString("QuesId"));
					else if(_fields[i].equals("ScheId") || _fields[i].equals("WfQues.ScheId"))
						wfQues.setScheId(rs.getInt("ScheId"));
					else if(_fields[i].equals("TaskId") || _fields[i].equals("WfQues.TaskId"))
						wfQues.setTaskId(rs.getInt("TaskId"));
					else if(_fields[i].equals("CateId") || _fields[i].equals("WfQues.CateId"))
						wfQues.setCateId(rs.getInt("CateId"));
					else if(_fields[i].equals("ComId") || _fields[i].equals("WfQues.ComId"))
						wfQues.setComId(rs.getInt("ComId"));
					else if(_fields[i].equals("DeptId") || _fields[i].equals("WfQues.DeptId"))
						wfQues.setDeptId(rs.getInt("DeptId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfQues.UserId"))
						wfQues.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("RyUsrs") || _fields[i].equals("WfQues.RyUsrs"))
						wfQues.setRyUsrs(rs.getString("RyUsrs"));
					else if(_fields[i].equals("Title") || _fields[i].equals("WfQues.Title"))
						wfQues.setTitle(rs.getString("Title"));
					else if(_fields[i].equals("QuesLevel") || _fields[i].equals("WfQues.QuesLevel"))
						wfQues.setQuesLevel(rs.getInt("QuesLevel"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfQues.Status")|| _fields[i].equals("WfQues.Status1")||_fields[i].equals("Status1"))
						wfQues.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfQues.CreateBy"))
						wfQues.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("IdtfBy") || _fields[i].equals("WfQues.IdtfBy"))
						wfQues.setIdtfBy(rs.getInt("IdtfBy"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfQues.LastUpd"))
						wfQues.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfQues.WfNo"))
						wfQues.setWfNo(rs.getString("WfNo"));
					else if(_fields[i].equals("IsRisk") || _fields[i].equals("WfQues.IsRisk"))
						wfQues.setIsRisk(rs.getString("IsRisk"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfQues.CreateDate"))
						wfQues.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("IdtfDate") || _fields[i].equals("WfQues.IdtfDate"))
						wfQues.setIdtfDate(rs.getTimestamp("IdtfDate"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfQues.LastUpdDate"))
						wfQues.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					else if(_fields[i].equals("QuesText") || _fields[i].equals("WfQues.QuesText"))
						wfQues.setQuesText(rs.getString("QuesText"));
					else if(_fields[i].equals("Title") || _fields[i].equals("WfQues.Title"))
						wfQues.setResult(rs.getString("Title"));
					else if(_fields[i].equals("RyUsrs") || _fields[i].equals("WfQues.RyUsrs"))
						wfQues.setResult(rs.getString("RyUsrs"));
					else if(_fields[i].equals("Result") || _fields[i].equals("WfQues.Result"))
						wfQues.setResult(rs.getString("Result"));
					else if(_fields[i].equals("UsrName") || _fields[i].equals("Usr.UsrName"))
						wfQues.setUsrName(rs.getString("UsrName"));
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfRd.FlowId"))
						wfQues.setFlowId(rs.getInt("FlowId"));
					else if(_fields[i].equals("IdtfRes") || _fields[i].equals("WfQues.IdtfRes"))
						wfQues.setIdtfRes(rs.getString("IdtfRes"));
					//else if(_fields[i].equals("WfRd.ProjectNo as PrjtNo"))
						//wfQues.setPrjtNo(rs.getString("PrjtNo"));
					else if(_fields[i].equals("CompletedDate") || _fields[i].equals("WfQues.CompletedDate"))
						wfQues.setCompletedDate(rs.getTimestamp("CompletedDate"));
					else if(_fields[i].equals("PicXY") || _fields[i].equals("WfQues.PicXY"))
						wfQues.setPicXY(rs.getString("PicXY"));
					else if(_fields[i].equals("CrDefectId") || _fields[i].equals("WfQues.CrDefectId"))
						wfQues.setCrDefectId(rs.getString("CrDefectId"));
					
					else if(_fields[i].equals("SourceID") || _fields[i].equals("WfQues.SourceID"))
						wfQues.setSourceID(rs.getInt("SourceID"));
					else if(_fields[i].equals("QuesTypeID") || _fields[i].equals("WfQues.QuesTypeID"))
						wfQues.setQuesTypeID(rs.getInt("QuesTypeID"));
					
					else if(_fields[i].equals("CloseQuesReason") || _fields[i].equals("WfQues.CloseQuesReason"))
						wfQues.setCloseQuesReason(rs.getString("CloseQuesReason"));
					
					else if(_fields[i].equals("FractionDefective") || _fields[i].equals("WfQues.FractionDefective"))
						wfQues.setFractionDefective(rs.getString("FractionDefective"));
					
					else if(_fields[i].indexOf("AS CreateUsr") > -1) {
						wfQues.setCreateUsr(rs.getString("CreateUsr"));
					}
					else if(_fields[i].indexOf("AS IdtfUsr") > -1) {
						wfQues.setIdtfUsr(rs.getString("IdtfUsr"));
					}
					else if(_fields[i].indexOf("AS QuesRespUserId") > -1) {
						wfQues.setUserId(rs.getInt("QuesRespUserId"));
					}
					else if(_fields[i].indexOf("AS SchNm") > -1) {
						wfQues.setSchNm(rs.getString("SchNm"));
					}
					else if(_fields[i].indexOf("AS TypNm") > -1) {
						wfQues.setTypNm(rs.getString("TypNm"));
					}
					else if(_fields[i].indexOf("AS RoleDQAId") > -1) {
						wfQues.setRoleDQAId(rs.getInt("RoleDQAId"));
					}
					else if(_fields[i].indexOf("AS PrjtNm") > -1) {
						wfQues.setPrjtNm(rs.getString("PrjtNm"));
					}
					else if(_fields[i].indexOf("AS WfName") > -1) {
						wfQues.setWfName(rs.getString("WfName"));
					}
					else if(_fields[i].indexOf("AS QuesRespId") > -1){
						wfQues.setQuesRespId(rs.getInt("QuesRespId"));
					}
					else if(_fields[i].indexOf("AS QuesRespIdtfDate") > -1){
						wfQues.setIdtfDate(rs.getTimestamp("QuesRespIdtfDate"));
					}
					else if(_fields[i].indexOf("AS QuesRespResult") > -1){
						wfQues.setQuesMeasures(rs.getString("QuesRespResult"));
					}
					
					
					else if(_fields[i].indexOf(" AS DeptNm") > -1){
						wfQues.setDeptNm(rs.getString("DeptNm"));
					}
					else if(_fields[i].indexOf(" AS QuesOpen") > -1){
						wfQues.setQuesOpen(rs.getInt("QuesOpen"));
					}
					else if(_fields[i].indexOf(" AS QuesClose") > -1){
						wfQues.setQuesClose(rs.getInt("QuesClose"));
					}
					else if(_fields[i].indexOf(" AS QuesRisk") > -1){
						wfQues.setQuesRisk(rs.getInt("QuesRisk"));
					}
					
					else if(_fields[i].indexOf(" AS Datetimeforques") > -1){
						wfQues.setDatetimeforques(rs.getInt("Datetimeforques"));
					}
					
					
					else if(_fields[i].indexOf("AS QuesRespAnalysis") > -1) {
						wfQues.setQuesAnalysis(rs.getString("QuesRespAnalysis"));
					}
					
					else if(_fields[i].equals("IdtfResFileNo") || _fields[i].equals("WfQues.IdtfResFileNo"))
						wfQues.setIdtfResFileNo(rs.getString("IdtfResFileNo"));
					else if(_fields[i].equals("ResultFileNo") || _fields[i].equals("WfQues.ResultFileNo"))
						wfQues.setResultFileNo(rs.getString("ResultFileNo"));
					else if(_fields[i].equals("IdtfResFileName") || _fields[i].equals("WfQues.IdtfResFileName"))
						wfQues.setIdtfResFileName(rs.getString("IdtfResFileName"));
					else if(_fields[i].equals("ResultFileName") || _fields[i].equals("WfQues.ResultFileName"))
						wfQues.setResultFileName(rs.getString("ResultFileName"));
					else if(_fields[i].equals("FileNo") || _fields[i].equals("WfQues.FileNo"))
						wfQues.setFileNo(rs.getString("FileNo"));
					else if(_fields[i].equals("FileName") || _fields[i].equals("WfQues.FileName"))
						wfQues.setFileName(rs.getString("FileName"));
					
					else if(_fields[i].equals("PrjtNo") || _fields[i].equals("WfQues.PrjtNo"))
						wfQues.setPrjtNo(rs.getString("PrjtNo"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("WfQues.Remark"))
						wfQues.setRemark(rs.getString("Remark"));
					
					else if(_fields[i].equals("UsrName") || _fields[i].equals("WfQues.UsrName"))
						wfQues.setUsrName(rs.getString("UsrName"));
					
					else if(_fields[i].equals("IsRiskQues") || _fields[i].equals("WfQues.IsRiskQues"))
						wfQues.setIsRiskQues(rs.getInt("IsRiskQues"));
					
					
					
					else if(_fields[i].equals("SourceID") || _fields[i].equals("WfQues.SourceID"))
						wfQues.setSourceID(rs.getInt("SourceID"));
					else if(_fields[i].equals("QuesTypeID") || _fields[i].equals("WfQues.QuesTypeID"))
						wfQues.setQuesTypeID(rs.getInt("QuesTypeID"));
				
					else if(_fields[i].equals("CloseQuesReason") || _fields[i].equals("WfQues.CloseQuesReason"))
						wfQues.setCloseQuesReason(rs.getString("CloseQuesReason"));
					
					
					else if(_fields[i].equals("FractionDefective") || _fields[i].equals("WfQues.FractionDefective"))
						wfQues.setFractionDefective(rs.getString("FractionDefective"));
					
					else if(_fields[i].equals("TestItemID") || _fields[i].equals("WfQues.TestItemID"))
						wfQues.setTestItemID(rs.getString("TestItemID"));
					else if(_fields[i].equals("TestItemName") || _fields[i].equals("WfQues.TestItemName"))
						wfQues.setTestItemName(rs.getString("TestItemName"));
					
					if(WFUtil.isNotNull(wfQues.getResultFileName())){
						wfQues.setShowResFileIcon(true);
						String uriLink = wfQues.getResultFileName().toLowerCase();
						if(uriLink.endsWith(".doc") || uriLink.endsWith(".docx")){
							wfQues.setResFileIcon(MSG.OWF_ICON_DOC);
						}else if(uriLink.endsWith(".xls") || uriLink.endsWith(".xlsx")){
							wfQues.setResFileIcon(MSG.OWF_ICON_XLS);
						}else if(uriLink.endsWith(".ppt") || uriLink.endsWith(".pptx")){
							wfQues.setResFileIcon(MSG.OWF_ICON_PPT);
						}else if(uriLink.endsWith(".txt")){
							wfQues.setResFileIcon(MSG.OWF_ICON_TXT);
						}else if(uriLink.endsWith(".pdf")){
							wfQues.setResFileIcon(MSG.OWF_ICON_PDF);
						}else if(uriLink.endsWith(".jpg") || uriLink.endsWith(".jpeg") 
								|| uriLink.endsWith(".png") || uriLink.endsWith(".bmp") || uriLink.endsWith(".gif")){
							wfQues.setResFileIcon(MSG.OWF_ICON_JPG);
						}else if(uriLink.endsWith(".rar")){
							wfQues.setResFileIcon(MSG.OWF_ICON_RAR);
						}else{
							wfQues.setResFileIcon(MSG.OWF_ICON_FILE);
						}
					}
					if(WFUtil.isNotNull(wfQues.getIdtfResFileName())){
						wfQues.setShowIdFileIcon(true);
						String uriLink = wfQues.getIdtfResFileName().toLowerCase();
						if(uriLink.endsWith(".doc") || uriLink.endsWith(".docx")){
							wfQues.setIdFileIcon(MSG.OWF_ICON_DOC);
						}else if(uriLink.endsWith(".xls") || uriLink.endsWith(".xlsx")){
							wfQues.setIdFileIcon(MSG.OWF_ICON_XLS);
						}else if(uriLink.endsWith(".ppt") || uriLink.endsWith(".pptx")){
							wfQues.setIdFileIcon(MSG.OWF_ICON_PPT);
						}else if(uriLink.endsWith(".txt")){
							wfQues.setIdFileIcon(MSG.OWF_ICON_TXT);
						}else if(uriLink.endsWith(".pdf")){
							wfQues.setIdFileIcon(MSG.OWF_ICON_PDF);
						}else if(uriLink.endsWith(".jpg") || uriLink.endsWith(".jpeg") 
								|| uriLink.endsWith(".png") || uriLink.endsWith(".bmp") || uriLink.endsWith(".gif")){
							wfQues.setIdFileIcon(MSG.OWF_ICON_JPG);
						}else if(uriLink.endsWith(".rar")){
							wfQues.setIdFileIcon(MSG.OWF_ICON_RAR);
						}else{
							wfQues.setIdFileIcon(MSG.OWF_ICON_FILE);
						}
					}
					
					if(WFUtil.isNotNull(wfQues.getFileName())){
						String uriLink = wfQues.getFileName().toLowerCase();
						if(uriLink.endsWith(".doc") || uriLink.endsWith(".docx")){
							wfQues.setFileIcon(MSG.OWF_ICON_DOC);
						}else if(uriLink.endsWith(".xls") || uriLink.endsWith(".xlsx")){
							wfQues.setFileIcon(MSG.OWF_ICON_XLS);
						}else if(uriLink.endsWith(".ppt") || uriLink.endsWith(".pptx")){
							wfQues.setFileIcon(MSG.OWF_ICON_PPT);
						}else if(uriLink.endsWith(".txt")){
							wfQues.setFileIcon(MSG.OWF_ICON_TXT);
						}else if(uriLink.endsWith(".pdf")){
							wfQues.setFileIcon(MSG.OWF_ICON_PDF);
						}else if(uriLink.endsWith(".jpg") || uriLink.endsWith(".jpeg") 
								|| uriLink.endsWith(".png") || uriLink.endsWith(".bmp") || uriLink.endsWith(".gif")){
							wfQues.setFileIcon(MSG.OWF_ICON_JPG);
						}else if(uriLink.endsWith(".rar")){
							wfQues.setFileIcon(MSG.OWF_ICON_RAR);
						}else{
							wfQues.setFileIcon(MSG.OWF_ICON_FILE);
						}
					}
					
				}
				list.add(wfQues);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfQuesHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}