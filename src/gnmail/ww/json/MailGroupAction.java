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

import gnmail.facade.MailGroupFacade;
import gnmail.vo.MailGroup;
import gnmail.vo.json.MailGroupJson;

public class MailGroupAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<MailGroup> mailGroups;
	private MailGroup mailGroup = new MailGroup();
	private java.util.List<gnmail.vo.MailBook> mailBooks;
	private java.util.List<gnmail.vo.MailTmplG> mailTmplGs;

	public String execute() throws Exception {
		try {
			if(mailGroup != null && mailGroup.getGroupId() != null) {
				mailGroup = new MailGroupFacade().findById(mailGroup);
				setJson(JSON.toJSONString(mailGroup)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailGroupAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(mailGroup != null && mailGroup.getGroupId() != null) {
				//mailGroup = new MailGroupFacade().findById(mailGroup);
				//setJson(JSON.toJSONString(mailGroup)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailGroupAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(mailGroup != null && mailGroup.getGroupId() != null) {
				mailGroup = new MailGroupFacade().findById(mailGroup);
				setJson(JSON.toJSONString(mailGroup)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailGroupAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(mailGroup != null && mailGroup.getGroupId() != null) {
				mailGroup = new MailGroupFacade().findById(mailGroup);
				setJson(JSON.toJSONString(mailGroup)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailGroupAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(mailGroup == null) mailGroup = new MailGroup();
			int total = new MailGroupFacade().amount(mailGroup);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			mailGroups = new MailGroupFacade().find(mailGroup,getPageVO());
			MailGroupJson mailGroupJson = new MailGroupJson();
			mailGroupJson.Rows = mailGroups;
			mailGroupJson.Total = total;
			setJson(JSON.toJSONString(mailGroupJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailGroupAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//mailGroup.setCreateBy(getSession().getUserId());
			//mailGroup.setCreateDate(new Date());
			//mailGroup.setLastUpd(getSession().getUserId());
			//mailGroup.setLastUpdDate(new Date());
			mailGroup.setMailBooks(mailBooks);
			mailGroup.setMailTmplGs(mailTmplGs);

			if(mailGroup.getGroupId() == null)
				new MailGroupFacade().save(mailGroup);
			else
				new MailGroupFacade().update(mailGroup);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("MailGroupAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//mailGroup.setCreateBy(getSession().getUserId());
			//mailGroup.setCreateDate(new Date());
			//mailGroup.setLastUpd(getSession().getUserId());
			//mailGroup.setLastUpdDate(new Date());
			new MailGroupFacade().update(mailGroup);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("MailGroupAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(mailGroups != null && mailGroups.size() > 0) {
				for(int i=0; i<mailGroups.size();i++) {
					if(mailGroups.get(i) != null) {
						//mailGroups.get(i).setLastUpd(getSession().getUserId());
						//mailGroups.get(i).setLastUpdDate(new Date());
						new MailGroupFacade().submit(mailGroups.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("MailGroupAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(mailGroups != null && mailGroups.size() > 0) {
				for(int i=0; i<mailGroups.size();i++) {
					if(mailGroups.get(i) != null) {
						//mailGroups.get(i).setLastUpd(getSession().getUserId());
						//mailGroups.get(i).setLastUpdDate(new Date());
						new MailGroupFacade().submit(mailGroups.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("MailGroupAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(mailGroups != null && mailGroups.size() > 0) {
				for(int i=0; i<mailGroups.size();i++) {
					if(mailGroups.get(i) != null) {
						//mailGroups.get(i).setLastUpd(getSession().getUserId());
						//mailGroups.get(i).setLastUpdDate(new Date());
						new MailGroupFacade().update(mailGroups.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("MailGroupAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(mailGroups != null && mailGroups.size() > 0) {
				for(int i=0; i<mailGroups.size();i++) {
					if(mailGroups.get(i) != null) {
						//mailGroups.get(i).setLastUpd(getSession().getUserId());
						//mailGroups.get(i).setLastUpdDate(new Date());
						new MailGroupFacade().submit(mailGroups.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("MailGroupAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(mailGroups != null && mailGroups.size() > 0) {
				for(int i=0; i<mailGroups.size();i++) {
					if(mailGroups.get(i) != null) {
						//mailGroups.get(i).setLastUpd(getSession().getUserId());
						//mailGroups.get(i).setLastUpdDate(new Date());
						new MailGroupFacade().submit(mailGroups.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("MailGroupAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(mailGroups != null && mailGroups.size() > 0) {
				for(int i=0; i<mailGroups.size();i++) {
					if(mailGroups.get(i) != null) {
						//mailGroups.get(i).setLastUpd(getSession().getUserId());
						//mailGroups.get(i).setLastUpdDate(new Date());
						new MailGroupFacade().submit(mailGroups.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("MailGroupAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(mailGroups != null && mailGroups.size() > 0) {
				for(int i=0; i<mailGroups.size();i++) {
					if(mailGroups.get(i) != null) {
						//mailGroups.get(i).setLastUpd(getSession().getUserId());
						//mailGroups.get(i).setLastUpdDate(new Date());
						new MailGroupFacade().submit(mailGroups.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("MailGroupAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(mailGroups != null && mailGroups.size() > 0) {
				for(int i=0; i<mailGroups.size();i++) {
					if(mailGroups.get(i) != null){
						//mailGroups.get(i).setLastUpd(getSession().getUserId());
						//mailGroups.get(i).setLastUpdDate(new Date());
						new MailGroupFacade().submit(mailGroups.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("MailGroupAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(mailGroups != null && mailGroups.size() > 0) {
				for(int i=0; i<mailGroups.size();i++) {
					if(mailGroups.get(i) != null){
						//mailGroups.get(i).setLastUpd(getSession().getUserId());
						//mailGroups.get(i).setLastUpdDate(new Date());
						new MailGroupFacade().review(mailGroups.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("MailGroupAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(mailGroups != null && mailGroups.size() > 0) {
				for(int i=0; i<mailGroups.size();i++) {
					if(mailGroups.get(i) != null) {
						//mailGroups.get(i).setLastUpd(getSession().getUserId());
						//mailGroups.get(i).setLastUpdDate(new Date());
						new MailGroupFacade().review(mailGroups.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("MailGroupAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(mailGroups != null && mailGroups.size() > 0) {
				for(int i=0; i<mailGroups.size();i++) {
					if(mailGroups.get(i) != null) {
						//mailGroups.get(i).setLastUpd(getSession().getUserId());
						//mailGroups.get(i).setLastUpdDate(new Date());
						new MailGroupFacade().confirm(mailGroups.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("MailGroupAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(mailGroups != null && mailGroups.size() > 0) {
				for(int i=0; i<mailGroups.size();i++) {
					if(mailGroups.get(i) != null) {
						//mailGroups.get(i).setLastUpd(getSession().getUserId());
						//mailGroups.get(i).setLastUpdDate(new Date());
						new MailGroupFacade().confirm(mailGroups.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("MailGroupAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(mailGroups != null && mailGroups.size() > 0) {
					for(int i=0; i<mailGroups.size();i++) {
						if(mailGroups.get(i) != null) {
							//mailGroups.get(i).setLastUpd(getSession().getUserId());
							//mailGroups.get(i).setLastUpdDate(new Date());
							new MailGroupFacade().confirm(mailGroups.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("MailGroupAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<MailGroup> mailGroups = new MailGroupFacade().find(mailGroup);
			if(mailGroups != null && mailGroups.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"分组ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"用户ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"上级分组",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"分组名称",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"组级别",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"状态",wcformat));
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
				for(int i=0; i<mailGroups.size();i++) {
					row++;
					int m = 0;
					if(mailGroups.get(i).getGroupId() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailGroups.get(i).getGroupId(),wcformat));
					m++;
					if(mailGroups.get(i).getUserId() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailGroups.get(i).getUserId(),wcformat));
					m++;
					if(mailGroups.get(i).getGroupParent() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailGroups.get(i).getGroupParent(),wcformat));
					m++;
					if(mailGroups.get(i).getGroupName() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailGroups.get(i).getGroupName(),wcformat));
					m++;
					if(mailGroups.get(i).getGroupLevel() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailGroups.get(i).getGroupLevel(),wcformat));
					m++;
					if(mailGroups.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailGroups.get(i).getStatus(),wcformat));
					m++;
					if(mailGroups.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,mailGroups.get(i).getCreateDate(),wcformat));
					m++;
					if(mailGroups.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailGroups.get(i).getLastUpd(),wcformat));
					m++;
					if(mailGroups.get(i).getLastUpdDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,mailGroups.get(i).getLastUpdDate(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("MailGroupListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(mailGroups != null && mailGroups.size() > 0) {
				for(int i=0; i<mailGroups.size();i++) {
					if(mailGroups.get(i) != null) {
						//mailGroups.get(i).setLastUpd(getSession().getUserId());
						//mailGroups.get(i).setLastUpdDate(new Date());
						new MailGroupFacade().confirm(mailGroups.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("MailGroupAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(mailGroups != null && mailGroups.size() > 0) {
				for(int i=0; i<mailGroups.size();i++) {
					if(mailGroups.get(i) != null) {
						//mailGroups.get(i).setLastUpd(getSession().getUserId());
						//mailGroups.get(i).setLastUpdDate(new Date());
						new MailGroupFacade().confirm(mailGroups.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("MailGroupAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<MailGroup> getMailGroups() {
		return mailGroups;
	}
	public void setMailGroups(List<MailGroup> mailGroups) {
		this.mailGroups = mailGroups;
	}
	public MailGroup getMailGroup() {
		return mailGroup;
	}
	public void setMailGroup(MailGroup mailGroup) {
		this.mailGroup = mailGroup;
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
	public java.util.List<gnmail.vo.MailBook> getMailBooks() {
		return mailBooks;
	}
	public void setMailBooks(java.util.List<gnmail.vo.MailBook> mailBooks){
		this.mailBooks = mailBooks;
	}
	public void addtoMailBooks(gnmail.vo.MailBook mailBook){
		if(getMailBooks() == null) setMailBooks(new java.util.ArrayList<gnmail.vo.MailBook>());
			getMailBooks().add(mailBook);
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

}