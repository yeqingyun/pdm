package gnwf.ww.json;

import gnwf.vo.AddrBook;
import gnwf.ww.BasicAction;
import gnwf.ww.MSG;
import gnwf.ww.workflow.WFUtil;

import java.util.Date;
import java.util.List;

import zrsy.facade.ComFacade;
import zrsy.facade.DeptFacade;
import zrsy.facade.UsrFacade;
import zrsy.vo.Com;
import zrsy.vo.Dept;
import zrsy.vo.Usr;


public class WFSelectUserAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private List<Com> baComs;
	private List<Dept> baDepts;
	private List<AddrBook> addrBooks;
	
	private Integer taskId;					//当前任务
	private Integer stepId;					//需转交步骤
	private Integer	taskType;				//任务类型  1:主办,2协办
	private int 	count;
	private Date	currentDate;			
	
	private List<Usr> constantUsers;		//固定选择人
	private List<Usr> wfinfoUsers;		//本流程用户
	private List<Usr> leaderUsers;		//领导用户
	private List<Usr> projectUsers;		//项目组用户
	
	private String 	wfinfoValue;
	private String 	leaderValue;
	private String 	projectValue;
	
	private String select = "<br><font color='blue'>待选人员列表：</font><br>" +
				"<select id='mailAddr' name='mailAddr' style='width:180px' multiple size='15'>";
	
	public String execute() throws Exception {
		
		//通讯录
//		AddrBook addrBook = new AddrBook();
//		addrBook.setStatus(MSG.CONST_STATUS_1);
//		addrBooks = new AddrBookFacade().findAll(addrBook);
		
		//当天
		currentDate = new Date();
		
		//公司部门
		baComs = new ComFacade().find("select "+Com.SELF_FIELDS+" " +
				" from Com where status = 2 ",Com.SELF_FIELDS);

		String deptsql = "WITH CTE as" +
				" (" +
				" SELECT DeptId,Parent,DeptNo,DeptNm,leve,Status FROM Dept WHERE Dept.parent = '10100001' " +
				" UNION ALL" +
				" SELECT A.DeptId,A.Parent,A.DeptNo,A.DeptNm,A.leve,A.Status FROM Dept A,CTE " +
				" where A.parent = CTE.deptId" +
				" ) " +
				" SELECT *  from CTE";
		baDepts = new DeptFacade().find(deptsql,"DeptId,Parent,DeptNo,DeptNm,Leve,Status");
		
//		for(int i=0; i<baComs.size(); i++) {
//			for(int j=0; j<baDepts.size(); j++) {
//				if(baComs.get(i).getComId().equals(baDepts.get(j).getComId()))
//					baComs.get(i).addtoDepartments(baDepts.get(j));
//			}
//		}
		
		//查是否有固定选择人
		String constantSql = "select Usr.Id,Usr.usrName from Usr " +
			" left join WfStepUser on(Usr.Id=WfStepUser.userId) where Usr.status="+MSG.CONST_STATUS_1+
			" and WfStepUser.stepId="+stepId+" and WfStepUser.userType="+taskType;
		constantUsers = new UsrFacade().find(constantSql, "Usr.Id,Usr.UsrName");
		
		if(WFUtil.isNull(constantUsers)){
			//本流程
			String wfinfoSql = "select Usr.Id,Usr.usrName from Usr " +
					"where Id in(select distinct acceptBy from wfrdtask " +
					"where wfno=(select wfno from wfrdtask where taskid="+taskId+"))";
			wfinfoUsers = new UsrFacade().find(wfinfoSql, "Usr.Id,Usr.UsrName");
			wfinfoValue = genSelectValue(wfinfoUsers);
			
			//部门领导
//			String leaderSql = "";
//			leaderUsers = new SyUserFacade().find(leaderSql, "SyUser.UserId,SyUser.UserName");
//			leaderValue = genSelectValue(leaderUsers);
			
			//项目组
//			String projectSql = "";
//			projectUsers = new SyUserFacade().find(projectSql, "SyUser.UserId,SyUser.UserName");
//			projectValue = genSelectValue(projectUsers);
		}
		
		return INITIALIZES;
	}
	
	protected String genSelectValue(List<Usr> list) {
		if(WFUtil.isNull(list)){
			return null;
		}
		
		StringBuffer str = new StringBuffer(select);
		for(int i=0;i<list.size();i++){
			str.append("<option value="+list.get(i).getId()+">"+list.get(i).getUsrName()+"</option>");
		}
		str.append("</select>");
		
		return str.toString();
	}
	
	
	public List<Com> getBaComs() {
		return baComs;
	}
	public void setBaComs(List<Com> baComs) {
		this.baComs = baComs;
	}
	public List<Dept> getBaDepts() {
		return baDepts;
	}
	public void setBaDepts(List<Dept> baDepts) {
		this.baDepts = baDepts;
	}
	public List<AddrBook> getAddrBooks() {
		return addrBooks;
	}
	public void setAddrBooks(List<AddrBook> addrBooks) {
		this.addrBooks = addrBooks;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public Integer getStepId() {
		return stepId;
	}
	public void setStepId(Integer stepId) {
		this.stepId = stepId;
	}
	public List<Usr> getConstantUsers() {
		return constantUsers;
	}
	public void setConstantUsers(List<Usr> constantUsers) {
		this.constantUsers = constantUsers;
	}
	public List<Usr> getWfinfoUsers() {
		return wfinfoUsers;
	}
	public void setWfinfoUsers(List<Usr> wfinfoUsers) {
		this.wfinfoUsers = wfinfoUsers;
	}
	public List<Usr> getLeaderUsers() {
		return leaderUsers;
	}
	public void setLeaderUsers(List<Usr> leaderUsers) {
		this.leaderUsers = leaderUsers;
	}
	public List<Usr> getProjectUsers() {
		return projectUsers;
	}
	public void setProjectUsers(List<Usr> projectUsers) {
		this.projectUsers = projectUsers;
	}
	public Integer getTaskType() {
		return taskType;
	}
	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}
	public String getWfinfoValue() {
		return wfinfoValue;
	}
	public void setWfinfoValue(String wfinfoValue) {
		this.wfinfoValue = wfinfoValue;
	}
	public String getLeaderValue() {
		return leaderValue;
	}
	public void setLeaderValue(String leaderValue) {
		this.leaderValue = leaderValue;
	}
	public String getProjectValue() {
		return projectValue;
	}
	public void setProjectValue(String projectValue) {
		this.projectValue = projectValue;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	
}
