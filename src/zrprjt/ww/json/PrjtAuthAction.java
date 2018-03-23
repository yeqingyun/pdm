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

import zrprjt.facade.PrjtAuthFacade;
import zrprjt.vo.PrjtAuth;
import zrprjt.vo.json.PrjtAuthJson;

public class PrjtAuthAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<PrjtAuth> prjtAuths;
	private PrjtAuth prjtAuth = new PrjtAuth();
	private java.util.List<zrprjt.vo.PrjtDef> prjtDefs;

	public String execute() throws Exception {
		try {
			if(prjtAuth != null && prjtAuth.getRoleId() != null) {
				prjtAuth = new PrjtAuthFacade().findById(prjtAuth);
				setJson(JSON.toJSONString(prjtAuth)); 
			}
			prjtDefs = new zrprjt.facade.PrjtDefFacade().find("select "+zrprjt.vo.PrjtDef.SELF_FIELDS+" from PrjtDef",zrprjt.vo.PrjtDef.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtAuthAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(prjtAuth != null && prjtAuth.getRoleId() != null) {
				//prjtAuth = new PrjtAuthFacade().findById(prjtAuth);
				//setJson(JSON.toJSONString(prjtAuth)); 
			//}
			prjtDefs = new zrprjt.facade.PrjtDefFacade().find("select "+zrprjt.vo.PrjtDef.SELF_FIELDS+" from PrjtDef",zrprjt.vo.PrjtDef.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtAuthAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(prjtAuth != null && prjtAuth.getRoleId() != null) {
				prjtAuth = new PrjtAuthFacade().findById(prjtAuth);
				setJson(JSON.toJSONString(prjtAuth)); 
			}
			prjtDefs = new zrprjt.facade.PrjtDefFacade().find("select "+zrprjt.vo.PrjtDef.SELF_FIELDS+" from PrjtDef",zrprjt.vo.PrjtDef.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtAuthAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(prjtAuth != null && prjtAuth.getRoleId() != null) {
				prjtAuth = new PrjtAuthFacade().findById(prjtAuth);
				setJson(JSON.toJSONString(prjtAuth)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtAuthAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(prjtAuth == null) prjtAuth = new PrjtAuth();
			int total = new PrjtAuthFacade().amount(prjtAuth);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			prjtAuths = new PrjtAuthFacade().find(prjtAuth,getPageVO());
			PrjtAuthJson prjtAuthJson = new PrjtAuthJson();
			prjtAuthJson.Rows = prjtAuths;
			prjtAuthJson.Total = total;
			setJson(JSON.toJSONString(prjtAuthJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtAuthAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//prjtAuth.setCreateBy(getSession().getUserId());
			//prjtAuth.setCreateDate(new Date());
			//prjtAuth.setLastUpd(getSession().getUserId());
			//prjtAuth.setLastUpdDate(new Date());

			if(prjtAuth.getRoleId() == null)
				new PrjtAuthFacade().save(prjtAuth);
			else
				new PrjtAuthFacade().update(prjtAuth);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("PrjtAuthAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//prjtAuth.setCreateBy(getSession().getUserId());
			//prjtAuth.setCreateDate(new Date());
			//prjtAuth.setLastUpd(getSession().getUserId());
			//prjtAuth.setLastUpdDate(new Date());
			new PrjtAuthFacade().update(prjtAuth);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("PrjtAuthAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(prjtAuths != null && prjtAuths.size() > 0) {
				for(int i=0; i<prjtAuths.size();i++) {
					if(prjtAuths.get(i) != null) {
						//prjtAuths.get(i).setLastUpd(getSession().getUserId());
						//prjtAuths.get(i).setLastUpdDate(new Date());
						new PrjtAuthFacade().submit(prjtAuths.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("PrjtAuthAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(prjtAuths != null && prjtAuths.size() > 0) {
				for(int i=0; i<prjtAuths.size();i++) {
					if(prjtAuths.get(i) != null) {
						//prjtAuths.get(i).setLastUpd(getSession().getUserId());
						//prjtAuths.get(i).setLastUpdDate(new Date());
						new PrjtAuthFacade().submit(prjtAuths.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("PrjtAuthAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(prjtAuths != null && prjtAuths.size() > 0) {
				for(int i=0; i<prjtAuths.size();i++) {
					if(prjtAuths.get(i) != null) {
						//prjtAuths.get(i).setLastUpd(getSession().getUserId());
						//prjtAuths.get(i).setLastUpdDate(new Date());
						new PrjtAuthFacade().update(prjtAuths.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("PrjtAuthAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(prjtAuths != null && prjtAuths.size() > 0) {
				for(int i=0; i<prjtAuths.size();i++) {
					if(prjtAuths.get(i) != null) {
						//prjtAuths.get(i).setLastUpd(getSession().getUserId());
						//prjtAuths.get(i).setLastUpdDate(new Date());
						new PrjtAuthFacade().submit(prjtAuths.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("PrjtAuthAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(prjtAuths != null && prjtAuths.size() > 0) {
				for(int i=0; i<prjtAuths.size();i++) {
					if(prjtAuths.get(i) != null) {
						//prjtAuths.get(i).setLastUpd(getSession().getUserId());
						//prjtAuths.get(i).setLastUpdDate(new Date());
						new PrjtAuthFacade().submit(prjtAuths.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("PrjtAuthAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(prjtAuths != null && prjtAuths.size() > 0) {
				for(int i=0; i<prjtAuths.size();i++) {
					if(prjtAuths.get(i) != null) {
						//prjtAuths.get(i).setLastUpd(getSession().getUserId());
						//prjtAuths.get(i).setLastUpdDate(new Date());
						new PrjtAuthFacade().submit(prjtAuths.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("PrjtAuthAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(prjtAuths != null && prjtAuths.size() > 0) {
				for(int i=0; i<prjtAuths.size();i++) {
					if(prjtAuths.get(i) != null) {
						//prjtAuths.get(i).setLastUpd(getSession().getUserId());
						//prjtAuths.get(i).setLastUpdDate(new Date());
						new PrjtAuthFacade().submit(prjtAuths.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("PrjtAuthAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(prjtAuths != null && prjtAuths.size() > 0) {
				for(int i=0; i<prjtAuths.size();i++) {
					if(prjtAuths.get(i) != null){
						//prjtAuths.get(i).setLastUpd(getSession().getUserId());
						//prjtAuths.get(i).setLastUpdDate(new Date());
						new PrjtAuthFacade().submit(prjtAuths.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("PrjtAuthAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(prjtAuths != null && prjtAuths.size() > 0) {
				for(int i=0; i<prjtAuths.size();i++) {
					if(prjtAuths.get(i) != null){
						//prjtAuths.get(i).setLastUpd(getSession().getUserId());
						//prjtAuths.get(i).setLastUpdDate(new Date());
						new PrjtAuthFacade().review(prjtAuths.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("PrjtAuthAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(prjtAuths != null && prjtAuths.size() > 0) {
				for(int i=0; i<prjtAuths.size();i++) {
					if(prjtAuths.get(i) != null) {
						//prjtAuths.get(i).setLastUpd(getSession().getUserId());
						//prjtAuths.get(i).setLastUpdDate(new Date());
						new PrjtAuthFacade().review(prjtAuths.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("PrjtAuthAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(prjtAuths != null && prjtAuths.size() > 0) {
				for(int i=0; i<prjtAuths.size();i++) {
					if(prjtAuths.get(i) != null) {
						//prjtAuths.get(i).setLastUpd(getSession().getUserId());
						//prjtAuths.get(i).setLastUpdDate(new Date());
						new PrjtAuthFacade().confirm(prjtAuths.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("PrjtAuthAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(prjtAuths != null && prjtAuths.size() > 0) {
				for(int i=0; i<prjtAuths.size();i++) {
					if(prjtAuths.get(i) != null) {
						//prjtAuths.get(i).setLastUpd(getSession().getUserId());
						//prjtAuths.get(i).setLastUpdDate(new Date());
						new PrjtAuthFacade().confirm(prjtAuths.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("PrjtAuthAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(prjtAuths != null && prjtAuths.size() > 0) {
					for(int i=0; i<prjtAuths.size();i++) {
						if(prjtAuths.get(i) != null) {
							//prjtAuths.get(i).setLastUpd(getSession().getUserId());
							//prjtAuths.get(i).setLastUpdDate(new Date());
							new PrjtAuthFacade().confirm(prjtAuths.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("PrjtAuthAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<PrjtAuth> prjtAuths = new PrjtAuthFacade().find(prjtAuth);
			if(prjtAuths != null && prjtAuths.size() > 0) {
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
					ws.addCell(new Label(index,1,"查看",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"上传及下载",wcformat));
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


				int row = 2;
				for(int i=0; i<prjtAuths.size();i++) {
					row++;
					int m = 0;
					if(prjtAuths.get(i).getRoleId() != null) 
						ws.addCell(new jxl.write.Number(m,row,prjtAuths.get(i).getRoleId(),wcformat));
					m++;
					if(prjtAuths.get(i).getIsRead() != null) 
						ws.addCell(new jxl.write.Number(m,row,prjtAuths.get(i).getIsRead(),wcformat));
					m++;
					if(prjtAuths.get(i).getIsLoad() != null) 
						ws.addCell(new jxl.write.Number(m,row,prjtAuths.get(i).getIsLoad(),wcformat));
					m++;
					if(prjtAuths.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,prjtAuths.get(i).getStatus(),wcformat));
					m++;
					if(prjtAuths.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,prjtAuths.get(i).getCreateBy(),wcformat));
					m++;
					if(prjtAuths.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,prjtAuths.get(i).getLastUpd(),wcformat));
					m++;
					if(prjtAuths.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,prjtAuths.get(i).getCreateDate(),wcformat));
					m++;
					if(prjtAuths.get(i).getLastDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,prjtAuths.get(i).getLastDate(),wcformat));
					m++;
					if(prjtAuths.get(i).getPrjtNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,prjtAuths.get(i).getPrjtNo(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("PrjtAuthListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(prjtAuths != null && prjtAuths.size() > 0) {
				for(int i=0; i<prjtAuths.size();i++) {
					if(prjtAuths.get(i) != null) {
						//prjtAuths.get(i).setLastUpd(getSession().getUserId());
						//prjtAuths.get(i).setLastUpdDate(new Date());
						new PrjtAuthFacade().confirm(prjtAuths.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("PrjtAuthAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(prjtAuths != null && prjtAuths.size() > 0) {
				for(int i=0; i<prjtAuths.size();i++) {
					if(prjtAuths.get(i) != null) {
						//prjtAuths.get(i).setLastUpd(getSession().getUserId());
						//prjtAuths.get(i).setLastUpdDate(new Date());
						new PrjtAuthFacade().confirm(prjtAuths.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("PrjtAuthAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<PrjtAuth> getPrjtAuths() {
		return prjtAuths;
	}
	public void setPrjtAuths(List<PrjtAuth> prjtAuths) {
		this.prjtAuths = prjtAuths;
	}
	public PrjtAuth getPrjtAuth() {
		return prjtAuth;
	}
	public void setPrjtAuth(PrjtAuth prjtAuth) {
		this.prjtAuth = prjtAuth;
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

}