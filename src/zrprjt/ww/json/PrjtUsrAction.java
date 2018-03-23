package zrprjt.ww.json;

import gnwf.ww.workflow.WFUtil;

import java.io.File;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import zrprjt.dao.PrjtUsrDAO;
import zrprjt.facade.PrjtDefFacade;
import zrprjt.facade.PrjtTypFacade;
import zrprjt.facade.PrjtUsrFacade;
import zrprjt.facade.PrjtUsrUpRecordFacade;
import zrprjt.vo.PrjtDef;
import zrprjt.vo.PrjtRole;
import zrprjt.vo.PrjtTyp;
import zrprjt.vo.PrjtUsr;
import zrprjt.vo.PrjtUsrUpRecord;
import zrprjt.vo.json.PrjtUsrJson;
import zrprjt.vo.json.PrjtUsrUpRecordJson;
import zrprjt.ww.BasicAction;
import zrprjt.ww.MSG;
import zrsy.dao.helper.UsrHelper;
import zrsy.facade.DeptFacade;
import zrsy.facade.UsrFacade;
import zrsy.vo.Com;
import zrsy.vo.Dept;
import zrsy.vo.Usr;

import com.alibaba.fastjson.JSON;

public class PrjtUsrAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<PrjtUsr> prjtUsrs;
	private PrjtUsr prjtUsr = new PrjtUsr();
	private java.util.List<zrprjt.vo.PrjtDef> prjtDefs;
	
	private java.util.List<zrprjt.vo.PrjtRole> prjtRoles;
	private java.util.List<Com> coms;
	private java.util.List<Dept> depts;
	private List<Usr> usrs;
	
	
	private Usr usr;
	
//	private Integer comId;
//	private Integer deptId;
	
	private String prjtNo;
	
	
	private PrjtUsr oldPrjtUsr;
	
	private String isAdd;
	
	private PrjtRole prjtRole;
	
	private Integer prjtUsrId;
	
	

	public String execute() throws Exception {
		try {
			if(prjtUsr != null && prjtUsr.getRoleId() != null) {
				prjtUsr = new PrjtUsrFacade().findById(prjtUsr);
				setJson(JSON.toJSONString(prjtUsr)); 
			}
			prjtDefs = new zrprjt.facade.PrjtDefFacade().find("select "+zrprjt.vo.PrjtDef.SELF_FIELDS+" from PrjtDef",zrprjt.vo.PrjtDef.SELF_FIELDS);
			prjtRoles = new zrprjt.facade.PrjtRoleFacade().find("select "+zrprjt.vo.PrjtRole.SELF_FIELDS+" from PrjtRole where RoleTyp = 0",zrprjt.vo.PrjtRole.SELF_FIELDS);
			
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	
	
	public String getpDefs() throws Exception {
		try{
			prjtDefs = new zrprjt.facade.PrjtDefFacade().find("select "+zrprjt.vo.PrjtDef.SELF_FIELDS+" from PrjtDef",zrprjt.vo.PrjtDef.SELF_FIELDS);

		this.setJson(JSON.toJSONString(prjtDefs));
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return PGLIS;
    }
	
	public String getpRoles() throws Exception {
		
		try{
			if(prjtRole!=null&&prjtRole.getPrjtTypId()!=null){
				
				prjtRoles = new zrprjt.facade.PrjtRoleFacade().find("select "+zrprjt.vo.PrjtRole.SELF_FIELDS+" from PrjtRole where PrjtTypId = "+prjtRole.getPrjtTypId()+" and PrjtRole.roleTyp="+prjtRole.getRoleTyp(),zrprjt.vo.PrjtRole.SELF_FIELDS);
				
			}else if(prjtNo!=null){
				
				prjtRoles = new zrprjt.facade.PrjtRoleFacade().find("select "+zrprjt.vo.PrjtRole.SELF_FIELDS+" from PrjtRole where PrjtTypId = (select PrjtDef.TypId from PrjtDef where PrjtDef.PrjtNo ='"+prjtNo+"')"+" and PrjtRole.roleTyp="+prjtRole.getRoleTyp(),zrprjt.vo.PrjtRole.SELF_FIELDS);
			
			}

		this.setJson(JSON.toJSONString(prjtRoles));
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return PGLIS;
    }
	
	
	
	public String getpComs() throws Exception {
		try{
			coms = new zrsy.facade.ComFacade().find("select "+zrsy.vo.Com.SELF_FIELDS+" from Com where status=1",zrsy.vo.Com.SELF_FIELDS);
			
		this.setJson(JSON.toJSONString(coms));
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return PGLIS;
    }
	
	public String getpDepts() throws Exception {
		try{
			depts = new zrsy.facade.DeptFacade().find("select "+zrsy.vo.Dept.SELF_FIELDS+" from Dept where status=1",zrsy.vo.Dept.SELF_FIELDS);

		this.setJson(JSON.toJSONString(depts));
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return PGLIS;
    }
	
	
	
	
	public String getprjtTypes() throws Exception {
		List<PrjtTyp> prjtTyps=  new ArrayList<PrjtTyp>();
		try{
			prjtTyps = new PrjtTypFacade().find(new PrjtTyp());

		this.setJson(JSON.toJSONString(prjtTyps));
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
			return ERROR;
		}
		return PGLIS;
    }
	
	
	public String getpUsers() throws Exception {
		try{
			
			String sql = "select "+zrsy.vo.Usr.SELF_FIELDS+" from Usr where status=1";
			
			if(usr.getComId()!=null){
				sql=sql+" and Usr.ComId = "+usr.getComId();
			}
			
			if(usr.getDeptId()!=null){
				sql=sql+" and Usr.DeptId = "+usr.getDeptId();
			}
			
			usrs = new zrsy.facade.UsrFacade().find(sql,zrsy.vo.Usr.SELF_FIELDS);

		this.setJson(JSON.toJSONString(usrs));
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return PGLIS;
    }
	
	
	public String getPUsRecs() throws Exception {
		try{
			PrjtUsrUpRecord pr = new PrjtUsrUpRecord();
			pr.setPrjtUsrId(prjtUsrId);
			List<PrjtUsrUpRecord> prs =  new ArrayList<PrjtUsrUpRecord>();
			prs = new PrjtUsrUpRecordFacade().find(pr);

			PrjtUsrUpRecordJson pJson = new PrjtUsrUpRecordJson();
			pJson.Rows = prs;
			pJson.Total = prs.size();
			
			//System.out.println(JSON.toJSONString(pJson));
		this.setJson(JSON.toJSONString(pJson));
		
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return PGLIS;
    }
	
	
	
	
	
	public String add() throws Exception {
		try {
			
			String  sql = "select "+zrprjt.vo.PrjtRole.SELF_FIELDS+" from PrjtRole where 1=1 ";
				
			if(prjtUsr.getRoleTyp()!=null){
				if(prjtUsr.getRoleTyp().intValue()==1){
					sql += " and PrjtRole.RoleId not in ( select PrjtUsr.RoleId from PrjtUsr where PrjtUsr.PrjtNo = '"+prjtUsr.getPrjtNo()+"' )";
				}
				sql+=" and PrjtRole.PrjtTypId = "+prjtUsr.getPrjtTypId()+" and PrjtRole.RoleTyp = "+prjtUsr.getRoleTyp();
			}else{
				//sql += " and PrjtRole.RoleId not in ( select PrjtUsr.RoleId from PrjtUsr where PrjtUsr.PrjtTypId = 0 and PrjtUsr.PrjtNo is null"+" )";
				sql+=" and PrjtRole.PrjtTypId = "+prjtUsr.getPriority() +" and PrjtRole.RoleTyp = 0";
			}
			
			
			prjtRoles = new zrprjt.facade.PrjtRoleFacade().find(
					sql,zrprjt.vo.PrjtRole.SELF_FIELDS);
		
			
		
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	
	
	public String addMailUser() throws Exception {
	
	        return "addMailUser";
	}
	
	public String edit() throws Exception {
		try {
			
			if(prjtUsr != null) {
				String sql = "select "+PrjtUsr.ALL_FIELDS+" from PrjtUsr" +
			            " left join PrjtDef on (PrjtDef.PrjtNo = PrjtUsr.PrjtNo) " +
			            " inner join PrjtRole on (PrjtRole.RoleId = PrjtUsr.RoleId)  " +
			            " inner join Usr on (Usr.Id = PrjtUsr.UsrId) " +
			            " left join PrjtTyp on (PrjtTyp.TypId = PrjtUsr.PrjtTypId) "+
						" where PrjtUsr.Id = "+prjtUsr.getId();
				prjtUsr = new PrjtUsrFacade().findById(sql, PrjtUsr.ALL_FIELDS);
				usr = new Usr();
				usr.setId(prjtUsr.getUsrId());
				usr = new zrsy.facade.UsrFacade().findById(usr);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(prjtUsr != null && prjtUsr.getUsrId() != null) {
				prjtUsr = new PrjtUsrFacade().findBy(prjtUsr);
				//setJson(JSON.toJSONString(prjtUsr)); 
				setIsAdd("no");
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(prjtUsr == null) prjtUsr = new PrjtUsr();
			prjtUsr.setPrjtNo(null);
			int total = new PrjtUsrFacade().amount(prjtUsr);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			
			//  String sql = "select +"+ ;
			prjtUsrs = new PrjtUsrFacade().find(prjtUsr,getPageVO());
			PrjtUsrJson prjtUsrJson = new PrjtUsrJson();
			prjtUsrJson.Rows = prjtUsrs;
			prjtUsrJson.Total = total;
			setJson(JSON.toJSONString(prjtUsrJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	
	
	public String prjtUsrlist() throws Exception {
		try {
			
			//if(prjtUsr == null) prjtUsr = new PrjtUsr();
			if(prjtNo!=null&&!prjtNo.trim().isEmpty()){
				
				PrjtDef  prjtDef= new PrjtDef();
				prjtDef.setPrjtNo(prjtNo);
				prjtDef = new PrjtDefFacade().findById(prjtDef);
//				String amoutSQl = "select count(*) as amount from  PrjtUsr " +
//						" where PrjtTypId = "+prjtDef.getTypId()+" and (PrjtNo = '"+prjtNo+"' or PrjtNo is null)";
//				int total = new PrjtUsrFacade().amount(amoutSQl);
//				if(getPage() == null) {
//					setPage(1);
//					setPagesize(10);
//				}
//				getPageVO().setPage(this.getPage());
//				getPageVO().setPageSize(this.getPagesize());
//				getPageVO().setTotal(total);
				
				String sqlString =  "select "+PrjtUsr.LIST_FIELDS_FOR+" from PrjtUsr " +
						" left join PrjtDef on (PrjtDef.PrjtNo = PrjtUsr.PrjtNo) " + 
						" inner join PrjtRole on (PrjtRole.RoleId = PrjtUsr.RoleId) " + 
						" inner join Usr on (Usr.Id = PrjtUsr.UsrId) " +
						/*"INNER JOIN Dept ON Usr.OrgNo = Dept.deptid"+*/
						// " left join _AddrBook on (Usr.Id = _AddrBook.UserId) " +
						" left join PrjtTyp on (PrjtTyp.TypId = PrjtUsr.PrjtTypId) " ;
				String conDitionSQl = " where PrjtUsr.PrjtTypId = "+prjtDef.getTypId()+" and (PrjtUsr.PrjtNo = '"+prjtNo+"' or prjtUsr.PrjtNo is null) order by PrjtRole.Sort asc";
				sqlString = sqlString+conDitionSQl;
				System.out.println("sqlString"+sqlString);
//				prjtUsrs = new PrjtUsrFacade().find(getPageVO(), sqlString, conDitionSQl);
				prjtUsrs = new PrjtUsrFacade().find(sqlString, PrjtUsr.LIST_FIELDS_FOR);
				PrjtUsrJson prjtUsrJson = new PrjtUsrJson();
				prjtUsrJson.Rows = prjtUsrs;
//				prjtUsrJson.Total = total;
				prjtUsrJson.Total = prjtUsrs.size();
				System.out.println("prjtUsrJson.Total"+prjtUsrJson.Total);
				setJson(JSON.toJSONString(prjtUsrJson)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	
	/*
	 * 通过名字查询责任人
	 */
	public String selectprjtUsrlist() throws Exception {
		try {
			if(prjtNo!=null&&!prjtNo.trim().isEmpty()){
				PrjtDef  prjtDef= new PrjtDef();
				prjtDef.setPrjtNo(prjtNo);
				prjtDef = new PrjtDefFacade().findById(prjtDef);
				String sqlString =  "select "+PrjtUsr.LIST_FIELDS_FOR+" from PrjtUsr " +
						" left join PrjtDef on (PrjtDef.PrjtNo = PrjtUsr.PrjtNo) " + 
						" inner join PrjtRole on (PrjtRole.RoleId = PrjtUsr.RoleId) " + 
						" inner join Usr on (Usr.Id = PrjtUsr.UsrId) " +
						" left join PrjtTyp on (PrjtTyp.TypId = PrjtUsr.PrjtTypId) " ;
				String conDitionSQl ="";
				if (WFUtil.isNotNull(usr.getUsrName())) {
					String name = URLDecoder.decode(usr.getUsrName(), "UTF-8");
					usr.setUsrName(name);
					System.out.println("username"+name);
					String selectUsername = "  and Usr.UsrName like '"+name+"%'";
					 conDitionSQl = " where PrjtUsr.PrjtTypId = "+prjtDef.getTypId()+" and (PrjtUsr.PrjtNo = '"+prjtNo+"' or prjtUsr.PrjtNo is null) "+ selectUsername + "order by PrjtRole.Sort asc";

				}else {
					 conDitionSQl = " where PrjtUsr.PrjtTypId = "+prjtDef.getTypId()+" and (PrjtUsr.PrjtNo = '"+prjtNo+"' or prjtUsr.PrjtNo is null)  order by PrjtRole.Sort asc";

				}
				
				
				sqlString = sqlString+conDitionSQl;
				System.out.println("sqlString"+sqlString);
				prjtUsrs = new PrjtUsrFacade().find(sqlString, PrjtUsr.LIST_FIELDS_FOR);
				PrjtUsrJson prjtUsrJson = new PrjtUsrJson();
				prjtUsrJson.Rows = prjtUsrs;
				prjtUsrJson.Total = prjtUsrs.size();
				System.out.println("prjtUsrJson.Total"+prjtUsrJson.Total);
				setJson(JSON.toJSONString(prjtUsrJson)); 
				
				Map map = new HashMap();
				map.put("usrs", prjtUsrs);
				
				System.out.println(map.toString());
				
				System.out.println("JSON.toJSONString(map)"+JSON.toJSONString(map));
				this.setJson(JSON.toJSONString(map));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass())
					.error("SchCfgAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	
	/*
	 * 加载所有项目成员列表
	 */
	public String selecAllUsrlist() throws Exception {
		try {
			if(prjtNo!=null&&!prjtNo.trim().isEmpty()){
				PrjtDef  prjtDef= new PrjtDef();
				prjtDef.setPrjtNo(prjtNo);
				prjtDef = new PrjtDefFacade().findById(prjtDef);
				String sqlString =  "select "+PrjtUsr.LIST_FIELDS_FOR+" from PrjtUsr " +
						" left join PrjtDef on (PrjtDef.PrjtNo = PrjtUsr.PrjtNo) " + 
						" inner join PrjtRole on (PrjtRole.RoleId = PrjtUsr.RoleId) " + 
						" inner join Usr on (Usr.Id = PrjtUsr.UsrId) " +
						" left join PrjtTyp on (PrjtTyp.TypId = PrjtUsr.PrjtTypId) " ;
				String conDitionSQl ="";
				
					 conDitionSQl = " where PrjtUsr.PrjtTypId = "+prjtDef.getTypId()+" and (PrjtUsr.PrjtNo = '"+prjtNo+"' or prjtUsr.PrjtNo is null)  order by PrjtRole.Sort asc";

				
				
				
				sqlString = sqlString+conDitionSQl;
				System.out.println("sqlString"+sqlString);
				prjtUsrs = new PrjtUsrFacade().find(sqlString, PrjtUsr.LIST_FIELDS_FOR);
				PrjtUsrJson prjtUsrJson = new PrjtUsrJson();
				prjtUsrJson.Rows = prjtUsrs;
				prjtUsrJson.Total = prjtUsrs.size();
				System.out.println("prjtUsrJson.Total"+prjtUsrJson.Total);
				setJson(JSON.toJSONString(prjtUsrJson)); 
				
				Map map = new HashMap();
				map.put("usrs", prjtUsrs);
				
				System.out.println(map.toString());
				
				System.out.println("JSON.toJSONString(map)"+JSON.toJSONString(map));
				this.setJson(JSON.toJSONString(map));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass())
					.error("SchCfgAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	
	public String sav() throws Exception {
		try {
			
			if(prjtUsr.getId()!=null){//编辑
//				Boolean rolePriorityisExist = chekRolePriority(prjtUsr);
//				if(rolePriorityisExist){
//					setMsg("优先级为的角色已经存在！");
//					return ERROR;
//				}else{
					prjtUsr.setLastUpd(getUsrSession().getId());
					prjtUsr.setLastDate(new Date());
//					String updateSQL = "UPDATE PrjtUsr ";
//					updateSQL += " set Priority = " + prjtUsr.getPriority();
//					updateSQL += ", Status = " + prjtUsr.getStatus();
//					updateSQL += " where PrjtNo = '"+prjtUsr.getPrjtNo()+"' and RoleId = "+prjtUsr.getRoleId()+" and UsrId = "+prjtUsr.getUsrId();
					new PrjtUsrFacade().update(prjtUsr);
//				}
			}else{//添加
				Boolean isExist = new PrjtUsrFacade().chekPrjtUsrExist(prjtUsr);
				if(isExist){
					setMsg("该成员已经存在，添加项目组成员重复！");
					return ERROR;
				}else{
					
					 String sql2 = "select max(Id) as maxid from PrjtUsr "; 
					 int id = new PrjtUsrDAO().getMaxId(sql2)+1;
					prjtUsr.setId(id);
					prjtUsr.setCreateBy(getUsrSession().getId());
					prjtUsr.setCreateDate(new Date());
					prjtUsr.setLastUpd(getUsrSession().getId());
					prjtUsr.setLastDate(new Date());
					new PrjtUsrFacade().save(prjtUsr);//再添加一个新的prjtUsr
				}
			}
			
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("PrjtUsrAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	
	
	
	public String batchSavPrjtUsr() throws Exception {
		try {
			List<PrjtUsr> editPrjtUsrs = new ArrayList<PrjtUsr>();  
			if(choices!=null){
				//System.out.println(choices);
				JSONArray array = JSONArray.fromObject(choices);//先读取串数组
				Object[] fs = array.toArray();  
				//转成对像数组
				for(int i=0;i<fs.length;i++){
					JSONObject obj = JSONObject.fromObject(fs[i]);//再使用JsonObject遍历一个个的对像
					PrjtUsr f = (PrjtUsr)JSONObject.toBean(obj,zrprjt.vo.PrjtUsr.class);//指定转换的类型，但仍需要强制转化-成功
					editPrjtUsrs.add(f);
				}
		    }
			
			
			if(editPrjtUsrs != null && editPrjtUsrs.size() > 0) {
				for(int i=0; i<editPrjtUsrs.size();i++) {
					if(editPrjtUsrs.get(i) != null) {
						PrjtUsr pu = editPrjtUsrs.get(i); 
						pu.setPrjtNo(prjtUsr.getPrjtNo());
						if(pu.getId()!=null){//编辑
							pu.setLastUpd(getUsrSession().getId());
							pu.setLastDate(new Date());
								new PrjtUsrFacade().update(pu);
//							}
						 }else{//添加
							Boolean isExist = new PrjtUsrFacade().chekPrjtUsrExist(pu);
							if(isExist){
								setMsg("添加项目组成员重复！");
								return ERROR;
							}else{
								
								 String sql2 = "select max(Id) as maxid from PrjtUsr "; 
								 int id = new PrjtUsrDAO().getMaxId(sql2)+1;
								 pu.setId(id);
								 
							    
								pu.setPrjtTypId(prjtUsr.getPrjtTypId());
								pu.setRoleTyp(prjtUsr.getRoleTyp());
								pu.setPriority(1);
								pu.setStatus(1);
								 pu.setCreateBy(getUsrSession().getId());
								 pu.setCreateDate(new Date());
								 pu.setLastUpd(getUsrSession().getId());
								 pu.setLastDate(new Date());
								new PrjtUsrFacade().save(pu);//再添加一个新的prjtUsr
							}
						}
//						editPrjtUsrs.get(i).setLastUpd(getUsrSession().getId());
//						editPrjtUsrs.get(i).setLastDate(new Date());
//						new PrjtUsrFacade().remove(editPrjtUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			e.printStackTrace();
			setMsg(MSG.S_UPD);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	
	
	
	private Boolean chekRolePriority(PrjtUsr prjtUsr2) {
		// TODO Auto-generated method stub
		return null;
	}


	public String upd() throws Exception {
		try {
			//prjtUsr.setCreateBy(getSession().getUserId());
			//prjtUsr.setCreateDate(new Date());
			//prjtUsr.setLastUpd(getSession().getUserId());
			//prjtUsr.setLastUpdDate(new Date());
			new PrjtUsrFacade().update(prjtUsr);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("PrjtUsrAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(prjtUsrs != null && prjtUsrs.size() > 0) {
				for(int i=0; i<prjtUsrs.size();i++) {
					if(prjtUsrs.get(i) != null) {
						//prjtUsrs.get(i).setLastUpd(getSession().getUserId());
						//prjtUsrs.get(i).setLastUpdDate(new Date());
						new PrjtUsrFacade().submit(prjtUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(prjtUsrs != null && prjtUsrs.size() > 0) {
				for(int i=0; i<prjtUsrs.size();i++) {
					if(prjtUsrs.get(i) != null) {
						//prjtUsrs.get(i).setLastUpd(getSession().getUserId());
						//prjtUsrs.get(i).setLastUpdDate(new Date());
						new PrjtUsrFacade().submit(prjtUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			List<PrjtUsr> delPrjtUsrs = new ArrayList<PrjtUsr>(); 
			if(choices!=null){
				//System.out.println(choices);
				JSONArray array = JSONArray.fromObject(choices);//先读取串数组
				Object[] fs = array.toArray();  
				//转成对像数组
				for(int i=0;i<fs.length;i++){
					JSONObject obj = JSONObject.fromObject(fs[i]);//再使用JsonObject遍历一个个的对像
					PrjtUsr f = (PrjtUsr)JSONObject.toBean(obj,zrprjt.vo.PrjtUsr.class);//指定转换的类型，但仍需要强制转化-成功
					delPrjtUsrs.add(f);
				}
		    }
			
			if(delPrjtUsrs != null && delPrjtUsrs.size() > 0) {
				for(int i=0; i<delPrjtUsrs.size();i++) {
					if(delPrjtUsrs.get(i) != null) {
						delPrjtUsrs.get(i).setLastUpd(getUsrSession().getId());
						delPrjtUsrs.get(i).setLastDate(new Date());
						new PrjtUsrFacade().remove(delPrjtUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(prjtUsrs != null && prjtUsrs.size() > 0) {
				for(int i=0; i<prjtUsrs.size();i++) {
					if(prjtUsrs.get(i) != null) {
						//prjtUsrs.get(i).setLastUpd(getSession().getUserId());
						//prjtUsrs.get(i).setLastUpdDate(new Date());
						new PrjtUsrFacade().submit(prjtUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(prjtUsrs != null && prjtUsrs.size() > 0) {
				for(int i=0; i<prjtUsrs.size();i++) {
					if(prjtUsrs.get(i) != null) {
						//prjtUsrs.get(i).setLastUpd(getSession().getUserId());
						//prjtUsrs.get(i).setLastUpdDate(new Date());
						new PrjtUsrFacade().submit(prjtUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(prjtUsrs != null && prjtUsrs.size() > 0) {
				for(int i=0; i<prjtUsrs.size();i++) {
					if(prjtUsrs.get(i) != null) {
						//prjtUsrs.get(i).setLastUpd(getSession().getUserId());
						//prjtUsrs.get(i).setLastUpdDate(new Date());
						new PrjtUsrFacade().submit(prjtUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(prjtUsrs != null && prjtUsrs.size() > 0) {
				for(int i=0; i<prjtUsrs.size();i++) {
					if(prjtUsrs.get(i) != null) {
						//prjtUsrs.get(i).setLastUpd(getSession().getUserId());
						//prjtUsrs.get(i).setLastUpdDate(new Date());
						new PrjtUsrFacade().submit(prjtUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(prjtUsrs != null && prjtUsrs.size() > 0) {
				for(int i=0; i<prjtUsrs.size();i++) {
					if(prjtUsrs.get(i) != null){
						//prjtUsrs.get(i).setLastUpd(getSession().getUserId());
						//prjtUsrs.get(i).setLastUpdDate(new Date());
						new PrjtUsrFacade().submit(prjtUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(prjtUsrs != null && prjtUsrs.size() > 0) {
				for(int i=0; i<prjtUsrs.size();i++) {
					if(prjtUsrs.get(i) != null){
						//prjtUsrs.get(i).setLastUpd(getSession().getUserId());
						//prjtUsrs.get(i).setLastUpdDate(new Date());
						new PrjtUsrFacade().review(prjtUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(prjtUsrs != null && prjtUsrs.size() > 0) {
				for(int i=0; i<prjtUsrs.size();i++) {
					if(prjtUsrs.get(i) != null) {
						//prjtUsrs.get(i).setLastUpd(getSession().getUserId());
						//prjtUsrs.get(i).setLastUpdDate(new Date());
						new PrjtUsrFacade().review(prjtUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(prjtUsrs != null && prjtUsrs.size() > 0) {
				for(int i=0; i<prjtUsrs.size();i++) {
					if(prjtUsrs.get(i) != null) {
						//prjtUsrs.get(i).setLastUpd(getSession().getUserId());
						//prjtUsrs.get(i).setLastUpdDate(new Date());
						new PrjtUsrFacade().confirm(prjtUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(prjtUsrs != null && prjtUsrs.size() > 0) {
				for(int i=0; i<prjtUsrs.size();i++) {
					if(prjtUsrs.get(i) != null) {
						//prjtUsrs.get(i).setLastUpd(getSession().getUserId());
						//prjtUsrs.get(i).setLastUpdDate(new Date());
						new PrjtUsrFacade().confirm(prjtUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(prjtUsrs != null && prjtUsrs.size() > 0) {
					for(int i=0; i<prjtUsrs.size();i++) {
						if(prjtUsrs.get(i) != null) {
							//prjtUsrs.get(i).setLastUpd(getSession().getUserId());
							//prjtUsrs.get(i).setLastUpdDate(new Date());
							new PrjtUsrFacade().confirm(prjtUsrs.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<PrjtUsr> prjtUsrs = new PrjtUsrFacade().find(prjtUsr);
			if(prjtUsrs != null && prjtUsrs.size() > 0) {
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
					ws.addCell(new Label(index,1,"角色名称",wcformat));
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
					ws.addCell(new Label(index,1,"项目编号",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<prjtUsrs.size();i++) {
					row++;
					int m = 0;
					if(prjtUsrs.get(i).getRoleId() != null) 
						ws.addCell(new jxl.write.Number(m,row,prjtUsrs.get(i).getRoleId(),wcformat));
					m++;
					if(prjtUsrs.get(i).getUsrId() != null) 
						ws.addCell(new jxl.write.Number(m,row,prjtUsrs.get(i).getUsrId(),wcformat));
					m++;
					if(prjtUsrs.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,prjtUsrs.get(i).getStatus(),wcformat));
					m++;
					if(prjtUsrs.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,prjtUsrs.get(i).getCreateBy(),wcformat));
					m++;
					if(prjtUsrs.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,prjtUsrs.get(i).getLastUpd(),wcformat));
					m++;
					if(prjtUsrs.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,prjtUsrs.get(i).getCreateDate(),wcformat));
					m++;
					if(prjtUsrs.get(i).getLastDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,prjtUsrs.get(i).getLastDate(),wcformat));
					m++;
					if(prjtUsrs.get(i).getPrjtNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,prjtUsrs.get(i).getPrjtNo(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("PrjtUsrListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(prjtUsrs != null && prjtUsrs.size() > 0) {
				for(int i=0; i<prjtUsrs.size();i++) {
					if(prjtUsrs.get(i) != null) {
						//prjtUsrs.get(i).setLastUpd(getSession().getUserId());
						//prjtUsrs.get(i).setLastUpdDate(new Date());
						new PrjtUsrFacade().confirm(prjtUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(prjtUsrs != null && prjtUsrs.size() > 0) {
				for(int i=0; i<prjtUsrs.size();i++) {
					if(prjtUsrs.get(i) != null) {
						//prjtUsrs.get(i).setLastUpd(getSession().getUserId());
						//prjtUsrs.get(i).setLastUpdDate(new Date());
						new PrjtUsrFacade().confirm(prjtUsrs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("PrjtUsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<PrjtUsr> getPrjtUsrs() {
		return prjtUsrs;
	}
	public void setPrjtUsrs(List<PrjtUsr> prjtUsrs) {
		this.prjtUsrs = prjtUsrs;
	}
	public PrjtUsr getPrjtUsr() {
		return prjtUsr;
	}
	public void setPrjtUsr(PrjtUsr prjtUsr) {
		this.prjtUsr = prjtUsr;
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
	public java.util.List<zrprjt.vo.PrjtDef> getPrjtDefs() {
		return prjtDefs;
	}
	public void setPrjtDefs(java.util.List<zrprjt.vo.PrjtDef> prjtDefs){
		this.prjtDefs = prjtDefs;
	}
	public void addtoPrjtDefs(zrprjt.vo.PrjtDef prjtDef){
		if(getPrjtDefs() == null) setPrjtDefs(new java.util.ArrayList<zrprjt.vo.PrjtDef>());
			getPrjtDefs().add(prjtDef);
	}
	public java.util.List<zrprjt.vo.PrjtRole> getPrjtRoles() {
		return prjtRoles;
	}
	public void setPrjtRoles(java.util.List<zrprjt.vo.PrjtRole> prjtRoles) {
		this.prjtRoles = prjtRoles;
	}
	public java.util.List<Com> getComs() {
		return coms;
	}
	public void setComs(java.util.List<Com> coms) {
		this.coms = coms;
	}
	public java.util.List<Dept> getDepts() {
		return depts;
	}
	public void setDepts(java.util.List<Dept> depts) {
		this.depts = depts;
	}
	public List<Usr> getUsrs() {
		return usrs;
	}
	public void setUsrs(List<Usr> usrs) {
		this.usrs = usrs;
	}


	public String getPrjtNo() {
		return prjtNo;
	}


	public void setPrjtNo(String prjtNo) {
		this.prjtNo = prjtNo;
	}
//
//
//	public int getComId() {
//		return comId;
//	}
//
//
//	public void setComId(int comId) {
//		this.comId = comId;
//	}
//
//
//	public int getDeptId() {
//		return deptId;
//	}
//
//
//	public void setDeptId(int deptId) {
//		this.deptId = deptId;
//	}


	public Usr getUsr() {
		return usr;
	}


	public void setUsr(Usr usr) {
		this.usr = usr;
	}


	public PrjtUsr getOldPrjtUsr() {
		return oldPrjtUsr;
	}


	public void setOldPrjtUsr(PrjtUsr oldPrjtUsr) {
		this.oldPrjtUsr = oldPrjtUsr;
	}


	public String getIsAdd() {
		return isAdd;
	}


	public void setIsAdd(String isAdd) {
		this.isAdd = isAdd;
	}


	public PrjtRole getPrjtRole() {
		return prjtRole;
	}


	public void setPrjtRole(PrjtRole prjtRole) {
		this.prjtRole = prjtRole;
	}


	public Integer getPrjtUsrId() {
		return prjtUsrId;
	}


	public void setPrjtUsrId(Integer prjtUsrId) {
		this.prjtUsrId = prjtUsrId;
	}

	/** get prjt usr list*/
	public String andrPrjtUsrlist() throws Exception{
		return prjtUsrlist();
	}

}