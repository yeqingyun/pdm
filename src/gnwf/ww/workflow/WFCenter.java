package gnwf.ww.workflow;

public interface WFCenter{
	public void previewExec() throws Exception;			//进预览页查数据
	public void processExec() throws Exception;			//进处理页查数据
	public boolean isHasJob() throws Exception;			//是否有任务
	public void acceptTask() throws Exception;			//接收任务
	public String rejectTask(int taskStatus) throws Exception;			//退回任务/收回
	public String backTask(int taskStatus) throws Exception;			//收回任务
	//保存任务、进入转交页、转交下一步骤
	public void saveJob() throws Exception;				//保存任务
	public void addFiles() throws Exception;			//保存附件
	public boolean nextStepPage() throws Exception;		//进入转交页
	public boolean sendTask() throws Exception;			//转交下一步骤
	
	public String exportXls()throws Exception;			//导出
	public String importXls()throws Exception;			//导入
	public String genAjaxInfo() throws Exception;		//ajax处理
	public void print() throws Exception;				//打印
	
	public boolean needSpecifyNext() throws Exception;	//wxh是否需要手动指定下一步骤办理人
}
