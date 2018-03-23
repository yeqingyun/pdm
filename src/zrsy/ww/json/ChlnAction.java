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

import zrsy.facade.ChlnFacade;
import zrsy.vo.Chln;
import zrsy.vo.json.ChlnJson;

public class ChlnAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<Chln> chlns;
	private Chln chln = new Chln();

	public String execute() throws Exception {
		try {
			if(chln != null && chln.getYear() != null) {
				chln = new ChlnFacade().findById(chln);
				setJson(JSON.toJSONString(chln)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ChlnAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(chln != null && chln.getYear() != null) {
				//chln = new ChlnFacade().findById(chln);
				//setJson(JSON.toJSONString(chln)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ChlnAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(chln != null && chln.getYear() != null) {
				chln = new ChlnFacade().findById(chln);
				setJson(JSON.toJSONString(chln)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ChlnAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(chln != null && chln.getYear() != null) {
				chln = new ChlnFacade().findById(chln);
				setJson(JSON.toJSONString(chln)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ChlnAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(chln == null) chln = new Chln();
			int total = new ChlnFacade().amount(chln);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			chlns = new ChlnFacade().find(chln,getPageVO());
			ChlnJson chlnJson = new ChlnJson();
			chlnJson.Rows = chlns;
			chlnJson.Total = total;
			setJson(JSON.toJSONString(chlnJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ChlnAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//chln.setCreateBy(getSession().getUserId());
			//chln.setCreateDate(new Date());
			//chln.setLastUpd(getSession().getUserId());
			//chln.setLastUpdDate(new Date());

			if(chln.getYear() == null)
				new ChlnFacade().save(chln);
			else
				new ChlnFacade().update(chln);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("ChlnAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//chln.setCreateBy(getSession().getUserId());
			//chln.setCreateDate(new Date());
			//chln.setLastUpd(getSession().getUserId());
			//chln.setLastUpdDate(new Date());
			new ChlnFacade().update(chln);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("ChlnAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(chlns != null && chlns.size() > 0) {
				for(int i=0; i<chlns.size();i++) {
					if(chlns.get(i) != null) {
						//chlns.get(i).setLastUpd(getSession().getUserId());
						//chlns.get(i).setLastUpdDate(new Date());
						new ChlnFacade().submit(chlns.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("ChlnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(chlns != null && chlns.size() > 0) {
				for(int i=0; i<chlns.size();i++) {
					if(chlns.get(i) != null) {
						//chlns.get(i).setLastUpd(getSession().getUserId());
						//chlns.get(i).setLastUpdDate(new Date());
						new ChlnFacade().submit(chlns.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("ChlnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(chlns != null && chlns.size() > 0) {
				for(int i=0; i<chlns.size();i++) {
					if(chlns.get(i) != null) {
						//chlns.get(i).setLastUpd(getSession().getUserId());
						//chlns.get(i).setLastUpdDate(new Date());
						new ChlnFacade().update(chlns.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("ChlnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(chlns != null && chlns.size() > 0) {
				for(int i=0; i<chlns.size();i++) {
					if(chlns.get(i) != null) {
						//chlns.get(i).setLastUpd(getSession().getUserId());
						//chlns.get(i).setLastUpdDate(new Date());
						new ChlnFacade().submit(chlns.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("ChlnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(chlns != null && chlns.size() > 0) {
				for(int i=0; i<chlns.size();i++) {
					if(chlns.get(i) != null) {
						//chlns.get(i).setLastUpd(getSession().getUserId());
						//chlns.get(i).setLastUpdDate(new Date());
						new ChlnFacade().submit(chlns.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("ChlnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(chlns != null && chlns.size() > 0) {
				for(int i=0; i<chlns.size();i++) {
					if(chlns.get(i) != null) {
						//chlns.get(i).setLastUpd(getSession().getUserId());
						//chlns.get(i).setLastUpdDate(new Date());
						new ChlnFacade().submit(chlns.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("ChlnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(chlns != null && chlns.size() > 0) {
				for(int i=0; i<chlns.size();i++) {
					if(chlns.get(i) != null) {
						//chlns.get(i).setLastUpd(getSession().getUserId());
						//chlns.get(i).setLastUpdDate(new Date());
						new ChlnFacade().submit(chlns.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("ChlnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(chlns != null && chlns.size() > 0) {
				for(int i=0; i<chlns.size();i++) {
					if(chlns.get(i) != null){
						//chlns.get(i).setLastUpd(getSession().getUserId());
						//chlns.get(i).setLastUpdDate(new Date());
						new ChlnFacade().submit(chlns.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("ChlnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(chlns != null && chlns.size() > 0) {
				for(int i=0; i<chlns.size();i++) {
					if(chlns.get(i) != null){
						//chlns.get(i).setLastUpd(getSession().getUserId());
						//chlns.get(i).setLastUpdDate(new Date());
						new ChlnFacade().review(chlns.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("ChlnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(chlns != null && chlns.size() > 0) {
				for(int i=0; i<chlns.size();i++) {
					if(chlns.get(i) != null) {
						//chlns.get(i).setLastUpd(getSession().getUserId());
						//chlns.get(i).setLastUpdDate(new Date());
						new ChlnFacade().review(chlns.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("ChlnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(chlns != null && chlns.size() > 0) {
				for(int i=0; i<chlns.size();i++) {
					if(chlns.get(i) != null) {
						//chlns.get(i).setLastUpd(getSession().getUserId());
						//chlns.get(i).setLastUpdDate(new Date());
						new ChlnFacade().confirm(chlns.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("ChlnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(chlns != null && chlns.size() > 0) {
				for(int i=0; i<chlns.size();i++) {
					if(chlns.get(i) != null) {
						//chlns.get(i).setLastUpd(getSession().getUserId());
						//chlns.get(i).setLastUpdDate(new Date());
						new ChlnFacade().confirm(chlns.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("ChlnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(chlns != null && chlns.size() > 0) {
					for(int i=0; i<chlns.size();i++) {
						if(chlns.get(i) != null) {
							//chlns.get(i).setLastUpd(getSession().getUserId());
							//chlns.get(i).setLastUpdDate(new Date());
							new ChlnFacade().confirm(chlns.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("ChlnAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<Chln> chlns = new ChlnFacade().find(chln);
			if(chlns != null && chlns.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"年",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"月",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"日",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"流水号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"创建人",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"最后更新",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"创建日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"更新日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"单据类型",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<chlns.size();i++) {
					row++;
					int m = 0;
					if(chlns.get(i).getYear() != null) 
						ws.addCell(new jxl.write.Number(m,row,chlns.get(i).getYear(),wcformat));
					m++;
					if(chlns.get(i).getMonth() != null) 
						ws.addCell(new jxl.write.Number(m,row,chlns.get(i).getMonth(),wcformat));
					m++;
					if(chlns.get(i).getDay() != null) 
						ws.addCell(new jxl.write.Number(m,row,chlns.get(i).getDay(),wcformat));
					m++;
					if(chlns.get(i).getChlnNo() != null) 
						ws.addCell(new jxl.write.Number(m,row,chlns.get(i).getChlnNo(),wcformat));
					m++;
					if(chlns.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,chlns.get(i).getCreateBy(),wcformat));
					m++;
					if(chlns.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,chlns.get(i).getLastUpd(),wcformat));
					m++;
					if(chlns.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,chlns.get(i).getCreateDate(),wcformat));
					m++;
					if(chlns.get(i).getLastDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,chlns.get(i).getLastDate(),wcformat));
					m++;
					if(chlns.get(i).getChlnTyp() != null) 
						ws.addCell(new jxl.write.Label(m,row,chlns.get(i).getChlnTyp(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("ChlnListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(chlns != null && chlns.size() > 0) {
				for(int i=0; i<chlns.size();i++) {
					if(chlns.get(i) != null) {
						//chlns.get(i).setLastUpd(getSession().getUserId());
						//chlns.get(i).setLastUpdDate(new Date());
						new ChlnFacade().confirm(chlns.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("ChlnAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(chlns != null && chlns.size() > 0) {
				for(int i=0; i<chlns.size();i++) {
					if(chlns.get(i) != null) {
						//chlns.get(i).setLastUpd(getSession().getUserId());
						//chlns.get(i).setLastUpdDate(new Date());
						new ChlnFacade().confirm(chlns.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("ChlnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<Chln> getChlns() {
		return chlns;
	}
	public void setChlns(List<Chln> chlns) {
		this.chlns = chlns;
	}
	public Chln getChln() {
		return chln;
	}
	public void setChln(Chln chln) {
		this.chln = chln;
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