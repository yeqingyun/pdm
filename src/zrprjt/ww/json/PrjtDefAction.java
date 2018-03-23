package zrprjt.ww.json;

import gnwf.facade.WfDocFacade;
import gnwf.facade.WfRdFacade;
import gnwf.facade.WfRdTaskFacade;
import gnwf.vo.WfDoc;
import gnwf.vo.WfRd;
import gnwf.vo.json.WfDocJson;
import gnwf.vo.json.WfRdJson;
import gnwf.ww.workflow.WFUtil;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.frm.comn.GenericUtil;
import org.frm.comn.WorkDayCal;
import org.frm.jdbc.SqlUtil;

import zrprjt.dao.helper.PrjtDefHelper;
import zrprjt.facade.PrjtDefFacade;
import zrprjt.facade.PrjtPointFacade;
import zrprjt.facade.PrjtRoleFacade;
import zrprjt.facade.PrjtTypFacade;
import zrprjt.facade.PrjtUsrFacade;
import zrprjt.facade.SchCfgFacade;
import zrprjt.facade.SchWfFacade;
import zrprjt.facade.TaskFacade;
import zrprjt.vo.PrjtDef;
import zrprjt.vo.PrjtPoint;
import zrprjt.vo.PrjtRole;
import zrprjt.vo.PrjtTyp;
import zrprjt.vo.PrjtUsr;
import zrprjt.vo.SchCfg;
import zrprjt.vo.SchWf;
import zrprjt.vo.Task;
import zrprjt.vo.json.PrjtDefJson;
import zrprjt.vo.json.PrjtUsrJson;
import zrprjt.vo.json.SchCfgJson;
import zrprjt.ww.BasicAction;
import zrprjt.ww.MSG;
import zrsy.vo.Gp;
import zrsy.vo.Usr;
import Utils.JSONUtil;
import Utils.StringUtil;

import com.alibaba.fastjson.JSON;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;

public class PrjtDefAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<PrjtDef> prjtDefs;
	private PrjtDef prjtDef = new PrjtDef();
	private java.util.List<zrprjt.vo.PrjtAuth> prjtAuths;
	private java.util.List<zrprjt.vo.PrjtRole> prjtRoles;
	private java.util.List<zrprjt.vo.PrjtUsr> prjtUsrs;
	private java.util.List<zrprjt.vo.Task> tasks = new ArrayList<zrprjt.vo.Task>();
	private java.util.List<zrprjt.vo.PrjtTyp> prjtTyps;
	
	private String taskjson;
	private Task task;
	private String taskUID;
	private int loadTaskType;
	private List<SchCfg> schCfgs;
	private SchCfg schCfg = new SchCfg();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Integer roleleve;
	
	private java.io.File  prjtDefDocFile;
	
	
	private List <gnwf.vo.WfDoc> wfDocs;
	
	private String slePrjNo ;
	
	private Gp gp;
	
	private boolean showCreatePrjtTab;
	
	private String  mailTitle;
	private String  mailContent;
	private List<Usr> mailUserList;
	
	
	private String usrIds;
	private String usrString;
	
	private String fileNo;
	private String fileName;
	private String syId;
	private String syNm;
	private String usrId;
	private String usrNm;
	private String prjtNo;
	private String prjtNm;
	private String tempParams;
	
	private String prjtDefFileType;
	
	private WfDoc wfDoc;
	
	
	private Integer devDeptNameID;

	

	private InputStream inputStream;
//	private int updWfDoc;
	//private String wfId;
	//private String wfNm;
//	 postData[0]=new NameValuePair("fileNo",fileNo); 
//     postData[1]=new NameValuePair("fileName",URLEncoder.encode(docNm,"UTF-8")); 
//     postData[2]=new NameValuePair("syId",syId);
//     postData[3]=new NameValuePair("syNm",URLEncoder.encode(syNm,"UTF-8"));
//     postData[4]=new NameValuePair("usrId",usrId);
//     postData[5]=new NameValuePair("usrNm",URLEncoder.encode(usrNm,"UTF-8"));
//     postData[6]=new NameValuePair("diyFolder",URLEncoder.encode(diyFolder,"UTF-8"));
//     postData[7]=new NameValuePair("callBackUrl",callBackUrl);
//     postData[8]=new NameValuePair("tempParams",URLEncoder.encode(tempParams,"UTF-8"));
	
	public String execute() throws Exception {
		try {
			if (prjtDef != null && prjtDef.getTypId() != null) {
				prjtDef = new PrjtDefFacade().findById(prjtDef);
				setJson(JSON.toJSONString(prjtDef));
			}
			prjtTyps = new zrprjt.facade.PrjtTypFacade().find("select "
					+ zrprjt.vo.PrjtTyp.SELF_FIELDS + " from PrjtTyp",
					zrprjt.vo.PrjtTyp.SELF_FIELDS);
			
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtDefAction Exception",
					e);
			return ERROR;
		}
		return INITIALIZES;
	}
	
	
	
	public String doc() throws Exception {
		return "doc";
	}
	
	public String showSendMail() throws Exception {
		System.out.println(usrIds);
		System.out.println(usrString);
		return "showSendMail";
	}
	
	
	
	
	public String sendMail(){
		try {
			mailUserList = new ArrayList<Usr>();
			String usrs[] =  usrIds.split(",");
			for(int i=0;i<usrs.length;i++){
				Usr u = new Usr();
				u.setId(Integer.valueOf(usrs[i]));
				mailUserList.add(u);
			}
			WFUtil.sendMailByIT(mailTitle, mailContent, mailUserList);
			//WFUtil.sendMail(getUsrSession().getId(), getUsrSession().getEmail(),mailTitle, mailContent, mailUserList);
			//WFUtil.sendMail(getUsrSession().getId(), getUsrSession().getEmail(),mailTitle, mailContent,mailUserList.);
			setMsg("发送完成");
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtDefAction sendMail Exception",
					e);
			return ERROR;
		}
		return SUCCESS;
	}
	

	public String add() throws Exception {
		try {
			prjtTyps = new zrprjt.facade.PrjtTypFacade().find("select "
					+ zrprjt.vo.PrjtTyp.SELF_FIELDS + " from PrjtTyp",
					zrprjt.vo.PrjtTyp.SELF_FIELDS);
//		    prjtDef = new PrjtDef();
//			prjtDef.setPrjtNo("E0013000016");
//			prjtDef = new PrjtDefFacade().findById(prjtDef);
////			if(wfDoc!=null&&wfDoc.getDocId()!=null){
////				wfDoc = new WfDocFacade().findBy(wfDoc);
////			}
//			syId = String.valueOf(getUsrSession().getSyId());
//			syNm = getUsrSession().getSyNm();
//			usrId = String.valueOf(getUsrSession().getId());
//			usrNm = getUsrSession().getUsrName();

		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtDefAction Exception",
					e);
			return ERROR;
		}
		return PGADD;
//		return "upPrjtDefFile";
	}
	
	public String edit() throws Exception {
		try {
			if (prjtDef != null && prjtDef.getPrjtNo() != null) {
				prjtDef = new PrjtDefFacade().findById(prjtDef);
				
				setJson(JSON.toJSONString(prjtDef));
			}
			
			prjtTyps = new zrprjt.facade.PrjtTypFacade().find("select "
					+ zrprjt.vo.PrjtTyp.SELF_FIELDS + " from PrjtTyp",
					zrprjt.vo.PrjtTyp.SELF_FIELDS);
			System.out.println(ActionContext.getContext().getSession().get("prjtDef.prjtNo"));
			System.out.println("PrjtDefAction:edit"+ServletActionContext.getRequest().getParameter("prjtDef.prjtNo"));
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtDefAction Exception",
					e);
			return ERROR;
		}
		return PGEDI;
	}

	public String view() throws Exception {
		try {
			if (prjtDef != null && prjtDef.getPrjtNo() != null) {
				prjtDef = new PrjtDefFacade().findById(prjtDef);
				setJson(JSON.toJSONString(prjtDef));
				prjtTyps = new zrprjt.facade.PrjtTypFacade().find("select "
						+ zrprjt.vo.PrjtTyp.SELF_FIELDS + " from PrjtTyp",
						zrprjt.vo.PrjtTyp.SELF_FIELDS);
			}
			
			syId = String.valueOf(getUsrSession().getSyId());
			syNm = getUsrSession().getSyNm();
			usrId = String.valueOf(getUsrSession().getId());
			usrNm = getUsrSession().getUsrName();
			
//			setPrjtRole();
			
//			String sql = "select "+PrjtDef.SELF_FIELDS+" from PrjtDef where PrjtNo in ( select PrjtNo from PrjtUsr where UsrId  = "+ getUsrSession().getId()+")" ;
//		    prjtDefs = new PrjtDefFacade().find(sql,PrjtDef.SELF_FIELDS);
//
//		    List<LiNodeData> data = new ArrayList<LiNodeData>();
//		    for(int i=0; i<prjtDefs.size(); i++) {
//				data.add(new LiNodeData(prjtDefs.get(i).getPrjtNo(),prjtDefs.get(i).getPrjtNm(),"prjtDefManage("+prjtDefs.get(i).getPrjtNo()+")"));
//			}
//			
//			LigerTree tree = new LigerTree(false,data,"nodeclk");
//
//			System.out.println(JSON.toJSONString(tree));
//			setJson1(JSON.toJSONString(tree));
			
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtDefAction Exception",
					e);
			return ERROR;
		}
		return PGVIE;
	}

//	private void setPrjtRole() {
////		for( Gp e: getUsrSession().getGps()){
////			
////			if(e.getGpName().indexOf("项目经理")>-1){
////				gp = e;
////				showCreatePrjtTab = true;
////			}
////			
////		}
//	}
	
	public String getTyps() throws Exception {
		try{
		prjtTyps = new PrjtTypFacade().find(new PrjtTyp());

		this.setJson(JSON.toJSONString(prjtTyps));
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return PGLIS;
    }
	
	private String loadPrjtTreeType;
	public String loadPrjtTree() throws Exception {
		try{
			Boolean selAllPrjts = false;
			
			for( Gp e: getUsrSession().getGps()){
				
				if(e.getGpName().indexOf("超级用户")>-1){
					selAllPrjts = true;
					break;
				}else if(e.getGpName().indexOf("不随项目变更角色")>-1){
					selAllPrjts = true;
					break;
				}
				
			}
			
			
			String sql = null;
			
			/*if(selAllPrjts){*/
				sql = "select "+PrjtDef.SELF_FIELDS+" from PrjtDef where (TypId = ( select TypId from PrjtTyp where TypNm  = '研发管理') )  order by Status asc , CreateDate desc" ;
			/*}else {
				sql = "select "+PrjtDef.SELF_FIELDS+" from PrjtDef where (PrjtNo in ( select PrjtNo from PrjtUsr where UsrId  = "+ getUsrSession().getId()+") ) or CreateBy = "+getUsrSession().getId() +" order by Status asc , CreateDate desc" ;
			}*/
			prjtDefs = new ArrayList<PrjtDef>();
			/*if(!"doc".equals(loadPrjtTreeType)&&!"risk".equals(loadPrjtTreeType)){
				PrjtDef AllPrjetDef = new PrjtDef();
				AllPrjetDef.setPrjtNm("全部");
				AllPrjetDef.setIconUri("./include/img/btn/over.ico");
				AllPrjetDef.setPrjtNo("ALL");
				prjtDefs.add(AllPrjetDef);
			}*/
			
		    List<PrjtDef> prjtDefs2 = new PrjtDefFacade().find(sql,PrjtDef.SELF_FIELDS);
		    
		    String iconUri = "";
		    
//		    if(prjtDefs.size()>0){
//		    	slePrjNo = prjtDefs.get(0).getPrjtNo();
//		    }
		    PrjtDef AllPrjetDef = new PrjtDef();
			AllPrjetDef.setPrjtNm("全部");
			AllPrjetDef.setIconUri("./include/img/btn/over.ico");
			AllPrjetDef.setPrjtNo("ALL");
			prjtDefs.add(AllPrjetDef);
		    
		    for(PrjtDef e:prjtDefs2){
		    	if(e.getStatus().intValue()==1){
		    		iconUri = "./include/img/btn/nostart.ico";
		    	}else if (e.getStatus().intValue()==2){
		    		iconUri = "./include/img/btn/startIng.ico";
		    	}else if (e.getStatus().intValue()==3){
		    		iconUri = "./include/img/btn/over.ico";
		    	}else if (e.getStatus().intValue()==4){
		    		iconUri = "./include/img/btn/over.ico";
		    	}
		    	e.setIconUri(iconUri);
		    	prjtDefs.add(e);
		    }
		    
		    PrjtDef noPrjetDef = new PrjtDef();
		    
		    if("doc".equals(loadPrjtTreeType)){
		    	noPrjetDef.setPrjtNm("非项目文档");
		    	noPrjetDef.setIconUri("./include/img/btn/over.ico");
		    	noPrjetDef.setPrjtNo("NO_PRJT");
		    	prjtDefs.add(noPrjetDef);
		    }else if("task".equals(loadPrjtTreeType)){
		    	noPrjetDef.setPrjtNm("非项目任务");
		    	noPrjetDef.setIconUri("./include/img/btn/over.ico");
		    	noPrjetDef.setPrjtNo("NO_PRJT");
		    	prjtDefs.add(noPrjetDef);
		    }
	    
	    	
	    	
//		    if("task".equals(loadPrjtTreeType)){
//		    	PrjtDef noPrjetDef = new PrjtDef();
//		    	noPrjetDef.setPrjtNm("非项目");
//		    	noPrjetDef.setIconUri("./include/img/btn/over.ico");
//		    	noPrjetDef.setPrjtNo(WfRd.NOT_PROJECT);
//		    	prjtDefs.add(noPrjetDef);
//		    }
			//System.out.println(JSON.toJSONString(prjtDefs));
		    this.setJson(JSON.toJSONString(prjtDefs));
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return PGLIS;
    }
	//问题模块项目树结构显示
	public String loadPrjtTreeForQues() throws Exception {
		try{
			/*Boolean selAllPrjts = false;
			
			for( Gp e: getUsrSession().getGps()){
				
				if(e.getGpName().indexOf("超级用户")>-1){
					selAllPrjts = true;
					break;
				}else if(e.getGpName().indexOf("不随项目变更角色")>-1){
					selAllPrjts = true;
					break;
				}
				
			}
			*/
			
			String sql = null;
			
			/*if(selAllPrjts){*/
				sql = "select "+PrjtDef.SELF_FIELDS+" from PrjtDef where (TypId = ( select TypId from PrjtTyp where TypNm  = '研发管理') )  order by Status asc , CreateDate desc" ;
			/*}else {
				sql = "select "+PrjtDef.SELF_FIELDS+" from PrjtDef where (PrjtNo in ( select PrjtNo from PrjtUsr where UsrId  = "+ getUsrSession().getId()+") ) or CreateBy = "+getUsrSession().getId() +" order by Status asc , CreateDate desc" ;
			}*/
			prjtDefs = new ArrayList<PrjtDef>();
			if(!"doc".equals(loadPrjtTreeType)&&!"risk".equals(loadPrjtTreeType)){
				PrjtDef AllPrjetDef = new PrjtDef();
				AllPrjetDef.setPrjtNm("全部");
				AllPrjetDef.setIconUri("./include/img/btn/over.ico");
				AllPrjetDef.setPrjtNo("ALL");
				prjtDefs.add(AllPrjetDef);
			}
			
		    List<PrjtDef> prjtDefs2 = new PrjtDefFacade().find(sql,PrjtDef.SELF_FIELDS);
		    
		    String iconUri = "";
		    
//		    if(prjtDefs.size()>0){
//		    	slePrjNo = prjtDefs.get(0).getPrjtNo();
//		    }
		    
		    for(PrjtDef e:prjtDefs2){
		    	if(e.getStatus().intValue()==1){
		    		iconUri = "./include/img/btn/nostart.ico";
		    	}else if (e.getStatus().intValue()==2){
		    		iconUri = "./include/img/btn/startIng.ico";
		    	}else if (e.getStatus().intValue()==3){
		    		iconUri = "./include/img/btn/over.ico";
		    	}else if (e.getStatus().intValue()==4){
		    		iconUri = "./include/img/btn/over.ico";
		    	}
		    	e.setIconUri(iconUri);
		    	prjtDefs.add(e);
		    }
		    
		    PrjtDef noPrjetDef = new PrjtDef();
		    
		    if("doc".equals(loadPrjtTreeType)){
		    	noPrjetDef.setPrjtNm("非项目文档");
		    	noPrjetDef.setIconUri("./include/img/btn/over.ico");
		    	noPrjetDef.setPrjtNo("NO_PRJT");
		    	prjtDefs.add(noPrjetDef);
		    }else if("task".equals(loadPrjtTreeType)){
		    	noPrjetDef.setPrjtNm("非项目任务");
		    	noPrjetDef.setIconUri("./include/img/btn/over.ico");
		    	noPrjetDef.setPrjtNo("NO_PRJT");
		    	prjtDefs.add(noPrjetDef);
		    }
	    
	    	
	    	
//		    if("task".equals(loadPrjtTreeType)){
//		    	PrjtDef noPrjetDef = new PrjtDef();
//		    	noPrjetDef.setPrjtNm("非项目");
//		    	noPrjetDef.setIconUri("./include/img/btn/over.ico");
//		    	noPrjetDef.setPrjtNo(WfRd.NOT_PROJECT);
//		    	prjtDefs.add(noPrjetDef);
//		    }
			//System.out.println(JSON.toJSONString(prjtDefs));
		    this.setJson(JSON.toJSONString(prjtDefs));
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return PGLIS;
    }
	
	
	
	
	
	public String projInfor() throws Exception {
		try {
			if (prjtDef != null && prjtDef.getPrjtNo() != null) {
				prjtDef = new PrjtDefFacade().findById(prjtDef);
				setJson(JSON.toJSONString(prjtDef));
			}
			prjtTyps = new zrprjt.facade.PrjtTypFacade().find("select "
					+ zrprjt.vo.PrjtTyp.SELF_FIELDS + " from PrjtTyp",
					zrprjt.vo.PrjtTyp.SELF_FIELDS);
//			setPrjtRole();
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtDefAction Exception",
					e);
			return ERROR;
		}
		return "projInfor";
	}
	
	
	public String projtDoc() throws Exception {
		return "projtDoc";
	}
	
	public String projtSurvey() throws Exception {
		return "projtSurvey";
	}
	
	public String prjtPointFrameAnnex() throws Exception {
		return "prjtPointFrameAnnex";
	}
	
	public String taskManger() throws Exception {
		prjtDef = new PrjtDefFacade().findById(prjtDef)	;
//		setPrjtRole();
		return "taskManger";
	}
	
	public String prjtPointManger() throws Exception {
		prjtDef = new PrjtDefFacade().findById(prjtDef)	;
		return "prjtPointManger";
	}
	
	
	public String prjtInfor() throws Exception {
		
		return "prjtInfor";
	}
	
	
	
	//输入框按名字模糊搜索项目
		public String listPrjtDefs() throws Exception {
			try {
				if(prjtDef==null || prjtDef.getPrjtNm()==null){
					return null;
				}
				
				String prjtNm = URLDecoder.decode(prjtDef.getPrjtNm(),"UTF-8");
				
				String sql = "select "+ PrjtDef.SELF_FIELDS + SqlUtil.getSqlString(PrjtDefHelper.class)+" and PrjtDef.PrjtNm like '%"+prjtNm+"%'";
				prjtDefs = new PrjtDefFacade().find(sql,PrjtDef.SELF_FIELDS);
				
//				for(SyUser s:syUsers){
//					s.setPageNext(null);
//				}
				
				//Gson g  = new Gson();
				//String json = g.toJson();
//				HttpServletResponse response = ServletActionContext.getResponse();
//				response.setContentType("application/json;charset=utf-8");
//				response.setHeader("Cache-Control","no-cache");
//				
//				PrintWriter pw = response.getWriter();
//				pw.print(JSON.toJSONString(prjtDefs));
//				pw.flush();
//				pw.close();
				this.setJson(JSON.toJSONString(prjtDefs));
				//System.out.println(JSON.toJSONString(prjtDefs));
			}
			catch(Exception e) {
				this.sendMessage("搜索项目失败", "SyUserList.shtml");
				Logger.getLogger(this.getClass()).error("SyUserViewAction execute Exception", e);
				return ERROR;
			}
//			return INITIALIZES;
			return PGLIS;
		}
	
	
	
	
	
	
	private void sendMessage(String string, String string2) {
			// TODO Auto-generated method stub
			
		}

	//load项目组成员
	public String projectUsers() throws Exception {
		try {
				PrjtUsr prjtUsr = new PrjtUsr();
				prjtUsr.setPrjtNo(prjtDef.getPrjtNo());
				prjtUsr.setPrjtTypId(prjtDef.getTypId());
				//String amoutSQl = "select count(*) as amount from  PrjtUsr " +
						           //" where PrjtTypId = "+prjtUsr.getPrjtTypId()+" and PrjtNo = '"+prjtUsr.getPrjtNo()+"' or PrjtNo is null";
				
				//int total = new PrjtUsrFacade().amount(amoutSQl);
				//if(getPage() == null) {
					//setPage(1);
					//setPagesize(20);
				//}
				//getPageVO().setPage(this.getPage());
				//getPageVO().setPageSize(this.getPagesize());
				//getPageVO().setTotal(total);
				
				String sqlString =  " from PrjtUsr " +
			               " left join PrjtDef on (PrjtDef.PrjtNo = PrjtUsr.PrjtNo) " + 
			               " inner join PrjtRole on (PrjtRole.RoleId = PrjtUsr.RoleId) " + 
			               " inner join Usr on (Usr.Id = PrjtUsr.UsrId) " +
			               " left join _AddrBook on (Usr.Id = _AddrBook.UserId) " +
				           " left join PrjtTyp on (PrjtTyp.TypId = PrjtUsr.PrjtTypId) " ;
				String conDitionSQl = " where PrjtUsr.PrjtTypId = "+prjtUsr.getPrjtTypId()+" and PrjtUsr.PrjtNo = '"+prjtUsr.getPrjtNo()+"'"+" and PrjtRole.Status = 1";
				prjtUsrs = new PrjtUsrFacade().findPrjtUsrs(sqlString, conDitionSQl);
				
				PrjtRole prjtRole = new PrjtRole();
				prjtRole.setPrjtTypId(prjtUsr.getPrjtTypId());
				prjtRole.setStatus(1);
				prjtRole.setRoleTyp(1);
				List<PrjtRole>	 prjtroles = new PrjtRoleFacade().find(prjtRole);	
					
				
				
				List<PrjtUsr> pus = new ArrayList<>();
				
					for(PrjtRole r:prjtroles ){
						
						boolean isExsit = false;
						for(PrjtUsr u: prjtUsrs ){
							if(u.getRoleId().intValue()==r.getRoleId().intValue()){
								isExsit = true;
								pus.add(u);
							}
						}
						
						if(!isExsit){
							PrjtUsr pju = new PrjtUsr();
							pju.setRoleId(r.getRoleId());
							pju.setRoleNm(r.getRoleNm());
							pju.setRoleTyp(r.getRoleTyp());
							pju.setUsrName("");
							pju.setStatus(-1);
							//prjtUsrs.add(pju);
							pus.add(pju);
							
						}
				}
				
				PrjtUsrJson prjtUsrJson = new PrjtUsrJson();
				prjtUsrJson.Rows = pus;
				prjtUsrJson.Total = pus.size();
				
				//System.out.println(JSON.toJSONString(prjtUsrJson));
				setJson(JSON.toJSONString(prjtUsrJson)); 
			
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtDefAction Exception",
					e);
			return ERROR;
		}
		return PGLIS;
	}

	public String list() throws Exception {
		try {
			if (prjtDef == null)
				prjtDef = new PrjtDef();
			
			prjtDef.setCreateBy(getUsrSession().getId());
//			int total = new PrjtDefFacade().amount(prjtDef);
//			if (getPage() == null) {
//				setPage(1);
//				setPagesize(20);
//			}
//			getPageVO().setPage(this.getPage());
//			getPageVO().setPageSize(this.getPagesize());
//			getPageVO().setTotal(total);
			
			
			prjtDefs = new PrjtDefFacade().find(prjtDef);
			PrjtDefJson prjtDefJson = new PrjtDefJson();
			prjtDefJson.Rows = prjtDefs;
			prjtDefJson.Total = prjtDefs.size();

			setJson(JSON.toJSONString(prjtDefJson));
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtDefAction Exception",
					e);
			return ERROR;
		}
		return PGLIS;
	}
	
	public String setTasks()throws Exception {
		try{
					if(taskjson!=null){
						//System.out.println(taskjson);
						//fDVPageRecords =  "[{pageNo:'1',pageVersion:'2',changeDesc:'rr'},{pageNo:'2',pageVersion:'2',changeDesc:'rr'},{pageNo:'3',pageVersion:'2',changeDesc:'rr'}]";
						JSONArray array = JSONArray.fromObject(taskjson);//先读取串数组
						Object[] fs = array.toArray();  
						//转成对像数组
						for(int i=0;i<fs.length;i++){
							JSONObject obj = JSONObject.fromObject(fs[i]);//再使用JsonObject遍历一个个的对像
							Task f = (Task)JSONObject.toBean(obj,zrprjt.vo.Task.class);//指定转换的类型，但仍需要强制转化-成功
							//f.setCreateBy(getUsrSession().getId());
							//f.setCreateDate(new Date());
							f.setLastUpd(getUsrSession().getId());
							f.setLastDate(new Date());
							
							tasks.add(f);
						}
				    }
					prjtDef.setTasks(tasks);

					//new PrjtDefFacade().update(prjtDef);
			
		} catch (Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error(
					"PrjtDefAddAction add Exception", e);
			return ERROR;
		}
			return MESSAGE;
	}
	
	
	private Integer reload =0;
	private WfDoc prjtDefFile;
	public String upPrjtDefFile()throws Exception {
		if (prjtDef != null && prjtDef.getPrjtNo() != null) {
			prjtDef = new PrjtDefFacade().findById(prjtDef);
			
			   if(reload==1){
				   wfDocs = new ArrayList<gnwf.vo.WfDoc>();
				   int cateId =91;
				   String sql = "select "+WfDoc.LIST_FIELDS+" FROM WfDoc where CateId = "+ cateId +" and ProjectNo = '"+prjtDef.getPrjtNo() 
						   +"' and DocVer in( SELECT max(DocVer)as DocVer FROM WfDoc where CateId ="+cateId+ " and ProjectNo = '"+prjtDef.getPrjtNo()+"' )";
				   wfDocs = new WfDocFacade().find(sql,WfDoc.LIST_FIELDS);
				   if(wfDocs.size()>0){
					   prjtDefFile = wfDocs.get(0);
				   }
			   }
		}
		syId = String.valueOf(getUsrSession().getSyId());
		syNm = getUsrSession().getSyNm();
		usrId = String.valueOf(getUsrSession().getId());
		usrNm = getUsrSession().getUsrName();
		return "upPrjtDefFile";
		
	}
	
	
//	 public String download() throws Exception {
//			try {
////				wfDoc = new WfDocFacade().findById(wfDoc);
////				System.out.println(wfDoc.getFileNo());
//				HttpClient httpclient = new HttpClient();  
//			    PostMethod postmethod = new PostMethod("http://gnfile.gionee.com:28080/gnfs/ProcFile!dow.shtml?fileNo="+fileNo);  
//			    
//			    int sendStatus = 0;  
//			    sendStatus = httpclient.executeMethod(postmethod);  
//			    sendStatus = postmethod.getStatusCode();
//			    System.out.println("----"+sendStatus);
//			    if(sendStatus == 200){
//			    	inputStream = (InputStream) postmethod.getResponseBodyAsStream();
//					fileName = new String(wfDoc.getDocName().getBytes("GBK"),"ISO-8859-1");
//					return "download";
//			    }else{
//			        setMsg("下载记录失败");
//					return ERROR;
//			    }
//			}
//			catch(Exception e) {
//				setMsg("下载记录失败");
//				Logger.getLogger(this.getClass()).error("WfDocAction dow Exception", e);
//				return ERROR;
//			}
//		}
	
	public String download() throws Exception {
		try {
			
			HttpClient httpclient = new HttpClient();  
			//String url = "http://gnfile.gionee.com:28080/gnfs/GnFileService!downloadProcFile.shtml";
			String gngile_download_URL = ServletActionContext.getServletContext().getInitParameter("gngile_download_URL");
			
			PostMethod postmethod = new PostMethod(gngile_download_URL);  
		    NameValuePair[] postData = new NameValuePair[1];
			postData[0] = new NameValuePair("fileNo", URLEncoder.encode(fileNo, "UTF-8"));
//			postData[1] = new NameValuePair("syNm",
//					URLEncoder.encode("PDM", "UTF-8"));
//			postData[2] = new NameValuePair("usrId", URLEncoder.encode(String.valueOf(1), "UTF-8"));
//			postData[3] = new NameValuePair("downloadType", URLEncoder.encode("BaseLib", "UTF-8"));
			postmethod.addParameters(postData);
			
		    int sendStatus = 0;  
		    sendStatus = httpclient.executeMethod(postmethod);  
		    sendStatus = postmethod.getStatusCode();
		    System.out.println("----"+sendStatus);
		    if(sendStatus == 200){
		    	inputStream = (InputStream) postmethod.getResponseBodyAsStream();
		    	fileName = new String(fileName.getBytes("GBK"),"ISO-8859-1");
		    	System.out.println(inputStream);
				return "download";
		    }else{
		        setMsg("下载记录失败");
				return ERROR;
		    }
		}
		catch(Exception e) {
			setMsg("下载记录失败");
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error("WfDocAction dow Exception", e);
			return ERROR;
		}
	}

	//private String fileDir; 
	public void afterUploadFile(){
		try{
			String updPrSql = ""; 
			setFileName(URLDecoder.decode(getFileName(), "UTF-8"));
			setTempParams(URLDecoder.decode(getTempParams(), "UTF-8"));
			
//			String fileDir2 = URLDecoder.decode(fileDir, "UTF-8");
//			System.out.println(fileDir2);
			if(getFileNo()!=null){
			    wfDoc = new WfDoc();
				wfDoc.setDocName(getFileName());
				wfDoc.setWfNo(PRJT_DEF_DOC_WFNO);
				wfDoc.setStatus(MSG.CONST_STATUS_1);
				wfDoc.setCreateBy(Integer.valueOf(usrId));
				wfDoc.setCreateDate(new Date());
				String prjtNo = null;
				if (tempParams != null){
					String s[] = tempParams.split(",");
					for(int i = 0;i<s.length;i++){
						 String ss[]=s[i].split(":");
						 if(ss[0].equals("prjtNo")){
							 prjtNo = ss[1];
						 }
						 if(ss[0].equals("prjtDefFileType")){
							
							 if(ss[1].indexOf("PrjtTaskFile")>-1){
								 updPrSql= "update PrjtDef set PrjtTaskFileNo = '"+fileNo+"',PrjtTaskFileName = '"+fileName+"' where PrjtNo='"+prjtNo+"'";
							 }else if(ss[1].indexOf("PrjtDefDocFile")>-1){
								 updPrSql= "update PrjtDef set PrjtDefDocFileNo = '"+fileNo+"',PrjtDefDocFileName = '"+fileName+"' where PrjtNo= '"+prjtNo+"'"; 
								 wfDoc.setCateId(91);
							 }
						 }
					}
				}
				wfDoc.setProjectNo(prjtNo);
				if(wfDoc.getDocId()!=null){//修改产品定义书
					new WfDocFacade().update(wfDoc);
				}else{
					new WfDocFacade().savePrjtDefDoc(wfDoc);
				}
				
				
				new PrjtDefFacade().update(updPrSql);
//				prjtDef = new PrjtDef();
//				prjtDef.setPrjtNo(prjtNo);
//				add();
			}else {
				setMsg("上传附件失败");
			}
		} catch (Exception e) {
			setMsg("上传附件失败");
			Logger.getLogger(this.getClass()).error(
					"PrjtDefAddAction afterUploadFile Exception", e);
			e.printStackTrace();
		}
	}
	
	
	public static final String PRJT_DEF_DOC_WFNO = "prjtDocWfNo";
	public String sav() throws Exception {
		try {
			String msg;
			if (prjtDef.getPrjtNo() == null) {
				prjtDef.setCreateBy(getUsrSession().getId());
				prjtDef.setCreateDate(new Date());
				prjtDef.setLastUpd(getUsrSession().getId());
				prjtDef.setLastDate(new Date());
				prjtDef.setStatus(1);
				prjtDef.setDevDeptNameID(prjtDef.getDevDeptNameID());
				//prjtDef.setDevDeptNameID(devDdd);
				
				//PrjtDef pd = new PrjtDef();
				//pd = new PrjtDefFacade().findById(pd);
				//System.out.println(pd);
				//System.out.println(pd.getDevDeptNameID());
				//pd.setDevDeptNameID(pd.getDevDeptNameID());
				//prjtDef.setDevDeptNameID(prjtDef.getDevDeptNameID());
				
				//prjtDef.setDevDeptNameID(tempDept.getDevDeptNameID());
				//prjtDef.setDevDeptNameID(prjtDef.getDevDeptNameID());
				/*PrjtDef tempDept = new PrjtDefFacade().findById(prjtDef);
				if (tempDept.getDevDeptNameID()!=null) {
					PrjtDef tempDept1 = new PrjtDefFacade().findById(prjtDef);
					prjtDef.setDevDeptNameID(tempDept1.getDevDeptNameID());
				}
*/

				
//				prjtDef.setDevDeptNameID(getUsrSession().getId());
//				if(prjtDef.getDevDeptNameID()==null){
				//prjtDef.setDevDeptNameID(new Integer((String)ServletActionContext.getRequest().getParameter("deptNameID")));
//				}
				
//				Object requestObject = ServletActionContext.getRequest().getParameter("prjtDef.devDeptNameID");
//				if(requestObject!=null){
//					Integer tempId = new Integer((String)requestObject);
//				}
//				
				List<SchCfg> schCfgList = new ArrayList<SchCfg>();
				String sql = "select " + SchCfg.SELF_FIELDS
						+ " from SchCfg where TypId ="+prjtDef.getTypId()+" order by SchCfg.SchNo";
				schCfgList = new SchCfgFacade().find(sql, SchCfg.SELF_FIELDS);
				List<Task> taskList = new ArrayList<Task>();
				for (SchCfg e : schCfgList) {	
					Task task = new Task();
					//task.setPrjtNo(prjtDef.getPrjtNo());
					task.setSchId(e.getSchId());
					task.setSchNo(e.getSchNo());
					task.setLeve(e.getLeve());
					if (null!=e.getParent()&&e.getParent().intValue()!=0) {
						task.setParent(e.getParent());
					}
					task.setTaskNm(e.getSchNm());
					task.setPlanDuration(e.getCfgTime());
					task.setCreateBy(getUsrSession().getId());
					task.setCreateDate(new Date());
					task.setLastUpd(getUsrSession().getId());
					task.setLastDate(new Date());
					task.setStatus(0);
					
					task.setCritical(e.getCritical());
					task.setMilestone(e.getMilestone());
					taskList.add(task);
				}
				prjtDef.setTasks(taskList);
				
				
				
				
				List<PrjtPoint> prjtPoints = new ArrayList<PrjtPoint>();
				String pointCfg = "初版定义提供,ID确认,MD投模,P0投板,P0贴片,P1投板,P1贴片,PR1装机,CTA贴片,CTA组装,机模开模,CTA送测(无委),CTA送测(泰尔)," +
						"P2投板,P2贴片,PR2装机 ,CTA证书,中批投板,天线开模,中批贴片,中批装机,结构封样,量产投板,包材封样,机模封样,软件封板,量产贴片,批量";
				String[]points = pointCfg.split(",");
				for (int i=0;i<points.length;i++) {	
					PrjtPoint point = new PrjtPoint(); 
					point.setLeve(1);
					point.setPointIndex(i+1);
					point.setPointName(points[i]);
					point.setCreateBy(getUsrSession().getId());
					point.setCreateDate(new Date());
					point.setLastUpd(getUsrSession().getId());
					point.setLastDate(new Date());
					point.setStatus(0);
					prjtPoints.add(point);
				}
				prjtDef.setPrjtPoints(prjtPoints);
				
				
				
				
				
				//保存产品定义书
				/**
				if(prjtDefDocFile!=null){
					
					String path="/upload/wfdoc/"+new SimpleDateFormat("yyyyMMdd").format(new Date())+"/"+getUsrSession().getUsrName()+"/"+WFUtil.UUID().replaceAll("-", "");
					File tempfile = new File(getServPath()+path+"/"+prjtDefDocFile.getName());

					tempfile.getParentFile().mkdirs(); 
					FileUtils.copyFile(prjtDefDocFile,tempfile);
					//GenericUtil.ConvertToSwf(action.getServPath()+path+"/",attach.getName());
					
					WfDoc wfDoc = new WfDoc();
					wfDoc.setDocName(prjtDefDocFile.getName());
					wfDoc.setWfNo(PRJT_DEF_DOC_WFNO);
					wfDoc.setUriLink(path+"/"+prjtDefDocFile.getName());
					wfDoc.setStatus(MSG.CONST_STATUS_1);
					wfDoc.setCreateBy(getUsrSession().getId());
					wfDoc.setCreateDate(new Date());
					//wfDoc.setCateId(fileCates.get(i));			//类别
//					if(currentTask!=null && currentTask.getTaskId()!=null){
//						wfDoc.setTaskId(currentTask.getTaskId());
//					}
					prjtDef.setWfDoc(wfDoc);
				}
				**/
//				new PrjtDefFacade().save(getUsrSession().getSyId(),prjtDef);
				new PrjtDefFacade().save(getUsrSession().getSyId(),prjtDef);
				//msg = "保存记录完成,请上传产品定义书";
				msg = "保存记录完成";
				//setMsg("保存记录完成,"+"prjtNo:"+prjtDef.getPrjtNo());
			} else {
//				System.out.println("prjtDef.prjtNo:"+ActionContext.getContext().getSession().get("prjtDef.prjtNo"));
//				prjtDef.setDevDeptNameID(ActionContext.getContext().getSession().get("parm"));
//				System.out.println("PrjtDefAction:save"+ServletActionContext.getRequest().getParameter("prjtDef.devDeptNameID")); 
//				prjtDef.setDevDeptNameID(ServletActionContext.getRequest().getParameter("prjtDef.devDeptNameID"));
				/*Object requestObject = ServletActionContext.getRequest().getParameter("prjtDef.devDeptNameID");
				if(requestObject !=null){
					Integer tempId = new Integer((String)requestObject);
					prjtDef.setDevDeptNameID(tempId);
				}*/
				
			//	prjtDef.setDevDeptNameID(new Integer((String)ServletActionContext.getRequest().getParameter("devDeptNameID")));
				//prjtDef.setLastUpd(getUsrSession().getId());
				//prjtDef.setLastDate(new Date());
				
		
				//prjtDef.setDevDeptNameID(temp.getDevDeptNameID());
				
				//prjtDef.setDevDeptNameID(new Integer((String)ServletActionContext.getRequest().getParameter("prjtDef.devDeptNameID")));
				
				//System.out.println("~~~~~~~~~~~"+temp.getDevDeptNameID()+"~~~~~~~~~~~");
				
				//System.out.println("获取22getDevDeptNameID"+temp.getDevDeptNameID());
				/*if (prjtDef.getDevDeptNameID()==null) {
					prjtDef.setDevDeptNameID(new Integer((String)ServletActionContext.getRequest().getParameter("prjtDef.devDeptNameID")));
					
				} else {
					prjtDef.setDevDeptNameID(new Integer((String)ServletActionContext.getRequest().getParameter("prjtDef.devDeptNameID")));
					
				}*/
				//prjtDef.setDevDeptNameID(new Integer((String)ServletActionContext.getRequest().getParameter("deptNameID")));
				
				PrjtDef temp = new PrjtDefFacade().findById(prjtDef);
				prjtDef.setDevDeptNameID(prjtDef.getDevDeptNameID());
				//prjtDef.setDevDeptNameID(devDeptNameID);
				
				if(temp.getTaskVersion()==null){
					prjtDef.setTaskVersion((float) 1.0);
				}else{
					//prjtDef.setTaskVersion(prjtDef.getTaskVersion()+1);
					PrjtDef  TempPdf = new PrjtDefFacade().findById(prjtDef);
					prjtDef.setTaskVersion(TempPdf.getTaskVersion()+1);
				}
				
				
				if(taskjson!=null){
					JSONArray array = JSONArray.fromObject(taskjson);//先读取串数组
					Object[] fs = array.toArray();  
					//转成对像数组
					SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
					for(int i=0;i<fs.length;i++){
						JSONObject obj = JSONObject.fromObject(fs[i]);//再使用JsonObject遍历一个个的对像
						String[] dateFormats = new String[] {"yyyy-MM-dd"};  
						JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));  
						HashMap map = (HashMap)JSONObject.toBean(obj,HashMap.class);
						Task task = new Task();
						
						int status = Integer.valueOf(map.get("status").toString());
						/*
						 * test
						 * */
//						int devDeptNameID = Integer.valueOf(map.get("devDeptNameID").toString());
						String planStaDate = map.get("planStaDate").toString()+" 00:00:00";
						String planOveDate = map.get("planOveDate").toString()+" 23:59:59";
						
						if(status==0 || status==1 || status==4 ){//status==4为  第一级别的 阶段
							task.setTaskNo(Integer.valueOf(map.get("taskNo").toString()));
							task.setTaskNm(map.get("taskNm").toString());
							task.setPlanDuration(Integer.valueOf(map.get("duration").toString()));
							task.setPlanStaDate(sdf.parse(planStaDate));
							task.setPlanOveDate(sdf.parse(planOveDate));
							task.setPerce(Integer.valueOf(map.get("perce").toString()));
							task.setSummary(Integer.valueOf(map.get("summary").toString()));
							task.setCritical(Integer.valueOf(map.get("critical").toString()));
							task.setMilestone(Integer.valueOf(map.get("milestone").toString()));
							if(map.get("predecessorLink").toString()!=null){
								String predecessorLink =  map.get("predecessorLink").toString();
								task.setPredecessorLink(JSONUtil.Encode(predecessorLink.substring(1, predecessorLink.length()-1)));
							}
							task.setParent(Integer.valueOf(map.get("parent").toString()));
							task.setLastUpd(getUsrSession().getId());
							task.setLastDate(new Date());
							task.setStatus(1);
							
							tasks.add(task); 
						}else if(status==2){
							task.setTaskNo(Integer.valueOf(map.get("taskNo").toString()));
							task.setPlanOveDate(sdf.parse(planOveDate));
							task.setPlanDuration(Integer.valueOf(map.get("duration").toString()));
							task.setLastUpd(getUsrSession().getId());
							task.setLastDate(new Date());
							tasks.add(task); 
						}
					}
			    }
				
				prjtDef.setTasks(tasks);
				new PrjtDefFacade().update(prjtDef);
				msg = "保存记录完成";
				System.out.println("prjtDef.prjtNo2:"+ActionContext.getContext().getSession().get("prjtDef.prjtNo"));
			}
			setMsg(MSG.S_SAV);
			String jsonStr = "{\"msg\":"+"\""+msg+"\""+","+
					 "\"prjtNo\":"+"\""+prjtDef.getPrjtNo()+"\""+"}";
					
			setJson(jsonStr);
			
		} catch (Exception e) {
			setMsg(MSG.F_SAV);
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error(
					"PrjtDefAddAction add Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	
	
	private String prjtPointjson;
	public String updatePrjtPoint(){
		try {
			SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
			List<PrjtPoint> prjtPoints = new ArrayList<PrjtPoint>();
			String jsonStr = "{\"msg\":"+"\""+"保存成功"+"\""+"}";
			if(prjtPointjson!=null){
				JSONArray array = JSONArray.fromObject(prjtPointjson);//先读取串数组
				Object[] fs = array.toArray();  
				//转成对像数组
			
				
				
				String pointversion = prjtDef.getPrjtPointVersion();
				PrjtDef dbPrjtDef = new PrjtDefFacade().findById(prjtDef);
				
				for(int i=0;i<fs.length;i++){
					JSONObject obj = JSONObject.fromObject(fs[i]);//再使用JsonObject遍历一个个的对像
					String[] dateFormats = new String[] {"yyyy-MM-dd"};  
					JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));  
					HashMap map = (HashMap)JSONObject.toBean(obj,HashMap.class);
					
					PrjtPoint point = new PrjtPoint(); 
					if(pointversion!=null&&pointversion.equals(dbPrjtDef.getPrjtPointVersion())){
						jsonStr = "{\"msg\":"+"\""+"版本号没跟新，请修改后更新版本号！"+"\""+"}"; 
						break;
						/*if(map.get("pointId")!=null){
							point.setPointId(Integer.valueOf(map.get("pointId").toString()));
						}*/
					}
			        if(!StringUtil.isNullOrEmpty(map.get("pointIndex").toString())){
			        	point.setPointIndex(Integer.valueOf(map.get("pointIndex").toString()));
			        }else{
			        	point.setCreateBy(getUsrSession().getId());
			        	point.setCreateDate(new Date());
			        }
					//point.setPointIndex(Integer.valueOf(map.get("pointIndex").toString()));
					point.setPointName(map.get("pointName").toString());
					if(map.get("remark")!=null){
						point.setRemark(map.get("remark").toString());
					}
					if(map.get("originalPlanOveDate")!=null){
						point.setOriginalPlanOveDate(sdf.parse(map.get("originalPlanOveDate").toString()+" 00:00:00"));
					}
					if(map.get("actualOverDate")!=null){
						point.setActualOverDate(sdf.parse(map.get("actualOverDate").toString()+" 00:00:00"));
					}
					if(map.get("planOverDate")!=null){
						point.setPlanOverDate(sdf.parse(map.get("planOverDate").toString()+" 00:00:00"));
					}
					if(map.get("responsUser")!=null){
						point.setResponsUser(map.get("responsUser").toString());
					}
					
					if(map.get("delayTime")!=null){
						point.setDelayTime(Integer.valueOf(map.get("delayTime").toString()));
					}
					if(map.get("delayReson")!=null){
						point.setDelayReson(map.get("delayReson").toString());
					}
					if(map.get("parent")!=null){
						point.setParent(Integer.valueOf(map.get("parent").toString()));
					}
					point.setLastUpd(getUsrSession().getId());
					point.setLastDate(new Date());
					point.setStatus(1);
					point.setVersion(prjtDef.getPrjtPointVersion());
					prjtPoints.add(point); 
				}
				
				//发送邮件给项目组所有成员
				ActionContext ctx = ActionContext.getContext();
				HttpServletRequest request = (HttpServletRequest) ctx
						.get(ServletActionContext.HTTP_REQUEST);
				String contextPath = request.getContextPath();
				String url = request.getRequestURL().toString();
				int index = url.indexOf(contextPath);
				String res_url = url.substring(0, index) + contextPath;
				StringBuffer title = new StringBuffer();
				title.append("项目《"+dbPrjtDef.getPrjtNm()+"》的产品定义书已经更新！");
				StringBuffer content = new StringBuffer();
				content.append("尊敬的同事，您好:<p>您参与的项目《"+dbPrjtDef.getPrjtNm()+"》")
				.append("，产品定义书已经更新！</p>链接地址：<a href=")
				.append(res_url)
				.append("/PrjtDef!view.shtml")
				.append(">")
				.append(res_url)
				.append("/PrjtDef!view.shtml")
				/*.append(this.wfQues.getQuesId())*/
				.append("</a>");
				List<Usr> usrlist = new ArrayList<Usr>();
				List<PrjtUsr> prjtUsrList = new PrjtUsrFacade().find("select PrjtUsr.UsrId from PrjtUsr join PrjtRole on PrjtUsr.RoleId = PrjtRole.RoleId"
                        + " where  PrjtUsr.PrjtNo = '"
                        + prjtNo + "'","PrjtUsr.UsrId");
				for(PrjtUsr pu : prjtUsrList) {
					Usr u = new Usr();
					u.setId(pu.getUsrId());
					usrlist.add(u);
				}
				
				WFUtil.sendMailByIT(title.toString(), content.toString(), usrlist);
				
		    }
			
			prjtDef.setPrjtPoints(prjtPoints);
			new PrjtDefFacade().update(prjtDef);
			
			setJson(jsonStr);
		} catch (Exception e) {
			String jsonStr = "{\"msg\":"+"\""+"保存失败"+"\""+"}";
			setJson(jsonStr);
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error(
					"PrjtDefAddAction saveProjPoint Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	
	
	private PrjtPoint prjtPoint;
	public String deletePrjtPoint(){
		try {
			new PrjtPointFacade().remove(prjtPoint);
			String jsonStr = "{\"msg\":"+"\""+"删除成功"+"\""+"}";
			setJson(jsonStr);
		} catch (Exception e) {
			String jsonStr = "{\"msg\":"+"\""+"删除失败"+"\""+"}";
			setJson(jsonStr);
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error(
					"PrjtDefAddAction saveProjPoint Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	
	
    public String  saveProjPoint(){
		try {
			new PrjtDefFacade().update(prjtDef);
			String jsonStr = "{\"msg\":"+"\""+"保存成功"+"\""+"}";
			setJson(jsonStr);
		} catch (Exception e) {
			String jsonStr = "{\"msg\":"+"\""+"保存失败"+"\""+"}";
			setJson(jsonStr);
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error(
					"PrjtDefAddAction saveProjPoint Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
    
    
    public String  findProject(){
		try {
			prjtDef = new PrjtDefFacade().findBy(prjtDef);
			/*PrjtUsr pUsr = new PrjtUsr();
			pUsr.setPrjtNo(prjtDef.getPrjtNo());
			pUsr.setUsrId(getUsrSession().getId());
			PrjtUsr prjtUsr = new PrjtUsrFacade().findBy(pUsr);
			Boolean role = false;
			if (prjtUsr.getRoleId() != null || prjtUsr.getRoleId().equals("")) {
				setJson(JSON.toJSONString(prjtDef)+role);
			}else {
				 role = true;
				 setJson(JSON.toJSONString(prjtDef)+role);
			}*/
			 setJson(JSON.toJSONString(prjtDef));
			
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error(
					"PrjtDefAddAction findProject Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	
	
	public String createScheWfRd() throws Exception {
	//	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			if(taskUID!=null){
				Task tempTask = new Task();
				tempTask.setTaskNo(Integer.valueOf(taskUID));
				tempTask = new TaskFacade().findById(tempTask);
				if(tempTask!=null){
					SchCfg sc = new SchCfg();
					sc.setSchId(tempTask.getSchId());
				    sc = new SchCfgFacade().findById(sc);
					//如果是手动启动 预立项以后的则必须先组建好项目组全部人员
				    if(sc.getSchNo()>2){
				    	String sql = "select "+PrjtRole.SELF_FIELDS +" from PrjtRole " +
				    			"where PrjtRole.PrjtTypId= "+prjtDef.getTypId()+
				    			" and PrjtRole.Status = 1  and PrjtRole.RoleId not in " +
				    			"( select PrjtUsr.RoleId from PrjtUsr " +
				    			" where PrjtUsr.PrjtTypId = "+prjtDef.getTypId()+" and ( PrjtUsr.PrjtNo = '"+prjtDef.getPrjtNo()+"' or PrjtUsr.PrjtNo is null ) )";
				    	List <PrjtRole> prjtRoles = new ArrayList<PrjtRole>();
				    	prjtRoles = new PrjtRoleFacade().find(sql, PrjtRole.SELF_FIELDS);
				    	if(prjtRoles.size()>0){
				    		String str ="项目成员未组建完成！还有项目角色";
				    		for(PrjtRole e:prjtRoles){	
				    			str += e.getRoleNm()+",";
				    		}
				    		str+="没有配置项目组人员!";
				    		setJson(str);
				    		return PGLIS;
				    	}
				    }else {//如果是手动启动 ID设计  堆叠forId则必选先配置好这两条流程相关的人员
				    	String sql = "select "+PrjtRole.SELF_FIELDS +" from PrjtRole " +
				    			"where  PrjtRole.Status = 1  and PrjtRole.RoleId not in " +
				    			"( select PrjtUsr.RoleId from PrjtUsr " +
				    			" where PrjtUsr.PrjtNo = '"+prjtDef.getPrjtNo()+"')" +" and PrjtRole.RoleId in (4,43,42,44,35,18,14,25,17,27,26)";
				    	// 堆叠工程师，ID工程师，PCB工程师，材料认证工程师，硬件设计评审工程师，结构设计评审工程师
				    	List <PrjtRole> prjtRoles = new ArrayList<PrjtRole>();
				    	prjtRoles = new PrjtRoleFacade().find(sql, PrjtRole.SELF_FIELDS);
				    	if(prjtRoles.size()>0){
				    		String str ="项目成员未组建完成！还有项目角色";
				    		for(PrjtRole e:prjtRoles){	
				    			str += e.getRoleNm()+",";
				    		}
				    		str+="没有配置项目组人员!";
				    		setJson(str);
				    		return PGLIS;
				    	}
				    }
					//如果重复启动工作流，则将先前的工作流状态改为已结束
					String updateWfRd = " update WfRd set Status = "+gnwf.ww.MSG.OWFRD_STATUS_3+" where ProjectNo = '"+prjtDef.getPrjtNo()+"' and ScheId = "+tempTask.getSchId();
					new WfRdTaskFacade().update(updateWfRd);
					
			        
					int  flowRepeatSort =1;
					flowRepeatSort = new WfRdFacade().getRepeatSort(tempTask.getPrjtNo(), tempTask.getSchId());
					SchWf schWf = new SchWf();
					schWf.setSchId(tempTask.getSchId());
					schWf = new SchWfFacade().findBy(schWf);
					String flowName = prjtDef.getPrjtNm()+"-"+tempTask.getTaskNm()+"-第"+flowRepeatSort+"轮";
					String wfNo = 	WFUtil.createScheWfRd(tempTask.getPrjtNo(), tempTask.getSchId(), schWf.getFlowId(), flowName, tempTask.getPlanStaDate(), tempTask.getPlanOveDate(),getUsrSession().getId());
					TaskFacade facade = new TaskFacade();
					if(wfNo!=null){
						tempTask.setStatus(2);  //将状态改为  进行中
						tempTask.setStaDate(new Date());
						tempTask.setSender(getUsrSession().getId());
						tempTask.setLastUpd(getUsrSession().getId());
						tempTask.setCreateDate(new Date());
						facade.update(tempTask);
						
						// 查看上级任务状态，如果未启动改成进行中状态
						facade.update("update Task " +
								" set Task.StaDate = '"+GenericUtil.dateFormat(new Date(), "yyyy-MM-dd HH:mm")+"'," +
								" Task.Status = 2," +
								" Task.LastUpd = " +getUsrSession().getId()+","+
								" Task.LastDate = '"+GenericUtil.dateFormat(new Date(), "yyyy-MM-dd HH:mm")+"'" +
								" where Task.Status = 1 and Task.TaskNo = "+tempTask.getParent());
						
						// 查看项目任务状态，如果未启动改成进行中状态
						new PrjtDefFacade().update("update PrjtDef " +
								" set PrjtDef.StaDate = '"+GenericUtil.dateFormat(new Date(), "yyyy-MM-dd HH:mm")+"'," +
								" PrjtDef.Status = 2," +
								" PrjtDef.LastUpd = " +getUsrSession().getId()+","+
								" PrjtDef.LastDate = '"+GenericUtil.dateFormat(new Date(), "yyyy-MM-dd HH:mm")+"'" +
								" where PrjtDef.Status = 1 and PrjtDef.PrjtNo = '"+tempTask.getPrjtNo()+"'");
						
						//重新loadTasksJson
					    JSONArray json = loadTasksJson(2,tempTask.getPrjtNo());
					    //System.out.println(json.toString()); 
						setJson(json.toString());
					}else {
						setMsg("找不到接受该任务的人，任务启动失败，请配置人员接受该任务");
						setJson("noPerson");
						//throw new Exception("createScheWfRd Exception");
					}
					return PGLIS;
				}
			}
			setMsg(MSG.S_START_TASK);
		} catch (Exception e) {
			setMsg(MSG.F_START_TASK);
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error(
					"PrjtDefAddAction add Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	
	
	
	
	public String  selectWfDoc(){
		
		try {
			 wfDocs = new ArrayList<gnwf.vo.WfDoc>();
			wfDocs = WFUtil.selectWfDoc(prjtDef.getPrjtNo(), null);
			
			WfDocJson wfDocJson = new WfDocJson();
			wfDocJson.Rows = wfDocs;
			wfDocJson.Total = wfDocs.size();
			this.setJson(JSON.toJSONString(wfDocJson)); 
			
			//System.out.println(JSON.toJSONString(wfDocs));
			//this.setJson(JSON.toJSONString(wfDocs));
		} catch (Exception e) {
			setMsg(MSG.S_SEA);
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error(
					"PrjtDefAddAction selectWfDoc Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	

	public String setSChCfg() throws Exception {

		return "setSchCfg";

	}
	
	public String gotoselectWfRd() throws Exception {

		return "selectWfRd";

	}
	
	
	public String getPreSchCfgs() throws Exception {
		try{
			
			String sql ="select "+SchCfg.SELF_FIELDS +" from SchCfg where SchCfg.TypId in (select PrjtDef.TypId from PrjtDef where PrjtDef.PrjtNo = '"+task.getPrjtNo()+"')";
		    List<SchCfg> schCfgs = new SchCfgFacade().find(sql,SchCfg.SELF_FIELDS);
		//System.out.println(JSON.toJSONString(schCfgs));
		this.setJson(JSON.toJSONString(schCfgs));
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return PGLIS;
    }
	
	public static final String FIELDS = "WfRd.ScheId,WfRd.FlowId,WfRd.Status,WfRd.CreateBy,WfRd.LastUpd,WfRd.WfNo,WfRd.ProjectNo,WfRd.PreWfNo,WfRd.PlanSDate,WfRd.PlanEDate,WfRd.FactSDate,WfRd.FactEDate,WfRd.CreateDate,WfRd.LastUpdDate,WfRd.WfName,WfRd.WfDesc";
   public String  selectWfRd(){
		try {
			
			schCfg.setLeve(0);
			schCfgs = new SchCfgFacade().find(schCfg);
			List<WfRd>  wfRds= new ArrayList<WfRd>();
			
		    //String findChild = "select "+SchCfg.LIST_FIELDS+ " from SchCfg left join PrjtTyp on (PrjtTyp.TypId = SchCfg.TypId) left join SchWf on (SchWf.SchId = SchCfg.SchId) left join WfCfg on (SchWf.FlowId = WfCfg.FlowId) where Parent = ";
		    String sql =  "select SchCfg.Parent as Parent,SchCfg.SchNm as TaskNm,"+ WfRd.ALL_FIELDS+" from SchCfg " +
					" left join ( select " +
					
                     WfRd.SELF_FIELDS+
					//" WfRd.DocCateId,WfRd.RepeatSort,WfRd.ScheId,WfRd.FlowId,WfRd.Status,WfRd.CreateBy,WfRd.LastUpd,WfRd.WfNo,WfRd.ProjectNo,WfRd.PreWfNo,WfRd.PlanSDate,WfRd.PlanEDate,WfRd.FactSDate,WfRd.FactEDate,WfRd.CreateDate,WfRd.LastUpdDate,WfRd.WfName,WfRd.WfDesc " +
					" from WfRd where WfRd.ProjectNo = '"+task.getPrjtNo()+"'"+") WfRd " + 
					" on (WfRd.ScheId = SchCfg.SchId) " + 
					" left join usr on (WfRd.CreateBy = usr.id) " +
					" where 1=1  ";
		    
		    for(SchCfg e : schCfgs){
		    	String childSql = sql;
				WfRd wfRd = new WfRd();
				wfRd.setScheId(e.getSchId());
				wfRd.setTaskNm(e.getSchNm());
				childSql += " and  SchCfg.Parent = "+e.getSchId() + " and WfRd.WfNo is not null ";
				childSql +=" order by SchCfg.SchId ";
				List<WfRd> children  = new WfRdFacade().find(childSql, "SchCfg.Parent as Parent,SchCfg.SchNm as TaskNm,"+WfRd.ALL_FIELDS);;
				wfRd.setChildren(children);
				if(children.size()>0){
					wfRds.add(wfRd);
				}
			}
		    
//			String sql =  "select SchCfg.Parent as Parent,SchCfg.SchNm as TaskNm,"+ WfRd.ALL_FIELDS+" from SchCfg " +
//					" left join ( select WfRd.ScheId,WfRd.FlowId,WfRd.Status,WfRd.CreateBy,WfRd.LastUpd,WfRd.WfNo,WfRd.ProjectNo,WfRd.PreWfNo,WfRd.PlanSDate,WfRd.PlanEDate,WfRd.FactSDate,WfRd.FactEDate,WfRd.CreateDate,WfRd.LastUpdDate,WfRd.WfName,WfRd.WfDesc " +
//					" from WfRd where WfRd.ProjectNo = '"+task.getPrjtNo()+"'"+") WfRd " + 
//					"on (WfRd.ScheId = SchCfg.SchId) " + 
//					" left join usr on (WfRd.CreateBy = usr.id) " +
//					" where 1=1  and ( SchCfg.Parent = 0 or WfRd.WfNo is not null ) ";
			
			//if(task.getPrjtNo()!=null){
			//	sql +=" and WfRd.ProjectNo = '"+task.getPrjtNo()+"'";
			//}
			
//			if(task.getSchId()!=null){
//				sql +=" and SchCfg.SchId = "+task.getSchId();
//			}
//			
//			sql+=" order by SchCfg.SchId";
//			
//			wfRds = new WfRdFacade().find(sql, "SchCfg.Parent as Parent,SchCfg.SchNm as TaskNm,"+WfRd.ALL_FIELDS);
//			WfRdJson wfRdJson = new WfRdJson();
//			wfRdJson.Rows = wfRds;
//			wfRdJson.Total = wfRds.size();
		    
		    WfRdJson wfRdJson = new WfRdJson();
		    wfRdJson.Rows = wfRds;
		    wfRdJson.Total = wfRds.size();
			//System.out.println(JSON.toJSONString(wfRdJson));
			this.setJson(JSON.toJSONString(wfRdJson));
		} catch (Exception e) {
			setMsg(MSG.S_SEA);
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error(
					"PrjtDefAddAction selectWfDoc Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	
	

	public String schCfgs() throws Exception {
		try {

			if (schCfg == null)
				schCfg = new SchCfg();
			schCfg.setLeve(0);
			int total = new SchCfgFacade().amount(schCfg);
			if (getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			schCfgs = new SchCfgFacade().find(schCfg, getPageVO());

			String findChild = "select " + SchCfg.SELF_FIELDS
					+ " from SchCfg where Parent = ";
			for (SchCfg e : schCfgs) {
				List<SchCfg> children = new SchCfgFacade().find(
						findChild + e.getSchId(), SchCfg.SELF_FIELDS);
				e.setChildren(children);
			}
			SchCfgJson schCfgJson = new SchCfgJson();
			schCfgJson.Rows = schCfgs;
			schCfgJson.Total = total;
			//System.out.println(JSON.toJSONString(schCfgJson));
			setJson(JSON.toJSONString(schCfgJson));

		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass())
					.error("SchCfgAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}

	public String loadProjectTasks() {
		try {
		 PrjtDef prjtDef = new PrjtDef();
		 prjtDef.setPrjtNo(task.getPrjtNo());
		 prjtDef = new PrjtDefFacade().findById(prjtDef);
	     JSONArray json = loadTasksJson(loadTaskType,task.getPrjtNo());
	     String s="{";
	     if(null!=prjtDef.getPrjtTaskFileName()){
	    	 
	    	 s += "\"PrjtTaskFileIcon\":\""+prjtDef.getPrjtTaskFileIcon()+"\",\"PrjtTaskFileNo\":\""+prjtDef.getPrjtTaskFileNo()+"\",\"PrjtTaskFileName\":\""+prjtDef.getPrjtTaskFileName()+"\",";
	     }
		 s = s+"\"Tasks\":"+json.toString()+"}";
		 System.out.println(s);
		  setJson(s);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return PGLIS;
	}

	public String loadProjectTaskFile() {
		try {
		 prjtDef = new PrjtDefFacade().findById(prjtDef);
		 String s = "{";
		 if(null!=prjtDef.getPrjtTaskFileName()){
			 
			s = s+"\"PrjtTaskFileIcon\":\""+prjtDef.getPrjtTaskFileIcon()+"\",\"PrjtTaskFileNo\":\""+prjtDef.getPrjtTaskFileNo()+"\",\"PrjtTaskFileName\":\""+prjtDef.getPrjtTaskFileName()+"\"}";
		 }
		 System.out.println(s);
		  setJson(s);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return PGLIS;
	}
	
	
	
	
	private JSONArray loadTasksJson(int loadType,String prjtNo) throws Exception {
		SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
		
		//SchCfg schCfg = new SchCfg();
		
		String sqlCondition = "";
		if(loadType==1){ //重新设置
			sqlCondition = " where Task.PrjtNo = '"+prjtNo+"'";
		}else if(loadType==2){ //任务管理
			sqlCondition=  " where Task.PrjtNo = '"+prjtNo+"' and Task.Status <> 0";
		}
		
		String sql = "select "+Task.ALL_FIELDS + " from Task  inner join PrjtDef"+
				" on (PrjtDef.PrjtNo = Task.PrjtNo) " +
				" inner join SchCfg on (SchCfg.SchId = Task.SchId) " +
				sqlCondition +
				" order by SchCfg.SchNo";
		tasks = new TaskFacade().find(sql,Task.ALL_FIELDS);
		List<String> plusTasklist = new ArrayList<String>();
		for(Task s: tasks ){
			Map<String, Object> plusTaskMap = new HashMap<String, Object>();
			
			plusTaskMap.put("ManualStart", s.getManualStart());
			plusTaskMap.put("UID", String.valueOf(s.getTaskNo()));
			plusTaskMap.put("Name", s.getTaskNm());
			
			if(s.getLeve()==0){
				plusTaskMap.put("Status", 4);
			}else{
				plusTaskMap.put("Status", s.getStatus());
			}
			
			
			if(s.getStatus()==0){
				plusTaskMap.put("Duration", s.getPlanDuration());
				if(s.getPlanStaDate()!=null){
					plusTaskMap.put("Start", sdft.format(s.getPlanStaDate()));
				}else{
					plusTaskMap.put("Start", sdft.format(new Date()));
				}
				if(s.getPlanOveDate()!=null){
					plusTaskMap.put("Finish", sdft.format(addDate(s.getPlanOveDate(),s.getPlanDuration())));
				}else{
					plusTaskMap.put("Finish", sdft.format(addDate(new Date(),s.getPlanDuration())));
				}
			}else{
				if(s.getPlanStaDate()!=null){
					String start = sdft.format(s.getPlanStaDate())+"T00:00:00";
					//System.out.println(start);
					plusTaskMap.put("Start", start);
				}
				if(s.getPlanOveDate()!=null){
					String end = sdft.format(s.getPlanOveDate())+"T23:59:59";
					//System.out.println(end);
					plusTaskMap.put("Finish", end);
				}
				
				if(s.getStaDate()!=null){
					String actrStart = sdft.format(s.getStaDate())+"T00:00:00";
					//System.out.println(actrStart);
					plusTaskMap.put("ActrStart", actrStart);
				}
				if(s.getOveDate()!=null){
					String actrEnd = sdft.format(s.getOveDate())+"T23:59:59";
					//System.out.println(actrEnd);
					plusTaskMap.put("ActrFinish", actrEnd);
				}
				
				
				
				    // "Duration": 8,
				    //"Start": "2007-01-01T00:00:00",
				    //"Finish": "2007-01-10T00:00:00",
				plusTaskMap.put("Duration", s.getPlanDuration());
			}
			plusTaskMap.put("PercentComplete", 0);
			//plusTaskMap.put("Summary", 0);
			if(s.getCritical()!=null){
					plusTaskMap.put("Critical", s.getCritical().intValue());
			}
			if(s.getMilestone()!=null){
				plusTaskMap.put("Milestone", s.getMilestone().intValue());
		    }
			
		    if (!StringUtil.isNullOrEmpty(s.getPredecessorLink()))
		    {
		        plusTaskMap.put("PredecessorLink",JSONUtil.Decode(s.getPredecessorLink().toString()));
		    }
			 // plusTaskMap.put("PredecessorLink",s.getPredecessorLink());
			plusTaskMap.put("ParentTaskUID", String.valueOf(s.getParent()));
			JSONObject mapjson = JSONObject.fromObject(plusTaskMap);
			//System.out.println(mapjson.toString());
			plusTasklist.add(mapjson.toString());
		}
		System.out.println(JSONArray.fromObject(plusTasklist));
		JSONArray json = JSONArray.fromObject(plusTasklist);
		return json;
	}
	
	public String loadProjectPointsforVersion(){
		try {
			prjtDef = new PrjtDefFacade().findById(prjtDef);
			System.out.println(prjtDef.getPrjtNo());
			List<PrjtDef> prjtDefList = new PrjtDefFacade().find("select * from prjtDef where prjtDef.prjtNo = '"+prjtDef.getPrjtNo()+"'", PrjtDef.SELF_FIELDS);
			PrjtDefJson  prjtDefJson = new PrjtDefJson();
			prjtDefJson.Rows = prjtDefList;
			prjtDefJson.Total=prjtDefList.size();
			setJson(JSON.toJSONString(prjtDefJson));
			}
			catch(Exception e) {
				setMsg(MSG.F_SEA);
				Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
				e.printStackTrace();
				return ERROR;
			}
			return PGLIS;
	}
	
	public String loadProjectPoints(){
		try {
			prjtDef = new PrjtDefFacade().findById(prjtDef);
			SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
			String sqlCondition = " where PrjtPoint.PrjtNo = '"+prjtDef.getPrjtNo()+"'";
			if(prjtDef.getPrjtPointVersion()!=null){
				sqlCondition = sqlCondition+" and PrjtPoint.Version = '"+prjtDef.getPrjtPointVersion()+"'";
			}
			
			String sql = "select "+PrjtPoint.ALL_FIELDS + " from PrjtPoint  " +
					//"inner join PrjtDef on (PrjtDef.PrjtNo = Task.PrjtNo) " +
					//" inner join SchCfg on (SchCfg.SchId = Task.SchId) " +
					sqlCondition +
					" order by PrjtPoint.PointIndex";
			List<PrjtPoint> prjtPoints = new PrjtPointFacade().find(sql,PrjtPoint.ALL_FIELDS);
			
			List<String> plusTasklist = new ArrayList<String>();
			for(PrjtPoint s: prjtPoints ){
				Map<String, Object> plusTaskMap = new HashMap<String, Object>();
				plusTaskMap.put("UID", String.valueOf(s.getPointId()));
				plusTaskMap.put("Name", s.getPointName());
				plusTaskMap.put("PointIndex", s.getPointIndex());
				
				if(s.getPrjtNo()!=null){
					plusTaskMap.put("PrjtNo", s.getPrjtNo());
				}
				if(s.getStatus()!=null){
					plusTaskMap.put("Status", s.getStatus());
				}
				if(s.getParent()!=null){
					plusTaskMap.put("Parent", s.getParent());
				}
				if(s.getRemark()!=null){
					plusTaskMap.put("Remark", s.getRemark());
				}
				if(s.getVersion()!=null){
					
					plusTaskMap.put("Version", s.getVersion());
				}
				
				if(s.getLeve()!=null){
					plusTaskMap.put("Leve", s.getLeve());
				}
				
				if(s.getCreateBy()!=null){
					plusTaskMap.put("CreateBy", s.getCreateBy());
				}
				
				if(s.getResponsUser()!=null){
					plusTaskMap.put("ResponsUser", s.getResponsUser());
				}
				
				if(s.getOriginalPlanOveDate()!=null){
					String originalPlanOveDate = sdft.format(s.getOriginalPlanOveDate())+"T00:00:00";
					plusTaskMap.put("OriginalPlanOveDate", originalPlanOveDate);
				}
				if(s.getActualOverDate()!=null){
					String actualOverDate = sdft.format(s.getActualOverDate())+"T00:00:00";
					plusTaskMap.put("ActualOverDate", actualOverDate);
				}
				if(s.getPlanOverDate()!=null){
					String planOverDate = sdft.format(s.getPlanOverDate())+"T00:00:00";
					plusTaskMap.put("PlanOverDate", planOverDate);
				}
				
				if(s.getDelayTime()!=null){
					plusTaskMap.put("DelayTime", s.getDelayTime());
				}
				if(s.getDelayReson()!=null){
					plusTaskMap.put("DelayReson", s.getDelayReson());
				}
				
				JSONObject mapjson = JSONObject.fromObject(plusTaskMap);
				plusTasklist.add(mapjson.toString());
			}
			System.out.println(JSONArray.fromObject(plusTasklist));
			JSONArray json = JSONArray.fromObject(plusTasklist);
			System.out.println(json.toString());
			System.out.println("1111111111");
			setJson(json.toString());
			}
			catch(Exception e) {
				setMsg(MSG.F_SEA);
				Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
				e.printStackTrace();
				return ERROR;
			}
			return PGLIS;
	}
	
	
	public String showGant(){
		
		return "showGant";
		
	}
	
	
	public String gotoStartFlow(){
		//System.out.println(task.getSchId());
		 setJson("getedFlow");
		
		return SUCCESS;
		
	}

	public Date addDate(Date start, int days) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(start);
//		long timeone = cal.getTimeInMillis();
//		long result = timeone + days * 24 * 60 * 60 * 1000;
//		Date getDate = new Date(result);
//		return getDate;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		//System.out.println("Now Date is-->" + sdf.format(start));
		Calendar d1 = Calendar.getInstance();
		d1.setTime(start); 
		WorkDayCal workDayCal = new WorkDayCal();
		return new Date(workDayCal.getWorkDate(d1,days).getTimeInMillis());
		
	}

	public String upd() throws Exception {
		try {
			// prjtDef.setCreateBy(getSession().getUserId());
			// prjtDef.setCreateDate(new Date());
			// prjtDef.setLastUpd(getSession().getUserId());
			// prjtDef.setLastUpdDate(new Date());
			new PrjtDefFacade().update(prjtDef);
			setMsg(MSG.S_UPD);
		} catch (Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error(
					"PrjtDefAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String voi() throws Exception {
		try {
			if (prjtDefs != null && prjtDefs.size() > 0) {
				for (int i = 0; i < prjtDefs.size(); i++) {
					if (prjtDefs.get(i) != null) {
						// prjtDefs.get(i).setLastUpd(getSession().getUserId());
						// prjtDefs.get(i).setLastUpdDate(new Date());
						new PrjtDefFacade().submit(prjtDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		} catch (Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("PrjtDefAction Exception",
					e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String can() throws Exception {
		try {
			if (prjtDefs != null && prjtDefs.size() > 0) {
				for (int i = 0; i < prjtDefs.size(); i++) {
					if (prjtDefs.get(i) != null) {
						// prjtDefs.get(i).setLastUpd(getSession().getUserId());
						// prjtDefs.get(i).setLastUpdDate(new Date());
						new PrjtDefFacade().submit(prjtDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		} catch (Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("PrjtDefAction Exception",
					e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String del() throws Exception {
		try {
			if (prjtDefs != null && prjtDefs.size() > 0) {
				for (int i = 0; i < prjtDefs.size(); i++) {
					if (prjtDefs.get(i) != null) {
						// prjtDefs.get(i).setLastUpd(getSession().getUserId());
						// prjtDefs.get(i).setLastUpdDate(new Date());
						new PrjtDefFacade().update(prjtDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		} catch (Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("PrjtDefAction Exception",
					e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String sub() throws Exception {
		try {
			if (prjtDefs != null && prjtDefs.size() > 0) {
				for (int i = 0; i < prjtDefs.size(); i++) {
					if (prjtDefs.get(i) != null) {
						// prjtDefs.get(i).setLastUpd(getSession().getUserId());
						// prjtDefs.get(i).setLastUpdDate(new Date());
						new PrjtDefFacade().submit(prjtDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		} catch (Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("PrjtDefAction Exception",
					e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String sta() throws Exception {
		try {
			if (prjtDefs != null && prjtDefs.size() > 0) {
				for (int i = 0; i < prjtDefs.size(); i++) {
					if (prjtDefs.get(i) != null) {
						// prjtDefs.get(i).setLastUpd(getSession().getUserId());
						// prjtDefs.get(i).setLastUpdDate(new Date());
						new PrjtDefFacade().submit(prjtDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		} catch (Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("PrjtDefAction Exception",
					e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String loa() throws Exception {
		try {
			if (prjtDefs != null && prjtDefs.size() > 0) {
				for (int i = 0; i < prjtDefs.size(); i++) {
					if (prjtDefs.get(i) != null) {
						// prjtDefs.get(i).setLastUpd(getSession().getUserId());
						// prjtDefs.get(i).setLastUpdDate(new Date());
						new PrjtDefFacade().submit(prjtDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		} catch (Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("PrjtDefAction Exception",
					e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String stp() throws Exception {
		try {
			if (prjtDefs != null && prjtDefs.size() > 0) {
				for (int i = 0; i < prjtDefs.size(); i++) {
					if (prjtDefs.get(i) != null) {
						// prjtDefs.get(i).setLastUpd(getSession().getUserId());
						// prjtDefs.get(i).setLastUpdDate(new Date());
						new PrjtDefFacade().submit(prjtDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		} catch (Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("PrjtDefAction Exception",
					e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String ove() throws Exception {
		try {
			if (prjtDefs != null && prjtDefs.size() > 0) {
				for (int i = 0; i < prjtDefs.size(); i++) {
					if (prjtDefs.get(i) != null) {
						// prjtDefs.get(i).setLastUpd(getSession().getUserId());
						// prjtDefs.get(i).setLastUpdDate(new Date());
						new PrjtDefFacade().submit(prjtDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		} catch (Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("PrjtDefAction Exception",
					e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String chk() throws Exception {
		try {
			if (prjtDefs != null && prjtDefs.size() > 0) {
				for (int i = 0; i < prjtDefs.size(); i++) {
					if (prjtDefs.get(i) != null) {
						// prjtDefs.get(i).setLastUpd(getSession().getUserId());
						// prjtDefs.get(i).setLastUpdDate(new Date());
						new PrjtDefFacade().review(prjtDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		} catch (Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("PrjtDefAction Exception",
					e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String rev() throws Exception {
		try {
			if (prjtDefs != null && prjtDefs.size() > 0) {
				for (int i = 0; i < prjtDefs.size(); i++) {
					if (prjtDefs.get(i) != null) {
						// prjtDefs.get(i).setLastUpd(getSession().getUserId());
						// prjtDefs.get(i).setLastUpdDate(new Date());
						new PrjtDefFacade().review(prjtDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		} catch (Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("PrjtDefAction Exception",
					e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String con() throws Exception {
		try {
			if (prjtDefs != null && prjtDefs.size() > 0) {
				for (int i = 0; i < prjtDefs.size(); i++) {
					if (prjtDefs.get(i) != null) {
						// prjtDefs.get(i).setLastUpd(getSession().getUserId());
						// prjtDefs.get(i).setLastUpdDate(new Date());
						new PrjtDefFacade().confirm(prjtDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		} catch (Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("PrjtDefAction Exception",
					e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String iss() throws Exception {
		try {
			if (prjtDefs != null && prjtDefs.size() > 0) {
				for (int i = 0; i < prjtDefs.size(); i++) {
					if (prjtDefs.get(i) != null) {
						// prjtDefs.get(i).setLastUpd(getSession().getUserId());
						// prjtDefs.get(i).setLastUpdDate(new Date());
						new PrjtDefFacade().confirm(prjtDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		} catch (Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("PrjtDefAction Exception",
					e);
			return ERROR;
		}
		return MESSAGE;
	}

	public String imp() throws Exception {
		try {
			if (fileInp != null) {
				if (prjtDefs != null && prjtDefs.size() > 0) {
					for (int i = 0; i < prjtDefs.size(); i++) {
						if (prjtDefs.get(i) != null) {
							// prjtDefs.get(i).setLastUpd(getSession().getUserId());
							// prjtDefs.get(i).setLastUpdDate(new Date());
							new PrjtDefFacade().confirm(prjtDefs.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		} catch (Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("PrjtDefAction Exception",
					e);
			return ERROR;
		}
		return PGUPL;
	}

	public String exp() throws Exception {
		try {
			List<PrjtDef> prjtDefs = new PrjtDefFacade().find(prjtDef);
			if (prjtDefs != null && prjtDefs.size() > 0) {
				WritableCellFormat wcformat = new WritableCellFormat();
				wcformat.setAlignment(jxl.format.Alignment.CENTRE);
				wcformat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				wcformat.setBorder(Border.LEFT, BorderLineStyle.THIN);
				wcformat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
				wcformat.setBorder(Border.TOP, BorderLineStyle.THIN);
				wcformat.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
				wcformat.setWrap(true);
				OutputStream os = getOutputStream();
				workbook = Workbook.createWorkbook(os);
				WritableSheet ws = workbook.createSheet("sheet0", 0);
				int index = 0;

				ws.addCell(new Label(index, 1, "分类ID", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "级别", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "实施范围", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "状态", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "创建人", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "最后更新", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "计划开始", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "计划结束", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "实际开始", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "实际结束", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "创建日期", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "更新日期", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "项目编号", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "项目名称", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "备注", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "研发部门", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "百分比", wcformat));
				ws.setColumnView(index, 20);
				index++;

				int row = 2;
				for (int i = 0; i < prjtDefs.size(); i++) {
					row++;
					int m = 0;
					if (prjtDefs.get(i).getTypId() != null)
						ws.addCell(new jxl.write.Number(m, row, prjtDefs.get(i)
								.getTypId(), wcformat));
					m++;
					if (prjtDefs.get(i).getLeve() != null)
						ws.addCell(new jxl.write.Number(m, row, prjtDefs.get(i)
								.getLeve(), wcformat));
					m++;
					if (prjtDefs.get(i).getScope() != null)
						ws.addCell(new jxl.write.Number(m, row, prjtDefs.get(i)
								.getScope(), wcformat));
					m++;
					if (prjtDefs.get(i).getStatus() != null)
						ws.addCell(new jxl.write.Number(m, row, prjtDefs.get(i)
								.getStatus(), wcformat));
					m++;
					if (prjtDefs.get(i).getCreateBy() != null)
						ws.addCell(new jxl.write.Number(m, row, prjtDefs.get(i)
								.getCreateBy(), wcformat));
					m++;
					if (prjtDefs.get(i).getLastUpd() != null)
						ws.addCell(new jxl.write.Number(m, row, prjtDefs.get(i)
								.getLastUpd(), wcformat));
					m++;
					if (prjtDefs.get(i).getPlanStaDate() != null)
						ws.addCell(new jxl.write.DateTime(m, row, prjtDefs.get(
								i).getPlanStaDate(), wcformat));
					m++;
					if (prjtDefs.get(i).getPlanOveDate() != null)
						ws.addCell(new jxl.write.DateTime(m, row, prjtDefs.get(
								i).getPlanOveDate(), wcformat));
					m++;
					if (prjtDefs.get(i).getStaDate() != null)
						ws.addCell(new jxl.write.DateTime(m, row, prjtDefs.get(
								i).getStaDate(), wcformat));
					m++;
					if (prjtDefs.get(i).getOveDate() != null)
						ws.addCell(new jxl.write.DateTime(m, row, prjtDefs.get(
								i).getOveDate(), wcformat));
					m++;
					if (prjtDefs.get(i).getCreateDate() != null)
						ws.addCell(new jxl.write.DateTime(m, row, prjtDefs.get(
								i).getCreateDate(), wcformat));
					m++;
					if (prjtDefs.get(i).getLastDate() != null)
						ws.addCell(new jxl.write.DateTime(m, row, prjtDefs.get(
								i).getLastDate(), wcformat));
					m++;
					if (prjtDefs.get(i).getPrjtNo() != null)
						ws.addCell(new jxl.write.Label(m, row, prjtDefs.get(i)
								.getPrjtNo(), wcformat));
					m++;
					if (prjtDefs.get(i).getPrjtNm() != null)
						ws.addCell(new jxl.write.Label(m, row, prjtDefs.get(i)
								.getPrjtNm(), wcformat));
					m++;
					if (prjtDefs.get(i).getRemark() != null)
						ws.addCell(new jxl.write.Label(m, row, prjtDefs.get(i)
								.getRemark(), wcformat));
					m++;
					if (prjtDefs.get(i).getDevDeptNameID() != null)
						ws.addCell(new jxl.write.Number(m, row, prjtDefs.get(i)
								.getDevDeptNameID(), wcformat));
					m++;
					if (prjtDefs.get(i).getPerce() != null)
						ws.addCell(new jxl.write.Label(m, row, prjtDefs.get(i)
								.getPerce(), wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		} catch (Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error(
					"PrjtDefListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}

	public String prn() throws Exception {
		try {
			if (prjtDefs != null && prjtDefs.size() > 0) {
				for (int i = 0; i < prjtDefs.size(); i++) {
					if (prjtDefs.get(i) != null) {
						// prjtDefs.get(i).setLastUpd(getSession().getUserId());
						// prjtDefs.get(i).setLastUpdDate(new Date());
						new PrjtDefFacade().confirm(prjtDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		} catch (Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("PrjtDefAction Exception",
					e);
			return ERROR;
		}
		return PRINT;
	}

	public String dow() throws Exception {
		try {
			if (prjtDefs != null && prjtDefs.size() > 0) {
				for (int i = 0; i < prjtDefs.size(); i++) {
					if (prjtDefs.get(i) != null) {
						// prjtDefs.get(i).setLastUpd(getSession().getUserId());
						// prjtDefs.get(i).setLastUpdDate(new Date());
						new PrjtDefFacade().confirm(prjtDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		} catch (Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("PrjtDefAction Exception",
					e);
			return ERROR;
		}
		return MESSAGE;
	}

	public List<PrjtDef> getPrjtDefs() {
		return prjtDefs;
	}

	public void setPrjtDefs(List<PrjtDef> prjtDefs) {
		this.prjtDefs = prjtDefs;
	}

	public PrjtDef getPrjtDef() {
		return prjtDef;
	}

	public void setPrjtDef(PrjtDef prjtDef) {
		this.prjtDef = prjtDef;
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

	public java.util.List<zrprjt.vo.PrjtAuth> getPrjtAuths() {
		return prjtAuths;
	}

	public void setPrjtAuths(java.util.List<zrprjt.vo.PrjtAuth> prjtAuths) {
		this.prjtAuths = prjtAuths;
	}

	public void addtoPrjtAuths(zrprjt.vo.PrjtAuth prjtAuth) {
		if (getPrjtAuths() == null)
			setPrjtAuths(new java.util.ArrayList<zrprjt.vo.PrjtAuth>());
		getPrjtAuths().add(prjtAuth);
	}

	public java.util.List<zrprjt.vo.PrjtRole> getPrjtRoles() {
		return prjtRoles;
	}

	public void setPrjtRoles(java.util.List<zrprjt.vo.PrjtRole> prjtRoles) {
		this.prjtRoles = prjtRoles;
	}

	public void addtoPrjtRoles(zrprjt.vo.PrjtRole prjtRole) {
		if (getPrjtRoles() == null)
			setPrjtRoles(new java.util.ArrayList<zrprjt.vo.PrjtRole>());
		getPrjtRoles().add(prjtRole);
	}

	public java.util.List<zrprjt.vo.PrjtUsr> getPrjtUsrs() {
		return prjtUsrs;
	}

	public void setPrjtUsrs(java.util.List<zrprjt.vo.PrjtUsr> prjtUsrs) {
		this.prjtUsrs = prjtUsrs;
	}

	public void addtoPrjtUsrs(zrprjt.vo.PrjtUsr prjtUsr) {
		if (getPrjtUsrs() == null)
			setPrjtUsrs(new java.util.ArrayList<zrprjt.vo.PrjtUsr>());
		getPrjtUsrs().add(prjtUsr);
	}

	public java.util.List<zrprjt.vo.Task> getTasks() {
		return tasks;
	}

	public void setTasks(java.util.List<zrprjt.vo.Task> tasks) {
		this.tasks = tasks;
	}

	public void addtoTasks(zrprjt.vo.Task task) {
		if (getTasks() == null)
			setTasks(new java.util.ArrayList<zrprjt.vo.Task>());
		getTasks().add(task);
	}

	public java.util.List<zrprjt.vo.PrjtTyp> getPrjtTyps() {
		return prjtTyps;
	}

	public void setPrjtTyps(java.util.List<zrprjt.vo.PrjtTyp> prjtTyps) {
		this.prjtTyps = prjtTyps;
	}

	public void addtoPrjtTyps(zrprjt.vo.PrjtTyp prjtTyp) {
		if (getPrjtTyps() == null)
			setPrjtTyps(new java.util.ArrayList<zrprjt.vo.PrjtTyp>());
		getPrjtTyps().add(prjtTyp);
	}

	public List<SchCfg> getSchCfgs() {
		return schCfgs;
	}

	public void setSchCfgs(List<SchCfg> schCfgs) {
		this.schCfgs = schCfgs;
	}

	public SchCfg getSchCfg() {
		return schCfg;
	}

	public void setSchCfg(SchCfg schCfg) {
		this.schCfg = schCfg;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getTaskjson() {
		return taskjson;
	}

	public void setTaskjson(String taskjson) {
		this.taskjson = taskjson;
	}

	public String getTaskUID() {
		return taskUID;
	}

	public void setTaskUID(String taskUID) {
		this.taskUID = taskUID;
	}

	public int getLoadTaskType() {
		return loadTaskType;
	}

	public void setLoadTaskType(int loadTaskType) {
		this.loadTaskType = loadTaskType;
	}

	public Integer getRoleleve() {
		return roleleve;
	}

	public void setRoleleve(Integer roleleve) {
		this.roleleve = roleleve;
	}

	public String getSlePrjNo() {
		return slePrjNo;
	}

	public void setSlePrjNo(String slePrjNo) {
		this.slePrjNo = slePrjNo;
	}

	public Gp getGp() {
		return gp;
	}

	public void setGp(Gp gp) {
		this.gp = gp;
	}

	public List <gnwf.vo.WfDoc> getWfDocs() {
		return wfDocs;
	}

	public void setWfDocs(List <gnwf.vo.WfDoc> wfDocs) {
		this.wfDocs = wfDocs;
	}

	public boolean isShowCreatePrjtTab() {
		return showCreatePrjtTab;
	}

	public void setShowCreatePrjtTab(boolean showCreatePrjtTab) {
		this.showCreatePrjtTab = showCreatePrjtTab;
	}

	public java.io.File getPrjtDefDocFile() {
		return prjtDefDocFile;
	}

	public void setPrjtDefDocFile(java.io.File prjtDefDocFile) {
		this.prjtDefDocFile = prjtDefDocFile;
	}



	public String getMailTitle() {
		return mailTitle;
	}



	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}



	public String getMailContent() {
		return mailContent;
	}



	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}



	public List<Usr> getMailUserList() {
		return mailUserList;
	}



	public void setMailUserList(List<Usr> mailUserList) {
		this.mailUserList = mailUserList;
	}



	public String getUsrIds() {
		return usrIds;
	}



	public void setUsrIds(String usrIds) {
		this.usrIds = usrIds;
	}



	public String getUsrString() {
		return usrString;
	}



	public void setUsrString(String usrString) {
		this.usrString = usrString;
	}



	public String getFileNo() {
		return fileNo;
	}



	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}



	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public String getSyId() {
		return syId;
	}



	public void setSyId(String syId) {
		this.syId = syId;
	}



	public String getSyNm() {
		return syNm;
	}



	public void setSyNm(String syNm) {
		this.syNm = syNm;
	}



	public String getUsrId() {
		return usrId;
	}



	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}



	public String getUsrNm() {
		return usrNm;
	}



	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
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



	public String getTempParams() {
		return tempParams;
	}



	public void setTempParams(String tempParams) {
		this.tempParams = tempParams;
	}



	public WfDoc getWfDoc() {
		return wfDoc;
	}



	public void setWfDoc(WfDoc wfDoc) {
		this.wfDoc = wfDoc;
	}



	public String getPrjtDefFileType() {
		return prjtDefFileType;
	}



	public void setPrjtDefFileType(String prjtDefFileType) {
		this.prjtDefFileType = prjtDefFileType;
	}



	public InputStream getInputStream() {
		return inputStream;
	}



	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}



	public String getLoadPrjtTreeType() {
		return loadPrjtTreeType;
	}



	public void setLoadPrjtTreeType(String loadPrjtTreeType) {
		this.loadPrjtTreeType = loadPrjtTreeType;
	}



	public Integer getReload() {
		return reload;
	}



	public void setReload(Integer reload) {
		this.reload = reload;
	}



	public WfDoc getPrjtDefFile() {
		return prjtDefFile;
	}



	public void setPrjtDefFile(WfDoc prjtDefFile) {
		this.prjtDefFile = prjtDefFile;
	}



	public Integer getDevDeptNameID() {
		return devDeptNameID;
	}



	public void setDevDeptNameID(Integer devDeptNameID) {
		this.devDeptNameID = devDeptNameID;
	}



	public String getPrjtPointjson() {
		return prjtPointjson;
	}



	public void setPrjtPointjson(String prjtPointjson) {
		this.prjtPointjson = prjtPointjson;
	}



	public PrjtPoint getPrjtPoint() {
		return prjtPoint;
	}



	public void setPrjtPoint(PrjtPoint prjtPoint) {
		this.prjtPoint = prjtPoint;
	}



	


//	public String getFileDir() {
//		return fileDir;
//	}
//
//
//
//	public void setFileDir(String fileDir) {
//		this.fileDir = fileDir;
//	}



//	public int getUpdWfDoc() {
//		return updWfDoc;
//	}
//
//
//
//	public void setUpdWfDoc(int updWfDoc) {
//		this.updWfDoc = updWfDoc;
//	}



}