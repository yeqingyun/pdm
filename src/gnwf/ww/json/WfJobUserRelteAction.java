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

import gnwf.facade.WfJobUserRelteFacade;
import gnwf.vo.WfJobUserRelte;
import gnwf.vo.json.WfJobUserRelteJson;

public class WfJobUserRelteAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfJobUserRelte> wfJobUserReltes;
	private WfJobUserRelte wfJobUserRelte = new WfJobUserRelte();

	public String execute() throws Exception {
		try {
			if(wfJobUserRelte != null && wfJobUserRelte.getJobId() != null) {
				wfJobUserRelte = new WfJobUserRelteFacade().findById(wfJobUserRelte);
				setJson(JSON.toJSONString(wfJobUserRelte)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfJobUserRelteAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(wfJobUserRelte != null && wfJobUserRelte.getJobId() != null) {
				//wfJobUserRelte = new WfJobUserRelteFacade().findById(wfJobUserRelte);
				//setJson(JSON.toJSONString(wfJobUserRelte)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfJobUserRelteAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(wfJobUserRelte != null && wfJobUserRelte.getJobId() != null) {
				wfJobUserRelte = new WfJobUserRelteFacade().findById(wfJobUserRelte);
				setJson(JSON.toJSONString(wfJobUserRelte)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfJobUserRelteAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(wfJobUserRelte != null && wfJobUserRelte.getJobId() != null) {
				wfJobUserRelte = new WfJobUserRelteFacade().findById(wfJobUserRelte);
				setJson(JSON.toJSONString(wfJobUserRelte)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfJobUserRelteAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(wfJobUserRelte == null) wfJobUserRelte = new WfJobUserRelte();
			int total = new WfJobUserRelteFacade().amount(wfJobUserRelte);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfJobUserReltes = new WfJobUserRelteFacade().find(wfJobUserRelte,getPageVO());
			WfJobUserRelteJson wfJobUserRelteJson = new WfJobUserRelteJson();
			wfJobUserRelteJson.Rows = wfJobUserReltes;
			wfJobUserRelteJson.Total = total;
			setJson(JSON.toJSONString(wfJobUserRelteJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfJobUserRelteAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//wfJobUserRelte.setCreateBy(getSession().getUserId());
			//wfJobUserRelte.setCreateDate(new Date());
			//wfJobUserRelte.setLastUpd(getSession().getUserId());
			//wfJobUserRelte.setLastUpdDate(new Date());

			if(wfJobUserRelte.getJobId() == null)
				new WfJobUserRelteFacade().save(wfJobUserRelte);
			else
				new WfJobUserRelteFacade().update(wfJobUserRelte);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfJobUserRelteAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfJobUserRelte.setCreateBy(getSession().getUserId());
			//wfJobUserRelte.setCreateDate(new Date());
			//wfJobUserRelte.setLastUpd(getSession().getUserId());
			//wfJobUserRelte.setLastUpdDate(new Date());
			new WfJobUserRelteFacade().update(wfJobUserRelte);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfJobUserRelteAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfJobUserReltes != null && wfJobUserReltes.size() > 0) {
				for(int i=0; i<wfJobUserReltes.size();i++) {
					if(wfJobUserReltes.get(i) != null) {
						//wfJobUserReltes.get(i).setLastUpd(getSession().getUserId());
						//wfJobUserReltes.get(i).setLastUpdDate(new Date());
						new WfJobUserRelteFacade().submit(wfJobUserReltes.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfJobUserRelteAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfJobUserReltes != null && wfJobUserReltes.size() > 0) {
				for(int i=0; i<wfJobUserReltes.size();i++) {
					if(wfJobUserReltes.get(i) != null) {
						//wfJobUserReltes.get(i).setLastUpd(getSession().getUserId());
						//wfJobUserReltes.get(i).setLastUpdDate(new Date());
						new WfJobUserRelteFacade().submit(wfJobUserReltes.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfJobUserRelteAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfJobUserReltes != null && wfJobUserReltes.size() > 0) {
				for(int i=0; i<wfJobUserReltes.size();i++) {
					if(wfJobUserReltes.get(i) != null) {
						//wfJobUserReltes.get(i).setLastUpd(getSession().getUserId());
						//wfJobUserReltes.get(i).setLastUpdDate(new Date());
						new WfJobUserRelteFacade().update(wfJobUserReltes.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfJobUserRelteAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfJobUserReltes != null && wfJobUserReltes.size() > 0) {
				for(int i=0; i<wfJobUserReltes.size();i++) {
					if(wfJobUserReltes.get(i) != null) {
						//wfJobUserReltes.get(i).setLastUpd(getSession().getUserId());
						//wfJobUserReltes.get(i).setLastUpdDate(new Date());
						new WfJobUserRelteFacade().submit(wfJobUserReltes.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfJobUserRelteAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfJobUserReltes != null && wfJobUserReltes.size() > 0) {
				for(int i=0; i<wfJobUserReltes.size();i++) {
					if(wfJobUserReltes.get(i) != null) {
						//wfJobUserReltes.get(i).setLastUpd(getSession().getUserId());
						//wfJobUserReltes.get(i).setLastUpdDate(new Date());
						new WfJobUserRelteFacade().submit(wfJobUserReltes.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfJobUserRelteAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfJobUserReltes != null && wfJobUserReltes.size() > 0) {
				for(int i=0; i<wfJobUserReltes.size();i++) {
					if(wfJobUserReltes.get(i) != null) {
						//wfJobUserReltes.get(i).setLastUpd(getSession().getUserId());
						//wfJobUserReltes.get(i).setLastUpdDate(new Date());
						new WfJobUserRelteFacade().submit(wfJobUserReltes.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfJobUserRelteAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfJobUserReltes != null && wfJobUserReltes.size() > 0) {
				for(int i=0; i<wfJobUserReltes.size();i++) {
					if(wfJobUserReltes.get(i) != null) {
						//wfJobUserReltes.get(i).setLastUpd(getSession().getUserId());
						//wfJobUserReltes.get(i).setLastUpdDate(new Date());
						new WfJobUserRelteFacade().submit(wfJobUserReltes.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfJobUserRelteAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfJobUserReltes != null && wfJobUserReltes.size() > 0) {
				for(int i=0; i<wfJobUserReltes.size();i++) {
					if(wfJobUserReltes.get(i) != null){
						//wfJobUserReltes.get(i).setLastUpd(getSession().getUserId());
						//wfJobUserReltes.get(i).setLastUpdDate(new Date());
						new WfJobUserRelteFacade().submit(wfJobUserReltes.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfJobUserRelteAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfJobUserReltes != null && wfJobUserReltes.size() > 0) {
				for(int i=0; i<wfJobUserReltes.size();i++) {
					if(wfJobUserReltes.get(i) != null){
						//wfJobUserReltes.get(i).setLastUpd(getSession().getUserId());
						//wfJobUserReltes.get(i).setLastUpdDate(new Date());
						new WfJobUserRelteFacade().review(wfJobUserReltes.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfJobUserRelteAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfJobUserReltes != null && wfJobUserReltes.size() > 0) {
				for(int i=0; i<wfJobUserReltes.size();i++) {
					if(wfJobUserReltes.get(i) != null) {
						//wfJobUserReltes.get(i).setLastUpd(getSession().getUserId());
						//wfJobUserReltes.get(i).setLastUpdDate(new Date());
						new WfJobUserRelteFacade().review(wfJobUserReltes.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfJobUserRelteAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfJobUserReltes != null && wfJobUserReltes.size() > 0) {
				for(int i=0; i<wfJobUserReltes.size();i++) {
					if(wfJobUserReltes.get(i) != null) {
						//wfJobUserReltes.get(i).setLastUpd(getSession().getUserId());
						//wfJobUserReltes.get(i).setLastUpdDate(new Date());
						new WfJobUserRelteFacade().confirm(wfJobUserReltes.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfJobUserRelteAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfJobUserReltes != null && wfJobUserReltes.size() > 0) {
				for(int i=0; i<wfJobUserReltes.size();i++) {
					if(wfJobUserReltes.get(i) != null) {
						//wfJobUserReltes.get(i).setLastUpd(getSession().getUserId());
						//wfJobUserReltes.get(i).setLastUpdDate(new Date());
						new WfJobUserRelteFacade().confirm(wfJobUserReltes.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfJobUserRelteAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfJobUserReltes != null && wfJobUserReltes.size() > 0) {
					for(int i=0; i<wfJobUserReltes.size();i++) {
						if(wfJobUserReltes.get(i) != null) {
							//wfJobUserReltes.get(i).setLastUpd(getSession().getUserId());
							//wfJobUserReltes.get(i).setLastUpdDate(new Date());
							new WfJobUserRelteFacade().confirm(wfJobUserReltes.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfJobUserRelteAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfJobUserRelte> wfJobUserReltes = new WfJobUserRelteFacade().find(wfJobUserRelte);
			if(wfJobUserReltes != null && wfJobUserReltes.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfJobUserReltes.size();i++) {
					row++;
					int m = 0;
					if(wfJobUserReltes.get(i).getJobId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfJobUserReltes.get(i).getJobId(),wcformat));
					m++;
					if(wfJobUserReltes.get(i).getUserId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfJobUserReltes.get(i).getUserId(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfJobUserRelteListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfJobUserReltes != null && wfJobUserReltes.size() > 0) {
				for(int i=0; i<wfJobUserReltes.size();i++) {
					if(wfJobUserReltes.get(i) != null) {
						//wfJobUserReltes.get(i).setLastUpd(getSession().getUserId());
						//wfJobUserReltes.get(i).setLastUpdDate(new Date());
						new WfJobUserRelteFacade().confirm(wfJobUserReltes.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfJobUserRelteAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfJobUserReltes != null && wfJobUserReltes.size() > 0) {
				for(int i=0; i<wfJobUserReltes.size();i++) {
					if(wfJobUserReltes.get(i) != null) {
						//wfJobUserReltes.get(i).setLastUpd(getSession().getUserId());
						//wfJobUserReltes.get(i).setLastUpdDate(new Date());
						new WfJobUserRelteFacade().confirm(wfJobUserReltes.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfJobUserRelteAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfJobUserRelte> getWfJobUserReltes() {
		return wfJobUserReltes;
	}
	public void setWfJobUserReltes(List<WfJobUserRelte> wfJobUserReltes) {
		this.wfJobUserReltes = wfJobUserReltes;
	}
	public WfJobUserRelte getWfJobUserRelte() {
		return wfJobUserRelte;
	}
	public void setWfJobUserRelte(WfJobUserRelte wfJobUserRelte) {
		this.wfJobUserRelte = wfJobUserRelte;
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