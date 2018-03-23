package gnwf.ww.json;

import gnfs.service.GnFileService;
import gnfs.service.impl.PDMGnFileServiceImpl;
import gnwf.facade.WfDocFacade;
import gnwf.facade.WfRdFacade;
import gnwf.facade.WfRdTaskFacade;
import gnwf.facade.WfStepFacade;
import gnwf.vo.AddrBook;
import gnwf.vo.WfDoc;
import gnwf.vo.WfRd;
import gnwf.vo.WfRdTask;
import gnwf.vo.WfStep;
import gnwf.vo.json.WfDocJson;
import gnwf.ww.BasicAction;
import gnwf.ww.MSG;
import gnwf.ww.workflow.WFUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import zrprjt.facade.PrjtDefFacade;
import zrprjt.vo.PrjtDef;
import zrsy.facade.ComFacade;
import zrsy.facade.DeptFacade;
import zrsy.facade.UsrFacade;
import zrsy.vo.Com;
import zrsy.vo.Dept;
import zrsy.vo.Usr;

import com.alibaba.fastjson.JSON;

public class BaseLibWfDocAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfDoc> wfDocs;
	private WfDoc wfDoc = new WfDoc();
	private java.util.List<gnwf.vo.WfDocRev> wfDocRevs;
	private java.util.List<gnwf.vo.WfRdTask> wfRdTasks;
	private java.util.List<gnwf.vo.WfRd> wfRds;

	private String contentType;
	private String fileName;
	private FileInputStream inputStream;

	private String projectNo;

	private Integer scheId;

	private String fileNo;
	private List<Integer> usrIds;
	private int typ;
	private String remark;

	private String selectUsrs;

	// private ProcFile pf ;

	public String execute() throws Exception {
		// try {
		// if(wfDoc != null && wfDoc.getDocId() != null) {
		// wfDoc = new WfDocFacade().findById(wfDoc);
		// setJson(JSON.toJSONString(wfDoc));
		// }
		// wfRdTasks = new
		// gnwf.facade.WfRdTaskFacade().find("select "+gnwf.vo.WfRdTask.SELF_FIELDS+" from WfRdTask",gnwf.vo.WfRdTask.SELF_FIELDS);
		// wfRds = new
		// gnwf.facade.WfRdFacade().find("select "+gnwf.vo.WfRd.SELF_FIELDS+" from WfRd",gnwf.vo.WfRd.SELF_FIELDS);
		//
		// }
		// catch(Exception e) {
		// setMsg(MSG.F_SEA);
		// Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
		// return ERROR;
		// }
		return INITIALIZES;
	}

	public String baseLib() throws Exception {

		return "baseLib";
	}

	public String list() throws Exception {
		try {
			if (wfDoc == null) wfDoc = new WfDoc();
			int total = new WfDocFacade().amount(wfDoc);
			if (getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfDocs = new WfDocFacade().find(wfDoc, getPageVO());
			WfDocJson wfDocJson = new WfDocJson();
			wfDocJson.Rows = wfDocs;
			wfDocJson.Total = total;
			setJson(JSON.toJSONString(wfDocJson));
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}

	// TODO ֱ�ӷ���
	public String shareBaseLib() throws Exception {

		try {
			// TODO��������Ϊ������һ���ĵ�����Ա

			// String usr[] = selectUsrs.split(",");
			usrIds = new ArrayList<Integer>();

			usrIds.add(MSG.SHARE_LIB_USR_ID);
			// for(int i= 0 ;i<usr.length;i++){
			// usrIds.add(Integer.valueOf(usr[i]));
			// }
			GnFileService gnFileService = new PDMGnFileServiceImpl();
			// gnFileService.releaseBasicLib("E0213000014", usrIds, 1, "��Ȩ����", getUsrSession().getId());
			gnFileService.releaseBasicLib(fileNo, usrIds, 1, "��Ȩ����", getUsrSession().getId());

		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("transToBasicLib Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return "�����ɹ�";

	}

	// TODO ��������
	public String StatShareFlow() throws Exception {

		try {
			// ProcFile pf = new ProcFile();
			// GnFileService gnFileService = new PDMGnFileServiceImpl();
			// gnFileService.transToBasicLib(pf);
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("transToBasicLib Exception", e);
			return ERROR;
		}
		return SUCCESS;

	}

	// TODO ��Ŀ���ҹ������
	public String selPrjtBaseLib() throws Exception {
		// if((null == wfDoc.getProjectNo())){
		// return null;
		// }

		// String fields =
		// "WfDoc.DocId,WfDoc.TaskId,WfDoc.FileNo,WfDoc.ProjectNo,WfDoc.Status,WfDoc.CreateBy,WfDoc.LastUpd,WfDoc.CateId,WfDoc.FlowId,WfDoc.DeptDocId,WfDoc.WfNo,WfDoc.UriLink,WfDoc.CreateDate,WfDoc.LastUpdDate,WfDoc.DocName,WfDoc.DocVer,"
		// +
		// "Usr.UsrName,WfScheCfgDoc.DocName as CateName";
		// String sql = "select "+fields+" " +
		// " from WfDoc " +
		// // " left join WfRdTask on (WfRdTask.TaskId = WfDoc.TaskId) " +
		// // " left join WfRd on (WfRd.WfNo = WfDoc.WfNo) " +
		// // " left join WfStep on(WfRdTask.StepId=WfStep.StepId) " +
		// " left join usr on (WfDoc.CreateBy = usr.id) " +
		// " left join WfScheCfgDoc on(WfScheCfgDoc.DocId=WfDoc.CateId) " +
		// " inner join BaseLib on(WfDoc.FileNo = BaseLib.FileNo) " +
		// " where 1=1 "+
		// //
		// " and  wfrdtask.wfno in(select distinct wfrd.wfno from wfrd where wfrd.projectNo='"+wfDoc.getProjectNo().trim()+"' "+str+");";
		// "and BaseLib.Status = 1 ";
		// if(null!= wfDoc.getProjectNo()&&!wfDoc.getProjectNo().trim().isEmpty()){
		// sql += "and  WfDoc.projectNo='"+wfDoc.getProjectNo().trim()+"' ";
		// }
		// if(null!= wfDoc.getDocName()&&!wfDoc.getDocName().trim().isEmpty()){
		// sql+= " and  WfDoc.DocName like '%"+wfDoc.getDocName().trim()+"%' ";
		// }
		// if(null!= wfDoc.getWfNo()&&!wfDoc.getWfNo().trim().isEmpty()){
		// sql+= " and  WfDoc.WfNo  like '%"+wfDoc.getWfNo().trim()+"%' ";
		// }

		String fields = "WfDoc.DocId,WfDoc.TaskId,WfDoc.FileNo,WfDoc.ProjectNo," + "WfDoc.CreateBy,WfDoc.LastUpd,WfDoc.CateId,WfDoc.FlowId,WfDoc.DeptDocId," + "WfDoc.WfNo,WfDoc.UriLink,WfDoc.CreateDate,WfDoc.LastUpdDate,WfDoc.DocName,WfDoc.DocVer," + "Usr.UsrName,WfScheCfgDoc.DocName as CateName,WfRd.WfName as WfName,BaseLib.Status as Status";
		String sql = "select " + fields + " " + " from WfDoc " + " left join usr on (WfDoc.CreateBy = usr.id) " + " left join WfScheCfgDoc on(WfScheCfgDoc.DocId=WfDoc.CateId) " + " inner join BaseLib on(WfDoc.FileNo = BaseLib.FileNo) " + " left join WfRd on (WfRd.WfNo = WfDoc.WfNo) " + " left join WfCfg on (WfRd.FlowId = WfCfg.FlowId) " + " where 1=1 ";
		if (null != wfDoc.getProjectNo() && !wfDoc.getProjectNo().trim().isEmpty()) {
			sql += " and  WfDoc.projectNo='" + wfDoc.getProjectNo().trim() + "' ";
		}
		// else{
		// sql+= " and  WfDoc.projectNo is null ";
		// }
		if (null != wfDoc.getDocName() && !wfDoc.getDocName().trim().isEmpty()) {
			sql += " and  WfDoc.DocName like '%" + wfDoc.getDocName().trim() + "%' ";
		}
		if (null != wfDoc.getCateId()) {
			sql += " and  WfScheCfgDoc.DocId  =" + wfDoc.getCateId();
		}
		if (null != wfDoc.getFlowId()) {
			sql += " and  WfCfg.FlowId =" + wfDoc.getFlowId();
		}

		wfDocs = new WfDocFacade().find(sql, fields);
		WfDocJson wfDocJson = new WfDocJson();
		wfDocJson.Rows = wfDocs;
		wfDocJson.Total = wfDocs.size();
		setJson(JSON.toJSONString(wfDocJson));
		System.out.println(JSON.toJSONString(wfDocJson));
		return PGLIS;
	}
	
	
	
	

	public String dow() throws Exception {
		try {
			wfDoc = new WfDocFacade().findById(wfDoc);
			contentType = "application/octet-stream;charset=gb2312";
			inputStream = new FileInputStream(new File(this.getServPath() + wfDoc.getUriLink()));
			fileName = new String(wfDoc.getDocName().getBytes("GBK"), "ISO-8859-1");
			return "download";
		} catch (FileNotFoundException e) {
			setMsg("ϵͳ�Ҳ����ļ���");
			Logger.getLogger(this.getClass()).error("WfDocAction dow Exception", e);
			return ERROR;
		} catch (Exception e) {
			setMsg("���ؼ�¼ʧ��");
			Logger.getLogger(this.getClass()).error("WfDocAction dow Exception", e);
			return ERROR;
		}
	}
	
	
	
	
	
	
	
	private WfRd wfRd;
	private String cateId;   //文档类型ID
	private Integer taskUsrId;   //归档任务接收者ID
	private String userMap;// 文档类型和归档人对应的数据
	private String prjtNo;
	private String prjtNm;
	public String archiveBaseLib() throws Exception {
		try {
			
			
			//List<PrjtUsr> editPrjtUsrs = new ArrayList<PrjtUsr>();  
			  PrjtDef prjt = new PrjtDef();
			  prjt.setPrjtNo(prjtNo);
			  prjt = new PrjtDefFacade().findById(prjt);
			
			if(userMap!=null){
				//System.out.println(choices);
				JSONArray array = JSONArray.fromObject(userMap);//先读取串数组
				Object[] fs = array.toArray();  
				//转成对像数组
				for(int i=0;i<fs.length;i++){
					JSONObject obj = JSONObject.fromObject(fs[i]);//再使用JsonObject遍历一个个的对像
					String cate_Id = String.valueOf(obj.get("cateId"));
					String task_UsrId = String.valueOf(obj.get("usrId"));
					String wfName  = "项目"+prjt.getPrjtNm()+String.valueOf(obj.get("cateName"))+"归档流程";
					//PrjtUsr f = (PrjtUsr)JSONObject.toBean(obj,zrprjt.vo.PrjtUsr.class);//指定转换的类型，但仍需要强制转化-成功
					//editPrjtUsrs.add(f);
					System.out.println(cate_Id);
					System.out.println(task_UsrId);
					
					//if (wfRd.getWfNo() == null) {
						// 新增
					    wfRd = new WfRd();
					    wfRd.setFlowId(39); //归档流程flowId =39
					    wfRd.setWfName(wfName);
						wfRd.setStatus(MSG.CONST_STATUS_1);
						wfRd.setCreateBy(getUsrSession().getId());
						wfRd.setCreateDate(new Date());
						wfRd.setDocCateId(cate_Id);
						new WfRdFacade().save(wfRd);
						
						// 发第一个任务、跳转回任务页面
						WfRdTask task = new WfRdTask();
						task.setCreateBy(-1);
						task.setCreateDate(new Date());
						task.setReqDate(new Date());
						task.setAcceptBy(Integer.valueOf(task_UsrId));
						//task.setAcceptDate(new Date());
						task.setStatus(MSG.OWFTASK_STATUS_1);
						task.setTaskType(MSG.OWFTASK_TYPE_1);
						task.setWfNo(wfRd.getWfNo());
						task.setDocCateId(cate_Id);
						
						WfStep step = new WfStep();
						step.setFlowId(wfRd.getFlowId());
						step.setSort(1);
						step = new WfStepFacade().findBy(step);
						if (step != null) {
							task.setStepId(step.getStepId());
							new WfRdTaskFacade().save(task);
					//	}
				}
		    }
			
		
			

				// 附件

				// 测试
				// WFUtil.createScheWfRd("PD1300008", 1, 1, "bbbb", new Date(), new Date());

				//this.sendMessage(MSG.S_SAV, "WfRdView!wfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
				return MESSAGE;
			} else { // 更改
				if (wfRd != null && wfRd.getStatus() != null && wfRd.getStatus() == MSG.OWFRD_STATUS_2) {
					wfRd.setEndFactEDate(new Date());
				}

				wfRd.setLastUpd(getUsrSession().getId());
				wfRd.setLastUpdDate(new Date());
				new WfRdFacade().update(wfRd);
				setMsg(MSG.S_SAV);
				return "msg";
			}
		} catch (Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfRdAddAction add Exception", e);
			return ERROR;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private List<Com> baComs;
	private List<Dept> baDepts;
	private List<AddrBook> addrBooks;

	private Integer taskId; // ��ǰ����
	private Integer stepId; // ��ת������
	private Integer taskType; // �������� 1:���,2Э��
	private int count;
	private Date currentDate;

	private List<Usr> constantUsers; // �̶�ѡ����
	private List<Usr> wfinfoUsers; // ������û�
	private List<Usr> leaderUsers; // �쵼�û�
	private List<Usr> projectUsers; // ��Ŀ���û�

	private String wfinfoValue;
	private String leaderValue;
	private String projectValue;

	private String select = "<br><font color='blue'>��ѡ��Ա�б?</font><br>" + "<select id='mailAddr' name='mailAddr' style='width:180px' multiple size='15'>";

	// public String openShareDocDig() throws Exception {
	//
	// return "openShareDocDig";
	// }

	public String openShareDocDig() throws Exception {

		// ͨѶ¼
		// AddrBook addrBook = new AddrBook();
		// addrBook.setStatus(MSG.CONST_STATUS_1);
		// addrBooks = new AddrBookFacade().findAll(addrBook);

		// ����
		setCurrentDate(new Date());

		// ��˾����
		setBaComs(new ComFacade().find("select " + Com.SELF_FIELDS + " " + " from Com where status = 1 ", Com.SELF_FIELDS));

		String deptsql = "WITH CTE as" + " (" + " SELECT DeptId,Parent,DeptNo,DeptNm,leve,Status FROM Dept WHERE Dept.parent = 1" + " UNION ALL" + " SELECT A.DeptId,A.Parent,A.DeptNo,A.DeptNm,A.leve,A.Status FROM Dept A,CTE " + " where A.parent = CTE.deptId" + " ) " + " SELECT *  from CTE";
		setBaDepts(new DeptFacade().find(deptsql, "DeptId,Parent,DeptNo,DeptNm,Leve,Status"));

		// for(int i=0; i<baComs.size(); i++) {
		// for(int j=0; j<baDepts.size(); j++) {
		// if(baComs.get(i).getComId().equals(baDepts.get(j).getComId()))
		// baComs.get(i).addtoDepartments(baDepts.get(j));
		// }
		// }

		// ���Ƿ��й̶�ѡ����
		String constantSql = "select Usr.Id,Usr.usrName from Usr " + " left join WfStepUser on(Usr.Id=WfStepUser.userId) where Usr.status=" + MSG.CONST_STATUS_1 + " and WfStepUser.stepId=" + stepId + " and WfStepUser.userType=" + taskType;
		constantUsers = new UsrFacade().find(constantSql, "Usr.Id,Usr.UsrName");

		if (WFUtil.isNull(constantUsers)) {
			// �����
			String wfinfoSql = "select Usr.Id,Usr.usrName from Usr " + "where Id in(select distinct acceptBy from wfrdtask " + "where wfno=(select wfno from wfrdtask where taskid=" + taskId + "))";
			wfinfoUsers = new UsrFacade().find(wfinfoSql, "Usr.Id,Usr.UsrName");
			setWfinfoValue(genSelectValue(wfinfoUsers));

			// �����쵼
			// String leaderSql = "";
			// leaderUsers = new SyUserFacade().find(leaderSql, "SyUser.UserId,SyUser.UserName");
			// leaderValue = genSelectValue(leaderUsers);

			// ��Ŀ��
			// String projectSql = "";
			// projectUsers = new SyUserFacade().find(projectSql, "SyUser.UserId,SyUser.UserName");
			// projectValue = genSelectValue(projectUsers);
		}

		return "openShareDocDig";
	}

	protected String genSelectValue(List<Usr> list) {
		if (WFUtil.isNull(list)) {
			return null;
		}

		StringBuffer str = new StringBuffer(select);
		for (int i = 0; i < list.size(); i++) {
			str.append("<option value=" + list.get(i).getId() + ">" + list.get(i).getUsrName() + "</option>");
		}
		str.append("</select>");

		return str.toString();
	}

	public List<WfDoc> getWfDocs() {
		return wfDocs;
	}

	public void setWfDocs(List<WfDoc> wfDocs) {
		this.wfDocs = wfDocs;
	}

	public WfDoc getWfDoc() {
		return wfDoc;
	}

	public void setWfDoc(WfDoc wfDoc) {
		this.wfDoc = wfDoc;
	}

	public WritableWorkbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(WritableWorkbook workbook) {
		this.workbook = workbook;
	}

	public File getFileInp() {
		return fileInp;
	}

	public void setFileInp(File fileInp) {
		this.fileInp = fileInp;
	}

	public String getChoices() {
		return choices;
	}

	public void setChoices(String choices) {
		this.choices = choices;
	}

	public java.util.List<gnwf.vo.WfDocRev> getWfDocRevs() {
		return wfDocRevs;
	}

	public void setWfDocRevs(java.util.List<gnwf.vo.WfDocRev> wfDocRevs) {
		this.wfDocRevs = wfDocRevs;
	}

	public void addtoWfDocRevs(gnwf.vo.WfDocRev wfDocRev) {
		if (getWfDocRevs() == null) setWfDocRevs(new java.util.ArrayList<gnwf.vo.WfDocRev>());
		getWfDocRevs().add(wfDocRev);
	}

	public java.util.List<gnwf.vo.WfRdTask> getWfRdTasks() {
		return wfRdTasks;
	}

	public void setWfRdTasks(java.util.List<gnwf.vo.WfRdTask> wfRdTasks) {
		this.wfRdTasks = wfRdTasks;
	}

	public void addtoWfRdTasks(gnwf.vo.WfRdTask wfRdTask) {
		if (getWfRdTasks() == null) setWfRdTasks(new java.util.ArrayList<gnwf.vo.WfRdTask>());
		getWfRdTasks().add(wfRdTask);
	}

	public java.util.List<gnwf.vo.WfRd> getWfRds() {
		return wfRds;
	}

	public void setWfRds(java.util.List<gnwf.vo.WfRd> wfRds) {
		this.wfRds = wfRds;
	}

	public void addtoWfRds(gnwf.vo.WfRd wfRd) {
		if (getWfRds() == null) setWfRds(new java.util.ArrayList<gnwf.vo.WfRd>());
		getWfRds().add(wfRd);
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public FileInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(FileInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public Integer getScheId() {
		return scheId;
	}

	public void setScheId(Integer scheId) {
		this.scheId = scheId;
	}

	public List<Integer> getUsrIds() {
		return usrIds;
	}

	public void setUsrIds(List<Integer> usrIds) {
		this.usrIds = usrIds;
	}

	public int getTyp() {
		return typ;
	}

	public void setTyp(int typ) {
		this.typ = typ;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
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

	public String getSelectUsrs() {
		return selectUsrs;
	}

	public void setSelectUsrs(String selectUsrs) {
		this.selectUsrs = selectUsrs;
	}

	// public ProcFile getPf() {
	// return pf;
	// }
	//
	//
	//
	// public void setPf(ProcFile pf) {
	// this.pf = pf;
	// }
//	public String bDoc() {
//		syId = String.valueOf(getUsrSession().getSyId());
//		syNm = getUsrSession().getSyNm();
//		usrId = String.valueOf(getUsrSession().getId());
//		usrNm = getUsrSession().getUsrName();
//		return "bdoc";
//	}

//	public String selPrjtBaseLib1() throws Exception {
//		String fields = "WfDoc.DocId,WfDoc.TaskId,WfDoc.FileNo,WfDoc.ProjectNo," + "WfDoc.CreateBy,WfDoc.LastUpd,WfDoc.CateId,WfDoc.FlowId,WfDoc.DeptDocId," + "WfDoc.WfNo,WfDoc.UriLink,WfDoc.CreateDate,WfDoc.LastUpdDate,WfDoc.DocName,WfDoc.DocVer," + "Usr.UsrName,WfScheCfgDoc.DocName as CateName,WfRd.WfName as WfName,BaseLib.Status as Status";
//
//		wfDocs = new ArrayList<>();
//		// 选出文档类型 WfScheCfgDoc
//		List<WfScheCfgDoc> wfScheCfgDocs = new WfScheCfgDocFacade().find("select WfScheCfgDoc.DocId,WfScheCfgDoc.DocName from WfScheCfgDoc" + (wfDoc.getCateId() != null ? " where WfScheCfgDoc.DocId=" + wfDoc.getCateId() : ""), "WfScheCfgDoc.DocId,WfScheCfgDoc.DocName");
//		for (WfScheCfgDoc wscd : wfScheCfgDocs) {
//			// 得到类别 wscd 中, 版本DocVer 最大的 WfDoc.DocId
//			StringBuilder sb = new StringBuilder();
//			sb.append("select top 1 ").append(fields).append(" from WfDoc ");
//			sb.append("left join usr on (WfDoc.CreateBy = usr.id) ");
//			sb.append("left join WfScheCfgDoc on(WfScheCfgDoc.DocId=WfDoc.CateId) ");
//			sb.append("inner join BaseLib on(WfDoc.FileNo = BaseLib.FileNo) ");//
//			sb.append("left join WfRd on (WfRd.WfNo = WfDoc.WfNo) ");
//			sb.append("left join WfCfg on (WfRd.FlowId = WfCfg.FlowId) ");
//			sb.append("where 1=1 ");
//			sb.append(getQueryCondition());
//			sb.append(" and WfScheCfgDoc.DocId=").append(wscd.getDocId());
//			sb.append(" order by WfDoc.DocVer desc");
//			List<WfDoc> wfDocs1 = new WfDocFacade().find(sb.toString(), fields);
//			if (wfDocs1 != null && !wfDocs1.isEmpty()) {
//				wfDocs.add(wfDocs1.get(0));
//			} else {
//				WfDoc d1 = new WfDoc();
//				d1.setCateName(wscd.getDocName());
//				d1.setCateId(wscd.getDocId());
//				wfDocs.add(d1);
//			}
//		}
//		WfDocJson wfDocJson = new WfDocJson();
//		wfDocJson.Rows = wfDocs;
//		wfDocJson.Total = wfDocs.size();
//		setJson(JSON.toJSONString(wfDocJson));
//		System.out.println(JSON.toJSONString(wfDocJson));
//		return PGLIS;
//	}
//
//	private String getQueryCondition() {
//		StringBuilder sb = new StringBuilder();
//		if (null != wfDoc.getProjectNo() && !wfDoc.getProjectNo().trim().isEmpty()) {
//			sb.append(" and  WfDoc.projectNo='").append(wfDoc.getProjectNo().trim()).append("' ");
//		}
//		if (null != wfDoc.getDocName() && !wfDoc.getDocName().trim().isEmpty()) {
//			sb.append(" and  WfDoc.DocName like '%").append(wfDoc.getDocName().trim()).append("%' ");
//		}
//		if (null != wfDoc.getCateId()) {
//			sb.append(" and  WfScheCfgDoc.DocId  =").append(wfDoc.getCateId());
//		}
//		if (null != wfDoc.getFlowId()) {
//			sb.append(" and  WfCfg.FlowId =").append(wfDoc.getFlowId());
//		}
//		return sb.toString();
//	}
//
//	/**
//	 * 查它的所有版本 WfDoc.projectNo,wfDoc.cateId
//	 */
//	public String selPrjtBaseLib1Dtl() throws Exception {
//		String fields = "WfDoc.DocId,WfDoc.TaskId,WfDoc.FileNo,WfDoc.ProjectNo," + "WfDoc.CreateBy,WfDoc.LastUpd,WfDoc.CateId,WfDoc.FlowId,WfDoc.DeptDocId," + "WfDoc.WfNo,WfDoc.UriLink,WfDoc.CreateDate,WfDoc.LastUpdDate,WfDoc.DocName,WfDoc.DocVer," + "Usr.UsrName,WfScheCfgDoc.DocName as CateName,WfRd.WfName as WfName,BaseLib.Status as Status";
//
//		StringBuilder sb = new StringBuilder();
//		sb.append("select ").append(fields).append(" from WfDoc ");
//		sb.append("left join usr on (WfDoc.CreateBy = usr.id) ");
//		sb.append("left join WfScheCfgDoc on(WfScheCfgDoc.DocId=WfDoc.CateId) ");
//		sb.append("inner join BaseLib on(WfDoc.FileNo = BaseLib.FileNo) ");//
//		sb.append("left join WfRd on (WfRd.WfNo = WfDoc.WfNo) ");
//		sb.append("left join WfCfg on (WfRd.FlowId = WfCfg.FlowId) ");
//		sb.append("where 1=1 ");
//		sb.append(getQueryCondition());
//		sb.append(" order by WfDoc.DocVer");
//		List<WfDoc> wfDocs = new WfDocFacade().find(sb.toString(), fields);
//		WfDocJson wfDocJson = new WfDocJson();
//		wfDocJson.Rows = wfDocs;
//		wfDocJson.Total = wfDocs.size();
//		setJson(JSON.toJSONString(wfDocJson));
//		System.out.println(JSON.toJSONString(wfDocJson));
//		return PGLIS;
//	}

//	private String tjson;
//
//	public String openPigeonholeWin() {
//		return "openPigeonholeWin";
//	}
//
//	public String openShareWin() {
//		return "openShareWin";
//	}
//	
//	public String openUpdateWin() {
//		return "openUpdateWin";
//	}
//	private String fileNo;
//	private String fileName;
//	private String tempParams;
//	
//	private String usrId;
//	private String syId;
//	private String syNm;
//	private String usrNm;
//	public void afterUploadFile(){
//		try{
//			setFileName(URLDecoder.decode(getFileName(), "UTF-8"));
//			setTempParams(URLDecoder.decode(getTempParams(), "UTF-8"));
//			if(getFileNo()!=null){
//				String sql =null;
//				if (getTempParams() != null){
//					String s[] = getTempParams().split(",");
//					for(int i = 0;i<s.length;i++){
//						 String ss[]=s[i].split(":");
//						 if(ss[0].equals("wfDoc.projectNo")){
//							 if(!"".equals(ss[1]))
//								 wfDoc.setProjectNo(ss[1]);
//						 }
//						 if(ss[0].equals("wfDoc.cateId")){
//							 Integer iv = Integer.parseInt(ss[1]);
//							 if(iv>0){
//								 wfDoc.setCateId(iv);
//							 }
//						 }
//						 if(ss[0].equals("wfDoc.taskId")){
//							 Integer iv = Integer.parseInt(ss[1]);
//							 if(iv>0){
//								 wfDoc.setTaskId(iv);
//							 }
//						 }
//						 if(ss[0].equals("wfDoc.wfNo")){
//							 if(!"".equals(ss[1]))
//								 wfDoc.setWfNo(ss[1]);
//						 }
//						 if(ss[0].equals("wfDoc.flowId")){
//							 Integer iv = Integer.parseInt(ss[1]);
//							 if(iv>0){
//								 wfDoc.setFlowId(iv);
//							 }
//						 }
//					}
//				}
//				if(wfDoc.getProjectNo()!=null&&wfDoc.getCateId()!=null){
//
//					String fields = "WfDoc.DocId,WfDoc.TaskId,WfDoc.FileNo,WfDoc.ProjectNo," + "WfDoc.CreateBy,WfDoc.LastUpd,WfDoc.CateId,WfDoc.FlowId,WfDoc.DeptDocId," + "WfDoc.WfNo,WfDoc.UriLink,WfDoc.CreateDate,WfDoc.LastUpdDate,WfDoc.DocName,WfDoc.DocVer," + "Usr.UsrName,WfScheCfgDoc.DocName as CateName,WfRd.WfName as WfName,BaseLib.Status as Status";
//
//					// 得到类别 wscd 中, 版本DocVer 最大的 WfDoc.DocId
//					StringBuilder sb = new StringBuilder();
//					sb.append("select top 1 ").append(fields).append(" from WfDoc ");
//					sb.append("left join usr on (WfDoc.CreateBy = usr.id) ");
//					sb.append("left join WfScheCfgDoc on(WfScheCfgDoc.DocId=WfDoc.CateId) ");
//					sb.append("inner join BaseLib on(WfDoc.FileNo = BaseLib.FileNo) ");//
//					sb.append("left join WfRd on (WfRd.WfNo = WfDoc.WfNo) ");
//					sb.append("left join WfCfg on (WfRd.FlowId = WfCfg.FlowId) ");
//					sb.append("where 1=1 ");
//					sb.append(getQueryCondition());
//					sb.append(" order by WfDoc.DocVer");
//					List<WfDoc> wfDocs1 = new WfDocFacade().find(sb.toString(), fields);
//					WfDoc upWfDoc = new WfDoc();
//					//添加,第一次上传
//					DecimalFormat df = new DecimalFormat("000");
//					upWfDoc.setTaskId(wfDoc.getTaskId());
//					upWfDoc.setWfNo(wfDoc.getWfNo());
//					upWfDoc.setProjectNo(wfDoc.getProjectNo());
//					upWfDoc.setFileNo(getFileNo());
//					upWfDoc.setCateId(wfDoc.getCateId());
//					upWfDoc.setDocName(getFileName());
//					upWfDoc.setStatus(1);
//					upWfDoc.setCreateBy(Integer.parseInt(getUsrId()));
//					upWfDoc.setCreateDate(new Date());
//					upWfDoc.setFlowId(wfDoc.getFlowId());
//					
//					new WfDocFacade().saveForVer(upWfDoc);
//					
//					BaseLib baseLib = new BaseLib();
//					baseLib.setFileNo(getFileNo());
//					baseLib.setCreateBy(Integer.parseInt(usrId));
//					baseLib.setCreateDate(new Date());
//					new BaseLibFacade().save(baseLib);
//				}
//			}else {
//				setMsg("上传附件失败");
//			}
//		} catch (Exception e) {
//			setMsg("上传附件失败");
//			Logger.getLogger(this.getClass()).error(
//					"BaseLibWfDocAction uploadResultFile Exception", e);
//			e.printStackTrace();
//		}
//		
//	}
//
//	public String getTjson() {
//		return tjson;
//	}
//
//	public void setTjson(String tjson) {
//		this.tjson = tjson;
//	}

//	public String getUsrId() {
//		return usrId;
//	}
//
//	public void setUsrId(String usrId) {
//		this.usrId = usrId;
//	}
//
//	public String getSyId() {
//		return syId;
//	}
//
//	public void setSyId(String syId) {
//		this.syId = syId;
//	}
//
//	public String getSyNm() {
//		return syNm;
//	}
//
//	public void setSyNm(String syNm) {
//		this.syNm = syNm;
//	}
//
//	public String getUsrNm() {
//		return usrNm;
//	}
//
//	public void setUsrNm(String usrNm) {
//		this.usrNm = usrNm;
//	}
//
//	public String getTempParams() {
//		return tempParams;
//	}
//
//	public void setTempParams(String tempParams) {
//		this.tempParams = tempParams;
//	}

	public Integer getTaskUsrId() {
		return taskUsrId;
	}

	public void setTaskUsrId(Integer taskUsrId) {
		this.taskUsrId = taskUsrId;
	}

	public String getUserMap() {
		return userMap;
	}

	public void setUserMap(String userMap) {
		this.userMap = userMap;
	}

	public String getPrjtNo() {
		return prjtNo;
	}

	public void setPrjtNo(String prjtNo) {
		this.prjtNo = prjtNo;
	}

	public String getPrjtNm() {
		return prjtNm;
	}

	public void setPrjtNm(String prjtNm) {
		this.prjtNm = prjtNm;
	}
	
}