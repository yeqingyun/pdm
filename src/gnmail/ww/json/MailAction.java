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

import gnmail.facade.MailFacade;
import gnmail.vo.Mail;
import gnmail.vo.json.MailJson;

public class MailAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<Mail> mails;
	private Mail mail = new Mail();
	private java.util.List<gnmail.vo.MailAutoM> mailAutoMs;
	private java.util.List<gnmail.vo.MailDoc> mailDocs;
	private java.util.List<gnmail.vo.MailCfg> mailCfgs;

	public String execute() throws Exception {
		try {
			if(mail != null && mail.getMailId() != null) {
				mail = new MailFacade().findById(mail);
				setJson(JSON.toJSONString(mail)); 
			}
			mailCfgs = new gnmail.facade.MailCfgFacade().find("select "+gnmail.vo.MailCfg.SELF_FIELDS+" from MailCfg",gnmail.vo.MailCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(mail != null && mail.getMailId() != null) {
				//mail = new MailFacade().findById(mail);
				//setJson(JSON.toJSONString(mail)); 
			//}
			mailCfgs = new gnmail.facade.MailCfgFacade().find("select "+gnmail.vo.MailCfg.SELF_FIELDS+" from MailCfg",gnmail.vo.MailCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(mail != null && mail.getMailId() != null) {
				mail = new MailFacade().findById(mail);
				setJson(JSON.toJSONString(mail)); 
			}
			mailCfgs = new gnmail.facade.MailCfgFacade().find("select "+gnmail.vo.MailCfg.SELF_FIELDS+" from MailCfg",gnmail.vo.MailCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(mail != null && mail.getMailId() != null) {
				mail = new MailFacade().findById(mail);
				setJson(JSON.toJSONString(mail)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(mail == null) mail = new Mail();
			int total = new MailFacade().amount(mail);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			mails = new MailFacade().find(mail,getPageVO());
			MailJson mailJson = new MailJson();
			mailJson.Rows = mails;
			mailJson.Total = total;
			setJson(JSON.toJSONString(mailJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//mail.setCreateBy(getSession().getUserId());
			//mail.setCreateDate(new Date());
			//mail.setLastUpd(getSession().getUserId());
			//mail.setLastUpdDate(new Date());
			mail.setMailAutoMs(mailAutoMs);
			mail.setMailDocs(mailDocs);

			if(mail.getMailId() == null)
				new MailFacade().save(mail);
			else
				new MailFacade().update(mail);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("MailAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//mail.setCreateBy(getSession().getUserId());
			//mail.setCreateDate(new Date());
			//mail.setLastUpd(getSession().getUserId());
			//mail.setLastUpdDate(new Date());
			new MailFacade().update(mail);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("MailAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(mails != null && mails.size() > 0) {
				for(int i=0; i<mails.size();i++) {
					if(mails.get(i) != null) {
						//mails.get(i).setLastUpd(getSession().getUserId());
						//mails.get(i).setLastUpdDate(new Date());
						new MailFacade().submit(mails.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("MailAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(mails != null && mails.size() > 0) {
				for(int i=0; i<mails.size();i++) {
					if(mails.get(i) != null) {
						//mails.get(i).setLastUpd(getSession().getUserId());
						//mails.get(i).setLastUpdDate(new Date());
						new MailFacade().submit(mails.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("MailAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(mails != null && mails.size() > 0) {
				for(int i=0; i<mails.size();i++) {
					if(mails.get(i) != null) {
						//mails.get(i).setLastUpd(getSession().getUserId());
						//mails.get(i).setLastUpdDate(new Date());
						new MailFacade().update(mails.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("MailAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(mails != null && mails.size() > 0) {
				for(int i=0; i<mails.size();i++) {
					if(mails.get(i) != null) {
						//mails.get(i).setLastUpd(getSession().getUserId());
						//mails.get(i).setLastUpdDate(new Date());
						new MailFacade().submit(mails.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("MailAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(mails != null && mails.size() > 0) {
				for(int i=0; i<mails.size();i++) {
					if(mails.get(i) != null) {
						//mails.get(i).setLastUpd(getSession().getUserId());
						//mails.get(i).setLastUpdDate(new Date());
						new MailFacade().submit(mails.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("MailAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(mails != null && mails.size() > 0) {
				for(int i=0; i<mails.size();i++) {
					if(mails.get(i) != null) {
						//mails.get(i).setLastUpd(getSession().getUserId());
						//mails.get(i).setLastUpdDate(new Date());
						new MailFacade().submit(mails.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("MailAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(mails != null && mails.size() > 0) {
				for(int i=0; i<mails.size();i++) {
					if(mails.get(i) != null) {
						//mails.get(i).setLastUpd(getSession().getUserId());
						//mails.get(i).setLastUpdDate(new Date());
						new MailFacade().submit(mails.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("MailAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(mails != null && mails.size() > 0) {
				for(int i=0; i<mails.size();i++) {
					if(mails.get(i) != null){
						//mails.get(i).setLastUpd(getSession().getUserId());
						//mails.get(i).setLastUpdDate(new Date());
						new MailFacade().submit(mails.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("MailAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(mails != null && mails.size() > 0) {
				for(int i=0; i<mails.size();i++) {
					if(mails.get(i) != null){
						//mails.get(i).setLastUpd(getSession().getUserId());
						//mails.get(i).setLastUpdDate(new Date());
						new MailFacade().review(mails.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("MailAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(mails != null && mails.size() > 0) {
				for(int i=0; i<mails.size();i++) {
					if(mails.get(i) != null) {
						//mails.get(i).setLastUpd(getSession().getUserId());
						//mails.get(i).setLastUpdDate(new Date());
						new MailFacade().review(mails.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("MailAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(mails != null && mails.size() > 0) {
				for(int i=0; i<mails.size();i++) {
					if(mails.get(i) != null) {
						//mails.get(i).setLastUpd(getSession().getUserId());
						//mails.get(i).setLastUpdDate(new Date());
						new MailFacade().confirm(mails.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("MailAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(mails != null && mails.size() > 0) {
				for(int i=0; i<mails.size();i++) {
					if(mails.get(i) != null) {
						//mails.get(i).setLastUpd(getSession().getUserId());
						//mails.get(i).setLastUpdDate(new Date());
						new MailFacade().confirm(mails.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("MailAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(mails != null && mails.size() > 0) {
					for(int i=0; i<mails.size();i++) {
						if(mails.get(i) != null) {
							//mails.get(i).setLastUpd(getSession().getUserId());
							//mails.get(i).setLastUpdDate(new Date());
							new MailFacade().confirm(mails.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("MailAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<Mail> mails = new MailFacade().find(mail);
			if(mails != null && mails.size() > 0) {
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
					ws.addCell(new Label(index,1,"邮件服务ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"业务ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"发件人ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"发件人",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"收件人ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"收件人",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"主题",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"邮件内容",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"邮件时间",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"备注",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"状态",wcformat));
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


				int row = 2;
				for(int i=0; i<mails.size();i++) {
					row++;
					int m = 0;
					if(mails.get(i).getMailId() != null) 
						ws.addCell(new jxl.write.Number(m,row,mails.get(i).getMailId(),wcformat));
					m++;
					if(mails.get(i).getCfgId() != null) 
						ws.addCell(new jxl.write.Number(m,row,mails.get(i).getCfgId(),wcformat));
					m++;
					if(mails.get(i).getOexId() != null) 
						ws.addCell(new jxl.write.Number(m,row,mails.get(i).getOexId(),wcformat));
					m++;
					if(mails.get(i).getSenderId() != null) 
						ws.addCell(new jxl.write.Number(m,row,mails.get(i).getSenderId(),wcformat));
					m++;
					if(mails.get(i).getSender() != null) 
						ws.addCell(new jxl.write.Label(m,row,mails.get(i).getSender(),wcformat));
					m++;
					if(mails.get(i).getAcceptId() != null) 
						ws.addCell(new jxl.write.Number(m,row,mails.get(i).getAcceptId(),wcformat));
					m++;
					if(mails.get(i).getAccept() != null) 
						ws.addCell(new jxl.write.Label(m,row,mails.get(i).getAccept(),wcformat));
					m++;
					if(mails.get(i).getTitle() != null) 
						ws.addCell(new jxl.write.Label(m,row,mails.get(i).getTitle(),wcformat));
					m++;
					if(mails.get(i).getMailText() != null) 
						ws.addCell(new jxl.write.Label(m,row,mails.get(i).getMailText(),wcformat));
					m++;
					if(mails.get(i).getMailDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,mails.get(i).getMailDate(),wcformat));
					m++;
					if(mails.get(i).getRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,mails.get(i).getRemark(),wcformat));
					m++;
					if(mails.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,mails.get(i).getStatus(),wcformat));
					m++;
					if(mails.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,mails.get(i).getCreateBy(),wcformat));
					m++;
					if(mails.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,mails.get(i).getCreateDate(),wcformat));
					m++;
					if(mails.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,mails.get(i).getLastUpd(),wcformat));
					m++;
					if(mails.get(i).getLastUpdDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,mails.get(i).getLastUpdDate(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("MailListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(mails != null && mails.size() > 0) {
				for(int i=0; i<mails.size();i++) {
					if(mails.get(i) != null) {
						//mails.get(i).setLastUpd(getSession().getUserId());
						//mails.get(i).setLastUpdDate(new Date());
						new MailFacade().confirm(mails.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("MailAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(mails != null && mails.size() > 0) {
				for(int i=0; i<mails.size();i++) {
					if(mails.get(i) != null) {
						//mails.get(i).setLastUpd(getSession().getUserId());
						//mails.get(i).setLastUpdDate(new Date());
						new MailFacade().confirm(mails.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("MailAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<Mail> getMails() {
		return mails;
	}
	public void setMails(List<Mail> mails) {
		this.mails = mails;
	}
	public Mail getMail() {
		return mail;
	}
	public void setMail(Mail mail) {
		this.mail = mail;
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
	public java.util.List<gnmail.vo.MailAutoM> getMailAutoMs() {
		return mailAutoMs;
	}
	public void setMailAutoMs(java.util.List<gnmail.vo.MailAutoM> mailAutoMs){
		this.mailAutoMs = mailAutoMs;
	}
	public void addtoMailAutoMs(gnmail.vo.MailAutoM mailAutoM){
		if(getMailAutoMs() == null) setMailAutoMs(new java.util.ArrayList<gnmail.vo.MailAutoM>());
			getMailAutoMs().add(mailAutoM);
	}
	public java.util.List<gnmail.vo.MailDoc> getMailDocs() {
		return mailDocs;
	}
	public void setMailDocs(java.util.List<gnmail.vo.MailDoc> mailDocs){
		this.mailDocs = mailDocs;
	}
	public void addtoMailDocs(gnmail.vo.MailDoc mailDoc){
		if(getMailDocs() == null) setMailDocs(new java.util.ArrayList<gnmail.vo.MailDoc>());
			getMailDocs().add(mailDoc);
	}
	public java.util.List<gnmail.vo.MailCfg> getMailCfgs() {
		return mailCfgs;
	}
	public void setMailCfgs(java.util.List<gnmail.vo.MailCfg> mailCfgs){
		this.mailCfgs = mailCfgs;
	}
	public void addtoMailCfgs(gnmail.vo.MailCfg mailCfg){
		if(getMailCfgs() == null) setMailCfgs(new java.util.ArrayList<gnmail.vo.MailCfg>());
			getMailCfgs().add(mailCfg);
	}

}