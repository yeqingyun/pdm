package gnmail.ww.json;

import gnmail.dao.helper.MailBookHelper;
import gnmail.facade.MailBookFacade;
import gnmail.vo.MailBook;
import gnmail.vo.json.MailBookJson;
import gnmail.ww.BasicAction;
import gnmail.ww.MSG;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;
import org.frm.jdbc.SqlUtil;

import com.alibaba.fastjson.JSON;

public class MailBookAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<MailBook> mailBooks;
	private MailBook mailBook = new MailBook();
	private java.util.List<gnmail.vo.MailGroup> mailGroups;

	public String execute() throws Exception {
		try {
			if(mailBook != null && mailBook.getBookId() != null) {
				mailBook = new MailBookFacade().findById(mailBook);
				setJson(JSON.toJSONString(mailBook)); 
			}
			mailGroups = new gnmail.facade.MailGroupFacade().find("select "+gnmail.vo.MailGroup.SELF_FIELDS+" from MailGroup",gnmail.vo.MailGroup.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailBookAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	
	public String selectMailBook()throws Exception {
		try{
			String sql = "select "
				+ MailBook.LIST_FIELDS
				+ SqlUtil.getSqlString(MailBookHelper.class)
				+ " and Usr.DeptId = "+mailBook.getDeptId()
				+ " and Usr.status="+MSG.CONST_STATUS_1;
			mailBooks = new MailBookFacade().find(sql,MailBook.LIST_FIELDS);
			this.setJson(JSON.toJSONString(mailBooks));
		}
		catch(Exception e) {
				setMsg(MSG.F_SEA);
				Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
				return ERROR;
		}
		return PGLIS;
	}
	
	public String add() throws Exception {
		try {
			//if(mailBook != null && mailBook.getBookId() != null) {
				//mailBook = new MailBookFacade().findById(mailBook);
				//setJson(JSON.toJSONString(mailBook)); 
			//}
			mailGroups = new gnmail.facade.MailGroupFacade().find("select "+gnmail.vo.MailGroup.SELF_FIELDS+" from MailGroup",gnmail.vo.MailGroup.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailBookAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(mailBook != null && mailBook.getBookId() != null) {
				mailBook = new MailBookFacade().findById(mailBook);
				setJson(JSON.toJSONString(mailBook)); 
			}
			mailGroups = new gnmail.facade.MailGroupFacade().find("select "+gnmail.vo.MailGroup.SELF_FIELDS+" from MailGroup",gnmail.vo.MailGroup.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailBookAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(mailBook != null && mailBook.getBookId() != null) {
				mailBook = new MailBookFacade().findById(mailBook);
				setJson(JSON.toJSONString(mailBook)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailBookAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(mailBook == null) mailBook = new MailBook();
			int total = new MailBookFacade().amount(mailBook);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			mailBooks = new MailBookFacade().find(mailBook,getPageVO());
			MailBookJson mailBookJson = new MailBookJson();
			mailBookJson.Rows = mailBooks;
			mailBookJson.Total = total;
			setJson(JSON.toJSONString(mailBookJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("MailBookAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//mailBook.setCreateBy(getSession().getUserId());
			//mailBook.setCreateDate(new Date());
			//mailBook.setLastUpd(getSession().getUserId());
			//mailBook.setLastUpdDate(new Date());

			if(mailBook.getBookId() == null)
				new MailBookFacade().save(mailBook);
			else
				new MailBookFacade().update(mailBook);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("MailBookAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//mailBook.setCreateBy(getSession().getUserId());
			//mailBook.setCreateDate(new Date());
			//mailBook.setLastUpd(getSession().getUserId());
			//mailBook.setLastUpdDate(new Date());
			new MailBookFacade().update(mailBook);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("MailBookAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(mailBooks != null && mailBooks.size() > 0) {
				for(int i=0; i<mailBooks.size();i++) {
					if(mailBooks.get(i) != null) {
						//mailBooks.get(i).setLastUpd(getSession().getUserId());
						//mailBooks.get(i).setLastUpdDate(new Date());
						new MailBookFacade().submit(mailBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("MailBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(mailBooks != null && mailBooks.size() > 0) {
				for(int i=0; i<mailBooks.size();i++) {
					if(mailBooks.get(i) != null) {
						//mailBooks.get(i).setLastUpd(getSession().getUserId());
						//mailBooks.get(i).setLastUpdDate(new Date());
						new MailBookFacade().submit(mailBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("MailBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(mailBooks != null && mailBooks.size() > 0) {
				for(int i=0; i<mailBooks.size();i++) {
					if(mailBooks.get(i) != null) {
						//mailBooks.get(i).setLastUpd(getSession().getUserId());
						//mailBooks.get(i).setLastUpdDate(new Date());
						new MailBookFacade().update(mailBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("MailBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(mailBooks != null && mailBooks.size() > 0) {
				for(int i=0; i<mailBooks.size();i++) {
					if(mailBooks.get(i) != null) {
						//mailBooks.get(i).setLastUpd(getSession().getUserId());
						//mailBooks.get(i).setLastUpdDate(new Date());
						new MailBookFacade().submit(mailBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("MailBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(mailBooks != null && mailBooks.size() > 0) {
				for(int i=0; i<mailBooks.size();i++) {
					if(mailBooks.get(i) != null) {
						//mailBooks.get(i).setLastUpd(getSession().getUserId());
						//mailBooks.get(i).setLastUpdDate(new Date());
						new MailBookFacade().submit(mailBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("MailBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(mailBooks != null && mailBooks.size() > 0) {
				for(int i=0; i<mailBooks.size();i++) {
					if(mailBooks.get(i) != null) {
						//mailBooks.get(i).setLastUpd(getSession().getUserId());
						//mailBooks.get(i).setLastUpdDate(new Date());
						new MailBookFacade().submit(mailBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("MailBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(mailBooks != null && mailBooks.size() > 0) {
				for(int i=0; i<mailBooks.size();i++) {
					if(mailBooks.get(i) != null) {
						//mailBooks.get(i).setLastUpd(getSession().getUserId());
						//mailBooks.get(i).setLastUpdDate(new Date());
						new MailBookFacade().submit(mailBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("MailBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(mailBooks != null && mailBooks.size() > 0) {
				for(int i=0; i<mailBooks.size();i++) {
					if(mailBooks.get(i) != null){
						//mailBooks.get(i).setLastUpd(getSession().getUserId());
						//mailBooks.get(i).setLastUpdDate(new Date());
						new MailBookFacade().submit(mailBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("MailBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(mailBooks != null && mailBooks.size() > 0) {
				for(int i=0; i<mailBooks.size();i++) {
					if(mailBooks.get(i) != null){
						//mailBooks.get(i).setLastUpd(getSession().getUserId());
						//mailBooks.get(i).setLastUpdDate(new Date());
						new MailBookFacade().review(mailBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("MailBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(mailBooks != null && mailBooks.size() > 0) {
				for(int i=0; i<mailBooks.size();i++) {
					if(mailBooks.get(i) != null) {
						//mailBooks.get(i).setLastUpd(getSession().getUserId());
						//mailBooks.get(i).setLastUpdDate(new Date());
						new MailBookFacade().review(mailBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("MailBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(mailBooks != null && mailBooks.size() > 0) {
				for(int i=0; i<mailBooks.size();i++) {
					if(mailBooks.get(i) != null) {
						//mailBooks.get(i).setLastUpd(getSession().getUserId());
						//mailBooks.get(i).setLastUpdDate(new Date());
						new MailBookFacade().confirm(mailBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("MailBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(mailBooks != null && mailBooks.size() > 0) {
				for(int i=0; i<mailBooks.size();i++) {
					if(mailBooks.get(i) != null) {
						//mailBooks.get(i).setLastUpd(getSession().getUserId());
						//mailBooks.get(i).setLastUpdDate(new Date());
						new MailBookFacade().confirm(mailBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("MailBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(mailBooks != null && mailBooks.size() > 0) {
					for(int i=0; i<mailBooks.size();i++) {
						if(mailBooks.get(i) != null) {
							//mailBooks.get(i).setLastUpd(getSession().getUserId());
							//mailBooks.get(i).setLastUpdDate(new Date());
							new MailBookFacade().confirm(mailBooks.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("MailBookAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<MailBook> mailBooks = new MailBookFacade().find(mailBook);
			if(mailBooks != null && mailBooks.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"地址薄ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"用户ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"分组ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"电话1",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"电话1",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"手机",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"邮件件地址",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"名称",wcformat));
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
				for(int i=0; i<mailBooks.size();i++) {
					row++;
					int m = 0;
					if(mailBooks.get(i).getBookId() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailBooks.get(i).getBookId(),wcformat));
					m++;
					if(mailBooks.get(i).getUserId() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailBooks.get(i).getUserId(),wcformat));
					m++;
					if(mailBooks.get(i).getGroupId() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailBooks.get(i).getGroupId(),wcformat));
					m++;
					if(mailBooks.get(i).getExtPhone() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailBooks.get(i).getExtPhone(),wcformat));
					m++;
					if(mailBooks.get(i).getPhone() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailBooks.get(i).getPhone(),wcformat));
					m++;
					if(mailBooks.get(i).getMobile() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailBooks.get(i).getMobile(),wcformat));
					m++;
					if(mailBooks.get(i).getMailAddr() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailBooks.get(i).getMailAddr(),wcformat));
					m++;
					if(mailBooks.get(i).getAddrName() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailBooks.get(i).getAddrName(),wcformat));
					m++;
					if(mailBooks.get(i).getRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,mailBooks.get(i).getRemark(),wcformat));
					m++;
					if(mailBooks.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailBooks.get(i).getStatus(),wcformat));
					m++;
					if(mailBooks.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailBooks.get(i).getCreateBy(),wcformat));
					m++;
					if(mailBooks.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,mailBooks.get(i).getCreateDate(),wcformat));
					m++;
					if(mailBooks.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,mailBooks.get(i).getLastUpd(),wcformat));
					m++;
					if(mailBooks.get(i).getLastUpdDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,mailBooks.get(i).getLastUpdDate(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("MailBookListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(mailBooks != null && mailBooks.size() > 0) {
				for(int i=0; i<mailBooks.size();i++) {
					if(mailBooks.get(i) != null) {
						//mailBooks.get(i).setLastUpd(getSession().getUserId());
						//mailBooks.get(i).setLastUpdDate(new Date());
						new MailBookFacade().confirm(mailBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("MailBookAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(mailBooks != null && mailBooks.size() > 0) {
				for(int i=0; i<mailBooks.size();i++) {
					if(mailBooks.get(i) != null) {
						//mailBooks.get(i).setLastUpd(getSession().getUserId());
						//mailBooks.get(i).setLastUpdDate(new Date());
						new MailBookFacade().confirm(mailBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("MailBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<MailBook> getMailBooks() {
		return mailBooks;
	}
	public void setMailBooks(List<MailBook> mailBooks) {
		this.mailBooks = mailBooks;
	}
	public MailBook getMailBook() {
		return mailBook;
	}
	public void setMailBook(MailBook mailBook) {
		this.mailBook = mailBook;
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

}