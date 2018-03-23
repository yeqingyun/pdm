package zrprjt.ww.json;

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

import zrprjt.ww.MSG;
import zrprjt.ww.BasicAction;

import zrprjt.facade.SchWfFacade;
import zrprjt.vo.SchWf;
import zrprjt.vo.json.SchWfJson;

public class SchWfAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<SchWf> schWfs;
	private SchWf schWf = new SchWf();
	private java.util.List<zrprjt.vo.SchCfg> schCfgs;
	

	public String execute() throws Exception {
		try {
			if(schWf != null && schWf.getSchId() != null) {
				schWf = new SchWfFacade().findById(schWf);
				setJson(JSON.toJSONString(schWf)); 
			}
			schCfgs = new zrprjt.facade.SchCfgFacade().find("select "+zrprjt.vo.SchCfg.SELF_FIELDS+" from SchCfg",zrprjt.vo.SchCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchWfAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(schWf != null && schWf.getSchId() != null) {
				//schWf = new SchWfFacade().findById(schWf);
				//setJson(JSON.toJSONString(schWf)); 
			//}
			schCfgs = new zrprjt.facade.SchCfgFacade().find("select "+zrprjt.vo.SchCfg.SELF_FIELDS+" from SchCfg",zrprjt.vo.SchCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchWfAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(schWf != null && schWf.getSchId() != null) {
				schWf = new SchWfFacade().findById(schWf);
				setJson(JSON.toJSONString(schWf)); 
			}
			schCfgs = new zrprjt.facade.SchCfgFacade().find("select "+zrprjt.vo.SchCfg.SELF_FIELDS+" from SchCfg",zrprjt.vo.SchCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchWfAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(schWf != null && schWf.getSchId() != null) {
				schWf = new SchWfFacade().findById(schWf);
				setJson(JSON.toJSONString(schWf)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchWfAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(schWf == null) schWf = new SchWf();
			int total = new SchWfFacade().amount(schWf);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			schWfs = new SchWfFacade().find(schWf,getPageVO());
			SchWfJson schWfJson = new SchWfJson();
			schWfJson.Rows = schWfs;
			schWfJson.Total = total;
			setJson(JSON.toJSONString(schWfJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchWfAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//schWf.setCreateBy(getSession().getUserId());
			//schWf.setCreateDate(new Date());
			//schWf.setLastUpd(getSession().getUserId());
			//schWf.setLastUpdDate(new Date());

			if(schWf.getSchId() == null)
				new SchWfFacade().save(schWf);
			else
				new SchWfFacade().update(schWf);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("SchWfAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//schWf.setCreateBy(getSession().getUserId());
			//schWf.setCreateDate(new Date());
			//schWf.setLastUpd(getSession().getUserId());
			//schWf.setLastUpdDate(new Date());
			new SchWfFacade().update(schWf);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("SchWfAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(schWfs != null && schWfs.size() > 0) {
				for(int i=0; i<schWfs.size();i++) {
					if(schWfs.get(i) != null) {
						//schWfs.get(i).setLastUpd(getSession().getUserId());
						//schWfs.get(i).setLastUpdDate(new Date());
						new SchWfFacade().submit(schWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("SchWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(schWfs != null && schWfs.size() > 0) {
				for(int i=0; i<schWfs.size();i++) {
					if(schWfs.get(i) != null) {
						//schWfs.get(i).setLastUpd(getSession().getUserId());
						//schWfs.get(i).setLastUpdDate(new Date());
						new SchWfFacade().submit(schWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("SchWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(schWfs != null && schWfs.size() > 0) {
				for(int i=0; i<schWfs.size();i++) {
					if(schWfs.get(i) != null) {
						//schWfs.get(i).setLastUpd(getSession().getUserId());
						//schWfs.get(i).setLastUpdDate(new Date());
						new SchWfFacade().update(schWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("SchWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(schWfs != null && schWfs.size() > 0) {
				for(int i=0; i<schWfs.size();i++) {
					if(schWfs.get(i) != null) {
						//schWfs.get(i).setLastUpd(getSession().getUserId());
						//schWfs.get(i).setLastUpdDate(new Date());
						new SchWfFacade().submit(schWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("SchWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(schWfs != null && schWfs.size() > 0) {
				for(int i=0; i<schWfs.size();i++) {
					if(schWfs.get(i) != null) {
						//schWfs.get(i).setLastUpd(getSession().getUserId());
						//schWfs.get(i).setLastUpdDate(new Date());
						new SchWfFacade().submit(schWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("SchWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(schWfs != null && schWfs.size() > 0) {
				for(int i=0; i<schWfs.size();i++) {
					if(schWfs.get(i) != null) {
						//schWfs.get(i).setLastUpd(getSession().getUserId());
						//schWfs.get(i).setLastUpdDate(new Date());
						new SchWfFacade().submit(schWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("SchWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(schWfs != null && schWfs.size() > 0) {
				for(int i=0; i<schWfs.size();i++) {
					if(schWfs.get(i) != null) {
						//schWfs.get(i).setLastUpd(getSession().getUserId());
						//schWfs.get(i).setLastUpdDate(new Date());
						new SchWfFacade().submit(schWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("SchWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(schWfs != null && schWfs.size() > 0) {
				for(int i=0; i<schWfs.size();i++) {
					if(schWfs.get(i) != null){
						//schWfs.get(i).setLastUpd(getSession().getUserId());
						//schWfs.get(i).setLastUpdDate(new Date());
						new SchWfFacade().submit(schWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("SchWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(schWfs != null && schWfs.size() > 0) {
				for(int i=0; i<schWfs.size();i++) {
					if(schWfs.get(i) != null){
						//schWfs.get(i).setLastUpd(getSession().getUserId());
						//schWfs.get(i).setLastUpdDate(new Date());
						new SchWfFacade().review(schWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("SchWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(schWfs != null && schWfs.size() > 0) {
				for(int i=0; i<schWfs.size();i++) {
					if(schWfs.get(i) != null) {
						//schWfs.get(i).setLastUpd(getSession().getUserId());
						//schWfs.get(i).setLastUpdDate(new Date());
						new SchWfFacade().review(schWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("SchWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(schWfs != null && schWfs.size() > 0) {
				for(int i=0; i<schWfs.size();i++) {
					if(schWfs.get(i) != null) {
						//schWfs.get(i).setLastUpd(getSession().getUserId());
						//schWfs.get(i).setLastUpdDate(new Date());
						new SchWfFacade().confirm(schWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("SchWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(schWfs != null && schWfs.size() > 0) {
				for(int i=0; i<schWfs.size();i++) {
					if(schWfs.get(i) != null) {
						//schWfs.get(i).setLastUpd(getSession().getUserId());
						//schWfs.get(i).setLastUpdDate(new Date());
						new SchWfFacade().confirm(schWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("SchWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(schWfs != null && schWfs.size() > 0) {
					for(int i=0; i<schWfs.size();i++) {
						if(schWfs.get(i) != null) {
							//schWfs.get(i).setLastUpd(getSession().getUserId());
							//schWfs.get(i).setLastUpdDate(new Date());
							new SchWfFacade().confirm(schWfs.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("SchWfAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<SchWf> schWfs = new SchWfFacade().find(schWf);
			if(schWfs != null && schWfs.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"项目编号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"流程ID",wcformat));
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


				int row = 2;
				for(int i=0; i<schWfs.size();i++) {
					row++;
					int m = 0;
					if(schWfs.get(i).getSchId() != null) 
						ws.addCell(new jxl.write.Number(m,row,schWfs.get(i).getSchId(),wcformat));
					m++;
					if(schWfs.get(i).getFlowId() != null) 
						ws.addCell(new jxl.write.Number(m,row,schWfs.get(i).getFlowId(),wcformat));
					m++;
					if(schWfs.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,schWfs.get(i).getStatus(),wcformat));
					m++;
					if(schWfs.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,schWfs.get(i).getCreateBy(),wcformat));
					m++;
					if(schWfs.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,schWfs.get(i).getLastUpd(),wcformat));
					m++;
					if(schWfs.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,schWfs.get(i).getCreateDate(),wcformat));
					m++;
					if(schWfs.get(i).getLastDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,schWfs.get(i).getLastDate(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("SchWfListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(schWfs != null && schWfs.size() > 0) {
				for(int i=0; i<schWfs.size();i++) {
					if(schWfs.get(i) != null) {
						//schWfs.get(i).setLastUpd(getSession().getUserId());
						//schWfs.get(i).setLastUpdDate(new Date());
						new SchWfFacade().confirm(schWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("SchWfAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(schWfs != null && schWfs.size() > 0) {
				for(int i=0; i<schWfs.size();i++) {
					if(schWfs.get(i) != null) {
						//schWfs.get(i).setLastUpd(getSession().getUserId());
						//schWfs.get(i).setLastUpdDate(new Date());
						new SchWfFacade().confirm(schWfs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("SchWfAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<SchWf> getSchWfs() {
		return schWfs;
	}
	public void setSchWfs(List<SchWf> schWfs) {
		this.schWfs = schWfs;
	}
	public SchWf getSchWf() {
		return schWf;
	}
	public void setSchWf(SchWf schWf) {
		this.schWf = schWf;
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
	public java.util.List<zrprjt.vo.SchCfg> getSchCfgs() {
		return schCfgs;
	}
	public void setSchCfgs(java.util.List<zrprjt.vo.SchCfg> schCfgs){
		this.schCfgs = schCfgs;
	}
	public void addtoSchCfgs(zrprjt.vo.SchCfg schCfg){
		if(getSchCfgs() == null) setSchCfgs(new java.util.ArrayList<zrprjt.vo.SchCfg>());
			getSchCfgs().add(schCfg);
	}

}