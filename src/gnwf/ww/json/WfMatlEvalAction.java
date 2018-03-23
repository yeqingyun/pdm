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

import gnwf.facade.WfMatlEvalFacade;
import gnwf.vo.WfMatlEval;
import gnwf.vo.json.WfMatlEvalJson;

public class WfMatlEvalAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfMatlEval> wfMatlEvals;
	private WfMatlEval wfMatlEval = new WfMatlEval();
	private java.util.List<gnwf.vo.WfMatl> wfMatls;

	public String execute() throws Exception {
		try {
			if(wfMatlEval != null && wfMatlEval.getMatlId() != null) {
				wfMatlEval = new WfMatlEvalFacade().findById(wfMatlEval);
				setJson(JSON.toJSONString(wfMatlEval)); 
			}
			wfMatls = new gnwf.facade.WfMatlFacade().find("select "+gnwf.vo.WfMatl.SELF_FIELDS+" from WfMatl",gnwf.vo.WfMatl.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfMatlEvalAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(wfMatlEval != null && wfMatlEval.getMatlId() != null) {
				//wfMatlEval = new WfMatlEvalFacade().findById(wfMatlEval);
				//setJson(JSON.toJSONString(wfMatlEval)); 
			//}
			wfMatls = new gnwf.facade.WfMatlFacade().find("select "+gnwf.vo.WfMatl.SELF_FIELDS+" from WfMatl",gnwf.vo.WfMatl.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfMatlEvalAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(wfMatlEval != null && wfMatlEval.getMatlId() != null) {
				wfMatlEval = new WfMatlEvalFacade().findById(wfMatlEval);
				setJson(JSON.toJSONString(wfMatlEval)); 
			}
			wfMatls = new gnwf.facade.WfMatlFacade().find("select "+gnwf.vo.WfMatl.SELF_FIELDS+" from WfMatl",gnwf.vo.WfMatl.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfMatlEvalAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(wfMatlEval != null && wfMatlEval.getMatlId() != null) {
				wfMatlEval = new WfMatlEvalFacade().findById(wfMatlEval);
				setJson(JSON.toJSONString(wfMatlEval)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfMatlEvalAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(wfMatlEval == null) wfMatlEval = new WfMatlEval();
			int total = new WfMatlEvalFacade().amount(wfMatlEval);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfMatlEvals = new WfMatlEvalFacade().find(wfMatlEval,getPageVO());
			WfMatlEvalJson wfMatlEvalJson = new WfMatlEvalJson();
			wfMatlEvalJson.Rows = wfMatlEvals;
			wfMatlEvalJson.Total = total;
			setJson(JSON.toJSONString(wfMatlEvalJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfMatlEvalAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//wfMatlEval.setCreateBy(getSession().getUserId());
			//wfMatlEval.setCreateDate(new Date());
			//wfMatlEval.setLastUpd(getSession().getUserId());
			//wfMatlEval.setLastUpdDate(new Date());

			if(wfMatlEval.getMatlId() == null)
				new WfMatlEvalFacade().save(wfMatlEval);
			else
				new WfMatlEvalFacade().update(wfMatlEval);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfMatlEvalAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfMatlEval.setCreateBy(getSession().getUserId());
			//wfMatlEval.setCreateDate(new Date());
			//wfMatlEval.setLastUpd(getSession().getUserId());
			//wfMatlEval.setLastUpdDate(new Date());
			new WfMatlEvalFacade().update(wfMatlEval);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfMatlEvalAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfMatlEvals != null && wfMatlEvals.size() > 0) {
				for(int i=0; i<wfMatlEvals.size();i++) {
					if(wfMatlEvals.get(i) != null) {
						//wfMatlEvals.get(i).setLastUpd(getSession().getUserId());
						//wfMatlEvals.get(i).setLastUpdDate(new Date());
						new WfMatlEvalFacade().submit(wfMatlEvals.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfMatlEvalAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfMatlEvals != null && wfMatlEvals.size() > 0) {
				for(int i=0; i<wfMatlEvals.size();i++) {
					if(wfMatlEvals.get(i) != null) {
						//wfMatlEvals.get(i).setLastUpd(getSession().getUserId());
						//wfMatlEvals.get(i).setLastUpdDate(new Date());
						new WfMatlEvalFacade().submit(wfMatlEvals.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfMatlEvalAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfMatlEvals != null && wfMatlEvals.size() > 0) {
				for(int i=0; i<wfMatlEvals.size();i++) {
					if(wfMatlEvals.get(i) != null) {
						//wfMatlEvals.get(i).setLastUpd(getSession().getUserId());
						//wfMatlEvals.get(i).setLastUpdDate(new Date());
						new WfMatlEvalFacade().update(wfMatlEvals.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfMatlEvalAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfMatlEvals != null && wfMatlEvals.size() > 0) {
				for(int i=0; i<wfMatlEvals.size();i++) {
					if(wfMatlEvals.get(i) != null) {
						//wfMatlEvals.get(i).setLastUpd(getSession().getUserId());
						//wfMatlEvals.get(i).setLastUpdDate(new Date());
						new WfMatlEvalFacade().submit(wfMatlEvals.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfMatlEvalAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfMatlEvals != null && wfMatlEvals.size() > 0) {
				for(int i=0; i<wfMatlEvals.size();i++) {
					if(wfMatlEvals.get(i) != null) {
						//wfMatlEvals.get(i).setLastUpd(getSession().getUserId());
						//wfMatlEvals.get(i).setLastUpdDate(new Date());
						new WfMatlEvalFacade().submit(wfMatlEvals.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfMatlEvalAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfMatlEvals != null && wfMatlEvals.size() > 0) {
				for(int i=0; i<wfMatlEvals.size();i++) {
					if(wfMatlEvals.get(i) != null) {
						//wfMatlEvals.get(i).setLastUpd(getSession().getUserId());
						//wfMatlEvals.get(i).setLastUpdDate(new Date());
						new WfMatlEvalFacade().submit(wfMatlEvals.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfMatlEvalAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfMatlEvals != null && wfMatlEvals.size() > 0) {
				for(int i=0; i<wfMatlEvals.size();i++) {
					if(wfMatlEvals.get(i) != null) {
						//wfMatlEvals.get(i).setLastUpd(getSession().getUserId());
						//wfMatlEvals.get(i).setLastUpdDate(new Date());
						new WfMatlEvalFacade().submit(wfMatlEvals.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfMatlEvalAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfMatlEvals != null && wfMatlEvals.size() > 0) {
				for(int i=0; i<wfMatlEvals.size();i++) {
					if(wfMatlEvals.get(i) != null){
						//wfMatlEvals.get(i).setLastUpd(getSession().getUserId());
						//wfMatlEvals.get(i).setLastUpdDate(new Date());
						new WfMatlEvalFacade().submit(wfMatlEvals.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfMatlEvalAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfMatlEvals != null && wfMatlEvals.size() > 0) {
				for(int i=0; i<wfMatlEvals.size();i++) {
					if(wfMatlEvals.get(i) != null){
						//wfMatlEvals.get(i).setLastUpd(getSession().getUserId());
						//wfMatlEvals.get(i).setLastUpdDate(new Date());
						new WfMatlEvalFacade().review(wfMatlEvals.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfMatlEvalAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfMatlEvals != null && wfMatlEvals.size() > 0) {
				for(int i=0; i<wfMatlEvals.size();i++) {
					if(wfMatlEvals.get(i) != null) {
						//wfMatlEvals.get(i).setLastUpd(getSession().getUserId());
						//wfMatlEvals.get(i).setLastUpdDate(new Date());
						new WfMatlEvalFacade().review(wfMatlEvals.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfMatlEvalAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfMatlEvals != null && wfMatlEvals.size() > 0) {
				for(int i=0; i<wfMatlEvals.size();i++) {
					if(wfMatlEvals.get(i) != null) {
						//wfMatlEvals.get(i).setLastUpd(getSession().getUserId());
						//wfMatlEvals.get(i).setLastUpdDate(new Date());
						new WfMatlEvalFacade().confirm(wfMatlEvals.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfMatlEvalAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfMatlEvals != null && wfMatlEvals.size() > 0) {
				for(int i=0; i<wfMatlEvals.size();i++) {
					if(wfMatlEvals.get(i) != null) {
						//wfMatlEvals.get(i).setLastUpd(getSession().getUserId());
						//wfMatlEvals.get(i).setLastUpdDate(new Date());
						new WfMatlEvalFacade().confirm(wfMatlEvals.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfMatlEvalAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfMatlEvals != null && wfMatlEvals.size() > 0) {
					for(int i=0; i<wfMatlEvals.size();i++) {
						if(wfMatlEvals.get(i) != null) {
							//wfMatlEvals.get(i).setLastUpd(getSession().getUserId());
							//wfMatlEvals.get(i).setLastUpdDate(new Date());
							new WfMatlEvalFacade().confirm(wfMatlEvals.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfMatlEvalAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfMatlEval> wfMatlEvals = new WfMatlEvalFacade().find(wfMatlEval);
			if(wfMatlEvals != null && wfMatlEvals.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"物料ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"用户ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"是否通过",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"1代表采购，2代表材料",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"工作流编号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"评审日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"评审意见",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfMatlEvals.size();i++) {
					row++;
					int m = 0;
					if(wfMatlEvals.get(i).getMatlId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfMatlEvals.get(i).getMatlId(),wcformat));
					m++;
					if(wfMatlEvals.get(i).getUserId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfMatlEvals.get(i).getUserId(),wcformat));
					m++;
					if(wfMatlEvals.get(i).getIsPass() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfMatlEvals.get(i).getIsPass(),wcformat));
					m++;
					if(wfMatlEvals.get(i).getEvaler() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfMatlEvals.get(i).getEvaler(),wcformat));
					m++;
					if(wfMatlEvals.get(i).getstatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfMatlEvals.get(i).getstatus(),wcformat));
					m++;
					if(wfMatlEvals.get(i).getEvalId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfMatlEvals.get(i).getEvalId(),wcformat));
					m++;
					if(wfMatlEvals.get(i).getWfNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlEvals.get(i).getWfNo(),wcformat));
					m++;
					if(wfMatlEvals.get(i).getEvalDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfMatlEvals.get(i).getEvalDate(),wcformat));
					m++;
					if(wfMatlEvals.get(i).getEvalText() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlEvals.get(i).getEvalText(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfMatlEvalListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfMatlEvals != null && wfMatlEvals.size() > 0) {
				for(int i=0; i<wfMatlEvals.size();i++) {
					if(wfMatlEvals.get(i) != null) {
						//wfMatlEvals.get(i).setLastUpd(getSession().getUserId());
						//wfMatlEvals.get(i).setLastUpdDate(new Date());
						new WfMatlEvalFacade().confirm(wfMatlEvals.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfMatlEvalAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfMatlEvals != null && wfMatlEvals.size() > 0) {
				for(int i=0; i<wfMatlEvals.size();i++) {
					if(wfMatlEvals.get(i) != null) {
						//wfMatlEvals.get(i).setLastUpd(getSession().getUserId());
						//wfMatlEvals.get(i).setLastUpdDate(new Date());
						new WfMatlEvalFacade().confirm(wfMatlEvals.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfMatlEvalAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfMatlEval> getWfMatlEvals() {
		return wfMatlEvals;
	}
	public void setWfMatlEvals(List<WfMatlEval> wfMatlEvals) {
		this.wfMatlEvals = wfMatlEvals;
	}
	public WfMatlEval getWfMatlEval() {
		return wfMatlEval;
	}
	public void setWfMatlEval(WfMatlEval wfMatlEval) {
		this.wfMatlEval = wfMatlEval;
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
	public java.util.List<gnwf.vo.WfMatl> getWfMatls() {
		return wfMatls;
	}
	public void setWfMatls(java.util.List<gnwf.vo.WfMatl> wfMatls){
		this.wfMatls = wfMatls;
	}
	public void addtoWfMatls(gnwf.vo.WfMatl wfMatl){
		if(getWfMatls() == null) setWfMatls(new java.util.ArrayList<gnwf.vo.WfMatl>());
			getWfMatls().add(wfMatl);
	}

}