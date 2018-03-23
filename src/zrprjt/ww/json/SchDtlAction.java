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

import zrprjt.facade.SchDtlFacade;
import zrprjt.vo.SchDtl;
import zrprjt.vo.json.SchDtlJson;

public class SchDtlAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<SchDtl> schDtls;
	private SchDtl schDtl = new SchDtl();
	private java.util.List<zrprjt.vo.SchCfg> schCfgs;

	public String execute() throws Exception {
		try {
			if(schDtl != null && schDtl.getSchDtlId() != null) {
				schDtl = new SchDtlFacade().findById(schDtl);
				setJson(JSON.toJSONString(schDtl)); 
			}
			schCfgs = new zrprjt.facade.SchCfgFacade().find("select "+zrprjt.vo.SchCfg.SELF_FIELDS+" from SchCfg",zrprjt.vo.SchCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchDtlAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(schDtl != null && schDtl.getSchDtlId() != null) {
				//schDtl = new SchDtlFacade().findById(schDtl);
				//setJson(JSON.toJSONString(schDtl)); 
			//}
			schCfgs = new zrprjt.facade.SchCfgFacade().find("select "+zrprjt.vo.SchCfg.SELF_FIELDS+" from SchCfg",zrprjt.vo.SchCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchDtlAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(schDtl != null && schDtl.getSchDtlId() != null) {
				schDtl = new SchDtlFacade().findById(schDtl);
				setJson(JSON.toJSONString(schDtl)); 
			}
			schCfgs = new zrprjt.facade.SchCfgFacade().find("select "+zrprjt.vo.SchCfg.SELF_FIELDS+" from SchCfg",zrprjt.vo.SchCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchDtlAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(schDtl != null && schDtl.getSchDtlId() != null) {
				schDtl = new SchDtlFacade().findById(schDtl);
				setJson(JSON.toJSONString(schDtl)); 
			}
			schCfgs = new zrprjt.facade.SchCfgFacade().find("select "+zrprjt.vo.SchCfg.SELF_FIELDS+" from SchCfg",zrprjt.vo.SchCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchDtlAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(schDtl == null) schDtl = new SchDtl();
			int total = new SchDtlFacade().amount(schDtl);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			schDtls = new SchDtlFacade().find(schDtl,getPageVO());
			SchDtlJson schDtlJson = new SchDtlJson();
			schDtlJson.Rows = schDtls;
			schDtlJson.Total = total;
			setJson(JSON.toJSONString(schDtlJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchDtlAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			

			if(schDtl.getSchDtlId() == null){
				schDtl.setCreateBy(getUsrSession().getId());
				schDtl.setCreateDate(new Date());
				schDtl.setLastUpd(getUsrSession().getId());
				schDtl.setLastDate(new Date());
				new SchDtlFacade().save(schDtl);
			}
			else{
				schDtl.setLastUpd(getUsrSession().getId());
				schDtl.setLastDate(new Date());
				new SchDtlFacade().update(schDtl);
			}
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("SchDtlAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//schDtl.setCreateBy(getSession().getUserId());
			//schDtl.setCreateDate(new Date());
			//schDtl.setLastUpd(getSession().getUserId());
			//schDtl.setLastUpdDate(new Date());
			new SchDtlFacade().update(schDtl);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("SchDtlAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(schDtls != null && schDtls.size() > 0) {
				for(int i=0; i<schDtls.size();i++) {
					if(schDtls.get(i) != null) {
						//schDtls.get(i).setLastUpd(getSession().getUserId());
						//schDtls.get(i).setLastUpdDate(new Date());
						new SchDtlFacade().submit(schDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("SchDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(schDtls != null && schDtls.size() > 0) {
				for(int i=0; i<schDtls.size();i++) {
					if(schDtls.get(i) != null) {
						//schDtls.get(i).setLastUpd(getSession().getUserId());
						//schDtls.get(i).setLastUpdDate(new Date());
						new SchDtlFacade().submit(schDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("SchDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(schDtls != null && schDtls.size() > 0) {
				for(int i=0; i<schDtls.size();i++) {
					if(schDtls.get(i) != null) {
						//schDtls.get(i).setLastUpd(getSession().getUserId());
						//schDtls.get(i).setLastUpdDate(new Date());
						new SchDtlFacade().update(schDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("SchDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(schDtls != null && schDtls.size() > 0) {
				for(int i=0; i<schDtls.size();i++) {
					if(schDtls.get(i) != null) {
						//schDtls.get(i).setLastUpd(getSession().getUserId());
						//schDtls.get(i).setLastUpdDate(new Date());
						new SchDtlFacade().submit(schDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("SchDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(schDtls != null && schDtls.size() > 0) {
				for(int i=0; i<schDtls.size();i++) {
					if(schDtls.get(i) != null) {
						//schDtls.get(i).setLastUpd(getSession().getUserId());
						//schDtls.get(i).setLastUpdDate(new Date());
						new SchDtlFacade().submit(schDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("SchDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(schDtls != null && schDtls.size() > 0) {
				for(int i=0; i<schDtls.size();i++) {
					if(schDtls.get(i) != null) {
						//schDtls.get(i).setLastUpd(getSession().getUserId());
						//schDtls.get(i).setLastUpdDate(new Date());
						new SchDtlFacade().submit(schDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("SchDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(schDtls != null && schDtls.size() > 0) {
				for(int i=0; i<schDtls.size();i++) {
					if(schDtls.get(i) != null) {
						//schDtls.get(i).setLastUpd(getSession().getUserId());
						//schDtls.get(i).setLastUpdDate(new Date());
						new SchDtlFacade().submit(schDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("SchDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(schDtls != null && schDtls.size() > 0) {
				for(int i=0; i<schDtls.size();i++) {
					if(schDtls.get(i) != null){
						//schDtls.get(i).setLastUpd(getSession().getUserId());
						//schDtls.get(i).setLastUpdDate(new Date());
						new SchDtlFacade().submit(schDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("SchDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(schDtls != null && schDtls.size() > 0) {
				for(int i=0; i<schDtls.size();i++) {
					if(schDtls.get(i) != null){
						//schDtls.get(i).setLastUpd(getSession().getUserId());
						//schDtls.get(i).setLastUpdDate(new Date());
						new SchDtlFacade().review(schDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("SchDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(schDtls != null && schDtls.size() > 0) {
				for(int i=0; i<schDtls.size();i++) {
					if(schDtls.get(i) != null) {
						//schDtls.get(i).setLastUpd(getSession().getUserId());
						//schDtls.get(i).setLastUpdDate(new Date());
						new SchDtlFacade().review(schDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("SchDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(schDtls != null && schDtls.size() > 0) {
				for(int i=0; i<schDtls.size();i++) {
					if(schDtls.get(i) != null) {
						//schDtls.get(i).setLastUpd(getSession().getUserId());
						//schDtls.get(i).setLastUpdDate(new Date());
						new SchDtlFacade().confirm(schDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("SchDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(schDtls != null && schDtls.size() > 0) {
				for(int i=0; i<schDtls.size();i++) {
					if(schDtls.get(i) != null) {
						//schDtls.get(i).setLastUpd(getSession().getUserId());
						//schDtls.get(i).setLastUpdDate(new Date());
						new SchDtlFacade().confirm(schDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("SchDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(schDtls != null && schDtls.size() > 0) {
					for(int i=0; i<schDtls.size();i++) {
						if(schDtls.get(i) != null) {
							//schDtls.get(i).setLastUpd(getSession().getUserId());
							//schDtls.get(i).setLastUpdDate(new Date());
							new SchDtlFacade().confirm(schDtls.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("SchDtlAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<SchDtl> schDtls = new SchDtlFacade().find(schDtl);
			if(schDtls != null && schDtls.size() > 0) {
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
					ws.addCell(new Label(index,1,"进度ID",wcformat));
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
					ws.addCell(new Label(index,1,"交附件",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"备注",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<schDtls.size();i++) {
					row++;
					int m = 0;
					if(schDtls.get(i).getSchDtlId() != null) 
						ws.addCell(new jxl.write.Number(m,row,schDtls.get(i).getSchDtlId(),wcformat));
					m++;
					if(schDtls.get(i).getSchId() != null) 
						ws.addCell(new jxl.write.Number(m,row,schDtls.get(i).getSchId(),wcformat));
					m++;
					if(schDtls.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,schDtls.get(i).getStatus(),wcformat));
					m++;
					if(schDtls.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,schDtls.get(i).getCreateBy(),wcformat));
					m++;
					if(schDtls.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,schDtls.get(i).getLastUpd(),wcformat));
					m++;
					if(schDtls.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,schDtls.get(i).getCreateDate(),wcformat));
					m++;
					if(schDtls.get(i).getLastDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,schDtls.get(i).getLastDate(),wcformat));
					m++;
					if(schDtls.get(i).getDocNm() != null) 
						ws.addCell(new jxl.write.Label(m,row,schDtls.get(i).getDocNm(),wcformat));
					m++;
					if(schDtls.get(i).getRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,schDtls.get(i).getRemark(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("SchDtlListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(schDtls != null && schDtls.size() > 0) {
				for(int i=0; i<schDtls.size();i++) {
					if(schDtls.get(i) != null) {
						//schDtls.get(i).setLastUpd(getSession().getUserId());
						//schDtls.get(i).setLastUpdDate(new Date());
						new SchDtlFacade().confirm(schDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("SchDtlAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(schDtls != null && schDtls.size() > 0) {
				for(int i=0; i<schDtls.size();i++) {
					if(schDtls.get(i) != null) {
						//schDtls.get(i).setLastUpd(getSession().getUserId());
						//schDtls.get(i).setLastUpdDate(new Date());
						new SchDtlFacade().confirm(schDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("SchDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<SchDtl> getSchDtls() {
		return schDtls;
	}
	public void setSchDtls(List<SchDtl> schDtls) {
		this.schDtls = schDtls;
	}
	public SchDtl getSchDtl() {
		return schDtl;
	}
	public void setSchDtl(SchDtl schDtl) {
		this.schDtl = schDtl;
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