package zrprjt.ww.json;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;

import zrprjt.facade.DriverDtlFacade;
import zrprjt.vo.DriverDtl;
import zrprjt.vo.json.DriverDtlJson;
import zrprjt.ww.BasicAction;
import zrprjt.ww.MSG;

import com.alibaba.fastjson.JSON;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;

public class DriverDtlAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<DriverDtl> driverDtls;
	private DriverDtl driverDtl = new DriverDtl();

	public String execute() throws Exception {
		try {
			if(driverDtl != null && driverDtl.getDriveId() != null) {
				driverDtl = new DriverDtlFacade().findById(driverDtl);
				setJson(JSON.toJSONString(driverDtl)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("DriverDtlAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(driverDtl != null && driverDtl.getDriveId() != null) {
				//driverDtl = new DriverDtlFacade().findById(driverDtl);
				//setJson(JSON.toJSONString(driverDtl)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("DriverDtlAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(driverDtl != null && driverDtl.getDriveId() != null) {
				driverDtl = new DriverDtlFacade().findById(driverDtl);
				setJson(JSON.toJSONString(driverDtl)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("DriverDtlAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(driverDtl != null && driverDtl.getDriveId() != null) {
				driverDtl = new DriverDtlFacade().findById(driverDtl);
				setJson(JSON.toJSONString(driverDtl)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("DriverDtlAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			ActionContext ctx = ActionContext.getContext();
		    HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
		    String driveId = request.getParameter("driveId");
			if(driverDtl == null) driverDtl = new DriverDtl();
			DriverDtlJson driverDtlJson = new DriverDtlJson();
			if(driveId!=null && !"".equals(driveId)) {
				driverDtl.setDriveId(Integer.parseInt(driveId));
			} else {
					driverDtls = new ArrayList<DriverDtl>();
					driverDtlJson.Rows = driverDtls;
					driverDtlJson.Total = 0;
					setJson(JSON.toJSONString(driverDtlJson));
					return PGLIS;
			}
			int total = new DriverDtlFacade().amount(driverDtl);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			driverDtls = new DriverDtlFacade().find(driverDtl,getPageVO());
			driverDtlJson.Rows = driverDtls;
			driverDtlJson.Total = total;
			setJson(JSON.toJSONString(driverDtlJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("DriverDtlAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//driverDtl.setCreateBy(getSession().getUserId());
			//driverDtl.setCreateDate(new Date());
			//driverDtl.setLastUpd(getSession().getUserId());
			//driverDtl.setLastUpdDate(new Date());

			if(driverDtl.getDriveId() == null)
				new DriverDtlFacade().save(driverDtl);
			else
				new DriverDtlFacade().update(driverDtl);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("DriverDtlAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//driverDtl.setCreateBy(getSession().getUserId());
			//driverDtl.setCreateDate(new Date());
			//driverDtl.setLastUpd(getSession().getUserId());
			//driverDtl.setLastUpdDate(new Date());
			new DriverDtlFacade().update(driverDtl);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("DriverDtlAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(driverDtls != null && driverDtls.size() > 0) {
				for(int i=0; i<driverDtls.size();i++) {
					if(driverDtls.get(i) != null) {
						//driverDtls.get(i).setLastUpd(getSession().getUserId());
						//driverDtls.get(i).setLastUpdDate(new Date());
						new DriverDtlFacade().submit(driverDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("DriverDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(driverDtls != null && driverDtls.size() > 0) {
				for(int i=0; i<driverDtls.size();i++) {
					if(driverDtls.get(i) != null) {
						//driverDtls.get(i).setLastUpd(getSession().getUserId());
						//driverDtls.get(i).setLastUpdDate(new Date());
						new DriverDtlFacade().submit(driverDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("DriverDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(driverDtls != null && driverDtls.size() > 0) {
				for(int i=0; i<driverDtls.size();i++) {
					if(driverDtls.get(i) != null) {
						//driverDtls.get(i).setLastUpd(getSession().getUserId());
						//driverDtls.get(i).setLastUpdDate(new Date());
						new DriverDtlFacade().update(driverDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("DriverDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(driverDtls != null && driverDtls.size() > 0) {
				for(int i=0; i<driverDtls.size();i++) {
					if(driverDtls.get(i) != null) {
						//driverDtls.get(i).setLastUpd(getSession().getUserId());
						//driverDtls.get(i).setLastUpdDate(new Date());
						new DriverDtlFacade().submit(driverDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("DriverDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(driverDtls != null && driverDtls.size() > 0) {
				for(int i=0; i<driverDtls.size();i++) {
					if(driverDtls.get(i) != null) {
						//driverDtls.get(i).setLastUpd(getSession().getUserId());
						//driverDtls.get(i).setLastUpdDate(new Date());
						new DriverDtlFacade().submit(driverDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("DriverDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(driverDtls != null && driverDtls.size() > 0) {
				for(int i=0; i<driverDtls.size();i++) {
					if(driverDtls.get(i) != null) {
						//driverDtls.get(i).setLastUpd(getSession().getUserId());
						//driverDtls.get(i).setLastUpdDate(new Date());
						new DriverDtlFacade().submit(driverDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("DriverDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(driverDtls != null && driverDtls.size() > 0) {
				for(int i=0; i<driverDtls.size();i++) {
					if(driverDtls.get(i) != null) {
						//driverDtls.get(i).setLastUpd(getSession().getUserId());
						//driverDtls.get(i).setLastUpdDate(new Date());
						new DriverDtlFacade().submit(driverDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("DriverDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(driverDtls != null && driverDtls.size() > 0) {
				for(int i=0; i<driverDtls.size();i++) {
					if(driverDtls.get(i) != null){
						//driverDtls.get(i).setLastUpd(getSession().getUserId());
						//driverDtls.get(i).setLastUpdDate(new Date());
						new DriverDtlFacade().submit(driverDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("DriverDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(driverDtls != null && driverDtls.size() > 0) {
				for(int i=0; i<driverDtls.size();i++) {
					if(driverDtls.get(i) != null){
						//driverDtls.get(i).setLastUpd(getSession().getUserId());
						//driverDtls.get(i).setLastUpdDate(new Date());
						new DriverDtlFacade().review(driverDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("DriverDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(driverDtls != null && driverDtls.size() > 0) {
				for(int i=0; i<driverDtls.size();i++) {
					if(driverDtls.get(i) != null) {
						//driverDtls.get(i).setLastUpd(getSession().getUserId());
						//driverDtls.get(i).setLastUpdDate(new Date());
						new DriverDtlFacade().review(driverDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("DriverDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(driverDtls != null && driverDtls.size() > 0) {
				for(int i=0; i<driverDtls.size();i++) {
					if(driverDtls.get(i) != null) {
						//driverDtls.get(i).setLastUpd(getSession().getUserId());
						//driverDtls.get(i).setLastUpdDate(new Date());
						new DriverDtlFacade().confirm(driverDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("DriverDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(driverDtls != null && driverDtls.size() > 0) {
				for(int i=0; i<driverDtls.size();i++) {
					if(driverDtls.get(i) != null) {
						//driverDtls.get(i).setLastUpd(getSession().getUserId());
						//driverDtls.get(i).setLastUpdDate(new Date());
						new DriverDtlFacade().confirm(driverDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("DriverDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(driverDtls != null && driverDtls.size() > 0) {
					for(int i=0; i<driverDtls.size();i++) {
						if(driverDtls.get(i) != null) {
							//driverDtls.get(i).setLastUpd(getSession().getUserId());
							//driverDtls.get(i).setLastUpdDate(new Date());
							new DriverDtlFacade().confirm(driverDtls.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("DriverDtlAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<DriverDtl> driverDtls = new DriverDtlFacade().find(driverDtl);
			if(driverDtls != null && driverDtls.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"驱动Id",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"工作流Id",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"步骤Id",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<driverDtls.size();i++) {
					row++;
					int m = 0;
					if(driverDtls.get(i).getDriveId() != null) 
						ws.addCell(new jxl.write.Number(m,row,driverDtls.get(i).getDriveId(),wcformat));
					m++;
					if(driverDtls.get(i).getFlowId() != null) 
						ws.addCell(new jxl.write.Number(m,row,driverDtls.get(i).getFlowId(),wcformat));
					m++;
					if(driverDtls.get(i).getStepId() != null) 
						ws.addCell(new jxl.write.Number(m,row,driverDtls.get(i).getStepId(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("DriverDtlListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(driverDtls != null && driverDtls.size() > 0) {
				for(int i=0; i<driverDtls.size();i++) {
					if(driverDtls.get(i) != null) {
						//driverDtls.get(i).setLastUpd(getSession().getUserId());
						//driverDtls.get(i).setLastUpdDate(new Date());
						new DriverDtlFacade().confirm(driverDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("DriverDtlAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(driverDtls != null && driverDtls.size() > 0) {
				for(int i=0; i<driverDtls.size();i++) {
					if(driverDtls.get(i) != null) {
						//driverDtls.get(i).setLastUpd(getSession().getUserId());
						//driverDtls.get(i).setLastUpdDate(new Date());
						new DriverDtlFacade().confirm(driverDtls.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("DriverDtlAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<DriverDtl> getDriverDtls() {
		return driverDtls;
	}
	public void setDriverDtls(List<DriverDtl> driverDtls) {
		this.driverDtls = driverDtls;
	}
	public DriverDtl getDriverDtl() {
		return driverDtl;
	}
	public void setDriverDtl(DriverDtl driverDtl) {
		this.driverDtl = driverDtl;
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