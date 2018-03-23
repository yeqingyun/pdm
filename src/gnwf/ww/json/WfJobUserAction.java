package gnwf.ww.json;

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

import gnwf.ww.MSG;
import gnwf.ww.BasicAction;

import gnwf.facade.WfJobUserFacade;
import gnwf.vo.WfJobUser;
import gnwf.vo.json.WfJobUserJson;

public class WfJobUserAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfJobUser> wfJobUsers;
	private WfJobUser wfJobUser = new WfJobUser();

	public String execute() throws Exception {
		try {
			if(wfJobUser != null && wfJobUser.getUserId() != null) {
				wfJobUser = new WfJobUserFacade().findById(wfJobUser);
				setJson(JSON.toJSONString(wfJobUser)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfJobUserAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(wfJobUser != null && wfJobUser.getUserId() != null) {
				//wfJobUser = new WfJobUserFacade().findById(wfJobUser);
				//setJson(JSON.toJSONString(wfJobUser)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfJobUserAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(wfJobUser != null && wfJobUser.getUserId() != null) {
				wfJobUser = new WfJobUserFacade().findById(wfJobUser);
				setJson(JSON.toJSONString(wfJobUser)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfJobUserAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(wfJobUser != null && wfJobUser.getUserId() != null) {
				wfJobUser = new WfJobUserFacade().findById(wfJobUser);
				setJson(JSON.toJSONString(wfJobUser)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfJobUserAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(wfJobUser == null) wfJobUser = new WfJobUser();
			int total = new WfJobUserFacade().amount(wfJobUser);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfJobUsers = new WfJobUserFacade().find(wfJobUser,getPageVO());
			WfJobUserJson wfJobUserJson = new WfJobUserJson();
			wfJobUserJson.Rows = wfJobUsers;
			wfJobUserJson.Total = total;
			setJson(JSON.toJSONString(wfJobUserJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfJobUserAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//wfJobUser.setCreateBy(getSession().getUserId());
			//wfJobUser.setCreateDate(new Date());
			//wfJobUser.setLastUpd(getSession().getUserId());
			//wfJobUser.setLastUpdDate(new Date());

			if(wfJobUser.getUserId() == null)
				new WfJobUserFacade().save(wfJobUser);
			else
				new WfJobUserFacade().update(wfJobUser);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfJobUserAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfJobUser.setCreateBy(getSession().getUserId());
			//wfJobUser.setCreateDate(new Date());
			//wfJobUser.setLastUpd(getSession().getUserId());
			//wfJobUser.setLastUpdDate(new Date());
			new WfJobUserFacade().update(wfJobUser);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfJobUserAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfJobUsers != null && wfJobUsers.size() > 0) {
				for(int i=0; i<wfJobUsers.size();i++) {
					if(wfJobUsers.get(i) != null) {
						//wfJobUsers.get(i).setLastUpd(getSession().getUserId());
						//wfJobUsers.get(i).setLastUpdDate(new Date());
						new WfJobUserFacade().submit(wfJobUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfJobUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfJobUsers != null && wfJobUsers.size() > 0) {
				for(int i=0; i<wfJobUsers.size();i++) {
					if(wfJobUsers.get(i) != null) {
						//wfJobUsers.get(i).setLastUpd(getSession().getUserId());
						//wfJobUsers.get(i).setLastUpdDate(new Date());
						new WfJobUserFacade().submit(wfJobUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfJobUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfJobUsers != null && wfJobUsers.size() > 0) {
				for(int i=0; i<wfJobUsers.size();i++) {
					if(wfJobUsers.get(i) != null) {
						//wfJobUsers.get(i).setLastUpd(getSession().getUserId());
						//wfJobUsers.get(i).setLastUpdDate(new Date());
						new WfJobUserFacade().update(wfJobUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfJobUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfJobUsers != null && wfJobUsers.size() > 0) {
				for(int i=0; i<wfJobUsers.size();i++) {
					if(wfJobUsers.get(i) != null) {
						//wfJobUsers.get(i).setLastUpd(getSession().getUserId());
						//wfJobUsers.get(i).setLastUpdDate(new Date());
						new WfJobUserFacade().submit(wfJobUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfJobUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfJobUsers != null && wfJobUsers.size() > 0) {
				for(int i=0; i<wfJobUsers.size();i++) {
					if(wfJobUsers.get(i) != null) {
						//wfJobUsers.get(i).setLastUpd(getSession().getUserId());
						//wfJobUsers.get(i).setLastUpdDate(new Date());
						new WfJobUserFacade().submit(wfJobUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfJobUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfJobUsers != null && wfJobUsers.size() > 0) {
				for(int i=0; i<wfJobUsers.size();i++) {
					if(wfJobUsers.get(i) != null) {
						//wfJobUsers.get(i).setLastUpd(getSession().getUserId());
						//wfJobUsers.get(i).setLastUpdDate(new Date());
						new WfJobUserFacade().submit(wfJobUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfJobUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfJobUsers != null && wfJobUsers.size() > 0) {
				for(int i=0; i<wfJobUsers.size();i++) {
					if(wfJobUsers.get(i) != null) {
						//wfJobUsers.get(i).setLastUpd(getSession().getUserId());
						//wfJobUsers.get(i).setLastUpdDate(new Date());
						new WfJobUserFacade().submit(wfJobUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfJobUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfJobUsers != null && wfJobUsers.size() > 0) {
				for(int i=0; i<wfJobUsers.size();i++) {
					if(wfJobUsers.get(i) != null){
						//wfJobUsers.get(i).setLastUpd(getSession().getUserId());
						//wfJobUsers.get(i).setLastUpdDate(new Date());
						new WfJobUserFacade().submit(wfJobUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfJobUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfJobUsers != null && wfJobUsers.size() > 0) {
				for(int i=0; i<wfJobUsers.size();i++) {
					if(wfJobUsers.get(i) != null){
						//wfJobUsers.get(i).setLastUpd(getSession().getUserId());
						//wfJobUsers.get(i).setLastUpdDate(new Date());
						new WfJobUserFacade().review(wfJobUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfJobUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfJobUsers != null && wfJobUsers.size() > 0) {
				for(int i=0; i<wfJobUsers.size();i++) {
					if(wfJobUsers.get(i) != null) {
						//wfJobUsers.get(i).setLastUpd(getSession().getUserId());
						//wfJobUsers.get(i).setLastUpdDate(new Date());
						new WfJobUserFacade().review(wfJobUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfJobUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfJobUsers != null && wfJobUsers.size() > 0) {
				for(int i=0; i<wfJobUsers.size();i++) {
					if(wfJobUsers.get(i) != null) {
						//wfJobUsers.get(i).setLastUpd(getSession().getUserId());
						//wfJobUsers.get(i).setLastUpdDate(new Date());
						new WfJobUserFacade().confirm(wfJobUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfJobUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfJobUsers != null && wfJobUsers.size() > 0) {
				for(int i=0; i<wfJobUsers.size();i++) {
					if(wfJobUsers.get(i) != null) {
						//wfJobUsers.get(i).setLastUpd(getSession().getUserId());
						//wfJobUsers.get(i).setLastUpdDate(new Date());
						new WfJobUserFacade().confirm(wfJobUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfJobUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfJobUsers != null && wfJobUsers.size() > 0) {
					for(int i=0; i<wfJobUsers.size();i++) {
						if(wfJobUsers.get(i) != null) {
							//wfJobUsers.get(i).setLastUpd(getSession().getUserId());
							//wfJobUsers.get(i).setLastUpdDate(new Date());
							new WfJobUserFacade().confirm(wfJobUsers.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfJobUserAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfJobUser> wfJobUsers = new WfJobUserFacade().find(wfJobUser);
			if(wfJobUsers != null && wfJobUsers.size() > 0) {
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
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfJobUsers.size();i++) {
					row++;
					int m = 0;
					if(wfJobUsers.get(i).getUserId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfJobUsers.get(i).getUserId(),wcformat));
					m++;
					if(wfJobUsers.get(i).getJobId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfJobUsers.get(i).getJobId(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfJobUserListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfJobUsers != null && wfJobUsers.size() > 0) {
				for(int i=0; i<wfJobUsers.size();i++) {
					if(wfJobUsers.get(i) != null) {
						//wfJobUsers.get(i).setLastUpd(getSession().getUserId());
						//wfJobUsers.get(i).setLastUpdDate(new Date());
						new WfJobUserFacade().confirm(wfJobUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfJobUserAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfJobUsers != null && wfJobUsers.size() > 0) {
				for(int i=0; i<wfJobUsers.size();i++) {
					if(wfJobUsers.get(i) != null) {
						//wfJobUsers.get(i).setLastUpd(getSession().getUserId());
						//wfJobUsers.get(i).setLastUpdDate(new Date());
						new WfJobUserFacade().confirm(wfJobUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfJobUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfJobUser> getWfJobUsers() {
		return wfJobUsers;
	}
	public void setWfJobUsers(List<WfJobUser> wfJobUsers) {
		this.wfJobUsers = wfJobUsers;
	}
	public WfJobUser getWfJobUser() {
		return wfJobUser;
	}
	public void setWfJobUser(WfJobUser wfJobUser) {
		this.wfJobUser = wfJobUser;
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