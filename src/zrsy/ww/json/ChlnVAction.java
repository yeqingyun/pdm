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

import zrsy.facade.ChlnVFacade;
import zrsy.vo.ChlnV;
import zrsy.vo.json.ChlnVJson;

public class ChlnVAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<ChlnV> chlnVs;
	private ChlnV chlnV = new ChlnV();

	public String execute() throws Exception {
		try {
			if(chlnV != null && chlnV.getFileNm() != null) {
				chlnV = new ChlnVFacade().findById(chlnV);
				setJson(JSON.toJSONString(chlnV)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ChlnVAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(chlnV != null && chlnV.getFileNm() != null) {
				//chlnV = new ChlnVFacade().findById(chlnV);
				//setJson(JSON.toJSONString(chlnV)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ChlnVAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(chlnV != null && chlnV.getFileNm() != null) {
				chlnV = new ChlnVFacade().findById(chlnV);
				setJson(JSON.toJSONString(chlnV)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ChlnVAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(chlnV != null && chlnV.getFileNm() != null) {
				chlnV = new ChlnVFacade().findById(chlnV);
				setJson(JSON.toJSONString(chlnV)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ChlnVAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(chlnV == null) chlnV = new ChlnV();
			int total = new ChlnVFacade().amount(chlnV);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			chlnVs = new ChlnVFacade().find(chlnV,getPageVO());
			ChlnVJson chlnVJson = new ChlnVJson();
			chlnVJson.Rows = chlnVs;
			chlnVJson.Total = total;
			setJson(JSON.toJSONString(chlnVJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ChlnVAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//chlnV.setCreateBy(getSession().getUserId());
			//chlnV.setCreateDate(new Date());
			//chlnV.setLastUpd(getSession().getUserId());
			//chlnV.setLastUpdDate(new Date());

			if(chlnV.getFileNm() == null)
				new ChlnVFacade().save(chlnV);
			else
				new ChlnVFacade().update(chlnV);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("ChlnVAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//chlnV.setCreateBy(getSession().getUserId());
			//chlnV.setCreateDate(new Date());
			//chlnV.setLastUpd(getSession().getUserId());
			//chlnV.setLastUpdDate(new Date());
			new ChlnVFacade().update(chlnV);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("ChlnVAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(chlnVs != null && chlnVs.size() > 0) {
				for(int i=0; i<chlnVs.size();i++) {
					if(chlnVs.get(i) != null) {
						//chlnVs.get(i).setLastUpd(getSession().getUserId());
						//chlnVs.get(i).setLastUpdDate(new Date());
						new ChlnVFacade().submit(chlnVs.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("ChlnVAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(chlnVs != null && chlnVs.size() > 0) {
				for(int i=0; i<chlnVs.size();i++) {
					if(chlnVs.get(i) != null) {
						//chlnVs.get(i).setLastUpd(getSession().getUserId());
						//chlnVs.get(i).setLastUpdDate(new Date());
						new ChlnVFacade().submit(chlnVs.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("ChlnVAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(chlnVs != null && chlnVs.size() > 0) {
				for(int i=0; i<chlnVs.size();i++) {
					if(chlnVs.get(i) != null) {
						//chlnVs.get(i).setLastUpd(getSession().getUserId());
						//chlnVs.get(i).setLastUpdDate(new Date());
						new ChlnVFacade().update(chlnVs.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("ChlnVAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(chlnVs != null && chlnVs.size() > 0) {
				for(int i=0; i<chlnVs.size();i++) {
					if(chlnVs.get(i) != null) {
						//chlnVs.get(i).setLastUpd(getSession().getUserId());
						//chlnVs.get(i).setLastUpdDate(new Date());
						new ChlnVFacade().submit(chlnVs.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("ChlnVAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(chlnVs != null && chlnVs.size() > 0) {
				for(int i=0; i<chlnVs.size();i++) {
					if(chlnVs.get(i) != null) {
						//chlnVs.get(i).setLastUpd(getSession().getUserId());
						//chlnVs.get(i).setLastUpdDate(new Date());
						new ChlnVFacade().submit(chlnVs.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("ChlnVAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(chlnVs != null && chlnVs.size() > 0) {
				for(int i=0; i<chlnVs.size();i++) {
					if(chlnVs.get(i) != null) {
						//chlnVs.get(i).setLastUpd(getSession().getUserId());
						//chlnVs.get(i).setLastUpdDate(new Date());
						new ChlnVFacade().submit(chlnVs.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("ChlnVAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(chlnVs != null && chlnVs.size() > 0) {
				for(int i=0; i<chlnVs.size();i++) {
					if(chlnVs.get(i) != null) {
						//chlnVs.get(i).setLastUpd(getSession().getUserId());
						//chlnVs.get(i).setLastUpdDate(new Date());
						new ChlnVFacade().submit(chlnVs.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("ChlnVAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(chlnVs != null && chlnVs.size() > 0) {
				for(int i=0; i<chlnVs.size();i++) {
					if(chlnVs.get(i) != null){
						//chlnVs.get(i).setLastUpd(getSession().getUserId());
						//chlnVs.get(i).setLastUpdDate(new Date());
						new ChlnVFacade().submit(chlnVs.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("ChlnVAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(chlnVs != null && chlnVs.size() > 0) {
				for(int i=0; i<chlnVs.size();i++) {
					if(chlnVs.get(i) != null){
						//chlnVs.get(i).setLastUpd(getSession().getUserId());
						//chlnVs.get(i).setLastUpdDate(new Date());
						new ChlnVFacade().review(chlnVs.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("ChlnVAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(chlnVs != null && chlnVs.size() > 0) {
				for(int i=0; i<chlnVs.size();i++) {
					if(chlnVs.get(i) != null) {
						//chlnVs.get(i).setLastUpd(getSession().getUserId());
						//chlnVs.get(i).setLastUpdDate(new Date());
						new ChlnVFacade().review(chlnVs.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("ChlnVAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(chlnVs != null && chlnVs.size() > 0) {
				for(int i=0; i<chlnVs.size();i++) {
					if(chlnVs.get(i) != null) {
						//chlnVs.get(i).setLastUpd(getSession().getUserId());
						//chlnVs.get(i).setLastUpdDate(new Date());
						new ChlnVFacade().confirm(chlnVs.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("ChlnVAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(chlnVs != null && chlnVs.size() > 0) {
				for(int i=0; i<chlnVs.size();i++) {
					if(chlnVs.get(i) != null) {
						//chlnVs.get(i).setLastUpd(getSession().getUserId());
						//chlnVs.get(i).setLastUpdDate(new Date());
						new ChlnVFacade().confirm(chlnVs.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("ChlnVAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(chlnVs != null && chlnVs.size() > 0) {
					for(int i=0; i<chlnVs.size();i++) {
						if(chlnVs.get(i) != null) {
							//chlnVs.get(i).setLastUpd(getSession().getUserId());
							//chlnVs.get(i).setLastUpdDate(new Date());
							new ChlnVFacade().confirm(chlnVs.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("ChlnVAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<ChlnV> chlnVs = new ChlnVFacade().find(chlnV);
			if(chlnVs != null && chlnVs.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"文件名",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"版本号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"创建人",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"创建日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"最后更新",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"更新日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"单据类型",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<chlnVs.size();i++) {
					row++;
					int m = 0;
					if(chlnVs.get(i).getFileNm() != null) 
						ws.addCell(new jxl.write.Label(m,row,chlnVs.get(i).getFileNm(),wcformat));
					m++;
					if(chlnVs.get(i).getChlnNo() != null) 
						ws.addCell(new jxl.write.Number(m,row,chlnVs.get(i).getChlnNo(),wcformat));
					m++;
					if(chlnVs.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,chlnVs.get(i).getCreateBy(),wcformat));
					m++;
					if(chlnVs.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,chlnVs.get(i).getCreateDate(),wcformat));
					m++;
					if(chlnVs.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,chlnVs.get(i).getLastUpd(),wcformat));
					m++;
					if(chlnVs.get(i).getLastDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,chlnVs.get(i).getLastDate(),wcformat));
					m++;
					if(chlnVs.get(i).getChlnTyp() != null) 
						ws.addCell(new jxl.write.Label(m,row,chlnVs.get(i).getChlnTyp(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("ChlnVListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(chlnVs != null && chlnVs.size() > 0) {
				for(int i=0; i<chlnVs.size();i++) {
					if(chlnVs.get(i) != null) {
						//chlnVs.get(i).setLastUpd(getSession().getUserId());
						//chlnVs.get(i).setLastUpdDate(new Date());
						new ChlnVFacade().confirm(chlnVs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("ChlnVAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(chlnVs != null && chlnVs.size() > 0) {
				for(int i=0; i<chlnVs.size();i++) {
					if(chlnVs.get(i) != null) {
						//chlnVs.get(i).setLastUpd(getSession().getUserId());
						//chlnVs.get(i).setLastUpdDate(new Date());
						new ChlnVFacade().confirm(chlnVs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("ChlnVAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<ChlnV> getChlnVs() {
		return chlnVs;
	}
	public void setChlnVs(List<ChlnV> chlnVs) {
		this.chlnVs = chlnVs;
	}
	public ChlnV getChlnV() {
		return chlnV;
	}
	public void setChlnV(ChlnV chlnV) {
		this.chlnV = chlnV;
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