package zrsy.ww.json;

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

import zrsy.ww.MSG;
import zrsy.ww.BasicAction;

import zrsy.facade.ScoFacade;
import zrsy.facade.UsrFacade;
import zrsy.vo.Sco;
import zrsy.vo.ScoDtl;
import zrsy.vo.Usr;
import zrsy.vo.json.ScoJson;

public class ScoAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<Sco> scos;
	private Sco sco = new Sco();
	private java.util.List<zrsy.vo.GpSco> gpScos;
	private java.util.List<zrsy.vo.ScoDtl> scoDtls;
	private List<Usr> usrs;
	private java.util.List<zrsy.vo.Com> coms;
	private java.util.List<zrsy.vo.Dept> depts;
	public String execute() throws Exception {
		try {
			if(sco != null && sco.getScoId() != null) {
				sco = new ScoFacade().findById(sco);
				setJson(JSON.toJSONString(sco)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ScoAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(sco != null && sco.getScoId() != null) {
				//sco = new ScoFacade().findById(sco);
				//setJson(JSON.toJSONString(sco)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ScoAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(sco != null && sco.getScoId() != null) {
				sco = new ScoFacade().findById(sco);
				setJson(JSON.toJSONString(sco)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ScoAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(sco != null && sco.getScoId() != null) {
				sco = new ScoFacade().findById(sco);
				setJson(JSON.toJSONString(sco)); 
			}
			coms = new zrsy.facade.ComFacade().find("select "+zrsy.vo.Com.SELF_FIELDS+" from Com",zrsy.vo.Com.SELF_FIELDS);
			List<ScoDtl> scoComs = new  zrsy.facade.ScoDtlFacade().find("select "+zrsy.vo.ScoDtl.SELF_FIELDS+" from [GnSy].[dbo].[ScoDtl] where ScoDtl.scoId="+sco.getScoId(),zrsy.vo.ScoDtl.SELF_FIELDS);
			List<ScoDtl> scoDepts = new  zrsy.facade.ScoDtlFacade().find("select "+zrsy.vo.ScoDtl.SELF_FIELDS+" from [GnSy].[dbo].[ScoDtl] where ScoDtl.scoId="+sco.getScoId(),zrsy.vo.ScoDtl.SELF_FIELDS);
			List<ScoDtl> scoUsrs = new  zrsy.facade.ScoDtlFacade().find("select "+zrsy.vo.ScoDtl.SELF_FIELDS+" from [GnSy].[dbo].[ScoDtl] where ScoDtl.scoId="+sco.getScoId(),zrsy.vo.ScoDtl.SELF_FIELDS);
			for(int i=0; i<coms.size(); i++) {
				for(int j=0; j<scoComs.size(); j++) {
					if(coms.get(i).getComId().equals(scoComs.get(j).getComId())) {
						coms.get(i).setChecked("checked");
					}
				}
				depts = new zrsy.facade.DeptFacade().find("select "+zrsy.vo.Dept.SELF_FIELDS+" from Dept where Dept.comId="+coms.get(i).getComId(),zrsy.vo.Dept.SELF_FIELDS);
				for(int n=0; n<depts.size(); n++) {
					for(int k=0; k<scoDepts.size(); k++) {
						if(depts.get(n).getDeptId().equals(scoDepts.get(k).getDetpId())) {
							depts.get(n).setChecked("checked");
						}
					}
					usrs = new UsrFacade().find("select "+zrsy.vo.Usr.SELF_FIELDS+" from Usr where Usr.status=1 and Usr.deptId="+depts.get(n).getDeptId(),zrsy.vo.Usr.SELF_FIELDS);
					for(int x=0; x<usrs.size(); x++) {
						for(int y=0; y<scoUsrs.size(); y++) {
							if(usrs.get(x).getId().equals(scoUsrs.get(y).getUsrId())) {
								usrs.get(x).setChecked("checked");
							}
						}
						depts.get(n).addtoUsrs(usrs.get(x));
					}
					coms.get(i).addtoDepts(depts.get(n));
				}
			}
			
			
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ScoAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String auth() throws Exception {
		try {
			if(sco != null && sco.getScoId() != null) {
				sco = new ScoFacade().findById(sco);
				setJson(JSON.toJSONString(sco)); 
			}
			coms = new zrsy.facade.ComFacade().find("select "+zrsy.vo.Com.SELF_FIELDS+" from Com",zrsy.vo.Com.SELF_FIELDS);
			List<ScoDtl> scoComs = new  zrsy.facade.ScoDtlFacade().find("select "+zrsy.vo.ScoDtl.SELF_FIELDS+" from [GnSy].[dbo].[ScoDtl] where ScoDtl.scoId="+sco.getScoId(),zrsy.vo.ScoDtl.SELF_FIELDS);
			List<ScoDtl> scoDepts = new  zrsy.facade.ScoDtlFacade().find("select "+zrsy.vo.ScoDtl.SELF_FIELDS+" from [GnSy].[dbo].[ScoDtl] where ScoDtl.scoId="+sco.getScoId(),zrsy.vo.ScoDtl.SELF_FIELDS);
			List<ScoDtl> scoUsrs = new  zrsy.facade.ScoDtlFacade().find("select "+zrsy.vo.ScoDtl.SELF_FIELDS+" from [GnSy].[dbo].[ScoDtl] where ScoDtl.scoId="+sco.getScoId(),zrsy.vo.ScoDtl.SELF_FIELDS);
			for(int i=0; i<coms.size(); i++) {
				for(int j=0; j<scoComs.size(); j++) {
					if(coms.get(i).getComId().equals(scoComs.get(j).getComId())) {
						coms.get(i).setChecked("checked");
					}
				}
				depts = new zrsy.facade.DeptFacade().find("select "+zrsy.vo.Dept.SELF_FIELDS+" from Dept where Dept.comId="+coms.get(i).getComId(),zrsy.vo.Dept.SELF_FIELDS);
				for(int n=0; n<depts.size(); n++) {
					for(int k=0; k<scoDepts.size(); k++) {
						if(depts.get(n).getDeptId().equals(scoDepts.get(k).getDetpId())) {
							depts.get(n).setChecked("checked");
						}
					}
					usrs = new UsrFacade().find("select "+zrsy.vo.Usr.SELF_FIELDS+" from Usr where Usr.status=1 and Usr.deptId="+depts.get(n).getDeptId(),zrsy.vo.Usr.SELF_FIELDS);
					for(int x=0; x<usrs.size(); x++) {
						for(int y=0; y<scoUsrs.size(); y++) {
							if(usrs.get(x).getId().equals(scoUsrs.get(y).getUsrId())) {
								usrs.get(x).setChecked("checked");
							}
						}
						depts.get(n).addtoUsrs(usrs.get(x));
					}
					coms.get(i).addtoDepts(depts.get(n));
				}
			}
			
			
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ScoAction Exception", e);
			return ERROR;
		}
		return "author";
	}
	public String list() throws Exception {
		try {
			if(sco == null) sco = new Sco();
			int total = new ScoFacade().amount(sco);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			scos = new ScoFacade().find(sco,getPageVO());
			ScoJson scoJson = new ScoJson();
			scoJson.Rows = scos;
			scoJson.Total = total;
			setJson(JSON.toJSONString(scoJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ScoAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			sco.setLastUpd(getUsrSession().getId());
			sco.setLastDate(new Date());
			sco.setGpScos(gpScos);
			sco.setScoDtls(scoDtls);
			if(sco.getScoId() == null){
				sco.setCreateBy(getUsrSession().getId());
				sco.setCreateDate(new Date());
				new ScoFacade().save(sco);
			}
			else{
				new ScoFacade().update(sco);
			}
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("ScoAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//sco.setCreateBy(getSession().getUserId());
			//sco.setCreateDate(new Date());
			//sco.setLastUpd(getSession().getUserId());
			//sco.setLastUpdDate(new Date());
			new ScoFacade().update(sco);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("ScoAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(scos != null && scos.size() > 0) {
				for(int i=0; i<scos.size();i++) {
					if(scos.get(i) != null) {
						//scos.get(i).setLastUpd(getSession().getUserId());
						//scos.get(i).setLastUpdDate(new Date());
						new ScoFacade().submit(scos.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("ScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(scos != null && scos.size() > 0) {
				for(int i=0; i<scos.size();i++) {
					if(scos.get(i) != null) {
						//scos.get(i).setLastUpd(getSession().getUserId());
						//scos.get(i).setLastUpdDate(new Date());
						new ScoFacade().submit(scos.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("ScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(scos != null && scos.size() > 0) {
				for(int i=0; i<scos.size();i++) {
					if(scos.get(i) != null) {
						//scos.get(i).setLastUpd(getSession().getUserId());
						//scos.get(i).setLastUpdDate(new Date());
						new ScoFacade().update(scos.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("ScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(scos != null && scos.size() > 0) {
				for(int i=0; i<scos.size();i++) {
					if(scos.get(i) != null) {
						//scos.get(i).setLastUpd(getSession().getUserId());
						//scos.get(i).setLastUpdDate(new Date());
						new ScoFacade().submit(scos.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("ScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(scos != null && scos.size() > 0) {
				for(int i=0; i<scos.size();i++) {
					if(scos.get(i) != null) {
						//scos.get(i).setLastUpd(getSession().getUserId());
						//scos.get(i).setLastUpdDate(new Date());
						new ScoFacade().submit(scos.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("ScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(scos != null && scos.size() > 0) {
				for(int i=0; i<scos.size();i++) {
					if(scos.get(i) != null) {
						//scos.get(i).setLastUpd(getSession().getUserId());
						//scos.get(i).setLastUpdDate(new Date());
						new ScoFacade().submit(scos.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("ScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(scos != null && scos.size() > 0) {
				for(int i=0; i<scos.size();i++) {
					if(scos.get(i) != null) {
						//scos.get(i).setLastUpd(getSession().getUserId());
						//scos.get(i).setLastUpdDate(new Date());
						new ScoFacade().submit(scos.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("ScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(scos != null && scos.size() > 0) {
				for(int i=0; i<scos.size();i++) {
					if(scos.get(i) != null){
						//scos.get(i).setLastUpd(getSession().getUserId());
						//scos.get(i).setLastUpdDate(new Date());
						new ScoFacade().submit(scos.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("ScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(scos != null && scos.size() > 0) {
				for(int i=0; i<scos.size();i++) {
					if(scos.get(i) != null){
						//scos.get(i).setLastUpd(getSession().getUserId());
						//scos.get(i).setLastUpdDate(new Date());
						new ScoFacade().review(scos.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("ScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(scos != null && scos.size() > 0) {
				for(int i=0; i<scos.size();i++) {
					if(scos.get(i) != null) {
						//scos.get(i).setLastUpd(getSession().getUserId());
						//scos.get(i).setLastUpdDate(new Date());
						new ScoFacade().review(scos.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("ScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(scos != null && scos.size() > 0) {
				for(int i=0; i<scos.size();i++) {
					if(scos.get(i) != null) {
						//scos.get(i).setLastUpd(getSession().getUserId());
						//scos.get(i).setLastUpdDate(new Date());
						new ScoFacade().confirm(scos.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("ScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(scos != null && scos.size() > 0) {
				for(int i=0; i<scos.size();i++) {
					if(scos.get(i) != null) {
						//scos.get(i).setLastUpd(getSession().getUserId());
						//scos.get(i).setLastUpdDate(new Date());
						new ScoFacade().confirm(scos.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("ScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(scos != null && scos.size() > 0) {
					for(int i=0; i<scos.size();i++) {
						if(scos.get(i) != null) {
							//scos.get(i).setLastUpd(getSession().getUserId());
							//scos.get(i).setLastUpdDate(new Date());
							new ScoFacade().confirm(scos.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("ScoAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<Sco> scos = new ScoFacade().find(sco);
			if(scos != null && scos.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"创建日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"更新日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"范围ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"类型",wcformat));
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
					ws.addCell(new Label(index,1,"范围名称",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<scos.size();i++) {
					row++;
					int m = 0;
					if(scos.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,scos.get(i).getCreateDate(),wcformat));
					m++;
					if(scos.get(i).getLastDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,scos.get(i).getLastDate(),wcformat));
					m++;
					if(scos.get(i).getScoId() != null) 
						ws.addCell(new jxl.write.Number(m,row,scos.get(i).getScoId(),wcformat));
					m++;
					if(scos.get(i).getScope() != null) 
						ws.addCell(new jxl.write.Number(m,row,scos.get(i).getScope(),wcformat));
					m++;
					if(scos.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,scos.get(i).getStatus(),wcformat));
					m++;
					if(scos.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,scos.get(i).getCreateBy(),wcformat));
					m++;
					if(scos.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,scos.get(i).getLastUpd(),wcformat));
					m++;
					if(scos.get(i).getScoNm() != null) 
						ws.addCell(new jxl.write.Label(m,row,scos.get(i).getScoNm(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("ScoListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(scos != null && scos.size() > 0) {
				for(int i=0; i<scos.size();i++) {
					if(scos.get(i) != null) {
						//scos.get(i).setLastUpd(getSession().getUserId());
						//scos.get(i).setLastUpdDate(new Date());
						new ScoFacade().confirm(scos.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("ScoAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(scos != null && scos.size() > 0) {
				for(int i=0; i<scos.size();i++) {
					if(scos.get(i) != null) {
						//scos.get(i).setLastUpd(getSession().getUserId());
						//scos.get(i).setLastUpdDate(new Date());
						new ScoFacade().confirm(scos.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("ScoAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<Sco> getScos() {
		return scos;
	}
	public void setScos(List<Sco> scos) {
		this.scos = scos;
	}
	public Sco getSco() {
		return sco;
	}
	public void setSco(Sco sco) {
		this.sco = sco;
	}
	public WritableWorkbook getWorkbook() {
		return workbook;
	}
	public void setWorkbook(WritableWorkbook workbook) {
		this.workbook = workbook;
	}
	public List<Usr> getUsrs() {
		return usrs;
	}
	public void setUsrs(List<Usr> usrs) {
		this.usrs = usrs;
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
	public java.util.List<zrsy.vo.GpSco> getGpScos() {
		return gpScos;
	}
	public void setGpScos(java.util.List<zrsy.vo.GpSco> gpScos){
		this.gpScos = gpScos;
	}
	public void addtoGpScos(zrsy.vo.GpSco gpSco){
		if(getGpScos() == null) setGpScos(new java.util.ArrayList<zrsy.vo.GpSco>());
			getGpScos().add(gpSco);
	}
	public java.util.List<zrsy.vo.ScoDtl> getScoDtls() {
		return scoDtls;
	}
	public void setScoDtls(java.util.List<zrsy.vo.ScoDtl> scoDtls){
		this.scoDtls = scoDtls;
	}
	public void addtoScoDtls(zrsy.vo.ScoDtl scoDtl){
		if(getScoDtls() == null) setScoDtls(new java.util.ArrayList<zrsy.vo.ScoDtl>());
			getScoDtls().add(scoDtl);
	}
	public java.util.List<zrsy.vo.Com> getComs() {
		return coms;
	}
	public void setComs(java.util.List<zrsy.vo.Com> coms){
		this.coms = coms;
	}
	public void addtoComs(zrsy.vo.Com com){
		if(getComs() == null) setComs(new java.util.ArrayList<zrsy.vo.Com>());
			getComs().add(com);
	}
	public java.util.List<zrsy.vo.Dept> getDepts() {
		return depts;
	}
	public void setDepts(java.util.List<zrsy.vo.Dept> depts){
		this.depts = depts;
	}
	public void addtoDepts(zrsy.vo.Dept dept){
		if(getDepts() == null) setDepts(new java.util.ArrayList<zrsy.vo.Dept>());
			getDepts().add(dept);
	}
	public void addtoUsrs(zrsy.vo.Usr usr){
		if(getUsrs() == null) setUsrs(new java.util.ArrayList<zrsy.vo.Usr>());
			getUsrs().add(usr);
	}
}