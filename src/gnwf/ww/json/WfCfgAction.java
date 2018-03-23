package gnwf.ww.json;

import gnwf.facade.WfCateFacade;
import gnwf.facade.WfCfgFacade;
import gnwf.facade.WfCfgRelateFacade;
import gnwf.facade.WfDocFacade;
import gnwf.facade.WfLeaderFacade;
import gnwf.vo.WfCate;
import gnwf.vo.WfCfg;
import gnwf.vo.WfCfgRelate;
import gnwf.vo.WfDoc;
import gnwf.vo.WfLeader;
import gnwf.vo.json.WfCfgJson;
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

import zrsy.facade.ComFacade;
import zrsy.facade.DeptFacade;
import zrsy.vo.Com;
import zrsy.vo.Dept;

import com.alibaba.fastjson.JSON;

public class WfCfgAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfCfg> wfCfgs;
	private WfCfg wfCfg = new WfCfg();
	private java.util.List<gnwf.vo.WfDept> wfDepts;
	private java.util.List<gnwf.vo.WfField> wfFields;
	private java.util.List<gnwf.vo.WfAgent> wfAgents;
	private java.util.List<gnwf.vo.WfLeader> wfLeaders;
	private java.util.List<gnwf.vo.WfStep> wfSteps;
	private java.util.List<gnwf.vo.WfCfgRelate> wfRelates;
	private java.util.List<gnwf.vo.WfJob> wfJobs;
	private java.util.List<gnwf.vo.WfCate> wfCates;
	private List<WfCate> cates;
	
	private List<WfCate> cateList;
	private List<Com> companyList;
	private List<Dept> deptList;
	private List<WfCfg> workFlows;
//	private List<WfCfgRelate> relateFlows;
//	private List<BaDept> wfDepts;
	private List<WfDoc>	docList;			//附件集
	
	private List<File> files;

	public String execute() throws Exception {
		try {
			WfCate cate = new WfCate();
			cate.setStatus(MSG.CONST_STATUS_1);
			cates = new WfCateFacade().findAll(cate);
			
			if(wfCfg != null && wfCfg.getFlowId() != null) {
				wfCfg = new WfCfgFacade().findById(wfCfg);
				setJson(JSON.toJSONString(wfCfg)); 
			}
			wfCates = new gnwf.facade.WfCateFacade().find("select "+gnwf.vo.WfCate.SELF_FIELDS+" from WfCate",gnwf.vo.WfCate.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfCfgAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//分类
			cateList = new WfCateFacade().find("select CateId,CateName from WfCate where status="+MSG.CONST_STATUS_1,"CateId,CateName");
			//wfCates = new gnwf.facade.WfCateFacade().find("select "+gnwf.vo.WfCate.SELF_FIELDS+" from WfCate",gnwf.vo.WfCate.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfCfgAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	
	//TODO
	public String edit() throws Exception {
		try {
			//分类
			cateList = new WfCateFacade().find("select CateId,CateName from WfCate where status="+MSG.CONST_STATUS_1,"CateId,CateName");
			
			//公司
			companyList = new ComFacade().find("select ComId,ComNm from com where status="+MSG.CONST_STATUS_1,"ComId,ComNm");
		
			//相关部门
			String deptSql = "select dept.DeptId,dept.Parent,dept.DeptNm,a.DeptId as ischecked from dept " +
					"left join(select DeptId from WfDept where flowId="+wfCfg.getFlowId()+")a on(dept.DeptId=a.DeptId) " +
					"where dept.leve = 1 and dept.status="+MSG.CONST_STATUS_1;
			deptList = new DeptFacade().find(deptSql,"DeptId,Parent,DeptNm,ischecked");
			
			for(int i=0; i<companyList.size(); i++) {
				for(int j=0; j<deptList.size(); j++) {
					if(deptList.get(j).getParent().equals(companyList.get(i).getComId())) {
						companyList.get(i).addtoDepts(deptList.get(j));
					}
				}
			}
			
			//相关工作流
			workFlows = new WfCfgFacade().find("select FlowId,FlowName from WfCfg where status=1 and flowId!="+wfCfg.getFlowId(), "FlowId,FlowName");
			WfCfgRelate relate = new WfCfgRelate();
			relate.setFlowId(wfCfg.getFlowId());
			wfRelates = new WfCfgRelateFacade().findAll(relate);
			
			for(int i=0; i<workFlows.size(); i++) {
				for(int j=0; j<wfRelates.size(); j++) {
					if(workFlows.get(i).getFlowId().equals(wfRelates.get(j).getRelateId())) {
						workFlows.get(i).setChecked("checked");
					}
				}
			}
			
			//主导人列表
			WfLeader leader = new WfLeader();
			leader.setFlowId(wfCfg.getFlowId());
			wfLeaders = new WfLeaderFacade().findAll(leader);
			
			//附件
			WfDoc doc = new WfDoc();
			doc.setFlowId(wfCfg.getFlowId());
			docList = new WfDocFacade().findAll(doc);
			
			
			if(wfCfg != null && wfCfg.getFlowId() != null) {
				wfCfg = new WfCfgFacade().findById(wfCfg);
				setJson(JSON.toJSONString(wfCfg)); 
			}
			wfCates = new gnwf.facade.WfCateFacade().find("select "+gnwf.vo.WfCate.SELF_FIELDS+" from WfCate",gnwf.vo.WfCate.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfCfgAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		return edit();
	}
	public String list() throws Exception {
		try {
			WfCate cate = new WfCate();
			cate.setStatus(MSG.CONST_STATUS_1);
			cates = new WfCateFacade().findAll(cate);
			
			if(wfCfg == null) wfCfg = new WfCfg();
			int total = new WfCfgFacade().amount(wfCfg);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfCfgs = new WfCfgFacade().find(wfCfg,getPageVO());
			WfCfgJson wfCfgJson = new WfCfgJson();
			wfCfgJson.Rows = wfCfgs;
			wfCfgJson.Total = total;
			setJson(JSON.toJSONString(wfCfgJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfCfgAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	
	//TODO
	public String sav() throws Exception {
		try {
			wfCfg.setCreateBy(getUsrSession().getId());
			wfCfg.setCreateDate(new Date());
			wfCfg.setLastUpd(getUsrSession().getId());
			wfCfg.setLastUpdDate(new Date());
			wfCfg.setWfDepts(wfDepts);
			wfCfg.setWfRelates(wfRelates);
			wfCfg.setWfLeaders(wfLeaders);
//			wfCfg.setWfFields(wfFields);
//			wfCfg.setWfAgents(wfAgents);
//			wfCfg.setWfSteps(wfSteps);
//			wfCfg.setWfJobs(wfJobs);

			if(wfCfg.getFlowId() == null){
				new WfCfgFacade().save(wfCfg);
			}else{
				new WfCfgFacade().update(wfCfg);
			}
				
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfCfgAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			System.out.println("upd----------");
			
			//wfCfg.setCreateBy(getSession().getUserId());
			//wfCfg.setCreateDate(new Date());
			//wfCfg.setLastUpd(getSession().getUserId());
			//wfCfg.setLastUpdDate(new Date());
			new WfCfgFacade().update(wfCfg);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfCfgAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfCfgs != null && wfCfgs.size() > 0) {
				for(int i=0; i<wfCfgs.size();i++) {
					if(wfCfgs.get(i) != null) {
						//wfCfgs.get(i).setLastUpd(getSession().getUserId());
						//wfCfgs.get(i).setLastUpdDate(new Date());
						new WfCfgFacade().submit(wfCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfCfgs != null && wfCfgs.size() > 0) {
				for(int i=0; i<wfCfgs.size();i++) {
					if(wfCfgs.get(i) != null) {
						//wfCfgs.get(i).setLastUpd(getSession().getUserId());
						//wfCfgs.get(i).setLastUpdDate(new Date());
						new WfCfgFacade().submit(wfCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfCfgs != null && wfCfgs.size() > 0) {
				for(int i=0; i<wfCfgs.size();i++) {
					if(wfCfgs.get(i) != null) {
						//wfCfgs.get(i).setLastUpd(getSession().getUserId());
						//wfCfgs.get(i).setLastUpdDate(new Date());
						new WfCfgFacade().update(wfCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfCfgs != null && wfCfgs.size() > 0) {
				for(int i=0; i<wfCfgs.size();i++) {
					if(wfCfgs.get(i) != null) {
						//wfCfgs.get(i).setLastUpd(getSession().getUserId());
						//wfCfgs.get(i).setLastUpdDate(new Date());
						new WfCfgFacade().submit(wfCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfCfgs != null && wfCfgs.size() > 0) {
				for(int i=0; i<wfCfgs.size();i++) {
					if(wfCfgs.get(i) != null) {
						//wfCfgs.get(i).setLastUpd(getSession().getUserId());
						//wfCfgs.get(i).setLastUpdDate(new Date());
						new WfCfgFacade().submit(wfCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfCfgs != null && wfCfgs.size() > 0) {
				for(int i=0; i<wfCfgs.size();i++) {
					if(wfCfgs.get(i) != null) {
						//wfCfgs.get(i).setLastUpd(getSession().getUserId());
						//wfCfgs.get(i).setLastUpdDate(new Date());
						new WfCfgFacade().submit(wfCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfCfgs != null && wfCfgs.size() > 0) {
				for(int i=0; i<wfCfgs.size();i++) {
					if(wfCfgs.get(i) != null) {
						//wfCfgs.get(i).setLastUpd(getSession().getUserId());
						//wfCfgs.get(i).setLastUpdDate(new Date());
						new WfCfgFacade().submit(wfCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfCfgs != null && wfCfgs.size() > 0) {
				for(int i=0; i<wfCfgs.size();i++) {
					if(wfCfgs.get(i) != null){
						//wfCfgs.get(i).setLastUpd(getSession().getUserId());
						//wfCfgs.get(i).setLastUpdDate(new Date());
						new WfCfgFacade().submit(wfCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfCfgs != null && wfCfgs.size() > 0) {
				for(int i=0; i<wfCfgs.size();i++) {
					if(wfCfgs.get(i) != null){
						//wfCfgs.get(i).setLastUpd(getSession().getUserId());
						//wfCfgs.get(i).setLastUpdDate(new Date());
						new WfCfgFacade().review(wfCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfCfgs != null && wfCfgs.size() > 0) {
				for(int i=0; i<wfCfgs.size();i++) {
					if(wfCfgs.get(i) != null) {
						//wfCfgs.get(i).setLastUpd(getSession().getUserId());
						//wfCfgs.get(i).setLastUpdDate(new Date());
						new WfCfgFacade().review(wfCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfCfgs != null && wfCfgs.size() > 0) {
				for(int i=0; i<wfCfgs.size();i++) {
					if(wfCfgs.get(i) != null) {
						//wfCfgs.get(i).setLastUpd(getSession().getUserId());
						//wfCfgs.get(i).setLastUpdDate(new Date());
						new WfCfgFacade().confirm(wfCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfCfgs != null && wfCfgs.size() > 0) {
				for(int i=0; i<wfCfgs.size();i++) {
					if(wfCfgs.get(i) != null) {
						//wfCfgs.get(i).setLastUpd(getSession().getUserId());
						//wfCfgs.get(i).setLastUpdDate(new Date());
						new WfCfgFacade().confirm(wfCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfCfgs != null && wfCfgs.size() > 0) {
					for(int i=0; i<wfCfgs.size();i++) {
						if(wfCfgs.get(i) != null) {
							//wfCfgs.get(i).setLastUpd(getSession().getUserId());
							//wfCfgs.get(i).setLastUpdDate(new Date());
							new WfCfgFacade().confirm(wfCfgs.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfCfgAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfCfg> wfCfgs = new WfCfgFacade().find(wfCfg);
			if(wfCfgs != null && wfCfgs.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"工作流配置ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"公司ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"部门ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"流程分类",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"是否创建第一步骤",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"适用范围（0:所有看，1：本人）",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"权限",wcformat));
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
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"创建日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"更新日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"工作流描述",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"工作程名称",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"工作流URI配置",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"打印URI配置",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"特殊流程",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"工作流编码",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfCfgs.size();i++) {
					row++;
					int m = 0;
					if(wfCfgs.get(i).getFlowId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfCfgs.get(i).getFlowId(),wcformat));
					m++;
					if(wfCfgs.get(i).getComId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfCfgs.get(i).getComId(),wcformat));
					m++;
					if(wfCfgs.get(i).getDeptId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfCfgs.get(i).getDeptId(),wcformat));
					m++;
					if(wfCfgs.get(i).getCateId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfCfgs.get(i).getCateId(),wcformat));
					m++;
					if(wfCfgs.get(i).getIsFirstStep() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfCfgs.get(i).getIsFirstStep(),wcformat));
					m++;
					if(wfCfgs.get(i).getSphere() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfCfgs.get(i).getSphere(),wcformat));
					m++;
					if(wfCfgs.get(i).getRelateId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfCfgs.get(i).getRelateId(),wcformat));
					m++;
					if(wfCfgs.get(i).getLimit() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfCfgs.get(i).getLimit(),wcformat));
					m++;
					if(wfCfgs.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfCfgs.get(i).getStatus(),wcformat));
					m++;
					if(wfCfgs.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfCfgs.get(i).getCreateBy(),wcformat));
					m++;
					if(wfCfgs.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfCfgs.get(i).getLastUpd(),wcformat));
					m++;
					if(wfCfgs.get(i).getHasQuesMoudle() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfCfgs.get(i).getHasQuesMoudle(),wcformat));
					m++;
					if(wfCfgs.get(i).getScheCfgId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfCfgs.get(i).getScheCfgId(),wcformat));
					m++;
					if(wfCfgs.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfCfgs.get(i).getCreateDate(),wcformat));
					m++;
					if(wfCfgs.get(i).getLastUpdDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfCfgs.get(i).getLastUpdDate(),wcformat));
					m++;
					if(wfCfgs.get(i).getFlowDesc() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfCfgs.get(i).getFlowDesc(),wcformat));
					m++;
					if(wfCfgs.get(i).getFlowName() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfCfgs.get(i).getFlowName(),wcformat));
					m++;
					if(wfCfgs.get(i).getFlowUri() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfCfgs.get(i).getFlowUri(),wcformat));
					m++;
					if(wfCfgs.get(i).getPrintUri() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfCfgs.get(i).getPrintUri(),wcformat));
					m++;
					if(wfCfgs.get(i).getSpecialClass() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfCfgs.get(i).getSpecialClass(),wcformat));
					m++;
					if(wfCfgs.get(i).getAddRdExtendUri() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfCfgs.get(i).getAddRdExtendUri(),wcformat));
					m++;
					if(wfCfgs.get(i).getFlowCode() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfCfgs.get(i).getFlowCode(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfCfgListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfCfgs != null && wfCfgs.size() > 0) {
				for(int i=0; i<wfCfgs.size();i++) {
					if(wfCfgs.get(i) != null) {
						//wfCfgs.get(i).setLastUpd(getSession().getUserId());
						//wfCfgs.get(i).setLastUpdDate(new Date());
						new WfCfgFacade().confirm(wfCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfCfgAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfCfgs != null && wfCfgs.size() > 0) {
				for(int i=0; i<wfCfgs.size();i++) {
					if(wfCfgs.get(i) != null) {
						//wfCfgs.get(i).setLastUpd(getSession().getUserId());
						//wfCfgs.get(i).setLastUpdDate(new Date());
						new WfCfgFacade().confirm(wfCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfCfg> getWfCfgs() {
		return wfCfgs;
	}
	public void setWfCfgs(List<WfCfg> wfCfgs) {
		this.wfCfgs = wfCfgs;
	}
	public WfCfg getWfCfg() {
		return wfCfg;
	}
	public void setWfCfg(WfCfg wfCfg) {
		this.wfCfg = wfCfg;
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
	public java.util.List<gnwf.vo.WfDept> getWfDepts() {
		return wfDepts;
	}
	public void setWfDepts(java.util.List<gnwf.vo.WfDept> wfDepts){
		this.wfDepts = wfDepts;
	}
	public void addtoWfDepts(gnwf.vo.WfDept wfDept){
		if(getWfDepts() == null) setWfDepts(new java.util.ArrayList<gnwf.vo.WfDept>());
			getWfDepts().add(wfDept);
	}
	public java.util.List<gnwf.vo.WfField> getWfFields() {
		return wfFields;
	}
	public void setWfFields(java.util.List<gnwf.vo.WfField> wfFields){
		this.wfFields = wfFields;
	}
	public void addtoWfFields(gnwf.vo.WfField wfField){
		if(getWfFields() == null) setWfFields(new java.util.ArrayList<gnwf.vo.WfField>());
			getWfFields().add(wfField);
	}
	public java.util.List<gnwf.vo.WfAgent> getWfAgents() {
		return wfAgents;
	}
	public void setWfAgents(java.util.List<gnwf.vo.WfAgent> wfAgents){
		this.wfAgents = wfAgents;
	}
	public void addtoWfAgents(gnwf.vo.WfAgent wfAgent){
		if(getWfAgents() == null) setWfAgents(new java.util.ArrayList<gnwf.vo.WfAgent>());
			getWfAgents().add(wfAgent);
	}
	public java.util.List<gnwf.vo.WfLeader> getWfLeaders() {
		return wfLeaders;
	}
	public void setWfLeaders(java.util.List<gnwf.vo.WfLeader> wfLeaders){
		this.wfLeaders = wfLeaders;
	}
	public void addtoWfLeaders(gnwf.vo.WfLeader wfLeader){
		if(getWfLeaders() == null) setWfLeaders(new java.util.ArrayList<gnwf.vo.WfLeader>());
			getWfLeaders().add(wfLeader);
	}
	public java.util.List<gnwf.vo.WfStep> getWfSteps() {
		return wfSteps;
	}
	public void setWfSteps(java.util.List<gnwf.vo.WfStep> wfSteps){
		this.wfSteps = wfSteps;
	}
	public void addtoWfSteps(gnwf.vo.WfStep wfStep){
		if(getWfSteps() == null) setWfSteps(new java.util.ArrayList<gnwf.vo.WfStep>());
			getWfSteps().add(wfStep);
	}
	
	public java.util.List<gnwf.vo.WfCfgRelate> getWfRelates() {
		return wfRelates;
	}
	public void setWfRelates(java.util.List<gnwf.vo.WfCfgRelate> wfRelates) {
		this.wfRelates = wfRelates;
	}
	public void addtoWfRelates(gnwf.vo.WfCfgRelate wfRelate){
		if(getWfRelates() == null) setWfRelates(new java.util.ArrayList<gnwf.vo.WfCfgRelate>());
			getWfRelates().add(wfRelate);
	}
	public java.util.List<gnwf.vo.WfJob> getWfJobs() {
		return wfJobs;
	}
	public void setWfJobs(java.util.List<gnwf.vo.WfJob> wfJobs){
		this.wfJobs = wfJobs;
	}
	public void addtoWfJobs(gnwf.vo.WfJob wfJob){
		if(getWfJobs() == null) setWfJobs(new java.util.ArrayList<gnwf.vo.WfJob>());
			getWfJobs().add(wfJob);
	}
	public java.util.List<gnwf.vo.WfCate> getWfCates() {
		return wfCates;
	}
	public void setWfCates(java.util.List<gnwf.vo.WfCate> wfCates){
		this.wfCates = wfCates;
	}
	public void addtoWfCates(gnwf.vo.WfCate wfCate){
		if(getWfCates() == null) setWfCates(new java.util.ArrayList<gnwf.vo.WfCate>());
			getWfCates().add(wfCate);
	}
	public List<WfCate> getCates() {
		return cates;
	}
	public void setCates(List<WfCate> cates) {
		this.cates = cates;
	}
	public List<WfCate> getCateList() {
		return cateList;
	}
	public void setCateList(List<WfCate> cateList) {
		this.cateList = cateList;
	}
	public List<Com> getCompanyList() {
		return companyList;
	}
	public void setCompanyList(List<Com> companyList) {
		this.companyList = companyList;
	}
	public List<Dept> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<Dept> deptList) {
		this.deptList = deptList;
	}
	public List<WfCfg> getWorkFlows() {
		return workFlows;
	}
	public void setWorkFlows(List<WfCfg> workFlows) {
		this.workFlows = workFlows;
	}
	public List<WfDoc> getDocList() {
		return docList;
	}
	public void setDocList(List<WfDoc> docList) {
		this.docList = docList;
	}
	public List<File> getFiles() {
		return files;
	}
	public void setFiles(List<File> files) {
		this.files = files;
	}

}