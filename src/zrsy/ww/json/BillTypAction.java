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

import zrsy.facade.BillTypFacade;
import zrsy.vo.BillTyp;
import zrsy.vo.json.BillTypJson;

public class BillTypAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<BillTyp> billTyps;
	private BillTyp billTyp = new BillTyp();
	private java.util.List<zrsy.vo.BillSubs> billSubss;

	public String execute() throws Exception {
		try {
			if(billTyp != null && billTyp.getId() != null) {
				billTyp = new BillTypFacade().findById(billTyp);
				setJson(JSON.toJSONString(billTyp)); 
			}
			billSubss = new zrsy.facade.BillSubsFacade().find("select "+zrsy.vo.BillSubs.SELF_FIELDS+" from BillSubs",zrsy.vo.BillSubs.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("BillTypAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(billTyp != null && billTyp.getId() != null) {
				//billTyp = new BillTypFacade().findById(billTyp);
				//setJson(JSON.toJSONString(billTyp)); 
			//}
			billSubss = new zrsy.facade.BillSubsFacade().find("select "+zrsy.vo.BillSubs.SELF_FIELDS+" from BillSubs",zrsy.vo.BillSubs.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("BillTypAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(billTyp != null && billTyp.getId() != null) {
				billTyp = new BillTypFacade().findById(billTyp);
				setJson(JSON.toJSONString(billTyp)); 
			}
			billSubss = new zrsy.facade.BillSubsFacade().find("select "+zrsy.vo.BillSubs.SELF_FIELDS+" from BillSubs",zrsy.vo.BillSubs.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("BillTypAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(billTyp != null && billTyp.getId() != null) {
				billTyp = new BillTypFacade().findById(billTyp);
				setJson(JSON.toJSONString(billTyp)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("BillTypAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(billTyp == null) billTyp = new BillTyp();
			int total = new BillTypFacade().amount(billTyp);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			billTyps = new BillTypFacade().find(billTyp,getPageVO());
			BillTypJson billTypJson = new BillTypJson();
			billTypJson.Rows = billTyps;
			billTypJson.Total = total;
			setJson(JSON.toJSONString(billTypJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("BillTypAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//billTyp.setCreateBy(getSession().getUserId());
			//billTyp.setCreateDate(new Date());
			//billTyp.setLastUpd(getSession().getUserId());
			//billTyp.setLastUpdDate(new Date());

			if(billTyp.getId() == null)
				new BillTypFacade().save(billTyp);
			else
				new BillTypFacade().update(billTyp);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("BillTypAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//billTyp.setCreateBy(getSession().getUserId());
			//billTyp.setCreateDate(new Date());
			//billTyp.setLastUpd(getSession().getUserId());
			//billTyp.setLastUpdDate(new Date());
			new BillTypFacade().update(billTyp);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("BillTypAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(billTyps != null && billTyps.size() > 0) {
				for(int i=0; i<billTyps.size();i++) {
					if(billTyps.get(i) != null) {
						//billTyps.get(i).setLastUpd(getSession().getUserId());
						//billTyps.get(i).setLastUpdDate(new Date());
						new BillTypFacade().submit(billTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("BillTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(billTyps != null && billTyps.size() > 0) {
				for(int i=0; i<billTyps.size();i++) {
					if(billTyps.get(i) != null) {
						//billTyps.get(i).setLastUpd(getSession().getUserId());
						//billTyps.get(i).setLastUpdDate(new Date());
						new BillTypFacade().submit(billTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("BillTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(billTyps != null && billTyps.size() > 0) {
				for(int i=0; i<billTyps.size();i++) {
					if(billTyps.get(i) != null) {
						//billTyps.get(i).setLastUpd(getSession().getUserId());
						//billTyps.get(i).setLastUpdDate(new Date());
						new BillTypFacade().update(billTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("BillTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(billTyps != null && billTyps.size() > 0) {
				for(int i=0; i<billTyps.size();i++) {
					if(billTyps.get(i) != null) {
						//billTyps.get(i).setLastUpd(getSession().getUserId());
						//billTyps.get(i).setLastUpdDate(new Date());
						new BillTypFacade().submit(billTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("BillTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(billTyps != null && billTyps.size() > 0) {
				for(int i=0; i<billTyps.size();i++) {
					if(billTyps.get(i) != null) {
						//billTyps.get(i).setLastUpd(getSession().getUserId());
						//billTyps.get(i).setLastUpdDate(new Date());
						new BillTypFacade().submit(billTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("BillTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(billTyps != null && billTyps.size() > 0) {
				for(int i=0; i<billTyps.size();i++) {
					if(billTyps.get(i) != null) {
						//billTyps.get(i).setLastUpd(getSession().getUserId());
						//billTyps.get(i).setLastUpdDate(new Date());
						new BillTypFacade().submit(billTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("BillTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(billTyps != null && billTyps.size() > 0) {
				for(int i=0; i<billTyps.size();i++) {
					if(billTyps.get(i) != null) {
						//billTyps.get(i).setLastUpd(getSession().getUserId());
						//billTyps.get(i).setLastUpdDate(new Date());
						new BillTypFacade().submit(billTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("BillTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(billTyps != null && billTyps.size() > 0) {
				for(int i=0; i<billTyps.size();i++) {
					if(billTyps.get(i) != null){
						//billTyps.get(i).setLastUpd(getSession().getUserId());
						//billTyps.get(i).setLastUpdDate(new Date());
						new BillTypFacade().submit(billTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("BillTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(billTyps != null && billTyps.size() > 0) {
				for(int i=0; i<billTyps.size();i++) {
					if(billTyps.get(i) != null){
						//billTyps.get(i).setLastUpd(getSession().getUserId());
						//billTyps.get(i).setLastUpdDate(new Date());
						new BillTypFacade().review(billTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("BillTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(billTyps != null && billTyps.size() > 0) {
				for(int i=0; i<billTyps.size();i++) {
					if(billTyps.get(i) != null) {
						//billTyps.get(i).setLastUpd(getSession().getUserId());
						//billTyps.get(i).setLastUpdDate(new Date());
						new BillTypFacade().review(billTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("BillTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(billTyps != null && billTyps.size() > 0) {
				for(int i=0; i<billTyps.size();i++) {
					if(billTyps.get(i) != null) {
						//billTyps.get(i).setLastUpd(getSession().getUserId());
						//billTyps.get(i).setLastUpdDate(new Date());
						new BillTypFacade().confirm(billTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("BillTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(billTyps != null && billTyps.size() > 0) {
				for(int i=0; i<billTyps.size();i++) {
					if(billTyps.get(i) != null) {
						//billTyps.get(i).setLastUpd(getSession().getUserId());
						//billTyps.get(i).setLastUpdDate(new Date());
						new BillTypFacade().confirm(billTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("BillTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(billTyps != null && billTyps.size() > 0) {
					for(int i=0; i<billTyps.size();i++) {
						if(billTyps.get(i) != null) {
							//billTyps.get(i).setLastUpd(getSession().getUserId());
							//billTyps.get(i).setLastUpdDate(new Date());
							new BillTypFacade().confirm(billTyps.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("BillTypAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<BillTyp> billTyps = new BillTypFacade().find(billTyp);
			if(billTyps != null && billTyps.size() > 0) {
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
					ws.addCell(new Label(index,1,"主表外键",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"单据类型",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"编码(比如:A01)",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<billTyps.size();i++) {
					row++;
					int m = 0;
					if(billTyps.get(i).getId() != null) 
						ws.addCell(new jxl.write.Number(m,row,billTyps.get(i).getId(),wcformat));
					m++;
					if(billTyps.get(i).getSubsId() != null) 
						ws.addCell(new jxl.write.Number(m,row,billTyps.get(i).getSubsId(),wcformat));
					m++;
					if(billTyps.get(i).getTypNm() != null) 
						ws.addCell(new jxl.write.Label(m,row,billTyps.get(i).getTypNm(),wcformat));
					m++;
					if(billTyps.get(i).getCode() != null) 
						ws.addCell(new jxl.write.Label(m,row,billTyps.get(i).getCode(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("BillTypListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(billTyps != null && billTyps.size() > 0) {
				for(int i=0; i<billTyps.size();i++) {
					if(billTyps.get(i) != null) {
						//billTyps.get(i).setLastUpd(getSession().getUserId());
						//billTyps.get(i).setLastUpdDate(new Date());
						new BillTypFacade().confirm(billTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("BillTypAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(billTyps != null && billTyps.size() > 0) {
				for(int i=0; i<billTyps.size();i++) {
					if(billTyps.get(i) != null) {
						//billTyps.get(i).setLastUpd(getSession().getUserId());
						//billTyps.get(i).setLastUpdDate(new Date());
						new BillTypFacade().confirm(billTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("BillTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<BillTyp> getBillTyps() {
		return billTyps;
	}
	public void setBillTyps(List<BillTyp> billTyps) {
		this.billTyps = billTyps;
	}
	public BillTyp getBillTyp() {
		return billTyp;
	}
	public void setBillTyp(BillTyp billTyp) {
		this.billTyp = billTyp;
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
	public java.util.List<zrsy.vo.BillSubs> getBillSubss() {
		return billSubss;
	}
	public void setBillSubss(java.util.List<zrsy.vo.BillSubs> billSubss){
		this.billSubss = billSubss;
	}
	public void addtoBillSubss(zrsy.vo.BillSubs billSubs){
		if(getBillSubss() == null) setBillSubss(new java.util.ArrayList<zrsy.vo.BillSubs>());
			getBillSubss().add(billSubs);
	}

}