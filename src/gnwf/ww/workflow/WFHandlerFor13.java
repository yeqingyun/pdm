package gnwf.ww.workflow;

import gnwf.ww.json.WfRdViewAction;


public class WFHandlerFor13 extends WFHandler {

	private static final long serialVersionUID = 4742884725585682821L;
	
	public WFHandlerFor13(WfRdViewAction action) {
		super(action);
	}

	@Override
	public void processExec() throws Exception {
		super.processExec();
		if(currentTask.getSort()==2){
			//第二步骤隐藏表单
			includeJspUri = null;
			resetActionValue();
		}
	}
}
