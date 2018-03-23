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

import gnmail.facade.MailAutoMFacade;
import gnmail.vo.MailAutoM;
import gnmail.vo.json.MailAutoMJson;

public class MailAutoMAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<MailAutoM> mailAutoMs;
	private MailAutoM mailAutoM = new MailAutoM();
	private java.util.List<gnmail.vo.Mail> mails;

	public String execute() throws Exception {
		try {
			if(mailAutoM != null && mailAutoM.getMailId() != null) {
				mailAutoM = new MailAutoMFacade().findById(mailAutoM);
				setJson(JSON.toJSONString(mailAutoM)); 
			}
			mails = new gnmail.facade.MailFacade().find("select "+gnmail.vo.Mail.SELF_FIELDS+" from Mail",gnmail.vo.Mail.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailAutoMAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(mailAutoM != null && mailAutoM.getMailId() != null) {
				//mailAutoM = new MailAutoMFacade().findById(mailAutoM);
				//setJson(JSON.toJSONString(mailAutoM)); 
			//}
			mails = new gnmail.facade.MailFacade().find("select "+gnmail.vo.Mail.SELF_FIELDS+" from Mail",gnmail.vo.Mail.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailAutoMAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(mailAutoM != null && mailAutoM.getMailId() != null) {
				mailAutoM = new MailAutoMFacade().findById(mailAutoM);
				setJson(JSON.toJSONString(mailAutoM)); 
			}
			mails = new gnmail.facade.MailFacade().find("select "+gnmail.vo.Mail.SELF_FIELDS+" from Mail",gnmail.vo.Mail.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailAutoMAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(mailAutoM != null && mailAutoM.getMailId() != null) {
				mailAutoM = new MailAutoMFacade().findById(mailAutoM);
				setJson(JSON.toJSONString(mailAutoM)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailAutoMAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(mailAutoM == null) mailAutoM = new MailAutoM();
			int total = new MailAutoMFacade().amount(mailAutoM);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			mailAutoMs = new MailAutoMFacade().find(mailAutoM,getPageVO());
			MailAutoMJson mailAutoMJson = new MailAutoMJson();
			mailAutoMJson.Rows = mailAutoMs;
			mailAutoMJson.Total = total;
			setJson(JSON.toJSONString(mailAutoMJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailAutoMAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//mailAutoM.setCreateBy(getSession().getUserId());
			//mailAutoM.setCreateDate(new Date());
			//mailAutoM.setLastUpd(getSession().getUserId());
			//mailAutoM.setLastUpdDate(new Date());

			if(mailAutoM.getMailId() == null)
				new MailAutoMFacade().save(mailAutoM);
			else
				new MailAutoMFacade().update(mailAutoM);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("MailAutoMAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//mailAutoM.setCreateBy(getSession().getUserId());
			//mailAutoM.setCreateDate(new Date());
			//mailAutoM.setLastUpd(getSession().getUserId());
			//mailAutoM.setLastUpdDate(new Date());
			new MailAutoMFacade().update(mailAutoM);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("MailAutoMAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(mailAutoMs != null && mailAutoMs.size() > 0) {
				for(int i=0; i<mailAutoMs.size();i++) {
					if(mailAutoMs.get(i) != null) {
						//mailAutoMs.get(i).setLastUpd(getSession().getUserId());
						//mailAutoMs.get(i).setLastUpdDate(new Date());
						new MailAutoMFacade().submit(mailAutoMs.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("MailAutoMAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(mailAutoMs != null && mailAutoMs.size() > 0) {
				for(int i=0; i<mailAutoMs.size();i++) {
					if(mailAutoMs.get(i) != null) {
						//mailAutoMs.get(i).setLastUpd(getSession().getUserId());
						//mailAutoMs.get(i).setLastUpdDate(new Date());
						new MailAutoMFacade().submit(mailAutoMs.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("MailAutoMAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(mailAutoMs != null && mailAutoMs.size() > 0) {
				for(int i=0; i<mailAutoMs.size();i++) {
					if(mailAutoMs.get(i) != null) {
						//mailAutoMs.get(i).setLastUpd(getSession().getUserId());
						//mailAutoMs.get(i).setLastUpdDate(new Date());
						new MailAutoMFacade().update(mailAutoMs.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("MailAutoMAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(mailAutoMs != null && mailAutoMs.size() > 0) {
				for(int i=0; i<mailAutoMs.size();i++) {
					if(mailAutoMs.get(i) != null) {
						//mailAutoMs.get(i).setLastUpd(getSession().getUserId());
						//mailAutoMs.get(i).setLastUpdDate(new Date());
						new MailAutoMFacade().submit(mailAutoMs.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("MailAutoMAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(mailAutoMs != null && mailAutoMs.size() > 0) {
				for(int i=0; i<mailAutoMs.size();i++) {
					if(mailAutoMs.get(i) != null) {
						//mailAutoMs.get(i).setLastUpd(getSession().getUserId());
						//mailAutoMs.get(i).setLastUpdDate(new Date());
						new MailAutoMFacade().submit(mailAutoMs.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("MailAutoMAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(mailAutoMs != null && mailAutoMs.size() > 0) {
				for(int i=0; i<mailAutoMs.size();i++) {
					if(mailAutoMs.get(i) != null) {
						//mailAutoMs.get(i).setLastUpd(getSession().getUserId());
						//mailAutoMs.get(i).setLastUpdDate(new Date());
						new MailAutoMFacade().submit(mailAutoMs.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("MailAutoMAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(mailAutoMs != null && mailAutoMs.size() > 0) {
				for(int i=0; i<mailAutoMs.size();i++) {
					if(mailAutoMs.get(i) != null) {
						//mailAutoMs.get(i).setLastUpd(getSession().getUserId());
						//mailAutoMs.get(i).setLastUpdDate(new Date());
						new MailAutoMFacade().submit(mailAutoMs.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("MailAutoMAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(mailAutoMs != null && mailAutoMs.size() > 0) {
				for(int i=0; i<mailAutoMs.size();i++) {
					if(mailAutoMs.get(i) != null){
						//mailAutoMs.get(i).setLastUpd(getSession().getUserId());
						//mailAutoMs.get(i).setLastUpdDate(new Date());
						new MailAutoMFacade().submit(mailAutoMs.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("MailAutoMAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(mailAutoMs != null && mailAutoMs.size() > 0) {
				for(int i=0; i<mailAutoMs.size();i++) {
					if(mailAutoMs.get(i) != null){
						//mailAutoMs.get(i).setLastUpd(getSession().getUserId());
						//mailAutoMs.get(i).setLastUpdDate(new Date());
						new MailAutoMFacade().review(mailAutoMs.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("MailAutoMAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(mailAutoMs != null && mailAutoMs.size() > 0) {
				for(int i=0; i<mailAutoMs.size();i++) {
					if(mailAutoMs.get(i) != null) {
						//mailAutoMs.get(i).setLastUpd(getSession().getUserId());
						//mailAutoMs.get(i).setLastUpdDate(new Date());
						new MailAutoMFacade().review(mailAutoMs.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("MailAutoMAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(mailAutoMs != null && mailAutoMs.size() > 0) {
				for(int i=0; i<mailAutoMs.size();i++) {
					if(mailAutoMs.get(i) != null) {
						//mailAutoMs.get(i).setLastUpd(getSession().getUserId());
						//mailAutoMs.get(i).setLastUpdDate(new Date());
						new MailAutoMFacade().confirm(mailAutoMs.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("MailAutoMAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(mailAutoMs != null && mailAutoMs.size() > 0) {
				for(int i=0; i<mailAutoMs.size();i++) {
					if(mailAutoMs.get(i) != null) {
						//mailAutoMs.get(i).setLastUpd(getSession().getUserId());
						//mailAutoMs.get(i).setLastUpdDate(new Date());
						new MailAutoMFacade().confirm(mailAutoMs.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("MailAutoMAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(mailAutoMs != null && mailAutoMs.size() > 0) {
					for(int i=0; i<mailAutoMs.size();i++) {
						if(mailAutoMs.get(i) != null) {
							//mailAutoMs.get(i).setLastUpd(getSession().getUserId());
							//mailAutoMs.get(i).setLastUpdDate(new Date());
							new MailAutoMFacade().confirm(mailAutoMs.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("MailAutoMAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<MailAutoM> mailAutoMs = new MailAutoMFacade().find(mailAutoM);
			if(mailAutoMs != null && mailAutoMs.size() > 0) {
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
					ws.addCell(new Label(index,1,"工作流记录编号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"工作任务ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"创建人ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"创建时间",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"接收人",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"接收时间",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"标题",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"邮件内容",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"任务连接URI",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"状态",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"备注",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<mailAutoMs.size();i++) {
					row++;
					int m = 0;
					if(mailAutoMs.get(i).getMailId() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailAutoMs.get(i).getMailId(),wcformat));
					m++;
					if(mailAutoMs.get(i).getWfNO() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailAutoMs.get(i).getWfNO(),wcformat));
					m++;
					if(mailAutoMs.get(i).getTaskId() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailAutoMs.get(i).getTaskId(),wcformat));
					m++;
					if(mailAutoMs.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailAutoMs.get(i).getCreateBy(),wcformat));
					m++;
					if(mailAutoMs.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,mailAutoMs.get(i).getCreateDate(),wcformat));
					m++;
					if(mailAutoMs.get(i).getAcceptBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailAutoMs.get(i).getAcceptBy(),wcformat));
					m++;
					if(mailAutoMs.get(i).getAcceptDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,mailAutoMs.get(i).getAcceptDate(),wcformat));
					m++;
					if(mailAutoMs.get(i).getTitle() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailAutoMs.get(i).getTitle(),wcformat));
					m++;
					if(mailAutoMs.get(i).getMailText() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailAutoMs.get(i).getMailText(),wcformat));
					m++;
					if(mailAutoMs.get(i).getTaskUri() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailAutoMs.get(i).getTaskUri(),wcformat));
					m++;
					if(mailAutoMs.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailAutoMs.get(i).getStatus(),wcformat));
					m++;
					if(mailAutoMs.get(i).getRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailAutoMs.get(i).getRemark(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("MailAutoMListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(mailAutoMs != null && mailAutoMs.size() > 0) {
				for(int i=0; i<mailAutoMs.size();i++) {
					if(mailAutoMs.get(i) != null) {
						//mailAutoMs.get(i).setLastUpd(getSession().getUserId());
						//mailAutoMs.get(i).setLastUpdDate(new Date());
						new MailAutoMFacade().confirm(mailAutoMs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("MailAutoMAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(mailAutoMs != null && mailAutoMs.size() > 0) {
				for(int i=0; i<mailAutoMs.size();i++) {
					if(mailAutoMs.get(i) != null) {
						//mailAutoMs.get(i).setLastUpd(getSession().getUserId());
						//mailAutoMs.get(i).setLastUpdDate(new Date());
						new MailAutoMFacade().confirm(mailAutoMs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("MailAutoMAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<MailAutoM> getMailAutoMs() {
		return mailAutoMs;
	}
	public void setMailAutoMs(List<MailAutoM> mailAutoMs) {
		this.mailAutoMs = mailAutoMs;
	}
	public MailAutoM getMailAutoM() {
		return mailAutoM;
	}
	public void setMailAutoM(MailAutoM mailAutoM) {
		this.mailAutoM = mailAutoM;
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