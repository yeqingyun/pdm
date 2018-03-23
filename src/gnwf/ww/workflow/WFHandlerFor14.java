package gnwf.ww.workflow;

import gnwf.facade.WfRdFacade;
import gnwf.facade.WfRdFieldFacade;
import gnwf.vo.WfRdField;
import gnwf.ww.json.WfRdViewAction;

import java.util.Date;
import java.util.List;

import zrsy.facade.UsrFacade;
import zrsy.vo.Usr;


public class WFHandlerFor14 extends WFHandler {

	private static final long serialVersionUID = 4742884725585682821L;
	
	public WFHandlerFor14(WfRdViewAction action) {
		super(action);
	}

//	@Override
//	public void saveJob() throws Exception {
//		//long s = System.currentTimeMillis();
//		if(WFUtil.isNotNull(fieldContents)){
//			if(currentTask.getSort()==4){
//				//保存时,字段内容不为空,则可删除 (不改非自己填写的字段)
//				new WfRdFieldFacade().saveFor14(fieldContents,wfRd.getWfNo());
//			}else{
//				new WfRdFieldFacade().saveAll(fieldContents,wfRd.getWfNo());
//			}
//		}
//		saveSign();
//		saveFile();
//	}
	
	@Override
	public boolean sendTask() throws Exception {
		
		if(currentTask.getSort()==5){		//本项目立项申请同意后
			String sql = "select WfRdField.FieldText from wfrdfield where wfno='"+wfRd.getWfNo()+"' and fieldid=720";
			WfRdField field = new WfRdFieldFacade().findById(sql,"WfRdField.FieldText");
			
			if(field!=null && WFUtil.isNotNull(field.getFieldText()) && "1".equals(field.getFieldText().toLowerCase())){
				//查是否已启动归档流程,如果未启动则启动
				String countSql = "select count(*) as amount from wfrd where flowid=39 and projectno='"+wfRd.getProjectNo()+"'";
				int count = new WfRdFacade().amount(countSql);
				if(count==0){
					String userSql = "select distinct PrjtUsr.UsrId as Id from dbo.PrjtUsr where prjtno='"+wfRd.getProjectNo()+"'" +
							" and roleid in (select distinct userrole from WfScheCfgDoc)";
					System.out.println(userSql);
					List<Usr> users = new UsrFacade().find(userSql, "Usr.Id");
					if(WFUtil.isNotNull(users)){
						for(int i=0;i<users.size();i++){
							Usr u = users.get(i);
							//创建流程和第一步骤
							WFUtil.createWfRd(wfRd.getProjectNo(),0, 39, "文件归档", u.getId(), new Date(),new Date(),"true",u.getId());
						}
					}
					sendMail(users); 	//发送邮件
				} 
			}
		}
		
		return super.sendTask();
	}
	
	
}
