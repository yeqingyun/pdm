package zrsy.ww.json;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
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

import zrsy.facade.BtnFacade;
import zrsy.facade.DeptFacade;
import zrsy.vo.Btn;
import zrsy.vo.Dept;
import zrsy.vo.json.DeptJson;
import zrsy.vo.json.TreeJson;
import zrsy.vo.liger.LiToolBar;
import zrsy.vo.liger.LiToolBarData;

public class DeptAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<Dept> depts;
	private Dept dept = new Dept();
	private java.util.List<zrsy.vo.Usr> usrs;
	private java.util.List<zrsy.vo.Com> coms;
	private java.util.List<zrsy.vo.json.TreeJson> treeJsons;
	public String execute() throws Exception {
		try {
			if(dept != null && dept.getDeptId() != null) {
				dept = new DeptFacade().findById(dept);
				setJson(JSON.toJSONString(dept)); 
			}
			coms = new zrsy.facade.ComFacade().find("select "+zrsy.vo.Com.SELF_FIELDS+" from Com where status=1 ",zrsy.vo.Com.SELF_FIELDS);
			depts = new zrsy.facade.DeptFacade().find("select "+zrsy.vo.Dept.SELF_FIELDS+" from Dept where status=1",zrsy.vo.Dept.SELF_FIELDS);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("DeptAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String loadTree() throws Exception {
		try {
			
			depts = new zrsy.facade.DeptFacade().find("select "+zrsy.vo.Dept.SELF_FIELDS+" from Dept where status=1",zrsy.vo.Dept.SELF_FIELDS);

			for(int i=0; i<depts.size(); i++) {
				TreeJson treeJson = new TreeJson();
				//treeJson.setId(depts.get(i).getDeptId());
				treeJson.setText(depts.get(i).getDeptNm());
				this.addtoTreeJsons(treeJson);
				for(int j=0; j<depts.size(); j++) {
					TreeJson children = new TreeJson();
					if(depts.get(i).getDeptId()==depts.get(j).getParent()){
						//children.setId(depts.get(j).getDeptId());
						children.setText(depts.get(j).getDeptNm());
						treeJson.addtoChildren(children);
					}
				}
			}
			setJson(JSON.toJSONString(treeJsons)); 
			System.out.println(getJson());
		}
		catch(Exception e) { 
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("IndexAction Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String selectDept()throws Exception {
		try{
			String deptsql = "WITH CTE as" +
			" (" +
			" SELECT DeptId,Parent,DeptNo,DeptNm,leve,Status FROM Dept WHERE Dept.parent = "+dept.getParent() +
			" UNION ALL" +
			" SELECT A.DeptId,A.Parent,A.DeptNo,A.DeptNm,A.leve,A.Status FROM Dept A,CTE " +
			" where A.parent = CTE.deptId" +
			" ) " +
			" SELECT *  from CTE";
			
			depts = new DeptFacade().find(deptsql,"DeptId,Parent,DeptNo,DeptNm,Leve,Status");
			
			this.setJson(JSON.toJSONString(depts));
		}
		catch(Exception e) {
				setMsg(MSG.F_SEA);
				Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
				return ERROR;
		}
		return PGLIS;
	}
	
	public String add() throws Exception {
		try {
			//if(dept != null && dept.getDeptId() != null) {
				//dept = new DeptFacade().findById(dept);
				//setJson(JSON.toJSONString(dept)); 
			//}
			coms = new zrsy.facade.ComFacade().find("select "+zrsy.vo.Com.SELF_FIELDS+" from Com where status=1 ",zrsy.vo.Com.SELF_FIELDS);
			depts = new zrsy.facade.DeptFacade().find("select "+zrsy.vo.Dept.SELF_FIELDS+" from Dept where status=1",zrsy.vo.Dept.SELF_FIELDS);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("DeptAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(dept != null && dept.getDeptId() != null) {
				dept = new DeptFacade().findById(dept);
				setJson(JSON.toJSONString(dept)); 
			}
			coms = new zrsy.facade.ComFacade().find("select "+zrsy.vo.Com.SELF_FIELDS+" from Com",zrsy.vo.Com.SELF_FIELDS);
			depts = new zrsy.facade.DeptFacade().find("select "+zrsy.vo.Dept.SELF_FIELDS+" from Dept",zrsy.vo.Dept.SELF_FIELDS);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("DeptAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(dept != null && dept.getDeptId() != null) {
				dept = new DeptFacade().findById(dept);
				setJson(JSON.toJSONString(dept)); 
			}
			coms = new zrsy.facade.ComFacade().find("select "+zrsy.vo.Com.SELF_FIELDS+" from Com",zrsy.vo.Com.SELF_FIELDS);
			depts = new zrsy.facade.DeptFacade().find("select "+zrsy.vo.Dept.SELF_FIELDS+" from Dept",zrsy.vo.Dept.SELF_FIELDS);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("DeptAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(dept == null) dept = new Dept();
			int total = new DeptFacade().amount(dept);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			depts = new DeptFacade().find(dept,getPageVO());
			DeptJson deptJson = new DeptJson();
			deptJson.Rows = depts;
			deptJson.Total = total;
			setJson(JSON.toJSONString(deptJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("DeptAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			dept.setUsrs(usrs);
			if(dept.getDeptId() == null){
				dept.setCreateBy(getUsrSession().getId());
				dept.setCreateDate(new Date());
				new DeptFacade().save(dept);
			}else{
				dept.setLastUpd(getUsrSession().getId());
				dept.setLastDate(new Date());
				new DeptFacade().update(dept);
			}
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("DeptAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//dept.setCreateBy(getSession().getUserId());
			//dept.setCreateDate(new Date());
			//dept.setLastUpd(getSession().getUserId());
			//dept.setLastUpdDate(new Date());
			new DeptFacade().update(dept);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("DeptAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(depts != null && depts.size() > 0) {
				for(int i=0; i<depts.size();i++) {
					if(depts.get(i) != null) {
						//depts.get(i).setLastUpd(getSession().getUserId());
						//depts.get(i).setLastUpdDate(new Date());
						new DeptFacade().submit(depts.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("DeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(depts != null && depts.size() > 0) {
				for(int i=0; i<depts.size();i++) {
					if(depts.get(i) != null) {
						//depts.get(i).setLastUpd(getSession().getUserId());
						//depts.get(i).setLastUpdDate(new Date());
						new DeptFacade().submit(depts.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("DeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(depts != null && depts.size() > 0) {
				for(int i=0; i<depts.size();i++) {
					if(depts.get(i) != null) {
						//depts.get(i).setLastUpd(getSession().getUserId());
						//depts.get(i).setLastUpdDate(new Date());
						new DeptFacade().update(depts.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("DeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(depts != null && depts.size() > 0) {
				for(int i=0; i<depts.size();i++) {
					if(depts.get(i) != null) {
						//depts.get(i).setLastUpd(getSession().getUserId());
						//depts.get(i).setLastUpdDate(new Date());
						new DeptFacade().submit(depts.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("DeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(depts != null && depts.size() > 0) {
				for(int i=0; i<depts.size();i++) {
					if(depts.get(i) != null) {
						//depts.get(i).setLastUpd(getSession().getUserId());
						//depts.get(i).setLastUpdDate(new Date());
						new DeptFacade().submit(depts.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("DeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(depts != null && depts.size() > 0) {
				for(int i=0; i<depts.size();i++) {
					if(depts.get(i) != null) {
						//depts.get(i).setLastUpd(getSession().getUserId());
						//depts.get(i).setLastUpdDate(new Date());
						new DeptFacade().submit(depts.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("DeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(depts != null && depts.size() > 0) {
				for(int i=0; i<depts.size();i++) {
					if(depts.get(i) != null) {
						//depts.get(i).setLastUpd(getSession().getUserId());
						//depts.get(i).setLastUpdDate(new Date());
						new DeptFacade().submit(depts.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("DeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(depts != null && depts.size() > 0) {
				for(int i=0; i<depts.size();i++) {
					if(depts.get(i) != null){
						//depts.get(i).setLastUpd(getSession().getUserId());
						//depts.get(i).setLastUpdDate(new Date());
						new DeptFacade().submit(depts.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("DeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(depts != null && depts.size() > 0) {
				for(int i=0; i<depts.size();i++) {
					if(depts.get(i) != null){
						//depts.get(i).setLastUpd(getSession().getUserId());
						//depts.get(i).setLastUpdDate(new Date());
						new DeptFacade().review(depts.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("DeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(depts != null && depts.size() > 0) {
				for(int i=0; i<depts.size();i++) {
					if(depts.get(i) != null) {
						//depts.get(i).setLastUpd(getSession().getUserId());
						//depts.get(i).setLastUpdDate(new Date());
						new DeptFacade().review(depts.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("DeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(depts != null && depts.size() > 0) {
				for(int i=0; i<depts.size();i++) {
					if(depts.get(i) != null) {
						//depts.get(i).setLastUpd(getSession().getUserId());
						//depts.get(i).setLastUpdDate(new Date());
						new DeptFacade().confirm(depts.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("DeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(depts != null && depts.size() > 0) {
				for(int i=0; i<depts.size();i++) {
					if(depts.get(i) != null) {
						//depts.get(i).setLastUpd(getSession().getUserId());
						//depts.get(i).setLastUpdDate(new Date());
						new DeptFacade().confirm(depts.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("DeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(depts != null && depts.size() > 0) {
					for(int i=0; i<depts.size();i++) {
						if(depts.get(i) != null) {
							//depts.get(i).setLastUpd(getSession().getUserId());
							//depts.get(i).setLastUpdDate(new Date());
							new DeptFacade().confirm(depts.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("DeptAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<Dept> depts = new DeptFacade().find(dept);
			if(depts != null && depts.size() > 0) {
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
					ws.addCell(new Label(index,1,"上级部门",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"级别",wcformat));
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
					ws.addCell(new Label(index,1,"公司编号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"公司名称",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"备注",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<depts.size();i++) {
					row++;
					int m = 0;
					if(depts.get(i).getDeptId() != null) 
						ws.addCell(new jxl.write.Number(m,row,depts.get(i).getDeptId(),wcformat));
					m++;
					if(depts.get(i).getComId() != null) 
						ws.addCell(new jxl.write.Number(m,row,depts.get(i).getComId(),wcformat));
					m++;
					if(depts.get(i).getParent() != null) 
						ws.addCell(new jxl.write.Number(m,row,depts.get(i).getParent(),wcformat));
					m++;
					if(depts.get(i).getLeve() != null) 
						ws.addCell(new jxl.write.Number(m,row,depts.get(i).getLeve(),wcformat));
					m++;
					if(depts.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,depts.get(i).getStatus(),wcformat));
					m++;
					if(depts.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,depts.get(i).getCreateBy(),wcformat));
					m++;
					if(depts.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,depts.get(i).getLastUpd(),wcformat));
					m++;
					if(depts.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,depts.get(i).getCreateDate(),wcformat));
					m++;
					if(depts.get(i).getLastDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,depts.get(i).getLastDate(),wcformat));
					m++;
					if(depts.get(i).getDeptNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,depts.get(i).getDeptNo(),wcformat));
					m++;
					if(depts.get(i).getDeptNm() != null) 
						ws.addCell(new jxl.write.Label(m,row,depts.get(i).getDeptNm(),wcformat));
					m++;
					if(depts.get(i).getRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,depts.get(i).getRemark(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("DeptListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(depts != null && depts.size() > 0) {
				for(int i=0; i<depts.size();i++) {
					if(depts.get(i) != null) {
						//depts.get(i).setLastUpd(getSession().getUserId());
						//depts.get(i).setLastUpdDate(new Date());
						new DeptFacade().confirm(depts.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("DeptAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(depts != null && depts.size() > 0) {
				for(int i=0; i<depts.size();i++) {
					if(depts.get(i) != null) {
						//depts.get(i).setLastUpd(getSession().getUserId());
						//depts.get(i).setLastUpdDate(new Date());
						new DeptFacade().confirm(depts.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("DeptAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<Dept> getDepts() {
		return depts;
	}
	public void setDepts(List<Dept> depts) {
		this.depts = depts;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
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
	public java.util.List<zrsy.vo.Usr> getUsrs() {
		return usrs;
	}
	public void setUsrs(java.util.List<zrsy.vo.Usr> usrs){
		this.usrs = usrs;
	}
	public void addtoUsrs(zrsy.vo.Usr usr){
		if(getUsrs() == null) setUsrs(new java.util.ArrayList<zrsy.vo.Usr>());
			getUsrs().add(usr);
	}
	public java.util.List<zrsy.vo.Com> getComs() {
		return coms;
	}
	public void setComs(java.util.List<zrsy.vo.Com> coms){
		this.coms = coms;
	}
	public void addtoComs(zrsy.vo.Com com){
		if(getComs() == null) setComs(new java.util.ArrayList<zrsy.vo.Com>());
			getComs().add(com);
	}
	public java.util.List<zrsy.vo.json.TreeJson> getTreeJsons() {
		return treeJsons;
	}
	public void setTreeJsons(java.util.List<zrsy.vo.json.TreeJson> treeJsons) {
		this.treeJsons = treeJsons;
	}

	public void addtoTreeJsons(zrsy.vo.json.TreeJson treeJson) {
		if(getTreeJsons() == null) setTreeJsons(new ArrayList<zrsy.vo.json.TreeJson>());
		getTreeJsons().add(treeJson);
	}
}