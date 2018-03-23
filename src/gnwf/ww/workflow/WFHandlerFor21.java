package gnwf.ww.workflow;

import gnwf.facade.WfRdFieldFacade;
import gnwf.ww.MSG;
import gnwf.ww.json.WfRdViewAction;

import java.util.ArrayList;
import java.util.List;

import zrsy.vo.Usr;


public class WFHandlerFor21 extends WFHandler {

	private static final long serialVersionUID = 4742884725585682821L;
	
	public WFHandlerFor21(WfRdViewAction action) {
		super(action);
	}
	
	@Override
	public void saveJob() throws Exception {
		if(WFUtil.isNotNull(fieldContents)){
			if(currentTask.getSort()==4){
				//保存时,字段内容不为空,则可删除 (不改非自己填写的字段)
				new WfRdFieldFacade().saveFor14(fieldContents,wfRd.getWfNo());
			}else{
				new WfRdFieldFacade().saveAll(fieldContents,wfRd.getWfNo());
			}
		}
		saveSign();
		saveFile();
	}
	
	@Override
	public boolean nextStepPage() throws Exception{
		boolean flag = super.nextStepPage();
		if(currentTask.getSort()==2){
			//需转交的下一步骤
			nextSteps = getNextStepList(currentTask.getTaskId());
			
			//决策 209徐黎  130王磊  3180李三保
			//"Usr.Id,UsrName,StepId,TaskType"
			List<Usr> hisUsers = new ArrayList<Usr>();
			Usr u1 = new Usr();
			u1.setId(209);
			u1.setUsrName("徐黎");
			u1.setStepId(103);
			u1.setTaskType(MSG.OWFTASK_TYPE_1);
			hisUsers.add(u1);
			
			Usr u2 = new Usr();
			u2.setId(130);
			u2.setUsrName("王磊");
			u2.setStepId(103);
			u2.setTaskType(MSG.OWFTASK_TYPE_1);
			hisUsers.add(u2);
			
			taskCount = genHisUser(hisUsers);
			resetActionValue();
			return true;
		}
		return flag;
	}
	
}
