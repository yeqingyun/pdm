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

import gnwf.facade.OptStoreFacade;
import gnwf.vo.OptStore;
import gnwf.vo.json.OptStoreJson;

public class OptStoreAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<OptStore> optStores;
	private OptStore optStore = new OptStore();

	public String execute() throws Exception {
		try {
			if(optStore != null && optStore.getMatlNo() != null) {
				optStore = new OptStoreFacade().findById(optStore);
				setJson(JSON.toJSONString(optStore)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("OptStoreAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(optStore != null && optStore.getMatlNo() != null) {
				//optStore = new OptStoreFacade().findById(optStore);
				//setJson(JSON.toJSONString(optStore)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("OptStoreAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(optStore != null && optStore.getMatlNo() != null) {
				optStore = new OptStoreFacade().findById(optStore);
				setJson(JSON.toJSONString(optStore)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("OptStoreAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(optStore != null && optStore.getMatlNo() != null) {
				optStore = new OptStoreFacade().findById(optStore);
				setJson(JSON.toJSONString(optStore)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("OptStoreAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(optStore == null) optStore = new OptStore();
			int total = new OptStoreFacade().amount(optStore);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			optStores = new OptStoreFacade().find(optStore,getPageVO());
			OptStoreJson optStoreJson = new OptStoreJson();
			optStoreJson.Rows = optStores;
			optStoreJson.Total = total;
			setJson(JSON.toJSONString(optStoreJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("OptStoreAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//optStore.setCreateBy(getSession().getUserId());
			//optStore.setCreateDate(new Date());
			//optStore.setLastUpd(getSession().getUserId());
			//optStore.setLastUpdDate(new Date());

			if(optStore.getMatlNo() == null)
				new OptStoreFacade().save(optStore);
			else
				new OptStoreFacade().update(optStore);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("OptStoreAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//optStore.setCreateBy(getSession().getUserId());
			//optStore.setCreateDate(new Date());
			//optStore.setLastUpd(getSession().getUserId());
			//optStore.setLastUpdDate(new Date());
			new OptStoreFacade().update(optStore);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("OptStoreAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(optStores != null && optStores.size() > 0) {
				for(int i=0; i<optStores.size();i++) {
					if(optStores.get(i) != null) {
						//optStores.get(i).setLastUpd(getSession().getUserId());
						//optStores.get(i).setLastUpdDate(new Date());
						new OptStoreFacade().submit(optStores.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("OptStoreAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(optStores != null && optStores.size() > 0) {
				for(int i=0; i<optStores.size();i++) {
					if(optStores.get(i) != null) {
						//optStores.get(i).setLastUpd(getSession().getUserId());
						//optStores.get(i).setLastUpdDate(new Date());
						new OptStoreFacade().submit(optStores.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("OptStoreAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(optStores != null && optStores.size() > 0) {
				for(int i=0; i<optStores.size();i++) {
					if(optStores.get(i) != null) {
						//optStores.get(i).setLastUpd(getSession().getUserId());
						//optStores.get(i).setLastUpdDate(new Date());
						new OptStoreFacade().update(optStores.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("OptStoreAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(optStores != null && optStores.size() > 0) {
				for(int i=0; i<optStores.size();i++) {
					if(optStores.get(i) != null) {
						//optStores.get(i).setLastUpd(getSession().getUserId());
						//optStores.get(i).setLastUpdDate(new Date());
						new OptStoreFacade().submit(optStores.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("OptStoreAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(optStores != null && optStores.size() > 0) {
				for(int i=0; i<optStores.size();i++) {
					if(optStores.get(i) != null) {
						//optStores.get(i).setLastUpd(getSession().getUserId());
						//optStores.get(i).setLastUpdDate(new Date());
						new OptStoreFacade().submit(optStores.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("OptStoreAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(optStores != null && optStores.size() > 0) {
				for(int i=0; i<optStores.size();i++) {
					if(optStores.get(i) != null) {
						//optStores.get(i).setLastUpd(getSession().getUserId());
						//optStores.get(i).setLastUpdDate(new Date());
						new OptStoreFacade().submit(optStores.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("OptStoreAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(optStores != null && optStores.size() > 0) {
				for(int i=0; i<optStores.size();i++) {
					if(optStores.get(i) != null) {
						//optStores.get(i).setLastUpd(getSession().getUserId());
						//optStores.get(i).setLastUpdDate(new Date());
						new OptStoreFacade().submit(optStores.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("OptStoreAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(optStores != null && optStores.size() > 0) {
				for(int i=0; i<optStores.size();i++) {
					if(optStores.get(i) != null){
						//optStores.get(i).setLastUpd(getSession().getUserId());
						//optStores.get(i).setLastUpdDate(new Date());
						new OptStoreFacade().submit(optStores.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("OptStoreAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(optStores != null && optStores.size() > 0) {
				for(int i=0; i<optStores.size();i++) {
					if(optStores.get(i) != null){
						//optStores.get(i).setLastUpd(getSession().getUserId());
						//optStores.get(i).setLastUpdDate(new Date());
						new OptStoreFacade().review(optStores.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("OptStoreAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(optStores != null && optStores.size() > 0) {
				for(int i=0; i<optStores.size();i++) {
					if(optStores.get(i) != null) {
						//optStores.get(i).setLastUpd(getSession().getUserId());
						//optStores.get(i).setLastUpdDate(new Date());
						new OptStoreFacade().review(optStores.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("OptStoreAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(optStores != null && optStores.size() > 0) {
				for(int i=0; i<optStores.size();i++) {
					if(optStores.get(i) != null) {
						//optStores.get(i).setLastUpd(getSession().getUserId());
						//optStores.get(i).setLastUpdDate(new Date());
						new OptStoreFacade().confirm(optStores.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("OptStoreAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(optStores != null && optStores.size() > 0) {
				for(int i=0; i<optStores.size();i++) {
					if(optStores.get(i) != null) {
						//optStores.get(i).setLastUpd(getSession().getUserId());
						//optStores.get(i).setLastUpdDate(new Date());
						new OptStoreFacade().confirm(optStores.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("OptStoreAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(optStores != null && optStores.size() > 0) {
					for(int i=0; i<optStores.size();i++) {
						if(optStores.get(i) != null) {
							//optStores.get(i).setLastUpd(getSession().getUserId());
							//optStores.get(i).setLastUpdDate(new Date());
							new OptStoreFacade().confirm(optStores.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("OptStoreAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<OptStore> optStores = new OptStoreFacade().find(optStore);
			if(optStores != null && optStores.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"物料编码",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"物料组编号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"物料版本",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"物料名称",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"描述",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"优选类型",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"物料类型",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"批量",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"是否联板",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"状态",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"创建人",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"创建日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"最后更新",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"更新日期",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<optStores.size();i++) {
					row++;
					int m = 0;
					if(optStores.get(i).getMatlNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,optStores.get(i).getMatlNo(),wcformat));
					m++;
					if(optStores.get(i).getGpCode() != null) 
						ws.addCell(new jxl.write.Label(m,row,optStores.get(i).getGpCode(),wcformat));
					m++;
					if(optStores.get(i).getMatlRev() != null) 
						ws.addCell(new jxl.write.Label(m,row,optStores.get(i).getMatlRev(),wcformat));
					m++;
					if(optStores.get(i).getMatlNm() != null) 
						ws.addCell(new jxl.write.Label(m,row,optStores.get(i).getMatlNm(),wcformat));
					m++;
					if(optStores.get(i).getMatlDesc() != null) 
						ws.addCell(new jxl.write.Label(m,row,optStores.get(i).getMatlDesc(),wcformat));
					m++;
					if(optStores.get(i).getOptTyp() != null) 
						ws.addCell(new jxl.write.Number(m,row,optStores.get(i).getOptTyp(),wcformat));
					m++;
					if(optStores.get(i).getMatlTyp() != null) 
						ws.addCell(new jxl.write.Number(m,row,optStores.get(i).getMatlTyp(),wcformat));
					m++;
					if(optStores.get(i).getLotSize() != null) 
						ws.addCell(new jxl.write.Number(m,row,optStores.get(i).getLotSize(),wcformat));
					m++;
					if(optStores.get(i).getIsPanel() != null) 
						ws.addCell(new jxl.write.Number(m,row,optStores.get(i).getIsPanel(),wcformat));
					m++;
					if(optStores.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,optStores.get(i).getStatus(),wcformat));
					m++;
					if(optStores.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,optStores.get(i).getCreateBy(),wcformat));
					m++;
					if(optStores.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,optStores.get(i).getCreateDate(),wcformat));
					m++;
					if(optStores.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,optStores.get(i).getLastUpd(),wcformat));
					m++;
					if(optStores.get(i).getLastDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,optStores.get(i).getLastDate(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("OptStoreListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(optStores != null && optStores.size() > 0) {
				for(int i=0; i<optStores.size();i++) {
					if(optStores.get(i) != null) {
						//optStores.get(i).setLastUpd(getSession().getUserId());
						//optStores.get(i).setLastUpdDate(new Date());
						new OptStoreFacade().confirm(optStores.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("OptStoreAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(optStores != null && optStores.size() > 0) {
				for(int i=0; i<optStores.size();i++) {
					if(optStores.get(i) != null) {
						//optStores.get(i).setLastUpd(getSession().getUserId());
						//optStores.get(i).setLastUpdDate(new Date());
						new OptStoreFacade().confirm(optStores.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("OptStoreAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<OptStore> getOptStores() {
		return optStores;
	}
	public void setOptStores(List<OptStore> optStores) {
		this.optStores = optStores;
	}
	public OptStore getOptStore() {
		return optStore;
	}
	public void setOptStore(OptStore optStore) {
		this.optStore = optStore;
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