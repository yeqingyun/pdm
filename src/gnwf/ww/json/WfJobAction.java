package gnwf.ww.json;

import gnhr.facade.HrJobCfgFacade;
import gnhr.vo.HrJobCfg;
import gnhr.vo.json.HrJobCfgJson;
import gnwf.facade.WfJobFacade;
import gnwf.facade.WfJobUserFacade;
import gnwf.vo.WfCfg;
import gnwf.vo.WfJob;
import gnwf.vo.WfJobUser;
import gnwf.ww.BasicAction;
import gnwf.ww.MSG;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;

import zrsy.facade.UsrFacade;
import zrsy.vo.Usr;
import zrsy.vo.json.TreeJson;

import com.alibaba.fastjson.JSON;

public class WfJobAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfJob> wfJobs;
	private WfJob wfJob = new WfJob();
	private java.util.List<gnwf.vo.WfCfg> wfCfgs;
	private List<HrJobCfg> jobs;
	private List<TreeJson> jobsJson;
	private List<HrJobCfg> hrJobCfgs;
	private HrJobCfg hrJobCfg;
	private List<WfJobUser> wfJobUsers;
	private List<Usr> usrs;

	public String execute() throws Exception {
		try {
			if(wfJob != null && wfJob.getJobId() != null) {
				wfJob = new WfJobFacade().findById(wfJob);
				setJson(JSON.toJSONString(wfJob)); 
			}
			wfCfgs = new gnwf.facade.WfCfgFacade().find("select "+gnwf.vo.WfCfg.SELF_FIELDS+" from WfCfg",gnwf.vo.WfCfg.SELF_FIELDS);
			jobs = new HrJobCfgFacade().find("select "+HrJobCfg.SELF_FIELDS+" from HrJobCfg ",HrJobCfg.SELF_FIELDS);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfJobAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(wfJob != null && wfJob.getJobId() != null) {
				//wfJob = new WfJobFacade().findById(wfJob);
				//setJson(JSON.toJSONString(wfJob)); 
			//}
			wfCfgs = new gnwf.facade.WfCfgFacade().find("select "+gnwf.vo.WfCfg.SELF_FIELDS+" from WfCfg",gnwf.vo.WfCfg.SELF_FIELDS);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfJobAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(wfJob != null && wfJob.getJobId() != null) {
				hrJobCfg = new HrJobCfg();
				hrJobCfg.setJobId(wfJob.getJobId());
				wfJob = new WfJobFacade().findById(wfJob);
				hrJobCfg = new HrJobCfgFacade().findBy(hrJobCfg);
				setJson(JSON.toJSONString(hrJobCfg)); 
				
				wfCfgs = new gnwf.facade.WfCfgFacade().find("select "+gnwf.vo.WfCfg.SELF_FIELDS+" from WfCfg where DeptId = " + hrJobCfg.getDeptId(),gnwf.vo.WfCfg.SELF_FIELDS);
				wfJobs = new WfJobFacade().find("select "+WfJob.SELF_FIELDS+" from WfJob where JobId = " +  hrJobCfg.getJobId(), WfJob.SELF_FIELDS);
				wfJobUsers = new WfJobUserFacade().find("select "+WfJobUser.SELF_FIELDS+" from WfJobUser where JobId = " +  hrJobCfg.getJobId(), WfJobUser.SELF_FIELDS);
				
				usrs = new UsrFacade().find("select "+Usr.SELF_FIELDS+" from Usr where DeptId = " +  hrJobCfg.getDeptId(),Usr.SELF_FIELDS);
				for(Usr usr : usrs) {
					for(WfJobUser ju : wfJobUsers) {
						if(usr.getId().equals(ju.getUserId())) {
							usr.setChecked("checked");
						}
					}
				}
				boolean isNull = false;
				if(wfJobs == null || wfJobs.size() == 0) {
					wfJobs = new ArrayList<WfJob>();
					isNull = true;
				}
				for(WfCfg wcf : wfCfgs) {
					if(isNull) {
						WfJob wj  = new WfJob();
						wj.setJobId(wcf.getFlowId());
						wj.setJobId(hrJobCfg.getJobId());
						wj.setFlowId(wcf.getFlowId());
						wj.setFlowName(wcf.getFlowName());
						wfJobs.add(wj);
					} else {
						for(WfJob wj :wfJobs){
							wj.setFlowName(wcf.getFlowName());
							if(wcf.getFlowId().equals(wj.getFlowId())) {
								wj.setChecked("checked");
							}
						}
					}
				}
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfJobAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(wfJob != null && wfJob.getJobId() != null) {
				hrJobCfg = new HrJobCfg();
				hrJobCfg.setJobId(wfJob.getJobId());
				hrJobCfg = new HrJobCfgFacade().findBy(hrJobCfg);
				setJson(JSON.toJSONString(hrJobCfg)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfJobAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(hrJobCfg == null) hrJobCfg = new HrJobCfg();
			int total = new HrJobCfgFacade().amount(hrJobCfg);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			hrJobCfgs = new HrJobCfgFacade().find(hrJobCfg,getPageVO());
			HrJobCfgJson hrJobCfgJson = new HrJobCfgJson();
			hrJobCfgJson.Rows = hrJobCfgs;
			hrJobCfgJson.Total = total;
			setJson(JSON.toJSONString(hrJobCfgJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("HrJobCfgAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	
	public String loadJobs() {
		try {
			jobs = new HrJobCfgFacade().find("select "+HrJobCfg.SELF_FIELDS+" from HrJobCfg ",HrJobCfg.SELF_FIELDS);
			for(int i=0;i<jobs.size();i++){
				TreeJson treeJson = new TreeJson();
				treeJson.setId(jobs.get(i).getJobId());
				treeJson.setText(jobs.get(i).getJobName());
				this.addtoComJsons(treeJson);
			}
			this.setJson(JSON.toJSONString(jobsJson));
			//System.out.println(this.getJson());
		}
		catch(Exception e) { 
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("IndexAction Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String sav() throws Exception {
		try {
			//wfJob.setCreateBy(getSession().getUserId());
			//wfJob.setCreateDate(new Date());
			//wfJob.setLastUpd(getSession().getUserId());
			//wfJob.setLastUpdDate(new Date());
			if(wfJob == null) {
				wfJob = new WfJob();
			}
			wfJob.setWfJobs(wfJobs);
			wfJob.setWfJobUsers(wfJobUsers);
			new WfJobFacade().save(wfJob);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfJobAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfJob.setCreateBy(getSession().getUserId());
			//wfJob.setCreateDate(new Date());
			//wfJob.setLastUpd(getSession().getUserId());
			//wfJob.setLastUpdDate(new Date());
			new WfJobFacade().update(wfJob);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfJobAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfJobs != null && wfJobs.size() > 0) {
				for(int i=0; i<wfJobs.size();i++) {
					if(wfJobs.get(i) != null) {
						//wfJobs.get(i).setLastUpd(getSession().getUserId());
						//wfJobs.get(i).setLastUpdDate(new Date());
						new WfJobFacade().submit(wfJobs.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfJobAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfJobs != null && wfJobs.size() > 0) {
				for(int i=0; i<wfJobs.size();i++) {
					if(wfJobs.get(i) != null) {
						//wfJobs.get(i).setLastUpd(getSession().getUserId());
						//wfJobs.get(i).setLastUpdDate(new Date());
						new WfJobFacade().submit(wfJobs.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfJobAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfJobs != null && wfJobs.size() > 0) {
				for(int i=0; i<wfJobs.size();i++) {
					if(wfJobs.get(i) != null) {
						//wfJobs.get(i).setLastUpd(getSession().getUserId());
						//wfJobs.get(i).setLastUpdDate(new Date());
						new WfJobFacade().update(wfJobs.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfJobAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfJobs != null && wfJobs.size() > 0) {
				for(int i=0; i<wfJobs.size();i++) {
					if(wfJobs.get(i) != null) {
						//wfJobs.get(i).setLastUpd(getSession().getUserId());
						//wfJobs.get(i).setLastUpdDate(new Date());
						new WfJobFacade().submit(wfJobs.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfJobAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfJobs != null && wfJobs.size() > 0) {
				for(int i=0; i<wfJobs.size();i++) {
					if(wfJobs.get(i) != null) {
						//wfJobs.get(i).setLastUpd(getSession().getUserId());
						//wfJobs.get(i).setLastUpdDate(new Date());
						new WfJobFacade().submit(wfJobs.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfJobAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfJobs != null && wfJobs.size() > 0) {
				for(int i=0; i<wfJobs.size();i++) {
					if(wfJobs.get(i) != null) {
						//wfJobs.get(i).setLastUpd(getSession().getUserId());
						//wfJobs.get(i).setLastUpdDate(new Date());
						new WfJobFacade().submit(wfJobs.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfJobAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfJobs != null && wfJobs.size() > 0) {
				for(int i=0; i<wfJobs.size();i++) {
					if(wfJobs.get(i) != null) {
						//wfJobs.get(i).setLastUpd(getSession().getUserId());
						//wfJobs.get(i).setLastUpdDate(new Date());
						new WfJobFacade().submit(wfJobs.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfJobAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfJobs != null && wfJobs.size() > 0) {
				for(int i=0; i<wfJobs.size();i++) {
					if(wfJobs.get(i) != null){
						//wfJobs.get(i).setLastUpd(getSession().getUserId());
						//wfJobs.get(i).setLastUpdDate(new Date());
						new WfJobFacade().submit(wfJobs.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfJobAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfJobs != null && wfJobs.size() > 0) {
				for(int i=0; i<wfJobs.size();i++) {
					if(wfJobs.get(i) != null){
						//wfJobs.get(i).setLastUpd(getSession().getUserId());
						//wfJobs.get(i).setLastUpdDate(new Date());
						new WfJobFacade().review(wfJobs.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfJobAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfJobs != null && wfJobs.size() > 0) {
				for(int i=0; i<wfJobs.size();i++) {
					if(wfJobs.get(i) != null) {
						//wfJobs.get(i).setLastUpd(getSession().getUserId());
						//wfJobs.get(i).setLastUpdDate(new Date());
						new WfJobFacade().review(wfJobs.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfJobAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfJobs != null && wfJobs.size() > 0) {
				for(int i=0; i<wfJobs.size();i++) {
					if(wfJobs.get(i) != null) {
						//wfJobs.get(i).setLastUpd(getSession().getUserId());
						//wfJobs.get(i).setLastUpdDate(new Date());
						new WfJobFacade().confirm(wfJobs.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfJobAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfJobs != null && wfJobs.size() > 0) {
				for(int i=0; i<wfJobs.size();i++) {
					if(wfJobs.get(i) != null) {
						//wfJobs.get(i).setLastUpd(getSession().getUserId());
						//wfJobs.get(i).setLastUpdDate(new Date());
						new WfJobFacade().confirm(wfJobs.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfJobAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfJobs != null && wfJobs.size() > 0) {
					for(int i=0; i<wfJobs.size();i++) {
						if(wfJobs.get(i) != null) {
							//wfJobs.get(i).setLastUpd(getSession().getUserId());
							//wfJobs.get(i).setLastUpdDate(new Date());
							new WfJobFacade().confirm(wfJobs.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfJobAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfJob> wfJobs = new WfJobFacade().find(wfJob);
			if(wfJobs != null && wfJobs.size() > 0) {
				WritableCellFormat wcformat = new WritableCellFormat();
				wcformat.setAlignment(jxl.format.Alignment.CENTRE);
				wcformat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				wcformat.setBorder(Border.LEFT,BorderLineStyle.THIN);
				wcformat.setBorder(Border.RIGHT,BorderLineStyle.THIN);
				wcformat.setBorder(Border.TOP,BorderLineStyle.THIN);
				wcformat.setBorder(Border.BOTTOM,BorderLineStyle.THIN);
				wcformat.setWrap(true);
				OutputStream os = getOutputStream();
				workbook = Workbook.createWorkbook(os);
				WritableSheet ws = workbook.createSheet("sheet0", 0);
				int index = 0;
				
					ws.addCell(new Label(index,1,"������λID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfJobs.size();i++) {
					row++;
					int m = 0;
					if(wfJobs.get(i).getJobId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfJobs.get(i).getJobId(),wcformat));
					m++;
					if(wfJobs.get(i).getFlowId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfJobs.get(i).getFlowId(),wcformat));
					m++;
					if(wfJobs.get(i).getIsUpdOrLoad() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfJobs.get(i).getIsUpdOrLoad(),wcformat));
					m++;
					if(wfJobs.get(i).getAnnexUpdOrLoad() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfJobs.get(i).getAnnexUpdOrLoad(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfJobListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfJobs != null && wfJobs.size() > 0) {
				for(int i=0; i<wfJobs.size();i++) {
					if(wfJobs.get(i) != null) {
						//wfJobs.get(i).setLastUpd(getSession().getUserId());
						//wfJobs.get(i).setLastUpdDate(new Date());
						new WfJobFacade().confirm(wfJobs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfJobAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfJobs != null && wfJobs.size() > 0) {
				for(int i=0; i<wfJobs.size();i++) {
					if(wfJobs.get(i) != null) {
						//wfJobs.get(i).setLastUpd(getSession().getUserId());
						//wfJobs.get(i).setLastUpdDate(new Date());
						new WfJobFacade().confirm(wfJobs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfJobAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfJob> getWfJobs() {
		return wfJobs;
	}
	public void setWfJobs(List<WfJob> wfJobs) {
		this.wfJobs = wfJobs;
	}
	public WfJob getWfJob() {
		return wfJob;
	}
	public void setWfJob(WfJob wfJob) {
		this.wfJob = wfJob;
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
	public java.util.List<gnwf.vo.WfCfg> getWfCfgs() {
		return wfCfgs;
	}
	public void setWfCfgs(java.util.List<gnwf.vo.WfCfg> wfCfgs){
		this.wfCfgs = wfCfgs;
	}
	public void addtoWfCfgs(gnwf.vo.WfCfg wfCfg){
		if(getWfCfgs() == null) setWfCfgs(new java.util.ArrayList<gnwf.vo.WfCfg>());
			getWfCfgs().add(wfCfg);
	}
	public List<HrJobCfg> getHrJobCfgs() {
		return hrJobCfgs;
	}
	public void setHrJobCfgs(List<HrJobCfg> hrJobCfgs) {
		this.hrJobCfgs = hrJobCfgs;
	}
	public HrJobCfg getHrJobCfg() {
		return hrJobCfg;
	}
	public void setHrJobCfg(HrJobCfg hrJobCfg) {
		this.hrJobCfg = hrJobCfg;
	}
	public List<HrJobCfg> getJobs() {
		return jobs;
	}
	public void setJobs(List<HrJobCfg> jobs) {
		this.jobs = jobs;
	}
	public List<TreeJson> getJobsJson() {
		return jobsJson;
	}
	public void setJobsJson(List<TreeJson> jobsJson) {
		this.jobsJson = jobsJson;
	}
	public void addtoComJsons(zrsy.vo.json.TreeJson treeJson) {
		if(getJobsJson() == null) setJobsJson(new ArrayList<zrsy.vo.json.TreeJson>());
		getJobsJson().add(treeJson);
	}
	public List<WfJobUser> getWfJobUsers() {
		return wfJobUsers;
	}
	public void setWfJobUsers(List<WfJobUser> wfJobUsers) {
		this.wfJobUsers = wfJobUsers;
	}
	public List<Usr> getUsrs() {
		return usrs;
	}
	public void setUsrs(List<Usr> usrs) {
		this.usrs = usrs;
	}
	
}