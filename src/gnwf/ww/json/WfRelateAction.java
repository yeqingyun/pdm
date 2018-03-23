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

import gnwf.facade.WfRelateFacade;
import gnwf.vo.WfRelate;
import gnwf.vo.json.WfRelateJson;

public class WfRelateAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfRelate> wfRelates;
	private WfRelate wfRelate = new WfRelate();
	private java.util.List<gnwf.vo.WfCfg> wfCfgs;

	public String execute() throws Exception {
		try {
			if(wfRelate != null && wfRelate.getFlowId() != null) {
				wfRelate = new WfRelateFacade().findById(wfRelate);
				setJson(JSON.toJSONString(wfRelate)); 
			}
			wfCfgs = new gnwf.facade.WfCfgFacade().find("select "+gnwf.vo.WfCfg.SELF_FIELDS+" from WfCfg",gnwf.vo.WfCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRelateAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(wfRelate != null && wfRelate.getFlowId() != null) {
				//wfRelate = new WfRelateFacade().findById(wfRelate);
				//setJson(JSON.toJSONString(wfRelate)); 
			//}
			wfCfgs = new gnwf.facade.WfCfgFacade().find("select "+gnwf.vo.WfCfg.SELF_FIELDS+" from WfCfg",gnwf.vo.WfCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRelateAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(wfRelate != null && wfRelate.getFlowId() != null) {
				wfRelate = new WfRelateFacade().findById(wfRelate);
				setJson(JSON.toJSONString(wfRelate)); 
			}
			wfCfgs = new gnwf.facade.WfCfgFacade().find("select "+gnwf.vo.WfCfg.SELF_FIELDS+" from WfCfg",gnwf.vo.WfCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRelateAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(wfRelate != null && wfRelate.getFlowId() != null) {
				wfRelate = new WfRelateFacade().findById(wfRelate);
				setJson(JSON.toJSONString(wfRelate)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRelateAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(wfRelate == null) wfRelate = new WfRelate();
			int total = new WfRelateFacade().amount(wfRelate);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfRelates = new WfRelateFacade().find(wfRelate,getPageVO());
			WfRelateJson wfRelateJson = new WfRelateJson();
			wfRelateJson.Rows = wfRelates;
			wfRelateJson.Total = total;
			setJson(JSON.toJSONString(wfRelateJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRelateAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//wfRelate.setCreateBy(getSession().getUserId());
			//wfRelate.setCreateDate(new Date());
			//wfRelate.setLastUpd(getSession().getUserId());
			//wfRelate.setLastUpdDate(new Date());

			if(wfRelate.getFlowId() == null)
				new WfRelateFacade().save(wfRelate);
			else
				new WfRelateFacade().update(wfRelate);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfRelateAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfRelate.setCreateBy(getSession().getUserId());
			//wfRelate.setCreateDate(new Date());
			//wfRelate.setLastUpd(getSession().getUserId());
			//wfRelate.setLastUpdDate(new Date());
			new WfRelateFacade().update(wfRelate);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfRelateAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfRelates != null && wfRelates.size() > 0) {
				for(int i=0; i<wfRelates.size();i++) {
					if(wfRelates.get(i) != null) {
						//wfRelates.get(i).setLastUpd(getSession().getUserId());
						//wfRelates.get(i).setLastUpdDate(new Date());
						new WfRelateFacade().submit(wfRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfRelates != null && wfRelates.size() > 0) {
				for(int i=0; i<wfRelates.size();i++) {
					if(wfRelates.get(i) != null) {
						//wfRelates.get(i).setLastUpd(getSession().getUserId());
						//wfRelates.get(i).setLastUpdDate(new Date());
						new WfRelateFacade().submit(wfRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfRelates != null && wfRelates.size() > 0) {
				for(int i=0; i<wfRelates.size();i++) {
					if(wfRelates.get(i) != null) {
						//wfRelates.get(i).setLastUpd(getSession().getUserId());
						//wfRelates.get(i).setLastUpdDate(new Date());
						new WfRelateFacade().update(wfRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfRelates != null && wfRelates.size() > 0) {
				for(int i=0; i<wfRelates.size();i++) {
					if(wfRelates.get(i) != null) {
						//wfRelates.get(i).setLastUpd(getSession().getUserId());
						//wfRelates.get(i).setLastUpdDate(new Date());
						new WfRelateFacade().submit(wfRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfRelates != null && wfRelates.size() > 0) {
				for(int i=0; i<wfRelates.size();i++) {
					if(wfRelates.get(i) != null) {
						//wfRelates.get(i).setLastUpd(getSession().getUserId());
						//wfRelates.get(i).setLastUpdDate(new Date());
						new WfRelateFacade().submit(wfRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfRelates != null && wfRelates.size() > 0) {
				for(int i=0; i<wfRelates.size();i++) {
					if(wfRelates.get(i) != null) {
						//wfRelates.get(i).setLastUpd(getSession().getUserId());
						//wfRelates.get(i).setLastUpdDate(new Date());
						new WfRelateFacade().submit(wfRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfRelates != null && wfRelates.size() > 0) {
				for(int i=0; i<wfRelates.size();i++) {
					if(wfRelates.get(i) != null) {
						//wfRelates.get(i).setLastUpd(getSession().getUserId());
						//wfRelates.get(i).setLastUpdDate(new Date());
						new WfRelateFacade().submit(wfRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfRelates != null && wfRelates.size() > 0) {
				for(int i=0; i<wfRelates.size();i++) {
					if(wfRelates.get(i) != null){
						//wfRelates.get(i).setLastUpd(getSession().getUserId());
						//wfRelates.get(i).setLastUpdDate(new Date());
						new WfRelateFacade().submit(wfRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfRelates != null && wfRelates.size() > 0) {
				for(int i=0; i<wfRelates.size();i++) {
					if(wfRelates.get(i) != null){
						//wfRelates.get(i).setLastUpd(getSession().getUserId());
						//wfRelates.get(i).setLastUpdDate(new Date());
						new WfRelateFacade().review(wfRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfRelates != null && wfRelates.size() > 0) {
				for(int i=0; i<wfRelates.size();i++) {
					if(wfRelates.get(i) != null) {
						//wfRelates.get(i).setLastUpd(getSession().getUserId());
						//wfRelates.get(i).setLastUpdDate(new Date());
						new WfRelateFacade().review(wfRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfRelates != null && wfRelates.size() > 0) {
				for(int i=0; i<wfRelates.size();i++) {
					if(wfRelates.get(i) != null) {
						//wfRelates.get(i).setLastUpd(getSession().getUserId());
						//wfRelates.get(i).setLastUpdDate(new Date());
						new WfRelateFacade().confirm(wfRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfRelates != null && wfRelates.size() > 0) {
				for(int i=0; i<wfRelates.size();i++) {
					if(wfRelates.get(i) != null) {
						//wfRelates.get(i).setLastUpd(getSession().getUserId());
						//wfRelates.get(i).setLastUpdDate(new Date());
						new WfRelateFacade().confirm(wfRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfRelates != null && wfRelates.size() > 0) {
					for(int i=0; i<wfRelates.size();i++) {
						if(wfRelates.get(i) != null) {
							//wfRelates.get(i).setLastUpd(getSession().getUserId());
							//wfRelates.get(i).setLastUpdDate(new Date());
							new WfRelateFacade().confirm(wfRelates.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfRelateAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfRelate> wfRelates = new WfRelateFacade().find(wfRelate);
			if(wfRelates != null && wfRelates.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"工作流ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"相关流程ID",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfRelates.size();i++) {
					row++;
					int m = 0;
					if(wfRelates.get(i).getFlowId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfRelates.get(i).getFlowId(),wcformat));
					m++;
					if(wfRelates.get(i).getRelateId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfRelates.get(i).getRelateId(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfRelateListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfRelates != null && wfRelates.size() > 0) {
				for(int i=0; i<wfRelates.size();i++) {
					if(wfRelates.get(i) != null) {
						//wfRelates.get(i).setLastUpd(getSession().getUserId());
						//wfRelates.get(i).setLastUpdDate(new Date());
						new WfRelateFacade().confirm(wfRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfRelateAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfRelates != null && wfRelates.size() > 0) {
				for(int i=0; i<wfRelates.size();i++) {
					if(wfRelates.get(i) != null) {
						//wfRelates.get(i).setLastUpd(getSession().getUserId());
						//wfRelates.get(i).setLastUpdDate(new Date());
						new WfRelateFacade().confirm(wfRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfRelate> getWfRelates() {
		return wfRelates;
	}
	public void setWfRelates(List<WfRelate> wfRelates) {
		this.wfRelates = wfRelates;
	}
	public WfRelate getWfRelate() {
		return wfRelate;
	}
	public void setWfRelate(WfRelate wfRelate) {
		this.wfRelate = wfRelate;
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
	public java.util.List<gnwf.vo.WfCfg> getWfCfgs() {
		return wfCfgs;
	}
	public void setWfCfgs(java.util.List<gnwf.vo.WfCfg> wfCfgs){
		this.wfCfgs = wfCfgs;
	}
	public void addtoWfCfgs(gnwf.vo.WfCfg wfCfg){
		if(getWfCfgs() == null) setWfCfgs(new java.util.ArrayList<gnwf.vo.WfCfg>());
			getWfCfgs().add(wfCfg);
	}

}