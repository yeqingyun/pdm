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

import gnwf.facade.WfItemFacade;
import gnwf.vo.WfItem;
import gnwf.vo.json.WfItemJson;

public class WfItemAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfItem> wfItems;
	private WfItem wfItem = new WfItem();

	public String execute() throws Exception {
		try {
			if(wfItem != null && wfItem.getItemId() != null) {
				wfItem = new WfItemFacade().findById(wfItem);
				setJson(JSON.toJSONString(wfItem)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfItemAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(wfItem != null && wfItem.getItemId() != null) {
				//wfItem = new WfItemFacade().findById(wfItem);
				//setJson(JSON.toJSONString(wfItem)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfItemAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(wfItem != null && wfItem.getItemId() != null) {
				wfItem = new WfItemFacade().findById(wfItem);
				setJson(JSON.toJSONString(wfItem)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfItemAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(wfItem != null && wfItem.getItemId() != null) {
				wfItem = new WfItemFacade().findById(wfItem);
				setJson(JSON.toJSONString(wfItem)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfItemAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(wfItem == null) wfItem = new WfItem();
			int total = new WfItemFacade().amount(wfItem);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfItems = new WfItemFacade().find(wfItem,getPageVO());
			WfItemJson wfItemJson = new WfItemJson();
			wfItemJson.Rows = wfItems;
			wfItemJson.Total = total;
			setJson(JSON.toJSONString(wfItemJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfItemAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//wfItem.setCreateBy(getSession().getUserId());
			//wfItem.setCreateDate(new Date());
			//wfItem.setLastUpd(getSession().getUserId());
			//wfItem.setLastUpdDate(new Date());

			if(wfItem.getItemId() == null)
				new WfItemFacade().save(wfItem);
			else
				new WfItemFacade().update(wfItem);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfItemAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfItem.setCreateBy(getSession().getUserId());
			//wfItem.setCreateDate(new Date());
			//wfItem.setLastUpd(getSession().getUserId());
			//wfItem.setLastUpdDate(new Date());
			new WfItemFacade().update(wfItem);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfItemAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfItems != null && wfItems.size() > 0) {
				for(int i=0; i<wfItems.size();i++) {
					if(wfItems.get(i) != null) {
						//wfItems.get(i).setLastUpd(getSession().getUserId());
						//wfItems.get(i).setLastUpdDate(new Date());
						new WfItemFacade().submit(wfItems.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfItemAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfItems != null && wfItems.size() > 0) {
				for(int i=0; i<wfItems.size();i++) {
					if(wfItems.get(i) != null) {
						//wfItems.get(i).setLastUpd(getSession().getUserId());
						//wfItems.get(i).setLastUpdDate(new Date());
						new WfItemFacade().submit(wfItems.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfItemAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfItems != null && wfItems.size() > 0) {
				for(int i=0; i<wfItems.size();i++) {
					if(wfItems.get(i) != null) {
						//wfItems.get(i).setLastUpd(getSession().getUserId());
						//wfItems.get(i).setLastUpdDate(new Date());
						new WfItemFacade().update(wfItems.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfItemAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfItems != null && wfItems.size() > 0) {
				for(int i=0; i<wfItems.size();i++) {
					if(wfItems.get(i) != null) {
						//wfItems.get(i).setLastUpd(getSession().getUserId());
						//wfItems.get(i).setLastUpdDate(new Date());
						new WfItemFacade().submit(wfItems.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfItemAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfItems != null && wfItems.size() > 0) {
				for(int i=0; i<wfItems.size();i++) {
					if(wfItems.get(i) != null) {
						//wfItems.get(i).setLastUpd(getSession().getUserId());
						//wfItems.get(i).setLastUpdDate(new Date());
						new WfItemFacade().submit(wfItems.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfItemAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfItems != null && wfItems.size() > 0) {
				for(int i=0; i<wfItems.size();i++) {
					if(wfItems.get(i) != null) {
						//wfItems.get(i).setLastUpd(getSession().getUserId());
						//wfItems.get(i).setLastUpdDate(new Date());
						new WfItemFacade().submit(wfItems.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfItemAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfItems != null && wfItems.size() > 0) {
				for(int i=0; i<wfItems.size();i++) {
					if(wfItems.get(i) != null) {
						//wfItems.get(i).setLastUpd(getSession().getUserId());
						//wfItems.get(i).setLastUpdDate(new Date());
						new WfItemFacade().submit(wfItems.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfItemAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfItems != null && wfItems.size() > 0) {
				for(int i=0; i<wfItems.size();i++) {
					if(wfItems.get(i) != null){
						//wfItems.get(i).setLastUpd(getSession().getUserId());
						//wfItems.get(i).setLastUpdDate(new Date());
						new WfItemFacade().submit(wfItems.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfItemAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfItems != null && wfItems.size() > 0) {
				for(int i=0; i<wfItems.size();i++) {
					if(wfItems.get(i) != null){
						//wfItems.get(i).setLastUpd(getSession().getUserId());
						//wfItems.get(i).setLastUpdDate(new Date());
						new WfItemFacade().review(wfItems.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfItemAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfItems != null && wfItems.size() > 0) {
				for(int i=0; i<wfItems.size();i++) {
					if(wfItems.get(i) != null) {
						//wfItems.get(i).setLastUpd(getSession().getUserId());
						//wfItems.get(i).setLastUpdDate(new Date());
						new WfItemFacade().review(wfItems.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfItemAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfItems != null && wfItems.size() > 0) {
				for(int i=0; i<wfItems.size();i++) {
					if(wfItems.get(i) != null) {
						//wfItems.get(i).setLastUpd(getSession().getUserId());
						//wfItems.get(i).setLastUpdDate(new Date());
						new WfItemFacade().confirm(wfItems.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfItemAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfItems != null && wfItems.size() > 0) {
				for(int i=0; i<wfItems.size();i++) {
					if(wfItems.get(i) != null) {
						//wfItems.get(i).setLastUpd(getSession().getUserId());
						//wfItems.get(i).setLastUpdDate(new Date());
						new WfItemFacade().confirm(wfItems.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfItemAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfItems != null && wfItems.size() > 0) {
					for(int i=0; i<wfItems.size();i++) {
						if(wfItems.get(i) != null) {
							//wfItems.get(i).setLastUpd(getSession().getUserId());
							//wfItems.get(i).setLastUpdDate(new Date());
							new WfItemFacade().confirm(wfItems.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfItemAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfItem> wfItems = new WfItemFacade().find(wfItem);
			if(wfItems != null && wfItems.size() > 0) {
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
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfItems.size();i++) {
					row++;
					int m = 0;
					if(wfItems.get(i).getItemId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfItems.get(i).getItemId(),wcformat));
					m++;
					if(wfItems.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfItems.get(i).getStatus(),wcformat));
					m++;
					if(wfItems.get(i).getFlowId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfItems.get(i).getFlowId(),wcformat));
					m++;
					if(wfItems.get(i).getOrderBy() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfItems.get(i).getOrderBy(),wcformat));
					m++;
					if(wfItems.get(i).getItemNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfItems.get(i).getItemNo(),wcformat));
					m++;
					if(wfItems.get(i).getDesc2() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfItems.get(i).getDesc2(),wcformat));
					m++;
					if(wfItems.get(i).getDesc5() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfItems.get(i).getDesc5(),wcformat));
					m++;
					if(wfItems.get(i).getItemName() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfItems.get(i).getItemName(),wcformat));
					m++;
					if(wfItems.get(i).getDesc3() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfItems.get(i).getDesc3(),wcformat));
					m++;
					if(wfItems.get(i).getDesc4() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfItems.get(i).getDesc4(),wcformat));
					m++;
					if(wfItems.get(i).getDesc1() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfItems.get(i).getDesc1(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfItemListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfItems != null && wfItems.size() > 0) {
				for(int i=0; i<wfItems.size();i++) {
					if(wfItems.get(i) != null) {
						//wfItems.get(i).setLastUpd(getSession().getUserId());
						//wfItems.get(i).setLastUpdDate(new Date());
						new WfItemFacade().confirm(wfItems.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfItemAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfItems != null && wfItems.size() > 0) {
				for(int i=0; i<wfItems.size();i++) {
					if(wfItems.get(i) != null) {
						//wfItems.get(i).setLastUpd(getSession().getUserId());
						//wfItems.get(i).setLastUpdDate(new Date());
						new WfItemFacade().confirm(wfItems.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfItemAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfItem> getWfItems() {
		return wfItems;
	}
	public void setWfItems(List<WfItem> wfItems) {
		this.wfItems = wfItems;
	}
	public WfItem getWfItem() {
		return wfItem;
	}
	public void setWfItem(WfItem wfItem) {
		this.wfItem = wfItem;
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