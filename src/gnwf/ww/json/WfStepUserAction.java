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

import gnwf.facade.WfStepUserFacade;
import gnwf.vo.WfStepUser;
import gnwf.vo.json.WfStepUserJson;

public class WfStepUserAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfStepUser> wfStepUsers;
	private WfStepUser wfStepUser = new WfStepUser();
	private java.util.List<gnwf.vo.WfStep> wfSteps;

	public String execute() throws Exception {
		try {
			if(wfStepUser != null && wfStepUser.getStepId() != null) {
				wfStepUser = new WfStepUserFacade().findById(wfStepUser);
				setJson(JSON.toJSONString(wfStepUser)); 
			}
			wfSteps = new gnwf.facade.WfStepFacade().find("select "+gnwf.vo.WfStep.SELF_FIELDS+" from WfStep",gnwf.vo.WfStep.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfStepUserAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(wfStepUser != null && wfStepUser.getStepId() != null) {
				//wfStepUser = new WfStepUserFacade().findById(wfStepUser);
				//setJson(JSON.toJSONString(wfStepUser)); 
			//}
			wfSteps = new gnwf.facade.WfStepFacade().find("select "+gnwf.vo.WfStep.SELF_FIELDS+" from WfStep",gnwf.vo.WfStep.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfStepUserAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(wfStepUser != null && wfStepUser.getStepId() != null) {
				wfStepUser = new WfStepUserFacade().findById(wfStepUser);
				setJson(JSON.toJSONString(wfStepUser)); 
			}
			wfSteps = new gnwf.facade.WfStepFacade().find("select "+gnwf.vo.WfStep.SELF_FIELDS+" from WfStep",gnwf.vo.WfStep.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfStepUserAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(wfStepUser != null && wfStepUser.getStepId() != null) {
				wfStepUser = new WfStepUserFacade().findById(wfStepUser);
				setJson(JSON.toJSONString(wfStepUser)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfStepUserAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(wfStepUser == null) wfStepUser = new WfStepUser();
			int total = new WfStepUserFacade().amount(wfStepUser);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfStepUsers = new WfStepUserFacade().find(wfStepUser,getPageVO());
			WfStepUserJson wfStepUserJson = new WfStepUserJson();
			wfStepUserJson.Rows = wfStepUsers;
			wfStepUserJson.Total = total;
			setJson(JSON.toJSONString(wfStepUserJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfStepUserAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//wfStepUser.setCreateBy(getSession().getUserId());
			//wfStepUser.setCreateDate(new Date());
			//wfStepUser.setLastUpd(getSession().getUserId());
			//wfStepUser.setLastUpdDate(new Date());

			if(wfStepUser.getStepId() == null)
				new WfStepUserFacade().save(wfStepUser);
			else
				new WfStepUserFacade().update(wfStepUser);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfStepUserAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfStepUser.setCreateBy(getSession().getUserId());
			//wfStepUser.setCreateDate(new Date());
			//wfStepUser.setLastUpd(getSession().getUserId());
			//wfStepUser.setLastUpdDate(new Date());
			new WfStepUserFacade().update(wfStepUser);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfStepUserAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfStepUsers != null && wfStepUsers.size() > 0) {
				for(int i=0; i<wfStepUsers.size();i++) {
					if(wfStepUsers.get(i) != null) {
						//wfStepUsers.get(i).setLastUpd(getSession().getUserId());
						//wfStepUsers.get(i).setLastUpdDate(new Date());
						new WfStepUserFacade().submit(wfStepUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfStepUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfStepUsers != null && wfStepUsers.size() > 0) {
				for(int i=0; i<wfStepUsers.size();i++) {
					if(wfStepUsers.get(i) != null) {
						//wfStepUsers.get(i).setLastUpd(getSession().getUserId());
						//wfStepUsers.get(i).setLastUpdDate(new Date());
						new WfStepUserFacade().submit(wfStepUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfStepUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfStepUsers != null && wfStepUsers.size() > 0) {
				for(int i=0; i<wfStepUsers.size();i++) {
					if(wfStepUsers.get(i) != null) {
						//wfStepUsers.get(i).setLastUpd(getSession().getUserId());
						//wfStepUsers.get(i).setLastUpdDate(new Date());
						new WfStepUserFacade().update(wfStepUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfStepUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfStepUsers != null && wfStepUsers.size() > 0) {
				for(int i=0; i<wfStepUsers.size();i++) {
					if(wfStepUsers.get(i) != null) {
						//wfStepUsers.get(i).setLastUpd(getSession().getUserId());
						//wfStepUsers.get(i).setLastUpdDate(new Date());
						new WfStepUserFacade().submit(wfStepUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfStepUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfStepUsers != null && wfStepUsers.size() > 0) {
				for(int i=0; i<wfStepUsers.size();i++) {
					if(wfStepUsers.get(i) != null) {
						//wfStepUsers.get(i).setLastUpd(getSession().getUserId());
						//wfStepUsers.get(i).setLastUpdDate(new Date());
						new WfStepUserFacade().submit(wfStepUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfStepUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfStepUsers != null && wfStepUsers.size() > 0) {
				for(int i=0; i<wfStepUsers.size();i++) {
					if(wfStepUsers.get(i) != null) {
						//wfStepUsers.get(i).setLastUpd(getSession().getUserId());
						//wfStepUsers.get(i).setLastUpdDate(new Date());
						new WfStepUserFacade().submit(wfStepUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfStepUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfStepUsers != null && wfStepUsers.size() > 0) {
				for(int i=0; i<wfStepUsers.size();i++) {
					if(wfStepUsers.get(i) != null) {
						//wfStepUsers.get(i).setLastUpd(getSession().getUserId());
						//wfStepUsers.get(i).setLastUpdDate(new Date());
						new WfStepUserFacade().submit(wfStepUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfStepUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfStepUsers != null && wfStepUsers.size() > 0) {
				for(int i=0; i<wfStepUsers.size();i++) {
					if(wfStepUsers.get(i) != null){
						//wfStepUsers.get(i).setLastUpd(getSession().getUserId());
						//wfStepUsers.get(i).setLastUpdDate(new Date());
						new WfStepUserFacade().submit(wfStepUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfStepUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfStepUsers != null && wfStepUsers.size() > 0) {
				for(int i=0; i<wfStepUsers.size();i++) {
					if(wfStepUsers.get(i) != null){
						//wfStepUsers.get(i).setLastUpd(getSession().getUserId());
						//wfStepUsers.get(i).setLastUpdDate(new Date());
						new WfStepUserFacade().review(wfStepUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfStepUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfStepUsers != null && wfStepUsers.size() > 0) {
				for(int i=0; i<wfStepUsers.size();i++) {
					if(wfStepUsers.get(i) != null) {
						//wfStepUsers.get(i).setLastUpd(getSession().getUserId());
						//wfStepUsers.get(i).setLastUpdDate(new Date());
						new WfStepUserFacade().review(wfStepUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfStepUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfStepUsers != null && wfStepUsers.size() > 0) {
				for(int i=0; i<wfStepUsers.size();i++) {
					if(wfStepUsers.get(i) != null) {
						//wfStepUsers.get(i).setLastUpd(getSession().getUserId());
						//wfStepUsers.get(i).setLastUpdDate(new Date());
						new WfStepUserFacade().confirm(wfStepUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfStepUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfStepUsers != null && wfStepUsers.size() > 0) {
				for(int i=0; i<wfStepUsers.size();i++) {
					if(wfStepUsers.get(i) != null) {
						//wfStepUsers.get(i).setLastUpd(getSession().getUserId());
						//wfStepUsers.get(i).setLastUpdDate(new Date());
						new WfStepUserFacade().confirm(wfStepUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfStepUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfStepUsers != null && wfStepUsers.size() > 0) {
					for(int i=0; i<wfStepUsers.size();i++) {
						if(wfStepUsers.get(i) != null) {
							//wfStepUsers.get(i).setLastUpd(getSession().getUserId());
							//wfStepUsers.get(i).setLastUpdDate(new Date());
							new WfStepUserFacade().confirm(wfStepUsers.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfStepUserAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfStepUser> wfStepUsers = new WfStepUserFacade().find(wfStepUser);
			if(wfStepUsers != null && wfStepUsers.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"步骤ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"用户ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"任务类型",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfStepUsers.size();i++) {
					row++;
					int m = 0;
					if(wfStepUsers.get(i).getStepId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfStepUsers.get(i).getStepId(),wcformat));
					m++;
					if(wfStepUsers.get(i).getUserId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfStepUsers.get(i).getUserId(),wcformat));
					m++;
					if(wfStepUsers.get(i).getUserType() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfStepUsers.get(i).getUserType(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfStepUserListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfStepUsers != null && wfStepUsers.size() > 0) {
				for(int i=0; i<wfStepUsers.size();i++) {
					if(wfStepUsers.get(i) != null) {
						//wfStepUsers.get(i).setLastUpd(getSession().getUserId());
						//wfStepUsers.get(i).setLastUpdDate(new Date());
						new WfStepUserFacade().confirm(wfStepUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfStepUserAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfStepUsers != null && wfStepUsers.size() > 0) {
				for(int i=0; i<wfStepUsers.size();i++) {
					if(wfStepUsers.get(i) != null) {
						//wfStepUsers.get(i).setLastUpd(getSession().getUserId());
						//wfStepUsers.get(i).setLastUpdDate(new Date());
						new WfStepUserFacade().confirm(wfStepUsers.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfStepUserAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfStepUser> getWfStepUsers() {
		return wfStepUsers;
	}
	public void setWfStepUsers(List<WfStepUser> wfStepUsers) {
		this.wfStepUsers = wfStepUsers;
	}
	public WfStepUser getWfStepUser() {
		return wfStepUser;
	}
	public void setWfStepUser(WfStepUser wfStepUser) {
		this.wfStepUser = wfStepUser;
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
	public java.util.List<gnwf.vo.WfStep> getWfSteps() {
		return wfSteps;
	}
	public void setWfSteps(java.util.List<gnwf.vo.WfStep> wfSteps){
		this.wfSteps = wfSteps;
	}
	public void addtoWfSteps(gnwf.vo.WfStep wfStep){
		if(getWfSteps() == null) setWfSteps(new java.util.ArrayList<gnwf.vo.WfStep>());
			getWfSteps().add(wfStep);
	}

}