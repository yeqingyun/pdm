package zrsy.ww.json;

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

import zrsy.ww.MSG;
import zrsy.ww.BasicAction;

import zrsy.facade.IpAddrFacade;
import zrsy.vo.IpAddr;
import zrsy.vo.json.IpAddrJson;

public class IpAddrAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<IpAddr> ipAddrs;
	private IpAddr ipAddr = new IpAddr();

	public String execute() throws Exception {
		try {
			if(ipAddr != null && ipAddr.getIpId() != null) {
				ipAddr = new IpAddrFacade().findById(ipAddr);
				setJson(JSON.toJSONString(ipAddr)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("IpAddrAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(ipAddr != null && ipAddr.getIpId() != null) {
				//ipAddr = new IpAddrFacade().findById(ipAddr);
				//setJson(JSON.toJSONString(ipAddr)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("IpAddrAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(ipAddr != null && ipAddr.getIpId() != null) {
				ipAddr = new IpAddrFacade().findById(ipAddr);
				setJson(JSON.toJSONString(ipAddr)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("IpAddrAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(ipAddr != null && ipAddr.getIpId() != null) {
				ipAddr = new IpAddrFacade().findById(ipAddr);
				setJson(JSON.toJSONString(ipAddr)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("IpAddrAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(ipAddr == null) ipAddr = new IpAddr();
			int total = new IpAddrFacade().amount(ipAddr);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			ipAddrs = new IpAddrFacade().find(ipAddr,getPageVO());
			IpAddrJson ipAddrJson = new IpAddrJson();
			ipAddrJson.Rows = ipAddrs;
			ipAddrJson.Total = total;
			setJson(JSON.toJSONString(ipAddrJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("IpAddrAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			
			ipAddr.setLastUpd(getUsrSession().getId());
			ipAddr.setLastDate(new Date());
			if(ipAddr.getIpId() == null){
				ipAddr.setCreateBy(getUsrSession().getId());
				ipAddr.setCreateDate(new Date());
				new IpAddrFacade().save(ipAddr);
			}
			else
				new IpAddrFacade().update(ipAddr);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("IpAddrAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//ipAddr.setCreateBy(getSession().getUserId());
			//ipAddr.setCreateDate(new Date());
			//ipAddr.setLastUpd(getSession().getUserId());
			//ipAddr.setLastUpdDate(new Date());
			new IpAddrFacade().update(ipAddr);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("IpAddrAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(ipAddrs != null && ipAddrs.size() > 0) {
				for(int i=0; i<ipAddrs.size();i++) {
					if(ipAddrs.get(i) != null) {
						//ipAddrs.get(i).setLastUpd(getSession().getUserId());
						//ipAddrs.get(i).setLastUpdDate(new Date());
						new IpAddrFacade().submit(ipAddrs.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("IpAddrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(ipAddrs != null && ipAddrs.size() > 0) {
				for(int i=0; i<ipAddrs.size();i++) {
					if(ipAddrs.get(i) != null) {
						//ipAddrs.get(i).setLastUpd(getSession().getUserId());
						//ipAddrs.get(i).setLastUpdDate(new Date());
						new IpAddrFacade().submit(ipAddrs.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("IpAddrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(ipAddrs != null && ipAddrs.size() > 0) {
				for(int i=0; i<ipAddrs.size();i++) {
					if(ipAddrs.get(i) != null) {
						//ipAddrs.get(i).setLastUpd(getSession().getUserId());
						//ipAddrs.get(i).setLastUpdDate(new Date());
						new IpAddrFacade().update(ipAddrs.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("IpAddrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(ipAddrs != null && ipAddrs.size() > 0) {
				for(int i=0; i<ipAddrs.size();i++) {
					if(ipAddrs.get(i) != null) {
						//ipAddrs.get(i).setLastUpd(getSession().getUserId());
						//ipAddrs.get(i).setLastUpdDate(new Date());
						new IpAddrFacade().submit(ipAddrs.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("IpAddrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(ipAddrs != null && ipAddrs.size() > 0) {
				for(int i=0; i<ipAddrs.size();i++) {
					if(ipAddrs.get(i) != null) {
						//ipAddrs.get(i).setLastUpd(getSession().getUserId());
						//ipAddrs.get(i).setLastUpdDate(new Date());
						new IpAddrFacade().submit(ipAddrs.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("IpAddrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(ipAddrs != null && ipAddrs.size() > 0) {
				for(int i=0; i<ipAddrs.size();i++) {
					if(ipAddrs.get(i) != null) {
						//ipAddrs.get(i).setLastUpd(getSession().getUserId());
						//ipAddrs.get(i).setLastUpdDate(new Date());
						new IpAddrFacade().submit(ipAddrs.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("IpAddrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(ipAddrs != null && ipAddrs.size() > 0) {
				for(int i=0; i<ipAddrs.size();i++) {
					if(ipAddrs.get(i) != null) {
						//ipAddrs.get(i).setLastUpd(getSession().getUserId());
						//ipAddrs.get(i).setLastUpdDate(new Date());
						new IpAddrFacade().submit(ipAddrs.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("IpAddrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(ipAddrs != null && ipAddrs.size() > 0) {
				for(int i=0; i<ipAddrs.size();i++) {
					if(ipAddrs.get(i) != null){
						//ipAddrs.get(i).setLastUpd(getSession().getUserId());
						//ipAddrs.get(i).setLastUpdDate(new Date());
						new IpAddrFacade().submit(ipAddrs.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("IpAddrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(ipAddrs != null && ipAddrs.size() > 0) {
				for(int i=0; i<ipAddrs.size();i++) {
					if(ipAddrs.get(i) != null){
						//ipAddrs.get(i).setLastUpd(getSession().getUserId());
						//ipAddrs.get(i).setLastUpdDate(new Date());
						new IpAddrFacade().review(ipAddrs.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("IpAddrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(ipAddrs != null && ipAddrs.size() > 0) {
				for(int i=0; i<ipAddrs.size();i++) {
					if(ipAddrs.get(i) != null) {
						//ipAddrs.get(i).setLastUpd(getSession().getUserId());
						//ipAddrs.get(i).setLastUpdDate(new Date());
						new IpAddrFacade().review(ipAddrs.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("IpAddrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(ipAddrs != null && ipAddrs.size() > 0) {
				for(int i=0; i<ipAddrs.size();i++) {
					if(ipAddrs.get(i) != null) {
						//ipAddrs.get(i).setLastUpd(getSession().getUserId());
						//ipAddrs.get(i).setLastUpdDate(new Date());
						new IpAddrFacade().confirm(ipAddrs.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("IpAddrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(ipAddrs != null && ipAddrs.size() > 0) {
				for(int i=0; i<ipAddrs.size();i++) {
					if(ipAddrs.get(i) != null) {
						//ipAddrs.get(i).setLastUpd(getSession().getUserId());
						//ipAddrs.get(i).setLastUpdDate(new Date());
						new IpAddrFacade().confirm(ipAddrs.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("IpAddrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(ipAddrs != null && ipAddrs.size() > 0) {
					for(int i=0; i<ipAddrs.size();i++) {
						if(ipAddrs.get(i) != null) {
							//ipAddrs.get(i).setLastUpd(getSession().getUserId());
							//ipAddrs.get(i).setLastUpdDate(new Date());
							new IpAddrFacade().confirm(ipAddrs.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("IpAddrAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<IpAddr> ipAddrs = new IpAddrFacade().find(ipAddr);
			if(ipAddrs != null && ipAddrs.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"创建日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"更新日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"IP标识",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"是否外网",wcformat));
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
					ws.addCell(new Label(index,1,"IP网段",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"描述",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<ipAddrs.size();i++) {
					row++;
					int m = 0;
					if(ipAddrs.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,ipAddrs.get(i).getCreateDate(),wcformat));
					m++;
					if(ipAddrs.get(i).getLastDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,ipAddrs.get(i).getLastDate(),wcformat));
					m++;
					if(ipAddrs.get(i).getIpId() != null) 
						ws.addCell(new jxl.write.Number(m,row,ipAddrs.get(i).getIpId(),wcformat));
					m++;
					if(ipAddrs.get(i).getIsWide() != null) 
						ws.addCell(new jxl.write.Number(m,row,ipAddrs.get(i).getIsWide(),wcformat));
					m++;
					if(ipAddrs.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,ipAddrs.get(i).getStatus(),wcformat));
					m++;
					if(ipAddrs.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,ipAddrs.get(i).getCreateBy(),wcformat));
					m++;
					if(ipAddrs.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,ipAddrs.get(i).getLastUpd(),wcformat));
					m++;
					if(ipAddrs.get(i).getIpSegment() != null) 
						ws.addCell(new jxl.write.Label(m,row,ipAddrs.get(i).getIpSegment(),wcformat));
					m++;
					if(ipAddrs.get(i).getAddrDesc() != null) 
						ws.addCell(new jxl.write.Label(m,row,ipAddrs.get(i).getAddrDesc(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("IpAddrListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(ipAddrs != null && ipAddrs.size() > 0) {
				for(int i=0; i<ipAddrs.size();i++) {
					if(ipAddrs.get(i) != null) {
						//ipAddrs.get(i).setLastUpd(getSession().getUserId());
						//ipAddrs.get(i).setLastUpdDate(new Date());
						new IpAddrFacade().confirm(ipAddrs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("IpAddrAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(ipAddrs != null && ipAddrs.size() > 0) {
				for(int i=0; i<ipAddrs.size();i++) {
					if(ipAddrs.get(i) != null) {
						//ipAddrs.get(i).setLastUpd(getSession().getUserId());
						//ipAddrs.get(i).setLastUpdDate(new Date());
						new IpAddrFacade().confirm(ipAddrs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("IpAddrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<IpAddr> getIpAddrs() {
		return ipAddrs;
	}
	public void setIpAddrs(List<IpAddr> ipAddrs) {
		this.ipAddrs = ipAddrs;
	}
	public IpAddr getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(IpAddr ipAddr) {
		this.ipAddr = ipAddr;
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