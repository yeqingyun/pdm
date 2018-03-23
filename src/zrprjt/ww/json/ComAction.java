package zrprjt.ww.json;

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

import zrprjt.ww.MSG;
import zrprjt.ww.BasicAction;

import zrprjt.facade.ComFacade;
import zrprjt.vo.Com;
import zrprjt.vo.json.ComJson;

public class ComAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<Com> coms;
	private Com com = new Com();

	public String execute() throws Exception {
		try {
			if(com != null && com.getComId() != null) {
				com = new ComFacade().findById(com);
				setJson(JSON.toJSONString(com)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ComAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(com != null && com.getComId() != null) {
				//com = new ComFacade().findById(com);
				//setJson(JSON.toJSONString(com)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ComAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(com != null && com.getComId() != null) {
				com = new ComFacade().findById(com);
				setJson(JSON.toJSONString(com)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ComAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(com != null && com.getComId() != null) {
				com = new ComFacade().findById(com);
				setJson(JSON.toJSONString(com)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ComAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(com == null) com = new Com();
			int total = new ComFacade().amount(com);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			coms = new ComFacade().find(com,getPageVO());
			ComJson comJson = new ComJson();
			comJson.Rows = coms;
			comJson.Total = total;
			setJson(JSON.toJSONString(comJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ComAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//com.setCreateBy(getSession().getUserId());
			//com.setCreateDate(new Date());
			//com.setLastUpd(getSession().getUserId());
			//com.setLastUpdDate(new Date());

			if(com.getComId() == null)
				new ComFacade().save(com);
			else
				new ComFacade().update(com);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("ComAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//com.setCreateBy(getSession().getUserId());
			//com.setCreateDate(new Date());
			//com.setLastUpd(getSession().getUserId());
			//com.setLastUpdDate(new Date());
			new ComFacade().update(com);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("ComAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(coms != null && coms.size() > 0) {
				for(int i=0; i<coms.size();i++) {
					if(coms.get(i) != null) {
						//coms.get(i).setLastUpd(getSession().getUserId());
						//coms.get(i).setLastUpdDate(new Date());
						new ComFacade().submit(coms.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("ComAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(coms != null && coms.size() > 0) {
				for(int i=0; i<coms.size();i++) {
					if(coms.get(i) != null) {
						//coms.get(i).setLastUpd(getSession().getUserId());
						//coms.get(i).setLastUpdDate(new Date());
						new ComFacade().submit(coms.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("ComAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(coms != null && coms.size() > 0) {
				for(int i=0; i<coms.size();i++) {
					if(coms.get(i) != null) {
						//coms.get(i).setLastUpd(getSession().getUserId());
						//coms.get(i).setLastUpdDate(new Date());
						new ComFacade().update(coms.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("ComAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(coms != null && coms.size() > 0) {
				for(int i=0; i<coms.size();i++) {
					if(coms.get(i) != null) {
						//coms.get(i).setLastUpd(getSession().getUserId());
						//coms.get(i).setLastUpdDate(new Date());
						new ComFacade().submit(coms.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("ComAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(coms != null && coms.size() > 0) {
				for(int i=0; i<coms.size();i++) {
					if(coms.get(i) != null) {
						//coms.get(i).setLastUpd(getSession().getUserId());
						//coms.get(i).setLastUpdDate(new Date());
						new ComFacade().submit(coms.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("ComAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(coms != null && coms.size() > 0) {
				for(int i=0; i<coms.size();i++) {
					if(coms.get(i) != null) {
						//coms.get(i).setLastUpd(getSession().getUserId());
						//coms.get(i).setLastUpdDate(new Date());
						new ComFacade().submit(coms.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("ComAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(coms != null && coms.size() > 0) {
				for(int i=0; i<coms.size();i++) {
					if(coms.get(i) != null) {
						//coms.get(i).setLastUpd(getSession().getUserId());
						//coms.get(i).setLastUpdDate(new Date());
						new ComFacade().submit(coms.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("ComAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(coms != null && coms.size() > 0) {
				for(int i=0; i<coms.size();i++) {
					if(coms.get(i) != null){
						//coms.get(i).setLastUpd(getSession().getUserId());
						//coms.get(i).setLastUpdDate(new Date());
						new ComFacade().submit(coms.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("ComAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(coms != null && coms.size() > 0) {
				for(int i=0; i<coms.size();i++) {
					if(coms.get(i) != null){
						//coms.get(i).setLastUpd(getSession().getUserId());
						//coms.get(i).setLastUpdDate(new Date());
						new ComFacade().review(coms.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("ComAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(coms != null && coms.size() > 0) {
				for(int i=0; i<coms.size();i++) {
					if(coms.get(i) != null) {
						//coms.get(i).setLastUpd(getSession().getUserId());
						//coms.get(i).setLastUpdDate(new Date());
						new ComFacade().review(coms.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("ComAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(coms != null && coms.size() > 0) {
				for(int i=0; i<coms.size();i++) {
					if(coms.get(i) != null) {
						//coms.get(i).setLastUpd(getSession().getUserId());
						//coms.get(i).setLastUpdDate(new Date());
						new ComFacade().confirm(coms.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("ComAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(coms != null && coms.size() > 0) {
				for(int i=0; i<coms.size();i++) {
					if(coms.get(i) != null) {
						//coms.get(i).setLastUpd(getSession().getUserId());
						//coms.get(i).setLastUpdDate(new Date());
						new ComFacade().confirm(coms.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("ComAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(coms != null && coms.size() > 0) {
					for(int i=0; i<coms.size();i++) {
						if(coms.get(i) != null) {
							//coms.get(i).setLastUpd(getSession().getUserId());
							//coms.get(i).setLastUpdDate(new Date());
							new ComFacade().confirm(coms.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("ComAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<Com> coms = new ComFacade().find(com);
			if(coms != null && coms.size() > 0) {
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
					ws.addCell(new Label(index,1,"上级公司",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"级别",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"状态",wcformat));
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
					ws.addCell(new Label(index,1,"公司编号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"公司名称",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"备注",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<coms.size();i++) {
					row++;
					int m = 0;
					if(coms.get(i).getComId() != null) 
						ws.addCell(new jxl.write.Number(m,row,coms.get(i).getComId(),wcformat));
					m++;
					if(coms.get(i).getParent() != null) 
						ws.addCell(new jxl.write.Number(m,row,coms.get(i).getParent(),wcformat));
					m++;
					if(coms.get(i).getLeve() != null) 
						ws.addCell(new jxl.write.Number(m,row,coms.get(i).getLeve(),wcformat));
					m++;
					if(coms.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,coms.get(i).getStatus(),wcformat));
					m++;
					if(coms.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,coms.get(i).getCreateBy(),wcformat));
					m++;
					if(coms.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,coms.get(i).getLastUpd(),wcformat));
					m++;
					if(coms.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,coms.get(i).getCreateDate(),wcformat));
					m++;
					if(coms.get(i).getLastDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,coms.get(i).getLastDate(),wcformat));
					m++;
					if(coms.get(i).getComNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,coms.get(i).getComNo(),wcformat));
					m++;
					if(coms.get(i).getComNm() != null) 
						ws.addCell(new jxl.write.Label(m,row,coms.get(i).getComNm(),wcformat));
					m++;
					if(coms.get(i).getRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,coms.get(i).getRemark(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("ComListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(coms != null && coms.size() > 0) {
				for(int i=0; i<coms.size();i++) {
					if(coms.get(i) != null) {
						//coms.get(i).setLastUpd(getSession().getUserId());
						//coms.get(i).setLastUpdDate(new Date());
						new ComFacade().confirm(coms.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("ComAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(coms != null && coms.size() > 0) {
				for(int i=0; i<coms.size();i++) {
					if(coms.get(i) != null) {
						//coms.get(i).setLastUpd(getSession().getUserId());
						//coms.get(i).setLastUpdDate(new Date());
						new ComFacade().confirm(coms.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("ComAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<Com> getComs() {
		return coms;
	}
	public void setComs(List<Com> coms) {
		this.coms = coms;
	}
	public Com getCom() {
		return com;
	}
	public void setCom(Com com) {
		this.com = com;
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