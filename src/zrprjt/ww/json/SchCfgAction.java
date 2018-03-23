package zrprjt.ww.json;

import gnwf.facade.WfCfgFacade;
import gnwf.vo.WfCfg;

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

import zrprjt.dao.SchCfgDAO;
import zrprjt.facade.PrjtTypFacade;
import zrprjt.facade.SchCfgFacade;
import zrprjt.vo.PrjtTyp;
import zrprjt.vo.SchCfg;
import zrprjt.vo.SchWf;
import zrprjt.vo.json.SchCfgJson;
import zrprjt.ww.BasicAction;
import zrprjt.ww.MSG;

import com.alibaba.fastjson.JSON;

public class SchCfgAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<SchCfg> schCfgs;
	private SchCfg schCfg = new SchCfg();
	private java.util.List<zrprjt.vo.SchDtl> schDtls;
	private java.util.List<zrprjt.vo.SchWf> schWfs;
	private java.util.List<zrprjt.vo.Task> tasks;
	private java.util.List<zrprjt.vo.PrjtTyp> prjtTyps;
	
	
	private SchWf schWf;
	
	private List<WfCfg> workFlows;
	
	//private Boolean isSea =false;
	
	private String prjtTypJson;
	

	public String execute() throws Exception {
		try {
			if(schCfg != null && schCfg.getSchId() != null) {
				schCfg = new SchCfgFacade().findById(schCfg);
				setJson(JSON.toJSONString(schCfg)); 
			}
			
			schCfgs = new SchCfgFacade().find(new SchCfg() );
			prjtTyps = new PrjtTypFacade().find(new PrjtTyp());

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(schCfg != null && schCfg.getSchId() != null) {
				//schCfg = new SchCfgFacade().findById(schCfg);
				//setJson(JSON.toJSONString(schCfg)); 
			//}
			SchCfg _schCfg = new SchCfg();
			schCfgs = new SchCfgFacade().find(_schCfg);
			
			prjtTyps = new PrjtTypFacade().find(new PrjtTyp());

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	
	public String getWfs(){
		
		try{
			
			WfCfg wfCfg = new WfCfg();
			//wfCfg.setCateId(4);
			workFlows = new WfCfgFacade().find(wfCfg);
			//System.out.println(JSON.toJSONString(workFlows));
			this.setJson(JSON.toJSONString(workFlows));
			}
			catch(Exception e) {
				setMsg(MSG.F_SEA);
				Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
				return ERROR;
			}
			return PGLIS;
		
	}  
	public String edit() throws Exception {
		try {
			if(schCfg != null && schCfg.getSchId() != null) {
				schCfg = new SchCfgFacade().findById(schCfg);
				setJson(JSON.toJSONString(schCfg)); 
				schCfgs = new SchCfgFacade().find(new SchCfg() );
				prjtTyps = new PrjtTypFacade().find(new PrjtTyp());
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(schCfg != null && schCfg.getSchId() != null) {
				schCfg = new SchCfgFacade().findById(schCfg);
				setJson(JSON.toJSONString(schCfg)); 
			}
			WfCfg wfCfg = new WfCfg();
			wfCfg.setCateId(4);
			workFlows = new WfCfgFacade().find(wfCfg);
			
//			SchWf wf = new SchWf();
//			wf.setFlowId(16);
//			schCfg.setSchWf(wf);
//			System.out.println(schCfg.getSchWf().getFlowId());
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(schCfg == null) schCfg = new SchCfg();
			//if(!isSea){
				schCfg.setLeve(0);
			//}
			
			int total = new SchCfgFacade().amount(schCfg);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			schCfgs = new SchCfgFacade().find(schCfg,getPageVO());
			
			String findChild = "select "+SchCfg.LIST_FIELDS+ " from SchCfg left join PrjtTyp on (PrjtTyp.TypId = SchCfg.TypId) left join SchWf on (SchWf.SchId = SchCfg.SchId) left join WfCfg on (SchWf.FlowId = WfCfg.FlowId) where Parent = ";
			for(SchCfg e : schCfgs){
				List<SchCfg> children = new SchCfgFacade().find(findChild+e.getSchId()+" order By SchCfg.SchNo",SchCfg.LIST_FIELDS);
				e.setChildren(children);
			}
			SchCfgJson schCfgJson = new SchCfgJson();
			schCfgJson.Rows = schCfgs;
			schCfgJson.Total = total;
			        // System.out.println(JSON.toJSONString(schCfgJson));
			setJson(JSON.toJSONString(schCfgJson)); 
		}
		catch(Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	
	
	public String getTyps() throws Exception {
		try{
		prjtTyps = new PrjtTypFacade().find(new PrjtTyp());

		this.setJson(JSON.toJSONString(prjtTyps));
		//System.out.println(JSON.toJSONString(prjtTyps));
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return PGLIS;
    }
	
	public String getPreSchCfgs() throws Exception {
		try{
		List<SchCfg> schCfgs = new SchCfgFacade().find(schCfg);
		//System.out.println(JSON.toJSONString(schCfgs));
		this.setJson(JSON.toJSONString(schCfgs));
		//System.out.println(JSON.toJSONString(schCfgs));
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return PGLIS;
    }
	
	
	
	public String sav() throws Exception {
		try {
//			schCfg.setCreateBy(getUsrSession().getId());
//			schCfg.setCreateDate(new Date());
//			schCfg.setLastUpd(getUsrSession().getId());
//			schCfg.setLastDate(new Date());
//			schCfg.setSchDtls(schDtls);
//			schCfg.setSchWfs(schWfs);
//			schCfg.setTasks(tasks);

			if(schCfg.getSchId() == null){
				schCfg.setCreateBy(getUsrSession().getId());
				schCfg.setCreateDate(new Date());
				schCfg.setLastUpd(getUsrSession().getId());
				schCfg.setLastDate(new Date());
				if(schCfg.getParent()==null){
					schCfg.setLeve(0);
				}else{
					SchCfg sc = new SchCfg();
					sc.setSchId(schCfg.getParent());
					sc = new SchCfgFacade().findById(sc);
					schCfg.setLeve(sc.getLeve().intValue()+1);
				}
				
				 String sql2 = "select max(SchId) as maxid from SchCfg "; 
				 int schId = new SchCfgDAO().getMaxId(sql2)+1;
				 schCfg.setSchId(schId);
				 schCfg.getSchWf().setSchId(schId);
				 schCfg.getSchWf().setStatus(1);
				 schCfg.getSchWf().setCreateBy(getUsrSession().getId());
				 schCfg.getSchWf().setCreateDate(new Date());
				 schCfg.getSchWf().setLastUpd(getUsrSession().getId());
				 schCfg.getSchWf().setLastDate(new Date());
				new SchCfgFacade().save(schCfg);
			}
			else{
				schCfg.setLastUpd(getUsrSession().getId());
				schCfg.setLastDate(new Date());
				schCfg.getSchWf().setSchId(schCfg.getSchId());
				schCfg.getSchWf().setLastUpd(getUsrSession().getId());
				schCfg.getSchWf().setLastDate(new Date());
				new SchCfgFacade().update(schCfg);
			}
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("SchCfgAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//schCfg.setCreateBy(getSession().getUserId());
			//schCfg.setCreateDate(new Date());
			//schCfg.setLastUpd(getSession().getUserId());
			//schCfg.setLastUpdDate(new Date());
			new SchCfgFacade().update(schCfg);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("SchCfgAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(schCfgs != null && schCfgs.size() > 0) {
				for(int i=0; i<schCfgs.size();i++) {
					if(schCfgs.get(i) != null) {
						//schCfgs.get(i).setLastUpd(getSession().getUserId());
						//schCfgs.get(i).setLastUpdDate(new Date());
						new SchCfgFacade().submit(schCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(schCfgs != null && schCfgs.size() > 0) {
				for(int i=0; i<schCfgs.size();i++) {
					if(schCfgs.get(i) != null) {
						//schCfgs.get(i).setLastUpd(getSession().getUserId());
						//schCfgs.get(i).setLastUpdDate(new Date());
						new SchCfgFacade().submit(schCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			//System.out.println(choices);
			String[] schCfgs = choices.split(",");
			if(schCfgs != null && schCfgs.length > 0) {
				for(int i=0; i<schCfgs.length;i++) {
					if(schCfgs[i] != null) {
						//schCfgs.get(i).setLastUpd(getSession().getUserId());
						//schCfgs.get(i).setLastUpdDate(new Date());
						SchCfg sc = new SchCfg(); 
						sc.setSchId(Integer.valueOf(schCfgs[i].trim()));
						new SchCfgFacade().remove(sc);
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(schCfgs != null && schCfgs.size() > 0) {
				for(int i=0; i<schCfgs.size();i++) {
					if(schCfgs.get(i) != null) {
						//schCfgs.get(i).setLastUpd(getSession().getUserId());
						//schCfgs.get(i).setLastUpdDate(new Date());
						new SchCfgFacade().submit(schCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(schCfgs != null && schCfgs.size() > 0) {
				for(int i=0; i<schCfgs.size();i++) {
					if(schCfgs.get(i) != null) {
						//schCfgs.get(i).setLastUpd(getSession().getUserId());
						//schCfgs.get(i).setLastUpdDate(new Date());
						new SchCfgFacade().submit(schCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(schCfgs != null && schCfgs.size() > 0) {
				for(int i=0; i<schCfgs.size();i++) {
					if(schCfgs.get(i) != null) {
						//schCfgs.get(i).setLastUpd(getSession().getUserId());
						//schCfgs.get(i).setLastUpdDate(new Date());
						new SchCfgFacade().submit(schCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(schCfgs != null && schCfgs.size() > 0) {
				for(int i=0; i<schCfgs.size();i++) {
					if(schCfgs.get(i) != null) {
						//schCfgs.get(i).setLastUpd(getSession().getUserId());
						//schCfgs.get(i).setLastUpdDate(new Date());
						new SchCfgFacade().submit(schCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(schCfgs != null && schCfgs.size() > 0) {
				for(int i=0; i<schCfgs.size();i++) {
					if(schCfgs.get(i) != null){
						//schCfgs.get(i).setLastUpd(getSession().getUserId());
						//schCfgs.get(i).setLastUpdDate(new Date());
						new SchCfgFacade().submit(schCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(schCfgs != null && schCfgs.size() > 0) {
				for(int i=0; i<schCfgs.size();i++) {
					if(schCfgs.get(i) != null){
						//schCfgs.get(i).setLastUpd(getSession().getUserId());
						//schCfgs.get(i).setLastUpdDate(new Date());
						new SchCfgFacade().review(schCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(schCfgs != null && schCfgs.size() > 0) {
				for(int i=0; i<schCfgs.size();i++) {
					if(schCfgs.get(i) != null) {
						//schCfgs.get(i).setLastUpd(getSession().getUserId());
						//schCfgs.get(i).setLastUpdDate(new Date());
						new SchCfgFacade().review(schCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(schCfgs != null && schCfgs.size() > 0) {
				for(int i=0; i<schCfgs.size();i++) {
					if(schCfgs.get(i) != null) {
						//schCfgs.get(i).setLastUpd(getSession().getUserId());
						//schCfgs.get(i).setLastUpdDate(new Date());
						new SchCfgFacade().confirm(schCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(schCfgs != null && schCfgs.size() > 0) {
				for(int i=0; i<schCfgs.size();i++) {
					if(schCfgs.get(i) != null) {
						//schCfgs.get(i).setLastUpd(getSession().getUserId());
						//schCfgs.get(i).setLastUpdDate(new Date());
						new SchCfgFacade().confirm(schCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(schCfgs != null && schCfgs.size() > 0) {
					for(int i=0; i<schCfgs.size();i++) {
						if(schCfgs.get(i) != null) {
							//schCfgs.get(i).setLastUpd(getSession().getUserId());
							//schCfgs.get(i).setLastUpdDate(new Date());
							new SchCfgFacade().confirm(schCfgs.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<SchCfg> schCfgs = new SchCfgFacade().find(schCfg);
			if(schCfgs != null && schCfgs.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"项目编号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"流程ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"分类ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"序号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"级别",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"工时",wcformat));
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
					ws.addCell(new Label(index,1,"任务名称",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"备注",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<schCfgs.size();i++) {
					row++;
					int m = 0;
					if(schCfgs.get(i).getSchId() != null) 
						ws.addCell(new jxl.write.Number(m,row,schCfgs.get(i).getSchId(),wcformat));
					m++;
					if(schCfgs.get(i).getParent() != null) 
						ws.addCell(new jxl.write.Number(m,row,schCfgs.get(i).getParent(),wcformat));
					m++;
//					if(schCfgs.get(i).getFlowId() != null) 
//						ws.addCell(new jxl.write.Number(m,row,schCfgs.get(i).getFlowId(),wcformat));
//					m++;
					if(schCfgs.get(i).getTypId() != null) 
						ws.addCell(new jxl.write.Number(m,row,schCfgs.get(i).getTypId(),wcformat));
					m++;
					if(schCfgs.get(i).getSchNo() != null) 
						ws.addCell(new jxl.write.Number(m,row,schCfgs.get(i).getSchNo(),wcformat));
					m++;
					if(schCfgs.get(i).getLeve() != null) 
						ws.addCell(new jxl.write.Number(m,row,schCfgs.get(i).getLeve(),wcformat));
					m++;
					if(schCfgs.get(i).getCfgTime() != null) 
						ws.addCell(new jxl.write.Number(m,row,schCfgs.get(i).getCfgTime(),wcformat));
					m++;
					if(schCfgs.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,schCfgs.get(i).getStatus(),wcformat));
					m++;
					if(schCfgs.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,schCfgs.get(i).getCreateBy(),wcformat));
					m++;
					if(schCfgs.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,schCfgs.get(i).getLastUpd(),wcformat));
					m++;
					if(schCfgs.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,schCfgs.get(i).getCreateDate(),wcformat));
					m++;
					if(schCfgs.get(i).getLastDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,schCfgs.get(i).getLastDate(),wcformat));
					m++;
					if(schCfgs.get(i).getSchNm() != null) 
						ws.addCell(new jxl.write.Label(m,row,schCfgs.get(i).getSchNm(),wcformat));
					m++;
					if(schCfgs.get(i).getRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,schCfgs.get(i).getRemark(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("SchCfgListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(schCfgs != null && schCfgs.size() > 0) {
				for(int i=0; i<schCfgs.size();i++) {
					if(schCfgs.get(i) != null) {
						//schCfgs.get(i).setLastUpd(getSession().getUserId());
						//schCfgs.get(i).setLastUpdDate(new Date());
						new SchCfgFacade().confirm(schCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(schCfgs != null && schCfgs.size() > 0) {
				for(int i=0; i<schCfgs.size();i++) {
					if(schCfgs.get(i) != null) {
						//schCfgs.get(i).setLastUpd(getSession().getUserId());
						//schCfgs.get(i).setLastUpdDate(new Date());
						new SchCfgFacade().confirm(schCfgs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<SchCfg> getSchCfgs() {
		return schCfgs;
	}
	public void setSchCfgs(List<SchCfg> schCfgs) {
		this.schCfgs = schCfgs;
	}
	public SchCfg getSchCfg() {
		return schCfg;
	}
	public void setSchCfg(SchCfg schCfg) {
		this.schCfg = schCfg;
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
	public java.util.List<zrprjt.vo.SchDtl> getSchDtls() {
		return schDtls;
	}
	public void setSchDtls(java.util.List<zrprjt.vo.SchDtl> schDtls){
		this.schDtls = schDtls;
	}
	public void addtoSchDtls(zrprjt.vo.SchDtl schDtl){
		if(getSchDtls() == null) setSchDtls(new java.util.ArrayList<zrprjt.vo.SchDtl>());
			getSchDtls().add(schDtl);
	}
	public java.util.List<zrprjt.vo.SchWf> getSchWfs() {
		return schWfs;
	}
	public void setSchWfs(java.util.List<zrprjt.vo.SchWf> schWfs){
		this.schWfs = schWfs;
	}
	public void addtoSchWfs(zrprjt.vo.SchWf schWf){
		if(getSchWfs() == null) setSchWfs(new java.util.ArrayList<zrprjt.vo.SchWf>());
			getSchWfs().add(schWf);
	}
	public java.util.List<zrprjt.vo.Task> getTasks() {
		return tasks;
	}
	public void setTasks(java.util.List<zrprjt.vo.Task> tasks){
		this.tasks = tasks;
	}
	public void addtoTasks(zrprjt.vo.Task task){
		if(getTasks() == null) setTasks(new java.util.ArrayList<zrprjt.vo.Task>());
			getTasks().add(task);
	}
	public java.util.List<zrprjt.vo.PrjtTyp> getPrjtTyps() {
		return prjtTyps;
	}
	public void setPrjtTyps(java.util.List<zrprjt.vo.PrjtTyp> prjtTyps) {
		this.prjtTyps = prjtTyps;
	}
	public String getPrjtTypJson() {
		return prjtTypJson;
	}
	public void setPrjtTypJson(String prjtTypJson) {
		this.prjtTypJson = prjtTypJson;
	}
//	public Boolean getIsSea() {
//		return isSea;
//	}
//	public void setIsSea(Boolean isSea) {
//		this.isSea = isSea;
//	}
	public List<WfCfg> getWorkFlows() {
		return workFlows;
	}
	public void setWorkFlows(List<WfCfg> workFlows) {
		this.workFlows = workFlows;
	}
	public SchWf getSchWf() {
		return schWf;
	}
	public void setSchWf(SchWf schWf) {
		this.schWf = schWf;
	}

	/**
	 * 获取项目阶段的接口
	 * @return
	 */
	public String andrGtSchCfgs(){
		try{
			schCfgs = new SchCfgFacade().find("select " + SchCfg.SELF_FIELDS + " from SchCfg", SchCfg.SELF_FIELDS);
			SchCfgJson schCfgJson = new SchCfgJson();
			schCfgJson.Rows = schCfgs;
			schCfgJson.Total = schCfgs.size();
			System.out.println(JSON.toJSONString(schCfgJson));
			setJson(JSON.toJSONString(schCfgJson)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PGLIS;
	}
}