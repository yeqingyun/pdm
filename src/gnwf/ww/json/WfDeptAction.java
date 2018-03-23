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

import gnwf.facade.WfDeptFacade;
import gnwf.vo.WfDept;
import gnwf.vo.json.WfDeptJson;

public class WfDeptAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfDept> wfDepts;
	private WfDept wfDept = new WfDept();
	private java.util.List<gnwf.vo.WfCfg> wfCfgs;

	public String execute() throws Exception {
		try {
			if(wfDept != null && wfDept.getFlowId() != null) {
				wfDept = new WfDeptFacade().findById(wfDept);
				setJson(JSON.toJSONString(wfDept)); 
			}
			wfCfgs = new gnwf.facade.WfCfgFacade().find("select "+gnwf.vo.WfCfg.SELF_FIELDS+" from WfCfg",gnwf.vo.WfCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfDeptAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(wfDept != null && wfDept.getFlowId() != null) {
				//wfDept = new WfDeptFacade().findById(wfDept);
				//setJson(JSON.toJSONString(wfDept)); 
			//}
			wfCfgs = new gnwf.facade.WfCfgFacade().find("select "+gnwf.vo.WfCfg.SELF_FIELDS+" from WfCfg",gnwf.vo.WfCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfDeptAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(wfDept != null && wfDept.getFlowId() != null) {
				wfDept = new WfDeptFacade().findById(wfDept);
				setJson(JSON.toJSONString(wfDept)); 
			}
			wfCfgs = new gnwf.facade.WfCfgFacade().find("select "+gnwf.vo.WfCfg.SELF_FIELDS+" from WfCfg",gnwf.vo.WfCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfDeptAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(wfDept != null && wfDept.getFlowId() != null) {
				wfDept = new WfDeptFacade().findById(wfDept);
				setJson(JSON.toJSONString(wfDept)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfDeptAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(wfDept == null) wfDept = new WfDept();
			int total = new WfDeptFacade().amount(wfDept);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfDepts = new WfDeptFacade().find(wfDept,getPageVO());
			WfDeptJson wfDeptJson = new WfDeptJson();
			wfDeptJson.Rows = wfDepts;
			wfDeptJson.Total = total;
			setJson(JSON.toJSONString(wfDeptJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfDeptAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//wfDept.setCreateBy(getSession().getUserId());
			//wfDept.setCreateDate(new Date());
			//wfDept.setLastUpd(getSession().getUserId());
			//wfDept.setLastUpdDate(new Date());

			if(wfDept.getFlowId() == null)
				new WfDeptFacade().save(wfDept);
			else
				new WfDeptFacade().update(wfDept);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfDeptAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfDept.setCreateBy(getSession().getUserId());
			//wfDept.setCreateDate(new Date());
			//wfDept.setLastUpd(getSession().getUserId());
			//wfDept.setLastUpdDate(new Date());
			new WfDeptFacade().update(wfDept);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfDeptAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfDepts != null && wfDepts.size() > 0) {
				for(int i=0; i<wfDepts.size();i++) {
					if(wfDepts.get(i) != null) {
						//wfDepts.get(i).setLastUpd(getSession().getUserId());
						//wfDepts.get(i).setLastUpdDate(new Date());
						new WfDeptFacade().submit(wfDepts.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfDeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfDepts != null && wfDepts.size() > 0) {
				for(int i=0; i<wfDepts.size();i++) {
					if(wfDepts.get(i) != null) {
						//wfDepts.get(i).setLastUpd(getSession().getUserId());
						//wfDepts.get(i).setLastUpdDate(new Date());
						new WfDeptFacade().submit(wfDepts.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfDeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfDepts != null && wfDepts.size() > 0) {
				for(int i=0; i<wfDepts.size();i++) {
					if(wfDepts.get(i) != null) {
						//wfDepts.get(i).setLastUpd(getSession().getUserId());
						//wfDepts.get(i).setLastUpdDate(new Date());
						new WfDeptFacade().update(wfDepts.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfDeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfDepts != null && wfDepts.size() > 0) {
				for(int i=0; i<wfDepts.size();i++) {
					if(wfDepts.get(i) != null) {
						//wfDepts.get(i).setLastUpd(getSession().getUserId());
						//wfDepts.get(i).setLastUpdDate(new Date());
						new WfDeptFacade().submit(wfDepts.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfDeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfDepts != null && wfDepts.size() > 0) {
				for(int i=0; i<wfDepts.size();i++) {
					if(wfDepts.get(i) != null) {
						//wfDepts.get(i).setLastUpd(getSession().getUserId());
						//wfDepts.get(i).setLastUpdDate(new Date());
						new WfDeptFacade().submit(wfDepts.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfDeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfDepts != null && wfDepts.size() > 0) {
				for(int i=0; i<wfDepts.size();i++) {
					if(wfDepts.get(i) != null) {
						//wfDepts.get(i).setLastUpd(getSession().getUserId());
						//wfDepts.get(i).setLastUpdDate(new Date());
						new WfDeptFacade().submit(wfDepts.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfDeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfDepts != null && wfDepts.size() > 0) {
				for(int i=0; i<wfDepts.size();i++) {
					if(wfDepts.get(i) != null) {
						//wfDepts.get(i).setLastUpd(getSession().getUserId());
						//wfDepts.get(i).setLastUpdDate(new Date());
						new WfDeptFacade().submit(wfDepts.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfDeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfDepts != null && wfDepts.size() > 0) {
				for(int i=0; i<wfDepts.size();i++) {
					if(wfDepts.get(i) != null){
						//wfDepts.get(i).setLastUpd(getSession().getUserId());
						//wfDepts.get(i).setLastUpdDate(new Date());
						new WfDeptFacade().submit(wfDepts.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfDeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfDepts != null && wfDepts.size() > 0) {
				for(int i=0; i<wfDepts.size();i++) {
					if(wfDepts.get(i) != null){
						//wfDepts.get(i).setLastUpd(getSession().getUserId());
						//wfDepts.get(i).setLastUpdDate(new Date());
						new WfDeptFacade().review(wfDepts.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfDeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfDepts != null && wfDepts.size() > 0) {
				for(int i=0; i<wfDepts.size();i++) {
					if(wfDepts.get(i) != null) {
						//wfDepts.get(i).setLastUpd(getSession().getUserId());
						//wfDepts.get(i).setLastUpdDate(new Date());
						new WfDeptFacade().review(wfDepts.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfDeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfDepts != null && wfDepts.size() > 0) {
				for(int i=0; i<wfDepts.size();i++) {
					if(wfDepts.get(i) != null) {
						//wfDepts.get(i).setLastUpd(getSession().getUserId());
						//wfDepts.get(i).setLastUpdDate(new Date());
						new WfDeptFacade().confirm(wfDepts.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfDeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfDepts != null && wfDepts.size() > 0) {
				for(int i=0; i<wfDepts.size();i++) {
					if(wfDepts.get(i) != null) {
						//wfDepts.get(i).setLastUpd(getSession().getUserId());
						//wfDepts.get(i).setLastUpdDate(new Date());
						new WfDeptFacade().confirm(wfDepts.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfDeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfDepts != null && wfDepts.size() > 0) {
					for(int i=0; i<wfDepts.size();i++) {
						if(wfDepts.get(i) != null) {
							//wfDepts.get(i).setLastUpd(getSession().getUserId());
							//wfDepts.get(i).setLastUpdDate(new Date());
							new WfDeptFacade().confirm(wfDepts.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfDeptAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfDept> wfDepts = new WfDeptFacade().find(wfDept);
			if(wfDepts != null && wfDepts.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"工作流定义ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"部门ID",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfDepts.size();i++) {
					row++;
					int m = 0;
					if(wfDepts.get(i).getFlowId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfDepts.get(i).getFlowId(),wcformat));
					m++;
					if(wfDepts.get(i).getDeptId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfDepts.get(i).getDeptId(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfDeptListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfDepts != null && wfDepts.size() > 0) {
				for(int i=0; i<wfDepts.size();i++) {
					if(wfDepts.get(i) != null) {
						//wfDepts.get(i).setLastUpd(getSession().getUserId());
						//wfDepts.get(i).setLastUpdDate(new Date());
						new WfDeptFacade().confirm(wfDepts.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfDeptAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfDepts != null && wfDepts.size() > 0) {
				for(int i=0; i<wfDepts.size();i++) {
					if(wfDepts.get(i) != null) {
						//wfDepts.get(i).setLastUpd(getSession().getUserId());
						//wfDepts.get(i).setLastUpdDate(new Date());
						new WfDeptFacade().confirm(wfDepts.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfDeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfDept> getWfDepts() {
		return wfDepts;
	}
	public void setWfDepts(List<WfDept> wfDepts) {
		this.wfDepts = wfDepts;
	}
	public WfDept getWfDept() {
		return wfDept;
	}
	public void setWfDept(WfDept wfDept) {
		this.wfDept = wfDept;
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