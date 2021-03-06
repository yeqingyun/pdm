package gnmail.ww.json;

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

import gnmail.ww.MSG;
import gnmail.ww.BasicAction;

import gnmail.facade.MailToFacade;
import gnmail.vo.MailTo;
import gnmail.vo.json.MailToJson;

public class MailToAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<MailTo> mailTos;
	private MailTo mailTo = new MailTo();

	public String execute() throws Exception {
		try {
			if(mailTo != null && mailTo.getMailId() != null) {
				mailTo = new MailToFacade().findById(mailTo);
				setJson(JSON.toJSONString(mailTo)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailToAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(mailTo != null && mailTo.getMailId() != null) {
				//mailTo = new MailToFacade().findById(mailTo);
				//setJson(JSON.toJSONString(mailTo)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailToAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(mailTo != null && mailTo.getMailId() != null) {
				mailTo = new MailToFacade().findById(mailTo);
				setJson(JSON.toJSONString(mailTo)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailToAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(mailTo != null && mailTo.getMailId() != null) {
				mailTo = new MailToFacade().findById(mailTo);
				setJson(JSON.toJSONString(mailTo)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailToAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(mailTo == null) mailTo = new MailTo();
			int total = new MailToFacade().amount(mailTo);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			mailTos = new MailToFacade().find(mailTo,getPageVO());
			MailToJson mailToJson = new MailToJson();
			mailToJson.Rows = mailTos;
			mailToJson.Total = total;
			setJson(JSON.toJSONString(mailToJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailToAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//mailTo.setCreateBy(getSession().getUserId());
			//mailTo.setCreateDate(new Date());
			//mailTo.setLastUpd(getSession().getUserId());
			//mailTo.setLastUpdDate(new Date());

			if(mailTo.getMailId() == null)
				new MailToFacade().save(mailTo);
			else
				new MailToFacade().update(mailTo);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("MailToAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//mailTo.setCreateBy(getSession().getUserId());
			//mailTo.setCreateDate(new Date());
			//mailTo.setLastUpd(getSession().getUserId());
			//mailTo.setLastUpdDate(new Date());
			new MailToFacade().update(mailTo);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("MailToAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(mailTos != null && mailTos.size() > 0) {
				for(int i=0; i<mailTos.size();i++) {
					if(mailTos.get(i) != null) {
						//mailTos.get(i).setLastUpd(getSession().getUserId());
						//mailTos.get(i).setLastUpdDate(new Date());
						new MailToFacade().submit(mailTos.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("MailToAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(mailTos != null && mailTos.size() > 0) {
				for(int i=0; i<mailTos.size();i++) {
					if(mailTos.get(i) != null) {
						//mailTos.get(i).setLastUpd(getSession().getUserId());
						//mailTos.get(i).setLastUpdDate(new Date());
						new MailToFacade().submit(mailTos.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("MailToAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(mailTos != null && mailTos.size() > 0) {
				for(int i=0; i<mailTos.size();i++) {
					if(mailTos.get(i) != null) {
						//mailTos.get(i).setLastUpd(getSession().getUserId());
						//mailTos.get(i).setLastUpdDate(new Date());
						new MailToFacade().update(mailTos.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("MailToAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(mailTos != null && mailTos.size() > 0) {
				for(int i=0; i<mailTos.size();i++) {
					if(mailTos.get(i) != null) {
						//mailTos.get(i).setLastUpd(getSession().getUserId());
						//mailTos.get(i).setLastUpdDate(new Date());
						new MailToFacade().submit(mailTos.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("MailToAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(mailTos != null && mailTos.size() > 0) {
				for(int i=0; i<mailTos.size();i++) {
					if(mailTos.get(i) != null) {
						//mailTos.get(i).setLastUpd(getSession().getUserId());
						//mailTos.get(i).setLastUpdDate(new Date());
						new MailToFacade().submit(mailTos.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("MailToAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(mailTos != null && mailTos.size() > 0) {
				for(int i=0; i<mailTos.size();i++) {
					if(mailTos.get(i) != null) {
						//mailTos.get(i).setLastUpd(getSession().getUserId());
						//mailTos.get(i).setLastUpdDate(new Date());
						new MailToFacade().submit(mailTos.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("MailToAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(mailTos != null && mailTos.size() > 0) {
				for(int i=0; i<mailTos.size();i++) {
					if(mailTos.get(i) != null) {
						//mailTos.get(i).setLastUpd(getSession().getUserId());
						//mailTos.get(i).setLastUpdDate(new Date());
						new MailToFacade().submit(mailTos.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("MailToAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(mailTos != null && mailTos.size() > 0) {
				for(int i=0; i<mailTos.size();i++) {
					if(mailTos.get(i) != null){
						//mailTos.get(i).setLastUpd(getSession().getUserId());
						//mailTos.get(i).setLastUpdDate(new Date());
						new MailToFacade().submit(mailTos.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("MailToAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(mailTos != null && mailTos.size() > 0) {
				for(int i=0; i<mailTos.size();i++) {
					if(mailTos.get(i) != null){
						//mailTos.get(i).setLastUpd(getSession().getUserId());
						//mailTos.get(i).setLastUpdDate(new Date());
						new MailToFacade().review(mailTos.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("MailToAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(mailTos != null && mailTos.size() > 0) {
				for(int i=0; i<mailTos.size();i++) {
					if(mailTos.get(i) != null) {
						//mailTos.get(i).setLastUpd(getSession().getUserId());
						//mailTos.get(i).setLastUpdDate(new Date());
						new MailToFacade().review(mailTos.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("MailToAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(mailTos != null && mailTos.size() > 0) {
				for(int i=0; i<mailTos.size();i++) {
					if(mailTos.get(i) != null) {
						//mailTos.get(i).setLastUpd(getSession().getUserId());
						//mailTos.get(i).setLastUpdDate(new Date());
						new MailToFacade().confirm(mailTos.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("MailToAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(mailTos != null && mailTos.size() > 0) {
				for(int i=0; i<mailTos.size();i++) {
					if(mailTos.get(i) != null) {
						//mailTos.get(i).setLastUpd(getSession().getUserId());
						//mailTos.get(i).setLastUpdDate(new Date());
						new MailToFacade().confirm(mailTos.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("MailToAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(mailTos != null && mailTos.size() > 0) {
					for(int i=0; i<mailTos.size();i++) {
						if(mailTos.get(i) != null) {
							//mailTos.get(i).setLastUpd(getSession().getUserId());
							//mailTos.get(i).setLastUpdDate(new Date());
							new MailToFacade().confirm(mailTos.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("MailToAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<MailTo> mailTos = new MailToFacade().find(mailTo);
			if(mailTos != null && mailTos.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"邮件ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"收件人ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"电子信箱地址",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"类型（0主送，1抄送，2暗送）",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"收件人",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<mailTos.size();i++) {
					row++;
					int m = 0;
					if(mailTos.get(i).getMailId() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailTos.get(i).getMailId(),wcformat));
					m++;
					if(mailTos.get(i).getToId() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailTos.get(i).getToId(),wcformat));
					m++;
					if(mailTos.get(i).getToMail() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailTos.get(i).getToMail(),wcformat));
					m++;
					if(mailTos.get(i).getToType() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailTos.get(i).getToType(),wcformat));
					m++;
					if(mailTos.get(i).getToName() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailTos.get(i).getToName(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("MailToListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(mailTos != null && mailTos.size() > 0) {
				for(int i=0; i<mailTos.size();i++) {
					if(mailTos.get(i) != null) {
						//mailTos.get(i).setLastUpd(getSession().getUserId());
						//mailTos.get(i).setLastUpdDate(new Date());
						new MailToFacade().confirm(mailTos.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("MailToAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(mailTos != null && mailTos.size() > 0) {
				for(int i=0; i<mailTos.size();i++) {
					if(mailTos.get(i) != null) {
						//mailTos.get(i).setLastUpd(getSession().getUserId());
						//mailTos.get(i).setLastUpdDate(new Date());
						new MailToFacade().confirm(mailTos.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("MailToAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<MailTo> getMailTos() {
		return mailTos;
	}
	public void setMailTos(List<MailTo> mailTos) {
		this.mailTos = mailTos;
	}
	public MailTo getMailTo() {
		return mailTo;
	}
	public void setMailTo(MailTo mailTo) {
		this.mailTo = mailTo;
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