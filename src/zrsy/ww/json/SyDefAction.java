package zrsy.ww.json;

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

import zrsy.ww.MSG;
import zrsy.ww.BasicAction;

import zrsy.facade.BtnFacade;
import zrsy.facade.GpFacade;
import zrsy.facade.MenuFacade;
import zrsy.facade.NodeFacade;
import zrsy.facade.SyDefFacade;
import zrsy.vo.Menu;
import zrsy.vo.SyDef;
import zrsy.vo.json.SyDefJson;

public class SyDefAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<SyDef> syDefs;
	private SyDef syDef = new SyDef();
	private java.util.List<zrsy.vo.Gp> gps;
	private java.util.List<zrsy.vo.Menu> menus;
	private java.util.List<zrsy.vo.Node> nodes;

	public String execute() throws Exception {
		try {
			if(syDef != null && syDef.getSyId() != null) {
				syDef = new SyDefFacade().findById(syDef);
				setJson(JSON.toJSONString(syDef)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SyDefAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(syDef != null && syDef.getSyId() != null) {
				//syDef = new SyDefFacade().findById(syDef);
				//setJson(JSON.toJSONString(syDef)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SyDefAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(syDef != null && syDef.getSyId() != null) {
				syDef = new SyDefFacade().findById(syDef);
				setJson(JSON.toJSONString(syDef)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SyDefAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(syDef != null && syDef.getSyId() != null) {
				syDef = new SyDefFacade().findById(syDef);
				setJson(JSON.toJSONString(syDef)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SyDefAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(syDef == null) syDef = new SyDef();
			int total = new SyDefFacade().amount(syDef);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			syDefs = new SyDefFacade().find(syDef,getPageVO());
			SyDefJson syDefJson = new SyDefJson();
			syDefJson.Rows = syDefs;
			syDefJson.Total = total;
			setJson(JSON.toJSONString(syDefJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SyDefAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//syDef.setCreateBy(getSession().getUserId());
			//syDef.setCreateDate(new Date());
			//syDef.setLastUpd(getSession().getUserId());
			//syDef.setLastUpdDate(new Date());
			syDef.setGps(gps);
			syDef.setMenus(menus);
			syDef.setNodes(nodes);

			if(syDef.getSyId() == null) {
				new SyDefFacade().save(syDef);
			}
			else
				new SyDefFacade().update(syDef);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("SyDefAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//syDef.setCreateBy(getSession().getUserId());
			//syDef.setCreateDate(new Date());
			//syDef.setLastUpd(getSession().getUserId());
			//syDef.setLastUpdDate(new Date());
			new SyDefFacade().update(syDef);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("SyDefAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(syDefs != null && syDefs.size() > 0) {
				for(int i=0; i<syDefs.size();i++) {
					if(syDefs.get(i) != null) {
						//syDefs.get(i).setLastUpd(getSession().getUserId());
						//syDefs.get(i).setLastUpdDate(new Date());
						new SyDefFacade().submit(syDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("SyDefAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(syDefs != null && syDefs.size() > 0) {
				for(int i=0; i<syDefs.size();i++) {
					if(syDefs.get(i) != null) {
						//syDefs.get(i).setLastUpd(getSession().getUserId());
						//syDefs.get(i).setLastUpdDate(new Date());
						new SyDefFacade().submit(syDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("SyDefAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(syDefs != null && syDefs.size() > 0) {
				for(int i=0; i<syDefs.size();i++) {
					if(syDefs.get(i) != null) {
						//syDefs.get(i).setLastUpd(getSession().getUserId());
						//syDefs.get(i).setLastUpdDate(new Date());
						new SyDefFacade().update(syDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("SyDefAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(syDefs != null && syDefs.size() > 0) {
				for(int i=0; i<syDefs.size();i++) {
					if(syDefs.get(i) != null) {
						//syDefs.get(i).setLastUpd(getSession().getUserId());
						//syDefs.get(i).setLastUpdDate(new Date());
						new SyDefFacade().submit(syDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("SyDefAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(syDefs != null && syDefs.size() > 0) {
				for(int i=0; i<syDefs.size();i++) {
					if(syDefs.get(i) != null) {
						//syDefs.get(i).setLastUpd(getSession().getUserId());
						//syDefs.get(i).setLastUpdDate(new Date());
						new SyDefFacade().submit(syDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("SyDefAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(syDefs != null && syDefs.size() > 0) {
				for(int i=0; i<syDefs.size();i++) {
					if(syDefs.get(i) != null) {
						//syDefs.get(i).setLastUpd(getSession().getUserId());
						//syDefs.get(i).setLastUpdDate(new Date());
						new SyDefFacade().submit(syDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("SyDefAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(syDefs != null && syDefs.size() > 0) {
				for(int i=0; i<syDefs.size();i++) {
					if(syDefs.get(i) != null) {
						//syDefs.get(i).setLastUpd(getSession().getUserId());
						//syDefs.get(i).setLastUpdDate(new Date());
						new SyDefFacade().submit(syDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("SyDefAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(syDefs != null && syDefs.size() > 0) {
				for(int i=0; i<syDefs.size();i++) {
					if(syDefs.get(i) != null){
						//syDefs.get(i).setLastUpd(getSession().getUserId());
						//syDefs.get(i).setLastUpdDate(new Date());
						new SyDefFacade().submit(syDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("SyDefAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(syDefs != null && syDefs.size() > 0) {
				for(int i=0; i<syDefs.size();i++) {
					if(syDefs.get(i) != null){
						//syDefs.get(i).setLastUpd(getSession().getUserId());
						//syDefs.get(i).setLastUpdDate(new Date());
						new SyDefFacade().review(syDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("SyDefAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(syDefs != null && syDefs.size() > 0) {
				for(int i=0; i<syDefs.size();i++) {
					if(syDefs.get(i) != null) {
						//syDefs.get(i).setLastUpd(getSession().getUserId());
						//syDefs.get(i).setLastUpdDate(new Date());
						new SyDefFacade().review(syDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("SyDefAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(syDefs != null && syDefs.size() > 0) {
				for(int i=0; i<syDefs.size();i++) {
					if(syDefs.get(i) != null) {
						//syDefs.get(i).setLastUpd(getSession().getUserId());
						//syDefs.get(i).setLastUpdDate(new Date());
						new SyDefFacade().confirm(syDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("SyDefAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(syDefs != null && syDefs.size() > 0) {
				for(int i=0; i<syDefs.size();i++) {
					if(syDefs.get(i) != null) {
						//syDefs.get(i).setLastUpd(getSession().getUserId());
						//syDefs.get(i).setLastUpdDate(new Date());
						new SyDefFacade().confirm(syDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("SyDefAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(syDefs != null && syDefs.size() > 0) {
					for(int i=0; i<syDefs.size();i++) {
						if(syDefs.get(i) != null) {
							//syDefs.get(i).setLastUpd(getSession().getUserId());
							//syDefs.get(i).setLastUpdDate(new Date());
							new SyDefFacade().confirm(syDefs.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("SyDefAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<SyDef> syDefs = new SyDefFacade().find(syDef);
			if(syDefs != null && syDefs.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"系统ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"系统名称",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"系统描述",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<syDefs.size();i++) {
					row++;
					int m = 0;
					if(syDefs.get(i).getSyId() != null) 
						ws.addCell(new jxl.write.Number(m,row,syDefs.get(i).getSyId(),wcformat));
					m++;
					if(syDefs.get(i).getSyName() != null) 
						ws.addCell(new jxl.write.Label(m,row,syDefs.get(i).getSyName(),wcformat));
					m++;
					if(syDefs.get(i).getSyText() != null) 
						ws.addCell(new jxl.write.Label(m,row,syDefs.get(i).getSyText(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("SyDefListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(syDefs != null && syDefs.size() > 0) {
				for(int i=0; i<syDefs.size();i++) {
					if(syDefs.get(i) != null) {
						//syDefs.get(i).setLastUpd(getSession().getUserId());
						//syDefs.get(i).setLastUpdDate(new Date());
						new SyDefFacade().confirm(syDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("SyDefAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
    public String dow() throws Exception {
		try {
			if(syDefs != null && syDefs.size() > 0) {
				for(int i=0; i<syDefs.size();i++) {
					if(syDefs.get(i) != null) {
						//syDefs.get(i).setLastUpd(getSession().getUserId());
						//syDefs.get(i).setLastUpdDate(new Date());
						new SyDefFacade().confirm(syDefs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("SyDefAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
    public String ini() throws Exception {
		try {
			if(syDefs != null && syDefs.size() > 0) {
				for(int i=0; i<syDefs.size();i++) {
					if(syDefs.get(i) != null) {
						//syDefs.get(i).setLastUpd(getSession().getUserId());
						//syDefs.get(i).setLastUpdDate(new Date());
						
						if(!syDefs.get(i).getSyId().equals(1)) {

							//初始化菜单
							MenuFacade facade = new MenuFacade();
							
							facade.remove("delete A from GpSy A Where A.SyId = "+syDefs.get(i).getSyId());
							facade.remove("delete A from GpBtn A inner join Gp on(A.GpId = Gp.GpId) where Gp.SyId = "+syDefs.get(i).getSyId());
							facade.remove("delete A from GpNode A inner join Gp on(A.GpId = Gp.GpId) where Gp.SyId = "+syDefs.get(i).getSyId());
							facade.remove("delete A from GpMenu A inner join Gp on(A.GpId = Gp.GpId) where Gp.SyId = "+syDefs.get(i).getSyId());
							facade.remove("delete A from GpUsr A inner join Gp on(A.GpId = Gp.GpId) where Gp.SyId = "+syDefs.get(i).getSyId());
							facade.remove("delete A from PgBtn A inner join Node on(A.NodeId = Node.Id) where Node.SyId = "+syDefs.get(i).getSyId());
							
							facade.remove("delete from Gp where SyId = "+syDefs.get(i).getSyId());
							facade.remove("delete from Btn where SyId = "+syDefs.get(i).getSyId());
							facade.remove("delete from Node where SyId = "+syDefs.get(i).getSyId());
							facade.remove("delete from Menu where SyId = "+syDefs.get(i).getSyId());

							facade.update("insert into Menu(SyId,Parent,Text,Width,Click,Icon,Leve,Sort) " +
									" select "+syDefs.get(i).getSyId()+" as SyId,Parent,Text,Width,Click,Icon,Leve,Sort from Menu " +
									" where SyId = 1 ");
							
							Menu _menu0 = facade.findById("select " + Menu.SELF_FIELDS + " from Menu " +
									" where Parent = 0 and SyId = "+syDefs.get(i).getSyId(), Menu.SELF_FIELDS);
							
							facade.update("update Menu set Parent = " +_menu0.getId() +
									" from Menu where Parent = 1 and SyId = "+syDefs.get(i).getSyId());
							
							Menu _menu1 = facade.findById("select " + Menu.SELF_FIELDS + " from Menu " +
									" where SyId = "+syDefs.get(i).getSyId() + " " +
									" and Text = '程序'", Menu.SELF_FIELDS);
							
							Menu _menu2 = facade.findById("select " + Menu.SELF_FIELDS + " from Menu " +
									" where SyId = "+syDefs.get(i).getSyId() + " " +
									" and Text = '权限管理'", Menu.SELF_FIELDS);
							
							Menu _menu3 = facade.findById("select " + Menu.SELF_FIELDS + " from Menu " +
									" where SyId = "+syDefs.get(i).getSyId() + " " +
									" and Text = '系统配置'", Menu.SELF_FIELDS);
							
//							Menu _menu4 = facade.findById("select " + Menu.SELF_FIELDS + " from Menu " +
//									" where SyId = "+syDefs.get(i).getSyId() + " " +
//									" and Text = '我的控制台'", Menu.SELF_FIELDS);
							
							facade.update("update Menu set Parent = " +_menu1.getId() +
									" from Menu where Parent = 3 and SyId = "+syDefs.get(i).getSyId());

							//初始化按钮
							new BtnFacade().update("insert into Btn(SyId,Text,Click,Icon,Line,Disable,Sort) " +
									" select "+syDefs.get(i).getSyId()+" as SyId,Text,Click,Icon,Line,Disable,Sort from Btn" +
									" where SyId = 1 ");

							//初始化功能
							new NodeFacade().update("insert into Node(SyId,Parent,MenuId,Text,Url,NodeWidth,CheckBox,Leve,Sort) " +
									" select "+syDefs.get(i).getSyId()+" as SyId,Parent,MenuId,Text,Url,NodeWidth,CheckBox,Leve,Sort from Node " +
									" where SyId = 1 order by Sort ");

							facade.update("update Node set MenuId = " +_menu2.getId() +
									" from Node where MenuId = 7 and SyId = "+syDefs.get(i).getSyId());
							
							facade.update("update Node set MenuId = " +_menu3.getId() +
									" from Node where MenuId = 6 and SyId = "+syDefs.get(i).getSyId());
							
//							facade.update("update Node set MenuId = " +_menu4.getId() +
//									" from Node where MenuId = 92 and SyId = "+syDefs.get(i).getSyId());
							
							//初始化用户组
							new GpFacade().update("insert into Gp(SyId,GpName,Remark) " +
									" select "+syDefs.get(i).getSyId()+" as SyId,GpName,Remark from Gp " +
									" where SyId = 1 ");
							
							//建立用户组菜单
							facade.update("insert into GpMenu(GpId,MenuId)" +
									" select distinct Gp.GpId,Menu.Id as MenuId from Gp,Menu " +
									" where Gp.SyId = "+syDefs.get(i).getSyId()+" " +
									"	and Menu.SyId = "+syDefs.get(i).getSyId()+"");
							
							//建立用户组功能
							facade.update("insert into GpNode(GpId,NodeId)" +
									" select distinct Gp.GpId,Node.Id as NodeId from Gp,Node " +
									" where Gp.SyId = "+syDefs.get(i).getSyId()+" " +
									"	and Node.SyId = "+syDefs.get(i).getSyId()+"");
							
							//建立用户组按钮
							facade.update("insert into GpBtn(GpId,NodeId,BtnId)" +
									" select distinct Gp.GpId,Node.Id as NodeId,Btn.Id as BtnId from Gp,Node,Btn " +
									" where Gp.SyId = "+syDefs.get(i).getSyId()+
									"	and Node.SyId = "+syDefs.get(i).getSyId()+
									"	and Btn.SyId = "+syDefs.get(i).getSyId());
							
							//建立功能按钮
							facade.update("insert into PgBtn(NodeId,BtnId,PgTyp)" +
									" select distinct Node.Id as NodeId,Btn.Id as BtnId,0 as PgTyp from Node,Btn " +
									" where Node.SyId = "+syDefs.get(i).getSyId()+
									"	and Btn.SyId = "+syDefs.get(i).getSyId() +
									" 	and Btn.Sort < 5 " +
									"	and Btn.Sort > 0");
							
							facade.update("insert into PgBtn(NodeId,BtnId,PgTyp)" +
									" select distinct Node.Id as NodeId,Btn.Id as BtnId,1 as PgTyp from Node,Btn " +
									" where Node.SyId = "+syDefs.get(i).getSyId()+
									"	and Btn.SyId = "+syDefs.get(i).getSyId() +
									" 	and Btn.Sort < 2 " +
									"	and Btn.Sort > -1");
							
							//建立超级用户权限
							facade.update("insert into GpUsr(GpId,UsrId)" +
									" select distinct Gp.GpId,1 as UsrId from Gp " +
									" where Gp.SyId = "+syDefs.get(i).getSyId());
							
							//建立超级用户系统访问权限
							facade.update("insert into GpSy(GpId,SyId)" +
									" select distinct Gp.GpId,"+syDefs.get(i).getSyId()+" as SyId from Gp " +
									" where Gp.SyId = "+syDefs.get(i).getSyId());
						}
					}
				}
			}
			setMsg("初始化完成。");
		}
		catch(Exception e) {
			setMsg("初始化失败。");
			Logger.getLogger(this.getClass()).error("SyDefAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<SyDef> getSyDefs() {
		return syDefs;
	}
	public void setSyDefs(List<SyDef> syDefs) {
		this.syDefs = syDefs;
	}
	public SyDef getSyDef() {
		return syDef;
	}
	public void setSyDef(SyDef syDef) {
		this.syDef = syDef;
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
	public java.util.List<zrsy.vo.Gp> getGps() {
		return gps;
	}
	public void setGps(java.util.List<zrsy.vo.Gp> gps){
		this.gps = gps;
	}
	public void addtoGps(zrsy.vo.Gp gp){
		if(getGps() == null) setGps(new java.util.ArrayList<zrsy.vo.Gp>());
			getGps().add(gp);
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
	public java.util.List<zrsy.vo.Node> getNodes() {
		return nodes;
	}
	public void setNodes(java.util.List<zrsy.vo.Node> nodes){
		this.nodes = nodes;
	}
	public void addtoNodes(zrsy.vo.Node node){
		if(getNodes() == null) setNodes(new java.util.ArrayList<zrsy.vo.Node>());
			getNodes().add(node);
	}

}