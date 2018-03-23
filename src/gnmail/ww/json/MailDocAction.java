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

import gnmail.facade.MailDocFacade;
import gnmail.vo.MailDoc;
import gnmail.vo.json.MailDocJson;

public class MailDocAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<MailDoc> mailDocs;
	private MailDoc mailDoc = new MailDoc();
	private java.util.List<gnmail.vo.Mail> mails;

	public String execute() throws Exception {
		try {
			if(mailDoc != null && mailDoc.getDocId() != null) {
				mailDoc = new MailDocFacade().findById(mailDoc);
				setJson(JSON.toJSONString(mailDoc)); 
			}
			mails = new gnmail.facade.MailFacade().find("select "+gnmail.vo.Mail.SELF_FIELDS+" from Mail",gnmail.vo.Mail.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailDocAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(mailDoc != null && mailDoc.getDocId() != null) {
				//mailDoc = new MailDocFacade().findById(mailDoc);
				//setJson(JSON.toJSONString(mailDoc)); 
			//}
			mails = new gnmail.facade.MailFacade().find("select "+gnmail.vo.Mail.SELF_FIELDS+" from Mail",gnmail.vo.Mail.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailDocAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(mailDoc != null && mailDoc.getDocId() != null) {
				mailDoc = new MailDocFacade().findById(mailDoc);
				setJson(JSON.toJSONString(mailDoc)); 
			}
			mails = new gnmail.facade.MailFacade().find("select "+gnmail.vo.Mail.SELF_FIELDS+" from Mail",gnmail.vo.Mail.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailDocAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(mailDoc != null && mailDoc.getDocId() != null) {
				mailDoc = new MailDocFacade().findById(mailDoc);
				setJson(JSON.toJSONString(mailDoc)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailDocAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(mailDoc == null) mailDoc = new MailDoc();
			int total = new MailDocFacade().amount(mailDoc);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			mailDocs = new MailDocFacade().find(mailDoc,getPageVO());
			MailDocJson mailDocJson = new MailDocJson();
			mailDocJson.Rows = mailDocs;
			mailDocJson.Total = total;
			setJson(JSON.toJSONString(mailDocJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailDocAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//mailDoc.setCreateBy(getSession().getUserId());
			//mailDoc.setCreateDate(new Date());
			//mailDoc.setLastUpd(getSession().getUserId());
			//mailDoc.setLastUpdDate(new Date());

			if(mailDoc.getDocId() == null)
				new MailDocFacade().save(mailDoc);
			else
				new MailDocFacade().update(mailDoc);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("MailDocAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//mailDoc.setCreateBy(getSession().getUserId());
			//mailDoc.setCreateDate(new Date());
			//mailDoc.setLastUpd(getSession().getUserId());
			//mailDoc.setLastUpdDate(new Date());
			new MailDocFacade().update(mailDoc);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("MailDocAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(mailDocs != null && mailDocs.size() > 0) {
				for(int i=0; i<mailDocs.size();i++) {
					if(mailDocs.get(i) != null) {
						//mailDocs.get(i).setLastUpd(getSession().getUserId());
						//mailDocs.get(i).setLastUpdDate(new Date());
						new MailDocFacade().submit(mailDocs.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("MailDocAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(mailDocs != null && mailDocs.size() > 0) {
				for(int i=0; i<mailDocs.size();i++) {
					if(mailDocs.get(i) != null) {
						//mailDocs.get(i).setLastUpd(getSession().getUserId());
						//mailDocs.get(i).setLastUpdDate(new Date());
						new MailDocFacade().submit(mailDocs.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("MailDocAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(mailDocs != null && mailDocs.size() > 0) {
				for(int i=0; i<mailDocs.size();i++) {
					if(mailDocs.get(i) != null) {
						//mailDocs.get(i).setLastUpd(getSession().getUserId());
						//mailDocs.get(i).setLastUpdDate(new Date());
						new MailDocFacade().update(mailDocs.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("MailDocAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(mailDocs != null && mailDocs.size() > 0) {
				for(int i=0; i<mailDocs.size();i++) {
					if(mailDocs.get(i) != null) {
						//mailDocs.get(i).setLastUpd(getSession().getUserId());
						//mailDocs.get(i).setLastUpdDate(new Date());
						new MailDocFacade().submit(mailDocs.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("MailDocAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(mailDocs != null && mailDocs.size() > 0) {
				for(int i=0; i<mailDocs.size();i++) {
					if(mailDocs.get(i) != null) {
						//mailDocs.get(i).setLastUpd(getSession().getUserId());
						//mailDocs.get(i).setLastUpdDate(new Date());
						new MailDocFacade().submit(mailDocs.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("MailDocAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(mailDocs != null && mailDocs.size() > 0) {
				for(int i=0; i<mailDocs.size();i++) {
					if(mailDocs.get(i) != null) {
						//mailDocs.get(i).setLastUpd(getSession().getUserId());
						//mailDocs.get(i).setLastUpdDate(new Date());
						new MailDocFacade().submit(mailDocs.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("MailDocAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(mailDocs != null && mailDocs.size() > 0) {
				for(int i=0; i<mailDocs.size();i++) {
					if(mailDocs.get(i) != null) {
						//mailDocs.get(i).setLastUpd(getSession().getUserId());
						//mailDocs.get(i).setLastUpdDate(new Date());
						new MailDocFacade().submit(mailDocs.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("MailDocAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(mailDocs != null && mailDocs.size() > 0) {
				for(int i=0; i<mailDocs.size();i++) {
					if(mailDocs.get(i) != null){
						//mailDocs.get(i).setLastUpd(getSession().getUserId());
						//mailDocs.get(i).setLastUpdDate(new Date());
						new MailDocFacade().submit(mailDocs.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("MailDocAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(mailDocs != null && mailDocs.size() > 0) {
				for(int i=0; i<mailDocs.size();i++) {
					if(mailDocs.get(i) != null){
						//mailDocs.get(i).setLastUpd(getSession().getUserId());
						//mailDocs.get(i).setLastUpdDate(new Date());
						new MailDocFacade().review(mailDocs.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("MailDocAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(mailDocs != null && mailDocs.size() > 0) {
				for(int i=0; i<mailDocs.size();i++) {
					if(mailDocs.get(i) != null) {
						//mailDocs.get(i).setLastUpd(getSession().getUserId());
						//mailDocs.get(i).setLastUpdDate(new Date());
						new MailDocFacade().review(mailDocs.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("MailDocAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(mailDocs != null && mailDocs.size() > 0) {
				for(int i=0; i<mailDocs.size();i++) {
					if(mailDocs.get(i) != null) {
						//mailDocs.get(i).setLastUpd(getSession().getUserId());
						//mailDocs.get(i).setLastUpdDate(new Date());
						new MailDocFacade().confirm(mailDocs.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("MailDocAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(mailDocs != null && mailDocs.size() > 0) {
				for(int i=0; i<mailDocs.size();i++) {
					if(mailDocs.get(i) != null) {
						//mailDocs.get(i).setLastUpd(getSession().getUserId());
						//mailDocs.get(i).setLastUpdDate(new Date());
						new MailDocFacade().confirm(mailDocs.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("MailDocAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(mailDocs != null && mailDocs.size() > 0) {
					for(int i=0; i<mailDocs.size();i++) {
						if(mailDocs.get(i) != null) {
							//mailDocs.get(i).setLastUpd(getSession().getUserId());
							//mailDocs.get(i).setLastUpdDate(new Date());
							new MailDocFacade().confirm(mailDocs.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("MailDocAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<MailDoc> mailDocs = new MailDocFacade().find(mailDoc);
			if(mailDocs != null && mailDocs.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"附件ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"邮件ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"文档名称",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"URI连接",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<mailDocs.size();i++) {
					row++;
					int m = 0;
					if(mailDocs.get(i).getDocId() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailDocs.get(i).getDocId(),wcformat));
					m++;
					if(mailDocs.get(i).getMailId() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailDocs.get(i).getMailId(),wcformat));
					m++;
					if(mailDocs.get(i).getDocName() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailDocs.get(i).getDocName(),wcformat));
					m++;
					if(mailDocs.get(i).getUriLink() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailDocs.get(i).getUriLink(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("MailDocListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(mailDocs != null && mailDocs.size() > 0) {
				for(int i=0; i<mailDocs.size();i++) {
					if(mailDocs.get(i) != null) {
						//mailDocs.get(i).setLastUpd(getSession().getUserId());
						//mailDocs.get(i).setLastUpdDate(new Date());
						new MailDocFacade().confirm(mailDocs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("MailDocAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(mailDocs != null && mailDocs.size() > 0) {
				for(int i=0; i<mailDocs.size();i++) {
					if(mailDocs.get(i) != null) {
						//mailDocs.get(i).setLastUpd(getSession().getUserId());
						//mailDocs.get(i).setLastUpdDate(new Date());
						new MailDocFacade().confirm(mailDocs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("MailDocAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<MailDoc> getMailDocs() {
		return mailDocs;
	}
	public void setMailDocs(List<MailDoc> mailDocs) {
		this.mailDocs = mailDocs;
	}
	public MailDoc getMailDoc() {
		return mailDoc;
	}
	public void setMailDoc(MailDoc mailDoc) {
		this.mailDoc = mailDoc;
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
	public java.util.List<gnmail.vo.Mail> getMails() {
		return mails;
	}
	public void setMails(java.util.List<gnmail.vo.Mail> mails){
		this.mails = mails;
	}
	public void addtoMails(gnmail.vo.Mail mail){
		if(getMails() == null) setMails(new java.util.ArrayList<gnmail.vo.Mail>());
			getMails().add(mail);
	}

}