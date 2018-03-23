package gnwf.ww.json;

import gnwf.facade.WfDocFacade;
import gnwf.vo.WfDoc;
import gnwf.vo.json.WfDocJson;
import gnwf.ww.BasicAction;
import gnwf.ww.MSG;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import jxl.write.WritableWorkbook;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.opensymphony.webwork.ServletActionContext;

public class ShareLibWfDocAction extends BasicAction {
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
	
	
	
	

	
	
//	private ProcFile pf ;

	public String execute() throws Exception {
//		try {
//			if(wfDoc != null && wfDoc.getDocId() != null) {
//				wfDoc = new WfDocFacade().findById(wfDoc);
//				setJson(JSON.toJSONString(wfDoc)); 
//			}
//			wfRdTasks = new gnwf.facade.WfRdTaskFacade().find("select "+gnwf.vo.WfRdTask.SELF_FIELDS+" from WfRdTask",gnwf.vo.WfRdTask.SELF_FIELDS);
//			wfRds = new gnwf.facade.WfRdFacade().find("select "+gnwf.vo.WfRd.SELF_FIELDS+" from WfRd",gnwf.vo.WfRd.SELF_FIELDS);
//
//		}
//		catch(Exception e) {
//			setMsg(MSG.F_SEA);
//			Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
//			return ERROR;
//		}
		return INITIALIZES;
	}
	
	
	
    public String shareLib() throws Exception {
		
		return "shareLib";
	}
    
    
public String shareRale() throws Exception {
		
		return "shareRale";
}
    
    

private String fileNo;
private InputStream inputStream2;
public String dowLoad() throws Exception {
	try {
		HttpClient httpclient = new HttpClient();  
		String gngile_trans_URL = ServletActionContext.getServletContext().getInitParameter("gngile_trans_URL");
		
		
		
		//String url = "http://gnfile.gionee.com:28080/gnfs/GnFileService!downloadBaseLib.shtml";
	    PostMethod postmethod = new PostMethod(gngile_trans_URL);  
	    NameValuePair[] postData = new NameValuePair[3];
		postData[0] = new NameValuePair("fileNo", URLEncoder.encode(fileNo.trim(), "UTF-8"));
		postData[1] = new NameValuePair("syNm",
				URLEncoder.encode(getUsrSession().getSyNm(), "UTF-8"));
		postData[2] = new NameValuePair("usrId", URLEncoder.encode(String.valueOf(getUsrSession().getId()), "UTF-8"));
		postmethod.addParameters(postData);
	    int sendStatus = 0;  
	    sendStatus = httpclient.executeMethod(postmethod);  
	    sendStatus = postmethod.getStatusCode();
	    if(sendStatus == 200){
	    	inputStream2 = (InputStream) postmethod.getResponseBodyAsStream();
	    	fileName = new String(fileName.getBytes("GBK"),"ISO-8859-1");
	    return "download";
	    }else{
	        setMsg("���ؼ�¼ʧ��");
			return ERROR;
	    }
	}
	catch(Exception e) {
		setMsg("���ؼ�¼ʧ��");
		e.printStackTrace();
		Logger.getLogger(this.getClass()).error("WfDocAction dow Exception", e);
		return ERROR;
	}

}
	
	
	
	public String list() throws Exception {
		try {
			if(wfDoc == null) wfDoc = new WfDoc();
			int total = new WfDocFacade().amount(wfDoc);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfDocs = new WfDocFacade().find(wfDoc,getPageVO());
			WfDocJson wfDocJson = new WfDocJson();
			wfDocJson.Rows = wfDocs;
			wfDocJson.Total = total;
			setJson(JSON.toJSONString(wfDocJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	
	//TODO 
//	public String  findShareRela ()throws Exception{
//		
//		List<ShareRela> relas = new ArrayList<ShareRela>() ;
//		ShareRela shareRela = new ShareRela();
//		shareRela.setFileNo(wfDoc.getFileNo());
//		try {
//			
//			GnFileService gnFileService = new PDMGnFileServiceImpl();
//			int total = gnFileService.ShareRelaAmount(wfDoc.getFileNo());
//			if(getPage() == null) {
//				setPage(1);
//				setPagesize(20);
//			}
//			getPageVO().setPage(this.getPage());
//			getPageVO().setPageSize(this.getPagesize());
//			getPageVO().setTotal(total);
//			relas = gnFileService.findShareRelaByFileNo(wfDoc.getFileNo(),getPageVO());
//			ShareRelaJson shareRelaJson = new ShareRelaJson(); 
//			shareRelaJson.Rows = relas;
//			shareRelaJson.Total = relas.size();
//			setJson(JSON.toJSONString(shareRelaJson)); 
//		}
//		catch(Exception e) {
//			setMsg(MSG.F_SEA);
//			Logger.getLogger(this.getClass()).error("transToBasicLib Exception", e);
//			return ERROR;
//		}
//		return SUCCESS;
//		
//	}
	
	//TODO �ջ��ѷ����ĸ�ĳ�˵Ĳ鿴����Ȩ��
	public String  takeBackShareReal ()throws Exception{
		
		try {
			//TODO
//			GnFileService gnFileService = new PDMGnFileServiceImpl();
//			gnFileService.transToBasicLibByFileBo(wfDoc.getFileNo());
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("transToBasicLib Exception", e);
			return ERROR;
		}
		return SUCCESS;
		
	}
	
	
	//TODO  �鿴ĳ����ĵ��ķ��ʼ�¼
		public String  selecVisitLog()throws Exception{
			
			try {
				//TODO
//				ProcFile pf = new ProcFile();
//				GnFileService gnFileService = new PDMGnFileServiceImpl();
//				gnFileService.transToBasicLib(pf);
			}
			catch(Exception e) {
				setMsg(MSG.F_SEA);
				Logger.getLogger(this.getClass()).error("transToBasicLib Exception", e);
				return ERROR;
			}
			return SUCCESS;
			
		}
		
		
	
	
	//TODO ��Ŀ���ҹ������
		public String  selPrjtShareLib() throws Exception{
//			if((null == wfDoc.getProjectNo())){
//				return null;
//			}
			
//			String str = "";
//			if(null !=scheId){
//				str = " and scheid="+scheId;
//			}
//			String fields = "WfDoc.DocId,WfDoc.TaskId,WfDoc.FileNo,WfDoc.ProjectNo,WfDoc.Status,WfDoc.CreateBy,WfDoc.LastUpd,WfDoc.CateId,WfDoc.FlowId,WfDoc.DeptDocId,WfDoc.WfNo,WfDoc.UriLink,WfDoc.CreateDate,WfDoc.LastUpdDate,WfDoc.DocName,WfDoc.DocVer," +
//					"Usr.UsrName,WfScheCfgDoc.DocName as CateName";
//			String sql = "select "+fields+" " +
//					   " from WfDoc " +
//		               " left join usr on (WfDoc.CreateBy = usr.id) " +
//		               " left join WfScheCfgDoc on(WfScheCfgDoc.DocId=WfDoc.CateId) " +
//		               " inner join ShareLib on(WfDoc.FileNo = ShareLib.FileNo) " +
//		               " where 1=1 ";
//		               if(null!= wfDoc.getProjectNo()&&!wfDoc.getProjectNo().trim().isEmpty()){
//			        	   sql += "and  WfDoc.projectNo='"+wfDoc.getProjectNo().trim()+"' ";
//			           }
//		               if(null!= wfDoc.getDocName()&&!wfDoc.getDocName().trim().isEmpty()){
//		            	   sql+= " and  WfDoc.DocName like '%"+wfDoc.getDocName().trim()+"%' ";
//		               }
//		               if(null!= wfDoc.getWfNo()&&!wfDoc.getWfNo().trim().isEmpty()){
//		            	   sql+= " and  WfDoc.WfNo  like '%"+wfDoc.getWfNo().trim()+"%' ";
//		               }
//			
		               
		             	String fields = "WfDoc.DocId,WfDoc.TaskId,WfDoc.FileNo,WfDoc.ProjectNo,WfDoc.Status," +
								"WfDoc.CreateBy,WfDoc.LastUpd,WfDoc.CateId,WfDoc.FlowId,WfDoc.DeptDocId," +
								"WfDoc.WfNo,WfDoc.UriLink,WfDoc.CreateDate,WfDoc.LastUpdDate,WfDoc.DocName,WfDoc.DocVer," +
								"Usr.UsrName,WfScheCfgDoc.DocName as CateName,WfRd.WfName as WfName";
						String sql = "select "+fields+" " +
								   " from WfDoc " +
					               " left join usr on (WfDoc.CreateBy = usr.id) " +
					               " left join WfScheCfgDoc on(WfScheCfgDoc.DocId=WfDoc.CateId) " +
					               " inner join ShareLib on(WfDoc.FileNo = ShareLib.FileNo) " +
					               " left join WfRd on (WfRd.WfNo = WfDoc.WfNo) " + 
					               " left join WfCfg on (WfRd.FlowId = WfCfg.FlowId) " + 
					               " where 1=1 ";
					               if(null!= wfDoc.getProjectNo()&&!wfDoc.getProjectNo().trim().isEmpty()){
					            	   sql+= " and  WfDoc.projectNo='"+wfDoc.getProjectNo().trim()+"' ";
					               }
//					               else{
//					            	   sql+= " and  WfDoc.projectNo is null ";
//					               }
					               if(null!= wfDoc.getDocName()&&!wfDoc.getDocName().trim().isEmpty()){
					            	   sql+= " and  WfDoc.DocName like '%"+wfDoc.getDocName().trim()+"%' ";
					               }
					               if(null!= wfDoc.getCateId()){
					            	   sql+= " and  WfScheCfgDoc.DocId  ="+wfDoc.getCateId();
					               }
					               if(null!= wfDoc.getFlowId()){
					            	   sql+= " and  WfCfg.FlowId ="+wfDoc.getFlowId();
					               }
		               
		               
			wfDocs = new WfDocFacade().find(sql,fields);
           
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
			contentType="application/octet-stream;charset=gb2312";
			inputStream = new FileInputStream(new File(this.getServPath()+wfDoc.getUriLink()));
			fileName = new String(wfDoc.getDocName().getBytes("GBK"),"ISO-8859-1");
			return "download";
		}
		catch(FileNotFoundException e) {
			setMsg("ϵͳ�Ҳ����ļ���");
			Logger.getLogger(this.getClass()).error("WfDocAction dow Exception", e);
			return ERROR;
		}
		catch(Exception e) {
			setMsg("���ؼ�¼ʧ��");
			Logger.getLogger(this.getClass()).error("WfDocAction dow Exception", e);
			return ERROR;
		}
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
	public void setWfDocRevs(java.util.List<gnwf.vo.WfDocRev> wfDocRevs){
		this.wfDocRevs = wfDocRevs;
	}
	public void addtoWfDocRevs(gnwf.vo.WfDocRev wfDocRev){
		if(getWfDocRevs() == null) setWfDocRevs(new java.util.ArrayList<gnwf.vo.WfDocRev>());
			getWfDocRevs().add(wfDocRev);
	}
	public java.util.List<gnwf.vo.WfRdTask> getWfRdTasks() {
		return wfRdTasks;
	}
	public void setWfRdTasks(java.util.List<gnwf.vo.WfRdTask> wfRdTasks){
		this.wfRdTasks = wfRdTasks;
	}
	public void addtoWfRdTasks(gnwf.vo.WfRdTask wfRdTask){
		if(getWfRdTasks() == null) setWfRdTasks(new java.util.ArrayList<gnwf.vo.WfRdTask>());
			getWfRdTasks().add(wfRdTask);
	}
	public java.util.List<gnwf.vo.WfRd> getWfRds() {
		return wfRds;
	}
	public void setWfRds(java.util.List<gnwf.vo.WfRd> wfRds){
		this.wfRds = wfRds;
	}
	public void addtoWfRds(gnwf.vo.WfRd wfRd){
		if(getWfRds() == null) setWfRds(new java.util.ArrayList<gnwf.vo.WfRd>());
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



	public String getFileNo() {
		return fileNo;
	}



	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}



	public InputStream getInputStream2() {
		return inputStream2;
	}



	public void setInputStream2(InputStream inputStream2) {
		this.inputStream2 = inputStream2;
	}



//	public ProcFile getPf() {
//		return pf;
//	}
//
//
//
//	public void setPf(ProcFile pf) {
//		this.pf = pf;
//	}
//	public String sLib(){
//		return "slib";
//	}
}