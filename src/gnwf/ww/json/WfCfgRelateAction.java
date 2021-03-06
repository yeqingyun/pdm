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

import gnwf.facade.WfCfgRelateFacade;
import gnwf.vo.WfCfgRelate;
import gnwf.vo.json.WfCfgRelateJson;

public class WfCfgRelateAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfCfgRelate> wfCfgRelates;
	private WfCfgRelate wfCfgRelate = new WfCfgRelate();

	public String execute() throws Exception {
		try {
			if(wfCfgRelate != null && wfCfgRelate.getRelateId() != null) {
				wfCfgRelate = new WfCfgRelateFacade().findById(wfCfgRelate);
				setJson(JSON.toJSONString(wfCfgRelate)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfCfgRelateAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(wfCfgRelate != null && wfCfgRelate.getRelateId() != null) {
				//wfCfgRelate = new WfCfgRelateFacade().findById(wfCfgRelate);
				//setJson(JSON.toJSONString(wfCfgRelate)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfCfgRelateAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(wfCfgRelate != null && wfCfgRelate.getRelateId() != null) {
				wfCfgRelate = new WfCfgRelateFacade().findById(wfCfgRelate);
				setJson(JSON.toJSONString(wfCfgRelate)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfCfgRelateAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(wfCfgRelate != null && wfCfgRelate.getRelateId() != null) {
				wfCfgRelate = new WfCfgRelateFacade().findById(wfCfgRelate);
				setJson(JSON.toJSONString(wfCfgRelate)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfCfgRelateAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(wfCfgRelate == null) wfCfgRelate = new WfCfgRelate();
			int total = new WfCfgRelateFacade().amount(wfCfgRelate);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfCfgRelates = new WfCfgRelateFacade().find(wfCfgRelate,getPageVO());
			WfCfgRelateJson wfCfgRelateJson = new WfCfgRelateJson();
			wfCfgRelateJson.Rows = wfCfgRelates;
			wfCfgRelateJson.Total = total;
			setJson(JSON.toJSONString(wfCfgRelateJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfCfgRelateAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//wfCfgRelate.setCreateBy(getSession().getUserId());
			//wfCfgRelate.setCreateDate(new Date());
			//wfCfgRelate.setLastUpd(getSession().getUserId());
			//wfCfgRelate.setLastUpdDate(new Date());

			if(wfCfgRelate.getRelateId() == null)
				new WfCfgRelateFacade().save(wfCfgRelate);
			else
				new WfCfgRelateFacade().update(wfCfgRelate);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfCfgRelateAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfCfgRelate.setCreateBy(getSession().getUserId());
			//wfCfgRelate.setCreateDate(new Date());
			//wfCfgRelate.setLastUpd(getSession().getUserId());
			//wfCfgRelate.setLastUpdDate(new Date());
			new WfCfgRelateFacade().update(wfCfgRelate);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfCfgRelateAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfCfgRelates != null && wfCfgRelates.size() > 0) {
				for(int i=0; i<wfCfgRelates.size();i++) {
					if(wfCfgRelates.get(i) != null) {
						//wfCfgRelates.get(i).setLastUpd(getSession().getUserId());
						//wfCfgRelates.get(i).setLastUpdDate(new Date());
						new WfCfgRelateFacade().submit(wfCfgRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfCfgRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfCfgRelates != null && wfCfgRelates.size() > 0) {
				for(int i=0; i<wfCfgRelates.size();i++) {
					if(wfCfgRelates.get(i) != null) {
						//wfCfgRelates.get(i).setLastUpd(getSession().getUserId());
						//wfCfgRelates.get(i).setLastUpdDate(new Date());
						new WfCfgRelateFacade().submit(wfCfgRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfCfgRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfCfgRelates != null && wfCfgRelates.size() > 0) {
				for(int i=0; i<wfCfgRelates.size();i++) {
					if(wfCfgRelates.get(i) != null) {
						//wfCfgRelates.get(i).setLastUpd(getSession().getUserId());
						//wfCfgRelates.get(i).setLastUpdDate(new Date());
						new WfCfgRelateFacade().update(wfCfgRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfCfgRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfCfgRelates != null && wfCfgRelates.size() > 0) {
				for(int i=0; i<wfCfgRelates.size();i++) {
					if(wfCfgRelates.get(i) != null) {
						//wfCfgRelates.get(i).setLastUpd(getSession().getUserId());
						//wfCfgRelates.get(i).setLastUpdDate(new Date());
						new WfCfgRelateFacade().submit(wfCfgRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfCfgRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfCfgRelates != null && wfCfgRelates.size() > 0) {
				for(int i=0; i<wfCfgRelates.size();i++) {
					if(wfCfgRelates.get(i) != null) {
						//wfCfgRelates.get(i).setLastUpd(getSession().getUserId());
						//wfCfgRelates.get(i).setLastUpdDate(new Date());
						new WfCfgRelateFacade().submit(wfCfgRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfCfgRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfCfgRelates != null && wfCfgRelates.size() > 0) {
				for(int i=0; i<wfCfgRelates.size();i++) {
					if(wfCfgRelates.get(i) != null) {
						//wfCfgRelates.get(i).setLastUpd(getSession().getUserId());
						//wfCfgRelates.get(i).setLastUpdDate(new Date());
						new WfCfgRelateFacade().submit(wfCfgRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfCfgRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfCfgRelates != null && wfCfgRelates.size() > 0) {
				for(int i=0; i<wfCfgRelates.size();i++) {
					if(wfCfgRelates.get(i) != null) {
						//wfCfgRelates.get(i).setLastUpd(getSession().getUserId());
						//wfCfgRelates.get(i).setLastUpdDate(new Date());
						new WfCfgRelateFacade().submit(wfCfgRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfCfgRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfCfgRelates != null && wfCfgRelates.size() > 0) {
				for(int i=0; i<wfCfgRelates.size();i++) {
					if(wfCfgRelates.get(i) != null){
						//wfCfgRelates.get(i).setLastUpd(getSession().getUserId());
						//wfCfgRelates.get(i).setLastUpdDate(new Date());
						new WfCfgRelateFacade().submit(wfCfgRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfCfgRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfCfgRelates != null && wfCfgRelates.size() > 0) {
				for(int i=0; i<wfCfgRelates.size();i++) {
					if(wfCfgRelates.get(i) != null){
						//wfCfgRelates.get(i).setLastUpd(getSession().getUserId());
						//wfCfgRelates.get(i).setLastUpdDate(new Date());
						new WfCfgRelateFacade().review(wfCfgRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfCfgRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfCfgRelates != null && wfCfgRelates.size() > 0) {
				for(int i=0; i<wfCfgRelates.size();i++) {
					if(wfCfgRelates.get(i) != null) {
						//wfCfgRelates.get(i).setLastUpd(getSession().getUserId());
						//wfCfgRelates.get(i).setLastUpdDate(new Date());
						new WfCfgRelateFacade().review(wfCfgRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfCfgRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfCfgRelates != null && wfCfgRelates.size() > 0) {
				for(int i=0; i<wfCfgRelates.size();i++) {
					if(wfCfgRelates.get(i) != null) {
						//wfCfgRelates.get(i).setLastUpd(getSession().getUserId());
						//wfCfgRelates.get(i).setLastUpdDate(new Date());
						new WfCfgRelateFacade().confirm(wfCfgRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfCfgRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfCfgRelates != null && wfCfgRelates.size() > 0) {
				for(int i=0; i<wfCfgRelates.size();i++) {
					if(wfCfgRelates.get(i) != null) {
						//wfCfgRelates.get(i).setLastUpd(getSession().getUserId());
						//wfCfgRelates.get(i).setLastUpdDate(new Date());
						new WfCfgRelateFacade().confirm(wfCfgRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfCfgRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfCfgRelates != null && wfCfgRelates.size() > 0) {
					for(int i=0; i<wfCfgRelates.size();i++) {
						if(wfCfgRelates.get(i) != null) {
							//wfCfgRelates.get(i).setLastUpd(getSession().getUserId());
							//wfCfgRelates.get(i).setLastUpdDate(new Date());
							new WfCfgRelateFacade().confirm(wfCfgRelates.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfCfgRelateAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfCfgRelate> wfCfgRelates = new WfCfgRelateFacade().find(wfCfgRelate);
			if(wfCfgRelates != null && wfCfgRelates.size() > 0) {
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


				int row = 2;
				for(int i=0; i<wfCfgRelates.size();i++) {
					row++;
					int m = 0;
					if(wfCfgRelates.get(i).getRelateId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfCfgRelates.get(i).getRelateId(),wcformat));
					m++;
					if(wfCfgRelates.get(i).getFlowId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfCfgRelates.get(i).getFlowId(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfCfgRelateListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfCfgRelates != null && wfCfgRelates.size() > 0) {
				for(int i=0; i<wfCfgRelates.size();i++) {
					if(wfCfgRelates.get(i) != null) {
						//wfCfgRelates.get(i).setLastUpd(getSession().getUserId());
						//wfCfgRelates.get(i).setLastUpdDate(new Date());
						new WfCfgRelateFacade().confirm(wfCfgRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfCfgRelateAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfCfgRelates != null && wfCfgRelates.size() > 0) {
				for(int i=0; i<wfCfgRelates.size();i++) {
					if(wfCfgRelates.get(i) != null) {
						//wfCfgRelates.get(i).setLastUpd(getSession().getUserId());
						//wfCfgRelates.get(i).setLastUpdDate(new Date());
						new WfCfgRelateFacade().confirm(wfCfgRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfCfgRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfCfgRelate> getWfCfgRelates() {
		return wfCfgRelates;
	}
	public void setWfCfgRelates(List<WfCfgRelate> wfCfgRelates) {
		this.wfCfgRelates = wfCfgRelates;
	}
	public WfCfgRelate getWfCfgRelate() {
		return wfCfgRelate;
	}
	public void setWfCfgRelate(WfCfgRelate wfCfgRelate) {
		this.wfCfgRelate = wfCfgRelate;
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