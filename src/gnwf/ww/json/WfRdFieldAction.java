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

import gnwf.facade.WfRdFieldFacade;
import gnwf.vo.WfRdField;
import gnwf.vo.json.WfRdFieldJson;

public class WfRdFieldAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfRdField> wfRdFields;
	private WfRdField wfRdField = new WfRdField();
	private java.util.List<gnwf.vo.WfField> wfFields;
	private java.util.List<gnwf.vo.WfRd> wfRds;

	public String execute() throws Exception {
		try {
			if(wfRdField != null && wfRdField.getFieldId() != null) {
				wfRdField = new WfRdFieldFacade().findById(wfRdField);
				setJson(JSON.toJSONString(wfRdField)); 
			}
			wfFields = new gnwf.facade.WfFieldFacade().find("select "+gnwf.vo.WfField.SELF_FIELDS+" from WfField",gnwf.vo.WfField.SELF_FIELDS);
			wfRds = new gnwf.facade.WfRdFacade().find("select "+gnwf.vo.WfRd.SELF_FIELDS+" from WfRd",gnwf.vo.WfRd.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdFieldAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(wfRdField != null && wfRdField.getFieldId() != null) {
				//wfRdField = new WfRdFieldFacade().findById(wfRdField);
				//setJson(JSON.toJSONString(wfRdField)); 
			//}
			wfFields = new gnwf.facade.WfFieldFacade().find("select "+gnwf.vo.WfField.SELF_FIELDS+" from WfField",gnwf.vo.WfField.SELF_FIELDS);
			wfRds = new gnwf.facade.WfRdFacade().find("select "+gnwf.vo.WfRd.SELF_FIELDS+" from WfRd",gnwf.vo.WfRd.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdFieldAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(wfRdField != null && wfRdField.getFieldId() != null) {
				wfRdField = new WfRdFieldFacade().findById(wfRdField);
				setJson(JSON.toJSONString(wfRdField)); 
			}
			wfFields = new gnwf.facade.WfFieldFacade().find("select "+gnwf.vo.WfField.SELF_FIELDS+" from WfField",gnwf.vo.WfField.SELF_FIELDS);
			wfRds = new gnwf.facade.WfRdFacade().find("select "+gnwf.vo.WfRd.SELF_FIELDS+" from WfRd",gnwf.vo.WfRd.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdFieldAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(wfRdField != null && wfRdField.getFieldId() != null) {
				wfRdField = new WfRdFieldFacade().findById(wfRdField);
				setJson(JSON.toJSONString(wfRdField)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdFieldAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(wfRdField == null) wfRdField = new WfRdField();
			int total = new WfRdFieldFacade().amount(wfRdField);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfRdFields = new WfRdFieldFacade().find(wfRdField,getPageVO());
			WfRdFieldJson wfRdFieldJson = new WfRdFieldJson();
			wfRdFieldJson.Rows = wfRdFields;
			wfRdFieldJson.Total = total;
			setJson(JSON.toJSONString(wfRdFieldJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdFieldAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//wfRdField.setCreateBy(getSession().getUserId());
			//wfRdField.setCreateDate(new Date());
			//wfRdField.setLastUpd(getSession().getUserId());
			//wfRdField.setLastUpdDate(new Date());

			if(wfRdField.getFieldId() == null)
				new WfRdFieldFacade().save(wfRdField);
			else
				new WfRdFieldFacade().update(wfRdField);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfRdFieldAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfRdField.setCreateBy(getSession().getUserId());
			//wfRdField.setCreateDate(new Date());
			//wfRdField.setLastUpd(getSession().getUserId());
			//wfRdField.setLastUpdDate(new Date());
			new WfRdFieldFacade().update(wfRdField);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfRdFieldAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfRdFields != null && wfRdFields.size() > 0) {
				for(int i=0; i<wfRdFields.size();i++) {
					if(wfRdFields.get(i) != null) {
						//wfRdFields.get(i).setLastUpd(getSession().getUserId());
						//wfRdFields.get(i).setLastUpdDate(new Date());
						new WfRdFieldFacade().submit(wfRdFields.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfRdFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfRdFields != null && wfRdFields.size() > 0) {
				for(int i=0; i<wfRdFields.size();i++) {
					if(wfRdFields.get(i) != null) {
						//wfRdFields.get(i).setLastUpd(getSession().getUserId());
						//wfRdFields.get(i).setLastUpdDate(new Date());
						new WfRdFieldFacade().submit(wfRdFields.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfRdFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfRdFields != null && wfRdFields.size() > 0) {
				for(int i=0; i<wfRdFields.size();i++) {
					if(wfRdFields.get(i) != null) {
						//wfRdFields.get(i).setLastUpd(getSession().getUserId());
						//wfRdFields.get(i).setLastUpdDate(new Date());
						new WfRdFieldFacade().update(wfRdFields.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfRdFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfRdFields != null && wfRdFields.size() > 0) {
				for(int i=0; i<wfRdFields.size();i++) {
					if(wfRdFields.get(i) != null) {
						//wfRdFields.get(i).setLastUpd(getSession().getUserId());
						//wfRdFields.get(i).setLastUpdDate(new Date());
						new WfRdFieldFacade().submit(wfRdFields.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfRdFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfRdFields != null && wfRdFields.size() > 0) {
				for(int i=0; i<wfRdFields.size();i++) {
					if(wfRdFields.get(i) != null) {
						//wfRdFields.get(i).setLastUpd(getSession().getUserId());
						//wfRdFields.get(i).setLastUpdDate(new Date());
						new WfRdFieldFacade().submit(wfRdFields.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfRdFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfRdFields != null && wfRdFields.size() > 0) {
				for(int i=0; i<wfRdFields.size();i++) {
					if(wfRdFields.get(i) != null) {
						//wfRdFields.get(i).setLastUpd(getSession().getUserId());
						//wfRdFields.get(i).setLastUpdDate(new Date());
						new WfRdFieldFacade().submit(wfRdFields.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfRdFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfRdFields != null && wfRdFields.size() > 0) {
				for(int i=0; i<wfRdFields.size();i++) {
					if(wfRdFields.get(i) != null) {
						//wfRdFields.get(i).setLastUpd(getSession().getUserId());
						//wfRdFields.get(i).setLastUpdDate(new Date());
						new WfRdFieldFacade().submit(wfRdFields.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfRdFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfRdFields != null && wfRdFields.size() > 0) {
				for(int i=0; i<wfRdFields.size();i++) {
					if(wfRdFields.get(i) != null){
						//wfRdFields.get(i).setLastUpd(getSession().getUserId());
						//wfRdFields.get(i).setLastUpdDate(new Date());
						new WfRdFieldFacade().submit(wfRdFields.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfRdFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfRdFields != null && wfRdFields.size() > 0) {
				for(int i=0; i<wfRdFields.size();i++) {
					if(wfRdFields.get(i) != null){
						//wfRdFields.get(i).setLastUpd(getSession().getUserId());
						//wfRdFields.get(i).setLastUpdDate(new Date());
						new WfRdFieldFacade().review(wfRdFields.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfRdFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfRdFields != null && wfRdFields.size() > 0) {
				for(int i=0; i<wfRdFields.size();i++) {
					if(wfRdFields.get(i) != null) {
						//wfRdFields.get(i).setLastUpd(getSession().getUserId());
						//wfRdFields.get(i).setLastUpdDate(new Date());
						new WfRdFieldFacade().review(wfRdFields.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfRdFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfRdFields != null && wfRdFields.size() > 0) {
				for(int i=0; i<wfRdFields.size();i++) {
					if(wfRdFields.get(i) != null) {
						//wfRdFields.get(i).setLastUpd(getSession().getUserId());
						//wfRdFields.get(i).setLastUpdDate(new Date());
						new WfRdFieldFacade().confirm(wfRdFields.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfRdFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfRdFields != null && wfRdFields.size() > 0) {
				for(int i=0; i<wfRdFields.size();i++) {
					if(wfRdFields.get(i) != null) {
						//wfRdFields.get(i).setLastUpd(getSession().getUserId());
						//wfRdFields.get(i).setLastUpdDate(new Date());
						new WfRdFieldFacade().confirm(wfRdFields.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfRdFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfRdFields != null && wfRdFields.size() > 0) {
					for(int i=0; i<wfRdFields.size();i++) {
						if(wfRdFields.get(i) != null) {
							//wfRdFields.get(i).setLastUpd(getSession().getUserId());
							//wfRdFields.get(i).setLastUpdDate(new Date());
							new WfRdFieldFacade().confirm(wfRdFields.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfRdFieldAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfRdField> wfRdFields = new WfRdFieldFacade().find(wfRdField);
			if(wfRdFields != null && wfRdFields.size() > 0) {
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
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"集合序列",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfRdFields.size();i++) {
					row++;
					int m = 0;
					if(wfRdFields.get(i).getFieldId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfRdFields.get(i).getFieldId(),wcformat));
					m++;
					if(wfRdFields.get(i).getWfId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfRdFields.get(i).getWfId(),wcformat));
					m++;
					if(wfRdFields.get(i).getRowId() != 0) 
						ws.addCell(new jxl.write.Number(m,row,wfRdFields.get(i).getRowId(),wcformat));
					m++;
					if(wfRdFields.get(i).getWfNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfRdFields.get(i).getWfNo(),wcformat));
					m++;
					if(wfRdFields.get(i).getFieldText() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfRdFields.get(i).getFieldText(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfRdFieldListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfRdFields != null && wfRdFields.size() > 0) {
				for(int i=0; i<wfRdFields.size();i++) {
					if(wfRdFields.get(i) != null) {
						//wfRdFields.get(i).setLastUpd(getSession().getUserId());
						//wfRdFields.get(i).setLastUpdDate(new Date());
						new WfRdFieldFacade().confirm(wfRdFields.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfRdFieldAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfRdFields != null && wfRdFields.size() > 0) {
				for(int i=0; i<wfRdFields.size();i++) {
					if(wfRdFields.get(i) != null) {
						//wfRdFields.get(i).setLastUpd(getSession().getUserId());
						//wfRdFields.get(i).setLastUpdDate(new Date());
						new WfRdFieldFacade().confirm(wfRdFields.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfRdFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfRdField> getWfRdFields() {
		return wfRdFields;
	}
	public void setWfRdFields(List<WfRdField> wfRdFields) {
		this.wfRdFields = wfRdFields;
	}
	public WfRdField getWfRdField() {
		return wfRdField;
	}
	public void setWfRdField(WfRdField wfRdField) {
		this.wfRdField = wfRdField;
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
	public java.util.List<gnwf.vo.WfField> getWfFields() {
		return wfFields;
	}
	public void setWfFields(java.util.List<gnwf.vo.WfField> wfFields){
		this.wfFields = wfFields;
	}
	public void addtoWfFields(gnwf.vo.WfField wfField){
		if(getWfFields() == null) setWfFields(new java.util.ArrayList<gnwf.vo.WfField>());
			getWfFields().add(wfField);
	}
	public java.util.List<gnwf.vo.WfRd> getWfRds() {
		return wfRds;
	}
	public void setWfRds(java.util.List<gnwf.vo.WfRd> wfRds){
		this.wfRds = wfRds;
	}
	public void addtoWfRds(gnwf.vo.WfRd wfRd){
		if(getWfRds() == null) setWfRds(new java.util.ArrayList<gnwf.vo.WfRd>());
			getWfRds().add(wfRd);
	}

}