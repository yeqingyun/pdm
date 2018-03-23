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

import gnwf.facade.WfRdStepFacade;
import gnwf.vo.WfRdStep;
import gnwf.vo.json.WfRdStepJson;

public class WfRdStepAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfRdStep> wfRdSteps;
	private WfRdStep wfRdStep = new WfRdStep();

	public String execute() throws Exception {
		try {
			if(wfRdStep != null && wfRdStep.getStepId() != null) {
				wfRdStep = new WfRdStepFacade().findById(wfRdStep);
				setJson(JSON.toJSONString(wfRdStep)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdStepAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(wfRdStep != null && wfRdStep.getStepId() != null) {
				//wfRdStep = new WfRdStepFacade().findById(wfRdStep);
				//setJson(JSON.toJSONString(wfRdStep)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdStepAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(wfRdStep != null && wfRdStep.getStepId() != null) {
				wfRdStep = new WfRdStepFacade().findById(wfRdStep);
				setJson(JSON.toJSONString(wfRdStep)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdStepAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(wfRdStep != null && wfRdStep.getStepId() != null) {
				wfRdStep = new WfRdStepFacade().findById(wfRdStep);
				setJson(JSON.toJSONString(wfRdStep)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdStepAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(wfRdStep == null) wfRdStep = new WfRdStep();
			int total = new WfRdStepFacade().amount(wfRdStep);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfRdSteps = new WfRdStepFacade().find(wfRdStep,getPageVO());
			WfRdStepJson wfRdStepJson = new WfRdStepJson();
			wfRdStepJson.Rows = wfRdSteps;
			wfRdStepJson.Total = total;
			setJson(JSON.toJSONString(wfRdStepJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdStepAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//wfRdStep.setCreateBy(getSession().getUserId());
			//wfRdStep.setCreateDate(new Date());
			//wfRdStep.setLastUpd(getSession().getUserId());
			//wfRdStep.setLastUpdDate(new Date());

			if(wfRdStep.getStepId() == null)
				new WfRdStepFacade().save(wfRdStep);
			else
				new WfRdStepFacade().update(wfRdStep);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfRdStepAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfRdStep.setCreateBy(getSession().getUserId());
			//wfRdStep.setCreateDate(new Date());
			//wfRdStep.setLastUpd(getSession().getUserId());
			//wfRdStep.setLastUpdDate(new Date());
			new WfRdStepFacade().update(wfRdStep);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfRdStepAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfRdSteps != null && wfRdSteps.size() > 0) {
				for(int i=0; i<wfRdSteps.size();i++) {
					if(wfRdSteps.get(i) != null) {
						//wfRdSteps.get(i).setLastUpd(getSession().getUserId());
						//wfRdSteps.get(i).setLastUpdDate(new Date());
						new WfRdStepFacade().submit(wfRdSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfRdStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfRdSteps != null && wfRdSteps.size() > 0) {
				for(int i=0; i<wfRdSteps.size();i++) {
					if(wfRdSteps.get(i) != null) {
						//wfRdSteps.get(i).setLastUpd(getSession().getUserId());
						//wfRdSteps.get(i).setLastUpdDate(new Date());
						new WfRdStepFacade().submit(wfRdSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfRdStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfRdSteps != null && wfRdSteps.size() > 0) {
				for(int i=0; i<wfRdSteps.size();i++) {
					if(wfRdSteps.get(i) != null) {
						//wfRdSteps.get(i).setLastUpd(getSession().getUserId());
						//wfRdSteps.get(i).setLastUpdDate(new Date());
						new WfRdStepFacade().update(wfRdSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfRdStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfRdSteps != null && wfRdSteps.size() > 0) {
				for(int i=0; i<wfRdSteps.size();i++) {
					if(wfRdSteps.get(i) != null) {
						//wfRdSteps.get(i).setLastUpd(getSession().getUserId());
						//wfRdSteps.get(i).setLastUpdDate(new Date());
						new WfRdStepFacade().submit(wfRdSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfRdStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfRdSteps != null && wfRdSteps.size() > 0) {
				for(int i=0; i<wfRdSteps.size();i++) {
					if(wfRdSteps.get(i) != null) {
						//wfRdSteps.get(i).setLastUpd(getSession().getUserId());
						//wfRdSteps.get(i).setLastUpdDate(new Date());
						new WfRdStepFacade().submit(wfRdSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfRdStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfRdSteps != null && wfRdSteps.size() > 0) {
				for(int i=0; i<wfRdSteps.size();i++) {
					if(wfRdSteps.get(i) != null) {
						//wfRdSteps.get(i).setLastUpd(getSession().getUserId());
						//wfRdSteps.get(i).setLastUpdDate(new Date());
						new WfRdStepFacade().submit(wfRdSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfRdStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfRdSteps != null && wfRdSteps.size() > 0) {
				for(int i=0; i<wfRdSteps.size();i++) {
					if(wfRdSteps.get(i) != null) {
						//wfRdSteps.get(i).setLastUpd(getSession().getUserId());
						//wfRdSteps.get(i).setLastUpdDate(new Date());
						new WfRdStepFacade().submit(wfRdSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfRdStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfRdSteps != null && wfRdSteps.size() > 0) {
				for(int i=0; i<wfRdSteps.size();i++) {
					if(wfRdSteps.get(i) != null){
						//wfRdSteps.get(i).setLastUpd(getSession().getUserId());
						//wfRdSteps.get(i).setLastUpdDate(new Date());
						new WfRdStepFacade().submit(wfRdSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfRdStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfRdSteps != null && wfRdSteps.size() > 0) {
				for(int i=0; i<wfRdSteps.size();i++) {
					if(wfRdSteps.get(i) != null){
						//wfRdSteps.get(i).setLastUpd(getSession().getUserId());
						//wfRdSteps.get(i).setLastUpdDate(new Date());
						new WfRdStepFacade().review(wfRdSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfRdStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfRdSteps != null && wfRdSteps.size() > 0) {
				for(int i=0; i<wfRdSteps.size();i++) {
					if(wfRdSteps.get(i) != null) {
						//wfRdSteps.get(i).setLastUpd(getSession().getUserId());
						//wfRdSteps.get(i).setLastUpdDate(new Date());
						new WfRdStepFacade().review(wfRdSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfRdStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfRdSteps != null && wfRdSteps.size() > 0) {
				for(int i=0; i<wfRdSteps.size();i++) {
					if(wfRdSteps.get(i) != null) {
						//wfRdSteps.get(i).setLastUpd(getSession().getUserId());
						//wfRdSteps.get(i).setLastUpdDate(new Date());
						new WfRdStepFacade().confirm(wfRdSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfRdStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfRdSteps != null && wfRdSteps.size() > 0) {
				for(int i=0; i<wfRdSteps.size();i++) {
					if(wfRdSteps.get(i) != null) {
						//wfRdSteps.get(i).setLastUpd(getSession().getUserId());
						//wfRdSteps.get(i).setLastUpdDate(new Date());
						new WfRdStepFacade().confirm(wfRdSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfRdStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfRdSteps != null && wfRdSteps.size() > 0) {
					for(int i=0; i<wfRdSteps.size();i++) {
						if(wfRdSteps.get(i) != null) {
							//wfRdSteps.get(i).setLastUpd(getSession().getUserId());
							//wfRdSteps.get(i).setLastUpdDate(new Date());
							new WfRdStepFacade().confirm(wfRdSteps.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfRdStepAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfRdStep> wfRdSteps = new WfRdStepFacade().find(wfRdStep);
			if(wfRdSteps != null && wfRdSteps.size() > 0) {
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
					ws.addCell(new Label(index,1,"用户ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"接收人",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"工作流ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"创建日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"更新日期",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfRdSteps.size();i++) {
					row++;
					int m = 0;
					if(wfRdSteps.get(i).getStepId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfRdSteps.get(i).getStepId(),wcformat));
					m++;
					if(wfRdSteps.get(i).getUserId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfRdSteps.get(i).getUserId(),wcformat));
					m++;
					if(wfRdSteps.get(i).getStepUser() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfRdSteps.get(i).getStepUser(),wcformat));
					m++;
					if(wfRdSteps.get(i).getFlowId() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfRdSteps.get(i).getFlowId(),wcformat));
					m++;
					if(wfRdSteps.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfRdSteps.get(i).getCreateDate(),wcformat));
					m++;
					if(wfRdSteps.get(i).getLastUpdDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfRdSteps.get(i).getLastUpdDate(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfRdStepListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfRdSteps != null && wfRdSteps.size() > 0) {
				for(int i=0; i<wfRdSteps.size();i++) {
					if(wfRdSteps.get(i) != null) {
						//wfRdSteps.get(i).setLastUpd(getSession().getUserId());
						//wfRdSteps.get(i).setLastUpdDate(new Date());
						new WfRdStepFacade().confirm(wfRdSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfRdStepAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfRdSteps != null && wfRdSteps.size() > 0) {
				for(int i=0; i<wfRdSteps.size();i++) {
					if(wfRdSteps.get(i) != null) {
						//wfRdSteps.get(i).setLastUpd(getSession().getUserId());
						//wfRdSteps.get(i).setLastUpdDate(new Date());
						new WfRdStepFacade().confirm(wfRdSteps.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfRdStepAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfRdStep> getWfRdSteps() {
		return wfRdSteps;
	}
	public void setWfRdSteps(List<WfRdStep> wfRdSteps) {
		this.wfRdSteps = wfRdSteps;
	}
	public WfRdStep getWfRdStep() {
		return wfRdStep;
	}
	public void setWfRdStep(WfRdStep wfRdStep) {
		this.wfRdStep = wfRdStep;
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

}