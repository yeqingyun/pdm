package zrsy.ww.json;

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

import zrsy.ww.MSG;
import zrsy.ww.BasicAction;

import zrsy.facade.BillSubsFacade;
import zrsy.vo.BillSubs;
import zrsy.vo.json.BillSubsJson;

public class BillSubsAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<BillSubs> billSubss;
	private BillSubs billSubs = new BillSubs();
	private java.util.List<zrsy.vo.BillTyp> billTyps;
	private java.util.List<zrsy.vo.SyDef> syDefs;

	public String execute() throws Exception {
		try {
			if(billSubs != null && billSubs.getId() != null) {
				billSubs = new BillSubsFacade().findById(billSubs);
				setJson(JSON.toJSONString(billSubs)); 
			}
			syDefs = new zrsy.facade.SyDefFacade().find("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef",zrsy.vo.SyDef.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("BillSubsAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(billSubs != null && billSubs.getId() != null) {
				//billSubs = new BillSubsFacade().findById(billSubs);
				//setJson(JSON.toJSONString(billSubs)); 
			//}
			syDefs = new zrsy.facade.SyDefFacade().find("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef",zrsy.vo.SyDef.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("BillSubsAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(billSubs != null && billSubs.getId() != null) {
				billSubs = new BillSubsFacade().findById(billSubs);
				setJson(JSON.toJSONString(billSubs)); 
			}
			syDefs = new zrsy.facade.SyDefFacade().find("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef",zrsy.vo.SyDef.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("BillSubsAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(billSubs != null && billSubs.getId() != null) {
				billSubs = new BillSubsFacade().findById(billSubs);
				setJson(JSON.toJSONString(billSubs)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("BillSubsAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(billSubs == null) billSubs = new BillSubs();
			int total = new BillSubsFacade().amount(billSubs);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			billSubss = new BillSubsFacade().find(billSubs,getPageVO());
			BillSubsJson billSubsJson = new BillSubsJson();
			billSubsJson.Rows = billSubss;
			billSubsJson.Total = total;
			setJson(JSON.toJSONString(billSubsJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("BillSubsAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//billSubs.setCreateBy(getSession().getUserId());
			//billSubs.setCreateDate(new Date());
			//billSubs.setLastUpd(getSession().getUserId());
			//billSubs.setLastUpdDate(new Date());
			billSubs.setBillTyps(billTyps);

			if(billSubs.getId() == null)
				new BillSubsFacade().save(billSubs);
			else
				new BillSubsFacade().update(billSubs);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("BillSubsAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//billSubs.setCreateBy(getSession().getUserId());
			//billSubs.setCreateDate(new Date());
			//billSubs.setLastUpd(getSession().getUserId());
			//billSubs.setLastUpdDate(new Date());
			new BillSubsFacade().update(billSubs);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("BillSubsAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(billSubss != null && billSubss.size() > 0) {
				for(int i=0; i<billSubss.size();i++) {
					if(billSubss.get(i) != null) {
						//billSubss.get(i).setLastUpd(getSession().getUserId());
						//billSubss.get(i).setLastUpdDate(new Date());
						new BillSubsFacade().submit(billSubss.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("BillSubsAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(billSubss != null && billSubss.size() > 0) {
				for(int i=0; i<billSubss.size();i++) {
					if(billSubss.get(i) != null) {
						//billSubss.get(i).setLastUpd(getSession().getUserId());
						//billSubss.get(i).setLastUpdDate(new Date());
						new BillSubsFacade().submit(billSubss.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("BillSubsAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(billSubss != null && billSubss.size() > 0) {
				for(int i=0; i<billSubss.size();i++) {
					if(billSubss.get(i) != null) {
						//billSubss.get(i).setLastUpd(getSession().getUserId());
						//billSubss.get(i).setLastUpdDate(new Date());
						new BillSubsFacade().update(billSubss.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("BillSubsAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(billSubss != null && billSubss.size() > 0) {
				for(int i=0; i<billSubss.size();i++) {
					if(billSubss.get(i) != null) {
						//billSubss.get(i).setLastUpd(getSession().getUserId());
						//billSubss.get(i).setLastUpdDate(new Date());
						new BillSubsFacade().submit(billSubss.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("BillSubsAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(billSubss != null && billSubss.size() > 0) {
				for(int i=0; i<billSubss.size();i++) {
					if(billSubss.get(i) != null) {
						//billSubss.get(i).setLastUpd(getSession().getUserId());
						//billSubss.get(i).setLastUpdDate(new Date());
						new BillSubsFacade().submit(billSubss.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("BillSubsAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(billSubss != null && billSubss.size() > 0) {
				for(int i=0; i<billSubss.size();i++) {
					if(billSubss.get(i) != null) {
						//billSubss.get(i).setLastUpd(getSession().getUserId());
						//billSubss.get(i).setLastUpdDate(new Date());
						new BillSubsFacade().submit(billSubss.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("BillSubsAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(billSubss != null && billSubss.size() > 0) {
				for(int i=0; i<billSubss.size();i++) {
					if(billSubss.get(i) != null) {
						//billSubss.get(i).setLastUpd(getSession().getUserId());
						//billSubss.get(i).setLastUpdDate(new Date());
						new BillSubsFacade().submit(billSubss.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("BillSubsAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(billSubss != null && billSubss.size() > 0) {
				for(int i=0; i<billSubss.size();i++) {
					if(billSubss.get(i) != null){
						//billSubss.get(i).setLastUpd(getSession().getUserId());
						//billSubss.get(i).setLastUpdDate(new Date());
						new BillSubsFacade().submit(billSubss.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("BillSubsAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(billSubss != null && billSubss.size() > 0) {
				for(int i=0; i<billSubss.size();i++) {
					if(billSubss.get(i) != null){
						//billSubss.get(i).setLastUpd(getSession().getUserId());
						//billSubss.get(i).setLastUpdDate(new Date());
						new BillSubsFacade().review(billSubss.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("BillSubsAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(billSubss != null && billSubss.size() > 0) {
				for(int i=0; i<billSubss.size();i++) {
					if(billSubss.get(i) != null) {
						//billSubss.get(i).setLastUpd(getSession().getUserId());
						//billSubss.get(i).setLastUpdDate(new Date());
						new BillSubsFacade().review(billSubss.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("BillSubsAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(billSubss != null && billSubss.size() > 0) {
				for(int i=0; i<billSubss.size();i++) {
					if(billSubss.get(i) != null) {
						//billSubss.get(i).setLastUpd(getSession().getUserId());
						//billSubss.get(i).setLastUpdDate(new Date());
						new BillSubsFacade().confirm(billSubss.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("BillSubsAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(billSubss != null && billSubss.size() > 0) {
				for(int i=0; i<billSubss.size();i++) {
					if(billSubss.get(i) != null) {
						//billSubss.get(i).setLastUpd(getSession().getUserId());
						//billSubss.get(i).setLastUpdDate(new Date());
						new BillSubsFacade().confirm(billSubss.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("BillSubsAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(billSubss != null && billSubss.size() > 0) {
					for(int i=0; i<billSubss.size();i++) {
						if(billSubss.get(i) != null) {
							//billSubss.get(i).setLastUpd(getSession().getUserId());
							//billSubss.get(i).setLastUpdDate(new Date());
							new BillSubsFacade().confirm(billSubss.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("BillSubsAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<BillSubs> billSubss = new BillSubsFacade().find(billSubs);
			if(billSubss != null && billSubss.size() > 0) {
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
					ws.addCell(new Label(index,1,"项目Id",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"分段(A-Z)",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"当前流水(00-99)",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"状态",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<billSubss.size();i++) {
					row++;
					int m = 0;
					if(billSubss.get(i).getId() != null) 
						ws.addCell(new jxl.write.Number(m,row,billSubss.get(i).getId(),wcformat));
					m++;
					if(billSubss.get(i).getSyId() != null) 
						ws.addCell(new jxl.write.Number(m,row,billSubss.get(i).getSyId(),wcformat));
					m++;
					if(billSubss.get(i).getSubs() != null) 
						ws.addCell(new jxl.write.Label(m,row,billSubss.get(i).getSubs(),wcformat));
					m++;
					if(billSubss.get(i).getCurrSn() != null) 
						ws.addCell(new jxl.write.Label(m,row,billSubss.get(i).getCurrSn(),wcformat));
					m++;
					if(billSubss.get(i).getState() != null) 
						ws.addCell(new jxl.write.Number(m,row,billSubss.get(i).getState(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("BillSubsListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(billSubss != null && billSubss.size() > 0) {
				for(int i=0; i<billSubss.size();i++) {
					if(billSubss.get(i) != null) {
						//billSubss.get(i).setLastUpd(getSession().getUserId());
						//billSubss.get(i).setLastUpdDate(new Date());
						new BillSubsFacade().confirm(billSubss.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("BillSubsAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(billSubss != null && billSubss.size() > 0) {
				for(int i=0; i<billSubss.size();i++) {
					if(billSubss.get(i) != null) {
						//billSubss.get(i).setLastUpd(getSession().getUserId());
						//billSubss.get(i).setLastUpdDate(new Date());
						new BillSubsFacade().confirm(billSubss.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("BillSubsAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<BillSubs> getBillSubss() {
		return billSubss;
	}
	public void setBillSubss(List<BillSubs> billSubss) {
		this.billSubss = billSubss;
	}
	public BillSubs getBillSubs() {
		return billSubs;
	}
	public void setBillSubs(BillSubs billSubs) {
		this.billSubs = billSubs;
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
	public java.util.List<zrsy.vo.BillTyp> getBillTyps() {
		return billTyps;
	}
	public void setBillTyps(java.util.List<zrsy.vo.BillTyp> billTyps){
		this.billTyps = billTyps;
	}
	public void addtoBillTyps(zrsy.vo.BillTyp billTyp){
		if(getBillTyps() == null) setBillTyps(new java.util.ArrayList<zrsy.vo.BillTyp>());
			getBillTyps().add(billTyp);
	}
	public java.util.List<zrsy.vo.SyDef> getSyDefs() {
		return syDefs;
	}
	public void setSyDefs(java.util.List<zrsy.vo.SyDef> syDefs){
		this.syDefs = syDefs;
	}
	public void addtoSyDefs(zrsy.vo.SyDef syDef){
		if(getSyDefs() == null) setSyDefs(new java.util.ArrayList<zrsy.vo.SyDef>());
			getSyDefs().add(syDef);
	}

}