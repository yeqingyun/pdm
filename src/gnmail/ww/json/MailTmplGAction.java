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

import gnmail.facade.MailTmplGFacade;
import gnmail.vo.MailTmplG;
import gnmail.vo.json.MailTmplGJson;

public class MailTmplGAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<MailTmplG> mailTmplGs;
	private MailTmplG mailTmplG = new MailTmplG();
	private java.util.List<gnmail.vo.MailGroup> mailGroups;
	private java.util.List<gnmail.vo.MailTmpl> mailTmpls;

	public String execute() throws Exception {
		try {
			if(mailTmplG != null && mailTmplG.getTmplId() != null) {
				mailTmplG = new MailTmplGFacade().findById(mailTmplG);
				setJson(JSON.toJSONString(mailTmplG)); 
			}
			mailGroups = new gnmail.facade.MailGroupFacade().find("select "+gnmail.vo.MailGroup.SELF_FIELDS+" from MailGroup",gnmail.vo.MailGroup.SELF_FIELDS);
			mailTmpls = new gnmail.facade.MailTmplFacade().find("select "+gnmail.vo.MailTmpl.SELF_FIELDS+" from MailTmpl",gnmail.vo.MailTmpl.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailTmplGAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(mailTmplG != null && mailTmplG.getTmplId() != null) {
				//mailTmplG = new MailTmplGFacade().findById(mailTmplG);
				//setJson(JSON.toJSONString(mailTmplG)); 
			//}
			mailGroups = new gnmail.facade.MailGroupFacade().find("select "+gnmail.vo.MailGroup.SELF_FIELDS+" from MailGroup",gnmail.vo.MailGroup.SELF_FIELDS);
			mailTmpls = new gnmail.facade.MailTmplFacade().find("select "+gnmail.vo.MailTmpl.SELF_FIELDS+" from MailTmpl",gnmail.vo.MailTmpl.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailTmplGAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(mailTmplG != null && mailTmplG.getTmplId() != null) {
				mailTmplG = new MailTmplGFacade().findById(mailTmplG);
				setJson(JSON.toJSONString(mailTmplG)); 
			}
			mailGroups = new gnmail.facade.MailGroupFacade().find("select "+gnmail.vo.MailGroup.SELF_FIELDS+" from MailGroup",gnmail.vo.MailGroup.SELF_FIELDS);
			mailTmpls = new gnmail.facade.MailTmplFacade().find("select "+gnmail.vo.MailTmpl.SELF_FIELDS+" from MailTmpl",gnmail.vo.MailTmpl.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailTmplGAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(mailTmplG != null && mailTmplG.getTmplId() != null) {
				mailTmplG = new MailTmplGFacade().findById(mailTmplG);
				setJson(JSON.toJSONString(mailTmplG)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailTmplGAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(mailTmplG == null) mailTmplG = new MailTmplG();
			int total = new MailTmplGFacade().amount(mailTmplG);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			mailTmplGs = new MailTmplGFacade().find(mailTmplG,getPageVO());
			MailTmplGJson mailTmplGJson = new MailTmplGJson();
			mailTmplGJson.Rows = mailTmplGs;
			mailTmplGJson.Total = total;
			setJson(JSON.toJSONString(mailTmplGJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailTmplGAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//mailTmplG.setCreateBy(getSession().getUserId());
			//mailTmplG.setCreateDate(new Date());
			//mailTmplG.setLastUpd(getSession().getUserId());
			//mailTmplG.setLastUpdDate(new Date());

			if(mailTmplG.getTmplId() == null)
				new MailTmplGFacade().save(mailTmplG);
			else
				new MailTmplGFacade().update(mailTmplG);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("MailTmplGAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//mailTmplG.setCreateBy(getSession().getUserId());
			//mailTmplG.setCreateDate(new Date());
			//mailTmplG.setLastUpd(getSession().getUserId());
			//mailTmplG.setLastUpdDate(new Date());
			new MailTmplGFacade().update(mailTmplG);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("MailTmplGAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(mailTmplGs != null && mailTmplGs.size() > 0) {
				for(int i=0; i<mailTmplGs.size();i++) {
					if(mailTmplGs.get(i) != null) {
						//mailTmplGs.get(i).setLastUpd(getSession().getUserId());
						//mailTmplGs.get(i).setLastUpdDate(new Date());
						new MailTmplGFacade().submit(mailTmplGs.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("MailTmplGAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(mailTmplGs != null && mailTmplGs.size() > 0) {
				for(int i=0; i<mailTmplGs.size();i++) {
					if(mailTmplGs.get(i) != null) {
						//mailTmplGs.get(i).setLastUpd(getSession().getUserId());
						//mailTmplGs.get(i).setLastUpdDate(new Date());
						new MailTmplGFacade().submit(mailTmplGs.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("MailTmplGAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(mailTmplGs != null && mailTmplGs.size() > 0) {
				for(int i=0; i<mailTmplGs.size();i++) {
					if(mailTmplGs.get(i) != null) {
						//mailTmplGs.get(i).setLastUpd(getSession().getUserId());
						//mailTmplGs.get(i).setLastUpdDate(new Date());
						new MailTmplGFacade().update(mailTmplGs.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("MailTmplGAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(mailTmplGs != null && mailTmplGs.size() > 0) {
				for(int i=0; i<mailTmplGs.size();i++) {
					if(mailTmplGs.get(i) != null) {
						//mailTmplGs.get(i).setLastUpd(getSession().getUserId());
						//mailTmplGs.get(i).setLastUpdDate(new Date());
						new MailTmplGFacade().submit(mailTmplGs.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("MailTmplGAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(mailTmplGs != null && mailTmplGs.size() > 0) {
				for(int i=0; i<mailTmplGs.size();i++) {
					if(mailTmplGs.get(i) != null) {
						//mailTmplGs.get(i).setLastUpd(getSession().getUserId());
						//mailTmplGs.get(i).setLastUpdDate(new Date());
						new MailTmplGFacade().submit(mailTmplGs.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("MailTmplGAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(mailTmplGs != null && mailTmplGs.size() > 0) {
				for(int i=0; i<mailTmplGs.size();i++) {
					if(mailTmplGs.get(i) != null) {
						//mailTmplGs.get(i).setLastUpd(getSession().getUserId());
						//mailTmplGs.get(i).setLastUpdDate(new Date());
						new MailTmplGFacade().submit(mailTmplGs.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("MailTmplGAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(mailTmplGs != null && mailTmplGs.size() > 0) {
				for(int i=0; i<mailTmplGs.size();i++) {
					if(mailTmplGs.get(i) != null) {
						//mailTmplGs.get(i).setLastUpd(getSession().getUserId());
						//mailTmplGs.get(i).setLastUpdDate(new Date());
						new MailTmplGFacade().submit(mailTmplGs.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("MailTmplGAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(mailTmplGs != null && mailTmplGs.size() > 0) {
				for(int i=0; i<mailTmplGs.size();i++) {
					if(mailTmplGs.get(i) != null){
						//mailTmplGs.get(i).setLastUpd(getSession().getUserId());
						//mailTmplGs.get(i).setLastUpdDate(new Date());
						new MailTmplGFacade().submit(mailTmplGs.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("MailTmplGAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(mailTmplGs != null && mailTmplGs.size() > 0) {
				for(int i=0; i<mailTmplGs.size();i++) {
					if(mailTmplGs.get(i) != null){
						//mailTmplGs.get(i).setLastUpd(getSession().getUserId());
						//mailTmplGs.get(i).setLastUpdDate(new Date());
						new MailTmplGFacade().review(mailTmplGs.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("MailTmplGAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(mailTmplGs != null && mailTmplGs.size() > 0) {
				for(int i=0; i<mailTmplGs.size();i++) {
					if(mailTmplGs.get(i) != null) {
						//mailTmplGs.get(i).setLastUpd(getSession().getUserId());
						//mailTmplGs.get(i).setLastUpdDate(new Date());
						new MailTmplGFacade().review(mailTmplGs.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("MailTmplGAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(mailTmplGs != null && mailTmplGs.size() > 0) {
				for(int i=0; i<mailTmplGs.size();i++) {
					if(mailTmplGs.get(i) != null) {
						//mailTmplGs.get(i).setLastUpd(getSession().getUserId());
						//mailTmplGs.get(i).setLastUpdDate(new Date());
						new MailTmplGFacade().confirm(mailTmplGs.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("MailTmplGAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(mailTmplGs != null && mailTmplGs.size() > 0) {
				for(int i=0; i<mailTmplGs.size();i++) {
					if(mailTmplGs.get(i) != null) {
						//mailTmplGs.get(i).setLastUpd(getSession().getUserId());
						//mailTmplGs.get(i).setLastUpdDate(new Date());
						new MailTmplGFacade().confirm(mailTmplGs.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("MailTmplGAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(mailTmplGs != null && mailTmplGs.size() > 0) {
					for(int i=0; i<mailTmplGs.size();i++) {
						if(mailTmplGs.get(i) != null) {
							//mailTmplGs.get(i).setLastUpd(getSession().getUserId());
							//mailTmplGs.get(i).setLastUpdDate(new Date());
							new MailTmplGFacade().confirm(mailTmplGs.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("MailTmplGAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<MailTmplG> mailTmplGs = new MailTmplGFacade().find(mailTmplG);
			if(mailTmplGs != null && mailTmplGs.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"模板ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"邮件组ID",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<mailTmplGs.size();i++) {
					row++;
					int m = 0;
					if(mailTmplGs.get(i).getTmplId() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailTmplGs.get(i).getTmplId(),wcformat));
					m++;
					if(mailTmplGs.get(i).getGroupId() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailTmplGs.get(i).getGroupId(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("MailTmplGListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(mailTmplGs != null && mailTmplGs.size() > 0) {
				for(int i=0; i<mailTmplGs.size();i++) {
					if(mailTmplGs.get(i) != null) {
						//mailTmplGs.get(i).setLastUpd(getSession().getUserId());
						//mailTmplGs.get(i).setLastUpdDate(new Date());
						new MailTmplGFacade().confirm(mailTmplGs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("MailTmplGAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(mailTmplGs != null && mailTmplGs.size() > 0) {
				for(int i=0; i<mailTmplGs.size();i++) {
					if(mailTmplGs.get(i) != null) {
						//mailTmplGs.get(i).setLastUpd(getSession().getUserId());
						//mailTmplGs.get(i).setLastUpdDate(new Date());
						new MailTmplGFacade().confirm(mailTmplGs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("MailTmplGAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<MailTmplG> getMailTmplGs() {
		return mailTmplGs;
	}
	public void setMailTmplGs(List<MailTmplG> mailTmplGs) {
		this.mailTmplGs = mailTmplGs;
	}
	public MailTmplG getMailTmplG() {
		return mailTmplG;
	}
	public void setMailTmplG(MailTmplG mailTmplG) {
		this.mailTmplG = mailTmplG;
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
	public java.util.List<gnmail.vo.MailGroup> getMailGroups() {
		return mailGroups;
	}
	public void setMailGroups(java.util.List<gnmail.vo.MailGroup> mailGroups){
		this.mailGroups = mailGroups;
	}
	public void addtoMailGroups(gnmail.vo.MailGroup mailGroup){
		if(getMailGroups() == null) setMailGroups(new java.util.ArrayList<gnmail.vo.MailGroup>());
			getMailGroups().add(mailGroup);
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