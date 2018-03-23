package zrsy.ww.json;

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

import zrsy.ww.MSG;
import zrsy.ww.BasicAction;

import zrsy.facade.PgBtnFacade;
import zrsy.vo.PgBtn;
import zrsy.vo.json.PgBtnJson;

public class PgBtnAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<PgBtn> pgBtns;
	private PgBtn pgBtn = new PgBtn();

	public String execute() throws Exception {
		try {
			if(pgBtn != null && pgBtn.getNodeId() != null) {
				pgBtn = new PgBtnFacade().findById(pgBtn);
				setJson(JSON.toJSONString(pgBtn)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PgBtnAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(pgBtn != null && pgBtn.getNodeId() != null) {
				//pgBtn = new PgBtnFacade().findById(pgBtn);
				//setJson(JSON.toJSONString(pgBtn)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PgBtnAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(pgBtn != null && pgBtn.getNodeId() != null) {
				pgBtn = new PgBtnFacade().findById(pgBtn);
				setJson(JSON.toJSONString(pgBtn)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PgBtnAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(pgBtn != null && pgBtn.getNodeId() != null) {
				pgBtn = new PgBtnFacade().findById(pgBtn);
				setJson(JSON.toJSONString(pgBtn)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PgBtnAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(pgBtn == null) pgBtn = new PgBtn();
			int total = new PgBtnFacade().amount(pgBtn);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			pgBtns = new PgBtnFacade().find(pgBtn,getPageVO());
			PgBtnJson pgBtnJson = new PgBtnJson();
			pgBtnJson.Rows = pgBtns;
			pgBtnJson.Total = total;
			setJson(JSON.toJSONString(pgBtnJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PgBtnAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//pgBtn.setCreateBy(getSession().getUserId());
			//pgBtn.setCreateDate(new Date());
			//pgBtn.setLastUpd(getSession().getUserId());
			//pgBtn.setLastUpdDate(new Date());

			if(pgBtn.getNodeId() == null)
				new PgBtnFacade().save(pgBtn);
			else
				new PgBtnFacade().update(pgBtn);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("PgBtnAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//pgBtn.setCreateBy(getSession().getUserId());
			//pgBtn.setCreateDate(new Date());
			//pgBtn.setLastUpd(getSession().getUserId());
			//pgBtn.setLastUpdDate(new Date());
			new PgBtnFacade().update(pgBtn);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("PgBtnAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(pgBtns != null && pgBtns.size() > 0) {
				for(int i=0; i<pgBtns.size();i++) {
					if(pgBtns.get(i) != null) {
						//pgBtns.get(i).setLastUpd(getSession().getUserId());
						//pgBtns.get(i).setLastUpdDate(new Date());
						new PgBtnFacade().submit(pgBtns.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("PgBtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(pgBtns != null && pgBtns.size() > 0) {
				for(int i=0; i<pgBtns.size();i++) {
					if(pgBtns.get(i) != null) {
						//pgBtns.get(i).setLastUpd(getSession().getUserId());
						//pgBtns.get(i).setLastUpdDate(new Date());
						new PgBtnFacade().submit(pgBtns.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("PgBtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(pgBtns != null && pgBtns.size() > 0) {
				for(int i=0; i<pgBtns.size();i++) {
					if(pgBtns.get(i) != null) {
						//pgBtns.get(i).setLastUpd(getSession().getUserId());
						//pgBtns.get(i).setLastUpdDate(new Date());
						new PgBtnFacade().update(pgBtns.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("PgBtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(pgBtns != null && pgBtns.size() > 0) {
				for(int i=0; i<pgBtns.size();i++) {
					if(pgBtns.get(i) != null) {
						//pgBtns.get(i).setLastUpd(getSession().getUserId());
						//pgBtns.get(i).setLastUpdDate(new Date());
						new PgBtnFacade().submit(pgBtns.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("PgBtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(pgBtns != null && pgBtns.size() > 0) {
				for(int i=0; i<pgBtns.size();i++) {
					if(pgBtns.get(i) != null) {
						//pgBtns.get(i).setLastUpd(getSession().getUserId());
						//pgBtns.get(i).setLastUpdDate(new Date());
						new PgBtnFacade().submit(pgBtns.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("PgBtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(pgBtns != null && pgBtns.size() > 0) {
				for(int i=0; i<pgBtns.size();i++) {
					if(pgBtns.get(i) != null) {
						//pgBtns.get(i).setLastUpd(getSession().getUserId());
						//pgBtns.get(i).setLastUpdDate(new Date());
						new PgBtnFacade().submit(pgBtns.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("PgBtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(pgBtns != null && pgBtns.size() > 0) {
				for(int i=0; i<pgBtns.size();i++) {
					if(pgBtns.get(i) != null) {
						//pgBtns.get(i).setLastUpd(getSession().getUserId());
						//pgBtns.get(i).setLastUpdDate(new Date());
						new PgBtnFacade().submit(pgBtns.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("PgBtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(pgBtns != null && pgBtns.size() > 0) {
				for(int i=0; i<pgBtns.size();i++) {
					if(pgBtns.get(i) != null){
						//pgBtns.get(i).setLastUpd(getSession().getUserId());
						//pgBtns.get(i).setLastUpdDate(new Date());
						new PgBtnFacade().submit(pgBtns.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("PgBtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(pgBtns != null && pgBtns.size() > 0) {
				for(int i=0; i<pgBtns.size();i++) {
					if(pgBtns.get(i) != null){
						//pgBtns.get(i).setLastUpd(getSession().getUserId());
						//pgBtns.get(i).setLastUpdDate(new Date());
						new PgBtnFacade().review(pgBtns.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("PgBtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(pgBtns != null && pgBtns.size() > 0) {
				for(int i=0; i<pgBtns.size();i++) {
					if(pgBtns.get(i) != null) {
						//pgBtns.get(i).setLastUpd(getSession().getUserId());
						//pgBtns.get(i).setLastUpdDate(new Date());
						new PgBtnFacade().review(pgBtns.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("PgBtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(pgBtns != null && pgBtns.size() > 0) {
				for(int i=0; i<pgBtns.size();i++) {
					if(pgBtns.get(i) != null) {
						//pgBtns.get(i).setLastUpd(getSession().getUserId());
						//pgBtns.get(i).setLastUpdDate(new Date());
						new PgBtnFacade().confirm(pgBtns.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("PgBtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(pgBtns != null && pgBtns.size() > 0) {
				for(int i=0; i<pgBtns.size();i++) {
					if(pgBtns.get(i) != null) {
						//pgBtns.get(i).setLastUpd(getSession().getUserId());
						//pgBtns.get(i).setLastUpdDate(new Date());
						new PgBtnFacade().confirm(pgBtns.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("PgBtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(pgBtns != null && pgBtns.size() > 0) {
					for(int i=0; i<pgBtns.size();i++) {
						if(pgBtns.get(i) != null) {
							//pgBtns.get(i).setLastUpd(getSession().getUserId());
							//pgBtns.get(i).setLastUpdDate(new Date());
							new PgBtnFacade().confirm(pgBtns.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("PgBtnAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<PgBtn> pgBtns = new PgBtnFacade().find(pgBtn);
			if(pgBtns != null && pgBtns.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"功能",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"按钮",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"页类型",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<pgBtns.size();i++) {
					row++;
					int m = 0;
					if(pgBtns.get(i).getNodeId() != null) 
						ws.addCell(new jxl.write.Number(m,row,pgBtns.get(i).getNodeId(),wcformat));
					m++;
					if(pgBtns.get(i).getBtnId() != null) 
						ws.addCell(new jxl.write.Number(m,row,pgBtns.get(i).getBtnId(),wcformat));
					m++;
					if(pgBtns.get(i).getPgTyp() != null) 
						ws.addCell(new jxl.write.Number(m,row,pgBtns.get(i).getPgTyp(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("PgBtnListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(pgBtns != null && pgBtns.size() > 0) {
				for(int i=0; i<pgBtns.size();i++) {
					if(pgBtns.get(i) != null) {
						//pgBtns.get(i).setLastUpd(getSession().getUserId());
						//pgBtns.get(i).setLastUpdDate(new Date());
						new PgBtnFacade().confirm(pgBtns.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("PgBtnAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(pgBtns != null && pgBtns.size() > 0) {
				for(int i=0; i<pgBtns.size();i++) {
					if(pgBtns.get(i) != null) {
						//pgBtns.get(i).setLastUpd(getSession().getUserId());
						//pgBtns.get(i).setLastUpdDate(new Date());
						new PgBtnFacade().confirm(pgBtns.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("PgBtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<PgBtn> getPgBtns() {
		return pgBtns;
	}
	public void setPgBtns(List<PgBtn> pgBtns) {
		this.pgBtns = pgBtns;
	}
	public PgBtn getPgBtn() {
		return pgBtn;
	}
	public void setPgBtn(PgBtn pgBtn) {
		this.pgBtn = pgBtn;
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