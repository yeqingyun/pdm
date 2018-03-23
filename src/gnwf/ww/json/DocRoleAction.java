package gnwf.ww.json;

import gnwf.facade.DocRoleFacade;
import gnwf.facade.WfScheCfgDocFacade;
import gnwf.vo.DocRole;
import gnwf.vo.WfScheCfgDoc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import zrprjt.facade.PrjtRoleFacade;
import zrprjt.vo.PrjtRole;
import zrprjt.ww.BasicAction;
import zrprjt.ww.MSG;

public class DocRoleAction extends BasicAction {
	private static final long serialVersionUID = 1L;
//WfScheCfgDoc.DocId
	public String execute() throws Exception {
		return INITIALIZES;
	}
	public String view(){
		return "view";
	}

	// 选出文档类型  和它的权限
	public String wfScheCfgDocList() throws Exception {
		// 选出文档类型 WfScheCfgDoc
		List<WfScheCfgDoc> wfScheCfgDocs = new WfScheCfgDocFacade().find("select WfScheCfgDoc.DocId,WfScheCfgDoc.DocName from WfScheCfgDoc", "WfScheCfgDoc.DocId,WfScheCfgDoc.DocName");
		for (WfScheCfgDoc wscd : wfScheCfgDocs) {
			StringBuffer sb = new StringBuffer();
			sb.append("select ").append(PrjtRole.SELF_FIELDS).append(" from GnPrjt.dbo.PrjtRole where exists(select * from GnWf.dbo.DocRole where DocRole.DocCateId=").append(wscd.getDocId()).append(" and DocRole.PrjtRoleId=PrjtRole.RoleId)");
			List<PrjtRole> prjtRoles = new PrjtRoleFacade().find(sb.toString(), PrjtRole.SELF_FIELDS);
			wscd.setPrjtRoles(prjtRoles);
		}
		JSONObject obj = new JSONObject();
		obj.put("Rows", wfScheCfgDocs);
		obj.put("Total", wfScheCfgDocs.size());
		setJson(obj.toString());
		System.out.println(obj);
		return PGLIS;
	}
	
	private String roleIds;
	private String docId;
	public String edit(){
		try {
			DocRole deleteDocRole = new DocRole();
			deleteDocRole.setDocCateId(Integer.parseInt(docId));
			List<DocRole> docRoles = new ArrayList<>();
			String[] rid = roleIds.split(",");
			for(String s:rid){
				if(s!=null&&s.length()>0){
					DocRole docRole = new DocRole();
					docRole.setDocCateId(deleteDocRole.getDocCateId());
					docRole.setPrjtRoleId(Integer.parseInt(s));
					docRole.setType(1);
					docRole.setCreateBy(getUsrSession().getId());
					docRole.setCreateDate(new Date());
					docRoles.add(docRole);
				}
			}
			new DocRoleFacade().edit(deleteDocRole, docRoles);
			setMsg(MSG.S_UPD);
		} catch (Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("DocRoleAction Exception", e);
			return "msg";
		}
		return "msg";
	}
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	public String getDocId() {
		return docId;
	}
	
	

	public void setDocId(String docId) {
		this.docId = docId;
	}
		
	
//	public  static void main (String args[]){
//		
//		try {
//			WfScheCfgDoc wfScheCfgDoc = new WfScheCfgDoc();
//			List<WfScheCfgDoc> wfScheCfgDocs = new ArrayList<>();
//			wfScheCfgDocs = new WfScheCfgDocFacade().findAll(wfScheCfgDoc);
//			for(WfScheCfgDoc wd:wfScheCfgDocs){
//				DocRole docRole = new DocRole();
//				docRole.setDocCateId(wd.getDocId());
//				docRole.setPrjtRoleId(31);
//				docRole.setType(1);
//				docRole.setCreateBy(15533);
//				docRole.setCreateDate(new Date());
//				new DocRoleFacade().save(docRole);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
}