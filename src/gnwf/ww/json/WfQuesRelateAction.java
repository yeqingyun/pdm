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

import gnwf.facade.WfQuesRelateFacade;
import gnwf.vo.WfQuesRelate;
import gnwf.vo.json.WfQuesRelateJson;

public class WfQuesRelateAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfQuesRelate> wfQuesRelates;
	private WfQuesRelate wfQuesRelate = new WfQuesRelate();
	private java.util.List<gnwf.vo.WfQues> wfQuess;
	private java.util.List<gnwf.vo.WfRd> wfRds;

	public String execute() throws Exception {
		try {
			if(wfQuesRelate != null && wfQuesRelate.getQuesId() != null) {
				wfQuesRelate = new WfQuesRelateFacade().findById(wfQuesRelate);
				setJson(JSON.toJSONString(wfQuesRelate)); 
			}
			wfQuess = new gnwf.facade.WfQuesFacade().find("select "+gnwf.vo.WfQues.SELF_FIELDS+" from WfQues",gnwf.vo.WfQues.SELF_FIELDS);
			wfRds = new gnwf.facade.WfRdFacade().find("select "+gnwf.vo.WfRd.SELF_FIELDS+" from WfRd",gnwf.vo.WfRd.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesRelateAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(wfQuesRelate != null && wfQuesRelate.getQuesId() != null) {
				//wfQuesRelate = new WfQuesRelateFacade().findById(wfQuesRelate);
				//setJson(JSON.toJSONString(wfQuesRelate)); 
			//}
			wfQuess = new gnwf.facade.WfQuesFacade().find("select "+gnwf.vo.WfQues.SELF_FIELDS+" from WfQues",gnwf.vo.WfQues.SELF_FIELDS);
			wfRds = new gnwf.facade.WfRdFacade().find("select "+gnwf.vo.WfRd.SELF_FIELDS+" from WfRd",gnwf.vo.WfRd.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesRelateAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(wfQuesRelate != null && wfQuesRelate.getQuesId() != null) {
				wfQuesRelate = new WfQuesRelateFacade().findById(wfQuesRelate);
				setJson(JSON.toJSONString(wfQuesRelate)); 
			}
			wfQuess = new gnwf.facade.WfQuesFacade().find("select "+gnwf.vo.WfQues.SELF_FIELDS+" from WfQues",gnwf.vo.WfQues.SELF_FIELDS);
			wfRds = new gnwf.facade.WfRdFacade().find("select "+gnwf.vo.WfRd.SELF_FIELDS+" from WfRd",gnwf.vo.WfRd.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesRelateAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(wfQuesRelate != null && wfQuesRelate.getQuesId() != null) {
				wfQuesRelate = new WfQuesRelateFacade().findById(wfQuesRelate);
				setJson(JSON.toJSONString(wfQuesRelate)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesRelateAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(wfQuesRelate == null) wfQuesRelate = new WfQuesRelate();
			int total = new WfQuesRelateFacade().amount(wfQuesRelate);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfQuesRelates = new WfQuesRelateFacade().find(wfQuesRelate,getPageVO());
			WfQuesRelateJson wfQuesRelateJson = new WfQuesRelateJson();
			wfQuesRelateJson.Rows = wfQuesRelates;
			wfQuesRelateJson.Total = total;
			setJson(JSON.toJSONString(wfQuesRelateJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfQuesRelateAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//wfQuesRelate.setCreateBy(getSession().getUserId());
			//wfQuesRelate.setCreateDate(new Date());
			//wfQuesRelate.setLastUpd(getSession().getUserId());
			//wfQuesRelate.setLastUpdDate(new Date());

			if(wfQuesRelate.getQuesId() == null)
				new WfQuesRelateFacade().save(wfQuesRelate);
			else
				new WfQuesRelateFacade().update(wfQuesRelate);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfQuesRelateAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfQuesRelate.setCreateBy(getSession().getUserId());
			//wfQuesRelate.setCreateDate(new Date());
			//wfQuesRelate.setLastUpd(getSession().getUserId());
			//wfQuesRelate.setLastUpdDate(new Date());
			new WfQuesRelateFacade().update(wfQuesRelate);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfQuesRelateAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfQuesRelates != null && wfQuesRelates.size() > 0) {
				for(int i=0; i<wfQuesRelates.size();i++) {
					if(wfQuesRelates.get(i) != null) {
						//wfQuesRelates.get(i).setLastUpd(getSession().getUserId());
						//wfQuesRelates.get(i).setLastUpdDate(new Date());
						new WfQuesRelateFacade().submit(wfQuesRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfQuesRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfQuesRelates != null && wfQuesRelates.size() > 0) {
				for(int i=0; i<wfQuesRelates.size();i++) {
					if(wfQuesRelates.get(i) != null) {
						//wfQuesRelates.get(i).setLastUpd(getSession().getUserId());
						//wfQuesRelates.get(i).setLastUpdDate(new Date());
						new WfQuesRelateFacade().submit(wfQuesRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfQuesRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfQuesRelates != null && wfQuesRelates.size() > 0) {
				for(int i=0; i<wfQuesRelates.size();i++) {
					if(wfQuesRelates.get(i) != null) {
						//wfQuesRelates.get(i).setLastUpd(getSession().getUserId());
						//wfQuesRelates.get(i).setLastUpdDate(new Date());
						new WfQuesRelateFacade().update(wfQuesRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfQuesRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfQuesRelates != null && wfQuesRelates.size() > 0) {
				for(int i=0; i<wfQuesRelates.size();i++) {
					if(wfQuesRelates.get(i) != null) {
						//wfQuesRelates.get(i).setLastUpd(getSession().getUserId());
						//wfQuesRelates.get(i).setLastUpdDate(new Date());
						new WfQuesRelateFacade().submit(wfQuesRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfQuesRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfQuesRelates != null && wfQuesRelates.size() > 0) {
				for(int i=0; i<wfQuesRelates.size();i++) {
					if(wfQuesRelates.get(i) != null) {
						//wfQuesRelates.get(i).setLastUpd(getSession().getUserId());
						//wfQuesRelates.get(i).setLastUpdDate(new Date());
						new WfQuesRelateFacade().submit(wfQuesRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfQuesRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfQuesRelates != null && wfQuesRelates.size() > 0) {
				for(int i=0; i<wfQuesRelates.size();i++) {
					if(wfQuesRelates.get(i) != null) {
						//wfQuesRelates.get(i).setLastUpd(getSession().getUserId());
						//wfQuesRelates.get(i).setLastUpdDate(new Date());
						new WfQuesRelateFacade().submit(wfQuesRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfQuesRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfQuesRelates != null && wfQuesRelates.size() > 0) {
				for(int i=0; i<wfQuesRelates.size();i++) {
					if(wfQuesRelates.get(i) != null) {
						//wfQuesRelates.get(i).setLastUpd(getSession().getUserId());
						//wfQuesRelates.get(i).setLastUpdDate(new Date());
						new WfQuesRelateFacade().submit(wfQuesRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfQuesRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfQuesRelates != null && wfQuesRelates.size() > 0) {
				for(int i=0; i<wfQuesRelates.size();i++) {
					if(wfQuesRelates.get(i) != null){
						//wfQuesRelates.get(i).setLastUpd(getSession().getUserId());
						//wfQuesRelates.get(i).setLastUpdDate(new Date());
						new WfQuesRelateFacade().submit(wfQuesRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfQuesRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfQuesRelates != null && wfQuesRelates.size() > 0) {
				for(int i=0; i<wfQuesRelates.size();i++) {
					if(wfQuesRelates.get(i) != null){
						//wfQuesRelates.get(i).setLastUpd(getSession().getUserId());
						//wfQuesRelates.get(i).setLastUpdDate(new Date());
						new WfQuesRelateFacade().review(wfQuesRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfQuesRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfQuesRelates != null && wfQuesRelates.size() > 0) {
				for(int i=0; i<wfQuesRelates.size();i++) {
					if(wfQuesRelates.get(i) != null) {
						//wfQuesRelates.get(i).setLastUpd(getSession().getUserId());
						//wfQuesRelates.get(i).setLastUpdDate(new Date());
						new WfQuesRelateFacade().review(wfQuesRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfQuesRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfQuesRelates != null && wfQuesRelates.size() > 0) {
				for(int i=0; i<wfQuesRelates.size();i++) {
					if(wfQuesRelates.get(i) != null) {
						//wfQuesRelates.get(i).setLastUpd(getSession().getUserId());
						//wfQuesRelates.get(i).setLastUpdDate(new Date());
						new WfQuesRelateFacade().confirm(wfQuesRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfQuesRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfQuesRelates != null && wfQuesRelates.size() > 0) {
				for(int i=0; i<wfQuesRelates.size();i++) {
					if(wfQuesRelates.get(i) != null) {
						//wfQuesRelates.get(i).setLastUpd(getSession().getUserId());
						//wfQuesRelates.get(i).setLastUpdDate(new Date());
						new WfQuesRelateFacade().confirm(wfQuesRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfQuesRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfQuesRelates != null && wfQuesRelates.size() > 0) {
					for(int i=0; i<wfQuesRelates.size();i++) {
						if(wfQuesRelates.get(i) != null) {
							//wfQuesRelates.get(i).setLastUpd(getSession().getUserId());
							//wfQuesRelates.get(i).setLastUpdDate(new Date());
							new WfQuesRelateFacade().confirm(wfQuesRelates.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfQuesRelateAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfQuesRelate> wfQuesRelates = new WfQuesRelateFacade().find(wfQuesRelate);
			if(wfQuesRelates != null && wfQuesRelates.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"问题Id",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"是否风险流程",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"工作流编号",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfQuesRelates.size();i++) {
					row++;
					int m = 0;
					if(wfQuesRelates.get(i).getQuesId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfQuesRelates.get(i).getQuesId(),wcformat));
					m++;
					if(wfQuesRelates.get(i).getIsRisk() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfQuesRelates.get(i).getIsRisk(),wcformat));
					m++;
					if(wfQuesRelates.get(i).getWfNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfQuesRelates.get(i).getWfNo(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfQuesRelateListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfQuesRelates != null && wfQuesRelates.size() > 0) {
				for(int i=0; i<wfQuesRelates.size();i++) {
					if(wfQuesRelates.get(i) != null) {
						//wfQuesRelates.get(i).setLastUpd(getSession().getUserId());
						//wfQuesRelates.get(i).setLastUpdDate(new Date());
						new WfQuesRelateFacade().confirm(wfQuesRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfQuesRelateAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfQuesRelates != null && wfQuesRelates.size() > 0) {
				for(int i=0; i<wfQuesRelates.size();i++) {
					if(wfQuesRelates.get(i) != null) {
						//wfQuesRelates.get(i).setLastUpd(getSession().getUserId());
						//wfQuesRelates.get(i).setLastUpdDate(new Date());
						new WfQuesRelateFacade().confirm(wfQuesRelates.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfQuesRelateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfQuesRelate> getWfQuesRelates() {
		return wfQuesRelates;
	}
	public void setWfQuesRelates(List<WfQuesRelate> wfQuesRelates) {
		this.wfQuesRelates = wfQuesRelates;
	}
	public WfQuesRelate getWfQuesRelate() {
		return wfQuesRelate;
	}
	public void setWfQuesRelate(WfQuesRelate wfQuesRelate) {
		this.wfQuesRelate = wfQuesRelate;
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