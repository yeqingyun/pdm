package zrsy.ww.json;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
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
import zrsy.facade.NodeFacade;
import zrsy.facade.PgBtnFacade;
import zrsy.vo.Btn;
import zrsy.vo.Node;
import zrsy.vo.PgBtn;
import zrsy.vo.json.NodeJson;

public class NodeAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<Node> nodes;
	private Node node = new Node();
	private java.util.List<zrsy.vo.GpNode> gpNodes;
	private java.util.List<zrsy.vo.Menu> menus;
	private java.util.List<zrsy.vo.SyDef> syDefs;
	private List<Btn> btns0;
	private List<Btn> btns1;
	private List<Btn> btns3;
	private java.util.List<zrsy.vo.PgBtn> pgBtns;
	
	public String execute() throws Exception {
		try {
			if(node != null && node.getId() != null) {
				node = new NodeFacade().findById(node);
				setJson(JSON.toJSONString(node)); 
			}
			menus = new zrsy.facade.MenuFacade().find("select "+zrsy.vo.Menu.SELF_FIELDS+" from Menu where Menu.SyId = " + getUsrSession().getSyId(),zrsy.vo.Menu.SELF_FIELDS);
			syDefs = new zrsy.facade.SyDefFacade().find("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef where SyDef.SyId = " + getUsrSession().getSyId(),zrsy.vo.SyDef.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("NodeAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(node != null && node.getId() != null) {
				//node = new NodeFacade().findById(node);
				//setJson(JSON.toJSONString(node)); 
			//}
			menus = new zrsy.facade.MenuFacade().find("select "+zrsy.vo.Menu.SELF_FIELDS+" from Menu where Menu.SyId = " + getUsrSession().getSyId(),zrsy.vo.Menu.SELF_FIELDS);
			nodes = new zrsy.facade.NodeFacade().find("select "+zrsy.vo.Node.SELF_FIELDS+" from Node where Leve = 0 and Node.SyId = " + getUsrSession().getSyId(),zrsy.vo.Node.SELF_FIELDS);
			syDefs = new zrsy.facade.SyDefFacade().find("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef where SyDef.SyId = " + getUsrSession().getSyId(),zrsy.vo.SyDef.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("NodeAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(node != null && node.getId() != null) {
				node = new NodeFacade().findById(node);
				setJson(JSON.toJSONString(node)); 
			}
			menus = new zrsy.facade.MenuFacade().find("select "+zrsy.vo.Menu.SELF_FIELDS+" from Menu where Menu.SyId = " + getUsrSession().getSyId(),zrsy.vo.Menu.SELF_FIELDS);
			nodes = new zrsy.facade.NodeFacade().find("select "+zrsy.vo.Node.SELF_FIELDS+" from Node where Leve = 0 and Node.SyId = " + getUsrSession().getSyId(),zrsy.vo.Node.SELF_FIELDS);
			syDefs = new zrsy.facade.SyDefFacade().find("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef where SyDef.SyId = " + getUsrSession().getSyId(),zrsy.vo.SyDef.SELF_FIELDS);

			btns0 = new BtnFacade().find("select "+zrsy.vo.Btn.SELF_FIELDS+" from Btn where syId="+node.getSyId(),zrsy.vo.Btn.SELF_FIELDS);
			btns1 = new BtnFacade().find("select "+zrsy.vo.Btn.SELF_FIELDS+" from Btn where syId="+node.getSyId(),zrsy.vo.Btn.SELF_FIELDS);
			btns3 = new BtnFacade().find("select "+zrsy.vo.Btn.SELF_FIELDS+" from Btn where syId="+node.getSyId(),zrsy.vo.Btn.SELF_FIELDS);
			 
			List<PgBtn> _pgBtns0=new PgBtnFacade().find("select "+zrsy.vo.PgBtn.SELF_FIELDS+" from PgBtn where PgTyp=0 and nodeId="+node.getId(),zrsy.vo.PgBtn.SELF_FIELDS);;
			List<PgBtn> _pgBtns1=new PgBtnFacade().find("select "+zrsy.vo.PgBtn.SELF_FIELDS+" from PgBtn where PgTyp=1 and nodeId="+node.getId(),zrsy.vo.PgBtn.SELF_FIELDS);;
			List<PgBtn> _pgBtns3=new PgBtnFacade().find("select "+zrsy.vo.PgBtn.SELF_FIELDS+" from PgBtn where PgTyp=3 and nodeId="+node.getId(),zrsy.vo.PgBtn.SELF_FIELDS);;
			
			 for(int i=0;i<btns0.size();i++){
				 for(int j=0;j<_pgBtns0.size();j++){
					 if(btns0.get(i).getId().equals(_pgBtns0.get(j).getBtnId())){
						 btns0.get(i).setChecked("checked");
					 }
				 }
			 }
			 for(int i=0;i<btns1.size();i++){
				 for(int j=0;j<_pgBtns1.size();j++){
					 if(btns1.get(i).getId().equals(_pgBtns1.get(j).getBtnId())){
						 btns1.get(i).setChecked("checked");
					 }
				 }
			 }
			 for(int i=0;i<btns3.size();i++){
				 for(int j=0;j<_pgBtns3.size();j++){
					 if(btns3.get(i).getId().equals(_pgBtns3.get(j).getBtnId())){
						 btns3.get(i).setChecked("checked");
					 }
				 }
			 }
		}
		catch(Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("NodeAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(node != null && node.getId() != null) {
				node = new NodeFacade().findById(node);
				setJson(JSON.toJSONString(node)); 
			}
			menus = new zrsy.facade.MenuFacade().find("select "+zrsy.vo.Menu.SELF_FIELDS+" from Menu",zrsy.vo.Menu.SELF_FIELDS);
			syDefs = new zrsy.facade.SyDefFacade().find("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef",zrsy.vo.SyDef.SELF_FIELDS);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("NodeAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(node == null) node = new Node();
			node.setSyId(getUsrSession().getSyId());
			int total = new NodeFacade().amount(node);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			node.setSyId(getUsrSession().getSyId());
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			nodes = new NodeFacade().find(node,getPageVO());
			NodeJson nodeJson = new NodeJson();
			nodeJson.Rows = nodes;
			nodeJson.Total = total;
			setJson(JSON.toJSONString(nodeJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("NodeAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//node.setCreateBy(getSession().getUserId());
			//node.setCreateDate(new Date());
			//node.setLastUpd(getSession().getUserId());
			//node.setLastUpdDate(new Date());
			node.setGpNodes(gpNodes);
			node.setPgBtns(pgBtns);
			if(node.getId() == null)
				new NodeFacade().save(node);
			else
				new NodeFacade().update(node);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("NodeAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//node.setCreateBy(getSession().getUserId());
			//node.setCreateDate(new Date());
			//node.setLastUpd(getSession().getUserId());
			//node.setLastUpdDate(new Date());
			new NodeFacade().update(node);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("NodeAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(nodes != null && nodes.size() > 0) {
				for(int i=0; i<nodes.size();i++) {
					if(nodes.get(i) != null) {
						//nodes.get(i).setLastUpd(getSession().getUserId());
						//nodes.get(i).setLastUpdDate(new Date());
						new NodeFacade().submit(nodes.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("NodeAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(nodes != null && nodes.size() > 0) {
				for(int i=0; i<nodes.size();i++) {
					if(nodes.get(i) != null) {
						//nodes.get(i).setLastUpd(getSession().getUserId());
						//nodes.get(i).setLastUpdDate(new Date());
						new NodeFacade().submit(nodes.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("NodeAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(nodes != null && nodes.size() > 0) {
				for(int i=0; i<nodes.size();i++) {
					if(nodes.get(i) != null) {
						//nodes.get(i).setLastUpd(getSession().getUserId());
						//nodes.get(i).setLastUpdDate(new Date());
						new NodeFacade().update(nodes.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("NodeAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(nodes != null && nodes.size() > 0) {
				for(int i=0; i<nodes.size();i++) {
					if(nodes.get(i) != null) {
						//nodes.get(i).setLastUpd(getSession().getUserId());
						//nodes.get(i).setLastUpdDate(new Date());
						new NodeFacade().submit(nodes.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("NodeAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(nodes != null && nodes.size() > 0) {
				for(int i=0; i<nodes.size();i++) {
					if(nodes.get(i) != null) {
						//nodes.get(i).setLastUpd(getSession().getUserId());
						//nodes.get(i).setLastUpdDate(new Date());
						new NodeFacade().submit(nodes.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("NodeAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(nodes != null && nodes.size() > 0) {
				for(int i=0; i<nodes.size();i++) {
					if(nodes.get(i) != null) {
						//nodes.get(i).setLastUpd(getSession().getUserId());
						//nodes.get(i).setLastUpdDate(new Date());
						new NodeFacade().submit(nodes.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("NodeAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(nodes != null && nodes.size() > 0) {
				for(int i=0; i<nodes.size();i++) {
					if(nodes.get(i) != null) {
						//nodes.get(i).setLastUpd(getSession().getUserId());
						//nodes.get(i).setLastUpdDate(new Date());
						new NodeFacade().submit(nodes.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("NodeAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(nodes != null && nodes.size() > 0) {
				for(int i=0; i<nodes.size();i++) {
					if(nodes.get(i) != null){
						//nodes.get(i).setLastUpd(getSession().getUserId());
						//nodes.get(i).setLastUpdDate(new Date());
						new NodeFacade().submit(nodes.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("NodeAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(nodes != null && nodes.size() > 0) {
				for(int i=0; i<nodes.size();i++) {
					if(nodes.get(i) != null){
						//nodes.get(i).setLastUpd(getSession().getUserId());
						//nodes.get(i).setLastUpdDate(new Date());
						new NodeFacade().review(nodes.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("NodeAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(nodes != null && nodes.size() > 0) {
				for(int i=0; i<nodes.size();i++) {
					if(nodes.get(i) != null) {
						//nodes.get(i).setLastUpd(getSession().getUserId());
						//nodes.get(i).setLastUpdDate(new Date());
						new NodeFacade().review(nodes.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("NodeAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(nodes != null && nodes.size() > 0) {
				for(int i=0; i<nodes.size();i++) {
					if(nodes.get(i) != null) {
						//nodes.get(i).setLastUpd(getSession().getUserId());
						//nodes.get(i).setLastUpdDate(new Date());
						new NodeFacade().confirm(nodes.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("NodeAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(nodes != null && nodes.size() > 0) {
				for(int i=0; i<nodes.size();i++) {
					if(nodes.get(i) != null) {
						//nodes.get(i).setLastUpd(getSession().getUserId());
						//nodes.get(i).setLastUpdDate(new Date());
						new NodeFacade().confirm(nodes.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("NodeAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(nodes != null && nodes.size() > 0) {
					for(int i=0; i<nodes.size();i++) {
						if(nodes.get(i) != null) {
							//nodes.get(i).setLastUpd(getSession().getUserId());
							//nodes.get(i).setLastUpdDate(new Date());
							new NodeFacade().confirm(nodes.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("NodeAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<Node> nodes = new NodeFacade().find(node);
			if(nodes != null && nodes.size() > 0) {
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
					ws.addCell(new Label(index,1,"系统",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"上级ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"所属菜单",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"宽度",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"复选",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"级别",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"排序",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"名称",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"URL",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<nodes.size();i++) {
					row++;
					int m = 0;
					if(nodes.get(i).getId() != null) 
						ws.addCell(new jxl.write.Number(m,row,nodes.get(i).getId(),wcformat));
					m++;
					if(nodes.get(i).getSyId() != null) 
						ws.addCell(new jxl.write.Number(m,row,nodes.get(i).getSyId(),wcformat));
					m++;
					if(nodes.get(i).getParent() != null) 
						ws.addCell(new jxl.write.Number(m,row,nodes.get(i).getParent(),wcformat));
					m++;
					if(nodes.get(i).getMenuId() != null) 
						ws.addCell(new jxl.write.Number(m,row,nodes.get(i).getMenuId(),wcformat));
					m++;
					if(nodes.get(i).getNodeWidth() != null) 
						ws.addCell(new jxl.write.Number(m,row,nodes.get(i).getNodeWidth(),wcformat));
					m++;
					if(nodes.get(i).getCheckBox() != null) 
						ws.addCell(new jxl.write.Number(m,row,nodes.get(i).getCheckBox(),wcformat));
					m++;
					if(nodes.get(i).getLeve() != null) 
						ws.addCell(new jxl.write.Number(m,row,nodes.get(i).getLeve(),wcformat));
					m++;
					if(nodes.get(i).getSort() != null) 
						ws.addCell(new jxl.write.Number(m,row,nodes.get(i).getSort(),wcformat));
					m++;
					if(nodes.get(i).getText() != null) 
						ws.addCell(new jxl.write.Label(m,row,nodes.get(i).getText(),wcformat));
					m++;
					if(nodes.get(i).getUrl() != null) 
						ws.addCell(new jxl.write.Label(m,row,nodes.get(i).getUrl(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("NodeListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(nodes != null && nodes.size() > 0) {
				for(int i=0; i<nodes.size();i++) {
					if(nodes.get(i) != null) {
						//nodes.get(i).setLastUpd(getSession().getUserId());
						//nodes.get(i).setLastUpdDate(new Date());
						new NodeFacade().confirm(nodes.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("NodeAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(nodes != null && nodes.size() > 0) {
				for(int i=0; i<nodes.size();i++) {
					if(nodes.get(i) != null) {
						//nodes.get(i).setLastUpd(getSession().getUserId());
						//nodes.get(i).setLastUpdDate(new Date());
						new NodeFacade().confirm(nodes.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("NodeAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	public Node getNode() {
		return node;
	}
	public void setNode(Node node) {
		this.node = node;
	}
	public List<Btn> getBtns0() {
		return btns0;
	}
	public void setBtns0(List<Btn> btns0) {
		this.btns0 = btns0;
	}
	public List<Btn> getBtns1() {
		return btns1;
	}
	public void setBtns1(List<Btn> btns1) {
		this.btns1 = btns1;
	}
	public List<Btn> getBtns3() {
		return btns3;
	}
	public void setBtns3(List<Btn> btns3) {
		this.btns3 = btns3;
	}
	public void addtoBtns0(zrsy.vo.Btn btn){
		if(getBtns0() == null) setBtns0(new java.util.ArrayList<zrsy.vo.Btn>());
		getBtns0().add(btn);
	}
	public void addtoBtns1(zrsy.vo.Btn btn){
		if(getBtns1() == null) setBtns1(new java.util.ArrayList<zrsy.vo.Btn>());
		getBtns1().add(btn);
	}
	public void addtoBtns3(zrsy.vo.Btn btn){
		if(getBtns3() == null) setBtns3(new java.util.ArrayList<zrsy.vo.Btn>());
		getBtns3().add(btn);
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
	public java.util.List<zrsy.vo.GpNode> getGpNodes() {
		return gpNodes;
	}
	public void setGpNodes(java.util.List<zrsy.vo.GpNode> gpNodes){
		this.gpNodes = gpNodes;
	}
	public void addtoGpNodes(zrsy.vo.GpNode gpNode){
		if(getGpNodes() == null) setGpNodes(new java.util.ArrayList<zrsy.vo.GpNode>());
			getGpNodes().add(gpNode);
	}
	public java.util.List<zrsy.vo.Menu> getMenus() {
		return menus;
	}
	public void setMenus(java.util.List<zrsy.vo.Menu> menus){
		this.menus = menus;
	}
	public void addtoMenus(zrsy.vo.Menu menu){
		if(getMenus() == null) setMenus(new java.util.ArrayList<zrsy.vo.Menu>());
			getMenus().add(menu);
	}
	public java.util.List<zrsy.vo.SyDef> getSyDefs() {
		return syDefs;
	}
	public void setSyDefs(java.util.List<zrsy.vo.SyDef> syDefs){
		this.syDefs = syDefs;
	}
	public void addtoSyDefs(zrsy.vo.SyDef syDef){
		if(getSyDefs() == null) setSyDefs(new java.util.ArrayList<zrsy.vo.SyDef>());
			getSyDefs().add(syDef);
	}
	public java.util.List<zrsy.vo.PgBtn> getPgBtns() {
		return pgBtns;
	}
	public void setPgBtns(java.util.List<zrsy.vo.PgBtn> pgBtns) {
		this.pgBtns = pgBtns;
	}
	public void addtoNodes(PgBtn pgBtn) {
		if(getPgBtns() == null) setPgBtns(new ArrayList<PgBtn>());
		getPgBtns().add(pgBtn);
	}	
}