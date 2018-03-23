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

import gnwf.facade.WfStepNextFacade;
import gnwf.vo.WfStepNext;
import gnwf.vo.json.WfStepNextJson;

public class WfStepNextAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfStepNext> wfStepNexts;
	private WfStepNext wfStepNext = new WfStepNext();
	private java.util.List<gnwf.vo.WfStep> wfSteps;

	public String execute() throws Exception {
		try {
			if(wfStepNext != null && wfStepNext.getStepId() != null) {
				wfStepNext = new WfStepNextFacade().findById(wfStepNext);
				setJson(JSON.toJSONString(wfStepNext)); 
			}
			wfSteps = new gnwf.facade.WfStepFacade().find("select "+gnwf.vo.WfStep.SELF_FIELDS+" from WfStep",gnwf.vo.WfStep.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfStepNextAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(wfStepNext != null && wfStepNext.getStepId() != null) {
				//wfStepNext = new WfStepNextFacade().findById(wfStepNext);
				//setJson(JSON.toJSONString(wfStepNext)); 
			//}
			wfSteps = new gnwf.facade.WfStepFacade().find("select "+gnwf.vo.WfStep.SELF_FIELDS+" from WfStep",gnwf.vo.WfStep.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfStepNextAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(wfStepNext != null && wfStepNext.getStepId() != null) {
				wfStepNext = new WfStepNextFacade().findById(wfStepNext);
				setJson(JSON.toJSONString(wfStepNext)); 
			}
			wfSteps = new gnwf.facade.WfStepFacade().find("select "+gnwf.vo.WfStep.SELF_FIELDS+" from WfStep",gnwf.vo.WfStep.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfStepNextAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(wfStepNext != null && wfStepNext.getStepId() != null) {
				wfStepNext = new WfStepNextFacade().findById(wfStepNext);
				setJson(JSON.toJSONString(wfStepNext)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfStepNextAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(wfStepNext == null) wfStepNext = new WfStepNext();
			int total = new WfStepNextFacade().amount(wfStepNext);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfStepNexts = new WfStepNextFacade().find(wfStepNext,getPageVO());
			WfStepNextJson wfStepNextJson = new WfStepNextJson();
			wfStepNextJson.Rows = wfStepNexts;
			wfStepNextJson.Total = total;
			setJson(JSON.toJSONString(wfStepNextJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfStepNextAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//wfStepNext.setCreateBy(getSession().getUserId());
			//wfStepNext.setCreateDate(new Date());
			//wfStepNext.setLastUpd(getSession().getUserId());
			//wfStepNext.setLastUpdDate(new Date());

			if(wfStepNext.getStepId() == null)
				new WfStepNextFacade().save(wfStepNext);
			else
				new WfStepNextFacade().update(wfStepNext);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfStepNextAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfStepNext.setCreateBy(getSession().getUserId());
			//wfStepNext.setCreateDate(new Date());
			//wfStepNext.setLastUpd(getSession().getUserId());
			//wfStepNext.setLastUpdDate(new Date());
			new WfStepNextFacade().update(wfStepNext);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfStepNextAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfStepNexts != null && wfStepNexts.size() > 0) {
				for(int i=0; i<wfStepNexts.size();i++) {
					if(wfStepNexts.get(i) != null) {
						//wfStepNexts.get(i).setLastUpd(getSession().getUserId());
						//wfStepNexts.get(i).setLastUpdDate(new Date());
						new WfStepNextFacade().submit(wfStepNexts.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfStepNextAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfStepNexts != null && wfStepNexts.size() > 0) {
				for(int i=0; i<wfStepNexts.size();i++) {
					if(wfStepNexts.get(i) != null) {
						//wfStepNexts.get(i).setLastUpd(getSession().getUserId());
						//wfStepNexts.get(i).setLastUpdDate(new Date());
						new WfStepNextFacade().submit(wfStepNexts.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfStepNextAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfStepNexts != null && wfStepNexts.size() > 0) {
				for(int i=0; i<wfStepNexts.size();i++) {
					if(wfStepNexts.get(i) != null) {
						//wfStepNexts.get(i).setLastUpd(getSession().getUserId());
						//wfStepNexts.get(i).setLastUpdDate(new Date());
						new WfStepNextFacade().update(wfStepNexts.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfStepNextAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfStepNexts != null && wfStepNexts.size() > 0) {
				for(int i=0; i<wfStepNexts.size();i++) {
					if(wfStepNexts.get(i) != null) {
						//wfStepNexts.get(i).setLastUpd(getSession().getUserId());
						//wfStepNexts.get(i).setLastUpdDate(new Date());
						new WfStepNextFacade().submit(wfStepNexts.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfStepNextAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfStepNexts != null && wfStepNexts.size() > 0) {
				for(int i=0; i<wfStepNexts.size();i++) {
					if(wfStepNexts.get(i) != null) {
						//wfStepNexts.get(i).setLastUpd(getSession().getUserId());
						//wfStepNexts.get(i).setLastUpdDate(new Date());
						new WfStepNextFacade().submit(wfStepNexts.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfStepNextAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfStepNexts != null && wfStepNexts.size() > 0) {
				for(int i=0; i<wfStepNexts.size();i++) {
					if(wfStepNexts.get(i) != null) {
						//wfStepNexts.get(i).setLastUpd(getSession().getUserId());
						//wfStepNexts.get(i).setLastUpdDate(new Date());
						new WfStepNextFacade().submit(wfStepNexts.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfStepNextAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfStepNexts != null && wfStepNexts.size() > 0) {
				for(int i=0; i<wfStepNexts.size();i++) {
					if(wfStepNexts.get(i) != null) {
						//wfStepNexts.get(i).setLastUpd(getSession().getUserId());
						//wfStepNexts.get(i).setLastUpdDate(new Date());
						new WfStepNextFacade().submit(wfStepNexts.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfStepNextAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfStepNexts != null && wfStepNexts.size() > 0) {
				for(int i=0; i<wfStepNexts.size();i++) {
					if(wfStepNexts.get(i) != null){
						//wfStepNexts.get(i).setLastUpd(getSession().getUserId());
						//wfStepNexts.get(i).setLastUpdDate(new Date());
						new WfStepNextFacade().submit(wfStepNexts.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfStepNextAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfStepNexts != null && wfStepNexts.size() > 0) {
				for(int i=0; i<wfStepNexts.size();i++) {
					if(wfStepNexts.get(i) != null){
						//wfStepNexts.get(i).setLastUpd(getSession().getUserId());
						//wfStepNexts.get(i).setLastUpdDate(new Date());
						new WfStepNextFacade().review(wfStepNexts.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfStepNextAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfStepNexts != null && wfStepNexts.size() > 0) {
				for(int i=0; i<wfStepNexts.size();i++) {
					if(wfStepNexts.get(i) != null) {
						//wfStepNexts.get(i).setLastUpd(getSession().getUserId());
						//wfStepNexts.get(i).setLastUpdDate(new Date());
						new WfStepNextFacade().review(wfStepNexts.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfStepNextAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfStepNexts != null && wfStepNexts.size() > 0) {
				for(int i=0; i<wfStepNexts.size();i++) {
					if(wfStepNexts.get(i) != null) {
						//wfStepNexts.get(i).setLastUpd(getSession().getUserId());
						//wfStepNexts.get(i).setLastUpdDate(new Date());
						new WfStepNextFacade().confirm(wfStepNexts.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfStepNextAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfStepNexts != null && wfStepNexts.size() > 0) {
				for(int i=0; i<wfStepNexts.size();i++) {
					if(wfStepNexts.get(i) != null) {
						//wfStepNexts.get(i).setLastUpd(getSession().getUserId());
						//wfStepNexts.get(i).setLastUpdDate(new Date());
						new WfStepNextFacade().confirm(wfStepNexts.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfStepNextAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfStepNexts != null && wfStepNexts.size() > 0) {
					for(int i=0; i<wfStepNexts.size();i++) {
						if(wfStepNexts.get(i) != null) {
							//wfStepNexts.get(i).setLastUpd(getSession().getUserId());
							//wfStepNexts.get(i).setLastUpdDate(new Date());
							new WfStepNextFacade().confirm(wfStepNexts.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfStepNextAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfStepNext> wfStepNexts = new WfStepNextFacade().find(wfStepNext);
			if(wfStepNexts != null && wfStepNexts.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"步骤ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"下一步骤ID",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfStepNexts.size();i++) {
					row++;
					int m = 0;
					if(wfStepNexts.get(i).getStepId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfStepNexts.get(i).getStepId(),wcformat));
					m++;
					if(wfStepNexts.get(i).getNextId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfStepNexts.get(i).getNextId(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfStepNextListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfStepNexts != null && wfStepNexts.size() > 0) {
				for(int i=0; i<wfStepNexts.size();i++) {
					if(wfStepNexts.get(i) != null) {
						//wfStepNexts.get(i).setLastUpd(getSession().getUserId());
						//wfStepNexts.get(i).setLastUpdDate(new Date());
						new WfStepNextFacade().confirm(wfStepNexts.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfStepNextAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfStepNexts != null && wfStepNexts.size() > 0) {
				for(int i=0; i<wfStepNexts.size();i++) {
					if(wfStepNexts.get(i) != null) {
						//wfStepNexts.get(i).setLastUpd(getSession().getUserId());
						//wfStepNexts.get(i).setLastUpdDate(new Date());
						new WfStepNextFacade().confirm(wfStepNexts.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfStepNextAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfStepNext> getWfStepNexts() {
		return wfStepNexts;
	}
	public void setWfStepNexts(List<WfStepNext> wfStepNexts) {
		this.wfStepNexts = wfStepNexts;
	}
	public WfStepNext getWfStepNext() {
		return wfStepNext;
	}
	public void setWfStepNext(WfStepNext wfStepNext) {
		this.wfStepNext = wfStepNext;
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
	public java.util.List<gnwf.vo.WfStep> getWfSteps() {
		return wfSteps;
	}
	public void setWfSteps(java.util.List<gnwf.vo.WfStep> wfSteps){
		this.wfSteps = wfSteps;
	}
	public void addtoWfSteps(gnwf.vo.WfStep wfStep){
		if(getWfSteps() == null) setWfSteps(new java.util.ArrayList<gnwf.vo.WfStep>());
			getWfSteps().add(wfStep);
	}

}