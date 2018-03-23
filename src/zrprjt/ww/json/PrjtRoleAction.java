package zrprjt.ww.json;

import java.io.File;
import java.io.OutputStream;
import java.util.Date;
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

import zrprjt.facade.PrjtRoleFacade;
import zrprjt.facade.PrjtTypFacade;
import zrprjt.vo.PrjtRole;
import zrprjt.vo.PrjtTyp;
import zrprjt.vo.json.PrjtRoleJson;

public class PrjtRoleAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<PrjtRole> prjtRoles;
	private PrjtRole prjtRole = new PrjtRole();
	private java.util.List<zrprjt.vo.PrjtDef> prjtDefs;
	
	
	private java.util.List<zrprjt.vo.PrjtTyp> prjtTyps;

	public String execute() throws Exception {
		try {
			if(prjtRole != null && prjtRole.getRoleId() != null) {
				prjtRole = new PrjtRoleFacade().findById(prjtRole);
				setJson(JSON.toJSONString(prjtRole)); 
			}
			prjtDefs = new zrprjt.facade.PrjtDefFacade().find("select "+zrprjt.vo.PrjtDef.SELF_FIELDS+" from PrjtDef",zrprjt.vo.PrjtDef.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtRoleAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	
	public String getTyps() throws Exception {
		try{
		prjtTyps = new PrjtTypFacade().find(new PrjtTyp());

		this.setJson(JSON.toJSONString(prjtTyps));
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return PGLIS;
    }
	
	public String getpDefs() throws Exception {
		try{
			prjtDefs = new zrprjt.facade.PrjtDefFacade().find("select "+zrprjt.vo.PrjtDef.SELF_FIELDS+" from PrjtDef",zrprjt.vo.PrjtDef.SELF_FIELDS);

		this.setJson(JSON.toJSONString(prjtDefs));
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtRoleAction Exception", e);
			return ERROR;
		}
		return PGLIS;
    }
	
	public String add() throws Exception {
		try {
			//if(prjtRole != null && prjtRole.getRoleId() != null) {
				//prjtRole = new PrjtRoleFacade().findById(prjtRole);
				//setJson(JSON.toJSONString(prjtRole)); 
			//}
			prjtDefs = new zrprjt.facade.PrjtDefFacade().find("select "+zrprjt.vo.PrjtDef.SELF_FIELDS+" from PrjtDef",zrprjt.vo.PrjtDef.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtRoleAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(prjtRole != null && prjtRole.getRoleId() != null) {
				prjtRole = new PrjtRoleFacade().findById(prjtRole);
				setJson(JSON.toJSONString(prjtRole)); 
			}
			prjtDefs = new zrprjt.facade.PrjtDefFacade().find("select "+zrprjt.vo.PrjtDef.SELF_FIELDS+" from PrjtDef",zrprjt.vo.PrjtDef.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtRoleAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(prjtRole != null && prjtRole.getRoleId() != null) {
				prjtRole = new PrjtRoleFacade().findById(prjtRole);
				setJson(JSON.toJSONString(prjtRole)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtRoleAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(prjtRole == null) prjtRole = new PrjtRole();
			int total = new PrjtRoleFacade().amount(prjtRole);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			prjtRoles = new PrjtRoleFacade().find(prjtRole,getPageVO());
			PrjtRoleJson prjtRoleJson = new PrjtRoleJson();
			prjtRoleJson.Rows = prjtRoles;
			prjtRoleJson.Total = total;
			setJson(JSON.toJSONString(prjtRoleJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtRoleAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			
			
			if(prjtRole.getRoleId() == null){
				prjtRole.setCreateBy(getUsrSession().getId());
				prjtRole.setCreateDate(new Date());
				prjtRole.setLastUpd(getUsrSession().getId());
				prjtRole.setLastDate(new Date());
				new PrjtRoleFacade().save(prjtRole);
			}
			else{
				prjtRole.setLastUpd(getUsrSession().getId());
				prjtRole.setLastDate(new Date());
				new PrjtRoleFacade().update(prjtRole);
			}
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("PrjtRoleAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//prjtRole.setCreateBy(getSession().getUserId());
			//prjtRole.setCreateDate(new Date());
			//prjtRole.setLastUpd(getSession().getUserId());
			//prjtRole.setLastUpdDate(new Date());
			new PrjtRoleFacade().update(prjtRole);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("PrjtRoleAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(prjtRoles != null && prjtRoles.size() > 0) {
				for(int i=0; i<prjtRoles.size();i++) {
					if(prjtRoles.get(i) != null) {
						//prjtRoles.get(i).setLastUpd(getSession().getUserId());
						//prjtRoles.get(i).setLastUpdDate(new Date());
						new PrjtRoleFacade().submit(prjtRoles.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("PrjtRoleAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(prjtRoles != null && prjtRoles.size() > 0) {
				for(int i=0; i<prjtRoles.size();i++) {
					if(prjtRoles.get(i) != null) {
						//prjtRoles.get(i).setLastUpd(getSession().getUserId());
						//prjtRoles.get(i).setLastUpdDate(new Date());
						new PrjtRoleFacade().submit(prjtRoles.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("PrjtRoleAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(prjtRoles != null && prjtRoles.size() > 0) {
				for(int i=0; i<prjtRoles.size();i++) {
					if(prjtRoles.get(i) != null) {
						//prjtRoles.get(i).setLastUpd(getSession().getUserId());
						//prjtRoles.get(i).setLastUpdDate(new Date());
						new PrjtRoleFacade().update(prjtRoles.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("PrjtRoleAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(prjtRoles != null && prjtRoles.size() > 0) {
				for(int i=0; i<prjtRoles.size();i++) {
					if(prjtRoles.get(i) != null) {
						//prjtRoles.get(i).setLastUpd(getSession().getUserId());
						//prjtRoles.get(i).setLastUpdDate(new Date());
						new PrjtRoleFacade().submit(prjtRoles.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("PrjtRoleAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(prjtRoles != null && prjtRoles.size() > 0) {
				for(int i=0; i<prjtRoles.size();i++) {
					if(prjtRoles.get(i) != null) {
						//prjtRoles.get(i).setLastUpd(getSession().getUserId());
						//prjtRoles.get(i).setLastUpdDate(new Date());
						new PrjtRoleFacade().submit(prjtRoles.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("PrjtRoleAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(prjtRoles != null && prjtRoles.size() > 0) {
				for(int i=0; i<prjtRoles.size();i++) {
					if(prjtRoles.get(i) != null) {
						//prjtRoles.get(i).setLastUpd(getSession().getUserId());
						//prjtRoles.get(i).setLastUpdDate(new Date());
						new PrjtRoleFacade().submit(prjtRoles.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("PrjtRoleAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(prjtRoles != null && prjtRoles.size() > 0) {
				for(int i=0; i<prjtRoles.size();i++) {
					if(prjtRoles.get(i) != null) {
						//prjtRoles.get(i).setLastUpd(getSession().getUserId());
						//prjtRoles.get(i).setLastUpdDate(new Date());
						new PrjtRoleFacade().submit(prjtRoles.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("PrjtRoleAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(prjtRoles != null && prjtRoles.size() > 0) {
				for(int i=0; i<prjtRoles.size();i++) {
					if(prjtRoles.get(i) != null){
						//prjtRoles.get(i).setLastUpd(getSession().getUserId());
						//prjtRoles.get(i).setLastUpdDate(new Date());
						new PrjtRoleFacade().submit(prjtRoles.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("PrjtRoleAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(prjtRoles != null && prjtRoles.size() > 0) {
				for(int i=0; i<prjtRoles.size();i++) {
					if(prjtRoles.get(i) != null){
						//prjtRoles.get(i).setLastUpd(getSession().getUserId());
						//prjtRoles.get(i).setLastUpdDate(new Date());
						new PrjtRoleFacade().review(prjtRoles.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("PrjtRoleAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(prjtRoles != null && prjtRoles.size() > 0) {
				for(int i=0; i<prjtRoles.size();i++) {
					if(prjtRoles.get(i) != null) {
						//prjtRoles.get(i).setLastUpd(getSession().getUserId());
						//prjtRoles.get(i).setLastUpdDate(new Date());
						new PrjtRoleFacade().review(prjtRoles.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("PrjtRoleAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(prjtRoles != null && prjtRoles.size() > 0) {
				for(int i=0; i<prjtRoles.size();i++) {
					if(prjtRoles.get(i) != null) {
						//prjtRoles.get(i).setLastUpd(getSession().getUserId());
						//prjtRoles.get(i).setLastUpdDate(new Date());
						new PrjtRoleFacade().confirm(prjtRoles.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("PrjtRoleAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(prjtRoles != null && prjtRoles.size() > 0) {
				for(int i=0; i<prjtRoles.size();i++) {
					if(prjtRoles.get(i) != null) {
						//prjtRoles.get(i).setLastUpd(getSession().getUserId());
						//prjtRoles.get(i).setLastUpdDate(new Date());
						new PrjtRoleFacade().confirm(prjtRoles.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("PrjtRoleAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(prjtRoles != null && prjtRoles.size() > 0) {
					for(int i=0; i<prjtRoles.size();i++) {
						if(prjtRoles.get(i) != null) {
							//prjtRoles.get(i).setLastUpd(getSession().getUserId());
							//prjtRoles.get(i).setLastUpdDate(new Date());
							new PrjtRoleFacade().confirm(prjtRoles.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("PrjtRoleAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<PrjtRole> prjtRoles = new PrjtRoleFacade().find(prjtRole);
			if(prjtRoles != null && prjtRoles.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"ID",wcformat));
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
					ws.addCell(new Label(index,1,"项目编号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"角色名称",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"备注",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<prjtRoles.size();i++) {
					row++;
					int m = 0;
					if(prjtRoles.get(i).getRoleId() != null) 
						ws.addCell(new jxl.write.Number(m,row,prjtRoles.get(i).getRoleId(),wcformat));
					m++;
					if(prjtRoles.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,prjtRoles.get(i).getStatus(),wcformat));
					m++;
					if(prjtRoles.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,prjtRoles.get(i).getCreateBy(),wcformat));
					m++;
					if(prjtRoles.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,prjtRoles.get(i).getLastUpd(),wcformat));
					m++;
					if(prjtRoles.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,prjtRoles.get(i).getCreateDate(),wcformat));
					m++;
					if(prjtRoles.get(i).getLastDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,prjtRoles.get(i).getLastDate(),wcformat));
					m++;
					//if(prjtRoles.get(i).getPrjtTypId() != null) 
					//	ws.addCell(new jxl.write.Label(m,row,prjtRoles.get(i).getPrjtTypId(),wcformat));
					m++;
					if(prjtRoles.get(i).getRoleNm() != null) 
						ws.addCell(new jxl.write.Label(m,row,prjtRoles.get(i).getRoleNm(),wcformat));
					m++;
					if(prjtRoles.get(i).getRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,prjtRoles.get(i).getRemark(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("PrjtRoleListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(prjtRoles != null && prjtRoles.size() > 0) {
				for(int i=0; i<prjtRoles.size();i++) {
					if(prjtRoles.get(i) != null) {
						//prjtRoles.get(i).setLastUpd(getSession().getUserId());
						//prjtRoles.get(i).setLastUpdDate(new Date());
						new PrjtRoleFacade().confirm(prjtRoles.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("PrjtRoleAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(prjtRoles != null && prjtRoles.size() > 0) {
				for(int i=0; i<prjtRoles.size();i++) {
					if(prjtRoles.get(i) != null) {
						//prjtRoles.get(i).setLastUpd(getSession().getUserId());
						//prjtRoles.get(i).setLastUpdDate(new Date());
						new PrjtRoleFacade().confirm(prjtRoles.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("PrjtRoleAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<PrjtRole> getPrjtRoles() {
		return prjtRoles;
	}
	public void setPrjtRoles(List<PrjtRole> prjtRoles) {
		this.prjtRoles = prjtRoles;
	}
	public PrjtRole getPrjtRole() {
		return prjtRole;
	}
	public void setPrjtRole(PrjtRole prjtRole) {
		this.prjtRole = prjtRole;
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
	public java.util.List<zrprjt.vo.PrjtDef> getPrjtDefs() {
		return prjtDefs;
	}
	public void setPrjtDefs(java.util.List<zrprjt.vo.PrjtDef> prjtDefs){
		this.prjtDefs = prjtDefs;
	}
	public void addtoPrjtDefs(zrprjt.vo.PrjtDef prjtDef){
		if(getPrjtDefs() == null) setPrjtDefs(new java.util.ArrayList<zrprjt.vo.PrjtDef>());
			getPrjtDefs().add(prjtDef);
	}

	public java.util.List<zrprjt.vo.PrjtTyp> getPrjtTyps() {
		return prjtTyps;
	}

	public void setPrjtTyps(java.util.List<zrprjt.vo.PrjtTyp> prjtTyps) {
		this.prjtTyps = prjtTyps;
	}

	public String listAll() throws Exception {
		try {
			if(prjtRole == null) prjtRole = new PrjtRole();
			prjtRoles = new PrjtRoleFacade().find(prjtRole);
			PrjtRoleJson prjtRoleJson = new PrjtRoleJson();
			prjtRoleJson.Rows = prjtRoles;
			prjtRoleJson.Total = prjtRoles.size();
			setJson(JSON.toJSONString(prjtRoleJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtRoleAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
}