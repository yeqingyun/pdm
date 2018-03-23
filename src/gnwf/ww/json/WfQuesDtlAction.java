package gnwf.ww.json;

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

import gnwf.ww.MSG;
import gnwf.ww.BasicAction;

import gnwf.facade.WfQuesDtlFacade;
import gnwf.vo.WfQuesDtl;
import gnwf.vo.json.WfQuesDtlJson;

public class WfQuesDtlAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfQuesDtl> wfQuesDtls;
	private WfQuesDtl wfQuesDtl = new WfQuesDtl();
	private java.util.List<gnwf.vo.WfQues> wfQuess;

	public String execute() throws Exception {
		try {
			if(wfQuesDtl != null && wfQuesDtl.getQuesDtlId() != null) {
				wfQuesDtl = new WfQuesDtlFacade().findById(wfQuesDtl);
				setJson(JSON.toJSONString(wfQuesDtl)); 
			}
			wfQuess = new gnwf.facade.WfQuesFacade().find("select "+gnwf.vo.WfQues.SELF_FIELDS+" from WfQues",gnwf.vo.WfQues.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesDtlAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(wfQuesDtl != null && wfQuesDtl.getQuesDtlId() != null) {
				//wfQuesDtl = new WfQuesDtlFacade().findById(wfQuesDtl);
				//setJson(JSON.toJSONString(wfQuesDtl)); 
			//}
			wfQuess = new gnwf.facade.WfQuesFacade().find("select "+gnwf.vo.WfQues.SELF_FIELDS+" from WfQues",gnwf.vo.WfQues.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesDtlAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(wfQuesDtl != null && wfQuesDtl.getQuesDtlId() != null) {
				wfQuesDtl = new WfQuesDtlFacade().findById(wfQuesDtl);
				setJson(JSON.toJSONString(wfQuesDtl)); 
			}
			wfQuess = new gnwf.facade.WfQuesFacade().find("select "+gnwf.vo.WfQues.SELF_FIELDS+" from WfQues",gnwf.vo.WfQues.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesDtlAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(wfQuesDtl != null && wfQuesDtl.getQuesDtlId() != null) {
				wfQuesDtl = new WfQuesDtlFacade().findById(wfQuesDtl);
				setJson(JSON.toJSONString(wfQuesDtl)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesDtlAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(wfQuesDtl == null) wfQuesDtl = new WfQuesDtl();
			int total = new WfQuesDtlFacade().amount(wfQuesDtl);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfQuesDtls = new WfQuesDtlFacade().find(wfQuesDtl,getPageVO());
			WfQuesDtlJson wfQuesDtlJson = new WfQuesDtlJson();
			wfQuesDtlJson.Rows = wfQuesDtls;
			wfQuesDtlJson.Total = total;
			setJson(JSON.toJSONString(wfQuesDtlJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesDtlAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//wfQuesDtl.setLastUpd(getSession().getUserId());
			//wfQuesDtl.setLastUpdDate(new Date());
			if(wfQuesDtl.getQuesDtlId() == null){
				wfQuesDtl.setCreateBy(getUsrSession().getId());
				wfQuesDtl.setCreateDate(new Date());
				wfQuesDtl.setStatus(1);
				new WfQuesDtlFacade().save(wfQuesDtl);
			}else {
				new WfQuesDtlFacade().update(wfQuesDtl);
			}
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfQuesDtlAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfQuesDtl.setCreateBy(getSession().getUserId());
			//wfQuesDtl.setCreateDate(new Date());
			//wfQuesDtl.setLastUpd(getSession().getUserId());
			//wfQuesDtl.setLastUpdDate(new Date());
			new WfQuesDtlFacade().update(wfQuesDtl);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfQuesDtlAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfQuesDtls != null && wfQuesDtls.size() > 0) {
				for(int i=0; i<wfQuesDtls.size();i++) {
					if(wfQuesDtls.get(i) != null) {
						//wfQuesDtls.get(i).setLastUpd(getSession().getUserId());
						//wfQuesDtls.get(i).setLastUpdDate(new Date());
						new WfQuesDtlFacade().submit(wfQuesDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfQuesDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfQuesDtls != null && wfQuesDtls.size() > 0) {
				for(int i=0; i<wfQuesDtls.size();i++) {
					if(wfQuesDtls.get(i) != null) {
						//wfQuesDtls.get(i).setLastUpd(getSession().getUserId());
						//wfQuesDtls.get(i).setLastUpdDate(new Date());
						new WfQuesDtlFacade().submit(wfQuesDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfQuesDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfQuesDtls != null && wfQuesDtls.size() > 0) {
				for(int i=0; i<wfQuesDtls.size();i++) {
					if(wfQuesDtls.get(i) != null) {
						//wfQuesDtls.get(i).setLastUpd(getSession().getUserId());
						//wfQuesDtls.get(i).setLastUpdDate(new Date());
						new WfQuesDtlFacade().update(wfQuesDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfQuesDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfQuesDtls != null && wfQuesDtls.size() > 0) {
				for(int i=0; i<wfQuesDtls.size();i++) {
					if(wfQuesDtls.get(i) != null) {
						//wfQuesDtls.get(i).setLastUpd(getSession().getUserId());
						//wfQuesDtls.get(i).setLastUpdDate(new Date());
						new WfQuesDtlFacade().submit(wfQuesDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfQuesDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfQuesDtls != null && wfQuesDtls.size() > 0) {
				for(int i=0; i<wfQuesDtls.size();i++) {
					if(wfQuesDtls.get(i) != null) {
						//wfQuesDtls.get(i).setLastUpd(getSession().getUserId());
						//wfQuesDtls.get(i).setLastUpdDate(new Date());
						new WfQuesDtlFacade().submit(wfQuesDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfQuesDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfQuesDtls != null && wfQuesDtls.size() > 0) {
				for(int i=0; i<wfQuesDtls.size();i++) {
					if(wfQuesDtls.get(i) != null) {
						//wfQuesDtls.get(i).setLastUpd(getSession().getUserId());
						//wfQuesDtls.get(i).setLastUpdDate(new Date());
						new WfQuesDtlFacade().submit(wfQuesDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfQuesDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfQuesDtls != null && wfQuesDtls.size() > 0) {
				for(int i=0; i<wfQuesDtls.size();i++) {
					if(wfQuesDtls.get(i) != null) {
						//wfQuesDtls.get(i).setLastUpd(getSession().getUserId());
						//wfQuesDtls.get(i).setLastUpdDate(new Date());
						new WfQuesDtlFacade().submit(wfQuesDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfQuesDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfQuesDtls != null && wfQuesDtls.size() > 0) {
				for(int i=0; i<wfQuesDtls.size();i++) {
					if(wfQuesDtls.get(i) != null){
						//wfQuesDtls.get(i).setLastUpd(getSession().getUserId());
						//wfQuesDtls.get(i).setLastUpdDate(new Date());
						new WfQuesDtlFacade().submit(wfQuesDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfQuesDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfQuesDtls != null && wfQuesDtls.size() > 0) {
				for(int i=0; i<wfQuesDtls.size();i++) {
					if(wfQuesDtls.get(i) != null){
						//wfQuesDtls.get(i).setLastUpd(getSession().getUserId());
						//wfQuesDtls.get(i).setLastUpdDate(new Date());
						new WfQuesDtlFacade().review(wfQuesDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfQuesDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfQuesDtls != null && wfQuesDtls.size() > 0) {
				for(int i=0; i<wfQuesDtls.size();i++) {
					if(wfQuesDtls.get(i) != null) {
						//wfQuesDtls.get(i).setLastUpd(getSession().getUserId());
						//wfQuesDtls.get(i).setLastUpdDate(new Date());
						new WfQuesDtlFacade().review(wfQuesDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfQuesDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfQuesDtls != null && wfQuesDtls.size() > 0) {
				for(int i=0; i<wfQuesDtls.size();i++) {
					if(wfQuesDtls.get(i) != null) {
						//wfQuesDtls.get(i).setLastUpd(getSession().getUserId());
						//wfQuesDtls.get(i).setLastUpdDate(new Date());
						new WfQuesDtlFacade().confirm(wfQuesDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfQuesDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfQuesDtls != null && wfQuesDtls.size() > 0) {
				for(int i=0; i<wfQuesDtls.size();i++) {
					if(wfQuesDtls.get(i) != null) {
						//wfQuesDtls.get(i).setLastUpd(getSession().getUserId());
						//wfQuesDtls.get(i).setLastUpdDate(new Date());
						new WfQuesDtlFacade().confirm(wfQuesDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfQuesDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfQuesDtls != null && wfQuesDtls.size() > 0) {
					for(int i=0; i<wfQuesDtls.size();i++) {
						if(wfQuesDtls.get(i) != null) {
							//wfQuesDtls.get(i).setLastUpd(getSession().getUserId());
							//wfQuesDtls.get(i).setLastUpdDate(new Date());
							new WfQuesDtlFacade().confirm(wfQuesDtls.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfQuesDtlAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfQuesDtl> wfQuesDtls = new WfQuesDtlFacade().find(wfQuesDtl);
			if(wfQuesDtls != null && wfQuesDtls.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"问题明细ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"问题编号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"问题办理人",wcformat));
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
					ws.addCell(new Label(index,1,"内容",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"标题",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfQuesDtls.size();i++) {
					row++;
					int m = 0;
					if(wfQuesDtls.get(i).getQuesDtlId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfQuesDtls.get(i).getQuesDtlId(),wcformat));
					m++;
					if(wfQuesDtls.get(i).getQuesId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfQuesDtls.get(i).getQuesId(),wcformat));
					m++;
					if(wfQuesDtls.get(i).getUserId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfQuesDtls.get(i).getUserId(),wcformat));
					m++;
					if(wfQuesDtls.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfQuesDtls.get(i).getStatus(),wcformat));
					m++;
					if(wfQuesDtls.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfQuesDtls.get(i).getCreateBy(),wcformat));
					m++;
					if(wfQuesDtls.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfQuesDtls.get(i).getLastUpd(),wcformat));
					m++;
					if(wfQuesDtls.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfQuesDtls.get(i).getCreateDate(),wcformat));
					m++;
					if(wfQuesDtls.get(i).getLastUpdDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfQuesDtls.get(i).getLastUpdDate(),wcformat));
					m++;
					if(wfQuesDtls.get(i).getQuesTxt() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfQuesDtls.get(i).getQuesTxt(),wcformat));
					m++;
					if(wfQuesDtls.get(i).getTitle() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfQuesDtls.get(i).getTitle(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfQuesDtlListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfQuesDtls != null && wfQuesDtls.size() > 0) {
				for(int i=0; i<wfQuesDtls.size();i++) {
					if(wfQuesDtls.get(i) != null) {
						//wfQuesDtls.get(i).setLastUpd(getSession().getUserId());
						//wfQuesDtls.get(i).setLastUpdDate(new Date());
						new WfQuesDtlFacade().confirm(wfQuesDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfQuesDtlAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfQuesDtls != null && wfQuesDtls.size() > 0) {
				for(int i=0; i<wfQuesDtls.size();i++) {
					if(wfQuesDtls.get(i) != null) {
						//wfQuesDtls.get(i).setLastUpd(getSession().getUserId());
						//wfQuesDtls.get(i).setLastUpdDate(new Date());
						new WfQuesDtlFacade().confirm(wfQuesDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfQuesDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfQuesDtl> getWfQuesDtls() {
		return wfQuesDtls;
	}
	public void setWfQuesDtls(List<WfQuesDtl> wfQuesDtls) {
		this.wfQuesDtls = wfQuesDtls;
	}
	public WfQuesDtl getWfQuesDtl() {
		return wfQuesDtl;
	}
	public void setWfQuesDtl(WfQuesDtl wfQuesDtl) {
		this.wfQuesDtl = wfQuesDtl;
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
	public java.util.List<gnwf.vo.WfQues> getWfQuess() {
		return wfQuess;
	}
	public void setWfQuess(java.util.List<gnwf.vo.WfQues> wfQuess){
		this.wfQuess = wfQuess;
	}
	public void addtoWfQuess(gnwf.vo.WfQues wfQues){
		if(getWfQuess() == null) setWfQuess(new java.util.ArrayList<gnwf.vo.WfQues>());
			getWfQuess().add(wfQues);
	}

}