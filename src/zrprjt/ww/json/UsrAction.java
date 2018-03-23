package zrprjt.ww.json;

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

import zrprjt.ww.MSG;
import zrprjt.ww.BasicAction;

import zrprjt.facade.UsrFacade;
import zrprjt.vo.Usr;
import zrprjt.vo.json.UsrJson;

public class UsrAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<Usr> usrs;
	private Usr usr = new Usr();

	public String execute() throws Exception {
		try {
			if(usr != null && usr.getId() != null) {
				usr = new UsrFacade().findById(usr);
				setJson(JSON.toJSONString(usr)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(usr != null && usr.getId() != null) {
				//usr = new UsrFacade().findById(usr);
				//setJson(JSON.toJSONString(usr)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(usr != null && usr.getId() != null) {
				usr = new UsrFacade().findById(usr);
				setJson(JSON.toJSONString(usr)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(usr != null && usr.getId() != null) {
				usr = new UsrFacade().findById(usr);
				setJson(JSON.toJSONString(usr)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(usr == null) usr = new Usr();
			int total = new UsrFacade().amount(usr);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			usrs = new UsrFacade().find(usr,getPageVO());
			UsrJson usrJson = new UsrJson();
			usrJson.Rows = usrs;
			usrJson.Total = total;
			setJson(JSON.toJSONString(usrJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//usr.setCreateBy(getSession().getUserId());
			//usr.setCreateDate(new Date());
			//usr.setLastUpd(getSession().getUserId());
			//usr.setLastUpdDate(new Date());

			if(usr.getId() == null)
				new UsrFacade().save(usr);
			else
				new UsrFacade().update(usr);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("UsrAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//usr.setCreateBy(getSession().getUserId());
			//usr.setCreateDate(new Date());
			//usr.setLastUpd(getSession().getUserId());
			//usr.setLastUpdDate(new Date());
			new UsrFacade().update(usr);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("UsrAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().submit(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().submit(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().update(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().submit(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().submit(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().submit(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().submit(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null){
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().submit(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null){
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().review(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().review(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().confirm(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().confirm(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(usrs != null && usrs.size() > 0) {
					for(int i=0; i<usrs.size();i++) {
						if(usrs.get(i) != null) {
							//usrs.get(i).setLastUpd(getSession().getUserId());
							//usrs.get(i).setLastUpdDate(new Date());
							new UsrFacade().confirm(usrs.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<Usr> usrs = new UsrFacade().find(usr);
			if(usrs != null && usrs.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"Id",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"公司",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"部门",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"状态",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"创建人",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"最后更新",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"创建日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"更新日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"登录账号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"密码",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"用户号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"用户名称",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"电子邮箱",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"备注",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<usrs.size();i++) {
					row++;
					int m = 0;
					if(usrs.get(i).getId() != null) 
						ws.addCell(new jxl.write.Number(m,row,usrs.get(i).getId(),wcformat));
					m++;
					if(usrs.get(i).getComId() != null) 
						ws.addCell(new jxl.write.Number(m,row,usrs.get(i).getComId(),wcformat));
					m++;
					if(usrs.get(i).getDeptId() != null) 
						ws.addCell(new jxl.write.Number(m,row,usrs.get(i).getDeptId(),wcformat));
					m++;
					if(usrs.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,usrs.get(i).getStatus(),wcformat));
					m++;
					if(usrs.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,usrs.get(i).getCreateBy(),wcformat));
					m++;
					if(usrs.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,usrs.get(i).getLastUpd(),wcformat));
					m++;
					if(usrs.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,usrs.get(i).getCreateDate(),wcformat));
					m++;
					if(usrs.get(i).getLastDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,usrs.get(i).getLastDate(),wcformat));
					m++;
					if(usrs.get(i).getLogin() != null) 
						ws.addCell(new jxl.write.Label(m,row,usrs.get(i).getLogin(),wcformat));
					m++;
					if(usrs.get(i).getPwd() != null) 
						ws.addCell(new jxl.write.Label(m,row,usrs.get(i).getPwd(),wcformat));
					m++;
					if(usrs.get(i).getUsrNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,usrs.get(i).getUsrNo(),wcformat));
					m++;
					if(usrs.get(i).getUsrName() != null) 
						ws.addCell(new jxl.write.Label(m,row,usrs.get(i).getUsrName(),wcformat));
					m++;
					if(usrs.get(i).getEmail() != null) 
						ws.addCell(new jxl.write.Label(m,row,usrs.get(i).getEmail(),wcformat));
					m++;
					if(usrs.get(i).getRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,usrs.get(i).getRemark(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("UsrListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().confirm(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().confirm(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<Usr> getUsrs() {
		return usrs;
	}
	public void setUsrs(List<Usr> usrs) {
		this.usrs = usrs;
	}
	public Usr getUsr() {
		return usr;
	}
	public void setUsr(Usr usr) {
		this.usr = usr;
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