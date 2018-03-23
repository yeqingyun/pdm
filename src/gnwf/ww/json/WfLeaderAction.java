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

import gnwf.facade.WfLeaderFacade;
import gnwf.vo.WfLeader;
import gnwf.vo.json.WfLeaderJson;

public class WfLeaderAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfLeader> wfLeaders;
	private WfLeader wfLeader = new WfLeader();
	private java.util.List<gnwf.vo.WfCfg> wfCfgs;

	public String execute() throws Exception {
		try {
			if(wfLeader != null && wfLeader.getFlowId() != null) {
				wfLeader = new WfLeaderFacade().findById(wfLeader);
				setJson(JSON.toJSONString(wfLeader)); 
			}
			wfCfgs = new gnwf.facade.WfCfgFacade().find("select "+gnwf.vo.WfCfg.SELF_FIELDS+" from WfCfg",gnwf.vo.WfCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfLeaderAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(wfLeader != null && wfLeader.getFlowId() != null) {
				//wfLeader = new WfLeaderFacade().findById(wfLeader);
				//setJson(JSON.toJSONString(wfLeader)); 
			//}
			wfCfgs = new gnwf.facade.WfCfgFacade().find("select "+gnwf.vo.WfCfg.SELF_FIELDS+" from WfCfg",gnwf.vo.WfCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfLeaderAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(wfLeader != null && wfLeader.getFlowId() != null) {
				wfLeader = new WfLeaderFacade().findById(wfLeader);
				setJson(JSON.toJSONString(wfLeader)); 
			}
			wfCfgs = new gnwf.facade.WfCfgFacade().find("select "+gnwf.vo.WfCfg.SELF_FIELDS+" from WfCfg",gnwf.vo.WfCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfLeaderAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(wfLeader != null && wfLeader.getFlowId() != null) {
				wfLeader = new WfLeaderFacade().findById(wfLeader);
				setJson(JSON.toJSONString(wfLeader)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfLeaderAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(wfLeader == null) wfLeader = new WfLeader();
			int total = new WfLeaderFacade().amount(wfLeader);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfLeaders = new WfLeaderFacade().find(wfLeader,getPageVO());
			WfLeaderJson wfLeaderJson = new WfLeaderJson();
			wfLeaderJson.Rows = wfLeaders;
			wfLeaderJson.Total = total;
			setJson(JSON.toJSONString(wfLeaderJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfLeaderAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//wfLeader.setCreateBy(getSession().getUserId());
			//wfLeader.setCreateDate(new Date());
			//wfLeader.setLastUpd(getSession().getUserId());
			//wfLeader.setLastUpdDate(new Date());

			if(wfLeader.getFlowId() == null)
				new WfLeaderFacade().save(wfLeader);
			else
				new WfLeaderFacade().update(wfLeader);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfLeaderAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfLeader.setCreateBy(getSession().getUserId());
			//wfLeader.setCreateDate(new Date());
			//wfLeader.setLastUpd(getSession().getUserId());
			//wfLeader.setLastUpdDate(new Date());
			new WfLeaderFacade().update(wfLeader);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfLeaderAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfLeaders != null && wfLeaders.size() > 0) {
				for(int i=0; i<wfLeaders.size();i++) {
					if(wfLeaders.get(i) != null) {
						//wfLeaders.get(i).setLastUpd(getSession().getUserId());
						//wfLeaders.get(i).setLastUpdDate(new Date());
						new WfLeaderFacade().submit(wfLeaders.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfLeaderAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfLeaders != null && wfLeaders.size() > 0) {
				for(int i=0; i<wfLeaders.size();i++) {
					if(wfLeaders.get(i) != null) {
						//wfLeaders.get(i).setLastUpd(getSession().getUserId());
						//wfLeaders.get(i).setLastUpdDate(new Date());
						new WfLeaderFacade().submit(wfLeaders.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfLeaderAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfLeaders != null && wfLeaders.size() > 0) {
				for(int i=0; i<wfLeaders.size();i++) {
					if(wfLeaders.get(i) != null) {
						//wfLeaders.get(i).setLastUpd(getSession().getUserId());
						//wfLeaders.get(i).setLastUpdDate(new Date());
						new WfLeaderFacade().update(wfLeaders.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfLeaderAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfLeaders != null && wfLeaders.size() > 0) {
				for(int i=0; i<wfLeaders.size();i++) {
					if(wfLeaders.get(i) != null) {
						//wfLeaders.get(i).setLastUpd(getSession().getUserId());
						//wfLeaders.get(i).setLastUpdDate(new Date());
						new WfLeaderFacade().submit(wfLeaders.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfLeaderAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfLeaders != null && wfLeaders.size() > 0) {
				for(int i=0; i<wfLeaders.size();i++) {
					if(wfLeaders.get(i) != null) {
						//wfLeaders.get(i).setLastUpd(getSession().getUserId());
						//wfLeaders.get(i).setLastUpdDate(new Date());
						new WfLeaderFacade().submit(wfLeaders.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfLeaderAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfLeaders != null && wfLeaders.size() > 0) {
				for(int i=0; i<wfLeaders.size();i++) {
					if(wfLeaders.get(i) != null) {
						//wfLeaders.get(i).setLastUpd(getSession().getUserId());
						//wfLeaders.get(i).setLastUpdDate(new Date());
						new WfLeaderFacade().submit(wfLeaders.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfLeaderAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfLeaders != null && wfLeaders.size() > 0) {
				for(int i=0; i<wfLeaders.size();i++) {
					if(wfLeaders.get(i) != null) {
						//wfLeaders.get(i).setLastUpd(getSession().getUserId());
						//wfLeaders.get(i).setLastUpdDate(new Date());
						new WfLeaderFacade().submit(wfLeaders.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfLeaderAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfLeaders != null && wfLeaders.size() > 0) {
				for(int i=0; i<wfLeaders.size();i++) {
					if(wfLeaders.get(i) != null){
						//wfLeaders.get(i).setLastUpd(getSession().getUserId());
						//wfLeaders.get(i).setLastUpdDate(new Date());
						new WfLeaderFacade().submit(wfLeaders.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfLeaderAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfLeaders != null && wfLeaders.size() > 0) {
				for(int i=0; i<wfLeaders.size();i++) {
					if(wfLeaders.get(i) != null){
						//wfLeaders.get(i).setLastUpd(getSession().getUserId());
						//wfLeaders.get(i).setLastUpdDate(new Date());
						new WfLeaderFacade().review(wfLeaders.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfLeaderAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfLeaders != null && wfLeaders.size() > 0) {
				for(int i=0; i<wfLeaders.size();i++) {
					if(wfLeaders.get(i) != null) {
						//wfLeaders.get(i).setLastUpd(getSession().getUserId());
						//wfLeaders.get(i).setLastUpdDate(new Date());
						new WfLeaderFacade().review(wfLeaders.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfLeaderAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfLeaders != null && wfLeaders.size() > 0) {
				for(int i=0; i<wfLeaders.size();i++) {
					if(wfLeaders.get(i) != null) {
						//wfLeaders.get(i).setLastUpd(getSession().getUserId());
						//wfLeaders.get(i).setLastUpdDate(new Date());
						new WfLeaderFacade().confirm(wfLeaders.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfLeaderAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfLeaders != null && wfLeaders.size() > 0) {
				for(int i=0; i<wfLeaders.size();i++) {
					if(wfLeaders.get(i) != null) {
						//wfLeaders.get(i).setLastUpd(getSession().getUserId());
						//wfLeaders.get(i).setLastUpdDate(new Date());
						new WfLeaderFacade().confirm(wfLeaders.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfLeaderAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfLeaders != null && wfLeaders.size() > 0) {
					for(int i=0; i<wfLeaders.size();i++) {
						if(wfLeaders.get(i) != null) {
							//wfLeaders.get(i).setLastUpd(getSession().getUserId());
							//wfLeaders.get(i).setLastUpdDate(new Date());
							new WfLeaderFacade().confirm(wfLeaders.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfLeaderAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfLeader> wfLeaders = new WfLeaderFacade().find(wfLeader);
			if(wfLeaders != null && wfLeaders.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"工作流定义ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"主导人ID",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfLeaders.size();i++) {
					row++;
					int m = 0;
					if(wfLeaders.get(i).getFlowId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfLeaders.get(i).getFlowId(),wcformat));
					m++;
					if(wfLeaders.get(i).getUserId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfLeaders.get(i).getUserId(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfLeaderListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfLeaders != null && wfLeaders.size() > 0) {
				for(int i=0; i<wfLeaders.size();i++) {
					if(wfLeaders.get(i) != null) {
						//wfLeaders.get(i).setLastUpd(getSession().getUserId());
						//wfLeaders.get(i).setLastUpdDate(new Date());
						new WfLeaderFacade().confirm(wfLeaders.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfLeaderAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfLeaders != null && wfLeaders.size() > 0) {
				for(int i=0; i<wfLeaders.size();i++) {
					if(wfLeaders.get(i) != null) {
						//wfLeaders.get(i).setLastUpd(getSession().getUserId());
						//wfLeaders.get(i).setLastUpdDate(new Date());
						new WfLeaderFacade().confirm(wfLeaders.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfLeaderAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfLeader> getWfLeaders() {
		return wfLeaders;
	}
	public void setWfLeaders(List<WfLeader> wfLeaders) {
		this.wfLeaders = wfLeaders;
	}
	public WfLeader getWfLeader() {
		return wfLeader;
	}
	public void setWfLeader(WfLeader wfLeader) {
		this.wfLeader = wfLeader;
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