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

import gnmail.facade.MailCfgFacade;
import gnmail.vo.MailCfg;
import gnmail.vo.json.MailCfgJson;

public class MailCfgAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<MailCfg> mailCfgs;
	private MailCfg mailCfg = new MailCfg();
	private java.util.List<gnmail.vo.Mail> mails;
	private java.util.List<gnmail.vo.MailTmpl> mailTmpls;

	public String execute() throws Exception {
		try {
			if(mailCfg != null && mailCfg.getCfgId() != null) {
				mailCfg = new MailCfgFacade().findById(mailCfg);
				setJson(JSON.toJSONString(mailCfg)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailCfgAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(mailCfg != null && mailCfg.getCfgId() != null) {
				//mailCfg = new MailCfgFacade().findById(mailCfg);
				//setJson(JSON.toJSONString(mailCfg)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailCfgAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(mailCfg != null && mailCfg.getCfgId() != null) {
				mailCfg = new MailCfgFacade().findById(mailCfg);
				setJson(JSON.toJSONString(mailCfg)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailCfgAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(mailCfg != null && mailCfg.getCfgId() != null) {
				mailCfg = new MailCfgFacade().findById(mailCfg);
				setJson(JSON.toJSONString(mailCfg)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailCfgAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(mailCfg == null) mailCfg = new MailCfg();
			int total = new MailCfgFacade().amount(mailCfg);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			mailCfgs = new MailCfgFacade().find(mailCfg,getPageVO());
			MailCfgJson mailCfgJson = new MailCfgJson();
			mailCfgJson.Rows = mailCfgs;
			mailCfgJson.Total = total;
			setJson(JSON.toJSONString(mailCfgJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailCfgAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//mailCfg.setCreateBy(getSession().getUserId());
			//mailCfg.setCreateDate(new Date());
			//mailCfg.setLastUpd(getSession().getUserId());
			//mailCfg.setLastUpdDate(new Date());
			mailCfg.setMails(mails);
			mailCfg.setMailTmpls(mailTmpls);

			if(mailCfg.getCfgId() == null)
				new MailCfgFacade().save(mailCfg);
			else
				new MailCfgFacade().update(mailCfg);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("MailCfgAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//mailCfg.setCreateBy(getSession().getUserId());
			//mailCfg.setCreateDate(new Date());
			//mailCfg.setLastUpd(getSession().getUserId());
			//mailCfg.setLastUpdDate(new Date());
			new MailCfgFacade().update(mailCfg);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("MailCfgAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(mailCfgs != null && mailCfgs.size() > 0) {
				for(int i=0; i<mailCfgs.size();i++) {
					if(mailCfgs.get(i) != null) {
						//mailCfgs.get(i).setLastUpd(getSession().getUserId());
						//mailCfgs.get(i).setLastUpdDate(new Date());
						new MailCfgFacade().submit(mailCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("MailCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(mailCfgs != null && mailCfgs.size() > 0) {
				for(int i=0; i<mailCfgs.size();i++) {
					if(mailCfgs.get(i) != null) {
						//mailCfgs.get(i).setLastUpd(getSession().getUserId());
						//mailCfgs.get(i).setLastUpdDate(new Date());
						new MailCfgFacade().submit(mailCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("MailCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(mailCfgs != null && mailCfgs.size() > 0) {
				for(int i=0; i<mailCfgs.size();i++) {
					if(mailCfgs.get(i) != null) {
						//mailCfgs.get(i).setLastUpd(getSession().getUserId());
						//mailCfgs.get(i).setLastUpdDate(new Date());
						new MailCfgFacade().update(mailCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("MailCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(mailCfgs != null && mailCfgs.size() > 0) {
				for(int i=0; i<mailCfgs.size();i++) {
					if(mailCfgs.get(i) != null) {
						//mailCfgs.get(i).setLastUpd(getSession().getUserId());
						//mailCfgs.get(i).setLastUpdDate(new Date());
						new MailCfgFacade().submit(mailCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("MailCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(mailCfgs != null && mailCfgs.size() > 0) {
				for(int i=0; i<mailCfgs.size();i++) {
					if(mailCfgs.get(i) != null) {
						//mailCfgs.get(i).setLastUpd(getSession().getUserId());
						//mailCfgs.get(i).setLastUpdDate(new Date());
						new MailCfgFacade().submit(mailCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("MailCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(mailCfgs != null && mailCfgs.size() > 0) {
				for(int i=0; i<mailCfgs.size();i++) {
					if(mailCfgs.get(i) != null) {
						//mailCfgs.get(i).setLastUpd(getSession().getUserId());
						//mailCfgs.get(i).setLastUpdDate(new Date());
						new MailCfgFacade().submit(mailCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("MailCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(mailCfgs != null && mailCfgs.size() > 0) {
				for(int i=0; i<mailCfgs.size();i++) {
					if(mailCfgs.get(i) != null) {
						//mailCfgs.get(i).setLastUpd(getSession().getUserId());
						//mailCfgs.get(i).setLastUpdDate(new Date());
						new MailCfgFacade().submit(mailCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("MailCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(mailCfgs != null && mailCfgs.size() > 0) {
				for(int i=0; i<mailCfgs.size();i++) {
					if(mailCfgs.get(i) != null){
						//mailCfgs.get(i).setLastUpd(getSession().getUserId());
						//mailCfgs.get(i).setLastUpdDate(new Date());
						new MailCfgFacade().submit(mailCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("MailCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(mailCfgs != null && mailCfgs.size() > 0) {
				for(int i=0; i<mailCfgs.size();i++) {
					if(mailCfgs.get(i) != null){
						//mailCfgs.get(i).setLastUpd(getSession().getUserId());
						//mailCfgs.get(i).setLastUpdDate(new Date());
						new MailCfgFacade().review(mailCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("MailCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(mailCfgs != null && mailCfgs.size() > 0) {
				for(int i=0; i<mailCfgs.size();i++) {
					if(mailCfgs.get(i) != null) {
						//mailCfgs.get(i).setLastUpd(getSession().getUserId());
						//mailCfgs.get(i).setLastUpdDate(new Date());
						new MailCfgFacade().review(mailCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("MailCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(mailCfgs != null && mailCfgs.size() > 0) {
				for(int i=0; i<mailCfgs.size();i++) {
					if(mailCfgs.get(i) != null) {
						//mailCfgs.get(i).setLastUpd(getSession().getUserId());
						//mailCfgs.get(i).setLastUpdDate(new Date());
						new MailCfgFacade().confirm(mailCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("MailCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(mailCfgs != null && mailCfgs.size() > 0) {
				for(int i=0; i<mailCfgs.size();i++) {
					if(mailCfgs.get(i) != null) {
						//mailCfgs.get(i).setLastUpd(getSession().getUserId());
						//mailCfgs.get(i).setLastUpdDate(new Date());
						new MailCfgFacade().confirm(mailCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("MailCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(mailCfgs != null && mailCfgs.size() > 0) {
					for(int i=0; i<mailCfgs.size();i++) {
						if(mailCfgs.get(i) != null) {
							//mailCfgs.get(i).setLastUpd(getSession().getUserId());
							//mailCfgs.get(i).setLastUpdDate(new Date());
							new MailCfgFacade().confirm(mailCfgs.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("MailCfgAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<MailCfg> mailCfgs = new MailCfgFacade().find(mailCfg);
			if(mailCfgs != null && mailCfgs.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"邮件配置",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"用户ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"邮箱地址",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"密码",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"名称",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"签名",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"服务器地址",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"Smt端口",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"POP3端口",wcformat));
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
				for(int i=0; i<mailCfgs.size();i++) {
					row++;
					int m = 0;
					if(mailCfgs.get(i).getCfgId() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailCfgs.get(i).getCfgId(),wcformat));
					m++;
					if(mailCfgs.get(i).getUserId() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailCfgs.get(i).getUserId(),wcformat));
					m++;
					if(mailCfgs.get(i).getMailAddr() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailCfgs.get(i).getMailAddr(),wcformat));
					m++;
					if(mailCfgs.get(i).getPwd() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailCfgs.get(i).getPwd(),wcformat));
					m++;
					if(mailCfgs.get(i).getMailName() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailCfgs.get(i).getMailName(),wcformat));
					m++;
					if(mailCfgs.get(i).getMailSign() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailCfgs.get(i).getMailSign(),wcformat));
					m++;
					if(mailCfgs.get(i).getServIpAddr() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailCfgs.get(i).getServIpAddr(),wcformat));
					m++;
					if(mailCfgs.get(i).getSmpt() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailCfgs.get(i).getSmpt(),wcformat));
					m++;
					if(mailCfgs.get(i).getPop3() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailCfgs.get(i).getPop3(),wcformat));
					m++;
					if(mailCfgs.get(i).getRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailCfgs.get(i).getRemark(),wcformat));
					m++;
					if(mailCfgs.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailCfgs.get(i).getStatus(),wcformat));
					m++;
					if(mailCfgs.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailCfgs.get(i).getCreateBy(),wcformat));
					m++;
					if(mailCfgs.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,mailCfgs.get(i).getCreateDate(),wcformat));
					m++;
					if(mailCfgs.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailCfgs.get(i).getLastUpd(),wcformat));
					m++;
					if(mailCfgs.get(i).getLastUpdDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,mailCfgs.get(i).getLastUpdDate(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("MailCfgListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(mailCfgs != null && mailCfgs.size() > 0) {
				for(int i=0; i<mailCfgs.size();i++) {
					if(mailCfgs.get(i) != null) {
						//mailCfgs.get(i).setLastUpd(getSession().getUserId());
						//mailCfgs.get(i).setLastUpdDate(new Date());
						new MailCfgFacade().confirm(mailCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("MailCfgAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(mailCfgs != null && mailCfgs.size() > 0) {
				for(int i=0; i<mailCfgs.size();i++) {
					if(mailCfgs.get(i) != null) {
						//mailCfgs.get(i).setLastUpd(getSession().getUserId());
						//mailCfgs.get(i).setLastUpdDate(new Date());
						new MailCfgFacade().confirm(mailCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("MailCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<MailCfg> getMailCfgs() {
		return mailCfgs;
	}
	public void setMailCfgs(List<MailCfg> mailCfgs) {
		this.mailCfgs = mailCfgs;
	}
	public MailCfg getMailCfg() {
		return mailCfg;
	}
	public void setMailCfg(MailCfg mailCfg) {
		this.mailCfg = mailCfg;
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
	public java.util.List<gnmail.vo.MailTmpl> getMailTmpls() {
		return mailTmpls;
	}
	public void setMailTmpls(java.util.List<gnmail.vo.MailTmpl> mailTmpls){
		this.mailTmpls = mailTmpls;
	}
	public void addtoMailTmpls(gnmail.vo.MailTmpl mailTmpl){
		if(getMailTmpls() == null) setMailTmpls(new java.util.ArrayList<gnmail.vo.MailTmpl>());
			getMailTmpls().add(mailTmpl);
	}

}