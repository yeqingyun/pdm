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

import gnwf.facade.WfMatlFacade;
import gnwf.vo.WfMatl;
import gnwf.vo.json.WfMatlJson;

public class WfMatlAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfMatl> wfMatls;
	private WfMatl wfMatl = new WfMatl();
	private java.util.List<gnwf.vo.WfMatlEval> wfMatlEvals;
	private java.util.List<gnwf.vo.WfRd> wfRds;

	public String execute() throws Exception {
		try {
			if(wfMatl != null && wfMatl.getMatlId() != null) {
				wfMatl = new WfMatlFacade().findById(wfMatl);
				setJson(JSON.toJSONString(wfMatl)); 
			}
			wfRds = new gnwf.facade.WfRdFacade().find("select "+gnwf.vo.WfRd.SELF_FIELDS+" from WfRd",gnwf.vo.WfRd.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfMatlAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(wfMatl != null && wfMatl.getMatlId() != null) {
				//wfMatl = new WfMatlFacade().findById(wfMatl);
				//setJson(JSON.toJSONString(wfMatl)); 
			//}
			wfRds = new gnwf.facade.WfRdFacade().find("select "+gnwf.vo.WfRd.SELF_FIELDS+" from WfRd",gnwf.vo.WfRd.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfMatlAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(wfMatl != null && wfMatl.getMatlId() != null) {
				wfMatl = new WfMatlFacade().findById(wfMatl);
				setJson(JSON.toJSONString(wfMatl)); 
			}
			wfRds = new gnwf.facade.WfRdFacade().find("select "+gnwf.vo.WfRd.SELF_FIELDS+" from WfRd",gnwf.vo.WfRd.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfMatlAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(wfMatl != null && wfMatl.getMatlId() != null) {
				wfMatl = new WfMatlFacade().findById(wfMatl);
				setJson(JSON.toJSONString(wfMatl)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfMatlAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(wfMatl == null) wfMatl = new WfMatl();
			int total = new WfMatlFacade().amount(wfMatl);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfMatls = new WfMatlFacade().find(wfMatl,getPageVO());
			WfMatlJson wfMatlJson = new WfMatlJson();
			wfMatlJson.Rows = wfMatls;
			wfMatlJson.Total = total;
			setJson(JSON.toJSONString(wfMatlJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfMatlAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//wfMatl.setCreateBy(getSession().getUserId());
			//wfMatl.setCreateDate(new Date());
			//wfMatl.setLastUpd(getSession().getUserId());
			//wfMatl.setLastUpdDate(new Date());
			wfMatl.setWfMatlEvals(wfMatlEvals);

			if(wfMatl.getMatlId() == null)
				new WfMatlFacade().save(wfMatl);
			else
				new WfMatlFacade().update(wfMatl);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfMatlAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfMatl.setCreateBy(getSession().getUserId());
			//wfMatl.setCreateDate(new Date());
			//wfMatl.setLastUpd(getSession().getUserId());
			//wfMatl.setLastUpdDate(new Date());
			new WfMatlFacade().update(wfMatl);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfMatlAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfMatls != null && wfMatls.size() > 0) {
				for(int i=0; i<wfMatls.size();i++) {
					if(wfMatls.get(i) != null) {
						//wfMatls.get(i).setLastUpd(getSession().getUserId());
						//wfMatls.get(i).setLastUpdDate(new Date());
						new WfMatlFacade().submit(wfMatls.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfMatlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfMatls != null && wfMatls.size() > 0) {
				for(int i=0; i<wfMatls.size();i++) {
					if(wfMatls.get(i) != null) {
						//wfMatls.get(i).setLastUpd(getSession().getUserId());
						//wfMatls.get(i).setLastUpdDate(new Date());
						new WfMatlFacade().submit(wfMatls.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfMatlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfMatls != null && wfMatls.size() > 0) {
				for(int i=0; i<wfMatls.size();i++) {
					if(wfMatls.get(i) != null) {
						//wfMatls.get(i).setLastUpd(getSession().getUserId());
						//wfMatls.get(i).setLastUpdDate(new Date());
						new WfMatlFacade().update(wfMatls.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfMatlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfMatls != null && wfMatls.size() > 0) {
				for(int i=0; i<wfMatls.size();i++) {
					if(wfMatls.get(i) != null) {
						//wfMatls.get(i).setLastUpd(getSession().getUserId());
						//wfMatls.get(i).setLastUpdDate(new Date());
						new WfMatlFacade().submit(wfMatls.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfMatlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfMatls != null && wfMatls.size() > 0) {
				for(int i=0; i<wfMatls.size();i++) {
					if(wfMatls.get(i) != null) {
						//wfMatls.get(i).setLastUpd(getSession().getUserId());
						//wfMatls.get(i).setLastUpdDate(new Date());
						new WfMatlFacade().submit(wfMatls.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfMatlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfMatls != null && wfMatls.size() > 0) {
				for(int i=0; i<wfMatls.size();i++) {
					if(wfMatls.get(i) != null) {
						//wfMatls.get(i).setLastUpd(getSession().getUserId());
						//wfMatls.get(i).setLastUpdDate(new Date());
						new WfMatlFacade().submit(wfMatls.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfMatlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfMatls != null && wfMatls.size() > 0) {
				for(int i=0; i<wfMatls.size();i++) {
					if(wfMatls.get(i) != null) {
						//wfMatls.get(i).setLastUpd(getSession().getUserId());
						//wfMatls.get(i).setLastUpdDate(new Date());
						new WfMatlFacade().submit(wfMatls.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfMatlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfMatls != null && wfMatls.size() > 0) {
				for(int i=0; i<wfMatls.size();i++) {
					if(wfMatls.get(i) != null){
						//wfMatls.get(i).setLastUpd(getSession().getUserId());
						//wfMatls.get(i).setLastUpdDate(new Date());
						new WfMatlFacade().submit(wfMatls.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfMatlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfMatls != null && wfMatls.size() > 0) {
				for(int i=0; i<wfMatls.size();i++) {
					if(wfMatls.get(i) != null){
						//wfMatls.get(i).setLastUpd(getSession().getUserId());
						//wfMatls.get(i).setLastUpdDate(new Date());
						new WfMatlFacade().review(wfMatls.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfMatlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfMatls != null && wfMatls.size() > 0) {
				for(int i=0; i<wfMatls.size();i++) {
					if(wfMatls.get(i) != null) {
						//wfMatls.get(i).setLastUpd(getSession().getUserId());
						//wfMatls.get(i).setLastUpdDate(new Date());
						new WfMatlFacade().review(wfMatls.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfMatlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfMatls != null && wfMatls.size() > 0) {
				for(int i=0; i<wfMatls.size();i++) {
					if(wfMatls.get(i) != null) {
						//wfMatls.get(i).setLastUpd(getSession().getUserId());
						//wfMatls.get(i).setLastUpdDate(new Date());
						new WfMatlFacade().confirm(wfMatls.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfMatlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfMatls != null && wfMatls.size() > 0) {
				for(int i=0; i<wfMatls.size();i++) {
					if(wfMatls.get(i) != null) {
						//wfMatls.get(i).setLastUpd(getSession().getUserId());
						//wfMatls.get(i).setLastUpdDate(new Date());
						new WfMatlFacade().confirm(wfMatls.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfMatlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfMatls != null && wfMatls.size() > 0) {
					for(int i=0; i<wfMatls.size();i++) {
						if(wfMatls.get(i) != null) {
							//wfMatls.get(i).setLastUpd(getSession().getUserId());
							//wfMatls.get(i).setLastUpdDate(new Date());
							new WfMatlFacade().confirm(wfMatls.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfMatlAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfMatl> wfMatls = new WfMatlFacade().find(wfMatl);
			if(wfMatls != null && wfMatls.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"物料记录ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"物料类型",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"是否可替代",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"风险等级",wcformat));
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
					ws.addCell(new Label(index,1,"最后更新",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"物料编码",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"工作流编号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"物料等级",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"创建日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"更新日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"物料名称",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"物料描述",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"供应商",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"备注",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"物料型号(外部编码)",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"物料选型原因",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfMatls.size();i++) {
					row++;
					int m = 0;
					if(wfMatls.get(i).getMatlId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfMatls.get(i).getMatlId(),wcformat));
					m++;
					if(wfMatls.get(i).getMatlType() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfMatls.get(i).getMatlType(),wcformat));
					m++;
					if(wfMatls.get(i).getIsSubs() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfMatls.get(i).getIsSubs(),wcformat));
					m++;
					if(wfMatls.get(i).getRisk() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfMatls.get(i).getRisk(),wcformat));
					m++;
					if(wfMatls.get(i).getLotSize() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfMatls.get(i).getLotSize(),wcformat));
					m++;
					if(wfMatls.get(i).getIsPanel() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfMatls.get(i).getIsPanel(),wcformat));
					m++;
					if(wfMatls.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfMatls.get(i).getStatus(),wcformat));
					m++;
					if(wfMatls.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfMatls.get(i).getCreateBy(),wcformat));
					m++;
					if(wfMatls.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfMatls.get(i).getLastUpd(),wcformat));
					m++;
					if(wfMatls.get(i).getMatlNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatls.get(i).getMatlNo(),wcformat));
					m++;
					if(wfMatls.get(i).getWfNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatls.get(i).getWfNo(),wcformat));
					m++;
					if(wfMatls.get(i).getMatlLevel() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatls.get(i).getMatlLevel(),wcformat));
					m++;
					if(wfMatls.get(i).gethistoryLevel() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatls.get(i).gethistoryLevel(),wcformat));
					m++;
					if(wfMatls.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfMatls.get(i).getCreateDate(),wcformat));
					m++;
					if(wfMatls.get(i).getLastUpdDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfMatls.get(i).getLastUpdDate(),wcformat));
					m++;
					if(wfMatls.get(i).getMatlName() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatls.get(i).getMatlName(),wcformat));
					m++;
					if(wfMatls.get(i).getMatlDesc() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatls.get(i).getMatlDesc(),wcformat));
					m++;
					if(wfMatls.get(i).getSupplier() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatls.get(i).getSupplier(),wcformat));
					m++;
					if(wfMatls.get(i).getRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatls.get(i).getRemark(),wcformat));
					m++;
					if(wfMatls.get(i).getSupNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatls.get(i).getSupNo(),wcformat));
					m++;
					if(wfMatls.get(i).getMatlEvalDesc() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatls.get(i).getMatlEvalDesc(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfMatlListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfMatls != null && wfMatls.size() > 0) {
				for(int i=0; i<wfMatls.size();i++) {
					if(wfMatls.get(i) != null) {
						//wfMatls.get(i).setLastUpd(getSession().getUserId());
						//wfMatls.get(i).setLastUpdDate(new Date());
						new WfMatlFacade().confirm(wfMatls.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfMatlAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfMatls != null && wfMatls.size() > 0) {
				for(int i=0; i<wfMatls.size();i++) {
					if(wfMatls.get(i) != null) {
						//wfMatls.get(i).setLastUpd(getSession().getUserId());
						//wfMatls.get(i).setLastUpdDate(new Date());
						new WfMatlFacade().confirm(wfMatls.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfMatlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfMatl> getWfMatls() {
		return wfMatls;
	}
	public void setWfMatls(List<WfMatl> wfMatls) {
		this.wfMatls = wfMatls;
	}
	public WfMatl getWfMatl() {
		return wfMatl;
	}
	public void setWfMatl(WfMatl wfMatl) {
		this.wfMatl = wfMatl;
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
	public java.util.List<gnwf.vo.WfMatlEval> getWfMatlEvals() {
		return wfMatlEvals;
	}
	public void setWfMatlEvals(java.util.List<gnwf.vo.WfMatlEval> wfMatlEvals){
		this.wfMatlEvals = wfMatlEvals;
	}
	public void addtoWfMatlEvals(gnwf.vo.WfMatlEval wfMatlEval){
		if(getWfMatlEvals() == null) setWfMatlEvals(new java.util.ArrayList<gnwf.vo.WfMatlEval>());
			getWfMatlEvals().add(wfMatlEval);
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