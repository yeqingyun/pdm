package zrprjt.dao.helper;

import gnwf.ww.MSG;
import gnwf.ww.workflow.WFUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrprjt.vo.PrjtDef;

public class PrjtDefHelper extends BasicPrjtDefHelper {
	public String getSqlString() {
		return " from PrjtDef " +
               " left join PrjtTyp on (PrjtTyp.TypId = PrjtDef.TypId) " + 

               " where 1=1 ";
	}

	public List<PrjtDef> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<PrjtDef> list = new ArrayList<PrjtDef>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				PrjtDef prjtDef = new PrjtDef();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("TypId") || _fields[i].equals("PrjtDef.TypId"))
						prjtDef.setTypId(rs.getInt("TypId"));
					if(_fields[i].equals("Leve") || _fields[i].equals("PrjtDef.Leve"))
						prjtDef.setLeve(rs.getInt("Leve"));
					if(_fields[i].equals("Scope") || _fields[i].equals("PrjtDef.Scope"))
						prjtDef.setScope(rs.getInt("Scope"));
					if(_fields[i].equals("Status") || _fields[i].equals("PrjtDef.Status"))
						prjtDef.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtDef.CreateBy"))
						prjtDef.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtDef.LastUpd"))
						prjtDef.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("PlanStaDate") || _fields[i].equals("PrjtDef.PlanStaDate"))
						prjtDef.setPlanStaDate(rs.getTimestamp("PlanStaDate"));
					if(_fields[i].equals("PlanOveDate") || _fields[i].equals("PrjtDef.PlanOveDate"))
						prjtDef.setPlanOveDate(rs.getTimestamp("PlanOveDate"));
					if(_fields[i].equals("StaDate") || _fields[i].equals("PrjtDef.StaDate"))
						prjtDef.setStaDate(rs.getTimestamp("StaDate"));
					if(_fields[i].equals("OveDate") || _fields[i].equals("PrjtDef.OveDate"))
						prjtDef.setOveDate(rs.getTimestamp("OveDate"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtDef.CreateDate"))
						prjtDef.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtDef.LastDate"))
						prjtDef.setLastDate(rs.getTimestamp("LastDate"));
					if(_fields[i].equals("PrjtNo") || _fields[i].equals("PrjtDef.PrjtNo"))
						prjtDef.setPrjtNo(rs.getString("PrjtNo"));
					if(_fields[i].equals("PrjtNm") || _fields[i].equals("PrjtDef.PrjtNm"))
						prjtDef.setPrjtNm(rs.getString("PrjtNm"));
					if(_fields[i].equals("Remark") || _fields[i].equals("PrjtDef.Remark"))
						prjtDef.setRemark(rs.getString("Remark"));
					if(_fields[i].equals("Perce") || _fields[i].equals("PrjtDef.Perce"))
						prjtDef.setPerce(rs.getString("Perce"));
					if(_fields[i].equals("projtTypeNm") || _fields[i].equals("PrjtTyp.TypNm as projtTypeNm"))
						prjtDef.setProjtTypeNm(rs.getString("projtTypeNm"));
					
					if(_fields[i].equals("DevDeptNameID") || _fields[i].equals("PrjtDef.DevDeptNameID"))
						prjtDef.setDevDeptNameID(rs.getInt("DevDeptNameID"));
					
					if(_fields[i].equals("TaskVersion") || _fields[i].equals("PrjtDef.TaskVersion"))
						prjtDef.setTaskVersion(rs.getFloat("TaskVersion"));
					if(_fields[i].equals("PrjtDefDocFileNo") || _fields[i].equals("PrjtDef.PrjtDefDocFileNo"))
						prjtDef.setPrjtDefDocFileNo(rs.getString("PrjtDefDocFileNo"));
					if(_fields[i].equals("PrjtDefDocFileName") || _fields[i].equals("PrjtDef.PrjtDefDocFileName"))
						prjtDef.setPrjtDefDocFileName(rs.getString("PrjtDefDocFileName"));
					if(_fields[i].equals("PrjtTaskFileNo") || _fields[i].equals("PrjtDef.PrjtTaskFileNo"))
						prjtDef.setPrjtTaskFileNo(rs.getString("PrjtTaskFileNo"));
					if(_fields[i].equals("PrjtTaskFileName") || _fields[i].equals("PrjtDef.PrjtTaskFileName"))
						prjtDef.setPrjtTaskFileName(rs.getString("PrjtTaskFileName"));
					
					
					if(_fields[i].equals("CurrentPoint") || _fields[i].equals("PrjtDef.CurrentPoint"))
						prjtDef.setCurrentPoint(rs.getString("CurrentPoint"));
					if(_fields[i].equals("NextPoint") || _fields[i].equals("PrjtDef.NextPoint"))
						prjtDef.setNextPoint(rs.getString("NextPoint"));
					
					if(_fields[i].equals("PrjtPointVersion") || _fields[i].equals("PrjtDef.PrjtPointVersion"))
						prjtDef.setPrjtPointVersion(rs.getString("PrjtPointVersion"));
				
					

					
					
					if(WFUtil.isNotNull(prjtDef.getPrjtTaskFileName())){
						//prjtDef.setShowResFileIcon(true);
						String uriLink = prjtDef.getPrjtTaskFileName().toLowerCase();
						if(uriLink.endsWith(".doc") || uriLink.endsWith(".docx")){
							prjtDef.setPrjtTaskFileIcon(MSG.OWF_ICON_DOC);
						}else if(uriLink.endsWith(".xls") || uriLink.endsWith(".xlsx")){
							prjtDef.setPrjtTaskFileIcon(MSG.OWF_ICON_XLS);
						}else if(uriLink.endsWith(".ppt") || uriLink.endsWith(".pptx")){
							prjtDef.setPrjtTaskFileIcon(MSG.OWF_ICON_PPT);
						}else if(uriLink.endsWith(".txt")){
							prjtDef.setPrjtTaskFileIcon(MSG.OWF_ICON_TXT);
						}else if(uriLink.endsWith(".pdf")){
							prjtDef.setPrjtTaskFileIcon(MSG.OWF_ICON_PDF);
						}else if(uriLink.endsWith(".jpg") || uriLink.endsWith(".jpeg") 
								|| uriLink.endsWith(".png") || uriLink.endsWith(".bmp") || uriLink.endsWith(".gif")){
							prjtDef.setPrjtTaskFileIcon(MSG.OWF_ICON_JPG);
						}else if(uriLink.endsWith(".rar")){
							prjtDef.setPrjtTaskFileIcon(MSG.OWF_ICON_RAR);
						}else{
							prjtDef.setPrjtTaskFileIcon(MSG.OWF_ICON_FILE);
						}
					}
					if(WFUtil.isNotNull(prjtDef.getPrjtDefDocFileName())){
						//prjtDef.setShowPrjtDefDocFileIcon(true);
						String uriLink = prjtDef.getPrjtDefDocFileName().toLowerCase();
						if(uriLink.endsWith(".doc") || uriLink.endsWith(".docx")){
							prjtDef.setPrjtDefDocFileIcon(MSG.OWF_ICON_DOC);
						}else if(uriLink.endsWith(".xls") || uriLink.endsWith(".xlsx")){
							prjtDef.setPrjtDefDocFileIcon(MSG.OWF_ICON_XLS);
						}else if(uriLink.endsWith(".ppt") || uriLink.endsWith(".pptx")){
							prjtDef.setPrjtDefDocFileIcon(MSG.OWF_ICON_PPT);
						}else if(uriLink.endsWith(".txt")){
							prjtDef.setPrjtDefDocFileIcon(MSG.OWF_ICON_TXT);
						}else if(uriLink.endsWith(".pdf")){
							prjtDef.setPrjtDefDocFileIcon(MSG.OWF_ICON_PDF);
						}else if(uriLink.endsWith(".jpg") || uriLink.endsWith(".jpeg") 
								|| uriLink.endsWith(".png") || uriLink.endsWith(".bmp") || uriLink.endsWith(".gif")){
							prjtDef.setPrjtDefDocFileIcon(MSG.OWF_ICON_JPG);
						}else if(uriLink.endsWith(".rar")){
							prjtDef.setPrjtDefDocFileIcon(MSG.OWF_ICON_RAR);
						}else{
							prjtDef.setPrjtDefDocFileIcon(MSG.OWF_ICON_FILE);
						}
					}
//					private String PrjtTaskFileIcon;
//					private String PrjtDefDocFileIcon;
				}
				list.add(prjtDef);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtDefHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}