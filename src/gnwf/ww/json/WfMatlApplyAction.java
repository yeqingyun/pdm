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

import gnwf.facade.WfMatlApplyFacade;
import gnwf.vo.WfMatlApply;
import gnwf.vo.json.WfMatlApplyJson;

public class WfMatlApplyAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfMatlApply> wfMatlApplys;
	private WfMatlApply wfMatlApply = new WfMatlApply();

	public String execute() throws Exception {
		try {
			if(wfMatlApply != null && wfMatlApply.getMatlId() != null) {
				wfMatlApply = new WfMatlApplyFacade().findById(wfMatlApply);
				setJson(JSON.toJSONString(wfMatlApply)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfMatlApplyAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(wfMatlApply != null && wfMatlApply.getMatlId() != null) {
				//wfMatlApply = new WfMatlApplyFacade().findById(wfMatlApply);
				//setJson(JSON.toJSONString(wfMatlApply)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfMatlApplyAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(wfMatlApply != null && wfMatlApply.getMatlId() != null) {
				wfMatlApply = new WfMatlApplyFacade().findById(wfMatlApply);
				setJson(JSON.toJSONString(wfMatlApply)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfMatlApplyAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(wfMatlApply != null && wfMatlApply.getMatlId() != null) {
				wfMatlApply = new WfMatlApplyFacade().findById(wfMatlApply);
				setJson(JSON.toJSONString(wfMatlApply)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfMatlApplyAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(wfMatlApply == null) wfMatlApply = new WfMatlApply();
			int total = new WfMatlApplyFacade().amount(wfMatlApply);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfMatlApplys = new WfMatlApplyFacade().find(wfMatlApply,getPageVO());
			WfMatlApplyJson wfMatlApplyJson = new WfMatlApplyJson();
			wfMatlApplyJson.Rows = wfMatlApplys;
			wfMatlApplyJson.Total = total;
			setJson(JSON.toJSONString(wfMatlApplyJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfMatlApplyAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//wfMatlApply.setCreateBy(getSession().getUserId());
			//wfMatlApply.setCreateDate(new Date());
			//wfMatlApply.setLastUpd(getSession().getUserId());
			//wfMatlApply.setLastUpdDate(new Date());

			if(wfMatlApply.getMatlId() == null)
				new WfMatlApplyFacade().save(wfMatlApply);
			else
				new WfMatlApplyFacade().update(wfMatlApply);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfMatlApplyAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfMatlApply.setCreateBy(getSession().getUserId());
			//wfMatlApply.setCreateDate(new Date());
			//wfMatlApply.setLastUpd(getSession().getUserId());
			//wfMatlApply.setLastUpdDate(new Date());
			new WfMatlApplyFacade().update(wfMatlApply);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfMatlApplyAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfMatlApplys != null && wfMatlApplys.size() > 0) {
				for(int i=0; i<wfMatlApplys.size();i++) {
					if(wfMatlApplys.get(i) != null) {
						//wfMatlApplys.get(i).setLastUpd(getSession().getUserId());
						//wfMatlApplys.get(i).setLastUpdDate(new Date());
						new WfMatlApplyFacade().submit(wfMatlApplys.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfMatlApplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfMatlApplys != null && wfMatlApplys.size() > 0) {
				for(int i=0; i<wfMatlApplys.size();i++) {
					if(wfMatlApplys.get(i) != null) {
						//wfMatlApplys.get(i).setLastUpd(getSession().getUserId());
						//wfMatlApplys.get(i).setLastUpdDate(new Date());
						new WfMatlApplyFacade().submit(wfMatlApplys.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfMatlApplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfMatlApplys != null && wfMatlApplys.size() > 0) {
				for(int i=0; i<wfMatlApplys.size();i++) {
					if(wfMatlApplys.get(i) != null) {
						//wfMatlApplys.get(i).setLastUpd(getSession().getUserId());
						//wfMatlApplys.get(i).setLastUpdDate(new Date());
						new WfMatlApplyFacade().update(wfMatlApplys.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfMatlApplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfMatlApplys != null && wfMatlApplys.size() > 0) {
				for(int i=0; i<wfMatlApplys.size();i++) {
					if(wfMatlApplys.get(i) != null) {
						//wfMatlApplys.get(i).setLastUpd(getSession().getUserId());
						//wfMatlApplys.get(i).setLastUpdDate(new Date());
						new WfMatlApplyFacade().submit(wfMatlApplys.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfMatlApplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfMatlApplys != null && wfMatlApplys.size() > 0) {
				for(int i=0; i<wfMatlApplys.size();i++) {
					if(wfMatlApplys.get(i) != null) {
						//wfMatlApplys.get(i).setLastUpd(getSession().getUserId());
						//wfMatlApplys.get(i).setLastUpdDate(new Date());
						new WfMatlApplyFacade().submit(wfMatlApplys.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfMatlApplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfMatlApplys != null && wfMatlApplys.size() > 0) {
				for(int i=0; i<wfMatlApplys.size();i++) {
					if(wfMatlApplys.get(i) != null) {
						//wfMatlApplys.get(i).setLastUpd(getSession().getUserId());
						//wfMatlApplys.get(i).setLastUpdDate(new Date());
						new WfMatlApplyFacade().submit(wfMatlApplys.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfMatlApplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfMatlApplys != null && wfMatlApplys.size() > 0) {
				for(int i=0; i<wfMatlApplys.size();i++) {
					if(wfMatlApplys.get(i) != null) {
						//wfMatlApplys.get(i).setLastUpd(getSession().getUserId());
						//wfMatlApplys.get(i).setLastUpdDate(new Date());
						new WfMatlApplyFacade().submit(wfMatlApplys.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfMatlApplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfMatlApplys != null && wfMatlApplys.size() > 0) {
				for(int i=0; i<wfMatlApplys.size();i++) {
					if(wfMatlApplys.get(i) != null){
						//wfMatlApplys.get(i).setLastUpd(getSession().getUserId());
						//wfMatlApplys.get(i).setLastUpdDate(new Date());
						new WfMatlApplyFacade().submit(wfMatlApplys.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfMatlApplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfMatlApplys != null && wfMatlApplys.size() > 0) {
				for(int i=0; i<wfMatlApplys.size();i++) {
					if(wfMatlApplys.get(i) != null){
						//wfMatlApplys.get(i).setLastUpd(getSession().getUserId());
						//wfMatlApplys.get(i).setLastUpdDate(new Date());
						new WfMatlApplyFacade().review(wfMatlApplys.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfMatlApplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfMatlApplys != null && wfMatlApplys.size() > 0) {
				for(int i=0; i<wfMatlApplys.size();i++) {
					if(wfMatlApplys.get(i) != null) {
						//wfMatlApplys.get(i).setLastUpd(getSession().getUserId());
						//wfMatlApplys.get(i).setLastUpdDate(new Date());
						new WfMatlApplyFacade().review(wfMatlApplys.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfMatlApplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfMatlApplys != null && wfMatlApplys.size() > 0) {
				for(int i=0; i<wfMatlApplys.size();i++) {
					if(wfMatlApplys.get(i) != null) {
						//wfMatlApplys.get(i).setLastUpd(getSession().getUserId());
						//wfMatlApplys.get(i).setLastUpdDate(new Date());
						new WfMatlApplyFacade().confirm(wfMatlApplys.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfMatlApplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfMatlApplys != null && wfMatlApplys.size() > 0) {
				for(int i=0; i<wfMatlApplys.size();i++) {
					if(wfMatlApplys.get(i) != null) {
						//wfMatlApplys.get(i).setLastUpd(getSession().getUserId());
						//wfMatlApplys.get(i).setLastUpdDate(new Date());
						new WfMatlApplyFacade().confirm(wfMatlApplys.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfMatlApplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfMatlApplys != null && wfMatlApplys.size() > 0) {
					for(int i=0; i<wfMatlApplys.size();i++) {
						if(wfMatlApplys.get(i) != null) {
							//wfMatlApplys.get(i).setLastUpd(getSession().getUserId());
							//wfMatlApplys.get(i).setLastUpdDate(new Date());
							new WfMatlApplyFacade().confirm(wfMatlApplys.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfMatlApplyAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfMatlApply> wfMatlApplys = new WfMatlApplyFacade().find(wfMatlApply);
			if(wfMatlApplys != null && wfMatlApplys.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfMatlApplys.size();i++) {
					row++;
					int m = 0;
					if(wfMatlApplys.get(i).getMatlId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfMatlApplys.get(i).getMatlId(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getInfoId() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getInfoId(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getBSTUZ() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getBSTUZ(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getDISLS() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getDISLS(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getKAUSF() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getKAUSF(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getMATKL() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getMATKL(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getWRKST() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getWRKST(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getSOBSL() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getSOBSL(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getBKLAS() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getBKLAS(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getRemark(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getPLIFZ() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getPLIFZ(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getMTART() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getMTART(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getBSTMA() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getBSTMA(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getGEWEI() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getGEWEI(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getMAKTX() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getMAKTX(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getMEINS() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getMEINS(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getDISPO() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getDISPO(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getMMSTA() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getMMSTA(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getVOLEH() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getVOLEH(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getBSTME() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getBSTME(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getBSTMI() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getBSTMI(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getBRGEW() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getBRGEW(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getBSTUN() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getBSTUN(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getBSTRF() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getBSTRF(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getVOLUM() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getVOLUM(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getMATNR() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getMATNR(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getLGFSB() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getLGFSB(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getBESKZ() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getBESKZ(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getSTPRS() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getSTPRS(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getWERKS() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getWERKS(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getGROES() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getGROES(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getDISMM() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getDISMM(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getSTAWN() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getSTAWN(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getVPRSV() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getVPRSV(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getNTGEW() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getNTGEW(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getEXTWG() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getEXTWG(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getNORMT() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getNORMT(),wcformat));
					m++;
					if(wfMatlApplys.get(i).getFERTH() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfMatlApplys.get(i).getFERTH(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfMatlApplyListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfMatlApplys != null && wfMatlApplys.size() > 0) {
				for(int i=0; i<wfMatlApplys.size();i++) {
					if(wfMatlApplys.get(i) != null) {
						//wfMatlApplys.get(i).setLastUpd(getSession().getUserId());
						//wfMatlApplys.get(i).setLastUpdDate(new Date());
						new WfMatlApplyFacade().confirm(wfMatlApplys.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfMatlApplyAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfMatlApplys != null && wfMatlApplys.size() > 0) {
				for(int i=0; i<wfMatlApplys.size();i++) {
					if(wfMatlApplys.get(i) != null) {
						//wfMatlApplys.get(i).setLastUpd(getSession().getUserId());
						//wfMatlApplys.get(i).setLastUpdDate(new Date());
						new WfMatlApplyFacade().confirm(wfMatlApplys.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfMatlApplyAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfMatlApply> getWfMatlApplys() {
		return wfMatlApplys;
	}
	public void setWfMatlApplys(List<WfMatlApply> wfMatlApplys) {
		this.wfMatlApplys = wfMatlApplys;
	}
	public WfMatlApply getWfMatlApply() {
		return wfMatlApply;
	}
	public void setWfMatlApply(WfMatlApply wfMatlApply) {
		this.wfMatlApply = wfMatlApply;
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