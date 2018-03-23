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

import zrprjt.facade.PrjtTypFacade;
import zrprjt.vo.PrjtTyp;
import zrprjt.vo.json.PrjtTypJson;

public class PrjtTypAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<PrjtTyp> prjtTyps;
	private PrjtTyp prjtTyp = new PrjtTyp();
	private java.util.List<zrprjt.vo.PrjtDef> prjtDefs;

	public String execute() throws Exception {
		try {
			if(prjtTyp != null && prjtTyp.getTypId() != null) {
				prjtTyp = new PrjtTypFacade().findById(prjtTyp);
				setJson(JSON.toJSONString(prjtTyp)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtTypAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(prjtTyp != null && prjtTyp.getTypId() != null) {
				//prjtTyp = new PrjtTypFacade().findById(prjtTyp);
				//setJson(JSON.toJSONString(prjtTyp)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtTypAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(prjtTyp != null && prjtTyp.getTypId() != null) {
				prjtTyp = new PrjtTypFacade().findById(prjtTyp);
				setJson(JSON.toJSONString(prjtTyp)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtTypAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(prjtTyp != null && prjtTyp.getTypId() != null) {
				prjtTyp = new PrjtTypFacade().findById(prjtTyp);
				setJson(JSON.toJSONString(prjtTyp)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtTypAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(prjtTyp == null) prjtTyp = new PrjtTyp();
			int total = new PrjtTypFacade().amount(prjtTyp);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			prjtTyps = new PrjtTypFacade().find(prjtTyp,getPageVO());
			PrjtTypJson prjtTypJson = new PrjtTypJson();
			prjtTypJson.Rows = prjtTyps;
			prjtTypJson.Total = total;
			setJson(JSON.toJSONString(prjtTypJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtTypAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			prjtTyp.setPrjtDefs(prjtDefs);

			if(prjtTyp.getTypId() == null){
				prjtTyp.setCreateBy(getUsrSession().getId());
				prjtTyp.setCreateDate(new Date());
				prjtTyp.setLastUpd(getUsrSession().getId());
				prjtTyp.setLastDate(new Date());
				new PrjtTypFacade().save(prjtTyp);
				}
			else{
				prjtTyp.setLastUpd(getUsrSession().getId());
				prjtTyp.setLastDate(new Date());
				new PrjtTypFacade().update(prjtTyp);
			}
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("PrjtTypAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//prjtTyp.setCreateBy(getSession().getUserId());
			//prjtTyp.setCreateDate(new Date());
			//prjtTyp.setLastUpd(getSession().getUserId());
			//prjtTyp.setLastUpdDate(new Date());
			new PrjtTypFacade().update(prjtTyp);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("PrjtTypAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(prjtTyps != null && prjtTyps.size() > 0) {
				for(int i=0; i<prjtTyps.size();i++) {
					if(prjtTyps.get(i) != null) {
						//prjtTyps.get(i).setLastUpd(getSession().getUserId());
						//prjtTyps.get(i).setLastUpdDate(new Date());
						new PrjtTypFacade().submit(prjtTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("PrjtTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(prjtTyps != null && prjtTyps.size() > 0) {
				for(int i=0; i<prjtTyps.size();i++) {
					if(prjtTyps.get(i) != null) {
						//prjtTyps.get(i).setLastUpd(getSession().getUserId());
						//prjtTyps.get(i).setLastUpdDate(new Date());
						new PrjtTypFacade().submit(prjtTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("PrjtTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(prjtTyps != null && prjtTyps.size() > 0) {
				for(int i=0; i<prjtTyps.size();i++) {
					if(prjtTyps.get(i) != null) {
						//prjtTyps.get(i).setLastUpd(getSession().getUserId());
						//prjtTyps.get(i).setLastUpdDate(new Date());
						new PrjtTypFacade().update(prjtTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("PrjtTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(prjtTyps != null && prjtTyps.size() > 0) {
				for(int i=0; i<prjtTyps.size();i++) {
					if(prjtTyps.get(i) != null) {
						//prjtTyps.get(i).setLastUpd(getSession().getUserId());
						//prjtTyps.get(i).setLastUpdDate(new Date());
						new PrjtTypFacade().submit(prjtTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("PrjtTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(prjtTyps != null && prjtTyps.size() > 0) {
				for(int i=0; i<prjtTyps.size();i++) {
					if(prjtTyps.get(i) != null) {
						//prjtTyps.get(i).setLastUpd(getSession().getUserId());
						//prjtTyps.get(i).setLastUpdDate(new Date());
						new PrjtTypFacade().submit(prjtTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("PrjtTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(prjtTyps != null && prjtTyps.size() > 0) {
				for(int i=0; i<prjtTyps.size();i++) {
					if(prjtTyps.get(i) != null) {
						//prjtTyps.get(i).setLastUpd(getSession().getUserId());
						//prjtTyps.get(i).setLastUpdDate(new Date());
						new PrjtTypFacade().submit(prjtTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("PrjtTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(prjtTyps != null && prjtTyps.size() > 0) {
				for(int i=0; i<prjtTyps.size();i++) {
					if(prjtTyps.get(i) != null) {
						//prjtTyps.get(i).setLastUpd(getSession().getUserId());
						//prjtTyps.get(i).setLastUpdDate(new Date());
						new PrjtTypFacade().submit(prjtTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("PrjtTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(prjtTyps != null && prjtTyps.size() > 0) {
				for(int i=0; i<prjtTyps.size();i++) {
					if(prjtTyps.get(i) != null){
						//prjtTyps.get(i).setLastUpd(getSession().getUserId());
						//prjtTyps.get(i).setLastUpdDate(new Date());
						new PrjtTypFacade().submit(prjtTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("PrjtTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(prjtTyps != null && prjtTyps.size() > 0) {
				for(int i=0; i<prjtTyps.size();i++) {
					if(prjtTyps.get(i) != null){
						//prjtTyps.get(i).setLastUpd(getSession().getUserId());
						//prjtTyps.get(i).setLastUpdDate(new Date());
						new PrjtTypFacade().review(prjtTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("PrjtTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(prjtTyps != null && prjtTyps.size() > 0) {
				for(int i=0; i<prjtTyps.size();i++) {
					if(prjtTyps.get(i) != null) {
						//prjtTyps.get(i).setLastUpd(getSession().getUserId());
						//prjtTyps.get(i).setLastUpdDate(new Date());
						new PrjtTypFacade().review(prjtTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("PrjtTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(prjtTyps != null && prjtTyps.size() > 0) {
				for(int i=0; i<prjtTyps.size();i++) {
					if(prjtTyps.get(i) != null) {
						//prjtTyps.get(i).setLastUpd(getSession().getUserId());
						//prjtTyps.get(i).setLastUpdDate(new Date());
						new PrjtTypFacade().confirm(prjtTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("PrjtTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(prjtTyps != null && prjtTyps.size() > 0) {
				for(int i=0; i<prjtTyps.size();i++) {
					if(prjtTyps.get(i) != null) {
						//prjtTyps.get(i).setLastUpd(getSession().getUserId());
						//prjtTyps.get(i).setLastUpdDate(new Date());
						new PrjtTypFacade().confirm(prjtTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("PrjtTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(prjtTyps != null && prjtTyps.size() > 0) {
					for(int i=0; i<prjtTyps.size();i++) {
						if(prjtTyps.get(i) != null) {
							//prjtTyps.get(i).setLastUpd(getSession().getUserId());
							//prjtTyps.get(i).setLastUpdDate(new Date());
							new PrjtTypFacade().confirm(prjtTyps.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("PrjtTypAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<PrjtTyp> prjtTyps = new PrjtTypFacade().find(prjtTyp);
			if(prjtTyps != null && prjtTyps.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"分类ID",wcformat));
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
					ws.addCell(new Label(index,1,"项目分类",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"备注",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<prjtTyps.size();i++) {
					row++;
					int m = 0;
					if(prjtTyps.get(i).getTypId() != null) 
						ws.addCell(new jxl.write.Number(m,row,prjtTyps.get(i).getTypId(),wcformat));
					m++;
					if(prjtTyps.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,prjtTyps.get(i).getStatus(),wcformat));
					m++;
					if(prjtTyps.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,prjtTyps.get(i).getCreateBy(),wcformat));
					m++;
					if(prjtTyps.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,prjtTyps.get(i).getLastUpd(),wcformat));
					m++;
					if(prjtTyps.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,prjtTyps.get(i).getCreateDate(),wcformat));
					m++;
					if(prjtTyps.get(i).getLastDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,prjtTyps.get(i).getLastDate(),wcformat));
					m++;
					if(prjtTyps.get(i).getTypNm() != null) 
						ws.addCell(new jxl.write.Label(m,row,prjtTyps.get(i).getTypNm(),wcformat));
					m++;
					if(prjtTyps.get(i).getRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,prjtTyps.get(i).getRemark(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("PrjtTypListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(prjtTyps != null && prjtTyps.size() > 0) {
				for(int i=0; i<prjtTyps.size();i++) {
					if(prjtTyps.get(i) != null) {
						//prjtTyps.get(i).setLastUpd(getSession().getUserId());
						//prjtTyps.get(i).setLastUpdDate(new Date());
						new PrjtTypFacade().confirm(prjtTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("PrjtTypAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(prjtTyps != null && prjtTyps.size() > 0) {
				for(int i=0; i<prjtTyps.size();i++) {
					if(prjtTyps.get(i) != null) {
						//prjtTyps.get(i).setLastUpd(getSession().getUserId());
						//prjtTyps.get(i).setLastUpdDate(new Date());
						new PrjtTypFacade().confirm(prjtTyps.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("PrjtTypAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<PrjtTyp> getPrjtTyps() {
		return prjtTyps;
	}
	public void setPrjtTyps(List<PrjtTyp> prjtTyps) {
		this.prjtTyps = prjtTyps;
	}
	public PrjtTyp getPrjtTyp() {
		return prjtTyp;
	}
	public void setPrjtTyp(PrjtTyp prjtTyp) {
		this.prjtTyp = prjtTyp;
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