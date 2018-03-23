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

import zrsy.facade.BtnFacade;
import zrsy.vo.Btn;
import zrsy.vo.json.BtnJson;

public class BtnAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<Btn> btns;
	private Btn btn = new Btn();
	private java.util.List<zrsy.vo.GpBtn> gpBtns;
	private java.util.List<zrsy.vo.SyDef> syDefs;
	public String execute() throws Exception {
		try {
			if(btn != null && btn.getId() != null) {
				btn = new BtnFacade().findById(btn);
				setJson(JSON.toJSONString(btn)); 
			}
			syDefs = new zrsy.facade.SyDefFacade().find("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef where SyId= " + getUsrSession().getSyId(),zrsy.vo.SyDef.SELF_FIELDS);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("BtnAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(btn != null && btn.getId() != null) {
				//btn = new BtnFacade().findById(btn);
				//setJson(JSON.toJSONString(btn)); 
			//}
			syDefs = new zrsy.facade.SyDefFacade().find("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef where SyId = " + getUsrSession().getSyId(),zrsy.vo.SyDef.SELF_FIELDS);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("BtnAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(btn != null && btn.getId() != null) {
				btn = new BtnFacade().findById(btn);
				setJson(JSON.toJSONString(btn)); 
			}
			syDefs = new zrsy.facade.SyDefFacade().find("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef",zrsy.vo.SyDef.SELF_FIELDS);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("BtnAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(btn != null && btn.getId() != null) {
				btn = new BtnFacade().findById(btn);
				setJson(JSON.toJSONString(btn)); 
			}
			syDefs = new zrsy.facade.SyDefFacade().find("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef",zrsy.vo.SyDef.SELF_FIELDS);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("BtnAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(btn == null) btn = new Btn();
			int total = new BtnFacade().amount(btn);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			btn.setSyId(getUsrSession().getSyId());
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			btns = new BtnFacade().find(btn,getPageVO());
			BtnJson btnJson = new BtnJson();
			btnJson.Rows = btns;
			btnJson.Total = total;
			setJson(JSON.toJSONString(btnJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("BtnAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//btn.setCreateBy(getSession().getUserId());
			//btn.setCreateDate(new Date());
			//btn.setLastUpd(getSession().getUserId());
			//btn.setLastUpdDate(new Date());
			btn.setGpBtns(gpBtns);

			if(btn.getId() == null)
				new BtnFacade().save(btn);
			else
				new BtnFacade().update(btn);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("BtnAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//btn.setCreateBy(getSession().getUserId());
			//btn.setCreateDate(new Date());
			//btn.setLastUpd(getSession().getUserId());
			//btn.setLastUpdDate(new Date());
			new BtnFacade().update(btn);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("BtnAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(btns != null && btns.size() > 0) {
				for(int i=0; i<btns.size();i++) {
					if(btns.get(i) != null) {
						//btns.get(i).setLastUpd(getSession().getUserId());
						//btns.get(i).setLastUpdDate(new Date());
						new BtnFacade().submit(btns.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("BtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(btns != null && btns.size() > 0) {
				for(int i=0; i<btns.size();i++) {
					if(btns.get(i) != null) {
						//btns.get(i).setLastUpd(getSession().getUserId());
						//btns.get(i).setLastUpdDate(new Date());
						new BtnFacade().submit(btns.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("BtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(btns != null && btns.size() > 0) {
				for(int i=0; i<btns.size();i++) {
					if(btns.get(i) != null) {
						//btns.get(i).setLastUpd(getSession().getUserId());
						//btns.get(i).setLastUpdDate(new Date());
						new BtnFacade().update(btns.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("BtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(btns != null && btns.size() > 0) {
				for(int i=0; i<btns.size();i++) {
					if(btns.get(i) != null) {
						//btns.get(i).setLastUpd(getSession().getUserId());
						//btns.get(i).setLastUpdDate(new Date());
						new BtnFacade().submit(btns.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("BtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(btns != null && btns.size() > 0) {
				for(int i=0; i<btns.size();i++) {
					if(btns.get(i) != null) {
						//btns.get(i).setLastUpd(getSession().getUserId());
						//btns.get(i).setLastUpdDate(new Date());
						new BtnFacade().submit(btns.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("BtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(btns != null && btns.size() > 0) {
				for(int i=0; i<btns.size();i++) {
					if(btns.get(i) != null) {
						//btns.get(i).setLastUpd(getSession().getUserId());
						//btns.get(i).setLastUpdDate(new Date());
						new BtnFacade().submit(btns.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("BtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(btns != null && btns.size() > 0) {
				for(int i=0; i<btns.size();i++) {
					if(btns.get(i) != null) {
						//btns.get(i).setLastUpd(getSession().getUserId());
						//btns.get(i).setLastUpdDate(new Date());
						new BtnFacade().submit(btns.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("BtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(btns != null && btns.size() > 0) {
				for(int i=0; i<btns.size();i++) {
					if(btns.get(i) != null){
						//btns.get(i).setLastUpd(getSession().getUserId());
						//btns.get(i).setLastUpdDate(new Date());
						new BtnFacade().submit(btns.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("BtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(btns != null && btns.size() > 0) {
				for(int i=0; i<btns.size();i++) {
					if(btns.get(i) != null){
						//btns.get(i).setLastUpd(getSession().getUserId());
						//btns.get(i).setLastUpdDate(new Date());
						new BtnFacade().review(btns.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("BtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(btns != null && btns.size() > 0) {
				for(int i=0; i<btns.size();i++) {
					if(btns.get(i) != null) {
						//btns.get(i).setLastUpd(getSession().getUserId());
						//btns.get(i).setLastUpdDate(new Date());
						new BtnFacade().review(btns.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("BtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(btns != null && btns.size() > 0) {
				for(int i=0; i<btns.size();i++) {
					if(btns.get(i) != null) {
						//btns.get(i).setLastUpd(getSession().getUserId());
						//btns.get(i).setLastUpdDate(new Date());
						new BtnFacade().confirm(btns.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("BtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(btns != null && btns.size() > 0) {
				for(int i=0; i<btns.size();i++) {
					if(btns.get(i) != null) {
						//btns.get(i).setLastUpd(getSession().getUserId());
						//btns.get(i).setLastUpdDate(new Date());
						new BtnFacade().confirm(btns.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("BtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(btns != null && btns.size() > 0) {
					for(int i=0; i<btns.size();i++) {
						if(btns.get(i) != null) {
							//btns.get(i).setLastUpd(getSession().getUserId());
							//btns.get(i).setLastUpdDate(new Date());
							new BtnFacade().confirm(btns.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("BtnAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<Btn> btns = new BtnFacade().find(btn);
			if(btns != null && btns.size() > 0) {
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
					ws.addCell(new Label(index,1,"系统",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"线条",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"可见",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"排序",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"名称",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"单击事件",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"图标",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<btns.size();i++) {
					row++;
					int m = 0;
					if(btns.get(i).getId() != null) 
						ws.addCell(new jxl.write.Number(m,row,btns.get(i).getId(),wcformat));
					m++;
					if(btns.get(i).getSyId() != null) 
						ws.addCell(new jxl.write.Number(m,row,btns.get(i).getSyId(),wcformat));
					m++;
					if(btns.get(i).getLine() != null) 
						ws.addCell(new jxl.write.Number(m,row,btns.get(i).getLine(),wcformat));
					m++;
					if(btns.get(i).getDisable() != null) 
						ws.addCell(new jxl.write.Number(m,row,btns.get(i).getDisable(),wcformat));
					m++;
					if(btns.get(i).getSort() != null) 
						ws.addCell(new jxl.write.Number(m,row,btns.get(i).getSort(),wcformat));
					m++;
					if(btns.get(i).getText() != null) 
						ws.addCell(new jxl.write.Label(m,row,btns.get(i).getText(),wcformat));
					m++;
					if(btns.get(i).getClick() != null) 
						ws.addCell(new jxl.write.Label(m,row,btns.get(i).getClick(),wcformat));
					m++;
					if(btns.get(i).getIcon() != null) 
						ws.addCell(new jxl.write.Label(m,row,btns.get(i).getIcon(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("BtnListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(btns != null && btns.size() > 0) {
				for(int i=0; i<btns.size();i++) {
					if(btns.get(i) != null) {
						//btns.get(i).setLastUpd(getSession().getUserId());
						//btns.get(i).setLastUpdDate(new Date());
						new BtnFacade().confirm(btns.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("BtnAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(btns != null && btns.size() > 0) {
				for(int i=0; i<btns.size();i++) {
					if(btns.get(i) != null) {
						//btns.get(i).setLastUpd(getSession().getUserId());
						//btns.get(i).setLastUpdDate(new Date());
						new BtnFacade().confirm(btns.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("BtnAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<Btn> getBtns() {
		return btns;
	}
	public void setBtns(List<Btn> btns) {
		this.btns = btns;
	}
	public Btn getBtn() {
		return btn;
	}
	public void setBtn(Btn btn) {
		this.btn = btn;
	}
	public WritableWorkbook getWorkbook() {
		return workbook;
	}
	public void setWorkbook(WritableWorkbook workbook) {
		this.workbook = workbook;
	}
	public java.util.List<zrsy.vo.SyDef> getSyDefs() {
		return syDefs;
	}
	public void setSyDefs(java.util.List<zrsy.vo.SyDef> syDefs) {
		this.syDefs = syDefs;
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
	public java.util.List<zrsy.vo.GpBtn> getGpBtns() {
		return gpBtns;
	}
	public void setGpBtns(java.util.List<zrsy.vo.GpBtn> gpBtns){
		this.gpBtns = gpBtns;
	}
	public void addtoGpBtns(zrsy.vo.GpBtn gpBtn){
		if(getGpBtns() == null) setGpBtns(new java.util.ArrayList<zrsy.vo.GpBtn>());
			getGpBtns().add(gpBtn);
	}

}