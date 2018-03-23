package gnwf.ww.json;

import gnwf.facade.WfDocFacade;
import gnwf.vo.WfDoc;
import gnwf.vo.json.WfDocJson;
import gnwf.ww.BasicAction;
import gnwf.ww.MSG;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import jxl.write.WritableWorkbook;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.opensymphony.webwork.ServletActionContext;

public class ProcFileWfDocAction extends BasicAction {
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
	

	public String execute() throws Exception {
		return INITIALIZES;
	}
	
	
	//TODO 直接发布
	public String  transToBasicLib()throws Exception{
		
		String gngile_trans_URL = ServletActionContext.getServletContext().getInitParameter("gngile_trans_URL");
		
		
		
		HttpClient httpclient=new HttpClient();  
		PostMethod postmethod=new PostMethod(gngile_trans_URL);  
		try {
		        NameValuePair[] postData=new NameValuePair[1];  
		        postData[0]=new NameValuePair("procFile.fileNo",wfDoc.getFileNo());  
		        postmethod.addParameters(postData);  
		        int sendStatus=0;  
		        sendStatus=httpclient.executeMethod(postmethod);  
	            System.out.println("response=" + postmethod.getResponseBodyAsString());  
	            sendStatus = postmethod.getStatusCode();
	            System.out.println("sendStatus:"+sendStatus);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("transToBasicLib Exception", e);
			return ERROR;
		}
		finally{  
		    //释放  
        	 postmethod.releaseConnection();  
        }  
		return SUCCESS;
	}
	
	
	//TODO  开启归档流程
		public String  StatTtblFlow()throws Exception{
			
			try {
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
	
	
	    //TODO 项目查找工作流附件
		public String  selPrjtProcFile() throws Exception{
//			if((null == wfDoc.getProjectNo())){
//				return null;
//			}
			
			String fields = "WfDoc.DocId,WfDoc.TaskId,WfDoc.FileNo,WfDoc.ProjectNo,WfDoc.Status," +
					"WfDoc.CreateBy,WfDoc.LastUpd,WfDoc.CateId,WfDoc.FlowId,WfDoc.DeptDocId," +
					"WfDoc.WfNo,WfDoc.UriLink,WfDoc.CreateDate,WfDoc.LastUpdDate,WfDoc.DocName,WfDoc.DocVer," +
					"Usr.UsrName,WfScheCfgDoc.DocName as CateName,WfRd.WfName as WfName";
			String sql = "select "+fields+" " +
					   " from WfDoc " +
		               " left join usr on (WfDoc.CreateBy = usr.id) " +
		               " left join WfScheCfgDoc on(WfScheCfgDoc.DocId=WfDoc.CateId) " +
		               " inner join ProcFile on(WfDoc.FileNo = ProcFile.FileNo) " +
		               " left join WfRd on (WfRd.WfNo = WfDoc.WfNo) " + 
		               " left join WfCfg on (WfRd.FlowId = WfCfg.FlowId) " + 
		               " where 1=1 ";
		               if(null!= wfDoc.getProjectNo()&&!wfDoc.getProjectNo().trim().isEmpty()){
		            	   sql+= " and  WfDoc.projectNo='"+wfDoc.getProjectNo().trim()+"' ";
		               }
//		               else{
//		            	   sql+= " and  WfDoc.projectNo is null ";
//		               }
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
			setMsg("系统找不到文件噢");
			Logger.getLogger(this.getClass()).error("WfDocAction dow Exception", e);
			return ERROR;
		}
		catch(Exception e) {
			setMsg("下载记录失败");
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
//	public String pDoc(){
//		return "pdoc";
//	}
//	public String main(){
//		return "main";
//	}
}