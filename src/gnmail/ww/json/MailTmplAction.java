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

import gnmail.facade.MailTmplFacade;
import gnmail.vo.MailTmpl;
import gnmail.vo.json.MailTmplJson;

public class MailTmplAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<MailTmpl> mailTmpls;
	private MailTmpl mailTmpl = new MailTmpl();
	private java.util.List<gnmail.vo.MailTmplG> mailTmplGs;
	private java.util.List<gnmail.vo.MailCfg> mailCfgs;

	public String execute() throws Exception {
		try {
			if(mailTmpl != null && mailTmpl.getTmplId() != null) {
				mailTmpl = new MailTmplFacade().findById(mailTmpl);
				setJson(JSON.toJSONString(mailTmpl)); 
			}
			mailCfgs = new gnmail.facade.MailCfgFacade().find("select "+gnmail.vo.MailCfg.SELF_FIELDS+" from MailCfg",gnmail.vo.MailCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailTmplAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(mailTmpl != null && mailTmpl.getTmplId() != null) {
				//mailTmpl = new MailTmplFacade().findById(mailTmpl);
				//setJson(JSON.toJSONString(mailTmpl)); 
			//}
			mailCfgs = new gnmail.facade.MailCfgFacade().find("select "+gnmail.vo.MailCfg.SELF_FIELDS+" from MailCfg",gnmail.vo.MailCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailTmplAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(mailTmpl != null && mailTmpl.getTmplId() != null) {
				mailTmpl = new MailTmplFacade().findById(mailTmpl);
				setJson(JSON.toJSONString(mailTmpl)); 
			}
			mailCfgs = new gnmail.facade.MailCfgFacade().find("select "+gnmail.vo.MailCfg.SELF_FIELDS+" from MailCfg",gnmail.vo.MailCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailTmplAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(mailTmpl != null && mailTmpl.getTmplId() != null) {
				mailTmpl = new MailTmplFacade().findById(mailTmpl);
				setJson(JSON.toJSONString(mailTmpl)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailTmplAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(mailTmpl == null) mailTmpl = new MailTmpl();
			int total = new MailTmplFacade().amount(mailTmpl);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			mailTmpls = new MailTmplFacade().find(mailTmpl,getPageVO());
			MailTmplJson mailTmplJson = new MailTmplJson();
			mailTmplJson.Rows = mailTmpls;
			mailTmplJson.Total = total;
			setJson(JSON.toJSONString(mailTmplJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailTmplAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//mailTmpl.setCreateBy(getSession().getUserId());
			//mailTmpl.setCreateDate(new Date());
			//mailTmpl.setLastUpd(getSession().getUserId());
			//mailTmpl.setLastUpdDate(new Date());
			mailTmpl.setMailTmplGs(mailTmplGs);

			if(mailTmpl.getTmplId() == null)
				new MailTmplFacade().save(mailTmpl);
			else
				new MailTmplFacade().update(mailTmpl);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("MailTmplAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//mailTmpl.setCreateBy(getSession().getUserId());
			//mailTmpl.setCreateDate(new Date());
			//mailTmpl.setLastUpd(getSession().getUserId());
			//mailTmpl.setLastUpdDate(new Date());
			new MailTmplFacade().update(mailTmpl);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("MailTmplAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(mailTmpls != null && mailTmpls.size() > 0) {
				for(int i=0; i<mailTmpls.size();i++) {
					if(mailTmpls.get(i) != null) {
						//mailTmpls.get(i).setLastUpd(getSession().getUserId());
						//mailTmpls.get(i).setLastUpdDate(new Date());
						new MailTmplFacade().submit(mailTmpls.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("MailTmplAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(mailTmpls != null && mailTmpls.size() > 0) {
				for(int i=0; i<mailTmpls.size();i++) {
					if(mailTmpls.get(i) != null) {
						//mailTmpls.get(i).setLastUpd(getSession().getUserId());
						//mailTmpls.get(i).setLastUpdDate(new Date());
						new MailTmplFacade().submit(mailTmpls.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("MailTmplAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(mailTmpls != null && mailTmpls.size() > 0) {
				for(int i=0; i<mailTmpls.size();i++) {
					if(mailTmpls.get(i) != null) {
						//mailTmpls.get(i).setLastUpd(getSession().getUserId());
						//mailTmpls.get(i).setLastUpdDate(new Date());
						new MailTmplFacade().update(mailTmpls.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("MailTmplAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(mailTmpls != null && mailTmpls.size() > 0) {
				for(int i=0; i<mailTmpls.size();i++) {
					if(mailTmpls.get(i) != null) {
						//mailTmpls.get(i).setLastUpd(getSession().getUserId());
						//mailTmpls.get(i).setLastUpdDate(new Date());
						new MailTmplFacade().submit(mailTmpls.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("MailTmplAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(mailTmpls != null && mailTmpls.size() > 0) {
				for(int i=0; i<mailTmpls.size();i++) {
					if(mailTmpls.get(i) != null) {
						//mailTmpls.get(i).setLastUpd(getSession().getUserId());
						//mailTmpls.get(i).setLastUpdDate(new Date());
						new MailTmplFacade().submit(mailTmpls.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("MailTmplAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(mailTmpls != null && mailTmpls.size() > 0) {
				for(int i=0; i<mailTmpls.size();i++) {
					if(mailTmpls.get(i) != null) {
						//mailTmpls.get(i).setLastUpd(getSession().getUserId());
						//mailTmpls.get(i).setLastUpdDate(new Date());
						new MailTmplFacade().submit(mailTmpls.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("MailTmplAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(mailTmpls != null && mailTmpls.size() > 0) {
				for(int i=0; i<mailTmpls.size();i++) {
					if(mailTmpls.get(i) != null) {
						//mailTmpls.get(i).setLastUpd(getSession().getUserId());
						//mailTmpls.get(i).setLastUpdDate(new Date());
						new MailTmplFacade().submit(mailTmpls.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("MailTmplAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(mailTmpls != null && mailTmpls.size() > 0) {
				for(int i=0; i<mailTmpls.size();i++) {
					if(mailTmpls.get(i) != null){
						//mailTmpls.get(i).setLastUpd(getSession().getUserId());
						//mailTmpls.get(i).setLastUpdDate(new Date());
						new MailTmplFacade().submit(mailTmpls.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("MailTmplAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(mailTmpls != null && mailTmpls.size() > 0) {
				for(int i=0; i<mailTmpls.size();i++) {
					if(mailTmpls.get(i) != null){
						//mailTmpls.get(i).setLastUpd(getSession().getUserId());
						//mailTmpls.get(i).setLastUpdDate(new Date());
						new MailTmplFacade().review(mailTmpls.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("MailTmplAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(mailTmpls != null && mailTmpls.size() > 0) {
				for(int i=0; i<mailTmpls.size();i++) {
					if(mailTmpls.get(i) != null) {
						//mailTmpls.get(i).setLastUpd(getSession().getUserId());
						//mailTmpls.get(i).setLastUpdDate(new Date());
						new MailTmplFacade().review(mailTmpls.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("MailTmplAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(mailTmpls != null && mailTmpls.size() > 0) {
				for(int i=0; i<mailTmpls.size();i++) {
					if(mailTmpls.get(i) != null) {
						//mailTmpls.get(i).setLastUpd(getSession().getUserId());
						//mailTmpls.get(i).setLastUpdDate(new Date());
						new MailTmplFacade().confirm(mailTmpls.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("MailTmplAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(mailTmpls != null && mailTmpls.size() > 0) {
				for(int i=0; i<mailTmpls.size();i++) {
					if(mailTmpls.get(i) != null) {
						//mailTmpls.get(i).setLastUpd(getSession().getUserId());
						//mailTmpls.get(i).setLastUpdDate(new Date());
						new MailTmplFacade().confirm(mailTmpls.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("MailTmplAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(mailTmpls != null && mailTmpls.size() > 0) {
					for(int i=0; i<mailTmpls.size();i++) {
						if(mailTmpls.get(i) != null) {
							//mailTmpls.get(i).setLastUpd(getSession().getUserId());
							//mailTmpls.get(i).setLastUpdDate(new Date());
							new MailTmplFacade().confirm(mailTmpls.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("MailTmplAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<MailTmpl> mailTmpls = new MailTmplFacade().find(mailTmpl);
			if(mailTmpls != null && mailTmpls.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"模板代码",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"所有人",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"模板名称",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"内容",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"分类",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"是否自动发送",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"开始日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"周期",wcformat));
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
				for(int i=0; i<mailTmpls.size();i++) {
					row++;
					int m = 0;
					if(mailTmpls.get(i).getTmplId() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailTmpls.get(i).getTmplId(),wcformat));
					m++;
					if(mailTmpls.get(i).getCfgId() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailTmpls.get(i).getCfgId(),wcformat));
					m++;
					if(mailTmpls.get(i).getUserId() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailTmpls.get(i).getUserId(),wcformat));
					m++;
					if(mailTmpls.get(i).getTmplName() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailTmpls.get(i).getTmplName(),wcformat));
					m++;
					if(mailTmpls.get(i).getTmplText() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailTmpls.get(i).getTmplText(),wcformat));
					m++;
					if(mailTmpls.get(i).getType() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailTmpls.get(i).getType(),wcformat));
					m++;
					if(mailTmpls.get(i).getIsAuto() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailTmpls.get(i).getIsAuto(),wcformat));
					m++;
					if(mailTmpls.get(i).getAutoStart() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,mailTmpls.get(i).getAutoStart(),wcformat));
					m++;
					if(mailTmpls.get(i).getAutoCycle() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailTmpls.get(i).getAutoCycle(),wcformat));
					m++;
					if(mailTmpls.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailTmpls.get(i).getStatus(),wcformat));
					m++;
					if(mailTmpls.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailTmpls.get(i).getCreateBy(),wcformat));
					m++;
					if(mailTmpls.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,mailTmpls.get(i).getCreateDate(),wcformat));
					m++;
					if(mailTmpls.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailTmpls.get(i).getLastUpd(),wcformat));
					m++;
					if(mailTmpls.get(i).getLastUpdDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,mailTmpls.get(i).getLastUpdDate(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("MailTmplListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(mailTmpls != null && mailTmpls.size() > 0) {
				for(int i=0; i<mailTmpls.size();i++) {
					if(mailTmpls.get(i) != null) {
						//mailTmpls.get(i).setLastUpd(getSession().getUserId());
						//mailTmpls.get(i).setLastUpdDate(new Date());
						new MailTmplFacade().confirm(mailTmpls.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("MailTmplAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(mailTmpls != null && mailTmpls.size() > 0) {
				for(int i=0; i<mailTmpls.size();i++) {
					if(mailTmpls.get(i) != null) {
						//mailTmpls.get(i).setLastUpd(getSession().getUserId());
						//mailTmpls.get(i).setLastUpdDate(new Date());
						new MailTmplFacade().confirm(mailTmpls.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("MailTmplAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<MailTmpl> getMailTmpls() {
		return mailTmpls;
	}
	public void setMailTmpls(List<MailTmpl> mailTmpls) {
		this.mailTmpls = mailTmpls;
	}
	public MailTmpl getMailTmpl() {
		return mailTmpl;
	}
	public void setMailTmpl(MailTmpl mailTmpl) {
		this.mailTmpl = mailTmpl;
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
	public java.util.List<gnmail.vo.MailTmplG> getMailTmplGs() {
		return mailTmplGs;
	}
	public void setMailTmplGs(java.util.List<gnmail.vo.MailTmplG> mailTmplGs){
		this.mailTmplGs = mailTmplGs;
	}
	public void addtoMailTmplGs(gnmail.vo.MailTmplG mailTmplG){
		if(getMailTmplGs() == null) setMailTmplGs(new java.util.ArrayList<gnmail.vo.MailTmplG>());
			getMailTmplGs().add(mailTmplG);
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