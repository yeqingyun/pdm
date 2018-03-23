package gnwf.ww.json;

import gnwf.facade.WfCateFacade;
import gnwf.vo.WfCate;
import gnwf.vo.json.WfCateJson;
import gnwf.ww.BasicAction;
import gnwf.ww.MSG;

import java.io.File;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

public class WfCateAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfCate> wfCates;
	private WfCate wfCate = new WfCate();
	
	private List<WfCate> cateList;

	public String execute() throws Exception {
		try {
			String sql = "select CateId,CateName from WfCate";
			cateList = new WfCateFacade().find(sql, "WfCate.CateId,WfCate.CateName");
			
			if(wfCate != null && wfCate.getCateId() != null) {
				wfCate = new WfCateFacade().findById(wfCate);
				setJson(JSON.toJSONString(wfCate)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfCateAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			String sql = "select CateId,CateName from WfCate";
			cateList = new WfCateFacade().find(sql, "WfCate.CateId,WfCate.CateName");
			
			//if(wfCate != null && wfCate.getCateId() != null) {
				//wfCate = new WfCateFacade().findById(wfCate);
				//setJson(JSON.toJSONString(wfCate)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfCateAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			String sql = "select CateId,CateName from WfCate where cateid!="+wfCate.getCateId();
			cateList = new WfCateFacade().find(sql, "WfCate.CateId,WfCate.CateName");
			
			if(wfCate != null && wfCate.getCateId() != null) {
				wfCate = new WfCateFacade().findById(wfCate);
				setJson(JSON.toJSONString(wfCate)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfCateAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			String sql = "select CateId,CateName from WfCate where cateid!="+wfCate.getCateId();
			cateList = new WfCateFacade().find(sql, "WfCate.CateId,WfCate.CateName");
			
			if(wfCate != null && wfCate.getCateId() != null) {
				wfCate = new WfCateFacade().findById(wfCate);
				setJson(JSON.toJSONString(wfCate)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfCateAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			String sql = "select CateId,CateName from WfCate";
			cateList = new WfCateFacade().find(sql, "WfCate.CateId,WfCate.CateName");
			
			if(wfCate == null) wfCate = new WfCate();
			int total = new WfCateFacade().amount(wfCate);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfCates = new WfCateFacade().find(wfCate,getPageVO());
			WfCateJson wfCateJson = new WfCateJson();
			wfCateJson.Rows = wfCates;
			wfCateJson.Total = total;
			setJson(JSON.toJSONString(wfCateJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfCateAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			if(wfCate.getCateId() == null){
				wfCate.setCreateBy(getUsrSession().getId());
				wfCate.setCreateDate(new Date());
				new WfCateFacade().save(wfCate);
			}
			else{
				new WfCateFacade().update(wfCate);
				wfCate.setLastUpd(getUsrSession().getId());
				wfCate.setLastUpdDate(new Date());
			}
				
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfCateAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfCate.setCreateBy(getSession().getUserId());
			//wfCate.setCreateDate(new Date());
			//wfCate.setLastUpd(getSession().getUserId());
			//wfCate.setLastUpdDate(new Date());
			new WfCateFacade().update(wfCate);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfCateAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfCates != null && wfCates.size() > 0) {
				for(int i=0; i<wfCates.size();i++) {
					if(wfCates.get(i) != null) {
						//wfCates.get(i).setLastUpd(getSession().getUserId());
						//wfCates.get(i).setLastUpdDate(new Date());
						new WfCateFacade().submit(wfCates.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfCateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfCates != null && wfCates.size() > 0) {
				for(int i=0; i<wfCates.size();i++) {
					if(wfCates.get(i) != null) {
						//wfCates.get(i).setLastUpd(getSession().getUserId());
						//wfCates.get(i).setLastUpdDate(new Date());
						new WfCateFacade().submit(wfCates.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfCateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfCates != null && wfCates.size() > 0) {
				for(int i=0; i<wfCates.size();i++) {
					if(wfCates.get(i) != null) {
						//wfCates.get(i).setLastUpd(getSession().getUserId());
						//wfCates.get(i).setLastUpdDate(new Date());
						new WfCateFacade().update(wfCates.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfCateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfCates != null && wfCates.size() > 0) {
				for(int i=0; i<wfCates.size();i++) {
					if(wfCates.get(i) != null) {
						//wfCates.get(i).setLastUpd(getSession().getUserId());
						//wfCates.get(i).setLastUpdDate(new Date());
						new WfCateFacade().submit(wfCates.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfCateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfCates != null && wfCates.size() > 0) {
				for(int i=0; i<wfCates.size();i++) {
					if(wfCates.get(i) != null) {
						//wfCates.get(i).setLastUpd(getSession().getUserId());
						//wfCates.get(i).setLastUpdDate(new Date());
						new WfCateFacade().submit(wfCates.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfCateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfCates != null && wfCates.size() > 0) {
				for(int i=0; i<wfCates.size();i++) {
					if(wfCates.get(i) != null) {
						//wfCates.get(i).setLastUpd(getSession().getUserId());
						//wfCates.get(i).setLastUpdDate(new Date());
						new WfCateFacade().submit(wfCates.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfCateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfCates != null && wfCates.size() > 0) {
				for(int i=0; i<wfCates.size();i++) {
					if(wfCates.get(i) != null) {
						//wfCates.get(i).setLastUpd(getSession().getUserId());
						//wfCates.get(i).setLastUpdDate(new Date());
						new WfCateFacade().submit(wfCates.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfCateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfCates != null && wfCates.size() > 0) {
				for(int i=0; i<wfCates.size();i++) {
					if(wfCates.get(i) != null){
						//wfCates.get(i).setLastUpd(getSession().getUserId());
						//wfCates.get(i).setLastUpdDate(new Date());
						new WfCateFacade().submit(wfCates.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfCateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfCates != null && wfCates.size() > 0) {
				for(int i=0; i<wfCates.size();i++) {
					if(wfCates.get(i) != null){
						//wfCates.get(i).setLastUpd(getSession().getUserId());
						//wfCates.get(i).setLastUpdDate(new Date());
						new WfCateFacade().review(wfCates.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfCateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfCates != null && wfCates.size() > 0) {
				for(int i=0; i<wfCates.size();i++) {
					if(wfCates.get(i) != null) {
						//wfCates.get(i).setLastUpd(getSession().getUserId());
						//wfCates.get(i).setLastUpdDate(new Date());
						new WfCateFacade().review(wfCates.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfCateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfCates != null && wfCates.size() > 0) {
				for(int i=0; i<wfCates.size();i++) {
					if(wfCates.get(i) != null) {
						//wfCates.get(i).setLastUpd(getSession().getUserId());
						//wfCates.get(i).setLastUpdDate(new Date());
						new WfCateFacade().confirm(wfCates.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfCateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfCates != null && wfCates.size() > 0) {
				for(int i=0; i<wfCates.size();i++) {
					if(wfCates.get(i) != null) {
						//wfCates.get(i).setLastUpd(getSession().getUserId());
						//wfCates.get(i).setLastUpdDate(new Date());
						new WfCateFacade().confirm(wfCates.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfCateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfCates != null && wfCates.size() > 0) {
					for(int i=0; i<wfCates.size();i++) {
						if(wfCates.get(i) != null) {
							//wfCates.get(i).setLastUpd(getSession().getUserId());
							//wfCates.get(i).setLastUpdDate(new Date());
							new WfCateFacade().confirm(wfCates.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfCateAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfCate> wfCates = new WfCateFacade().find(wfCate);
			if(wfCates != null && wfCates.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"分类ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"上级分类",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"分类级别",wcformat));
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
					ws.addCell(new Label(index,1,"分类名称",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfCates.size();i++) {
					row++;
					int m = 0;
					if(wfCates.get(i).getCateId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfCates.get(i).getCateId(),wcformat));
					m++;
					if(wfCates.get(i).getCateParent() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfCates.get(i).getCateParent(),wcformat));
					m++;
					if(wfCates.get(i).getCateLevel() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfCates.get(i).getCateLevel(),wcformat));
					m++;
					if(wfCates.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfCates.get(i).getStatus(),wcformat));
					m++;
					if(wfCates.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfCates.get(i).getCreateBy(),wcformat));
					m++;
					if(wfCates.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfCates.get(i).getLastUpd(),wcformat));
					m++;
					if(wfCates.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfCates.get(i).getCreateDate(),wcformat));
					m++;
					if(wfCates.get(i).getLastUpdDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfCates.get(i).getLastUpdDate(),wcformat));
					m++;
					if(wfCates.get(i).getCateName() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfCates.get(i).getCateName(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfCateListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfCates != null && wfCates.size() > 0) {
				for(int i=0; i<wfCates.size();i++) {
					if(wfCates.get(i) != null) {
						//wfCates.get(i).setLastUpd(getSession().getUserId());
						//wfCates.get(i).setLastUpdDate(new Date());
						new WfCateFacade().confirm(wfCates.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfCateAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfCates != null && wfCates.size() > 0) {
				for(int i=0; i<wfCates.size();i++) {
					if(wfCates.get(i) != null) {
						//wfCates.get(i).setLastUpd(getSession().getUserId());
						//wfCates.get(i).setLastUpdDate(new Date());
						new WfCateFacade().confirm(wfCates.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfCateAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfCate> getWfCates() {
		return wfCates;
	}
	public void setWfCates(List<WfCate> wfCates) {
		this.wfCates = wfCates;
	}
	public WfCate getWfCate() {
		return wfCate;
	}
	public void setWfCate(WfCate wfCate) {
		this.wfCate = wfCate;
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
	public List<WfCate> getCateList() {
		return cateList;
	}
	public void setCateList(List<WfCate> cateList) {
		this.cateList = cateList;
	}

}