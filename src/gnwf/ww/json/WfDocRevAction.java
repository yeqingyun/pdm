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

import gnwf.facade.WfDocRevFacade;
import gnwf.vo.WfDocRev;
import gnwf.vo.json.WfDocRevJson;

public class WfDocRevAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfDocRev> wfDocRevs;
	private WfDocRev wfDocRev = new WfDocRev();
	private java.util.List<gnwf.vo.WfDoc> wfDocs;

	public String execute() throws Exception {
		try {
			if(wfDocRev != null && wfDocRev.getDocId() != null) {
				wfDocRev = new WfDocRevFacade().findById(wfDocRev);
				setJson(JSON.toJSONString(wfDocRev)); 
			}
			wfDocs = new gnwf.facade.WfDocFacade().find("select "+gnwf.vo.WfDoc.SELF_FIELDS+" from WfDoc",gnwf.vo.WfDoc.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfDocRevAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(wfDocRev != null && wfDocRev.getDocId() != null) {
				//wfDocRev = new WfDocRevFacade().findById(wfDocRev);
				//setJson(JSON.toJSONString(wfDocRev)); 
			//}
			wfDocs = new gnwf.facade.WfDocFacade().find("select "+gnwf.vo.WfDoc.SELF_FIELDS+" from WfDoc",gnwf.vo.WfDoc.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfDocRevAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(wfDocRev != null && wfDocRev.getDocId() != null) {
				wfDocRev = new WfDocRevFacade().findById(wfDocRev);
				setJson(JSON.toJSONString(wfDocRev)); 
			}
			wfDocs = new gnwf.facade.WfDocFacade().find("select "+gnwf.vo.WfDoc.SELF_FIELDS+" from WfDoc",gnwf.vo.WfDoc.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfDocRevAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(wfDocRev != null && wfDocRev.getDocId() != null) {
				wfDocRev = new WfDocRevFacade().findById(wfDocRev);
				setJson(JSON.toJSONString(wfDocRev)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfDocRevAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(wfDocRev == null) wfDocRev = new WfDocRev();
			int total = new WfDocRevFacade().amount(wfDocRev);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfDocRevs = new WfDocRevFacade().find(wfDocRev,getPageVO());
			WfDocRevJson wfDocRevJson = new WfDocRevJson();
			wfDocRevJson.Rows = wfDocRevs;
			wfDocRevJson.Total = total;
			setJson(JSON.toJSONString(wfDocRevJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfDocRevAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//wfDocRev.setCreateBy(getSession().getUserId());
			//wfDocRev.setCreateDate(new Date());
			//wfDocRev.setLastUpd(getSession().getUserId());
			//wfDocRev.setLastUpdDate(new Date());

			if(wfDocRev.getDocId() == null)
				new WfDocRevFacade().save(wfDocRev);
			else
				new WfDocRevFacade().update(wfDocRev);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfDocRevAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfDocRev.setCreateBy(getSession().getUserId());
			//wfDocRev.setCreateDate(new Date());
			//wfDocRev.setLastUpd(getSession().getUserId());
			//wfDocRev.setLastUpdDate(new Date());
			new WfDocRevFacade().update(wfDocRev);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfDocRevAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfDocRevs != null && wfDocRevs.size() > 0) {
				for(int i=0; i<wfDocRevs.size();i++) {
					if(wfDocRevs.get(i) != null) {
						//wfDocRevs.get(i).setLastUpd(getSession().getUserId());
						//wfDocRevs.get(i).setLastUpdDate(new Date());
						new WfDocRevFacade().submit(wfDocRevs.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfDocRevAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfDocRevs != null && wfDocRevs.size() > 0) {
				for(int i=0; i<wfDocRevs.size();i++) {
					if(wfDocRevs.get(i) != null) {
						//wfDocRevs.get(i).setLastUpd(getSession().getUserId());
						//wfDocRevs.get(i).setLastUpdDate(new Date());
						new WfDocRevFacade().submit(wfDocRevs.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfDocRevAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfDocRevs != null && wfDocRevs.size() > 0) {
				for(int i=0; i<wfDocRevs.size();i++) {
					if(wfDocRevs.get(i) != null) {
						//wfDocRevs.get(i).setLastUpd(getSession().getUserId());
						//wfDocRevs.get(i).setLastUpdDate(new Date());
						new WfDocRevFacade().update(wfDocRevs.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfDocRevAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfDocRevs != null && wfDocRevs.size() > 0) {
				for(int i=0; i<wfDocRevs.size();i++) {
					if(wfDocRevs.get(i) != null) {
						//wfDocRevs.get(i).setLastUpd(getSession().getUserId());
						//wfDocRevs.get(i).setLastUpdDate(new Date());
						new WfDocRevFacade().submit(wfDocRevs.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfDocRevAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfDocRevs != null && wfDocRevs.size() > 0) {
				for(int i=0; i<wfDocRevs.size();i++) {
					if(wfDocRevs.get(i) != null) {
						//wfDocRevs.get(i).setLastUpd(getSession().getUserId());
						//wfDocRevs.get(i).setLastUpdDate(new Date());
						new WfDocRevFacade().submit(wfDocRevs.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfDocRevAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfDocRevs != null && wfDocRevs.size() > 0) {
				for(int i=0; i<wfDocRevs.size();i++) {
					if(wfDocRevs.get(i) != null) {
						//wfDocRevs.get(i).setLastUpd(getSession().getUserId());
						//wfDocRevs.get(i).setLastUpdDate(new Date());
						new WfDocRevFacade().submit(wfDocRevs.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfDocRevAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfDocRevs != null && wfDocRevs.size() > 0) {
				for(int i=0; i<wfDocRevs.size();i++) {
					if(wfDocRevs.get(i) != null) {
						//wfDocRevs.get(i).setLastUpd(getSession().getUserId());
						//wfDocRevs.get(i).setLastUpdDate(new Date());
						new WfDocRevFacade().submit(wfDocRevs.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfDocRevAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfDocRevs != null && wfDocRevs.size() > 0) {
				for(int i=0; i<wfDocRevs.size();i++) {
					if(wfDocRevs.get(i) != null){
						//wfDocRevs.get(i).setLastUpd(getSession().getUserId());
						//wfDocRevs.get(i).setLastUpdDate(new Date());
						new WfDocRevFacade().submit(wfDocRevs.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfDocRevAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfDocRevs != null && wfDocRevs.size() > 0) {
				for(int i=0; i<wfDocRevs.size();i++) {
					if(wfDocRevs.get(i) != null){
						//wfDocRevs.get(i).setLastUpd(getSession().getUserId());
						//wfDocRevs.get(i).setLastUpdDate(new Date());
						new WfDocRevFacade().review(wfDocRevs.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfDocRevAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfDocRevs != null && wfDocRevs.size() > 0) {
				for(int i=0; i<wfDocRevs.size();i++) {
					if(wfDocRevs.get(i) != null) {
						//wfDocRevs.get(i).setLastUpd(getSession().getUserId());
						//wfDocRevs.get(i).setLastUpdDate(new Date());
						new WfDocRevFacade().review(wfDocRevs.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfDocRevAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfDocRevs != null && wfDocRevs.size() > 0) {
				for(int i=0; i<wfDocRevs.size();i++) {
					if(wfDocRevs.get(i) != null) {
						//wfDocRevs.get(i).setLastUpd(getSession().getUserId());
						//wfDocRevs.get(i).setLastUpdDate(new Date());
						new WfDocRevFacade().confirm(wfDocRevs.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfDocRevAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfDocRevs != null && wfDocRevs.size() > 0) {
				for(int i=0; i<wfDocRevs.size();i++) {
					if(wfDocRevs.get(i) != null) {
						//wfDocRevs.get(i).setLastUpd(getSession().getUserId());
						//wfDocRevs.get(i).setLastUpdDate(new Date());
						new WfDocRevFacade().confirm(wfDocRevs.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfDocRevAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfDocRevs != null && wfDocRevs.size() > 0) {
					for(int i=0; i<wfDocRevs.size();i++) {
						if(wfDocRevs.get(i) != null) {
							//wfDocRevs.get(i).setLastUpd(getSession().getUserId());
							//wfDocRevs.get(i).setLastUpdDate(new Date());
							new WfDocRevFacade().confirm(wfDocRevs.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfDocRevAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfDocRev> wfDocRevs = new WfDocRevFacade().find(wfDocRev);
			if(wfDocRevs != null && wfDocRevs.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"文档ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"任务ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"评审人",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"工作流编号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"评审时间",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"评审内容",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"评审ID",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfDocRevs.size();i++) {
					row++;
					int m = 0;
					if(wfDocRevs.get(i).getDocId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfDocRevs.get(i).getDocId(),wcformat));
					m++;
					if(wfDocRevs.get(i).getTaskId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfDocRevs.get(i).getTaskId(),wcformat));
					m++;
					if(wfDocRevs.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfDocRevs.get(i).getCreateBy(),wcformat));
					m++;
					if(wfDocRevs.get(i).getWfNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfDocRevs.get(i).getWfNo(),wcformat));
					m++;
					if(wfDocRevs.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfDocRevs.get(i).getCreateDate(),wcformat));
					m++;
					if(wfDocRevs.get(i).getRevText() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfDocRevs.get(i).getRevText(),wcformat));
					m++;
					if(wfDocRevs.get(i).getRevId() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfDocRevs.get(i).getRevId(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfDocRevListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfDocRevs != null && wfDocRevs.size() > 0) {
				for(int i=0; i<wfDocRevs.size();i++) {
					if(wfDocRevs.get(i) != null) {
						//wfDocRevs.get(i).setLastUpd(getSession().getUserId());
						//wfDocRevs.get(i).setLastUpdDate(new Date());
						new WfDocRevFacade().confirm(wfDocRevs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfDocRevAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfDocRevs != null && wfDocRevs.size() > 0) {
				for(int i=0; i<wfDocRevs.size();i++) {
					if(wfDocRevs.get(i) != null) {
						//wfDocRevs.get(i).setLastUpd(getSession().getUserId());
						//wfDocRevs.get(i).setLastUpdDate(new Date());
						new WfDocRevFacade().confirm(wfDocRevs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfDocRevAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfDocRev> getWfDocRevs() {
		return wfDocRevs;
	}
	public void setWfDocRevs(List<WfDocRev> wfDocRevs) {
		this.wfDocRevs = wfDocRevs;
	}
	public WfDocRev getWfDocRev() {
		return wfDocRev;
	}
	public void setWfDocRev(WfDocRev wfDocRev) {
		this.wfDocRev = wfDocRev;
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
	public java.util.List<gnwf.vo.WfDoc> getWfDocs() {
		return wfDocs;
	}
	public void setWfDocs(java.util.List<gnwf.vo.WfDoc> wfDocs){
		this.wfDocs = wfDocs;
	}
	public void addtoWfDocs(gnwf.vo.WfDoc wfDoc){
		if(getWfDocs() == null) setWfDocs(new java.util.ArrayList<gnwf.vo.WfDoc>());
			getWfDocs().add(wfDoc);
	}

}