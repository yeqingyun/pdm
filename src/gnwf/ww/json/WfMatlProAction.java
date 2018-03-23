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

import gnwf.facade.WfMatlProFacade;
import gnwf.vo.WfMatlPro;
import gnwf.vo.json.WfMatlProJson;

public class WfMatlProAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfMatlPro> wfMatlPros;
	private WfMatlPro wfMatlPro = new WfMatlPro();

	public String execute() throws Exception {
		try {
			if(wfMatlPro != null && wfMatlPro.getMatlProId() != null) {
				wfMatlPro = new WfMatlProFacade().findById(wfMatlPro);
				setJson(JSON.toJSONString(wfMatlPro)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfMatlProAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(wfMatlPro != null && wfMatlPro.getMatlProId() != null) {
				//wfMatlPro = new WfMatlProFacade().findById(wfMatlPro);
				//setJson(JSON.toJSONString(wfMatlPro)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfMatlProAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(wfMatlPro != null && wfMatlPro.getMatlProId() != null) {
				wfMatlPro = new WfMatlProFacade().findById(wfMatlPro);
				setJson(JSON.toJSONString(wfMatlPro)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfMatlProAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(wfMatlPro != null && wfMatlPro.getMatlProId() != null) {
				wfMatlPro = new WfMatlProFacade().findById(wfMatlPro);
				setJson(JSON.toJSONString(wfMatlPro)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfMatlProAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(wfMatlPro == null) wfMatlPro = new WfMatlPro();
			int total = new WfMatlProFacade().amount(wfMatlPro);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfMatlPros = new WfMatlProFacade().find(wfMatlPro,getPageVO());
			WfMatlProJson wfMatlProJson = new WfMatlProJson();
			wfMatlProJson.Rows = wfMatlPros;
			wfMatlProJson.Total = total;
			setJson(JSON.toJSONString(wfMatlProJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfMatlProAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//wfMatlPro.setCreateBy(getSession().getUserId());
			//wfMatlPro.setCreateDate(new Date());
			//wfMatlPro.setLastUpd(getSession().getUserId());
			//wfMatlPro.setLastUpdDate(new Date());

			if(wfMatlPro.getMatlProId() == null)
				new WfMatlProFacade().save(wfMatlPro);
			else
				new WfMatlProFacade().update(wfMatlPro);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfMatlProAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfMatlPro.setCreateBy(getSession().getUserId());
			//wfMatlPro.setCreateDate(new Date());
			//wfMatlPro.setLastUpd(getSession().getUserId());
			//wfMatlPro.setLastUpdDate(new Date());
			new WfMatlProFacade().update(wfMatlPro);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfMatlProAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfMatlPros != null && wfMatlPros.size() > 0) {
				for(int i=0; i<wfMatlPros.size();i++) {
					if(wfMatlPros.get(i) != null) {
						//wfMatlPros.get(i).setLastUpd(getSession().getUserId());
						//wfMatlPros.get(i).setLastUpdDate(new Date());
						new WfMatlProFacade().submit(wfMatlPros.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfMatlProAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfMatlPros != null && wfMatlPros.size() > 0) {
				for(int i=0; i<wfMatlPros.size();i++) {
					if(wfMatlPros.get(i) != null) {
						//wfMatlPros.get(i).setLastUpd(getSession().getUserId());
						//wfMatlPros.get(i).setLastUpdDate(new Date());
						new WfMatlProFacade().submit(wfMatlPros.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfMatlProAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfMatlPros != null && wfMatlPros.size() > 0) {
				for(int i=0; i<wfMatlPros.size();i++) {
					if(wfMatlPros.get(i) != null) {
						//wfMatlPros.get(i).setLastUpd(getSession().getUserId());
						//wfMatlPros.get(i).setLastUpdDate(new Date());
						new WfMatlProFacade().update(wfMatlPros.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfMatlProAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfMatlPros != null && wfMatlPros.size() > 0) {
				for(int i=0; i<wfMatlPros.size();i++) {
					if(wfMatlPros.get(i) != null) {
						//wfMatlPros.get(i).setLastUpd(getSession().getUserId());
						//wfMatlPros.get(i).setLastUpdDate(new Date());
						new WfMatlProFacade().submit(wfMatlPros.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfMatlProAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfMatlPros != null && wfMatlPros.size() > 0) {
				for(int i=0; i<wfMatlPros.size();i++) {
					if(wfMatlPros.get(i) != null) {
						//wfMatlPros.get(i).setLastUpd(getSession().getUserId());
						//wfMatlPros.get(i).setLastUpdDate(new Date());
						new WfMatlProFacade().submit(wfMatlPros.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfMatlProAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfMatlPros != null && wfMatlPros.size() > 0) {
				for(int i=0; i<wfMatlPros.size();i++) {
					if(wfMatlPros.get(i) != null) {
						//wfMatlPros.get(i).setLastUpd(getSession().getUserId());
						//wfMatlPros.get(i).setLastUpdDate(new Date());
						new WfMatlProFacade().submit(wfMatlPros.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfMatlProAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfMatlPros != null && wfMatlPros.size() > 0) {
				for(int i=0; i<wfMatlPros.size();i++) {
					if(wfMatlPros.get(i) != null) {
						//wfMatlPros.get(i).setLastUpd(getSession().getUserId());
						//wfMatlPros.get(i).setLastUpdDate(new Date());
						new WfMatlProFacade().submit(wfMatlPros.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfMatlProAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfMatlPros != null && wfMatlPros.size() > 0) {
				for(int i=0; i<wfMatlPros.size();i++) {
					if(wfMatlPros.get(i) != null){
						//wfMatlPros.get(i).setLastUpd(getSession().getUserId());
						//wfMatlPros.get(i).setLastUpdDate(new Date());
						new WfMatlProFacade().submit(wfMatlPros.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfMatlProAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfMatlPros != null && wfMatlPros.size() > 0) {
				for(int i=0; i<wfMatlPros.size();i++) {
					if(wfMatlPros.get(i) != null){
						//wfMatlPros.get(i).setLastUpd(getSession().getUserId());
						//wfMatlPros.get(i).setLastUpdDate(new Date());
						new WfMatlProFacade().review(wfMatlPros.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfMatlProAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfMatlPros != null && wfMatlPros.size() > 0) {
				for(int i=0; i<wfMatlPros.size();i++) {
					if(wfMatlPros.get(i) != null) {
						//wfMatlPros.get(i).setLastUpd(getSession().getUserId());
						//wfMatlPros.get(i).setLastUpdDate(new Date());
						new WfMatlProFacade().review(wfMatlPros.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfMatlProAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfMatlPros != null && wfMatlPros.size() > 0) {
				for(int i=0; i<wfMatlPros.size();i++) {
					if(wfMatlPros.get(i) != null) {
						//wfMatlPros.get(i).setLastUpd(getSession().getUserId());
						//wfMatlPros.get(i).setLastUpdDate(new Date());
						new WfMatlProFacade().confirm(wfMatlPros.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfMatlProAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfMatlPros != null && wfMatlPros.size() > 0) {
				for(int i=0; i<wfMatlPros.size();i++) {
					if(wfMatlPros.get(i) != null) {
						//wfMatlPros.get(i).setLastUpd(getSession().getUserId());
						//wfMatlPros.get(i).setLastUpdDate(new Date());
						new WfMatlProFacade().confirm(wfMatlPros.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfMatlProAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfMatlPros != null && wfMatlPros.size() > 0) {
					for(int i=0; i<wfMatlPros.size();i++) {
						if(wfMatlPros.get(i) != null) {
							//wfMatlPros.get(i).setLastUpd(getSession().getUserId());
							//wfMatlPros.get(i).setLastUpdDate(new Date());
							new WfMatlProFacade().confirm(wfMatlPros.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfMatlProAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfMatlPro> wfMatlPros = new WfMatlProFacade().find(wfMatlPro);
			if(wfMatlPros != null && wfMatlPros.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"MatlProId",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"采购是否通过",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"材料审批结果",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"产品经理审批结果",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"采购审批日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"材料审批日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"产品经理审批日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"工作流编号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"项目名称",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"设计人员",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"产品经理",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"功能描述",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"产品目前处于研发流程的阶段",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"产品经理审批人",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"产品经理审批意见",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"报告版本",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"成本要求",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"采购工程师",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"采购意见",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"材料工程师",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"材料审批意见",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfMatlPros.size();i++) {
					row++;
					int m = 0;
					if(wfMatlPros.get(i).getMatlProId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfMatlPros.get(i).getMatlProId(),wcformat));
					m++;
					if(wfMatlPros.get(i).getIsPurPass() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfMatlPros.get(i).getIsPurPass(),wcformat));
					m++;
					if(wfMatlPros.get(i).getIsMatPass() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfMatlPros.get(i).getIsMatPass(),wcformat));
					m++;
					if(wfMatlPros.get(i).getIsManagerPass() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfMatlPros.get(i).getIsManagerPass(),wcformat));
					m++;
					if(wfMatlPros.get(i).getPurRevDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfMatlPros.get(i).getPurRevDate(),wcformat));
					m++;
					if(wfMatlPros.get(i).getMatRevDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfMatlPros.get(i).getMatRevDate(),wcformat));
					m++;
					if(wfMatlPros.get(i).getManagerRevDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfMatlPros.get(i).getManagerRevDate(),wcformat));
					m++;
					if(wfMatlPros.get(i).getWfNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlPros.get(i).getWfNo(),wcformat));
					m++;
					if(wfMatlPros.get(i).getProName() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlPros.get(i).getProName(),wcformat));
					m++;
					if(wfMatlPros.get(i).getDesignName() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlPros.get(i).getDesignName(),wcformat));
					m++;
					if(wfMatlPros.get(i).getManageName() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlPros.get(i).getManageName(),wcformat));
					m++;
					if(wfMatlPros.get(i).getProDesc() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlPros.get(i).getProDesc(),wcformat));
					m++;
					if(wfMatlPros.get(i).getCurStep() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlPros.get(i).getCurStep(),wcformat));
					m++;
					if(wfMatlPros.get(i).getManagerReview() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlPros.get(i).getManagerReview(),wcformat));
					m++;
					if(wfMatlPros.get(i).getManagerRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlPros.get(i).getManagerRemark(),wcformat));
					m++;
					if(wfMatlPros.get(i).getCurVersion() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlPros.get(i).getCurVersion(),wcformat));
					m++;
					if(wfMatlPros.get(i).getProCost() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlPros.get(i).getProCost(),wcformat));
					m++;
					if(wfMatlPros.get(i).getPurReview() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlPros.get(i).getPurReview(),wcformat));
					m++;
					if(wfMatlPros.get(i).getPurRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlPros.get(i).getPurRemark(),wcformat));
					m++;
					if(wfMatlPros.get(i).getMatReveiw() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlPros.get(i).getMatReveiw(),wcformat));
					m++;
					if(wfMatlPros.get(i).getMatRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlPros.get(i).getMatRemark(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfMatlProListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfMatlPros != null && wfMatlPros.size() > 0) {
				for(int i=0; i<wfMatlPros.size();i++) {
					if(wfMatlPros.get(i) != null) {
						//wfMatlPros.get(i).setLastUpd(getSession().getUserId());
						//wfMatlPros.get(i).setLastUpdDate(new Date());
						new WfMatlProFacade().confirm(wfMatlPros.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfMatlProAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfMatlPros != null && wfMatlPros.size() > 0) {
				for(int i=0; i<wfMatlPros.size();i++) {
					if(wfMatlPros.get(i) != null) {
						//wfMatlPros.get(i).setLastUpd(getSession().getUserId());
						//wfMatlPros.get(i).setLastUpdDate(new Date());
						new WfMatlProFacade().confirm(wfMatlPros.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfMatlProAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfMatlPro> getWfMatlPros() {
		return wfMatlPros;
	}
	public void setWfMatlPros(List<WfMatlPro> wfMatlPros) {
		this.wfMatlPros = wfMatlPros;
	}
	public WfMatlPro getWfMatlPro() {
		return wfMatlPro;
	}
	public void setWfMatlPro(WfMatlPro wfMatlPro) {
		this.wfMatlPro = wfMatlPro;
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