package gnwf.ww.workflow;

import gnwf.facade.WfRdFieldFacade;
import gnwf.ww.json.WfRdViewAction;


public class WFHandlerFor4 extends WFHandler {

	private static final long serialVersionUID = 4742884725585682821L;
	
	public WFHandlerFor4(WfRdViewAction action) {
		super(action);
	}
	
	@Override
	public void saveJob() throws Exception {
		if(WFUtil.isNotNull(fieldContents)){
			if(currentTask.getSort()==2){
				//保存时,字段内容不为空,则可删除 (不改非自己填写的字段)
				new WfRdFieldFacade().saveFor14(fieldContents,wfRd.getWfNo());
			}else{
				new WfRdFieldFacade().saveAll(fieldContents,wfRd.getWfNo());
			}
		}
		saveSign();
		saveFile();
	}
	
	//规划  209徐黎  130王磊  3180李三保  2127王正  226陆伟  642 崔晓东 1394 王建中
	//决策  209徐黎  130王磊  3180李三保
	//主任  442卢伟冰
	
	/*
	 * select *from AddrBook where userid in(442,209,130,3180,2127,226,642,1394);
	 * 
	 *  cuixd@gionee.com	崔晓东
		wangjz@gionee.com	王建中
		wangzheng@gionee.com	王正
		luwb@gionee.com	卢伟冰
		xuli@gionee.com	徐黎
		luwei@gionee.com	陆伟
		wanglei@gionee.com	王磊
	 */
	
}
