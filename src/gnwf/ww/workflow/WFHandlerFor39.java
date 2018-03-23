package gnwf.ww.workflow;

import gnwf.facade.WfScheCfgDocFacade;
import gnwf.ww.json.WfRdViewAction;


public class WFHandlerFor39 extends WFHandler {

	private static final long serialVersionUID = 4742884725585682821L;
	
	public WFHandlerFor39(WfRdViewAction action) {
		super(action);
	}
	
	@Override
	public void processExec() throws Exception {
		super.processExec();
		
		if(currentTask.getSort()==1){
			//文档类别
			String sql = "select WfScheCfgDoc.DocId,WfScheCfgDoc.DocName from WfScheCfgDoc " +
					" where userrole in(select distinct roleid from dbo.PrjtUsr " +
					" where prjtno='E0013000031' and usrid="+user.getId()+")";
		
			docCates = new WfScheCfgDocFacade().find(sql,"WfScheCfgDoc.DocId,WfScheCfgDoc.DocName");
			currentTask.setDocNames(genDocName(docCates));
			resetActionValue();
		}
	}
}
