package zrsy.ww.json;

import java.io.File;
import java.io.OutputStream;
import java.util.Date;
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

import zrsy.dao.helper.AddrBookHelper;
import zrsy.facade.AddrBookFacade;
import zrsy.vo.AddrBook;
import zrsy.vo.json.AddrBookJson;
import zrsy.ww.BasicAction;
import zrsy.ww.MSG;

import com.alibaba.fastjson.JSON;

public class AddrBookAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<AddrBook> addrBooks;
	private AddrBook addrBook = new AddrBook();
	private java.util.List<zrsy.vo.Usr> usrs;
	private java.util.List<zrsy.vo.Com> coms;
	private java.util.List<zrsy.vo.Dept> depts;
	
	public String execute() throws Exception {
		try {
			if(addrBook != null && addrBook.getBookId() != null) {
				addrBook = new AddrBookFacade().findById(addrBook);
				setJson(JSON.toJSONString(addrBook)); 
			}
			usrs = new zrsy.facade.UsrFacade().find("select "+zrsy.vo.Usr.SELF_FIELDS+" from Usr",zrsy.vo.Usr.SELF_FIELDS);
			coms = new zrsy.facade.ComFacade().find("select "+zrsy.vo.Com.SELF_FIELDS+" from Com where status=1 ",zrsy.vo.Com.SELF_FIELDS);
			depts = new zrsy.facade.DeptFacade().find("select "+zrsy.vo.Dept.SELF_FIELDS+" from Dept where status=1",zrsy.vo.Dept.SELF_FIELDS);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("AddrBookAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	
	public String selectAddrBook()throws Exception {
		try{
			String sql = "select " + AddrBook.ALL_FIELDS
				+ SqlUtil.getSqlString(AddrBookHelper.class)
				+ " and Usr.DeptId = '"+addrBook.getDeptId()+"' ";
			
			//System.out.println(sql);
			
			addrBooks = new AddrBookFacade().find(sql,AddrBook.ALL_FIELDS);
			this.setJson(JSON.toJSONString(addrBooks));
			
			//System.out.println(JSON.toJSONString(addrBooks));
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
			//if(addrBook != null && addrBook.getBookId() != null) {
				//addrBook = new AddrBookFacade().findById(addrBook);
				//setJson(JSON.toJSONString(addrBook)); 
			//}
			usrs = new zrsy.facade.UsrFacade().find("select "+zrsy.vo.Usr.SELF_FIELDS+" from Usr",zrsy.vo.Usr.SELF_FIELDS);
			coms = new zrsy.facade.ComFacade().find("select "+zrsy.vo.Com.SELF_FIELDS+" from Com where status=1",zrsy.vo.Com.SELF_FIELDS);
			depts = new zrsy.facade.DeptFacade().find("select "+zrsy.vo.Dept.SELF_FIELDS+" from Dept where status=1",zrsy.vo.Dept.SELF_FIELDS);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("AddrBookAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(addrBook != null && addrBook.getBookId() != null) {
				addrBook = new AddrBookFacade().findById(addrBook);
				setJson(JSON.toJSONString(addrBook)); 
			}
			usrs = new zrsy.facade.UsrFacade().find("select "+zrsy.vo.Usr.SELF_FIELDS+" from Usr",zrsy.vo.Usr.SELF_FIELDS);
			coms = new zrsy.facade.ComFacade().find("select "+zrsy.vo.Com.SELF_FIELDS+" from Com where status=1 ",zrsy.vo.Com.SELF_FIELDS);
			depts = new zrsy.facade.DeptFacade().find("select "+zrsy.vo.Dept.SELF_FIELDS+" from Dept where status=1",zrsy.vo.Dept.SELF_FIELDS);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("AddrBookAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(addrBook != null && addrBook.getBookId() != null) {
				addrBook = new AddrBookFacade().findById(addrBook);
				setJson(JSON.toJSONString(addrBook)); 
			}
			usrs = new zrsy.facade.UsrFacade().find("select "+zrsy.vo.Usr.SELF_FIELDS+" from Usr",zrsy.vo.Usr.SELF_FIELDS);
			coms = new zrsy.facade.ComFacade().find("select "+zrsy.vo.Com.SELF_FIELDS+" from Com where status=1 ",zrsy.vo.Com.SELF_FIELDS);
			depts = new zrsy.facade.DeptFacade().find("select "+zrsy.vo.Dept.SELF_FIELDS+" from Dept where status=1",zrsy.vo.Dept.SELF_FIELDS);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("AddrBookAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(addrBook == null) addrBook = new AddrBook();
			int total = new AddrBookFacade().amount(addrBook);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			addrBooks = new AddrBookFacade().find(addrBook,getPageVO());
			AddrBookJson addrBookJson = new AddrBookJson();
			addrBookJson.Rows = addrBooks;
			addrBookJson.Total = total;
			setJson(JSON.toJSONString(addrBookJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("AddrBookAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			addrBook.setLastUpd(getUsrSession().getId());
			addrBook.setLastDate(new Date());
			if(addrBook.getBookId() == null){
				addrBook.setStatus(MSG.CONST_STATUS_1);
				addrBook.setCreateBy(getUsrSession().getId());
				addrBook.setCreateDate(new Date());
				new AddrBookFacade().save(addrBook);
			}
			else{
				new AddrBookFacade().update(addrBook);
			}
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("AddrBookAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//addrBook.setCreateBy(getSession().getUserId());
			//addrBook.setCreateDate(new Date());
			//addrBook.setLastUpd(getSession().getUserId());
			//addrBook.setLastUpdDate(new Date());
			new AddrBookFacade().update(addrBook);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("AddrBookAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(addrBooks != null && addrBooks.size() > 0) {
				for(int i=0; i<addrBooks.size();i++) {
					if(addrBooks.get(i) != null) {
						//addrBooks.get(i).setLastUpd(getSession().getUserId());
						//addrBooks.get(i).setLastUpdDate(new Date());
						new AddrBookFacade().submit(addrBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("AddrBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(addrBooks != null && addrBooks.size() > 0) {
				for(int i=0; i<addrBooks.size();i++) {
					if(addrBooks.get(i) != null) {
						//addrBooks.get(i).setLastUpd(getSession().getUserId());
						//addrBooks.get(i).setLastUpdDate(new Date());
						new AddrBookFacade().submit(addrBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("AddrBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(addrBooks != null && addrBooks.size() > 0) {
				for(int i=0; i<addrBooks.size();i++) {
					if(addrBooks.get(i) != null) {
						//addrBooks.get(i).setLastUpd(getSession().getUserId());
						//addrBooks.get(i).setLastUpdDate(new Date());
						new AddrBookFacade().update(addrBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("AddrBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(addrBooks != null && addrBooks.size() > 0) {
				for(int i=0; i<addrBooks.size();i++) {
					if(addrBooks.get(i) != null) {
						//addrBooks.get(i).setLastUpd(getSession().getUserId());
						//addrBooks.get(i).setLastUpdDate(new Date());
						new AddrBookFacade().submit(addrBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("AddrBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(addrBooks != null && addrBooks.size() > 0) {
				for(int i=0; i<addrBooks.size();i++) {
					if(addrBooks.get(i) != null) {
						//addrBooks.get(i).setLastUpd(getSession().getUserId());
						//addrBooks.get(i).setLastUpdDate(new Date());
						new AddrBookFacade().submit(addrBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("AddrBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(addrBooks != null && addrBooks.size() > 0) {
				for(int i=0; i<addrBooks.size();i++) {
					if(addrBooks.get(i) != null) {
						//addrBooks.get(i).setLastUpd(getSession().getUserId());
						//addrBooks.get(i).setLastUpdDate(new Date());
						new AddrBookFacade().submit(addrBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("AddrBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(addrBooks != null && addrBooks.size() > 0) {
				for(int i=0; i<addrBooks.size();i++) {
					if(addrBooks.get(i) != null) {
						//addrBooks.get(i).setLastUpd(getSession().getUserId());
						//addrBooks.get(i).setLastUpdDate(new Date());
						new AddrBookFacade().submit(addrBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("AddrBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(addrBooks != null && addrBooks.size() > 0) {
				for(int i=0; i<addrBooks.size();i++) {
					if(addrBooks.get(i) != null){
						//addrBooks.get(i).setLastUpd(getSession().getUserId());
						//addrBooks.get(i).setLastUpdDate(new Date());
						new AddrBookFacade().submit(addrBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("AddrBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(addrBooks != null && addrBooks.size() > 0) {
				for(int i=0; i<addrBooks.size();i++) {
					if(addrBooks.get(i) != null){
						//addrBooks.get(i).setLastUpd(getSession().getUserId());
						//addrBooks.get(i).setLastUpdDate(new Date());
						new AddrBookFacade().review(addrBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("AddrBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(addrBooks != null && addrBooks.size() > 0) {
				for(int i=0; i<addrBooks.size();i++) {
					if(addrBooks.get(i) != null) {
						//addrBooks.get(i).setLastUpd(getSession().getUserId());
						//addrBooks.get(i).setLastUpdDate(new Date());
						new AddrBookFacade().review(addrBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("AddrBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(addrBooks != null && addrBooks.size() > 0) {
				for(int i=0; i<addrBooks.size();i++) {
					if(addrBooks.get(i) != null) {
						//addrBooks.get(i).setLastUpd(getSession().getUserId());
						//addrBooks.get(i).setLastUpdDate(new Date());
						new AddrBookFacade().confirm(addrBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("AddrBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(addrBooks != null && addrBooks.size() > 0) {
				for(int i=0; i<addrBooks.size();i++) {
					if(addrBooks.get(i) != null) {
						//addrBooks.get(i).setLastUpd(getSession().getUserId());
						//addrBooks.get(i).setLastUpdDate(new Date());
						new AddrBookFacade().confirm(addrBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("AddrBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(addrBooks != null && addrBooks.size() > 0) {
					for(int i=0; i<addrBooks.size();i++) {
						if(addrBooks.get(i) != null) {
							//addrBooks.get(i).setLastUpd(getSession().getUserId());
							//addrBooks.get(i).setLastUpdDate(new Date());
							new AddrBookFacade().confirm(addrBooks.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("AddrBookAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<AddrBook> addrBooks = new AddrBookFacade().find(addrBook);
			if(addrBooks != null && addrBooks.size() > 0) {
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
					ws.addCell(new Label(index,1,"公司ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"部门ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"用户ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"电话",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"分机",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"手机",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"邮件地址",wcformat));
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
				for(int i=0; i<addrBooks.size();i++) {
					row++;
					int m = 0;
					if(addrBooks.get(i).getBookId() != null) 
						ws.addCell(new jxl.write.Number(m,row,addrBooks.get(i).getBookId(),wcformat));
					m++;
					if(addrBooks.get(i).getComId() != null) 
						ws.addCell(new jxl.write.Number(m,row,addrBooks.get(i).getComId(),wcformat));
					m++;
					if(addrBooks.get(i).getDeptId() != null) 
						ws.addCell(new jxl.write.Number(m,row,addrBooks.get(i).getDeptId(),wcformat));
					m++;
					if(addrBooks.get(i).getUserId() != null) 
						ws.addCell(new jxl.write.Number(m,row,addrBooks.get(i).getUserId(),wcformat));
					m++;
					if(addrBooks.get(i).getEmpId() != null) 
						ws.addCell(new jxl.write.Number(m,row,addrBooks.get(i).getEmpId(),wcformat));
					m++;
					if(addrBooks.get(i).getPhone() != null) 
						ws.addCell(new jxl.write.Label(m,row,addrBooks.get(i).getPhone(),wcformat));
					m++;
					if(addrBooks.get(i).getExtPhone() != null) 
						ws.addCell(new jxl.write.Label(m,row,addrBooks.get(i).getExtPhone(),wcformat));
					m++;
					if(addrBooks.get(i).getMobile() != null) 
						ws.addCell(new jxl.write.Label(m,row,addrBooks.get(i).getMobile(),wcformat));
					m++;
					if(addrBooks.get(i).getMailAddr() != null) 
						ws.addCell(new jxl.write.Label(m,row,addrBooks.get(i).getMailAddr(),wcformat));
					m++;
					if(addrBooks.get(i).getAddrName() != null) 
						ws.addCell(new jxl.write.Label(m,row,addrBooks.get(i).getAddrName(),wcformat));
					m++;
					if(addrBooks.get(i).getRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,addrBooks.get(i).getRemark(),wcformat));
					m++;
					if(addrBooks.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,addrBooks.get(i).getStatus(),wcformat));
					m++;
					if(addrBooks.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,addrBooks.get(i).getCreateBy(),wcformat));
					m++;
					if(addrBooks.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,addrBooks.get(i).getCreateDate(),wcformat));
					m++;
					if(addrBooks.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,addrBooks.get(i).getLastUpd(),wcformat));
					m++;
					if(addrBooks.get(i).getLastDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,addrBooks.get(i).getLastDate(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("AddrBookListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(addrBooks != null && addrBooks.size() > 0) {
				for(int i=0; i<addrBooks.size();i++) {
					if(addrBooks.get(i) != null) {
						//addrBooks.get(i).setLastUpd(getSession().getUserId());
						//addrBooks.get(i).setLastUpdDate(new Date());
						new AddrBookFacade().confirm(addrBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("AddrBookAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(addrBooks != null && addrBooks.size() > 0) {
				for(int i=0; i<addrBooks.size();i++) {
					if(addrBooks.get(i) != null) {
						//addrBooks.get(i).setLastUpd(getSession().getUserId());
						//addrBooks.get(i).setLastUpdDate(new Date());
						new AddrBookFacade().confirm(addrBooks.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("AddrBookAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<AddrBook> getAddrBooks() {
		return addrBooks;
	}
	public void setAddrBooks(List<AddrBook> addrBooks) {
		this.addrBooks = addrBooks;
	}
	public AddrBook getAddrBook() {
		return addrBook;
	}
	public void setAddrBook(AddrBook addrBook) {
		this.addrBook = addrBook;
	}
	public WritableWorkbook getWorkbook() {
		return workbook;
	}
	public void setWorkbook(WritableWorkbook workbook) {
		this.workbook = workbook;
	}
	public java.util.List<zrsy.vo.Com> getComs() {
		return coms;
	}
	public void setComs(java.util.List<zrsy.vo.Com> coms) {
		this.coms = coms;
	}
	public java.util.List<zrsy.vo.Dept> getDepts() {
		return depts;
	}
	public void setDepts(java.util.List<zrsy.vo.Dept> depts) {
		this.depts = depts;
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
	public java.util.List<zrsy.vo.Usr> getUsrs() {
		return usrs;
	}
	public void setUsrs(java.util.List<zrsy.vo.Usr> usrs){
		this.usrs = usrs;
	}
	public void addtoUsrs(zrsy.vo.Usr usr){
		if(getUsrs() == null) setUsrs(new java.util.ArrayList<zrsy.vo.Usr>());
			getUsrs().add(usr);
	}

}