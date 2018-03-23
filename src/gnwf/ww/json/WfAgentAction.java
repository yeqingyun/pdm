package gnwf.ww.json;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import jxl.Workbook;
import jxl.write.WritableWorkbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import gnwf.ww.MSG;
import gnwf.ww.BasicAction;

import gnwf.facade.WfAgentFacade;
import gnwf.vo.WfAgent;
import gnwf.vo.json.WfAgentJson;

public class WfAgentAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfAgent> wfAgents;
	private WfAgent wfAgent = new WfAgent();
	private java.util.List<gnwf.vo.WfCfg> wfCfgs;

	public String execute() throws Exception {
		try {
			if(wfAgent != null && wfAgent.getUserId() != null) {
				wfAgent = new WfAgentFacade().findById(wfAgent);
				setJson(JSON.toJSONString(wfAgent)); 
			}
			wfCfgs = new gnwf.facade.WfCfgFacade().find("select "+gnwf.vo.WfCfg.SELF_FIELDS+" from WfCfg",gnwf.vo.WfCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfAgentAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(wfAgent != null && wfAgent.getUserId() != null) {
				//wfAgent = new WfAgentFacade().findById(wfAgent);
				//setJson(JSON.toJSONString(wfAgent)); 
			//}
			wfCfgs = new gnwf.facade.WfCfgFacade().find("select "+gnwf.vo.WfCfg.SELF_FIELDS+" from WfCfg",gnwf.vo.WfCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfAgentAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(wfAgent != null && wfAgent.getUserId() != null) {
				wfAgent = new WfAgentFacade().findById(wfAgent);
				setJson(JSON.toJSONString(wfAgent)); 
			}
			wfCfgs = new gnwf.facade.WfCfgFacade().find("select "+gnwf.vo.WfCfg.SELF_FIELDS+" from WfCfg",gnwf.vo.WfCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfAgentAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(wfAgent != null && wfAgent.getUserId() != null) {
				wfAgent = new WfAgentFacade().findById(wfAgent);
				setJson(JSON.toJSONString(wfAgent)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfAgentAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(wfAgent == null) wfAgent = new WfAgent();
			int total = new WfAgentFacade().amount(wfAgent);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfAgents = new WfAgentFacade().find(wfAgent,getPageVO());
			WfAgentJson wfAgentJson = new WfAgentJson();
			wfAgentJson.Rows = wfAgents;
			wfAgentJson.Total = total;
			setJson(JSON.toJSONString(wfAgentJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfAgentAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//wfAgent.setCreateBy(getSession().getUserId());
			//wfAgent.setCreateDate(new Date());
			//wfAgent.setLastUpd(getSession().getUserId());
			//wfAgent.setLastUpdDate(new Date());

			if(wfAgent.getUserId() == null)
				new WfAgentFacade().save(wfAgent);
			else
				new WfAgentFacade().update(wfAgent);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfAgentAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfAgent.setCreateBy(getSession().getUserId());
			//wfAgent.setCreateDate(new Date());
			//wfAgent.setLastUpd(getSession().getUserId());
			//wfAgent.setLastUpdDate(new Date());
			new WfAgentFacade().update(wfAgent);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfAgentAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfAgents != null && wfAgents.size() > 0) {
				for(int i=0; i<wfAgents.size();i++) {
					if(wfAgents.get(i) != null) {
						//wfAgents.get(i).setLastUpd(getSession().getUserId());
						//wfAgents.get(i).setLastUpdDate(new Date());
						new WfAgentFacade().submit(wfAgents.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfAgentAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfAgents != null && wfAgents.size() > 0) {
				for(int i=0; i<wfAgents.size();i++) {
					if(wfAgents.get(i) != null) {
						//wfAgents.get(i).setLastUpd(getSession().getUserId());
						//wfAgents.get(i).setLastUpdDate(new Date());
						new WfAgentFacade().submit(wfAgents.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfAgentAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfAgents != null && wfAgents.size() > 0) {
				for(int i=0; i<wfAgents.size();i++) {
					if(wfAgents.get(i) != null) {
						//wfAgents.get(i).setLastUpd(getSession().getUserId());
						//wfAgents.get(i).setLastUpdDate(new Date());
						new WfAgentFacade().update(wfAgents.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfAgentAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfAgents != null && wfAgents.size() > 0) {
				for(int i=0; i<wfAgents.size();i++) {
					if(wfAgents.get(i) != null) {
						//wfAgents.get(i).setLastUpd(getSession().getUserId());
						//wfAgents.get(i).setLastUpdDate(new Date());
						new WfAgentFacade().submit(wfAgents.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfAgentAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfAgents != null && wfAgents.size() > 0) {
				for(int i=0; i<wfAgents.size();i++) {
					if(wfAgents.get(i) != null) {
						//wfAgents.get(i).setLastUpd(getSession().getUserId());
						//wfAgents.get(i).setLastUpdDate(new Date());
						new WfAgentFacade().submit(wfAgents.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfAgentAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfAgents != null && wfAgents.size() > 0) {
				for(int i=0; i<wfAgents.size();i++) {
					if(wfAgents.get(i) != null) {
						//wfAgents.get(i).setLastUpd(getSession().getUserId());
						//wfAgents.get(i).setLastUpdDate(new Date());
						new WfAgentFacade().submit(wfAgents.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfAgentAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfAgents != null && wfAgents.size() > 0) {
				for(int i=0; i<wfAgents.size();i++) {
					if(wfAgents.get(i) != null) {
						//wfAgents.get(i).setLastUpd(getSession().getUserId());
						//wfAgents.get(i).setLastUpdDate(new Date());
						new WfAgentFacade().submit(wfAgents.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfAgentAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfAgents != null && wfAgents.size() > 0) {
				for(int i=0; i<wfAgents.size();i++) {
					if(wfAgents.get(i) != null){
						//wfAgents.get(i).setLastUpd(getSession().getUserId());
						//wfAgents.get(i).setLastUpdDate(new Date());
						new WfAgentFacade().submit(wfAgents.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfAgentAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfAgents != null && wfAgents.size() > 0) {
				for(int i=0; i<wfAgents.size();i++) {
					if(wfAgents.get(i) != null){
						//wfAgents.get(i).setLastUpd(getSession().getUserId());
						//wfAgents.get(i).setLastUpdDate(new Date());
						new WfAgentFacade().review(wfAgents.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfAgentAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfAgents != null && wfAgents.size() > 0) {
				for(int i=0; i<wfAgents.size();i++) {
					if(wfAgents.get(i) != null) {
						//wfAgents.get(i).setLastUpd(getSession().getUserId());
						//wfAgents.get(i).setLastUpdDate(new Date());
						new WfAgentFacade().review(wfAgents.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfAgentAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfAgents != null && wfAgents.size() > 0) {
				for(int i=0; i<wfAgents.size();i++) {
					if(wfAgents.get(i) != null) {
						//wfAgents.get(i).setLastUpd(getSession().getUserId());
						//wfAgents.get(i).setLastUpdDate(new Date());
						new WfAgentFacade().confirm(wfAgents.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfAgentAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfAgents != null && wfAgents.size() > 0) {
				for(int i=0; i<wfAgents.size();i++) {
					if(wfAgents.get(i) != null) {
						//wfAgents.get(i).setLastUpd(getSession().getUserId());
						//wfAgents.get(i).setLastUpdDate(new Date());
						new WfAgentFacade().confirm(wfAgents.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfAgentAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfAgents != null && wfAgents.size() > 0) {
					for(int i=0; i<wfAgents.size();i++) {
						if(wfAgents.get(i) != null) {
							//wfAgents.get(i).setLastUpd(getSession().getUserId());
							//wfAgents.get(i).setLastUpdDate(new Date());
							new WfAgentFacade().confirm(wfAgents.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfAgentAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfAgent> wfAgents = new WfAgentFacade().find(wfAgent);
			if(wfAgents != null && wfAgents.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"用户ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"代理人ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"工作流ID",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfAgents.size();i++) {
					row++;
					int m = 0;
					if(wfAgents.get(i).getUserId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfAgents.get(i).getUserId(),wcformat));
					m++;
					if(wfAgents.get(i).getAgentId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfAgents.get(i).getAgentId(),wcformat));
					m++;
					if(wfAgents.get(i).getFlowId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfAgents.get(i).getFlowId(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfAgentListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfAgents != null && wfAgents.size() > 0) {
				for(int i=0; i<wfAgents.size();i++) {
					if(wfAgents.get(i) != null) {
						//wfAgents.get(i).setLastUpd(getSession().getUserId());
						//wfAgents.get(i).setLastUpdDate(new Date());
						new WfAgentFacade().confirm(wfAgents.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfAgentAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfAgents != null && wfAgents.size() > 0) {
				for(int i=0; i<wfAgents.size();i++) {
					if(wfAgents.get(i) != null) {
						//wfAgents.get(i).setLastUpd(getSession().getUserId());
						//wfAgents.get(i).setLastUpdDate(new Date());
						new WfAgentFacade().confirm(wfAgents.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfAgentAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfAgent> getWfAgents() {
		return wfAgents;
	}
	public void setWfAgents(List<WfAgent> wfAgents) {
		this.wfAgents = wfAgents;
	}
	public WfAgent getWfAgent() {
		return wfAgent;
	}
	public void setWfAgent(WfAgent wfAgent) {
		this.wfAgent = wfAgent;
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

}